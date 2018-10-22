package com.rograndec.feijiayun.chain.business.report.quality.user.service.impl;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.basic.user.dao.HealthCheckMapper;
import com.rograndec.feijiayun.chain.business.report.quality.user.service.HealthCheckReportService;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckVO;
import com.rograndec.feijiayun.chain.common.component.HealthCheckRecordExcelComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class HealthCheckReportServiceImpl implements HealthCheckReportService {
	
	@Autowired
	HealthCheckMapper healthCheckMapper;
	@Autowired
	HealthCheckRecordExcelComponent<ResponseHealthCheckDetailVO> healthCheckRecordExcelComponent;

	@Override
	public List<ResponseHealthCheckVO> getHealthCheckReportList(int start, int pageSize, Integer chainType,
			String enterpriseCode, String enterpriseName, String code, String planManName, String planYear,
			String checkType, Integer enterpriseCodeOrder, Integer planDateOrder,UserVO user,Date startTime,Date endTime) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", start);
		map.put("pageSize", pageSize);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("chainType", chainType);
		map.put("enterpriseCode", enterpriseCode);
		map.put("enterpriseName", enterpriseName);
		map.put("code", code);
		map.put("planManName", planManName);
		map.put("planYear", planYear);
		map.put("checkType", checkType);
		map.put("enterpriseCodeOrder", enterpriseCodeOrder);
		map.put("planDateOrder", planDateOrder);
		map.put("enterpriseId", user.getEnterpriseId());
		return healthCheckMapper.getHealthCheckReportList(map);
	}

	@Override
	public Integer getHealthCheckReportListTotalNum(Integer chainType, String enterpriseCode,
			String enterpriseName, String code, String planManName, String planYear, String checkType,
			Integer enterpriseCodeOrder, Integer planDateOrder, UserVO user, Date startTime, Date endTime) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("chainType", chainType);
		map.put("enterpriseCode", enterpriseCode);
		map.put("enterpriseName", enterpriseName);
		map.put("code", code);
		map.put("planManName", planManName);
		map.put("planYear", planYear);
		map.put("checkType", checkType);
		map.put("enterpriseCodeOrder", enterpriseCodeOrder);
		map.put("planDateOrder", planDateOrder);
		map.put("enterpriseId", user.getEnterpriseId());
		return healthCheckMapper.getHealthCheckReportListTotalNum(map);
	}

	@Override
	public List<ResponseHealthCheckDetailVO> getHealthCheckReportDetlList(Long id, Integer start, Integer pageSize,
			UserVO user) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("enterpriseId", user.getEnterpriseId());
		/*map.put("start", start);
		map.put("pageSize", pageSize);*/
		map.put("id", id);
		return healthCheckMapper.getHealthCheckReportDetlList(map);
	}

	@Override
	public Integer getHealthCheckReportDetlListTotalNum(Long id, UserVO user) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("enterpriseId", user.getEnterpriseId());
		map.put("id", id);
		return healthCheckMapper.getHealthCheckReportDetlListTotalNum(map);
	}

	@Override
	public void exportHealthCheckReportDetlListExcel(UserVO user, OutputStream output, Long id) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("enterpriseId", user.getEnterpriseId());
		map.put("id", id);
		List<ResponseHealthCheckDetailVO> list=healthCheckMapper.getHealthCheckReportDetlList(map);
		ResponseHealthCheckVO responseHealthCheckVO=healthCheckMapper.getHealthCheckReportById(id);
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		rowHeaderList.put("userCode", "员工编码");
		rowHeaderList.put("userName", "员工姓名");
		rowHeaderList.put("deptNames", "部门");
		rowHeaderList.put("positionNames", "岗位");
		rowHeaderList.put("sexName", "性别");
		rowHeaderList.put("birthDate", "出生日期");
		rowHeaderList.put("inductionTime", "入职日期");
		rowHeaderList.put("checkDate", "检查日期");
		rowHeaderList.put("checkResult", "检查结果");
		rowHeaderList.put("measures", "采取措施");
		healthCheckRecordExcelComponent.commExcelExport(output, rowHeaderList, list, user, responseHealthCheckVO);
		
	}
	
}
