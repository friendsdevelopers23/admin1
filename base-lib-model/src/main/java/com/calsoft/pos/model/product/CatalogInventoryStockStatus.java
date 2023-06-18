package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cataloginventory_stock_status")
@Getter
@Setter
public class CatalogInventoryStockStatus implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Column(name = "qty")
	private double qty;

	@Column(name = "stock_status")
	private Long stockStatus;

	@Column(name = "stock_id", updatable = false)
	private Long stockId;

	@Column(name = "website_id", updatable = false)
	private Long websiteId;


	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, targetEntity = ProductEntity.class)
	@MapsId
	@JoinColumn(name = "product_id", updatable = false)
	private ProductEntity productEntity;
	
	@Id
	@Column(name = "product_id", insertable = false, updatable = false)
	private Long productId;

}
