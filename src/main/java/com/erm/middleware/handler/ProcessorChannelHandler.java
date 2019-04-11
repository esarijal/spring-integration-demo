package com.erm.middleware.handler;

import com.erm.middleware.payload.ClassData;
import com.erm.middleware.processor.DataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessorChannelHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final DataProcessor dataProcessor;

    public ProcessorChannelHandler(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }

    @ServiceActivator(inputChannel = "processorChannel", outputChannel = "csvChannel")
    public List<ClassData> handle(List<ClassData> data){
        List<ClassData> newData = dataProcessor.enhanceData(data);
        newData.forEach(d -> logger.info(d.toString()));
        return newData;
    }
}
