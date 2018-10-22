/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service;

import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.business.system.set.vo.WarnSetVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月21日 下午7:50:47

 */
public interface WarnSetService {
    List<SysRole> getRole(Long deptId, UserVO loginUser) throws Exception;
    List<Department> getDepartment(UserVO loginUser) throws Exception;
    List<WarnSetVO> getWarnSet(UserVO loginUser, Integer setType) throws Exception;

    void updateWarnSet(UserVO loginUser, List<WarnSet> warnSets) throws Exception;
}
