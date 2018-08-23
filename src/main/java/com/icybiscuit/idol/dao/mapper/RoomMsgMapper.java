package com.icybiscuit.idol.dao.mapper;

import com.icybiscuit.idol.entity.DO.RoomMsgDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMsgMapper {
    List<RoomMsgDO> listAll();

    List<RoomMsgDO> findByName(String name);

    List<RoomMsgDO> findByType(String Type);

}
