package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name = "catalog_product_relation")
@IdClass(CatalogProductRelationId.class)
@Entity
@Getter
@Setter
public class CatalogProductRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "parent_id")
	private Long parentId;

	@Id
	@Column(name = "child_id")
	private int childId;

}
