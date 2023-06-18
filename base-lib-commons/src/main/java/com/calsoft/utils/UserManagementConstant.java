package com.calsoft.utils;

public class UserManagementConstant {

	public static final String SUPER_ADMIN = "Super Admin";
	public static final String SUPER_ADMIN_B = "Super Admin B";

	public static final String USER_TYPE = "ut";
	public static final String ADMIN_USER = "Admin";
	public static final String VENDOR_USER = "VENDOR";
	public static final String SITE_USER = "Site User";
	public static final String SITE_USER_ADMIN = "Site User Admin";
	public static final String SYSTEM_NAME = "calsoft";
	public static final String TOKEN_ACCESS = "accessToken";
	public static final String TOKEN_REFRESH = "refreshToken";
	public static final String TOKEN_UUID = "UUID";
	public static final String VST = "VST";
	public static final String SYSTEM_DEFAULT_TENANT_ID = "Primary";
	public static final String XSRF_TOKEN = "cci";
	public static final String X_XSRF_TOKEN = "cxi";

	public static final String USER_NAME = "user-name";
	public static final String USER_ID = "user-id";

	public static final Integer MAX_AGE = 6 * 30 * 24 * 3600;

	public static final Integer EXCEPTION_LOGOUT_CODE = 210;
	public static final String APIDISCOVER = "apidiscover";

	public static final String SITE_PROFILE = "Site";

	public static final String CATALOG_PROFILE = "Attribute,CatalogsearchQuery,Category,Product,AttributeSet,supplier,supplierDiscount";

	public static final String ADMIN_PROFILE = "CmsPage,userRole,AdminConfig,AdminCurrency,AdminNewsletterSubscriber,AdminShippingTablerate,AccessSet,Profile,AdminReview,AdminCustomer,DeliveryCountry,abandonedCart,cartProduct,adminCoupon,pincode,coreTranslate,layout,language,paymentMode,warehouse,footerIcon,contact,productQtyReport";

	public static final String SALES_PROFILE = "CreditMemo,Shipment,Invoice,Order,Tax,purchase";

	public static final String MEDICAL_PROFILE = "medicalDelivery";

	public static final String LIST_OF_PROFILES = "SALES_PROFILE,ADMIN_PROFILE,CATALOG_PROFILE,MEDICAL_PROFILE";

	public static final String MENU_TYPE = "page";
	
	public static final String CART_ID = "ast";

	public static final String ENTERPRISE_NAME = "c-id";

	public static final String DEVICE_TYPE = "MOBILE";

	public static final String DEVICE_TYPE_NAME = "deviceType";

	public static final String PRESCRIPTION = "prescription";

	public static final String BASIC_PLAN_SALES_PROFILE = "CreditMemo,Shipment,Invoice,Order,Tax";

	public static final String BASIC_PLAN_ACCESS = "CreditMemo,Shipment,Invoice,Order,Tax,CmsPage,userRole,AdminConfig,AdminNewsletterSubscriber,AdminShippingTablerate,accessSet,Profile,AdminReview,AdminCustomer,DeliveryCountry,adminCoupon,layout,paymentMode,footerIcon,contact,productQtyReport,Attribute,Category,Product,AttributeSet";

	public static final String BASIC_PLAN_API_ACCESS = "creditMemo,order,invoice,shipment,tax,profile,adminCustomer,product,productCustomization,category,attribute,attributeSet,adminReview,customer,userRole,accessSet,adminNewsletterSubscriber,cmsPage,adminShippingTablerate,adminConfig,adminCoupon,deliveryCountry,layout,paymentMode,footerIcon,contact,productQtyReport";

	public static final String BASIC_PLAN_ADMIN_PROFILE_SALES_PROFILE = "CmsPage,userRole,AdminConfig,AdminNewsletterSubscriber,AdminShippingTablerate,AccessSet,Profile,AdminReview,AdminCustomer,DeliveryCountry,adminCoupon,layout,paymentMode,footerIcon,contact,productQtyReport";

	public static final String BASIC_PLAN_CATALOG_PROFILE = "Attribute,Category,Product,AttributeSet";

	public static String getCatalogProfile() {
		return CATALOG_PROFILE;
	}

	public static String getAdminProfile() {
		return ADMIN_PROFILE;
	}

	public static String getSalesProfile() {
		return SALES_PROFILE;
	}

	public static String getMedicalProfile() {
		return MEDICAL_PROFILE;
	}

	public static String getBasicPlanProfile() {
		return BASIC_PLAN_ACCESS;
	}

	public static String getBasicPlanApiProfile() {
		return BASIC_PLAN_API_ACCESS;
	}

	public static String getBasicCatalogProfile() {
		return BASIC_PLAN_CATALOG_PROFILE;
	}

	public static String getBasicAdminProfile() {
		return BASIC_PLAN_ADMIN_PROFILE_SALES_PROFILE;
	}

	public static String getBasicSalesProfile() {
		return BASIC_PLAN_SALES_PROFILE;
	}

}
