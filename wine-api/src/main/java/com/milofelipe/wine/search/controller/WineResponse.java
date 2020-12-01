package com.milofelipe.wine.search.controller;

import com.milofelipe.wine.common.domain.Wine;
import lombok.Getter;

import java.util.Objects;

@Getter
public class WineResponse {
    private final String lotCode;
    private final double volume;
    private final String description;
    private final String tankCode;
    private final String productState;
    private final String ownerName;

    public WineResponse(Wine wine) {
        Objects.requireNonNull(wine, "Wine is required");
        lotCode = wine.getLotCode();
        volume = wine.getVolume();
        description = wine.getDescription();
        tankCode = wine.getTankCode();
        productState = wine.getProductState();
        ownerName = wine.getOwnerName();
    }

}
