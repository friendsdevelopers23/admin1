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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Table(name = "catalog_product_link")
@Entity
@Data
public class CatalogProductLink implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "link_id")
	private Long linkId;

	@Column(name = "product_id", insertable = false, updatable = false)
	private long productId;

	@Column(name = "linked_product_id")
	private long linkedProductId;

	@Column(name = "link_type_id")
	private int linkTypeId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;

	@Transient
	private String name;

	@Transient
	private String sku;

	@Transient
	private double price;

	public CatalogProductLink() {
		super();
	}

	public CatalogProductLink(int linkTypeId, long productId) {

		this.linkTypeId = linkTypeId;
		this.productId = productId;
	}

}
