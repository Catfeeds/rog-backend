package com.rograndec.feijiayun.chain.business.auth.register.service;

import com.rograndec.feijiayun.chain.business.auth.register.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;

import java.util.List;

public interface RegisterService {
    User registerUser(RegisterUserVO registerVO,Boolean isRegistr) throws Exception;

    CheckRegisterVO checkUser(String loginAccount);

    Enterprise registerEnterprise(RegisterEnterpriseVO registerVO, Boolean isSynchronize) throws Exception;

    Enterprise synchronizeRGTEnterprise(Long userId) throws Exception;

    List<RegisterEnterpriseNotBindVO> getNotBindEnterprise(Page page);
}
