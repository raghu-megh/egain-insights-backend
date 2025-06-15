package com.egain.visitorinsights.mapper;

import com.egain.visitorinsights.dto.VisitorDto;
import com.egain.visitorinsights.entity.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {PageViewMapper.class, CompanyInfoMapper.class})
public interface VisitorMapper {

    VisitorDto toDto(Visitor entity);
}