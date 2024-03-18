package com.example.qingyuanbackend.mapper;

import com.baomidou.mybatisplus.annotation.TableId;
import com.example.qingyuanbackend.model.Region;
import com.example.qingyuanbackend.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegionMapper {
    @Select("SELECT * FROM regions")
    List<Region> selectAllRegions();

    @Select("SELECT leader FROM regions WHERE region_name = #{region_name}")
    int getLeaderByRegionName(@Param("region_name") String regionName);
}
