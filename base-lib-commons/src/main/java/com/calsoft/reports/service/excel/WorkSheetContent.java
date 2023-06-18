package com.calsoft.reports.service.excel;

import com.calsoft.reports.vo.CellsStyle;
import com.calsoft.reports.vo.DataTable;

import java.util.List;

public class WorkSheetContent {
    private static final int DEFAULT_VERTICAL_SPACE=1;
    private static final int DEFAULT_HORIZONTAL_SPACE=2;
    private String sheetName;
    private String sheetHeader;
    private List<DataTable> dataTables;
    private Integer verticalSpace;
    private Integer horizontalSpace;
    private String filterRange;
    private CellsStyle sheetHeaderStyle;

    public WorkSheetContent(List<DataTable> dataTables){
        this(Integer.valueOf(1),Integer.valueOf(2),dataTables);
    }
    public WorkSheetContent(Integer verticalSpace,Integer horizontalSpace,List<DataTable> dataTables){
        this.verticalSpace=verticalSpace;
        this.horizontalSpace=horizontalSpace;
        this.dataTables=dataTables;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getSheetHeader() {
        return sheetHeader;
    }

    public void setSheetHeader(String sheetHeader) {
        this.sheetHeader = sheetHeader;
    }

    public List<DataTable> getDataTables() {
        return dataTables;
    }

    public void setDataTables(List<DataTable> dataTables) {
        this.dataTables = dataTables;
    }

    public Integer getVerticalSpace() {
        return verticalSpace;
    }

    public void setVerticalSpace(Integer verticalSpace) {
        this.verticalSpace = verticalSpace;
    }

    public Integer getHorizontalSpace() {
        return horizontalSpace;
    }

    public void setHorizontalSpace(Integer horizontalSpace) {
        this.horizontalSpace = horizontalSpace;
    }

    public String getFilterRange() {
        return filterRange;
    }

    public void setFilterRange(String filterRange) {
        this.filterRange = filterRange;
    }

    public CellsStyle getSheetHeaderStyle() {
        return sheetHeaderStyle;
    }

    public void setSheetHeaderStyle(CellsStyle sheetHeaderStyle) {
        this.sheetHeaderStyle = sheetHeaderStyle;
    }
}
