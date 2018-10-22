package com.rograndec.feijiayun.chain.business.auth.login.dao;

import com.rograndec.feijiayun.chain.business.auth.register.entity.LoginLog;
import org.apache.ibatis.annotations.Param;

public interface LoginLogMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(LoginLog record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(LoginLog record);

    /**
     *
     * @mbg.generated
     */
    LoginLog selectByPrimaryKey(Long id);

    LoginLog selectByDescAccount(String loginAccount);

    Integer selectLoginCount(@Param("loginAccount") String loginAccount);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LoginLog record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LoginLog record);
}