package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.responseOrRequest.LoginRequest;
import com.example.qingyuanbackend.responseOrRequest.LoginResponse;
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
@RequestMapping(value = "/login", method = RequestMethod.POST)
@Tag(name = "Login", description = "用户的登录控制类")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping(consumes = "application/json")
    @Operation(summary = "用户登录", description = "用户通过输入用户名和密码进行登录。",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginRequest.class))
            }))
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.toString());
        LoginResponse loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

}
