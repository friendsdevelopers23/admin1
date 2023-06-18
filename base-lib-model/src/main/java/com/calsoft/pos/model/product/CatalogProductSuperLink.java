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

import lombok.Data;

@Table(name = "catalog_product_super_link")
@Entity
@Data
public class CatalogProductSuperLink implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "link_id")
	private Long linkId;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "parent_id", insertable = false, updatable = false)
	private long parentId;

	@Column(name = "manage_stock_seperately")
	private int manageStockSeperately;

	@Column(name = "query_condition")
	private String queryCondition;

	@Column(name = "manage_price_seperately")
	private int managePriceSeperately;

	@Column(name = "manage_image_seperately")
	private int manageImageSeperately;
	
	@Column(name = "manage_description_seperately")
	private int manageDescriptionSeperately;
	
	@Column(name = "manage_additional_information_seperately")
	private int manageAdditionalInformationSeperately;
	
	@Transient
	private String name;
	
	@Transient
	private String sku;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private ProductEntity productEntity;

}
