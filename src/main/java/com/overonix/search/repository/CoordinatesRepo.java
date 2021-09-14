package com.overonix.search.repository;

import com.overonix.search.entity.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatesRepo extends JpaRepository<Coordinates, Integer> {
}
