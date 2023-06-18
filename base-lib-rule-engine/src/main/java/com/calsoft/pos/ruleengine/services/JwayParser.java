package com.calsoft.pos.ruleengine.services;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwayParser {

    MVELParser mvelParser;

    public JwayParser(MVELParser mvelParser) {
        this.mvelParser = mvelParser;
    }

    Configuration configuration = Configuration.builder().build();

    public Object parseJwayExpression(Map<String,Object> rule, Object in) {

        /** Construct the input to based on rule engine stardards*/
        Map<String, Object> inputObjects = new HashMap<>();
        inputObjects.put("$", in);

        String response = new Gson().toJson(in);
        DocumentContext json = JsonPath.using(configuration).parse(in);
        String actionStr = rule.get("action").toString();
        JSONArray thens = new JSONArray(actionStr);
        if(thens != null) {
            for(Object then : thens) {
                try {
                    response = applyActions(then, json , inputObjects);
                    json = JsonPath.using(configuration).parse(response);
                } catch (Exception e) {
                    log.error("unabled to apply action for the rule "+ then + " input as" + inputObjects, e);
                }
            }
        }
        return response;
    }

    public String applyActions(Object then, DocumentContext json, Map<String, Object> inputObjects) {
        String[] abc = then.toString().split("==");
        String keyToReplace = abc[0].trim();
        String valueToBeReplace = abc[1].trim();
        if(valueToBeReplace.contains("$.")) {
            return evalExpression(keyToReplace, valueToBeReplace, json, inputObjects);
        } else {
            return applyActions(keyToReplace, valueToBeReplace, json);
        }
    }

    public String applyActions(String keyToReplace, Object valueToBeReplace, DocumentContext json) {
        return json.set(keyToReplace, valueToBeReplace).jsonString();
    }

    public String evalExpression(String keyToReplace, String valueToBeReplace, DocumentContext json, Map<String, Object> inputObjects) {
        Object stmg = mvelParser.parseMvelExpression(valueToBeReplace, inputObjects);
        if(stmg != null) {
            return json.set(keyToReplace, stmg).jsonString();
        } else {
            return json.jsonString();
        }
    }
}
