package com.calsoft.pos.model.shipment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.calsoft.pos.model.order.SalesFlatOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "sales_flat_shipment")
@Data
public class SalesFlatShipment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	public long entityId;

	@Column(name = "store_id")
	private Long storeid;

	@Column(name = "total_weight")
	private Double totalWeight;

	@Column(name = "total_qty")
	private Double totalQty;

	@Column(name = "email_sent")
	private Long emailSent;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "shipping_address_id")
	private Long shippingAddressId;

	@Column(name = "billing_address_id")
	private Long billingAddressId;

	@Column(name = "shipment_status")
	private Long shipmentStatus;

	@Column(name = "increment_id")
	private String incrementId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	public Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "packages")
	private String packages;

	@Lob
	@Column(name = "shipping_label", columnDefinition = "BLOB")
	private byte[] shippingLabel;
	
	@Column(name = "order_id",insertable=false,updatable=false)
	private Long orderId;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "order_id")
	private SalesFlatOrder salesFlatOrder;

	@OneToMany(mappedBy = "salesFlatShipment", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatShipmentItem> salesFlatShipmentItem = new ArrayList<SalesFlatShipmentItem>();

	@OneToMany(mappedBy = "salesFlatShipment", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatShipmentComment> salesFlatShipmentComment = new ArrayList<SalesFlatShipmentComment>();

	@OneToMany(mappedBy = "salesFlatShipment", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalesFlatShipmentTrack> salesFlatShipmentTrack = new ArrayList<SalesFlatShipmentTrack>();

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "salesFlatShipment")
	private SalesFlatShipmentGrid SalesFlatShipmentGrid;
	
	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;
	
	

}
