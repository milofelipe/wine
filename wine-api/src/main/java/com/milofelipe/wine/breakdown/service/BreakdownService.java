package com.milofelipe.wine.breakdown.service;

import com.milofelipe.wine.common.domain.GrapePercentage;
import com.milofelipe.wine.common.service.WineRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Returns a wine's breakdown of the TOTAL percentage of year, variety, region, and year+variety
 */
@Service
public class BreakdownService {

    private final WineRepository wineRepository;

    public BreakdownService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    public Map<String, Double> byYear(String lotCode) {
        return processBreakdown(lotCode, "year");
    }

    public Map<String, Double> byVariety(String lotCode) {
        return processBreakdown(lotCode, "variety");
    }

    public Map<String, Double> byRegion(String lotCode) {
        return processBreakdown(lotCode, "region");
    }

    public Map<String, Double> byYearVariety(String lotCode) {
        return processBreakdown(lotCode, "year-variety");
    }

    private Map<String, Double> processBreakdown(String lotCode, String breakdownType) {
        validate(lotCode);
        Map<String, Double> breakdown = new HashMap<>();
        wineRepository.searchByLotCode(lotCode).ifPresent(wine -> {
            for (GrapePercentage grapePercentage : wine.getComponents()) {
                String key = getKey(grapePercentage, breakdownType);
                double percentage = grapePercentage.getPercentage();
                double totalPercentage = breakdown.containsKey(key) ? breakdown.get(key) : 0;
                breakdown.put(key, totalPercentage + percentage);
            }
        });

        return breakdown;
    }

    private void validate(String lotCode) {
        Objects.requireNonNull(lotCode, "LotCode is required");
    }

    private String getKey(GrapePercentage grapePercentage, String breakdownType) {
        switch (breakdownType) {
            case "variety":
                return grapePercentage.getGrape().getVariety().getName();
            case "region":
                return grapePercentage.getGrape().getRegion().getName();
            case "year-variety":
                return String.format("%s - %s", grapePercentage.getGrape().getYear(),
                        grapePercentage.getGrape().getVariety().getName());
            default: // defaults to "year"
                return String.valueOf(grapePercentage.getGrape().getYear());
        }
    }

}
