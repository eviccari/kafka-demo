package com.viccari.learning.kafka_demo.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends Exception implements HttpException{

    private static final long serialVersionUID = 7767447569469035061L;

    public InternalServerErrorException() {
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public boolean printableStack() {
        return true;
    }
}
