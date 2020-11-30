package com.milofelipe.wine.breakdown.controller;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
class BreakdownResponse {
    private String breakdownType;
    private List<BreakdownDetails> breakdown = new ArrayList();

    /**
     * REST API response object. Breakdown percentage will be sorted from highest to lowest
     * upon creation.
     *
     * @param type corresponds to the "breakdownType" field in the JSON response
     * @param breakdownPercentage map of "key" and "percentage" pairs
     */
    public BreakdownResponse(String type, Map<String, Double> breakdownPercentage) {
        breakdownType = type;
        for (Map.Entry<String, Double> mapEntry : breakdownPercentage.entrySet()) {
            breakdown.add(new BreakdownDetails(mapEntry.getKey(), mapEntry.getValue()));
        }
        Collections.sort(breakdown, (o1, o2) -> {
            if (o1.percentage < o2.percentage) {
                return 1;
            } else if (o1.percentage > o2.percentage) {
                return -1;
            } else {
                return 0;
            }
        });
    }

    @Getter
    class BreakdownDetails {
        private double percentage;
        private String key;

        public BreakdownDetails(String key, double percentage) {
            this.key = key;
            this.percentage = percentage;
        }
    }
}
