package com.calsoft.pos.config;

public class OrderConfig {
	public static final String ORDER_COD_STATUS = "Pending";
	public static final String ORDER_INVOICE_STATUS = "Invoiced";
	public static final String ORDER_SHIPPMENT_STATUS = "Shipped";
	public static final String ORDER_CREDITMEMO_STATUS = "Refunded";
	public static final String ORDER_CANCEL_STATUS = "Cancelled";
	public static final String ORDER_PAYMENT_STATUS = "Approved";
	public static final String ORDER_PROCESSING_STATUS = "Processing";
	public static final String ORDER_PARTIALLY_SHIPPED_STATUS = "Partially Shipped";
	public static final String ORDER_CLOSED_STATUS = "Closed";
	public static final String ORDER_COMPLETED_STATUS = "Completed";
	
	public static final String ORDER_PARTIALLY_REFUNDED_STATUS = "Partially Refunded";
	
	public static final String CASH_REGISTER_TRANSACTION_TYPE_CREDIT = "credit";
	
	public static final String CASH_REGISTER_TRANSACTION_TYPE_DEBIT = "credit";
	
	public static final String CASH_REGISTER_TRANSACTION_TYPE_INITIAL = "initial";
	
	public static final String CASH_REGISTER_TRANSACTION_TYPE_SELL = "sell";
	
	public static final String CASH_REGISTER_TRANSACTION_TYPE_TRANSFER = "transfer";
	
	public static final String CASH_REGISTER_TRANSACTION_TYPE_REFUND = "refund";
	
}
