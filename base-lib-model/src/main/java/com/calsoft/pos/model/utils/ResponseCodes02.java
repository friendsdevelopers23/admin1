package com.calsoft.pos.model.utils;

import com.calsoft.utils.CommonUtils;

public enum ResponseCodes02 implements ErrorCode {

	ADDPRODUCT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Product !", "Product Added Successfully !"),
	UPDATEPRODUCT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Product !", "Product Updated Successfully !"),
	ADDPRODUCT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Product !", "Product Added Failed !"),

	ADDCATEGORY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Category!", "Category Added Successfully !"),
	ADDCATEGORY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Category!", "Category Added Failed !"),

	UPDATECATEGORY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Category!", "Category Updated Successfully !"),
	UPDATECATEGORY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Category!", "Category Updated Failed !"),

	// Catalog Product Pincode
	ADD_PINCODE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Pincode!", "Catalog Product Pincode Added Successfully !"),
	ADD_PINCODE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Pincode!", "CatalogProduct Pincode Added Failed !"),

	// Catalog Product Pincode response
	PINCODE_EXIST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attribute  !",
			"Catalog Product Pincode Name Already Exists !"),
	PINCODE_EXISTE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attribute !",
			"Catalog ProductPincode Name Never Exists  !"),

	// add seo
	ADD_SEO_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Seo!", "Seo Added Successfully !"),
	ADD_SEO_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Seo!", "Seo Added Failed !"),

	// seo response
	SEO_EXIST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attribute  !", "SEO Name Already Exists !"),
	SEO_EXISTE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attribute !", "SEO Name Never Exists  !"),

	UPDATE_SEO__REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Seo!", "Seo Updated Successfully !"),
	UPDATE_SEO_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Seo!", "Seo Updated Failed !"),

	ADD_USER__REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "User!", "User Added Successfully !"),
	ADD_USER_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "User!", "User Added Failed !"),

	// orderstate
	ADDORDERSTATE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "OrderState Add !", "OrderState Added Successfully !"),
	ADDORDERSTATE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "OrderState Add !", "OrderState Added Failed !"),
	// Updated
	UPDATEORDERSTATE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "OrderState Updated Successfully !",
			"OrderState Updated Successfully !"),
	UPDATEORDERSTATE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "OrderState Updated Successfully !",
			"OrderState Updated Failed !"),
	// add
	ADDCREDITMEMO_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CreditMemo Add !", "CreditMemo Added Successfully !"),
	ADDCREDITMEMO_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "CreditMemo Add !", "CreditMemo Added Failed !"),
	// Updated
	UPDATECREDITMEMO_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CreditMemo Updated Successfully !",
			"CreditMemo Updated Successfully !"),
	UPDATECREDITMEMO_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "CreditMemo Updated Successfully !",
			"CreditMemo Updated Failed !"),

	MINIMUMQTY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "CreditMemo Updated Successfully !", "Atleast 1 Qty to be"),

	// add
	ADDSEARCHQUERY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "SearchQuery Add !", "SearchQuery Added Successfully !"),
	ADDSEARCHQUERY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "SearchQuery Add !", "SearchQuery Added Failed !"),
	// Updated
	UPDATESEARCHQUERY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "SearchQuery Updated Successfully !",
			"SearchQuery Updated Successfully !"),
	UPDATESEARCHQUERY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "SearchQuery Updated Successfully !",
			"SearchQuery Updated Failed !"),

	// add
	ADDSHIPPINGTABLERATE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Shipping Table Rate Add !",
			"Shipping Table Rate Added Successfully !"),
	ADDSHIPPINGTABLERATE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Shipping Table Rate Add !",
			"Shipping Table Rate Added Failed !"),
	// Updated
	UPDATEADDSHIPPINGTABLERATE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Shipping Table Rate Updated Successfully !",
			"Shipping Table Rate Updated Successfully !"),
	UPDATEADDSHIPPINGTABLERATE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Shipping Table Rate Updated Successfully !",
			"Shipping Table Rate Updated Failed !"),

	// add
	ADDCURRENCY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Currency Add !", "Currency Added Successfully !"),
	ADDCURRENCY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Currency Add !", "Currency Added Failed !"),

	// Updated
	UPDATECURRENCY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Currency Updated Successfully !",
			"Currency Updated Successfully !"),
	UPDATECURRENCY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Currency Updated Successfully !",
			"Currency Updated Failed !"),

	// add
	ADDTAXRATE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "TaxRate Add !", "Tax Rate Added Successfully !"),
	ADDTAXRATE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "TaxRate Add !", "Tax Rate Added Failed !"),
	// Updated
	UPDATETAXRATE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "TaxRate Updated Successfully !",
			"Tax Rate Updated Successfully !"),
	UPDATETAXRATE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "TaxRate Updated Successfully !",
			"Tax Rate Updated Failed !"),

	// add
	ADDTAXRULE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "TaxRule Add !", "Tax Rule Added Successfully !"),
	ADDTAXRULE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "TaxRule Add !", "Tax Rule Added Failed !"),
	// Updated
	UPDATETAXRULE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "TaxRule Updated Successfully !",
			"Tax Rule Updated Successfully !"),
	UPDATETAXRULE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "TaxRule Updated Successfully !",
			"Tax Rule Updated Failed !"),

	// add taxclass
	ADDTAXCLASS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "TaxClass Add !", "TaxClass Added Successfully !"),
	ADDTAXCLASS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "TaxClass Add !", "TaxClass Added Failed !"),
	// Updated
	UPDATETAXCLASS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "TaxClass Updated Successfully !",
			"TaxClass Updated Successfully !"),
	UPDATETAXCLASS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "TaxClass Updated Successfully !",
			"TaxClass Updated Failed !"),
	// add cmspage
	ADDCMSPAGE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CmsPage Add !", "Page Added Successfully !"),
	ADDCMSPAGE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "CmsPage Add !", "Page Added Failed !"),
	// Updated
	UPDATECMSPAGE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CmsPage Updated Successfully !",
			"Page Updated Successfully !"),
	UPDATECMSPAGE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "CmsPage Updated Successfully !", "Page Updated Failed !"),

	ADDATTRIBUTE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attribute Add !", "Attribute Added Successfully !"),
	ADDATTRIBUTE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attribute Add !", "Attribute Added Failed !"),

	ADDATTRIBUTE_DELETED_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attribute Add !",
			"Attribute Deleted Successfully !"),
	ADDATTRIBUTE_DELETED_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attribute Add !", "Attribute Deleted Failed !"),

	ATTRIBUTECODEEXIST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attribute  !", "Attribute Code Already Exists !"),
	ATTRIBUTECODEEXISTE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attribute !", "Attribute Code Never Exists  !"),

	PIN_CODECODEEXIST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attribute  !", "PinCode Already Exists !"),
	PIN_CODECODEEXISTE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attribute !", "PinCode Never Exists  !"),

	LANUAGE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Language  !", "Language Already Exists !"),
	LANUAGE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Language !", "Language Never Exists  !"),

	UPDATEATTRIBUTE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attribute Add !", "Attribute Updated Successfully !"),
	UPDATEATTRIBUTE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attribute Add !", "Attribute Updated Failed !"),

	ADDATTRIBUTESET_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attributeset Add !",
			"Attributeset Added Successfully !"),
	ADDATTRIBUTESET_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attributeset Add !", "Attributeset Added Failed !"),

	ADDATTRIBUTESET_REC_ALLREADY_EXIST_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attributeset Add !",
			"AttributeSetName already Registered !"),
	ADDATTRIBUTESET_REC_ALLREADY_EXIST_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attributeset Add !",
			"AttributeSetName  not  Yet Registered!"),

	ATTRIBUTE_LABLE_EXIST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attributeset Add !",
			"frontendLabel already Registered !"),
	ATTRIBUTE_LABLE_EXIST_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attributeset Add !",
			"frontendLabel  not  Yet Registered!"),

	PRODUCT_NAME_EXIST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Product Add !", "Product Name not Exists!"),
	PRODUCT_NAME_EXIST_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Product Add !", "Product Name Already Exists!"),

	UPDATEATTRIBUTESET_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Attributeset Updated Successfully !",
			"Attributeset Updated Successfully !"),
	UPDATEATTRIBUTESET_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attributeset Updated Successfully !",
			"Attributeset Updated Failed !"),

	// dns

	DNS_RECORD_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "DnsRecord Add !", "DnsRecord Added Successfully !"),
	DNS_RECORD_EXISTE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "DnsRecord Add !", "DnsRecord Added Failed !"),

	// UPLOAD

	UPLOAD_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "File !", "File Uploaded Successfully..."),
	UPLOAD_ERROR(CommonUtils.RESPONSE_FAILURE, "File !", "Error while uploading the file"),

	// DElETE

	DELETE_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "File !", "File Deleted Successfully..."),
	DELETE_ERROR(CommonUtils.RESPONSE_FAILURE, "File !", "File not Found"),

	// add Order

	CANCEL_ORDERITEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order ADD !", "Order Cancelled Successfully !"),
	CANCEL_ORDERITEMS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order ADD !", "Order Cancelled Failed !"),

	// add Incoice
	ADDINVOICE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order ADD !", "Invoice Created Successfully !"),
	ADDINVOICE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order ADD !", "Invoice Creation  Failed !"),

	// add Incoice
	ADD_SHIPMENT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order ADD !", "Shipment Created Successfully !"),
	ADD_SHIPMENT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order ADD !", "Shipment Creation  Failed !"),

	// add Incoice
	UPDATE_SHIPMENT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order ADD !", "Shipment Updated Successfully !"),
	UPDATE_SHIPMENT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order ADD !", "Shipment Updated  Failed !"),

	// Updated Incoice

	UPDATEINVOICE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order ADD !", "Invoice Created Successfully !"),
	UPDATEINVOICE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order ADD !", "Invoice Creation  Failed !"),

	MAILSEND_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, " !", "Order Email Sent Successfully !"),
	MAILSEND_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order Email !", "Order Email Sent Failure !"),

	ADDSUPPLIER_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Supplier Add !", "Supplier Added Successfully !"),
	ADDSUPPLIER_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Supplier Add !", "Supplier Added Failed !"),

	ADDPURCAHSE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Purchase Add !", "Purchase Added Successfully !"),
	ADDPURCAHSE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Purchase Add !", "Purchase Added Failed !"),

	UPDATEPURCAHSE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Purchase Updated Successfully !",
			"Purchase Updated Successfully !"),
	UPDATEPURCAHSE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Purchase Updated Successfully !",
			"Purchase Updated Failed !"),

	INDEXED_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Record !", "Record Indexed Successfully"),
	INDEXED_FAILIRE(CommonUtils.RESPONSE_FAILURE, "Record!", "Record Indexing Failed"),

	// Updated
	UPDATESUPPLIER_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Supplier Updated Successfully !",
			"Supplier Updated Successfully !"),
	UPDATESUPPLIER_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Supplier Updated Successfully !",
			"Supplier Updated Failed !"),

	// order responsecode

	// add cart
	ADDCARTTEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Cart ADD !", "Cart Updated Successfully !"),
	ADDCARTTEMS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Cart ADD !", "Cart Updated Failed !"),

	// Updated
	UPDATECARTTEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Cart ADD !", "Cart Updated Successfully !"),
	UPDATECARTTEMS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Cart ADD !", "Cart Updated Failed !"),

	// delete particular cartitem
	DELETECARTITEM_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Cart Delete !", "Cart Item Deleted Successfully !"),
	DELETECARTITEM_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Cart Delete !", "Cart Item Deleted Failed !"),

	// delete entire cart
	DELETECARTITEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Cart Delete !", "Cart Deleted Successfully !"),
	DELETECARTITEMS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Cart Delete !", "Cart Deleted Failed !"),

	// add order
	ADDORDERTEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order ADD !", "Order Placed Successfully !"),
	ADDORDERTEMS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order ADD !", "Order Can't be Placed !"),

	// add order
	ORDER_CREATION_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order ADD !", "Order Placed Successfully !"),
	ORDER_CREATION_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order ADD !", "Order Creation Failed !"),

	UPDATEORDERTEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order ADD !", "Order Updated Successfully !"),
	UPDATEORDERTEMS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order ADD !", "Order Can't be Updated Successfully !"),

	// delete particular cartitem
	DELETEORDERTEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Order Delete !", "Order Delete Successfully !"),
	DELETEORDERTEMS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Order Delete !", "Order Item Delete Failed !"),

	CART_UPDATE_FAILED(CommonUtils.RESPONSE_FAILURE, "Cart Qty Updated  !",
			"The selected qty is either out of stock or more than max allowed."),

	MIN_CART_UPDATE_FAILED(CommonUtils.RESPONSE_FAILURE, "Cart Qty Updated  !",
			"The Updated qty is Lesser then Product Minmum Qty Added in the Cart"),

	// review

	ADDREVIEW_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Review Add !", "Review Added Successfully !"),
	ADDREVIEW_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Review Add !", "Review Added Failed !"),

	UPDATEREVIEW_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Review Add !", "Review Updated Successfully !"),
	UPDATEREVIEW_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Review Add !", "Review Updated Failed !"),

	REVIEW_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Review Add !", "Data Fetched Successfully !"),
	REVIEW_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Review Add !", "Data Fetched Failed !"),

	// rating

	ADDRATING_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Rating Add !", "Rating Added Successfully !"),
	ADDRATING_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Rating Add !", "Rating Added Failed !"),

	RATTING_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Rating Add !", "Data Fetched Successfully !"),
	RATTING_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Rating Add !", "Data Fetched Failed !"),

	// Wishlist

	ADDWISHLIST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Wishlist Add !", "Wishlist Added Successfully !"),
	ADDWISHLIST_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Wishlist Add !", "Wishlist Added Failed !"),
	ADDWISHLIST_REC_EXIST(CommonUtils.RESPONSE_FAILURE, "Wishlist Add !", " The Product Already Added Your Wishlist !"),

	ADDSUBSCRIPER_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Subscriber Add !", "Subscriber Registered Successfully !"),
	ADDSUBSCRIPER_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Subscriber Add !", "Subscriber Registered Failed !"),
	ADDSUBSCRIPER_REC_EXIST(CommonUtils.RESPONSE_FAILURE, "Subscriber Add !", " The Subscriber Already Registered!"),

	// Remove
	REMOVEWISHLIST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Wishlist Remove !", "Wishlist Remove Successfully !"),
	REMOVEWISHLIST_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Wishlist Add !", "Wishlist Remove Failed !"),

	// Catalogrule
	ADDCATALOGRULE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Catalogrule Add !", "Catalogrule Added Successfully !"),
	ADDCATALOGRULE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Catalogrule Add !", "Catalogrule Added Failed !"),

	UPDATECATALOGRULE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Catalogrule Updated Successfully !",
			"Catalogrule Updated Successfully !"),
	UPDATECATALOGRULE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Catalogrule Updated Successfully !",
			"Catalogrule Updated Failed !"),

	// accountTentant
	ADDTENTANT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Tentant Add !", "Tentant Added Successfully !"),
	ADDTENTANT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Tentant Add !", "Tentant Added Failed !"),
	// Index Tentant
	INDEX_TENTANT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Tentant Add !", "Tentant Indexed Successfully !"),
	INDEX_TENTANT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Tentant Add !", "Tentant Indexed Failed !"),

	UPDATETENTANT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Tentant Updated Successfully !",
			"Tentant Updated Successfully !"),
	UPDATETENTANT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Tentant Updated Successfully !",
			"Tentant Updated Failed !"),

	LOGOUT_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Logout !", "User Logged Out Successfully !"),
//core start
	USER_FAILED(CommonUtils.RESPONSE_FAILURE, "User !", "User is Not Registered"),

	LOGINVERIFIED_FAILED(CommonUtils.RESPONSE_FAILURE, "User !", "User is Not Verified"),

	USER_IN_ACTIVE_FAILED(CommonUtils.INACTIVE_FAILURE, "User !", "User is Not Verified"),

	PASSWORD_INCORRECT(CommonUtils.RESPONSE_FAILURE, "User !", "Invalid credentials!"),

	PASSWORD_SAME_AS_LAST_FIVE_PASSWORD(CommonUtils.RESPONSE_FAILURE, "User !",
			"Your Password can't be same as last Three Passwords"),

	INVALID_PASSWORD(CommonUtils.RESPONSE_FAILURE, "User !", "Invalid Password!"),

	NON_ADMIN(CommonUtils.RESPONSE_FAILURE, "User !", "Not a admin User"),

	LOGIN_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Login !", "Login Successful"),
	LOGIN_FAILED(CommonUtils.RESPONSE_FAILURE, "Login !", "Invalid Username Or Password"),

	CAPTCH_VERIFY_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Login !", "Successful"),
	CAPTCH_VERIFY_FAILED(CommonUtils.RESPONSE_FAILURE, "Login !", "Failed"),

	ACCESS_VERIFICATION_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "Assigned Roles Create Successful"),
	UPDATEACCESS_VERIFICATION_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "Assigned Roles Updated Successful"),

	ACCESS_VERIFICATION_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", "Assigned Roles  Verification Failed"),
	UPDATEACCESS_VERIFICATION_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", "Assigned Roles Updated Failed"),

	ADD_ACCESS_SET_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "AccessSet Created Successful"),
	UPDATE_ACCESS_SET_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "AccessSet Updated Successful"),

	ADD_ACCESS_SET_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", "AccessSet Creation Failed"),
	UPDATE_ACCESS_SET_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", "AccessSet Updated Failed"),

	ADD_PROFILE_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "Profile Created Successful"),
	UPDATE_PROFILE_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "Profile Updated Successful"),

	ADD_PROFILE_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", "Profile Creation Failed"),
	UPDATE_PROFILE_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", "Profile Updated Failed"),

	CUSTOMER_EMAIL_ALLREADY_EXIST_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "User !", "User is already Registered !"),
	CUSTOMER_EMAIL_ALLREADY_EXIST_FAILURE(CommonUtils.RESPONSE_FAILURE, "User !", "User is not  Yet Registered !"),

	ALLREADY_EXIST_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "User !", "Name Already Exists !"),
	ALLREADY_EXIST_FAILURE(CommonUtils.RESPONSE_FAILURE, "User !", "Name is not  Yet Registered !"),

	USER_VERIFICATION_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "User !", "User Verification Successful"),
	USER_VERIFICATION_FAILED(CommonUtils.RESPONSE_FAILURE, "User !", "User Verification Failed"),

	USER_TOKEN_EXPIRED(CommonUtils.RESPONSE_FAILURE, "User !", "User Token Expired"),

	USER_VERIFICATION_AGAIN_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "User !",
			"Email has been Sent to your Registered Mail Id.Please Verify Your Account To Continue"),

	OTP_VERIFICATION_AGAIN_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "User !", "Verification code sent to"),

	USER_VERIFICATION_AGAIN_FAILED(CommonUtils.RESPONSE_FAILURE, "User !", "Please Try Again Later After Some Time"),

	USER_NOT_REGISETERED(CommonUtils.USER_REGISTER_FAILURE, "User !", "User not yet Registered"),

	SMS_NOT_REGISETERED(CommonUtils.USER_REGISTER_FAILURE, "User !", "Sms not Configured Properly Error Code is "),

	PLEASE_TRY_AGAIN_LATER(CommonUtils.RESPONSE_FAILURE, "User !", "Please Try Again Later"),

	TOKEN_VERIFICATION_AGAIN_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Token !", "Token verified"),
	TOKEN_VERIFICATION_AGAIN_FAILED(CommonUtils.RESPONSE_FAILURE, "Token !", "Token verification failed"),

	PASSWORD_CHANGED_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Password !", "Password Changed SuccessFully"),
	PASSWORD_CHANGED_FAILED(CommonUtils.RESPONSE_SUCCESS, "Password !", "Password Changed failed"),

	USER_NOT_FOUND(CommonUtils.RESPONSE_FAILURE, "User !", "User is not  Yet Registered !"),

	USER_ADMIN_RESET_PASSWORD_SITE(CommonUtils.RESPONSE_FAILURE, "User !",
			"User PassWord Can't be Changed. Please Contact System Admin!"),

	PASSWORD_MATCH_FAILED(CommonUtils.RESPONSE_FAILURE, "Password !", "Password Entered is Invalid !"),

	NEW_PASSWORD_MATCH_FAILED(CommonUtils.RESPONSE_FAILURE, "Password !",
			"New Password is same As the Old Password/ Please Enter A New Password!"),

	RECORD_FETCH_SUCCESSS(CommonUtils.RESPONSE_SUCCESS, "User !", "Record Fetched Successfully!"),
	RECORD_FETCH_ERRORR(CommonUtils.RESPONSE_FAILURE, "User !", "Error while fetching the record !"),

	RECORD_SAVE_SUCCESS(CommonUtils.INDEX_SUCCESS, "Record !", "Record Saved Successfully"),
	RECORD_SAVE_ERROR(CommonUtils.INDEX_FAILURE, "Record !", "Error while saving the record"),

	RECORD_INDEX_SUCCESS(CommonUtils.INDEX_SUCCESS, "Record !", "Record Saved Successfully"),
	RECORD_SAVE_ERRORR(CommonUtils.INDEX_FAILURE, "Record !", "Error while saving the record"),

	// Customer Details by Email
	CUSTOMER_EMAIL_SUCCESS("590", "Customer By Email !", "Customer data fetched Successfully !"),

	USER_ACCCOUT_ACTIVATE(CommonUtils.RESPONSE_SUCCESS_VERIFY, "User !",
			"Your email  been Already verified,Please Login to Continue"),

	RECORD_FETCH_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Record !", "Record Fetched Successfully"),
	RECORD_FETCH_ERROR(CommonUtils.RESPONSE_FAILURE, "Record !", "Error while fetching the record"),

	// report
	REPORT_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Reports ", "Report generation completed successfully"),
	REPORT_FAILED(CommonUtils.RESPONSE_FAILURE, "Reports ", "Error occurred while generating reports"),

	CUSTOMER_EMAIL_DEACTIVATE_SUCCESS("1003", "Email !", "Your account Has been DeActivated  !"),
	CUSTOMER_EMAIL_DEACTIVATE_FAILURE("1003", "Email !", "Your account Has been DeActivated Failed !"),

	RESPONSE_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Success", "Api Response Success"),
	RESPONSE_FAILED(CommonUtils.RESPONSE_FAILURE, "Failed", "Api Response Failed"),

	EMAIL_VERIFY_RESPONSE_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Success",
			"Your account needs to be verified, please check your email for Activation."),

	NO_ACCCES(CommonUtils.RESPONSE_FAILURE, "Failed", "Has No Access"),

	// Updated
	COUNTRY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Country ADD !", "Country Saved Successfully !"),
	COUNTRY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Country ADD !", "Country Saved Failed !"),

	// Updated
	LANGUAGE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Language ADD !", "Language Saved Successfully !"),
	LANGUAGE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Language ADD !", "Language Saved Failed !"),

	LAYOUT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Layout ADD !", "Layout Saved Successfully !"),
	LAYOUT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Layout ADD !", "Layout Saved Failed !"),

	CONSULTATION_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CONSULTATION ADD !", "Consultation Saved Successfully !"),
	CONSULTATION_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "CONSULTATION ADD !", "Consultation Saved Failed !"),

	// Updated
	CORETRANSLATE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Translate ADD !", "Record Saved Successfully !"),
	CORETRANSLATE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Translate ADD !", "Record Saved Failed !"),

	// Updated
	PINCODE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Country ADD !", "Pincode Saved Successfully !"),
	PINCODE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Country ADD !", "Pincode Saved Failed !"),

	ADD_PRODUCT_CUSTOM_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Saved !", " Saved Successfully !"),
	ADD_PRODUCT_CUSTOM_FAILURE(CommonUtils.RESPONSE_FAILURE, "failed !", " Saved Failed !"),

	// Updated
	UPDATE_PRODUCT_CUSTOM_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, " Updated Successfully !",
			"Updated Successfully !"),
	UPDATE_PRODUCT_CUSTOM_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "failed Updated Successfully !",
			" Updated Failed !"),

	DELETE_PRODUCT_CUSTOM_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, " Updated Successfully !",
			"Product Deleted Successfully !"),
	DELETE_PRODUCT_CUSTOM_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "failed Updated Successfully !",
			"Attribute Deletion Failed !"),

	DELETE_ASSOCIATED_PRODUCT_CUSTOM_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, " Updated Successfully !",
			"Product Associated Deleted Successfully !"),
	DELETE_ASSOCIATED_PRODUCT_CUSTOM_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "failed Updated Successfully !",
			" Product Associated Deletion Failed !"),

	CONTACT_SYSTEM_ADMIN(CommonUtils.RESPONSE_FAILURE, "User !", "Unable to login. Please contact your Administrator"),

	// delete shop data

	DELETE_SHOPPEE_SUCCESS(CommonUtils.RESPONSE_SUCCESS, " Updated Successfully !", "Product Deleted Successfully !"),
	DELETE_SHOPPEE_FAILURE(CommonUtils.RESPONSE_FAILURE, "failed Updated Successfully !", " Product Deletion Failed !"),

//RuleMaster

	ADDED_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "RuleMaster Add !", " Added Successfully !"),
	ADDED_FAILURE(CommonUtils.RESPONSE_FAILURE, "RuleMaster Add !", " Add Failed !"),
	// Updated
	UPDATED_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "RuleMaster Updated Successfully !", "Updated Successfully !"),
	UPDATED_FAILURE(CommonUtils.RESPONSE_FAILURE, "RuleMaster Updated Successfully !", " Updated Failed !"),

	// SalesCartRules
	ADD_SALES_CART_RULE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "SalesCartRules Add !", " Added Successfully !"),
	ADD_SALES_CART_RULE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "SalesCartRules Add !", " Add Failed !"),
	// Updated
	UPDATE_SALES_CART_RULE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "SalesCartRules Updated Successfully !",
			"Updated Successfully !"),
	UPDATE_SALES_CART_RULE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "SalesCartRules Updated Successfully !",
			" Updated Failed !"),

	TOKEN_GENERATION_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Coupon !", "Coupon Applied SuccessFully"),
	TOKEN_GENERATION_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Coupon !", " Coupon not Valid !"),

	TOKEN_GENERATION_FAILURE(CommonUtils.RESPONSE_FAILURE, "Token !", " Token Cannot be renewed !"),

	TEMPLATE_MISMATCH_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Excel !", "Uploaded Wrong Excel Template!"),

	EXCEL_TEMPLATE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Coupon !", "Excel Imported SuccessFully"),

	EXCEL_TEMPLATE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Excel !", "Uploaded Wrong Excel Template!"),

	// Updated
	SLIDER_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Slider ADD !", "Slider Saved Successfully !"),
	SLIDER_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Slider ADD !", "Slider Saved Failed !"),

	SYS_ADMIN_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, " !", "PLease Contact System Admin!"),

	ADD_UNITS_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "Unit Created Successful"),
	UPDATE_UNITS_SET_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "Unit Updated Successful"),

	ADD_UNITS_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", " Unit Creation Failed"),
	UPDATE_UNITS_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", "Unit Updated Failed"),

	// delete entire cart
	UPDATE_SUSPENDED_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Sale ADD !", "Sale Added successfully!"),
	UPDATE_SUSPENDED_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Sale ADD !", "Sale Added Failed !"),

	ADD_CASH_REGISTER_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "Cash Register Created Successful"),
	UPDATE_CASH_REGISTER_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Roles !", "Cash Register Closed Successful"),

	// delete entire cart
	OPWAPAYMENTAUTH_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Cart Delete !", "Success !"),
	OPWAPAYMENTAUTH_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Cart Delete !",
			"Payment gateway not Configured pls configure it  !"),

	ADD_CASH_REGISTER_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", " Cash Register Creation Failed"),
	UPDATE_CASH_REGISTER_FAILED(CommonUtils.RESPONSE_FAILURE, "Roles !", "Cash Register Closed Failed"),

	// add
	ADD_PAYMMENT_MODE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "PaymentMode Add !",
			"Payment Mode Added Successfully !"),
	ADD_PAYMMENT_MODE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "PaymentMode Add !", "Payment Mode Added Failed !"),

	PAYMMENT_MODE_EXIST_REC_SUCCESS(CommonUtils.RESPONSE_FAILURE, "Attribute  !", "Payment Mode Already Exists !"),
	PAYMMENT_MODE_EXISTE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Attribute !", "Payment Mode Never Exists  !"),

	// Updated
	UPDATE_PAYMMENT_MODE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "PaymentMode Updated Successfully !",
			"Payment Mode Updated Successfully !"),
	UPDATE_PAYMMENT_MODE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "PaymentMode Updated Successfully !",
			"Payment Mode Updated Failed !"),

	ADD_WAREHOUSE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Warehouse Add !", "Warehouse Added Successfully !"),
	ADD_WAREHOUSE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Warehouse Add !", "Warehouse Added Failed !"),

	// Updated
	UPDATE_WAREHOUSE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Warehouse Updated Successfully !",
			"Warehouse Updated Successfully !"),
	UPDATE_WAREHOUSE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Warehouse Updated Successfully !",
			"Warehouse Updated Failed !"),

	ADD_DELIVERY_PEOPLE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "DeliveryPeople Add !",
			"DeliveryPeople Added Successfully !"),
	ADD_DELIVERY_PEOPLE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "DeliveryPeople Add !",
			"DeliveryPeople Added Failed !"),

	// Updated
	UPDATE_DELIVERYPEOPLE_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "DeliveryPeople Updated Successfully !",
			"DeliveryPeople Updated Successfully !"),
	UPDATE_DELIVERYPEOPLE_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "DeliveryPeople Updated Successfully !",
			"DeliveryPeople Updated Failed !"),

	// order prescription

	ADD_ORDER_PRESCRIPTION_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Prescription Add !",
			"Prescription Added Successfully !"),
	ADD_ORDER_PRESCRIPTION_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Prescription Add !",
			"Prescription Added Failed !"),

	// Updated
	UPDATE_ORDER_PRESCRIPTION_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Prescription Updated Successfully !",
			"Prescription Updated Successfully !"),
	UPDATE_ORDER_PRESCRIPTION_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Prescription Updated Successfully !",
			"Prescription Updated Failed !"),

	ADD_DNS_RECORD_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "DnsRecord Add !", "DnsRecord Added Successfully !"),
	ADD_DNS_RECORD_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "DnsRecord Add !", "DnsRecord Added Failed !"),

	// Updated
	UPDATE_DNS_RECORD_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "DnsRecord Updated Successfully !",
			"DnsRecord Updated Successfully !"),
	UPDATE_DNS_RECORD_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "DnsRecord Updated Successfully !",
			"DnsRecord Updated Failed !"),

	ADD_MEDICAL_DELIVERY_OPTION_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Medical Delivery Option Add !",
			"Medical Delivery Option Added Successfully !"),
	ADD_MEDICAL_DELIVERY_OPTION_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Medical Delivery Option Add !",
			"Medical Delivery Option Added Failed !"),

	// Updated
	UPDATE_MEDICAL_DELIVERY_OPTION_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS,
			"Medical Delivery Option Updated Successfully !", "Medical Delivery Option Updated Successfully !"),
	UPDATE_MEDICAL_DELIVERY_OPTION_REC_FAILURE(CommonUtils.RESPONSE_FAILURE,
			"Medical Delivery Option Updated Successfully !", "Medical Delivery Option Updated Failed !"),

	CONTACT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Contact ADD !", "Contact Saved Successfully !"),
	CONTACT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Contact ADD !", "Contact Saved Failed !"),

	DELETE_CUSTOMER_ADDREESS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, " Updated Successfully !",
			"Product Associated Deleted Successfully !"),
	DELETE_CUSTOMER_ADDREESS_CUSTOM_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "failed Updated Successfully !",
			" Product Associated Deletion Failed !"),

	CONTACT_SUBMIT_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Contact ADD !", "Contact Submitted Successfully !"),
	CONTACT_SUBMIT_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Contact ADD !", "Contact Submitted Saved Failed !"),

	// onemg data

	ADD_ONEMGRX_DATA_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "OneMgRxData Add !", "OneMgRxData Added Successfully !"),
	ADD_ONEMGRX_DATA_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "OneMgRxData Add !", "OneMgRxData Added Failed !"),

	UPDATE_ONEMGRX_DATA_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "OneMgRxData Updated Successfully !",
			"OneMgRxData Updated Successfully !"),
	UPDATE_ONEMGRX_DATA_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "OneMgRxData Updated Successfully !",
			"OneMgRxData Updated Failed !"),

	// onemgNON data

	ADD_ONEMGNONRX_DATA_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "OneMgNonRxData Add !",
			"OneMgNonRxData Added Successfully !"),
	ADD_ONEMGNONRX_DATA_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "OneMgNonRxData Add !", "MigrateData Added Failed !"),

	UPDATE_ONEMGNONRX_DATA_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "OneMgNonRxData Updated Successfully !",
			"OneMgNonRxData Updated Successfully !"),
	UPDATE_ONEMGNONRX_DATA_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "OneMgNonRxData Updated Successfully !",
			"OneMgNonRxData Updated Failed !"),

	ADDACCOUNTTEST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "AccountTest !", "AccountTest Added Successfully !"),
	UPDATEACCOUNTTEST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "AccountTest !", "AccountTest Updated Successfully !"),
	ADDACCOUNTTEST_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "AccountTest !", "AccountTest Added Failed !"),

	ADDSTUDENTTEST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "AccountTest !", "STUDENTTest Added Successfully !"),
	UPDATESTUDENTTEST_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "AccountTest !", "STUDENTTest Updated Successfully !"),
	ADDSTUDENTTEST_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "AccountTest !", "STUDENTTest Added Failed !"),

	ADDWAREHOUSES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Warehouses !", "WAREHOUSES Added Successfully !"),
	UPDATEWAREHOUSES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Warehouses !", "WAREHOUSES Updated Successfully !"),
	ADDWAREHOUSES_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Warehouses !", "WAREHOUSES Added Failed !"),

	ADDBRANDS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Brands !", "BRANDS Added Successfully !"),
	UPDATEBRANDS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Brands !", "BRANDS Updated Successfully !"),
	ADDBRANDS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Brands !", "BRANDS Added Failed !"),

	ADDTAX_RATES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Taxrates !", "TAX_RATES Added Successfully !"),
	UPDATETAX_RATES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Taxrates !", "TAX_RATES Updated Successfully !"),
	ADDTAX_RATES_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Taxrates !", "TAX_RATES Added Failed !"),

	ADDCOMPANIES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Companies !", "COMPANIES Added Successfully !"),
	UPDATECOMPANIES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Companies !", "COMPANIES Updated Successfully !"),
	ADDCOMPANIES_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Companies !", "COMPANIES Added Failed !"),

	ADDEXPENSES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Expenses !", "EXPENSES Added Successfully !"),
	UPDATEEXPENSES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Expenses !", "EXPENSES Updated Successfully !"),
	ADDEXPENSES_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Expenses !", "EXPENSES Added Failed !"),

	ADDSETTINGS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Settings !", "SETTINGS Added Successfully !"),
	UPDATESETTINGS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Settings !", "SETTINGS Updated Successfully !"),
	ADDSETTINGS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Settings !", "SETTINGS Added Failed !"),

	ADDTRANSFERS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Transfers !", "TRANSFERS Added Successfully !"),
	UPDATETRANSFERS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Transfers !", "TRANSFERS Updated Successfully !"),
	ADDTRANSFERS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Transfers !", "TRANSFERS Added Failed !"),

	ADDCUSTOMER_GROUP_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CustomerGroup !",
			"CUSTOMERGROUP Added Successfully !"),
	UPDATECUSTOMER_GROUP_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CustomerGroup !",
			"CUSTOMERGROUP Updated Successfully !"),
	ADDCUSTOMER_GROUP_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "CustomerGroup !", "CUSTOMERGROUP Added Failed !"),

	ADDVARIANTS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Variants !", "VARIANTS Added Successfully !"),
	UPDATEVARIANTS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Variants !", "VARIANTS Updated Successfully !"),
	ADDVARIANTS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Variants !", "VARIANTS Added Failed !"),

	ADDCATEGORIES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Categories !", "CATEGORIES Added Successfully !"),
	UPDATECATEGORIES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Categories !", "CATEGORIES Updated Successfully !"),
	ADDCATEGORIES_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Categories !", "CATEGORIES Added Failed !"),

	ADDEXPENSE_CATEGORIES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Expensecategories !",
			"EXPENSECATEGORIES Added Successfully !"),
	UPDATEEXPENSE_CATEGORIES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Expensecategories !",
			"EXPENSECATEGORIES Updated Successfully !"),
	ADDEXPENSE_CATEGORIES_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Expensecategories !",
			"EXPENSECATEGORIES Added Failed !"),

	ADDKITCHEN_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Kitchen !", "KITCHEN Added Successfully !"),
	UPDATEKITCHEN_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Kitchen !", "KITCHEN Updated Successfully !"),
	ADDKITCHEN_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Kitchen !", "KITCHEN Added Failed !"),

	ADDPRODUCTS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Products !", "PRODUCTS Added Successfully !"),
	UPDATEPRODUCTS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Products !", "PRODUCTS Updated Successfully !"),
	ADDPRODUCTS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Products !", "PRODUCTS Added Failed !"),

	ADDCURRENCIES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Currencies !", "CURRENCIES Added Successfully !"),
	UPDATECURRENCIES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Currencies !", "CURRENCIES Updated Successfully !"),
	ADDCURRENCIES_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Currencies !", "CURRENCIES Added Failed !"),

	ADDINVOICES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Invoices !", "INVOICES Added Successfully !"),
	UPDATEINVOICES_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "Invoices !", "INVOICES Updated Successfully !"),
	ADDINVOICES_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "Invoices !", "INVOICES Added Failed !"),

	ADDPOS_SETTINGS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "PosSettings !", "POSSETTINGS Added Successfully !"),
	UPDATEPOS_SETTINGS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "PosSettings !", "POSSETTINGS Updated Successfully !"),
	ADDPOS_SETTINGS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "PosSettings !", "POSSETTINGS Added Failed !"),

	ADDPRICE_GROUPS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "PriceGroups !", "PRICEGROUPS Added Successfully !"),
	UPDATEPRICE_GROUPS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "PriceGroups !", "PRICEGROUPS Updated Successfully !"),
	ADDPRICE_GROUPS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "PriceGroups !", "PRICEGROUPS Added Failed !"),

	ADDSHOPSETTINGS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "ShopSettings !", "SHOPSETTINGS Added Successfully !"),
	UPDATESHOPSETTINGS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "ShopSettings !",
			"SHOP_SETTINGS Updated Successfully !"),
	ADDSHOPSETTINGS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "ShopSettings !", "SHOPSETTINGS Added Failed !"),

	ADDREFERALORDERITEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "ReferalOrderItems !",
			"REFERALORDERITEMS Added Successfully !"),
	UPDATEREFERALORDERITEMS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "ReferalOrderItems !",
			"REFERALORDERITEMS Updated Successfully !"),
	ADDREFERALORDERITEMS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "ReferalOrderItems !",
			"REFERALORDERITEMS Added Failed !"),

	ADDCREDIT_DEBIT_ENTRY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CreditDebitEntry !",
			"CREDITDEBITENTRY Added Successfully !"),
	UPDCREDIT_DEBIT_ENTRY_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "CreditDebitEntry !",
			"CREDITDEBITENTRY Updated Successfully !"),
	ADDCREDIT_DEBIT_ENTRY_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "CreditDebitEntry !",
			"CREDITDEBITENTRY Added Failed !"),

	ADDAPPROVE_TRANSACTIONS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "ApproveTransatctions !",
			"APPROVETRANSACTIONS Added Successfully !"),
	UPDATEAPPROVE_TRANSACTIONS_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "ApproveTransaction !",
			"APPROVETRANSACTIONS Updated Successfully !"),
	ADDAPPROVE_TRANSACTIONS_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "ApproveTransaction !",
			"APPROVETRANSACTIONS Added Failed !"),

	// migrate date

	ADD_MIGRATE_DATA_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "MigrateData Add !", "MigrateData Added Successfully !"),
	ADD_MIGRATE_DATA_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "MigrateData Add !", "MigrateData Added Failed !"),

	UPDATE_MIGRATE_DATA_REC_SUCCESS(CommonUtils.RESPONSE_SUCCESS, "MigrateData Updated Successfully !",
			"MigrateData Updated Successfully !"),
	UPDATE_MIGRATE_DATA_REC_FAILURE(CommonUtils.RESPONSE_FAILURE, "MigrateData Updated Successfully !",
			"MigrateData Updated Failed !");

	private String code;
	private String title;
	private String description;

	ResponseCodes02(String code, String title, String description) {
		this.code = code;
		this.title = title;
		this.description = description;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
