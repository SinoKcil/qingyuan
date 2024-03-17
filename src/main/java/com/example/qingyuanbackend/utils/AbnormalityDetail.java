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
        detailHinter.put(0, "擎源大数据分析模型通过对该时间段前后的数据进行分析，发现该处轨道没有异常");
        detailHinter.put(1, "擎源大数据分析模型通过对该时间段前后的数据进行分析，发现了该处单侧轨道异常。该数据已经自动上报至穿梭车调度系统。出现该异常的主要因素可能是穿梭车长时间搬运重量不均匀的货物造成轨道偏载而发生变形。");
        detailHinter.put(2, "擎源大数据分析模型通过对该时间段前后的数据进行分析，发现了该轨道接缝处出现较大落差，小车发生比较明显的颠簸。该数据已经自动上报至穿梭车调度系统。出现该异常的主要因素可能是轨道在安装初期矫正不够完全，或者长时间使用导致其中一段轨道受压较大而发生下沉。");
        detailHinter.put(3, "擎源大数据分析模型通过对该时间段前后的数据进行分析，发现了该处出现了轨道不平顺的现象,小车长时间发生颠簸。该数据已经自动上报至穿梭车调度系统。出现该异常的主要因素能是仓库在长时间使用中积累了大量灰尘、杂物，使得轨道表面不平顺。");

        // 初始化状态提示器
        statusHinter.put(0, "正常");
        statusHinter.put(1, "轨道出现异常，请及时上报维护申请。");
        statusHinter.put(2, "轨道出现异常，已经上报维护申请，可以等待管理员查阅。");
        statusHinter.put(3, "轨道出现异常，异常已经排除。");
        statusHinter.put(4, "轨道未发生异常，模型发生误报。");

        labelSuggestionHinter.put(0, "该处无需进行维修，但为了轨道穿梭车的高效运行，确保立体仓储安全运转，也请定期进行巡查维护。");
        labelSuggestionHinter.put(1, "该处出现左右轨道高度差异，请检查轨道高度差异，并对该处进行合理调整。");
        labelSuggestionHinter.put(2, "该处出现轨道高低落差，检查轨道接缝处是否安装平整，确保轨道高度平整。");
        labelSuggestionHinter.put(3, "该处出现长段轨道不平顺，请对此处轨道进行检查，确保没有灰尘、杂物堆积，如不及时检查，异常状况可能严重化。");
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

    public String getFixSuggestion() {
        return FixSuggestion;
    }

    public void setFixSuggestion(String fixSuggestion) {
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
