package com.ruoyi.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroRedisCacheManager implements CacheManager {

    private Logger logger = LoggerFactory.getLogger(ShiroRedisCacheManager.class);
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
    public <K, V> RedisCache<K, V> getCache(String name) {
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

    public void destroy() throws Exception {

    }

    public void init() throws Exception {

    }
}