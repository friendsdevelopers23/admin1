package com.calsoft.pos.model;

import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "attribute_idx")
public class AttribueIDX {

	@Id
	@Indexed(name = "entityId", type = "long")
	private long entityId;

	@Indexed(name = "categoryId")
	private long categoryId;

	@Indexed(name = "stockStatus")
	private int stockStatus;

	@Indexed(name = "isFilterable")
	private int isFilterable;

	@Indexed(name = "qty")
	private double qty;

	@Indexed(name = "backendType")
	private String backendType;

	@Dynamic
	@Field("*_s")
	private Map<String, String> values;

	@Indexed(name = "type")
	private String type;

	@Indexed(name = "price")
	private double price;

	@Indexed(name = "specialPrice")
	private double specialPrice;

	@Indexed(name = "discountPercentage")
	private double discountPercentage;

	@Indexed(name = "description")
	private String description;

	@Indexed(name = "name")
	private String name;

	@Indexed(name = "shortDescription")
	private String shortDescription;

	@Indexed(name = "image")
	private String productImageUrl;

	@Indexed(name = "weight")
	private String weight;

	@Indexed(name = "emi")
	private double emi;

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}

	public int getIsFilterable() {
		return isFilterable;
	}

	public void setIsFilterable(int isFilterable) {
		this.isFilterable = isFilterable;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public String getBackendType() {
		return backendType;
	}

	public void setBackendType(String backendType) {
		this.backendType = backendType;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

}
