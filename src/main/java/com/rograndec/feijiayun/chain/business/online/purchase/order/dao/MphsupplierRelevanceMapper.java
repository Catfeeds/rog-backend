package com.rograndec.feijiayun.chain.business.online.purchase.order.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.online.purchase.order.entity.MphsupplierRelevance;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectBoundSupplyVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectSupplierVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MphsupplierRelevanceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MphsupplierRelevance record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MphsupplierRelevance record);

    /**
     *
     * @mbg.generated
     */
    MphsupplierRelevance selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MphsupplierRelevance record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MphsupplierRelevance record);

    SelectSupplierVO selectByMPHSupplyId(@Param("odSellerId")Long odSellerId, @Param("enterpriseId")Long enterpriseId);

    List<SelectBoundSupplyVO> selectByMPHSupplyName(@Param("enterpriseId")Long enterpriseId, @Param("mphSupplyName")String mphSupplyName);

    MphsupplierRelevance selectByMPHSupplyIdForUnBoundSupply(@Param("enterpriseId")Long enterpriseId, @Param("mphSupplyId")String mphSupplyId, @Param("supplyId")Long supplyId);

    int selectByMPHSupplyIdForSave(@Param("mphSupplierId")Long mphSupplierId, @Param("enterpriseId")Long enterpriseId);
}