package com.overonix.search.service.impl;

import com.overonix.search.entity.Coordinates;
import com.overonix.search.repository.CoordinatesRepo;
import com.overonix.search.service.CoordinatesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatesServiceImpl implements CoordinatesService {

    private final CoordinatesRepo coordinatesRepo;

    public CoordinatesServiceImpl(CoordinatesRepo coordinatesRepo) {
        this.coordinatesRepo = coordinatesRepo;
    }

    @Override
    public List<Coordinates> getCoordinates() {
        return coordinatesRepo.findAll();
    }

    @Override
    public void saveAll(List<Coordinates> coordinates) {
        coordinatesRepo.saveAll(coordinates);
    }
}
