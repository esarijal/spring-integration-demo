package com.erm.middleware.processor;

import com.erm.middleware.payload.ClassData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataProcessor  {

    public List<ClassData> enhanceData(List<ClassData> data) {
        List<ClassData> newData = new ArrayList<>(data);
        newData.add(new ClassData("Class-3", 20, 13) );
        newData.add(new ClassData("Class-4", 10, 12) );
        newData.add(new ClassData("Class-5", 24, 16) );
        return newData;
    }
}
