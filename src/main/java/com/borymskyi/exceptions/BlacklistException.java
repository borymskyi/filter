package com.borymskyi.exceptions;

public class BlacklistException extends RuntimeException {
    public BlacklistException(String message) {
        super(message);
    }
}
