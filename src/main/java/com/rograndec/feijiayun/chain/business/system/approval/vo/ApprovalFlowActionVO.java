package com.rograndec.feijiayun.chain.business.system.approval.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.Min;

/**
 * 
 * @ClassName: ApprovalFlowActionVO  
 * @Description: TODO(用于审批操作中查询列表接收前端参数vo)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月23日 下午9:06:40  
 *
 */
public class ApprovalFlowActionVO {
	
	/**
     * 复合状态（0-待审核；1-审核被驳回；2-已完成）
     */
//	@NotNull(message="复合状态,不能为空")
	@Min(value=0, message="复合状态,不能为空")
	@ApiModelProperty(value = "复合状态（0-待审核；1-已完成；2-审核被驳回）", required = true)
    private Integer statusRecom;
    
	/**
     * 审批内容（0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）
     */
	@ApiModelProperty(
    		value = "审批内容（0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）", 
    		required = true)
//	@NotNull(message="审批内容,不能为空")
    private String content;

	/**
	 * 流程名称
	 */
	@ApiModelProperty(value = "流程名称", required = false)
    private String name;
    
    /**
     * 发起机构ID
     */
	@ApiModelProperty(value = "发起机构ID", required = false)
    private Long startOrgId;
    
    /**
     * 发起人名称
     */
	@ApiModelProperty(value = "发起人名称", required = false)
    private String starterName;
    
    /**
     * 审批机构ID
     */
	@ApiModelProperty(value = "审批机构ID", required = false)
    private Long approvalOrgId;
    
    /**
     * 审批人名称
     */
	@ApiModelProperty(value = "审批人名称", required = false)
    private String approverName;

	@ApiParam(name = "pageNo", value = "当前页码", required = false)
	private Integer pageNo;
	@ApiParam(name = "pageSize", value = "显示条数", required = false)
	private Integer pageSize;

    
	public Integer getStatusRecom() {
		return statusRecom;
	}

	public void setStatusRecom(Integer statusRecom) {
		this.statusRecom = statusRecom;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStartOrgId() {
		return startOrgId;
	}

	public void setStartOrgId(Long startOrgId) {
		this.startOrgId = startOrgId;
	}

	public String getStarterName() {
		return starterName;
	}

	public void setStarterName(String starterName) {
		this.starterName = starterName;
	}

	public Long getApprovalOrgId() {
		return approvalOrgId;
	}

	public void setApprovalOrgId(Long approvalOrgId) {
		this.approvalOrgId = approvalOrgId;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
