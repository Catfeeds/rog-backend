package com.rograndec.feijiayun.chain.business.report.finance.account.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerVO;

public interface LedgerReportMapper {
	
	/**
	 * 
	 * @Description: 应付/应收总账
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月8日 下午6:00:01 
	 * @param map
	 * @return 
	 * @return List<LedgerVO>
	 */
	List<LedgerVO> getLedger(Map<String,Object> map);
	Integer countLedger(Map<String,Object> map);
	
	/**
	 * 
	 * @Description: 获取期初余额数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月30日 下午8:46:14 
	 * @param minId
	 * @return 
	 * @return Map<String,BigDecimal>
	 */
	Map<String,BigDecimal> getMinId(Long minId);
	
	/**
	 * 
	 * @Description: 获取期末余额
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月30日 下午8:46:33 
	 * @param minId
	 * @return 
	 * @return Map<String,BigDecimal>
	 */
	Map<String,BigDecimal> getMaxId(Long maxId);
}
