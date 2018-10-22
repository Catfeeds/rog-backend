package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by doondong.zhang on 2017/12/5.
 */
public class DistrInRetReqDtlVO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 提示语句
     */
    @ApiModelProperty(value = "提示语句")
    private String message = "要货计划中存在大于库存可用量的商品，是否按库存可用量调配";
    
    /**
     * 是否弹窗标识  0 否    1 是
     */
    @ApiModelProperty(value = "是否弹窗标识  0 否    1 是")
    private Integer flag = 0;
    
    /**
     * 要货计划详情列表
     */
    @ApiModelProperty(value = "要货计划详情列表")
    private List<DistrInReqPlanDetailVO> DistrInReqPlanDetailVOs;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<DistrInReqPlanDetailVO> getDistrInReqPlanDetailVOs() {
		return DistrInReqPlanDetailVOs;
	}

	public void setDistrInReqPlanDetailVOs(List<DistrInReqPlanDetailVO> distrInReqPlanDetailVOs) {
		DistrInReqPlanDetailVOs = distrInReqPlanDetailVOs;
	}
    
}
