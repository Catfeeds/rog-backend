package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DistrReturnNoticeFormVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

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

    /**
     * 配后退回通知单号
     */
    @ApiModelProperty(value = "配后退回通知单号")
    private String code;


    /**
     * 配后退回通知日期
     */
    @ApiModelProperty(value = "配后退回通知日期---界面上是订单日期")
    private Date noticeDate;


    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码---界面上指配进退出单号")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

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
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    //明细表查询
    @ApiModelProperty(value = "明细集合")
    private List<DistrReturnNoticeDtlVO> distrReturnNoticeDtlVOList;

    public static DistrReturnNoticeFormVO getDistrReturnNoticeFormVO(UserVO userVO, DistrInReturnOut distrInReturnOut){

        DistrReturnNoticeFormVO distrReturnNoticeFormVO = new DistrReturnNoticeFormVO();

        /**
         *       //需要上游提供的数据以下#################################################
         //品种类型先置为0 我查看明细之后计算塞入
         distrReturnNotice.setRemark(distrReturnNoticeFormVO.getRemark());//备注
         //需要上游数据提供以上################################################
         */

        distrReturnNoticeFormVO.setBaseOrderId(distrInReturnOut.getId());
        distrReturnNoticeFormVO.setBaseOrderType(distrInReturnOut.getOrderType());
        distrReturnNoticeFormVO.setBaseOrderCode(distrInReturnOut.getCode());
        distrReturnNoticeFormVO.setBaseOrderDate(distrInReturnOut.getOutDate());
        distrReturnNoticeFormVO.setRequestUnitId(userVO.getEnterpriseId());
        distrReturnNoticeFormVO.setRequestUnitCode(userVO.getEnterpriseCode());
        distrReturnNoticeFormVO.setRequestUnitName(userVO.getEnterpriseName());
        distrReturnNoticeFormVO.setDistrType(distrInReturnOut.getDistrType());
        distrReturnNoticeFormVO.setQuantityTotal(distrInReturnOut.getQuantityTotal());
        distrReturnNoticeFormVO.setAmountTotal(distrInReturnOut.getAmountTotal());
        distrReturnNoticeFormVO.setWholeDiscount(distrInReturnOut.getWholeDiscount());
        distrReturnNoticeFormVO.setWholeDiscountAmount(distrInReturnOut.getWholeDiscountAmount());
        distrReturnNoticeFormVO.setRealAmountTotal(distrInReturnOut.getRealAmountTotal());
        distrReturnNoticeFormVO.setNotaxRealAmountTotal(distrInReturnOut.getNotaxRealAmountTotal());
        distrReturnNoticeFormVO.setTaxAmountTotal(distrInReturnOut.getTaxAmountTotal());
        distrReturnNoticeFormVO.setStatus(DistrReturnStatus.WAIT_RECEIVE);
        distrReturnNoticeFormVO.setRemark(distrInReturnOut.getRemark());

        return distrReturnNoticeFormVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
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

    public List<DistrReturnNoticeDtlVO> getDistrReturnNoticeDtlVOList() {
        return distrReturnNoticeDtlVOList;
    }

    public void setDistrReturnNoticeDtlVOList(List<DistrReturnNoticeDtlVO> distrReturnNoticeDtlVOList) {
        this.distrReturnNoticeDtlVOList = distrReturnNoticeDtlVOList;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "DistrReturnNoticeFormVO{" +
                "id=" + id +
                ", requestUnitId=" + requestUnitId +
                ", requestUnitCode='" + requestUnitCode + '\'' +
                ", requestUnitName='" + requestUnitName + '\'' +
                ", code='" + code + '\'' +
                ", noticeDate=" + noticeDate +
                ", distrType=" + distrType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", quantityTotal=" + quantityTotal +
                ", amountTotal=" + amountTotal +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", distrReturnNoticeDtlVOList=" + distrReturnNoticeDtlVOList +
                '}';
    }

    public static DistrReturnNoticeFormVO converToVO(DistrReturnNotice distrReturnNotice) {
        DistrReturnNoticeFormVO vo = new DistrReturnNoticeFormVO();
        vo.setId(distrReturnNotice.getId());
        vo.setRequestUnitId(distrReturnNotice.getRequestUnitId());
        vo.setRequestUnitCode(distrReturnNotice.getRequestUnitCode());
        vo.setRequestUnitName(distrReturnNotice.getRequestUnitName());
        vo.setCode(distrReturnNotice.getCode());
        vo.setNoticeDate(distrReturnNotice.getNoticeDate());
        vo.setDistrType(distrReturnNotice.getDistrType());
        vo.setBaseOrderCode(distrReturnNotice.getBaseOrderCode());
        vo.setQuantityTotal(distrReturnNotice.getQuantityTotal());
        vo.setAmountTotal(distrReturnNotice.getAmountTotal());
        vo.setWholeDiscount(distrReturnNotice.getWholeDiscount());
        vo.setWholeDiscountAmount(distrReturnNotice.getWholeDiscountAmount());
        vo.setRealAmountTotal(distrReturnNotice.getRealAmountTotal());
        vo.setNotaxRealAmountTotal(distrReturnNotice.getNotaxRealAmountTotal());
        vo.setTaxAmountTotal(distrReturnNotice.getTaxAmountTotal());
        return vo;
    }
}
