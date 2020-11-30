package com.milofelipe.wine.search.service;

import com.milofelipe.wine.common.domain.Wine;
import com.milofelipe.wine.common.service.WineRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WineService {

    private final WineRepository wineRepository;

    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    public List<Wine> getAll() {
        return wineRepository.getAll();
    }

    public List<Wine> searchByLotCodeOrDescription(String lotCodeOrDescription) {
        if (lotCodeOrDescription == null) {
            return Collections.emptyList();
        }

        return wineRepository.searchByLotCodeOrDescription(lotCodeOrDescription);
    }

    public Optional<Wine> getByLotCode(String lotCode) {
        return wineRepository.searchByLotCode(lotCode);
    }

}
