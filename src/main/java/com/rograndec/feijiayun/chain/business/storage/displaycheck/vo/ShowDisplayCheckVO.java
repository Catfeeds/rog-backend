package com.rograndec.feijiayun.chain.business.storage.displaycheck.vo;

import com.rograndec.feijiayun.chain.business.storage.maintance.vo.SelectMaintanceGoodsVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_goods_display_check
 * 
 * 
 * @author Asze
 * 
 * 2017-09-26
 */
public class ShowDisplayCheckVO implements Serializable {

    /**
     * 检查日期
     */
    @ApiModelProperty(value = "检查日期")
    private Date checkDate;

    /**
     * 检查单号
     */
    @ApiModelProperty(value = "检查单号")
    private String code;

    /**
     * 检查人员ID
     */
    @ApiModelProperty(value = "检查人员ID")
    private Long checkerId;

    /**
     * 检查人员编码
     */
    @ApiModelProperty(value = "检查人员编码")
    private String checkerCode;

    /**
     * 检查人员名称
     */
    @ApiModelProperty(value = "检查人员名称")
    private String checkerName;

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
     * 养护类型名
     */
    @ApiModelProperty(value = "养护类型名")
    private String maintanceTypeName;

    /**
     * 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
     */
    @ApiModelProperty(value = "药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）")
    private Integer goodsType;

    /**
     * 药品类型名
     */
    @ApiModelProperty(value = "药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）")
    private String goodsTypeName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 货品明细列表
     */
    private List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOList;


    /**
     * saas_goods_display_check
     */
    private static final long serialVersionUID = 1L;

    /**
     * 检查日期
     * @return check_date 检查日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 检查日期
     * @param checkDate 检查日期
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 检查单号
     * @return code 检查单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 检查单号
     * @param code 检查单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 检查人员ID
     * @return checker_id 检查人员ID
     */
    public Long getCheckerId() {
        return checkerId;
    }

    /**
     * 检查人员ID
     * @param checkerId 检查人员ID
     */
    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * 检查人员编码
     * @return checker_code 检查人员编码
     */
    public String getCheckerCode() {
        return checkerCode;
    }

    /**
     * 检查人员编码
     * @param checkerCode 检查人员编码
     */
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode == null ? null : checkerCode.trim();
    }

    /**
     * 检查人员名称
     * @return checker_name 检查人员名称
     */
    public String getCheckerName() {
        return checkerName;
    }

    /**
     * 检查人员名称
     * @param checkerName 检查人员名称
     */
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName == null ? null : checkerName.trim();
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

    public List<SelectMaintanceGoodsVO> getSelectMaintanceGoodsVOList() {
        return selectMaintanceGoodsVOList;
    }

    public void setSelectMaintanceGoodsVOList(List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOList) {
        this.selectMaintanceGoodsVOList = selectMaintanceGoodsVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShowDisplayCheckVO that = (ShowDisplayCheckVO) o;

        if (checkDate != null ? !checkDate.equals(that.checkDate) : that.checkDate != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (checkerId != null ? !checkerId.equals(that.checkerId) : that.checkerId != null) return false;
        if (checkerCode != null ? !checkerCode.equals(that.checkerCode) : that.checkerCode != null) return false;
        if (checkerName != null ? !checkerName.equals(that.checkerName) : that.checkerName != null) return false;
        if (warehouseAreaId != null ? !warehouseAreaId.equals(that.warehouseAreaId) : that.warehouseAreaId != null)
            return false;
        if (warehouseAreaName != null ? !warehouseAreaName.equals(that.warehouseAreaName) : that.warehouseAreaName != null)
            return false;
        if (maintanceType != null ? !maintanceType.equals(that.maintanceType) : that.maintanceType != null)
            return false;
        if (maintanceTypeName != null ? !maintanceTypeName.equals(that.maintanceTypeName) : that.maintanceTypeName != null)
            return false;
        if (goodsType != null ? !goodsType.equals(that.goodsType) : that.goodsType != null) return false;
        if (goodsTypeName != null ? !goodsTypeName.equals(that.goodsTypeName) : that.goodsTypeName != null)
            return false;
        return remark != null ? remark.equals(that.remark) : that.remark == null;
    }

    @Override
    public int hashCode() {
        int result = checkDate != null ? checkDate.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (checkerId != null ? checkerId.hashCode() : 0);
        result = 31 * result + (checkerCode != null ? checkerCode.hashCode() : 0);
        result = 31 * result + (checkerName != null ? checkerName.hashCode() : 0);
        result = 31 * result + (warehouseAreaId != null ? warehouseAreaId.hashCode() : 0);
        result = 31 * result + (warehouseAreaName != null ? warehouseAreaName.hashCode() : 0);
        result = 31 * result + (maintanceType != null ? maintanceType.hashCode() : 0);
        result = 31 * result + (maintanceTypeName != null ? maintanceTypeName.hashCode() : 0);
        result = 31 * result + (goodsType != null ? goodsType.hashCode() : 0);
        result = 31 * result + (goodsTypeName != null ? goodsTypeName.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShowDisplayCheckVO{" +
                "checkDate=" + checkDate +
                ", code='" + code + '\'' +
                ", checkerId=" + checkerId +
                ", checkerCode='" + checkerCode + '\'' +
                ", checkerName='" + checkerName + '\'' +
                ", warehouseAreaId=" + warehouseAreaId +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", maintanceType=" + maintanceType +
                ", maintanceTypeName='" + maintanceTypeName + '\'' +
                ", goodsType=" + goodsType +
                ", goodsTypeName='" + goodsTypeName + '\'' +
                ", remark='" + remark + '\'' +
                ", selectMaintanceGoodsVOList=" + selectMaintanceGoodsVOList +
                '}';
    }
}