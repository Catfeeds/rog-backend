/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service;

import com.rograndec.feijiayun.chain.business.auth.menu.vo.RoleMenuFormVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月21日 下午8:01:46

 */
public interface PermissionSettingsService {
    List<Department> getDepartment(UserVO enterpriseId) throws Exception;
    Map<Map<String,Long>,List<SysAction>> getAction(Long enterpriseId) throws Exception ;
    List<Position> getPosition(UserVO userVO, Long deptId) throws Exception;

    List<SysRole> getRoleByPosition(Long pId) throws Exception;

    List<SysRole> getRoleByPositions(List<Long> pId, UserVO userVO) throws Exception;

    int saveAll(UserVO loginUser, SysRole role, List<SysAction> actions) throws Exception;
    int updateAll(UserVO loginUser, SysRole sysRole, List<SysRoleAction> sysRoleActions) throws Exception;
    List<SysRoleAction> getChooseActions(Long enterpriseId,Long roleId) throws Exception;
    void deleteActions(Long enterpriseId, Long roleId) throws Exception;

    void setMenus2Role(RoleMenuFormVO roleMenuFormVO, UserVO userVO) throws Exception;

    SysRole getRole(Long enterpriseId, Long roleId) throws Exception;
    List<SysRole> getAllRoles(Long enterpriseId) throws Exception;

    Tree<List<SysAction>> getActions(Long enterpriseId) throws Exception;

    List<TreePOJO<SysAction>> getTree(UserVO loginUser,Long roleId,Boolean checked) throws Exception;

    Boolean checkDeleteRoleAction(Long enterpriseId, Long roleId) throws Exception;

    List<TreePOJO<SysRole>> getRoleTree4Report(Long enterpriseId) throws Exception;

    List<TreePOJO<SysRole>> getRoleTree(UserVO loginUser) throws Exception;

    List<TreePOJO<SysRole>> getRoleByPositionIds(List<Long> ids) throws Exception;

    void exportRoleExcel(OutputStream output, UserVO user, Long enterpriseId) throws Exception;

    List<ConnectTreePOJO<SysRole>> getConnectRoleByPositionIds(List<Long> ids) throws Exception;

    boolean checkExistsCodeName(SysRole role, UserVO loginUser) throws Exception;
}
