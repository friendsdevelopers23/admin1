package com.calsoft.pos.model.productcustomization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.calsoft.pos.model.product.ProductVarchar;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "catalog_product_customization")
@Getter
@Setter
public class ProductCustomization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "value_id")
	private int valueId;

	@Column(name = "value")
	private String value;

	@Column(name = "type")
	private String type;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Transient
	private String operationType;

	@OneToMany(mappedBy = "productCustomization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductCustomizationList> productCustomizationList = new ArrayList<ProductCustomizationList>();

	public ProductCustomization(int valueId, String value) {
		this.valueId = valueId;
		this.value = value;
	}

	public ProductCustomization() {
		super();
	}

}
