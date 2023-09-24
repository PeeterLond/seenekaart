package com.example.seenekaart.business;

import com.example.seenekaart.business.dto.LocationGetDto;
import com.example.seenekaart.business.dto.LocationPostDto;
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
    public List<LocationGetDto> findAllLocations() {
       return positionService.findAllLocations();
    }

    @PostMapping("/location")
    @Operation(summary = "Lisab uue seene leiukoha.",
            description = "Saates POST päringu /location rajale, salvestab uue leiukoha andmebaasi." +
                    "Ootab sisse GEOJSON formaadis request body.")
    public void addLocation(@RequestBody LocationPostDto request) {
        positionService.addLocation(request);
    }

    @PutMapping("location")
    @Operation(summary = "Muudab olemasoleva seene leiukoha infot.",
            description = "Saates PUT pärinug /location rajale, muudab ära leiukoha info. " +
                    "Ootab sisse GEOJSON formaadis request body.")
    public void updateLocation(@RequestBody LocationGetDto request) {
        positionService.updateLocation(request);
    }

    @DeleteMapping("/location")
    @Operation(summary = "Kustutab seene leiukoha andmebaasist.",
            description = "Saates DELETE pärnigu /location rajale, kustutab seene leiukoha ja " +
                    "tema koordinaadid asukoha ja koordinaatide Id järgi. Ootab sisse GEOJSON formaadis request body.")
    public void deleteLocation(@RequestBody LocationGetDto request) {
        positionService.deleteLocation(request);
    }

}
