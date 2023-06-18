package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalog_product_entity_media_gallery")
@Getter
@Setter
public class ProductEntityMediaGallery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private long valueId;

	@Column(name = "attribute_id")
	private long attributeId;

	@Column(name = "value")
	private String value;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "entity_id")
	private ProductEntity productEntity;
	
	@OneToOne(mappedBy = "productEntityMediaGallery", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private ProductEntityMediaGalleryValue productEntityMediaGalleryValue;
	
	@Transient
	private String attributeCode;
	

}
