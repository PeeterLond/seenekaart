package com.example.seenekaart.business;

import com.example.seenekaart.business.dto.LocationShowDto;
import com.example.seenekaart.business.dto.LocationPostDto;
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


    public List<LocationShowDto> findAllLocations() {
        List<Location> locations = locationService.findAllLocations();
        List<LocationShowDto> locationShowDtos = locationMapper.toLocationDtos(locations);

        setCoordinatesToLocationDtos(locationShowDtos, locations);
        return locationShowDtos;
    }

    @Transactional
    public void addLocation(LocationPostDto request) {
        Location location = locationMapper.toLocation(request);

        createSetAndSaveCoordinateToLocation(request, location);
        locationService.saveLocation(location);
    }

    @Transactional
    public void updateLocation(LocationShowDto request) {
        Location location = locationService.getLocationBy(request.getProperties().getLocationId());

        getSetAndSaveCoordinateToLocation(request);
        location.setTitle(request.getProperties().getTitle());
        location.setDescription(request.getProperties().getDescription());

        locationService.saveLocation(location);
    }

    public void deleteLocation(Integer locationId, Integer coordinateId) {
        locationService.deleteLocation(locationId);
        coordinateService.deleteCoordinates(coordinateId);
    }

    private static void setCoordinatesToLocationDtos(List<LocationShowDto> locationShowDtos, List<Location> locations) {
        for (int i = 0; i < locationShowDtos.size(); i++) {
            BigDecimal longitude = locations.get(i).getCoordinate().getLongitude();
            BigDecimal latitude = locations.get(i).getCoordinate().getLatitude();
            locationShowDtos.get(i).getGeometry().setCoordinates(new BigDecimal[]{longitude, latitude});
        }
    }

    private void getSetAndSaveCoordinateToLocation(LocationShowDto request) {
        BigDecimal longitude = request.getGeometry().getCoordinates()[0];
        BigDecimal latitude = request.getGeometry().getCoordinates()[1];
        Coordinate coordinate = coordinateService.getCoordinateBy(request.getProperties().getCoordinateId());
        coordinate.setLongitude(longitude);
        coordinate.setLatitude(latitude);
        coordinateService.saveCoordinate(coordinate);
    }

    private void createSetAndSaveCoordinateToLocation(LocationPostDto request, Location location) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLongitude(request.getGeometry().getCoordinates()[0]);
        coordinate.setLatitude(request.getGeometry().getCoordinates()[1]);
        coordinateService.saveCoordinate(coordinate);
        location.setCoordinate(coordinate);
    }
}
