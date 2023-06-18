package com.calsoft.pos.ruleengine.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RuleEngine {

	MVELParser mvelParser;
	JwayParser jwayParser;

	public RuleEngine(MVELParser mvelParser, JwayParser jwayParser) {
		this.mvelParser = mvelParser;
		this.jwayParser = jwayParser;
	}

	// @Cacheable( value = "cachekey", key = "#decoded")
	public Object execute(String inputString) throws JsonMappingException, JsonProcessingException {
		// log.debug("Redis cache is called for " + decoded);

		Gson gson = new Gson();

		ObjectMapper mapper = new ObjectMapper();

		/** To get the rule and input object from the requests */
		Map inputObj = mapper.readValue(inputString, Map.class);
		Map<String, Object> rule = (HashMap<String, Object>) inputObj.get("rule");
		Object in = inputObj.get("input");

		/** Execute the expression in rule engine */
		boolean result = mvelParser.parseMvelExpression(rule, in);

		if (result) {
			/** Apply action based on the rule for response object */
			return jwayParser.parseJwayExpression(rule, in);
		}
		return in;
	}

	public Object execute1(String inputString) throws JsonMappingException, JsonProcessingException {
		Gson gson = new Gson();

		ObjectMapper mapper = new ObjectMapper();

		/** To get the rule and input object from the requests */
		Map inputObj = mapper.readValue(inputString, Map.class);
		
		List<Map<String, Object>> rules = (List<Map<String, Object>>) inputObj.get("rule");
		Object in = inputObj.get("input");

		// todo sorting iteration

		for (Map<String, Object> rule : rules) {

			/** Execute the expression in rule engine */
			boolean result = mvelParser.parseMvelExpression(rule, in);
			if (result) {
				/** Apply action based on the rule for response object */

				Object result1 = jwayParser.parseJwayExpression(rule, in);

				gson = new Gson();

				Map<String, Object> mapObj1 = gson.fromJson(result1.toString(), Map.class);

				mapObj1.put("discountPercent", rule.get("discountPercent"));
				result1 = mapObj1;
				return result1;
			}
		}

		return in;
	}

	public Object requestParser(Object inputObject) {
		return inputObject;
	}
}
