package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * saas_distr_return_in_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-08
 */
public class DistrReturnInDetailPageVO implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "购后退回品种信息id")
    private Long id;

    /**
     * 配后退回入库单ID
     */
    @ApiModelProperty(value = "配后退回入库单ID")
    private Long returnInId;

    /**
     *  配后退回验收明细单ID
     */
    @ApiModelProperty(value = "配后退回验收明细单ID")
    private Long checkDtId;

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
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;


    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount=BigDecimal.ZERO;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount=BigDecimal.ZERO;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount=BigDecimal.ZERO;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice=BigDecimal.ZERO;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount=BigDecimal.ZERO;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate=BigDecimal.ZERO;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount=BigDecimal.ZERO;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    @ApiModelProperty(value = "购后退出货位信息集合")
    private List<DistrReturnInShelfPageVO> distrReturnInShelfFormVOS = new ArrayList<>();



    public static DistrReturnInDetailPageVO getDistrReturnInDetailPageVO4Check(
            DistrReturnCheckDetail distrReturnInDetail
            ,DistrReturnNoticeDetail distrReturnNoticeDetail
            ,DistrReturnNotice distrReturnNotice
    ){

        DistrReturnInDetailPageVO distrReturnInDetailPageVO = new DistrReturnInDetailPageVO();

        /**
         * 主键ID
         */
        distrReturnInDetailPageVO.setCheckDtId(distrReturnInDetail.getId());

        /**
         * 商品ID
         */
        distrReturnInDetailPageVO.setGoodsId(distrReturnInDetail.getGoodsId());

        /**
         * 商品编码
         */
        distrReturnInDetailPageVO.setGoodsCode(distrReturnInDetail.getGoodsCode());

        /**
         * 商品名称
         */
        distrReturnInDetailPageVO.setGoodsName(distrReturnInDetail.getGoodsName());

        /**
         * 商品通用名称
         */
        distrReturnInDetailPageVO.setGoodsGenericName(distrReturnInDetail.getGoodsGenericName());

        /**
         * 剂型ID
         */
        distrReturnInDetailPageVO.setDosageId(distrReturnInDetail.getDosageId());

        /**
         * 剂型名称
         */
        distrReturnInDetailPageVO.setDosageName(distrReturnInDetail.getDosageName());

        /**
         * 商品规格
         */
        distrReturnInDetailPageVO.setGoodsSpecification(distrReturnInDetail.getGoodsSpecification());

        /**
         * 生产厂商ID
         */
        distrReturnInDetailPageVO.setManufacturerId(distrReturnInDetail.getManufacturerId());

        /**
         * 生产厂商
         */
        distrReturnInDetailPageVO.setManufacturer(distrReturnInDetail.getManufacturer());

        distrReturnInDetailPageVO.setGoodsPlace(distrReturnInDetail.getGoodsPlace());

        /**
         * 单位ID
         */
        distrReturnInDetailPageVO.setUnitId(distrReturnInDetail.getUnitId());

        /**
         * 单位名称
         */
        distrReturnInDetailPageVO.setUnitName(distrReturnInDetail.getUnitName());

        /**
         * 数量
         */
        distrReturnInDetailPageVO.setQuantity(distrReturnInDetail.getReceiveQuantity());

        /**
         * 单价
         */
        distrReturnInDetailPageVO.setUnitPrice(distrReturnNoticeDetail.getUnitPrice());

        /**
         * 行折扣（%）
         */
        distrReturnInDetailPageVO.setLineDiscount(distrReturnNoticeDetail.getLineDiscount());

        /**
         *
         * @Title: getAmountByQuantityAndPriceAndLineDiscount
         * @Description: 根据数量、单价、行折扣获取金额（整单折扣前金额）：数量*单价*行折扣
         * @param @param quantity 数量
         * @param @param price 单价
         * @param @param lineDiscount 行折扣（传％前数值）
         * @param @return
         * @return BigDecimal 返回类型
         * @
         *
         */
        BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
                distrReturnInDetailPageVO.getQuantity()
                , distrReturnInDetailPageVO.getUnitPrice()
                , distrReturnInDetailPageVO.getLineDiscount()
        );

        distrReturnInDetailPageVO.setAmount(amount);


        /**
         *
         * @Title: getLineRoundOffByLineAmountAndWholeAmountTotal
         * @Description: 根据整单舍入、整单金额合计获取行舍入：整单舍入*（行金额/整单金额合计）
         * @param @param wholeRoundOff 整单舍入
         * @param @param lineAmount 行金额
         * @param @param wholeAmountTotal 整单金额合计
         * @param @return
         * @return BigDecimal 返回类型
         * @throws
         */
        BigDecimal lineRoundOff = CalculateComponent.getLineRoundOffByLineAmountAndWholeAmountTotal(
                distrReturnNotice.getWholeDiscountAmount()
                , distrReturnInDetailPageVO.getAmount()
                , distrReturnNotice.getAmountTotal()
        );

        /**
         *
         * @Title: getRealAmountByQuantityAndPriceAndLineDiscount
         * @Description: 根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
         * @param @param quantity 数量
         * @param @param price 单价
         * @param @param lineDiscount 行折扣（传％前数值）
         * @param @param wholeDiscount 整单折扣（传％前数值）
         * @param @param lineRoundOff 行舍入
         * @param @return
         * @return BigDecimal 返回类型
         * @throws
         */
        BigDecimal realAmount = CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(
                distrReturnInDetailPageVO.getQuantity()
                , distrReturnInDetailPageVO.getUnitPrice()
                , distrReturnInDetailPageVO.getLineDiscount()
                , distrReturnNotice.getWholeDiscount()
                , lineRoundOff
        );
        /**
         * 实际金额
         */
        distrReturnInDetailPageVO.setRealAmount(realAmount);

        /**
         * 不含税实际单价
         */
        distrReturnInDetailPageVO.setNotaxRealPrice(distrReturnNoticeDetail.getNotaxRealPrice());


        /**
         * @Title: getNotaxAmountByRealAmountAndTaxRate
         * @Description: 根据实际金额和税率获取不含税金额：金额/(1+税率)
         * @param @param realAmount 实际金额
         * @param @param taxRate 税率
         * @param @return
         * @return BigDecimal 返回类型
         * @throws
         */
        BigDecimal notaxRealAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(
                distrReturnInDetailPageVO.getRealAmount()
                , distrReturnNoticeDetail.getTaxRate()
        );

        /**
         * 不含税实际金额
         */
        distrReturnInDetailPageVO.setNotaxRealAmount(notaxRealAmount);


        /**
         * 税率ID
         */
        distrReturnInDetailPageVO.setTaxRateId(distrReturnNoticeDetail.getTaxRateId());

        /**
         * 税率
         */
        distrReturnInDetailPageVO.setTaxRate(distrReturnNoticeDetail.getTaxRate());


        /**
         *
         * @Title: getTaxAmountByRealAmountAndNotaxAmount
         * @Description: 根据实际金额和不含税金额获取税额：金额-不含税金额
         * @param @param realAmount 实际金额
         * @param @param notaxAmount 不含税金额
         * @param @return
         * @return BigDecimal 返回类型
         * @throws
         */
        BigDecimal taxAmount = CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(
                distrReturnInDetailPageVO.getRealAmount()
                , distrReturnInDetailPageVO.getNotaxRealAmount()
        );
        distrReturnInDetailPageVO.setTaxAmount(taxAmount);

        /**
         * 备注
         */
        distrReturnInDetailPageVO.setRemark(distrReturnInDetail.getRemark());

        return distrReturnInDetailPageVO;
    }

    public static List<DistrReturnInDetailPageVO> getDistrReturnInDetailPageVOs(List<DistrReturnInDetail> distrReturnInDetails){

        List<DistrReturnInDetailPageVO> distrReturnInDetailPageVOS = new ArrayList<>();

        distrReturnInDetails.forEach(drid -> {

            DistrReturnInDetailPageVO distrReturnInDetailPageVO = getDistrReturnInDetailPageVO(drid);

            distrReturnInDetailPageVOS.add(distrReturnInDetailPageVO);
        });

        return distrReturnInDetailPageVOS;

    }

    public static DistrReturnInDetailPageVO getDistrReturnInDetailPageVO(DistrReturnInDetail distrReturnInDetail){

        DistrReturnInDetailPageVO distrReturnInDetailPageVO = new DistrReturnInDetailPageVO();

        /**
         * 主键ID
         */
        distrReturnInDetailPageVO.setId(distrReturnInDetail.getId());

        distrReturnInDetailPageVO.setReturnInId(distrReturnInDetail.getReturnInId());
        /**
         * 商品ID
         */
        distrReturnInDetailPageVO.setGoodsId(distrReturnInDetail.getGoodsId());

        /**
         * 商品编码
         */
        distrReturnInDetailPageVO.setGoodsCode(distrReturnInDetail.getGoodsCode());

        /**
         * 商品名称
         */
        distrReturnInDetailPageVO.setGoodsName(distrReturnInDetail.getGoodsName());

        /**
         * 商品通用名称
         */
        distrReturnInDetailPageVO.setGoodsGenericName(distrReturnInDetail.getGoodsGenericName());

        /**
         * 剂型ID
         */
        distrReturnInDetailPageVO.setDosageId(distrReturnInDetail.getDosageId());

        /**
         * 剂型名称
         */
        distrReturnInDetailPageVO.setDosageName(distrReturnInDetail.getDosageName());

        /**
         * 商品规格
         */
        distrReturnInDetailPageVO.setGoodsSpecification(distrReturnInDetail.getGoodsSpecification());

        /**
         * 生产厂商ID
         */
        distrReturnInDetailPageVO.setManufacturerId(distrReturnInDetail.getManufacturerId());

        /**
         * 生产厂商
         */
        distrReturnInDetailPageVO.setManufacturer(distrReturnInDetail.getManufacturer());

        /**
         * 单位ID
         */
        distrReturnInDetailPageVO.setUnitId(distrReturnInDetail.getUnitId());

        /**
         * 单位名称
         */
        distrReturnInDetailPageVO.setUnitName(distrReturnInDetail.getUnitName());

        distrReturnInDetailPageVO.setGoodsPlace(distrReturnInDetail.getGoodsPlace());

        /**
         * 数量
         */
        distrReturnInDetailPageVO.setQuantity(distrReturnInDetail.getQuantity());

        /**
         * 单价
         */
        distrReturnInDetailPageVO.setUnitPrice(distrReturnInDetail.getUnitPrice());
        distrReturnInDetailPageVO.setAmount(distrReturnInDetail.getAmount());

        /**
         * 行折扣（%）
         */
        distrReturnInDetailPageVO.setLineDiscount(distrReturnInDetail.getLineDiscount());

        /**
         * 实际金额
         */
        distrReturnInDetailPageVO.setRealAmount(distrReturnInDetail.getRealAmount());

        /**
         * 不含税实际单价
         */
        distrReturnInDetailPageVO.setNotaxRealPrice(distrReturnInDetail.getNotaxRealPrice());


        /**
         * 不含税实际金额
         */
        distrReturnInDetailPageVO.setNotaxRealAmount(distrReturnInDetail.getNotaxRealAmount());

        /**
         * 税率ID
         */
        distrReturnInDetailPageVO.setTaxRateId(distrReturnInDetail.getTaxRateId());

        /**
         * 税率
         */
        distrReturnInDetailPageVO.setTaxRate(distrReturnInDetail.getTaxRate());
        distrReturnInDetailPageVO.setTaxAmount(distrReturnInDetail.getTaxAmount());

        /**
         * 备注
         */
        distrReturnInDetailPageVO.setRemark(distrReturnInDetail.getRemark());

        distrReturnInDetailPageVO.setLineNum(distrReturnInDetail.getLineNum());

        return distrReturnInDetailPageVO;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DistrReturnInShelfPageVO> getDistrReturnInShelfFormVOS() {
        return distrReturnInShelfFormVOS;
    }

    public void setDistrReturnInShelfFormVOS(List<DistrReturnInShelfPageVO> distrReturnInShelfFormVOS) {
        this.distrReturnInShelfFormVOS = distrReturnInShelfFormVOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReturnInId() {
        return returnInId;
    }

    public void setReturnInId(Long returnInId) {
        this.returnInId = returnInId;
    }

    public Long getCheckDtId() {
        return checkDtId;
    }

    public void setCheckDtId(Long checkDtId) {
        this.checkDtId = checkDtId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }
}