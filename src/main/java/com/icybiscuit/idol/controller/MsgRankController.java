package com.icybiscuit.idol.controller;

import com.icybiscuit.idol.entity.VO.RankVO;
import com.icybiscuit.idol.service.RankService;
import com.icybiscuit.idol.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ajax/rank")
public class MsgRankController {

    private final RankService rankService;

    @Autowired
    public MsgRankController(RankService rankService) {
        this.rankService = rankService;
    }


    //    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    @GetMapping(path = "/all")
    public /*Map<String, List<RankVO>>*/MessageUtil<Map<String, List<RankVO>>> getAllRank() {
        Map<String, List<RankVO>> rankMap = rankService.getAllRank();

        return MessageUtil.ok(rankMap);
//        return rankMap;
    }

    //    @RequestMapping(value = "rank/{type}", method = RequestMethod.GET)
    @GetMapping(path = "/{type}")
    public /*List<RankVO>*/MessageUtil<List<RankVO>> getRankByName(@PathVariable String type) {
        List<RankVO> rankVOList = rankService.getRankByType(type);

        return MessageUtil.ok(rankVOList);
//        return rankVOList;
    }

    @GetMapping(path = "/team")
    public /*List<RankVO>*/MessageUtil<List<RankVO>> getTeamRank() {
        List<RankVO> teamMsgRank = rankService.getTeamMsgRank();

        return MessageUtil.ok(teamMsgRank);
//        return teamMsgRank;
    }

    @GetMapping(path = "/all/live")
    public MessageUtil<List<RankVO>> getLiveRank() {
        List<RankVO> liveRank = rankService.getLiveRank();
        return MessageUtil.ok(liveRank);
    }
}

