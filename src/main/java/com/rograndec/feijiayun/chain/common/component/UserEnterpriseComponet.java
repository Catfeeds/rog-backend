package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhaiwei on 2017/9/6.
 */
@Component
public class UserEnterpriseComponet<T> {

    public T setUserEnterprise(T t, UserVO userVO,boolean isAdd) throws Exception {

        Date date = new Date();
        if(isAdd){
            ReflectUtils.setValueOfGet(t,"createrId",userVO.getUserId());
            ReflectUtils.setValueOfGet(t,"createrCode",userVO.getUserCode());
            ReflectUtils.setValueOfGet(t,"createrName",userVO.getUserName());
            ReflectUtils.setValueOfGet(t,"createTime",date);
            ReflectUtils.setValueOfGet(t,"modifierId",userVO.getUserId());
            ReflectUtils.setValueOfGet(t,"modifierCode",userVO.getUserCode());
            ReflectUtils.setValueOfGet(t,"modifierName",userVO.getUserName());
            ReflectUtils.setValueOfGet(t,"updateTime",date);
        }else {
            ReflectUtils.setValueOfGet(t,"modifierId",userVO.getUserId());
            ReflectUtils.setValueOfGet(t,"modifierCode",userVO.getUserCode());
            ReflectUtils.setValueOfGet(t,"modifierName",userVO.getUserName());
            ReflectUtils.setValueOfGet(t,"updateTime",date);
        }

        return t;
    }
}
