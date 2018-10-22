package com.rograndec.feijiayun.chain.business.basic.supplier.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SupplierBusinessMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SupplierBusiness record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SupplierBusiness record);

    /**
     *
     * @mbg.generated
     */
    SupplierBusiness selectByPrimaryKey(Long id);

    List<SupplierBusiness> selectBySupplierIds(@Param("list") List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SupplierBusiness record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SupplierBusiness record);

	void saveBusinessSupplier(Map<String, Object> map);

    SupplierBusiness getSupplierBuseinessBySupplierId(Long supplierId);
}