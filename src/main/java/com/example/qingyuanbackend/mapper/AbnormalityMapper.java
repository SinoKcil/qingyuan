package com.example.qingyuanbackend.mapper;

import com.example.qingyuanbackend.model.Abnormality;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface AbnormalityMapper {

    //根据行 列 对应区域名 对应层数 找到故障点
    @Select("SELECT * FROM abnormalities WHERE region_name = #{region} AND layers = #{layer} AND x = #{x} AND y = #{y} AND label = #{label}")
    Abnormality findAbnormalityByRegionLayerAndCoordinates(@Param("region") String region,
                                                           @Param("layer") Integer layer,
                                                           @Param("x") int x,
                                                           @Param("y") int y,
                                                           @Param("label") int label);

    // 根据区域名和层数来找一层的故障
    @Select("SELECT * FROM abnormalities WHERE region_name = #{region} AND layers = #{layer}")
    List<Abnormality> findAbnormalitiesByRegionAndLayer(String region, Integer layer);

    // 找到区域的最大值
    @Select("SELECT MAX(layers) FROM abnormalities WHERE region_name = #{region}")
    Integer findMaxLayerByRegion(String region);

    @Select("SELECT * FROM abnormalities WHERE id = #{id}")
    Abnormality findAbnormalityById(@Param("id") int id);


}
