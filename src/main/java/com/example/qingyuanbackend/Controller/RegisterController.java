package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.model.User;
import com.example.qingyuanbackend.responseOrRequest.RegisterResponse;
import com.example.qingyuanbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/register", method = RequestMethod.POST)
@Tag(name = "Register", description = "用户的注册控制类")
public class RegisterController {
    @Autowired
    private UserService userService;

//    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "用户输入用户名、手机号" +
            "设置好自己的密码。头像可以后续上传，角色默认为`检测员`，管理区域需要由管理员选择"
            )
    @ApiResponse(responseCode = "200", description = "注册成功")
    @ApiResponse(responseCode = "400", description = "注册失败")
    public ResponseEntity<?> register(@RequestBody User user){
        RegisterResponse response = userService.register(user);
        if(response.isSuccess()){
            return ResponseEntity.ok(response);

        }else {
            return ResponseEntity.badRequest().body(response); // 当操作失败时，返回400 Bad Request状态码及响应体
        }
    }
}
