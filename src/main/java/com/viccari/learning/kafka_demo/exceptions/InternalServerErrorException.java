package com.viccari.learning.kafka_demo.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends Exception implements HttpException{

    private static final long serialVersionUID = 7767447569469035061L;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
