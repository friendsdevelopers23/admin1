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
@Table(name = "referal_product_item")
public class ReferalProductItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "product_id")
	private long productId;

	@Column(name = "name")
	private String name;

	@Column(name = "referal_customer_id")
	private long referalCustomerId;

	@Column(name = "sku")
	private String sku;

	@Column(name = "grand_total")
	private double grandTotal;

	@Column(name = "additional_medical_charge")
	private double additionalMedicalCharge;

	@Column(name = "referral_commision_individual")
	private double referralCommisionIndividual;

	@Column(name = "referral_commision_cancelled")
	private double referralCommisionCancelled;

	@Column(name = "referral_commission_refunded")
	private double referralCommissionRefunded;

	@Column(name = "qty_ordered")
	private double qtyOrdered;

	@Column(name = "qty_refunded")
	private double qtyRefunded;

	@Column(name = "qty_shipped")
	private double qtyShipped;

	@Column(name = "qty_canceled")
	private double qtyCanceled;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = ".updated_at", updatable = false)
	private Date updatedAt;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
