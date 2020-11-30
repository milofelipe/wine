package com.milofelipe.wine.breakdown.controller;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreakdownResponseTest {

    @Test
    void breakdownListSortedByPercentageDescending() {
        Map<String, Double> breakdownPercentage = new HashMap<>();
        breakdownPercentage.put("2011", 15.0);
        breakdownPercentage.put("2005", 10.0);
        breakdownPercentage.put("2015", 50.0);
        breakdownPercentage.put("2016", 1.0);
        breakdownPercentage.put("2000", 45.0);

        BreakdownResponse breakdownResponse = new BreakdownResponse("year", breakdownPercentage);

        assertEquals(50, breakdownResponse.getBreakdown().get(0).getPercentage());
        assertEquals(45, breakdownResponse.getBreakdown().get(1).getPercentage());
        assertEquals(15, breakdownResponse.getBreakdown().get(2).getPercentage());
        assertEquals(10, breakdownResponse.getBreakdown().get(3).getPercentage());
        assertEquals(1, breakdownResponse.getBreakdown().get(4).getPercentage());
    }

}