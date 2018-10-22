package com.rograndec.feijiayun.chain.business.retail.prescription.dao;

import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegisterDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrescriptionRegisterDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrescriptionRegisterDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrescriptionRegisterDetail record);

    /**
     *
     * @mbg.generated
     */
    PrescriptionRegisterDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrescriptionRegisterDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrescriptionRegisterDetail record);

    List<Long> getDetailIdsByRId(@Param("enterpriseId")Long enterpriseId, @Param("rid")Long rid);

    Integer getCountVarietiesQuantity(Long registerId);

    int updateStatusByRid(@Param("status")Integer status,@Param("enterpriseId")Long enterpriseId,@Param("rid")Long rid);

    int deleteByGIdAndRId(@Param("goodsId")Long goodsId,@Param("rid") Long rid);
}