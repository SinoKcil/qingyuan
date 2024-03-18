package com.example.qingyuanbackend.service;

import com.example.qingyuanbackend.model.Form;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<Form> uploadFiles(MultipartFile[] files, String date, Integer abnormalid, Integer userid);
}
