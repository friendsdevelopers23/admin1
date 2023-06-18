package com.calsoft.pos.model.currency;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class DirectoryCurrencyRatePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "currency_from")
	private String currencyFrom;

	@Column(name = "currency_to")
	private String currencyTo;

}
