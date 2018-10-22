package com.rograndec.feijiayun.chain.business.report.member.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.member.vo.MemberAmountRecordTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberBirthDateParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberBirthDateVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralDtlVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberIntegralExchangeTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberLivenessParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberLivenessTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberRankingParamVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberRankingTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberSaleTotalVO;
import com.rograndec.feijiayun.chain.business.report.member.vo.MemberStoredAmountDtlVO;
import com.rograndec.feijiayun.chain.common.Page;

public interface MemberReportService {
	
	Page<MemberIntegralExchangeTotalVO> getIntegralExchange(MemberIntegralExchangeParamVO param);
	
	/**
     * 导出
     * @param output
     * @param id
     */
    void exportExcel(OutputStream output,MemberIntegralExchangeParamVO param) throws Exception;
    
    /**
     * 
     * @Description: 会员销售报表
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月17日 上午11:49:29 
     * @param param
     * @return
     * @throws Exception 
     * @return Page<List<MemberSaleTotalVO>>
     */
    Page<MemberSaleTotalVO> getMemberSale(MemberIntegralExchangeParamVO param) throws Exception;
    void exportMemberSaleExcel(OutputStream output,MemberIntegralExchangeParamVO param) throws Exception;
    
    /**
     * 
     * @Description: 积分明细报表
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月17日 下午5:08:37 
     * @param param
     * @return
     * @throws Exception 
     * @return Page<List<MemberSaleTotalVO>>
     */
    Page<List<MemberIntegralDtlVO>> getIntegralDtl(MemberIntegralExchangeParamVO param) throws Exception;
    void exportIntegralDtl(OutputStream output,MemberIntegralExchangeParamVO param) throws Exception;
    
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
    Page<MemberAmountRecordTotalVO> getStoredAmount(MemberIntegralExchangeParamVO param) throws Exception;
    void exportMemberAmountRecord(OutputStream output,MemberIntegralExchangeParamVO param) throws Exception;
    
    
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
    Page<List<MemberStoredAmountDtlVO>> getStoredAmountDtl(MemberIntegralExchangeParamVO param) throws Exception;
    void exportStoredAmountDtl(OutputStream output,MemberIntegralExchangeParamVO param) throws Exception;
    
    /**
     * 
     * @Description: 会员排行
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月19日 下午2:12:10 
     * @param param
     * @return
     * @throws Exception 
     * @return Page<List<MemberRankingVO>>
     */
    Page<MemberRankingTotalVO> getMemberRanking(MemberRankingParamVO param) throws Exception;
    void exportMemberRanking(OutputStream output,MemberRankingParamVO param) throws Exception;
    
    /**
     * 
     * @Description: 会员活跃
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月19日 下午5:40:58 
     * @param param
     * @return
     * @throws Exception 
     * @return Page<List<MemberLivenessTotalVO>>
     */
    Page<MemberLivenessTotalVO> getMemberLiveness(MemberLivenessParamVO param) throws Exception;
    void exportMemberLiveness(OutputStream output,MemberLivenessParamVO param) throws Exception;
    
    /**
     * 
     * @Description: 会员生日
     * @author yuting.li
     * @version 1.0 
     * @date 2017年10月20日 下午2:03:45 
     * @param param
     * @return
     * @throws Exception 
     * @return Page<List<MemberBirthDateVO>>
     */
    Page<List<MemberBirthDateVO>> getMemberBirthDate(MemberBirthDateParamVO param) throws Exception;
    void exportMemberBirthDate(OutputStream output,MemberBirthDateParamVO param) throws Exception;
}

