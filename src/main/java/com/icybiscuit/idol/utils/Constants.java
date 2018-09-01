package com.icybiscuit.idol.utils;

public final class Constants {
    public static final String GET_FROM_REDIS = "Get from redis, key: %s, value: %s";
    public static final String SET_INTO_REDIS = "Set into redis, key: %s, values: %s";
    public static final String GET_FROM_DB = "Get from database, value: %s";
    public static final String LIST_MEMBER_KEY = "member_list";
    public static final String RANK_KEY = "rank";
    public static final String RANK_TYPE_SUFFIX = "_rank";
    public static final String TEAM_RANK_KEY = "team_rank";
    public static final String ALL_LIVE_RANK_KEY = "all_live_rank";

    public static final String[] TEAMS = {"G", "NIII", "Z", "预备生"};

    public static final String TEAM_DAYS_COUNTS_SUFFIX = "_days_counts";
    public static final String DAY_COUNTS_KEY = "days_counts";
}
