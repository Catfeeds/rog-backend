package com.rograndec.feijiayun.chain.business.basic.supplier.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierModifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SupplierModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SupplierModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    SupplierModifyRecord selectByPrimaryKey(Long id);

    List<SupplierModifyRecord> selectByKeyId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SupplierModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SupplierModifyRecord record);

	List<SupplierModifyRecord> selectBySupplierIdAndUserId(@Param("supplierId")Long supplierId, @Param("enterpriseId")Long enterpriseId);

}