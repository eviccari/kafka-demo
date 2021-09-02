package com.viccari.learning.kafka_demo.models;

import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;

public interface EntityValidatable <T>{

    /**
     * Make basic validations here
     * @param entity - business entity to be validated
     */
    void validate(T entity) throws UnprocessableEntityException;
}
