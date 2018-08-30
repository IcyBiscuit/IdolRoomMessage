package com.icybiscuit.idol.dao.mapper;

import com.icybiscuit.idol.entity.VO.RankVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoomMsgRankMapper {
    List<RankVO> getRank(String type);

    List<String> getMsgTypes();

    List<RankVO> getMsgCountByTeam();
}
