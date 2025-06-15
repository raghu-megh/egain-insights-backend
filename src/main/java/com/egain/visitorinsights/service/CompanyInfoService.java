package com.egain.visitorinsights.service;

import com.egain.visitorinsights.dto.CompanyInfoDto;
import com.egain.visitorinsights.entity.CompanyInfo;
import com.egain.visitorinsights.mapper.CompanyInfoMapper;
import com.egain.visitorinsights.repository.CompanyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;
    private final CompanyInfoMapper companyInfoMapper;
    private final EnrichmentService enrichmentService;

    @Autowired
    public CompanyInfoService(CompanyInfoRepository companyInfoRepository,
                              CompanyInfoMapper companyInfoMapper,
                              ClearbitEnrichmentService enrichmentService) {
        this.companyInfoRepository = companyInfoRepository;
        this.companyInfoMapper = companyInfoMapper;
        this.enrichmentService = enrichmentService;
    }

    /**
     * Fetch existing company info or enrich and persist if absent.
     */
    public CompanyInfoDto getOrEnrichByDomain(String domain) {
        Optional<CompanyInfo> existing = companyInfoRepository.findByDomain(domain);
        if (existing.isPresent()) {
            return companyInfoMapper.toDto(existing.get());
        }
        // Enrich from external API
        CompanyInfoDto dto = enrichmentService.enrichByDomain(domain);
        // Persist enriched entity
        companyInfoRepository.save(companyInfoMapper.toEntity(dto));
        return dto;
    }
}
