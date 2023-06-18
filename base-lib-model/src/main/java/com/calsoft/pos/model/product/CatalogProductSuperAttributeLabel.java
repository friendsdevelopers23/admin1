package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Table(name = "catalog_product_super_attribute_label")
@Entity
@Data
public class CatalogProductSuperAttributeLabel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private Long valueId;

	@Column(name = "store_id")
	private long storeId;

	@Column(name = "use_default")
	private long useDefault;

	@Column(name = "value")
	private String value;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "product_super_attribute_id")
	private CatalogProductSuperAttribute catalogProductSuperAttribute;

}
