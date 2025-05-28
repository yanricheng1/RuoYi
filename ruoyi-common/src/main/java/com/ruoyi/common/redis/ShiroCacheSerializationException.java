package com.ruoyi.common.redis;

import org.springframework.data.redis.serializer.SerializationException;

public class ShiroCacheSerializationException extends SerializationException {
    public ShiroCacheSerializationException(String msg) {
        super(msg);
    }
    public ShiroCacheSerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
