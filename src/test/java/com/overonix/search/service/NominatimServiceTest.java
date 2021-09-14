package com.overonix.search.service;

import com.overonix.search.dto.SearchResultDto;
import com.overonix.search.service.impl.NominatimServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class NominatimServiceTest {

    @Value("${nominatim.url}")
    private String nominatimUrl;
    private SearchResultDto resultDto;

    @Mock
    private ResponseEntity<List<SearchResultDto>> responseEntity;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NominatimServiceImpl nominatimService;

    @BeforeEach
    void init() {
        resultDto = new SearchResultDto("Kyiv", "lat", "lon");
        ReflectionTestUtils.setField(nominatimService, "nominatimUrl", nominatimUrl);
    }

    @Test
    void testSearchQuery() {
        String query = String.format("%s?q=%s&format=json", nominatimUrl, resultDto.getDisplayName());
        Mockito.when(responseEntity.getBody()).thenReturn(Collections.singletonList(resultDto));
        Mockito.when(restTemplate.exchange(query, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SearchResultDto>>() {
                })).thenReturn(responseEntity);

        List<SearchResultDto> result = nominatimService.searchQuery(resultDto.getDisplayName());
        Assertions.assertEquals(Collections.singletonList(resultDto), result);
    }

    @Test
    void testSearchByCoordinates() {
        String query = String.format("%s?q=%s+%s&format=json", nominatimUrl, resultDto.getLat(), resultDto.getLon());
        Mockito.when(responseEntity.getBody()).thenReturn(Collections.singletonList(resultDto));
        Mockito.when(restTemplate.exchange(query, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SearchResultDto>>() {
                })).thenReturn(responseEntity);

        List<SearchResultDto> result = nominatimService.searchByCoordinates(resultDto.getLat(), resultDto.getLon());
        Assertions.assertEquals(Collections.singletonList(resultDto), result);
    }
}
