package com.mike.redirect.dto;

public class DailyStatsResponse {

    private String date;
    private long clicks;

    public DailyStatsResponse(String date, long clicks) {
        this.date = date;
        this.clicks = clicks;
    }

    public String getDate() {
        return date;
    }

    public long getClicks() {
        return clicks;
    }
}
