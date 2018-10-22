package com.rograndec.feijiayun.chain.utils.user;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;

import java.util.Date;

/**
 * Created by zhaiwei on 2017/9/6.
 */
public class UserEnterpriseUtils {

    public static Object setUserCreateOrModify(Object t, UserVO userVO, boolean isAdd) throws Exception {

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

    /**
     * 获取登录用户的总部id
     * @param userVO
     * @return
     */
    public static Long getHeadquartersEnterpriseId(UserVO userVO){

        if(ChainType.Headquarters.getCode() != userVO.getChainType()){
            return userVO.getParentId();
        }else {
            return userVO.getEnterpriseId();
        }

    }
}
