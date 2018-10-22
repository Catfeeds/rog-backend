package com.rograndec.feijiayun.chain.business.report.finance.account.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerGroupDateVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerVO;
import com.rograndec.feijiayun.chain.common.Page;

/**
 * 
 * @ClassName: LedgerServiceImpl   
 * @Description: 应付/应收总账显示
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月8日 下午5:03:47
 */
public interface LedgerService {
	
	/**
	 * 
	 * @Description: 分页获取应付/应收总账数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月8日 下午8:13:45 
	 * @param map
	 * @return
	 * @throws Exception 
	 * @return Page<LedgerVO>
	 */
	Page<List<LedgerVO>> getLedger(Map<String,Object> map) throws Exception;
	
	/**
	 * 
	 * @Description: 导出应付/应收总账数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月8日 下午8:14:06 
	 * @param output
	 * @param map
	 * @throws Exception 
	 * @return void
	 */
	void exportExcel(OutputStream output,Map<String,Object> map) throws Exception;
	
	/**
	 * 
	 * @Description: 打印分组的数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月16日 上午11:04:21 
	 * @param map
	 * @return
	 * @throws Exception 
	 * @return List<LedgerGroupDateVO>
	 */
	List<LedgerGroupDateVO> groupData(Map<String,Object> map) throws Exception;
	
}
