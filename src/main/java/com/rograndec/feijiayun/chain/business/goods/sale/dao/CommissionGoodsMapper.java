package com.rograndec.feijiayun.chain.business.goods.sale.dao;

import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionGoods;

import java.util.List;
import java.util.Map;

public interface CommissionGoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(CommissionGoods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(CommissionGoods record);

    /**
     *
     * @mbg.generated
     */
    CommissionGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CommissionGoods record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CommissionGoods record);

    List<CommissionGoods> selectByStrategyIdByEnterpriseId(Map<String, Long> param);
    int batchInsert(List<CommissionGoods> list);

    List<CommissionGoods> selectByEnterpriseId(Map param);

    Long selectGoodsExists(Map param);

    Integer selectCountByEnterpriseId(Map param);
}