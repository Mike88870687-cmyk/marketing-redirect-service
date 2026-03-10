package com.mike.redirect.repository;

import com.mike.redirect.model.Click;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<Click, Long> {
}
