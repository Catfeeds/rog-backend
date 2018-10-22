package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleFlowListSumQueryBranchVO", description = "按日结标识、汇总查询销售流水列表（分店）查询参数对象")
public class SaleFlowListSumQueryBranchVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "页码")
	private Integer pageNo;
	
	@ApiModelProperty(required = true, value = "每页显示的记录数")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, value = "按某一列排序")
	private String order;
	
	@ApiModelProperty(required = false, value = "排序方式（升序：asc,降序：desc）")
	private String sort;
	
	@ApiModelProperty(required = false, value = "起始时间")
	private Date startDate;

	@ApiModelProperty(required = false, value = "截止时间")
	private Date endDate;
	
	@ApiModelProperty(required = true, value = "日结标识（0-未日结；1-已日结）")
	private Integer dailySettlementFlag;
	
	/**
	 * 总部快速搜索，仅搜索直营店销售数据 ，将chainType默认为直营店
	 */
	@ApiModelProperty(required = false, value = "药店类型（0-总部；1-自营店；2-加盟店）")
	private Integer chainType = 1;
	
	@ApiModelProperty(required = false, value = "门店编码")
	private String storeCode;
	
	@ApiModelProperty(required = false, value = "门店名称")
	private String storeName;
	
	@ApiModelProperty(required = false, value = "销售单号")
	private String saleCode;
	
	@ApiModelProperty(required = false, value = "款台编码")
	private String standCode;
	
	@ApiModelProperty(required = false, value = "班组ID")
	private Long teamId;
	
	@ApiModelProperty(required = false, value = "收款人员")
	private String payeeName;
	
	/*@ApiModelProperty(required = false, value = "营业人员")
	private String clerkName;*/
	
	@ApiModelProperty(required = false, value = "会员卡号")
	private String memberCardCode;
	

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getDailySettlementFlag() {
		return dailySettlementFlag;
	}

	public void setDailySettlementFlag(Integer dailySettlementFlag) {
		this.dailySettlementFlag = dailySettlementFlag;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	/*public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}*/

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
