package com.rograndec.feijiayun.chain.business.online.purchase.smart.service;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.PlaceOrderDataVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.RequestDeleteScartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartPurchasingPlanVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingCartVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanGoodsVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.math.BigDecimal;
import java.util.List;

public interface SmartService {

    List<SmartPurchasingPlanVO> selectSmartPlan(UserVO userVO);

    List<SmartPurchasingPlanVO> convertToListWithDistinct(List<PurchasePlanGoodsVO> purchasePlanGoodsVOS, UserVO userVO) throws Exception;

    List<SmartPurchasingPlanVO> insertSerachGoodsList(List<SmartPurchasingPlanVO> list, UserVO userVO) throws Exception;

    List<SmartPurchasingPlanVO> updateGoodsCount(Long tid, BigDecimal quantity, UserVO userVO) throws Exception;

    List<SmartPurchasingPlanVO> deleteGoodsList(List<Long> list, UserVO userVO);

    void submitCart(List<Long> list, UserVO userVO);

    SmartSourcingCartVO getSmartCart(UserVO loginUser) throws Exception;

    SmartSourcingCartVO deleteSmartCart(UserVO loginUser, List<RequestDeleteScartVO> requestDeleteScartVO) throws Exception;

    void updateSmartCart(UserVO loginUser, SmartSourcingCartVO smartSourcingCartVO) throws Exception;

    PlaceOrderDataVO dealOrder(UserVO loginUser) throws Exception;
}
