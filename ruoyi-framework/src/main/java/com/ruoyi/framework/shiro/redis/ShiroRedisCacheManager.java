package com.ruoyi.framework.shiro.redis;

import org.apache.shiro.ShiroException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ShiroRedisCacheManager implements CacheManager, Initializable, Destroyable {

    private Logger logger = LoggerFactory.getLogger(ShiroRedisCacheManager.class);
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();
    /**
     * cache存活时间
     */
    private Integer cacheLive;
    /**
     * cache前缀
     */
    private String cacheKeyPrefix;
    private RedisTemplate redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("get cache, name=" + name);
        return new ShiroRedisCache<K, V>(redisTemplate, cacheLive, cacheKeyPrefix, null, null);
    }

    public void setCacheLive(Integer cacheLive) {
        this.cacheLive = cacheLive;
    }

    public void setCacheKeyPrefix(String cacheKeyPrefix) {
        this.cacheKeyPrefix = cacheKeyPrefix;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void init() throws ShiroException {

    }
}