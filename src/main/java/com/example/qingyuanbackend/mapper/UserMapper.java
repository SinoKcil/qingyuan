package com.example.qingyuanbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.qingyuanbackend.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);
}