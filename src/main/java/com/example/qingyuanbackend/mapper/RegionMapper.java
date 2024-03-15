package com.example.qingyuanbackend.mapper;

import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegionMapper {
    @Select("SELECT region_name FROM regions")
    List<String> selectAllRegions();
}
