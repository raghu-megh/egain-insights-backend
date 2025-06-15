package com.egain.visitorinsights.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CompanyInfoDto {
    private String domain;
    private String name;
    private String industry;
    private String location;
    private Integer employeeCount;
}