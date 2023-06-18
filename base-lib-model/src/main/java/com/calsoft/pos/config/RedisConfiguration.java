package com.calsoft.pos.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class RedisConfiguration {

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.host}")
	private String hostName;

	@Value("${spring.redis.password}")
	private String password;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(hostName);
		redisStandaloneConfiguration.setPort(port);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));

		JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout

		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
				jedisClientConfiguration.build());
		return jedisConFactory;

	}

	@Bean
	@Primary
	public RedisTemplate<String, Object> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
	}
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer(getMapper());
    }
    
    public ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
		/*
		 * mapper = new
		 * ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
		 * true); mapper.enable(SerializationFeature.INDENT_OUTPUT);
		 * mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		 * mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
		 * //mapper.enable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		 * mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS,true);
		 * mapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 * // Registering Hibernate5Module to support lazy objects for hibernate 5 //
		 * Use Hibernate4Module if using hibernate 4 mapper.registerModule(new
		 * Hibernate5Module());
		 */
        return mapper;
    }
	
    @Bean
    public CacheManager cacheManager(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(12))
                //.prefixKeysWith("svcadmin::")
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
                .disableCachingNullValues();
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration)
                .transactionAware()
                .build();
    }

}