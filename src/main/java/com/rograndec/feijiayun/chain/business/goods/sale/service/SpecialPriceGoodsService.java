package com.rograndec.feijiayun.chain.business.goods.sale.service;

import com.rograndec.feijiayun.chain.business.goods.sale.vo.SpecialPriceGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by madong on 2017/9/5.
 */
public interface SpecialPriceGoodsService {
    int saveSpecialPriceGoods(List<SpecialPriceGoodsVO> specialPriceGoodsVOs, UserVO loginUser) throws Exception;

    int updateSpecialPriceGoods(SpecialPriceGoodsVO specialPriceGoodsVO, UserVO loginUser) throws Exception;

    int deleteSpecialPriceGoods(Long id) throws Exception;

    List<Map> getSpecialPriceInfo(UserVO loginUser) throws Exception;

    Page getSpecialgoods(UserVO loginUser, String goodsInfo, String strategyId, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception;
}