package com.erm.middleware.handler;

import com.erm.middleware.payload.ConsumedMessage;
import com.erm.middleware.restclient.FileDownloader;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class KafkaChannelHandler {

    private final FileDownloader downloader;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public KafkaChannelHandler(FileDownloader downloader) {
        this.downloader = downloader;
    }

    @ServiceActivator(inputChannel = "kafkaChannel", outputChannel = "downloadChannel")
    public Path handle(ConsumedMessage message){
        UrlValidator validator = new UrlValidator();
        if(validator.isValid(message.getUrl())){
            Path path = downloader.downloadFile(message.getUrl());
            logger.info("Return Path is : {}", path.toAbsolutePath());
            return path;
        } else {
            throw new RuntimeException("URL provided is not valid");
        }
    }
}
