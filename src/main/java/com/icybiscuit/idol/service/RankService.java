package com.icybiscuit.idol.service;

import com.icybiscuit.idol.entity.VO.RankVO;

import java.util.List;

public interface RankService {
    List<RankVO> getAllRank();

    List<RankVO> getRankByType(String type);
}
