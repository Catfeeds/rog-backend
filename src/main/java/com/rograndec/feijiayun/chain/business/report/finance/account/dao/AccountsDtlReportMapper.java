package com.rograndec.feijiayun.chain.business.report.finance.account.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AccountsDtlVO;

public interface AccountsDtlReportMapper {
	
	/**
	 * 
	 * @Description: 应付/应收账款明细账
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月12日 上午11:32:58 
	 * @param map
	 * @return 
	 * @return List<AccountsDtlVO>
	 */
	List<AccountsDtlVO> getAccountsDtl(Map<String,Object> map);
	Integer countAccountsDtl(Map<String,Object> map);
}
