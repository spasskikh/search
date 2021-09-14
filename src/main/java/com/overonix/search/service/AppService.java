package com.overonix.search.service;

import com.overonix.search.dto.SearchResultDto;
import com.overonix.search.entity.Coordinates;

import java.util.List;

public interface AppService {

    List<Coordinates> searchAndSave(String query);

    List<SearchResultDto> getAllFromDb();
}
