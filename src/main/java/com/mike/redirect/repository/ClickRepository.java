package com.mike.redirect.repository;

import com.mike.redirect.model.Click;
import com.mike.redirect.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<Click, Long> {

    long countByLink(Link link);
}
