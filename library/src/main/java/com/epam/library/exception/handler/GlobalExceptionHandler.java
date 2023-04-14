package com.epam.library.exception.handler;

import com.epam.library.dto.ErrorDto;
import com.epam.library.exception.InsufficientException;
import com.epam.library.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleNotFoundException(NotFoundException notFoundException){
        return new ErrorDto(notFoundException.getMessage() , System.currentTimeMillis());
    }

    @ExceptionHandler(InsufficientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto insufficientBalanceException(InsufficientException notFoundException){
        return new ErrorDto(notFoundException.getMessage() , System.currentTimeMillis());
    }
}
