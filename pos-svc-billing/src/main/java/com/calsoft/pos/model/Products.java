package com.calsoft.pos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "regional_product_name")
	private String regionalProductName;

	@Column(name = "unit")
	private Long unit;

	@Column(name = "cost")
	private Double cost;

	@Column(name = "price")
	private Double price;

	@Column(name = "alert_quantity")
	private Double alertQuantity;

	@Column(name = "image")
	private String image;

	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "subcategory_id")
	private Long subcategoryId;

	@Column(name = "cf1")
	private String cf1;

	@Column(name = "cf2")
	private String cf2;

	@Column(name = "cf3")
	private String cf3;

	@Column(name = "cf4")
	private String cf4;

	@Column(name = "cf5")
	private String cf5;

	@Column(name = "cf6")
	private String cf6;

	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "tax_rate")
	private Long taxRate;

	@Column(name = "track_quantity")
	private Long trackQuantity;

	@Column(name = "details")
	private String details;

	@Column(name = "warehouse")
	private Long warehouse;

	@Column(name = "barcode_symbology")
	private String barcodeSymbology;

	@Column(name = "file")
	private String file;

	@Column(name = "product_details")
	private String productDetails;

	@Column(name = "tax_method")
	private Long taxMethod;

	@Column(name = "type")
	private String type;

	@Column(name = "supplier1")
	private Long supplier1;

	@Column(name = "supplier1price")
	private Double supplier1price;

	@Column(name = "supplier2")
	private Long supplier2;

	@Column(name = "supplier2price")
	private Double supplier2price;

	@Column(name = "supplier3")
	private Long supplier3;

	@Column(name = "supplier3price")
	private Double supplier3price;

	@Column(name = "supplier4")
	private Long supplier4;

	@Column(name = "supplier4price")
	private Double supplier4price;

	@Column(name = "supplier5")
	private Long supplier5;

	@Column(name = "supplier5price")
	private Double supplier5price;

	@Column(name = "promotion")
	private Long promotion;

	@Column(name = "promo_price")
	private Double promoPrice;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", updatable = false)
	private Date startDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", updatable = false)
	private Date endDate;

	@Column(name = "supplier1_part_no")
	private String supplier1PartNo;

	@Column(name = "supplier2_part_no")
	private String supplier2PartNo;

	@Column(name = "supplier3_part_no")
	private String supplier3PartNo;

	@Column(name = "supplier4_part_no")
	private String supplier4PartNo;

	@Column(name = "supplier5_part_no")
	private String supplier5PartNo;

	@Column(name = "sale_unit")
	private Long saleUnit;

	@Column(name = "purchase_unit")
	private Long purchaseUnit;

	@Column(name = "brand")
	private Long brand;

	@Column(name = "slug")
	private String slug;

	@Column(name = "featured")
	private Long featured;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "hsn_code")
	private Long hsnCode;

	@Column(name = "views")
	private Long views;

	@Column(name = "hide")
	private Long hide;

	@Column(name = "second_name")
	private String secondName;

	@Column(name = "hide_pos")
	private Long hidePos;

	@Column(name = "select_type_dropdown")
	private Long selectTypeDropdown;

	@Column(name = "multiple_prices")
	private String multiplePrices;

	@Column(name = "minimum_price")
	private Float minimumPrice;

	@Column(name = "kitchen_id")
	private Long kitchenId;

	@Column(name = "secondary_name")
	private String secondaryName;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
