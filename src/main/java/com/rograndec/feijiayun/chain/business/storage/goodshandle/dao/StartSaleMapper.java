package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StartSale;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface StartSaleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StartSale record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StartSale record);

    /**
     *
     * @mbg.generated
     */
    StartSale selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StartSale record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StartSale record);

    List<StartSalePageVO> selectStartSalePage(SaleOrderRequestVO requestVO);

    StartSaleReturnVO selectDetailById(@Param("id") Long id);

    /**
     * 从锁定记录中查询锁定的商品
     * @param map
     * @return
     */
    List<StockDestroyVO> getLockStockList(Map<String, Object> map);

    /**
     * 查询数据总和
     * @param requestVO
     * @return
     */
    Integer selectStartSalePageCount(SaleOrderRequestVO requestVO);
}