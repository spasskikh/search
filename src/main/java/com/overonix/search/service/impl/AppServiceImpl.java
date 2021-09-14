package com.overonix.search.service.impl;

import com.overonix.search.dto.SearchResultDto;
import com.overonix.search.entity.Coordinates;
import com.overonix.search.service.AppService;
import com.overonix.search.service.CoordinatesService;
import com.overonix.search.service.NominatimService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppServiceImpl implements AppService {

    private final CoordinatesService coordinatesService;
    private final NominatimService nominatimService;

    public AppServiceImpl(CoordinatesService coordinatesService, NominatimService nominatimService) {
        this.coordinatesService = coordinatesService;
        this.nominatimService = nominatimService;
    }

    @Transactional
    @Override
    public List<Coordinates> searchAndSave(String query) {
        List<Coordinates> coordinates = nominatimService.searchQuery(query).stream()
                .map(r -> new Coordinates(r.getLat(), r.getLon()))
                .collect(Collectors.toList());
        coordinatesService.saveAll(coordinates);
        return coordinates;
    }

    @Transactional
    @Override
    public List<SearchResultDto> getAllFromDb() {
        List<SearchResultDto> result = new ArrayList<>();
        coordinatesService.getCoordinates().forEach(e ->
                result.addAll(nominatimService.searchByCoordinates(e.getLatitude(), e.getLongitude()))
        );
        return result;
    }
}
