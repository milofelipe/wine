package com.milofelipe.wine.search;

import com.milofelipe.wine.common.domain.Wine;
import com.milofelipe.wine.search.exception.WineNotFoundException;
import com.milofelipe.wine.search.service.WineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "wine", description = "Wine API")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @Operation(summary = "Get list of wines", description = "Get list of all wines or filtered list by lot code OR description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = WineResponse.class)))
    })
    @GetMapping("/api/wines")
    public List<WineResponse> list(@RequestParam(required = false) String lotCodeOrDescription) {
        List<Wine> wines = null;
        if (lotCodeOrDescription == null) {
            wines = wineService.getAll();
        } else {
            wines = wineService.searchByLotCodeOrDescription(lotCodeOrDescription);
        }

        return wines.stream().map(WineResponse::new).collect(Collectors.toList());
    }

    @Operation(summary = "Find Wine by lot code", description = "Returns a wine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = WineResponse.class))),
            @ApiResponse(responseCode = "404", description = "Could not find Lot Code")})
    @GetMapping("/api/wines/{lotCode}")
    public WineResponse view(@PathVariable String lotCode) {
        Wine wine = wineService.getByLotCode(lotCode).orElseThrow(() -> new WineNotFoundException(lotCode));
        return new WineResponse(wine);
    }

}
