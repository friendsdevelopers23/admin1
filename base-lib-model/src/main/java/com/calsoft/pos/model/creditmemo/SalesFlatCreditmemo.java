package com.calsoft.pos.model.creditmemo;

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
import javax.persistence.ManyToOne;
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
@Table(name = "sales_flat_creditmemo")
@Getter
@Setter
public class SalesFlatCreditmemo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id", insertable = false, updatable = false)
	public Long entityId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "order_id")
	private SalesFlatOrder salesFlatOrder;

	@OneToMany(mappedBy = "salesFlatCreditmemo", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatCreditmemoItem> salesFlatCreditmemoItem = new ArrayList<SalesFlatCreditmemoItem>();

	@OneToMany(mappedBy = "salesFlatCreditmemo", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatCreditmemoComment> salesFlatCreditmemoComment = new ArrayList<SalesFlatCreditmemoComment>();;

	@OneToOne(mappedBy = "salesFlatCreditmemo", cascade = CascadeType.ALL)
	@JsonManagedReference
	private SalesFlatCreditmemoGrid salesFlatCreditmemoGrid;

	@Column(name = "store_id")
	private Long storeId;

	@Column(name = "adjustment_positive")
	private Double adjustmentPositive;

	@Column(name = "base_shipping_tax_amount")
	private Double baseShippingTaxAmount;

	@Column(name = "store_to_order_rate")
	private Double storeToOrderRate;

	@Column(name = "base_discount_amount")
	private Double baseDiscountAmount;

	@Column(name = "base_to_order_rate")
	private Double baseToOrderRate;

	@Column(name = "grand_total")
	private Double grandTotal;

	@Column(name = "base_adjustment_negative")
	private Double baseAdjustmentNegative;

	@Column(name = "base_subtotal_incl_tax")
	private Double baseSubtotalInclTax;

	@Column(name = "shipping_amount")
	private Double shippingAmount;

	@Column(name = "subtotal_incl_tax")
	private Double subtotalInclTax;

	@Column(name = "adjustment_negative")
	private Double adjustmentNegative;

	@Column(name = "base_shipping_amount")
	private Double baseShippingAmount;

	@Column(name = "store_to_base_rate")
	private Double storeToBaseRate;

	@Column(name = "base_to_global_rate")
	private Double baseToGlobalRate;

	@Column(name = "base_adjustment")
	private Double baseAdjustment;

	@Column(name = "base_subtotal")
	private Double baseSubtotal;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "subtotal")
	private Double subtotal;

	@Column(name = "adjustment")
	private Double adjustment;

	@Column(name = "base_grand_total")
	private Double baseGrandTotal;

	@Column(name = "base_adjustment_positive")
	private Double baseAdjustmentPositive;

	@Column(name = "base_tax_amount")
	private Double baseTaxAmount;

	@Column(name = "shipping_tax_amount")
	private Double shippingTaxAmount;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@Column(name = "email_sent")
	private Long emailSent;

	@Column(name = "creditmemo_status")
	private Long creditmemoStatus;

	@Column(name = "state")
	private Long state;

	@Column(name = "shipping_address_id")
	private Long shippingAddressId;

	@Column(name = "billing_address_id")
	private Long billingAddressId;

	@Column(name = "invoice_id")
	private Long invoiceId;

	@Column(name = "store_currency_code")
	private String storeCurrencyCode;

	@Column(name = "order_currency_code")
	private String orderCurrencyCode;

	@Column(name = "base_currency_code")
	private String baseCurrencyCode;

	@Column(name = "global_currency_code")
	private String globalCurrencyCode;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "increment_id")
	private String incrementId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	public Date createdDate;

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
	private Double baseMultifeesAmount;

	@Column(name = "multifees_tax_amount")
	private Double multifeesTaxAmount;

	@Column(name = "base_multifees_tax_amount")
	private Double baseMultifeesTaxAmount;

	@Column(name = "details_multifees")
	private String detailsMultifees;

	@Transient
	private String operationType;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

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