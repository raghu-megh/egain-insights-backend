package com.egain.visitorinsights.repository;

import com.egain.visitorinsights.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findBySessionId(String sessionId);
    List<Visitor> findByDomain(String domain);
}