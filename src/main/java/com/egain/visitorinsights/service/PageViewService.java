package com.egain.visitorinsights.service;

import com.egain.visitorinsights.dto.PageViewDto;
import com.egain.visitorinsights.mapper.PageViewMapper;
import com.egain.visitorinsights.repository.PageViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageViewService {
    private final PageViewRepository pageViewRepository;
    private final PageViewMapper pageViewMapper;

    @Autowired
    public PageViewService(PageViewRepository pageViewRepository,
                           PageViewMapper pageViewMapper) {
        this.pageViewRepository = pageViewRepository;
        this.pageViewMapper = pageViewMapper;
    }

    /**
     * Retrieve all page views for a given visitor.
     */
    public List<PageViewDto> findByVisitorId(Long visitorId) {
        return pageViewRepository.findByVisitor_Id(visitorId).stream()
                .map(pageViewMapper::toDto)
                .toList();
    }
}