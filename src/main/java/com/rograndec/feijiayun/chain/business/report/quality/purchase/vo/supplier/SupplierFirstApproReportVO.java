package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierSalerVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/20 19:05
 */

public class SupplierFirstApproReportVO extends SupplierDetailVO {

    /**
     * 申请人员
     */
    @ApiModelProperty(value = "申请人员")
    private String applicantName;

    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    private Date applicationTime;


    @ApiModelProperty(value = "质量管理体系")
    private String qualityManageSys;



    @ApiModelProperty(value = "供货单位-资质-配置子表")
    private List<QualificationConfigVO> qualificationConfigVOS;

    @ApiModelProperty(value = "供货单位员工")
    private SupplierSalerVO supplierSalerVO;

    @ApiModelProperty(value = "审批流程")
    private List<ApprovalFlowActionDetail> approvalFlowActionDetailList;

    public List<QualificationConfigVO> getQualificationConfigVOS() {
        return qualificationConfigVOS;
    }

    public void setQualificationConfigVOS(List<QualificationConfigVO> qualificationConfigVOS) {
        this.qualificationConfigVOS = qualificationConfigVOS;
    }

    @Override
    public SupplierSalerVO getSupplierSalerVO() {
        return supplierSalerVO;
    }

    @Override
    public void setSupplierSalerVO(SupplierSalerVO supplierSalerVO) {
        this.supplierSalerVO = supplierSalerVO;
    }

    public List<ApprovalFlowActionDetail> getApprovalFlowActionDetailList() {
        return approvalFlowActionDetailList;
    }

    public void setApprovalFlowActionDetailList(List<ApprovalFlowActionDetail> approvalFlowActionDetailList) {
        this.approvalFlowActionDetailList = approvalFlowActionDetailList;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Date getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getQualityManageSys() {
        return qualityManageSys;
    }

    public void setQualityManageSys(String qualityManageSys) {
        this.qualityManageSys = qualityManageSys;
    }
}