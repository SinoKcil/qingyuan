package com.example.springlearning.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.mime.MultipartEntityBuilder;



// 上传文件Service层
@Service
public class FileUploadService {

    private final String FLASK_API_URL = "http://localhost:5555/LoadDataByCsv";
    private final String FLASK_PREPROCESS_API_URL = "http://localhost:5555/preprocessData";

    private final String FLASK_API_ANALYZE_URL = "http://localhost:5555/analyzeData";

    public String uploadFileAndProcessResponse(MultipartFile file) throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(FLASK_API_URL);

        // 构建文件上传的多部分表单数据
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
        HttpEntity multipart = builder.build();
        post.setEntity(multipart);

        // 执行请求并获取响应
        HttpEntity responseEntity = client.execute(post).getEntity();
        String responseString = EntityUtils.toString(responseEntity);

        // 解析JSON响应
        JSONObject jsonResponse = new JSONObject(responseString);
        if (jsonResponse.getInt("code") == 200000) {
            System.out.println(jsonResponse.getString("filePath")+ "and" + jsonResponse.getString("fileName"));
            return callPreprocessAPI(jsonResponse.getString("filePath"), jsonResponse.getString("fileName"));
        } else {
            throw new Exception("Failed to upload file to Flask API: " + jsonResponse.getString("message"));
        }
    }

//    重载，当chunkName没有值的时候，默认添加到default
    private String callPreprocessAPI(String filePath, String fileName) throws Exception{
        return callPreprocessAPI(filePath, fileName, "Default");
    }

    private String callPreprocessAPI(String filePath, String fileName, String chunkName) throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(FLASK_PREPROCESS_API_URL);
        // 创建JSON对象
        JSONObject json = new JSONObject();
        json.put("filePath", filePath);
        json.put("fileName", fileName);
        json.put("chunkName", chunkName);

        // 设置请求体
        post.setEntity(new org.apache.http.entity.StringEntity(json.toString(), ContentType.APPLICATION_JSON));

        // 执行请求并获取响应
        HttpEntity responseEntity = client.execute(post).getEntity();
        String responseString = EntityUtils.toString(responseEntity);

        // 解析JSON响应
        JSONObject jsonResponse = new JSONObject(responseString);

//        return EntityUtils.toString(responseEntity);
        return callAnalyzeAPI(jsonResponse.getString("chunkName"), jsonResponse.getString("processedDataPath"));
    }


    private String callAnalyzeAPI(String chunkName, String processedDataPath) throws Exception{
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(FLASK_API_ANALYZE_URL);

        JSONObject json = new JSONObject();
        json.put("processedDataPath", processedDataPath);
        json.put("chunkName", chunkName);

// 设置请求体
        post.setEntity(new org.apache.http.entity.StringEntity(json.toString(), ContentType.APPLICATION_JSON));

        // 执行请求并获取响应
        HttpEntity responseEntity = client.execute(post).getEntity();
        String responseString = EntityUtils.toString(responseEntity);
        // 解析JSON响应
        JSONObject jsonResponse = new JSONObject(responseString);

        System.out.println(jsonResponse.toString());
        return EntityUtils.toString(responseEntity);
    }
}