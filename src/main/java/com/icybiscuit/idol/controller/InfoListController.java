package com.icybiscuit.idol.controller;

import com.icybiscuit.idol.entity.DO.IdolInfoDO;
import com.icybiscuit.idol.service.IdolInfoService;
import com.icybiscuit.idol.service.TypeService;
import com.icybiscuit.idol.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/ajax/list")
public class InfoListController {

    private final IdolInfoService idolInfoService;

    private final TypeService typeService;

    @Autowired
    public InfoListController(IdolInfoService idolInfoService, TypeService typeService) {
        this.idolInfoService = idolInfoService;
        this.typeService = typeService;
    }

    //    @RequestMapping(value = "/allmember", method = RequestMethod.GET)
    @GetMapping(path = "/member")
    public /*List<IdolInfoDO>*/ MessageUtil<List<IdolInfoDO>> listAllController() {
        List<IdolInfoDO> memberInfoList = idolInfoService.listAll();
        return MessageUtil.ok(memberInfoList);
//        return memberInfoList;

    }

    //    @RequestMapping(value = "/color", method = RequestMethod.GET)
    @GetMapping(path = "/color")
    public /*List<String>*/MessageUtil<List<String>> getSupportColor() {
        List<String> supportColorList = typeService.getSupportColor();
        return MessageUtil.ok(supportColorList);
//        return supportColorList;

    }

    //    @RequestMapping(value = "/team", method = RequestMethod.GET)
    @GetMapping(path = "/team")
    public /*List<String>*/ MessageUtil<List<String>> getTeam() {
        List<String> teamList = typeService.getTeam();
        return MessageUtil.ok(teamList);
//        return teamList;
    }
}
