package com.rograndec.feijiayun.chain.business.report.finance.account.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AgingGroupVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AgingVO;
import com.rograndec.feijiayun.chain.common.Page;

/**
 * 
 * @ClassName: AgingService   
 * @Description: 财务管理-应付/应收账款-应付/应收账龄分析
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月15日 下午5:23:17
 */
public interface AgingService {
	
	/**
	 * 
	 * @Description: 应付/应收账龄
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月15日 下午5:23:12 
	 * @param map
	 * @return
	 * @throws Exception 
	 * @return Page<List<AgingVO>>
	 */
	Page<List<AgingVO>> getAging(Map<String,Object> map)throws Exception;
	void exportExcel(OutputStream output,Map<String,Object> map) throws Exception; 
	
	/**
	 * 
	 * @Description: 打印的数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月15日 下午9:07:15 
	 * @param list
	 * @return 
	 * @return List<AgingGroupVO>
	 */
	List<AgingGroupVO> groupData(Map<String,Object> map);
	
}
