package com.calsoft.pos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@Table(name = "restriction")
@AllArgsConstructor
public class Restriction implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "plan_name")
	private String planName;

	@Column(name = "product_restriction")
	private long productRestriction;

	@Column(name = "attribute_restriction")
	private long attributeRestriction;

	@Column(name = "layout_restriction")
	private long layoutRestriction;

	@Column(name = "is_active")
	private int isActive;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "user_restriction")
	private long userRestriction;

	@Column(name = "plan_id")
	private long planId;

	@Column(name = "pixel_restriction")
	private long pixelRestriction;

	@Column(name = "ga_restriction")
	private long gaRestriction;

	@Column(name = "wa_bot_restriction")
	private long waBotRestriction;

	@Column(name = "store_pickup_restriction")
	private long storePickupRestriction;

	@Column(name = "enable_cash_in_hand_restriction")
	private long enableCashInHandRestriction;

	@Column(name = "remove_expiry_from_stock")
	private long removeExpiryFromStock;

	@Column(name = "update_stock_by_purchase")
	private long updateStockByPurchase;

	@Column(name = "based_on_vendor_restriction")
	private long basedOnVendorRestriction;

	@Column(name = "language_restriction")
	private long languageRestriction;

	@Column(name = "pos_restriction")
	private long posRestriction;
	
	@Column(name = "pincode_restriction")
	private long pincodeRestriction;

	@Transient
	private String operationType;

}
