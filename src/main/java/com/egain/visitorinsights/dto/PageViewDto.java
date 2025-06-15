package com.egain.visitorinsights.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PageViewDto {
    private LocalDateTime timestamp;
    private String url;
    private String referrer;
}