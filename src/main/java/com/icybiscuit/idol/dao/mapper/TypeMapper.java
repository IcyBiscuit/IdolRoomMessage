package com.icybiscuit.idol.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeMapper {

    public List<String> getSupportColor();

    public List<String> getTeam();

}
