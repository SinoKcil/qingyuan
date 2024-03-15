package com.example.qingyuanbackend.service;

import com.example.qingyuanbackend.mapper.RegionMapper;
import com.example.qingyuanbackend.mapper.UserMapper;
import com.example.qingyuanbackend.model.User;
import com.example.qingyuanbackend.responseOrRequest.LoginRequest;
import com.example.qingyuanbackend.responseOrRequest.LoginResponse;
import com.example.qingyuanbackend.responseOrRequest.RegisterResponse;

import com.example.qingyuanbackend.utils.JwtUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.qingyuanbackend.Message.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${avatar.avatarPath}")
    private String avatarPath;
    @Autowired
    private RegionMapper regionMapper;

    // 列出所有用户
    public List<User> listAllUsers(int page, int size) {
        // 计算offset和limit，RowBounds的两个参数
        int offset = (page - 1) * size;
        int limit = size;

        // 创建RowBounds对象
        RowBounds rowBounds = new RowBounds(offset, limit);

        // 调用Mapper的方法，并传入RowBounds对象以实现分页
        return userMapper.selectAllUsers(rowBounds);
    }

    // 列出所有区域
    public List<String> listAllRegions() {
        return regionMapper.selectAllRegions();
    }

    //根据用户名来更新区域
    public Map<String, Object> updateUserRegionByUsername(String username,String region){
        Map<String, Object> response = new HashMap<>();
        User user = userMapper.findByUsername(username);
        if(user == null){
            response.put("success", false);
            response.put("message", "User not found");
        }
        else{
            user.setRegion(region);
            int result = userMapper.updateUser(user);
            response.put("success", result > 0);
            response.put("user", user);
        }
        return response;
    }

    //根据用户名删除用户，同时返回刚才被删除的用户的信息
    public Map<String, Object> deleteUserByUsername(String username) {
        Map<String, Object> response = new HashMap<>();
        User user = userMapper.findByUsername(username);
        if (user != null) {
            int result = userMapper.deleteUser(username);
            response.put("success", result > 0);
            response.put("user", user);
        } else {
            response.put("success", false);
            response.put("message", "User not found");
        }
        return response;
    }

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
            return new LoginResponse(false, "账号尚未被注册", null, Collections.emptyList(), null, null, 0, null);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse(false, "密码错误", null, Collections.emptyList(), null, null, 0, null);
        }

        // 获取用户角色
        List<String> roles = Arrays.asList(user.getRole());

        // 生成Access Token
        String accessToken = jwtUtil.generateToken(user.getUsername());

        // 生成Refresh Token
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        // 计算Access Token过期时间
        long expiresIn = new Date().getTime() + jwtUtil.getExpiration();

        if(user.getAvatar() == null || Objects.equals(user.getAvatar(), "string")){
            user.setAvatar(avatarPath);
        }

        return new LoginResponse(
                true,
                "登录成功",
                user.getUsername(),
                roles,
                accessToken,
                refreshToken,
                expiresIn,
                user.getAvatar()
        );
    }

    // 业务逻辑 获取所有的用户和区域
    public ResponseEntity<?> getAllUsersAndRegions(int page, int size) {
        RowBounds rowBounds = new RowBounds((page-1) * size, size);
        List<User> users = userMapper.selectAllUsers(rowBounds);
        List<String> regions = listAllRegions();
        Map<String, Object> response = new HashMap<>();

        response.put("success", true);
        response.put("users", users);
        response.put("regions", regions);
        return ResponseEntity.ok(response);
    }

    //业务逻辑 更新用户的区域
    public ResponseEntity<?> updateUserRegion( String username, String region){
//        if(!jwtUtil.isAdmin(token)){
//            return ResponseEntity.status(200)
//                    .body(Map.of("success", false, "message", "只有管理员" +
//                            "才能够进行用户管理"));
//        }
        Map<String, Object> response = updateUserRegionByUsername(username, region);
        return ResponseEntity.ok(response);
    }

    // 业务逻辑，删除用户
    public ResponseEntity<?> deleteUser(String username) {
//        if(!jwtUtil.isAdmin(token)){
//            return ResponseEntity.status(200)
//                    .body(Map.of("success", false, "message", "只有管理员" +
//                            "才能够进行用户管理"));
//        }
        Map<String, Object> response = deleteUserByUsername(username);
        return ResponseEntity.ok(response);
    }

}
