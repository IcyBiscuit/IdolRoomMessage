package com.icybiscuit.idol.tasks;

import com.icybiscuit.idol.dao.mapper.RoomMsgRankMapper;
import com.icybiscuit.idol.entity.VO.RankVO;
import com.icybiscuit.idol.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTask.class);

    //    private RankService rankService;
    private final RoomMsgRankMapper mapper;
    private final ValueOperations valueOperations;

    @Autowired
    public AsyncTask(RoomMsgRankMapper mapper, ValueOperations valueOperations) {
        this.mapper = mapper;
        this.valueOperations = valueOperations;
    }


    @Async
    public Future<Boolean> getTeamRank() {

//        List<RankVO> teamMsgRank = rankService.getTeamMsgRank();
        List<RankVO> teamRank = mapper.getMsgCountByTeam();

        valueOperations.set(Constants.TEAM_RANK_KEY, teamRank, 1, TimeUnit.DAYS);
        LOGGER.info(String.format(Constants.SET_INTO_REDIS, Constants.TEAM_RANK_KEY, teamRank.toString()));
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> getMemberMsgRank() {

//        Map<String, List<RankVO>> allRank = rankService.getAllRank();
        Map<String, List<RankVO>> map = new HashMap<>();


        for (String msgType : mapper.getMsgTypes()) {

            String key = msgType + Constants.RANK_TYPE_SUFFIX;

            List<RankVO> rank = mapper.getRank(msgType);
            map.put(msgType, rank);

            LOGGER.info(String.format(Constants.SET_INTO_REDIS, key, rank.toString()));
            valueOperations.set(key, rank, 1, TimeUnit.DAYS);
        }

        String key = Constants.RANK_KEY;
        LOGGER.info(String.format(Constants.SET_INTO_REDIS, key, map.toString()));
        valueOperations.set(key, map, 1, TimeUnit.DAYS);

        return new AsyncResult<>(true);
    }
}
