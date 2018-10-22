package com.rograndec.feijiayun.chain.business.auth.menu.service;

import com.rograndec.feijiayun.chain.business.auth.menu.vo.EnterpriseMenuFormVO;
import com.rograndec.feijiayun.chain.business.auth.menu.vo.MenuFormVO;
import com.rograndec.feijiayun.chain.business.auth.menu.vo.MenuPageVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface MenuManagerService {
    /**
     *  新增和修改权限
     * @param userVO
     * @param menuFormVO
     * @throws Exception
     */
    void modify(UserVO userVO, MenuFormVO menuFormVO) throws Exception;

    /**
     * 删除菜单,级联删除该菜单底下所有子菜单
     */
    void remove(Long actionId);

    /**
     * 查询菜单管理
     */
    MenuPageVO cascadeQueryAction();

    /**
     * 给设置菜单适用的企业类型
     */
    void setMenus2Enterprise(List<EnterpriseMenuFormVO> enterpriseMenuFormVOS);
}
