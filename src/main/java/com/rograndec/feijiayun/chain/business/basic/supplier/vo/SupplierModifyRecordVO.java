package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import java.io.Serializable;
import java.util.Date;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierModifyRecord;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.ApiModelProperty;

public class SupplierModifyRecordVO implements Serializable{

	
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
     * 供货单位ID
     */
	@ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

 
    /**
     * 修改字段中文名称（修改项目）
     */
	@ApiModelProperty(value = "修改字段中文名称（修改项目）")
    private String columnChName;

    /**
     * 原内容
     */
	@ApiModelProperty(value = "原内容")
    private String oldContent;

    /**
     * 新内容
     */
	@ApiModelProperty(value = "新内容")
    private String newContent;

    /**
     * 创建人ID
     */
	@ApiModelProperty(value = "修改人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
	@ApiModelProperty(value = "修改人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
	@ApiModelProperty(value = "修改人名称")
    private String createrName;

    /**
     * 修改时间
     */
	@ApiModelProperty(value = "修改时间")
    private Date updateTime;

    
    /**
     * 备注
     */
	@ApiModelProperty(value = "备注[修改原因]")
    private String remark;


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


	public Long getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}


	public String getColumnChName() {
		return columnChName;
	}


	public void setColumnChName(String columnChName) {
		this.columnChName = columnChName;
	}


	public String getOldContent() {
		return oldContent;
	}


	public void setOldContent(String oldContent) {
		this.oldContent = oldContent;
	}


	public String getNewContent() {
		return newContent;
	}


	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}




	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(String createrCode) {
		this.createrCode = createrCode;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	@Override
	public String toString() {
		return "SupplierModifyRecordVO{" +
				"id=" + id +
				", enterpriseId=" + enterpriseId +
				", supplierId=" + supplierId +
				", columnChName='" + columnChName + '\'' +
				", oldContent='" + oldContent + '\'' +
				", newContent='" + newContent + '\'' +
				", createrId=" + createrId +
				", createrCode='" + createrCode + '\'' +
				", createrName='" + createrName + '\'' +
				", updateTime=" + updateTime +
				", remark='" + remark + '\'' +
				'}';
	}

	public static SupplierModifyRecordVO changeRecordToVo(SupplierModifyRecord s, UserVO user) {
		SupplierModifyRecordVO vo = new SupplierModifyRecordVO();
		vo.setId(s.getId());
	    vo.setEnterpriseId(s.getEnterpriseId());
	    vo.setSupplierId(s.getSupplierId());
	    vo.setColumnChName(s.getColumnChName());
	    vo.setOldContent(s.getOldContent());
	    vo.setNewContent(s.getNewContent());
	    vo.setRemark(s.getRemark());
	    vo.setCreaterId(s.getCreaterId());
	    vo.setCreaterCode(s.getCreaterCode());
	    vo.setCreaterName(s.getCreaterName());
	    vo.setUpdateTime(s.getUpdateTime());
		return vo;
	}

    

	
}
