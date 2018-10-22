package com.rograndec.feijiayun.chain.business.purchase.retout.dao;

import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseReturnOutDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReturnOutDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReturnOutDetail record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReturnOutDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReturnOutDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReturnOutDetail record);

    /**
     * 品种数量
     * @param outId
     * @return
     */
    Integer getCountVarietiesQuantity(Long outId);

    List<PurchaseReturnOutDetail> getPurchaseReturnOutDetailByOutId(Long outId);

    void updateStatusByReturnOut(PurchaseReturnOutDetail purchaseReturnOutDetail);



    PurchaseReturnOutDetail getByPrimaryKeyAndEId(@Param("id") Long id,@Param("enterpriseId")Long enterpriseId);



//    int updateStatusByReturnOut(PurchaseReturnOutDetail purchaseReturnOutDetail);

}