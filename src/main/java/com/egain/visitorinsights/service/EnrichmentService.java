package com.egain.visitorinsights.service;

import com.egain.visitorinsights.dto.CompanyInfoDto;

public interface EnrichmentService {
    CompanyInfoDto enrichByDomain(String domain);
}
