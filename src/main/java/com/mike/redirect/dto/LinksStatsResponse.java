package com.mike.redirect.dto;

public class LinksStatsResponse {

    private String code;
    private long clicks;

    public LinksStatsResponse(String code, long clicks) {
        this.code = code;
        this.clicks = clicks;
    }

    public String getCode() {
        return code;
    }

    public long getClicks() {
        return clicks;
    }
}
