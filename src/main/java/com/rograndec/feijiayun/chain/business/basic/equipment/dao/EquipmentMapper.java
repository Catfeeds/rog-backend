package com.rograndec.feijiayun.chain.business.basic.equipment.dao;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.DepartmentVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipmentMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Equipment record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Equipment record);

    /**
     *
     * @mbg.generated
     */
    Equipment selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Equipment record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Equipment record);

	int countListEquipmentData(RequestEquipmentListVO requestEquipmentListVO);

    List<Equipment> listEquipmentData(RequestEquipmentListVO requestEquipmentListVO);

	int countListEquipmentReportData(RequestEquipmentListVO requestEquipmentListVO);

    List<Equipment> listEquipmentReportData(RequestEquipmentListVO requestEquipmentListVO);

    List<Equipment> listEquipmentPrintData(RequestEquipmentListEVO requestEquipmentListEVO);

    /**
     * 获取当前企业部门
     */
    List<DepartmentVO> getDepartment(@Param("enterpriseId") Long enterpriseId);


    /**
     * 校验编码和名称不能重复
     *
     * @param enterpriseId
     * @param code
     * @param name
     * @param id
     * @return
     */
    List<Equipment> checkRepeat(@Param("enterpriseId") Long enterpriseId, @Param("code") String code, @Param("name") String name, @Param("id") Long id);

    List<Equipment> selectByEnterpriseId(Long enterpriseId);
}