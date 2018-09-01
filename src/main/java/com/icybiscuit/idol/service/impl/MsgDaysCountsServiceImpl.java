package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.MsgDaysCountsMapper;
import com.icybiscuit.idol.entity.DO.MsgDaysCounts;
import com.icybiscuit.idol.service.MsgDaysCountsService;
import com.icybiscuit.idol.utils.Constants;
import com.icybiscuit.idol.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MsgDaysCountsServiceImpl implements MsgDaysCountsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgDaysCountsServiceImpl.class);

    private static final String[] teamList = Constants.TEAMS;
    private final MsgDaysCountsMapper mapper;
    private RedisUtil redisUtil;

    @Autowired
    public MsgDaysCountsServiceImpl(MsgDaysCountsMapper mapper, RedisUtil redisUtil) {
        this.mapper = mapper;
        this.redisUtil = redisUtil;
    }

    @Override
    public Map<String, List<MsgDaysCounts>> getDaysCounts() {

        String key = Constants.DAY_COUNTS_KEY;

        Object objFromRedis = redisUtil.getFromRedis(key);

        if (objFromRedis != null) {

            return (Map<String, List<MsgDaysCounts>>) objFromRedis;
        } else {
            Map<String, List<MsgDaysCounts>> map = new HashMap<>();

            for (String team : teamList) {

                String keyToBeSet = team + Constants.TEAM_DAYS_COUNTS_SUFFIX;

                List<MsgDaysCounts> daysCounts;

                Object fromRedis = redisUtil.getFromRedis(keyToBeSet);

                if (fromRedis != null) {

                    daysCounts = (List<MsgDaysCounts>) fromRedis;
                } else {

                    daysCounts = this.getDaysCounts(team);
                    LOGGER.info(String.format(Constants.GET_FROM_DB, daysCounts.toString()));
                }
                map.put(team, daysCounts);
            }
            redisUtil.setIntoRedis(key, map);
            return map;
        }
    }

    @Override
    public List<MsgDaysCounts> getDaysCounts(String team) {

        String key = team + Constants.TEAM_DAYS_COUNTS_SUFFIX;
        Object fromRedis = redisUtil.getFromRedis(key);

        if (fromRedis != null) {
            return (List<MsgDaysCounts>) fromRedis;
        }

        List<MsgDaysCounts> daysCounts = mapper.getDaysCounts(team);
        LOGGER.info(String.format(Constants.GET_FROM_DB, daysCounts.toString()));
        redisUtil.setIntoRedis(key, daysCounts);

        return daysCounts;
    }
}
