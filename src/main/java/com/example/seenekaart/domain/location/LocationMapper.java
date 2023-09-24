package com.example.seenekaart.domain.location;

import com.example.seenekaart.business.dto.LocationGetDto;
import com.example.seenekaart.business.dto.LocationPostDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {


    @Mapping(constant = "Feature", target = "type")
    @Mapping(constant = "Point", target = "geometry.type")
    @Mapping(source = "id", target = "properties.locationId")
    @Mapping(source = "coordinate.id", target = "properties.coordinateId")
    @Mapping(source = "title", target = "properties.title")
    @Mapping(source = "description", target = "properties.description")
    LocationGetDto toLocationDto(Location location);

    List<LocationGetDto> toLocationDtos(List<Location> locations);



    @Mapping(source = "properties.title", target = "title")
    @Mapping(source = "properties.description", target = "description")
    Location toLocation(LocationPostDto locationPostDto);

}