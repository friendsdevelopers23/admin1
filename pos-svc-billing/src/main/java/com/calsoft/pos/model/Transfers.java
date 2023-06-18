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
@Table(name = "transfers")
public class Transfers implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transfer_id")
	private Long transferId;

	@Column(name = "transfer_no")
	private String transferNo;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", updatable = false)
	private Date date;

	@Column(name = "from_warehouse_id")
	private Long fromWarehouseId;

	@Column(name = "from_warehouse_code")
	private String fromWarehouseCode;

	@Column(name = "from_warehouse_name")
	private String fromWarehouseName;

	@Column(name = "to_warehouse_id")
	private Long toWarehouseId;

	@Column(name = "to_warehouse_code")
	private String toWarehouseCode;

	@Column(name = "to_warehouse_name")
	private String toWarehouseName;

	@Column(name = "note")
	private String note;

	@Column(name = "total")
	private Double total;

	@Column(name = "total_tax")
	private Double totalTax;

	@Column(name = "grand_total")
	private Double grandTotal;

	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@Column(name = "status")
	private String status;

	@Column(name = "shipping")
	private Double shipping;

	@Column(name = "attachment")
	private String attachment;

	@OneToMany(mappedBy = "transfers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<TransferItems> transferItems = new ArrayList<TransferItems>();

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

}
