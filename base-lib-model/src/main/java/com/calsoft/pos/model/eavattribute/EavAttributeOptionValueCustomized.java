package com.calsoft.pos.model.eavattribute;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eav_attribute_option_value_customized")
@Getter
@Setter
public class EavAttributeOptionValueCustomized implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "value_id",insertable=false,updatable=false)
	private long valueId;

	@Column(name = "value")
	private String value;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "value_id")
	@MapsId
	private EavAttributeOptionValue eavAttributeOptionValue;

	

}
