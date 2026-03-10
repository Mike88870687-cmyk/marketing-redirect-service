package com.mike.redirect.controller;

import com.mike.redirect.model.Link;
import com.mike.redirect.service.RedirectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URL;

@RestController
public class RedirectController {

    private final RedirectService redirectService;

    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/r/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {

        Link link = redirectService.getLinkByCode(code);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(link.getDestinationUrl()))
                .build();
    }
}
