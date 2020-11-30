package com.milofelipe.wine.common.domain;

public enum Region {
    MORNINGTON("Mornington"),
    YARRA_VALLEY("Yarra Valley"),
    MACEDON("Macedon"),
    HEATHCOTE("Heathcote"),
    PORT_PHILIP("Port Philip");

    private String name;

    Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
