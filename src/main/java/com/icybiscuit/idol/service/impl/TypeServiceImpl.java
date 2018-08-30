package com.icybiscuit.idol.service.impl;

import com.icybiscuit.idol.dao.mapper.TypeMapper;
import com.icybiscuit.idol.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeMapper typeMapper;

    @Autowired
    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public List<String> getSupportColor() {
        List<String> supportColor = typeMapper.getSupportColor();
        return supportColor;
    }

    @Override
    public List<String> getTeam() {

        List<String> team = typeMapper.getTeam();
        return team;
    }
}
