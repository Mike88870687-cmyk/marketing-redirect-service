package com.mike.redirect.repository;

import com.mike.redirect.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByCode(String code);

}
