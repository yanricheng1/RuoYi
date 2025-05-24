package com.ruoyi.framework.shiro.redis;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.ruoyi.common.utils.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ShiroRedisCache<K, V> implements Cache<K, V> {

    public static String DEFAULT_CACHE_KEY_PREFIX = "shiro:cache:";
    public static Integer DEFAULT_EXPIRE = 1800;
    private final Integer cacheLive;
    private final String cacheKeyPrefix;
    private final RedisTemplate redisTemplate;
    private final RedisSerializer keySerializer;
    private final RedisSerializer valueSerializer;
    private final Logger logger = LoggerFactory.getLogger(ShiroRedisCache.class);


    public ShiroRedisCache(RedisTemplate redisTemplate, Integer cacheLive, String cachePrefix,
                           RedisSerializer keySerializer, RedisSerializer valueSerializer) {

        this.redisTemplate = redisTemplate;

        if (cacheLive == null) {
            this.cacheLive = DEFAULT_EXPIRE;
        } else {
            this.cacheLive = cacheLive;
        }

        if (StringUtils.isBlank(cachePrefix)) {
            this.cacheKeyPrefix = DEFAULT_CACHE_KEY_PREFIX;
        } else {
            this.cacheKeyPrefix = cachePrefix;
        }

        if (keySerializer == null) {
            this.keySerializer = new StringSerializer();
        } else {
            this.keySerializer = keySerializer;
        }


        if (valueSerializer == null) {
            this.valueSerializer = new GenericFastJsonRedisSerializer();
        } else {
            this.valueSerializer = valueSerializer;
        }

        redisTemplate.setKeySerializer(this.keySerializer);
        redisTemplate.setValueSerializer(this.valueSerializer);
        redisTemplate.setHashValueSerializer(this.valueSerializer);
    }

    @Override
    public V get(K key) throws CacheException {
        if (logger.isDebugEnabled()) {
            logger.debug("get key:{}", this.keySerializer.serialize(key));
        }

        if (key == null) {
            return null;
        }

        return (V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(key));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (logger.isDebugEnabled()) {
            logger.debug("put key:{},value:{}", this.keySerializer.serialize(k), this.valueSerializer.serialize(v));
        }

        if (k == null) {
            return v;
        }

        redisTemplate.opsForValue().set(this.getRedisCacheKey(k), v, cacheLive, TimeUnit.MINUTES);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove key:{}", this.keySerializer.serialize(k));
        }

        if (k == null) {
            return null;
        }

        V value = (V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(k));
        redisTemplate.delete(this.getRedisCacheKey(k));
        return value;
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("clear cache");
        Set keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");
        if (null != keys && keys.size() > 0) {
            Iterator itera = keys.iterator();
            this.redisTemplate.delete(itera.next());
        }
    }

    @Override
    public int size() {
        Set<K> keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");
        return keys.size();
    }

    @Override
    public Set<K> keys() {
        return this.redisTemplate.keys(this.cacheKeyPrefix + "*");
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = this.redisTemplate.keys(this.cacheKeyPrefix + "*");

        if (keys == null) {
            return Collections.emptySet();
        }

        Set<V> values = new HashSet<V>(keys.size());
        for (K key : keys) {
            values.add((V) this.redisTemplate.opsForValue().get(this.getRedisCacheKey(key)));
        }
        return values;
    }

    private String getRedisCacheKey(K key) {
        Object redisKey = this.getStringRedisKey(key);
        if (redisKey instanceof String) {
            return this.cacheKeyPrefix + redisKey;
        } else {
            return String.valueOf(redisKey);
        }
    }

    private Object getStringRedisKey(K key) {
        Object redisKey;
        if (key instanceof PrincipalCollection) {
            redisKey = this.getRedisKeyFromPrincipalCollection((PrincipalCollection) key);
        } else {
            redisKey = key.toString();
        }
        return redisKey;
    }

    private Object getRedisKeyFromPrincipalCollection(PrincipalCollection key) {
        List realmNames;
        realmNames = this.getRealmNames(key);
        Collections.sort(realmNames);
        Object redisKey = this.joinRealmNames(realmNames);
        return redisKey;
    }

    private List<String> getRealmNames(PrincipalCollection key) {
        ArrayList realmArr = new ArrayList();
        Set realmNames = key.getRealmNames();
        Iterator it = realmNames.iterator();
        while (it.hasNext()) {
            String realmName = (String) it.next();
            realmArr.add(realmName);
        }
        return realmArr;
    }

    private Object joinRealmNames(List<String> realmArr) {
        StringBuilder redisKeyBuilder = new StringBuilder();
        for (int i = 0; i < realmArr.size(); ++i) {
            String s = realmArr.get(i);
            redisKeyBuilder.append(s);
        }
        String redisKey = redisKeyBuilder.toString();
        return redisKey;
    }
}