package com.rograndec.feijiayun.chain.business.report.finance.account.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AccountsDtlGroupCodeVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AccountsDtlVO;
import com.rograndec.feijiayun.chain.common.Page;

/**
 * 
 * @ClassName: AccountsDtlServiceImpl   
 * @Description: 应付/应收账款明细账分组数据
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月12日 下午2:02:43
 */
public interface AccountsDtlService {
	
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
	Page<List<AccountsDtlVO>> getAccountsDtl(Map<String,Object> map) throws Exception;
	
	
	/**
	 * 
	 * @Description: 分组数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月16日 下午1:19:54 
	 * @param map
	 * @return
	 * @throws Exception 
	 * @return List<AccountsDtlGroupCodeVO>
	 */
	List<AccountsDtlGroupCodeVO> groupData(Map<String,Object> map) throws Exception;
	
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
