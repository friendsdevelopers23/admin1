package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Table(name = "catalog_category_product")
@IdClass(CatalogCategoryProductId.class)
@Entity
@Getter
@Setter
public class CatalogCategoryProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "position")
	private int position;

	@Id
	@Column(name = "product_id", insertable = false, updatable = false)
	private Long productId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private ProductEntity productEntity;

	@JsonIgnoreProperties
	@JsonIgnore
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	public CatalogCategoryProduct(Long productId,Long categoryId) {
		this.productId = productId;
		this.categoryId = categoryId;
	}

	public CatalogCategoryProduct() {
		super();
	}
	
	

}
