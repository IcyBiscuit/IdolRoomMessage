package com.icybiscuit.idol.controller;

import com.icybiscuit.idol.entity.DO.MsgDaysCounts;
import com.icybiscuit.idol.service.MsgDaysCountsService;
import com.icybiscuit.idol.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/ajax/msg/counts")
public class MsgDaysCountsController {

    private MsgDaysCountsService daysCountsService;

    @Autowired
    public MsgDaysCountsController(MsgDaysCountsService daysCountsService) {
        this.daysCountsService = daysCountsService;
    }

    @GetMapping(path = "/all")
    public /*Map<String, List<MsgDaysCounts>>*/ MessageUtil<Map<String, List<MsgDaysCounts>>> getDaysCount() {
        Map<String, List<MsgDaysCounts>> allDaysCounts = daysCountsService.getDaysCounts();
        return MessageUtil.ok(allDaysCounts);
    }

    @GetMapping(path = "/{team}")
    public /*List<MsgDaysCounts>*/MessageUtil<List<MsgDaysCounts>> getDaysCountsBytTeam(@PathVariable String team) {
        List<MsgDaysCounts> daysCounts;
        if (team.equalsIgnoreCase("ybs")) {
            team = "预备生";
            daysCounts = daysCountsService.getDaysCounts(team);
        } else {
            daysCounts = daysCountsService.getDaysCounts(team.toUpperCase());
        }
        return MessageUtil.ok(daysCounts);
    }
}
