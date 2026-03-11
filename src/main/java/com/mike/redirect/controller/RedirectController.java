package com.mike.redirect.controller;

import com.mike.redirect.model.Link;
import com.mike.redirect.service.RedirectService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class RedirectController {

    private final RedirectService redirectService;

    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/r/{code}")
    public ResponseEntity<Void> redirect(
            @PathVariable String code,
            HttpServletRequest request) {

        Link link = redirectService.processRedirect(code, request);

        return ResponseEntity
                .status(302)
                .location(URI.create(link.getDestinationUrl()))
                .build();
    }
}
