package com.rograndec.feijiayun.chain.business.auth.register.entity;

import com.rograndec.feijiayun.chain.business.auth.constant.RegisterAuditStatus;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
 
/**
 * 
 * saas_register_audit
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-11-07
 */
public class RegisterAudit implements Serializable {
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
     * MPH企业ID
     */
    @ApiModelProperty(value = "MPH企业ID")
    private Integer mphEnterpriseId;

    /**
     * 许可内容（0-ERP+药学服务；1-ERP；2-药学服务）
     */
    @ApiModelProperty(value = "许可内容（0-ERP+药学服务；1-ERP；2-药学服务）")
    private Integer permitContent;

    /**
     * 许可类型（1-正式系统；2-测试系统）
     */
    @ApiModelProperty(value = "许可类型（1-正式系统；2-测试系统）")
    private Integer permitType;

    /**
     * 许可数量
     */
    @ApiModelProperty(value = "许可数量")
    private Integer permitQuantity;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Date dateLimit;

    /**
     * 销售人员
     */
    @ApiModelProperty(value = "销售人员")
    private String saleMan;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private String actualizeMan;

    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

    /**
     * 审核结果（0-待审核；1-同意；拒绝）
     */
    @ApiModelProperty(value = "审核结果（0-待审核；1-同意；拒绝）")
    private Integer auditResult;

    /**
     * 初始化
     * @param enterprise
     * @return
     */
    public static RegisterAudit initRegisterAudit(Enterprise enterprise){

        RegisterAudit registerAudit = new RegisterAudit();

        registerAudit.setEnterpriseId(enterprise.getId());
        registerAudit.setParentId(enterprise.getParentId());
        registerAudit.setMphEnterpriseId(enterprise.getRgtEnterpriseId());
        registerAudit.setAuditResult(RegisterAuditStatus.DETAIL_STATUS_WAIT.getValue());

        return registerAudit;
    }
    /**
     * saas_register_audit
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
     * MPH企业ID
     * @return mph_enterprise_id MPH企业ID
     */
    public Integer getMphEnterpriseId() {
        return mphEnterpriseId;
    }

    /**
     * MPH企业ID
     * @param mphEnterpriseId MPH企业ID
     */
    public void setMphEnterpriseId(Integer mphEnterpriseId) {
        this.mphEnterpriseId = mphEnterpriseId;
    }

    /**
     * 许可内容（0-ERP+药学服务；1-ERP；2-药学服务）
     * @return permit_content 许可内容（0-ERP+药学服务；1-ERP；2-药学服务）
     */
    public Integer getPermitContent() {
        return permitContent;
    }

    /**
     * 许可内容（0-ERP+药学服务；1-ERP；2-药学服务）
     * @param permitContent 许可内容（0-ERP+药学服务；1-ERP；2-药学服务）
     */
    public void setPermitContent(Integer permitContent) {
        this.permitContent = permitContent;
    }

    /**
     * 许可类型（1-正式系统；2-测试系统）
     * @return permit_type 许可类型（1-正式系统；2-测试系统）
     */
    public Integer getPermitType() {
        return permitType;
    }

    /**
     * 许可类型（1-正式系统；2-测试系统）
     * @param permitType 许可类型（1-正式系统；2-测试系统）
     */
    public void setPermitType(Integer permitType) {
        this.permitType = permitType;
    }

    /**
     * 许可数量
     * @return permit_quantity 许可数量
     */
    public Integer getPermitQuantity() {
        return permitQuantity;
    }

    /**
     * 许可数量
     * @param permitQuantity 许可数量
     */
    public void setPermitQuantity(Integer permitQuantity) {
        this.permitQuantity = permitQuantity;
    }

    /**
     * 
     * @return date_limit 
     */
    public Date getDateLimit() {
        return dateLimit;
    }

    /**
     * 
     * @param dateLimit 
     */
    public void setDateLimit(Date dateLimit) {
        this.dateLimit = dateLimit;
    }

    /**
     * 销售人员
     * @return sale_man 销售人员
     */
    public String getSaleMan() {
        return saleMan;
    }

    /**
     * 销售人员
     * @param saleMan 销售人员
     */
    public void setSaleMan(String saleMan) {
        this.saleMan = saleMan == null ? null : saleMan.trim();
    }

    /**
     * 
     * @return actualize_man 
     */
    public String getActualizeMan() {
        return actualizeMan;
    }

    /**
     * 
     * @param actualizeMan 
     */
    public void setActualizeMan(String actualizeMan) {
        this.actualizeMan = actualizeMan == null ? null : actualizeMan.trim();
    }

    /**
     * 审核意见
     * @return audit_opinion 审核意见
     */
    public String getAuditOpinion() {
        return auditOpinion;
    }

    /**
     * 审核意见
     * @param auditOpinion 审核意见
     */
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion == null ? null : auditOpinion.trim();
    }

    /**
     * 审核结果（0-待审核；1-同意；拒绝）
     * @return audit_result 审核结果（0-待审核；1-同意；拒绝）
     */
    public Integer getAuditResult() {
        return auditResult;
    }

    /**
     * 审核结果（0-待审核；1-同意；拒绝）
     * @param auditResult 审核结果（0-待审核；1-同意；拒绝）
     */
    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
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
        sb.append(", mphEnterpriseId=").append(mphEnterpriseId);
        sb.append(", permitContent=").append(permitContent);
        sb.append(", permitType=").append(permitType);
        sb.append(", permitQuantity=").append(permitQuantity);
        sb.append(", dateLimit=").append(dateLimit);
        sb.append(", saleMan=").append(saleMan);
        sb.append(", actualizeMan=").append(actualizeMan);
        sb.append(", auditOpinion=").append(auditOpinion);
        sb.append(", auditResult=").append(auditResult);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}