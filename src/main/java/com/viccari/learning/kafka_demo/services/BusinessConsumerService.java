package com.viccari.learning.kafka_demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viccari.learning.kafka_demo.exceptions.BadRequestException;
import com.viccari.learning.kafka_demo.exceptions.InternalServerErrorException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import com.viccari.learning.kafka_demo.models.BusinessEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
public class BusinessConsumerService {

    @Autowired
    BusinessEntityService businessEntityService;

    @Autowired
    KafkaPublisherService kafkaPublisherService;


    @KafkaListener(topics = "business-topic", groupId = "businessConsumerGroupId")
    public void businessTopicListener(String message) throws UnprocessableEntityException, BadRequestException, InternalServerErrorException {

        try {

            var businessEntity = new ObjectMapper().readValue(message, BusinessEntity.class);

//            try {
                businessEntity.setReceivedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                businessEntityService.doProcess(businessEntity);
//            }catch (InternalServerErrorException ise){
                kafkaPublisherService.sendWithSynchronous("business-topic-retry-01", businessEntity.toString());
//            }

        }catch (JsonProcessingException jpe){
            throw new BadRequestException(jpe.getMessage());
        }

    }
}
