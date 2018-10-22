package com.rograndec.feijiayun.chain.business.medicine.consult.dao;

import com.rograndec.feijiayun.chain.business.medicine.consult.entity.MedicineConsultGoods;

public interface MedicineConsultGoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MedicineConsultGoods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MedicineConsultGoods record);

    /**
     *
     * @mbg.generated
     */
    MedicineConsultGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MedicineConsultGoods record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MedicineConsultGoods record);
    
    void deleteByConsultId(Long consultId);
}