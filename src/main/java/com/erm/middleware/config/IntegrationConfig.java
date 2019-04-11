package com.erm.middleware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    @Primary
    public MessageChannel kafkaChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel downloadChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel processorChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel csvChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel uploadChannel(){
        return new DirectChannel();
    }
}
