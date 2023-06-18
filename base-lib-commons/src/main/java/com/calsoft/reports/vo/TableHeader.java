package com.calsoft.reports.vo;

public class TableHeader {
    private String columnName;
    private String columnType;
    private Integer columnWidth;
    private Integer columnFormat;

    public TableHeader(){}

    public TableHeader(String columnName, String columnType, Integer columnWidth, Integer columnFormat) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnWidth = columnWidth;
        this.columnFormat = columnFormat;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public Integer getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(Integer columnWidth) {
        this.columnWidth = columnWidth;
    }

    public Integer getColumnFormat() {
        return columnFormat;
    }

    public void setColumnFormat(Integer columnFormat) {
        this.columnFormat = columnFormat;
    }
}
