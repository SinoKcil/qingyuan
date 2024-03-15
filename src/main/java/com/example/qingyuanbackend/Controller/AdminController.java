package com.example.qingyuanbackend.Controller;

import com.example.qingyuanbackend.mapper.RegionMapper;
import com.example.qingyuanbackend.responseOrRequest.LoginRequest;
import com.example.qingyuanbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

/*
* 只有权限是admin的用户才能够进行角色管理，分配检测区域，或者删除用户。
* 新：权限归前端管理
* */
@RestController
@RequestMapping("/admin")
@Tag(name = "Admin", description = "用户管理")
public class AdminController {

    @Autowired
    private UserService userService;

    // 获取所有用户信息及其区域列表
    @GetMapping(value = "/users")
    @Operation(summary = "获取所有用户信息和区域信息", description = "前端首先向该route发起请求，然后获取到所有的用户信息和区域信息" +
            "，封装在user的list中" +
            "可以直接用来循环渲染，需要通过'Authorization'请求头中的JWT来进行身份验证。" +
            "该查询是分页查询" +
            "当用户进行更改或者删除之后，前端可以重新请求这个路由",
            parameters = {
            @Parameter(in = ParameterIn.QUERY, name = "page",description = "页码", required = true),
            @Parameter(in = ParameterIn.QUERY, name = "size", description =  "一页的大小,按照前端规定", required = true)
    }
            )
    public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int size){
        return userService.getAllUsersAndRegions(page, size);
    }

    // 更新用户的区域
    @PutMapping("/users/{username}/region")
    @Operation(summary = "更新用户的区域", description = "该接口允许管理员修改指定用户的区域。操作成功返回更新后的用户信息。" +
            "操作需要管理员权限，并通过'Authorization'请求头中的JWT进行身份验证。")
    public ResponseEntity<?> updateUserRegion(
                                              @PathVariable String username,
                                              @RequestParam String region) {
        return userService.updateUserRegion(username, region);
    }

    // 删除用户
    @DeleteMapping("/users/{username}")
    @Operation(summary = "删除用户", description = "该接口允许管理员删除指定的用户。操作成功返回确认信息。" +
            "操作需要管理员权限，并通过'Authorization'请求头中的JWT进行身份验证。")
    public ResponseEntity<?> deleteUser(
                                        @PathVariable String username) {
        return userService.deleteUser(username);
    }





}
