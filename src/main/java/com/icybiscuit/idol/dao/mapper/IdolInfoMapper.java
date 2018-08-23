package com.icybiscuit.idol.dao.mapper;

import com.icybiscuit.idol.entity.DO.IdolInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IdolInfoMapper {
    List<IdolInfoDO> listAllInfo();

    IdolInfoDO findByName(String name);

    List<IdolInfoDO> findByColor(String color);

}
