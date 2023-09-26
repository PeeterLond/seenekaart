package com.example.seenekaart.business;

import com.example.seenekaart.business.dto.LocationGetDto;
import com.example.seenekaart.business.dto.LocationPostDto;
import com.example.seenekaart.domain.coordinate.Coordinate;
import com.example.seenekaart.domain.coordinate.CoordinateService;
import com.example.seenekaart.domain.location.Location;
import com.example.seenekaart.domain.location.LocationMapper;
import com.example.seenekaart.domain.location.LocationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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


    public List<LocationGetDto> findAllLocations() {
        List<Location> locations = locationService.findAllLocations();
        List<LocationGetDto> locationGetDtos = locationMapper.toLocationDtos(locations);

        setCoordinatesToLocationDto(locationGetDtos, locations);
        return locationGetDtos;
    }

    @Transactional
    public void addLocation(LocationPostDto request) {
        Location location = locationMapper.toLocation(request);

        createSetAndSaveCoordinateToLocation(request, location);
        locationService.saveLocation(location);
    }

    @Transactional
    public void updateLocation(LocationGetDto request) {
        Location location = locationService.getLocationBy(request.getProperties().getLocationId());

        getSetAndSaveCoordinateToLocation(request);
        location.setTitle(request.getProperties().getTitle());
        location.setDescription(request.getProperties().getDescription());

        locationService.saveLocation(location);
    }

    private void getSetAndSaveCoordinateToLocation(LocationGetDto request) {
        BigDecimal longitude = request.getGeometry().getCoordinates()[0];
        BigDecimal latitude = request.getGeometry().getCoordinates()[1];
        Coordinate coordinate = coordinateService.getCoordinateBy(request.getProperties().getCoordinateId());
        coordinate.setLongitude(longitude);
        coordinate.setLatitude(latitude);
        coordinateService.saveCoordinate(coordinate);
    }

    public void deleteLocation(Integer locationId, Integer coordinateId) {
        locationService.deleteLocation(locationId);
        coordinateService.deleteCoordinates(coordinateId);
    }

    private static void setCoordinatesToLocationDto(List<LocationGetDto> locationGetDtos, List<Location> locations) {
        for (int i = 0; i < locationGetDtos.size(); i++) {
            BigDecimal longitude = locations.get(i).getCoordinate().getLongitude();
            BigDecimal latitude = locations.get(i).getCoordinate().getLatitude();
            locationGetDtos.get(i).getGeometry().setCoordinates(new BigDecimal[]{longitude, latitude});
        }
    }

    private void createSetAndSaveCoordinateToLocation(LocationPostDto request, Location location) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLongitude(request.getGeometry().getCoordinates()[0]);
        coordinate.setLatitude(request.getGeometry().getCoordinates()[1]);
        coordinateService.saveCoordinate(coordinate);
        location.setCoordinate(coordinate);
    }
}
