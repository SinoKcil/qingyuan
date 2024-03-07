package com.example.springlearning.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// 添加chunk
@Service
public class ChunkService {

    private  static final String FLASK_URL = "http://localhost:5555/addChunks";

    public ResponseEntity<?> sendChunkData(String chunkName){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"chunkName\":\"" + chunkName + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        try {
            return restTemplate.exchange(FLASK_URL, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add chunk due to: " + e.getMessage());
        }
    }
}
