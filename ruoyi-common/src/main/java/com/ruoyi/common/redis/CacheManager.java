package com.ruoyi.common.redis;

public interface CacheManager {
    <K, V> RedisCache<K, V> getCache(String name);
}
