package com.calsoft.pos.model.order;

import java.io.Serializable;

public class PaymentInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String PaypalPayerId;
	
	private String PaypalPayerEmail;
	
	private String payerStatus;
	
	private String paypalPaymentStatus;
	
	private String paypalAddressStatus;
	
	private String paypalPayerStatus;
	
	private String paymentCurrency;
	
	private Double dueAmount;
	
	private Double exChangeRate;

	public String getPaypalPayerId() {
		return PaypalPayerId;
	}

	public void setPaypalPayerId(String paypalPayerId) {
		PaypalPayerId = paypalPayerId;
	}

	public String getPaypalPayerEmail() {
		return PaypalPayerEmail;
	}

	public void setPaypalPayerEmail(String paypalPayerEmail) {
		PaypalPayerEmail = paypalPayerEmail;
	}

	public String getPayerStatus() {
		return payerStatus;
	}

	public void setPayerStatus(String payerStatus) {
		this.payerStatus = payerStatus;
	}

	public String getPaypalPaymentStatus() {
		return paypalPaymentStatus;
	}

	public void setPaypalPaymentStatus(String paypalPaymentStatus) {
		this.paypalPaymentStatus = paypalPaymentStatus;
	}

	public String getPaypalAddressStatus() {
		return paypalAddressStatus;
	}

	public void setPaypalAddressStatus(String paypalAddressStatus) {
		this.paypalAddressStatus = paypalAddressStatus;
	}

	public String getPaypalPayerStatus() {
		return paypalPayerStatus;
	}

	public void setPaypalPayerStatus(String paypalPayerStatus) {
		this.paypalPayerStatus = paypalPayerStatus;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Double getExChangeRate() {
		return exChangeRate;
	}

	public void setExChangeRate(Double exChangeRate) {
		this.exChangeRate = exChangeRate;
	}
	
	
	
	
	
	
	
	
}
