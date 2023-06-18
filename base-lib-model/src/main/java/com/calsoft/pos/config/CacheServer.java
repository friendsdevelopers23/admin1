package com.calsoft.pos.config;

import java.util.Set;

public interface CacheServer {
	Object get(String key);

	void set(String key, Object value);

	void setExpiryTime(String key, Object value, Long refreshTokenValidity);

	void deleteByKey(String key);

	Set<String> getListOfKeys(String keyFormat);

}
