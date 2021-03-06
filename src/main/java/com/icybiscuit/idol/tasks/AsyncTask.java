package com.icybiscuit.idol.tasks;

import com.icybiscuit.idol.dao.mapper.IdolInfoMapper;
import com.icybiscuit.idol.dao.mapper.MemberMsgDataMapper;
import com.icybiscuit.idol.dao.mapper.MsgDaysCountsMapper;
import com.icybiscuit.idol.dao.mapper.RoomMsgRankMapper;
import com.icybiscuit.idol.entity.DO.IdolInfoDO;
import com.icybiscuit.idol.entity.DO.MemberPocketDataDO;
import com.icybiscuit.idol.entity.DO.MsgDaysCounts;
import com.icybiscuit.idol.entity.VO.RankVO;
import com.icybiscuit.idol.utils.Constants;
import com.icybiscuit.idol.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Component
public class AsyncTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTask.class);

    //    private RankService rankService;
    private RoomMsgRankMapper roomMsgRankMapper;
    //    private final ValueOperations valueOperations;
    private RedisUtil redisUtil;
    private MsgDaysCountsMapper msgDaysCountsMapper;
    private IdolInfoMapper idolInfoMapper;
    private MemberMsgDataMapper memberMsgDataMapper;

    @Autowired
    public void setMemberMsgDataMapper(MemberMsgDataMapper memberMsgDataMapper) {
        this.memberMsgDataMapper = memberMsgDataMapper;
    }

    @Autowired
    public void setIdolInfoMapper(IdolInfoMapper idolInfoMapper) {
        this.idolInfoMapper = idolInfoMapper;
    }

    @Autowired
    public void setRoomMsgRankMapper(RoomMsgRankMapper roomMsgRankMapper) {
        this.roomMsgRankMapper = roomMsgRankMapper;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Autowired
    public void setMsgDaysCountsMapper(MsgDaysCountsMapper msgDaysCountsMapper) {
        this.msgDaysCountsMapper = msgDaysCountsMapper;
    }

    @Async
    public Future<Boolean> getMemberInfoList() {
        String key = Constants.MEMBER_LIST_KEY;

        List<IdolInfoDO> allInfo = idolInfoMapper.listAllInfo();
        redisUtil.setIntoRedis(key, allInfo);

        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> getTeamRank() {

//        List<RankVO> teamMsgRank = rankService.getTeamMsgRank();
        List<RankVO> teamRank = roomMsgRankMapper.getMsgCountByTeam();
        redisUtil.setIntoRedis(Constants.TEAM_RANK_KEY, teamRank);
//        valueOperations.set(Constants.TEAM_RANK_KEY, teamRank, 1, TimeUnit.DAYS);
//        LOGGER.info(String.format(Constants.SET_INTO_REDIS, Constants.TEAM_RANK_KEY, teamRank.toString()));
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> getMemberMsgRank() {

//        Map<String, List<RankVO>> allRank = rankService.getAllRank();
        Map<String, List<RankVO>> map = new HashMap<>();


        for (String msgType : roomMsgRankMapper.getMsgTypes()) {

            String key = msgType + Constants.RANK_TYPE_SUFFIX;

            List<RankVO> rank = roomMsgRankMapper.getRank(msgType);
            map.put(msgType, rank);

//            LOGGER.info(String.format(Constants.SET_INTO_REDIS, key, rank.toString()));
//            valueOperations.set(key, rank, 1, TimeUnit.DAYS);
            redisUtil.setIntoRedis(key, rank);
        }

        String key = Constants.RANK_KEY;
//        LOGGER.info(String.format(Constants.SET_INTO_REDIS, key, map.toString()));
//        valueOperations.set(key, map, 1, TimeUnit.DAYS);
        redisUtil.setIntoRedis(key, map);

        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> getLiveRank() {
        String key = Constants.ALL_LIVE_RANK_KEY;
        List<RankVO> liveRank = roomMsgRankMapper.getLiveRank();
//        LOGGER.info(String.format(Constants.SET_INTO_REDIS, key, liveRank.toString()));
//        valueOperations.set(key, liveRank, 1, TimeUnit.DAYS);
        redisUtil.setIntoRedis(key, liveRank);
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> getMsgDaysCounts() {
        Map<String, List<MsgDaysCounts>> map = new HashMap<>();

        for (String team : Constants.TEAMS) {

            String key = team + Constants.TEAM_DAYS_COUNTS_SUFFIX;
            List<MsgDaysCounts> daysCounts = msgDaysCountsMapper.getDaysCounts(team);
            map.put(team, daysCounts);
            redisUtil.setIntoRedis(key, daysCounts);
        }

        redisUtil.setIntoRedis(Constants.DAY_COUNTS_KEY, map);

        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> getMemberPocketData() {
        List<IdolInfoDO> memberList;

        Object fromRedis = redisUtil.getFromRedis(Constants.MEMBER_LIST_KEY);
        if (fromRedis != null) {
            memberList = (List<IdolInfoDO>) fromRedis;
        } else {
            memberList = idolInfoMapper.listAllInfo();
        }

        for (IdolInfoDO member : memberList) {
            Map<String, Object> map = new HashMap<>();

            String roomId = member.getRoomId();

            Integer days = memberMsgDataMapper.getDays(roomId);
            List<MemberPocketDataDO> pocketData = memberMsgDataMapper.getPocketData(roomId);

            map.put("days", days);
            map.put("data", pocketData);

            String key = roomId + Constants.POCKET_DATA_KEY_SUFFIX;
            redisUtil.setIntoRedis(key, map);

        }
        return new AsyncResult<>(true);
    }
}
