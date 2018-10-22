package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPosition;

public interface UserPositionMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);
    int deleteByUserId(Long userId);

    /**
     *
     * @mbg.generated
     */
    int insert(UserPosition record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserPosition record);

    /**
     *
     * @mbg.generated
     */
    UserPosition selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserPosition record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserPosition record);
}