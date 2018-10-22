package com.rograndec.feijiayun.chain.business.goods.pharmacy.dao;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.Incompatibility;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.IncompatibilityGoodsOne;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.IncompatibilityVO2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncompatibilityGoodsOneMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(IncompatibilityGoodsOne record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(IncompatibilityGoodsOne record);

    /**
     *
     * @mbg.generated
     */
    IncompatibilityGoodsOne selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IncompatibilityGoodsOne record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IncompatibilityGoodsOne record);

    int deleteByKey(Long id);

    List<IncompatibilityVO2> IncompatibilityVo2ByParam(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

}