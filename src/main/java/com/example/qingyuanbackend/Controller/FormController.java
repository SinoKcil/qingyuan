package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.model.Form;
import com.example.qingyuanbackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 提交工单的控制类
@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/form")
public class FormController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files,
                                         @RequestParam("date") String date,
                                         @RequestParam("abnormalid") Integer abnormalid,
                                         @RequestParam("userid") Integer userid) {
        List<Form> forms = fileService.uploadFiles(files, date, abnormalid, userid);
        Map<String, Object> response = new HashMap<>();
        response.put("success", "true"); // 添加success字段
        response.put("data", forms); // 将原来直接返回的forms作为data字段的值

        // 使用构建的Map作为响应体
        return ResponseEntity.ok().body(response);
    }
}
