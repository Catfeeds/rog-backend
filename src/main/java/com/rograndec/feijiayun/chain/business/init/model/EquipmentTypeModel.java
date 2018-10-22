package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备类型初始化数据模型
 */
public class EquipmentTypeModel {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级类型编码
     */
    private String parentTypeCode;

    /**
     * 上级类型名称
     */
    private String parentTypeName;

    public EquipmentTypeModel(String code, String name, String parentTypeCode, String parentTypeName) {
        this.code = code;
        this.name = name;
        this.parentTypeCode = parentTypeCode;
        this.parentTypeName = parentTypeName;
    }

    public static List<EquipmentTypeModel> build(){
        List<EquipmentTypeModel> eList = new ArrayList<EquipmentTypeModel>();
        eList.add(new EquipmentTypeModel("100", "库房设备", "", ""));
        eList.add(new EquipmentTypeModel("101", "隔离设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("102", "避光设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("103", "通风设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("104", "防潮设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("105", "防虫设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("106", "防鼠设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("107", "温湿度调控设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("108", "温湿度监测设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("109", "照明设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("110", "零货拣选设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("111", "拼箱发货设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("112", "复核设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("113", "特殊管理药品存储设施", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("114", "中药样品室(柜)", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("115", "冷库温度监测设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("116", "冷库制冷设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("117", "特殊低温存储设备", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("118", "冷库温湿度检测系统", "100", "库房设备"));
        eList.add(new EquipmentTypeModel("200", "计量设备", "", ""));
        eList.add(new EquipmentTypeModel("201", "计量器具", "200", "计量设备"));
        eList.add(new EquipmentTypeModel("300", "运输设备", "", ""));
        eList.add(new EquipmentTypeModel("301", "冷藏车", "300", "运输设备"));
        eList.add(new EquipmentTypeModel("302", "车载冷藏箱", "300", "运输设备"));
        eList.add(new EquipmentTypeModel("303", "车载保温箱", "300", "运输设备"));
        eList.add(new EquipmentTypeModel("304", "储运温湿度监控系统", "300", "运输设备"));
        return eList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentTypeCode() {
        return parentTypeCode;
    }

    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode;
    }

    public String getParentTypeName() {
        return parentTypeName;
    }

    public void setParentTypeName(String parentTypeName) {
        this.parentTypeName = parentTypeName;
    }
}