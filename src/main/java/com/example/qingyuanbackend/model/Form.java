package com.example.qingyuanbackend.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("form")
public class Form {
    private Integer id;
    private Integer userid;
    private Integer abnormalid;
    private String filepath;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAbnormalid() {
        return abnormalid;
    }

    public void setAbnormalid(Integer abnormalid) {
        this.abnormalid = abnormalid;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
