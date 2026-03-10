package com.mike.redirect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PixelScript {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String script;

    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;
}
