package com.viccari.learning.kafka_demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viccari.learning.kafka_demo.exceptions.BadRequestException;
import com.viccari.learning.kafka_demo.exceptions.InternalServerErrorException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import com.viccari.learning.kafka_demo.models.BusinessEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessConsumerRetryService {

/*    @Autowired
    BusinessEntityService businessEntityService;

    @Autowired
    KafkaPublisherService kafkaPublisherService;

    @KafkaListener(topics = "business-topic-retry-01", groupId = "businessConsumerGroupId")
    public void businessTopicListenerRetry01(String message) throws UnprocessableEntityException, BadRequestException, InternalServerErrorException {
        try {

            log.info("first_retry_attempt:waiting_2_seconds");
            Thread.sleep(2000); // in the first retry attempt, wait 2 seconds
            var businessEntity = new ObjectMapper().readValue(message, BusinessEntity.class);

            try {
                businessEntityService.doProcess(businessEntity);
            }catch (InternalServerErrorException ise){
                kafkaPublisherService.sendWithSynchronous("business-topic-retry-02", message);
            }

        }catch (JsonProcessingException jpe){
            throw new BadRequestException(jpe.getMessage());
        } catch (InterruptedException ie) {
            throw new InternalServerErrorException(ie.getMessage());
        }
    }

    @KafkaListener(topics = "business-topic-retry-02", groupId = "businessConsumerGroupId")
    public void businessTopicListenerRetry02(String message) throws UnprocessableEntityException, BadRequestException, InternalServerErrorException {
        try {

            log.info("second_retry_attempt:waiting_3_seconds");
            Thread.sleep(3000); // in the second retry attempt, wait 3 seconds
            var businessEntity = new ObjectMapper().readValue(message, BusinessEntity.class);

            try {
                businessEntityService.doProcess(businessEntity);
            }catch (InternalServerErrorException ise){
                kafkaPublisherService.sendWithSynchronous("business-topic-retry-03", message);
            }

        }catch (JsonProcessingException jpe){
            throw new BadRequestException(jpe.getMessage());
        } catch (InterruptedException ie) {
            throw new InternalServerErrorException(ie.getMessage());
        }
    }

    @KafkaListener(topics = "business-topic-retry-03", groupId = "businessConsumerGroupId")
    public void businessTopicListenerRetry03(String message) throws UnprocessableEntityException, BadRequestException, InternalServerErrorException {
        try {

            log.info("third_retry_attempt:waiting_5_seconds");
            Thread.sleep(5000); // in the third retry attempt, wait 5 seconds
            var businessEntity = new ObjectMapper().readValue(message, BusinessEntity.class);

            try {
                businessEntityService.doProcess(businessEntity);
            }catch (InternalServerErrorException ise){
                log.info("no_more_attempts:send_to_DLQ");
                kafkaPublisherService.sendWithSynchronous("business-topic-error-DLQ", message);
            }

        }catch (JsonProcessingException jpe){
            throw new BadRequestException(jpe.getMessage());
        } catch (InterruptedException ie) {
            throw new InternalServerErrorException(ie.getMessage());
        }
    }

 */

}
