package com.milofelipe.wine.common.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Grape {
    private final int year;
    private final Variety variety;
    private final Region region;
}
