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
@Table(name = "credit_debit_entry")
public class CreditDebitEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private long id;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time", updatable = false)
	private Date dateTime;

	@Column(name = "data_json")
	private String dataJson;

	@Column(name = "module_id")
	private long moduleId;

	@Column(name = "table_name")
	private String tableName;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "credit_amount")
	private float creditAmount;

	@Column(name = "debit_amount")
	private float debitAmount;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "bank_id")
	private int bankId;

	@Column(name = "module_type")
	private String moduleType;

	@Column(name = "approve_status")
	private int approveStatus;

	@Column(name = "approve_1_by")
	private int approve1By;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approve_1_date_time", updatable = false)
	private Date approve1DateTime;

	@Column(name = "approve_2_by")
	private int approve2By;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approve_2_date_time", updatable = false)
	private Date approve2DateTime;

	@Column(name = "approve_3_by")
	private int approve3By;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approve_3_date_time", updatable = false)
	private Date approve3DateTime;

	@Column(name = "disapprove_by")
	private int disapproveBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "disapprove_time", updatable = false)
	private Date disapproveTime;

	@Column(name = "from_warehouse")
	private int fromWarehouse;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;
}
