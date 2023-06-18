package com.calsoft.pos.model.shipment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "sales_flat_shipment_grid")
@Data
public class SalesFlatShipmentGrid implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "entity_id", unique = true, nullable = false)
	private long entityId;

	@Column(name = "store_id")
	private long storeid;

	@Column(name = "total_qty")
	private double totalQty;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "shipment_status")
	private long shipmentStatus;

	@Column(name = "increment_id")
	private String incrementId;

	@Column(name = "order_increment_id")
	private String orderIncrementId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_created_at")
	private Date orderCreatedAt;

	@Column(name = "shipping_name")
	private String shippingName;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "entity_id")
	private SalesFlatShipment salesFlatShipment;
	
	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;
	
	@Column(name = "supplier_id")
	private String supplierId;

	@Column(name = "supplier_name")
	private String supplierName;
}
