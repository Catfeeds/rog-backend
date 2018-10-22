package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserModifyRecord;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserModifyRecordWithBLOBs;

import java.util.List;

public interface UserModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(UserModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    UserModifyRecordWithBLOBs selectByPrimaryKey(Long id);

    List<UserModifyRecordWithBLOBs> selectByUserId(Long userId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(UserModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserModifyRecord record);
}