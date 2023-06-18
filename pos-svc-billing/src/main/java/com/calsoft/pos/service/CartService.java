package com.calsoft.pos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calsoft.pos.model.ResponseWrapper;
import com.calsoft.pos.model.cart.SalesFlatQuote;
import com.calsoft.pos.model.cart.SalesFlatQuoteAddress;
import com.calsoft.pos.model.cart.SalesFlatQuoteItem;
import com.calsoft.pos.model.cart.SalesFlatQuoteItemOption;
import com.calsoft.pos.model.customerindex.CustomerIDX;
import com.calsoft.pos.model.productindex.ProductIDX2;
import com.calsoft.pos.model.utils.ResponseCodes02;
import com.calsoft.pos.repository.CustomerIdxV2Repository;
import com.calsoft.pos.repository.ProductIDx2Repository;
import com.calsoft.pos.repository.SalesFlatQuoteAddressJpaRepository;
import com.calsoft.pos.repository.SalesFlatQuoteItemJpaRepository;
import com.calsoft.pos.repository.SalesFlatQuoteItemOptionJpaRepository;
import com.calsoft.pos.repository.SalesFlatQuoteJpaRepository;
import com.calsoft.pos.exception.InvalidException;
import com.calsoft.pos.exception.MinumSaleException;
import com.calsoft.pos.utils.WeighingScaleConnection;
import com.calsoft.reports.service.ReportService;
import com.calsoft.utils.UserManagementConstant;

@Service
public class CartService {

	Logger logger = LoggerFactory.getLogger(CartService.class);
	@Autowired
	private SalesFlatQuoteItemJpaRepository salesFlatQuoteItemJpaRepository;

	@Qualifier("excel")
	@Autowired
	private ReportService reportService;

	@Autowired
	private CustomerIdxV2Repository customerIdxV2Repository;

	@Autowired
	private SalesFlatQuoteJpaRepository salesFlatQuoteJpaRepository;

	@Autowired
	SalesFlatQuoteAddressJpaRepository salesFlatQuoteAddressJpaRepository;

	@Autowired
	ProductIDx2Repository productIDx2Repository;

	@Autowired
	SalesFlatQuoteItemOptionJpaRepository salesFlatQuoteItemOptionJpaRepository;

	@Autowired
	public RedisTemplate<String, ?> redisTemplate;

	@Autowired
	private WeighingScaleConnection weighingScaleConnection;

	/***
	 * this method using cart page details show with customerId get login into
	 * credentials based open
	 * 
	 * @param customerId
	 * @param tenantId
	 * @return
	 * @throws Throwable
	 */
	public SalesFlatQuote fetchByCustomerId(String customerId, String tenantId) throws Throwable {
		SalesFlatQuote salesFlatQuote = new SalesFlatQuote();

		salesFlatQuote = salesFlatQuoteJpaRepository.fetchAllBillerIdAndIsVirtualAndTenantIdOrTenantId(
				Long.valueOf(customerId), Long.valueOf(1), tenantId, UserManagementConstant.SYSTEM_DEFAULT_TENANT_ID);

		return salesFlatQuote;

	}

	/***
	 * this method using cart page details show with customerId get login into
	 * credentials based open
	 * 
	 * @param customerId
	 * @param tenantId
	 * @param pageable
	 * @return
	 * @throws Throwable
	 */
	public Page<SalesFlatQuote> findSalesSuspended(String customerId, String tenantId, Pageable pageable)
			throws Throwable {

		Page<SalesFlatQuote> salesFlatQuote = salesFlatQuoteJpaRepository
				.fetchAllBillerIdAndIsSuspendedAndTenantIdOrTenantId(Long.valueOf(customerId), 1, tenantId,
						UserManagementConstant.SYSTEM_DEFAULT_TENANT_ID, pageable);

		return salesFlatQuote;

	}

	/***
	 * this method using add to cart new product in web menakart used
	 * 
	 * @param salesFlatQuote
	 * @param tenantId
	 * @return
	 * @throws Throwable
	 */
	@Transactional(rollbackFor = Exception.class)
	public ResponseWrapper saveCartItem(SalesFlatQuote salesFlatQuote, String tenantId) throws Throwable {
		String operationType = salesFlatQuote.getOperationType();
		SalesFlatQuote salesFlatQuoteC = null;
//        List<SalesFlatQuoteItem> updatedCartList = new ArrayList<SalesFlatQuoteItem>();
		Double subTotal = Double.valueOf(0);
		List<SalesFlatQuoteItem> list = new ArrayList<SalesFlatQuoteItem>();

		try {

			salesFlatQuoteC = salesFlatQuoteJpaRepository.fetchAllBillerIdAndIsVirtualAndTenantIdOrTenantId(
					salesFlatQuote.getBillerId(), Long.valueOf(1), tenantId,
					UserManagementConstant.SYSTEM_DEFAULT_TENANT_ID);

			if (salesFlatQuoteC == null) {
				salesFlatQuoteC = new SalesFlatQuote();
				populateBaseQuoteDetails(salesFlatQuoteC, salesFlatQuote, tenantId);
			}

			SalesFlatQuoteItem salesFlatQuoteItem = null;

			List<SalesFlatQuoteItem> cartList = salesFlatQuote.getSalesFlatQuoteItem();
			for (SalesFlatQuoteItem salesFlatQuoteitemE : cartList) {
				salesFlatQuoteItem = salesFlatQuoteItemJpaRepository
						.findByItemId(salesFlatQuoteitemE.getItemId() == null ? 0 : salesFlatQuoteitemE.getItemId());
				if (salesFlatQuoteItem == null) {
					salesFlatQuoteItem = salesFlatQuoteItemJpaRepository.findByProductIdAndSalesFlatQuoteEntityId(
							salesFlatQuoteitemE.getProductId(), salesFlatQuoteC.getEntityId());
					if (salesFlatQuoteItem == null) {
						salesFlatQuoteItem = new SalesFlatQuoteItem();
					} else if (salesFlatQuoteItem != null
							&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
						salesFlatQuoteitemE.setQty(salesFlatQuoteItem.getQty() + salesFlatQuoteitemE.getQty());
						salesFlatQuoteitemE.setOperationType("Change");
						if (salesFlatQuoteitemE.getQty() == 0) {

							list.add(salesFlatQuoteItem);

						}

					}
				}
				if (salesFlatQuoteitemE.getOperationType() != null
						&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
					populateItemProductDetails(salesFlatQuoteItem, salesFlatQuoteitemE, tenantId);
				}

				populateQuoteItem(salesFlatQuoteItem, salesFlatQuoteitemE, salesFlatQuoteC, tenantId, list);

				if (salesFlatQuoteitemE.getOperationType() != null
						&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
					salesFlatQuoteItem.setSalesFlatQuote(salesFlatQuoteC);
				}
				if (salesFlatQuoteitemE.getOperationType() != null
						&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
					salesFlatQuoteC.addQuoteItem(salesFlatQuoteItem);
				}

			}

			computeTotal(salesFlatQuoteC);
			List<SalesFlatQuoteAddress> orderAddressList = salesFlatQuote.getSalesFlatQuoteAddress();

			if (salesFlatQuoteItem.getOperationType() != null
					&& salesFlatQuoteItem.getOperationType().equalsIgnoreCase("New")) {
				for (SalesFlatQuoteAddress salesFlatQuoteAddressList : orderAddressList) {
					if (salesFlatQuoteAddressList != null) {
						final long itemId = salesFlatQuoteAddressList.getAddressId();
						SalesFlatQuoteAddress salesFlatQuoteAddress = salesFlatQuoteAddressJpaRepository
								.findByAddressId(itemId);
						if (salesFlatQuoteAddress == null) {
							salesFlatQuoteAddress = new SalesFlatQuoteAddress();
						}
						populateQuoteAddress(salesFlatQuoteC, orderAddressList, salesFlatQuoteAddress);
					}
				}
			}
			salesFlatQuoteC.setTenantId(tenantId);
			salesFlatQuoteC.setBillerId(salesFlatQuote.getBillerId());
			salesFlatQuoteJpaRepository.save(salesFlatQuoteC);

			if (!list.isEmpty()) {
				for (SalesFlatQuoteItem item : list) {

					salesFlatQuoteItemJpaRepository.deleteByItemId(item.getItemId());

				}
			}
			if (operationType != null && operationType.equalsIgnoreCase("New")) {
				return new ResponseWrapper(salesFlatQuote, ResponseCodes02.ADDCARTTEMS_REC_SUCCESS.getCode(),
						ResponseCodes02.ADDCARTTEMS_REC_SUCCESS.getDescription());
			}
			if (operationType != null && operationType.equalsIgnoreCase("Delete")) {
				return new ResponseWrapper(salesFlatQuote, ResponseCodes02.DELETECARTITEM_REC_SUCCESS.getCode(),
						ResponseCodes02.DELETECARTITEM_REC_SUCCESS.getDescription());
			}
			return new ResponseWrapper(salesFlatQuote, ResponseCodes02.UPDATECARTTEMS_REC_SUCCESS.getCode(),
					ResponseCodes02.UPDATECARTTEMS_REC_SUCCESS.getDescription());
		} catch (Exception MinumSaleException) {
			return new ResponseWrapper(salesFlatQuote, ResponseCodes02.CART_UPDATE_FAILED.getCode(),
					ResponseCodes02.MIN_CART_UPDATE_FAILED.getDescription() + MinumSaleException.getMessage());
		} catch (Throwable exp) {
			if (exp instanceof InvalidException) {
				return new ResponseWrapper(salesFlatQuote, ResponseCodes02.CART_UPDATE_FAILED.getCode(),
						ResponseCodes02.CART_UPDATE_FAILED.getDescription());
			}
			logger.error("Exception while trying to save record", exp);
			if (operationType != null && operationType.equalsIgnoreCase("New")) {
				return new ResponseWrapper(null, ResponseCodes02.ADDCARTTEMS_REC_FAILURE.getCode(),
						ResponseCodes02.ADDCARTTEMS_REC_FAILURE.getDescription());
			}
			return new ResponseWrapper(salesFlatQuote, ResponseCodes02.UPDATECARTTEMS_REC_FAILURE.getCode(),
					ResponseCodes02.UPDATECARTTEMS_REC_SUCCESS.getDescription());
		}
	}

	/***
	 * this method using cart page multiple product adds cards item id based remove
	 * single product used
	 * 
	 * @param quoteId
	 * @param itemId
	 * @param salesFlatQuoteItem
	 * @param tenantId
	 * @return
	 */
	public ResponseWrapper deleteCartItem(String quoteId, String itemId, SalesFlatQuoteItem salesFlatQuoteItem,
			String tenantId) {
		try {

			salesFlatQuoteItemJpaRepository.delete(salesFlatQuoteItem);
			SalesFlatQuote salesFlatQuoteC = salesFlatQuoteJpaRepository
					.findByEntityIdAndTenantId(Long.valueOf(quoteId), tenantId);

			if (salesFlatQuoteC.getSalesFlatQuoteItem().isEmpty()) {
				salesFlatQuoteJpaRepository.delete(salesFlatQuoteC);

			} else {
				salesFlatQuoteC.getSalesFlatQuoteItem()
						.removeIf(cartItem -> cartItem.getItemId() == Integer.parseInt(itemId));
				computeTotal(salesFlatQuoteC);
				salesFlatQuoteC.setTenantId(tenantId);

				salesFlatQuoteJpaRepository.save(salesFlatQuoteC);
			}

			return new ResponseWrapper(salesFlatQuoteC, ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getDescription());
		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);
			return new ResponseWrapper(exp, ResponseCodes02.DELETECARTITEMS_REC_FAILURE.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_FAILURE.getDescription());
		}

	}

	private void computeTotal(SalesFlatQuote salesFlatQuoteC) {
		salesFlatQuoteC.setGrandTotal(0.0);
		salesFlatQuoteC.setSubtotal(0.0);
		salesFlatQuoteC.setItemsQty(0.0);
		salesFlatQuoteC.setBaseGrandTotal(0.0);
		salesFlatQuoteC.setBaseSubtotal(0.0);
		salesFlatQuoteC.setSubtotalWithDiscount(0.0);
		salesFlatQuoteC.setBaseSubtotalWithDiscount(0.0);
		for (SalesFlatQuoteItem item : salesFlatQuoteC.getSalesFlatQuoteItem()) {

			salesFlatQuoteC.setGrandTotal(salesFlatQuoteC.getGrandTotal() + item.getRowTotal());
			if (item.getCustomPrice() == null) {
				item.setCustomPrice(0.0);
			}
			salesFlatQuoteC.setSubtotal(salesFlatQuoteC.getSubtotal() + item.getRowTotal() - item.getCustomPrice());
			salesFlatQuoteC.setItemsQty(salesFlatQuoteC.getItemsQty() + item.getQty());
			salesFlatQuoteC.setBaseGrandTotal(salesFlatQuoteC.getBaseGrandTotal() + item.getBaseRowTotal());
			salesFlatQuoteC.setBaseSubtotal(
					salesFlatQuoteC.getBaseSubtotal() + item.getBaseRowTotal() - item.getCustomPrice());
			salesFlatQuoteC.setSubtotalWithDiscount(salesFlatQuoteC.getSubtotalWithDiscount());
			salesFlatQuoteC.setBaseSubtotalWithDiscount(
					salesFlatQuoteC.getBaseSubtotalWithDiscount() + item.getBaseRowTotal() - item.getCustomPrice());
			salesFlatQuoteC.setItemsCount(salesFlatQuoteC.getSalesFlatQuoteItem().size());

		}

	}

	private void populateQuoteAddress(SalesFlatQuote salesFlatQuoteC, List<SalesFlatQuoteAddress> orderAddressList,
			SalesFlatQuoteAddress salesFlatQuoteAddress) {
		salesFlatQuoteAddress.setFirstname(salesFlatQuoteAddress.getFirstname());
		salesFlatQuoteAddress.setSaveInAddressBook(0);
		salesFlatQuoteAddress.setCustomerId(salesFlatQuoteC.getCustomerId());
		salesFlatQuoteAddress.setCustomerAddressId(0);
		salesFlatQuoteAddress.setAddressType(salesFlatQuoteAddress.getAddressType());
		salesFlatQuoteAddress.setEmail(salesFlatQuoteAddress.getEmail());
		salesFlatQuoteAddress.setPrefix(null);
		salesFlatQuoteAddress.setMiddlename(null);
		salesFlatQuoteAddress.setLastname(salesFlatQuoteAddress.getLastname());
		salesFlatQuoteAddress.setSuffix(null);
		salesFlatQuoteAddress.setCompany(null);
		salesFlatQuoteAddress.setStreet(salesFlatQuoteAddress.getStreet());
		salesFlatQuoteAddress.setCity(salesFlatQuoteAddress.getCity());
		salesFlatQuoteAddress.setRegionId(0);
		salesFlatQuoteAddress.setPostcode(null);
		salesFlatQuoteAddress.setCountryId(salesFlatQuoteAddress.getCountryId());
		salesFlatQuoteAddress.setTelephone(salesFlatQuoteAddress.getTelephone());
		salesFlatQuoteAddress.setFax(0);
		salesFlatQuoteAddress.setVatIsValid(0);
		salesFlatQuoteAddress.setVatRequestSuccess(0);
		salesFlatQuoteAddress.setBaseShippingHiddenTaxAmnt(0);
		salesFlatQuoteAddress.setAddressId(salesFlatQuoteAddress.getAddressId());
		salesFlatQuoteAddress.setSalesFlatQuote(salesFlatQuoteC);
		orderAddressList.add(salesFlatQuoteAddress);
		salesFlatQuoteC.setSalesFlatQuoteAddress(orderAddressList);
	}

	private void populateQuoteItem(SalesFlatQuoteItem salesFlatQuoteItem, SalesFlatQuoteItem salesFlatQuoteitemE,
			SalesFlatQuote salesFlatQuoteC, String tenantId, List<SalesFlatQuoteItem> removeList)
			throws InvalidException, MinumSaleException {

		double itemRowTotal = 0, qty = 0;

		if (salesFlatQuoteitemE.getOperationType() != null
				&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
			qty = salesFlatQuoteItem.getQty() + salesFlatQuoteitemE.getQty();
		} else {
			qty = salesFlatQuoteitemE.getQty();
		}

		List<ProductIDX2> productIDXList = productIDx2Repository
				.findByEntityIdAndTenantId(salesFlatQuoteitemE.getProductId(), tenantId);

		if (productIDXList.isEmpty()) {
			salesFlatQuoteItemJpaRepository.delete(salesFlatQuoteItem);
		}

		else {
			ProductIDX2 productIDX2 = productIDXList.get(0);

			if (salesFlatQuoteitemE.getOperationType() != null
					&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
				qty = salesFlatQuoteItem.getQty() + salesFlatQuoteitemE.getQty();
			} else {
				qty = salesFlatQuoteitemE.getQty();
			}

			if (productIDX2.getManageStock() == 1) {

				boolean isValidQty = isValidQty(productIDX2, qty);
				if (isValidQty) {
					throw new InvalidException("");
				}

				if (qty < -1) {
					qty = 0;
					removeList.add(salesFlatQuoteItem);

				}
			}
			
		

			salesFlatQuoteItem.setQty(qty);
			salesFlatQuoteItem.setFreeShipping(0);
			salesFlatQuoteItem.setIsQtyDecimal(0);
			salesFlatQuoteItem.setNoDiscount(0);
			salesFlatQuoteItem.setStoreId(1);
			salesFlatQuoteItem.setDiscountAmount(salesFlatQuoteitemE.getDiscountAmount());
			salesFlatQuoteItem.setBaseDiscountAmount(salesFlatQuoteitemE.getDiscountAmount());
			salesFlatQuoteItem.setTaxPercent(productIDX2.getTax());

//			if()
			/** Compute Item Row Total **/
			itemRowTotal = salesFlatQuoteItem.getPrice() * salesFlatQuoteItem.getQty();
			salesFlatQuoteC.setSubtotal(salesFlatQuoteC.getSubtotal() + itemRowTotal);

			if (salesFlatQuoteItem.getCustomPrice() == null) {
				salesFlatQuoteItem.setCustomPrice(0.0);
			}
			salesFlatQuoteItem.setRowTotal(itemRowTotal + salesFlatQuoteItem.getCustomPrice());
			salesFlatQuoteItem.setRowTotalInclTax(itemRowTotal + salesFlatQuoteItem.getCustomPrice());

			salesFlatQuoteItem.setTaxAmount(
					(salesFlatQuoteItem.getPrice() * salesFlatQuoteItem.getQty()) * productIDX2.getTax() / 100);
			salesFlatQuoteItem.setRowWeight(salesFlatQuoteItem.getWeight() * salesFlatQuoteItem.getQty());

			salesFlatQuoteItem.setPriceInclTax(salesFlatQuoteItem.getPrice());

			double itemBaseRowTotalInclTax = (salesFlatQuoteItem.getBasePrice() * salesFlatQuoteItem.getQty());

			salesFlatQuoteItem.setBaseRowTotalInclTax(itemBaseRowTotalInclTax + salesFlatQuoteItem.getCustomPrice());
			salesFlatQuoteItem.setBaseRowTotal(itemBaseRowTotalInclTax + salesFlatQuoteItem.getCustomPrice());
			salesFlatQuoteItem.setHiddenTaxAmount(salesFlatQuoteItem.getTaxAmount());
			salesFlatQuoteItem.setBaseHiddenTaxAmount(salesFlatQuoteItem.getTaxAmount());

			salesFlatQuoteC.setSubtotalWithDiscount(itemRowTotal);

			salesFlatQuoteC.setBaseGrandTotal(itemBaseRowTotalInclTax + salesFlatQuoteItem.getCustomPrice());
			salesFlatQuoteC.setSubtotalWithDiscount(itemRowTotal);
			salesFlatQuoteC.setCustomerTaxvat(productIDX2.getTax() + "%");
			salesFlatQuoteC.setCustomerTaxClassId(productIDX2.getTaxClassId());
		}

	}

	private boolean isValidQty(ProductIDX2 productIDX2, double qty) {
		boolean returnValue = true;
		/* available qty is greater than ordered qty */
		if (productIDX2.getQty() < qty) {
			returnValue = true;
		} else if (productIDX2.getMaxSaleQty() == 0) {
			returnValue = false;
		} else if (productIDX2.getMaxSaleQty() < qty) {
			returnValue = true;
		} else {
			returnValue = false;
		}

		return returnValue;
	}

	private void populateItemProductDetails(SalesFlatQuoteItem salesFlatQuoteItem,
			SalesFlatQuoteItem salesFlatQuoteitemE, String tenantId) {

		List<ProductIDX2> productIDXList = productIDx2Repository
				.findByEntityIdAndTenantId(salesFlatQuoteitemE.getProductId(), tenantId);
		ProductIDX2 productIDX2 = productIDXList.get(0);

		salesFlatQuoteItem.setName(productIDX2.getName());
		salesFlatQuoteItem.setSku(productIDX2.getSku());
		salesFlatQuoteItem.setDescription(productIDX2.getDescription());
		salesFlatQuoteItem.setProductId(Long.valueOf(productIDX2.getEntityId()));
		salesFlatQuoteItem.setImageUrl(productIDX2.getProductImageUrl());
		salesFlatQuoteItem.setIsInStock(1);
		if (productIDX2.getWeight() != null) {

			salesFlatQuoteItem.setWeight(Double.valueOf(productIDX2.getWeight()));
		} else {
			salesFlatQuoteItem.setWeight(0.0);
		}
		if (productIDX2.getSpecialPrice() != 0) {
			salesFlatQuoteItem.setPrice(productIDX2.getSpecialPrice());
		} else {
			salesFlatQuoteItem.setPrice(productIDX2.getPrice());
		}
		salesFlatQuoteItem.setBasePrice(productIDX2.getPrice());
		if (Double.isInfinite(productIDX2.getDiscountPercentage())) {
			salesFlatQuoteItem.setDiscountPercent(0);
		} else {
			salesFlatQuoteItem.setDiscountPercent(productIDX2.getDiscountPercentage());
		}
		salesFlatQuoteItem.setProductType(productIDX2.getType());
		salesFlatQuoteItem.setBasePriceInclTax(productIDX2.getPrice());

		salesFlatQuoteItem.setProductCustomOption(salesFlatQuoteitemE.getProductCustomOption());
		// salesFlatQuoteItem.setWeight(productIDX2.getWeight());

		salesFlatQuoteItem.setCustomPrice(0.0);
		if (!salesFlatQuoteitemE.getSalesFlatQuoteItemOption().isEmpty()) {
			salesFlatQuoteitemE.getSalesFlatQuoteItemOption().stream().forEachOrdered(quoteItemOption -> {

				SalesFlatQuoteItemOption salesFlatQuoteItemOption = new SalesFlatQuoteItemOption();

				salesFlatQuoteItemOption.setCode(quoteItemOption.getCode());

				salesFlatQuoteItemOption.setIndex(quoteItemOption.getIndex());

				salesFlatQuoteItemOption.setAdditional(quoteItemOption.getAdditional());

				salesFlatQuoteItemOption.setAdditionalInfoGiven(quoteItemOption.getAdditionalInfoGiven());

				salesFlatQuoteItemOption.setProductId(quoteItemOption.getProductId());

				salesFlatQuoteItemOption.setValue(quoteItemOption.getValue());

				salesFlatQuoteItemOption.setSalesFlatQuoteItem(salesFlatQuoteItem);

				salesFlatQuoteItemOption.setSalesFlatQuoteItem(salesFlatQuoteItem);

				salesFlatQuoteItem.getSalesFlatQuoteItemOption().add(salesFlatQuoteItemOption);

				salesFlatQuoteItem.setCustomPrice(
						salesFlatQuoteItem.getCustomPrice() + Double.valueOf(quoteItemOption.getValue()));

			});

		}

	}

	private void populateBaseQuoteDetails(SalesFlatQuote salesFlatQuoteC, SalesFlatQuote salesFlatQuote,
			String tenantId) {
		salesFlatQuoteC.setStoreId(1);
		salesFlatQuoteC.setIsActive(0);
		salesFlatQuoteC.setIsVirtual(1);
		salesFlatQuoteC.setIsMultiShipping(0);
		salesFlatQuoteC.setOrigOrderId(0);
		salesFlatQuoteC.setStoreToBaseRate(1);
		salesFlatQuoteC.setStoreToQuoteRate(1);
		salesFlatQuoteC.setBaseCurrencyCode(salesFlatQuote.getCurrencyCode());
		salesFlatQuoteC.setStoreCurrencyCode(salesFlatQuote.getCurrencyCode());
		salesFlatQuoteC.setQuoteCurrencyCode(salesFlatQuote.getCurrencyCode());
		// salesFlatQuoteC.setCustomerTaxClassId(3);
		salesFlatQuoteC.setCustomerGroupId(1);

		if (salesFlatQuote.getCustomerId() > 0) {

			CustomerIDX customer = customerIdxV2Repository.findByEntityId(Long.valueOf(salesFlatQuote.getCustomerId()));

			salesFlatQuoteC.setCustomerGender(customer.getGender());
			salesFlatQuoteC.setCustomerId((int) customer.getEntityId());
			salesFlatQuoteC.setCustomerEmail(customer.getEmail());
			salesFlatQuoteC.setCustomerFirstname(customer.getFirstName());
//	           salesFlatQuote.setCustomerMiddlename(customer.g);
			salesFlatQuoteC.setCustomerLastname(customer.getLastName());
			salesFlatQuoteC.setCustomerIsGuest(0);
			salesFlatQuoteC.setRemoteIp(salesFlatQuote.getRemoteIp());
			salesFlatQuoteC.setPasswordHash(customer.getPassword());
		}

		salesFlatQuoteC.setGlobalCurrencyCode(salesFlatQuote.getCurrencyCode());
		salesFlatQuoteC.setBaseToGlobalRate(1);
		salesFlatQuoteC.setBaseToQuoteRate(1);
		// salesFlatQuoteC.setCustomerTaxvat("5%");

		salesFlatQuoteC.setIsChanged(1);
		salesFlatQuoteC.setTriggerRecollect(0);
		salesFlatQuoteC.setIsPersistent(0);
	}

	/***
	 * this method remove all cart details information and customerId based remove
	 * 
	 * @param customerId
	 * @param tenantId
	 * @return
	 */
	public ResponseWrapper deleteById(String customerId, String tenantId) {
		try {

			SalesFlatQuote salesFlatQuote = salesFlatQuoteJpaRepository
					.findByEntityIdAndTenantId(Long.valueOf(customerId), tenantId);

			salesFlatQuoteJpaRepository.delete(salesFlatQuote);

			String key = "totalCartCount::" + customerId;
			redisTemplate.delete(key);
			return new ResponseWrapper(null, ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getDescription());
		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);
			return new ResponseWrapper(null, ResponseCodes02.DELETECARTITEMS_REC_FAILURE.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_FAILURE.getDescription());
		}

	}

	/***
	 * this method remove all cart details information and customerId based remove
	 * 
	 * @param customerId
	 * @param tenantId
	 * @return
	 */
	public ResponseWrapper deleteCartItemsSuspended(String customerId, String tenantId) {
		try {

			SalesFlatQuote salesFlatQuote = salesFlatQuoteJpaRepository
					.fetchAllBillerIdAndIsSuspendeddAndTenantIdOrTenantId(Long.valueOf(customerId), 1, tenantId,
							UserManagementConstant.SYSTEM_DEFAULT_TENANT_ID);
			salesFlatQuoteJpaRepository.delete(salesFlatQuote);
			String key = "totalCartCount::" + customerId;
			redisTemplate.delete(key);
			return new ResponseWrapper(null, ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getDescription());
		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);
			return new ResponseWrapper(null, ResponseCodes02.DELETECARTITEMS_REC_FAILURE.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_FAILURE.getDescription());
		}

	}

	/***
	 * this method using customer Id based cart count show
	 * 
	 * @param tenantId
	 * 
	 */
	public Long findQuoteItemCount(String customerId, String tenantId) {
		return salesFlatQuoteItemJpaRepository.cartCount(Long.valueOf(customerId), tenantId);
	}

	/***
	 * this method using CustomerId And EntityId based show cart items
	 * 
	 * @param customerId
	 * @param entityId
	 * @param pageable
	 * @param tenantId
	 * @return
	 */
	public ResponseWrapper findByCustomerIdAndEntityId(String customerId, String entityId, Pageable pageable,
			String tenantId) {
		SalesFlatQuote salesFlatQuote = salesFlatQuoteJpaRepository
				.findByBillerIdAndEntityIdAndTenantId(Integer.valueOf(customerId), Long.valueOf(entityId), tenantId);
		List<SalesFlatQuoteItem> salesFlatQuoteItem = salesFlatQuote.getSalesFlatQuoteItem().stream()
				.filter(line -> line.getIsInStock() == 0).collect(Collectors.toList());
		Page<SalesFlatQuoteItem> salesFlatQuoteItemPaginated = new PageImpl<SalesFlatQuoteItem>(salesFlatQuoteItem,
				pageable, salesFlatQuoteItem.size());

		String key = "totalCartCount::" + salesFlatQuote.getCustomerId();
		redisTemplate.delete(key);
		if (salesFlatQuote.getSalesFlatQuoteItem().size() < 0) {
			return new ResponseWrapper(null, ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getDescription());
		} else {
			return new ResponseWrapper(salesFlatQuoteItemPaginated,
					ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getDescription());
		}
	}

	/***
	 * this method using Delete cart qty count
	 * 
	 * @param salesFlatQuoteItem
	 * @param tenantId
	 * @return
	 */
	public ResponseWrapper deleteOutOfStockItemFromCart(List<SalesFlatQuoteItem> salesFlatQuoteItem, String tenantId) {
		try {
			salesFlatQuoteItem.forEach(outOfStockItem -> {
				salesFlatQuoteItemJpaRepository.delete(outOfStockItem);
			});

			return new ResponseWrapper(null, ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_SUCCESS.getDescription());
		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);
			return new ResponseWrapper(null, ResponseCodes02.DELETECARTITEMS_REC_FAILURE.getCode(),
					ResponseCodes02.DELETECARTITEMS_REC_FAILURE.getDescription());
		}
	}

	public Double getWeight(String compValue) {

		Double value = weighingScaleConnection.getWeight(compValue);

		return value;
	}

	public ResponseWrapper saveSuspendedCart(SalesFlatQuote salesFlatQuote, String tenantId) {
		try {

			salesFlatQuote.setTenantId(tenantId);

			salesFlatQuoteJpaRepository.save(salesFlatQuote);

			return new ResponseWrapper(true, ResponseCodes02.UPDATE_SUSPENDED_REC_SUCCESS.getCode(),
					ResponseCodes02.UPDATE_SUSPENDED_REC_SUCCESS.getDescription());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseWrapper(null, ResponseCodes02.UPDATE_SUSPENDED_REC_FAILURE.getCode(),
					ResponseCodes02.UPDATE_SUSPENDED_REC_FAILURE.getDescription());
		}

	}

	/***
	 * this method using add to cart new product in web menakart used
	 * 
	 * @param salesFlatQuote
	 * @param tenantId
	 * @return
	 * @throws Throwable
	 */
	@Transactional(rollbackFor = Exception.class)
	public ResponseWrapper saveCartSuspendedSales(SalesFlatQuote salesFlatQuote, String tenantId) throws Throwable {
		String operationType = salesFlatQuote.getOperationType();
		SalesFlatQuote salesFlatQuoteC = null;
//        List<SalesFlatQuoteItem> updatedCartList = new ArrayList<SalesFlatQuoteItem>();
		Double subTotal = Double.valueOf(0);
		List<SalesFlatQuoteItem> list = new ArrayList<SalesFlatQuoteItem>();

		try {

			salesFlatQuoteC = salesFlatQuoteJpaRepository.findByEntityId(salesFlatQuote.getEntityId());

			if (salesFlatQuoteC == null) {
				salesFlatQuoteC = new SalesFlatQuote();
				populateBaseQuoteDetails(salesFlatQuoteC, salesFlatQuote, tenantId);
			}

			SalesFlatQuoteItem salesFlatQuoteItem = null;

			List<SalesFlatQuoteItem> cartList = salesFlatQuote.getSalesFlatQuoteItem();
			for (SalesFlatQuoteItem salesFlatQuoteitemE : cartList) {
				salesFlatQuoteItem = salesFlatQuoteItemJpaRepository
						.findByItemId(salesFlatQuoteitemE.getItemId() == null ? 0 : salesFlatQuoteitemE.getItemId());
				if (salesFlatQuoteItem == null) {
					salesFlatQuoteItem = salesFlatQuoteItemJpaRepository.findByProductIdAndSalesFlatQuoteEntityId(
							salesFlatQuoteitemE.getProductId(), salesFlatQuoteC.getEntityId());
					if (salesFlatQuoteItem == null) {
						salesFlatQuoteItem = new SalesFlatQuoteItem();
					} else if (salesFlatQuoteItem != null
							&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
						salesFlatQuoteitemE.setQty(salesFlatQuoteItem.getQty() + salesFlatQuoteitemE.getQty());
						salesFlatQuoteitemE.setOperationType("Change");
						if (salesFlatQuoteitemE.getQty() == 0) {

							list.add(salesFlatQuoteItem);

						}

					}
				}
				if (salesFlatQuoteitemE.getOperationType() != null
						&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
					populateItemProductDetails(salesFlatQuoteItem, salesFlatQuoteitemE, tenantId);
				}

				populateQuoteItem(salesFlatQuoteItem, salesFlatQuoteitemE, salesFlatQuoteC, tenantId, list);

				if (salesFlatQuoteitemE.getOperationType() != null
						&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
					salesFlatQuoteItem.setSalesFlatQuote(salesFlatQuoteC);
				}
				if (salesFlatQuoteitemE.getOperationType() != null
						&& salesFlatQuoteitemE.getOperationType().equalsIgnoreCase("New")) {
					salesFlatQuoteC.addQuoteItem(salesFlatQuoteItem);
				}

			}

			computeTotal(salesFlatQuoteC);
			List<SalesFlatQuoteAddress> orderAddressList = salesFlatQuote.getSalesFlatQuoteAddress();

			if (salesFlatQuoteItem.getOperationType() != null
					&& salesFlatQuoteItem.getOperationType().equalsIgnoreCase("New")) {
				for (SalesFlatQuoteAddress salesFlatQuoteAddressList : orderAddressList) {
					if (salesFlatQuoteAddressList != null) {
						final long itemId = salesFlatQuoteAddressList.getAddressId();
						SalesFlatQuoteAddress salesFlatQuoteAddress = salesFlatQuoteAddressJpaRepository
								.findByAddressId(itemId);
						if (salesFlatQuoteAddress == null) {
							salesFlatQuoteAddress = new SalesFlatQuoteAddress();
						}
						populateQuoteAddress(salesFlatQuoteC, orderAddressList, salesFlatQuoteAddress);
					}
				}
			}
			salesFlatQuoteC.setIsSuspended(1);
			salesFlatQuoteC.setIsActive(0);
			salesFlatQuoteC.setIsVirtual(0);
			salesFlatQuoteC.setTenantId(tenantId);
			salesFlatQuoteC.setBillerId(salesFlatQuote.getBillerId());
			salesFlatQuoteJpaRepository.save(salesFlatQuoteC);

			String key = "totalCartCount::" + salesFlatQuoteC.getCustomerId();
			redisTemplate.delete(key);
			if (!list.isEmpty()) {
				for (SalesFlatQuoteItem item : list) {

					salesFlatQuoteItemJpaRepository.deleteByItemId(Long.valueOf(item.getItemId()));

				}
			}
			if (operationType != null && operationType.equalsIgnoreCase("New")) {
				return new ResponseWrapper(salesFlatQuote, ResponseCodes02.ADDCARTTEMS_REC_SUCCESS.getCode(),
						ResponseCodes02.ADDCARTTEMS_REC_SUCCESS.getDescription());
			}
			if (operationType != null && operationType.equalsIgnoreCase("Delete")) {
				return new ResponseWrapper(salesFlatQuote, ResponseCodes02.DELETECARTITEM_REC_SUCCESS.getCode(),
						ResponseCodes02.DELETECARTITEM_REC_SUCCESS.getDescription());
			}
			return new ResponseWrapper(salesFlatQuote, ResponseCodes02.UPDATECARTTEMS_REC_SUCCESS.getCode(),
					ResponseCodes02.UPDATECARTTEMS_REC_SUCCESS.getDescription());
		} catch (Exception MinumSaleException) {
			MinumSaleException.printStackTrace();
			return new ResponseWrapper(salesFlatQuote, ResponseCodes02.CART_UPDATE_FAILED.getCode(),
					ResponseCodes02.MIN_CART_UPDATE_FAILED.getDescription() + MinumSaleException.getMessage());
		} catch (Throwable exp) {
			logger.error("Exception while trying to save record", exp);
			if (exp instanceof InvalidException) {
				return new ResponseWrapper(salesFlatQuote, ResponseCodes02.CART_UPDATE_FAILED.getCode(),
						ResponseCodes02.CART_UPDATE_FAILED.getDescription());
			}

			if (operationType != null && operationType.equalsIgnoreCase("New")) {
				return new ResponseWrapper(null, ResponseCodes02.ADDCARTTEMS_REC_FAILURE.getCode(),
						ResponseCodes02.ADDCARTTEMS_REC_FAILURE.getDescription());
			}
			return new ResponseWrapper(salesFlatQuote, ResponseCodes02.UPDATECARTTEMS_REC_FAILURE.getCode(),
					ResponseCodes02.UPDATECARTTEMS_REC_SUCCESS.getDescription());
		}
	}

	public SalesFlatQuote findByEntityId(String entityId, String tenantId) {

		SalesFlatQuote salesFlatQuote = new SalesFlatQuote();

		salesFlatQuote = salesFlatQuoteJpaRepository.findByEntityIdAndTenantId(Long.valueOf(entityId), tenantId);

		return salesFlatQuote;
	}

}