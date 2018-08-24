package com.icybiscuit.idol.service;

import com.icybiscuit.idol.entity.VO.RankVO;

import java.util.List;
import java.util.Map;

public interface RankService {
    Map<String, List<RankVO>> getAllRank();

    List<RankVO> getRankByType(String type);

    List<String> getMsgTypes();
}
