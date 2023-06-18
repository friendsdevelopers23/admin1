package com.calsoft.pos.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.calsoft.pos.kafka.producer.CalsoftEventProducer;
import com.calsoft.notification.vo.NotificationVO;
import com.calsoft.pos.config.OrderConfig;
import com.calsoft.pos.model.CashRegister;
import com.calsoft.pos.model.CashRegisterTransactions;
import com.calsoft.pos.model.CoreConfigData;
import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.cart.SalesFlatQuote;
import com.calsoft.pos.model.coupon.SalesRule;
import com.calsoft.pos.model.coupon.SalesRuleCoupon;
import com.calsoft.pos.model.coupon.SalesRuleCustomer;
import com.calsoft.pos.model.order.SalesFlatOrder;
import com.calsoft.pos.model.order.SalesFlatOrderAddress;
import com.calsoft.pos.model.order.SalesFlatOrderGrid;
import com.calsoft.pos.model.order.SalesFlatOrderItem;
import com.calsoft.pos.model.order.SalesFlatOrderPayment;
import com.calsoft.pos.model.order.SalesFlatOrderStatusHistory;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.repository.CashRegistersRepository;
import com.calsoft.pos.repository.ConfigRepository;
import com.calsoft.pos.repository.SalesFlatOrderGridRepository;
import com.calsoft.pos.repository.SalesFlatOrderJpaRepository;
import com.calsoft.pos.repository.SalesFlatQuoteJpaRepository;
import com.calsoft.pos.repository.SalesRuleCouponJpaRepository;
import com.calsoft.pos.repository.SalesRuleCustomerJpaRepository;
import com.calsoft.pos.repository.SalesRuleJpaRepository;
import com.calsoft.pos.utils.InvoiceConstructor;
import com.calsoft.pos.utils.MailConstructor;
import com.calsoft.reports.service.ReportService;
import com.calsoft.utils.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lowagie.text.DocumentException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PosOrderService {

	@Autowired
	private SalesFlatOrderJpaRepository salesFlatOrderJpaRepository;

	@Autowired
	private SalesFlatQuoteJpaRepository salesFlatQuoteJpaRepository;

	@Autowired
	public RedisTemplate<String, ?> redisTemplate;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	ConfigRepository configRepository;

	@Autowired
	private SalesFlatOrderGridRepository salesFlatOrderGridRepository;

	@Qualifier("pdf")
	@Autowired
	private ReportService reportService;

	@Autowired
	private SalesRuleCustomerJpaRepository salesRuleCustomerJpaRepository;

	@Autowired
	private SalesRuleCouponJpaRepository salesRuleCouponJpaRepository;

	@Autowired
	private SalesRuleJpaRepository salesRuleJpaRepository;

	@Autowired
	private RestUtils restUtils;

	@Autowired
	public InvoiceConstructor invoiceConstructor;

	@Autowired
	CalsoftEventProducer calsoftEventProducer;

	@Autowired
	private CashRegistersRepository cashRegisterRepository;

	/***
	 * this method using cart information place order conform into paynow page move
	 * using
	 * 
	 * @param salesFlatOrder
	 * @param request
	 * @param tenantId
	 * @param customerId
	 * @return
	 * @throws ParseException
	 */
	public ResponseWrapper saveOrderItem(SalesFlatOrder salesFlatOrder, HttpServletRequest request, String tenantId,
			String customerId) throws ParseException {
		try {

			if (salesFlatOrder.getSalesFlatOrderAddress().isEmpty()) {
				CoreConfigData data = configRepository.getAllDetails(tenantId);

				SalesFlatOrderAddress salesFlatOrderAddress = new SalesFlatOrderAddress();

				salesFlatOrderAddress.setCity(data.getCity());
				salesFlatOrderAddress.setFirstName(data.getName());
				salesFlatOrderAddress.setLastName(data.getSalesName());
				salesFlatOrderAddress.setPostCode(data.getZipPostCode());
				salesFlatOrderAddress.setStreet(data.getStreetAddress());
				salesFlatOrderAddress.setTelephone(data.getStorePhoneNumber());
				salesFlatOrderAddress.setRegion(data.getCountry());
				salesFlatOrderAddress.setRegionId(Long.valueOf(1));
				salesFlatOrderAddress.setCountryId("1");
				salesFlatOrderAddress.setEntityId(0);
				salesFlatOrderAddress.setSalesFlatOrder(salesFlatOrder);
				salesFlatOrder.getSalesFlatOrderAddress().add(salesFlatOrderAddress);

				salesFlatOrder.setCustomerEmail(data.getEmail());

				salesFlatOrder.setCustomerFirstname(data.getName());

				salesFlatOrder.setCustomerLastname(data.getName());
			}

			populateFlatOrder(salesFlatOrder, tenantId);

			populateFlatPayment(salesFlatOrder.getSalesFlatOrderPayment(), salesFlatOrder);

			salesFlatOrderStatusHistory(salesFlatOrder);

			if (salesFlatOrder.getStatus() == "Processing") {

				salesFlatOrder.setTotalPaid(salesFlatOrder.getGrandTotal());
				salesFlatOrder.setBaseTotalPaid(salesFlatOrder.getBaseTotalPaid());
			}

			salesFlatOrder.setTenantId(tenantId);

			setTaxAmout(salesFlatOrder.getSalesFlatOrderItem(), salesFlatOrder);

			SalesFlatOrder salesFlatOrderFinal = salesFlatOrderJpaRepository.saveAndFlush(salesFlatOrder);

			populateFlatOrderGrid(salesFlatOrder, tenantId);

			log.info(" Order saved :: Order Id : {} ", salesFlatOrder.getEntityId());

			updateProductInventory(salesFlatOrder, request, tenantId);

			if (salesFlatOrder.getCouponCode() != null && salesFlatOrder.getCouponRuleName() != null) {

				upDateEntryInSalesRuleCustomer(salesFlatOrder.getCouponCode(), salesFlatOrder.getCustomerId(),
						tenantId);

			}

			updateCloseRegister(customerId, tenantId, salesFlatOrderFinal);

			SalesFlatQuote salesFlatQuote = salesFlatQuoteJpaRepository
					.findByEntityIdAndTenantId(salesFlatOrderFinal.getQuoteId(), tenantId);

			salesFlatQuote.setReservedOrderId(String.valueOf(salesFlatOrderFinal.getEntityId()));
			salesFlatQuote.setIsVirtual(0);
			salesFlatQuote.setIsActive(0);
			salesFlatQuote.setIsSuspended(0);
			salesFlatQuoteJpaRepository.save(salesFlatQuote);

			String key1 = "orderdashboard::" + tenantId;
			redisTemplate.delete(key1);

			return new ResponseWrapper(salesFlatOrderFinal, ResponseCodes02.ORDER_CREATION_REC_SUCCESS.getCode(),
					ResponseCodes02.ORDER_CREATION_REC_SUCCESS.getDescription());
		} catch (Throwable exp) {
			log.error("Exception while trying to save record", exp);
			return new ResponseWrapper(salesFlatOrder, ResponseCodes02.ORDER_CREATION_REC_FAILURE.getCode(),
					ResponseCodes02.ORDER_CREATION_REC_FAILURE.getDescription());
		}

	}

	private void updateCloseRegister(String customerId, String tenantId, SalesFlatOrder salesFlatOrderFinal) {
		CashRegister cashRegisterData = cashRegisterRepository
				.findByUserIdAndStatusAndTenantId(Long.valueOf(customerId), 1, tenantId);

		try {

			CashRegisterTransactions cashRegisterTransactions = new CashRegisterTransactions();

			cashRegisterTransactions.setAmount(salesFlatOrderFinal.getGrandTotal());

			cashRegisterTransactions.setType(OrderConfig.CASH_REGISTER_TRANSACTION_TYPE_CREDIT);

			cashRegisterTransactions.setTransactionType(OrderConfig.CASH_REGISTER_TRANSACTION_TYPE_SELL);

			cashRegisterTransactions.setCashRegister(cashRegisterData);

			cashRegisterTransactions.setPayMethod(
					salesFlatOrderFinal.getStatus().equalsIgnoreCase(OrderConfig.ORDER_COD_STATUS) ? "Cash" : "Card");

			cashRegisterTransactions.setTransactionId(salesFlatOrderFinal.getEntityId());

			cashRegisterData.getCashRegisterTransactions().add(cashRegisterTransactions);

			if (salesFlatOrderFinal.getStatus().equalsIgnoreCase(OrderConfig.ORDER_COD_STATUS)) {
				cashRegisterData.setTotalCashInHand(
						cashRegisterData.getTotalCashInHand() + salesFlatOrderFinal.getGrandTotal());

			} else {

				cashRegisterData.setTotalCardSlips(cashRegisterData.getTotalCardSlips() + 1);
			}

			cashRegisterData
					.setTotalSaleAmount(cashRegisterData.getTotalSaleAmount() + salesFlatOrderFinal.getGrandTotal());

			cashRegisterData.setTenantId(tenantId);

			cashRegisterRepository.save(cashRegisterData);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void populateFlatOrder(SalesFlatOrder salesFlatOrder, String tenantId) {

		String externalOrderId = null;

		externalOrderId = generateOrderId(externalOrderId, tenantId);

		salesFlatOrder.setExtOrderId(externalOrderId);

		salesFlatOrder.setState("New");
		salesFlatOrder.setOrderCurrencyCode(salesFlatOrder.getBaseCurrencyCode());
		salesFlatOrder.setShippingDescription("Flat Rate - Fixed");
		salesFlatOrder.setStoreId(1);
		salesFlatOrder.setBaseShippingTaxAmount(0);
		salesFlatOrder.setBaseShippingTaxRefunded(0.0);
		salesFlatOrder.setShippingCancelled(0.0);
		salesFlatOrder.setShippingInvoiced(0.00);
		salesFlatOrder.setShippingRefunded(0.0);
		salesFlatOrder.setShippingTaxAmount(0.0);
		salesFlatOrder.setTaxRefunded(0.0);
		salesFlatOrder.setStoreToBaseRate(1);
		salesFlatOrder.setStoreToOrderRate(1);
		salesFlatOrder.setEmailSent(1);
		salesFlatOrder.setForcedShipmentWithInvoice(null);
		salesFlatOrder.setPaymentAuthExpiration(null);
		salesFlatOrder.setQuoteAddressId(null);
		salesFlatOrder.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		salesFlatOrder.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
		salesFlatOrder.setCustomerId(salesFlatOrder.getCustomerId() == 0 ? 1 : salesFlatOrder.getCustomerId());
	}

	private String generateOrderId(String externalOrderId, String tenantId) {
		try {
			CoreConfigData data = configRepository.getAllDetails(tenantId);
			long beforeInsert = salesFlatOrderJpaRepository.findOrderCount(tenantId);
			beforeInsert = beforeInsert + 1;

			if (data.getOrderPrefix().isEmpty() && data.getOrderPrefix() == null) {
				externalOrderId = String.valueOf(beforeInsert);
			} else {
				externalOrderId = data.getOrderPrefix() + beforeInsert;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return externalOrderId;
	}

	private void populateFlatPayment(SalesFlatOrderPayment salesFlatOrderPayment, SalesFlatOrder salesFlatOrder)
			throws IOException {
		System.out.println(salesFlatOrder.getShippingAmount());
		double shipAmt = salesFlatOrder.getShippingAmount() == null ? 0 : salesFlatOrder.getShippingAmount();
		salesFlatOrderPayment.setBaseShippingAmount(shipAmt);
		salesFlatOrderPayment.setShippingCaptured(shipAmt);
		salesFlatOrderPayment.setBaseAmountPaid(salesFlatOrder.getGrandTotal());
		salesFlatOrderPayment.setBaseShippingAmount(shipAmt);
		salesFlatOrderPayment.setAmountPaid(salesFlatOrder.getGrandTotal());
		salesFlatOrderPayment.setBaseAmountOrdered(salesFlatOrder.getGrandTotal());
		salesFlatOrderPayment.setAmountOrdered(salesFlatOrder.getGrandTotal());
	}

	private void salesFlatOrderStatusHistory(SalesFlatOrder salesFlatOrder) {
		List<SalesFlatOrderStatusHistory> statusHistoryList = new ArrayList<SalesFlatOrderStatusHistory>();
		SalesFlatOrderStatusHistory salesFlatOrderStatusHistory = new SalesFlatOrderStatusHistory();
		salesFlatOrderStatusHistory.setSalesFlatOrder(salesFlatOrder);
		salesFlatOrderStatusHistory.setIsCustomerNotified(1);
		salesFlatOrderStatusHistory.setIsVisibleOnFront(0);
		salesFlatOrderStatusHistory.setEntityName("order");
		salesFlatOrderStatusHistory.setStatus(salesFlatOrder.getStatus());
		statusHistoryList.add(salesFlatOrderStatusHistory);
		salesFlatOrder.setSalesFlatOrderStatusHistory(statusHistoryList);
	}

	private void populateFlatOrderGrid(SalesFlatOrder salesFlatOrder, String tenantId) {

		SalesFlatOrderGrid salesFlatOrderGrid = new SalesFlatOrderGrid();
		salesFlatOrderGrid.setEntityId(salesFlatOrder.getEntityId());
		salesFlatOrderGrid.setStatus(salesFlatOrder.getStatus());
		salesFlatOrderGrid.setStoreId(salesFlatOrder.getStoreId());
		salesFlatOrderGrid.setStoreName(salesFlatOrder.getStoreName());
		salesFlatOrderGrid.setCustomerId(salesFlatOrder.getCustomerId() == 0 ? 1 : salesFlatOrder.getCustomerId());
		salesFlatOrderGrid.setBaseGrandTotal(salesFlatOrder.getBaseGrandTotal());
		salesFlatOrderGrid.setGrandTotal(salesFlatOrder.getGrandTotal());
		salesFlatOrderGrid.setBaseTotalPaid(salesFlatOrder.getBaseTotalPaid());
		salesFlatOrderGrid.setTotalPaid(salesFlatOrder.getTotalPaid());
		salesFlatOrderGrid.setIncrementId(String.valueOf(salesFlatOrder.getExtOrderId()));
		salesFlatOrderGrid.setBaseCurrencyCode(salesFlatOrder.getBaseCurrencyCode());
		salesFlatOrderGrid.setOrderCurrencyCode(salesFlatOrder.getOrderCurrencyCode());
		salesFlatOrderGrid.setPos(1);

		if (salesFlatOrder.getCustomerId() == 1) {

			salesFlatOrderGrid.setShippingName("Walk In Customer");
			salesFlatOrderGrid.setBillingName("Walk In Customer");
		} else {

			salesFlatOrderGrid.setShippingName(salesFlatOrder.getSalesFlatOrderAddress().get(0).getFirstName());
			salesFlatOrderGrid.setBillingName(salesFlatOrder.getSalesFlatOrderAddress().get(0).getFirstName());
		}

		salesFlatOrderGrid.setTenantId(tenantId);
		salesFlatOrderGridRepository.save(salesFlatOrderGrid);

	}

	private void setTaxAmout(List<SalesFlatOrderItem> salesFlatOrderItem, SalesFlatOrder salesFlatOrder) {

		double taxAmount = 0.0;
		double baseTaxAmount = 0.0;

		for (SalesFlatOrderItem orderItem : salesFlatOrderItem) {

			double tax = getTaxAmout(orderItem.getPrice(), orderItem.getTaxPercent());
			taxAmount = taxAmount + tax;
			double baseTax = getTaxAmout(orderItem.getBasePrice(), orderItem.getTaxPercent());
			baseTaxAmount = baseTaxAmount + baseTax;
		}

		salesFlatOrder.setTaxAmount(taxAmount);
		salesFlatOrder.setBaseTaxAmount(taxAmount);

	}

	private void updateProductInventory(SalesFlatOrder salesFlatOrder, HttpServletRequest request, String tenantId)
			throws JsonProcessingException {

		String key = "AddInventory" + salesFlatOrder.getEntityId();

		Map<String, String> accessToken = restUtils.extractHeaderToken(request);

		calsoftEventProducer.sendEventWithAccessToken("inventory-events", key, salesFlatOrder, tenantId, accessToken);
	}

	private void upDateEntryInSalesRuleCustomer(String couponCode, long customerId, String tenantId) {

		SalesRuleCoupon salesRule = salesRuleCouponJpaRepository.findByCode(couponCode);
		SalesRuleCustomer salesRuleCustomer = new SalesRuleCustomer();
		salesRuleCustomer.setCustomerId(customerId == 0 ? 1 : customerId);
		salesRuleCustomer.setRuleId(salesRule.getRuleId());
		salesRuleCustomer.setTimesUsed(salesRule.getTimesUsed() + 1);
		SalesRule saleRules = salesRuleJpaRepository.findByRuleIdAndTenantId(salesRule.getRuleId(), tenantId);

		saleRules.setTimesUsed(salesRule.getTimesUsed() + 1);

		salesRuleJpaRepository.save(saleRules);

		salesRuleCustomerJpaRepository.save(salesRuleCustomer);
	}

	/***
	 * this method using Send mail
	 * 
	 * @param notificationVO
	 * @param key
	 * @param tenantId
	 * @return
	 * @throws ParseException
	 */
	public void triggerMail(NotificationVO notificationVO, String key, String tenantId) {
		try {
			calsoftEventProducer.sendEvent("mail-events", key, notificationVO, tenantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private double getTaxAmout(double price, double taxPercent) {

		return price * taxPercent / 100;
	}

	public byte[] generatePdf(String entityId, String tenantId) throws IOException, DocumentException {
		SalesFlatOrder salesFlatOrder = salesFlatOrderJpaRepository.findByEntityIdAndTenantId(Long.valueOf(entityId),
				tenantId);

		NotificationVO notificationVO = invoiceConstructor.constructOrderInvoice(salesFlatOrder, Locale.ENGLISH,
				tenantId);

		String html = invoiceConstructor.buildInvoiceAttributes(notificationVO);

		return renderPdf(html);
	}

	public byte[] generatePdfQuote(String entityId, String tenantId, String userName)
			throws IOException, DocumentException {

		SalesFlatQuote salesFlatQuote = salesFlatQuoteJpaRepository.findByEntityIdAndTenantId(Long.valueOf(entityId),
				tenantId);
		salesFlatQuote.setExtOrderId("000");
		NotificationVO notificationVO = invoiceConstructor.constructQuotation(salesFlatQuote, Locale.ENGLISH, tenantId,
				userName);

		String html = invoiceConstructor.buildInvoiceAttributes(notificationVO);

		return renderPdf(html);
	}

	private static final String PDF_RESOURCES = "/pdf-resources/";

	private byte[] renderPdf(String html) throws IOException, DocumentException {
		byte[] fileContent = null;
		OutputStream outputStream = null;
		try {
			File file = File.createTempFile("order", ".pdf");
			outputStream = new FileOutputStream(file);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
			renderer.layout();
			renderer.createPDF(outputStream);
			outputStream.close();
			file.deleteOnExit();
			fileContent = Files.readAllBytes(file.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outputStream.close();

		}

		return fileContent;

	}

}
