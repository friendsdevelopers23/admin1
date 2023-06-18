package com.calsoft.pos.model.productcustomization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalog_product_customization_list")
@Getter
@Setter
public class ProductCustomizationList implements  Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "entity_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int entityId;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "resource_key")
	private String key;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "value_id")
	private ProductCustomization productCustomization;
}
