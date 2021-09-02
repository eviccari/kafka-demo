package com.viccari.learning.kafka_demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viccari.learning.kafka_demo.exceptions.BadRequestException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import com.viccari.learning.kafka_demo.models.BusinessEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "business-topic", groupId = "groupId")
    public void businessTopicListener(String message) throws UnprocessableEntityException, BadRequestException {

        if(message == null){
           throw new UnprocessableEntityException("message_is_required");
        }

        try {

            var businessEntity = new ObjectMapper().readValue(message, BusinessEntity.class);
            log.info(String.format("Incoming message: %s", businessEntity.toString()));

        }catch (JsonProcessingException jpe){
            throw new BadRequestException(jpe.getMessage());
        }

    }
}
