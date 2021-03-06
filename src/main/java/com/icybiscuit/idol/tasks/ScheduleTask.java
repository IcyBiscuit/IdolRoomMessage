package com.icybiscuit.idol.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class ScheduleTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);

    private AsyncTask asyncTask;

    @Autowired
    public void setAsyncTask(AsyncTask asyncTask) {
        this.asyncTask = asyncTask;
    }

    @Scheduled(cron = "0 0 0/6 * * ? ")
    public void runRankQuery() {
        LOGGER.info("start query schedule");

        Future<Boolean> memberInfoList = asyncTask.getMemberInfoList();
        Future<Boolean> teamRank = asyncTask.getTeamRank();
        Future<Boolean> memberMsgRank = asyncTask.getMemberMsgRank();
        Future<Boolean> liveRank = asyncTask.getLiveRank();
        Future<Boolean> msgDaysCounts = asyncTask.getMsgDaysCounts();
        Future<Boolean> memberPocketData = asyncTask.getMemberPocketData();

        LOGGER.info("query schedule finished");
    }
}
