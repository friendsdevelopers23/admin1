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
@Table(name = "warehouse")
@Data
public class Warehouse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "is_active")
	private int isActive;

	@Column(name = "street")
	private String street;

	@Column(name = "region")
	private String region;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "postcode")
	private String postCode;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "region_id")
	private String regionId;

	@Column(name = "country_id")
	private String countryId;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;
	
	@Transient
	private String operationType;
	
	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;


}
