package com.mike.redirect.service;

import com.mike.redirect.model.Link;
import com.mike.redirect.repository.LinkRepository;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {

    private final LinkRepository linkRepository;

    public RedirectService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link getLinkByCode(String code) {
        return linkRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Link not found"));
    }
}
