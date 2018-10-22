package com.rograndec.feijiayun.chain.business.report.finance.account.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AgingVO;

/**
 * 
 * @ClassName: AgingYFReportDao   
 * @Description: 应收账龄分析
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月15日 下午4:35:16
 */
public interface AgingYSReportMapper {
	
	List<AgingVO> getAgingYSDtl(Map<String,Object> map);
	Integer countAgingYSDtl(Map<String,Object> map);
	
}
