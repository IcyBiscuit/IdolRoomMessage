package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.RoomMsgRankMapper;
import com.icybiscuit.idol.entity.VO.RankVO;
import com.icybiscuit.idol.service.RankService;
import com.icybiscuit.idol.utils.Constants;
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

import static com.icybiscuit.idol.utils.Constants.GET_FROM_DB;
import static com.icybiscuit.idol.utils.Constants.GET_FROM_REDIS;

@Service
public class RankServiceImpl implements RankService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RankServiceImpl.class);

    private final RoomMsgRankMapper mapper;

    private final RedisTemplate<String, Object> redisTemplate;

    private final ValueOperations<String, Object> valueOperations;

    @Autowired
    public RankServiceImpl(RoomMsgRankMapper mapper, RedisTemplate<String, Object> redisTemplate, ValueOperations<String, Object> valueOperations) {
        this.mapper = mapper;
        this.redisTemplate = redisTemplate;
        this.valueOperations = valueOperations;
    }

    @Override
    public List<RankVO> getRankByType(String type) {
        String key = type + "_rank";

        Object obj = getFromRedis(key);
        if (obj != null) {
            return (List<RankVO>) obj;
        } else {
            List<RankVO> rankList = mapper.getRank(type);
            LOGGER.info(String.format(GET_FROM_DB, rankList.toString()));
            setIntoRedis(key, rankList);
            return rankList;
        }

    }


    @Override
    public Map<String, List<RankVO>> getAllRank() {
//        final String key = "rank";
        final String key = Constants.RANK_KEY;

        Object o = getFromRedis(key);
        if (o != null) {
            return (Map<String, List<RankVO>>) o;
        } else {
            List<String> msgTypes = getMsgTypes();
            Map<String, List<RankVO>> rankMap = new HashMap<>();

            for (String type : msgTypes
            ) {
//            String keyToBeSet = type + "_rank";
                String keyToBeSet = type + Constants.RANK_TYPE_SUFFIX;

                List<RankVO> rank;

                Object fromRedis = getFromRedis(keyToBeSet);

                if (fromRedis != null) {
                    rank = (List<RankVO>) fromRedis;
                    LOGGER.info(String.format(GET_FROM_REDIS, keyToBeSet, rank.toString()));

                } else {
                    rank = mapper.getRank(type);
//                    valueOperations.set(keyToBeSet, rank, 20, TimeUnit.MINUTES);
//                    LOGGER.info(String.format(SET_INTO_REDIS, keyToBeSet, rank.toString()));
                    setIntoRedis(keyToBeSet, rank);

                }

                rankMap.put(type, rank);
            }


//        String test = "live";
//        List<RankVO> rankList = mapper.getAllRank(test);

            LOGGER.info(String.format(GET_FROM_DB, rankMap.toString()));
            setIntoRedis(key, rankMap);
            return rankMap;
        }
    }

    @Override
    public List<String> getMsgTypes() {
        return mapper.getMsgTypes();
    }

    @Override
    public List<RankVO> getTeamMsgRank() {
        final String key = Constants.TEAM_RANK_KEY;

        if (redisTemplate.hasKey(key)) {
            Object o = valueOperations.get(Constants.TEAM_RANK_KEY);
            List<RankVO> teamRank = (List<RankVO>) o;
            LOGGER.info(String.format(Constants.GET_FROM_REDIS, key, teamRank.toString()));
            return teamRank;
        }

        List<RankVO> teamMsgCount = mapper.getMsgCountByTeam();
        LOGGER.info(String.format(Constants.GET_FROM_DB, teamMsgCount.toString()));

        setIntoRedis(key, teamMsgCount);
        return teamMsgCount;
    }


    @Override
    public List<RankVO> getLiveRank() {
        final String key = Constants.ALL_LIVE_RANK_KEY;

        if (redisTemplate.hasKey(key)) {
            Object o = valueOperations.get(Constants.ALL_LIVE_RANK_KEY);
            List<RankVO> liveRank = (List<RankVO>) o;
            LOGGER.info(String.format(Constants.GET_FROM_REDIS, key, liveRank.toString()));
            return liveRank;
        }
        List<RankVO> liveRank = mapper.getLiveRank();
        LOGGER.info(String.format(Constants.GET_FROM_DB, liveRank.toString()));


        setIntoRedis(key, liveRank);
        return liveRank;
    }

    private void setIntoRedis(String key, Object o) {
        valueOperations.set(key, o, 20, TimeUnit.MINUTES);
        LOGGER.info(String.format(Constants.SET_INTO_REDIS, key, o.toString()));
    }

    private Object getFromRedis(String key) {
        if (redisTemplate.hasKey(key)) {
            Object obj = valueOperations.get(key);
//            List<RankVO> list = (List<RankVO>) obj;
            LOGGER.info(String.format(GET_FROM_REDIS, key, obj.toString()));
            return obj;

        }
        return null;
    }
}
