package com.calsoft.reports.vo;

import java.util.List;
import java.util.Map;

public class DataTable {
    private String tableName;
    private List<TableHeader> tableHeaders;
    private List<Map<String,String>> excelContents;
    private String tableHeaderTitle;
    private CellsStyle tableHeaderStyle;

    public DataTable() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableHeader> getTableHeaders() {
        return tableHeaders;
    }

    public void setTableHeaders(List<TableHeader> tableHeaders) {
        this.tableHeaders = tableHeaders;
    }

    public List<Map<String, String>> getExcelContents() {
        return excelContents;
    }

    public void setExcelContents(List<Map<String, String>> excelContents) {
        this.excelContents = excelContents;
    }

    public String getTableHeaderTitle() {
        return tableHeaderTitle;
    }

    public void setTableHeaderTitle(String tableHeaderTitle) {
        this.tableHeaderTitle = tableHeaderTitle;
    }

    public CellsStyle getTableHeaderStyle() {
        return tableHeaderStyle;
    }

    public void setTableHeaderStyle(CellsStyle tableHeaderStyle) {
        this.tableHeaderStyle = tableHeaderStyle;
    }
}
