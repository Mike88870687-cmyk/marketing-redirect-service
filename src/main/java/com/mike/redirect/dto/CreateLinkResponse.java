package com.mike.redirect.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateLinkResponse {

    private String code;
    private String destinationUrl;

}
