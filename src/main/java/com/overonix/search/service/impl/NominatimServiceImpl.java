package com.overonix.search.service.impl;

import com.overonix.search.dto.SearchResultDto;
import com.overonix.search.service.NominatimService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NominatimServiceImpl implements NominatimService {

    @Value("${nominatim.url}")
    private String nominatimUrl;

    private final RestTemplate restTemplate;

    public NominatimServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<SearchResultDto> searchQuery(String query) {
        return execute(String.format("%s?q=%s&format=json",nominatimUrl, query));
    }

    @Override
    public List<SearchResultDto> searchByCoordinates(String latitude, String longitude) {
        return execute(String.format("%s?q=%s+%s&format=json",nominatimUrl, latitude, longitude));
    }

    private List<SearchResultDto> execute(String url){
        return restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SearchResultDto>>() {
                }).getBody();
    }
}
