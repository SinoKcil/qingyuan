package com.example.qingyuanbackend.utils;

import java.util.List;

public class AbnormalityForm {
    private List<List<String>> tableId;
    private List<List<Integer>> tableStatus;

    private int maxX;
    private int maxY;
    private int Layer;

    private int totalLayers;
    private String regionName;

    public AbnormalityForm(List<List<String>> tableId, List<List<Integer>> tableStatus, int maxX, int maxY, int layer, int totalLayers, String regionName) {
        this.tableId = tableId;
        this.tableStatus = tableStatus;
        this.maxX = maxX;
        this.maxY = maxY;
        Layer = layer;
        this.totalLayers = totalLayers;
        this.regionName = regionName;
    }

    public List<List<String>> getTableId() {
        return tableId;
    }

    public void setTableId(List<List<String>> tableId) {
        this.tableId = tableId;
    }

    public List<List<Integer>> getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(List<List<Integer>> tableStatus) {
        this.tableStatus = tableStatus;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getLayer() {
        return Layer;
    }

    public void setLayer(int layer) {
        Layer = layer;
    }

    public int getTotalLayers() {
        return totalLayers;
    }

    public void setTotalLayers(int totalLayers) {
        this.totalLayers = totalLayers;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}

