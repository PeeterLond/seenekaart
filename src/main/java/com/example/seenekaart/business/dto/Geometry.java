package com.example.seenekaart.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Geometry implements Serializable {

    private String type;
    private BigDecimal[] coordinates;
}
