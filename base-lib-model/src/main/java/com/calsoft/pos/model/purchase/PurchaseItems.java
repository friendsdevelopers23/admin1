package com.calsoft.pos.model.purchase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.calsoft.pos.config.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Entity
@Table(name = "purchase_items")
@Data
public class PurchaseItems implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private Long purchaseId;

	@Column(name = "parent_id", insertable = false, updatable = false)
	private long parentId;

	@Column(name = "transfer_id")
	private long transferId;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "sku")
	private String sku;

	@Column(name = "name")
	private String name;

	@Column(name = "option_id")
	private double optionId;

	@Column(name = "net_unit_cost")
	private Double netUnitCost;

	@Column(name = "qty")
	private Double qty;

	@Column(name = "warehouse_id")
	private long warehouseId;

	@Column(name = "item_tax")
	private double itemTax;

	@Column(name = "tax_rate_id")
	private Long taxRateId;

	@Column(name = "tax")
	private String tax;

	@Column(name = "discount")
	private String discount;

	@Column(name = "item_discount")
	private double itemDiscount;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "expiry")
	private Date expiry;

	@Column(name = "grand_total")
	private double grandTotal;

	@Column(name = "subtotal")
	private double subtotal;

	@Column(name = "quantity_balance")
	private double quantityBalance;

	@Column(name = "status")
	private String status;

	@Column(name = "unit_cost")
	private Double unitCost;

	@Column(name = "real_unit_cost")
	private Double realUnitCost;

	@Column(name = "quantity_received")
	private double quantityReceived;

	@Column(name = "supplier_part_no")
	private double supplierPartNo;

	@Column(name = "purchase_item_id")
	private int purchaseItemId;

	@Column(name = "cgst")
	private Double cgst;

	@Column(name = "sgst")
	private Double sgst;

	@Column(name = "igst")
	private Double igst;

	@Column(name = "base_unit_cost")
	private Double baseUnitCost;

	@Column(name = "product_unit_code")
	private String productUnitCode;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Purchase purchase;
	
	@Column(name = "stock_expiry_done")
	private int stockExpiryDone;

	public PurchaseItems(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public PurchaseItems() {
		super();
	}

}
