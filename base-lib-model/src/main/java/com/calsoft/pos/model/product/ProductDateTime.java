package com.calsoft.pos.model.product;

import javax.persistence.*;

import com.calsoft.pos.config.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "catalog_product_entity_datetime")
@Cacheable
@Data
public class ProductDateTime implements Serializable {

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

	@Column(name = "value")
	private Date value;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "entity_id")
	private ProductEntity productEntity;

}
