package com.ruoyi.common.redis;

import java.util.Collection;
import java.util.Set;

public interface RedisCache<K, V> {
    V get(K key) throws RuntimeException;

    V put(K k, V v) throws RuntimeException;

    V remove(K k) throws RuntimeException;

    void clear() throws RuntimeException;

    int size();

    Set<K> keys();

    Collection<V> values();
}
