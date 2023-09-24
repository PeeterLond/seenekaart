package com.example.seenekaart.business.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationPostDto implements Serializable {

    @NotNull
    private String type;
    @NotNull
    private Geometry geometry;
    @NotNull
    private PropertiesPost properties;

}
