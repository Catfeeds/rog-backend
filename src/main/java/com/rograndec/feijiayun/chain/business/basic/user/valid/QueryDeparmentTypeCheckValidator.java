package com.rograndec.feijiayun.chain.business.basic.user.valid;

import com.rograndec.feijiayun.chain.business.basic.user.constant.QueryDepartmentType;
import com.rograndec.feijiayun.chain.business.basic.user.exception.UserManagerBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by zhaiwei on 2017/8/26.
 */
public class QueryDeparmentTypeCheckValidator implements ConstraintValidator<QueryDepartmentTypeCheck, String> {
    private Logger logger = LoggerFactory.getLogger(QueryDeparmentTypeCheckValidator.class);

    @Override
    public void initialize(QueryDepartmentTypeCheck addUserPasswordConfirm) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        QueryDepartmentType[] queryDepartmentTypes = QueryDepartmentType.values();
        boolean result = false;
        for(QueryDepartmentType queryDepartmentType : queryDepartmentTypes){
            if(queryDepartmentType.getValue().equals(value)){
                result = !result;
                break;
            }
        }
        if(!result){
            throw new UserManagerBizException(UserManagerBizException.VALUE_CHECK,"查下部门信息,type传递不正确");
        }
        return result;
    }
}
