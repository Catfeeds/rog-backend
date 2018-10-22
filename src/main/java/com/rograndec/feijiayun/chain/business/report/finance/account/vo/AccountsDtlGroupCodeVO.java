package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AccountsDtlGroupCodeVO", description = "财务管理-应付/应收账款-应付/应收账款明细账分组显示")
public class AccountsDtlGroupCodeVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2018年1月12日 下午1:23:00 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "供应商编码-供应商名称")
	private String subAccountCode;
	
	@ApiModelProperty(value = "供应商名称")
	private String subAccountName;
	
	@ApiModelProperty(value = "数据")
	private List<AccountsDtlVO> list;

	public String getSubAccountCode() {
		return subAccountCode;
	}

	public void setSubAccountCode(String subAccountCode) {
		this.subAccountCode = subAccountCode;
	}

	public String getSubAccountName() {
		return subAccountName;
	}

	public void setSubAccountName(String subAccountName) {
		this.subAccountName = subAccountName;
	}

	public List<AccountsDtlVO> getList() {
		return list;
	}

	public void setList(List<AccountsDtlVO> list) {
		this.list = list;
	}
	

}
