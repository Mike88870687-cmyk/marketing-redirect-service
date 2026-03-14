package com.mike.redirect.service;

import com.mike.redirect.exception.LinkNotFoundException;
import com.mike.redirect.model.Click;
import com.mike.redirect.model.Link;
import com.mike.redirect.repository.ClickRepository;
import com.mike.redirect.repository.LinkRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RedirectService {

    private final LinkRepository linkRepository;
    private final ClickRepository clickRepository;

    public RedirectService(LinkRepository linkRepository, ClickRepository clickRepository) {
        this.linkRepository = linkRepository;
        this.clickRepository = clickRepository;
    }

    public Link processRedirect(String code, HttpServletRequest request) {
        Link link = linkRepository.findByCode(code)
                .orElseThrow(() -> new LinkNotFoundException(code));

        Click click = new Click();

        click.setLink(link);
        click.setIp(request.getRemoteAddr());
        click.setUserAgent(request.getHeader("User-Agent"));
        click.setReferer(request.getHeader("Referer"));
        click.setTimestamp(LocalDateTime.now());

        clickRepository.save(click);

        return link;
    }
}
