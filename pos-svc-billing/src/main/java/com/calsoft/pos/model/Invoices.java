package com.calsoft.pos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "invoices")
public class Invoices {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "invoice_id")
	private Long invoiceId;

	@Column(name = "invoice_name")
	private String invoiceName;

	@Column(name = "desgin_type")
	private Long desginType;

	@Column(name = "invoice_logo")
	private String invoiceLogo;

	@Column(name = "show_invoice_logo")
	private Long showInvoiceLogo;

	@Column(name = "header_text")
	private String headerText;

	@Column(name = "sub_header_line_1")
	private String subHeaderLine1;

	@Column(name = "sub_header_line_2")
	private String subHeaderLine2;

	@Column(name = "sub_header_line_3")
	private String subHeaderLine3;

	@Column(name = "sub_header_line_4")
	private String subHeaderLine4;

	@Column(name = "sub_header_line_5")
	private String subHeaderLine5;

	@Column(name = "invoice_heading")
	private String invoiceHeading;

	@Column(name = "invoice_no_label")
	private String invoiceNoLabel;

	@Column(name = "show_sale_person")
	private Long showSalePerson;

	@Column(name = "sale_person_label")
	private String salePersonLabel;

	@Column(name = "show_customer")
	private Long showCustomer;

	@Column(name = "customer_label")
	private String customerLabel;

	@Column(name = "show_seller_mo")
	private String showSellerMo;

	@Column(name = "product_label")
	private String productLabel;

	@Column(name = "qty_label")
	private String qtyLabel;

	@Column(name = "unit_price_label")
	private String unitPriceLabel;

	@Column(name = "sub_total_label")
	private String subTotalLabel;

	@Column(name = "show_brand")
	private Long showBrand;

	@Column(name = "show_sku")
	private Long showSku;

	@Column(name = "total_label")
	private String totalLabel;

	@Column(name = "discount_label")
	private String discountLabel;

	@Column(name = "tax_label")
	private String taxLabel;

	@Column(name = "total_paid_label")
	private String totalPaidLabel;

	@Column(name = "show_payment_info")
	private Long showPaymentInfo;

	@Column(name = "show_qr")
	private Long showQr;

	@Column(name = "show_barcode")
	private Long showBarcode;

	@Column(name = "footer_text")
	private String footerText;

	@Column(name = "is_default")
	private Long isDefault;

	@Column(name = "invoice_type")
	private String invoiceType;

	@Column(name = "show_biller_name")
	private String showBillerName;

	@Column(name = "show_sale_phone")
	private String showSalePhone;

	@Column(name = "sales_phone_number_label")
	private String salesPhoneNumberLabel;

	@Column(name = "show_customer_address")
	private Long showCustomerAddress;

	@Column(name = "show_customer_number")
	private Long showCustomerNumber;

	@Column(name = "show_customer_gst")
	private Long showCustomerGST;

	@Column(name = "show_customer_vat")
	private Long showCustomerVAT;

	@Column(name = "text_below_payment_qr")
	private String textBelowPaymentQr;
	
	@Column(name = "sub_total_label1")
	private String subTotalLabel1;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
