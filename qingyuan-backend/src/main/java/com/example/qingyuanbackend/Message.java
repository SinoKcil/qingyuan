package com.example.qingyuanbackend;
//封装错误信息
public enum Message {
    VALIDATION_ERROR("请输入正确的验证码"),
    ACCOUNT_NOT_REGISTERED("账号尚未被注册"),
    LOGIN_SUCCESS("登录成功"),
    PASSWORD_ERROR("密码错误"),
    PASSWORD_TOO_SHORT("密码长度不能小于6位"),
    ACCOUNT_ALREADY_REGISTERED("账号已被注册"),
    REGISTRATION_SUCCESS("账号注册成功"),
    UPDATE_SUCCESS("修改成功"),
    DELETE_SUCCESS("删除成功"),
    SEARCH_INFO_REQUIRED("搜索信息不能为空"),
    FILE_UPLOAD_FAIL("文件上传失败"),
    FILE_UPLOAD_SUCCESS("文件上传成功");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
