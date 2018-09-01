package com.icybiscuit.idol.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.icybiscuit.idol.utils.Constants.GET_FROM_REDIS;

@Component
public class RedisUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtil.class);

    private RedisTemplate<String, Object> redisTemplate;
    private ValueOperations valueOperations;

    @Autowired
    public RedisUtil(RedisTemplate<String, Object> redisTemplate, ValueOperations valueOperations) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = valueOperations;
    }

    public Object getFromRedis(String key) {
        boolean flag = redisTemplate.hasKey(key);

        if (flag) {
            Object obj = valueOperations.get(key);
            LOGGER.info(String.format(GET_FROM_REDIS, key, obj.toString()));
            return obj;
        }
        return null;
    }

    public void setIntoRedis(String key, Object o) {
        LOGGER.info(String.format(Constants.SET_INTO_REDIS, key, o.toString()));
        valueOperations.set(key, o, 1, TimeUnit.DAYS);
    }
}
