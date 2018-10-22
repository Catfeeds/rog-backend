package com.rograndec.feijiayun.chain.business.finance.set.period.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdong.zhang
 * 2018-01-04
 */
@ApiModel
public class RequestAccountingPeriodVO implements Serializable {
	
	@ApiModelProperty(value = "会计年度修改时必填id")
	private Long id;

    /**
     * 会计年度
     */
    @ApiModelProperty(value = "会计年度")
    @NotNull(message = "会计年度不能为空")
    @Min(value = 1970)
    @Max(value = 9999)
    private Integer year;

    /**
     * 状态（0-已激活；1-已结账）
     */
    @ApiModelProperty(value = "状态（0-已激活；1-已结账）")
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 年度信息详情
     */
    @ApiModelProperty(value = "年度信息详情")
    private List<RequestAccountingPeriodDetailVO> detailList;


    /**
     * saas_accounting_period
     */
    private static final long serialVersionUID = 1L;


    /**
     * 会计年度
     * @return year 会计年度
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 会计年度
     * @param year 会计年度
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 状态（0-已激活；1-已结账））
     * @return status 状态（0-已激活；1-已结账））
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-已激活；1-已结账））
     * @param status 状态（0-已激活；1-已结账））
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public List<RequestAccountingPeriodDetailVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<RequestAccountingPeriodDetailVO> detailList) {
		this.detailList = detailList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}