package com.calsoft.pos.model.taxclass;

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
@Table(name = "tax_calculation_rate")
@Data
public class TaxCalculationRate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tax_calculation_rate_id")
	private long taxCalculationRateId;

	@Column(name = "tax_country_id")
	private String taxCountryId;

	@Column(name = "tax_region_id")
	private int taxRegionId;

	@Column(name = "tax_postcode")
	private String taxPostcode;

	@Column(name = "code")
	private String code;

	@Column(name = "rate")
	private double rate;

	@Column(name = "zip_is_range")
	private Integer zipIsRange;

	@Column(name = "zip_from")
	private Integer zipFrom;

	@Column(name = "zip_to")
	private Integer zipTo;

	@Column(name = "cgst")
	private double cgst;

	@Column(name = "sgst")
	private double sgst;

	@Column(name = "igst")
	private double igst;

	@Transient
	private String operationType;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Transient
	private int calculateSubtotal;

	public TaxCalculationRate(double rate,double cgst, double sgst, double igst,int calculateSubtotal) {
		this.rate = rate;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.calculateSubtotal = calculateSubtotal;
	}

	public TaxCalculationRate() {
		super();
	}

}
