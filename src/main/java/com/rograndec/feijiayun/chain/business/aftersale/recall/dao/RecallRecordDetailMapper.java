package com.rograndec.feijiayun.chain.business.aftersale.recall.dao;

import com.rograndec.feijiayun.chain.business.aftersale.recall.entity.RecallRecordDetail;
import com.rograndec.feijiayun.chain.business.aftersale.recall.vo.RecallRecordDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecallRecordDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RecallRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RecallRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    RecallRecordDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RecallRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RecallRecordDetail record);

    int deleteByRecordId(@Param("recallId") Long recallId, @Param("enterpriseId") Long enterpriseId);

    List<RecallRecordDetailVO> selectByRecallId(@Param("recallId") Long recallId, @Param("enterpriseId") Long enterpriseId);

    int batchInsert(List<RecallRecordDetail> recallRecordDetails);
}