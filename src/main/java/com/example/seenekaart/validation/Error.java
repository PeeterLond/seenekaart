package com.example.seenekaart.validation;

import lombok.Getter;

@Getter
public enum Error {
    NO_LOCATIONS("Hetkel pole ühtegi asukohta andmebaasi lisatud.", 111);

    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
