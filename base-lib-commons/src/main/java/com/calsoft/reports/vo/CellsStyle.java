package com.calsoft.reports.vo;

import net.sf.jasperreports.engine.type.ColorEnum;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;

public class CellsStyle {
    private ColumnTypeEnum sheetHeaderEnum;
    private Boolean isBold;
    private int headerHeight;
    private double fontHeight;
    private ColorEnum colorEnum;
    private FontEnum fontEnum;
    private HSSFCellStyle hssfCellStyle;

    public CellsStyle() {
    }

    public ColumnTypeEnum getSheetHeaderEnum() {
        return sheetHeaderEnum;
    }

    public void setSheetHeaderEnum(ColumnTypeEnum sheetHeaderEnum) {
        this.sheetHeaderEnum = sheetHeaderEnum;
    }

    public Boolean getBold() {
        return isBold;
    }

    public void setBold(Boolean bold) {
        isBold = bold;
    }

    public int getHeaderHeight() {
        return headerHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public double getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(double fontHeight) {
        this.fontHeight = fontHeight;
    }

    public ColorEnum getColorEnum() {
        return colorEnum;
    }

    public void setColorEnum(ColorEnum colorEnum) {
        this.colorEnum = colorEnum;
    }

    public FontEnum getFontEnum() {
        return fontEnum;
    }

    public void setFontEnum(FontEnum fontEnum) {
        this.fontEnum = fontEnum;
    }

    public HSSFCellStyle getHssfCellStyle() {
        return hssfCellStyle;
    }

    public void setHssfCellStyle(HSSFCellStyle hssfCellStyle) {
        this.hssfCellStyle = hssfCellStyle;
    }
}
