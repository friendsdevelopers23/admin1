package com.calsoft.pos.model.taxclass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@EqualsAndHashCode
@Entity
@Table(name = "tax_calculation_rule")
public class TaxCalculationRule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tax_calculation_rule_id")
	private long taxCalculationRuleId;

	@Column(name = "code")
	private String code;

	@Column(name = "priority")
	private int priority;

	@Column(name = "position")
	private int position;

	@Column(name = "calculate_subtotal")
	private int calculateSubtotal;

	@OneToMany(mappedBy = "taxCalculationRule", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<TaxCalculation> taxCalculation;

	@Transient
	private String operationType;
	
	@Transient
	private List<Long> customerTaxMultiCtrl = new ArrayList<Long>();
	
	@Transient
	private List<Long> productTaxMultiCtrl = new ArrayList<Long>();
	
	@Transient
	private List<Long> taxRateMultiCtrl = new ArrayList<Long>();

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}