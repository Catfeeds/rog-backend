/**
 *
 */
package com.rograndec.feijiayun.chain.business.system.set.service.impl;

import com.rograndec.feijiayun.chain.business.auth.menu.vo.RoleMenuFormVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.business.system.set.service.PermissionSettingsService;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.collection.DeepCloneListUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.*;

/**

 * @Description:TODO

 * @author:Dong.ma

 * @time:2017年8月22日 上午10:14:32

 */
@Service
public class PermissionSettingsServiceImpl implements PermissionSettingsService{
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    SysActionMapper sysActionMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysRoleActionMapper sysRoleActionMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private EnterpriseBusinessMapper  businessMapper;

    @Override
    public List<Department> getDepartment(UserVO user) throws Exception {

        Long enterpriseId = user.getEnterpriseId();

        if (user.getChainType() == ChainType.Selfoperatedshop.getCode()){// 分店获取的是总部的
            enterpriseId = user.getParentId();
        }


        List<Department> departments = departmentMapper.selectByEnterpriseId(enterpriseId);
        List<Department> copyDepartment = DeepCloneListUtils.deepCopy(departments);
        Iterator<Department> it1 = copyDepartment.iterator();
        while (it1.hasNext()){
            Department d1 = it1.next();
            Iterator<Department> it = departments.iterator();
            while(it.hasNext()){
                Department d = it.next();
                if(d.getId().equals(d1.getParentDeptId())){
                    it.remove();
                }
            }
        }
        return departments;
    }

    @Override
    public List<Position> getPosition(UserVO user, Long deptId) throws Exception {
        Long enterpriseId = user.getEnterpriseId();
        if (user.getChainType() == ChainType.Selfoperatedshop.getCode()){// 分店获取的是总部的
            enterpriseId = user.getParentId();
        }
        List<Long> depIds = new ArrayList<>();
        depIds.add(deptId);
        return positionMapper.selectPositionsByDepartments(depIds,enterpriseId);
    }

  @Override
    public List<SysRole> getRoleByPosition(Long pId) throws Exception {
        return sysRoleMapper.selectByPosition(pId);
    }

    @Override
    public List<SysRole> getRoleByPositions(List<Long> pId, UserVO userVO) throws Exception {
        Long enterpriseId = null;
        Long parentId = null;
        if(userVO.getChainType() == ChainType.Headquarters.getCode()){
            //总部
            enterpriseId = userVO.getEnterpriseId();
            parentId = userVO.getEnterpriseId();
        } else{
            //
            enterpriseId = userVO.getEnterpriseId();
            EnterpriseBusiness enterpriseBusiness = businessMapper.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
            Integer permissionSet = enterpriseBusiness.getPermissionSet();
            if(permissionSet == 1){
                /**
                 * 企业用户的权限管理，自营店或加盟店“权限管理”选择“自主控制”，允许编辑，可自定义角色，加盟店或
                 自营店除显示默认角色、总部自定义角色外还显示本店增加的角色，本店增加的角色仅本店使用，总部和其它门店
                 不可见，否则只读(读取总部设置)
                 */
                enterpriseId = userVO.getEnterpriseId();
                parentId = userVO.getParentId();
            } else {
                enterpriseId = userVO.getParentId();
                parentId = userVO.getParentId();
            }

        }
        return sysRoleMapper.selectByPositions(pId,enterpriseId,parentId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveAll(UserVO loginUser, SysRole role, List<SysAction> actions) throws Exception {

        EnterpriseBusiness enterpriseBusiness = businessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());

        Integer permissionSet = enterpriseBusiness.getPermissionSet();//权限控制
        if(!loginUser.getChainType().equals(ChainType.Headquarters.getCode())
                && permissionSet == 0) {// 总部控制
            throw  new BusinessException(SysCode.FAIL.getCode(),"当前门店权限控制为总部控制，不允许新增自己的权限");
        }


        List<SysRole> sysRoles = sysRoleMapper.selectByEnterpriseId(loginUser.getEnterpriseId());
        for (SysRole sysRole : sysRoles){
            if(sysRole.getName().equals(role.getName())){
                throw new BusinessException("角色名称重复!");
            }
            if(sysRole.getCode().equals(role.getCode())){
                throw new BusinessException("角色编码重复!");
            }
        }
        UserEnterpriseUtils.setUserCreateOrModify(role,loginUser,true);
        role.setEnterpriseId(loginUser.getEnterpriseId());
        role.setParentId(loginUser.getParentId());
        role.setChainType(loginUser.getChainType());
        role.setShowOrder(1);
        role.setSysType(0);
        role.setStatus(1);
        sysRoleMapper.insertSelective(role);
        for (SysAction action : actions ) {
            sysRoleActionMapper.insertSelective(createRoleAction(role,action.getId(),loginUser));
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAll(UserVO loginUser, SysRole sysRole, List<SysRoleAction> sysRoleActions) throws Exception {
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        for (SysRoleAction sysRoleAction:sysRoleActions) {
            sysRoleAction.setChainType(loginUser.getParentId()==0?0:1);
        }
//        for (SysRoleAction sysRoleAction:sysRoleActions) {
//            sysRoleActionMapper.deleteByPrimaryKey(sysRoleAction.getId());
//
//        }
        Map param = new HashMap();
//        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("roleId",sysRole.getId());
        sysRoleActionMapper.deleteByenterpriseIdByroleId(param);
        for (SysRoleAction sysRoleAction:sysRoleActions) {
            sysRoleAction.setId(null);
            sysRoleAction.setModifierId(loginUser.getUserId());
            sysRoleAction.setModifierCode(loginUser.getUserCode());
            sysRoleAction.setModifierName(loginUser.getUserName());
            sysRoleActionMapper.insertSelective(sysRoleAction);
        }
        return 0;
    }

    @Override
    public List<SysRoleAction> getChooseActions(Long enterpriseId, Long roleId) throws Exception {
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        param.put("roleId",roleId);
        return sysRoleActionMapper.selectByEnterpriseIdByRoleId(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteActions(Long enterpriseId, Long roleId) throws Exception{
        if(!isDelete(enterpriseId,roleId)) {
            throw new BusinessException("该角色已关联人员,不允许删除~!");
        }
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        param.put("roleId",roleId);
        sysRoleActionMapper.deleteByenterpriseIdByroleId(param);
        sysRoleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setMenus2Role(RoleMenuFormVO roleMenuFormVO,UserVO userVO) throws Exception {

        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",userVO.getEnterpriseId());
        param.put("roleId",roleMenuFormVO.getId());
        sysRoleActionMapper.deleteByenterpriseIdByroleId(param);


        List<SysRoleAction> sysRoleActions = SysRoleAction.getSysRoleActions(roleMenuFormVO, userVO);

        for(SysRoleAction sysRoleAction : sysRoleActions){
            sysRoleActionMapper.insertSelective(sysRoleAction);
        }

    }


    @Override
    public SysRole getRole(Long enterpriseId, Long roleId) throws Exception{
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        param.put("roleId",roleId);
        return sysRoleMapper.selectByEnterpriseIdByRoleId(param);
    }

    @Override
    public List<SysRole> getAllRoles(Long enterpriseId) throws Exception {
        return sysRoleMapper.selectByEnterpriseId(enterpriseId);
    }

    private SysRoleAction createRoleAction(SysRole role,Long actionId,UserVO loginUser) throws Exception{
        SysRoleAction sysRoleAction = new SysRoleAction();
        sysRoleAction.setRoleId(role.getId());
        sysRoleAction.setActionId(actionId);
        sysRoleAction.setEnterpriseId(loginUser.getEnterpriseId());
        sysRoleAction.setParentId(loginUser.getParentId());
        sysRoleAction.setChainType(role.getChainType());
        sysRoleAction.setCreaterId(loginUser.getUserId());
        UserEnterpriseUtils.setUserCreateOrModify(sysRoleAction,loginUser,true);
        return sysRoleAction;
    }

    @Override
    public Map<Map<String,Long>,List<SysAction>> getAction(Long enterpriseId) throws Exception {
        Map<Map<String,Long>,List<SysAction>> result = new HashMap<>();
        Map<String,Long> param = new HashMap<>();
        Map<String,Long> parentInfo = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        param.put("parentActionId",0l);
        parentInfo.put("parentActionId",0l);
        List<SysAction> p_action = sysActionMapper.selectByenterpriseIdByParentActionId(param);//获取所有根菜单
        result.put(parentInfo,p_action);
        getChildAction(p_action,param,result);
        return result;
    }

    private void getChildAction(List<SysAction> p_action,Map<String,Long> param,Map<Map<String,Long>,List<SysAction>> result)throws Exception {
        for (SysAction action:p_action) {
            param.replace("parentActionId",action.getId());
            List<SysAction> c_action = sysActionMapper.selectByenterpriseIdByParentActionId(param);//获取下级菜单信息
            Map<String,Long> parentInfo = new HashMap<>();
            parentInfo.put("parentActionId",action.getId());
            if(!c_action.isEmpty() || c_action.size() != 0)
                result.put(parentInfo,c_action);
            getChildAction(c_action,param,result);
        }
    }

    @Override
    public Tree<List<SysAction>> getActions(Long enterpriseId) throws Exception {
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        param.put("parentActionId",0l);
        Tree<List<SysAction>> tree = new Tree<>();
        List<SysAction> p_action = sysActionMapper.selectByenterpriseIdByParentActionId(param);//获取所有根菜单
        getChileActions(p_action,param,tree);
        return tree;
    }

    private void getChileActions(List<SysAction> p_action,Map<String,Long> param,Tree tree) throws Exception{
        for (SysAction action: p_action) {
            param.replace("parentActionId",action.getId());
            tree.setData(action);
            List<SysAction> c_action = sysActionMapper.selectByenterpriseIdByParentActionId(param);//获取下级菜单信息
            if(!c_action.isEmpty() || c_action.size() != 0)
                tree.setChildren(c_action);
            getChileActions(tree.getChildren(),param,tree);
        }
    }

    @Override
    public List<TreePOJO<SysAction>> getTree(UserVO loginUser,Long roleId,Boolean checked) throws Exception {
        Long enterpriseId = loginUser.getEnterpriseId();

        if(roleId != -1){
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
            if(sysRole == null){
                throw  new  BusinessException("当前角色不存在");
            }
            enterpriseId = sysRole.getEnterpriseId();
        }


        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        List<TreePOJO<SysAction>> listParent = new ArrayList<>();
        List<SysAction> p_action = null;
        List<SysAction> select_action = null;

        if(roleId == -1) {
            p_action = sysActionMapper.selectByenterpriseId(enterpriseId);//获取所有菜单
        }else if(roleId != -1 && !checked){
            param.put("roleId",roleId);
            p_action = sysActionMapper.selectByenterpriseIdByRoleId(param);
        }else if(roleId != -1 && checked){
            param.put("roleId",roleId);
            p_action = sysActionMapper.selectByenterpriseId(enterpriseId);
            select_action = sysActionMapper.selectByenterpriseIdByRoleId(param);
        }
        for(SysAction action : p_action){
            //isParent 为 1 并且ParentActionId 为0 的是根功能
            if(action.getIsParent() == 1 && action.getParentActionId() == 0l && addChildCheck(loginUser.getChainType(),action)){
                TreePOJO<SysAction> t = new TreePOJO();
                t.setData(action);
//                t.setLabel(action.getName()+"-"+action.getCode());
                t.setId(action.getId());
                t.setParentId(action.getParentActionId()==0l?null:action.getParentActionId());
                listParent.add(t);
            }
        }
        for(TreePOJO<SysAction> tree : listParent){
            SysAction action = (SysAction)tree.getData();
            List<TreePOJO<SysAction>> chileren = getChild(action.getId(),p_action,loginUser);
            tree.setChildren(chileren);
        }
        List<TreePOJO<SysAction>> trees = getlistActions(listParent);
        if(roleId != -1 && checked){
            trees = getSelectTree(trees,select_action);
        }
//        else if(roleId == -1 && checked){
//            trees = getSelectTree(trees);
//        }
        recursive = 0;
        return trees;
    }
    private List<TreePOJO<SysAction>> getChild(Long id, List<SysAction> p_action, UserVO loginUser) {
        List<TreePOJO<SysAction>> childList = new ArrayList<>();
        for(SysAction action : p_action){
            if(action.getParentActionId().equals(id) && addChildCheck(loginUser.getChainType(),action)){
                TreePOJO<SysAction> tree = new TreePOJO<>();
                tree.setData(action);
                tree.setId(action.getId());
//                tree.setLabel(action.getName()+"-"+action.getCode());
                tree.setParentId(action.getParentActionId()==0l?null:action.getParentActionId());
                childList.add(tree);
            }
        }
        for(TreePOJO<SysAction> tree : childList){
            for(SysAction action : p_action){
                SysAction sysAction = tree.getData();
                if(sysAction.getId().equals(action.getParentActionId()) && addChildCheck(loginUser.getChainType(),action)){
                    tree.setChildren(getChild(sysAction.getId(),p_action,loginUser));
                }
            }
        }
        return childList;
    }

    volatile int recursive = 0;//记录递归深度
    private List<TreePOJO<SysAction>> getlistActions(List<TreePOJO<SysAction>> listParent){
//        recursive++;
        for(int i = 0;i<listParent.size();i++){
            TreePOJO<SysAction> tree = listParent.get(i);
//            tree.setLabel("第"+recursive+"级节点");
            tree.setLabel(tree.getData().getName()+"-"+tree.getData().getCode());
            if(tree.getData().getEnterpriseId()==0l){
                tree.setNodeShowDelete(false);
                tree.setSupplierShow(false);
            }
            if(tree.getChildren() == null){
                tree.setLeaf(true);
                tree.setChildren(new ArrayList<>());
            }else if(tree.getChildren().isEmpty() || tree.getChildren().size() == 0){
                tree.setLeaf(true);
            }else {
                getlistActions(tree.getChildren());
            }
        }
//        recursive=1;
        return listParent;
    }

    private List<TreePOJO<SysAction>> getSelectTree(List<TreePOJO<SysAction>> allList,List<SysAction> selectAction){
        for(TreePOJO<SysAction> tree : allList){
            if(selectAction.contains((SysAction)tree.getData())){
                tree.setChecked(true);
            }
            getSelectTree(tree.getChildren(),selectAction);
        }
        return allList;
    }
//    private List<TreePOJO<SysAction>> getSelectTree(List<TreePOJO<SysAction>> allList){
//        for(TreePOJO<SysAction> tree : allList){
//            tree.setChecked(true);
//            if(tree.getChildren() != null || tree.getChildren().isEmpty() || tree.getChildren().size()==0){
//                getSelectTree(tree.getChildren());
//            }
//        }
//        return allList;
//    }

    @Override
    public Boolean checkDeleteRoleAction(Long enterpriseId, Long roleId) {
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);
        param.put("roleId",roleId);
        List<SysRole> role = sysRoleMapper.checkDeleteRoleActionByenterpriseIdByroleId(param);
        if(role.size()==0 || role.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<TreePOJO<SysRole>> getRoleTree4Report(Long enterpriseId) throws Exception {

        List<TreePOJO<SysRole>> treeList = new ArrayList<>();
        List<SysRole> roles =  sysRoleMapper.selectByEnterpriseId(enterpriseId);
        Set<Long> deptIds = new HashSet<>();
        Map<Long,Long> positionIds = new HashMap<>();
        for(SysRole role : roles){
            deptIds.add(role.getDeptId());
            positionIds.put(role.getPositionId(),role.getDeptId());
        }
        for(Long deptId : deptIds){
            TreePOJO tree = new TreePOJO();
            tree.setId(deptId);
            tree.setParentId(null);
            Department d = departmentMapper.selectByPrimaryKey(deptId);
            tree.setLabel(d.getName());
            tree.setData(new SysRole());
            treeList.add(tree);
        }

        for(TreePOJO<SysRole> tree : treeList){
            List<TreePOJO<SysRole>> childList = new ArrayList<>();
            Iterator<Map.Entry<Long,Long>> it = positionIds.entrySet().iterator();

            while(it.hasNext()){
                Map.Entry<Long,Long> entry = it.next();
                TreePOJO<SysRole> t = new TreePOJO<>();
                if(tree.getId().equals(entry.getValue())){
                    t.setId(entry.getKey());
                    t.setParentId(entry.getValue());
                    t.setLabel(positionMapper.selectByPrimaryKey(entry.getKey()).getName());
                    t.setData(new SysRole());
                    childList.add(t);
                }
            }
            tree.setChildren(childList);
        }

        for(TreePOJO<SysRole> tree : treeList){
            for(TreePOJO<SysRole> t : tree.getChildren()){
                List<TreePOJO<SysRole>> childList = new ArrayList<>();
                for(SysRole role : roles){
                    TreePOJO<SysRole> t1 = new TreePOJO<>();
                    if(role.getPositionId().equals(t.getId()) && role.getDeptId().equals(t.getParentId())){
                        t1.setId(role.getId());
                        t1.setParentId(t.getId());
                        t1.setLabel(role.getName());
                        t1.setData(role);
                        t1.setLeaf(true);
                        t1.setChildren(new ArrayList<>());
                        childList.add(t1);
                    }
                }
                t.setChildren(childList);
            }
        }

        return treeList;
    }

    @Override
    public List<TreePOJO<SysRole>> getRoleTree(UserVO loginUser) throws Exception {
        Long enterpriseId = loginUser.getEnterpriseId();


        Map<String,Object> param = new HashMap<>();
        List<Long> enterpriseIdList = new ArrayList<>();
        enterpriseIdList.add(0L);// 系统默认
        // dongyang.du
        // 门店信息“权限管理”选择“自主控制”，则门店除读取总部信息外还允许新增仅属于自己门店的权限，总部和其它门店不可见，否则仅读取总部设置

        EnterpriseBusiness enterpriseBusiness = businessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());

        Integer permissionSet = enterpriseBusiness.getPermissionSet();//权限控制
        if(!loginUser.getChainType().equals(ChainType.Headquarters.getCode())
                && permissionSet == 0) {// 总部控制,读取总部信息
            enterpriseId = loginUser.getParentId();
        } else {
            enterpriseIdList.add(loginUser.getParentId());
        }

        enterpriseIdList.add(enterpriseId);
        param.put("enterpriseIdList",enterpriseIdList);

        List<TreePOJO<SysRole>> treeList = new ArrayList<>();
        //List<SysRole> roles =  //sysRoleMapper.selectByEnterpriseId(enterpriseId);
        List<SysRole> roles = sysRoleMapper.selectByParamMap(param);


        Set<Long> deptIds = new HashSet<>();
        Map<Long,Long> positionIds = new HashMap<>();
        for(SysRole role : roles){
            deptIds.add(role.getDeptId());
            positionIds.put(role.getPositionId(),role.getDeptId());
        }
        for(Long deptId : deptIds){
            TreePOJO tree = new TreePOJO();
            tree.setId(deptId);
            tree.setParentId(null);
            Department d = departmentMapper.selectByPrimaryKey(deptId);
            tree.setLabel(d.getName());
            tree.setData(new SysRole());
            tree.setNodeShowDelete(false);
            tree.setSupplierShow(false);
            treeList.add(tree);
        }

        for(TreePOJO<SysRole> tree : treeList){
            List<TreePOJO<SysRole>> childList = new ArrayList<>();
            Iterator<Map.Entry<Long,Long>> it = positionIds.entrySet().iterator();

            while(it.hasNext()){
                Map.Entry<Long,Long> entry = it.next();
                TreePOJO<SysRole> t = new TreePOJO<>();
                if(tree.getId().equals(entry.getValue())){
                    t.setNodeShowDelete(tree.getNodeShowDelete());
                    t.setSupplierShow(tree.getSupplierShow());
                    t.setId(entry.getKey());
                    t.setParentId(entry.getValue());
                    t.setLabel(positionMapper.selectByPrimaryKey(entry.getKey()).getName());
                    t.setData(new SysRole());
                    childList.add(t);
                }
            }
            tree.setChildren(childList);
        }

        for(TreePOJO<SysRole> tree : treeList){
            for(TreePOJO<SysRole> t : tree.getChildren()){
                List<TreePOJO<SysRole>> childList = new ArrayList<>();
                for(SysRole role : roles){
                    TreePOJO<SysRole> t1 = new TreePOJO<>();
                    if(role.getPositionId().equals(t.getId()) && role.getDeptId().equals(t.getParentId())){
                        if(role.getEnterpriseId() == 0l){
                            t1.setNodeShowDelete(false);
                            t1.setSupplierShow(false);
                        }else {
                            t1.setNodeShowDelete(true);
                            t1.setSupplierShow(true);
                        }
                        t1.setSeeDelete(true);
                        t1.setSeeUpdate(true);
                        t1.setId(role.getId());
                        t1.setParentId(t.getId());
                        t1.setLabel(role.getName());
                        t1.setData(role);
                        t1.setLeaf(true);
                        t1.setChildren(new ArrayList<>());
//                        if(loginUser.getParentId() != 0 && permissionSet == 0){//如果是门店登录,总部控制,则不允许修改删除
//                            t1.setSupplierShow(false);
//                            t1.setNodeShowDelete(false);
//                            t1.setNodeShowRemove(false);
//                            t1.setNodeShowRelateBranch(false);
//                            t1.setSeeDelete(false);
//                            t1.setSeeUpdate(false);
//                        }

                        if(!loginUser.getEnterpriseId().equals(role.getEnterpriseId())){// 不是自家企业，不允许编辑
                            t1.setSupplierShow(false);
                            t1.setNodeShowDelete(false);
                            t1.setNodeShowRemove(false);
                            t1.setNodeShowRelateBranch(false);
                            t1.setSeeDelete(false);
                            t1.setSeeUpdate(false);
                        }

                        if(!isDelete(loginUser.getEnterpriseId(),role.getId())){
                            t1.setNodeShowDelete(false);
                        }
                        childList.add(t1);
                    }
                }
                t.setChildren(childList);
            }
        }

        return treeList;
    }

    private boolean isDelete(Long enterpriseId, Long roleId) throws Exception{
        List<UserRole> userRoles = userRoleMapper.selectByEnterpriseIdByRoleId(enterpriseId,roleId);
        return userRoles.isEmpty();
    }

    @Override
    public List<TreePOJO<SysRole>> getRoleByPositionIds(List<Long> ids) {
        List<TreePOJO<SysRole>> treeList = new ArrayList<>();
        //List<SysRole> roles =  sysRoleMapper.selectByEnterpriseId(enterpriseId);
        List<SysRole> roles = new ArrayList<SysRole>();
        if (ids != null && ids.size() > 0){
            for (Long id:ids) {
                List<SysRole> s = sysRoleMapper.selectRoleByPositionId(id);
                for (SysRole role:s) {
                    roles.add(role);
                }
            }
        }
        Set<Long> deptIds = new HashSet<>();
        Map<Long,Long> positionIds = new HashMap<>();
        for(SysRole role : roles){
            deptIds.add(role.getDeptId());
            positionIds.put(role.getPositionId(),role.getDeptId());
        }
        for(Long deptId : deptIds){
            TreePOJO tree = new TreePOJO();
            tree.setId(deptId);
            tree.setParentId(null);
            Department d = departmentMapper.selectByPrimaryKey(deptId);
            tree.setLabel(departmentMapper.selectByPrimaryKey(deptId).getName());
            tree.setData(new SysRole());
            treeList.add(tree);
        }

        for(TreePOJO<SysRole> tree : treeList){
            List<TreePOJO<SysRole>> childList = new ArrayList<>();
            Iterator<Map.Entry<Long,Long>> it = positionIds.entrySet().iterator();

            while(it.hasNext()){
                Map.Entry<Long,Long> entry = it.next();
                TreePOJO<SysRole> t = new TreePOJO<>();
                if(tree.getId().equals(entry.getValue())){
                    t.setId(entry.getKey());
                    t.setParentId(entry.getValue());
                    t.setLabel(positionMapper.selectByPrimaryKey(entry.getKey()).getName());
                    t.setData(new SysRole());
                    childList.add(t);
                }
            }
            tree.setChildren(childList);
        }

        for(TreePOJO<SysRole> tree : treeList){
            for(TreePOJO<SysRole> t : tree.getChildren()){
                List<TreePOJO<SysRole>> childList = new ArrayList<>();
                for(SysRole role : roles){
                    TreePOJO<SysRole> t1 = new TreePOJO<>();
                    if(role.getPositionId().equals(t.getId()) && role.getDeptId().equals(t.getParentId())){
                        t1.setId(role.getId());
                        t1.setParentId(t.getId());
                        t1.setLabel(role.getName());
                        t1.setData(role);
                        t1.setLeaf(true);
                        t1.setChildren(new ArrayList<>());
                        childList.add(t1);
                    }
                }
                t.setChildren(childList);
            }
        }

        return treeList;
    }


    /**
     * 导出
     *
     * @param output
     */
    @Override
    public void exportRoleExcel(OutputStream output, UserVO user, Long enterpriseId) throws Exception {

        List<SysRole> roles =  sysRoleMapper.selectByEnterpriseId(enterpriseId);

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());//企业
        List<String> headerList = new ArrayList<>();
        headerList.add(enterprise.getName());
        headerList.add("角色");


        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
        rowHeaderMap.put("code","角色编码");
        rowHeaderMap.put("name","角色名称");

        StringBuilder endString = new StringBuilder();

        // 单元格合并
        // 四个参数分别是：起始行，起始列，结束行，结束列
//        CellRangeAddress r1 = new CellRangeAddress(0, (short) 0, 0,
//                (short) (rowHeaderMap.size()-1));
//
//        CellRangeAddress r2 = new CellRangeAddress(1, (short) 1, 1,
//                (short) (rowHeaderMap.size()-1));

        purchaseGeneralComponent.commExcelExport(
                output
                ,rowHeaderMap
                ,roles
                ,headerList
                ,new ArrayList<>()
                ,endString.toString()
                ,true
                ,new ArrayList<>()
        );
    }

    @Override
    public List<ConnectTreePOJO<SysRole>> getConnectRoleByPositionIds(List<Long> ids) {
        List<ConnectTreePOJO<SysRole>> treeList = new ArrayList<>();
        //List<SysRole> roles =  sysRoleMapper.selectByEnterpriseId(enterpriseId);
        List<SysRole> roles = new ArrayList<SysRole>();
        if (ids != null && ids.size() > 0){
            for (Long id:ids) {
                List<SysRole> s = sysRoleMapper.selectRoleByPositionId(id);
                for (SysRole role:s) {
                    roles.add(role);
                }
            }
        }
        Set<Long> deptIds = new HashSet<>();
        Map<Long,Long> positionIds = new HashMap<>();
        for(SysRole role : roles){
            deptIds.add(role.getDeptId());
            positionIds.put(role.getPositionId(),role.getDeptId());
        }
        for(Long deptId : deptIds){
            ConnectTreePOJO tree = new ConnectTreePOJO();
            tree.setValue(deptId);
            tree.setParentId(null);
            Department d = departmentMapper.selectByPrimaryKey(deptId);
            tree.setLabel(departmentMapper.selectByPrimaryKey(deptId).getName());
            tree.setData(new SysRole());
            treeList.add(tree);
        }

        for(ConnectTreePOJO<SysRole> tree : treeList){
            List<ConnectTreePOJO<SysRole>> childList = new ArrayList<>();
            Iterator<Map.Entry<Long,Long>> it = positionIds.entrySet().iterator();

            while(it.hasNext()){
                Map.Entry<Long,Long> entry = it.next();
                ConnectTreePOJO<SysRole> t = new ConnectTreePOJO<>();
                if(tree.getValue().equals(entry.getValue())){
                    t.setValue(entry.getKey());
                    t.setParentId(entry.getValue());
                    t.setLabel(positionMapper.selectByPrimaryKey(entry.getKey()).getName());
                    t.setData(new SysRole());
                    childList.add(t);
                }
            }
            tree.setChildren(childList);
        }

        for(ConnectTreePOJO<SysRole> tree : treeList){
            for(ConnectTreePOJO<SysRole> t : tree.getChildren()){
                List<ConnectTreePOJO<SysRole>> childList = new ArrayList<>();
                for(SysRole role : roles){
                    ConnectTreePOJO<SysRole> t1 = new ConnectTreePOJO<>();
                    if(role.getPositionId().equals(t.getValue()) && role.getDeptId().equals(t.getParentId())){
                        t1.setValue(role.getId());
                        t1.setParentId(t.getValue());
                        t1.setLabel(role.getName());
                        t1.setData(role);
                        t1.setLeaf(true);
                        t1.setChildren(new ArrayList<>());
                        childList.add(t1);
                    }
                }
                t.setChildren(childList);
            }
        }

        return treeList;
    }

    @Override
    public boolean checkExistsCodeName(SysRole role, UserVO loginUser) throws Exception {
        List<SysRole> sysRoles = sysRoleMapper.selectByEnterpriseId(loginUser.getEnterpriseId());
        for (SysRole sysRole : sysRoles){
            if(sysRole.getName().equals(role.getName()) || sysRole.getCode().equals(role.getCode())){
                return true;
            }
        }
        return false;
    }

    private boolean addChildCheck(Integer chainType,SysAction sysAction){

        /**
         *用于自营店（0-否；1-是）
         */
        int forBranch = sysAction.getForBranch();

        /**
         *用于加盟店（0-否；1-是）
         */
        int forLeague = sysAction.getForLeague();

        /**
         *用于总部（0-否；1-是）
         */
        int forParent = sysAction.getForParent();

        switch (chainType){
            case 0: //总部
                if(forParent == 1){
                    return true;
                }
            case 1: //自营店
                if(forBranch == 1){
                    return true;
                }
            case 2: //加盟店
                if(forLeague == 1){
                    return true;
                }
            default:
                return false;
        }
    }
}
