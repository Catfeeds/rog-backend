package com.rograndec.feijiayun.chain.business.basic.warehouse.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class WarehouseCargoArea implements Serializable {
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

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * saas_warehouse_cargo_area
     */
    private static final long serialVersionUID = 1L;



    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 仓库ID
     * @return warehouse_id 仓库ID
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 仓库ID
     * @param warehouseId 仓库ID
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 库区ID
     * @return warehouse_area_id 库区ID
     */
    public Long getWarehouseAreaId() {
        return warehouseAreaId;
    }

    /**
     * 库区ID
     * @param warehouseAreaId 库区ID
     */
    public void setWarehouseAreaId(Long warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）
     * @return job_type 作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）
     */
    public Integer getJobType() {
        return jobType;
    }

    /**
     * 作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）
     * @param jobType 作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）
     */
    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

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
 
     * @return job_area 作业区域：作业区域：
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
 
     */
    public Integer getJobArea() {
        return jobArea;
    }

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
 
     * @param jobArea 作业区域：作业区域：
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
 
     */
    public void setJobArea(Integer jobArea) {
        this.jobArea = jobArea;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getShelfQuantity() {
        return shelfQuantity;
    }

    public void setShelfQuantity(Integer shelfQuantity) {
        this.shelfQuantity = shelfQuantity;
    }

    public Integer getLayerQuantity() {
        return layerQuantity;
    }

    public void setLayerQuantity(Integer layerQuantity) {
        this.layerQuantity = layerQuantity;
    }


    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", warehouseAreaId=").append(warehouseAreaId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", jobType=").append(jobType);
        sb.append(", jobArea=").append(jobArea);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", layerQuantity=").append(layerQuantity);
        sb.append(", shelfQuantity=").append(shelfQuantity);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }
}