package com.calsoft.reports.util;

public enum ReportType {
    PDF("PDF"),
    EXCEL("EXCEL");

    private String typeDescrption;

    ReportType(String typeDescrption){
        this.typeDescrption=typeDescrption;
    }

}
