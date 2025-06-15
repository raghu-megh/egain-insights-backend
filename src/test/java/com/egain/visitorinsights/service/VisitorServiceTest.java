// src/test/java/com/egain/visitorinsights/service/VisitorServiceTest.java
package com.egain.visitorinsights.service;

import com.egain.visitorinsights.dto.VisitorDto;
import com.egain.visitorinsights.entity.Visitor;
import com.egain.visitorinsights.mapper.CompanyInfoMapper;
import com.egain.visitorinsights.mapper.PageViewMapper;
import com.egain.visitorinsights.mapper.VisitorMapper;
import com.egain.visitorinsights.repository.VisitorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class VisitorServiceTest {

    @MockBean
    private VisitorRepository visitorRepository;

    @Autowired
    private VisitorMapper visitorMapper;

    @Autowired
    private VisitorService visitorService;

    private Visitor sampleVisitor;

    @BeforeEach
    void setUp() {
        sampleVisitor = Visitor.builder()
                .id(1L)
                .sessionId("sess-abc123")
                .ip("192.0.2.10")
                .domain("acme.com")
                .firstSeen(LocalDateTime.of(2025,6,1,9,0))
                .lastSeen(LocalDateTime.of(2025,6,1,9,15))
                .visitCount(3)
                .build();
    }

    @Test
    void search_WithDomain_ReturnsFilteredPage() {
        List<Visitor> list = List.of(sampleVisitor);
        when(visitorRepository.findByDomain("acme.com")).thenReturn(list);
        when(visitorRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        Pageable pageable = PageRequest.of(0, 10);
        Page<VisitorDto> result = visitorService.search("acme.com", pageable);

        assertThat(result.getContent()).hasSize(1);
        VisitorDto dto = result.getContent().getFirst();
        assertThat(dto.getDomain()).isEqualTo("acme.com");
        assertThat(dto.getVisitCount()).isEqualTo(3);
        verify(visitorRepository).findByDomain("acme.com");
    }

    @Test
    void findById_ExistingId_ReturnsDto() {
        when(visitorRepository.findById(1L)).thenReturn(Optional.of(sampleVisitor));

        VisitorDto dto = visitorService.findById(1L);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getSessionId()).isEqualTo("sess-abc123");
    }

    @Test
    void findById_UnknownId_ThrowsException() {
        when(visitorRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> visitorService.findById(999L)
        );
    }
}
