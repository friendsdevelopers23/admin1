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
@Table(name = "customer_group")
public class CustomerGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "customer_group_id")
	private long customerGroupId;

	@Column(name = "customer_group_code")
	private String customerGroupCode;

	@Column(name = "tax_class_id")
	private long taxClassId;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
