package com.rograndec.feijiayun.chain.business.system.approval.vo;

import java.util.List;

import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;

/**
 * 
 * @ClassName: ApprovalFlowActionDetailVO  
 * @Description: TODO(用于查询审批操作明细VO)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月24日 下午9:31:12  
 *
 */
public class ApprovalFlowActionDetailVO {
	
	/**
	 * 审批阶段，前端控件特殊字段
	 */
	private Integer stage;
	
	/**
	 * 审批操作明细信息
	 */
	private List<ApprovalFlowActionDetail> aFADList;

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public List<ApprovalFlowActionDetail> getaFADList() {
		return aFADList;
	}

	public void setaFADList(List<ApprovalFlowActionDetail> aFADList) {
		this.aFADList = aFADList;
	}
	
}
