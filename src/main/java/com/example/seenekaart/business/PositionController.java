package com.example.seenekaart.business;

import com.example.seenekaart.business.dto.LocationDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionController {

    @Resource
    private PositionService positionService;

    @GetMapping("/location")
    @Operation(summary = "Tagastab kõik seente leiukohad.",
            description = "Saates GET päringu rajale /location, otsib andmebaasist ülesse kõik seente " +
                    "leiukohtade asukohad ja tagastab info GEOJSON formaadis.")
    public List<LocationDto> findAllLocations() {
       return positionService.findAllLocations();
    }
}
