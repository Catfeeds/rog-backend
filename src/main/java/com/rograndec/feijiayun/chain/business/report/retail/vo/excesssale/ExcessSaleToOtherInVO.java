package com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherInDetailVO;

public class ExcessSaleToOtherInVO implements Serializable{
	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "入库日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date inDate;

    @ApiModelProperty(value = "入库人员Id")
    private Long inUserId;
    
    @ApiModelProperty(value = "入库人员名称")
    private String inUserName;
    
    @ApiModelProperty(value = "入库类型")
    private Integer inType;
    
    @ApiModelProperty(value = "入库类型")
    private String inTypeName;
    
    @ApiModelProperty(value = "来源单位Id")
    private Long storeId;
    
    @ApiModelProperty(value = "来源单位名称")
    private String StoreName;
    
    private List<OtherInDetailVO> detailList;

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Long getInUserId() {
		return inUserId;
	}

	public void setInUserId(Long inUserId) {
		this.inUserId = inUserId;
	}

	public String getInUserName() {
		return inUserName;
	}

	public void setInUserName(String inUserName) {
		this.inUserName = inUserName;
	}

	public Integer getInType() {
		return inType;
	}

	public void setInType(Integer inType) {
		this.inType = inType;
	}

	public String getInTypeName() {
		return inTypeName;
	}

	public void setInTypeName(String inTypeName) {
		this.inTypeName = inTypeName;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}

	public List<OtherInDetailVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OtherInDetailVO> detailList) {
		this.detailList = detailList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
