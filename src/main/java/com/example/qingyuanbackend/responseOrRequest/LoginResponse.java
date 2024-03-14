package com.example.qingyuanbackend.responseOrRequest;

import java.util.List;

//登录相应DTO
public class LoginResponse {
    private boolean success;
    private String message;
    private String username;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
    private long expires;
    private String Backend; // 演示用字段

    private String avatar;// 头像

    // 构造函数、Getters 和 Setters

    public LoginResponse(boolean success, String message, String username, List<String> roles, String accessToken, String refreshToken, long expires, String avatar) {
        this.success = success;
        this.message = message;
        this.username = username;
        this.roles = roles;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expires = expires;
        this.avatar = avatar;
        this.Backend = "这是擎源后台返回的数据，便于排查mock";
    }

    public boolean isSuccess() {
        return success;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getBackend() {
        return Backend;
    }

    public void setBackend(String backend) {
        Backend = backend;
    }
}