package com.example.qingyuanbackend.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

// 用户表
@TableName("user")

public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id; // 如果使用基于数据库的自增ID，推荐使用Long类型
    private String username;     // 用户的用户名
    private String phone;    // 用户的手机号
    private String password;    //用户的密码，密文加密
    private String role; // 用户的角色
    private String avatar;  //用户性别
    private String region = "default";// 用户管理的区域

    public User() {
        // 在这里，你可以进一步确认'data/avatar'目录是否存在，如果不存在则创建它
        ensureDirectoryExists();
    }

    private void ensureDirectoryExists() {
        String directoryPath = "data/avatar"; // 目录路径
        java.nio.file.Path path = java.nio.file.Paths.get(directoryPath);
        if (java.nio.file.Files.notExists(path)) {
            try {
                java.nio.file.Files.createDirectories(path);
            } catch (java.io.IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
