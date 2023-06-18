package com.calsoft.pos.model.creditmemo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "sales_flat_creditmemo_item")
@Data
public class SalesFlatCreditmemoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private long entityId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private SalesFlatCreditmemo salesFlatCreditmemo;

	@Column(name = "base_price")
	private Double basePrice;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@Column(name = "base_row_total")
	private Double baseRowTotal;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "row_total")
	private Double rowTotal;

	@Column(name = "base_discount_amount")
	private Double baseDiscountAmount;

	@Column(name = "price_incl_tax")
	private Double priceInclTax;

	@Column(name = "base_tax_amount")
	private Double baseTaxAmount;

	@Column(name = "base_price_incl_tax")
	private Double basePriceInclTax;

	@Column(name = "qty")
	private Double qty;

	@Column(name = "base_cost")
	private Double baseCost;

	@Column(name = "price")
	private Double price;

	@Column(name = "base_row_total_incl_tax")
	private Double baseRowTotalInclTax;

	@Column(name = "row_total_incl_tax")
	private Double rowTotalInclTax;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "order_item_id")
	private long orderItemId;

	@Column(name = "additional_data")
	private String additionalData;

	@Column(name = "description")
	private String description;

	@Column(name = "sku")
	private String sku;

	@Column(name = "name")
	private String name;

	@Column(name = "hidden_tax_amount")
	private Double hiddenTaxAmount;

	@Column(name = "base_hidden_tax_amount")
	private Double baseHiddenTaxAmount;

	@Column(name = "base_weee_tax_applied_amount")
	private Double baseWeeeTaxAppliedAmount;

	@Column(name = "base_weee_tax_applied_row_amnt")
	private Double baseWeeeTaxAppliedRowAmnt;

	@Column(name = "weee_tax_applied_amount")
	private Double weeeTaxAppliedAmount;

	@Column(name = "weee_tax_applied_row_amount")
	private Double weeeTaxAppliedRowAmount;

	@Column(name = "weee_tax_applied")
	private String weeeTaxApplied;

	@Column(name = "weee_tax_disposition")
	private Double weeeTaxDisposition;

	@Column(name = "weee_tax_row_disposition")
	private Double weeeTaxRowDisposition;

	@Column(name = "base_weee_tax_disposition")
	private Double baseWeeeTaxDisposition;

	@Column(name = "base_weee_tax_row_disposition")
	private Double baseWeeeTaxRowDisposition;

	@Transient
	private Double qtyRefunded;

	@Transient
	private Double qtyInvoiced;

	@Transient
	private String operationType;

	@Transient
	private double taxPercent;

	@Column(name = "supplier_latitude")
	private String supplierLatitude;

	@Column(name = "supplier_longitude")
	private String supplierLongitude;

	@Column(name = "supplier_id")
	private String supplierId;

	@Column(name = "supplier_name")
	private String supplierName;

	@Column(name = "shipping_amount")
	private Double shippingAmount;

	@Column(name = "cgst_amount")
	private Double cgstAmount;

	@Column(name = "sgst_amount")
	private Double sgstAmount;

	@Column(name = "igst_amount")
	private Double igstAmount;

	@Column(name = "inclusive_tax")
	private int inclusiveTax;

	@Column(name = "cgst")
	private Double cgst;

	@Column(name = "sgst")
	private Double sgst;

	@Column(name = "igst")
	private Double igst;

	@Column(name = "stock_in_hand")
	private Double stockInHand;

	@Column(name = "cess")
	private Double cess;
	
	@Column(name = "supplier_commision_percent")
	private double supplierCommissionPercent;

	@Column(name = "supplier_commission_refunded")
	private double supplierCommissionRefunded;

	@Column(name = "supplier_commision_individual")
	private double supplierCommisionIndividual;

	@Column(name = "supplier_commision_based_on_category")
	private int supplierCommisionBasedOnCategory;

	@Column(name = "supplier_commision_amount")
	private double supplierCommissionAmount;
	
	@Column(name = "supplier_commision_cancelled")
	private double supplierCommisionCancelled;
	
	@Column(name = "total_referral_commission_amount")
	private double totalReferralCommissionAmount;

	@Column(name = "referral_commission_rule")
	private String referralCommissionRule;
	
	@Column(name = "referral_commision_percent")
	private String referralCommisionPercent;
   
	@Column(name = "referral_commission_refunded")
	private double referralCommissionRefunded;
	
	@Column(name = "referral_commision_cancelled")
	private double referralCommisionCancelled;
	

}
