package com.milofelipe.wine.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WineNotFoundException extends RuntimeException {
    public WineNotFoundException(String lotCode) {
        super(String.format("Could not find Lot Code %s", lotCode));
    }
}
