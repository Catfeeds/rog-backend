package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StopSale;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.SaleOrderRequestVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSalePageVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleReturnVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StopSaleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StopSale record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StopSale record);

    /**
     *
     * @mbg.generated
     */
    StopSale selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StopSale record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StopSale record);

    List<StopSalePageVO> selectStopSalePage(SaleOrderRequestVO requestVO);

    StopSaleReturnVO selectDetailById(@Param("id") Long id);

    /**
     * 获取总的记录数量
     * @param requestVO
     * @return
     */
    Integer selectStopSalePageCount(SaleOrderRequestVO requestVO);

}