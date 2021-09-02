package com.viccari.learning.kafka_demo.services;

public class StringValidationService {

    /**
     * to verify if the string is empty, testing null, empty and black values
     * @param value - string parameter
     * @return boolean validation
     */
    public static boolean isEmpty(String value){
        return value == null || value.isEmpty() || value.isBlank();
    }
}
