package com.calsoft.pos.model.shipment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "sales_flat_shipment_item")
@Data
public class SalesFlatShipmentItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private long entityId;

	@Column(name = "row_total")
	private Double rowTotal;

	@Column(name = "price")
	private double price;

	@Column(name = "weight")
	private double weight;

	@Column(name = "qty")
	private double qty;

	@Column(name = "product_id")
	private long productId;

	@Column(name = "order_item_id")
	private long orderItemId;

	@Column(name = "additional_data")
	private String additionalData;

	@Column(name = "description")
	private String description;

	@Column(name = "name")
	private String name;

	@Column(name = "sku")
	private String sku;

	@Column(name = "parent_id", insertable = false, updatable = false)
	public long parentId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private SalesFlatShipment salesFlatShipment;

	@Transient
	private String operationType;

	@Transient
	private double qtyShipped;

	@Transient
	private double qtyRefunded;
	
	@Column(name = "supplier_latitude")
	private String supplierLatitude;

	@Column(name = "supplier_longitude")
	private String supplierLongitude;

	@Column(name = "supplier_id")
	private String supplierId;

	@Column(name = "supplier_name")
	private String supplierName;

	@Column(name = "shipping_amount")
	private Double shippingAmount;

}
