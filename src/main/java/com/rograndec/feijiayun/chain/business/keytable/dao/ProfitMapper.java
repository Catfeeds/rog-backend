package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.keytable.entity.Profit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProfitMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Profit record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Profit record);

    /**
     *
     * @mbg.generated
     */
    Profit selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Profit record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Profit record);

    List<Profit> selectByParamMapWithFIFO(Map<String,Object> paramMap);

    /**
     * 总部配后退回入库获取分店配进退出出库毛利追溯列表
     * @param enterpriseId
     * @param orderDtlId
     * @param orderId
     * @return
     */
    List<Profit>  queryProfitWhenDistrReturnIn(@Param("enterpriseId") Long enterpriseId, @Param("orderDtlId") Long orderDtlId, @Param("orderId") Long orderId);

    /**
     * 分店配进入库获取总部配货出库毛利追溯列表
     * @param enterpriseId
     * @param orderDtlId
     * @param orderId
     * @return
     */
    List<Profit> queryProfitWhenDistrIn(@Param("enterpriseId") Long enterpriseId, @Param("orderDtlId") Long orderDtlId, @Param("orderId") Long orderId);

    /**
     * 分店销退入库获取销售出库毛利追溯列表
     * @param enterpriseId
     * @param orderDtlId
     * @param orderId
     * @return
     */
    List<Profit> queryProfitWhenSaleReturnIn(@Param("enterpriseId") Long enterpriseId, @Param("orderDtlId") Long orderDtlId, @Param("orderId") Long orderId);

    List<Profit> selectByOutParamMap(Map<String,Object> paramMap);

}