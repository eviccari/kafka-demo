package com.viccari.learning.kafka_demo.services;

import com.viccari.learning.kafka_demo.exceptions.InternalServerErrorException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import com.viccari.learning.kafka_demo.models.BusinessEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
public class BusinessEntityService {

    /**
     * Apply some business logic in here
     * @param businessEntity - entity to be processed
     */
    public void doProcess(BusinessEntity businessEntity) throws UnprocessableEntityException, InternalServerErrorException {

        businessEntity.validate(businessEntity);
        businessEntity.setReceivedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        if(businessEntity.getWithInternalServerError()) throw new InternalServerErrorException("forced_error_for_retry_process_validation");

        log.info(String.format("received_entity_%s", businessEntity));
    }
}
