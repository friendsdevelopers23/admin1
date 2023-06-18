package com.calsoft.pos.model.product;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "catalog_product_entity_text")
@Data
public class ProductText implements Serializable {

	private static final long serialVersionUID = -3045454542242241606L;

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

	@Column(name = "value")
	private String value;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "entity_id")
	private ProductEntity productEntity;

}
