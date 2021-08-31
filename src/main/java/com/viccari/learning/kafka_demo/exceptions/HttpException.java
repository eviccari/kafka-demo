package com.viccari.learning.kafka_demo.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author Eduardo Viccari
 * To specifies the Http Exceptions contract for RESTfull responses
 */
public interface HttpException {

    /**
     * Return the respective http status for exception
     * @return http status
     */
    HttpStatus getHttpStatus();

    /**
     * Return the most specific message for exception
     * @return error message
     */
    String getMessage();

    /**
     * Flag to print a stack trace from error
     * @return boolean validation
     */
    boolean printableStack();

    /**
     * To print stack trace error
     */
    void printStackTrace();


}
