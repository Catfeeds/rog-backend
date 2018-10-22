package com.rograndec.feijiayun.chain.business.storage.storehouse.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.storage.storehouse.dao.TemperatureHumidityDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.storehouse.dao.TemperatureHumidityMapper;
import com.rograndec.feijiayun.chain.business.storage.storehouse.entity.TemperatureHumidity;
import com.rograndec.feijiayun.chain.business.storage.storehouse.entity.TemperatureHumidityDetail;
import com.rograndec.feijiayun.chain.business.storage.storehouse.service.TemperatureHumidityService;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.RequestTempHumDetUpdateVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.RequsetTempHumUpdateVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.ResponseTempHumDetVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.ResponseTempHumVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.TemperatureHumidityDetailVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.TemperatureHumidityVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.TemperatureRecordVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.TemperatureExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class TemperatureHumidityServiceImpl implements TemperatureHumidityService {

	@Autowired
	OrderCodeComponent orderCodeComponent;
	@Autowired
	TemperatureHumidityMapper temperatureHumidityMapper;
	
	@Autowired
	TemperatureHumidityDetailMapper temperatureHumidityDetailMapper;
	@SuppressWarnings("rawtypes")
	@Autowired
	TemperatureExcelComponent  temperatureExcelComponent;
	
	@Override
	public List<WarehouseVO> getWarehouseList(Long enterpriseId) {
		return temperatureHumidityMapper.getWarehouseList(enterpriseId);
	}

	@Override
	public List<WarehouseAreaVO> getWarehouseAreaList(Long warehouseId) {
		return temperatureHumidityDetailMapper.getWarehouseAreaList(warehouseId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String insert(TemperatureHumidityVO record,UserVO user) throws Exception {
		List<TemperatureHumidityDetailVO>  detailList=record.getDetailList();
		for(TemperatureHumidityDetailVO detail:detailList){
			if(detail.getLineNum()==null) return "行号不能为空";
		}
		if(detailList.isEmpty()) return "请至少添加一条记录";
		detailList=judgeNullPoint(detailList);//过滤空值
		if(detailList.isEmpty()) return "请至少添加一条有效记录";
		
		TemperatureHumidity temperatureHumidity=new TemperatureHumidity();
		TemperatureHumidityDetail temperatureHumidityDetail=new TemperatureHumidityDetail();
		//设置参数信息
		BeanUtils.copyProperties(record,temperatureHumidity);
		//设置企业信息
		BeanUtils.copyProperties(user,temperatureHumidity);
		//如果没有设置记录人则为当前登陆人
		if(record.getRecordManId()==null){
			//设置记录人和创建人信息和记录单类型和编号
			setTemperatureHumidityInfo(temperatureHumidity, user,user);
		}else{
			//通过id获取用户
			Long recordManId=record.getRecordManId();
			if(recordManId!=null){
				UserVO recordMan=temperatureHumidityDetailMapper.getUserById(recordManId, user.getEnterpriseId());
				if(recordMan==null) return "该企业记录人员的id不存在";
				setTemperatureHumidityInfo(temperatureHumidity,user,recordMan);
			}
		}
		//状态暂未用到
		temperatureHumidity.setStatus(0);
		//添加温湿度记录单
		temperatureHumidityMapper.insert(temperatureHumidity);
		Long recordId=temperatureHumidity.getId();
		
		//设置行详情的企业信息，记录人和创建人信息
		BeanUtils.copyProperties(temperatureHumidity,temperatureHumidityDetail);
		//设置单据id
		temperatureHumidityDetail.setRecordId(recordId);
		//设置单据单号
		temperatureHumidityDetail.setRecordCode(temperatureHumidity.getCode());
		//添加温湿度记录单详细信息
		
		for(TemperatureHumidityDetailVO t:detailList){
			//设置详细的温度记录信息
			BeanUtils.copyProperties(t,temperatureHumidityDetail);
			temperatureHumidityDetail.setId(null);
			temperatureHumidityDetail.setStatus(0);//暂留字段
			temperatureHumidityDetailMapper.insertSelective(temperatureHumidityDetail);
		}
		return null;
	}
	
	private List<TemperatureHumidityDetailVO> judgeNullPoint(List<TemperatureHumidityDetailVO> list){
		List<TemperatureHumidityDetailVO> reslist=new ArrayList<>();
		for(TemperatureHumidityDetailVO v:list){
			//只要有一个不为null则可添加
			if(v.getAmHumidityA()!=null||v.getAmHumidityB()!=null||v.getAmMeasure()!=null||v.getAmTempA()!=null||v.getAmTempB()!=null
			||v.getAmTime()!=null||v.getPmHumidityA()!=null||v.getPmHumidityB()!=null
			||v.getPmMeasure()!=null||v.getPmTempA()!=null||v.getPmTempB()!=null||v.getPmTime()!=null){
				reslist.add(v);
			}
		}
		return reslist;
	}
	
	private void setTemperatureHumidityInfo(TemperatureHumidity temperatureHumidity,UserVO user,UserVO recordMan) throws Exception{
		//设置创建人信息
		temperatureHumidity.setCreaterId(user.getUserId());
		temperatureHumidity.setCreaterName(user.getUserName());
		temperatureHumidity.setCreaterCode(user.getUserCode());
		temperatureHumidity.setRecordManId(recordMan.getUserId());
		temperatureHumidity.setRecordManCode(recordMan.getUserCode());
		temperatureHumidity.setRecordManName(recordMan.getUserName());
		temperatureHumidity.setCreateTime(new Date());
		//设置修改人信息，添加的时候修改人信息同创建人
		temperatureHumidity.setModifierId(user.getUserId());
		temperatureHumidity.setModifierCode(user.getUserCode());
		temperatureHumidity.setModifierName(user.getUserName());
		temperatureHumidity.setUpdateTime(new Date());
		//设置记录单类型和编号
		String code=orderCodeComponent.generate(OrderRule.TEMPERATURE_HUMIDITY.getCodePrefix(), user.getEnterpriseId(), user.getEnterpriseCode());
		temperatureHumidity.setCode(code);
		temperatureHumidity.setOrderType(OrderRule.TEMPERATURE_HUMIDITY.getType());
	}
	
	private void setTemperatureHumidityRecordManInfo(TemperatureHumidity temperatureHumidity,UserVO user,UserVO recordMan){
		//设置修改人信息
		temperatureHumidity.setRecordManId(recordMan.getUserId());
		temperatureHumidity.setRecordManCode(recordMan.getUserCode());
		temperatureHumidity.setRecordManName(recordMan.getUserName());
		temperatureHumidity.setModifierId(user.getUserId());
		temperatureHumidity.setModifierCode(user.getUserCode());
		temperatureHumidity.setModifierName(user.getUserName());
		temperatureHumidity.setUpdateTime(new Date());
	}
	
	@Override
	public Page<List<TemperatureRecordVO>> getTempHumidityRecordList(Page<List<TemperatureRecordVO>> page,
			Long enterpriseId,Map<String,Object> map) {
		map.put("start", page.getStart());
		map.put("enterpriseId", enterpriseId);
		Integer totalNum=temperatureHumidityMapper.getTempHumidityRecordListTotalNum(map);
		//无记录直接返回
		if(totalNum==0) return page;
		page.setTotalRecord(totalNum);
		List<TemperatureRecordVO> list=temperatureHumidityMapper.getTempHumidityRecordList(map);
		page.setResult(list);
		return page;
	}
	
	@Override
	public ResponseTempHumVO getTempHumidityRecordDtlList(Long id) {
		return temperatureHumidityMapper.getTempHumidityRecordDtlList(id);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String updateTempHumidityRecord(RequsetTempHumUpdateVO requsetTempHumUpdateVO,UserVO user){
		List<RequestTempHumDetUpdateVO>  detailList=requsetTempHumUpdateVO.getDetailList();
		for(RequestTempHumDetUpdateVO detail:detailList){
			if(detail.getLineNum()==null) return "行号不能为空";
		}
		detailList=judgeNullPoint2(detailList);
		if(detailList!=null&&detailList.isEmpty()) return "修改时请至少添加一条有效记录";
		
		Long fileId=requsetTempHumUpdateVO.getFileId();
		Long recordId=requsetTempHumUpdateVO.getRecordId();
		//若是文件删除则更新文件为null
		if(fileId==null){// null 代表删除
			temperatureHumidityMapper.deleteFileFormTempHumidityRecord(recordId,user.getUserId(),user.getUserCode(),user.getUserName(),new Date());
		}else{//不删除则修改附件信息
			temperatureHumidityMapper.updateFileFormTempHumidityRecord(recordId,user.getUserId(),user.getUserCode(),user.getUserName(),requsetTempHumUpdateVO.getFileId(),new Date());
		}
		//若是修改日期或是人员
		TemperatureHumidity temperatureHumidityupdate=new TemperatureHumidity();
		//通过id获取用户
		Long recordManId=requsetTempHumUpdateVO.getRecordManId();
		if(recordManId!=null){
			UserVO recordMan=temperatureHumidityDetailMapper.getUserById(recordManId, user.getEnterpriseId());
			if(recordMan==null) {
				return "该企业记录人员的id不存在";
			}
			setTemperatureHumidityRecordManInfo(temperatureHumidityupdate,user,recordMan);
			temperatureHumidityupdate.setId(requsetTempHumUpdateVO.getRecordId());
			temperatureHumidityupdate.setRecordDate(requsetTempHumUpdateVO.getRecordDate());
		}else{
			temperatureHumidityupdate.setId(requsetTempHumUpdateVO.getRecordId());
			temperatureHumidityupdate.setRecordDate(requsetTempHumUpdateVO.getRecordDate());
			temperatureHumidityupdate.setModifierId(user.getUserId());
			temperatureHumidityupdate.setModifierCode(user.getUserCode());
			temperatureHumidityupdate.setModifierName(user.getUserName());
			temperatureHumidityupdate.setUpdateTime(new Date());
		}
		//更改部分信息
		temperatureHumidityMapper.updateByPrimaryKeySelective(temperatureHumidityupdate);
	
		List<Long> deleteIds=requsetTempHumUpdateVO.getDeleteIds();
		//若有删除的记录，则删除记录
		if(deleteIds!=null&&!deleteIds.isEmpty()){
			for(Long id:deleteIds){
				temperatureHumidityDetailMapper.deleteByPrimaryKey(id);
			}
		}
		//要修改或添加的记录
		if(detailList!=null&&detailList.size()!=0){
			TemperatureHumidityDetail temperatureHumidityDetail=new TemperatureHumidityDetail();
			//设置本次修改人信息
			setTemperatureHumidityDetailModifierInfo(temperatureHumidityDetail, user);
			for(RequestTempHumDetUpdateVO  detail:detailList){
				//判断该条信息是更改或是添加
				Long detailId=detail.getDetailId();
				//此条信息为新增
				if(detailId==null){
					TemperatureHumidity temperatureHumidity=temperatureHumidityMapper.selectByPrimaryKey(requsetTempHumUpdateVO.getRecordId());
					//设置企业信息和创建人信息
					setTemperatureHumidityDetailEnterpriseAndCreaterInfo(temperatureHumidityDetail,user,temperatureHumidity);
					//设置单据id
					temperatureHumidityDetail.setRecordId(recordId);
					temperatureHumidityDetail.setOrderType(temperatureHumidity.getOrderType());
					//设置单据单号
					temperatureHumidityDetail.setRecordCode(temperatureHumidity.getCode());
					temperatureHumidityDetail.setRecordDate(new Date());
					temperatureHumidityDetail.setStatus(0);
					//设置详细的温度记录信息
					BeanUtils.copyProperties(detail,temperatureHumidityDetail);
					temperatureHumidityDetailMapper.insertSelective(temperatureHumidityDetail);
					continue;
				}
				//设置详细的温度记录信息
				BeanUtils.copyProperties(detail,temperatureHumidityDetail);
				temperatureHumidityDetail.setRecordDate(temperatureHumidityupdate.getRecordDate());
				temperatureHumidityDetail.setId(detailId);
				temperatureHumidityDetailMapper.updateByPrimaryKeySelective(temperatureHumidityDetail);
			}
		}
		return null;
	}
	
	private List<RequestTempHumDetUpdateVO> judgeNullPoint2(List<RequestTempHumDetUpdateVO> list){
		List<RequestTempHumDetUpdateVO> reslist=new ArrayList<>();
		for(RequestTempHumDetUpdateVO v:list){
			//只要有一个不为null则可添加
			if(v.getAmHumidityA()!=null||v.getAmHumidityB()!=null||v.getAmMeasure()!=null||v.getAmTempA()!=null||v.getAmTempB()!=null
			||v.getAmTime()!=null||v.getPmHumidityA()!=null||v.getPmHumidityB()!=null
			||v.getPmMeasure()!=null||v.getPmTempA()!=null||v.getPmTempB()!=null||v.getPmTime()!=null){
				reslist.add(v);
			}
		}
		return reslist;
	}
	//设置更改信息的操作人
	private void setTemperatureHumidityDetailModifierInfo(TemperatureHumidityDetail temperatureHumidityDetail,UserVO user){
		temperatureHumidityDetail.setModifierId(user.getUserId());
		temperatureHumidityDetail.setModifierCode(user.getUserCode());
		temperatureHumidityDetail.setModifierName(user.getUserName());
		temperatureHumidityDetail.setUpdateTime(new Date());
	}
	
	//设置企业和创建人基本信息
	private void setTemperatureHumidityDetailEnterpriseAndCreaterInfo(TemperatureHumidityDetail temperatureHumidityDetail,UserVO user,TemperatureHumidity temperatureHumidity){
		temperatureHumidityDetail.setEnterpriseId(user.getEnterpriseId());
		temperatureHumidityDetail.setParentId(user.getParentId());
		temperatureHumidityDetail.setCreaterId(user.getUserId());
		temperatureHumidityDetail.setCreaterName(user.getUserName());
		temperatureHumidityDetail.setCreaterCode(user.getUserCode());
		temperatureHumidityDetail.setCreateTime(new Date());
		//设置修改人信息
		temperatureHumidityDetail.setModifierId(user.getUserId());
		temperatureHumidityDetail.setModifierCode(user.getUserCode());
		temperatureHumidityDetail.setModifierName(user.getUserName());
		temperatureHumidityDetail.setUpdateTime(new Date());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void exportExcel(OutputStream output, ResponseTempHumVO responseVO,UserVO user) {
		List<ResponseTempHumDetVO> detailList=responseVO.getDetailList();
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();
		rowHeaderList.put("amTime", "上午记录时间");
		rowHeaderList.put("amTempA", "上午记录温度℃");
		rowHeaderList.put("amHumidityA", "上午相对湿度%");
		rowHeaderList.put("amMeasure", "上午调控措施");
		rowHeaderList.put("amTempB", "上午调控后相对温度");
		rowHeaderList.put("amHumidityB", "下午调控后相对湿度");
		rowHeaderList.put("pmTime", "下午记录时间");
		rowHeaderList.put("pmTempA", "下午记录温度℃");
		rowHeaderList.put("pmHumidityA", "下午相对湿度%");
		rowHeaderList.put("pmMeasure", "下午调控措施");
		rowHeaderList.put("pmTempB", "下午调控后相对温度");
		rowHeaderList.put("pmHumidityB", "下午调控后相对湿度");
       temperatureExcelComponent.commExcelExport(output,responseVO, rowHeaderList, detailList,user);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteTempHumidityRecord(Long recordId) {
		int row=temperatureHumidityMapper.deleteByPrimaryKey(recordId);
		temperatureHumidityDetailMapper.deleteInfoById(recordId);
		return row;
	}

	@Override
	public String getWarehouseAreaNameById(Long enterpriseId, Long warehouseId, Long warehouseAreaId) {
		Map<String,Object> map=new HashMap<>();
		map.put("enterpriseId", enterpriseId);
		map.put("warehouseId", warehouseId);
		map.put("warehouseAreaId", warehouseAreaId);
		return temperatureHumidityMapper.getWarehouseAreaNameById(map);
	}

	@Override
	public String getWarehouseNameById(Long enterpriseId, Long warehouseId) {
		Map<String,Object> map=new HashMap<>();
		map.put("enterpriseId", enterpriseId);
		map.put("warehouseId", warehouseId);
		return temperatureHumidityMapper.getWarehouseNameById(map);
	}
	
	
}
