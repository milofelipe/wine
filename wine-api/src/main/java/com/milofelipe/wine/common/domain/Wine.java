package com.milofelipe.wine.common.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Wine {
    private final String lotCode;
    private final double volume;
    private final String description;
    private final String tankCode;
    private final String productState;
    private final String ownerName;
    private final List<GrapePercentage> components;
}
