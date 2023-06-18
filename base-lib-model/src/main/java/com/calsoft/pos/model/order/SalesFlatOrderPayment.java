package com.calsoft.pos.model.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sales_flat_order_payment")
@Getter
@Setter
public class SalesFlatOrderPayment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private Long entityId;

	@Column(nullable = true, name = "base_shipping_captured")
	private Double baseShippingCaptured;

	@Column(nullable = true, name = "shipping_captured")
	private Double shippingCaptured;

	@Column(nullable = true, name = "amount_refunded")
	private Double amountRefunded;

	@Column(nullable = true, name = "base_amount_paid")
	private Double baseAmountPaid;

	@Column(nullable = true, name = "amount_canceled")
	private Double amountCanceled;

	@Column(nullable = true, name = "base_amount_authorized")
	private Double baseAmountAuthorized;

	@Column(nullable = true, name = "base_amount_paid_online")
	private Double baseAmountPaidOnline;

	@Column(nullable = true, name = "base_amount_refunded_online")
	private Double baseAmountRefundedOnline;

	@Column(name = "base_shipping_amount")
	private double baseShippingAmount;

	@Column(name = "shipping_amount")
	private double shippingAmount;

	@Column(nullable = true, name = "amount_paid")
	private Double amountPaid;

	@Column(nullable = true, name = "amount_authorized")
	private Double amountAuthorized;

	@Column(name = "base_amount_ordered")
	private double baseAmountOrdered;

	@Column(nullable = true, name = "base_shipping_refunded")
	private Double baseShippingRefunded;

	@Column(nullable = true, name = "shipping_refunded")
	private Double shippingRefunded;

	@Column(nullable = true, name = "base_amount_refunded")
	private Double baseAmountRefunded;

	@Column(name = "amount_ordered")
	private double amountOrdered;

	@Column(nullable = true, name = "base_amount_canceled")
	private Double baseAmountCanceled;

	@Column(nullable = true, name = "quote_payment_id")
	private Long quotePaymentId;

	@Column(name = "additional_data")
	private String additionalData;

	@Column(name = "cc_exp_month")
	private String ccExpMonth;

	@Column(name = "cc_ss_start_year")
	private String ccSsStartYear;

	@Column(name = "echeck_bank_name")
	private String echeckBankName;

	@Column(name = "method")
	private String method;

	@Column(name = "cc_debug_request_body")
	private String ccDebugRequestBody;

	@Column(name = "cc_secure_verify")
	private String ccSecureVerify;

	@Column(name = "protection_eligibility")
	private String protectionEligibility;

	@Column(name = "cc_approval")
	private String ccApproval;

	@Column(name = "cc_last4")
	private String ccLast4;

	@Column(name = "cc_status_description")
	private String ccStatusDescription;

	@Column(name = "echeck_type")
	private String echeckType;

	@Column(name = "cc_debug_response_serialized")
	private String ccDebugResponseSerialized;

	@Column(name = "cc_ss_start_month")
	private String ccSsStartMonth;

	@Column(name = "echeck_account_type")
	private String echeckAccountType;

	@Column(name = "last_trans_id")
	private String lastTransId;

	@Column(name = "cc_cid_status")
	private String ccCidStatus;

	@Column(name = "cc_owner")
	private String ccOwner;

	@Column(name = "cc_type")
	private String ccType;

	@Column(name = "po_number")
	private String poNumber;

	@Column(name = "cc_exp_year")
	private String ccExpYear;

	@Column(name = "cc_status")
	private String ccStatus;

	@Column(name = "echeck_routing_number")
	private String echeckRoutingNumber;

	@Column(name = "account_status")
	private String accountStatus;

	@Column(name = "anet_trans_method")
	private String anetTransMethod;

	@Column(name = "cc_debug_response_body")
	private String ccDebugResponseBody;

	@Column(name = "cc_ss_issue")
	private String ccSsIssue;

	@Column(name = "echeck_account_name")
	private String echeckAccountName;

	@Column(name = "cc_avs_status")
	private String ccAvsStatus;

	@Column(name = "cc_number_enc")
	private String ccNumberEnc;

	@Column(name = "cc_trans_id")
	private String ccTransId;

	@Column(name = "paybox_request_number")
	private String payboxRequestNumber;

	@Column(name = "address_status")
	private String addressStatus;

	@Column(name = "additional_information")
	private String additionalInformation;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "parent_id",nullable = true)
	private SalesFlatOrder salesFlatOrder;

	/*
	 * @OneToMany(mappedBy = "salesFlatOrderPayment", cascade = CascadeType.ALL)
	 * 
	 * @JsonManagedReference(value = "salesFlatOrderPayment") private
	 * List<SalesPaymentTransaction> salesPaymentTransaction = new
	 * ArrayList<SalesPaymentTransaction>();
	 */

	@Transient
	private PaymentInformation paymentInformation;

}
