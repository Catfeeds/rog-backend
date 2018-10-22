package com.rograndec.feijiayun.chain.business.report.member.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberStoredAmountDtlVO;

public interface StoredAmountRecordReportMapper {
	
	/**
	 * 
	 * @Description: 储值明细报表
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月18日 下午5:33:57 
	 * @param param
	 * @return 
	 * @return List<MemberStoredAmountDtlVO>
	 */
	List<MemberStoredAmountDtlVO> getStoredAmountDtl(MemberIntegralExchangeParamVO param);
	Integer countStoredAmountDtl(MemberIntegralExchangeParamVO param);
}
