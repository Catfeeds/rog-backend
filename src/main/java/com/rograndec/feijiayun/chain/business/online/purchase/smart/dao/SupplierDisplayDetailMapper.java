package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SupplierDisplayDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierDisplayDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SupplierDisplayDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SupplierDisplayDetail record);

    /**
     *
     * @mbg.generated
     */
    SupplierDisplayDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SupplierDisplayDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SupplierDisplayDetail record);

    List<SupplierDisplayDetail> selectSmartDisplaySupplierIdS(@Param("dataId")Integer dataId, @Param("type")Integer type);

    List<SupplierDisplayDetail> selectBySupplierDisplayId(Long id);
}