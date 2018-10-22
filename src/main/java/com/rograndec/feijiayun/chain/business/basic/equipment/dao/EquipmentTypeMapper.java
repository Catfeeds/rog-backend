package com.rograndec.feijiayun.chain.business.basic.equipment.dao;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;

import java.util.List;

public interface EquipmentTypeMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EquipmentType record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EquipmentType record);

    /**
     *
     * @mbg.generated
     */
    EquipmentType selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EquipmentType record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EquipmentType record);

	List<EquipmentType> getParentEquipmentTypeData();

    List<EquipmentType> selectByParent(String code);
}