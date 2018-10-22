package com.rograndec.feijiayun.chain.business.medicine.consult.dao;

import com.rograndec.feijiayun.chain.business.medicine.consult.entity.MedicineConsultCheck;

public interface MedicineConsultCheckMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MedicineConsultCheck record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MedicineConsultCheck record);

    /**
     *
     * @mbg.generated
     */
    MedicineConsultCheck selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MedicineConsultCheck record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MedicineConsultCheck record);
    
    void deleteByConsultId(Long consultId);
}