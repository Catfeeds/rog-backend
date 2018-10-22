package com.rograndec.feijiayun.chain.business.storage.maintance.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * saas_goods_maintance
 * 
 * 
 * @author Asze
 * 
 * 2017-09-26
 */
public class GoodsMaintanceVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 养护日期
     */
    @ApiModelProperty(value = "养护日期")
    private Date maintanceDate;

    /**
     * 养护单号
     */
    @ApiModelProperty(value = "养护单号")
    private String code;

    /**
     * 养护人员ID
     */
    @ApiModelProperty(value = "养护人员ID")
    private Long maintanceManId;

    /**
     * 养护人员编码
     */
    @ApiModelProperty(value = "养护人员编码")
    private String maintanceManCode;

    /**
     * 养护人员名称
     */
    @ApiModelProperty(value = "养护人员名称")
    private String maintanceManName;

    /**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;

    /**
     * 库区名称
     */
    @ApiModelProperty(value = "库区名称")
    private String warehouseAreaName;

    /**
     * 养护类型（0-重点养护；1-常规养护）
     */
    @ApiModelProperty(value = "养护类型（0-重点养护；1-常规养护）")
    private Integer maintanceType;

    /**
     * 养护类型名称
     */
    @ApiModelProperty(value = "养护类型名称")
    private String maintanceTypeName;

    /**
     * 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     */
    @ApiModelProperty(value = "药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）")
    private Integer goodsType;

    /**
     * 药品类型名称
     */
    @ApiModelProperty(value = "药品类型名称")
    private String goodsTypeName;

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

    @ApiModelProperty(value = "gsp标识")
    private Boolean gspFlag;

    /**
     * saas_goods_maintance
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
     * 养护日期
     * @return maintance_date 养护日期
     */
    public Date getMaintanceDate() {
        return maintanceDate;
    }

    /**
     * 养护日期
     * @param maintanceDate 养护日期
     */
    public void setMaintanceDate(Date maintanceDate) {
        this.maintanceDate = maintanceDate;
    }

    /**
     * 养护单号
     * @return code 养护单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 养护单号
     * @param code 养护单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 养护人员ID
     * @return maintance_man_id 养护人员ID
     */
    public Long getMaintanceManId() {
        return maintanceManId;
    }

    /**
     * 养护人员ID
     * @param maintanceManId 养护人员ID
     */
    public void setMaintanceManId(Long maintanceManId) {
        this.maintanceManId = maintanceManId;
    }

    /**
     * 养护人员编码
     * @return maintance_man_code 养护人员编码
     */
    public String getMaintanceManCode() {
        return maintanceManCode;
    }

    /**
     * 养护人员编码
     * @param maintanceManCode 养护人员编码
     */
    public void setMaintanceManCode(String maintanceManCode) {
        this.maintanceManCode = maintanceManCode == null ? null : maintanceManCode.trim();
    }

    /**
     * 养护人员名称
     * @return maintance_man_name 养护人员名称
     */
    public String getMaintanceManName() {
        return maintanceManName;
    }

    /**
     * 养护人员名称
     * @param maintanceManName 养护人员名称
     */
    public void setMaintanceManName(String maintanceManName) {
        this.maintanceManName = maintanceManName == null ? null : maintanceManName.trim();
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
     * 库区名称
     * @return warehouse_area_name 库区名称
     */
    public String getWarehouseAreaName() {
        return warehouseAreaName;
    }

    /**
     * 库区名称
     * @param warehouseAreaName 库区名称
     */
    public void setWarehouseAreaName(String warehouseAreaName) {
        this.warehouseAreaName = warehouseAreaName == null ? null : warehouseAreaName.trim();
    }

    /**
     * 养护类型（0-重点养护；1-常规养护）
     * @return maintance_type 养护类型（0-重点养护；1-常规养护）
     */
    public Integer getMaintanceType() {
        return maintanceType;
    }

    /**
     * 养护类型（0-重点养护；1-常规养护）
     * @param maintanceType 养护类型（0-重点养护；1-常规养护）
     */
    public void setMaintanceType(Integer maintanceType) {
        this.maintanceType = maintanceType;
    }

    /**
     * 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     * @return goods_type 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     */
    public Integer getGoodsType() {
        return goodsType;
    }

    /**
     * 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     * @param goodsType 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     */
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
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

    public String getMaintanceTypeName() {
        return maintanceTypeName;
    }

    public void setMaintanceTypeName(String maintanceTypeName) {
        this.maintanceTypeName = maintanceTypeName;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public Boolean getGspFlag() {
        return gspFlag;
    }

    public void setGspFlag(Boolean gspFlag) {
        this.gspFlag = gspFlag;
    }
    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "GoodsMaintanceVO{" +
                "id=" + id +
                ", maintanceDate=" + maintanceDate +
                ", code='" + code + '\'' +
                ", maintanceManId=" + maintanceManId +
                ", maintanceManCode='" + maintanceManCode + '\'' +
                ", maintanceManName='" + maintanceManName + '\'' +
                ", warehouseAreaId=" + warehouseAreaId +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", maintanceType=" + maintanceType +
                ", maintanceTypeName=" + maintanceTypeName +
                ", goodsType=" + goodsType +
                ", goodsTypeName=" + goodsTypeName +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", gspFlag=" + gspFlag +
                '}';
    }
}