package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SupplierDisplay;

public interface SupplierDisplayMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SupplierDisplay record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SupplierDisplay record);

    /**
     *
     * @mbg.generated
     */
    SupplierDisplay selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SupplierDisplay record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SupplierDisplay record);

	SupplierDisplay selectByAreaId(Integer id);

	SupplierDisplay selectByCityId(Integer id);

	SupplierDisplay selectByProvinceId(Integer id);
}