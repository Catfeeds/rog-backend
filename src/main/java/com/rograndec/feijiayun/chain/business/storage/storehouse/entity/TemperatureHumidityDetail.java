package com.rograndec.feijiayun.chain.business.storage.storehouse.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_temperature_humidity_detail
 * 
 * 
 * @author rograndec
 * 
 * 2017-09-26
 */
public class TemperatureHumidityDetail implements Serializable {
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
     * 单据（温湿度记录单）ID
     */
    @ApiModelProperty(value = "单据（温湿度记录单）ID")
    private Long recordId;

    /**
     * 单据（温湿度记录单）类型
     */
    @ApiModelProperty(value = "单据（温湿度记录单）类型")
    private Integer orderType;

    /**
     * 单据（温湿度记录单）编码
     */
    @ApiModelProperty(value = "单据（温湿度记录单）编码")
    private String recordCode;

    /**
     * 单据（温湿度记录单）日期
     */
    @ApiModelProperty(value = "单据（温湿度记录单）日期")
    private Date recordDate;

    /**
     * 上午记录时间
     */
    @ApiModelProperty(value = "上午记录时间")
    private String amTime;

    /**
     * 上午相对温度
     */
    @ApiModelProperty(value = "上午相对温度")
    private BigDecimal amTempA;

    /**
     * 上午相对湿度
     */
    @ApiModelProperty(value = "上午相对湿度")
    private BigDecimal amHumidityA;

    /**
     * 上午调控措施
     */
    @ApiModelProperty(value = "上午调控措施")
    private String amMeasure;

    /**
     * 上午调控后相对温度
     */
    @ApiModelProperty(value = "上午调控后相对温度")
    private BigDecimal amTempB;

    /**
     * 下午调控后相对湿度
     */
    @ApiModelProperty(value = "下午调控后相对湿度")
    private BigDecimal amHumidityB;

    /**
     * 下午记录时间
     */
    @ApiModelProperty(value = "下午记录时间")
    private String pmTime;

    /**
     * 下午相对温度
     */
    @ApiModelProperty(value = "下午相对温度")
    private BigDecimal pmTempA;

    /**
     * 下午相对湿度
     */
    @ApiModelProperty(value = "下午相对湿度")
    private BigDecimal pmHumidityA;

    /**
     * 下午调控措施
     */
    @ApiModelProperty(value = "下午调控措施")
    private String pmMeasure;

    /**
     * 下午调控后相对温度
     */
    @ApiModelProperty(value = "下午调控后相对温度")
    private BigDecimal pmTempB;

    /**
     * 下午调控后相对湿度
     */
    @ApiModelProperty(value = "下午调控后相对湿度")
    private BigDecimal pmHumidityB;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_temperature_humidity_detail
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
     * 单据（温湿度记录单）ID
     * @return record_id 单据（温湿度记录单）ID
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * 单据（温湿度记录单）ID
     * @param recordId 单据（温湿度记录单）ID
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * 单据（温湿度记录单）类型
     * @return order_type 单据（温湿度记录单）类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据（温湿度记录单）类型
     * @param orderType 单据（温湿度记录单）类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据（温湿度记录单）编码
     * @return record_code 单据（温湿度记录单）编码
     */
    public String getRecordCode() {
        return recordCode;
    }

    /**
     * 单据（温湿度记录单）编码
     * @param recordCode 单据（温湿度记录单）编码
     */
    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode == null ? null : recordCode.trim();
    }

    /**
     * 单据（温湿度记录单）日期
     * @return record_date 单据（温湿度记录单）日期
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 单据（温湿度记录单）日期
     * @param recordDate 单据（温湿度记录单）日期
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * 上午记录时间
     * @return am_time 上午记录时间
     */
    public String getAmTime() {
        return amTime;
    }

    /**
     * 上午记录时间
     * @param amTime 上午记录时间
     */
    public void setAmTime(String amTime) {
        this.amTime = amTime;
    }

    /**
     * 上午相对温度
     * @return am_temp_a 上午相对温度
     */
    public BigDecimal getAmTempA() {
        return amTempA;
    }

    /**
     * 上午相对温度
     * @param amTempA 上午相对温度
     */
    public void setAmTempA(BigDecimal amTempA) {
        this.amTempA = amTempA;
    }

    /**
     * 上午相对湿度
     * @return am_humidity_a 上午相对湿度
     */
    public BigDecimal getAmHumidityA() {
        return amHumidityA;
    }

    /**
     * 上午相对湿度
     * @param amHumidityA 上午相对湿度
     */
    public void setAmHumidityA(BigDecimal amHumidityA) {
        this.amHumidityA = amHumidityA;
    }

    /**
     * 上午调控措施
     * @return am_measure 上午调控措施
     */
    public String getAmMeasure() {
        return amMeasure;
    }

    /**
     * 上午调控措施
     * @param amMeasure 上午调控措施
     */
    public void setAmMeasure(String amMeasure) {
        this.amMeasure = amMeasure == null ? null : amMeasure.trim();
    }

    /**
     * 上午调控后相对温度
     * @return am_temp_b 上午调控后相对温度
     */
    public BigDecimal getAmTempB() {
        return amTempB;
    }

    /**
     * 上午调控后相对温度
     * @param amTempB 上午调控后相对温度
     */
    public void setAmTempB(BigDecimal amTempB) {
        this.amTempB = amTempB;
    }

    /**
     * 下午调控后相对湿度
     * @return am_humidity_b 下午调控后相对湿度
     */
    public BigDecimal getAmHumidityB() {
        return amHumidityB;
    }

    /**
     * 下午调控后相对湿度
     * @param amHumidityB 下午调控后相对湿度
     */
    public void setAmHumidityB(BigDecimal amHumidityB) {
        this.amHumidityB = amHumidityB;
    }

    /**
     * 下午记录时间
     * @return pm_time 下午记录时间
     */
    public String getPmTime() {
        return pmTime;
    }

    /**
     * 下午记录时间
     * @param pmTime 下午记录时间
     */
    public void setPmTime(String pmTime) {
        this.pmTime = pmTime;
    }

    /**
     * 下午相对温度
     * @return pm_temp_a 下午相对温度
     */
    public BigDecimal getPmTempA() {
        return pmTempA;
    }

    /**
     * 下午相对温度
     * @param pmTempA 下午相对温度
     */
    public void setPmTempA(BigDecimal pmTempA) {
        this.pmTempA = pmTempA;
    }

    /**
     * 下午相对湿度
     * @return pm_humidity_a 下午相对湿度
     */
    public BigDecimal getPmHumidityA() {
        return pmHumidityA;
    }

    /**
     * 下午相对湿度
     * @param pmHumidityA 下午相对湿度
     */
    public void setPmHumidityA(BigDecimal pmHumidityA) {
        this.pmHumidityA = pmHumidityA;
    }

    /**
     * 下午调控措施
     * @return pm_measure 下午调控措施
     */
    public String getPmMeasure() {
        return pmMeasure;
    }

    /**
     * 下午调控措施
     * @param pmMeasure 下午调控措施
     */
    public void setPmMeasure(String pmMeasure) {
        this.pmMeasure = pmMeasure == null ? null : pmMeasure.trim();
    }

    /**
     * 下午调控后相对温度
     * @return pm_temp_b 下午调控后相对温度
     */
    public BigDecimal getPmTempB() {
        return pmTempB;
    }

    /**
     * 下午调控后相对温度
     * @param pmTempB 下午调控后相对温度
     */
    public void setPmTempB(BigDecimal pmTempB) {
        this.pmTempB = pmTempB;
    }

    /**
     * 下午调控后相对湿度
     * @return pm_humidity_b 下午调控后相对湿度
     */
    public BigDecimal getPmHumidityB() {
        return pmHumidityB;
    }

    /**
     * 下午调控后相对湿度
     * @param pmHumidityB 下午调控后相对湿度
     */
    public void setPmHumidityB(BigDecimal pmHumidityB) {
        this.pmHumidityB = pmHumidityB;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
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
        sb.append(", recordId=").append(recordId);
        sb.append(", orderType=").append(orderType);
        sb.append(", recordCode=").append(recordCode);
        sb.append(", recordDate=").append(recordDate);
        sb.append(", amTime=").append(amTime);
        sb.append(", amTempA=").append(amTempA);
        sb.append(", amHumidityA=").append(amHumidityA);
        sb.append(", amMeasure=").append(amMeasure);
        sb.append(", amTempB=").append(amTempB);
        sb.append(", amHumidityB=").append(amHumidityB);
        sb.append(", pmTime=").append(pmTime);
        sb.append(", pmTempA=").append(pmTempA);
        sb.append(", pmHumidityA=").append(pmHumidityA);
        sb.append(", pmMeasure=").append(pmMeasure);
        sb.append(", pmTempB=").append(pmTempB);
        sb.append(", pmHumidityB=").append(pmHumidityB);
        sb.append(", lineNum=").append(lineNum);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
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
}