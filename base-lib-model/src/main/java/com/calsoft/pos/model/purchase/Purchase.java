package com.calsoft.pos.model.purchase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.calsoft.pos.config.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Entity
@Table(name = "purchase")
@Data
public class Purchase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "reference_no")
	private String referenceNo;

	@Column(name = "supplier_id")
	private long supplierId;

	@Column(name = "supplier")
	private String supplier;

	@Column(name = "warehouse_id")
	private Long warehouseId;

	@Column(name = "note")
	private String note;

	@Column(name = "total")
	private double total;

	@Column(name = "product_discount")
	private double productDiscount;

	@Column(name = "order_discount_id")
	private String orderDiscountId;

	@Column(name = "order_discount")
	private double orderDiscount;

	@Column(name = "total_discount")
	private double totalDiscount;

	@Column(name = "product_tax")
	private double productTax;

	@Column(name = "order_tax_id")
	private Long orderTaxId;

	@Column(name = "order_tax")
	private double orderTax;

	@Column(name = "total_tax")
	private double totalTax;

	@Column(name = "shipping")
	private double shipping;

	@Column(name = "grand_total")
	private double grandTotal;

	@Column(name = "paid")
	private double paid;

	@Column(name = "status")
	private String status;

	@Column(name = "payment_status")
	private String paymentStatus;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@Column(name = "attachment")
	private String attachment;

	@Column(name = "payment_term")
	private int paymentTerm;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "due_date")
	private Date dueDate;

	@Column(name = "return_id")
	private int returnId;

	@Column(name = "surcharge")
	private double surcharge;

	@Column(name = "return_purchase_ref")
	private String returnPurchaseRef;

	@Column(name = "purchase_id")
	private String purchaseId;

	@Column(name = "return_purchase_total")
	private double returnPurchaseTotal;

	@Column(name = "cgst")
	private Double cgst;

	@Column(name = "sgst")
	private Double sgst;

	@Column(name = "igst")
	private Double igst;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

	@Transient
	private String operationType;

	@JsonSerialize(using = CustomDateSerializer.class)
	@Column(name = "date")
	private Date date;

	@OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<PurchaseItems> purchaseItems = new ArrayList<PurchaseItems>();
	
	@Transient
	private List<Long> removedId = new ArrayList<Long>();

	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@Column(name = "updated_by")
	private Long updatedBy;
}
