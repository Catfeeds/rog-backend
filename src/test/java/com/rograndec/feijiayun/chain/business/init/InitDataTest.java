package com.rograndec.feijiayun.chain.business.init;

//import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
//import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
//import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
//import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
//import com.rograndec.feijiayun.chain.business.init.service.SysDataInitService;
//import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
//import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
//import com.rograndec.feijiayun.chain.business.system.set.dao.SysActionMapper;
//import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleActionMapper;
//import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleMapper;
//import com.rograndec.feijiayun.chain.business.system.set.entity.SysAction;
//import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
//import com.rograndec.feijiayun.chain.business.system.set.entity.SysRoleAction;
//import com.rograndec.feijiayun.chain.common.vo.UserVO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@SpringBootApplication
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@MapperScan("com.rograndec.feijiayun.chain.**.dao")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InitDataTest {

//    @Autowired
//    private EnterpriseMapper enterpriseMapper;
//
//    @Autowired
//    public UserMapper userMapper;
//
//    @Autowired
//    private SysDataInitService sysDataInitService;
//
//    @Autowired
//    private SysRoleMapper sysRoleMapper;
//
//    @Autowired
//    private SysActionMapper sysActionMapper;
//
//    @Autowired
//    private SysRoleActionMapper sysRoleActionMapper;
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;
//
//    // 测试初始化系统默认数据（全局只执行一次）
////    @Test
//    public void testInitGlobalDataOnlyOnce() throws Exception {
//        sysDataInitService.initGlobalDataOnlyOnce();
//    }
//
//    // 初始化总部、分店共公共默认数据（总部总部审核通过后、分店新增保存后执行）
//    @Test
//    public void testInitCommonData() throws Exception {
//        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(3L);
//        User user = userMapper.selectByPrimaryKey(2L);
//        sysDataInitService.initCommonData(enterprise, userToUserVO(user));
//    }
//
//    private UserVO userToUserVO(User user) {
//        UserVO userVO = new UserVO();
//        userVO.setUserId(user.getId());
//        userVO.setEnterpriseId(user.getEnterpriseId());
//        userVO.setParentId(user.getParentId());
//        userVO.setUserCode(user.getCode());
//        userVO.setUserName(user.getName());
//        return userVO;
//    }
//
//    // 测试生成系统默认系统功能
////    @Test
//    public void testInitPermissionData() throws Exception {
////        User user = userMapper.selectByPrimaryKey(0L);
//        sysDataInitService.initPermissionData();
//    }
//
//
////    @Test
//    public void initUserPermissionTest(){
//        User user = userMapper.selectByPrimaryKey(2L);
//
//        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(3L);// TODO
//
//        List<SysAction> actionList = sysActionMapper.selectByenterpriseId(0L);
//
//        SysRole sysRole = sysRoleMapper.selectDefRoleByCode("090101");
//
//        List<SysRole> roleList = new ArrayList<>();
//        roleList.add(sysRole);
//
//        // 角色菜单、权限设置
//        initSysRoleAction(enterprise, user, roleList, actionList);
//
//    }
//
//
//    private void initUserRole(Enterprise enterprise, User user, SysRole sysRole) {
//        UserRole userRole = new UserRole();
//        userRole.setEnterpriseId(enterprise.getId());
//        userRole.setParentId(enterprise.getParentId());
//        userRole.setUserId(user.getId());
//        userRole.setRoleId(sysRole.getId());
//        userRole.setRemark("初始化权限");
//        userRole.setCreaterId(user.getId());
//        userRole.setCreaterCode(user.getCode());
//        userRole.setCreaterName(user.getName());
//        userRole.setCreateTime(new Date());
//        userRole.setModifierId(user.getId());
//        userRole.setModifierCode(user.getCode());
//        userRole.setModifierName(user.getName());
//        userRole.setUpdateTime(new Date());
//        userRoleMapper.insertSelective(userRole);
//    }
//
//    private void initSysRoleAction(Enterprise enterprise, User user, List<SysRole> roleList, List<SysAction> actionList) {
//        for(SysRole sysRole:roleList){
//            for(SysAction sysAction:actionList){
//                SysRoleAction sysRoleAction = new SysRoleAction();
//                sysRoleAction.setEnterpriseId(enterprise.getId());
//                sysRoleAction.setParentId(enterprise.getParentId());
//                sysRoleAction.setChainType(enterprise.getChainType());
//                sysRoleAction.setRoleId(sysRole.getId());
//                sysRoleAction.setActionId(sysAction.getId());
//                sysRoleAction.setRemark("系统默认权限");
//                sysRoleAction.setCreaterId(user.getId());
//                sysRoleAction.setCreaterCode(user.getCode());
//                sysRoleAction.setCreaterName(user.getName());
//                sysRoleAction.setCreateTime(new Date());
//                sysRoleAction.setModifierId(user.getId());
//                sysRoleAction.setModifierCode(user.getCode());
//                sysRoleAction.setModifierName(user.getName());
//                sysRoleAction.setUpdateTime(new Date());
//                sysRoleActionMapper.insertSelective(sysRoleAction);
//            }
//        }
//    }

}