package com.calsoft.pos.model;

import java.io.Serializable;
import java.util.Date;

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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
@Table(name = "transfer_items")
public class TransferItems implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "transfer_item_id")
	private Long transferItemId;

	@Column(name = "transfer_id", insertable = false, updatable = false)
	private Long transferId;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "option_id")
	private long optionId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiry", updatable = false)
	private Date expiry;

	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "tax_rate_id")
	private long taxRateId;

	@Column(name = "tax")
	private String tax;

	@Column(name = "item_tax")
	private Double itemTax;

	@Column(name = "net_unit_cost")
	private Double netUnitCost;

	@Column(name = "subtotal")
	private Double subtotal;

	@Column(name = "quantity_balance")
	private Double quantityBalance;

	@Column(name = "unit_cost")
	private Double unitCost;

	@Column(name = "real_unit_cost")
	private Double realUnitCost;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", updatable = false)
	private Date date;

	@Column(name = "warehouse_id")
	private long warehouseId;

	@Column(name = "product_unit_id")
	private long productUnitId;

	@Column(name = "product_unit_code")
	private String productUnitCode;

	@Column(name = "unit_quantity")
	private Double unitQuantity;

	@Column(name = "gst")
	private String gst;

	@Column(name = "cgst")
	private Double cgst;

	@Column(name = "sgst")
	private String sgst;

	@Column(name = "igst")
	private String igst;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "transfer_id")
	private Transfers transfers;
}
