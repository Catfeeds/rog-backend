package com.rograndec.feijiayun.chain.business.system.approval.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalStartOrg;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlow;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowContent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApprovalFlowReturnVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "审批流ID", required = true)
    private Long id;

    /**
     * 企业ID
     */
	@JsonIgnore
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
	@JsonIgnore
    private Long parentId;

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店；）
     */
	@JsonIgnore
    private Integer chainType;

    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称", required = true)
    private String name;

    /**
     * 审批内容
     */
    @ApiModelProperty(
    		value = "审批内容（0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）", 
    		required = true)
    private String content;


    @ApiModelProperty(
            value = "审批内容（0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）")
    private String contentDesc;
    
    /**
     * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
     */
    @ApiModelProperty(value = "发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）", required = true)
    private Integer startOrg;

    @ApiModelProperty(value = "发起机构描述0-总部；1-全部分店；2-仅自营店；3-仅加盟店 ; 指定发起机构为发起机构名称", required = true)
    private String startOrgDesc;


    /**
     * 指定发起机构ID
     */
    @ApiModelProperty(value = "指定发起机构ID", required = true)
    private Long startOrgId;
    
    /**
     * 指定发起机构名称
     */
    @ApiModelProperty(value = "指定发起机构名称", required = true)
    private String startOrgName;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）", required = true)
    private Integer status;

    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private String statusDesc;

    /**
     * 系统默认标识（0-系统默认；1-非系统默认）
     */
    @JsonIgnore
    private Integer defaultFlag;

    private List<ApprovalFlowDetailReturnVO> aFDList;


    public static List<ApprovalFlowReturnVO> getApprovalFlowReturnVOs4ApprovalFlows(List<ApprovalFlow> approvalFlows,List<ApprovalFlowContent> approvalFlowContents){

        List<ApprovalFlowReturnVO> approvalFlowReturnVOS = new ArrayList<>();

        for(ApprovalFlow approvalFlow : approvalFlows){
            ApprovalFlowReturnVO approvalFlowReturnVO = getApprovalFlowReturnVO4ApprovalFlow(approvalFlow,approvalFlowContents);
            approvalFlowReturnVOS.add(approvalFlowReturnVO);
        }
        return approvalFlowReturnVOS;
    }

    public static ApprovalFlowReturnVO getApprovalFlowReturnVO4ApprovalFlow(ApprovalFlow approvalFlow, List<ApprovalFlowContent> approvalFlowContents){
        ApprovalFlowReturnVO approvalFlowReturnVO = new ApprovalFlowReturnVO();
        approvalFlowReturnVO.setId(approvalFlow.getId());
        approvalFlowReturnVO.setEnterpriseId(approvalFlow.getEnterpriseId());
        approvalFlowReturnVO.setParentId(approvalFlow.getParentId());
        approvalFlowReturnVO.setChainType(approvalFlow.getChainType());
        approvalFlowReturnVO.setName(approvalFlow.getName());
        approvalFlowReturnVO.setContent(approvalFlow.getContent());
        approvalFlowReturnVO.setStartOrg(approvalFlow.getStartOrg());
        approvalFlowReturnVO.setStartOrgId(approvalFlow.getStartOrgId());
        approvalFlowReturnVO.setStartOrgName(approvalFlow.getStartOrgName());
        approvalFlowReturnVO.setStatus(approvalFlow.getStatus());
        approvalFlowReturnVO.setDefaultFlag(approvalFlow.getDefaultFlag());
        approvalFlowReturnVO.setStatusDesc(EnableStatus.getName(approvalFlow.getStatus()));
        approvalFlowContents.forEach(approvalFlowContent -> {

            if(approvalFlowContent.getContentId().equals(approvalFlow.getContent())){
                approvalFlowReturnVO.setContentDesc(approvalFlowContent.getName());
            }
        });

        String startOrgDescStr = getStartOrgDescStr(approvalFlow);
        approvalFlowReturnVO.setStartOrgDesc(startOrgDescStr);
        return approvalFlowReturnVO;
    }

    public static String getStartOrgDescStr(ApprovalFlow approvalFlow){

        //指定机构
        if(approvalFlow.getStartOrg().equals(ApprovalStartOrg.ZDFQJG.getOrgFlag())){
            return approvalFlow.getStartOrgName();
        }else {

            Integer startOrg = approvalFlow.getStartOrg();
            ApprovalStartOrg approvalStartOrgEnum = ApprovalStartOrg.getApprovalStartOrgEnum(startOrg);
            if(null != approvalStartOrgEnum){
                return approvalStartOrgEnum.getOrgName();
            }else {
                return "";
            }

        }

    }

    /**
     * saas_approval_flow
     */
    private static final long serialVersionUID = 1L;

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

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStartOrg() {
        return startOrg;
    }

    public void setStartOrg(Integer startOrg) {
        this.startOrg = startOrg;
    }

    public Long getStartOrgId() {
        return startOrgId;
    }

    public void setStartOrgId(Long startOrgId) {
        this.startOrgId = startOrgId;
    }

    public String getStartOrgName() {
        return startOrgName;
    }

    public void setStartOrgName(String startOrgName) {
        this.startOrgName = startOrgName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ApprovalFlowDetailReturnVO> getaFDList() {
        return aFDList;
    }

    public void setaFDList(List<ApprovalFlowDetailReturnVO> aFDList) {
        this.aFDList = aFDList;
    }

    public String getStartOrgDesc() {
        return startOrgDesc;
    }

    public void setStartOrgDesc(String startOrgDesc) {
        this.startOrgDesc = startOrgDesc;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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
        sb.append(", chainType=").append(chainType);
        sb.append(", name=").append(name);
        sb.append(", content=").append(content);
        sb.append(", startOrg=").append(startOrg);
        sb.append(", startOrgId=").append(startOrgId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}