package com.example.seenekaart.business;

import com.example.seenekaart.business.dto.LocationDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PositionController {

    @Resource
    private PositionService positionService;

    @GetMapping("/location")
    @Operation(summary = "Tagastab kõik seente leiukohad.",
            description = "Saates GET päringu /location rajale, otsib andmebaasist ülesse kõik seente " +
                    "leiukohtade asukohad ja tagastab info GEOJSON formaadis.")
    public List<LocationDto> findAllLocations() {
       return positionService.findAllLocations();
    }

    @PostMapping("/location")
    @Operation(summary = "Lisab uue seene leiukoha.",
            description = "Ootab sisse GEOJSON formaadis request body. " +
                    "Saates POST päringu /location rajale, salvestab uue leiukoha andmebaasi.")
    public void addLocation(@RequestBody LocationDto request) {
        positionService.addLocation(request);
    }

    @DeleteMapping("/location")
    @Operation(summary = "Kustutab seene leiukoha andmebaasist.",
            description = "Ootab sisse parameetrina asukoha Id-d. Saates DELETE pärnigu /location rajale, " +
                    "kustutab seene leiukoha tema asukoha Id järgi.")
    public void deleteLocation(@RequestParam Integer locationId) {
        positionService.deleteLocation(locationId);
    }

}
