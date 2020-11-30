package com.milofelipe.wine.common.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GrapePercentage {
    private final double percentage;
    private final Grape grape;
}
