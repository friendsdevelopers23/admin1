package com.calsoft.utils;

import java.util.Arrays;

public enum CategoryImgTypes {

	SMALL_IMAGE("small_image", "small_image/70x70"), IMAGE("image", "image/150x150");

	private String key;
	private String value;

	private CategoryImgTypes(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static String getValueByKey(String key) {
		return String.valueOf(Arrays.stream(CategoryImgTypes.values())
				.filter(value -> value.key.equalsIgnoreCase(value.getKey())).findFirst().orElse(null));
	}

	public static String searchValue(String search) {
		for (CategoryImgTypes el : CategoryImgTypes.values()) {

			return el.value;

		}
		return null;
	}
}
