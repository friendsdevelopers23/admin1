package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "catalog_product_entity_decimal")
@Data
public class ProductDecimal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private Long valueId;

	@Column(name = "entity_type_id")
	private Long entityTypeId;

	@Column(name = "attribute_id")
	private Long attributeId;

	@Column(name = "store_id")
	private Long storeId;
	
	@Column(name = "entity_id", insertable = false, updatable = false)
	private Long entityId;

	@Column(name = "value")
	private Double value;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "entity_id")
	private ProductEntity productEntity;

}
