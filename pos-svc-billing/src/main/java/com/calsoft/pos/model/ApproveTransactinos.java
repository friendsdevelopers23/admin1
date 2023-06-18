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
@Table(name = "approve_transactinos")
public class ApproveTransactinos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private long id;

	@Column(name = "module_name")
	private String moduleName;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time", updatable = false)
	private Date dateTime;

	@Column(name = "expense_id")
	private int expenseId;

	@Column(name = "purchase_id")
	private int purchaseId;

	@Column(name = "purchase_payment_id")
	private int purchasePaymentId;

	@Column(name = "sale_id")
	private int saleId;

	@Column(name = "suspense_id")
	private int suspenseId;

	@Column(name = "warehouse_id")
	private int warehouseId;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "description")
	private String description;

	@Column(name = "credit_amount")
	private double creditAmount;

	@Column(name = "debit_amount")
	private double debitAmount;

	@Column(name = "payment_type")
	private String paymentType;

	@Column(name = "bank_id")
	private int bankId;

	@Column(name = "warehouse_transaction_id")
	private int warehouseTransactionId;

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

	@Column(name = "approve_status")
	private int approveStatus;

	@Column(name = "module_ref")
	private String moduleRef;

	@Column(name = "related_transaction_id")
	private int relatedTransactionId;

	@Column(name = "transaction_type")
	private int transactionType;

	@Column(name = "from_bank_id")
	private int fromBankId;

	@Column(name = "to_bank_id")
	private int toBankId;

	@Column(name = "from_warehouse_id")
	private int fromWarehouseId;

	@Column(name = "to_warehouse_id")
	private int toWarehouseId;

	@JsonIgnore
	@JsonIgnoreProperties
	@Column(name = "tenant_id", updatable = false)
	private String tenantId;

}
