package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SmartSupplierDisplayDetail;

public interface SmartSupplierDisplayDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SmartSupplierDisplayDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SmartSupplierDisplayDetail record);

    /**
     *
     * @mbg.generated
     */
    SmartSupplierDisplayDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SmartSupplierDisplayDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SmartSupplierDisplayDetail record);
}