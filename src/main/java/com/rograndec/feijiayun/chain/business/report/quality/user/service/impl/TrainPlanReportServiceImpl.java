package com.rograndec.feijiayun.chain.business.report.quality.user.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.common.constant.ExamineMethod;
import com.rograndec.feijiayun.chain.common.constant.ExamineResult;
import com.rograndec.feijiayun.chain.common.constant.TrainContent;
import com.rograndec.feijiayun.chain.common.constant.TrainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.basic.user.dao.TrainPlanMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.TrainPlan;
import com.rograndec.feijiayun.chain.business.report.quality.user.service.TrainPlanReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.RequestTraimPlanVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseTrainPlanDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseTrainPlanVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.TrainPlanExcelComponent;
import com.rograndec.feijiayun.chain.common.component.TrainPlanRecordExcelComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class TrainPlanReportServiceImpl implements TrainPlanReportService {
	
	@Autowired
	TrainPlanMapper trainPlanMapper;
	
	@Autowired
	TrainPlanExcelComponent<ResponseTrainPlanVO> trainPlanExcelComponent;
	@Autowired
	TrainPlanRecordExcelComponent<ResponseTrainPlanDetailVO> trainPlanRecordExcelComponent;
	
	@Override
	public Page<List<ResponseTrainPlanVO>> getTrainPlanReport(Long enterpriseId,Date startTime,
			Date endTime, Integer chainType, String enterpriseCode, String enterpriseName, String code,
			String plannerName, Integer planYear, String planTitle, Integer trainType, Integer trainContent,
			Integer enterpriseCodeOrder, Integer planDateOrder, Page<List<ResponseTrainPlanVO>> page, UserVO user,Integer sign) {
		Map<String,Object> map=new HashMap<>();
		map.put("start", page.getStart());
		map.put("pageSize", page.getPageSize());
		map.put("enterpriseId", enterpriseId);
		map.put("zongbu", user.getChainType());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("chainType", chainType);
		map.put("enterpriseCode", enterpriseCode);
		map.put("enterpriseName", enterpriseName);
		map.put("code", code);
		map.put("plannerName", plannerName);
		map.put("planYear", planYear);
		map.put("planTitle", planTitle);
		map.put("trainType", trainType);
		map.put("trainContent", trainContent);
		map.put("enterpriseCodeOrder", enterpriseCodeOrder);
		map.put("planDateOrder", planDateOrder);
		map.put("sign", sign);
		Integer totalRecord=trainPlanMapper.getTrainPlanReportTotalNum(map);
		if(totalRecord==0){
			page.setTotalRecord(totalRecord);
			page.setResult(new ArrayList<ResponseTrainPlanVO>());
			return page;
		}
		page.setTotalRecord(totalRecord);
		List<ResponseTrainPlanVO> list= trainPlanMapper.getTrainPlanReport(map);
		if(!list.isEmpty()){
			for(ResponseTrainPlanVO responseTrainPlanVO : list){
				responseTrainPlanVO.setTrainContentName(TrainContent.getValue(responseTrainPlanVO.getTrainContent()));
				responseTrainPlanVO.setTrainTypeName(TrainType.getValue(responseTrainPlanVO.getTrainType()));
			}
		}
		page.setResult(list);
		return page;
	}
	
	@Override
	public void exportPlanExcel(Date startTime, Date endTime,
			Integer chainType, String enterpriseCode, String enterpriseName, String code, String plannerName,
			Long planYear, String planTitle, Long trainType, Long trainContent, Integer enterpriseCodeOrder,
			Integer planDateOrder, UserVO user,OutputStream output) {
		Map<String,Object> map=new HashMap<>();
		map.put("enterpriseId", user.getEnterpriseId());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("chainType", chainType);
		map.put("enterpriseCode", enterpriseCode);
		map.put("enterpriseName", enterpriseName);
		map.put("code", code);
		map.put("plannerName", plannerName);
		map.put("planYear", planYear);
		map.put("planTitle", planTitle);
		map.put("trainType", trainType);
		map.put("trainContent", trainContent);
		map.put("enterpriseCodeOrder", enterpriseCodeOrder);
		map.put("planDateOrder", planDateOrder);
		List<ResponseTrainPlanVO> list= trainPlanMapper.getTrainPlanReport(map);
		if(!list.isEmpty()){
			for(ResponseTrainPlanVO responseTrainPlanVO : list){
				responseTrainPlanVO.setTrainContentName(TrainContent.getValue(responseTrainPlanVO.getTrainContent()));
				responseTrainPlanVO.setTrainTypeName(TrainType.getValue(responseTrainPlanVO.getTrainType()));
			}
		}
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		if(user.getChainType()==0){
			rowHeaderList.put("enterpriseCode", "组织机构编码");
			rowHeaderList.put("enterpriseName", "组织机构名称");
		}
		rowHeaderList.put("planDate", "日期");
		rowHeaderList.put("code", "编号");
		rowHeaderList.put("plannerName", "计划人员");
		rowHeaderList.put("planYear", "年度");
		rowHeaderList.put("planTitle", "标题");
		rowHeaderList.put("startDate", "开始日期");
		rowHeaderList.put("endDate", "结束日期");
		rowHeaderList.put("trainTypeName", "培训类型");
		rowHeaderList.put("trainContentName", "培训内容");
		trainPlanExcelComponent.commExcelExport(output, rowHeaderList, list, user);
	}

	@Override
	public List<ResponseTrainPlanDetailVO> getTrainRecordReportDtlList(Long id,Integer start,Integer pageSize,UserVO user) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		map.put("start", start);
		map.put("pageSize", pageSize);
		map.put("enterpriseId", user.getEnterpriseId());
		List<ResponseTrainPlanDetailVO> responseTrainPlanDetailVOList = trainPlanMapper.getTrainRecordReportDtlList(map);
		if(!responseTrainPlanDetailVOList.isEmpty()){
			for(ResponseTrainPlanDetailVO responseTrainPlanDetailVO : responseTrainPlanDetailVOList){
				responseTrainPlanDetailVO.setExamineMethodName(ExamineMethod.getValue(responseTrainPlanDetailVO.getExamineMethod()));
				responseTrainPlanDetailVO.setExamineResultName(ExamineResult.getValue(responseTrainPlanDetailVO.getExamineResult()));
			}
		}
		return responseTrainPlanDetailVOList;
	}

	@Override
	public Integer getTrainRecordReportDtlListTotalNum(Long id, UserVO user) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		map.put("enterpriseId", user.getEnterpriseId());
		return trainPlanMapper.getTrainRecordReportDtlListTotalNum(map);
	}

	@Override
	public void exportPlanRecordDetlExcel(UserVO user, OutputStream output, Long id) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		map.put("enterpriseId", user.getEnterpriseId());
		List<ResponseTrainPlanDetailVO> list=trainPlanMapper.getTrainRecordReportDtlList(map);
		TrainPlan trainPlan=trainPlanMapper.selectByPrimaryKey(id);
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		rowHeaderList.put("deptNames", "部门");
		rowHeaderList.put("positionNames", "岗位");
		rowHeaderList.put("userName", "员工");
		rowHeaderList.put("trainLessonHour", "培训课时");
		rowHeaderList.put("trainPerformance", "培训表现");
		rowHeaderList.put("examineItem", "考核项目");
		rowHeaderList.put("examineMethodName", "考核方式");
		rowHeaderList.put("examineTime", "考核时间");
		rowHeaderList.put("examineResultName", "考核结果");
		rowHeaderList.put("measures", "采取措施");
		rowHeaderList.put("remark", "备注");
		trainPlanRecordExcelComponent.commExcelExport(output, rowHeaderList, list, user, trainPlan);
	}

	@Override
	public void exportPlanExcelNew(RequestTraimPlanVO requestTraimPlanVO, UserVO user, OutputStream output) {
		Map<String,Object> map=new HashMap<>();
		map.put("enterpriseId", user.getEnterpriseId());
		map.put("startTime", requestTraimPlanVO.getStartTime());
		map.put("endTime", requestTraimPlanVO.getEndTime());
		map.put("chainType", requestTraimPlanVO.getChainType());
		map.put("enterpriseCode", requestTraimPlanVO.getEnterpriseCode());
		map.put("enterpriseName", requestTraimPlanVO.getEnterpriseName());
		map.put("code", requestTraimPlanVO.getCode());
		map.put("plannerName", requestTraimPlanVO.getPlannerName());
		map.put("planYear", requestTraimPlanVO.getPlanYear());
		map.put("planTitle", requestTraimPlanVO.getPlanTitle());
		map.put("trainType", requestTraimPlanVO.getTrainType());
		map.put("trainContent", requestTraimPlanVO.getTrainContent());
		map.put("enterpriseCodeOrder", requestTraimPlanVO.getEnterpriseCodeOrder());
		map.put("planDateOrder", requestTraimPlanVO.getPlanDateOrder());
		List<ResponseTrainPlanVO> list= trainPlanMapper.getTrainPlanReport(map);
		if(!list.isEmpty()){
			for(ResponseTrainPlanVO responseTrainPlanVO : list){
				responseTrainPlanVO.setTrainContentName(TrainContent.getValue(responseTrainPlanVO.getTrainContent()));
				responseTrainPlanVO.setTrainTypeName(TrainType.getValue(responseTrainPlanVO.getTrainType()));
			}
		}
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		if(user.getChainType()==0){
			rowHeaderList.put("enterpriseCode", "组织机构编码");
			rowHeaderList.put("enterpriseName", "组织机构名称");
		}
		rowHeaderList.put("planDate", "日期");
		rowHeaderList.put("code", "编号");
		rowHeaderList.put("plannerName", "计划人员");
		rowHeaderList.put("planYear", "年度");
		rowHeaderList.put("planTitle", "标题");
		rowHeaderList.put("startDate", "开始日期");
		rowHeaderList.put("endDate", "结束日期");
		rowHeaderList.put("trainTypeName", "培训类型");
		rowHeaderList.put("trainContentName", "培训内容");
		trainPlanExcelComponent.commExcelExport(output, rowHeaderList, list, user);
		
	}
	
}
