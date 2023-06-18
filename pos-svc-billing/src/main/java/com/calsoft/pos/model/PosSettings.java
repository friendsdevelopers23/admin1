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
@Table(name = "pos_settings")
public class PosSettings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "pos_id")
	private Long posId;

	@Column(name = "cat_limit")
	private Long catLimit;

	@Column(name = "pro_limit")
	private Long proLimit;

	@Column(name = "default_category")
	private Long defaultCategory;

	@Column(name = "default_customer")
	private Long defaultCustomer;

	@Column(name = "default_biller")
	private Long defaultBiller;

	@Column(name = "display_time")
	private String displayTime;

	@Column(name = "cf_title1")
	private String cfTitle1;

	@Column(name = "cf_title2")
	private String cfTitle2;

	@Column(name = "cf_value1")
	private String cfValue1;

	@Column(name = "cf_value2")
	private String cfValue2;

	@Column(name = "receipt_printer")
	private String receiptPrinter;

	@Column(name = "cash_drawer_codes")
	private String cashDrawerCodes;

	@Column(name = "focus_add_item")
	private String focusAddItem;

	@Column(name = "add_manual_product")
	private String addManualProduct;

	@Column(name = "customer_selection")
	private String customerSelection;

	@Column(name = "add_customer")
	private String addCustomer;

	@Column(name = "toggle_category_slider")
	private String toggleCategorySlider;

	@Column(name = "toggle_subcategory_slider")
	private String toggleSubcategorySlider;

	@Column(name = "cancel_sale")
	private String cancelSale;

	@Column(name = "suspend_sale")
	private String suspendSale;

	@Column(name = "print_items_list")
	private String printItemsList;

	@Column(name = "finalize_sale")
	private String finalizeSale;

	@Column(name = "today_sale")
	private String todaySale;

	@Column(name = "open_hold_bills")
	private String openHoldBills;

	@Column(name = "close_register")
	private String closeRegister;

	@Column(name = "keyboard")
	private Long keyboard;

	@Column(name = "pos_printers")
	private String posPrinters;

	@Column(name = "java_applet")
	private Long javaApplet;

	@Column(name = "product_button_color")
	private String productButtonColor;

	@Column(name = "tooltips")
	private Long tooltips;

	@Column(name = "paypal_pro")
	private Long paypalPro;

	@Column(name = "stripe")
	private Long stripe;

	@Column(name = "rounding")
	private Long rounding;

	@Column(name = "char_per_line")
	private Long charPerLine;

	@Column(name = "pin_code")
	private String pinCode;

	@Column(name = "purchase_code")
	private String purchase_code;

	@Column(name = "envato_username")
	private String envatoUsername;

	@Column(name = "version")
	private String version;

	@Column(name = "after_sale_page")
	private Long afterSalePage;

	@Column(name = "item_order")
	private Long itemOrder;

	@Column(name = "authorize")
	private Long authorize;

	@Column(name = "toggle_brands_slider")
	private String toggleBrandsSlider;

	@Column(name = "remote_printing")
	private Long remotePrinting;

	@Column(name = "printer")
	private Long printer;

	@Column(name = "order_printers")
	private String orderPrinters;

	@Column(name = "auto_print")
	private Long autoPrint;

	@Column(name = "customer_details")
	private Long customerDetails;

	@Column(name = "local_printers")
	private Long localPrinters;

	@Column(name = "open_product_modal")
	private String openProductModal;

	@Column(name = "weigt_capture_key")
	private String weigtCaptureKey;

	@Column(name = "last_added_product_key")
	private String lastAddedProductKey;

	@Column(name = "weight_capture_key")
	private String weightCaptureKey;

	@Column(name = "action_for_expiry_products")
	private Long actionForExpiryProducts;

	@Column(name = "pos_design")
	private Long posDesign;

	@Column(name = "enable_edit_price")
	private Long enableEditPrice;
	
	@Column(name = "display_products")
	private String displayProducts;
	
	@Column(name = "pos_pin_code")
	private Long POSPinCode;
	
	@Column(name = "allow_expired_products_in_pos")
	private String allowExpiredProductsInPOS;
	
	@Column(name = "on_screen_keyboard")
	private Long onScreenKeyboard;
	
	@Column(name = "token_number_voice")
	private String tokenNumberVoice;
	
	@Column(name = "customer_input")
	private String customerInput;
	
	@Column(name = "open_suspended_sales")
	private String openSuspendedSales;
	
	@Column(name = "go_to_product_qty")
	private String goToProductQty;
	
	
	@Column(name = "custom_field_2_title")
	private String customField2Title;
	

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}

