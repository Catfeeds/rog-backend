package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 
 * saas_distr_in_return_out
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-12
 */
public class DistrInReturnOutAuditFormVO implements Serializable {

    @ApiModelProperty(value = "购进退出出库单id",required = true)
    private Long id;

    @ApiModelProperty(value = "复核原因",required = false)
    private String auditReason;

    @ApiModelProperty(value = "复核原因 0 : 驳回,1:同意",required = true)
    private int audit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }
}