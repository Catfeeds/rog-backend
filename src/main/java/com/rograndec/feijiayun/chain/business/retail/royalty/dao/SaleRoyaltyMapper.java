package com.rograndec.feijiayun.chain.business.retail.royalty.dao;

import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyalty;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.ResponseNoSaleRoyaltyDetailVO;

import java.util.List;
import java.util.Map;

public interface SaleRoyaltyMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleRoyalty record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleRoyalty record);

    /**
     *
     * @mbg.generated
     */
    SaleRoyalty selectByPrimaryKey(Long id);

    List<SaleRoyalty> selectByEnterpriseId(Map<String,Object> map);
    
    /**
     * 获取已提成总页数
     * @param map
     * @return
     */
    Integer selectTotalRecordByEnterpriseId(Map<String,Object> map);
    
    /**
     * 获取已提成列表
     * @param map
     * @return
     */
    List<SaleRoyalty> queryByEnterpriseId(Map<String,Object> map);

    List<ResponseNoSaleRoyaltyDetailVO> selectNoRoyaltyDetailsByParam(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleRoyalty record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleRoyalty record);
    
    /**
    *获取受总部控制的门店id
    * @param list
    * @return
    */
    List<Long> selectEidByEnterpriseId(List<Long> list);
}