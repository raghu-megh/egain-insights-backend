package com.egain.visitorinsights.controller;

import com.egain.visitorinsights.dto.PageViewDto;
import com.egain.visitorinsights.service.PageViewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pageviews")
@AllArgsConstructor
public class PageViewController {

    private final PageViewService pageViewService;

    /**
     * GET /api/pageviews?visitorId=
     */
    @GetMapping
    public List<PageViewDto> getByVisitor(
            @RequestParam Long visitorId) {
        return pageViewService.findByVisitorId(visitorId);
    }
}