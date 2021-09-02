package com.viccari.learning.kafka_demo.services;

import com.viccari.learning.kafka_demo.exceptions.InternalServerErrorException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.servlet.ServletException;
import java.util.ArrayList;

@Service
@Slf4j
public class KafkaPublisherService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Send a string message to topic
     * @param topic - topic destination of message
     * @param message - payload of message
     */
    public void send(String topic, String message) throws UnprocessableEntityException, InternalServerErrorException{
        if(isInvalid(message)) throw new UnprocessableEntityException("message_is_required");

        try {
            kafkaTemplate.send(topic, message);
        }catch (KafkaException ke){
            throw new InternalServerErrorException(ke.getMessage());
        }
    }

    /**
     * Send a string message to topic, in synchronous way
     * @param topic - topic destination of message
     * @param message - payload of message
     */
    public SendResult<String, String> sendWithSynchronous(String topic, String message) throws UnprocessableEntityException, InternalServerErrorException{
        if(isInvalid(message)) throw new UnprocessableEntityException("message_is_required");

        try {
            var future = kafkaTemplate.send(topic, message);
            return future.get();

        }catch (Exception ke){
            throw new InternalServerErrorException(ke.getMessage(), ke);
        }
    }

    /**
     * To verify if the message is valid
     * @param message - payload of message
     * @return boolean validation
     */
    private boolean isInvalid(String message){
        return message == null;
    }
}
