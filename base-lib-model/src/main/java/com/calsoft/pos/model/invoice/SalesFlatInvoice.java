package com.calsoft.pos.model.invoice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.calsoft.pos.model.order.SalesFlatOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales_flat_invoice")
@Getter
@Setter
public class SalesFlatInvoice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	public long entityId;

	@Column(name = "store_id")
	private Long storeid;

	@Column(name = "base_grand_total")
	private Double baseGrandTotal;

	@Column(name = "shipping_tax_amount")
	private Double shippingTaxAmount;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@Column(name = "base_tax_amount")
	private Double baseTaxAmount;

	@Column(name = "store_to_order_rate")
	private Double storeToOrderRate;

	@Column(name = "base_shipping_tax_amount")
	private Double baseShippingTaxAmount;

	@Column(name = "base_discount_amount")
	private Double baseDiscountAmount;

	@Column(name = "base_to_order_rate")
	private Double baseToOrderRate;

	@Column(name = "grand_total")
	private Double grandTotal;

	@Column(name = "shipping_amount")
	private Double shippingAmount;

	@Column(name = "subtotal_incl_tax")
	private Double subtotalInclTax;

	@Column(name = "base_subtotal_incl_tax")
	private Double baseSubtotalInclTax;

	@Column(name = "store_to_base_rate")
	private Double storeToBaseRate;

	@Column(name = "base_shipping_amount")
	private Double baseShippingAmount;

	@Column(name = "total_qty")
	private Double totalQty;

	@Column(name = "base_to_global_rate")
	private Double baseToGlobalRate;

	@Column(name = "subtotal")
	private Double subtotal;

	@Column(name = "base_subtotal")
	private Double baseSubtotal;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "billing_address_id")
	private Long billingAddressId;

	@Column(name = "is_used_for_refund")
	private Long isUsedForRefund;

	@Column(name = "email_sent")
	private Long emailSent;

	@Column(name = "can_void_flag")
	private Long canVoidFlag;

	@Column(name = "state")
	private Long state;

	@Column(name = "shipping_address_id")
	private Long shippingAddressId;

	@Column(name = "store_currency_code")
	private String storeCurrencyCode;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "order_currency_code")
	private String orderCurrencyCode;

	@Column(name = "base_currency_code")
	private String baseCurrencyCode;

	@Column(name = "global_currency_code")
	private String globalCurrencyCode;

	@Column(name = "increment_id")
	private String incrementId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "hidden_tax_amount")
	private Double hiddenTaxAmount;

	@Column(name = "base_hidden_tax_amount")
	private Double baseHiddenTaxAmount;

	@Column(name = "shipping_hidden_tax_amount")
	private Double shippingHiddenTaxAmount;

	@Column(name = "base_shipping_hidden_tax_amnt")
	private Double baseShippingHiddenTaxAmnt;

	@Column(name = "shipping_incl_tax")
	private Double shippingInclTax;

	@Column(name = "base_shipping_incl_tax")
	private Double baseShippingInclTax;

	@Column(name = "base_total_refunded")
	private Double baseTotalRefunded;

	@Column(name = "discount_description")
	private String discountDescription;

	@Column(name = "cod_fee")
	private Double codFee;

	@Column(name = "base_cod_fee")
	private Double baseCodFee;

	@Column(name = "cod_tax_amount")
	private Double codTaxAmount;

	@Column(name = "base_cod_tax_amount")
	private Double baseCodTaxAmount;

	@Column(name = "multifees_amount")
	private Double multifeesAmount;

	@Column(name = "base_multifees_amount", columnDefinition = "Decimal(10,2) default '0.00'")
	private Double baseMultifeesAmount = 0.0;

	@Column(name = "multifees_tax_amount", columnDefinition = "Decimal(10,2) default '0.00'")
	private Double multifeesTaxAmount = 0.0;

	@Column(name = "base_multifees_tax_amount")
	private Double baseMultifeesTaxAmount;

	@Column(name = "details_multifees")
	private String detailsMultifees;

	@OneToMany(mappedBy = "salesFlatInvoice", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatInvoiceItem> salesFlatInvoiceItem = new ArrayList<SalesFlatInvoiceItem>();

	@OneToMany(mappedBy = "salesFlatInvoice", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatInvoiceComment> salesFlatInvoiceComment = new ArrayList<SalesFlatInvoiceComment>();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "salesFlatInvoice")
	private SalesFlatInvoiceGrid salesFlatInvoiceGrid;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "order_id")
	private SalesFlatOrder salesFlatOrder;

	@Transient
	private String operationType;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Column(name = "order_id", insertable = false, updatable = false)
	private Long orderId;

	@Column(name = "additional_medical_charge")
	private double additionalMedicalharge;

	@Column(name = "cgst_amount")
	private Double cgstAmount;

	@Column(name = "sgst_amount")
	private Double sgstAmount;

	@Column(name = "igst_amount")
	private Double igstAmount;

	@Column(name = "inclusive_tax")
	private int inclusiveTax;

	@Column(name = "cess")
	private Double cess;

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

}
