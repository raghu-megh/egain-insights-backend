package com.egain.visitorinsights.controller;

import com.egain.visitorinsights.dto.VisitorDto;
import com.egain.visitorinsights.service.VisitorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = VisitorController.class)
class VisitorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitorService visitorService;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void searchEndpoint_ReturnsJsonPage() throws Exception {
        VisitorDto dto = VisitorDto.builder()
                .id(1L)
                .sessionId("sess-abc123")
                .domain("acme.com")
                .visitCount(3)
                .firstSeen(LocalDateTime.of(2025,6,1,9,0))
                .lastSeen(LocalDateTime.of(2025,6,1,9,15))
                .build();
        Page<VisitorDto> page = new PageImpl<>(List.of(dto), PageRequest.of(0,10), 1);
        Mockito.when(visitorService.search("acme.com", PageRequest.of(0,10))).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/visitors?domain=acme.com&page=0&size=10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].domain").value("acme.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1));

        Mockito.verify(visitorService).search("acme.com", PageRequest.of(0,10));
    }

    @Test
    void getByIdEndpoint_ReturnsVisitor() throws Exception {
        VisitorDto dto = VisitorDto.builder().id(1L).sessionId("sess-abc123").build();
        Mockito.when(visitorService.findById(1L)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/visitors/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sessionId").value("sess-abc123"));

        Mockito.verify(visitorService).findById(1L);
    }
}
