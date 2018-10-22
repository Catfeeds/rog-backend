package com.rograndec.feijiayun.chain.business.retail.royalty.entity;

import com.rograndec.feijiayun.chain.business.retail.royalty.vo.SaveOrUpdateSaleRoyaltyTotalVO;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer.SaveOrUpdateTotalVO;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class SaleRoyaltyTotal implements Serializable {
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
    private Long royaltyId;

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
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private BigDecimal saleAmount;

    /**
     * 成本金额
     */
    @ApiModelProperty(value = "成本金额")
    private BigDecimal costAmount;

    /**
     * 利润金额
     */
    @ApiModelProperty(value = "利润金额")
    private BigDecimal profit;

    /**
     * 应提金额
     */
    @ApiModelProperty(value = "应提金额")
    private BigDecimal royaltyAmount;

    /**
     * 实提金额
     */
    @ApiModelProperty(value = "实提金额")
    private BigDecimal realRoyaltyAmount;

    /**
     * 提成差额
     */
    @ApiModelProperty(value = "提成差额")
    private BigDecimal diffRoyaltyAmount;

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

    public static List<Long> getIds(List<SaleRoyaltyTotal> saleRoyaltyTotals){
        List<Long> ids = new ArrayList<>();

        for(SaleRoyaltyTotal saleRoyaltyTotal : saleRoyaltyTotals){
            ids.add(saleRoyaltyTotal.getId());
        }
        return ids;
    }

    public static List<SaleRoyaltyTotal> getSaleRoyaltyTotals(List<SaveOrUpdateTotalVO> saveOrUpdateTotalVOS , Boolean isAdd) throws Exception {
        List<SaleRoyaltyTotal> saleRoyaltyTotals = new ArrayList<>();

        for(SaveOrUpdateTotalVO totalVo : saveOrUpdateTotalVOS){
            saleRoyaltyTotals.add(getSaleRoyaltyTotal(totalVo,isAdd));
        }

        return saleRoyaltyTotals;
    }

    /**
     * 组装提成汇总信息,在生成提成人员信息之后调用
     * @param saveOrUpdateTotalVO
     * @return
     */
    public static SaleRoyaltyTotal getSaleRoyaltyTotal(SaveOrUpdateTotalVO saveOrUpdateTotalVO,Boolean isAdd) throws Exception {

        SaleRoyaltyTotal saleRoyaltyTotal =  new SaleRoyaltyTotal();
        SaveOrUpdateSaleRoyaltyTotalVO totalVO = saveOrUpdateTotalVO.getSaveOrUpdateSaleRoyaltyTotalVO();
        /**
         * 主键ID
         */
        saleRoyaltyTotal.setId(totalVO.getId());

        /**
         * 企业ID,上级企业ID 取提成营业员的企业信息
         */
        saleRoyaltyTotal.setEnterpriseId(saveOrUpdateTotalVO.getClerk().getEnterpriseId());
        saleRoyaltyTotal.setParentId(saveOrUpdateTotalVO.getClerk().getParentId());

        /**
         * 提成管理ID
         */
        saleRoyaltyTotal.setRoyaltyId(saveOrUpdateTotalVO.getSaleRoyalty().getId());

        /**
         * 营业员ID
         */
        saleRoyaltyTotal.setClerkId(saveOrUpdateTotalVO.getClerk().getId());
        /**
         * 营业员编码
         */
        saleRoyaltyTotal.setClerkCode(saveOrUpdateTotalVO.getClerk().getCode());
        /**
         * 营业员名称
         */
        saleRoyaltyTotal.setClerkName(saveOrUpdateTotalVO.getClerk().getName());

        /**
         * 销售金额
         */
        saleRoyaltyTotal.setSaleAmount(saveOrUpdateTotalVO.getSaveOrUpdateSaleRoyaltyTotalVO().getSaleAmount());

        /**
         * 成本金额
         */
        saleRoyaltyTotal.setCostAmount(saveOrUpdateTotalVO.getSaveOrUpdateSaleRoyaltyTotalVO().getCostAmount());

        /**
         * 利润金额
         */
        saleRoyaltyTotal.setProfit(saveOrUpdateTotalVO.getSaveOrUpdateSaleRoyaltyTotalVO().getProfit());

        /**
         * 应提金额
         */
        saleRoyaltyTotal.setRoyaltyAmount(saveOrUpdateTotalVO.getSaveOrUpdateSaleRoyaltyTotalVO().getRoyaltyAmount());

        /**
         * 实提金额
         */
        saleRoyaltyTotal.setRealRoyaltyAmount(saveOrUpdateTotalVO.getSaveOrUpdateSaleRoyaltyTotalVO().getRealRoyaltyAmoun());
        /**
         * 提成差额 = 应提金额 - 实提金额
         */
        BigDecimal royaltyAmount = saleRoyaltyTotal.getRoyaltyAmount();
        BigDecimal realRoyaltyAmount = saleRoyaltyTotal.getRealRoyaltyAmount();
        BigDecimal diffRoyaltyAmount = royaltyAmount.subtract(realRoyaltyAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
        saleRoyaltyTotal.setDiffRoyaltyAmount(diffRoyaltyAmount);

        /**
         * 状态（0-禁用；1-启用）
         */
        saleRoyaltyTotal.setStatus(EnableStatus.ENABLE.getStatus());
        UserEnterpriseUtils.setUserCreateOrModify(saleRoyaltyTotal,saveOrUpdateTotalVO.getUserVO(),isAdd);

        return saleRoyaltyTotal;
    }
    /**
     * saas_sale_royalty_total
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
     * @return royalty_id 提成管理ID
     */
    public Long getRoyaltyId() {
        return royaltyId;
    }

    /**
     * 提成管理ID
     * @param royaltyId 提成管理ID
     */
    public void setRoyaltyId(Long royaltyId) {
        this.royaltyId = royaltyId;
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
     * 销售金额
     * @return sale_amount 销售金额
     */
    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    /**
     * 销售金额
     * @param saleAmount 销售金额
     */
    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    /**
     * 成本金额
     * @return cost_amount 成本金额
     */
    public BigDecimal getCostAmount() {
        return costAmount;
    }

    /**
     * 成本金额
     * @param costAmount 成本金额
     */
    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    /**
     * 利润金额
     * @return profit 利润金额
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 利润金额
     * @param profit 利润金额
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * 应提金额
     * @return royalty_amount 应提金额
     */
    public BigDecimal getRoyaltyAmount() {
        return royaltyAmount;
    }

    /**
     * 应提金额
     * @param royaltyAmount 应提金额
     */
    public void setRoyaltyAmount(BigDecimal royaltyAmount) {
        this.royaltyAmount = royaltyAmount;
    }

    /**
     * 实提金额
     * @return real_royalty_amoun 实提金额
     */
    public BigDecimal getRealRoyaltyAmount() {
        return realRoyaltyAmount;
    }

    /**
     * 实提金额
     * @param realRoyaltyAmount 实提金额
     */
    public void setRealRoyaltyAmount(BigDecimal realRoyaltyAmount) {
        this.realRoyaltyAmount = realRoyaltyAmount;
    }

    /**
     * 提成差额
     * @return diff_royalty_amount 提成差额
     */
    public BigDecimal getDiffRoyaltyAmount() {
        return diffRoyaltyAmount;
    }

    /**
     * 提成差额
     * @param diffRoyaltyAmount 提成差额
     */
    public void setDiffRoyaltyAmount(BigDecimal diffRoyaltyAmount) {
        this.diffRoyaltyAmount = diffRoyaltyAmount;
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
        sb.append(", royaltyId=").append(royaltyId);
        sb.append(", clerkId=").append(clerkId);
        sb.append(", clerkCode=").append(clerkCode);
        sb.append(", clerkName=").append(clerkName);
        sb.append(", saleAmount=").append(saleAmount);
        sb.append(", costAmount=").append(costAmount);
        sb.append(", profit=").append(profit);
        sb.append(", royaltyAmount=").append(royaltyAmount);
        sb.append(", realRoyaltyAmount=").append(realRoyaltyAmount);
        sb.append(", diffRoyaltyAmount=").append(diffRoyaltyAmount);
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