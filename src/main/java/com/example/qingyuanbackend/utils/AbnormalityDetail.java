package com.example.qingyuanbackend.utils;

import com.example.qingyuanbackend.model.Abnormality;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AbnormalityDetail {
    private Integer id;
    private String regionName;
    private Integer x;
    private Integer y;
    private Integer label;
    private Date time;
    private Integer status;
    private Integer layers;
    // 故障提示器
    private Map<Integer, String> detailHinter = new HashMap<>();
    private Map<Integer, String> statusHinter = new HashMap<>();
    // 对故障标签的详细描述
    private String LabelDescription;
    // 维修建议
    private String FixSuggestion;
    // 员工对这个故障的状态
    private String FixStatus;

    // 维修建议映射器
    private Map<Integer, String> labelSuggestionHinter = new HashMap<>();

    public AbnormalityDetail() {
        // 初始化故障提示器
        detailHinter.put(0, "正常");
        detailHinter.put(1, "单侧轨道异常，轨道高度不一致");
        detailHinter.put(2, "轨道接缝出现较大落差，小车发生颠簸");
        detailHinter.put(3, "轨道不平顺,可能由于长时间运行积累灰尘杂物");

        // 初始化状态提示器
        statusHinter.put(0, "正常");
        statusHinter.put(1, "轨道出现异常，请即使上报维护申请。");
        statusHinter.put(2, "轨道出现异常，已经上报维护申请，等待管理员审批。");
        statusHinter.put(3, "轨道出现异常，异常已经排除。");
        statusHinter.put(4, "轨道未发生异常，模型发生误报。");

        labelSuggestionHinter.put(0, "无需维修，系统正常。");
        labelSuggestionHinter.put(1, "请检查轨道高度差异，并进行调整。");
        labelSuggestionHinter.put(2, "检查轨道接缝处，需要平整处理。");
        labelSuggestionHinter.put(3, "清理轨道，确保无杂物堵塞。");
    }

    public void fillFromAbnormality(Abnormality abnormality) {
        this.id = abnormality.getId();
        this.regionName = abnormality.getRegionName();
        this.x = abnormality.getX();
        this.y = abnormality.getY();
        this.label = abnormality.getLabel();
        this.time = abnormality.getTime();
        this.status = abnormality.getStatus();
        this.layers = abnormality.getLayers();

        // 根据status和label的值，查找并设置FixStatus和LabelDescription
        this.FixStatus = statusHinter.getOrDefault(status, "未知状态");
        this.LabelDescription = detailHinter.getOrDefault(label, "未知描述");
        this.FixSuggestion = labelSuggestionHinter.getOrDefault(abnormality.getLabel(), "未知建议");
    }

    public String getLabelDescription() {
        return LabelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        LabelDescription = labelDescription;
    }

    public String getFixSuggestionde() {
        return FixSuggestion;
    }

    public void setFixSuggestionde(String fixSuggestion) {
        FixSuggestion = fixSuggestion;
    }

    public String getFixStatus() {
        return FixStatus;
    }

    public void setFixStatus(String fixStatus) {
        FixStatus = fixStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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
