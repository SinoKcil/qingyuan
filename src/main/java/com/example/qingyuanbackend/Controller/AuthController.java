package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.mapper.UserMapper;
import com.example.qingyuanbackend.model.User;
import com.example.qingyuanbackend.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "从token中获得用户信息")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getauth")
    @Operation(summary = "获取认证用户信息", description = "通过解析JWT token来获取当前认证用户的详细信息。",
            responses = {
                    @ApiResponse(description = "成功获取用户信息",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(description = "用户未找到或Token无效",
                            responseCode = "200", // 或者根据实际情况使用400/401等
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    public ResponseEntity<?> getAuthenticatedUser(@RequestHeader("Authorization") String token) {
        // 从Authorization请求头中提取token，通常Bearer 开头，所以要去掉Bearer和空格
        String cleanToken = token.replace("Bearer ", "");

        // 使用JwtUtil从token中获取用户ID
        String userId = jwtUtil.getUserIdFromToken(cleanToken);

        // 使用用户ID从数据库中查询用户信息
        User user = userMapper.findByUsername(userId);

        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("success", true);
            response.put("user", user);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "User not found.");
            return ResponseEntity.ok(response); // 这里可以根据需要使用ok或者badRequest
        }
    }
}
