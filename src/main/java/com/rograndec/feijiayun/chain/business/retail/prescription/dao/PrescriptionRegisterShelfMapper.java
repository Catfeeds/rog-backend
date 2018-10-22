package com.rograndec.feijiayun.chain.business.retail.prescription.dao;

import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegisterShelf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrescriptionRegisterShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrescriptionRegisterShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrescriptionRegisterShelf record);

    /**
     *
     * @mbg.generated
     */
    PrescriptionRegisterShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrescriptionRegisterShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrescriptionRegisterShelf record);

    int deleteByDetailId(@Param("registerId")Long registerId,@Param("detailId") Long detailId, @Param("enterpriseId") Long enterpriseId,@Param("goodsId")Long goodsId);

    List<Long> getShelfIdsByDId(@Param("registerId")Long registerId, @Param("detailId") Long detailId, @Param("enterpriseId") Long enterpriseId);

    int updateStatusByParam(@Param("status")Integer status,@Param("enterpriseId")Long enterpriseId,@Param("rid")Long rid,@Param("did")Long dis);
}