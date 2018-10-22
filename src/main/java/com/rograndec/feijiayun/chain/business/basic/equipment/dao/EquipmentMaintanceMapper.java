package com.rograndec.feijiayun.chain.business.basic.equipment.dao;

import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentMaintance;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.EquipmentMaintanceRequestVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.EquipmentMaintanceVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.EquipmentSimpleVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListEVO;
import com.rograndec.feijiayun.chain.business.basic.equipment.vo.RequestEquipmentListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipmentMaintanceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EquipmentMaintance record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EquipmentMaintance record);

    /**
     *
     * @mbg.generated
     */
    EquipmentMaintance selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EquipmentMaintance record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EquipmentMaintance record);

    /**
     * 分页查询
     * @param requestVO
     * @return
     */
    List<EquipmentMaintanceVO> getMaintancePageList(EquipmentMaintanceRequestVO requestVO);

    EquipmentMaintanceVO get(@Param("id") Long id);

    List<EquipmentMaintance> getMaintanceByEquipment(Long id);

    List<EquipmentSimpleVO> getEquipmentSimpleVOList(@Param("enterpriseId") Long enterpriseId, @Param("typeId") Long typeId, @Param("list") List list);

	int countListEquipmentReportData(RequestEquipmentListVO requestEquipmentListVO);

    List<EquipmentMaintance> listEquipmentReportData(RequestEquipmentListVO requestEquipmentListVO);

    List<EquipmentMaintance> listEquipmentPrintData(RequestEquipmentListEVO requestEquipmentListEVO);

    /**
     * 获取分页总数
     * @param requestVO
     * @return
     */
    Integer getMaintancePageCount(EquipmentMaintanceRequestVO requestVO);
}