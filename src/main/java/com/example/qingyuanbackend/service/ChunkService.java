package com.example.qingyuanbackend.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

// 添加chunk
@Service
public class ChunkService {

    private static final String FLASK_URL = "http://localhost:5555/addChunks";

    public ResponseEntity<?> sendChunkData(String chunkName) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String requestJson = "{\"chunkName\":\"" + chunkName + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        try {
            return restTemplate.exchange(FLASK_URL, HttpMethod.POST, entity, String.class);
        } catch (HttpClientErrorException e) {
            // 对于4xx错误，通常表示客户端请求有问题
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            // 对于5xx错误，通常表示服务器端出现问题
            return ResponseEntity.status(e.getStatusCode()).body("Server error when adding chunk: " + e.getMessage());
        } catch (Exception e) {
            // 其他异常，如网络问题等
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }
}
