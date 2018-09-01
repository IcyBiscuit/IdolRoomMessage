package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.RoomMsgRankMapper;
import com.icybiscuit.idol.entity.VO.RankVO;
import com.icybiscuit.idol.service.RankService;
import com.icybiscuit.idol.utils.Constants;
import com.icybiscuit.idol.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.icybiscuit.idol.utils.Constants.GET_FROM_DB;
import static com.icybiscuit.idol.utils.Constants.GET_FROM_REDIS;

@Service
public class RankServiceImpl implements RankService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RankServiceImpl.class);

    private final RoomMsgRankMapper mapper;

    private RedisUtil redisUtil;

    @Autowired
    public RankServiceImpl(RoomMsgRankMapper mapper, RedisUtil redisUtil) {
        this.mapper = mapper;
        this.redisUtil = redisUtil;
    }

    @Override
    public List<RankVO> getRankByType(String type) {
        String key = type + "_rank";

        Object obj = redisUtil.getFromRedis(key);
        if (obj != null) {
            return (List<RankVO>) obj;
        } else {
            List<RankVO> rankList = mapper.getRank(type);
            LOGGER.info(String.format(GET_FROM_DB, rankList.toString()));
            redisUtil.setIntoRedis(key, rankList);
            return rankList;
        }

    }


    @Override
    public Map<String, List<RankVO>> getAllRank() {

        String key = Constants.RANK_KEY;

        Object o = redisUtil.getFromRedis(key);
        if (o != null) {
            return (Map<String, List<RankVO>>) o;
        } else {
            List<String> msgTypes = getMsgTypes();
            Map<String, List<RankVO>> rankMap = new HashMap<>();

            for (String type : msgTypes) {

                String keyToBeSet = type + Constants.RANK_TYPE_SUFFIX;

                List<RankVO> rank;

                Object fromRedis = redisUtil.getFromRedis(keyToBeSet);

                if (fromRedis != null) {
                    rank = (List<RankVO>) fromRedis;
                    LOGGER.info(String.format(GET_FROM_REDIS, keyToBeSet, rank.toString()));

                } else {
                    rank = mapper.getRank(type);
                    redisUtil.setIntoRedis(keyToBeSet, rank);

                }

                rankMap.put(type, rank);
            }

            LOGGER.info(String.format(GET_FROM_DB, rankMap.toString()));
            redisUtil.setIntoRedis(key, rankMap);
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

        Object fromRedis = redisUtil.getFromRedis(key);
        if (fromRedis != null) {
            return (List<RankVO>) fromRedis;
        } else {
            List<RankVO> teamMsgCount = mapper.getMsgCountByTeam();
            LOGGER.info(String.format(Constants.GET_FROM_DB, teamMsgCount.toString()));

            redisUtil.setIntoRedis(key, teamMsgCount);
            return teamMsgCount;
        }

    }

    @Override
    public List<RankVO> getLiveRank() {
        final String key = Constants.ALL_LIVE_RANK_KEY;

        Object fromRedis = redisUtil.getFromRedis(key);
        if (fromRedis != null) {

            return (List<RankVO>) fromRedis;
        } else {

            List<RankVO> liveRank = mapper.getLiveRank();
            LOGGER.info(String.format(Constants.GET_FROM_DB, liveRank.toString()));

            redisUtil.setIntoRedis(key, liveRank);
            return liveRank;
        }
    }
}
