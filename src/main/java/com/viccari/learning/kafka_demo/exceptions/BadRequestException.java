package com.viccari.learning.kafka_demo.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends Exception implements HttpException{

    private static final long serialVersionUID = 3234537653880562343L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public boolean printableStack() {
        return false;
    }
}
