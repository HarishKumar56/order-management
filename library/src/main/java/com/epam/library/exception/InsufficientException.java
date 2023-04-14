package com.epam.library.exception;

import lombok.Getter;

@Getter
public class InsufficientException extends RuntimeException{

    private String message;

    public InsufficientException(String message) {
        super(message);
        this.message = message;
    }
}
