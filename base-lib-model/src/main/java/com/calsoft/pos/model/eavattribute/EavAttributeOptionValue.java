package com.calsoft.pos.model.eavattribute;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eav_attribute_option_value")
@Getter
@Setter
public class EavAttributeOptionValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private long valueId;

	@Column(name = "option_id", insertable = false, updatable = false)
	private long optionId;

	@Column(name = "value")
	private String value;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "option_id")
	private EavAttributeOption eavAttributeOption;

	@OneToOne(mappedBy = "eavAttributeOptionValue", cascade = CascadeType.ALL)
	@JsonManagedReference
	private EavAttributeOptionValueCustomized eavAttributeOptionValueCustomized;

	public EavAttributeOptionValue(long optionId, String value) {
		this.optionId = optionId;
		this.value = value;
	}

	
	public EavAttributeOptionValue() {
		super();
	}


	public EavAttributeOptionValue(long valueId, long optionId, String value) {
		this.valueId = valueId;
		this.optionId = optionId;
		this.value = value;
	}

}
