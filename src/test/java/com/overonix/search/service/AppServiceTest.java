package com.overonix.search.service;

import com.overonix.search.dto.SearchResultDto;
import com.overonix.search.entity.Coordinates;
import com.overonix.search.service.impl.AppServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class AppServiceTest {

    @Mock
    private CoordinatesService coordinatesService;

    @Mock
    private NominatimService nominatimService;

    @InjectMocks
    private AppServiceImpl appService;

    private String query;
    private Coordinates coordinates;
    private List<Coordinates> coordinatesList;
    private SearchResultDto searchResultDto;
    private List<SearchResultDto> searchResultDtoList;

    @BeforeEach
    void init() {
        query = "Kyiv,Ukraine";
        coordinates = new Coordinates("lat", "lon");
        coordinatesList = Collections.singletonList(coordinates);
        searchResultDto = new SearchResultDto("description", "lat", "lon");
        searchResultDtoList = Collections.singletonList(searchResultDto);
    }

    @Test
    void testSearchAndSave() {
        Mockito.when(nominatimService.searchQuery(query)).thenReturn(searchResultDtoList);

        List<Coordinates> searchAndSave = appService.searchAndSave(query);
        Mockito.verify(coordinatesService, Mockito.times(1)).saveAll(coordinatesList);
        Assertions.assertEquals(searchAndSave, coordinatesList);
    }

    @Test
    void testGetAllFromDb() {
        Mockito.when(coordinatesService.getCoordinates()).thenReturn(coordinatesList);
        Mockito.when(nominatimService.searchByCoordinates(coordinates.getLatitude(), coordinates.getLongitude()))
                .thenReturn(searchResultDtoList);

        List<SearchResultDto> allFromDb = appService.getAllFromDb();
        Assertions.assertEquals(allFromDb, searchResultDtoList);
    }
}
