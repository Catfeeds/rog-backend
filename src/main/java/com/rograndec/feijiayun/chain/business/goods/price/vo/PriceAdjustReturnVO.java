package com.rograndec.feijiayun.chain.business.goods.price.vo;

import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel
public class PriceAdjustReturnVO implements Serializable {
    /**
     * 价格调整头主键id
     */
    @ApiModelProperty(value = "价格调整头主键id")
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
     * 调整单号
     */
    @ApiModelProperty(value = "调整单号")
    private String orderCode;

    /**
     * 调整日期
     */
    @ApiModelProperty(value = "调整日期")
    private Date adjustDate;

    /**
     * 调整人员ID
     */
    @ApiModelProperty(value = "调整人员ID")
    private Long adjustManId;

    /**
     * 调整人员编码
     */
    @ApiModelProperty(value = "调整人员编码")
    private String adjustManCode;

    /**
     * 调整人员名称
     */
    @ApiModelProperty(value = "调整人员名称")
    private String adjustManName;

    /**
     * 价格清单ID
     */
    @ApiModelProperty(value = "价格清单ID")
    private Long priceOrderId;

    @ApiModelProperty(value = "价格清单名称")
    private String priceOrderName;

    /**
     * 调整原因
     */
    @ApiModelProperty(value = "调整原因")
    private String adjustReason;

    /**
     * 价格调整单状态（0-禁用；1-启用
     */
    @ApiModelProperty(value = "价格调整单状态（0-禁用；1-启用")
    private Integer status;
    @ApiModelProperty(value = "价格调整单状态描述（0-禁用；1-启用")
    private String statusDesc;

    /**
     * 审批流程id
     */
    @ApiModelProperty(value = "审批流程id")
    private Long flowId;

    @ApiModelProperty(value = "审批流程操作id")
    private Long actionflowId;

    @ApiModelProperty(value = "审批状态描述")
    private String approvalFlowStatusDesc;

    @ApiModelProperty(value = "审批状态 （0-待审核 1-审核通过；2-审核被驳回）")
    private Integer approvalFLowStatus;


    public static List<Long> getActionflowId(List<PriceAdjustReturnVO> priceAdjustReturnVOS){
        List<Long> ids = new ArrayList<>();
        for(PriceAdjustReturnVO priceAdjustReturnVO : priceAdjustReturnVOS){
            ids.add(priceAdjustReturnVO.getActionflowId());
        }
        return ids;
    }

    public static List<PriceAdjustReturnVO>  setApprovalFlow(List<ApprovalFlowActionDetail> approvalFlowActionDetails
        ,List<PriceAdjustReturnVO> priceAdjustReturnVOS){
        for(ApprovalFlowActionDetail af : approvalFlowActionDetails){

            for(PriceAdjustReturnVO avp : priceAdjustReturnVOS){

                if(af.getApprovalFlowActionId().equals(avp.getActionflowId())){
                    avp.setApprovalFLowStatus(af.getStatus());
                    avp.setApprovalFlowStatusDesc(
                            ApprovalFlowAuditStatusRecom.getApprovalFlowAuditStatusRecomEnum(af.getStatus())
                            .getName()
                    );
                }
            }
        }

        return priceAdjustReturnVOS;
    }

    public static List<PriceAdjustReturnVO>  setStatusDesc(List<PriceAdjustReturnVO> priceAdjustReturnVOS){

        for(PriceAdjustReturnVO avp : priceAdjustReturnVOS){
            Integer status = avp.getStatus();
            String name = PurchaseStatus.getName(status);
            avp.setStatusDesc(name);

        }

        return priceAdjustReturnVOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    public Long getAdjustManId() {
        return adjustManId;
    }

    public void setAdjustManId(Long adjustManId) {
        this.adjustManId = adjustManId;
    }

    public String getAdjustManCode() {
        return adjustManCode;
    }

    public void setAdjustManCode(String adjustManCode) {
        this.adjustManCode = adjustManCode;
    }

    public String getAdjustManName() {
        return adjustManName;
    }

    public void setAdjustManName(String adjustManName) {
        this.adjustManName = adjustManName;
    }

    public Long getPriceOrderId() {
        return priceOrderId;
    }

    public void setPriceOrderId(Long priceOrderId) {
        this.priceOrderId = priceOrderId;
    }

    public String getPriceOrderName() {
        return priceOrderName;
    }

    public void setPriceOrderName(String priceOrderName) {
        this.priceOrderName = priceOrderName;
    }

    public String getAdjustReason() {
        return adjustReason;
    }

    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getActionflowId() {
        return actionflowId;
    }

    public void setActionflowId(Long actionflowId) {
        this.actionflowId = actionflowId;
    }

    public String getApprovalFlowStatusDesc() {
        return approvalFlowStatusDesc;
    }

    public void setApprovalFlowStatusDesc(String approvalFlowStatusDesc) {
        this.approvalFlowStatusDesc = approvalFlowStatusDesc;
    }

    public Integer getApprovalFLowStatus() {
        return approvalFLowStatus;
    }

    public void setApprovalFLowStatus(Integer approvalFLowStatus) {
        this.approvalFLowStatus = approvalFLowStatus;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}