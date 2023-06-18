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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Table(name = "catalog_product_super_attribute")
@Entity
@Data
public class CatalogProductSuperAttribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_super_attribute_id")
	private Long productSuperAttributeId;

	@Column(name = "product_id",insertable = false,updatable = false)
	private long productId;

	@Column(name = "attribute_id")
	private long attributeId;

	@Column(name = "position")
	private long position;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity productEntity;
	
	@OneToOne(mappedBy = "catalogProductSuperAttribute", cascade = CascadeType.ALL)
	@JsonManagedReference
	private CatalogProductSuperAttributeLabel catalogProductSuperAttributeLabel;

}
