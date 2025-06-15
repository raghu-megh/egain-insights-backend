package com.egain.visitorinsights.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DomainVisitDto {
    private String domain;
    private long visits;
}