package com.calsoft.pos.model.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Data;

@Entity
@Table(name = "customer_address_entity_varchar")
@Data
public class CustomerAddressVarchar implements Serializable, CustomerExt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private int valueId;

	@Column(name = "entity_type_id")
	private int entityTypeId;

	@Column(name = "attribute_id")
	private int attributeId;

	@Column(name = "entity_id")
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private int entityId;

	@Column(name = "value")
	private String value;

}
