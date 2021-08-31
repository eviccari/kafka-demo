package com.viccari.learning.kafka_demo.services;

import com.viccari.learning.kafka_demo.exceptions.InternalServerErrorException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.servlet.ServletException;
import java.util.ArrayList;

@Service
public class KafkaPublisherService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Send a string message to topic
     * @param topic
     * @param message
     * @throws UnprocessableEntityException
     * @throws InternalServerErrorException
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
     * @param topic
     * @param message
     * @throws UnprocessableEntityException
     * @throws InternalServerErrorException
     */
    public void sendWithSynchronous(String topic, String message) throws UnprocessableEntityException, InternalServerErrorException{
        if(isInvalid(message)) throw new UnprocessableEntityException("message_is_required");

        try {
            var future = kafkaTemplate.send(topic, message);
            var statusList = new ArrayList<String>();

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    statusList.add("nok");
                    statusList.add(throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, String> stringStringSendResult) {
                    statusList.add("ok");
                }
            });

            if(!statusList.get(0).equals("ok")){
                throw new InternalServerErrorException(statusList.get(1));
            }

        }catch (Exception ke){
            throw new InternalServerErrorException(ke.getMessage(), ke);
        }
    }

    /**
     * To verify if the message is valid
     * @param message
     * @return boolean validation
     */
    private boolean isInvalid(String message){
        return message == null;
    }
}
