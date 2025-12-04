package com.loutredev.pokecarte.exceptions;

public class JwtValidationException extends RuntimeException {
    public JwtValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
