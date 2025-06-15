package com.egain.visitorinsights.mapper;

import com.egain.visitorinsights.dto.CompanyInfoDto;
import com.egain.visitorinsights.entity.CompanyInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyInfoMapper {
    CompanyInfoDto toDto(CompanyInfo entity);
    CompanyInfo toEntity(CompanyInfoDto dto);
}