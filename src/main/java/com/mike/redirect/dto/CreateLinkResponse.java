package com.mike.redirect.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateLinkResponse {

    private String code;

    public CreateLinkResponse(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
