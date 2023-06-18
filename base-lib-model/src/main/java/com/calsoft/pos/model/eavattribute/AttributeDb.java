package com.calsoft.pos.model.eavattribute;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class AttributeDb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "entity_pk")
	private String entityPk;

	@Column(name = "entity_id")
	private int entityId;

	@Column(name = "attribute_id")
	private int attributeId;

	@Column(name = "label")
	private String label;

	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "attribute_code")
	private String attributeCode;

	@Column(name = "stock_status")
	private int stockStatus;

	@Column(name = "value")
	private String value;

	@Column(name = "is_filterable")
	private int isFilterable;

	@Column(name = "QTY")
	private double qty;

	@Column(name = "backend_type")
	private String backendType;

	@Column(name = "type_id")
	private String type_id;

	public String getEntityPk() {
		return entityPk;
	}

	public void setEntityPk(String entityPk) {
		this.entityPk = entityPk;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getAttributeCode() {
		return attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public AttributeDb() {
		super();
	}

	public AttributeDb(String entityPk, int entityId, int attributeId, String label, int categoryId,
			String attributeCode, int stockStatus, String value, int isFilterable, double qty, String backendType,
			String type_id) {
		super();
		this.entityPk = entityPk;
		this.entityId = entityId;
		this.attributeId = attributeId;
		this.label = label;
		this.categoryId = categoryId;
		this.attributeCode = attributeCode;
		this.stockStatus = stockStatus;
		this.value = value;
		this.isFilterable = isFilterable;
		this.qty = qty;
		this.backendType = backendType;
		this.type_id = type_id;
	}



}
