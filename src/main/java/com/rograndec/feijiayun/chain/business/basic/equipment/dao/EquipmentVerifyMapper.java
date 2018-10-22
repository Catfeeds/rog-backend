package com.rograndec.feijiayun.chain.business.basic.equipment.dao;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentVerify;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;


import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EquipmentVerifyMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EquipmentVerify record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EquipmentVerify record);

    /**
     *
     * @mbg.generated
     */
    EquipmentVerify selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EquipmentVerify record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EquipmentVerify record);

	List<EquipmentVerify> listEquipmentVerifyData(RequestEquipmentListVO requestEquipmentListVO);

    int countEquipmentVerifyData(RequestEquipmentListVO requestEquipmentListVO);

	List<EquipmentVerify> listEquipmentReportData(RequestEquipmentListVO requestEquipmentListVO);

    List<EquipmentVerify> listEquipmentPrintData(RequestEquipmentListEVO requestEquipmentListEVO);
    /**
     * 获取为总部控制的门店id集合
     * @return
     */
    List<Long> listEnterpriseIds(@Param("id") Long id);
    
    int countListEquipmentReportData(RequestEquipmentListVO requestEquipmentListVO);
}