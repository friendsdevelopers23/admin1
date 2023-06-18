package com.calsoft.reports.util;

public enum ReportTemplate {
    ORDER("order.jrxml"),
    INVOICE("invoice.jrxml");

    private String templateName;

    ReportTemplate(String templateName){
        this.templateName=templateName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
