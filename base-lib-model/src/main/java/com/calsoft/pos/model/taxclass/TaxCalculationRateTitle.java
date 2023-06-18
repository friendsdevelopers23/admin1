package com.calsoft.pos.model.taxclass;

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
@Table(name = "tax_calculation_rate_title")
@Data
public class TaxCalculationRateTitle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "tax_calculation_rate_title_id")
	private long taxCalculationRateTitleId;

	@Column(name = "tax_calculation_rate_id")
	private int taxCalculationRateId;

	@Column(name = "store_id")
	private int storeId;

	@Column(name = "value")
	private String value;
	
	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

}
