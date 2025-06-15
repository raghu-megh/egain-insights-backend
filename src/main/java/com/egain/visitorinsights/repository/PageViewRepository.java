package com.egain.visitorinsights.repository;

import com.egain.visitorinsights.dto.DomainVisitDto;
import com.egain.visitorinsights.entity.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PageViewRepository extends JpaRepository<PageView, Long> {
    List<PageView> findByVisitor_Id(Long visitorId);

    @Query("""
      SELECT new com.egain.visitorinsights.dto.DomainVisitDto(v.domain, COUNT(pv))
      FROM PageView pv
      JOIN pv.visitor v
      WHERE pv.timestamp >= :from
      GROUP BY v.domain
      ORDER BY COUNT(pv) DESC
    """)
    List<DomainVisitDto> countVisitsByDomainSince(@Param("from") LocalDateTime from);

}