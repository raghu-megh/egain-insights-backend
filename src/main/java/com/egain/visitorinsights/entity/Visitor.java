package com.egain.visitorinsights.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visitors")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;
    private String ip;
    private String domain;
    private LocalDateTime firstSeen;
    private LocalDateTime lastSeen;
    private int visitCount;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PageView> pageViews = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_domain", referencedColumnName = "domain")
    private CompanyInfo company;
}