package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: MemberIntegralRecordVO   
 * @Description: 会员报表-储值付款
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月16日 下午2:01:44
 */
@ApiModel(value = "MemberAmountRecordTotalVO", description = "储值付款报表列表")
public class MemberAmountRecordTotalVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月16日 下午3:46:38 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "销售金额")
    private BigDecimal amountTotal;
    
    @ApiModelProperty(value = "支付金额")
    private BigDecimal changeStoredAmountTotal;
    
    @ApiModelProperty(value = "储值付款list")
    private List<MemberAmountRecordVO> list;

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getChangeStoredAmountTotal() {
		return changeStoredAmountTotal;
	}

	public void setChangeStoredAmountTotal(BigDecimal changeStoredAmountTotal) {
		this.changeStoredAmountTotal = changeStoredAmountTotal;
	}

	public List<MemberAmountRecordVO> getList() {
		return list;
	}

	public void setList(List<MemberAmountRecordVO> list) {
		this.list = list;
	}


}
