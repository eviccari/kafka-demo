package com.viccari.learning.kafka_demo.controllers;

import com.viccari.learning.kafka_demo.exceptions.InternalServerErrorException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import com.viccari.learning.kafka_demo.models.BusinessEntity;
import com.viccari.learning.kafka_demo.payloads.BusinessPayload;
import com.viccari.learning.kafka_demo.services.KafkaPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BusinessEntityController extends SimpleController{

    @Autowired
    KafkaPublisherService kafkaPublisherService;

    @GetMapping(value = "/healthz", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResponseBody> healthz(){
        log.info("Listening!");
        return SimpleResponseBody.build("listening!", HttpStatus.OK);
    }

    @PostMapping(value = "/sendMessage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimpleResponseBody> sendMessage(@RequestBody BusinessPayload payload) throws UnprocessableEntityException, InternalServerErrorException {

        var businessEntity = new BusinessEntity();
        businessEntity.setId(payload.getId());
        businessEntity.setDescription(payload.getDescription());

        kafkaPublisherService.sendWithSynchronous(BusinessEntity.BUSINESS_TOPIC, businessEntity.toString());
        return SimpleResponseBody.build("request_ok", HttpStatus.OK);
    }
}
