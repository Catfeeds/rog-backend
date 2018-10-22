package com.rograndec.feijiayun.chain.business.retail.special.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * <零售管理-专管登记-待登记销售单列表查询param>
 *
 * @Author: Zhengbin.jin 金正斌
 * @Email: Zhengbin.jin@rograndec.com
 * @2017/9/22 18:11
 */
public class RequestSaleVo {
    @ApiModelProperty(value = "每页记录数", required = true)
    private Integer pageSize;
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;
    @ApiModelProperty(value = "查询日期起始", required = false)
    private String startDate;
    @ApiModelProperty(value = "查询日期结束", required = false)
    private String endDate;
    
    private Long enterpriseId;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
    
    
    
}
