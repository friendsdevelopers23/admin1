package com.calsoft.pos.model.category;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "catalog_category_entity_int")
@Data
public class CategoryInt implements Serializable, CategoryExt {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private int valueId;

	@Column(name = "entity_type_id")
	private int entityTypeId;

	@Column(name = "attribute_id")
	private int attributeId;

	@Column(name = "store_id")
	private int storeId;

	@Column(name = "value")
	private String value;

	@Column(name = "entity_id")
	private int entityId;

}