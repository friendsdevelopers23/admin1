package com.calsoft.pos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "expenses")
public class ExpensesModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", updatable = false)
	private Date date;

	@Column(name = "reference")
	private String reference;

	@Column(name = "amount")
	private double amount;

	@Column(name = "note")
	private String note;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "attachment")
	private String attachment;

	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "warehouse_id")
	private int warehouseId;

	@Column(name = "paid_by")
	private String paidBy;

	@Column(name = "paid_bank_id")
	private int paidBankId;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
