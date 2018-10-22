package com.rograndec.feijiayun.chain.business.report.finance.account.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AgingVO;

/**
 * 
 * @ClassName: AgingYFReportDao   
 * @Description: 应付账龄分析
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月15日 下午4:35:16
 */
public interface AgingYFReportMapper {
	
	List<AgingVO> getAgingYFDtl(Map<String,Object> map);
	Integer countAgingYFDtl(Map<String,Object> map);

}
