package com.rograndec.feijiayun.chain.business.report.quality.user.service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


public interface HealthCheckReportService {

	List<ResponseHealthCheckVO> getHealthCheckReportList(int start,int pageSize, Integer chainType,String enterpriseCode,
			String enterpriseName,String code,String planManName,String planYear,String checkType,Integer enterpriseCodeOrder,
			Integer planDateOrder,UserVO user,Date startTime,Date endTime);
	
	Integer getHealthCheckReportListTotalNum(Integer chainType,String enterpriseCode,
			String enterpriseName,String code,String planManName,String planYear,String checkType,Integer enterpriseCodeOrder,
			Integer planDateOrder,UserVO user,Date startTime,Date endTime);
	
	List<ResponseHealthCheckDetailVO> getHealthCheckReportDetlList(Long id,Integer start,Integer pageSize,UserVO user);
	
	Integer getHealthCheckReportDetlListTotalNum(Long id,UserVO user);
	
	void exportHealthCheckReportDetlListExcel(UserVO user,OutputStream output,Long id);
}
