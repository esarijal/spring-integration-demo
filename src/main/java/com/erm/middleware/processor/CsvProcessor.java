package com.erm.middleware.processor;

import com.erm.middleware.payload.ClassData;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.OpencsvUtils;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

@Component
public class CsvProcessor {

    public List<ClassData> mapFromFile(String filename) throws FileNotFoundException {
        CsvToBean<ClassData> csv = new CsvToBean<>();
        CSVReader reader = new CSVReader(new FileReader(filename));
        csv.setCsvReader(reader);
        csv.setMappingStrategy(OpencsvUtils.determineMappingStrategy(ClassData.class, Locale.getDefault()));

        return csv.parse();
    }

    public Path writeCsvFromData(List<ClassData> data) throws IOException {

        File tempFile = File.createTempFile("middleware_", ".csv");
        try(FileWriter fileWriter = new FileWriter(tempFile)){
            StatefulBeanToCsvBuilder<ClassData> builder = new StatefulBeanToCsvBuilder<>(fileWriter);
            builder.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(OpencsvUtils.determineMappingStrategy(ClassData.class,
                    Locale.getDefault()));

            StatefulBeanToCsv<ClassData> writer = builder.build();

            writer.write(data);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            e.printStackTrace();
            throw new RuntimeException("Cannot write CSV file");
        }
        return tempFile.toPath();
    }
}
