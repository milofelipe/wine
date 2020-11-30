package com.milofelipe.wine.common.domain;

public enum Variety {
    CHARDONNAY("Chardonnay"),
    CABERNET("Cabernet"),
    MALBEC("Malbec"),
    MERLOT("Merlot"),
    PINOT_NOIR("Pinot Noir"),
    SHIRAZ("Shiraz"),
    ZINFANDEL("Zinfandel");

    private String name;

    Variety(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
