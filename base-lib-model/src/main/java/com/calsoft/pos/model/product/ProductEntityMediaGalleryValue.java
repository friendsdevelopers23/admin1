package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalog_product_entity_media_gallery_value")
@Getter
@Setter
public class ProductEntityMediaGalleryValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "value_id")
	private long valueId;

	@Column(name = "store_id")
	private long storeId;

	@Column(name = "label")
	private String label;

	@Column(name = "position")
	private int position;

	@Column(name = "disabled")
	private long disabled;

	@JsonBackReference
	@OneToOne
	@MapsId
	@JoinColumn(name = "value_id")
	private ProductEntityMediaGallery productEntityMediaGallery = new ProductEntityMediaGallery();
}
