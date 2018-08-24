package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.RoomMsgRankMapper;
import com.icybiscuit.idol.entity.VO.RankVO;
import com.icybiscuit.idol.service.RankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RankServiceImpl implements RankService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RankServiceImpl.class);
    @Autowired
    RoomMsgRankMapper mapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ValueOperations<String, Object> valueOperations;

    @Override
    public List<RankVO> getRankByType(String type) {
        String key = type + "_rank";

        if (redisTemplate.hasKey(key)) {
            Object obj = valueOperations.get(key);
            List<RankVO> list = (List<RankVO>) obj;
            LOGGER.info(String.format("get from redis key: %s, value: %s", key, list.toString()));
            return list;

        }
        List<RankVO> rankList = mapper.getRank(type);
        LOGGER.info(String.format("get rank from %s, value: %s", "database", rankList.toString()));
        valueOperations.set(key, rankList, 20, TimeUnit.MINUTES);
        LOGGER.info(String.format("set into redis, key: %s, values: %s", key, rankList.toString()));
        return rankList;
    }

    @Override
    public Map<String, List<RankVO>> getAllRank() {
        final String key = "rank";
        if (redisTemplate.hasKey(key)) {
            Object obj = valueOperations.get(key);
            Map<String, List<RankVO>> map = (Map<String, List<RankVO>>) obj;
            LOGGER.info(String.format("get from redis key: %s, value: %s", key, map.toString()));
            return map;

        }

//        TODO get msg type list
        List<String> msgTypes = getMsgTypes();
        Map<String, List<RankVO>> rankMap = new HashMap<>();

        for (String type : msgTypes
        ) {
            String keyToBeSet = type + "_rank";
            List<RankVO> rank;

            if (redisTemplate.hasKey(keyToBeSet)) {
                rank = (List<RankVO>) valueOperations.get(keyToBeSet);
                LOGGER.info(String.format("get from redis, key: %s, values: %s", keyToBeSet, rank.toString()));

            } else {
                rank = mapper.getRank(type);
                valueOperations.set(keyToBeSet, rank, 20, TimeUnit.MINUTES);
                LOGGER.info(String.format("set into redis, key: %s, values: %s", keyToBeSet, rank.toString()));

            }

            rankMap.put(type, rank);
        }


//        String test = "live";
//        List<RankVO> rankList = mapper.getAllRank(test);

        LOGGER.info(String.format("get all rank from %s, value: %s", "database", rankMap.toString()));
        valueOperations.set(key, rankMap, 20, TimeUnit.MINUTES);
        LOGGER.info(String.format("set into redis, key: %s, values: %s", key, rankMap.toString()));
        return rankMap;
    }

    @Override
    public List<String> getMsgTypes() {
        return mapper.getMsgTypes();
    }
}
