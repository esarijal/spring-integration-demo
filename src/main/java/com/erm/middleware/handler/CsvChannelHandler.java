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
public class CsvChannelHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final CsvProcessor csvProcessor;

    public CsvChannelHandler(CsvProcessor csvProcessor) {
        this.csvProcessor = csvProcessor;
    }

    @ServiceActivator(inputChannel = "csvChannel", outputChannel = "uploadChannel")
    public Path handle(List<ClassData> data){
        try {
            Path path = csvProcessor.writeCsvFromData(data);
            logger.info("New CSV file: {}", path.toAbsolutePath());
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot Write CSV File from Data");
        }
    }
}
