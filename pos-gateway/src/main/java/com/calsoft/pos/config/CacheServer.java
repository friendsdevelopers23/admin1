package com.calsoft.pos.config;

public interface CacheServer {
    Object get(String key);
    void set(String key, Object value);
}
