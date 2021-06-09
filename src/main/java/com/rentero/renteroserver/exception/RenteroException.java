package com.rentero.renteroserver.exception;

import org.springframework.http.HttpStatus;

public class RenteroException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public RenteroException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public RenteroException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}