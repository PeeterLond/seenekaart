package com.example.seenekaart.business;

import com.example.seenekaart.business.dto.LocationShowDto;
import com.example.seenekaart.business.dto.LocationPostDto;
import com.example.seenekaart.infrastructure.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
                    "leiukohtade asukohad ja tagastab info GEOJSON formaadis. " +
                    "Kui vastust ei saada, siis visatakse viga errorCode-iga 111")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403",
                    description = "message: Hetkel pole ühtegi asukohta andmebaasi lisatud. errorCode: 111",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public List<LocationShowDto> findAllLocations() {
        return positionService.findAllLocations();
    }

    @PostMapping("/location")
    @Operation(summary = "Lisab uue seene leiukoha.",
            description = "Saates POST päringu /location rajale, salvestab uue leiukoha andmebaasi. " +
                    "Ootab sisse GEOJSON formaadis request body.")
    public void addLocation(@RequestBody LocationPostDto request) {
        positionService.addLocation(request);
    }

    @PutMapping("location")
    @Operation(summary = "Muudab olemasoleva seene leiukoha infot.",
            description = "Saates PUT päringu /location rajale, muudab ära leiukoha info. " +
                    "Ootab sisse GEOJSON formaadis request body.")
    public void updateLocation(@RequestBody LocationShowDto request) {
        positionService.updateLocation(request);
    }

    @DeleteMapping("/location")
    @Operation(summary = "Kustutab seene leiukoha andmebaasist.",
            description = "Saates DELETE pärnigu /location rajale, kustutab seene leiukoha ja " +
                    "tema koordinaadid asukoha ja koordinaatide Id järgi. " +
                    "Ootab sisse locationId ja coordinateId parameetrid.")
    public void deleteLocation(@RequestParam Integer locationId, @RequestParam Integer coordinateId) {
        positionService.deleteLocation(locationId, coordinateId);
    }

}
