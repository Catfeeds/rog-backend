package com.rograndec.feijiayun.chain.business.distr.branch.vo;


import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 * Created by ddz on 2017/10/11 14:46
 */

public class DistrInReturnParamVO {
	
	@ApiModelProperty("0 - 配进退出；1-配进退出出库")
	private Integer distrFlag = 0;

    @ApiModelProperty("开始日期")
    private Date startDate;

    @ApiModelProperty("结束日期")
    private Date endDate;

    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;

    @ApiModelProperty("日期排序 0/倒序；1/正序;默认0，查询入库单列表时使用")
    private Integer dateOrder=0;

    @ApiModelProperty("单号排序 0/倒序；1/正序;默认0")
    private Integer codeOrder=1;

    @ApiModelProperty("行号排序 0/倒序；1/正序;默认0，查询入库单详情列表时使用")
    private Integer lineNumOrder;

    @ApiModelProperty("入库单(要货计划单)id,查询入库单详情列表时使用")
    private Long distrInId;
    @ApiModelProperty("不用传")
    private Long enterpriseId;
    
    @ApiModelProperty("不用传")
    private Integer start = 0;	 //起始条
    
    private Integer distributionManage;
    
    private List<Integer> distrTypes;
    
    private List<Integer> orderStatus;
    
    private Integer distributionHead;

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

    public Integer getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    public Integer getLineNumOrder() {
        return lineNumOrder;
    }

    public void setLineNumOrder(Integer lineNumOrder) {
        this.lineNumOrder = lineNumOrder;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Integer dateOrder) {
        this.dateOrder = dateOrder;
    }

	public Long getDistrInId() {
		return distrInId;
	}

	public void setDistrInId(Long distrInId) {
		this.distrInId = distrInId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getDistributionManage() {
		return distributionManage;
	}

	public void setDistributionManage(Integer distributionManage) {
		this.distributionManage = distributionManage;
	}

	public Integer getDistrFlag() {
		return distrFlag;
	}

	public void setDistrFlag(Integer distrFlag) {
		this.distrFlag = distrFlag;
	}

	public List<Integer> getDistrTypes() {
		return distrTypes;
	}

	public void setDistrTypes(List<Integer> distrTypes) {
		this.distrTypes = distrTypes;
	}

	public List<Integer> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(List<Integer> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getDistributionHead() {
		return distributionHead;
	}

	public void setDistributionHead(Integer distributionHead) {
		this.distributionHead = distributionHead;
	}
    
}