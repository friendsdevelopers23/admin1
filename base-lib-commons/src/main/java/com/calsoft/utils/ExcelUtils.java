package com.calsoft.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.calsoft.pos.model.CatalogsearchQuery;
import com.calsoft.pos.model.NewsletterSubscriber;
import com.calsoft.pos.model.ShippingTablerate;
import com.calsoft.pos.model.audit.AuditVO;
import com.calsoft.pos.model.creditmemo.SalesFlatCreditmemoGrid;
import com.calsoft.pos.model.currency.DirectoryCurrencyRate;
import com.calsoft.pos.model.customerindex.CustomerIDX;
import com.calsoft.pos.model.invoice.SalesFlatInvoiceGrid;
import com.calsoft.pos.model.order.SalesFlatOrderGrid;
import com.calsoft.pos.model.shipment.SalesFlatShipmentGrid;
import com.calsoft.reports.service.excel.WorkSheetContent;
import com.calsoft.reports.vo.CellsStyle;
import com.calsoft.reports.vo.DataTable;
import com.calsoft.reports.vo.FontEnum;
import com.calsoft.reports.vo.TableHeader;

public class ExcelUtils {

	public static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

	public static final String SHIPPING_TABLE_SHEET_NAME = "Shipping Tablerate";
	public static final String SHIPPING_TABLE_TEMPLATE = "shippingtable_template.xls";

	public static final String PRODUCT_TABLE_TEMPLATE = "productImport.xls";
	public static final String FILE_PATH = "templates/";
	public static final String PK = "PK";
	public static final String WEBSITE_ID = "Website Id";
	public static final String DEST_COUNTRY_ID = "DestCountry Id";
	public static final String DEST_REGION_ID = "DestRegion Id";
	public static final String DEST_ZIP = "destZip";
	public static final String CONDITION_NAME = "Condition Name";
	public static final String CONDITION_VALUE = "Condition Value";
	public static final String PRICE = "Price";
	public static final String COST = "Cost";
	public static final String EXCEL_DATEFORMAT = "dd-MMM-yyyy";
	public static final Integer UNIT_WIDTH = 256;

	public static final String[] SHIPPING_TABLE_COL_NAMES = { PK, DEST_COUNTRY_ID, DEST_REGION_ID, CONDITION_NAME,
			CONDITION_VALUE, PRICE, COST };

	public static final String[] PRODUCT_TABLE_COL_NAMES = { "old_id", "url_key", "url_path", "category_ids",
			"required_options", "has_options", "image_label", "small_image_label", "thumbnail_label", "created_at",
			"updated_at", "creareseo_discontinued_product", "isCustomized", "CustomProductResource",
			"special_from_date", "special_to_date", "thumbnail", "small_image", "media_gallery", "gallery",
			"is_recurring", "recurring_profile", "custom_design", "custom_design_from", "custom_design_to",
			"custom_layout_update", "page_layout", "options_container", "tax_class_id", "country_of_manufacture",
			"supplier" };

	public static final String[] SHIPPING_TABLE_HEADERS = { "PK", "DestCountry Id", "DestRegion Id", "Condition Name",
			"Condition Value", "Price", "Cost" };

	private static Map<String, String> SHIPPING_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> SHIPPING_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> SHIPPING_TABLE_FIELD_MAP = new HashMap<>();

	private static Map<String, String> PRODUCT_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : SHIPPING_TABLE_COL_NAMES) {
			SHIPPING_TABLE_COL_MAP.put(name, name);
			SHIPPING_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		SHIPPING_TABLE_FIELD_MAP.put(PK, "pk~Long");
		SHIPPING_TABLE_FIELD_MAP.put(DEST_COUNTRY_ID, "destCountryId~String");
		SHIPPING_TABLE_FIELD_MAP.put(DEST_REGION_ID, "destRegionId~String");
		SHIPPING_TABLE_FIELD_MAP.put(CONDITION_NAME, "conditionName~String");
		SHIPPING_TABLE_FIELD_MAP.put(CONDITION_VALUE, "conditionValue~Double");
		SHIPPING_TABLE_FIELD_MAP.put(PRICE, "price~Double");
		SHIPPING_TABLE_FIELD_MAP.put(COST, "cost~Double");
	}

	static {
		for (String name : PRODUCT_TABLE_COL_NAMES) {
			PRODUCT_TABLE_FIELD_MAP.put(name, name);
		}

	}

	public static Map<String, String> getShippingTableColMap() {
		return Collections.unmodifiableMap(SHIPPING_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getShippingTableWidthMap() {
		return Collections.unmodifiableMap(SHIPPING_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getShippingTableFieldMap() {
		return Collections.unmodifiableMap(SHIPPING_TABLE_FIELD_MAP);
	}

	public static Map<String, String> getProcutTableFieldMap() {
		return Collections.unmodifiableMap(PRODUCT_TABLE_FIELD_MAP);
	}

	public static List<TableHeader> getExcelHeaderContents(List<String> columnList, Map<String, String> colNameMap,
			Map<String, Integer> fieldWidthMap) {
		List<TableHeader> tableHeaders = new LinkedList<>();
		for (String columnName : columnList) {
			TableHeader tableHeader = new TableHeader();
			if (colNameMap == null) {
				tableHeader.setColumnName(columnName);
			} else {
				tableHeader.setColumnName(colNameMap.get(columnName));
			}
			tableHeader.setColumnType("String");
			tableHeader.setColumnWidth(fieldWidthMap.get(columnName));
			tableHeaders.add(tableHeader);
		}
		return tableHeaders;
	}

	public static List<Map<String, String>> getExcelContents(List<String> columnList, List<ShippingTablerate> newsAll,
			Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (ShippingTablerate subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, ShippingTablerate subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	private static String convertDateToString(Date fieldValueObj, String excelDateformat) {
		if (fieldValueObj != null) {
			SimpleDateFormat df = new SimpleDateFormat(excelDateformat);
			return df.format(fieldValueObj);
		} else {
			return StringUtils.EMPTY;
		}
	}

	public static List<WorkSheetContent> getExcelWorksheetContents(String newsLetterSheetName, String tableHeaderTitle,
			List<TableHeader> tableHeaders, List<Map<String, String>> excelContents) {
		List<WorkSheetContent> workSheetContentList = new LinkedList<>();
		DataTable dataTable = new DataTable();
		dataTable.setTableHeaders(tableHeaders);
		dataTable.setExcelContents(excelContents);

		List<DataTable> dataTables = new LinkedList<>();
		dataTables.add(dataTable);

		WorkSheetContent workSheetContent = new WorkSheetContent(dataTables);
		CellsStyle sheetHeaderStyle = new CellsStyle();
		sheetHeaderStyle.setFontEnum(FontEnum.Calibri);
		sheetHeaderStyle.setBold(true);
		sheetHeaderStyle.setFontHeight(14);

		workSheetContent.setDataTables(dataTables);
		workSheetContent.setHorizontalSpace(0);
		workSheetContent.setSheetName(newsLetterSheetName);
		workSheetContent.setVerticalSpace(0);
		workSheetContent.setSheetHeaderStyle(sheetHeaderStyle);
		if (StringUtils.isNotBlank(tableHeaderTitle)) {
			workSheetContent.setSheetHeader(tableHeaderTitle);
		}
		workSheetContentList.add(workSheetContent);
		return workSheetContentList;
	}

	public static void callSetter(Object obj, String fieldName, Object value) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(fieldName, obj.getClass());
			pd.getWriteMethod().invoke(obj, value);
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.info("Error while trying to invoke setter ", e);
		}
	}

	public static Object callGetter(Object obj, String fieldName) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(fieldName, obj.getClass());
			final Object fieldValObj = pd.getReadMethod().invoke(obj);
			System.out.println("" + fieldValObj);
			return fieldValObj;
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.info("Error while trying to invoke getter ", e);
		}
		return null;
	}

	public static final String CURRENCY_FROM = "Currency From";
	public static final String CURRENCY_TO = "Currency to";
	public static final String RATE = "Rate";

	public static final String[] CURRENCY_RATE_COL_NAMES = { CURRENCY_FROM, CURRENCY_TO, RATE };
	public static final String[] CURRENCY_HEADERS = { "Currency From", "Currency to", "Rate" };

	public static final String CURRENCY_RATE_SHEET_NAME = "Currency Rate";
	public static final String CURRENCY_RATE_TEMPLATE = "currency_template.xls";

	private static Map<String, Integer> CURRENCY_RATE_WIDTH_MAP = new HashMap<>();

	private static Map<String, String> CURRENCY_RATE_COL_MAP = new HashMap<>();
	private static Map<String, String> CURRENCY_RATE_FIELD_MAP = new HashMap<>();
	static {
		for (String name : CURRENCY_RATE_COL_NAMES) {
			CURRENCY_RATE_COL_MAP.put(name, name);
			CURRENCY_RATE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}
		CURRENCY_RATE_FIELD_MAP.put(CURRENCY_FROM, "currencyFrom~String");
		CURRENCY_RATE_FIELD_MAP.put(CURRENCY_TO, "currencyTo~String");
		CURRENCY_RATE_FIELD_MAP.put(RATE, "rate~Double");

	}

	public static Map<String, String> getColMap() {
		return Collections.unmodifiableMap(CURRENCY_RATE_COL_MAP);
	}

	public static Map<String, Integer> getCurrencyRateWidthMap() {
		return Collections.unmodifiableMap(CURRENCY_RATE_WIDTH_MAP);
	}

	public static Map<String, String> getCurrencyRateFieldMap() {
		return Collections.unmodifiableMap(CURRENCY_RATE_FIELD_MAP);
	}

	public static Map<String, String> getCurrencyRateColMap() {
		return Collections.unmodifiableMap(CURRENCY_RATE_COL_MAP);
	}

	public static List<Map<String, String>> getCurrencyRateExcelContents(List<String> columnList,
			List<DirectoryCurrencyRate> directoryCurrencyRate, Map<String, String> currencyRateFieldMap,
			String excelDateformat) {
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (DirectoryCurrencyRate subRecord : directoryCurrencyRate) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, currencyRateFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, DirectoryCurrencyRate subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	// order table start

	public static final String ORDER_TABLE_SHEET_NAME = "Orders";
	public static final String ORDER_TABLE_TEMPLATE = "order_template.xls";
	public static final String ENTITY_ID = "Order Id";
	public static final String CREATED_AT = "Purchased On";
	public static final String BILLING_NAME = "Bill to Name";
	public static final String SHIPPING_NAME = "Ship to Name";
	public static final String GRAND_TOTAL = "GrandTotal";
	public static final String STATUS = "Status";
	public static final String ADDRESS = "Address";
	public static final String[] ORDER_TABLE_COL_NAMES = { ENTITY_ID, CREATED_AT, BILLING_NAME, SHIPPING_NAME,
			GRAND_TOTAL, STATUS, ADDRESS};
	public static final String[] ORDER_TABLE_HEADERS = { "Order Id", "Purchased On", "Bill to Name", "Grand Total",
			"Status","ADDRESS" };

	private static Map<String, String> ORDER_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> ORDER_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> ORDER_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : ORDER_TABLE_COL_NAMES) {
			ORDER_TABLE_COL_MAP.put(name, name);
			ORDER_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		ORDER_TABLE_FIELD_MAP.put(ENTITY_ID, "incrementId~Long");
		ORDER_TABLE_FIELD_MAP.put(CREATED_AT, "createdDate~Date");
		ORDER_TABLE_FIELD_MAP.put(BILLING_NAME, "billingName~String");
		ORDER_TABLE_FIELD_MAP.put(SHIPPING_NAME, "shippingName~String");
		ORDER_TABLE_FIELD_MAP.put(GRAND_TOTAL, "grandTotal~Double");
		ORDER_TABLE_FIELD_MAP.put(STATUS, "status~String");
		ORDER_TABLE_FIELD_MAP.put(ADDRESS, "address~String");
	}

	public static Map<String, String> getOrderTableColMap() {
		return Collections.unmodifiableMap(ORDER_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getOrderTableWidthMap() {
		return Collections.unmodifiableMap(ORDER_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getOrderTableFieldMap() {
		return Collections.unmodifiableMap(ORDER_TABLE_FIELD_MAP);
	}

	// invoice table
	public static final String INVOICE_TABLE_SHEET_NAME = "Invoices";
	public static final String INVOICE_TABLE_TEMPLATE = "invoices_template.xls";
	public static final String ENTITY_ID1 = "Invoice Id";
	public static final String INVOICE_AT = "Invoice Date";
	public static final String ORDER_ID = "Order Id";
	public static final String ORDER_DATE = "Order Date";
	public static final String CUSTOMER_FIRSTNAME1 = "Bill to Name";
	// public static final String STATUS1 = "Status";
	public static final String GRAND_TOTAL1 = "Amount";
	public static final String[] INVOICE_TABLE_COL_NAMES = { ENTITY_ID1, INVOICE_AT, ORDER_ID, ORDER_DATE,
			CUSTOMER_FIRSTNAME1, GRAND_TOTAL1 };
	public static final String[] INVOICE_TABLE_HEADERS = { "Invoice Id", "Invoice Date", "Order Id", "Order Date",
			"Bill to Name", "Amount" };

	private static Map<String, String> INVOICE_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> INVOICE_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> INVOICE_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : INVOICE_TABLE_COL_NAMES) {
			INVOICE_TABLE_COL_MAP.put(name, name);
			INVOICE_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		INVOICE_TABLE_FIELD_MAP.put(ENTITY_ID1, "entityId~Long");
		INVOICE_TABLE_FIELD_MAP.put(INVOICE_AT, "createdDate~Date");
		INVOICE_TABLE_FIELD_MAP.put(ORDER_ID, "incrementId~Long");
		INVOICE_TABLE_FIELD_MAP.put(ORDER_DATE, "orderCreatedAt~Date");
		INVOICE_TABLE_FIELD_MAP.put(CUSTOMER_FIRSTNAME1, "billingName~String");
		// INVOICE_TABLE_FIELD_MAP.put(STATUS, "state~Integer");
		INVOICE_TABLE_FIELD_MAP.put(GRAND_TOTAL1, "grandTotal~Double");

	}

	public static Map<String, String> getInvoiceTableColMap() {
		return Collections.unmodifiableMap(INVOICE_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getInvoiceTableWidthMap() {
		return Collections.unmodifiableMap(INVOICE_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getInvoiceTableFieldMap() {
		return Collections.unmodifiableMap(INVOICE_TABLE_FIELD_MAP);
	}

	// Shipment table
	public static final String SHIPMENT_TABLE_SHEET_NAME = "Shipments";
	public static final String SHIPMENT_TABLE_TEMPLATE = "Shipment_template.xls";
	public static final String ENTITY_ID2 = "Shipment Id";
	public static final String CREATED_AT2 = "Date Shipped";
	public static final String ORDER_ID1 = "Order Id";
	public static final String ORDER_DATE1 = "Order Date";
	public static final String CUSTOMER_FIRSTNAME2 = "Ship to Name";
	public static final String TOTAL_QTY_ORDERED = "Total Qty";
	public static final String[] SHIPMENT_TABLE_COL_NAMES = { ENTITY_ID2, CREATED_AT2, ORDER_ID1, ORDER_DATE1,
			CUSTOMER_FIRSTNAME2, TOTAL_QTY_ORDERED };
	public static final String[] SHIPMENT_TABLE_HEADERS = { "Shipment Id", "Date Shipped", "Order Id", "Order Date",
			"Ship to Name", "Total Qty" };

	private static Map<String, String> SHIPMENT_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> SHIPMENT_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> SHIPMENT_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : SHIPMENT_TABLE_COL_NAMES) {
			SHIPMENT_TABLE_COL_MAP.put(name, name);
			SHIPMENT_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		SHIPMENT_TABLE_FIELD_MAP.put(ENTITY_ID2, "entityId~Long");
		SHIPMENT_TABLE_FIELD_MAP.put(CREATED_AT2, "createdDate~Date");
		SHIPMENT_TABLE_FIELD_MAP.put(ORDER_ID1, "incrementId~Long");
		SHIPMENT_TABLE_FIELD_MAP.put(ORDER_DATE1, "orderCreatedAt~Date");
		SHIPMENT_TABLE_FIELD_MAP.put(CUSTOMER_FIRSTNAME2, "shippingName~String");
		SHIPMENT_TABLE_FIELD_MAP.put(TOTAL_QTY_ORDERED, "totalQty~Double");

	}

	public static Map<String, String> getShipmentTableColMap() {
		return Collections.unmodifiableMap(SHIPMENT_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getShipmentTableWidthMap() {
		return Collections.unmodifiableMap(SHIPMENT_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getShipmentTableFieldMap() {
		return Collections.unmodifiableMap(SHIPMENT_TABLE_FIELD_MAP);
	}

	// creditmemo table
	public static final String CREDITMEMO_TABLE_SHEET_NAME = "Creditmemos";
	public static final String CREDITMEMO_TABLE_TEMPLATE = "Creditmemo_template.xls";
	public static final String ENTITY_ID3 = "CreditMemo Id";
	public static final String CREATED_AT3 = "Date Creditmemo";
	public static final String ORDER_ID2 = "Order Id";
	public static final String ORDER_DATE2 = "Order Date";
	public static final String CUSTOMER_FIRSTNAME3 = "Bill to Name";
	public static final String AMOUNT_REFUNDED = "Grand Total";
	public static final String[] CREDITMEMO_TABLE_COL_NAMES = { ENTITY_ID3, CREATED_AT3, ORDER_ID2, ORDER_DATE2,
			CUSTOMER_FIRSTNAME3, AMOUNT_REFUNDED };
	public static final String[] CREDITMEMO_TABLE_HEADERS = { "Creditmemo Id", "Date Creditmemo", "Order Id",
			"Order Date", "Bill to Name", "Grand Total" };

	private static Map<String, String> CREDITMEMO_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> CREDITMEMO_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> CREDITMEMO_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : CREDITMEMO_TABLE_COL_NAMES) {
			CREDITMEMO_TABLE_COL_MAP.put(name, name);
			CREDITMEMO_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		CREDITMEMO_TABLE_FIELD_MAP.put(ENTITY_ID3, "entityId~Long");
		CREDITMEMO_TABLE_FIELD_MAP.put(CREATED_AT3, "createdDate~Date");
		CREDITMEMO_TABLE_FIELD_MAP.put(ORDER_ID2, "incrementId~Long");
		CREDITMEMO_TABLE_FIELD_MAP.put(ORDER_DATE2, "orderCreatedAt~Date");
		CREDITMEMO_TABLE_FIELD_MAP.put(CUSTOMER_FIRSTNAME3, "billingName~String");
		CREDITMEMO_TABLE_FIELD_MAP.put(AMOUNT_REFUNDED, "grandTotal~Double");

	}

	public static Map<String, String> getCreditmemoTableColMap() {
		return Collections.unmodifiableMap(CREDITMEMO_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getCreditmemoTableWidthMap() {
		return Collections.unmodifiableMap(CREDITMEMO_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getCreditmemoTableFieldMap() {
		return Collections.unmodifiableMap(CREDITMEMO_TABLE_FIELD_MAP);
	}

	// Search Query table
	public static final String SEARCHQUERY_TABLE_SHEET_NAME = "Search";
	public static final String SEARCHQUERY_TABLE_TEMPLATE = "search_template.xls";
	public static final String QUERY_ID = "ID";
	public static final String QUERY_TEXT = "Search Query";
	public static final String NUM_RESULTS = "Results";
	public static final String POPULARITY = "Hits";
	public static final String[] SEARCHQUERY_TABLE_COL_NAMES = { QUERY_ID, QUERY_TEXT, NUM_RESULTS, POPULARITY };
	public static final String[] SEARCHQUERY_TABLE_HEADERS = { "ID", "Search Query", "Results", "Hits" };

	private static Map<String, String> SEARCHQUERY_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> SEARCHQUERY_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> SEARCHQUERY_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : SEARCHQUERY_TABLE_COL_NAMES) {
			SEARCHQUERY_TABLE_COL_MAP.put(name, name);
			SEARCHQUERY_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		SEARCHQUERY_TABLE_FIELD_MAP.put(QUERY_ID, "queryId~Integer");
		SEARCHQUERY_TABLE_FIELD_MAP.put(QUERY_TEXT, "queryText~String");
		SEARCHQUERY_TABLE_FIELD_MAP.put(NUM_RESULTS, "numResults~Integer");
		SEARCHQUERY_TABLE_FIELD_MAP.put(POPULARITY, "popularity~Integer");

	}

	public static Map<String, String> getSearchQueryTableColMap() {
		return Collections.unmodifiableMap(SEARCHQUERY_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getSearchQueryTableWidthMap() {
		return Collections.unmodifiableMap(SEARCHQUERY_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getSearchQueryTableFieldMap() {
		return Collections.unmodifiableMap(SEARCHQUERY_TABLE_FIELD_MAP);
	}

	// order table
	public static List<Map<String, String>> getExcelOrderContents(List<String> columnList,
			List<SalesFlatOrderGrid> newsAll, Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelOrderContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (SalesFlatOrderGrid subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, SalesFlatOrderGrid subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	// invoice table

	public static List<Map<String, String>> getExcelInvoiceContents(List<String> columnList,
			List<SalesFlatInvoiceGrid> newsAll, Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelInvoiceContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (SalesFlatInvoiceGrid subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, SalesFlatInvoiceGrid subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	// shipment table

	public static List<Map<String, String>> getExcelShipmentContents(List<String> columnList,
			List<SalesFlatShipmentGrid> newsAll, Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelInvoiceContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (SalesFlatShipmentGrid subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, SalesFlatShipmentGrid subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	// Creditmemo table

	public static List<Map<String, String>> getExcelCreditmemoContents(List<String> columnList,
			List<SalesFlatCreditmemoGrid> newsAll, Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelInvoiceContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (SalesFlatCreditmemoGrid subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, SalesFlatCreditmemoGrid subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	// Search Query table

	public static List<Map<String, String>> getExcelSearchQueryContents(List<String> columnList,
			List<CatalogsearchQuery> newsAll, Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelOrderContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (CatalogsearchQuery subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, CatalogsearchQuery subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	// order model

	public static final String NEWS_LETTER_SHEET_NAME = "Newsletter Subscriber";
	public static final String NEWS_LETTER_TEMPLATE = "newsletter_template.xls";
	public static final String CUSTOMER_NAME = "Customer Name";
	public static final String SUBSCRIBER_STATUS = "Subscriber Status";
	public static final String SUBSCRIBER_EMAIL = "Subcriber Email";
	public static final String SUBSCRIBER_ID = "Subcriber ID";
	public static final String CUSTOMER_ID = "Customer Id";
	public static final String STATUS1 = "status";

	public static final String[] NEWS_LETTER_COL_NAMES = { SUBSCRIBER_ID, SUBSCRIBER_EMAIL, SUBSCRIBER_STATUS };
	public static final String[] NEWS_LETTER_COL_NAMES_SAVE = { SUBSCRIBER_EMAIL, SUBSCRIBER_STATUS };
	public static final String[] NEWSLETTER_HEADERS = { "Subscriber Id", "Subscriber Email", "Subscriber Status" };
	private static Map<String, String> NEWS_LETTER_COL_MAP = new HashMap<>();
	private static Map<String, Integer> NEWS_LETTER_WIDTH_MAP = new HashMap<>();
//				    private static Map<String, String> NEWS_LETTER_METHOD_MAP = new HashMap<>();
	private static Map<String, String> NEWS_LETTER_FIELD_MAP = new HashMap<>();
	static {
		for (String name : NEWS_LETTER_COL_NAMES) {
			NEWS_LETTER_COL_MAP.put(name, name);
			NEWS_LETTER_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}
//				        NEWS_LETTER_METHOD_MAP.put(SUBSCRIBER_ID, "getSubscriberId~");
//				        NEWS_LETTER_METHOD_MAP.put(SUBSCRIBER_EMAIL, "getSubscriberEmail~");
//				        NEWS_LETTER_METHOD_MAP.put(SUBSCRIBER_STATUS1, "getSubscriberStatus~");
//				        NEWS_LETTER_METHOD_MAP.put(CUSTOMER_ID, "getCustomerId~");

		NEWS_LETTER_FIELD_MAP.put(SUBSCRIBER_ID, "subscriberId~Long");
		NEWS_LETTER_FIELD_MAP.put(SUBSCRIBER_EMAIL, "subscriberEmail~String");
		NEWS_LETTER_FIELD_MAP.put(SUBSCRIBER_STATUS, "subscriberStatus~Integer");
	}

	public static Map<String, String> getNewsLetterColMap() {
		return Collections.unmodifiableMap(NEWS_LETTER_COL_MAP);
	}

	public static Map<String, Integer> getNewsLetterWidthMap() {
		return Collections.unmodifiableMap(NEWS_LETTER_WIDTH_MAP);
	}

	public static Map<String, String> getNewsLetterFieldMap() {
		return Collections.unmodifiableMap(NEWS_LETTER_FIELD_MAP);
	}

	// customer table

	public static final String CUSTOMER_SHEET_NAME = "Customers";
	public static final String CUSTOMER_TEMPLATE = "customers_template.xls";
	public static final String ENTITY_ID4 = "ID";
	public static final String FIRST_NAME = "Name";
	public static final String EMAIL = "Email";
	public static final String CREATEDATE = "Customer Since";
//					public static final String TELEPHONE = "Telephone";
//					public static final String POSTCODE = "ZIP";
//					public static final String REGION = "Country";
//					public static final String STATE = "State/Province";

	public static final String[] CUSTOMER_COL_NAMES = { FIRST_NAME, EMAIL };
	public static final String[] CUSTOMER_HEADERS = { "Name", "Email" };
	private static Map<String, String> CUSTOMER_COL_MAP = new HashMap<>();
	private static Map<String, Integer> CUSTOMER_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> CUSTOMER_FIELD_MAP = new HashMap<>();
	static {
		for (String name : CUSTOMER_COL_NAMES) {
			CUSTOMER_COL_MAP.put(name, name);
			CUSTOMER_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		CUSTOMER_FIELD_MAP.put(FIRST_NAME, "firstName~String");
		CUSTOMER_FIELD_MAP.put(EMAIL, "email~String");
//						CUSTOMER_FIELD_MAP.put(TELEPHONE, "telephone~Long");
//						CUSTOMER_FIELD_MAP.put(POSTCODE, "postCode~String");
//						CUSTOMER_FIELD_MAP.put(REGION, "region~String");
//						CUSTOMER_FIELD_MAP.put(STATE, "state~String");

	}

	public static Map<String, String> getCustomerColMap() {
		return Collections.unmodifiableMap(CUSTOMER_COL_MAP);
	}

	public static Map<String, Integer> getCustomerWidthMap() {
		return Collections.unmodifiableMap(CUSTOMER_WIDTH_MAP);
	}

	public static Map<String, String> getCustomerFieldMap() {
		return Collections.unmodifiableMap(CUSTOMER_FIELD_MAP);
	}

	// subscriber
	public static List<Map<String, String>> getExcelSubscriberContents(List<String> columnList,
			List<NewsletterSubscriber> newsAll, Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (NewsletterSubscriber subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, NewsletterSubscriber subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
//				                fieldValueObj = subRecord.getClass().getMethod(fieldInfo[0]).invoke(subRecord);
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	// customer table

	public static List<Map<String, String>> getExcelCustomerContents(List<String> columnList, List<CustomerIDX> newsAll,
			Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (CustomerIDX subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, CustomerIDX subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
//				                fieldValueObj = subRecord.getClass().getMethod(fieldInfo[0]).invoke(subRecord);
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	// audit table

	public static final String AUDIT_TABLE_SHEET_NAME = "Audit";
	public static final String AUDIT_TABLE_TEMPLATE = "audit_template.xls";
	public static final String FIELD_NAME = "Field Name";
	public static final String FIELD_CURRENTVALUE = "Field CurrentValue";
	public static final String FIELD_PREVIOUSVALUE = "Field PreviousValue";
	public static final String LAST_UPDATEDATE = "Last UpdatedDate";
	public static final String LAST_UPDATEDATE_USER = "Last UpdatedUser";
	public static final String UPD_VIA = "Update Via";

	public static final String[] AUDIT_TABLE_COL_NAMES = { FIELD_NAME, FIELD_CURRENTVALUE, FIELD_PREVIOUSVALUE,
			LAST_UPDATEDATE, LAST_UPDATEDATE_USER, UPD_VIA };
	public static final String[] AUDIT_TABLE_HEADERS = { "Field Name", "fieldCurrentValue", "Field PreviousValue",
			"Last UpdatedDate", "Last UpdatedUser", "Update Via" };

	private static Map<String, String> AUDIT_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> AUDIT_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> AUDIT_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : AUDIT_TABLE_COL_NAMES) {
			AUDIT_TABLE_COL_MAP.put(name, name);
			AUDIT_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		AUDIT_TABLE_FIELD_MAP.put(FIELD_NAME, "fieldName~String");
		AUDIT_TABLE_FIELD_MAP.put(FIELD_CURRENTVALUE, "fieldCurrentValue~String");
		AUDIT_TABLE_FIELD_MAP.put(FIELD_PREVIOUSVALUE, "fieldPreviousValue~String");
		AUDIT_TABLE_FIELD_MAP.put(LAST_UPDATEDATE, "lastUpdatedDate~String");
		AUDIT_TABLE_FIELD_MAP.put(LAST_UPDATEDATE_USER, "lastUpdatedUser~String");
		AUDIT_TABLE_FIELD_MAP.put(UPD_VIA, "updatedVia~String");

	}

	public static Map<String, String> getAuditTableColMap() {
		return Collections.unmodifiableMap(AUDIT_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getAuditTableWidthMap() {
		return Collections.unmodifiableMap(AUDIT_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getAuditTableFieldMap() {
		return Collections.unmodifiableMap(AUDIT_TABLE_FIELD_MAP);
	}

	// audit
	public static List<Map<String, String>> getExcelAuditContents(List<String> columnList, List<AuditVO> newsAll,
			Map<String, String> newsLetterFieldMap, String excelDateformat) {
		logger.info("getExcelContents :: field Map :: {}", newsLetterFieldMap);
		List<Map<String, String>> excelContents = new LinkedList<>();
		for (AuditVO subRecord : newsAll) {
			excelContents
					.add(getExcelContentForCurrentRecord(columnList, newsLetterFieldMap, excelDateformat, subRecord));
		}
		return excelContents;
	}

	private static Map<String, String> getExcelContentForCurrentRecord(List<String> columnList,
			Map<String, String> newsLetterFieldMap, String excelDateformat, AuditVO subRecord) {
		final Map<String, String> excelContent = new HashMap<>();
		String fieldType;
		String fieldValue;
		Object fieldValueObj;
		for (String columnName : columnList) {
			String[] fieldInfo = newsLetterFieldMap.get(columnName).split("~", -1);
			fieldType = fieldInfo[1];
			try {
				fieldValueObj = callGetter(subRecord, fieldInfo[0]);
				if ("Date".equalsIgnoreCase(fieldType) || fieldValueObj instanceof Date) {
					fieldValue = ExcelUtils.convertDateToString((Date) fieldValueObj, excelDateformat);
				} else {
					fieldValue = fieldValueObj == null ? "-" : String.valueOf(fieldValueObj);
				}
				excelContent.put(columnName, fieldValue);
			} catch (Exception exp) {
				logger.error("Exception while trying to populate excel data ", exp);
			}
		}
		return excelContent;
	}

	public static Map<String, String> getPincodeFieldMap() {
		return Collections.unmodifiableMap(SHIPPING_TABLE_FIELD_MAP);
	}

	// Pincode Query table

	public static final String PINCODE_TABLE_SHEET_NAME = "Pincode";
	public static final String PINCODE_TABLE_TEMPLATE = "Pincode_template.xls";

	public static final String PINCODE = "PinCode";
	public static final String IS_ACTIVE_PINCODE = "Active";

	public static final String[] PINCODE_TABLE_COL_NAMES = { PINCODE, IS_ACTIVE_PINCODE };
	public static final String[] PINCODE_TABLE_HEADERS = { "Pincode", "Active" };

	private static Map<String, String> PINCODE_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> PINCODE_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> PINCODE_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : PINCODE_TABLE_COL_NAMES) {
			PINCODE_TABLE_COL_MAP.put(name, name);
			PINCODE_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		PINCODE_TABLE_FIELD_MAP.put(PINCODE, "pinCode~String");
		PINCODE_TABLE_FIELD_MAP.put(IS_ACTIVE_PINCODE, "isActive~Integer");

	}

	public static Map<String, String> getPincodeTableColMap() {
		return Collections.unmodifiableMap(PINCODE_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getPincodeTableWidthMap() {
		return Collections.unmodifiableMap(PINCODE_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getPincodeTableFieldMap() {
		return Collections.unmodifiableMap(PINCODE_TABLE_FIELD_MAP);
	}

	/// product update

	public static final String PRODUCT_UPDATE_TABLE_SHEET_NAME = "ProductUpdate";
	public static final String PRODUCT_UPDATE_TABLE_TEMPLATE = "Product_Update_template.xls";

	public static final String NAME_PRODUCT_UPDATE = "Product Name";
	public static final String SKU_PRODUCT_UPDATE = "Sku";

	public static final String PRICE_PRODUCT_UPDATE = "Price";

	public static final String SPECIAL_PRICE_PRODUCT_UPDATE = "Special Price";

	public static final String QTY_PRODUCT_UPDATE = "Qty";

	public static final String[] PRODUCT_UPDATE_TABLE_COL_NAMES = { NAME_PRODUCT_UPDATE, SKU_PRODUCT_UPDATE,
			PRICE_PRODUCT_UPDATE, SPECIAL_PRICE_PRODUCT_UPDATE, QTY_PRODUCT_UPDATE };
	public static final String[] PRODUCT_UPDATE_TABLE_HEADERS = { "Product Name", "Sku", "Price", "Special Price",
			"Qty" };

	private static Map<String, String> PRODUCT_UPDATE_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> PRODUCT_UPDATE_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> PRODUCT_UPDATE_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : PRODUCT_UPDATE_TABLE_COL_NAMES) {
			PRODUCT_UPDATE_TABLE_COL_MAP.put(name, name);
			PRODUCT_UPDATE_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		PRODUCT_UPDATE_TABLE_FIELD_MAP.put(NAME_PRODUCT_UPDATE, "name~String");
		PRODUCT_UPDATE_TABLE_FIELD_MAP.put(SKU_PRODUCT_UPDATE, "sku~String");
		PRODUCT_UPDATE_TABLE_FIELD_MAP.put(PRICE_PRODUCT_UPDATE, "price~Double");
		PRODUCT_UPDATE_TABLE_FIELD_MAP.put(SPECIAL_PRICE_PRODUCT_UPDATE, "specialPrice~Double");
		PRODUCT_UPDATE_TABLE_FIELD_MAP.put(QTY_PRODUCT_UPDATE, "qty~Double");

	}

	public static Map<String, String> getProductUpdateTableColMap() {
		return Collections.unmodifiableMap(PRODUCT_UPDATE_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getProductUpdateTableWidthMap() {
		return Collections.unmodifiableMap(PRODUCT_UPDATE_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getProductUpdateTableFieldMap() {
		return Collections.unmodifiableMap(PRODUCT_UPDATE_TABLE_FIELD_MAP);
	}

	public static Map<String, String> getProductUpdateFieldMap() {
		return Collections.unmodifiableMap(PRODUCT_UPDATE_TABLE_FIELD_MAP);
	}

	/// purchase

	
	public static final String PURCHASE_TABLE_SHEET_NAME = "Purchase Tablerate";
	public static final String PURCHASE_TABLE_TEMPLATE = "purchase_template.xls";

	public static final String SKU_PURCHASE = "Sku";

	public static final String Net_Unit_Cost_PURCHASE = "Net Unit Cost";

	public static final String QTY_PURCHASE = "Qty";

	public static final String Tax_PURCHASE = "Tax Rate Name";

	public static final String Discount_PURCHASE = "Discount";

	public static final String Expiry_PURCHASE = "Expiry";

	public static final String[] PURCHASE_TABLE_COL_NAMES = { SKU_PURCHASE, Net_Unit_Cost_PURCHASE, QTY_PURCHASE,
			Tax_PURCHASE, Discount_PURCHASE, Expiry_PURCHASE };
	public static final String[] PURCHASE_TABLE_HEADERS = { "Sku", "Net Unit Cost", "Qty", "Tax Rate Name", "Discount",
			"Expiry" };

	private static Map<String, String> PURCHASE_TABLE_COL_MAP = new HashMap<>();
	private static Map<String, Integer> PURCHASE_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> PURCHASE_TABLE_FIELD_MAP = new HashMap<>();

	static {
		for (String name : PURCHASE_TABLE_COL_NAMES) {
			PURCHASE_TABLE_COL_MAP.put(name, name);
			PURCHASE_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		PURCHASE_TABLE_FIELD_MAP.put(SKU_PURCHASE, "sku~String");
		PURCHASE_TABLE_FIELD_MAP.put(Net_Unit_Cost_PURCHASE, "netUnitCost~Double");
		PURCHASE_TABLE_FIELD_MAP.put(QTY_PURCHASE, "qty~Double");
		PURCHASE_TABLE_FIELD_MAP.put(Tax_PURCHASE, "tax~Double");
		PURCHASE_TABLE_FIELD_MAP.put(Discount_PURCHASE, "discount~Double");
		PURCHASE_TABLE_FIELD_MAP.put(Expiry_PURCHASE, "expiry~String");

	}

	public static Map<String, String> getPurchaseTableColMap() {
		return Collections.unmodifiableMap(PURCHASE_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getPurchaseTableWidthMap() {
		return Collections.unmodifiableMap(PURCHASE_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getPurchaseTableFieldMap() {
		return Collections.unmodifiableMap(PURCHASE_TABLE_FIELD_MAP);
	}

	public static Map<String, String> getPurchaseFieldMap() {
		return Collections.unmodifiableMap(PURCHASE_TABLE_FIELD_MAP);
	}
	
	///supplier
		
	public static final String SUPPLIER_TABLE_SHEET_NAME = "Supplier Tablerate";
	public static final String SUPPLIER_TABLE_TEMPLATE = "Supplier_template.xls";
	public static final String PRODUCT_GMC_TABLE_TEMPLATE = "Product_Gmc_template.xls";
	public static final String PRODUCT_SHOPPRIME_TABLE_TEMPLATE = "Product_shopprime_template.xls";
	

	private static final String NAME_SUPPLIER = "Name";
	private static final String COMPANY_SUPPLIER = "Company";
	private static final String STATE_SUPPLIER = "State";
	private static final String PINCODE_SUPPLIER = "Pincode";
	private static final String PHONE_SUPPLIER = "Phone";
	private static final String ADDRESS_SUPPLIER = "Address";
	private static final String EMAIL_SUPPLIER = "Email";
	private static final String GSTNUMBER_SUPPLIER = "GstNumber";
	private static final String VATNUMBER_SUPPLIER = "VatNumber";
	private static final String WEBSITE_SUPPLIER = "Website";
	private static final String LATITUDE_SUPPLIER = "Latitude";
	private static final String LONGITUDE_SUPPLIER = "Longitude";
	private static final String COUNTRY_SUPPLIER = "Country";
	private static final String CITY_SUPPLIER = "City";
	
 
   public static final String[] SUPPLIER_TABLE_COL_NAMES = {NAME_SUPPLIER, COMPANY_SUPPLIER, STATE_SUPPLIER, PINCODE_SUPPLIER, PHONE_SUPPLIER,
		   ADDRESS_SUPPLIER,EMAIL_SUPPLIER, GSTNUMBER_SUPPLIER, VATNUMBER_SUPPLIER,WEBSITE_SUPPLIER,LATITUDE_SUPPLIER ,LONGITUDE_SUPPLIER,COUNTRY_SUPPLIER,CITY_SUPPLIER};
		
	public static final String[] SUPPLIER_TABLE_HEADERS = { "Name", "Company", "State", "Pincode", "Phone",
	"Address","Email", "GstNumber","VatNumber","Website","Latitude","Longitude","Country","City"};


    private static final Map <String, String> SUPPLIER_TABLE_COL_MAP = new HashMap<>();
    private static Map<String, Integer> SUPPLIER_TABLE_WIDTH_MAP = new HashMap<>();
	private static Map<String, String> SUPPLIER_TABLE_FIELD_MAP = new HashMap<>();
    
	
	public static Map<String, String> getSupplierTableColMap() {
		return Collections.unmodifiableMap(SUPPLIER_TABLE_COL_MAP);


	}
	private static final String Name = null;
	
	private static String Company1;

	static {
		for (String name: SUPPLIER_TABLE_COL_NAMES) {
			SUPPLIER_TABLE_COL_MAP.put(name, name);
			SUPPLIER_TABLE_WIDTH_MAP.put(name, 35 * UNIT_WIDTH);
		}

		SUPPLIER_TABLE_FIELD_MAP.put(NAME_SUPPLIER, "name~String");
		SUPPLIER_TABLE_FIELD_MAP.put(COMPANY_SUPPLIER, "company~String");
		SUPPLIER_TABLE_FIELD_MAP.put(STATE_SUPPLIER, "state~String");
		SUPPLIER_TABLE_FIELD_MAP.put(PINCODE_SUPPLIER, "postalCode~String");
		SUPPLIER_TABLE_FIELD_MAP.put(PHONE_SUPPLIER, "phone~String");
		SUPPLIER_TABLE_FIELD_MAP.put(ADDRESS_SUPPLIER, "address~String");
		SUPPLIER_TABLE_FIELD_MAP.put(EMAIL_SUPPLIER, "email~String");
		SUPPLIER_TABLE_FIELD_MAP.put(GSTNUMBER_SUPPLIER, "gstNo~String");
		SUPPLIER_TABLE_FIELD_MAP.put(VATNUMBER_SUPPLIER, "vatNo~String");
		SUPPLIER_TABLE_FIELD_MAP.put(WEBSITE_SUPPLIER, "supplierWebsite~String");
		SUPPLIER_TABLE_FIELD_MAP.put(LATITUDE_SUPPLIER, "supplierLatitude~String");
		SUPPLIER_TABLE_FIELD_MAP.put(LONGITUDE_SUPPLIER, "supplierLongitude~String");
		SUPPLIER_TABLE_FIELD_MAP.put(COUNTRY_SUPPLIER, "country~String");
		SUPPLIER_TABLE_FIELD_MAP.put(CITY_SUPPLIER, "city~String");

	}

	public static Map<String, String> getSupplierTableColMap1() {
		return Collections.unmodifiableMap(SUPPLIER_TABLE_COL_MAP);
	}

	public static Map<String, Integer> getSupplierTableWidthMap() {
		return Collections.unmodifiableMap(SUPPLIER_TABLE_WIDTH_MAP);
	}

	public static Map<String, String> getSupplierTableFieldMap1() {
		return Collections.unmodifiableMap(SUPPLIER_TABLE_FIELD_MAP);
	}

	public static Map<String, String> getSupplierFieldMap1() {
		return Collections.unmodifiableMap(SUPPLIER_TABLE_FIELD_MAP);
	}

}
