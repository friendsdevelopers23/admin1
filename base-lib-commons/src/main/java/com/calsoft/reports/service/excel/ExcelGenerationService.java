package com.calsoft.reports.service.excel;

import com.calsoft.reports.service.ReportService;
import com.calsoft.reports.vo.*;
import net.sf.jasperreports.engine.type.ColorEnum;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import static net.sf.jasperreports.engine.JasperCompileManager.compileReport;

@Service("excel")
public class ExcelGenerationService implements ReportService {
    Logger logger = LoggerFactory.getLogger(ExcelGenerationService.class);

    public byte[] generate(ReportVO reportVO) {
        try {
            final List<WorkSheetContent> workSheetContentList = reportVO.getWorkSheetContentList();
            if (!CollectionUtils.isEmpty(workSheetContentList)) {
                return generateWorkSheet(workSheetContentList).toByteArray();
            }
        } catch (Exception e) {
            logger.error("Exception while trying to generate  :: ", e);
        }
        return null;
    }

    @Override
    public byte[] downloadTemplateFile(String fileName) {
        ByteArrayOutputStream responseContent = new ByteArrayOutputStream();
        try {
            InputStream resource = ExcelGenerationService.class.getClassLoader().getResourceAsStream(fileName);
            if (resource == null) {
                throw new IllegalArgumentException("file is not found!");
            }
            HSSFWorkbook workbook = new HSSFWorkbook(resource);
            workbook.write(responseContent);
            responseContent.flush();
        } catch (Exception touch) {
            logger.error("Exception while trying to write excel content ", touch);
        }
        return responseContent.toByteArray();
    }
    
    @Override
    public boolean verifyTemplate(Map<String, String> pincodeColMap, Map<String, String> map) {
		HashMap<String, String> newMap = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {

			if (!newMap.containsKey(entry.getKey()))

				newMap.put(entry.getKey().split("#")[1], entry.getValue());
		}
		if (pincodeColMap.size() == newMap.size()) {
			if (newMap.keySet().equals(pincodeColMap.keySet())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

    @Override
    public List<Map<String, String>> uploadFile(MultipartFile newsletterFile, Map<String, String> newsLetterFieldMap) {
        logger.info("Uploading initiated for file {}...",newsletterFile.getName());
        List<Map<String, String>> contents = new ArrayList<>();
        Map<String, String> sheetcontent = new HashMap<>();
        contents.add(sheetcontent);
        try {
            InputStream inStream = newsletterFile.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(inStream);
            final HSSFSheet sheetAt = workbook.getSheetAt(0);
            final Iterator<Row> rowIterator = sheetAt.iterator();
            Map<Integer, String> colIndex = new HashMap<Integer, String>();
            while (rowIterator.hasNext()) {

                HSSFRow currentRow = (HSSFRow) rowIterator.next();
                if (currentRow.getRowNum() == 0) {
                    continue;
                } else {
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        if (currentRow.getRowNum() == 1) {
                            colIndex.put(currentCell.getColumnIndex(), currentCell.getStringCellValue());
                        } else {
                            if (currentCell.getCellType().getCode() == CellType.STRING.getCode()) {
                                sheetcontent.put(currentRow.getRowNum()+"#"+colIndex.get(currentCell.getColumnIndex()), currentCell.getStringCellValue());
                            } else if (currentCell.getCellType().getCode() == CellType.NUMERIC.getCode()) {
                                sheetcontent.put(currentRow.getRowNum()+"#"+colIndex.get(currentCell.getColumnIndex()), String.valueOf(currentCell.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
            sheetAt.iterator();
        } catch (Exception io) {
            logger.error("Exception while trying to read file ", io);
        }
        logger.info("Rows Parsed from File {}",sheetcontent.size());
        return contents;
    }

    private ByteArrayOutputStream generateWorkSheet(List<WorkSheetContent> workSheetContentList) {
        ByteArrayOutputStream responseContent = null;
        HSSFWorkbook multiSheetMultiTableWorkBook = new HSSFWorkbook();
        HSSFSheet workSheet = null;
        try {
            responseContent = new ByteArrayOutputStream();
            final Iterator<WorkSheetContent> itr = workSheetContentList.iterator();
            while (itr.hasNext()) {
                WorkSheetContent workSheetContent = (WorkSheetContent) itr.next();
                workSheet = multiSheetMultiTableWorkBook.createSheet(workSheetContent.getSheetName());
                int rownum = 1;
                String sheetHeader = workSheetContent.getSheetHeader();
                int verticalSpace = workSheetContent.getVerticalSpace().intValue();
                int horizontalSpace = workSheetContent.getHorizontalSpace().intValue();
                Boolean firstDataTable = Boolean.valueOf(true);
                Boolean firstDataTableHeader = Boolean.valueOf(true);
                Boolean firstTable = Boolean.valueOf(true);
                Boolean setSheetHeader = Boolean.valueOf(true);

                label520:
                for (Iterator itr2 = workSheetContent.getDataTables().iterator(); itr2.hasNext(); firstDataTable = Boolean.valueOf(false)) {
                    DataTable dataTable = (DataTable) itr2.next();
                    List headers = new ArrayList();
                    List widths = new ArrayList();
                    List columnType = new ArrayList();
                    String tableHeaderTitle = dataTable.getTableHeaderTitle();
                    Iterator itr3 = dataTable.getTableHeaders().iterator();
                    while (itr3.hasNext()) {
                        TableHeader header = (TableHeader) itr3.next();
                        headers.add(header.getColumnName());
                        widths.add(header.getColumnWidth());
                        columnType.add(header.getColumnType());
                    }
                    String filterRange = workSheetContent.getFilterRange();
                    this.setWorkSheetStyle(workSheet, widths, filterRange, verticalSpace);
                    if (workSheetContent.getSheetHeaderStyle() != null && workSheetContent.getSheetHeader() != null && setSheetHeader.booleanValue()) {
                        this.setWorkSheetHeader(sheetHeader, multiSheetMultiTableWorkBook, workSheet, headers, verticalSpace, workSheetContent.getSheetHeaderStyle());
                    } else if (workSheetContent.getSheetHeader() == null && firstDataTable.booleanValue()) {
                        rownum = 0;
                    }
                    setSheetHeader = Boolean.valueOf(false);
                    if (dataTable.getTableHeaderStyle() != null && dataTable.getTableHeaderTitle() != null) {
                        this.setTableHeader(tableHeaderTitle, multiSheetMultiTableWorkBook, workSheet, headers, rownum, horizontalSpace, verticalSpace, dataTable.getTableHeaderStyle(), firstDataTable);
                    } else if (workSheetContent.getSheetHeader() == null && firstDataTable.booleanValue()) {
                        --rownum;
                    } else if (firstDataTable.booleanValue()) {
                        rownum = 0;
                    } else if (!firstDataTable.booleanValue()) {
                        --rownum;
                    }
                    if (headers.size() > 0 && dataTable.getExcelContents() != null && !dataTable.getExcelContents().isEmpty()) {
                        rownum = this.writeExcelHeader(multiSheetMultiTableWorkBook, workSheet, headers, rownum, horizontalSpace, verticalSpace, firstDataTableHeader);
                    }
                    firstDataTable = Boolean.valueOf(false);
                    firstDataTableHeader = Boolean.valueOf(false);
                    CreationHelper createHelper = multiSheetMultiTableWorkBook.getCreationHelper();
                    HSSFCellStyle cellStyle = (HSSFCellStyle) multiSheetMultiTableWorkBook.createCellStyle();
                    String columnTypeHeader = null;
                    short dateFormat = createHelper.createDataFormat().getFormat("MMM dd, yyyy");
                    CellStyle style = this.getCellStyle(multiSheetMultiTableWorkBook);
                    Iterator itr4 = dataTable.getExcelContents().iterator();
                    while (true) {
                        Map excelContent;
                        int i;
                        do {
                            do {
                                if (!itr4.hasNext()) {
                                    if (firstDataTable.booleanValue()) {
                                        ++rownum;
                                    } else {
                                        ++rownum;
                                    }
                                    continue label520;
                                }
                                excelContent = (Map) itr4.next();
                                i = 0;
                            } while (excelContent == null);
                        } while (headers.size() <= 0);
                        HSSFRow row;
                        if (firstDataTable.booleanValue()) {
                            ++rownum;
                            row = (HSSFRow) workSheet.createRow(rownum);
                        } else {
                            ++rownum;
                            row = (HSSFRow) workSheet.createRow(rownum);
                        }
                        int columnTypeIndex = 0;
                        HSSFCell cell = null;
                        for (Iterator itr5 = headers.iterator(); itr5.hasNext(); cell.setCellStyle(style)) {
                            String key = (String) itr5.next();
                            columnTypeHeader = (String) columnType.get(columnTypeIndex);
                            ++columnTypeIndex;
                            cell = (HSSFCell) row.createCell(i++ + verticalSpace);
                            if (columnTypeHeader.equalsIgnoreCase(ColumnTypeEnum.DOUBLE.toString())) {
                                cell.setCellValue((new Double((String) excelContent.get(key))).doubleValue());
                            } else if (columnTypeHeader.equalsIgnoreCase(ColumnTypeEnum.INTEGER.toString())) {
                                cell.setCellValue((double) (new Integer((String) excelContent.get(key))).intValue());
                            } else if (columnTypeHeader.equalsIgnoreCase(ColumnTypeEnum.DATE.toString())) {
                                cellStyle.setDataFormat(dateFormat);
                                cell.setCellValue((String) excelContent.get(key));
                                cell.setCellStyle(cellStyle);
                            } else {
                                cell.setCellValue((String) excelContent.get(key));
                            }
                        }
                    }
                }
            }
            multiSheetMultiTableWorkBook.write(responseContent);
            responseContent.flush();
            return responseContent;
        } catch (Exception touch) {
            logger.error("Exception while trying to write excel content ", touch);
        }
        return null;
    }

    private CellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    private int writeExcelHeader(HSSFWorkbook multiSheetMultiTableWorkBook, HSSFSheet
            workSheet, List<String> excelHeaders, int rownum, int horizontalSpace, int verticalSpace, Boolean firstTable) {
        HSSFRow rowTableHeading = null;
        if (firstTable.booleanValue()) {
            rowTableHeading = (HSSFRow) workSheet.createRow(rownum + 1);
        } else {
            rowTableHeading = (HSSFRow) workSheet.createRow(rownum + horizontalSpace + 1);
        }
        rowTableHeading.setHeight((short) 512);
        HSSFFont font = (HSSFFont) multiSheetMultiTableWorkBook.createFont();
        font.setFontName("Calibri");
        font.setColor((short) 255);
        font.setBold(true);
        HSSFCellStyle cellStyle = (HSSFCellStyle) multiSheetMultiTableWorkBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(true);
        cellStyle.setFont(font);
        for (int cellNo = 0; cellNo < excelHeaders.size(); ++cellNo) {
            HSSFCell headerCell = (HSSFCell) rowTableHeading.createCell(cellNo + verticalSpace);
            headerCell.setCellValue((String) excelHeaders.get(cellNo));
            headerCell.setCellStyle(cellStyle);
        }
        return rowTableHeading.getRowNum();
    }

    private void setTableHeader(String tableHeader, HSSFWorkbook multiSheetMultiTableWorkBook, HSSFSheet
            workSheet, List<String> excelHeader, int rownum, int horizontalSpace, int verticalSpace, CellsStyle
                                        tableHeaderStyle, Boolean firstDataTable) {
        HSSFRow rowSheetHeader;
        int mergedRow;
        if (firstDataTable.booleanValue()) {
            rowSheetHeader = (HSSFRow) workSheet.createRow(rownum);
            mergedRow = rownum;
        } else {
            rowSheetHeader = (HSSFRow) workSheet.createRow(rownum + horizontalSpace);
            mergedRow = rownum + horizontalSpace;
        }
        HSSFFont font = (HSSFFont) multiSheetMultiTableWorkBook.createFont();
        if (tableHeaderStyle.getFontEnum().toString() != null) {
            font.setFontName(tableHeaderStyle.getFontEnum().toString());
        }
        if (tableHeaderStyle.getBold() != null) {
            font.setBold(tableHeaderStyle.getBold().booleanValue());
        }
        if (tableHeaderStyle.getFontHeight() > 0.0D) {
            font.setFontHeight((short) tableHeaderStyle.getFontHeight());
        }
        if (tableHeaderStyle.getColorEnum() != null) {
            this.setFontColor(tableHeaderStyle.getColorEnum().toString(), font);
        }
        workSheet.addMergedRegion(new CellRangeAddress(mergedRow, mergedRow, verticalSpace, excelHeader.size() + verticalSpace - 1));
        if (tableHeaderStyle.getHeaderHeight() > 0) {
            rowSheetHeader.setHeight((short) tableHeaderStyle.getHeaderHeight());
        }
        HSSFCellStyle cellStyle = (HSSFCellStyle) multiSheetMultiTableWorkBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.forInt(1));
        cellStyle.setWrapText(true);
        cellStyle.setFont(font);
        HSSFCell headerCell = (HSSFCell) rowSheetHeader.createCell(0 + verticalSpace);
        headerCell.setCellValue(tableHeader);
        headerCell.setCellStyle(cellStyle);
    }

    private void setWorkSheetStyle(HSSFSheet workSheet, List<Integer> columnWidth, String filterRange,
                                   int verticalSpace) {
        workSheet.setPrintGridlines(true);
        workSheet.setDisplayGridlines(true);
        workSheet.setDefaultRowHeight((short) 373);
        if (filterRange != null && !filterRange.isEmpty()) {
            workSheet.setAutoFilter(CellRangeAddress.valueOf(filterRange));
        }
        if (columnWidth != null && columnWidth.size() > 0) {
            for (int i = 0; i < columnWidth.size(); i++) {
                workSheet.setColumnWidth(i + verticalSpace, columnWidth.get(i) != null ? ((Integer) columnWidth.get(i).intValue()) : 2500);
            }
        } else {
            workSheet.setDefaultColumnWidth(6400);
        }
    }

    private void setWorkSheetHeader(String sheetHeader, HSSFWorkbook multiSheetMultiTableWorkBook, HSSFSheet
            workSheet, List<String> excelHeaders, int verticalSpace, CellsStyle sheetHeaderStyle) {
        HSSFRow rowSheetHeader = (HSSFRow) workSheet.createRow(0);
        HSSFFont font = (HSSFFont) multiSheetMultiTableWorkBook.createFont();
        if (sheetHeaderStyle.getFontEnum() != null) {
            font.setFontName(sheetHeaderStyle.getFontEnum().toString());
        }
        if (sheetHeaderStyle.getBold() != null) {
            font.setBold(sheetHeaderStyle.getBold().booleanValue());
        }
        if (sheetHeaderStyle.getFontHeight() > 0.0D) {
            font.setFontHeightInPoints((short) sheetHeaderStyle.getFontHeight());
        }
        if (sheetHeaderStyle.getColorEnum() != null) {
            String sheetHeaderColor = sheetHeaderStyle.getColorEnum().toString();
            this.setFontColor(sheetHeaderColor, font);
        }
        workSheet.addMergedRegion(new CellRangeAddress(0, 0, verticalSpace, excelHeaders.size() + verticalSpace - 1));
        if (sheetHeaderStyle.getHeaderHeight() > 0) {
            rowSheetHeader.setHeight((short) sheetHeaderStyle.getHeaderHeight());
        }
        HSSFCellStyle cellStyle = (HSSFCellStyle) multiSheetMultiTableWorkBook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.forInt(1));
        cellStyle.setWrapText(true);
        cellStyle.setFont(font);
        HSSFCell headerCell = (HSSFCell) rowSheetHeader.createCell(0 + verticalSpace);
        headerCell.setCellValue(sheetHeader);
        headerCell.setCellStyle(cellStyle);
    }

    private void setFontColor(String sheetHeaderColor, HSSFFont font) {
        if (sheetHeaderColor.equalsIgnoreCase(ColorEnum.RED.toString())) {
            font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        } else if (sheetHeaderColor.equalsIgnoreCase(ColorEnum.BLACK.toString())) {
            font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        } else if (sheetHeaderColor.equalsIgnoreCase(ColorEnum.BLUE.toString())) {
            font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        } else if (sheetHeaderColor.equalsIgnoreCase(ColorEnum.GREEN.toString())) {
            font.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        } else {
            font.setColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
        }
    }

    @Override
	public List<Map<String, String>> uploadFileNewExcel(MultipartFile newsletterFile,
			Map<String, String> newsLetterFieldMap) {
		logger.info("Uploading initiated for file {}...", newsletterFile.getName());
		List<Map<String, String>> contents = new ArrayList<>();
		Map<String, String> sheetcontent = new HashMap<>();
		contents.add(sheetcontent);
		try {
			InputStream inStream = newsletterFile.getInputStream();
			HSSFWorkbook workbook = new HSSFWorkbook(inStream);
			final HSSFSheet sheetAt = workbook.getSheetAt(0);
			final Iterator<Row> rowIterator = sheetAt.iterator();
			Map<Integer, String> colIndex = new HashMap<Integer, String>();
			while (rowIterator.hasNext()) {

				HSSFRow currentRow = (HSSFRow) rowIterator.next();

				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentRow.getRowNum() == 0) {
						colIndex.put(currentCell.getColumnIndex(), currentCell.getStringCellValue());
					} else {
						if (currentCell.getCellType().getCode() == CellType.STRING.getCode()) {
							sheetcontent.put(currentRow.getRowNum() + "#" + colIndex.get(currentCell.getColumnIndex()),
									currentCell.getStringCellValue());
						} else if (currentCell.getCellType().getCode() == CellType.NUMERIC.getCode()) {
							sheetcontent.put(currentRow.getRowNum() + "#" + colIndex.get(currentCell.getColumnIndex()),
									String.valueOf(currentCell.getNumericCellValue()));
						}
					}
				}

			}
			sheetAt.iterator();
		} catch (Exception io) {
			logger.error("Exception while trying to read file ", io);
		}
		logger.info("Rows Parsed from File {}", sheetcontent.size());
		return contents;
	}


}
