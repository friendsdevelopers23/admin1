package com.calsoft.pos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "settings")
public class Settings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "setting_id")
	private Long settingId;

	@Column(name = "logo")
	private String logo;

	@Column(name = "logo2")
	private String logo2;

	@Column(name = "biller_logo")
	private String billerLogo;

	@Column(name = "site_name")
	private String siteName;

	@Column(name = "language")
	private String language;

	@Column(name = "default_warehouse")
	private Long defaultWarehouse;

	@Column(name = "accounting_method")
	private Long accountingMethod;

	@Column(name = "default_currency")
	private String defaultCurrency;

	@Column(name = "default_tax_rate")
	private Long defaultTaxRate;

	@Column(name = "rows_per_page")
	private Long rowsPerPage;

	@Column(name = "version")
	private String version;

	@Column(name = "default_tax_rate2")
	private Long defaultTaxRate2;

	@Column(name = "dateformat")
	private Long dateformat;

	@Column(name = "sales_prefix")
	private String salesPrefix;

	@Column(name = "quote_prefix")
	private String quotePrefix;

	@Column(name = "purchase_prefix")
	private String purchasePrefix;

	@Column(name = "transfer_prefix")
	private String transferPrefix;

	@Column(name = "delivery_prefix")
	private String deliveryPrefix;

	@Column(name = "payment_prefix")
	private String paymentPrefix;

	@Column(name = "return_prefix")
	private String returnPrefix;

	@Column(name = "returnp_prefix")
	private String returnpPrefix;

	@Column(name = "expense_prefix")
	private String expensePrefix;

	@Column(name = "item_addition")
	private Long itemAddition;

	@Column(name = "theme")
	private String theme;

	@Column(name = "product_serial")
	private Long productSerial;

	@Column(name = "default_discount")
	private Long defaultDiscount;

	@Column(name = "product_discount")
	private Long productDiscount;

	@Column(name = "discount_method")
	private Long discountMethod;

	@Column(name = "tax1")
	private Long tax1;

	@Column(name = "tax2")
	private Long tax2;

	@Column(name = "overselling")
	private Long overselling;

	@Column(name = "restrict_user")
	private Long restrictUser;

	@Column(name = "restrict_calendar")
	private Long restrictCalendar;

	@Column(name = "timezone")
	private String timezone;

	@Column(name = "iwidth")
	private Long iwidth;

	@Column(name = "iheight")
	private Long iheight;

	@Column(name = "twidth")
	private Long twidth;

	@Column(name = "theight")
	private Long theight;

	@Column(name = "watermark")
	private Long watermark;

	@Column(name = "reg_ver")
	private Long regVer;

	@Column(name = "allow_reg")
	private Long allowReg;

	@Column(name = "reg_notification")
	private Long regNotification;

	@Column(name = "auto_reg")
	private Long autoReg;

	@Column(name = "protocol")
	private String protocol;

	@Column(name = "mailpath")
	private String mailpath;

	@Column(name = "smtp_host")
	private String smtpHost;

	@Column(name = "smtp_user")
	private String smtpUser;

	@Column(name = "smtp_pass")
	private String smtpPass;

	@Column(name = "smtp_port")
	private String smtpPort;

	@Column(name = "smtp_crypto")
	private String smtpCrypto;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "corn", updatable = false)
	private Date corn;

	@Column(name = "customer_group")
	private Long customerGroup;

	@Column(name = "default_email")
	private String defaultEmail;

	@Column(name = "mmode")
	private Long mmode;

	@Column(name = "bc_fix")
	private Long bcFix;

	@Column(name = "auto_detect_barcode")
	private Long autoDetectBarcode;

	@Column(name = "captcha")
	private Long captcha;

	@Column(name = "reference_format")
	private Long referenceFormat;

	@Column(name = "racks")
	private Long racks;

	@Column(name = "attributes")
	private Long attributes;

	@Column(name = "product_expiry")
	private Long productExpiry;

	@Column(name = "decimals")
	private Long decimals;

	@Column(name = "qty_decimals")
	private Long qtyDecimals;

	@Column(name = "decimals_sep")
	private String decimalsSep;

	@Column(name = "thousands_sep")
	private String thousandsSep;

	@Column(name = "invoice_view")
	private Long invoiceView;

	@Column(name = "default_biller")
	private Long defaultBiller;

	@Column(name = "envato_username")
	private String envatoUsername;

	@Column(name = "purchase_code")
	private String purchaseCode;

	@Column(name = "rtl")
	private Long rtl;

	@Column(name = "each_spent")
	private Double eachSpent;

	@Column(name = "ca_point")
	private Long caPoint;

	@Column(name = "each_sale")
	private Double eachSale;

	@Column(name = "sa_point")
	private Long saPoint;

	@Column(name = "edit")
	private int edit;

	@Column(name = "sac")
	private Long sac;

	@Column(name = "display_all_products")
	private Long displayAllProducts;

	@Column(name = "display_symbol")
	private Long displaySymbol;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "remove_expired")
	private Long removeExpired;

	@Column(name = "barcode_separator")
	private String barcodeSeparator;

	@Column(name = "set_focus")
	private Long setFocus;

	@Column(name = "price_group")
	private Long priceGroup;

	@Column(name = "barcode_img")
	private Long barcodeImg;

	@Column(name = "ppayment_prefix")
	private String ppaymentPrefix;

	@Column(name = "disable_editing")
	private Long disableEditing;

	@Column(name = "qa_prefix")
	private String qaPrefix;

	@Column(name = "edit_cost")
	private int editCost;

	@Column(name = "apis")
	private Long apis;

	@Column(name = "state")
	private String state;

	@Column(name = "pdf_lib")
	private String pdfLib;

	@Column(name = "use_code_for_slug")
	private int useCodeForSlug;

	@Column(name = "ws_barcode_type")
	private String wsBarcodeType;

	@Column(name = "ws_barcode_chars")
	private Long wsBarcodeChars;

	@Column(name = "flag_chars")
	private Long flagChars;

	@Column(name = "item_code_start")
	private Long itemCodeStart;

	@Column(name = "item_code_chars")
	private Long itemCodeChars;

	@Column(name = "price_start")
	private Long priceStart;

	@Column(name = "price_chars")
	private Long priceChars;

	@Column(name = "price_divide_by")
	private Long priceDivideBy;

	@Column(name = "weight_start")
	private Long weightStart;

	@Column(name = "weight_chars")
	private Long weightChars;

	@Column(name = "weight_divide_by")
	private Long weightDivideBy;

	@Column(name = "ecom")
	private int ecom;

	@Column(name = "login_api_url")
	private String loginApiUrl;

	@Column(name = "core_api_url")
	private String coreApiUrl;

	@Column(name = "api_username")
	private String apiUsername;

	@Column(name = "api_password")
	private String apiPassword;

	@Column(name = "use_regional_product_name_in_recipt")
	private int useRegionalProductNameInRecipt;

	@Column(name = "payment_qr_code")
	private String paymentQrCode;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiry_date_for_account", updatable = false)
	private Date expiryDateForAccount;

	@Column(name = "enable_custom_price")
	private Long enableCustomPrice;

	@Column(name = "product_cost_validation")
	private Long productCostValidation;

	@Column(name = "kitchen_module")
	private Long kitchenModule;

	@Column(name = "cash_balance")
	private Float cashBalance;

	@Column(name = "kitchen_module_type")
	private Long kitchenModuleType;

	@Column(name = "kitcheb_module_text")
	private String kitchebModuleText;

	@Column(name = "approval_transation")
	private Long approvalTransation;

	@Column(name = "token_start_number")
	private Long tokenStartNumber;

	@Column(name = "token_renew_days")
	private Long tokenRenewDays;

	@Column(name = "current_token_number")
	private Long currentTokenNumber;

	@Column(name = "token_list_font_size")
	private Long tokenListFontSize;

	@Column(name = "show_reject_button")
	private Long showRejectButton;

	@Column(name = "token_number_font")
	private Long tokenNumberFont;

	@Column(name = "product_name_font")
	private Long productNameFont;

	//@Column(name = "products_count_to_fix_barcode_input")
	//private String productsCountToFixBarcodeInput;

	//@Column(name = "display_warehouse_products")
	//private String displayWarehouseProducts;

	@Column(name = "south_asian_countriesc_currency_format")
	private String southAsianCountriesCurrencyFormat;

	@Column(name = "barcode_contains")
	private String barcodeContains;

	@Column(name = "barcode_total_characters")
	private String barcodeTotalCharacters;

	@Column(name = "number_of_characters_in_item_code")
	private String numberOfCharactersInItemCode;

	@Column(name = "display_currency_symbol")
	private String displayCurrencySymbol;

	@Column(name = "allow_edit_amount_in_payment")
	private String allowEditAmountInPayment;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_token_renew_date", updatable = false)
	private Date lastTokenRenewDate;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;
}
