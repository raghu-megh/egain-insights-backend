package com.egain.visitorinsights.service;

import com.egain.visitorinsights.dto.CompanyInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ClearbitEnrichmentService implements EnrichmentService {
    private final RestTemplate restTemplate;
    private final String clearbitApiKey;

    public ClearbitEnrichmentService(RestTemplate restTemplate,
                                     @Value("${clearbit.api.key}") String clearbitApiKey) {
        this.restTemplate = restTemplate;
        this.clearbitApiKey = clearbitApiKey;
    }

    /**
     * Call Clearbit API to enrich company info by domain.
     */
    @Override
    public CompanyInfoDto enrichByDomain(String domain) {
        String url = "https://company.clearbit.com/v2/companies/find?domain=" + domain;
        var headers = new org.springframework.http.HttpHeaders();
        headers.setBasicAuth(clearbitApiKey, "");
        var entity = new org.springframework.http.HttpEntity<>(headers);

        var response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                entity,
                CompanyInfoDto.class
        );
        return response.getBody();
    }
}
