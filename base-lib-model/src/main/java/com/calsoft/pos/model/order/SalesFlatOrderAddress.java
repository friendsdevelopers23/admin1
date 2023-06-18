package com.calsoft.pos.model.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales_flat_order_address")
@Getter
@Setter
public class SalesFlatOrderAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private int entityId;

	@Column(nullable = true, name = "customer_address_id")
	private Long customerAddressId;

	@Column(nullable = true, name = "quote_address_id")
	private Long quoteAddressId;

	@Column(nullable = true, name = "region_id")
	private Long regionId;

	@Column(nullable = true, name = "customer_id")
	private Long customerId;

	@Column(name = "fax")
	private String fax;

	@Column(name = "region")
	private String region;

	@Column(name = "postcode")
	private String postCode;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "email")
	private String email;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "country_id")
	private String countryId;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "address_type")
	private String addressType;

	@Column(name = "prefix")
	private String prefix;

	@Column(name = "middlename")
	private String middleName;

	@Column(name = "suffix")
	private String suffix;

	@Column(name = "company")
	private String company;

	@Column(name = "vat_id")
	private String vatId;

	@Column(nullable = true, name = "vat_is_valid")
	private Long vatIsValid;

	@Column(name = "vat_request_id")
	private String vatRequestId;

	@Column(name = "vat_request_date")
	private String vatRequestDate;

	@Column(nullable = true, name = "vat_request_success")
	private Long vatRequestSuccess;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private SalesFlatOrder salesFlatOrder;

	@Column(name = "parent_id", insertable = false, updatable = false)
	private Long parentId;

}
