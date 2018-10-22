package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.io.Serializable;

import com.rograndec.feijiayun.chain.common.vo.PageVO;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberIntegralExchangeParamVO", description = "积分兑换报表页面参数")
public class MemberIntegralExchangeParamVO extends PageVO implements Serializable {

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月16日 下午3:29:52 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "会员卡号/姓名/手机")
	private String param;
	
	@ApiModelProperty(value = "销售门店类型,药店类型（0-总部；1-自营店；2-加盟店）")
	private int chainType;
	
	@ApiModelProperty(value = "销售门店编码")
	private String enterpriseCode;
	
	@ApiModelProperty(value = "销售门店名称")
	private String enterpriseName;
	
	@ApiModelProperty(value = "起始时间,yyyy-MM-dd")
	private String startTime;
	
	@ApiModelProperty(value = "结束时间,yyyy-MM-dd")
	private String endTime;
	
	@ApiModelProperty(value = "积分变动类型（0-充值；1-扣款；2-转账；3-转入；4-开卡；5-销售付款；6-销退收款；10-全部）")
	private Integer changeType;
	
	

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = StringUtil.trimStr(param);
	}

	public int getChainType() {
		return chainType;
	}

	public void setChainType(int chainType) {
		this.chainType = chainType;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = StringUtil.trimStr(enterpriseCode);
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = StringUtil.trimStr(enterpriseName);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = StringUtil.trimStr(startTime);
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = StringUtil.trimStr(endTime);
	}

	public Integer getChangeType() {
		return changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	
}
