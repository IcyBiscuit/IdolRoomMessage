package com.icybiscuit.idol.service;

import com.icybiscuit.idol.entity.DO.MsgDaysCounts;

import java.util.List;
import java.util.Map;

public interface MsgDaysCountsService {
    List<MsgDaysCounts> getDaysCounts(String team);

    Map<String, List<MsgDaysCounts>> getDaysCounts();
}
