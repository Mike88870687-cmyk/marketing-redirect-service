package com.mike.redirect.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String userAgent;

    private String referer;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;
}
