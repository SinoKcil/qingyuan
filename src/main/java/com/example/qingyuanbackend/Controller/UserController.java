package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.model.User;
import com.example.qingyuanbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "用户的注册与登录控制类")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "用户输入用户名、手机号" +
            "设置好自己的密码。头像可以后续上传，角色默认为`检测员`，管理区域需要由管理员选择" +
            )
    @ApiResponse(responseCode = "200", description = "注册成功")
    @ApiResponse(responseCode = "400", description = "注册失败")
    public ResponseEntity<?> register(@RequestBody User user){
        boolean success = userService.register(user);

        if (success){

        }
    }
}
