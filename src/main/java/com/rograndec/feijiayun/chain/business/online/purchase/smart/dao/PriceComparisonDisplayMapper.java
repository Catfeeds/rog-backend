package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.PriceComparisonDisplay;

import java.util.List;

public interface PriceComparisonDisplayMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PriceComparisonDisplay record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PriceComparisonDisplay record);

    /**
     *
     * @mbg.generated
     */
    PriceComparisonDisplay selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PriceComparisonDisplay record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PriceComparisonDisplay record);

    List<PriceComparisonDisplay> selectByAreaId(Integer areaId);

    List<PriceComparisonDisplay> selectByCityId(Integer cityId);

    List<PriceComparisonDisplay> selectByProvinceId(Integer provinceId);
}