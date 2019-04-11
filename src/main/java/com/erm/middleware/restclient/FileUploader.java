package com.erm.middleware.restclient;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class FileUploader {

    private final String FORM_FILE = "upload";

    public ResponseEntity<String> uploadFile(String url, String fileName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        FileSystemResource value = new FileSystemResource(fileName);
        body.add(FORM_FILE, value);
//        body.add("id", LocalDate.now());

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<MultiValueMap<String, Object>>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForEntity(url, requestEntity, String.class);
    }
}
