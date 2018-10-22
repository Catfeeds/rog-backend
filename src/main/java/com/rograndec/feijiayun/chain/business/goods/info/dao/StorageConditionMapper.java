package com.rograndec.feijiayun.chain.business.goods.info.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.StorageCondition;
import com.rograndec.feijiayun.chain.business.goods.info.vo.ResponseStorageConditionVO;

import java.util.List;

public interface StorageConditionMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StorageCondition record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StorageCondition record);

    /**
     *
     * @mbg.generated
     */
    StorageCondition selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StorageCondition record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StorageCondition record);

    List<ResponseStorageConditionVO> getStorageCondition(Long enterpriseId);
}