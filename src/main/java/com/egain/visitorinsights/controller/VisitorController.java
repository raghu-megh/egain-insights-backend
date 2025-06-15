package com.egain.visitorinsights.controller;

import com.egain.visitorinsights.dto.DomainVisitDto;
import com.egain.visitorinsights.dto.VisitorDto;
import com.egain.visitorinsights.service.VisitorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
@AllArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;

    /**
     * GET /api/visitors?domain=&page=&size=
     */
    @GetMapping
    public Page<VisitorDto> search(
            @RequestParam String domain,
            Pageable pageable) {
        return visitorService.search(domain, pageable);
    }

    /**
     * GET /api/visitors/{id}
     */
    @GetMapping("/{id}")
    public VisitorDto getById(@PathVariable Long id) {
        return visitorService.findById(id);
    }

    /**
     * GET /api/visitors/frequent?days=7
     */
    @GetMapping("/frequent")
    public List<DomainVisitDto> frequent(@RequestParam(defaultValue = "10") int days) {
        return visitorService.getFrequentVisitors(days);
    }

}