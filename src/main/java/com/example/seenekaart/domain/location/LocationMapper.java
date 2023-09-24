package com.example.seenekaart.domain.location;

import com.example.seenekaart.business.dto.LocationDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {


    @Mapping(constant = "Feature", target = "type")
    @Mapping(constant = "Point", target = "geometry.type")
    @Mapping(source = "id", target = "properties.id")
    @Mapping(source = "title", target = "properties.title")
    @Mapping(source = "description", target = "properties.description")
    LocationDto toLocationDto(Location location);

    List<LocationDto> toLocationDtos(List<Location> locations);

}