package com.rograndec.feijiayun.chain.business.finance.commission.vo;

import com.rograndec.feijiayun.chain.common.constant.status.SaleCommissionStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_sale_royalty_total
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-23
 */
@ApiModel
public class NoSaleCommissionResponseTotalVO implements Serializable {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 门店id
     */
    @NotNull(message = "门店不能为空")
    @ApiModelProperty(value = "门店id")
    private Long branchId;

    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    private String branchCode;

    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称")
    private String branchName;

    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private BigDecimal saleAmount = BigDecimal.ZERO;

    /**
     * 成本金额
     */
    @ApiModelProperty(value = "成本金额")
    private BigDecimal costAmount = BigDecimal.ZERO;

    /**
     * 利润金额
     */
    @ApiModelProperty(value = "利润金额")
    private BigDecimal profit = BigDecimal.ZERO;

    /**
     * 应提金额合计
     */
    @ApiModelProperty(value = "应提金额合计")
    private BigDecimal amountTotal = BigDecimal.ZERO;


    /**
     * 实提金额合计
     */
    @ApiModelProperty(value = "实提金额合计")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;

    /**
     * 差异金额合计
     */
    @ApiModelProperty(value = "差异金额合计")
    private BigDecimal diffAmountTotal = BigDecimal.ZERO;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量")
    private BigDecimal quantity = BigDecimal.ZERO;


    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 提成日期
     */
    @ApiModelProperty(value = "提成日期")
    private Date commissionDate;

    /**
     * 提成人员ID
     */
    @ApiModelProperty(value = "提成人员ID")
    private Long commissionManId;

    /**
     * 提成人员编码
     */
    @ApiModelProperty(value = "提成人员编码")
    private String commissionManCode;

    /**
     * 提成人员名称
     */
    @ApiModelProperty(value = "提成人员名称")
    private String commissionManName;

    /**
     * 营业人员ID
     */
    @ApiModelProperty(value = "营业人员ID")
    private Long clerkId;

    /**
     * 营业人员编码
     */
    @ApiModelProperty(value = "营业人员编码")
    private String clerkCode;

    /**
     * 营业人员名称
     */
    @ApiModelProperty(value = "营业人员名称")
    private String clerkName;

    /**
     * 销售日期从
     */
    @ApiModelProperty(value = "销售日期从")
    private Date saleDateFrom;

    /**
     * 销售日期至
     */
    @ApiModelProperty(value = "销售日期至")
    private Date saleDateTo;

    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态（1-已完成；2-已冲销）
     */
    @ApiModelProperty(value = "状态（1-已完成；2-已冲销）")
    private Integer status;
    @ApiModelProperty(value = "状态（1-已完成；2-已冲销）")
    private String statusDesc;

    @ApiModelProperty(value = "修改原因")
    private String reason;

    /**
     * 草稿ID
     * */
    @ApiModelProperty(value = "草稿ID")
    private String redisKeyValue;

    @ApiModelProperty(value = "提成明细集合")
    private List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS = new ArrayList<>();


    public static void setTotal(NoSaleCommissionResponseTotalVO totalVO, List<NoSaleCommissionDetailResponseVO> detailVOS){

        /**
         * 销售金额
         */
        BigDecimal saleAmount = NoSaleCommissionDetailResponseVO.getSaleAmount(detailVOS);
        totalVO.setSaleAmount(saleAmount);

        /**
         * 成本金额
         */
        BigDecimal costAmount = NoSaleCommissionDetailResponseVO.getCostAmount(detailVOS);
        totalVO.setCostAmount(costAmount);

        /**
         * 利润金额
         */
        BigDecimal profitAmount = NoSaleCommissionDetailResponseVO.getProfitAmount(detailVOS);
        totalVO.setProfit(profitAmount);

        /**
         * 总数量
         */
        BigDecimal sumQuantity = NoSaleCommissionDetailResponseVO.getSumQuantity(detailVOS);
        totalVO.setQuantity(sumQuantity);

    }

    public static NoSaleCommissionResponseTotalVO getResponseNoSaleRoyaltyTotalVO(UserVO userVO,
            List<NoSaleCommissionDetailResponseVO> detailVOS
    ) throws ParseException {

        NoSaleCommissionResponseTotalVO totalVO = new NoSaleCommissionResponseTotalVO();
        NoSaleCommissionDetailResponseVO fristDetailVO = detailVOS.get(0);

        /**
         * 营业员ID
         */
        totalVO.setClerkId(fristDetailVO.getClerkId());

        /**
         * 营业员编码
         */
        totalVO.setClerkCode(fristDetailVO.getClerkCode());

        /**
         * 营业员名称
         */
        totalVO.setClerkName(fristDetailVO.getClerkName());


        /**
         * 销售金额
         */
        BigDecimal saleAmount = NoSaleCommissionDetailResponseVO.getSaleAmount(detailVOS);
        totalVO.setSaleAmount(saleAmount);

        /**
         * 成本金额
         */
        BigDecimal costAmount = NoSaleCommissionDetailResponseVO.getCostAmount(detailVOS);
        totalVO.setCostAmount(costAmount);

        /**
         * 利润金额
         */
        BigDecimal profitAmount = NoSaleCommissionDetailResponseVO.getProfitAmount(detailVOS);
        totalVO.setProfit(profitAmount);

        /**
         * 应提金额
         */
        BigDecimal royaltyAmount = NoSaleCommissionDetailResponseVO.getRoyaltyAmount(detailVOS);
        totalVO.setAmountTotal(royaltyAmount);

        totalVO.setRealAmountTotal(royaltyAmount);

        /**
         * 总数量
         */
        BigDecimal sumQuantity = NoSaleCommissionDetailResponseVO.getSumQuantity(detailVOS);
        totalVO.setQuantity(sumQuantity);



        /**
         * 提成日期
         */
        Date currentDate = DateUtils.getCurrentDate(new Date());
        totalVO.setCommissionDate(currentDate);

        /**
         * 提成人员ID
         */
        totalVO.setCommissionManId(userVO.getUserId());

        /**
         * 提成人员编码
         */
        totalVO.setCommissionManCode(userVO.getEnterpriseCode());

        /**
         * 提成人员名称
         */
        totalVO.setCommissionManName(userVO.getEnterpriseName());

        /**
         * 销售日期从
         */
        totalVO.setSaleDateFrom(currentDate);

        /**
         * 销售日期至
         */
        totalVO.setSaleDateTo(currentDate);

        totalVO.setResponseNoSaleRoyaltyDetailVOS(detailVOS);

        return totalVO;
    }


    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public String getClerkCode() {
        return clerkCode;
    }

    public void setClerkCode(String clerkCode) {
        this.clerkCode = clerkCode;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public List<NoSaleCommissionDetailResponseVO> getResponseNoSaleRoyaltyDetailVOS() {
        return responseNoSaleRoyaltyDetailVOS;
    }

    public void setResponseNoSaleRoyaltyDetailVOS(List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS) {
        this.responseNoSaleRoyaltyDetailVOS = responseNoSaleRoyaltyDetailVOS;
    }


    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(Date commissionDate) {
        this.commissionDate = commissionDate;
    }

    public Long getCommissionManId() {
        return commissionManId;
    }

    public void setCommissionManId(Long commissionManId) {
        this.commissionManId = commissionManId;
    }

    public String getCommissionManCode() {
        return commissionManCode;
    }

    public void setCommissionManCode(String commissionManCode) {
        this.commissionManCode = commissionManCode;
    }

    public String getCommissionManName() {
        return commissionManName;
    }

    public void setCommissionManName(String commissionManName) {
        this.commissionManName = commissionManName;
    }

    public Date getSaleDateFrom() {
        return saleDateFrom;
    }

    public void setSaleDateFrom(Date saleDateFrom) {
        this.saleDateFrom = saleDateFrom;
    }

    public Date getSaleDateTo() {
        return saleDateTo;
    }

    public void setSaleDateTo(Date saleDateTo) {
        this.saleDateTo = saleDateTo;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        String sdc = SaleCommissionStatus.getStatusDesc(status);
        return sdc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }
}