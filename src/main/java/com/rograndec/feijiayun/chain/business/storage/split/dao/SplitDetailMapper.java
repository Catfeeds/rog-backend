package com.rograndec.feijiayun.chain.business.storage.split.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitDetail;
import com.rograndec.feijiayun.chain.business.storage.split.vo.ResponseSplitVO;

public interface SplitDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SplitDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SplitDetail record);

    /**
     *
     * @mbg.generated
     */
    SplitDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SplitDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SplitDetail record);
    
    /**
     * 根据id获取拆分明细详情
     * @param id
     * @return
     */
    ResponseSplitVO getSplitOrderDtlList(Long id); 
    
    
}