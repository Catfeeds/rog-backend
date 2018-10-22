package com.rograndec.feijiayun.chain.business.goods.sale.service;

import com.rograndec.feijiayun.chain.business.goods.sale.vo.CommissionGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by madong on 2017/9/5.
 */
public interface CommissionGoodsService {
    int saveRoyaltyGoods(List<CommissionGoodsVO> commissionGoodsVOS, UserVO loginUser) throws Exception;

    List<Map> getRoyaltyInfo(UserVO loginUser) throws Exception;

    int updateRoyaltyGoods(CommissionGoodsVO commissionGoodsVO, UserVO loginUser) throws Exception;

    int deleteRoyaltyGoods(Long id) throws Exception;

    Page getRoyaltyGoods(UserVO loginUser, String goodsInfo, String strategyId, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception;
}
