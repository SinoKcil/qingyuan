package com.example.qingyuanbackend.service;

import com.example.qingyuanbackend.mapper.UserMapper;
import com.example.qingyuanbackend.model.User;
import com.example.qingyuanbackend.responseOrRequest.LoginRequest;
import com.example.qingyuanbackend.responseOrRequest.LoginResponse;
import com.example.qingyuanbackend.responseOrRequest.RegisterResponse;

import com.example.qingyuanbackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.example.qingyuanbackend.Message.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public RegisterResponse register(User user) {
        if(user.getPassword().length() < 6){
            return new RegisterResponse(false, PASSWORD_TOO_SHORT.getMessage());
        }
//      账号已经被注册
        User existingUser = userMapper.findByUsername(user.getUsername());
        if(existingUser != null){
            return new RegisterResponse(false, ACCOUNT_ALREADY_REGISTERED.getMessage());
        }
//      加密二字
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);

        return new RegisterResponse(true, REGISTRATION_SUCCESS.getMessage());

    }
    public LoginResponse login(LoginRequest request){
        User user = userMapper.findByUsername(request.getUsername());
        if (user == null) {
            return new LoginResponse(false, "账号尚未被注册", null, Collections.emptyList(), null, null, 0);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse(false, "密码错误", null, Collections.emptyList(), null, null, 0);
        }

        // 获取用户角色
        List<String> roles = Arrays.asList(user.getRole());

        // 生成Access Token
        String accessToken = jwtUtil.generateToken(user.getUsername());

        // 生成Refresh Token
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        // 计算Access Token过期时间
        long expiresIn = new Date().getTime() + jwtUtil.getExpiration();

        return new LoginResponse(
                true,
                "登录成功",
                user.getUsername(),
                roles,
                accessToken,
                refreshToken,
                expiresIn
        );
    }

}
