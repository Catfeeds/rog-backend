package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserDept;

public interface UserDeptMapper {
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
    int insert(UserDept record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserDept record);

    /**
     *
     * @mbg.generated
     */
    UserDept selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserDept record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserDept record);
}