package com.rograndec.feijiayun.chain.business.online.purchase.centralized.service;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartBusiness2GoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.CentralizedCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.PlaceOrderDataVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface CentralizedCartService {
    CentralizedCartVO getCentralizedCart(UserVO loginUser) throws Exception;

    String getRedisKey(UserVO loginUser) throws Exception;

//    void updateCentralizedCart(UserVO loginUser, Long mphSupplierId, String goodsId, Integer amount) throws Exception;

    CentralizedCartVO deleteCentralizedCart(UserVO loginUser, List<CentralizedCartBusiness2GoodsVO> business2GoodsVOS) throws Exception;

    PlaceOrderDataVO dealOrder(UserVO loginUser) throws Exception;

    void saveCentralizedCart(UserVO loginUser, CentralizedCartVO centralizedCartVO) throws Exception;
}
