package com.calsoft.pos.ruleengine.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MVELParser {

	public boolean parseMvelExpression(Map<String, Object> rule, Object in) {
		Map<String, Object> inputObjects = new HashMap<>();
		try {
			/** Construct the input to based on rule engine stardards */
			inputObjects.put("$", in);

			if (rule != null && rule.get("condition") != null) {
				String ruleCondition = rule.get("condition").toString();
				if(ruleCondition.trim().length()==0) {
					ruleCondition = "true";
				}
				ruleCondition = parseCondition(ruleCondition, in);
				// ruleCondition = ruleCondition.replace("=" , "==");
				return MVEL.evalToBoolean(ruleCondition, inputObjects);
			}
		} catch (Exception e) {
			e.printStackTrace();

			if (rule.get("condition").equals("$.null null \"null\"")) {
				String ruleCondition = "true";
				ruleCondition = parseCondition(ruleCondition, in);
				// ruleCondition = ruleCondition.replace("=" , "==");
				return MVEL.evalToBoolean(ruleCondition, inputObjects);
			}

		}
		return false;
	}

	private String parseCondition(String ruleCondition, Object inputObjects) {
		if (ruleCondition.contains("~CI")) {
			Map<String, String> result = serlizeCondition(ruleCondition, "~CI");
			for (Map.Entry<String, String> entry : result.entrySet()) {
				String condition = entry.getKey();
				String value = entry.getValue();
				ruleCondition = parseCIConidtion(ruleCondition, inputObjects, condition, value, "~CI");
			}
		}
		return ruleCondition;
	}

	private Map<String, String> serlizeCondition(String condition, String expression) {
		String[] array = condition.split(" ");
		HashMap<String, String> map = new HashMap<String, String>();
		int i = 0;
		for (String var : array) {
			if (var.contains("$.")) {

				if (array[i + 1].contains(expression)) {

					if (map.get(var) == null) {
						map.put(var, "");
					}
				}
			} else if (array[i - 1].contains(expression)) {
				if (map.get(array[i - 2]) != null) {
					String value = null;
					String condition11 = map.get(array[i - 2]);
					if (map.get(array[i - 2]).equalsIgnoreCase(" ")) {
						value = var;
					} else {
						value = map.get(array[i - 2]) + var;
					}
					map.put(array[i - 2], value);
				}

			}
			i++;
		}
		return map;
	}

	private String parseCIConidtion(String ruleCondition, Object inputObjects, String condition, String value,
			String expression) {
		
		
		@SuppressWarnings("unchecked")
		List<Object> inputData = getValueFromInput(condition, inputObjects);
		System.out.println(inputData);
		boolean status = validateCI(value, inputData);
		String temp = condition + " " + expression + " " + value;
		ruleCondition = ruleCondition.replace(temp, status + "");
		return ruleCondition;
	}

	private boolean validateCI(String value, List inputData) {
		List<String> inputDataTransformed = Lists.transform(inputData, Functions.toStringFunction());
		value = value.replace("'", "").trim();
		List<String> list = new ArrayList<>(Arrays.asList(value.split(",")));
		for (String l : list) {
			l = l.replace("\"", "");
			if (!inputDataTransformed.contains(l)) {
				return false;
			}
		}
		return true;
	}

	private List getValueFromInput(String condition, Object inputObjects) {
		Gson gson = new Gson();
		String tempVarC = condition.trim();
		String tempVarIP = gson.toJson(inputObjects);
		List data = JsonPath.read(tempVarIP, tempVarC);
		return data;
	}

	public Object parseMvelExpression(String condition, Map<String, Object> inputObjects) {
		try {
			return MVEL.eval(condition, inputObjects);
		} catch (Exception e) {
			log.error("Can not parse Mvel Expression : {} Error: {}" + condition + "for Input" + inputObjects, e);
		}
		return null;
	}
}
