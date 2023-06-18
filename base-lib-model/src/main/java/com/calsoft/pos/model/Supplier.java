package com.calsoft.pos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "supplier")
@Data
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "company")
	private String company;

	@Column(name = "vat_no")
	private String vatNo;

	@Column(name = "gst_no")
	private String gstNo;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "country")
	private String country;

	@Column(name = "cf1")
	private String cf1;

	@Column(name = "cf2")
	private String cf2;

	@Column(name = "cf3")
	private String cf3;

	@Column(name = "cf4")
	private String cf4;

	@Column(name = "cf5")
	private String cf5;

	@Column(name = "cf6")
	private String cf6;

	@Column(name = "payment_term")
	private long payment_term;

	@Column(name = "logo")
	private String logo;

	@Column(name = "award_points")
	private long awardPoints;

	@Column(name = "deposit_amount")
	private double deposit_amount;

	@Column(name = "supplier_website")
	private String supplierWebsite;

	@Column(name = "supplier_latitude")
	private String supplierLatitude;

	@Column(name = "supplier_longitude")
	private String supplierLongitude;
	
	@Column(name = "vendor_rule")
	private String vendorRule;

	@Transient
	private String operationType;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
