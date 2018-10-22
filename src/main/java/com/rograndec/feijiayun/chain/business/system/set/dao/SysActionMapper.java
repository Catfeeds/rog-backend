package com.rograndec.feijiayun.chain.business.system.set.dao;

import com.rograndec.feijiayun.chain.business.auth.menu.vo.TreeAction;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysAction;
import com.rograndec.feijiayun.chain.common.vo.BaseTreeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysActionMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SysAction record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysAction record);

    /**
     *
     * @mbg.generated
     */
    SysAction selectByPrimaryKey(Long id);

    List<TreeAction> selectIdAndParentIdAll();

    List<BaseTreeVO> selectByEnterpriseAndType(@Param("type") Integer type);

    List<BaseTreeVO> selectByEnterpriseIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysAction record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysAction record);

    List<SysAction> selectByenterpriseIdByParentActionId(Map param);

    List<SysAction> selectByenterpriseId(Long enterpriseId);

    List<SysAction> selectByEnterpriseId(Long enterpriseId);

    List<SysAction> selectByenterpriseIdByRoleId(Map<String, Long> param);

    SysAction selectByCodeAndEnterpriseIdAndParentId(@Param("code") String code,@Param("enterpriseId") Long enterpriseId,@Param("parentId") Long parentId);

}