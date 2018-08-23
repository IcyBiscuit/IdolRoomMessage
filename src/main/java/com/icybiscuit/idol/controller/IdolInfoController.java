package com.icybiscuit.idol.controller;

import com.icybiscuit.idol.entity.DO.IdolInfoDO;
import com.icybiscuit.idol.entity.VO.RankVO;
import com.icybiscuit.idol.service.IdolInfoService;
import com.icybiscuit.idol.service.RankService;
import com.icybiscuit.idol.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax")
public class IdolInfoController {

    @Autowired
    IdolInfoService idolInfoService;

    @Autowired
    TypeService typeService;

    @Autowired
    RankService rankService;

    @RequestMapping(value = "/allmember", method = RequestMethod.GET)
    public List<IdolInfoDO> listAllController() {
        List<IdolInfoDO> infoDOList = idolInfoService.listAll();
        return infoDOList;

    }

    @RequestMapping(value = "/color", method = RequestMethod.GET)
    public List<String> getSupportColor() {
        List<String> supportColor = typeService.getSupportColor();
        return supportColor;

    }

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public List<String> getTeam() {
        List<String> team = typeService.getTeam();
        return team;
    }

    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public List<RankVO> getRank() {
        List<RankVO> rankList = rankService.getAllRank();
        return rankList;
    }

    @RequestMapping(value = "rank/{type}", method = RequestMethod.GET)
    public List<RankVO> getRankByName(@PathVariable String type) {
        List<RankVO> rankVOList = rankService.getRankByType(type);
        return rankVOList;
    }
}
