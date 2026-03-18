package com.mike.redirect.controller;

import com.mike.redirect.dto.LinkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import com.mike.redirect.dto.CreateLinkRequest;
import com.mike.redirect.dto.CreateLinkResponse;
import com.mike.redirect.model.Link;
import com.mike.redirect.service.LinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public CreateLinkResponse createLink(@Valid @RequestBody CreateLinkRequest request) {

        Link link = linkService.createLink(request.getDestinationUrl());

        return CreateLinkResponse.builder()
                .code(link.getCode())
                .destinationUrl(link.getDestinationUrl())
                .build();
    }

    @Operation(summary = "Get all short links")
    @ApiResponse(responseCode = "200", description = "List of links returned")
    @ApiResponse(responseCode = "404", description = "Link not found")

    @GetMapping
    public List<LinkResponse> getAllLinks() {
        return linkService.getAllLinks();
    }
}
