package com.example.qingyuanbackend.Service;
import com.example.qingyuanbackend.service.OperationDataUploadService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OperationDataUploadServiceTest {

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private OperationDataUploadService service;

    @Mock
    private HttpResponse httpResponse;

    @Mock
    private HttpEntity httpEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // 假设可以通过某种方式将mock的httpClient注入到service中
    }

    @Test
    public void testUploadFileAndProcessResponse_Success() throws Exception {
        // 模拟成功的响应
        String mockResponseString = "{\"code\":200000,\"message\":\"Success\",\"filePath\":\"/path/to/file\",\"fileName\":\"filename.csv\"}";
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(mockResponseString.getBytes()));
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);

        // 构建MockMultipartFile
        MultipartFile file = new MockMultipartFile("file", "filename.csv", "text/plain", "some csv data".getBytes());

        // 执行测试的方法
        String result = service.uploadFileAndProcessResponse(file, "chunkName");

        // 验证
        assertNotNull(result);
        // 根据实际逻辑进行断言验证
    }
}
