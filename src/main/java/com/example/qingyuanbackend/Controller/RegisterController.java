package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.model.User;
import com.example.qingyuanbackend.responseOrRequest.RegisterResponse;
import com.example.qingyuanbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/register")
@Tag(name = "Register", description = "用户的注册控制类")
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json")
    @Operation(summary = "用户注册", description = "用户通过输入用户名、手机号和密码进行注册。头像可以后续上传，角色默认为`检测员`，管理区域需要由管理员选择。",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
            }))
    public ResponseEntity<?> register(@RequestBody User user){
        RegisterResponse response = userService.register(user);
        return ResponseEntity.ok(response);
    }
}