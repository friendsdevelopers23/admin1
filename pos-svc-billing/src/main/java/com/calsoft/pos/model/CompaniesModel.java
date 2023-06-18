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

import lombok.Data;

@Entity
@Data
@Table(name = "companies")
public class CompaniesModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "group_id")
	private int groupId;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "customer_group_id")
	private int customerGroupId;

	@Column(name = "customer_group_name")
	private String customerGroupName;

	@Column(name = "name")
	private String name;

	@Column(name = "company")
	private String company;

	@Column(name = "vat_no")
	private String vatNo;

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

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

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

	@Column(name = "invoice_footer")
	private String invoiceFooter;

	@Column(name = "payment_term")
	private int paymentTerm;

	@Column(name = "logo")
	private String logo;

	@Column(name = "award_points")
	private int awardPoints;

	@Column(name = "deposit_amount")
	private double depositAmount;

	@Column(name = "price_group_id")
	private int priceGroupId;

	@Column(name = "price_group_name")
	private String priceGroupName;

	@Column(name = "gst_no")
	private String gstNo;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
