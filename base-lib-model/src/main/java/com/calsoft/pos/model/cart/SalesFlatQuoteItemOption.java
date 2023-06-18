package com.calsoft.pos.model.cart;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sales_flat_quote_item_option")
@Data
public class SalesFlatQuoteItemOption implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private Long optionId;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "code")
	private String code;

	@Column(name = "value")
	private String value;

	@Column(name = "additional")
	private String additional;

	@Column(name = "additional_info_given")
	private int additionalInfoGiven;
	
	@Column(name = "entity_id")
	private int index;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JoinColumn(name = "item_id")
	private SalesFlatQuoteItem salesFlatQuoteItem;

}
