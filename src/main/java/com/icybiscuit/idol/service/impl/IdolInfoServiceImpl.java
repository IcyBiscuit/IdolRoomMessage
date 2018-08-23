package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.IdolInfoMapper;
import com.icybiscuit.idol.entity.DO.IdolInfoDO;
import com.icybiscuit.idol.service.IdolInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class IdolInfoServiceImpl implements IdolInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdolInfoServiceImpl.class);

    @Autowired
    private IdolInfoMapper mapper;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    @Qualifier("myRedisTemplate")
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<IdolInfoDO> listAll() {
        final String key = "memberList";

        if (redisTemplate.hasKey(key)) {

            Object list = valueOperations.get(key);
            List<IdolInfoDO> idolInfoDOList = (List<IdolInfoDO>) list;
            LOGGER.info("get from redis >>> key: " + key + ", value: " + idolInfoDOList.toString());

            return idolInfoDOList;
        }

        List<IdolInfoDO> infoDOList = mapper.listAllInfo();
        LOGGER.info(String.format("set into redis >>> key: %s , value: %s", key, infoDOList.toString()));
        valueOperations.set(key, infoDOList, 10L, TimeUnit.MINUTES);
        return infoDOList;
    }
}
