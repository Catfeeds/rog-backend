package com.rograndec.feijiayun.chain.business.system.set.dao;

import com.rograndec.feijiayun.chain.business.system.set.entity.SysRoleAction;

import java.util.List;
import java.util.Map;

public interface SysRoleActionMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SysRoleAction record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysRoleAction record);

    /**
     *
     * @mbg.generated
     */
    SysRoleAction selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysRoleAction record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysRoleAction record);

    List<SysRoleAction> selectByEnterpriseIdByRoleId(Map<String, Long> param);

    List<SysRoleAction> selectByEnterpriseIdByRoleIds(Map<String, Object> param);

    int deleteByenterpriseIdByroleId(Map<String, Long> param);

    List<SysRoleAction> selectByEnterpriseId(Long enterpriseId);
}