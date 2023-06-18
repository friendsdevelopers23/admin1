
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
@Table(name = "shop_settings")
public class ShopSettings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_id")
	private Long shopId;

	@Column(name = "shop_name")
	private String shopName;

	@Column(name = "description")
	private String description;

	@Column(name = "warehouse")
	private Long warehouse;

	@Column(name = "biller")
	private Long biller;

	@Column(name = "about_link")
	private String aboutLink;

	@Column(name = "terms_link")
	private String termsLink;

	@Column(name = "privacy_link")
	private String privacyLink;

	@Column(name = "contact_link")
	private String contactLink;

	@Column(name = "payment_text")
	private String paymentText;

	@Column(name = "follow_text")
	private String followText;

	@Column(name = "facebook")
	private String facebook;

	@Column(name = "twitter")
	private String twitter;

	@Column(name = "google_plus")
	private String googlePlus;

	@Column(name = "instagram")
	private String instagram;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "cookie_message")
	private String cookieMessage;

	@Column(name = "cookie_link")
	private String cookieLink;

	@Column(name = "slider")
	private String slider;

	@Column(name = "shipping")
	private Long shipping;

	@Column(name = "purchase_code")
	private String purchaseCode;

	@Column(name = "envato_username")
	private String envatoUsername;

	@Column(name = "version")
	private String version;

	@Column(name = "logovisible")
	private String logovisible;

	@Column(name = "bank_details")
	private String bankDetails;

	@Column(name = "products_page")
	private Long productsPage;

	@Column(name = "hide0")
	private Long hide0;

	@Column(name = "products_description")
	private String productsDescription;

	@Column(name = "Private")
	private Long Private;

	@Column(name = "hide_price")
	private Long hidePrice;

	@Column(name = "stripe")
	private int stripe;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;
}
