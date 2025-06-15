package com.egain.visitorinsights.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VisitorDto {
    private Long id;
    private String sessionId;
    private String ip;
    private String domain;
    private LocalDateTime firstSeen;
    private LocalDateTime lastSeen;
    private int visitCount;
    private CompanyInfoDto company;
    private List<PageViewDto> pageViews;
}