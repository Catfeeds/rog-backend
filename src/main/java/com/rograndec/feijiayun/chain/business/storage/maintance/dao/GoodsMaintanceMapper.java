package com.rograndec.feijiayun.chain.business.storage.maintance.dao;

import com.rograndec.feijiayun.chain.business.storage.maintance.entity.GoodsMaintance;
import com.rograndec.feijiayun.chain.business.storage.maintance.vo.ShowGoodsMaintanceVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsMaintanceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsMaintance record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsMaintance record);

    /**
     *
     * @mbg.generated
     */
    GoodsMaintance selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsMaintance record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsMaintance record);

    List<ShowGoodsMaintanceVO> getWillMaintanceOrderList(@Param("enterpriseId") Long enterpriseId, @Param("orderName") String orderName, @Param("orderType") String orderType, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<GoodsMaintance> getMaintanceOrderList(@Param("enterpriseId") Long enterpriseId, @Param("maintanceOrderType") Integer maintanceOrderType, @Param("orderName") String orderName, @Param("orderType") String orderType, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("warehouseAreaId") Long warehouseAreaId, @Param("maintanceType") Integer maintanceType, @Param("goodsType") Integer goodsType, @Param("code") String code, @Param("pageSize") Integer pageSize, @Param("start") Integer start);
}