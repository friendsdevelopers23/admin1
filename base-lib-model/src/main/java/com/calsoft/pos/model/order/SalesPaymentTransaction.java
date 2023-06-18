package com.calsoft.pos.model.order;

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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "sales_payment_transaction")
public class SalesPaymentTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "parent_id", insertable = false, updatable = false)
	private Long parentId;

	@Column(name = "order_id", insertable = false, updatable = false)
	private Long orderId;

	@Column(name = "payment_id", insertable = false, updatable = false)
	private Long paymentId;

	@Column(name = "txn_id")
	private String txnId;

	@Column(name = "parent_txn_id")
	private String parentTxnId;

	@Column(name = "txn_type")
	private String txnType;

	@Column(name = "is_closed")
	private int isClosed;

	@Lob
	@Column(name = "additional_information", columnDefinition = "BLOB")
	private byte[] additionalInformation;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "payment_id")
	@JsonBackReference(value = "salesFlatOrderPayment")
	private SalesFlatOrderPayment salesFlatOrderPayment;

	@OneToMany(mappedBy = "parentTransAction", fetch = FetchType.EAGER)
	@JsonManagedReference(value = "parentTransAction")
	@NotFound(action = NotFoundAction.IGNORE)
	private List<SalesPaymentTransaction> salesTransaction = new ArrayList<SalesPaymentTransaction>();

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonBackReference(value = "parentTransAction")
	private SalesPaymentTransaction parentTransAction;

	/*
	 * @JsonBackReference(value = "salesFlatOrder")
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "order_id")
	 * 
	 * @NotFound(action = NotFoundAction.IGNORE) private SalesFlatOrder
	 * salesFlatOrder;
	 */

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public long getOrderId() {
		return this.salesFlatOrderPayment.getSalesFlatOrder().getEntityId();
	}

	public void setOrderId(long orderId) {
		System.out.println(this.orderId + this.salesFlatOrderPayment.getSalesFlatOrder().getEntityId());
		this.orderId = this.salesFlatOrderPayment.getSalesFlatOrder().getEntityId();
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getParentTxnId() {
		return parentTxnId;
	}

	public void setParentTxnId(String parentTxnId) {
		this.parentTxnId = parentTxnId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public int getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(int isClosed) {
		this.isClosed = isClosed;
	}

	public byte[] getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(byte[] additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public SalesFlatOrderPayment getSalesFlatOrderPayment() {
		return salesFlatOrderPayment;
	}

	public void setSalesFlatOrderPayment(SalesFlatOrderPayment salesFlatOrderPayment) {
		this.salesFlatOrderPayment = salesFlatOrderPayment;
	}

	public List<SalesPaymentTransaction> getSalesTransaction() {
		return salesTransaction;
	}

	public void setSalesTransaction(List<SalesPaymentTransaction> salesTransaction) {
		this.salesTransaction = salesTransaction;
	}

	public SalesPaymentTransaction getParentTransAction() {
		return parentTransAction;
	}

	public void setParentTransAction(SalesPaymentTransaction parentTransAction) {
		this.parentTransAction = parentTransAction;
	}

	/*
	 * public SalesFlatOrder getSalesFlatOrder() { return salesFlatOrder; }
	 * 
	 * public void setSalesFlatOrder(SalesFlatOrder salesFlatOrder) {
	 * this.salesFlatOrder = salesFlatOrder; }
	 */

	/*
	 * public SalesFlatOrder getSalesFlatOrder() { return salesFlatOrder; }
	 * 
	 * public void setSalesFlatOrder(SalesFlatOrder salesFlatOrder) {
	 * this.salesFlatOrder = salesFlatOrder; }
	 */

}
