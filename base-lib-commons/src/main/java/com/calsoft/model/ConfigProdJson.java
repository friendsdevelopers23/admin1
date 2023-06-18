package com.calsoft.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ConfigProdJson {

	private String key;
	private String value;
	private Long productId;
	private int configAttributeType;
	private List<HashMap<String, Object>> recursuive = new ArrayList<HashMap<String, Object>>();
}
