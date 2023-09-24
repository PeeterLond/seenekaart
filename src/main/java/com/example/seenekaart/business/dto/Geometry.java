package com.example.seenekaart.business.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geometry implements Serializable {

    @NotNull
    private String type;
    @NotNull
    private BigDecimal[] coordinates;
}
