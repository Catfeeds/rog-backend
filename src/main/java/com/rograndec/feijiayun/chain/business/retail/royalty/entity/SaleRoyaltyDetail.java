package com.rograndec.feijiayun.chain.business.retail.royalty.entity;

import com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer.SaveOrUpdateDetailVO;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_sale_royalty_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-23
 */
public class SaleRoyaltyDetail implements Serializable {
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
     * 提成管理ID
     */
    @ApiModelProperty(value = "提成管理ID")
    private Long royaltyTotalId;

    /**
     * 提成ID
     */
    @ApiModelProperty(value = "提成ID")
    private Long royaltyId;

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID")
    private Long baseOrderDtlId;

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
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 营业员ID
     */
    @ApiModelProperty(value = "营业员ID")
    private Long clerkId;

    /**
     * 营业员编码
     */
    @ApiModelProperty(value = "营业员编码")
    private String clerkCode;

    /**
     * 营业员名称
     */
    @ApiModelProperty(value = "营业员名称")
    private String clerkName;

    /**
     * 提成策略
     */
    @ApiModelProperty(value = "提成策略")
    private String royaltyStrategy;

    /**
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额")
    private BigDecimal royaltyAmount;

    @ApiModelProperty(value = "成本总额")
    private BigDecimal costAmount;

    @ApiModelProperty(value = "利润总额")
    private BigDecimal profit;

    @ApiModelProperty(value = "利润率")
    private BigDecimal profitRate;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
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


    public static List<SaleRoyaltyDetail> getSaleRoyaltyDetails(List<SaveOrUpdateDetailVO> saveOrUpdateDetailVOS,Boolean isAdd) throws Exception {

        List<SaleRoyaltyDetail> saleRoyaltyDetails = new ArrayList<>();

        for(SaveOrUpdateDetailVO vo : saveOrUpdateDetailVOS){
            saleRoyaltyDetails.add(getSaleRoyaltyDetail(vo,isAdd));
        }
        return saleRoyaltyDetails;
    }

    public static SaleRoyaltyDetail getSaleRoyaltyDetail(SaveOrUpdateDetailVO detailVO,Boolean isAdd) throws Exception {

        SaleRoyaltyDetail saleRoyaltyDetail = new SaleRoyaltyDetail();
        saleRoyaltyDetail.setId(detailVO.getSaveOrUpdateSaleRoyaltyDetailVO().getId());
        saleRoyaltyDetail.setRoyaltyTotalId(detailVO.getSaleRoyaltyTotal().getId());

        /**
         * 企业ID,上级企业ID 取提成营业员的企业信息
         */
        saleRoyaltyDetail.setEnterpriseId(detailVO.getClerk().getEnterpriseId());
        saleRoyaltyDetail.setParentId(detailVO.getClerk().getParentId());



        /**
         * 提成管理ID
         */
//        saleRoyaltyDetail.setRoyaltyId(detailVO.getSaleRoyaltyTotal().getId());

        /**
         * 提成ID
         */
        saleRoyaltyDetail.setRoyaltyId(detailVO.getSaleRoyaltyTotal().getRoyaltyId());

        /**
         * 基础单据明细ID
         */
        saleRoyaltyDetail.setBaseOrderDtlId(detailVO.getSaveOrUpdateSaleRoyaltyDetailVO().getSaleDtlId());

        /**
         * 基础单据ID
         */
        saleRoyaltyDetail.setBaseOrderId(detailVO.getSaveOrUpdateSaleRoyaltyDetailVO().getSaleId());

        /**
         * 基础单据类型
         */
        saleRoyaltyDetail.setBaseOrderType(OrderRule.SALES_OUT.getType());

        /**
         * 基础单据编码
         */
        saleRoyaltyDetail.setBaseOrderCode(detailVO.getSale().getCode());

        /**
         * 基础单据日期
         */
        saleRoyaltyDetail.setBaseOrderDate(detailVO.getSale().getSaleDate());

        /**
         * 营业员ID
         */
        saleRoyaltyDetail.setClerkId(detailVO.getClerk().getId());

        /**
         * 营业员编码
         */
        saleRoyaltyDetail.setClerkCode(detailVO.getClerk().getCode());

        /**
         * 营业员名称
         */
        saleRoyaltyDetail.setClerkName(detailVO.getClerk().getName());

        /**
         * 提成策略
         */
        saleRoyaltyDetail.setRoyaltyStrategy(detailVO.getSaveOrUpdateSaleRoyaltyDetailVO().getRoyaltyStrategy());

        /**
         * 提成金额
         */
        saleRoyaltyDetail.setRoyaltyAmount(detailVO.getSaveOrUpdateSaleRoyaltyDetailVO().getRoyaltyAmount());

        /**
         * 成本总额
         */
        saleRoyaltyDetail.setCostAmount(detailVO.getSaveOrUpdateSaleRoyaltyDetailVO().getCostAmount());

        /**
         * 利润总额
         */
        saleRoyaltyDetail.setProfit(detailVO.getSaveOrUpdateSaleRoyaltyDetailVO().getProfit());

        /**
         * 利润率
         */
        saleRoyaltyDetail.setProfitRate(detailVO.getSaveOrUpdateSaleRoyaltyDetailVO().getProfitRate());

        /**
         * 状态（0-禁用；1-启用）
         */
        saleRoyaltyDetail.setStatus(EnableStatus.ENABLE.getStatus());

        UserEnterpriseUtils.setUserCreateOrModify(saleRoyaltyDetail,detailVO.getUserVO(),isAdd);

        return saleRoyaltyDetail;
    }

    /**
     *   List<Sale> sales
     , List<SaleDetail> saleDetails
     */
    public static List<Long> getSaleIds(List<SaleRoyaltyDetail> saleRoyaltyDetails){
        List<Long> ids = new ArrayList<>();
        for(SaleRoyaltyDetail saleRoyaltyDetail : saleRoyaltyDetails){
            ids.add(saleRoyaltyDetail.getBaseOrderId());
        }
        return ids;
    }

    public static List<Long> getSaleDetailIds(List<SaleRoyaltyDetail> saleRoyaltyDetails){
        List<Long> ids = new ArrayList<>();
        for(SaleRoyaltyDetail saleRoyaltyDetail : saleRoyaltyDetails){
            ids.add(saleRoyaltyDetail.getBaseOrderDtlId());
        }
        return ids;
    }



    /**
     * saas_sale_royalty_detail
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
     * 提成管理ID
     * @return royalty_total_id 提成管理ID
     */
    public Long getRoyaltyTotalId() {
        return royaltyTotalId;
    }

    /**
     * 提成管理ID
     * @param royaltyTotalId 提成管理ID
     */
    public void setRoyaltyTotalId(Long royaltyTotalId) {
        this.royaltyTotalId = royaltyTotalId;
    }

    /**
     * 提成ID
     * @return royalty_id 提成ID
     */
    public Long getRoyaltyId() {
        return royaltyId;
    }

    /**
     * 提成ID
     * @param royaltyId 提成ID
     */
    public void setRoyaltyId(Long royaltyId) {
        this.royaltyId = royaltyId;
    }

    /**
     * 基础单据明细ID
     * @return base_order_dtl_id 基础单据明细ID
     */
    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    /**
     * 基础单据明细ID
     * @param baseOrderDtlId 基础单据明细ID
     */
    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 营业员ID
     * @return clerk_id 营业员ID
     */
    public Long getClerkId() {
        return clerkId;
    }

    /**
     * 营业员ID
     * @param clerkId 营业员ID
     */
    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    /**
     * 营业员编码
     * @return clerk_code 营业员编码
     */
    public String getClerkCode() {
        return clerkCode;
    }

    /**
     * 营业员编码
     * @param clerkCode 营业员编码
     */
    public void setClerkCode(String clerkCode) {
        this.clerkCode = clerkCode == null ? null : clerkCode.trim();
    }

    /**
     * 营业员名称
     * @return clerk_name 营业员名称
     */
    public String getClerkName() {
        return clerkName;
    }

    /**
     * 营业员名称
     * @param clerkName 营业员名称
     */
    public void setClerkName(String clerkName) {
        this.clerkName = clerkName == null ? null : clerkName.trim();
    }

    /**
     * 提成策略
     * @return royalty_strategy 提成策略
     */
    public String getRoyaltyStrategy() {
        return royaltyStrategy;
    }

    /**
     * 提成策略
     * @param royaltyStrategy 提成策略
     */
    public void setRoyaltyStrategy(String royaltyStrategy) {
        this.royaltyStrategy = royaltyStrategy == null ? null : royaltyStrategy.trim();
    }

    /**
     * 提成金额
     * @return royalty_amount 提成金额
     */
    public BigDecimal getRoyaltyAmount() {
        return royaltyAmount;
    }

    /**
     * 提成金额
     * @param royaltyAmount 提成金额
     */
    public void setRoyaltyAmount(BigDecimal royaltyAmount) {
        this.royaltyAmount = royaltyAmount;
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
        sb.append(", royaltyTotalId=").append(royaltyTotalId);
        sb.append(", royaltyId=").append(royaltyId);
        sb.append(", baseOrderDtlId=").append(baseOrderDtlId);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", clerkId=").append(clerkId);
        sb.append(", clerkCode=").append(clerkCode);
        sb.append(", clerkName=").append(clerkName);
        sb.append(", royaltyStrategy=").append(royaltyStrategy);
        sb.append(", royaltyAmount=").append(royaltyAmount);
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

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }
}