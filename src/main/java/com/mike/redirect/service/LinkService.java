package com.mike.redirect.service;

import com.mike.redirect.dto.CreateLinkRequest;
import com.mike.redirect.dto.CreateLinkResponse;
import com.mike.redirect.dto.DailyStatsResponse;
import com.mike.redirect.dto.LinkResponse;
import com.mike.redirect.exception.LinkNotFoundException;
import com.mike.redirect.model.Link;
import com.mike.redirect.repository.ClickRepository;
import com.mike.redirect.repository.LinkRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

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

    public CreateLinkResponse createLink(@Valid CreateLinkRequest request) {

        String code = UUID.randomUUID()
                .toString()
                .substring(0, 6);

        Link link = new Link();
        link.setCode(code);
        link.setDestinationUrl(request.getDestinationUrl());

        linkRepository.save(link);

        return new CreateLinkResponse(code);
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

    public LinkResponse getLinkByCode(String code) {

        Link link = linkRepository.findByCode(code)
                .orElseThrow(() -> new LinkNotFoundException(code));

        return new LinkResponse(
                link.getCode(),
                link.getDestinationUrl()
        );
    }

    public void deleteByCode(String code) {
        Link link = linkRepository.findByCode(code)
                .orElseThrow(()-> new LinkNotFoundException(code));

        linkRepository.delete(link);
    }

    public List<DailyStatsResponse> getDailyStats(String code) {

        List<Object[]> results = clickRepository.getDailyStats(code);

        return results.stream()
                .map(r -> new DailyStatsResponse(
                        r[0].toString(),
                        (Long) r[1]
                ))
                .toList();
    }
}
