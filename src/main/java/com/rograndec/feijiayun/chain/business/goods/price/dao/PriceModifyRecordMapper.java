package com.rograndec.feijiayun.chain.business.goods.price.dao;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceModifyRecord;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceModifyRecordWithBLOBs;

import java.util.List;
import java.util.Map;

public interface PriceModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PriceModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PriceModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    PriceModifyRecordWithBLOBs selectByPrimaryKey(Long id);

    List<PriceModifyRecordWithBLOBs> selectByParam(Map map);
    Long selectByParamCount(Map map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PriceModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(PriceModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PriceModifyRecord record);
}