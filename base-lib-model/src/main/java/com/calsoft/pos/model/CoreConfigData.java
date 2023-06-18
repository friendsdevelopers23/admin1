package com.calsoft.pos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "core_config_data_main1")
public class CoreConfigData implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "config_id")
	private int configId;

	@Column(name = "site_name")
	private String siteName;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "unsecure_base_url")
	private String unsecureBaseUrl;

	@Column(name = "base_url")
	private String baseUrl;

	@Column(name = "currency_base")
	private String currencyBase;

	@Column(name = "default_title")
	private String defaultTitle;

	@Column(name = "logo_src")
	private String logoSrc;

	@Column(name = "logo_without_background_src")
	private String logoWithoutBackgroundSrc;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "sales_name")
	private String salesName;

	@Column(name = "support_name")
	private String supportName;

	@Column(name = "support_email")
	private String supportEmail;

	@Column(name = "smtp_host")
	private String smtpHost;

	@Column(name = "smtp_password")
	private String smtpPassword;

	@Column(name = "store_phone_number")
	private String storePhoneNumber;

	@Column(name = "store_hours_of_operation")
	private String storeHoursOfOperation;

	@Column(name = "country")
	private String country;

	@Column(name = "region_state")
	private String regionState;

	@Column(name = "zip_post_code")
	private String zipPostCode;

	@Column(name = "city")
	private String city;

	@Column(name = "street_address")
	private String streetAddress;

	@Column(name = "street_address_line2")
	private String streetAddressLine2;

	@Column(name = "vat_number")
	private String vatNumber;

	@Column(name = "server_socket_layer")
	private String ssl;

	@Column(name = "disable_email_communication")
	private String disableEmailCommunication;

	@Column(name = "smtp_port")
	private int smtpPort;

	@Column(name = "layout")
	private String layout;

	@Column(name = "color")
	private String color;

	@Column(name = "ship_rocket_user_name")
	private String shipRocketUserName;

	@Column(name = "ship_rocket_password")
	private String shipRocketPassword;

	@Column(name = "invoice_template_id")
	private String invoiceTemplateId;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "order_template_id", updatable = false)
	private String orderTemplateId;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Column(name = "cc_avenue_access_code")
	private String ccAvenueAccessCode;

	@Column(name = "cc_avenue_enc_key")
	private String ccAvenueEncKey;

	@Column(name = "cc_avenue_merchant_id")
	private String ccAvenueMerchantId;

	@Column(name = "aromex_user_name")
	private String aromexUserName;

	@Column(name = "aromex_password")
	private String aromexPassword;

	@Column(name = "aromex_version")
	private String aromexVersion;

	@Column(name = "aromex_account_number")
	private String aromexAccountNumber;

	@Column(name = "aromex_account_pin")
	private String aromexAccountPin;

	@Column(name = "aromex_account_entity")
	private String aromexAccountEntity;

	@Column(name = "aromex_account_country_code")
	private String aromexAccountCountryCode;

	@Column(name = "aromex_source")
	private String aromexSource;

	@Column(name = "order_prefix")
	private String orderPrefix;

	@Column(name = "email_verification")
	private int emailVerification;

	@Column(name = "auto_review")
	private int autoReview;

	@Column(name = "pay_u_key")
	private String payUKey;

	@Column(name = "pay_u_salt")
	private String payUSalt;

	@Column(name = "razor_pay_key")
	private String razorPayKey;

	@Column(name = "serial_line")
	private String serialLine;

	@Column(name = "serial_line_active")
	private int serialLineActive;

	@Column(name = "opwa_token")
	private String opwaToken;

	@Column(name = "opwa_id")
	private String opwaId;

	@Column(name = "opwa_country_id")
	private String opwaCountryId;

	@Column(name = "opwa_url")
	private String opwaUrl;

	@Column(name = "otp_enabled")
	private int otpEnabled;

	@Column(name = "smsc_client_id")
	private String smscClientId;

	@Column(name = "smsc_api_key")
	private String smscApiKey;

	@Column(name = "smsc_sender_id")
	private String smscSenderId;

	@Column(name = "otp_digit")
	private int otpDigit;

	@Column(name = "otp_max_digit")
	private int otpMaxDigit;

	@Column(name = "opt_message_template")
	private String optMessageTemplate;

	@Column(name = "avaliable_pincodes")
	private int avaliablePincodes;

	@Column(name = "language_selection")
	private int languageSelection;

	@Column(name = "shipping_charges_applied")
	private int shippingChargesApplied;

	@Column(name = "shipping_vendor")
	private String shippingVendor;

	@Column(name = "free_shipping_applied_amount")
	private double freeShippingAppliedAmount;

	@Column(name = "author")
	private String author;

	@Column(name = "description")
	private String description;

	@Column(name = "keywords")
	private String keywords;

	@Column(name = "sms_vendor")
	private String smsVendor;

	@Column(name = "google_social_login")
	private String googleSocialLogin;

	@Column(name = "google_social_login_status")
	private int googleSocialLoginStatus;

	@Column(name = "show_cart_product_page")
	private int showCartProductPage;

	@Column(name = "password_validation_required")
	private int passwordValidationRequired;

	@Column(name = "show_configurable_dropdown_product_page")
	private int showConfigurableDropdownProductPage;

	@Column(name = "delivery_time")
	private String deliveryTime;

	@Column(name = "show_genuine_product_icon")
	private int showGenuineProductIcon;

	@Column(name = "show_time_icon")
	private int showTimeIcon;

	@Column(name = "delivery_door_step_icon")
	private int deliveryDoorStepIcon;

	@Column(name = "genuine_product_text")
	private String genuineProductText;

	@Column(name = "delivery_door_step_text")
	private String deliveryDoorStepText;

	@Column(name = "show_cart_incremented")
	private int showCartIncremented;

	@Column(name = "password_minimum_characters")
	private int passwordMinimumCharacters;

	@Column(name = "related_products_text")
	private String relatedProductsText;

	@Column(name = "up_sell_products_text")
	private String upSellProductsText;

	@Column(name = "cross_sell_products_text")
	private String crossSellProductsText;

	@Column(name = "recently_viewed_products_text")
	private String recentlyViewedProductsText;

	@Column(name = "enable_store_pickup")
	private int enableStorePickup;

	@Column(name = "checkout_timer_enabled")
	private int checkoutTimerEnabled;

	@Column(name = "checkout_timer_seconds")
	private int checkoutTimerSeconds;

	@Column(name = "google_analytics_id")
	private String googleAnalyticsId;

	@Column(name = "facebook_pixel_id")
	private String facebookPixelId;

	@Column(name = "google_site_verification")
	private String googleSiteVerification;

	@Column(name = "supplier_latitude")
	private String supplierLatitude;

	@Column(name = "supplier_longitude")
	private String supplierLongitude;

	@Column(name = "manual_order_triggered_for_delivery")
	private long manualOrderTriggeredForDelivery;

	@Column(name = "order_last_dispatched_time")
	private String orderLastDispatchedTime;

	@Column(name = "maximum_delivery_km")
	private double maximumDeliveryKm;

	@Column(name = "google_map_avaliable")
	private long googleMapAvaliable;

	@Column(name = "google_map_api_key")
	private String googleMapApiKey;

	@Column(name = "additional_delivery_charge")
	private double additionalDeliveryCharge;

	@Column(name = "additional_delivery_charge_in_percent")
	private long additionalDeliveryChargeInPercent;

	@Column(name = "calculate_shipping_rate_based_on_vendors")
	private int calculateShippingRateBasedOnVendors;

	@Column(name = "delivery_closed")
	private int deliveryClosed;

	@Column(name = "smtp_username")
	private String smtpUsername;

	@Column(name = "pos_url")
	private String posUrl;

	@Column(name = "pos_enabled")
	private int posEnabled;

	@Column(name = "type")
	private int type;

	@Column(name = "pos_username")
	private String posUsername;

	@Column(name = "pos_password")
	private String posPassword;

	@Column(name = "whats_app_vendor")
	private int whatsAppVendor;

	@Column(name = "bot_boba_auth_token")
	private String botBobaAuthToken;

	@Column(name = "bot_baba_id")
	private String botBabaId;

	@Column(name = "bot_enabled")
	private int botEnabled;

	@Column(name = "bot_url")
	private String botUrl;

	@Column(name = "remove_expiry_product_from_stock")
	private int removeExpiryProductFromStock;

	@Column(name = "update_stock_by_purchase_only")
	private int updateStockByPurchaseOnly;

	@Column(name = "products_image_height")
	private int productsImageHeight;

	@Column(name = "enable_gst")
	private int enableGst;

	@Column(name = "enable_stock_in_hand")
	private int enableStockInHand;

	@Column(name = "show_footer_icon")
	private int showFooterIcon;

	@Column(name = "show_contact_number")
	private int showContactNumber;

	@Column(name = "show_gst_in_address")
	private int showGstInAddress;

	@Column(name = "show_gst_in_user")
	private int showGstInUser;

	@Column(name = "show_contact_page")
	private int showContactPage;

	@Column(name = "show_blog_page")
	private int showBlogPage;
	
	@Column(name = "show_brochure_page")
	private int showBrochurePage;

	@Column(name = "chat_vendor")
	private int chatVendor;

	@Column(name = "chat_enabled")
	private int chatEnabled;

	@Column(name = "tawk_to_url")
	private String tawkToUrl;

	@Column(name = "partial_payment")
	private int partialPayment;

	@Column(name = "partial_payment_minimum_amount")
	private double partialPaymentMinimumAmount;

	@Column(name = "edit_paritial_amount")
	private int editParitialAmount;

	@Column(name = "calculate_paritial_amount_percent")
	private int calculateParitialAmountPercent;

	@Column(name = "partial_payment_info")
	private String partialPaymentInfo;

	@Column(name = "products_image_width")
	private int productsImageWidth;

	@Column(name = "products_image_type")
	private int productsImageType;

	@Column(name = "category_menu_type")
	private int categoryMenuType;

	@Column(name = "whats_app_button_enable_product")
	private int whatsAppButtonEnableProduct;

	@Column(name = "product_image_width")
	private int productImageWidth;

	@Column(name = "product_zoom")
	private int productzoom;

	@Column(name = "footer_layout_colour")
	private String footerLayoutColour;

	@Column(name = "api_key")
	private String apiKey;

	@Column(name = "auth_domain")
	private String authDomain;

	@Column(name = "project_id")
	private String projectId;

	@Column(name = "storage_bucket")
	private String storageBucket;

	@Column(name = "messaging_sender_id")
	private String messagingSenderId;

	@Column(name = "app_id")
	private String appId;

	@Column(name = "products_image_width_mob")
	private int productsImageWidthMob;

	@Column(name = "products_image_height_mob")
	private int productsImageHeightMob;

	@Column(name = "footer_layout_font_colour")
	private String footerLayoutFontColour;

	@Column(name = "vendor_enabled")
	private int vendorEnabled;

	@Column(name = "sms_url")
	private String smsUrl;

	@Column(name = "sms_disabled")
	private int smsDisabled;

	@Column(name = "order_message_template")
	private String orderMessageTemplate;

	@Column(name = "invoice_message_template")
	private String invoiceMessageTemplate;

	@Column(name = "shipment_message_template")
	private String shipmentMessageTemplate;

	@Column(name = "refund_message_template")
	private String refundMessageTemplate;

	@Column(name = "sms_verification")
	private int smsVerification;

	@Column(name = "guest_login")
	private int guestLogin;

	@Column(name = "coversion_tracking_id_ga")
	private String coversionTrackingIdGa;

	@Column(name = "predefined_color")
	private int predefinedColor;

	@Column(name = "gmc_verification_code")
	private String gmcVerificationCode;

	@Column(name = "fb_verification_code")
	private String fbVerificationCode;

	@Column(name = "product_image_height")
	private String productImageHeight;

	@Column(name = "product_imge_width")
	private String producImageWidth;

	@Column(name = "product_m_image_width")
	private String productMImageWidth;

	@Column(name = "product_m_image_height")
	private String productMImageHeight;

	@Column(name = "show_gst_cart_page")
	private int showGstCartPage;

	@Column(name = "show_exclusive_price")
	private int showExclusivePrice;
	
	@Column(name = "b2b_pricing_enabled")
	private int b2bPricingEnabled;

	@Column(name = "disable_coupoun")
	private int disableCoupoun;

	@Column(name = "price_sortings")
	private String priceSortings;
	
	@Column(name = "footer_layout")
	private int footerLayout;
	
	@Column(name = "product_detail_page")
	private int productDetailPage;
	
	@Column(name = "gmc_credentials")
	private String  gmcCredentials;
	
	public CoreConfigData(String layout, String color) {
		this.layout = layout;
		this.color = color;
	}

	public CoreConfigData() {
		super();
	}

	public CoreConfigData(String siteName, String tenantId, int removeExpiryProductFromStock) {
		this.siteName = siteName;
		this.tenantId = tenantId;
		this.removeExpiryProductFromStock = removeExpiryProductFromStock;
	}

}
