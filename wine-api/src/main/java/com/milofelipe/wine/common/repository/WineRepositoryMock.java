package com.milofelipe.wine.common.repository;

import com.milofelipe.wine.common.domain.Grape;
import com.milofelipe.wine.common.domain.GrapePercentage;
import com.milofelipe.wine.common.domain.Region;
import com.milofelipe.wine.common.domain.Variety;
import com.milofelipe.wine.common.domain.Wine;
import com.milofelipe.wine.common.service.WineRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Mocked wine repository containing three records. Lot Codes:
 * - 11YVCHAR001
 * - 11YVCHAR002
 * - 15MPPN002-VK
 */
@Component
public class WineRepositoryMock implements WineRepository {

    private final Map<String, Wine> wineDatabase = new HashMap<>();

    public WineRepositoryMock() {
        wineDatabase.put("11YVCHAR001", createWine11YVCHAR001());
        wineDatabase.put("11YVCHAR002", createWine11YVCHAR002());
        wineDatabase.put("15MPPN002-VK", createWine15MPPN002VK());
    }

    @Override
    public Optional<Wine> searchByLotCode(String lotCode) {
        return Optional.ofNullable(wineDatabase.get(lotCode));
    }

    @Override
    public List<Wine> getAll() {
        return new ArrayList<>(wineDatabase.values());
    }

    @Override
    public List<Wine> searchByLotCodeOrDescription(String lotCodeOrDescription) {
        return wineDatabase.values().stream().filter(wine -> wine.getLotCode().contains(lotCodeOrDescription) ||
                wine.getDescription().contains(lotCodeOrDescription)).collect(Collectors.toList());
    }

    private Wine createWine11YVCHAR001() {
        return Wine.builder().lotCode("11YVCHAR001").volume(1000.0).description("2011 Yarra Valley Chardonnay")
                .tankCode("T25-01").productState("Ready for bottling").ownerName("YV Wines Pty Ltd")
                .components(Arrays.asList(
                        GrapePercentage.builder().percentage(5.0).grape(
                                Grape.builder().year(2011).variety(Variety.PINOT_NOIR).region(Region.MORNINGTON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(80.0).grape(
                                Grape.builder().year(2011).variety(Variety.CHARDONNAY).region(Region.YARRA_VALLEY).build()
                        ).build(),
                        GrapePercentage.builder().percentage(5.0).grape(
                                Grape.builder().year(2010).variety(Variety.PINOT_NOIR).region(Region.MACEDON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(10.0).grape(
                                Grape.builder().year(2010).variety(Variety.CHARDONNAY).region(Region.MACEDON).build()
                        ).build()
                        )
                ).build();
    }

    private Wine createWine11YVCHAR002() {
        return Wine.builder().lotCode("11YVCHAR002").volume(5077.0).description("")
                .tankCode("T25-06").productState("").ownerName("YV Wines P/L and Vintage Kerr Joint Venture")
                .components(Arrays.asList(
                        GrapePercentage.builder().percentage(5.0).grape(
                                Grape.builder().year(2011).variety(Variety.PINOT_NOIR).region(Region.MORNINGTON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(80.0).grape(
                                Grape.builder().year(2011).variety(Variety.CHARDONNAY).region(Region.YARRA_VALLEY).build()
                        ).build(),
                        GrapePercentage.builder().percentage(5.0).grape(
                                Grape.builder().year(2010).variety(Variety.PINOT_NOIR).region(Region.MACEDON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(10.0).grape(
                                Grape.builder().year(2010).variety(Variety.CHARDONNAY).region(Region.MACEDON).build()
                        ).build()
                        )
                ).build();
    }

    private Wine createWine15MPPN002VK() {
        return Wine.builder().lotCode("15MPPN002-VK").volume(100000.0).description("2015 Mornington Peninsula Pinot " +
                "Noir - Vintage Kerr special batch")
                .tankCode("T100-03").productState("Filtered").ownerName("Vintage Kerr")
                .components(Arrays.asList(
                        GrapePercentage.builder().percentage(60.0).grape(
                                Grape.builder().year(2015).variety(Variety.PINOT_NOIR).region(Region.MORNINGTON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(2.0).grape(
                                Grape.builder().year(2015).variety(Variety.PINOT_NOIR).region(Region.YARRA_VALLEY).build()
                        ).build(),
                        GrapePercentage.builder().percentage(5.0).grape(
                                Grape.builder().year(2014).variety(Variety.PINOT_NOIR).region(Region.YARRA_VALLEY).build()
                        ).build(),
                        GrapePercentage.builder().percentage(3.0).grape(
                                Grape.builder().year(2015).variety(Variety.MERLOT).region(Region.YARRA_VALLEY).build()
                        ).build(),
                        GrapePercentage.builder().percentage(1.0).grape(
                                Grape.builder().year(2015).variety(Variety.SHIRAZ).region(Region.MORNINGTON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(2.0).grape(
                                Grape.builder().year(2015).variety(Variety.ZINFANDEL).region(Region.MACEDON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(2.0).grape(
                                Grape.builder().year(2014).variety(Variety.MALBEC).region(Region.PORT_PHILIP).build()
                        ).build(),
                        GrapePercentage.builder().percentage(10.0).grape(
                                Grape.builder().year(2014).variety(Variety.PINOT_NOIR).region(Region.MORNINGTON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(10.0).grape(
                                Grape.builder().year(2015).variety(Variety.PINOT_NOIR).region(Region.MORNINGTON).build()
                        ).build(),
                        GrapePercentage.builder().percentage(5.0).grape(
                                Grape.builder().year(2013).variety(Variety.CABERNET).region(Region.HEATHCOTE).build()
                        ).build()
                        )
                ).build();
    }

}
