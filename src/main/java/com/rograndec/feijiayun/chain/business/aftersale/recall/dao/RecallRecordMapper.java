package com.rograndec.feijiayun.chain.business.aftersale.recall.dao;

import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallRecord;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallRecordPageParamVO;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecallRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RecallRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RecallRecord record);

    /**
     *
     * @mbg.generated
     */
    RecallRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RecallRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RecallRecord record);

    List<RecallRecordVO> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("param") RecallRecordPageParamVO recallRecordPageParamVO);

    List<RecallRecord> selectByRecallPlanId(@Param("baseOrderId") Long id, @Param("enterpriseId") Long enterpriseId);
}