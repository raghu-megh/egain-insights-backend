package com.egain.visitorinsights.controller;

import com.egain.visitorinsights.dto.CompanyInfoDto;
import com.egain.visitorinsights.service.CompanyInfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyInfoController {

    private final CompanyInfoService companyInfoService;

    /**
     * GET /api/companies/{domain}
     */
    @GetMapping("/{domain}")
    public CompanyInfoDto getCompanyInfo(
            @PathVariable String domain) {
        return companyInfoService.getOrEnrichByDomain(domain);
    }
}