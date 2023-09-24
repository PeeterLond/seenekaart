package com.example.seenekaart.domain.coordinate;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CoordinateService {

    @Resource
    private CoordinateRepository coordinateRepository;

    public Coordinate getCoordinateBy(Integer coordinateId) {
        return coordinateRepository.getReferenceById(coordinateId);
    }

    public void saveCoordinate(Coordinate coordinate) {
        coordinateRepository.save(coordinate);
    }

    public void deleteCoordinates(Integer coordinateId) {
        coordinateRepository.deleteById(coordinateId);
    }
}
