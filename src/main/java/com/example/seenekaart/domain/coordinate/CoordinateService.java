package com.example.seenekaart.domain.coordinate;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CoordinateService {

    @Resource
    private CoordinateRepository coordinateRepository;

    public void saveCoordinate(Coordinate coordinate) {
        coordinateRepository.save(coordinate);
    }
}
