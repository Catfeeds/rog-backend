package com.rograndec.feijiayun.chain.business.auth.register.valid;

import com.rograndec.feijiayun.chain.business.auth.login.exception.LoginBizException;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserPersonalMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;
import com.rograndec.feijiayun.chain.common.SysCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class RegisterUserCheckValidator implements ConstraintValidator<RegisterUserCheck, RegisterUserVO> {
    private Logger logger = LoggerFactory.getLogger(RegisterUserCheckValidator.class);

    @Autowired
    private UserAdministrationMapper userAdministrationMapper;

    @Autowired
    private UserPersonalMapper userPersonalMapper;

    @Override
    public void initialize(RegisterUserCheck constraintAnnotation) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(RegisterUserVO value, ConstraintValidatorContext context) {

        //校验账户在saas系统中是否唯一
        UserAdministration userAdministration = userAdministrationMapper.selectUserAdministrationByAccount(value.getLoginAccount());
        if(null != userAdministration){
            throw new ValidationException("用户名存在");
        }

        //校验邮箱是否存在
        UserPersonal userPersonal = userPersonalMapper.selectByEmail(value.getEmail());
        if(null != userPersonal){
            throw new ValidationException("邮箱存在");
        }

        //校验手机号是否存在
        userPersonal = userPersonalMapper.selectByMobilePhone(value.getMobilePhone());
        if(null != userPersonal){
            throw new ValidationException("手机号存在");
        }



        return true;
    }
}
