package com.rograndec.feijiayun.chain.business.goods.price.dao;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjust;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustReturnVO;

import java.util.List;
import java.util.Map;

public interface PriceAdjustMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PriceAdjust record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PriceAdjust record);

    /**
     *
     * @mbg.generated
     */
    PriceAdjust selectByPrimaryKey(Long id);


    List<PriceAdjustReturnVO> selectByParam(Map<String,Object> map);

    List<PriceAdjustReturnVO> selectByParamNotApprovalFLow(Map<String,Object> map);

    List<PriceAdjustReturnVO> selectByApproval(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PriceAdjust record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PriceAdjust record);
}