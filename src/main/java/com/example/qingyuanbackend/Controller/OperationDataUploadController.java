package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.service.OperationDataUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/operationData") // 定义基础路径
@Tag(name = "小车运行数据上传接口", description = "处理小车运行数据上传的请求")
public class OperationDataUploadController {

    @Autowired
    private OperationDataUploadService operationDataUploadService;

    @PostMapping("/upload")
    @Operation(summary = "上传小车运行数据", description = "上传一个小车运行数据并可选地指定一个chunkName。如果未指定chunkName，则使用默认值。",
            requestBody = @RequestBody(content = {
                    @Content(mediaType = "multipart/form-data", schema = @Schema(implementation = FileUploadBody.class))
            }))
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam(value = "chunkName", required = false, defaultValue = "Default") String chunkName) {
        try {
            String response = operationDataUploadService.uploadFileAndProcessResponse(file, chunkName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Adjustments to correctly document the multipart request in Swagger UI
    private static class FileUploadBody {

        // This is just for Swagger UI documentation purposes and does not affect the actual API operation
        @Schema(description = "文件内容", type = "string", format = "binary")
        private String file;

        @Schema(description = "Chunk名称，如果未指定则使用默认值。", type = "string")
        private String chunkName;
    }
}
