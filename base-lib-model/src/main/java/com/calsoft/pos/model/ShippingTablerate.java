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
@Table(name = "shipping_tablerate")
@Data
public class ShippingTablerate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "pk")
	private long pk;

	@Column(name = "website_id")
	private int websiteId;
	
	@Column(name = "dest_country_id")
	private String destCountryId;

	@Column(name = "dest_region_id")
	private String destRegionId;

	@Column(name = "dest_zip")
	private String destZip;

	@Column(name = "condition_name")
	private String conditionName;

	@Column(name = "condition_value")
	private double conditionValue;

	@Column(name = "price")
	private double price;

	@Column(name = "cost")
	private double cost;
	
	@Transient
	private String operationType;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

}
