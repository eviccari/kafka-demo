package com.viccari.learning.kafka_demo.exceptions;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends Exception implements HttpException{

    private static final long serialVersionUID = 3234537653880562343L;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

}
