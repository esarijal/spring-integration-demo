package com.erm.middleware.handler;

import com.erm.middleware.restclient.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class UploadChannelHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final FileUploader uploader;

    @Autowired
    private Environment env;

    public UploadChannelHandler(FileUploader uploader) {
        this.uploader = uploader;
    }

    @ServiceActivator(inputChannel = "uploadChannel")
    public void handle(Path path){
        ResponseEntity<String> response = uploader.uploadFile(
                env.getRequiredProperty("upload.url"), path.toAbsolutePath().toString());

        logger.info("Final response from upload: {}", response.getBody());

    }
}
