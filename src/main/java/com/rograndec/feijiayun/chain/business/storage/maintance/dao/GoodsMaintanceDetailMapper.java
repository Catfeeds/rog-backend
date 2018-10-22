package com.rograndec.feijiayun.chain.business.storage.maintance.dao;

import com.rograndec.feijiayun.chain.business.storage.maintance.entity.GoodsMaintanceDetail;
import com.rograndec.feijiayun.chain.business.storage.maintance.vo.SelectMaintanceGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMaintanceDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsMaintanceDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsMaintanceDetail record);

    /**
     *
     * @mbg.generated
     */
    GoodsMaintanceDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsMaintanceDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsMaintanceDetail record);

    List<SelectMaintanceGoodsVO> selectMaintanceGoodsVO(@Param("enterpriseId") Long enterpriseId, @Param("param") String param, @Param("warehouseAreaId") Long warehouseAreaId, @Param("type") Integer type);

    List<GoodsMaintanceDetail> selectByMaintanceId(@Param("maintanceId") Long id);

    int batchInsert(List<GoodsMaintanceDetail> goodsMaintanceDetails);
}