package com.example.seenekaart.domain.location;

import com.example.seenekaart.infrastructure.exception.DataNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.seenekaart.validation.Error.NO_LOCATIONS;

@Service
public class LocationService {

    @Resource
    private LocationRepository locationRepository;

    public List<Location> findAllLocations() {
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty()) {
            throw new DataNotFoundException(NO_LOCATIONS.getMessage(), NO_LOCATIONS.getErrorCode());
        }
        return locations;
    }

    public Location getLocationBy(Integer locationId) {
        return locationRepository.getReferenceById(locationId);
    }

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocation(Integer locationId) {
        locationRepository.deleteById(locationId);
    }
}
