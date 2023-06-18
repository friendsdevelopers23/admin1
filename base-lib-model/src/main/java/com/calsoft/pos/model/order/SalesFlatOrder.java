package com.calsoft.pos.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.calsoft.pos.model.creditmemo.SalesFlatCreditmemo;
import com.calsoft.pos.model.invoice.SalesFlatInvoice;
import com.calsoft.pos.model.shipment.SalesFlatShipment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales_flat_order")
@Getter
@Setter
public class SalesFlatOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private Long entityId;

	@Column(name = "state")
	private String state;

	@Column(name = "status")
	private String status;

	@Column(name = "coupon_code")
	private String couponCode;

	@Column(name = "protect_code")
	private String protectCode;

	@Column(name = "shipping_description")
	private String shippingDescription;

	@Column(name = "is_virtual")
	private long isVirtual;

	@Column(name = "store_id")
	private long storeId;

	@Column(name = "customer_id")
	private int customerId;

	@Column(name = "base_discount_amount")
	private double baseDiscountAmount;

	@Column(nullable = true, name = "base_discount_canceled")
	private Double baseDiscountCancelled;

	@Column(nullable = true, name = "base_discount_invoiced")
	private Double baseDiscountInvoice;

	@Column(nullable = true, name = "base_shipping_refunded")
	private Double baseShippingRefunded;

	@Column(nullable = true, name = "base_grand_total")
	private Double baseGrandTotal;

	@Column(name = "base_shipping_tax_amount")
	private double baseShippingTaxAmount;

	@Column(nullable = true, name = "base_shipping_tax_refunded")
	private Double baseShippingTaxRefunded;

	@Column(name = "base_subtotal")
	private double baseSubtotal;

	@Column(nullable = true, name = "base_subtotal_canceled")
	private Double baseSubtotalCancelled;

	@Column(nullable = true, name = "base_subtotal_invoiced")
	private Double baseSubtotalInvoiced;

	@Column(nullable = true, name = "base_subtotal_refunded")
	private Double baseSubtotalRefunded;

	@Column(name = "base_tax_amount")
	private double baseTaxAmount;

	@Column(nullable = true, name = "base_tax_canceled")
	private Double baseTaxCancelled;

	@Column(nullable = true, name = "base_tax_invoiced")
	private Double baseTaxInvoiced;

	@Column(nullable = true, name = "base_tax_refunded")
	private Double baseTaxRefunded;

	@Column(name = "base_to_global_rate")
	private double baseToGlobalRate;

	@Column(name = "base_to_order_rate")
	private double baseToOrderRate;

	@Column(nullable = true, name = "base_total_canceled")
	private Double baseTotalCancelled;

	@Column(nullable = true, name = "base_total_invoiced")
	private Double baseTotalInvoiced;

	@Column(nullable = true, name = "base_total_invoiced_cost")
	private Double baseTotalInvoicedCost;

	@Column(nullable = true, name = "base_total_offline_refunded")
	private Double baseTotalOfflineRefunded;

	@Column(nullable = true, name = "base_total_online_refunded")
	private Double baseTotalOnlineRefunded;

	@Column(nullable = true, name = "base_total_paid")
	private Double baseTotalPaid;

	@Column(nullable = true, name = "base_total_qty_ordered")
	private Double baseTotalQtyOrdered;

	@Column(nullable = true, name = "base_total_refunded")
	private Double baseTotalRefunded;

	@Column(name = "discount_amount")
	private double discountAmount;

	@Column(nullable = true, name = "discount_canceled")
	private Double discountCancelled;

	@Column(nullable = true, name = "discount_invoiced")
	private Double discountInvoiced;

	@Column(nullable = true, name = "discount_refunded")
	private Double discountRefunded;

	@Column(nullable = true, name = "grand_total")
	private Double grandTotal;

	@Column(nullable = true, name = "shipping_amount")
	private Double shippingAmount;

	@Column(nullable = true, name = "shipping_canceled")
	private Double shippingCancelled;

	@Column(nullable = true, name = "shipping_invoiced")
	private Double shippingInvoiced;

	@Column(nullable = true, name = "shipping_refunded")
	private Double shippingRefunded;

	@Column(nullable = true, name = "shipping_tax_amount")
	private Double shippingTaxAmount;

	@Column(nullable = true, name = "shipping_tax_refunded")
	private Double shippingTaxRefunded;

	@Column(name = "store_to_base_rate")
	private double storeToBaseRate;

	@Column(name = "store_to_order_rate")
	private double storeToOrderRate;

	@Column(name = "subtotal")
	private double subtotal;

	@Column(nullable = true, name = "subtotal_canceled")
	private Double subtotalCancelled;

	@Column(nullable = true, name = "subtotal_invoiced")
	private Double subtotalInvoiced;

	@Column(nullable = true, name = "subtotal_refunded")
	private Double subtotalRefunded;

	@Column(name = "tax_amount")
	private double taxAmount;

	@Column(nullable = true, name = "tax_canceled")
	private Double taxCancelled;

	@Column(nullable = true, name = "tax_invoiced")
	private Double taxInvoiced;

	@Column(nullable = true, name = "tax_refunded")
	private Double taxRefunded;

	@Column(nullable = true, name = "total_canceled")
	private Double totalCancelled;

	@Column(nullable = true, name = "total_invoiced")
	private Double totalInvoiced;

	@Column(nullable = true, name = "total_offline_refunded")
	private Double totalOfflineRefunded;

	@Column(nullable = true, name = "total_online_refunded")
	private Double totalOnlineRefunded;

	@Column(nullable = true, name = "total_paid")
	private Double totalPaid;

	@Column(name = "total_qty_ordered")
	private Double itemsQty;

	@Column(nullable = true, name = "total_refunded")
	private Double totalRefunded;

	@Column(nullable = true, name = "can_ship_partially")
	private Long canShipPartially;

	@Column(nullable = true, name = "can_ship_partially_item")
	private Long canShipPartiallyItem;

	@Column(name = "customer_is_guest")
	private long customerIsGuest;

	@Column(name = "customer_note_notify")
	private long customerNoteNotify;

	@Column(name = "billing_address_id")
	private long billingAddressId;

	@Column(name = "customer_group_id")
	private long customerGroupId;

	@Column(nullable = true, name = "edit_increment")
	private Long editIncrement;

	@Column(name = "email_sent")
	private long emailSent;

	@Column(nullable = true, name = "forced_shipment_with_invoice")
	private Long forcedShipmentWithInvoice;

	@Column(nullable = true, name = "payment_auth_expiration")
	private Long paymentAuthExpiration;

	@Column(nullable = true, name = "quote_address_id")
	private Long quoteAddressId;

	@Column(name = "quote_id")
	private long quoteId;

	@Column(name = "shipping_address_id")
	private long shippingAddressId;

	@Column(nullable = true, name = "adjustment_negative")
	private Double adjustmentNegative;

	@Column(nullable = true, name = "adjustment_positive")
	private Double adjustmentPositive;

	@Column(nullable = true, name = "base_adjustment_positive")
	private Double baseAdjustmentPositive;

	@Column(name = "base_shipping_discount_amount")
	private double baseShippingDiscountAmount;

	@Column(name = "base_subtotal_incl_tax")
	private double baseSubtotalInclTax;

	@Column(nullable = true, name = "base_total_due")
	private Double baseTotalDue;

	@Column(nullable = true, name = "payment_authorization_amount")
	private Double paymentAuthorizationAmount;

	@Column(name = "shipping_discount_amount")
	private double shippingDiscountAmount;

	@Column(name = "subtotal_incl_tax")
	private double subtotalInclTax;

	@Column(nullable = true, name = "total_due")
	private Double totalDue;

	@Column(name = "weight")
	private double weight;

	@Column(name = "customer_dob")
	private Date customerDob;

	@Column(name = "increment_id")
	private String incrementId;

	@Column(name = "applied_rule_ids")
	private String appliedRuleIds;

	@Column(name = "base_currency_code")
	private String baseCurrencyCode;

	@Column(name = "customer_email")
	private String customerEmail;

	@Column(name = "customer_firstname")
	private String customerFirstname;

	@Column(name = "customer_lastname")
	private String customerLastname;

	@Column(name = "customer_middlename")
	private String customerMiddlename;

	@Column(name = "customer_prefix")
	private String customerPrefix;

	@Column(name = "customer_suffix")
	private String customerSuffix;

	@Column(name = "customer_taxvat")
	private String customerTaxvat;

	@Column(name = "discount_description")
	private String discountDescription;

	@Column(name = "ext_customer_id")
	private String extCustomerId;

	@Column(name = "ext_order_id")
	private String extOrderId;

	@Column(name = "global_currency_code")
	private String globalCurrencyCode;

	@Column(name = "hold_before_state")
	private String holdBeforeState;

	@Column(name = "hold_before_status")
	private String holdBeforeStatus;

	@Column(name = "order_currency_code")
	private String orderCurrencyCode;

	@Column(name = "original_increment_id")
	private String originalIncrementId;

	@Column(name = "relation_child_id")
	private String relationChildId;

	@Column(name = "relation_child_real_id")
	private String relationChildRealId;

	@Column(name = "relation_parent_id")
	private String RelationParentId;

	@Column(name = "relation_parent_real_id")
	private String relationParentRealId;

	@Column(name = "remote_ip")
	private String remoteIp;

	@Column(name = "shipping_method")
	private String shippingMethod;

	@Column(name = "store_currency_code")
	private String storeCurrencyCode;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "x_forwarded_for")
	private String xForwardedFor;

	@Column(name = "customer_note")
	private String customer_note;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "total_item_count")
	private long itemsCount;

	@Column(nullable = true, name = "customer_gender")
	private Long customerGender;

	@Column(name = "hidden_tax_amount")
	private double hiddenTaxAmount;

	@Column(name = "base_hidden_tax_amount")
	private double baseHiddenTaxAmount;

	@Column(name = "shipping_hidden_tax_amount")
	private double shippingHiddenTaxAmount;

	@Column(name = "base_shipping_hidden_tax_amnt")
	private double baseShippingHiddenTaxAmnt;

	@Column(nullable = true, name = "hidden_tax_invoiced")
	private Double hiddenTaxInvoiced;

	@Column(nullable = true, name = "base_hidden_tax_invoiced")
	private Double baseHiddenTaxInvoiced;

	@Column(nullable = true, name = "hidden_tax_refunded")
	private Double hiddenTaxRefunded;

	@Column(nullable = true, name = "base_hidden_tax_refunded")
	private Double baseHiddenTaxRefunded;

	@Column(name = "shipping_incl_tax")
	private double shippingInclTax;

	@Column(name = "base_shipping_incl_tax")
	private double baseShippingInclTax;

	@Column(name = "coupon_rule_name")
	private String couponRuleName;

	@Column(name = "paypal_ipn_customer_notified")
	private long paypalIpnCustomerNotified;

	@Column(nullable = true, name = "gift_message_id")
	private Long giftMessageId;

	@OneToMany(mappedBy = "salesFlatOrder", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<SalesFlatOrderAddress> salesFlatOrderAddress = new ArrayList<SalesFlatOrderAddress>();

	@Transient
	private SalesFlatOrderAddress shippingAddress;

	@Transient
	private Long invoiceId;

	@Transient
	private Long shipmentId;

	@Transient
	private Date dateShipped;

	@Transient
	private Long creditmemoId;

	@Transient
	private Date createdAt;

	@OneToMany(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatOrderStatusHistory> salesFlatOrderStatusHistory = new ArrayList<SalesFlatOrderStatusHistory>();

	@OneToMany(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatOrderItem> salesFlatOrderItem = new ArrayList<SalesFlatOrderItem>();

	@OneToMany(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<SalesFlatCreditmemo> salesFlatCreditmemo = new ArrayList<SalesFlatCreditmemo>();

	@OneToOne(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	@JsonManagedReference
	private SalesFlatInvoice salesFlatInvoice;

	@OneToMany(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<SalesFlatShipment> salesFlatShipment = new ArrayList<SalesFlatShipment>();

	@OneToOne(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	@JsonManagedReference
	private SalesFlatOrderGrid salesFlatOrderGrid;

	/*
	 * @OneToMany(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	 * 
	 * @JsonManagedReference(value = "salesFlatOrder") private
	 * List<SalesPaymentTransaction> salesPaymentTransaction = new
	 * ArrayList<SalesPaymentTransaction>();
	 */
	@OneToOne(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	@JsonManagedReference
	private SalesFlatOrderPayment salesFlatOrderPayment;

	@OneToMany(mappedBy = "salesFlatOrder", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<SalesFlatOrderPrescription> salesFlatOrderPrescription = new ArrayList<SalesFlatOrderPrescription>();

	@Transient
	private String operationType;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Transient
	private Double totalOrders;

	@Transient
	private Double sumTotalInvoiced;

	@Transient
	private Long totalNoOfOrder;

	@Transient
	private Double averageOrder;

	@Transient
	private Double totalQty;

	@Column(name = "biller_id")
	private Long billerId;

	@Column(name = "biller")
	private String biller;

	@Column(name = "pos")
	private int pos;

	@Column(name = "type")
	private String type;

	@Column(name = "changeAmount")
	private Double changeAmount;

	@Transient
	private double discountPercent;

	@Transient
	private boolean maxDiscountAmountMethod;

	@Column(name = "delivery_option")
	private String deliveryOption;

	@Column(name = "calculate_shipping_rate_based_on_vendors")
	private int calculateShippingRateBasedOnVendors;

	@Column(name = "additional_delivery_amount")
	private double additionalDeliveryAmount;

	@Column(name = "prescription_attached")
	private int prescriptionAttached;

	@Column(name = "prescription_object")
	private String prescriptionObject;

	@Column(name = "product_type_medical")
	private int productTypeMedical;

	@Column(name = "additional_medical_charge")
	private double additionalMedicalharge;

	@Column(name = "inclusive_tax")
	private int inclusiveTax;

	@Column(name = "cgst_amount")
	private Double cgstAmount;

	@Column(name = "sgst_amount")
	private Double sgstAmount;

	@Column(name = "igst_amount")
	private Double igstAmount;

	@Column(name = "cess")
	private Double cess;

	@Column(name = "partial_paid")
	private int partialPaid;

	@Column(name = "partial_paid_amount")
	private Double partialPaidAmount;

	@Column(name = "remaining_balance")
	private Double remainingBalance;

	@Column(name = "vendor_enabled")
	private int vendorEnabled;

	@Transient
	private String cartId;

	@Column(name = "total_supplier_commission_amount")
	private double totalSupplierCommissionAmount;

	@Column(name = "supplier_commission_rule")
	private String supplierCommissionRule;

	@Column(name = "supplier_commission_refunded")
	private double supplierCommissionRefunded; 

	@Column(name = "supplier_commision_cancelled")
	private double supplierCommisionCancelled;
	
	@Column(name = "total_referral_commission_amount")
	private double totalReferralCommissionAmount;

	@Column(name = "referral_commission_rule")
	private String referralCommissionRule;
   	
	@Column(name = "referral_commission_refunded")
	private double referralCommissionRefunded;
	
	@Column(name = "referral_commision_cancelled")
	private double referralCommisionCancelled;
	
	@Column(name = "referral_commision_percent")
	private String referralCommisionPercent;

	public SalesFlatOrder(Double totalOrders, Double sumTotalInvoiced, Long totalNoOfOrder, Double averageOrder,
			Double totalQty) {

		this.totalOrders = totalOrders;
		this.sumTotalInvoiced = sumTotalInvoiced;
		this.totalNoOfOrder = totalNoOfOrder;
		this.averageOrder = averageOrder;
		this.totalQty = totalQty;
	}

	public SalesFlatOrder() {
		super();
	}

}
