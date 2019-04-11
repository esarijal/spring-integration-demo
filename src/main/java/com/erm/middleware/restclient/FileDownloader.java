package com.erm.middleware.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@Component
public class FileDownloader {

    @Autowired
    private Environment env;

    public Path downloadFile(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);

        File tempFile;
        try {
            tempFile = File.createTempFile("middleware_", ".txt");
            Path path = Paths.get(tempFile.getAbsolutePath());
            Files.write(path, response.getBody());

            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Cannot create file");
    }
}
