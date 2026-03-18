package com.mike.redirect.service;

import com.mike.redirect.dto.LinkResponse;
import com.mike.redirect.exception.LinkNotFoundException;
import com.mike.redirect.model.Link;
import com.mike.redirect.repository.ClickRepository;
import com.mike.redirect.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final ClickRepository clickRepository;

    public LinkService(LinkRepository linkRepository, ClickRepository clickRepository) {
        this.linkRepository = linkRepository;
        this.clickRepository = clickRepository;
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

    public long getClicksCount(String code) {

        Link link = linkRepository.findByCode(code)
                .orElseThrow(() -> new LinkNotFoundException(code));

        return clickRepository.countByLink(link);
    }

    public List<LinkResponse> getAllLinks() {

        return linkRepository.findAll()
                .stream()
                .map(link -> new LinkResponse(
                        link.getCode(),
                        link.getDestinationUrl()
                ))
                .toList();
    }
}
