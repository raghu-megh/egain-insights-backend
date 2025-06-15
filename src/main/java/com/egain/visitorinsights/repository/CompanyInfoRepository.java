package com.egain.visitorinsights.repository;

import com.egain.visitorinsights.entity.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {
    Optional<CompanyInfo> findByDomain(String domain);
}