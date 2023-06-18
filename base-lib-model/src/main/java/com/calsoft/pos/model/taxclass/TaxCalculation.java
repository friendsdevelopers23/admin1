package com.calsoft.pos.model.taxclass;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude="taxCalculationRule")
@Entity
@Table(name = "tax_calculation")
public class TaxCalculation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tax_calculation_id")
	private long taxCalculationId;

	@Column(name = "customer_tax_class_id")
	private long customerTaxClassId;

	@Column(name = "product_tax_class_id")
	private long productTaxClassId;

	@Column(name = "tax_calculation_rate_id")
	private long taxCalculationRateId;
	
	@Column(name = "tax_calculation_rule_id",insertable=false,updatable=false)
	private long taxCalculationRuleId;

	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "tax_calculation_rule_id")
	private TaxCalculationRule taxCalculationRule;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
