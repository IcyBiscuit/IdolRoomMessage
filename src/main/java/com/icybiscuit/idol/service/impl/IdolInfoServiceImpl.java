package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.IdolInfoMapper;
import com.icybiscuit.idol.entity.DO.IdolInfoDO;
import com.icybiscuit.idol.service.IdolInfoService;
import com.icybiscuit.idol.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.icybiscuit.idol.utils.Constants.*;

@Service
public class IdolInfoServiceImpl implements IdolInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdolInfoServiceImpl.class);
    private final RedisTemplate<String, Object> redisTemplate;
    private final IdolInfoMapper mapper;
    private final ValueOperations<String, Object> valueOperations;

    @Autowired
    public IdolInfoServiceImpl(@Qualifier("myRedisTemplate") RedisTemplate<String, Object> redisTemplate, IdolInfoMapper mapper, ValueOperations<String, Object> valueOperations) {
        this.redisTemplate = redisTemplate;
        this.mapper = mapper;
        this.valueOperations = valueOperations;
    }

    @Override
    public List<IdolInfoDO> listAll() {
//        final String key = "memberList";
        final String key = Constants.LIST_MEMBER_KEY;

        if (redisTemplate.hasKey(key)) {

            Object list = valueOperations.get(key);
            List<IdolInfoDO> idolInfoDOList = (List<IdolInfoDO>) list;
            LOGGER.info(String.format(GET_FROM_REDIS, key, idolInfoDOList.toString()));

            return idolInfoDOList;
        }
        List<IdolInfoDO> infoDOList = mapper.listAllInfo();
        LOGGER.info(String.format(GET_FROM_DB, infoDOList));

        LOGGER.info(String.format(SET_INTO_REDIS, key, infoDOList.toString()));
        valueOperations.set(key, infoDOList, 2, TimeUnit.HOURS);
        return infoDOList;
    }
}
