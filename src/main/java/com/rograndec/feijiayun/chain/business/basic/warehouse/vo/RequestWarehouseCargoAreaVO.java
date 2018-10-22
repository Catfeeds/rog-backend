package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ST on 2017/9/1.
 */
public class RequestWarehouseCargoAreaVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    @ApiModelProperty(value = "0：用户自定义部门；1-系统默认部门")
    private Integer sysType;


    /**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;



    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）
     */
    @ApiModelProperty(value = "作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）")
    private Integer jobType;

    /**
     * 作业区域：作业区域：
     当job_type=0（存储作业区）时，job_area=0，含义为“合格品区”；
     job_area=1，含义为“待处理区”；
     job_area=2，含义为“不合格品区”；

     当job_type=1（辅助作业区）时，job_area=0，含义为“收货区”；
     job_area=1，含义为“验收区”；
     job_area=2，含义为“退货区”；
     job_area=3，含义为“复核区”；
     job_area=4，含义为“零货拣选区区”；
     job_area=5，含义为“拼箱发货区”；
     job_area=6，含义为“包装物料发货区”；

     当job_type=2（存储设备）时，job_area=0，含义为“保温设备”；
     job_area=1，含义为“冷藏设备”；
     job_area=2，含义为“冷冻设备”；

     */
    @ApiModelProperty(value = "作业区域：作业区域：\n" +
            " 当job_type=0（存储作业区）时，job_area=0，含义为“合格品区”；\n" +
            " job_area=1，含义为“待处理区”；\n" +
            " job_area=2，含义为“不合格品区”；\n" +
            " \n" +
            " 当job_type=1（辅助作业区）时，job_area=0，含义为“收货区”；\n" +
            " job_area=1，含义为“验收区”；\n" +
            " job_area=2，含义为“退货区”；\n" +
            " job_area=3，含义为“复核区”；\n" +
            " job_area=4，含义为“零货拣选区区”；\n" +
            " job_area=5，含义为“拼箱发货区”；\n" +
            " job_area=6，含义为“包装物料发货区”；" +
            "当job_type=2（存储设备）时，job_area=0，含义为“保温设备”；\n" +
            "     job_area=1，含义为“冷藏设备”；\n" +
            "     job_area=2，含义为“冷冻设备”；")
    private Integer jobArea;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    @ApiModelProperty(value = "层数")
    private Integer layerQuantity;

    @ApiModelProperty(value = "货位数量")
    private Integer shelfQuantity;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }


    public Long getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(Long warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
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

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Integer getJobArea() {
        return jobArea;
    }

    public void setJobArea(Integer jobArea) {
        this.jobArea = jobArea;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLayerQuantity() {
        return layerQuantity;
    }

    public void setLayerQuantity(Integer layerQuantity) {
        this.layerQuantity = layerQuantity;
    }

    public Integer getShelfQuantity() {
        return shelfQuantity;
    }

    public void setShelfQuantity(Integer shelfQuantity) {
        this.shelfQuantity = shelfQuantity;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }
}