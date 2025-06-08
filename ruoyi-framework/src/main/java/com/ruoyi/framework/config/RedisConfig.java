package com.ruoyi.framework.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;


@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer defaultRedisSerializer = springSessionDefaultRedisSerializer();


        ObjectMapper mapper = null;
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        Field mapperField = ReflectionUtils.findField(jackson2JsonRedisSerializer.getClass(), "mapper");
        if (mapperField != null) {
            try {
                mapper = (ObjectMapper) mapperField.get(jackson2JsonRedisSerializer);

            } catch (IllegalAccessException ignore) {
            }
        }

        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

//        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper());
//        Field mapperField = ReflectionUtils.findField(jackson2JsonRedisSerializer.getClass(), "mapper");
//        if (mapperField != null) {
//            try {
//                ObjectMapper mapper = (ObjectMapper) mapperField.get(jackson2JsonRedisSerializer);
//                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
//            } catch (IllegalAccessException ignore) {
//            }
//        }

        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer<String> stringRedisSerializer = RedisSerializer.string();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setDefaultSerializer(defaultRedisSerializer);

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 解决Spring Session中文乱码
     *
     * @return
     */
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//        return new GenericFastJsonRedisSerializer();
        ObjectMapper mapper = null;
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        Field mapperField = ReflectionUtils.findField(jackson2JsonRedisSerializer.getClass(), "mapper");
        if (mapperField != null) {
            try {
                mapper = (ObjectMapper) mapperField.get(jackson2JsonRedisSerializer);

            } catch (IllegalAccessException ignore) {
            }
        }

        if (mapper == null) {
            mapper = new ObjectMapper();
        }

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        return new GenericJackson2JsonRedisSerializer(mapper);

    }


//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = null;
//        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//        Field mapperField = ReflectionUtils.findField(jackson2JsonRedisSerializer.getClass(), "mapper");
//        if (mapperField != null) {
//            try {
//                mapper = (ObjectMapper) mapperField.get(jackson2JsonRedisSerializer);
//
//            } catch (IllegalAccessException ignore) {
//            }
//        }
//
//        if (mapper == null) {
//            mapper = new ObjectMapper();
//        }
//
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
//
//        return mapper;
//    }

}
