package com.example.qingyuanbackend.service;

import com.example.qingyuanbackend.mapper.AbnormalityMapper;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.qingyuanbackend.model.Abnormality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.qingyuanbackend.utils.AbnormalityForm;
import com.example.qingyuanbackend.utils.AbnormalityDetail;
@Service
public class AbnormalityService {
    @Autowired
    private AbnormalityMapper abnormalityMapper;

    public AbnormalityForm transformAbnormalitiesToForm(List<Abnormality> abnormalities, String regionName, int layer) {
        // 获取最小和最大x, y值
        int xMin = abnormalities.stream().min(Comparator.comparingInt(Abnormality::getX)).orElseThrow().getX();
        int yMin = abnormalities.stream().min(Comparator.comparingInt(Abnormality::getY)).orElseThrow().getY();
        int xMax = abnormalities.stream().max(Comparator.comparingInt(Abnormality::getX)).orElseThrow().getX();
        int yMax = abnormalities.stream().max(Comparator.comparingInt(Abnormality::getY)).orElseThrow().getY();
        int rows = yMax - yMin + 1;
        int cols = xMax - xMin + 1;

        // 初始化状态矩阵和ID矩阵
        List<List<Integer>> statusMatrix = new ArrayList<>(rows);
        List<List<String>> idMatrix = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<Integer> statusRow = new ArrayList<>(Collections.nCopies(cols, -1)); // 默认状态为-1
            List<String> idRow = new ArrayList<>(Collections.nCopies(cols, "-1")); // 默认ID为空
            statusMatrix.add(statusRow);
            idMatrix.add(idRow);
        }

        // 填充矩阵
        for (Abnormality abnormality : abnormalities) {
            int xIndex = abnormality.getX() - xMin;
            int yIndex = abnormality.getY() - yMin;
            statusMatrix.get(yIndex).set(xIndex, abnormality.getLabel()); // 设置状态
            idMatrix.get(yIndex).set(xIndex, String.format("(%d,%d)", abnormality.getX(), abnormality.getY())); // 设置ID
        }
        int totalLayers = abnormalityMapper.findMaxLayerByRegion(regionName);
        return new AbnormalityForm(idMatrix, statusMatrix, xMax, yMax, layer, totalLayers, regionName);
    }


    // 请求的第一入口业务逻辑，返回某一区域的一层的整个地图
    public List<Abnormality> getAbnormalitiesByRegionAndLayer(String region, Integer layer) {
        List<Abnormality> abnormalities = abnormalityMapper.findAbnormalitiesByRegionAndLayer(region, layer);
        return abnormalities;
    }

    public AbnormalityDetail getAbnormalityDetail(String region, int layer, int row, int col, int label) {
        List<Abnormality> abnormalities = abnormalityMapper.findAbnormalitiesByRegionAndLayer(region, layer);
        AbnormalityForm form = transformAbnormalitiesToForm(abnormalities, region, layer);

        // row和col直接映射到矩阵中的位置
        String id = form.getTableId().get(row).get(col);
        // 解析ID获取x, y坐标
        Pattern p = Pattern.compile("\\((\\d+),(\\d+)\\)");
        Matcher m = p.matcher(id);
        if (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            // 使用x, y, region, layer查询对应的异常详情
            Abnormality abnormality = abnormalityMapper.findAbnormalityByRegionLayerAndCoordinates(region, layer, x, y, label);
            System.out.println(abnormality.toString());
            // 构建AbnormalityDetail对象并填充数据
            AbnormalityDetail detail = new AbnormalityDetail();
            detail.fillFromAbnormality(abnormality);
            return detail;
        } else {
            throw new RuntimeException("无法解析坐标");
        }
    }
}
