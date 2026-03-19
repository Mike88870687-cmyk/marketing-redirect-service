package com.mike.redirect.controller;

import com.mike.redirect.dto.LinkResponse;
import com.mike.redirect.dto.LinksStatsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.mike.redirect.dto.CreateLinkRequest;
import com.mike.redirect.dto.CreateLinkResponse;
import com.mike.redirect.service.LinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name ="Links", description = "API for managing short links")
@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }
    //create link
    @Operation(summary = "Create a short link")
    @ApiResponse(responseCode = "200", description = "Short link created")
    @ApiResponse(responseCode = "400", description = "Invalid request")

    @PostMapping
    public CreateLinkResponse createLink(@Valid @RequestBody CreateLinkRequest request) {
        return linkService.createLink(request);
    }

    //get all links
    @Operation(summary = "Get all short links")
    @ApiResponse(responseCode = "200", description = "List of links returned")

    @GetMapping
    public List<LinkResponse> getAllLinks() {
        return linkService.getAllLinks();
    }

    //get single link
    @Operation(summary = "Get link by code")
    @ApiResponse(responseCode = "200", description = "Link found")
    @ApiResponse(responseCode = "404", description = "Link not found")

    @GetMapping("/{code}")
    public LinkResponse getLink(@PathVariable String code) {
        return linkService.getLinkByCode(code);
    }

    //get link stats
    @Operation(summary = "Get statistics for a short link")
    @ApiResponse(responseCode = "200", description = "Statistics returned")
    @ApiResponse(responseCode = "404", description = "Link not found")

    @GetMapping("/{code}/stats")
    public LinksStatsResponse getStats(@PathVariable String code) {
        long clicks = linkService.getClicksCount(code);
        return new LinksStatsResponse(code, clicks);
    }
}
