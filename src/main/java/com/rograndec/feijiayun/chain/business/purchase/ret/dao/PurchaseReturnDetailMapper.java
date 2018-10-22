package com.rograndec.feijiayun.chain.business.purchase.ret.dao;

import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseReturnDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReturnDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReturnDetail record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReturnDetail selectByPrimaryKey(Long id);
    List<PurchaseReturnDetail> selectByReturnId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReturnDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReturnDetail record);

    void updateReturnDetailStatus(@Param("id")Long id, @Param("status") Integer status);

    int updateReturnDetailQuantity(PurchaseReturnDetail returnDetail);

    void batchInsert(List<PurchaseReturnDetail> returnDetails);
}