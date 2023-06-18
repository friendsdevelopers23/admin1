package com.calsoft.pos.model.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> values;
	private Map<String, Object> valueIds;

	private String typeId;

	private Map<String, Object> catalogInventoryStockItem;

	private Map<String, Object> catalogInventoryStockStatus;

	List<ProductEntityMediaGallery> productEntityMediaGallery = new ArrayList<ProductEntityMediaGallery>();

	List<CatalogCategoryProduct> catalogCategoryProduct = new ArrayList<CatalogCategoryProduct>();
	
	private List<CatalogProductSuperAttribute> catalogProductSuperAttribute = new ArrayList<CatalogProductSuperAttribute>();
	
	private List<CatalogProductSuperLink> catalogProductSuperLink = new ArrayList<CatalogProductSuperLink>();
	
	private List<CatalogProductLink> catalogProductLink = new ArrayList<CatalogProductLink>();

}
