package com.calsoft.pos.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "cs_catalog_product_pincode")
public class CatalogProductPincode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	private int id;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "isActive")
	private int isActive;
	
	@Column(name = "product_id")
	private long productId;
	

}
