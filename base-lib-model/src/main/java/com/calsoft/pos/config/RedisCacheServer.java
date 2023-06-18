package com.calsoft.pos.config;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "calsoft.config.cache.type", havingValue = "redis")
public class RedisCacheServer implements CacheServer {

	@Autowired
	RedisTemplate redisTemplate;

	@Override
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void setExpiryTime(String key, Object value, Long expiryMinutes) {
		redisTemplate.opsForValue().set(key, value, expiryMinutes, TimeUnit.MINUTES);
	}

	@Override
	public void deleteByKey(String key) {

		redisTemplate.delete(key);

	}

	@Override
	public Set<String> getListOfKeys(String keyFormat) {

		return redisTemplate.keys(keyFormat);
	}
}
