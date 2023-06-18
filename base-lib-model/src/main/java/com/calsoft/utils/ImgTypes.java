package com.calsoft.utils;

import java.util.Arrays;

public enum ImgTypes {
	SMALL_IMAGE("small_image", "small_image/200x170"), IMAGE("image", "image/600x600"),
	THUMBNAIL("thumbnail", "thumbnail/75x75");

	private String key;
	private String value;

	private ImgTypes(String key, String value) {
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
		return String.valueOf(Arrays.stream(ImgTypes.values())
				.filter(value -> value.key.equalsIgnoreCase(value.getKey())).findFirst().orElse(null));
	}

	public static String searchValue(String search) {
		for (ImgTypes el : ImgTypes.values()) {

			return el.value;

		}
		return null;
	}
}