package com.example.seenekaart.business;

import com.example.seenekaart.business.dto.LocationDto;
import com.example.seenekaart.domain.coordinate.Coordinate;
import com.example.seenekaart.domain.coordinate.CoordinateService;
import com.example.seenekaart.domain.location.Location;
import com.example.seenekaart.domain.location.LocationMapper;
import com.example.seenekaart.domain.location.LocationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PositionService {

    @Resource
    private LocationService locationService;

    @Resource
    private CoordinateService coordinateService;

    @Resource
    private LocationMapper locationMapper;


    public List<LocationDto> findAllLocations() {
        List<Location> locations = locationService.findAllLocations();
        List<LocationDto> locationDtos = locationMapper.toLocationDtos(locations);

        setCoordinatesToLocationDto(locationDtos, locations);
        return locationDtos;
    }

    private static void setCoordinatesToLocationDto(List<LocationDto> locationDtos, List<Location> locations) {
        for (int i = 0; i < locationDtos.size(); i++) {
            BigDecimal longitude = locations.get(i).getCoordinate().getLongitude();
            BigDecimal latitude = locations.get(i).getCoordinate().getLatitude();
            locationDtos.get(i).getGeometry().setCoordinates(new BigDecimal[]{longitude, latitude});
        }
    }

    @Transactional
    public void addLocation(LocationDto request) {
        Location location = locationMapper.toLocation(request);

        createSetAndSaveCoordinateToLocation(request, location);
        locationService.saveLocation(location);
    }

    private void createSetAndSaveCoordinateToLocation(LocationDto request, Location location) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLongitude(request.getGeometry().getCoordinates()[0]);
        coordinate.setLatitude(request.getGeometry().getCoordinates()[1]);
        coordinateService.saveCoordinate(coordinate);
        location.setCoordinate(coordinate);
    }

    public void deleteLocation(Integer locationId) {
        locationService.deleteLocation(locationId);
    }
}
