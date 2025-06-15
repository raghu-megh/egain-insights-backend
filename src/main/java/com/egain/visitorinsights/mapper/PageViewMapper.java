package com.egain.visitorinsights.mapper;

import com.egain.visitorinsights.dto.PageViewDto;
import com.egain.visitorinsights.entity.PageView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PageViewMapper {
    PageViewDto toDto(PageView entity);
}