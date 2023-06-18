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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "cash_registers")
@Data
public class CashRegister implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "business_id")
	private Long businessId;

	@Column(name = "location_id")
	private Long locationId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "status")
	private int status;

	@Column(name = "closed_at")
	private Date closedAt;

	@Column(name = "closing_amount")
	private double closingAmount;

	@Column(name = "total_card_slips")
	private Long totalCardSlips;

	@Column(name = "total_cheques")
	private Long totalCheques;

	@Column(name = "denominations")
	private String denominations;

	@Column(name = "closing_note")
	private String closingNote;
	
	@Column(name = "total_sale_amount")
	private Double totalSaleAmount;
	
	@Column(name = "total_cash_in_hand")
	private Double totalCashInHand;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedDate;

	@OneToMany(mappedBy = "cashRegister", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CashRegisterTransactions> cashRegisterTransactions = new ArrayList<CashRegisterTransactions>();
	
	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;
	
	@Transient
	private String operationType;

}
