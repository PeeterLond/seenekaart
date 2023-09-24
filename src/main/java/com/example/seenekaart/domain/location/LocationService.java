package com.example.seenekaart.domain.location;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Resource
    private LocationRepository locationRepository;

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocation(Integer locationId) {
        locationRepository.deleteById(locationId);
    }
}
