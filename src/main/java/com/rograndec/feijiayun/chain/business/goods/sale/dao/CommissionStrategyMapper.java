package com.rograndec.feijiayun.chain.business.goods.sale.dao;


import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommissionStrategyMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(CommissionStrategy record);

    /**
     *
     * @mbg.generated
     */
    Long insertSelective(CommissionStrategy record);

    /**
     *
     * @mbg.generated
     */
    CommissionStrategy selectByPrimaryKey(Long id);

    List<CommissionStrategy> selectByIds(@Param("list") List<Long> list, @Param("status") Integer status);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CommissionStrategy record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CommissionStrategy record);

    List<CommissionStrategy> selectByEnterpriseIdOrder(Map param);

    List<Map> selectByEnterpriseId(Long enterpriseId);

}