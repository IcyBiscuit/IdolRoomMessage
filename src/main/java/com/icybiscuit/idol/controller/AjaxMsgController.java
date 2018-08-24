package com.icybiscuit.idol.controller;

import com.icybiscuit.idol.entity.DO.IdolInfoDO;
import com.icybiscuit.idol.entity.VO.RankVO;
import com.icybiscuit.idol.service.IdolInfoService;
import com.icybiscuit.idol.service.RankService;
import com.icybiscuit.idol.service.TypeService;
import com.icybiscuit.idol.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ajax")
public class AjaxMsgController {

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
    public /*Map<String, List<RankVO>>*/MessageUtil<Map<String, List<RankVO>>> getAllRank() {
        Map<String, List<RankVO>> rankMap = rankService.getAllRank();

        return MessageUtil.isOK(rankMap);
//        return rankMap;
    }

    @RequestMapping(value = "rank/{type}", method = RequestMethod.GET)
    public List<RankVO> getRankByName(@PathVariable String type) {
        List<RankVO> rankVOList = rankService.getRankByType(type);
        return rankVOList;
    }
}
