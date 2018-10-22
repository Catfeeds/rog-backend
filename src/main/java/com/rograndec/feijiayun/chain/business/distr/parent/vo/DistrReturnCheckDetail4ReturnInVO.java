package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_return_check_detail
 * 
 * 
 * @author Asze
 * 
 * 2017-10-08
 */
public class DistrReturnCheckDetail4ReturnInVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long checkDtId;

    /**
     * 配后退回验收单ID
     */
    @ApiModelProperty(value = "配后退回验收单ID")
    private Long checkId;


    /**
     * 配进验收单号
     */
    @ApiModelProperty(value = "配进验收单号")
    private String checkCode;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    private Date checkDate;


    /**
     * 配后退回通知明细ID
     */
    @ApiModelProperty(value = "配后退回通知明细ID")
    private Long noticeDtlId;

    /**
     * 配后退回通知单ID
     */
    @ApiModelProperty(value = "配后退回通知单ID")
    private Long noticeId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量")
    private BigDecimal qualifiedQuantity;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal unqualifiedQuantity;


    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "购进退回验收明细单的货位批号集合,列表展示时无数据,详情页面才会有数据")
    private List<DistrReturnCheckLot4ReturnInVO> distrReturnCheckLot4ReturnInVOS = new ArrayList<>();


    public static List<Long> getDistrReturnCheckDetailIds(List<DistrReturnCheckDetail> distrReturnCheckDetails){

        List<Long> list = new ArrayList<>();

        distrReturnCheckDetails.forEach(drcd -> {
            list.add(drcd.getId());
        });

        return list;
    }

    public static List<DistrReturnCheckDetail4ReturnInVO> getDistrReturnCheckDetail4ReturnInVOs(List<DistrReturnCheckDetail> distrReturnCheckDetails){

        List<DistrReturnCheckDetail4ReturnInVO> list = new ArrayList<>();

        distrReturnCheckDetails.forEach(drcd -> {
            DistrReturnCheckDetail4ReturnInVO distrReturnCheckDetail4ReturnInVO = getDistrReturnCheckDetail4ReturnInVO(drcd);
            list.add(distrReturnCheckDetail4ReturnInVO);
        });

        return list;
    }

    public static DistrReturnCheckDetail4ReturnInVO getDistrReturnCheckDetail4ReturnInVO(DistrReturnCheckDetail distrReturnCheckDetail){

        DistrReturnCheckDetail4ReturnInVO distrReturnCheckDetail4ReturnInVO = new DistrReturnCheckDetail4ReturnInVO();

        /**
         * 主键ID
         */
        distrReturnCheckDetail4ReturnInVO.setCheckDtId(distrReturnCheckDetail.getId());

        /**
         * 配后退回验收单ID
         */
        distrReturnCheckDetail4ReturnInVO.setCheckId(distrReturnCheckDetail.getCheckId());


        /**
         * 配进验收单号
         */
        distrReturnCheckDetail4ReturnInVO.setCheckCode(distrReturnCheckDetail.getCheckCode());

        /**
         * 验收日期
         */
        distrReturnCheckDetail4ReturnInVO.setCheckDate(distrReturnCheckDetail.getCheckDate());


        /**
         * 配后退回通知明细ID
         */
        distrReturnCheckDetail4ReturnInVO.setNoticeDtlId(distrReturnCheckDetail.getNoticeDtlId());

        /**
         * 配后退回通知单ID
         */
        distrReturnCheckDetail4ReturnInVO.setNoticeId(distrReturnCheckDetail.getNoticeId());

        /**
         * 商品ID
         */
        distrReturnCheckDetail4ReturnInVO.setGoodsId(distrReturnCheckDetail.getGoodsId());

        /**
         * 商品编码
         */
        distrReturnCheckDetail4ReturnInVO.setGoodsCode(distrReturnCheckDetail.getGoodsCode());

        /**
         * 条形码
         */
        distrReturnCheckDetail4ReturnInVO.setBarcode(distrReturnCheckDetail.getBarcode());

        /**
         * 商品名称
         */
        distrReturnCheckDetail4ReturnInVO.setGoodsName(distrReturnCheckDetail.getGoodsName());

        /**
         * 商品通用名称
         */
        distrReturnCheckDetail4ReturnInVO.setGoodsGenericName(distrReturnCheckDetail.getGoodsGenericName());

        /**
         * 剂型ID
         */
        distrReturnCheckDetail4ReturnInVO.setDosageId(distrReturnCheckDetail.getDosageId());

        /**
         * 剂型名称
         */
        distrReturnCheckDetail4ReturnInVO.setDosageName(distrReturnCheckDetail.getDosageName());

        /**
         * 单位ID
         */
        distrReturnCheckDetail4ReturnInVO.setUnitId(distrReturnCheckDetail.getUnitId());

        /**
         * 单位名称
         */
        distrReturnCheckDetail4ReturnInVO.setUnitName(distrReturnCheckDetail.getUnitName());

        /**
         * 商品规格
         */
        distrReturnCheckDetail4ReturnInVO.setGoodsSpecification(distrReturnCheckDetail.getGoodsSpecification());

        /**
         * 生产厂商ID
         */
        distrReturnCheckDetail4ReturnInVO.setManufacturerId(distrReturnCheckDetail.getManufacturerId());

        /**
         * 生产厂商
         */
        distrReturnCheckDetail4ReturnInVO.setManufacturer(distrReturnCheckDetail.getManufacturer());

        /**
         * 商品产地
         */
        distrReturnCheckDetail4ReturnInVO.setGoodsPlace(distrReturnCheckDetail.getGoodsPlace());

        /**
         * 批准文号
         */
        distrReturnCheckDetail4ReturnInVO.setApprovalNumber(distrReturnCheckDetail.getApprovalNumber());

        /**
         * 收货数量
         */
        distrReturnCheckDetail4ReturnInVO.setReceiveQuantity(distrReturnCheckDetail.getReceiveQuantity());

        /**
         * 验收合格数量
         */
        distrReturnCheckDetail4ReturnInVO.setQualifiedQuantity(distrReturnCheckDetail.getQualifiedQuantity());

        /**
         * 验收不合格数量
         */
        distrReturnCheckDetail4ReturnInVO.setUnqualifiedQuantity(distrReturnCheckDetail.getUnqualifiedQuantity());

        /**
         * 状态
         */
        distrReturnCheckDetail4ReturnInVO.setStatus(distrReturnCheckDetail.getStatus());

        return distrReturnCheckDetail4ReturnInVO;
    }

    public Long getCheckDtId() {
        return checkDtId;
    }

    public void setCheckDtId(Long checkDtId) {
        this.checkDtId = checkDtId;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }


    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }


    public Long getNoticeDtlId() {
        return noticeDtlId;
    }

    public void setNoticeDtlId(Long noticeDtlId) {
        this.noticeDtlId = noticeDtlId;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
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

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DistrReturnCheckLot4ReturnInVO> getDistrReturnCheckLot4ReturnInVOS() {
        return distrReturnCheckLot4ReturnInVOS;
    }

    public void setDistrReturnCheckLot4ReturnInVOS(List<DistrReturnCheckLot4ReturnInVO> distrReturnCheckLot4ReturnInVOS) {
        this.distrReturnCheckLot4ReturnInVOS = distrReturnCheckLot4ReturnInVOS;
    }
}