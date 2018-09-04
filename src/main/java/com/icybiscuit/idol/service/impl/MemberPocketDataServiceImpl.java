package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.MemberMsgDataMapper;
import com.icybiscuit.idol.entity.DO.MemberPocketDataDO;
import com.icybiscuit.idol.service.MemberPocketDataService;
import com.icybiscuit.idol.utils.Constants;
import com.icybiscuit.idol.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberPocketDataServiceImpl implements MemberPocketDataService {

    private MemberMsgDataMapper memberMsgDataMapper;
    private RedisUtil redisUtil;

    @Autowired
    public MemberPocketDataServiceImpl(MemberMsgDataMapper memberMsgDataMapper, RedisUtil redisUtil) {
        this.memberMsgDataMapper = memberMsgDataMapper;
        this.redisUtil = redisUtil;
    }

    @Override
    public Map<String, Object> getMemberPocketData(String roomId) {

        String key = roomId + Constants.POCKET_DATA_KEY_SUFFIX;
        Object fromRedis = redisUtil.getFromRedis(key);

        if (fromRedis != null) {
            return (Map<String, Object>) fromRedis;
        }

        Map<String, Object> map = new HashMap<>();

        Integer days = memberMsgDataMapper.getDays(roomId);
        List<MemberPocketDataDO> pocketData = memberMsgDataMapper.getPocketData(roomId);

        map.put("days", days);
        map.put("data", pocketData);
        redisUtil.setIntoRedis(key, map);

        return map;
    }
}
