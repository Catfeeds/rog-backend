package com.rograndec.feijiayun.chain.business.basic.equipment.service;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentVerify;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: EquipmentVerifyService
 * @Description: 设施设备-验证-接口
 * @date 2017-10-16 13:18:19
 */
public interface EquipmentVerifyService {


	EquipmentVerify getEquipmentVerifyData(Long id) throws Exception;

	int save(EquipmentVerify equipmentVerify, UserVO userVO) throws Exception;

	int update(EquipmentVerify equipmentVerify, UserVO userVO) throws Exception;

	int delete(Long id) throws Exception;


	void listEquipmentVerifyData(RequestEquipmentListVO requestEquipmentListVO, Page page,UserVO userVO);

	void listEquipmentReportData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page);

	List<EquipmentVerify> listEquipmentPrintData(UserVO userVO, RequestEquipmentListEVO requestEquipmentListEVO);

	void excelExport(OutputStream output, List<EquipmentVerify> equipmentList, UserVO userVO);
}
