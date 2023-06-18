package com.calsoft.pos.model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales_flat_order_item")
@Getter
@Setter
public class SalesFlatOrderItem implements Serializable {

	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Integer itemId;

	@Column(name = "parent_item_id")
	private Long parentItemId;

	@Column(name = "quote_item_id")
	private Long quoteItemId;

	@Column(name = "store_id")
	private Long storeId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_type")
	private String productType;

	@Column(name = "product_options")
	private String productOptions;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "is_virtual")
	private Long isVirtual;

	@Column(name = "sku")
	private String sku;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "applied_rule_ids")
	private String appliedRuleIds;

	@Column(name = "additional_data")
	private String additionalData;

	@Column(name = "free_shipping")
	private Long freeShipping;

	@Column(name = "is_qty_decimal")
	private Long isQtyecDimal;

	@Column(name = "no_discount")
	private Long noDiscount;

	@Column(name = "qty_backordered")
	private Double qtyBackordered;

	@Column(name = "qty_canceled")
	private Double qtyCancelled;

	@Column(name = "qty_invoiced")
	private Double qtyInvoiced;

	@Column(name = "qty_ordered")
	private Double qty;

	@Column(name = "qty_refunded")
	private Double qtyRefunded;

	@Column(name = "qty_shipped")
	private Double qtyShipped;

	@Column(name = "base_cost")
	private Double baseCost;

	@Column(name = "price")
	private Double price;

	@Column(name = "base_price")
	private Double basePrice;

	@Column(name = "base_original_price")
	private Double baseOriginalPrice;

	@Column(name = "tax_percent")
	private Double taxPercent;

	@Column(name = "tax_amount")
	private Double taxAmount;

	@Column(name = "base_tax_amount")
	private Double baseTaxAmount;

	@Column(name = "tax_invoiced")
	private Double taxInvoiced;

	@Column(name = "base_tax_invoiced")
	private Double baseTaxInvoiced;

	@Column(name = "discount_percent")
	private Double discountPercent;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "base_discount_amount")
	private Double baseDiscountAmount;

	@Column(name = "discount_invoiced")
	private Double discountInvoiced;

	@Column(name = "base_discount_invoiced")
	private Double baseDiscountInvoiced;

	@Column(name = "amount_refunded")
	private Double amountRefunded;

	@Column(name = "base_amount_refunded")
	private Double baseAmountRefunded;

	@Column(name = "row_total")
	private Double rowTotal;

	@Column(name = "base_row_total")
	private Double baseRowTotal;

	@Column(name = "row_invoiced")
	private Double rowInvoiced;

	@Column(name = "base_row_invoiced")
	private Double baseRowInvoiced;

	@Column(name = "row_weight")
	private Double rowWeight;

	@Column(name = "base_tax_before_discount")
	private Double baseTaxBeforeDiscount;

	@Column(name = "tax_before_discount")
	private Double taxBeforeDiscount;

	@Column(name = "ext_order_item_id")
	private String extOrderItemId;

	@Column(name = "locked_do_invoice")
	private Long lockedDoInvoice;

	@Column(name = "locked_do_ship")
	private Long lockedDoShip;

	@Column(name = "price_incl_tax")
	private Double priceInclTax;

	@Column(name = "base_price_incl_tax")
	private Double basePriceInclTax;

	@Column(name = "row_total_incl_tax")
	private Double rowTotalInclTax;

	@Column(name = "base_row_total_incl_tax")
	private Double baseRowTotalInclTax;

	@Column(name = "hidden_tax_amount")
	private Double hiddenTaxAmount;

	@Column(name = "base_hidden_tax_amount")
	private Double baseHiddenTaxAmount;

	@Column(name = "hidden_tax_invoiced")
	private Double hiddenTaxInvoiced;

	@Column(name = "base_hidden_tax_invoiced")
	private Double baseHiddenTaxInvoiced;

	@Column(name = "hidden_tax_refunded")
	private Double hiddenTaxRefunded;

	@Column(name = "base_hidden_tax_refunded")
	private Double baseHiddenTaxRefunded;

	@Column(name = "is_nominal")
	private Long isNominal;

	@Column(name = "tax_canceled")
	private Double taxCanceled;

	@Column(name = "hidden_tax_canceled")
	private Double hiddenTaxCanceled;

	@Column(name = "tax_refunded")
	private Double taxRefunded;

	@Column(name = "base_tax_refunded")
	private Double baseTaxRefunded;

	@Column(name = "discount_refunded")
	private Double discountRefunded;

	@Column(name = "base_discount_refunded")
	private Double baseDiscountRefunded;

	@Column(name = "gift_message_id")
	private Long giftMessageId;

	@Column(name = "gift_message_available")
	private Long giftMessageAvailable;

	@Column(name = "base_weee_tax_applied_amount")
	private Double baseWeeeTaxAppliedAmount;

	@Column(name = "base_weee_tax_applied_row_amnt")
	private Double baseWeeeTaxAppliedRowAmnt;

	@Column(name = "weee_tax_applied_amount")
	private Double weeeTaxAppliedAmount;

	@Column(name = "weee_tax_applied_row_amount")
	private Long weeeTaxAppliedRowAmount;

	@Column(name = "weee_tax_applied")
	private String weeeTaxApplied;

	@Column(name = "weee_tax_disposition")
	private Long weeeTaxDisposition;

	@Column(name = "weee_tax_row_disposition")
	private Double weeeTaxRowDisposition;

	@Column(name = "base_weee_tax_disposition")
	private Long baseWeeeTaxDisposition;

	@Column(name = "base_weee_tax_row_disposition")
	private Long baseWeeeTaxRowDisposition;

	@Column(name = "product_image")
	private String imageUrl;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "order_id")
	private SalesFlatOrder salesFlatOrder;

	@Column(name = "order_id", insertable = false, updatable = false)
	private Long orderId;

	@Column(name = "unit")
	private String unit;

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

	@Column(name = "prescription_required")
	private int prescriptionRequired;

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

	@Column(name = "partial_paid_amount")
	private Double partialPaidAmount;

	@Column(name = "additional_medical_charge")
	private Double additionalMedicalCharge;

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

	@Column(name = "category_Id")
	private String categoryId;

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

	@Transient
	private String phone;

	@Transient
	private String address;

	@Transient
	private String city;

	@Transient
	private String country;

	@Transient
	private String sid;

	public SalesFlatOrderItem(String supplierName, double supplierCommisionIndividual,
			double supplierCommisionCancelled, double supplierCommissionRefunded, String phone, String address,
			String city, String country, String supplierId) {

		this.supplierName = supplierName;
		this.supplierCommisionIndividual = supplierCommisionIndividual;
		this.supplierCommisionCancelled = supplierCommisionCancelled;
		this.supplierCommissionRefunded = supplierCommissionRefunded;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.country = country;
		this.supplierId = supplierId;

	}

	public SalesFlatOrderItem(String name, String sku, Double qtyCancelled, Double qtyInvoiced, Double qty,
			Double qtyRefunded, double supplierCommissionAmount, double supplierCommisionCancelled,
			double supplierCommissionRefunded, Long productId) {
		this.name = name;
		this.sku = sku;
		this.qtyCancelled = qtyCancelled;
		this.qtyInvoiced = qtyInvoiced;
		this.qty = qty;
		this.qtyRefunded = qtyRefunded;
		this.supplierCommissionAmount = supplierCommissionAmount;
		this.supplierCommisionCancelled = supplierCommisionCancelled;
		this.supplierCommissionRefunded = supplierCommissionRefunded;
		this.productId = productId;
	}

	public SalesFlatOrderItem() {
		super();
	}


}
