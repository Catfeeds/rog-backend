package com.rograndec.feijiayun.chain.business.storage.storehouse.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.RequsetTempHumUpdateVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.ResponseTempHumVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.TemperatureHumidityVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.TemperatureRecordVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface TemperatureHumidityService {

	
	List<WarehouseVO> getWarehouseList(Long enterpriseId);
	
	List<WarehouseAreaVO> getWarehouseAreaList(Long warehouseId);
	
	String insert(TemperatureHumidityVO record,UserVO user) throws Exception;
	
	Page<List<TemperatureRecordVO>> getTempHumidityRecordList( Page<List<TemperatureRecordVO>> page,Long enterpriseId,Map<String,Object> map);
	
	ResponseTempHumVO getTempHumidityRecordDtlList(Long id);
	
	String updateTempHumidityRecord(RequsetTempHumUpdateVO requsetTempHumUpdateVO,UserVO user);
	
	void exportExcel(OutputStream output,ResponseTempHumVO responseVO,UserVO user);
	
	int deleteTempHumidityRecord(Long recordId);
	
	String getWarehouseAreaNameById(Long enterpriseId,Long warehouseId,Long warehouseAreaId);
	
	String getWarehouseNameById(Long enterpriseId,Long warehouseId);
	
}
