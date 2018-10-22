package com.rograndec.feijiayun.chain.business.report.finance.account.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.FinanceBalanceTotalVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.FinanceBalanceVO;

public interface FinanceBalanceReportMapper {
	
	/**
	 * 
	 * @Description: 应付账款/应收账款数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月5日 上午11:01:42 
	 * @param map
	 * @return 
	 * @return List<FinanceBalanceVO>
	 */
	List<FinanceBalanceVO> getFinanceBalance(Map<String,Object> map);
	Integer countFinanceBalance(Map<String,Object> map);
	FinanceBalanceTotalVO sumFinanceBalance(Map<String,Object> map);
}
