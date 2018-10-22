package com.rograndec.feijiayun.chain.business.aftersale.recover.dao;

import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverRecordDetail;

import java.util.List;

public interface RecoverRecordDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RecoverRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RecoverRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    RecoverRecordDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RecoverRecordDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RecoverRecordDetail record);

    /**
     * 根据追回记录ID，查询追回记录明细
     * @param recoverId
     * @return
     */
    List<RecoverRecordDetail> getRecoverRecordDetailWithPlanId(Long recoverId);

    /**
     * 根据追回记录ID，删除追回记录明细
     * @param recoverId
     * @return
     */
    int deleteDetailWithRecoverId(Long recoverId);
}