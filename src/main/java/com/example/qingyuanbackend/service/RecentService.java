package com.example.qingyuanbackend.service;

import com.example.qingyuanbackend.mapper.AbnormalityMapper;
import com.example.qingyuanbackend.mapper.FormMapper;
import com.example.qingyuanbackend.mapper.UserMapper;
import com.example.qingyuanbackend.model.Abnormality;
import com.example.qingyuanbackend.model.Form;
import com.example.qingyuanbackend.model.User;
import jakarta.validation.constraints.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecentService{

    @Autowired
    private FormMapper formMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AbnormalityMapper abnormalityMapper;

    public List<String> getRecentActivities() {
        List<Form> forms = formMapper.findRecentForms();
        List<String> activities = new ArrayList<>();

        // 创建一个SimpleDateFormat实例，指定日期时间的格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

        for (Form form : forms) {
            User user = userMapper.findByUserid(form.getUserid().intValue());
            Abnormality abnormality = abnormalityMapper.findAbnormalityById(form.getAbnormalid());

            String statusMessage = switch (abnormality.getStatus()) {
                case 2 -> "上报了";
                case 3, 4 -> "排除了";
                default -> "";
            };

            // 使用dateFormat格式化日期
            String formattedDate = dateFormat.format(form.getDate());

            String activity = String.format("%s %s %s %s区域的%s层的故障",
                    formattedDate, user.getUsername(), statusMessage,
                    abnormality.getRegionName(), abnormality.getLayers());

            activities.add(activity);
        }

        return activities;
    }
}
