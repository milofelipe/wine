package com.milofelipe.wine.breakdown.controller;

import com.milofelipe.wine.breakdown.service.BreakdownService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/breakdown")
@Tag(name = "breakdown", description = "API to get a wine's TOTAL percentage breakdown")
public class BreakdownController {

    private final BreakdownService breakdownService;

    public BreakdownController(BreakdownService breakdownService) {
        this.breakdownService = breakdownService;
    }

    @Operation(summary = "Breakdown by Year", description = "Get breakdown by year of the given lot code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = BreakdownResponse.class)))
    })
    @GetMapping("/year/{lotCode}")
    public BreakdownResponse byYear(@PathVariable String lotCode) {
        Map<String, Double> breakdownPercentage = breakdownService.byYear(lotCode);
        return new BreakdownResponse("year", breakdownPercentage);
    }

    @Operation(summary = "Breakdown by Variety", description = "Get breakdown by variety of the given lot code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = BreakdownResponse.class)))
    })
    @GetMapping("/variety/{lotCode}")
    public BreakdownResponse byVariety(@PathVariable String lotCode) {
        Map<String, Double> breakdownPercentage = breakdownService.byVariety(lotCode);
        return new BreakdownResponse("variety", breakdownPercentage);
    }

    @Operation(summary = "Breakdown by Region", description = "Get breakdown by region of the given lot code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = BreakdownResponse.class)))
    })
    @GetMapping("/region/{lotCode}")
    public BreakdownResponse byRegion(@PathVariable String lotCode) {
        Map<String, Double> breakdownPercentage = breakdownService.byRegion(lotCode);
        return new BreakdownResponse("region", breakdownPercentage);
    }

    @Operation(summary = "Breakdown by Year+Variety", description = "Get breakdown by year+variety of the given lot code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = BreakdownResponse.class)))
    })
    @GetMapping("/year-variety/{lotCode}")
    public BreakdownResponse byYearVariety(@PathVariable String lotCode) {
        Map<String, Double> breakdownPercentage = breakdownService.byYearVariety(lotCode);
        return new BreakdownResponse("year-variety", breakdownPercentage);
    }

}
