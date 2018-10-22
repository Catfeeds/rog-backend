package com.rograndec.feijiayun.chain.business.report.quality.user.service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.user.vo.RequestTraimPlanVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseTrainPlanDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseTrainPlanVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


public interface TrainPlanReportService {
	
	Page<List<ResponseTrainPlanVO>> getTrainPlanReport(Long enterpriseId,Date startTime,Date endTime,Integer chainType,String enterpriseCode,
		     String enterpriseName,String code,String plannerName,Integer planYear,String planTitle,Integer trainType,Integer trainContent,
	         Integer enterpriseCodeOrder,Integer planDateOrder,Page<List<ResponseTrainPlanVO>> page, UserVO user,Integer sign);
	
	void exportPlanExcel(Date startTime,Date endTime,Integer chainType,String enterpriseCode,
		     String enterpriseName,String code,String plannerName,Long planYear,String planTitle,Long trainType,Long trainContent,
	         Integer enterpriseCodeOrder,Integer planDateOrder,UserVO user,OutputStream output);
	void exportPlanExcelNew(RequestTraimPlanVO requestTraimPlanVO,UserVO user,OutputStream output);
	
	List<ResponseTrainPlanDetailVO> getTrainRecordReportDtlList(Long id,Integer start,Integer pageSize,UserVO user);
	
	Integer getTrainRecordReportDtlListTotalNum(Long id,UserVO user);
	
	void exportPlanRecordDetlExcel(UserVO user,OutputStream output,Long id);

}
