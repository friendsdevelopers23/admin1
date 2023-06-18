package com.calsoft.notification.util;

public enum MailTemplate {
	USERVERIFICATION("userverification"), FORGOTPASSWORD("forgotpassword"),

	NOTIFICATION("notify"), ORDER("orders"),
	
	
	ORDERINVOICE("orderInvoice");

	private String templateName;

	MailTemplate(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
}
