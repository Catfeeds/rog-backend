package com.rograndec.feijiayun.chain.business.report.member.vo;

import javax.validation.constraints.NotNull;

import com.rograndec.feijiayun.chain.common.vo.PageVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: MemberRankingVO   
 * @Description: 会员排行参数
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月19日 上午11:05:29
 */
@ApiModel(value = "MemberRankingParamVO", description = "会员排行参数")
public class MemberRankingParamVO extends PageVO{
	
	@NotNull(message="搜索类型不能为空！")
	@ApiModelProperty(value = "搜索类型：1=按销售金额，2=按累计积分，3=按当前积分，4=按累计储值，5=按储值余额")
	private int type = 1;
	@ApiModelProperty(value = "TOP显示多少数据",hidden=true)
	private Long top;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Long getTop() {
		return top;
	}
	public void setTop(Long top) {
		this.top = top;
	}
	
}
