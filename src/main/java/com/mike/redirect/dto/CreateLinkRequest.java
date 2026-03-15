package com.mike.redirect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateLinkRequest {

    @NotBlank(message = "Destination URL cannot be empty")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Destination URL must start with http:// or https://"
    )
    private String destinationUrl;

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl =destinationUrl;
    }
}
