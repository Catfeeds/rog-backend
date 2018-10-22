package com.rograndec.feijiayun.chain.business.retail.pos.entity;

import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
 
/**
 * 
 * saas_pos_payee_auth
 * 
 * 
 * @author liyut
 * 
 * 2017-09-18
 */
public class PosPayeeAuth implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 款员表主键ID
     */
    @ApiModelProperty(value = "款员表主键ID")
    private Long payKeyId;

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
     * 收款人员ID
     */
    @ApiModelProperty(value = "收款人员ID")
    private Long payeeId;

    /**
     * 收款人编码
     */
    @ApiModelProperty(value = "收款人编码")
    private String payeeCode;

    /**
     * 收款人名称
     */
    @ApiModelProperty(value = "收款人名称")
    private String payeeName;

    /**
     * 开单收款（0-否；1-是）
     */
    @ApiModelProperty(value = "开单收款（0-否；1-是）")
    private Integer billingPayment;

    /**
     * 款员交班（0-否；1-是）
     */
    @ApiModelProperty(value = "款员交班（0-否；1-是）")
    private Integer payeeShift;

    /**
     * 报表查询（0-否；1-是）
     */
    @ApiModelProperty(value = "报表查询（0-否；1-是）")
    private Integer reportQuery;

    /**
     * 预售开票（0-否；1-是）
     */
    @ApiModelProperty(value = "预售开票（0-否；1-是）")
    private Integer advanceBooking;

    /**
     * 增加会员（0-否；1-是）
     */
    @ApiModelProperty(value = "增加会员（0-否；1-是）")
    private Integer addMember;

    /**
     * 收款练习（0-否；1-是）
     */
    @ApiModelProperty(value = "收款练习（0-否；1-是）")
    private Integer collectionPractice;

    /**
     * 操作日志（0-否；1-是）
     */
    @ApiModelProperty(value = "操作日志（0-否；1-是）")
    private Integer operationLog;

    /**
     * 系统设置（0-否；1-是）
     */
    @ApiModelProperty(value = "系统设置（0-否；1-是）")
    private Integer systemSet;

    /**
     * 中药模式（0-否；1-是）
     */
    @ApiModelProperty(value = "中药模式（0-否；1-是）")
    private Integer chMedicineMode;

    /**
     * 补单模式（0-否；1-是）
     */
    @ApiModelProperty(value = "补单模式（0-否；1-是）")
    private Integer patchMode;

    /**
     * 销售退货（0-否；1-是）
     */
    @ApiModelProperty(value = "销售退货（0-否；1-是）")
    private Integer saleReturn;

    /**
     * 查看应缴金额（0-否；1-是）
     */
    @ApiModelProperty(value = "查看应缴金额（0-否；1-是）")
    private Integer queryPayableAmount;

    /**
     * 修改单价和金额
     */
    @ApiModelProperty(value = "修改单价和金额")
    private Integer modifyPriceAmount;

    /**
     * 修改行折扣（0-否；1-是）
     */
    @ApiModelProperty(value = "修改行折扣（0-否；1-是）")
    private Integer modifyLineDiscount;

    /**
     * 整单折扣
     */
    @ApiModelProperty(value = "整单折扣")
    private Integer wholeDiscount;

    /**
     * 抹零（0-否；1-是）
     */
    @ApiModelProperty(value = "抹零（0-否；1-是）")
    private Integer maling;

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


    public static PosPayeeAuth getPosPayeeAuth(PosPayee posPayee){

        PosPayeeAuth posPayeeAuth = new PosPayeeAuth();


        /**
         * 款员表主键ID
         */
        posPayeeAuth.setPayKeyId(posPayee.getId());

        /**
         * 企业ID
         */
        posPayeeAuth.setEnterpriseId(posPayee.getEnterpriseId());

        /**
         * 上级企业ID
         */
        posPayeeAuth.setParentId(posPayee.getParentId());

        /**
         * 收款人员ID
         */
        posPayeeAuth.setPayeeId(posPayee.getPayeeId());

        /**
         * 收款人编码
         */
        posPayeeAuth.setPayeeCode(posPayee.getPayeeCode());

        /**
         * 收款人名称
         */
        posPayeeAuth.setPayeeName(posPayee.getPayeeName());

        /**
         * 开单收款（0-否；1-是）
         */
        posPayeeAuth.setBillingPayment(EnableStatus.ENABLE.getStatus());

        /**
         * 款员交班（0-否；1-是）
         */
        posPayeeAuth.setPayeeShift(EnableStatus.ENABLE.getStatus());

        /**
         * 报表查询（0-否；1-是）
         */
        posPayeeAuth.setReportQuery(EnableStatus.ENABLE.getStatus());

        /**
         * 预售开票（0-否；1-是）
         */
        posPayeeAuth.setAdvanceBooking(EnableStatus.ENABLE.getStatus());

        /**
         * 增加会员（0-否；1-是）
         */
        posPayeeAuth.setAddMember(EnableStatus.ENABLE.getStatus());

        /**
         * 收款练习（0-否；1-是）
         */
        posPayeeAuth.setCollectionPractice(EnableStatus.ENABLE.getStatus());

        /**
         * 操作日志（0-否；1-是）
         */
        posPayeeAuth.setOperationLog(EnableStatus.ENABLE.getStatus());

        /**
         * 系统设置（0-否；1-是）
         */
        posPayeeAuth.setSystemSet(EnableStatus.ENABLE.getStatus());

        /**
         * 中药模式（0-否；1-是）
         */
        posPayeeAuth.setChMedicineMode(EnableStatus.ENABLE.getStatus());

        /**
         * 补单模式（0-否；1-是）
         */
        posPayeeAuth.setPatchMode(EnableStatus.ENABLE.getStatus());

        /**
         * 销售退货（0-否；1-是）
         */
        posPayeeAuth.setSaleReturn(EnableStatus.ENABLE.getStatus());

        /**
         * 查看应缴金额（0-否；1-是）
         */
        posPayeeAuth.setQueryPayableAmount(EnableStatus.ENABLE.getStatus());

        /**
         * 修改单价和金额
         */
        posPayeeAuth.setModifyPriceAmount(EnableStatus.ENABLE.getStatus());

        /**
         * 修改行折扣（0-否；1-是）
         */
        posPayeeAuth.setModifyLineDiscount(EnableStatus.ENABLE.getStatus());

        /**
         * 整单折扣
         */
        posPayeeAuth.setWholeDiscount(EnableStatus.ENABLE.getStatus());

        /**
         * 抹零（0-否；1-是）
         */
        posPayeeAuth.setMaling(EnableStatus.ENABLE.getStatus());

        /**
         * 状态（0-禁用；1-启用）
         */
        posPayeeAuth.setStatus(EnableStatus.ENABLE.getStatus());
        posPayeeAuth.setCreaterId(posPayee.getCreaterId());
        posPayeeAuth.setCreaterCode(posPayee.getCreaterCode());
        posPayeeAuth.setCreaterName(posPayee.getCreaterName());
        posPayeeAuth.setCreateTime(new Date());
        posPayeeAuth.setModifierId(posPayee.getCreaterId());
        posPayeeAuth.setModifierCode(posPayee.getCreaterCode());
        posPayeeAuth.setModifierName(posPayee.getCreaterName());
        posPayeeAuth.setUpdateTime(new Date());

        return posPayeeAuth;
    }

    /**
     * saas_pos_payee_auth
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
     * 款员表主键ID
     * @return pay_key_id 款员表主键ID
     */
    public Long getPayKeyId() {
        return payKeyId;
    }

    /**
     * 款员表主键ID
     * @param payKeyId 款员表主键ID
     */
    public void setPayKeyId(Long payKeyId) {
        this.payKeyId = payKeyId;
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
     * 收款人员ID
     * @return payee_id 收款人员ID
     */
    public Long getPayeeId() {
        return payeeId;
    }

    /**
     * 收款人员ID
     * @param payeeId 收款人员ID
     */
    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    /**
     * 收款人编码
     * @return payee_code 收款人编码
     */
    public String getPayeeCode() {
        return payeeCode;
    }

    /**
     * 收款人编码
     * @param payeeCode 收款人编码
     */
    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode == null ? null : payeeCode.trim();
    }

    /**
     * 收款人名称
     * @return payee_name 收款人名称
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * 收款人名称
     * @param payeeName 收款人名称
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    /**
     * 开单收款（0-否；1-是）
     * @return billing_payment 开单收款（0-否；1-是）
     */
    public Integer getBillingPayment() {
        return billingPayment;
    }

    /**
     * 开单收款（0-否；1-是）
     * @param billingPayment 开单收款（0-否；1-是）
     */
    public void setBillingPayment(Integer billingPayment) {
        this.billingPayment = billingPayment;
    }

    /**
     * 款员交班（0-否；1-是）
     * @return payee_shift 款员交班（0-否；1-是）
     */
    public Integer getPayeeShift() {
        return payeeShift;
    }

    /**
     * 款员交班（0-否；1-是）
     * @param payeeShift 款员交班（0-否；1-是）
     */
    public void setPayeeShift(Integer payeeShift) {
        this.payeeShift = payeeShift;
    }

    /**
     * 报表查询（0-否；1-是）
     * @return report_query 报表查询（0-否；1-是）
     */
    public Integer getReportQuery() {
        return reportQuery;
    }

    /**
     * 报表查询（0-否；1-是）
     * @param reportQuery 报表查询（0-否；1-是）
     */
    public void setReportQuery(Integer reportQuery) {
        this.reportQuery = reportQuery;
    }

    /**
     * 预售开票（0-否；1-是）
     * @return advance_booking 预售开票（0-否；1-是）
     */
    public Integer getAdvanceBooking() {
        return advanceBooking;
    }

    /**
     * 预售开票（0-否；1-是）
     * @param advanceBooking 预售开票（0-否；1-是）
     */
    public void setAdvanceBooking(Integer advanceBooking) {
        this.advanceBooking = advanceBooking;
    }

    /**
     * 增加会员（0-否；1-是）
     * @return add_member 增加会员（0-否；1-是）
     */
    public Integer getAddMember() {
        return addMember;
    }

    /**
     * 增加会员（0-否；1-是）
     * @param addMember 增加会员（0-否；1-是）
     */
    public void setAddMember(Integer addMember) {
        this.addMember = addMember;
    }

    /**
     * 收款练习（0-否；1-是）
     * @return collection_practice 收款练习（0-否；1-是）
     */
    public Integer getCollectionPractice() {
        return collectionPractice;
    }

    /**
     * 收款练习（0-否；1-是）
     * @param collectionPractice 收款练习（0-否；1-是）
     */
    public void setCollectionPractice(Integer collectionPractice) {
        this.collectionPractice = collectionPractice;
    }

    /**
     * 操作日志（0-否；1-是）
     * @return operation_log 操作日志（0-否；1-是）
     */
    public Integer getOperationLog() {
        return operationLog;
    }

    /**
     * 操作日志（0-否；1-是）
     * @param operationLog 操作日志（0-否；1-是）
     */
    public void setOperationLog(Integer operationLog) {
        this.operationLog = operationLog;
    }

    /**
     * 系统设置（0-否；1-是）
     * @return system_set 系统设置（0-否；1-是）
     */
    public Integer getSystemSet() {
        return systemSet;
    }

    /**
     * 系统设置（0-否；1-是）
     * @param systemSet 系统设置（0-否；1-是）
     */
    public void setSystemSet(Integer systemSet) {
        this.systemSet = systemSet;
    }

    /**
     * 中药模式（0-否；1-是）
     * @return ch_medicine_mode 中药模式（0-否；1-是）
     */
    public Integer getChMedicineMode() {
        return chMedicineMode;
    }

    /**
     * 中药模式（0-否；1-是）
     * @param chMedicineMode 中药模式（0-否；1-是）
     */
    public void setChMedicineMode(Integer chMedicineMode) {
        this.chMedicineMode = chMedicineMode;
    }

    /**
     * 补单模式（0-否；1-是）
     * @return patch_mode 补单模式（0-否；1-是）
     */
    public Integer getPatchMode() {
        return patchMode;
    }

    /**
     * 补单模式（0-否；1-是）
     * @param patchMode 补单模式（0-否；1-是）
     */
    public void setPatchMode(Integer patchMode) {
        this.patchMode = patchMode;
    }

    /**
     * 销售退货（0-否；1-是）
     * @return sale_return 销售退货（0-否；1-是）
     */
    public Integer getSaleReturn() {
        return saleReturn;
    }

    /**
     * 销售退货（0-否；1-是）
     * @param saleReturn 销售退货（0-否；1-是）
     */
    public void setSaleReturn(Integer saleReturn) {
        this.saleReturn = saleReturn;
    }

    /**
     * 查看应缴金额（0-否；1-是）
     * @return query_payable_amount 查看应缴金额（0-否；1-是）
     */
    public Integer getQueryPayableAmount() {
        return queryPayableAmount;
    }

    /**
     * 查看应缴金额（0-否；1-是）
     * @param queryPayableAmount 查看应缴金额（0-否；1-是）
     */
    public void setQueryPayableAmount(Integer queryPayableAmount) {
        this.queryPayableAmount = queryPayableAmount;
    }

    /**
     * 修改单价和金额
     * @return modify_price_amount 修改单价和金额
     */
    public Integer getModifyPriceAmount() {
        return modifyPriceAmount;
    }

    /**
     * 修改单价和金额
     * @param modifyPriceAmount 修改单价和金额
     */
    public void setModifyPriceAmount(Integer modifyPriceAmount) {
        this.modifyPriceAmount = modifyPriceAmount;
    }

    /**
     * 修改行折扣（0-否；1-是）
     * @return modify_line_discount 修改行折扣（0-否；1-是）
     */
    public Integer getModifyLineDiscount() {
        return modifyLineDiscount;
    }

    /**
     * 修改行折扣（0-否；1-是）
     * @param modifyLineDiscount 修改行折扣（0-否；1-是）
     */
    public void setModifyLineDiscount(Integer modifyLineDiscount) {
        this.modifyLineDiscount = modifyLineDiscount;
    }

    /**
     * 整单折扣
     * @return whole_discount 整单折扣
     */
    public Integer getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣
     * @param wholeDiscount 整单折扣
     */
    public void setWholeDiscount(Integer wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 抹零（0-否；1-是）
     * @return maling 抹零（0-否；1-是）
     */
    public Integer getMaling() {
        return maling;
    }

    /**
     * 抹零（0-否；1-是）
     * @param maling 抹零（0-否；1-是）
     */
    public void setMaling(Integer maling) {
        this.maling = maling;
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
        sb.append(", payKeyId=").append(payKeyId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", payeeId=").append(payeeId);
        sb.append(", payeeCode=").append(payeeCode);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", billingPayment=").append(billingPayment);
        sb.append(", payeeShift=").append(payeeShift);
        sb.append(", reportQuery=").append(reportQuery);
        sb.append(", advanceBooking=").append(advanceBooking);
        sb.append(", addMember=").append(addMember);
        sb.append(", collectionPractice=").append(collectionPractice);
        sb.append(", operationLog=").append(operationLog);
        sb.append(", systemSet=").append(systemSet);
        sb.append(", chMedicineMode=").append(chMedicineMode);
        sb.append(", patchMode=").append(patchMode);
        sb.append(", saleReturn=").append(saleReturn);
        sb.append(", queryPayableAmount=").append(queryPayableAmount);
        sb.append(", modifyPriceAmount=").append(modifyPriceAmount);
        sb.append(", modifyLineDiscount=").append(modifyLineDiscount);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", maling=").append(maling);
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