package com.epam.library.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
