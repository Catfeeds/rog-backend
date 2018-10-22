package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserQualificationConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserQualificationConfigMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(UserQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    UserQualificationConfig selectByPrimaryKey(Long id);

    List<UserQualificationConfig> selectByTypeMust(List<Long> list);

    List<UserQualificationConfig> selectByUserId(@Param("userId") Long userId,@Param("enterpriseId") Long enterpriseId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserQualificationConfig record);
}