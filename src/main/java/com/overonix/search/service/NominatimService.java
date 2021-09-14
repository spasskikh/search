package com.overonix.search.service;

import com.overonix.search.dto.SearchResultDto;

import java.util.List;

public interface NominatimService {

    List<SearchResultDto> searchQuery(String query);

    List<SearchResultDto> searchByCoordinates(String latitude, String longitude);
}
