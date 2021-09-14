package com.overonix.search.service;

import com.overonix.search.entity.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class CoordinatesServiceTest {

    @Autowired
    private CoordinatesService coordinatesService;

    @Test
    void testGetAll() {
        Assertions.assertEquals(0, coordinatesService.getCoordinates().size());
    }

    @Test
    void testSaveAll() {
        Coordinates coordinates = new Coordinates("test", "test");
        coordinatesService.saveAll(Collections.singletonList(coordinates));
        Assertions.assertEquals(1, coordinatesService.getCoordinates().size());
    }

}
