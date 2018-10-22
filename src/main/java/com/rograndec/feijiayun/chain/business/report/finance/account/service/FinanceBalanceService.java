package com.rograndec.feijiayun.chain.business.report.finance.account.service;

import java.io.OutputStream;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.FinanceBalanceTotalVO;
import com.rograndec.feijiayun.chain.common.Page;

/**
 * 
 * @ClassName: FinanceBalanceService   
 * @Description: 应付账款/应收账款接口
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月5日 下午1:32:29
 */
public interface FinanceBalanceService {
	
	/**
	 * 
	 * @Description: 分页获取数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月5日 下午1:33:06 
	 * @param map
	 * @return 
	 * @return Page<FinanceBalanceTotalVO>
	 */
	Page<FinanceBalanceTotalVO> getBalanceList(Map<String,Object> map) throws Exception;
	
	/**
	 * 
	 * @Description: 导出数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月5日 下午1:33:25 
	 * @param map 
	 * @return void
	 */
	void exportExcel(OutputStream output,Map<String,Object> map) throws Exception;
	

}
