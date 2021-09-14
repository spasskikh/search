package com.overonix.search.service;

import com.overonix.search.entity.Coordinates;

import java.util.List;

public interface CoordinatesService {

    List <Coordinates> getCoordinates();

    void saveAll(List<Coordinates> coordinates);
}
