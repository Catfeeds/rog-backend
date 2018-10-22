package com.rograndec.feijiayun.chain.business.basic.equipment.dao;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentCheck;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;

import java.util.List;

public interface EquipmentCheckMapper {
	/**
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * @mbg.generated
	 */
	int insert(EquipmentCheck record);

	/**
	 * @mbg.generated
	 */
	int insertSelective(EquipmentCheck record);

	/**
	 * @mbg.generated
	 */
	EquipmentCheck selectByPrimaryKey(Long id);

	/**
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(EquipmentCheck record);

	/**
	 * @mbg.generated
	 */
	int updateByPrimaryKey(EquipmentCheck record);

	int countEquipmentCheckData(RequestEquipmentListVO requestEquipmentListVO);

	List<EquipmentCheck> listEquipmentCheckData(RequestEquipmentListVO requestEquipmentListVO);

	List<EquipmentCheck> listEquipmentReportData(RequestEquipmentListVO requestEquipmentListVO);

	List<EquipmentCheck> listEquipmentPrintData(RequestEquipmentListEVO requestEquipmentListEVO);
}