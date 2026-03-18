package com.mike.redirect.dto;

public class LinkResponse {

    private String code;
    private String destinationUrl;

    public LinkResponse(String code, String destinationUrl) {
        this.code =code;
        this.destinationUrl = destinationUrl;
    }

    public String getCode() {
        return code;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }
}
