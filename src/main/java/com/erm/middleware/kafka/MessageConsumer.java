package com.erm.middleware.kafka;

import com.erm.middleware.payload.ConsumedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private CountDownLatch latch = new CountDownLatch(3);

    @Autowired
    private MessageChannel kafkaChannel;

    @KafkaListener(topics= "${kafka.topic-name}", groupId="${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        logger.info("Received message : [" + message + "]");
        ConsumedMessage consumedMessage = new ConsumedMessage(message);
        Message<ConsumedMessage> msg = MessageBuilder.withPayload(consumedMessage).build();
        kafkaChannel.send(msg);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
