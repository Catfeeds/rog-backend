package com.rograndec.feijiayun.chain.business.storage.displaycheck.dao;

import com.rograndec.feijiayun.chain.business.storage.displaycheck.entity.GoodsDisplayCheck;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsDisplayCheckMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsDisplayCheck record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsDisplayCheck record);

    /**
     *
     * @mbg.generated
     */
    GoodsDisplayCheck selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsDisplayCheck record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsDisplayCheck record);

    List<GoodsDisplayCheck> getCheckOrderList(@Param("enterpriseId") Long enterpriseId, @Param("checkOrderType") Integer checkOrderType, @Param("orderName") String orderName, @Param("orderType") String orderType, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("warehouseAreaId") Long warehouseAreaId, @Param("maintanceType") Integer maintanceType, @Param("goodsType") Integer goodsType, @Param("code") String code, @Param("pageSize") Integer pageSize, @Param("start") Integer start);
}