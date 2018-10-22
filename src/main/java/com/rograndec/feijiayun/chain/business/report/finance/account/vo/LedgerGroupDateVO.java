package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: LedgerVO   
 * @Description: 应付/应收总账显示
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月8日 下午4:08:19
 */
@ApiModel(value = "LedgerGroupDateVO", description = "财务管理-应付/应收账款-应付/应收总账显示")
public class LedgerGroupDateVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2018年1月8日 下午4:08:16 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "日期")
	private String orderDate;
	
	@ApiModelProperty(value = "按日期分组的数据")
	private List<LedgerVO> list;

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public List<LedgerVO> getList() {
		return list;
	}

	public void setList(List<LedgerVO> list) {
		this.list = list;
	}

	
}
