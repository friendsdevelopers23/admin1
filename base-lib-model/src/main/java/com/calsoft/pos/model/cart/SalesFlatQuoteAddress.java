package com.calsoft.pos.model.cart;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "sales_flat_quote_address")
@Data
public class SalesFlatQuoteAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private long addressId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "save_in_address_book")
	private int saveInAddressBook;

	@Column(name = "customer_address_id")
	private Integer customerAddressId = 0;

	@Column(name = "address_type")
	private String addressType;

	@Column(name = "email")
	private String email;

	@Column(name = "prefix")
	private String prefix;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "middlename")
	private String middlename;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "suffix")
	private String suffix;

	@Column(name = "company")
	private String company;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "region")
	private String region;

	@Column(name = "region_id")
	private Integer regionId;

	@Column(name = "postcode")
	private String postcode;

	@Column(name = "country_id")
	private String countryId;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "fax")
	private Integer fax;

	@Column(name = "same_as_billing")
	private int sameAsBilling;

	@Column(name = "free_shipping")
	private int freeShipping;

	@Column(name = "collect_shipping_rates")
	private int collectShippingRates;

	@Column(name = "shipping_method")
	private String shippingMethod;

	@Column(name = "shipping_description")
	private String shippingDescription;

	@Column(name = "weight")
	private double weight;

	@Column(name = "subtotal")
	private double subtotal;

	@Column(name = "base_subtotal")
	private double baseSubtotal;

	@Column(name = "subtotal_with_discount")
	private double subtotalWithDiscount;

	@Column(name = "base_subtotal_with_discount")
	private double baseSubtotalWithDSiscount;

	@Column(name = "tax_amount")
	private double taxAmount;

	@Column(name = "base_tax_amount")
	private double baseTaxAmount;

	@Column(name = "shipping_amount")
	private double shippingAmount;

	@Column(name = "base_shipping_amount")
	private double baseShippingAmount;

	@Column(name = "shipping_tax_amount")
	private double shippingTaxAmount;

	@Column(name = "base_shipping_tax_amount")
	private double baseShippingTaxAmount;

	@Column(name = "discount_amount")
	private double discountAmount;

	@Column(name = "base_discount_amount")
	private double baseDiscountAmount;

	@Column(name = "grand_total")
	private double grandTotal;

	@Column(name = "base_grand_total")
	private double baseGrandTotal;

	@Column(name = "customer_notes")
	private String customerNotes;

	@Column(name = "applied_taxes")
	private String appliedTaxes;

	@Column(name = "discount_description")
	private String discountDescription;

	@Column(name = "shipping_discount_amount")
	private double shippingDiscountAmount = 0;

	@Column(name = "base_shipping_discount_amount")
	private double baseShippingDiscountAmount = 0;

	@Column(name = "subtotal_incl_tax")
	private double subtotalInclTax = 0;

	@Column(name = "base_subtotal_total_incl_tax")
	private double baseSubtotalTotalInclTax = 0;

	@Column(name = "hidden_tax_amount")
	private double hiddenTaxAmount = 0;

	@Column(name = "base_hidden_tax_amount")
	private double baseHiddenTaxAmount = 0;

	@Column(name = "shipping_hidden_tax_amount")
	private double shippingHiddenTaxAmount = 0;

	@Column(name = "base_shipping_hidden_tax_amnt")
	private double baseShippingHiddenTaxAmnt = 0;

	@Column(name = "shipping_incl_tax")
	private double shippingInclTax = 0;

	@Column(name = "base_shipping_incl_tax")
	private double baseShippingInclTax = 0;

	@Column(name = "vat_id")
	private String vat_id;

	@Column(name = "vat_is_valid")
	private Integer vatIsValid;

	@Column(name = "vat_request_id")
	private String vatRequestId;

	@Column(name = "vat_request_date")
	private String vatRequestDate;

	@Column(name = "vat_request_success")
	private Integer vatRequestSuccess;

	@Column(name = "gift_message_id")
	private Integer giftMessageId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "quote_id", nullable = true)
	private SalesFlatQuote salesFlatQuote;

}
