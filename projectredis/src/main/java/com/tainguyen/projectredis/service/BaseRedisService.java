package com.tainguyen.projectredis.service;

import java.time.Duration;

public interface BaseRedisService {
    void set(String key, Object value, Duration timeout);

    Object get(String key);

    void delete(String key);
}
