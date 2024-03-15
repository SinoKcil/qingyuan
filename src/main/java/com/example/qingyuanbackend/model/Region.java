package com.example.qingyuanbackend.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

// Region类 由Python-flask维护，由springboot查询
@TableName("regions")
public class Region {
    public String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Region(String region) {
        this.region = region;
    }
}
