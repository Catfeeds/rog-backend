package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.common.constant.*;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShelfMoveExcelVO implements Serializable{
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 组织机构编码
     */
    @ApiModelProperty(value = "组织机构编码")
    private String enterpriseCode;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称")
    private String enterpriseName;

    /**
     * 单据（货位移动）日期
     */
    @ApiModelProperty(value = "单据（货位移动）日期")
    private String moveDate;

    /**
     * 单据（货位移动）编码
     */
    @ApiModelProperty(value = "单据（货位移动）编码//移动单号")
    private String code;

    /**
     * 移动人员名称
     */
    @ApiModelProperty(value = "移动人员名称")
    private String moveManName;

    /**
     * 接收人员名称
     */
    @ApiModelProperty(value = "接收人员名称")
    private String receiverName;
    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    @ApiModelProperty(value="条形码")
    private String barcode;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;


    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;


    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String goodsPlace;

    @ApiModelProperty(value="批准文号")
    private String approvalNumber;

    @ApiModelProperty(value="批号")
    private String lotNum;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private String productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private String validDate;


    /**
     * 源货位名称
     */
    @ApiModelProperty(value = "源货位名称")
    private String srcShelfName;

    /**
     * 源货位质量状况
     */
    @ApiModelProperty(value = "源货位质量状况")
    private String srcShelfStatusDesc;


    /**
     * 目标货位名称
     */
    @ApiModelProperty(value = "目标货位名称")
    private String targetShelfName;

    /**
     * 目标货位质量状况
     */
    @ApiModelProperty(value = "目标货位质量状况")
    private String targetShelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 0-禁用 1-启用")
    private String status;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private String businessVariety;


    @ApiModelProperty(value="商品分类名称")
    private String categoryName;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value="商品属性（0-成药；1-中药材；2-中药饮片）")
    private String goodsAttribute;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口（0-国产；1-进口）")
    private String domesticImport;


    /**
     * 经营范围名称
     */
    @ApiModelProperty(value=" 经营范围名称")
    private String managementScopeName;

    /**
     *
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private String specialDrug;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private String inChargeDrug;

    /**
     * 限购数量
     */
    @ApiModelProperty(value="限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value=" 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private String storageTemp;


    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value="贮藏条件名称")
    private String storageConditionName;

    /**
     * 保质期
     */
    @ApiModelProperty(value="保质期")
    private String safeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getMoveDate() {
        return moveDate;
    }

    public void setMoveDate(String moveDate) {
        this.moveDate = moveDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMoveManName() {
        return moveManName;
    }

    public void setMoveManName(String moveManName) {
        this.moveManName = moveManName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getSrcShelfName() {
        return srcShelfName;
    }

    public void setSrcShelfName(String srcShelfName) {
        this.srcShelfName = srcShelfName;
    }

    public String getSrcShelfStatusDesc() {
        return srcShelfStatusDesc;
    }

    public void setSrcShelfStatusDesc(String srcShelfStatusDesc) {
        this.srcShelfStatusDesc = srcShelfStatusDesc;
    }

    public String getTargetShelfName() {
        return targetShelfName;
    }

    public void setTargetShelfName(String targetShelfName) {
        this.targetShelfName = targetShelfName;
    }

    public String getTargetShelfStatusDesc() {
        return targetShelfStatusDesc;
    }

    public void setTargetShelfStatusDesc(String targetShelfStatusDesc) {
        this.targetShelfStatusDesc = targetShelfStatusDesc;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(String businessVariety) {
        this.businessVariety = businessVariety;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(String goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public String getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(String domesticImport) {
        this.domesticImport = domesticImport;
    }

    public String getManagementScopeName() {
        return managementScopeName;
    }

    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName;
    }

    public String getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(String specialDrug) {
        this.specialDrug = specialDrug;
    }

    public String getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(String inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public String getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(String storageTemp) {
        this.storageTemp = storageTemp;
    }

    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public String getSafeTime() {
        return safeTime;
    }

    public void setSafeTime(String safeTime) {
        this.safeTime = safeTime;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    @Override
    public String toString() {
        return "ShelfMoveExcelVO{" +
                "id=" + id +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", moveDate='" + moveDate + '\'' +
                ", code='" + code + '\'' +
                ", moveManName='" + moveManName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", lotNum='" + lotNum + '\'' +
                ", productDate='" + productDate + '\'' +
                ", validDate='" + validDate + '\'' +
                ", srcShelfName='" + srcShelfName + '\'' +
                ", srcShelfStatusDesc='" + srcShelfStatusDesc + '\'' +
                ", targetShelfName='" + targetShelfName + '\'' +
                ", targetShelfStatusDesc='" + targetShelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", businessVariety='" + businessVariety + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", goodsAttribute='" + goodsAttribute + '\'' +
                ", domesticImport='" + domesticImport + '\'' +
                ", managementScopeName='" + managementScopeName + '\'' +
                ", specialDrug='" + specialDrug + '\'' +
                ", inChargeDrug='" + inChargeDrug + '\'' +
                ", limitQuantity=" + limitQuantity +
                ", storageTemp='" + storageTemp + '\'' +
                ", storageConditionName='" + storageConditionName + '\'' +
                ", safeTime='" + safeTime + '\'' +
                '}';
    }

    public static ShelfMoveExcelVO convertTOExcelVO(ShelfMoveVO move) {
        ShelfMoveExcelVO vo = new ShelfMoveExcelVO();
        vo.setId(move.getId());
        vo.setEnterpriseCode(move.getEnterpriseCode());
        vo.setEnterpriseName(move.getEnterpriseName());
        vo.setMoveDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(move.getMoveDate()));
        vo.setCode(move.getCode());
        vo.setMoveManName(move.getMoveManName());
        vo.setReceiverName(move.getReceiverName());
        vo.setGoodsCode(move.getGoodsCode());
        vo.setBarcode(move.getBarcode());
        vo.setGoodsName(move.getGoodsName());
        vo.setGoodsGenericName(move.getGoodsGenericName());
        vo.setDosageName(move.getDosageName());
        vo.setGoodsSpecification(move.getGoodsSpecification());
        vo.setUnitName(move.getUnitName());
        vo.setManufacturer(move.getManufacturer());
        vo.setGoodsPlace(move.getGoodsPlace());
        vo.setApprovalNumber(move.getApprovalNumber());
        vo.setLotNum(move.getLotNum());
        vo.setProductDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(move.getProductDate()));
        vo.setValidDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(move.getValidDate()));
        vo.setSrcShelfName(move.getSrcShelfName());
        vo.setSrcShelfStatusDesc(move.getSrcShelfStatusDesc());
        vo.setTargetShelfName(move.getTargetShelfName());
        vo.setTargetShelfStatusDesc(move.getTargetShelfStatusDesc());
        vo.setQuantity(move.getQuantity());
        vo.setRemark(move.getRemark());
        vo.setStatus(move.getStatus() == 0 ? "禁用" : "启用");
        //0-药品；1-医疗器械；2-食品；3-化妆品；4-其它
        vo.setBusinessVariety(BusinessVariety.getName(move.getBusinessVariety()));
        vo.setCategoryName(move.getCategoryName());
        //0-成药；1-中药材；2-中药饮片
        vo.setGoodsAttribute(move.getGoodsAttributeDetail());
        vo.setDomesticImport(DomesticImport.getName(move.getDomesticImport()));
        vo.setManagementScopeName(move.getManagementScopeName());
        vo.setSpecialDrug(move.getSpecialDrugDetail());
        vo.setInChargeDrug(move.getInChargeDrugDetail());
        vo.setLimitQuantity(move.getLimitQuantity());
        vo.setStorageTemp(StorageTemp.getName(move.getStorageTemp()));
        vo.setStorageConditionName(move.getStorageConditionName());
        vo.setSafeTime(move.getQualityPeriod() + (move.getQualityPeriodUnit() == 0 ? "日" : (move.getQualityPeriodUnit() == 1 ? "月" : (move.getQualityPeriodUnit() == 2 ? "年" : "")))  + "");
        return vo;
    }
}
