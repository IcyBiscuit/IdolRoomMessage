package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.IdolInfoMapper;
import com.icybiscuit.idol.entity.DO.IdolInfoDO;
import com.icybiscuit.idol.service.IdolInfoService;
import com.icybiscuit.idol.utils.Constants;
import com.icybiscuit.idol.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.icybiscuit.idol.utils.Constants.GET_FROM_DB;

@Service
public class IdolInfoServiceImpl implements IdolInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdolInfoServiceImpl.class);
    //    private final RedisTemplate<String, Object> redisTemplate;
    private final IdolInfoMapper mapper;
    //    private final ValueOperations<String, Object> valueOperations;
    private RedisUtil redisUtil;

    @Autowired
    public IdolInfoServiceImpl(IdolInfoMapper mapper, RedisUtil redisUtil) {
        this.mapper = mapper;
        this.redisUtil = redisUtil;
    }


    @Override
    public List<IdolInfoDO> listAll() {
        final String key = Constants.LIST_MEMBER_KEY;

//        if (redisTemplate.hasKey(key)) {
//
//            Object list = valueOperations.get(key);
//            List<IdolInfoDO> idolInfoDOList = (List<IdolInfoDO>) list;
//            LOGGER.info(String.format(GET_FROM_REDIS, key, idolInfoDOList.toString()));
//
//            return idolInfoDOList;
//        }
        Object fromRedis = redisUtil.getFromRedis(key);
        if (fromRedis != null) {
            return (List<IdolInfoDO>) fromRedis;
        }

        List<IdolInfoDO> infoDOList = mapper.listAllInfo();
        LOGGER.info(String.format(GET_FROM_DB, infoDOList));

//        LOGGER.info(String.format(SET_INTO_REDIS, key, infoDOList.toString()));
//        valueOperations.set(key, infoDOList, 2, TimeUnit.HOURS);
        redisUtil.setIntoRedis(key, infoDOList);
        return infoDOList;
    }
}
