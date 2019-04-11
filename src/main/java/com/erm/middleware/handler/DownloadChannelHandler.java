package com.erm.middleware.handler;

import com.erm.middleware.payload.ClassData;
import com.erm.middleware.processor.CsvProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public class DownloadChannelHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final CsvProcessor csvMapper;

    public DownloadChannelHandler(CsvProcessor csvMapper) {
        this.csvMapper = csvMapper;
    }

    @ServiceActivator(inputChannel = "downloadChannel", outputChannel = "processorChannel")
    public List<ClassData> handle(Path path){
        try {
            List<ClassData> classData = csvMapper.mapFromFile(path.toAbsolutePath().toString());
            classData.forEach(e -> logger.info(e.toString()));
            return classData;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot map CSV file");
        }
    }
}
