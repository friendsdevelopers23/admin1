package com.calsoft.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.solr.client.solrj.util.ClientUtils;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SolrUtlis {

	public static String OPEN_BRACKET = "(";
	public static String CLOSE_BRACKET = ")";
	public static String AND_OPERATOR = " AND ";
	public static String OR_OPERATOR = " OR ";
	public static String HASH_SEPARATOR = "#";
	public static String sTOP_SEPARATOR = "\"";
	public static String HARD_CODED_VALUE = "{!complexphrase inOrder=true}";

	public static Criteria createCondition(String specs, String tenantId) throws UnsupportedEncodingException {

		specs = specs.replace("%", "BREPRE");
		String specs1 = URLDecoder.decode(specs, "UTF-8");

		specs1 = specs1.replace("BREPRE", "%");
		specs1 = specs1.replaceAll("' OR ", "' OR' ");
		specs1 = specs1.replaceAll(" AND ", "' AND' ");
		String[] conditon = specs1.split("' ");

		System.out.println(conditon);

		StringBuffer conditionsStr = new StringBuffer();
		int i = 0;
		for (String var : conditon) {

			if (var.contains("AND") && var.length() == 3) {
				conditionsStr.append(AND_OPERATOR);
			} else if (var.contains("OR") && var.length() == 2) {
				conditionsStr.append(OR_OPERATOR);
			} else if (var.contains("~")) {
				String[] dateCondtion = var.split(":");

				StringBuilder sb = new StringBuilder(dateCondtion[1]);

				// Removing the last character
				// of a string
				sb.deleteCharAt(dateCondtion[1].length() - 1);

				// Removing the first character
				// of a string
				sb.deleteCharAt(0);

				String[] dateValue = sb.toString().split("~");
				String min = dateValue[0].toString();
				String max = dateValue[1];
				java.sql.Date fromDateSQL = null;
				java.sql.Date toDateSQL = null;
				java.sql.Date temp = null;
				java.sql.Date temp1 = null;
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();

				try {
					java.util.Date fromDate = sdf1.parse(min);
					java.util.Date ToDate = sdf1.parse(max);
					fromDateSQL = new java.sql.Date(fromDate.getTime());
					toDateSQL = new java.sql.Date(ToDate.getTime());
					temp = toDateSQL;
					temp1 = fromDateSQL;
					if (fromDateSQL.compareTo(toDateSQL) > 0) {
						c.setTime(sdf1.parse(min));
						c.add(Calendar.DAY_OF_MONTH, 1);
						String newDate = sdf1.format(c.getTime());
						fromDateSQL = temp;
						ToDate = sdf1.parse(newDate);
						toDateSQL = new java.sql.Date(ToDate.getTime());

					} else {
						c.setTime(sdf1.parse(max));
						c.add(Calendar.DAY_OF_MONTH, 1);
						String newDate = sdf1.format(c.getTime());
						System.out.println(newDate);
						ToDate = sdf1.parse(newDate);
						toDateSQL = new java.sql.Date(ToDate.getTime());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				conditionsStr.append(dateCondtion[0]).append(":");
				conditionsStr.append("[").append(fromDateSQL).append("T00:00:00Z").append(" TO ").append(toDateSQL)
						.append("T00:00:00Z").append("]");
			} else if (var.contains(":")) {
				String[] value = var.split(":");

				String key = (StringUtils.isEmpty(value[0].trim()) ? "*" : ClientUtils.escapeQueryChars(value[0]));

				key = key.replace("\\", "");

				String[] value1 = value[1].split("\\*");

				String finalValue = value1[0].equalsIgnoreCase("") ? value1[1] : value1[0];

				System.out.println(value1.length);

				String val = (StringUtils.isEmpty(finalValue) ? "*" : ClientUtils.escapeQueryChars(finalValue.trim()));

				boolean isNumeric = val.chars().allMatch(Character::isDigit);

				if (val.equalsIgnoreCase(">0")) {
					val = "[1 TO *]";

				} else if (isNumeric) {
					val = CommonUtils.solrEscapeText(val);
				} else {
					val =  "*" + CommonUtils.solrEscapeText(val) + "*";

				}

				if (value1.length == 3) {

					if (value1[2].equalsIgnoreCase(")")) {
						val = val + ")";
					}

					if (value1[2].equalsIgnoreCase("))")) {
						val = val + "))";
					}
				}

				conditionsStr.append(OPEN_BRACKET);
				conditionsStr.append(key).append(":").append(CommonUtils.solrEscapeText(val));
				conditionsStr.append(CLOSE_BRACKET);

				i++;
			}

		}

		conditionsStr.insert(0, OPEN_BRACKET);
		conditionsStr.append(CLOSE_BRACKET);
		conditionsStr.append(AND_OPERATOR);

		solrFieldPlacer(conditionsStr, "tenantId", tenantId);
		String cond = conditionsStr.toString();
		// cond = cond.replaceAll("'", "");
		log.info(cond);

		cond = cond.replace("?asasfas?", "?AND?");
		cond = cond.replace("?bhhasfas?", "?OR?");

		return new SimpleStringCriteria(cond);

	}

	private static void solrFieldPlacer(StringBuffer conditionsStr, String kee, String valu) {
		conditionsStr.append(OPEN_BRACKET);
		conditionsStr.append(kee).append(":").append("*" + valu + "*");
		conditionsStr.append(CLOSE_BRACKET);
	}

	public static Criteria solrFieldPlacerTenantID(String key, String value) {
		StringBuffer conditionsStr = new StringBuffer();
		conditionsStr.append(OPEN_BRACKET);
		conditionsStr.append(key).append(":").append("*" + value + "*");
		conditionsStr.append(CLOSE_BRACKET);

		return new SimpleStringCriteria(conditionsStr.toString());

	}

}
