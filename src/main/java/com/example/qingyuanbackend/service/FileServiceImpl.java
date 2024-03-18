package com.example.qingyuanbackend.service;

import com.example.qingyuanbackend.mapper.AbnormalityMapper;
import com.example.qingyuanbackend.mapper.FormMapper;
import com.example.qingyuanbackend.mapper.UserMapper;
import com.example.qingyuanbackend.model.Form;
import com.example.qingyuanbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AbnormalityMapper abnormalityMapper;

    @Autowired
    private FormMapper formMapper;
    // 存在目录下
    private static final String RELATIVE_PATH = "/public/files/";
    @Override
    public List<Form> uploadFiles(MultipartFile[] files, String dateString, Integer abnormalid, Integer userid) {
        List<Form> forms = new ArrayList<>();

        try {
            // 获取用户名
            User user = userMapper.findByUserid(userid);
            String username = user.getUsername();

            // 更新异常状态
            abnormalityMapper.updateStatus(abnormalid);

            // 确保上传目录存在
            String uploadDirPath = System.getProperty("user.dir") + RELATIVE_PATH;
            File uploadDir = new File(uploadDirPath);
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                throw new RuntimeException("Unable to create upload directory.");
            }

            // 文件上传
            for (MultipartFile file : files) {
                // 文件名处理
                String sanitizedDate = dateString.replace(":", "_").replace(" ", "_");
                String filename = username + "_" + sanitizedDate + "_" + file.getOriginalFilename();
                String filepath = uploadDirPath + filename;

                // 保存文件
                File targetFile = new File(filepath);// 拿到一个文件对象
                // 存进去！
                file.transferTo(targetFile);

                // 转换日期格式
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString.replace("T", " "));

                // 创建Form记录
                Form form = new Form();
                form.setUserid(userid);
                form.setAbnormalid(abnormalid);
                form.setFilepath(filepath);
                form.setDate(date);

                formMapper.insertForm(form);
                forms.add(form);
            }
        } catch (Exception e) {
            throw new RuntimeException("File upload error: " + e.getMessage());
        }

        return forms;
    }
}
