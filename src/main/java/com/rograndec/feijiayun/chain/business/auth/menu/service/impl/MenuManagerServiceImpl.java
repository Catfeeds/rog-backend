package com.rograndec.feijiayun.chain.business.auth.menu.service.impl;

import com.rograndec.feijiayun.chain.business.auth.constant.ActionType;
import com.rograndec.feijiayun.chain.business.auth.menu.service.MenuManagerService;
import com.rograndec.feijiayun.chain.business.auth.menu.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysActionMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleActionMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysAction;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.vo.BaseTreeVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class MenuManagerServiceImpl implements MenuManagerService{

    @Autowired
    private SysActionMapper sysActionMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    /**
     *  新增和修改权限
     * @param userVO
     * @param menuFormVO
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void modify(UserVO userVO, MenuFormVO menuFormVO) throws Exception {

        SysAction sysAction = SysAction.getSysAction(userVO, menuFormVO);

        if(sysAction.getId() == null){
            sysActionMapper.insertSelective(sysAction);
        }else {
            sysActionMapper.updateByPrimaryKey(sysAction);
        }
    }


    @Override
    /**
     * 删除菜单,级联删除该菜单底下所有子菜单
     */
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long actionId){

        List<TreeAction> treeActions = sysActionMapper.selectIdAndParentIdAll();

        Map<Long, List<Long>> parentIdAndIdMap = generateParentIdAndChildIdMap(treeActions);

        Set<Long> deleteIds = new HashSet<>();

        List<Long> actionIds = parentIdAndIdMap.get(actionId);

        setDeleteId(parentIdAndIdMap,actionIds,deleteIds);

        deleteIds.add(actionId);

        deleteIds.forEach(deleteId -> {
            sysActionMapper.deleteByPrimaryKey(deleteId);
        });
    }

    /**
     * 递归添加子菜单id
     * @param parentIdAndIdMap parentId : id 的map
     * @param actionIds 需要递归从 parentIdAndIdMap获取的子菜单id集合
     * @param deleteIds 需要递归添加的id集合
     */
    private void setDeleteId(Map<Long, List<Long>> parentIdAndIdMap,List<Long> actionIds,Set<Long> deleteIds){

        if(!CollectionUtils.isEmpty(actionIds)){
            for(Long id : actionIds){
                List<Long> aLong = parentIdAndIdMap.get(id);
                deleteIds.add(id);

                setDeleteId( parentIdAndIdMap,aLong,deleteIds);
            }

        }else {
            return;
        }

    }

    private Map<Long, List<Long>> generateParentIdAndChildIdMap(List<TreeAction> treeActions){

        Map<Long, List<Long>> map = new HashMap<>();

       for(TreeAction ta : treeActions){
           List<Long> longs = map.get(ta.getParentId());
           if(CollectionUtils.isEmpty(longs)){
               longs = new ArrayList<>();
               longs.add(ta.getId());
               map.put(ta.getParentId(),longs);
           }else {
               longs.add(ta.getId());
           }
       }

        return map;

    }

    @Override
    /**
     * 查询菜单管理
     */
    public MenuPageVO cascadeQueryAction(){

        List<BaseTreeVO> baseTreeVOS4System = sysActionMapper.selectByEnterpriseAndType(ActionType.SYSTEM_MENU.getCode());

        List<Tree> trees4System = commonComponent.structureCommTree(baseTreeVOS4System);

        List<BaseTreeVO> baseTreeVOS4Manager = sysActionMapper.selectByEnterpriseAndType(ActionType.MANAGER_MENU.getCode());

        List<Tree> trees4Manager = commonComponent.structureCommTree(baseTreeVOS4Manager);

        MenuPageVO menuPageVO = new MenuPageVO(trees4System,trees4Manager);

        return menuPageVO;

    }

    @Override
    /**
     * 给设置菜单适用的企业类型
     */
    public void setMenus2Enterprise(List<EnterpriseMenuFormVO> enterpriseMenuFormVOS){

        for(EnterpriseMenuFormVO menuFormVO : enterpriseMenuFormVOS){

            SysAction sysAction = new SysAction();
            sysAction.setId(menuFormVO.getId());
            sysAction.setForParent(menuFormVO.getForParent());
            sysAction.setForBranch(menuFormVO.getForBranch());
            sysAction.setForLeague(menuFormVO.getForLeague());
            sysActionMapper.updateByPrimaryKeySelective(sysAction);
        }

    }


}
