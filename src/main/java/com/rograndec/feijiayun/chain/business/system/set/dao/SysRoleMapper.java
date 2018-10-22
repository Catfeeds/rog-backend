package com.rograndec.feijiayun.chain.business.system.set.dao;

import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SysRole record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysRole record);

    /**
     *
     * @mbg.generated
     */
    SysRole selectByPrimaryKey(Long id);

    SysRole selectDefRoleByCode(String code);

    /**
     * in 查询
     * @param list
     * @return
     */
    List<SysRole> selectByIds(List<Long> list);

    List<SysRole> selectByPosition(Long positionId);

    List<SysRole> selectByPositions(@Param("list") List<Long> list, @Param("enterpriseId") Long enterpriseId, @Param("parentId") Long parentId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectByDepartmentIdByEnterpriseId(Map param);

    SysRole selectByEnterpriseIdByRoleId(Map<String, Long> param);

    List<SysRole> selectByEnterpriseId(Long enterpriseId);

    List<SysRole> checkDeleteRoleActionByenterpriseIdByroleId(Map<String, Long> param);

	SysRole selectDefaultRoleByParamMap(Map<String, Object> paramMap);

	List<SysRole> selectRoleByRoleCode(@Param("roleCode")String roleCode, @Param("enterpriseId")Long enterpriseId);

	SysRole selectDefRoleByRoleCode(@Param("roleCode")String roleCode);

    List<SysRole> selectRoleByPositionId(Long id);

    List<SysRole> selectALlByDepartmentIdByEnterpriseId(Map<String, Long> param);

    int selectRoleByDepartmentId(@Param("id") Long id);

    int getRoleByPosition(Long id);

    /**
     * 通过参数获取系统角色，分店自主控制- 获取自己，总部和系统默认的
     * dongyang.du
     * @param param
     * @return
     */
    List<SysRole> selectByParamMap(Map<String, Object> param);
}