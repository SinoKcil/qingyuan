package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.responseOrRequest.LoginRequest;
import com.example.qingyuanbackend.responseOrRequest.LoginResponse;
import com.example.qingyuanbackend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login", method = RequestMethod.POST)
@Tag(name = "Login", description = "用户的登录控制类")
public class LoginController {
    @Autowired
    private UserService userService;

    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.login(loginRequest);
        if(loginResponse.isSuccess()){
            return ResponseEntity.ok(loginResponse);
        }else{
            return ResponseEntity.badRequest().body(loginResponse);
        }
    }

}
