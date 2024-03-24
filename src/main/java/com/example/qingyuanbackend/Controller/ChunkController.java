package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.service.ChunkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController

@RequestMapping("/chunks")
public class ChunkController {

    @Autowired
    private ChunkService chunkService;

    @PostMapping(consumes = "tex/plain")
    @Operation(summary = "添加一个新的区域Chunk", description = "通过POST请求的Body直接传入chunkName的纯文本" +
            "字符串来添加一个新的区域Chunk。",
            requestBody = @RequestBody(required = true, content = @Content(mediaType = "text/plain",
                    schema = @Schema(type = "string", description = "直接传入纯文本的Chunk名称。"))))
    public ResponseEntity<?> addChunk(@org.springframework.web.bind.annotation.RequestBody String chunkName) {
        try {
            return chunkService.sendChunkData(chunkName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add chunk: " + e.getMessage());
        }
    }
}
