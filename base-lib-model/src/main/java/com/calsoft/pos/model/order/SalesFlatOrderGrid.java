package com.calsoft.pos.model.order;

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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "sales_flat_order_grid")
@Data
public class SalesFlatOrderGrid implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "entity_id", insertable = false, updatable = false)
	private long entityId;

	@Column(name = "status")
	private String status;

	@Column(name = "store_id")
	private long storeId;

	@Column(name = "store_name")
	private String storeName;

	@Column(nullable = true, name = "customer_id")
	private int customerId;

	@Column(name = "base_grand_total")
	private double baseGrandTotal;

	@Column(nullable = true, name = "base_total_paid")
	private Double baseTotalPaid;

	@Column(name = "grand_total")
	private double grandTotal;

	@Column(nullable = true, name = "total_paid")
	private Double totalPaid;

	@Column(name = "increment_id")
	private String incrementId;

	@Column(name = "base_currency_code")
	private String baseCurrencyCode;

	@Column(name = "order_currency_code")
	private String orderCurrencyCode;

	@Column(name = "shipping_name")
	private String shippingName;

	@Column(name = "billing_name")
	private String billingName;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "entity_id")
	private SalesFlatOrder salesFlatOrder;

	@Column(name = "pos")
	private int pos;

	@Column(name = "supplier_id")
	private String supplierId;

	@Column(name = "supplier_name")
	private String supplierName;

	@Transient
	private long count;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Transient
	private String address;

	public SalesFlatOrderGrid() {
		super();
	}

	public SalesFlatOrderGrid(long entityId, String status, long count) {
		this.entityId = entityId;
		this.status = status;
		this.count = count;
	}

	public SalesFlatOrderGrid(String incrementId, Date createdDate, String billingName, String shippingName,
			String status, double grandTotal, String address) {
		this.incrementId = incrementId;
		this.createdDate = createdDate;
		this.billingName = billingName;
		this.shippingName = shippingName;
		this.grandTotal = grandTotal;
		this.status = status;
		this.address = address;
	}
	
	public SalesFlatOrderGrid(String incrementId,String status,String billingName, String shippingName,Date createdDate,double grandTotal,String address) {
		this.incrementId = incrementId;
		this.status = status;
		this.billingName = billingName;
		this.shippingName = shippingName;
		this.createdDate = createdDate;
		this.grandTotal = grandTotal;
		this.address = address;
	}

}
