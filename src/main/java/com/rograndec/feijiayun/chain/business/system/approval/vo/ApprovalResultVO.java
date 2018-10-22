package com.rograndec.feijiayun.chain.business.system.approval.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * @ClassName: ApprovalResultVO  
 * @Description: TODO(用于接收前端审核操作表单数据)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月25日 上午1:29:23  
 *
 */
public class ApprovalResultVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 审批操作明细ID
	 */
	@ApiModelProperty(value = "审批操作明细ID", required = true)
	@NotNull(message = "审批操作明细ID,不能为空")
	@Min(value=1, message="审批操作明细ID,不能小于1")
	private Long id;
	/**
	 * 审批意见
	 */
	@ApiModelProperty(value = "审批意见", required = false)
	private String approvalOpinion;
	/**
	 * 审批结果（0-拒绝；1-同意）
	 */
	@ApiModelProperty(value = "审批结果（0-拒绝；1-同意）", required = false)
	private Integer approvalResult;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApprovalOpinion() {
		return approvalOpinion;
	}
	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}
	public Integer getApprovalResult() {
		return approvalResult;
	}
	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}
	
}
