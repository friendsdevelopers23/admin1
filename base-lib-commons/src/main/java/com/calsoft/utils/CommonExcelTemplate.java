package com.calsoft.utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDataValidationHelper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonExcelTemplate {

	public static int count = 1;
	public static int counter1 = 1, counter2 = 1;
	public static int indexValue = 0;
	public static boolean check = false;

	public static List<String> listForLabel = new ArrayList<String>();

	public static Map<String, List<String>> map = new HashMap<String, List<String>>();

	public static List<Map<String, String>> attributeList = new ArrayList<Map<String, String>>();

	public static Map<Integer, String> excelHeaderMap = new HashMap<Integer, String>();

	private static byte[] createExcel(String downLoadFileName) {

		DataValidationConstraint dvConstraint;
		CellRangeAddressList addressList = null;

		HSSFDataValidation validation;
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Product-Sheet");
			HSSFRow rowhead = sheet.createRow((short) 0);

			for (int i = 0; i < listForLabel.size(); i++) {

				rowhead.createCell(i).setCellValue(listForLabel.get(i));
			}

			HSSFDataValidationHelper dvHelper = new HSSFDataValidationHelper(sheet);

			int hideSheet = 1;

			for (Entry<String, List<String>> me : map.entrySet()) {

				String myArray[] = new String[me.getValue().size()];
				for (int i = 0; i < me.getValue().size(); i++) {
					myArray[i] = me.getValue().get(i);
				}

				for (int i = 0; i < listForLabel.size(); i++) {
					if (me.getKey().equalsIgnoreCase(listForLabel.get(i))) {
						addressList = new CellRangeAddressList(1, 1000, i, i);
					}
				}

				String hiddenName = me.getKey().toString().replaceAll("\\s", "");
				System.out.println("hiddenName -- " + hiddenName);
				//
				HSSFSheet hidden = workbook.createSheet(hiddenName);
				//
				for (int i = 0, length = myArray.length; i < length; i++) {
					String name = myArray[i];
					HSSFRow row = hidden.createRow(i);
					HSSFCell cell = row.createCell(0);
					cell.setCellValue(name);
				}
				//
				Name namedCell = workbook.createName();
				namedCell.setNameName(hiddenName);

				if (myArray.length > 0) {
					namedCell.setRefersToFormula(hiddenName + "!$A$1:$A$" + myArray.length);

					DVConstraint constraint = DVConstraint.createFormulaListConstraint(hiddenName);
					validation = new HSSFDataValidation(addressList, constraint);
					workbook.setSheetHidden(hideSheet++, true);
					sheet.addValidationData(validation);
				}

			}

			autoResizeColumns(listForLabel.size(), sheet);

			workbook.write(out);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	static HSSFWorkbook workbook = new HSSFWorkbook();
	static HSSFSheet sheet = workbook.createSheet("Product-Sheet");

	private static void autoResizeColumns(int len, HSSFSheet sheet) {
		for (int colIndex = 0; colIndex < len; colIndex++) {
			sheet.autoSizeColumn(colIndex);
		}
	}

	public byte[] handleJson(JSONArray dataArray, String downLoadFileName, String tenantId, String type, String id)
			throws JSONException {

		listForLabel.clear();

		map.clear();

		handleValue("data", dataArray);

		System.out.println("Enter handleJson");

		for (Entry<String, List<String>> me : map.entrySet()) {
			System.out.println(me.getKey() + ":" + me.getValue());
		}

		for (String label : listForLabel) {
			System.out.println(count++ + " - " + label);
		}

		byte[] ouput = createExcel(downLoadFileName);

		return ouput;

	}

	public static void handleValue(String key, Object value) throws JSONException {

		List<String> tempList = new ArrayList<String>();

		if (key != null && key.equalsIgnoreCase("final") && value.toString() != null
				&& !value.toString().equalsIgnoreCase("null")) {
//                      System.out.println("key -- " + key);

			JSONObject jsonObject = (JSONObject) value;

			String jsonLabel = String.valueOf(jsonObject.get("frontendLabel"));
			String jsonInput = String.valueOf(jsonObject.get("frontendInput"));

			boolean attributeCodeExists = false;

			Map<String, String> eavAttributeMap = new HashMap<String, String>();

			System.out.println(jsonLabel);

			if (jsonLabel != null && !jsonLabel.toString().equalsIgnoreCase("null") && jsonInput != null
					&& !jsonInput.toString().equalsIgnoreCase("null") && !attributeCodeExists) {

//                              System.out.println("value -- " + counter1++ + " -- " + jsonInput + "\t\t -- " + jsonLabel);

				eavAttributeMap.put("attributeCode", jsonLabel);
				eavAttributeMap.put("frontendInput", jsonInput);

				attributeList.add(eavAttributeMap);

				if (jsonInput != null
						&& (jsonInput.equalsIgnoreCase("multiselect") || jsonInput.equalsIgnoreCase("select"))) {

					JSONArray jsonAttrOption = new JSONObject(value.toString()).getJSONArray("options");

					for (int i = 0; i < jsonAttrOption.length(); i++) {

						System.out.println(jsonAttrOption.get(i));

						tempList.add(jsonAttrOption.get(i).toString());
						// tempList.add(jsonAttrOption.getJSONObject(i).get("value").toString());

						map.put(jsonLabel, tempList);

					}

				} else if (jsonInput != null && jsonInput.equalsIgnoreCase("boolean")) {

					tempList.add("True");
					tempList.add("False");
					map.put(jsonLabel, tempList);

				}

				listForLabel.add(jsonLabel);

			} else if (jsonLabel.equalsIgnoreCase("tax_class_id")
					|| jsonLabel.equalsIgnoreCase("country_of_manufacture") || jsonLabel.equalsIgnoreCase("supplier")
					|| jsonLabel.equalsIgnoreCase("small_image") || jsonLabel.equalsIgnoreCase("thumbnail")) {

				eavAttributeMap.put("attributeCode", jsonLabel);
				eavAttributeMap.put("frontendInput", jsonInput);

				attributeList.add(eavAttributeMap);

			}

		} else if (value instanceof JSONArray) {
			handleJSONArray(key, (JSONArray) value);
		} else if (value instanceof JSONObject) {
			handleJSONObject((JSONObject) value);
		}

	}

	public static void handleJSONObject(JSONObject jsonObject) throws JSONException {
		Iterator<String> jsonObjectIterator = jsonObject.keys();
		jsonObjectIterator.forEachRemaining(key -> {
			Object value;
			try {
				value = jsonObject.get(key);
				handleValue(key, value);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		});
	}

	public static void handleJSONArray(String key, JSONArray jsonArray) throws JSONException {
		if (key != null && key.equalsIgnoreCase("data")) {
			for (int i = indexValue; i <= jsonArray.length() - 1; i++) {
				handleValue("final", jsonArray.getJSONObject(i));
			}

		} else {
			for (int i = 0; i < jsonArray.length(); i++) {
				handleValue(key, jsonArray.getJSONObject(i));
			}
		}
	}

}
