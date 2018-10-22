package com.rograndec.feijiayun.chain.business.report.member.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralDtlVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeParamVO;

/**
 * 
 * @ClassName: MemberIntegralDtlReportMapper   
 * @Description: 积分兑换明细报表
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月17日 下午5:16:11
 */
public interface MemberIntegralDtlReportMapper {
	
	List<MemberIntegralDtlVO> getIntegralDtl(MemberIntegralExchangeParamVO param);
	Integer countIntegralDtl(MemberIntegralExchangeParamVO param);
}
