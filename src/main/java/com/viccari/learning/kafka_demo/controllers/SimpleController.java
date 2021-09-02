package com.viccari.learning.kafka_demo.controllers;

import com.viccari.learning.kafka_demo.exceptions.BadRequestException;
import com.viccari.learning.kafka_demo.exceptions.HttpException;
import com.viccari.learning.kafka_demo.exceptions.InternalServerErrorException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class SimpleController {

    @Autowired
    HttpServletRequest request;

    @ExceptionHandler({UnprocessableEntityException.class, InternalServerErrorException.class, BadRequestException.class})
    public ResponseEntity<SimpleResponseBody> handleHttpException(HttpServletRequest request, HttpException exception){

        if(exception.printableStack()){
            exception.printStackTrace();
        }

        return new SimpleResponseBody()
            .withMessage(exception.getMessage())
            .withHttpStatus(exception.getHttpStatus())
        .build();
    }

    /**
     * Helper class to map simple http responses
     */
    public static class SimpleResponseBody {

        private String message;
        private HttpStatus httpStatus;

        public SimpleResponseBody withMessage(String message){
            this.message = message;
            return this;
        }

        public SimpleResponseBody withHttpStatus(HttpStatus httpStatus){
            this.httpStatus = httpStatus;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public ResponseEntity<SimpleResponseBody> build(){
            this.message = this.message == null ? "empty_message" : this.message;
            this.httpStatus = this.httpStatus == null ? HttpStatus.OK : this.httpStatus;

            var result = new SimpleResponseBody();
            result.message = this.message;
            result.httpStatus = this.httpStatus;

            return new ResponseEntity<>(
                result,
                this.httpStatus
            );
        }

        public static ResponseEntity<SimpleResponseBody> build(String message, HttpStatus httpStatus){

            var result = new SimpleResponseBody()
                .withMessage(message)
                .withHttpStatus(httpStatus);

            return new ResponseEntity<>(
                result,
                httpStatus
            );
        }

    }

}
