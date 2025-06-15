package com.egain.visitorinsights.service;

import com.egain.visitorinsights.dto.DomainVisitDto;
import com.egain.visitorinsights.dto.VisitorDto;
import com.egain.visitorinsights.entity.Visitor;
import com.egain.visitorinsights.mapper.VisitorMapper;
import com.egain.visitorinsights.repository.VisitorRepository;
import com.egain.visitorinsights.repository.PageViewRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final VisitorMapper visitorMapper;
    private final PageViewRepository pageViewRepo;

    /**
     * Search for visitors, optionally filtering by company domain.
     */
    public Page<VisitorDto> search(String domain, Pageable pageable) {
        return Optional.ofNullable(domain)
                .map(visitorRepository::findByDomain)
                .map(list -> {
                    int start = Long.valueOf(pageable.getOffset()).intValue();
                    int end = Math.min(start + pageable.getPageSize(), list.size());
                    return new PageImpl<>(list.subList(start, end), pageable, list.size())
                            .map(visitorMapper::toDto);
                }).orElse(visitorRepository.findAll(pageable)
                        .map(visitorMapper::toDto));
    }

    /**
     * Retrieve a single visitor session by ID.
     */
    public VisitorDto findById(Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visitor not found: " + id));
        return visitorMapper.toDto(visitor);
    }

    /** Returns visitor–visit counts per domain over the last `days`. */
    public List<DomainVisitDto> getFrequentVisitors(int days) {
        LocalDateTime from = LocalDateTime.now().minusDays(days);
        return pageViewRepo.countVisitsByDomainSince(from);
    }
}