package com.viccari.learning.kafka_demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BusinessEntityController extends SimpleController{

    @GetMapping(value = "/healthz", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResponseBody> healthz(){
        log.info("Listening!");
        return SimpleResponseBody.build("listening!", HttpStatus.OK);
    }
}
