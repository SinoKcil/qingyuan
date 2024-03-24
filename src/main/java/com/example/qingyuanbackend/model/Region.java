package com.example.qingyuanbackend.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

// Region类 由Python-flask维护，由springboot查询
@TableName("regions")
public class Region implements Serializable {
    public String region_name;
    public String leader;//负责人

    public int id;// 仓库的id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getRegion() {
        return region_name;
    }

    public void setRegion(String region) {
        this.region_name = region;
    }

    public Region() {
        this.region_name = region_name;
    }
}
