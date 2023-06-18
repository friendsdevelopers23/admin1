package com.calsoft.pos.model.invoice;

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
@Table(name = "sales_flat_invoice_grid")
@Data
public class SalesFlatInvoiceGrid implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "entity_id", unique = true, nullable = false)
	private long entityId;

	@Column(name = "store_id")
	private Long storeId;

	@Column(name = "base_grand_total")
	private Double baseGrandTotal;

	@Column(name = "grand_total")
	private Double grandTotal;

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "state")
	private Long state;

	@Column(name = "store_currency_code")
	private String storeCurrencyCode;

	@Column(name = "order_currency_code")
	private String orderCurrencyCode;

	@Column(name = "base_currency_code")
	private String baseCurrencyCode;

	@Column(name = "global_currency_code")
	private String globalCurrencyCode;

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

	@Column(name = "billing_name")
	private String billingName;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "entity_id")
	private SalesFlatInvoice salesFlatInvoice;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id",updatable=false)
	private String tenantId;
	
	@Column(name = "supplier_id")
	private String supplierId;

	@Column(name = "supplier_name")
	private String supplierName;

}
