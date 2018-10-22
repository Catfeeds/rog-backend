package com.rograndec.feijiayun.chain.business.finance.set.period.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.common.constant.status.PeriodStatus;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdong.zhang
 * 2018-01-17
 */
public class AccountingFreshVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
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
     * 会计年度
     */
    @ApiModelProperty(value = "会计年度")
    @NotNull(message = "会计年度必填")
    private Integer year;
    
    /**
     * 开始日期
     */
    @ApiModelProperty(value = "开始日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    /**
     * 状态（0-已激活；1-已结账）
     */
    @ApiModelProperty(value = "状态（0-已激活；1-已结账）")
    private Integer status;
    
    /**
     * 状态（0-已激活；1-已结账）
     */
    @ApiModelProperty(value = "状态（0-已激活；1-已结账）")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    @NotNull(message = "详情不能为空")
    private List<AccountingPeriodFreshVO> detailList;
    
    /**
     * 判断是否可编辑或是删除
     */
    @ApiModelProperty(value = "判断是否可编辑或是删除")
    private boolean updateFlag = false;
    
    /**
     * 判断是否可删除
     */
    @ApiModelProperty(value = "判断是否可删除")
    private boolean deleteFlag = false;
    
    /**
     * 判断备注是否可编辑
     */
    @ApiModelProperty(value = "判断备注是否可编辑")
    private boolean remarkFlag = false;
    /**
     * saas_accounting_period
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

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
     * 状态（0-未激活；1-年度期间；2-已结账）
     * @return status 状态（0-未激活；1-年度期间；2-已结账）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-未激活；1-年度期间；2-已结账）
     * @param status 状态（0-未激活；1-年度期间；2-已结账）
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

	public List<AccountingPeriodFreshVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<AccountingPeriodFreshVO> detailList) {
		this.detailList = detailList;
	}

	public String getStatusName() {
		
		return PeriodStatus.getName(status);
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public boolean isRemarkFlag() {
		return remarkFlag;
	}

	public void setRemarkFlag(boolean remarkFlag) {
		this.remarkFlag = remarkFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}