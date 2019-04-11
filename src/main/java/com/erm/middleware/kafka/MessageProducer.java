package com.erm.middleware.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

//@Component
public class MessageProducer {

    private final String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate, Environment environment) {
        super();
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = environment.getRequiredProperty("kafka.topic-name");
    }

    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Message Sent: [" + message + "] with offset: ["+
                        result.getRecordMetadata().offset()+"]");

            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message: [" + message +"] due to: " + ex.getMessage());

            }
        });
    }
}
