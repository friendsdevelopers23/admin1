package com.calsoft.pos.model.cart;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "sales_flat_quote_item")
@Data
public class SalesFlatQuoteItem implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long itemId;

	@Column(name = "quote_id", insertable = false, updatable = false)
	private long quoteId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "store_id")
	private long storeId;

	/*
	 * @Column(name = "parent_item_id") private long parentItemId;
	 */

	@Column(name = "is_virtual")
	private long isVirtual;

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
	private long freeShipping;

	@Column(name = "is_qty_decimal")
	private long isQtyDecimal;

	@Column(name = "no_discount")
	private long noDiscount;

	@Column(name = "weight")
	private double weight;

	@Column(name = "qty")
	private double qty = 0.0;

	@Column(name = "price")
	private double price;

	@Column(name = "base_price")
	private double basePrice;

	@Column(name = "custom_price")
	private Double customPrice;

	@Column(name = "discount_percent")
	private double discountPercent;

	@Column(name = "discount_amount")
	private double discountAmount;

	@Column(name = "base_discount_amount")
	private double baseDiscountAmount;

	@Column(name = "tax_percent")
	private double taxPercent;

	@Column(name = "tax_amount")
	private double taxAmount;

	@Column(name = "base_tax_amount")
	private double baseTaxAmount;

	@Column(name = "row_total")
	private double rowTotal;

	@Column(name = "base_row_total")
	private double baseRowTotal;

	@Column(name = "row_total_with_discount")
	private double rowTotalWithDiscount;

	@Column(name = "row_weight")
	private double rowWeight;

	@Column(name = "product_type")
	private String productType;

	/*
	 * @Column(name = "base_tax_before_discount") private double
	 * baseTaxBeforeDiscount;
	 */

	/*
	 * @Column(name = "tax_before_discount") private double taxBeforeDiscount;
	 *
	 * @Column(name = "original_custom_price") private double originalCustomPrice;
	 */
	@Column(name = "redirect_url")
	private String redirectUrl;

	/*
	 * @Column(name = "base_cost") private double baseCost;
	 */

	@Column(name = "price_incl_tax")
	private double priceInclTax;

	@Column(name = "base_price_incl_tax")
	private double basePriceInclTax;

	@Column(name = "row_total_incl_tax")
	private double rowTotalInclTax;

	@Column(name = "base_row_total_incl_tax")
	private double baseRowTotalInclTax;

	@Column(name = "hidden_tax_amount")
	private double hiddenTaxAmount;

	@Column(name = "base_hidden_tax_amount")
	private double baseHiddenTaxAmount;

	/*
	 * @Column(name = "gift_message_id") private long giftMessageId;
	 */

	@Column(name = "weee_tax_disposition")
	private double weeeTaxDisPosition;

	@Column(name = "weee_tax_row_disposition")
	private double weeeTaxRowDisposition;

	@Column(name = "base_weee_tax_disposition")
	private double baseWeeeTaxDisposition;

	@Column(name = "base_weee_tax_row_disposition")
	private double baseWeeeTaxRowDisposition;

	@Column(name = "weee_tax_applied")
	private String weeeTaxApplied;

	@Column(name = "product_image")
	private String imageUrl;

	@Column(name = "weee_tax_applied_amount")
	private double weeeTaxAppliedAmount;

	/*
	 * @Column(name = "weee_tax_applied_row_amount") private double
	 * weeeTaxAppliedRowAmount;
	 */

	@Column(name = "base_weee_tax_applied_amount")
	private double baseWeeeTaxAppliedAmount;

	@Column(name = "is_in_stock")
	private int isInStock;

	@Column(name = "product_custom_option")
	private String productCustomOption;

	@Column(name = "unit")
	private String unit;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "quote_id")
	private SalesFlatQuote salesFlatQuote;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "salesFlatQuoteItem", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonManagedReference
	private List<SalesFlatQuoteItemOption> salesFlatQuoteItemOption = new ArrayList<SalesFlatQuoteItemOption>();

	@Transient
	private List<SalesFlatQuoteItemOption> salesFlatQuoteItemOptionDuplicate = new ArrayList<SalesFlatQuoteItemOption>();

	@Transient
	private long itemsCount;

	@Transient
	private long orders;

	@Transient
	private String currencyCode;

	@Transient
	private int customerId;

	@Transient
	private Long cartCount;

	@Transient
	private String remoteIp;

	@Transient
	private String operationType = "NoChange";

	@Column(name = "category_Id")
	private String categoryId;

	@Column(name = "supplier_latitude")
	private String supplierLatitude;

	@Column(name = "supplier_longitude")
	private String supplierLongitude;

	@Column(name = "supplier_id")
	private String supplierId;

	@Column(name = "supplier_name")
	private String supplierName;

	@Column(name = "supplier_pincode")
	private String supplierPincode;

	@Column(name = "supplier_country_id")
	private String supplierCountryId;

	@Column(name = "prescription_required")
	private int prescriptionRequired;

	@Column(name = "cgst")
	private Double cgst;

	@Column(name = "sgst")
	private Double sgst;

	@Column(name = "igst")
	private Double igst;

	@Column(name = "inclusive_tax")
	private int inclusiveTax;

	@Column(name = "stock_in_hand")
	private Double stockInHand;

	@Column(name = "cess")
	private Double cess;
	
	@Column(name = "cgst_amount")
	private Double cgstAmount;

	@Column(name = "sgst_amount")
	private Double sgstAmount;

	@Column(name = "igst_amount")
	private Double igstAmount;

	public SalesFlatQuoteItem(long productId, double qty, Double stockInHand) {
		this.productId = productId;
		this.qty = qty;
		this.stockInHand = stockInHand;
	}

	public SalesFlatQuoteItem() {
		super();
	}

}
