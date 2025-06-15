package com.egain.visitorinsights.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_info")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CompanyInfo {
    @Id
    private String domain;

    private String name;
    private String industry;
    private String location;
    private Integer employeeCount;
}