package com.rograndec.feijiayun.chain.business.system.approval.vo;

/**
 * 
 * @ClassName: ApprovalFlowActionSearchVO  
 * @Description: TODO(审批操作列表查询dao参数)  
 * @author jianhui.zhao jianhui.zhao@rograndec.com  
 * @date 2017年8月25日 下午8:54:14  
 *
 */
public class ApprovalFlowActionSearchVO {
	/**
	 * 企业ID
	 */
	private long enterpriseId;
	
	/**
	 * 当前用户ID
	 */
	private long currentUserId;
	
	/**
	 * 当前用户角色ID集合，以[,]分割
	 */
	private String roleIdsStr;
	
	/**
     * 复合状态（0-待审核；1-审核被驳回；2-已完成）
     */
    private Integer statusRecom;
    
	/**
     * 审批内容（）
     */
    private String content;
    
    private String name;
    
    /**
     * 发起机构ID
     */
    private Long startOrgId;
    
    /**
     * 发起人名称
     */
    private String starterName;
    
    /**
     * 状态（0-待审核 1-审核通过；2-审核被驳回）
     */
    private Integer status;
    
    /**
     * 审批机构ID
     */
    private Long approvalOrgId;
    
    /**
     * 审批人ID
     */
    private String approverName;
    

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public long getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(long currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getRoleIdsStr() {
		return roleIdsStr;
	}

	public void setRoleIdsStr(String roleIdsStr) {
		this.roleIdsStr = roleIdsStr;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}
