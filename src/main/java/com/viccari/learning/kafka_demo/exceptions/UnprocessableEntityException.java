package com.viccari.learning.kafka_demo.exceptions;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends Exception implements HttpException{

    private static final long serialVersionUID = 3234537653880562343L;

    public UnprocessableEntityException() {
    }

    public UnprocessableEntityException(String message) {
        super(message);
    }

    public UnprocessableEntityException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    @Override
    public boolean printableStack() {
        return false;
    }
}
