package com.rograndec.feijiayun.chain.utils.param;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 功能描述：
 * Created by ST on 2017/12/25 11:13
 */

public class ParamUtils {

    public static void packParam(UserVO loginUser, CommonParamSupplierAndGoods pageVO) {
        pageVO.setChainType(loginUser.getChainType());
        if(loginUser.getChainType() == ChainType.Headquarters.getCode()){
            //总部查询自己和所有加盟店的商品
            pageVO.setEnterpriseId(loginUser.getEnterpriseId());
        } else if(loginUser.getChainType() == ChainType.Selfoperatedshop.getCode()) {
            //自营店
            pageVO.setEnterpriseId(loginUser.getParentId());
        } else if(loginUser.getChainType() == ChainType.Division.getCode()){
            //加盟店
            pageVO.setEnterpriseId(loginUser.getEnterpriseId());
            pageVO.setParentId(loginUser.getParentId());
        }
    }


}