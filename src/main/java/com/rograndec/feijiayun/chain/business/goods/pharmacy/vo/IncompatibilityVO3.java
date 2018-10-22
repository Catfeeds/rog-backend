package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/9.
 */

public class IncompatibilityVO3 implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(required = true, value = "ID")
    private Long id;

    /**
     * 种类
     */
    @ApiModelProperty(required = true, value = "种类")
    private String varity;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
    private Integer status;
    
    private Long enterpriseId;
    
    /**
     * 加盟店可编辑标识（true-可编辑 false-不可编辑）
     */
    @ApiModelProperty(required = true, value = "加盟店可编辑标识 true-可编辑 false-不可编辑")
    private boolean updateFlag=false;


    private List<IncompatibilityVO2> goodsList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVarity() {
        return varity;
    }

    public void setVarity(String varity) {
        this.varity = varity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<IncompatibilityVO2> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<IncompatibilityVO2> goodsList) {
        this.goodsList = goodsList;
    }

    public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	@Override
    public String toString() {
        return "IncompatibilityVO3[" +
                "id=" + id +
                ", varity='" + varity + '\'' +
                ", status=" + status +
                ", goodsList=" + goodsList +
                ']';
    }
}
