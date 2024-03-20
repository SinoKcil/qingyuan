package com.example.qingyuanbackend.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("abnormalities")
public class Abnormality {
    private Integer id;
    private String region_name;
    private Integer x;
    private Integer y;
    private Integer label;
    private Date time;
    private Integer status;
    private Integer layers;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return region_name;
    }

    public void setRegionName(String regionName) {
        this.region_name = regionName;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLayers() {
        return layers;
    }

    public void setLayers(Integer layers) {
        this.layers = layers;
    }
}
