package com.example.seenekaart.business.dto;

import com.example.seenekaart.domain.location.Location;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Location}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationShowDto implements Serializable {

    @NotNull
    private String type;
    @NotNull
    private Geometry geometry;
    @NotNull
    private PropertiesShow properties;
}