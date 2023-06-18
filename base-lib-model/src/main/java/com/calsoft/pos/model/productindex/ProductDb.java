package com.calsoft.pos.model.productindex;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.calsoft.pos.config.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Entity
@Data
public class ProductDb implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ENTITY_PK")
	private String entityPk;

	@Column(name = "entity_id")
	private long entityId;

	@Column(name = "attribute_id")
	private int attributeId;

	@Column(name = "label")
	private String label;

	@Column(name = "category_id")
	private long categoryId;

	@Column(name = "attribute_code")
	private String attributeCode;

	@Column(name = "value")
	private String value;

	@Column(name = "is_filterable")
	private int isFilterable;

	@Column(name = "QTY")
	private double qty;

	@Column(name = "is_visible_on_front")
	private Integer isAdditional;

	@Column(name = "type_id")
	private String typeId;

	@Column(name = "stock_status")
	private int stockStatus;

	@Column(name = "stock_id")
	private int stockId;

	@Column(name = "website_id")
	private int websiteId;

	@Column(name = "manage_stock")
	private int manageStock;

	@Column(name = "min_sale_qty")
	private double minSaleQty;

	@Column(name = "max_sale_qty")
	private double maxQty;

	@Column(name = "is_qty_decimal")
	private int isQtyDecimal;

	@Column(name = "notify_stock_qty")
	private Double notifyStockQty;

	@Column(name = "enable_qty_increments")
	private int enableQtyIncrements;

	@Column(name = "is_in_stock")
	private double isInStock;

	@Column(name = "attribute_set_id")
	private int attributeSetId;

	@Column(name = "value_Id")
	private String valueId;

	@Column(name = "backend_type")
	private String backendType;

	@Column(name = "item_id")
	private int itemId;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "sku")
	private String sku;
	
	@Column(name = "is_customized")
	private int isCustomized;
	
	@Column(name = "frontend_input")
	private String frontendInput;
	
	@Column(name = "is_used_in_site")
	private int isUsedInSite;
	
	@Column(name = "is_global")
	private int global;
	
	@Column(name = "is_configurable")
	private int isConfigurable;

	public ProductDb() {	
		super();
	}

	public ProductDb(String entityPk, long entityId, int attributeId, String label, long categoryId, String attributeCode,
			String value, int isFilterable, double qty, int isAdditional, String typeId, int stockStatus, int stockId,
			int websiteId, int manageStock, double minSaleQty, double maxQty, int isQtyDecimal, Double notifyStockQty,
			int enableQtyIncrements, double isInStock, String valueId, String backendType, int itemId,String sku) {
		this.entityPk = entityPk;
		this.entityId = entityId;
		this.attributeId = attributeId;
		this.label = label;
		this.categoryId = categoryId;
		this.attributeCode = attributeCode;
		this.value = value;
		this.isFilterable = isFilterable;
		this.qty = qty;
		this.isAdditional = isAdditional;
		this.typeId = typeId;
		this.stockStatus = stockStatus;
		this.stockId = stockId;
		this.websiteId = websiteId;
		this.manageStock = manageStock;
		this.minSaleQty = minSaleQty;
		this.maxQty = maxQty;
		this.isQtyDecimal = isQtyDecimal;
		this.notifyStockQty = notifyStockQty;
		this.enableQtyIncrements = enableQtyIncrements;
		this.isInStock = isInStock;
		this.valueId = valueId;
		this.backendType = backendType;
		this.itemId = itemId;
		this.sku = sku;

	}

	// index query parmeters findbyProductIdWithOutAdditionValue
	public ProductDb(String entityPk, long entityId, int attributeId, String label, long categoryId, String attributeCode,
			String value, int isFilterable, double qty, int isAdditional, String typeId, int stockStatus, int stockId,
			int websiteId, int manageStock, double minSaleQty, double maxQty, int isQtyDecimal, Double notifyStockQty,
			int enableQtyIncrements, double isInStock, int attributeSetId, Date createdDate, Date updatedDate,
			String sku,int isCustomized,String frontendInput,int isUsedInSite,int global ,int isConfigurable ) {
		this.entityPk = entityPk;
		this.entityId = entityId;
		this.attributeId = attributeId;
		this.label = label;
		this.categoryId = categoryId;
		this.attributeCode = attributeCode;
		this.value = value;
		this.isFilterable = isFilterable;
		this.qty = qty;
		this.isAdditional = isAdditional;
		this.typeId = typeId;
		this.stockStatus = stockStatus;
		this.stockId = stockId;
		this.websiteId = websiteId;
		this.manageStock = manageStock;
		this.minSaleQty = minSaleQty;
		this.maxQty = maxQty;
		this.isQtyDecimal = isQtyDecimal;
		this.notifyStockQty = notifyStockQty;
		this.enableQtyIncrements = enableQtyIncrements;
		this.isInStock = isInStock;
		this.attributeSetId = attributeSetId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.sku = sku;
		this.isCustomized=isCustomized;
		this.frontendInput=frontendInput;
		this.isUsedInSite=isUsedInSite;
		this.global = global;
		this.isConfigurable = isConfigurable;
	}

}
