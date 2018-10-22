package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SmartSupplierDisplay;

public interface SmartSupplierDisplayMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SmartSupplierDisplay record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SmartSupplierDisplay record);

    /**
     *
     * @mbg.generated
     */
    SmartSupplierDisplay selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SmartSupplierDisplay record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SmartSupplierDisplay record);

	List<SmartSupplierDisplay> selectByEnterpriseId(
			Long id);
}