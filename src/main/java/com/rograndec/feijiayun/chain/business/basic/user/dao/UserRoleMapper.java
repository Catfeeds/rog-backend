package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    int deleteByUserId(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(UserRole record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserRole record);

    /**
     *
     * @mbg.generated
     */
    UserRole selectByPrimaryKey(Long id);

    List<UserRole> selectByUserId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserRole record);

    List<UserRole> selectByEnterpriseIdByRoleId(@Param("enterpriseId") Long enterpriseId, @Param("roleId") Long roleId);
}