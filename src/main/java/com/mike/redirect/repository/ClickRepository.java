package com.mike.redirect.repository;

import com.mike.redirect.model.Click;
import com.mike.redirect.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClickRepository extends JpaRepository<Click, Long> {

    long countByLink(Link link);

    @Query("""
    SELECT DATE(c.timestamp), COUNT(c)
    FROM Click c
    WHERE c.link.code = :code
    GROUP BY DATE(c.timestamp)
    ORDER BY DATE(c.timestamp)
    """)
    List<Object[]> getDailyStats(String code);
}
