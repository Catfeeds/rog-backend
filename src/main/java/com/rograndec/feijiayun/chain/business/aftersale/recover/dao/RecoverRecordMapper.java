package com.rograndec.feijiayun.chain.business.aftersale.recover.dao;

import com.rograndec.feijiayun.chain.business.aftersale.recover.entity.RecoverRecord;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverRecordVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RecoverRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RecoverRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RecoverRecord record);

    /**
     *
     * @mbg.generated
     */
    RecoverRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RecoverRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RecoverRecord record);

    /**
     * 根据条件搜索追回记录列表
     * @param map
     * @return
     */
    List<RecoverRecordVO> getRecoverRecordPage(Map<String, Object> map);

    /**
     * 通过追回记录ID查询
     * @param id
     * @return
     */
    RecoverRecordVO getRecoverPlanWithId(Long id);

    List<RecoverRecord> selectWithRecoverPlanId(@Param("baseOrderId") Long id, @Param("enterpriseId") Long enterpriseId);
}