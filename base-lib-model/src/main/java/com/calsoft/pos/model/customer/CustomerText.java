package com.calsoft.pos.model.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "customer_entity_text")
@Data
public class CustomerText implements Serializable, CustomerExt {

	private static final long serialVersionUID = -3045454542242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private int valueId;

	@Column(name = "entity_type_id")
	private int entityTypeId;

	@Column(name = "attribute_id")
	private int attributeId;

	@Column(name = "entity_id")
	private int entityId;

	@Column(name = "value")
	private String value;

	public int getValueId() {
		return valueId;
	}

}