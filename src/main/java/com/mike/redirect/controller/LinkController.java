package com.mike.redirect.controller;

import com.mike.redirect.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.mike.redirect.service.LinkService;
import org.springframework.http.ResponseEntity;
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
    public CreateLinkResponse createLink(@RequestBody CreateLinkRequest request) {
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

    //delete link
    @Operation(summary = "Delete link by code")
    @ApiResponse(responseCode = "200", description = "Link delete")
    @ApiResponse(responseCode = "404", description = "Link not found")

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteLink(@PathVariable String code) {
        linkService.deleteByCode(code);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/{code}/daily-stats")
    public List<DailyStatsResponse> getDailyStats(@PathVariable String code) {
        return linkService.getDailyStats(code);
    }

    @GetMapping("/p/{code}")
    public ResponseEntity<byte[]> trackPixel(@PathVariable String code) {

        linkService.trackClick(code);

        byte[] pixel = new byte[]{
                (byte)0x47,(byte)0x49,(byte)0x46,(byte)0x38,(byte)0x39,
                (byte)0x61,0x01,0x00,0x01,0x00,
                (byte)0x80,0x00,0x00,0x00,0x00,0x00,
                (byte)0xff,(byte)0xff,(byte)0xff,0x21,
                (byte)0xf9,0x04,0x01,0x00,0x00,0x00,0x00,
                0x2c,0x00,0x00,0x00,0x00,0x01,0x00,0x01,0x00,
                0x00,0x02,0x02,0x44,0x01,0x00,0x3b
        };

        return ResponseEntity.ok()
                .header("Content-Type", "image/gif")
                .body(pixel);
    }
}
