package com.calsoft.pos.model;

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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name = "returns")
public class Returns implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "returns_id")
	private Long returnsId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", updatable = false)
	private Date date;

	@Column(name = "reference_no")
	private String referenceNo;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "customer")
	private String customer;

	@Column(name = "biller_id")
	private Long billerId;

	@Column(name = "biller")
	private String biller;

	@Column(name = "warehouse_id")
	private Long warehouseId;

	@Column(name = "note")
	private String note;

	@Column(name = "staff_note")
	private String staffNote;

	@Column(name = "total")
	private Double total;

	@Column(name = "product_discount")
	private Double productDiscount;

	@Column(name = "order_discount_id")
	private String orderDiscountId;

	@Column(name = "total_discount")
	private Double totalDiscount;

	@Column(name = "order_discount")
	private Double orderDiscount;

	@Column(name = "product_tax")
	private Double productTax;

	@Column(name = "order_tax_id")
	private Long orderTaxId;

	@Column(name = "order_tax")
	private Double orderTax;

	@Column(name = "total_tax")
	private Double totalTax;

	@Column(name = "grand_total")
	private Double grandTotal;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_by")
	private Long updatedBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", updatable = false)
	private Date updatedAt;

	@Column(name = "total_items")
	private Long totalItems;

	@Column(name = "paid")
	private Double paid;

	@Column(name = "surcharge")
	private Double surcharge;

	@Column(name = "attachment")
	private String attachment;

	@Column(name = "hash")
	private String hash;

	@Column(name = "cgst")
	private Double cgst;

	@Column(name = "sgst")
	private Double sgst;

	@Column(name = "igst")
	private Double igst;

	@Column(name = "shipping")
	private Double shipping;

//	@OneToMany(mappedBy = "returns", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonManagedReference
//	private List<ReturnItems> returnItems = new ArrayList<ReturnItems>();

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;
}
