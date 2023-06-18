package com.calsoft.pos.model.currency;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Table(name = "directory_currency_rate")
@IdClass(DirectoryCurrencyRatePK.class)
@Entity
@Getter
@Setter
public class DirectoryCurrencyRate implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Column(name = "rate")
	private double rate;

	@Transient
	private String operationType;
	
	@Id
	@Column(name = "currency_from")
	private String currencyFrom;
	
	@Id
	@Column(name = "currency_to")
	private String currencyTo;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;

}
