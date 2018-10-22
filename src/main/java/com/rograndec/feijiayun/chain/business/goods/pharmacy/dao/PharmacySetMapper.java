package com.rograndec.feijiayun.chain.business.goods.pharmacy.dao;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.PharmacySet;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetForPrescVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PharmacySetMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PharmacySet record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PharmacySet record);

    /**
     *
     * @mbg.generated
     */
    PharmacySet selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PharmacySet record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PharmacySet record);



    List<PharmacySetVO> selectAll(@Param("enterpriseId")Long enterpriseId,@Param("type")Long type);


    List<PharmacySet> selectByCode(Map param);

    List<PharmacySetForPrescVO> getPharmacySetByParam(@Param("enterpriseId")Long enterpriseId,@Param("setType")Integer type,@Param("status") Integer status);


    List<PrescriptionRegister> selectByIdType(Map<String, Long> param);


    List<PharmacySet> selectByTypeId(Map<String, Long> param);

    List<PharmacySet> selectByCodes(Map params);

    List<PrescriptionRegister> getByIdType(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId, @Param("type")Long type);
}