package com.calsoft.pos.model.productindex;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@SolrDocument(collection = "product_idx")
@Data
public class ProductIDX2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Indexed(name = "entityId")
	private Long entityId;

	@Indexed(name = "categoryName")
	private String categoryName;

	@Dynamic
	@Field("*_s")
	private Map<String, String> values;

	@Dynamic
	@Field("*_a")
	private Map<String, String> additionalSpec;

	@Dynamic
	@Field("*_cust")
	private Map<String, String> isCustomizedValue;

	@Field("categoryId_txt")
	private List<Long> categoryId;

	@Field("images_txt")
	private List<String> images;

	@Field("categoryNames_txt")
	private List<String> categoryNames;

	@Indexed(name = "qty")
	private double qty;

	@Indexed(name = "attributeCode")
	private String attributeCode;

	@Indexed(name = "type")
	private String type;

	@Indexed(name = "specialPrice")
	private double specialPrice;

	@Indexed(name = "discountPercentage")
	private double discountPercentage;

	@Indexed(name = "price")
	private double price;

	@Indexed(name = "emi")
	private double emi;

	@Indexed(name = "description")
	private String description;
	
	@Indexed(name = "keyword")
	private String keyword;

	@Indexed(name = "name")
	private String name;

	@Indexed(name = "shortDescription")
	private String shortDescription;

	@Indexed(name = "image")
	private String productImageUrl;

	@Indexed(name = "stockStatus")
	private Long stockStatus;

	@Indexed(name = "stockId")
	private Long stockId;

	@Indexed(name = "websiteId")
	private long websiteId;

	@Indexed(name = "manageStock")
	private Long manageStock;

	@Indexed(name = "minSaleQty")
	private double minSaleQty;

	@Indexed(name = "maxSaleQty")
	private double maxSaleQty;

	@Indexed(name = "isQtyDecimal")
	private Long isQtyDecimal;

	@Indexed(name = "notifyStockQty")
	private double notifyStockQty;

	@Indexed(name = "enableQtyIncrements")
	private Long enableQtyIncrements;

	@Indexed(name = "isInStock")
	private double isInStock;

	@Indexed(name = "attributeSetId")
	private Long attributeSetId;

	@Indexed(name = "createdDate")
	private Date createdDate;

	@Indexed(name = "updatedDate")
	private Date updatedDate;

	@Indexed(name = "sku")
	private String sku;

	@Indexed(name = "weight")
	private String weight;

	@Indexed(name = "calculateAvgRating")
	private double calculateAvgRating;

	@Indexed(name = "averageRating")
	private long averageRating;

	@JsonIgnore
	@JsonIgnoreProperties
	@Indexed(name = "tenantId", type = "string")
	private String tenantId;

	@Indexed(name = "taxClassId")
	private long taxClassId;

	@Indexed(name = "calculateSubtotalOnly")
	private long calculateSubtotalOnly;

	@Indexed(name = "tax")
	private double tax;

	@Indexed(name = "isCustomized")
	private Long isCustomized;

	@Indexed(name = "visibility")
	private long visibility;

	@Indexed(name = "attributeSetName")
	private String attributeSetName;

	@Indexed(name = "posHide")
	private Long posHide;

	@Indexed(name = "hideInShop")
	private Long hideinShop;

	@Indexed(name = "posPrice")
	private double posPrice;

	@Indexed(name = "posSpecialPrice")
	private double posSpecialPrice;

	@Indexed(name = "featured")
	private Long featured;

	@Indexed(name = "units")
	private String units;

	@Indexed(name = "maintain_stock_ind_conf_product")
	private int maintainStockIndConfProduct;

	@Indexed(name = "maintain_price_ind_conf_product")
	private int maintainPriceIndConfProduct;

	@Indexed(name = "maintain_image_ind_conf_product")
	private int maintainImageIndConfProduct;

	@Dynamic
	@Field("*_configurableProduct")
	private LinkedHashMap<String, String> configurableProductMap;

	@Dynamic
	@Field("*_configProuctDetails")
	private Map<String, String> configProduct;

	@Dynamic
	@Field("*_config")
	private Map<String, String> configurableProductDropdown;

	@Indexed(name = "configurable_product_active")
	private int configurableProductActive;

	@Field("related_txt")
	private List<Long> relatedProducts;

	@Field("upSells_txt")
	private List<Long> upSells;

	@Field("cross-Sells_txt")
	private List<Long> crossSells;

	@Transient
	private long supplierId;

}
