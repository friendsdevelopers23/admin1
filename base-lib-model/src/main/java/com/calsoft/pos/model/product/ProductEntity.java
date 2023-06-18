package com.calsoft.pos.model.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "catalog_product_entity")
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private Long entityId;

	@Column(name = "entity_type_id")
	private Long entityTypeId;

	@Column(name = "attribute_set_id")
	private Long attributeSetId;

	@Column(name = "type_id")
	private String typeId;

	@Column(name = "sku")
	private String sku;

	@Column(name = "has_options")
	private Long hasOptions;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductVarchar> productVarchar = new ArrayList<ProductVarchar>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductInt> productInt = new ArrayList<ProductInt>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductText> productText = new ArrayList<ProductText>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductDecimal> productDecimal = new ArrayList<ProductDecimal>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductDateTime> productDateTime = new ArrayList<ProductDateTime>();

	/*
	 * @OneToMany(cascade = CascadeType.ALL,orphanRemoval=true)
	 * 
	 * @JoinColumn(name="product_id")
	 */

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productEntity", orphanRemoval = true)
	private List<CatalogCategoryProduct> catalogCategoryProduct = new ArrayList<CatalogCategoryProduct>();

	@OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL)
	@JsonManagedReference
	private CatalogInventoryStockItem catalogInventoryStockItem;

	@OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL)
	@JsonManagedReference
	private CatalogInventoryStockStatus catalogInventoryStockStatus;

	@OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ProductEntityMediaGallery> productEntityMediaGallery = new ArrayList<ProductEntityMediaGallery>();

	@OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<CatalogProductSuperAttribute> catalogProductSuperAttribute = new ArrayList<CatalogProductSuperAttribute>();

	@OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<CatalogProductSuperLink> catalogProductSuperLink = new ArrayList<CatalogProductSuperLink>();

	@OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<CatalogProductLink> catalogProductLink = new ArrayList<CatalogProductLink>();

	/*
	 * @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL,
	 * orphanRemoval = true) private Set<CatalogruleProductEntity>
	 * catalogruleProductEntity;
	 */

	@Transient
	private String operationType;

	@Transient
	private List<String> imageRemove = new ArrayList<String>();

}
