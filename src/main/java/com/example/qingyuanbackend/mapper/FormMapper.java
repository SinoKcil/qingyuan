package com.example.qingyuanbackend.mapper;

import com.example.qingyuanbackend.model.Form;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FormMapper {
    @Insert("INSERT INTO form (userid, abnormalid, filepath, date) " +
            "VALUES (#{userid}, #{abnormalid}, #{filepath}, #{date})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertForm(Form form);

    @Select("SELECT * FROM form ORDER BY date DESC LIMIT 20")
    List<Form> findRecentForms();

}
