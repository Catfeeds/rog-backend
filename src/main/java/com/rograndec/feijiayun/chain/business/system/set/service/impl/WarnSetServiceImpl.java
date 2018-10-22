/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service.impl;

import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.WarnSetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.business.system.set.vo.WarnSetVO;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.DepartmentComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.collection.DeepCloneObject;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.system.set.service.WarnSetService;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月22日 上午10:16:48

 */
@Service
public class WarnSetServiceImpl implements WarnSetService{

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    WarnSetMapper warnSetMapper;
    @Autowired
    DepartmentComponent departmentComponent;

    @Autowired
    private EnterpriseBusinessMapper businessMapper;

    @Override
    public List<SysRole> getRole(Long deptId, UserVO loginUser) throws Exception {
        List<SysRole> sysRoles = null;
        deptId = deptId == -1l ? null : deptId;
        if(loginUser.getParentId() == 0l) {
            Map<String, Long> param = new HashMap<>();
            param.put("enterpriseId", loginUser.getEnterpriseId());
            param.put("deptId", deptId);
            sysRoles = sysRoleMapper.selectALlByDepartmentIdByEnterpriseId(param);
        }else {
            Map<String, Long> param = new HashMap<>();
            param.put("enterpriseId", loginUser.getParentId());
            param.put("deptId", deptId);
            sysRoles = sysRoleMapper.selectByDepartmentIdByEnterpriseId(param);
        }
        return sysRoles;
    }

    @Override
    //如果是总部登录,则返回总部和门店的所有部门
    public List<Department> getDepartment(UserVO loginUser) throws Exception {
        List<Department> departments = null;
        if(loginUser.getParentId() == 0l){
            departments = departmentMapper.selectAllByEnterprise(loginUser.getEnterpriseId());
        }else {
            departments = departmentMapper.selectByEnterpriseId(loginUser.getEnterpriseId());
        }
        return departments;
    }

    @Override
    public List<WarnSetVO> getWarnSet(UserVO loginUser,Integer setType) throws Exception {


        Long enterpriseId = loginUser.getEnterpriseId();

        EnterpriseBusiness business = businessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        Integer warnSet1 = business.getWarnSet();
        if( warnSet1 == 0 && !loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
            // 门店信息“预警管理”选择“自主控制”，则允许门店允许设置自己的预警，否则读取总部设置
            enterpriseId = loginUser.getParentId();
        }
        Map<String,Object> param = new HashMap<>();
        param.put("enterpriseId",enterpriseId);

        if(loginUser.getParentId() == 0l){
            param.put("chainType",0);
        }else{
            param.put("chainType",1);
        }
        List<WarnSetVO> warnSets = null;
        switch (setType){
            case 0 : warnSets = warnSetMapper.selectByEnterpriseQualification(param);
            break;
            case 1 : warnSets = warnSetMapper.selectByUserQualification(param);
            break;
            case 2 : warnSets = warnSetMapper.selectBySupplierQualification(enterpriseId);
            break;
            case 3 : warnSets = warnSetMapper.selectByGoodsQualification(enterpriseId);
            break;
            case 4 : warnSets = warnSetMapper.selectByStorageQualification(enterpriseId);
            break;

        }
//        //库存直接写死一共六个资质
//        if(setType == 4){
//            warnSets = new ArrayList<WarnSetVO>();
//            WarnSetVO warnSet = new WarnSetVO();
//            warnSet.setContent("过期商品");
//            warnSet.setQualificationId(0l);
//            warnSets.add(warnSet);
//            warnSet.setContent("近效期商品");
//            warnSets.add(warnSet);
//            warnSet.setContent("缺货商品");
//            warnSets.add(warnSet);
//            warnSet.setContent("积货商品");
//            warnSets.add(warnSet);
//            warnSet.setContent("滞销商品");
//            warnSets.add(warnSet);
//            warnSet.setContent("药品养护");
//            warnSets.add(warnSet);
//        }
//        List<WarnSetVO> warnSetsCopy = DeepCloneObject.cloneObject((ArrayList<WarnSetVO>)warnSets);
//        warnSetsCopy.addAll(warnSets);
        Map existsParam = new HashMap();
        existsParam.put("setType",setType);

        existsParam.put("enterpriseId",enterpriseId);
        List<WarnSetVO> existsWarnSets = warnSetMapper.selectBySetTypeByEnterpriseId(existsParam);
        for(WarnSetVO existsWarnSet : existsWarnSets){
            Iterator<WarnSetVO> it = warnSets.iterator();
            while (it.hasNext()){
                WarnSetVO warnSet = it.next();
//            for(WarnSetVO warnSet : warnSetsCopy){
                if(existsWarnSet.getContent().equals(warnSet.getContent()) && existsWarnSet.getQualificationId().equals(warnSet.getQualificationId())){
                    it.remove();
                }
            }
        }
        existsWarnSets.addAll(warnSets);
        return existsWarnSets;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWarnSet(UserVO loginUser, List<WarnSet> warnSets) throws Exception{

        EnterpriseBusiness business = businessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        Integer warnSet1 = business.getWarnSet();
        if( warnSet1 == 0 && !loginUser.getChainType().equals(ChainType.Headquarters.getCode())){

            throw  new BusinessException(SysCode.FAIL.getCode(),"预警设置为总部控制，不允许修改");
        }

        for (WarnSet warnSet:warnSets) {
            if(warnSet.getId() == null){
                warnSet.setEnterpriseId(loginUser.getEnterpriseId());
                warnSet.setParentId(loginUser.getParentId());
                UserEnterpriseUtils.setUserCreateOrModify(warnSet,loginUser,true);
                warnSet.setChainType(loginUser.getChainType());
                warnSet.setSysType(0);//新增一定是自定义的
                warnSetMapper.insertSelective(warnSet);
            }else{
                warnSet.setModifierId(loginUser.getUserId());
                warnSet.setModifierCode(loginUser.getUserCode());
                warnSet.setModifierName(loginUser.getUserName());
                warnSetMapper.updateByPrimaryKey(warnSet);
            }
        }
    }
}
