package com.calsoft.pos.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.calsoft.pos.model.CoreConfigData;
import com.calsoft.pos.model.cart.SalesFlatQuote;
import com.calsoft.pos.model.order.SalesFlatOrder;
import com.calsoft.pos.model.order.SalesFlatOrderAddress;
import com.calsoft.pos.repository.ConfigRepository;
import com.calsoft.notification.vo.NotificationVO;

@Component
public class InvoiceConstructor {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	ConfigRepository configRepository;

	@Value("${image.url.path}")
	String primaryUrl;

	@Value("${image.url}")
	String imageUrl;

	public NotificationVO constructOrderInvoice(SalesFlatOrder order, Locale locale, String tenantId) {
		NotificationVO notificationVO = new NotificationVO();
		Map<String, Object> params = new HashMap<String, Object>();
		CoreConfigData data = configRepository.getAllDetails(tenantId);
		SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
		String createdDate = formatter.format(order.getCreatedDate().getTime());
		String uploadFolder = primaryUrl + "/" + tenantId + "?fileName=" + imageUrl;
		String logo = data.getBaseUrl() + uploadFolder;

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
		salesFlatOrderAddress.setParentId(order.getEntityId());
		List<SalesFlatOrderAddress> salesFlatOrderAddressList = new ArrayList<SalesFlatOrderAddress>();
		salesFlatOrderAddressList.add(salesFlatOrderAddress);
		order.setSalesFlatOrderAddress(salesFlatOrderAddressList);

		params.put("order", order);
		params.put("clientAddress", data);
		params.put("imageUrl", logo);
		params.put("orderItemList", order.getSalesFlatOrderItem());
		params.put("orderPlacedDate", createdDate);
		params.put("currency", order.getBaseCurrencyCode());
		params.put("contactNumber", data.getStorePhoneNumber());
		params.put("email", data.getEmail());

		String customerEmail = salesFlatOrderAddress.getFirstName();

		if (order.getCustomerId() == 0) {
			customerEmail = "Walk-in Customer";
		}

		params.put("biller", order.getBiller());
		
		params.put("type", order.getType());
		params.put("changeAmount", order.getChangeAmount());
		params.put("typeOfCustome", customerEmail);
		notificationVO.setMailParams(params);
		notificationVO.setTemplateId("bill");
		notificationVO.setToAddress(order.getCustomerEmail());
		notificationVO.setSubject("Order Confirmation - " + order.getEntityId());
		return notificationVO;
	}
	
	
	public NotificationVO constructQuotation(SalesFlatQuote order, Locale locale, String tenantId, String userName) {
		NotificationVO notificationVO = new NotificationVO();
		Map<String, Object> params = new HashMap<String, Object>();
		CoreConfigData data = configRepository.getAllDetails(tenantId);
		SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
		String createdDate = formatter.format(order.getCreatedDate().getTime());
		String uploadFolder = primaryUrl + "/" + tenantId + "?fileName=" + imageUrl;
		String logo = data.getBaseUrl() + uploadFolder;

		SalesFlatOrderAddress salesFlatOrderAddress = new SalesFlatOrderAddress();

		order.setExtOrderId(data.getOrderPrefix()+order.getExtOrderId());
		salesFlatOrderAddress.setCity(data.getCity());
		salesFlatOrderAddress.setFirstName(data.getName());
		salesFlatOrderAddress.setLastName(data.getSalesName());
		salesFlatOrderAddress.setPostCode(data.getZipPostCode());
		salesFlatOrderAddress.setStreet(data.getStreetAddress());
		salesFlatOrderAddress.setTelephone(data.getStorePhoneNumber());
		salesFlatOrderAddress.setRegion(data.getCountry());
		salesFlatOrderAddress.setRegionId(Long.valueOf(1));
		salesFlatOrderAddress.setCountryId("1");
		salesFlatOrderAddress.setParentId(order.getEntityId());
		List<SalesFlatOrderAddress> salesFlatOrderAddressList = new ArrayList<SalesFlatOrderAddress>();
		salesFlatOrderAddressList.add(salesFlatOrderAddress);
	

		params.put("order", order);
		params.put("clientAddress", data);
		params.put("imageUrl", logo);
		params.put("orderItemList", order.getSalesFlatQuoteItem());
		params.put("orderPlacedDate", createdDate);
		params.put("currency", order.getBaseCurrencyCode());
		params.put("contactNumber", data.getStorePhoneNumber());
		params.put("email", data.getEmail());

		String customerEmail = salesFlatOrderAddress.getFirstName();

		if (order.getCustomerId() == 0) {
			customerEmail = "Walk-in Customer";
		}

		params.put("biller", userName);
		
		params.put("type", "Invoice");
		params.put("changeAmount", 0.0);
		params.put("typeOfCustome", customerEmail);
		notificationVO.setMailParams(params);
		notificationVO.setTemplateId("quotebill");
		notificationVO.setToAddress(order.getCustomerEmail());
		notificationVO.setSubject("Order Confirmation - " + order.getEntityId());
		return notificationVO;
	}

	public String buildInvoiceAttributes(NotificationVO notificationVO) {

		String text = invoiceContent(notificationVO.getMailParams(), notificationVO.getTemplateId());

		return text;
	}

	public String invoiceContent(Map<String, Object> mailParams, String templateId) {
		Context context = new Context();
		mailParams.forEach((key, value) -> context.setVariable(key, value));
		return templateEngine.process(templateId, context);
	}

}
