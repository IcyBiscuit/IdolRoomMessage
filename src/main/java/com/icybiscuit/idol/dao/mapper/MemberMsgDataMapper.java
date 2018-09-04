package com.icybiscuit.idol.dao.mapper;

import com.icybiscuit.idol.entity.DO.MemberPocketDataDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMsgDataMapper {

    Integer getDays(String roomId);

    List<MemberPocketDataDO> getPocketData(String roomId);
}
