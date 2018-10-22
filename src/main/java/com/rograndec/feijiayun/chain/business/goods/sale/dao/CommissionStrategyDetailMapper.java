package com.rograndec.feijiayun.chain.business.goods.sale.dao;


import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategyDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommissionStrategyDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(CommissionStrategyDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(CommissionStrategyDetail record);

    /**
     *
     * @mbg.generated
     */
    CommissionStrategyDetail selectByPrimaryKey(Long id);

    List<CommissionStrategyDetail> selectBySetIds(@Param("list") List<Long> list,@Param("status") Integer status);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CommissionStrategyDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CommissionStrategyDetail record);

    int batchInsert(List<CommissionStrategyDetail> royaltyStrategyDetails);

    int deleteByEnterpriseIdBySetId(Map<String, Long> param);

    List<CommissionStrategyDetail> selectBySetId(Long id);
}