package com.rograndec.feijiayun.chain.business.report.member.vo;

import com.rograndec.feijiayun.chain.common.vo.PageVO;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: MemberRankingVO   
 * @Description: 会员活跃度参数
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月19日 上午11:05:29
 */
@ApiModel(value = "MemberLivenessParamVO", description = "会员活跃度参数")
public class MemberLivenessParamVO extends PageVO{
	
	@ApiModelProperty(value = "起始时间,yyyy-MM-dd")
	private String startTime;
	
	@ApiModelProperty(value = "结束时间,yyyy-MM-dd")
	private String endTime;
	
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
	
}
