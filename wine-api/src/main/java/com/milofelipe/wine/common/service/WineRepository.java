package com.milofelipe.wine.common.service;

import com.milofelipe.wine.common.domain.Wine;

import java.util.List;
import java.util.Optional;

public interface WineRepository {
    Optional<Wine> searchByLotCode(String lotCode);

    List<Wine> getAll();

    List<Wine> searchByLotCodeOrDescription(String lotCodeOrDescription);
}
