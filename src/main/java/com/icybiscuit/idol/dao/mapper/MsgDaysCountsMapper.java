package com.icybiscuit.idol.dao.mapper;

import com.icybiscuit.idol.entity.DO.MsgDaysCounts;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MsgDaysCountsMapper {
    List<MsgDaysCounts> getDaysCounts(String team);
}
