package com.rograndec.feijiayun.chain.business.goods.pharmacy.dao;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.IncompatibilityGoodsTwo;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.IncompatibilityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IncompatibilityGoodsTwoMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(IncompatibilityGoodsTwo record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(IncompatibilityGoodsTwo record);

    /**
     *
     * @mbg.generated
     */
    IncompatibilityGoodsTwo selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IncompatibilityGoodsTwo record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IncompatibilityGoodsTwo record);

    int deleteByKey(Long id);

    List<IncompatibilityVO> IncompatibilityVoByParam(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

}