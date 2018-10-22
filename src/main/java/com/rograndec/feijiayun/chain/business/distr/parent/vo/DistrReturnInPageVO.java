package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheck;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DistrReturnInPageVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "配合退回id")
    private Long id;

    /**
     * 校验单id
     */
    @ApiModelProperty(value = "校验单id")
    private Long checkId;

    /**
     * 配进入库单号
     */
    @ApiModelProperty(value = "配进入库单号")
    private String code;

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;

    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    @ApiModelProperty(value = "配货类型描述（0-总部配送；3-分店间调剂；4-直调配送）")
    private String distrTypeDesc;

    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID")
    private Long storageManId;

    /**
     * 入库人员编码
     */
    @ApiModelProperty(value = "入库人员编码")
    private String storageManCode;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String storageManName;
    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private Date returnInDate;


    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal=BigDecimal.ZERO;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal=BigDecimal.ZERO;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal=BigDecimal.ZERO;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;
    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal=BigDecimal.ZERO;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount=BigDecimal.ZERO;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantityTotal;

    @ApiModelProperty(value = "购后退出明细单集合")
    private List<DistrReturnInDetailPageVO> distrReturnInDetailFormVOS = new ArrayList<>();


    public static DistrReturnInPageVO afterGenerateDetailsetReturnIn(DistrReturnInPageVO distrReturnInPageVO,List<DistrReturnInDetailPageVO> distrReturnInDetailPageVOS){


        BigDecimal notaxRealAmountTotal = new BigDecimal(0);

        BigDecimal taxAmountTotal = new BigDecimal(0);
        BigDecimal amountTotal = new BigDecimal(0);

        for(DistrReturnInDetailPageVO drid : distrReturnInDetailPageVOS){
            notaxRealAmountTotal = notaxRealAmountTotal.add(drid.getNotaxRealAmount());
            taxAmountTotal = taxAmountTotal.add(drid.getTaxAmount());
            amountTotal = amountTotal.add(drid.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        /**
         * 不含税金额合计
         */
        distrReturnInPageVO.setNotaxRealAmountTotal(notaxRealAmountTotal);

        /**
         * 税额合计
         */
        distrReturnInPageVO.setTaxAmountTotal(taxAmountTotal);


        /**
         * 金额合计（整单优惠前的金额合计）
         */
        distrReturnInPageVO.setAmountTotal(amountTotal);


        /**
         * 实际金额合计
         */
        BigDecimal realAmountTotal = DistrReturnIn.calcTotalRealAmount(
                distrReturnInPageVO.getAmountTotal()
                , distrReturnInPageVO.getWholeDiscount()
                , distrReturnInPageVO.getWholeDiscountAmount()
        );
        distrReturnInPageVO.setRealAmountTotal(realAmountTotal);


        return distrReturnInPageVO;
    }

    public static DistrReturnInPageVO getDistrReturnInPageVO4ReturnCheck(UserVO userVO,DistrReturnCheck distrReturnIn,DistrReturnNotice distrReturnNotice){

        DistrReturnInPageVO distrReturnInPageVO = new DistrReturnInPageVO();

        /**
         * 主键ID
         */
        distrReturnInPageVO.setCheckId(distrReturnIn.getId());


        /**
         * 要货单位ID
         */
        distrReturnInPageVO.setRequestUnitId(distrReturnIn.getRequestUnitId());

        /**
         * 要货单位编码
         */
        distrReturnInPageVO.setRequestUnitCode(distrReturnIn.getRequestUnitCode());

        /**
         * 要货单位名称
         */
        distrReturnInPageVO.setRequestUnitName(distrReturnIn.getRequestUnitName());

        /**
         * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
         */
        distrReturnInPageVO.setDistrType(distrReturnIn.getDistrType());

        distrReturnInPageVO.setDistrTypeDesc(DistrType.getValue(distrReturnIn.getDistrType()));

        /**
         * 入库人员ID
         */
        distrReturnInPageVO.setStorageManId(userVO.getUserId());

        /**
         * 入库人员编码
         */
        distrReturnInPageVO.setStorageManCode(userVO.getUserCode());

        /**
         * 入库人员名称
         */
        distrReturnInPageVO.setStorageManName(userVO.getUserName());

        /**
         * 入库日期
         */
        distrReturnInPageVO.setReturnInDate(new Date());


        /**
         * 状态
         */
        distrReturnInPageVO.setStatus(distrReturnIn.getStatus());

        distrReturnInPageVO.setStatusDesc(DistrReturnStatus.getStatusDesc(distrReturnIn.getStatus()));


        /**
         * 整单折扣（%）
         */
        distrReturnInPageVO.setWholeDiscount(distrReturnNotice.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        distrReturnInPageVO.setWholeDiscountAmount(distrReturnNotice.getWholeDiscountAmount());

        /**
         * 数量
         */
        distrReturnInPageVO.setQuantityTotal(distrReturnIn.getQuantityTotal());

        return distrReturnInPageVO;
    }

    public static List<DistrReturnInPageVO> getDistrReturnInPageVOs(List<DistrReturnIn> distrReturnIns){

        List<DistrReturnInPageVO> distrReturnInPageVOS = new ArrayList<>();

        distrReturnIns.forEach(drti -> {

            DistrReturnInPageVO distrReturnInPageVO = getDistrReturnInPageVO(drti);

            distrReturnInPageVOS.add(distrReturnInPageVO);
        });

        return distrReturnInPageVOS;
    }

    public static DistrReturnInPageVO getDistrReturnInPageVO(DistrReturnIn distrReturnIn){

        DistrReturnInPageVO distrReturnInPageVO = new DistrReturnInPageVO();

        /**
         * 主键ID
         */
        distrReturnInPageVO.setId(distrReturnIn.getId());

        /**
         * 配进入库单号
         */
        distrReturnInPageVO.setCode(distrReturnIn.getCode());

        /**
         * 要货单位ID
         */
        distrReturnInPageVO.setRequestUnitId(distrReturnIn.getRequestUnitId());

        /**
         * 要货单位编码
         */
        distrReturnInPageVO.setRequestUnitCode(distrReturnIn.getRequestUnitCode());

        /**
         * 要货单位名称
         */
        distrReturnInPageVO.setRequestUnitName(distrReturnIn.getRequestUnitName());

        /**
         * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
         */
        distrReturnInPageVO.setDistrType(distrReturnIn.getDistrType());

        distrReturnInPageVO.setDistrTypeDesc(DistrType.getValue(distrReturnIn.getDistrType()));

        /**
         * 入库人员ID
         */
        distrReturnInPageVO.setStorageManId(distrReturnIn.getStorageManId());

        /**
         * 入库人员编码
         */
        distrReturnInPageVO.setStorageManCode(distrReturnIn.getStorageManCode());

        /**
         * 入库人员名称
         */
        distrReturnInPageVO.setStorageManName(distrReturnIn.getStorageManName());

        /**
         * 入库日期
         */
        distrReturnInPageVO.setReturnInDate(distrReturnIn.getReturnInDate());

        /**
         * 实际金额合计
         */
        distrReturnInPageVO.setRealAmountTotal(distrReturnIn.getRealAmountTotal());

        /**
         * 不含税金额合计
         */
        distrReturnInPageVO.setNotaxRealAmountTotal(distrReturnIn.getNotaxRealAmountTotal());

        /**
         * 税额合计
         */
        distrReturnInPageVO.setTaxAmountTotal(distrReturnIn.getTaxAmountTotal());

        /**
         * 状态
         */
        distrReturnInPageVO.setStatus(distrReturnIn.getStatus());

        distrReturnInPageVO.setStatusDesc(DistrReturnStatus.getStatusDesc(distrReturnIn.getStatus()));
        /**
         * 流通监管码
         */
        distrReturnInPageVO.setFlowCode(distrReturnIn.getFlowCode());

        /**
         * 金额合计（整单优惠前的金额合计）
         */
        distrReturnInPageVO.setAmountTotal(distrReturnIn.getAmountTotal());

        /**
         * 整单折扣（%）
         */
        distrReturnInPageVO.setWholeDiscount(distrReturnIn.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        distrReturnInPageVO.setWholeDiscountAmount(distrReturnIn.getWholeDiscountAmount());

        /**
         * 数量
         */
        distrReturnInPageVO.setQuantityTotal(distrReturnIn.getQuantityTotal());

        return distrReturnInPageVO;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public Date getReturnInDate() {
        return returnInDate;
    }

    public void setReturnInDate(Date returnInDate) {
        this.returnInDate = returnInDate;
    }

    public Long getStorageManId() {
        return storageManId;
    }

    public void setStorageManId(Long storageManId) {
        this.storageManId = storageManId;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }


    public List<DistrReturnInDetailPageVO> getDistrReturnInDetailFormVOS() {
        return distrReturnInDetailFormVOS;
    }

    public void setDistrReturnInDetailFormVOS(List<DistrReturnInDetailPageVO> distrReturnInDetailFormVOS) {
        this.distrReturnInDetailFormVOS = distrReturnInDetailFormVOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode;
    }

    public String getRequestUnitName() {
        return requestUnitName;
    }

    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName;
    }

    public String getDistrTypeDesc() {
        return distrTypeDesc;
    }

    public void setDistrTypeDesc(String distrTypeDesc) {
        this.distrTypeDesc = distrTypeDesc;
    }

    public String getStorageManCode() {
        return storageManCode;
    }

    public void setStorageManCode(String storageManCode) {
        this.storageManCode = storageManCode;
    }

    public String getStorageManName() {
        return storageManName;
    }

    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }
}
