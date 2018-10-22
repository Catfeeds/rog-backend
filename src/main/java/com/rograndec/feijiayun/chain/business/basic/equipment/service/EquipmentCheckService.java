package com.rograndec.feijiayun.chain.business.basic.equipment.service;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentCheck;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: EquipmentCheckService
 * @Description: 设施设备-校准和检定-接口
 * @date 2017-10-16 13:18:06
 */
public interface EquipmentCheckService {


	EquipmentCheck getEquipmentCheckData(Long id) throws Exception;

	int save(EquipmentCheck equipmentCheck, UserVO userVO) throws Exception;

	int update(EquipmentCheck equipmentCheck, UserVO userVO) throws Exception;

	int delete(Long id) throws Exception;


	void listEquipmentCheckData(UserVO userVO, Page page, RequestEquipmentListVO requestEquipmentListVO);

	void listEquipmentReportData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page);

	List<EquipmentCheck> listEquipmentPrintData(UserVO userVO, RequestEquipmentListEVO requestEquipmentListEVO);

	void excelExport(OutputStream output, List<EquipmentCheck> equipmentList, UserVO userVO);
}
