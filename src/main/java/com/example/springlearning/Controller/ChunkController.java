package com.example.springlearning.Controller;

import com.example.springlearning.service.ChunkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ChunkController {
    @Autowired
    private ChunkService chunkService;

    @PostMapping("/addChunk")
    public ResponseEntity<?> addChunk(@RequestParam String chunkName) {
        return chunkService.sendChunkData(chunkName);
    }
}
