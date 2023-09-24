package com.example.seenekaart.business;

import com.example.seenekaart.business.dto.LocationDto;
import com.example.seenekaart.domain.location.Location;
import com.example.seenekaart.domain.location.LocationMapper;
import com.example.seenekaart.domain.location.LocationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PositionService {

    @Resource
    private LocationService locationService;

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
}
