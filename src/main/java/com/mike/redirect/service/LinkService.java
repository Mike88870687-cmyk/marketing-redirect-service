package com.mike.redirect.service;

import com.mike.redirect.model.Link;
import com.mike.redirect.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link createLink(String destinationUrl) {

        String code = UUID.randomUUID()
                .toString()
                .substring(0, 6);

        Link link = new Link();

        link.setCode(code);
        link.setDestinationUrl(destinationUrl);
        link.setCreatedAt(LocalDateTime.now());

        return linkRepository.save(link);
    }
}
