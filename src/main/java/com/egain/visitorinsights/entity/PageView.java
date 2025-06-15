package com.egain.visitorinsights.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "page_views")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PageView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    private LocalDateTime timestamp;
    private String url;
    private String referrer;
}