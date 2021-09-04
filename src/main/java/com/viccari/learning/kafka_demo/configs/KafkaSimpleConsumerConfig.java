package com.viccari.learning.kafka_demo.configs;

import com.viccari.learning.kafka_demo.exceptions.BadRequestException;
import com.viccari.learning.kafka_demo.exceptions.InternalServerErrorException;
import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;

@EnableKafka
@Configuration
@Slf4j
public class KafkaSimpleConsumerConfig {

    @Value("${app.params.kafka.bootstrapAddress}")
    String bootstrapAddress;

    @Value("${app.params.kafka.groupId}")
    String groupId;

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        var configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory(){
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRetryTemplate(retryTemplate());

        factory.setErrorHandler(((exception, data) ->{
            log.error("error_occurs_with_exception_{}_and_data_{}", exception, data);
        }));

        factory.setRecoveryCallback((retryContext -> {
            if(retryContext.getLastThrowable().getCause() instanceof RecoverableDataAccessException){
                log.info("recoverable_data");
            } else {
                throw new RuntimeException(retryContext.getLastThrowable().getMessage());
            }

            return null;
        }));

        return factory;
    }

    @Bean
    public RetryTemplate retryTemplate(){
        var retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(simpleRetryPolicy());
        return retryTemplate;
    }

    @Bean
    public SimpleRetryPolicy simpleRetryPolicy(){
        var exceptionsMap = new HashMap<Class<? extends Throwable>, Boolean>();
        exceptionsMap.put(InternalServerErrorException.class, true);
        exceptionsMap.put(BadRequestException.class, false);
        exceptionsMap.put(UnprocessableEntityException.class, false);

        return new SimpleRetryPolicy(1, exceptionsMap, true);
    }
}
