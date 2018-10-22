package com.rograndec.feijiayun.chain.business.report.member.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.report.member.vo.MemberAmountRecordTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberAmountRecordVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberBirthDateParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberBirthDateVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberLivenessParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberLivenessTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberLivenessVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberRankingParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberRankingTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberRankingVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberSaleTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberSaleVO;

/**
 * 
 * @ClassName: MemberReportMapper   
 * @Description: 会员报表模块
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月16日 下午1:58:48
 */
public interface MemberReportMapper {
	
	/**
	 * 
	 * @Description: 会员积分兑换
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月17日 上午11:06:22 
	 * @param param
	 * @return 
	 * @return List<MemberIntegralExchangeVO>
	 */
	List<MemberIntegralExchangeVO> getIntegralExchange(MemberIntegralExchangeParamVO param);
	MemberIntegralExchangeTotalVO sumIntegralExchange(MemberIntegralExchangeParamVO param);
	Integer countIntegralExchange(MemberIntegralExchangeParamVO param);
	
	/**
	 * 
	 * @Description: 会员销售报表
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月17日 上午11:46:58 
	 * @param param
	 * @return 
	 * @return List<MemberSaleVO>
	 */
	List<MemberSaleVO> getMemberSale(MemberIntegralExchangeParamVO param);
	MemberSaleTotalVO sumMemberSale(MemberIntegralExchangeParamVO param);
	Integer countMemberSale(MemberIntegralExchangeParamVO param);
	
	/***
	 * 
	 * @Description: 储值付款报表
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月18日 下午3:32:09 
	 * @param param
	 * @return 
	 * @return List<MemberAmountRecordVO>
	 */
	List<MemberAmountRecordVO> getStoredAmount(MemberIntegralExchangeParamVO param);
	MemberAmountRecordTotalVO sumStoredAmount(MemberIntegralExchangeParamVO param);
	Integer countStoredAmount(MemberIntegralExchangeParamVO param);
	
	/**
	 * 
	 * @Description: 会员排行
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月19日 下午2:07:59 
	 * @param param
	 * @return 
	 * @return List<MemberRankingVO>
	 */
	List<MemberRankingVO> getMemberRanking(MemberRankingParamVO param);
	MemberRankingTotalVO sumMemberRanking(MemberRankingParamVO param);
	Integer countMemberRanking(MemberRankingParamVO param);
	
	/**
	 * 
	 * @Description: 会员活跃
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月19日 下午5:39:13 
	 * @param param
	 * @return 
	 * @return List<MemberLivenessVO>
	 */
	List<MemberLivenessVO> getMemberLiveness(MemberLivenessParamVO param);
	MemberLivenessTotalVO sumMemberLiveness(MemberLivenessParamVO param);
	Integer countMemberLiveness(MemberLivenessParamVO param);
	
	/**
	 * 
	 * @Description: 会员生日
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月20日 下午2:01:49 
	 * @param param
	 * @return 
	 * @return List<MemberBirthDateVO>
	 */
	List<MemberBirthDateVO> getMemberBirthDate(MemberBirthDateParamVO param);
	Integer countMemberBirthDate(MemberBirthDateParamVO param);
	
}
