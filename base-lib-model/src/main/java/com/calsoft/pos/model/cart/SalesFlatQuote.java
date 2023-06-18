package com.calsoft.pos.model.cart;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "sales_flat_quote")
@Data
public class SalesFlatQuote implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private long entityId;

	@Column(name = "store_id")
	private long storeId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "converted_at")
	private Date converterDate;

	@Column(name = "is_active")
	private long isActive;

	@Column(name = "is_virtual")
	private long isVirtual;

	@Column(name = "is_multi_shipping")
	private long isMultiShipping;

	@Column(name = "items_count")
	private long itemsCount;

	@Column(name = "items_qty")
	private double itemsQty;

	@Column(name = "orig_order_id")
	private long origOrderId;

	@Column(name = "store_to_base_rate")
	private double storeToBaseRate;

	@Column(name = "store_to_quote_rate")
	private double storeToQuoteRate;

	@Column(name = "base_currency_code")
	private String baseCurrencyCode;

	@Column(name = "store_currency_code")
	private String storeCurrencyCode;

	@Column(name = "quote_currency_code")
	private String quoteCurrencyCode;

	@Column(name = "grand_total")
	private double grandTotal;

	@Column(name = "base_grand_total")
	private double baseGrandTotal;

	@Column(name = "checkout_method")
	private String checkoutMethod;

	@Column(name = "customer_id")
	private long customerId;

	@Column(name = "customer_tax_class_id")
	private long customerTaxClassId;

	@Column(name = "customer_group_id")
	private long customerGroupId;

	@Column(name = "customer_email")
	private String customerEmail;

	@Column(name = "customer_prefix")
	private String customerPrefix;

	@Column(name = "customer_firstname")
	private String customerFirstname;

	@Column(name = "customer_middlename")
	private String customerMiddlename;

	@Column(name = "customer_lastname")
	private String customerLastname;

	@Column(name = "customer_suffix")
	private String customerSuffix;

	@Column(name = "customer_dob")
	private Date customerDob;

	@Column(name = "customer_note")
	private String customerNote;

	@Column(name = "customer_note_notify")
	private long customerNoteNotify;

	@Column(name = "customer_is_guest")
	private long customerIsGuest;

	@Column(name = "remote_ip")
	private String remoteIp;

	@Column(name = "applied_rule_ids")
	private String appliedRuleIds;

	@Column(name = "reserved_order_id")
	private String reservedOrderId;

	@Column(name = "password_hash")
	private String passwordHash;

	@Column(name = "coupon_code")
	private String couponCode;

	@Column(name = "global_currency_code")
	private String globalCurrencyCode;

	@Column(name = "base_to_global_rate")
	private double baseToGlobalRate;

	@Column(name = "base_to_quote_rate")
	private double baseToQuoteRate;

	@Column(name = "customer_taxvat")
	private String customerTaxvat;

	@Column(name = "customer_gender")
	private String customerGender;

	@Column(name = "subtotal")
	private double subtotal;

	@Column(name = "base_subtotal")
	private double baseSubtotal;

	@Column(name = "subtotal_with_discount")
	private double subtotalWithDiscount;

	@Column(name = "base_subtotal_with_discount")
	private double baseSubtotalWithDiscount;

	@Column(name = "is_changed")
	private long isChanged;

	@Column(name = "trigger_recollect")
	private long triggerRecollect;

	@Column(name = "ext_shipping_info")
	private String extShippingInfo;

	@Column(name = "is_persistent")
	private long isPersistent;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "store_id")
	private List<CoreStore> coreStore = new ArrayList<CoreStore>();

	@OneToMany(mappedBy = "salesFlatQuote", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatQuoteItem> salesFlatQuoteItem = new ArrayList<SalesFlatQuoteItem>();

	@OneToMany(mappedBy = "salesFlatQuote", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatQuoteAddress> salesFlatQuoteAddress = new ArrayList<SalesFlatQuoteAddress>();

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Transient
	private String currencyCode;

	@Transient
	private String operationType;

	@Column(name = "biller_id")
	private Long billerId;

	@Column(name = "is_suspended")
	private int isSuspended;

	@Transient
	private String extOrderId;

	@Column(name = "cess")
	private Double cess;

	@Column(name = "tax_amount")
	private double taxAmount;

	@Column(name = "base_tax_amount")
	private double baseTaxAmount;

	@Column(name = "cgst_amount")
	private Double cgstAmount;

	@Column(name = "sgst_amount")
	private Double sgstAmount;

	@Column(name = "igst_amount")
	private Double igstAmount;

	@Transient
	private String cartId;

	public void addQuoteItem(SalesFlatQuoteItem childItem) {
		this.salesFlatQuoteItem.add(childItem);
	}

	public void removeQuoteItem(SalesFlatQuoteItem childItem) {
		this.salesFlatQuoteItem.remove(childItem);
	}

}
