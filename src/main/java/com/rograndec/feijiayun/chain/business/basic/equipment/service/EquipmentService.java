package com.rograndec.feijiayun.chain.business.basic.equipment.service;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.DepartmentVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EnterprisePageVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: EquipmentService
 * @Description: 设施设备-接口
 * @date 2017-10-13 13:26:02
 */
public interface EquipmentService {


	Equipment getEquipmentData(UserVO userVO,Long id) throws Exception;

	int save(Equipment equipment, UserVO userVO) throws Exception;

	int update(Equipment equipment, UserVO userVO) throws Exception;

	int delete(Long id) throws Exception;


	void listEquipmentData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page);

	void listEquipmentReportData(UserVO userVO, RequestEquipmentListVO requestEquipmentListVO, Page page);

	List<Equipment> listEquipmentDataByEnterpeise(UserVO userVO, Long id,Long typeId);

	List<Equipment> listEquipmentPrintData(UserVO userVO, RequestEquipmentListEVO requestEquipmentListEVO);

	void excelExport(OutputStream output, List<Equipment> equipmentList, UserVO userVO);

	List<TreePOJO<DepartmentVO>> getDepartment(UserVO userVO);

	/**
	 * 获取 设备管理选择总部控制的 门店
	 * @param userVO
	 * @return
	 */
    List<EnterprisePageVO> getEqptMngHeadCtlEnt(UserVO userVO);
}
