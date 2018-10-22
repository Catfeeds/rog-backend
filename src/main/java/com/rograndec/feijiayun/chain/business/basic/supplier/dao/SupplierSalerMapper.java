package com.rograndec.feijiayun.chain.business.basic.supplier.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SupplierSalerMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SupplierSaler record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SupplierSaler record);

    /**
     *
     * @mbg.generated
     */
    SupplierSaler selectByPrimaryKey(Long id);
    List<SupplierSaler> selectBySuppliereId(Long id);

    List<SupplierSaler> selectBySupplierParam(Map map);

    List<SupplierSaler> selectByParam(Map map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SupplierSaler record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SupplierSaler record);

	SupplierSaler addSale(Map<String, Object> map);

	SupplierSaler getTmpGroup();

	void insertSupplierSaler(Map<String, Object> mapSale);

	void deleteTmpSupplierSaler(Long id);

	List<SupplierSaler> getTransport(@Param("id") Long id);
}