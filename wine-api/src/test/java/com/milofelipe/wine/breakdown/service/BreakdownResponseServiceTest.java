package com.milofelipe.wine.breakdown.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BreakdownResponseServiceTest {

    @Autowired
    private BreakdownService breakdownService;

    @Test
    void byYear() {
        Map<String, Double> breakdown = breakdownService.byYear("11YVCHAR001");

        assertEquals(85, breakdown.get("2011"));
        assertEquals(15, breakdown.get("2010"));
    }

    @Test
    void byVariety() {
        Map<String, Double> breakdown = breakdownService.byVariety("11YVCHAR001");

        assertEquals(10, breakdown.get("Pinot Noir"));
        assertEquals(90, breakdown.get("Chardonnay"));
    }

    @Test
    void byRegion() {
        Map<String, Double> breakdown = breakdownService.byRegion("11YVCHAR001");

        assertEquals(5, breakdown.get("Mornington"));
        assertEquals(80, breakdown.get("Yarra Valley"));
        assertEquals(15, breakdown.get("Macedon"));
    }

    @Test
    void byYearVariety() {
        Map<String, Double> breakdown = breakdownService.byYearVariety("11YVCHAR001");

        assertEquals(5, breakdown.get("2011-Pinot Noir"));
        assertEquals(5, breakdown.get("2010-Pinot Noir"));
        assertEquals(80, breakdown.get("2011-Chardonnay"));
        assertEquals(10, breakdown.get("2010-Chardonnay"));
    }

    @Test
    void nullLotCode() {
        assertThrows(NullPointerException.class, () -> breakdownService.byYear(null),
                "LotCode is required");
    }

    @Test
    void invalidLotCode() {
        Map<String, Double> breakdown = breakdownService.byYear("INVALID");
        assertEquals(0, breakdown.size());
    }

}