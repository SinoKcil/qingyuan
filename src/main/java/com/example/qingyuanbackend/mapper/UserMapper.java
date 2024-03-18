package com.example.qingyuanbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.qingyuanbackend.model.User;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

//@Mapper
//public interface UserMapper extends BaseMapper<User> {
//    @Select("SELECT * FROM user WHERE username = #{username}")
//    User findByUsername(String username);
//    @Insert("INSERT INTO user (username, phone, password, role, avatar, region) VALUES (#{username}, #{phone}, #{password}, #{role}, #{avatar}, #{region})")
//    int insertUser(User user);
//}

@Mapper
public interface UserMapper {

    // 根据用户名查询用户
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user WHERE id = #{userid}")
    User findByUserid(@Param("userid") int userid);

    @Select("SELECT * FROM user WHERE role != 'admin'")
    List<User> selectAllUsers(RowBounds rowBounds);

    // 插入用户，并返回影响的行数。这里假设您的用户表名为 user
    @Insert("INSERT INTO user (username, phone, password, role, avatar, region) " +
            "VALUES (#{username}, #{phone}, #{password}, #{role}, #{avatar}, #{region})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    // 更新用户信息
    @Update("UPDATE user SET phone=#{phone}, password=#{password}, role=#{role}, avatar=#{avatar}, region=#{region} " +
            "WHERE username = #{username}")
    int updateUser(User user);

    // 删除用户
    @Delete("DELETE FROM user WHERE username = #{username}")
    int deleteUser(@Param("username") String username);
}
