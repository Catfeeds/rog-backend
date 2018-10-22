package com.rograndec.feijiayun.chain.business.member.integralexchange.service;

import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeGoodsRequestVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeGoodsVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.StockGoodsVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * Created by dudy on 2017/9/21.
 */
public interface IntegralExchangeGoodsService {
    /**
     * 批量保存
     * @param requestVOS
     * @param loginUser
     * @return
     */
    List<String> batchSave(List<IntegralExchangeGoodsRequestVO> requestVOS, UserVO loginUser);


    void delete(Long id);

    /**
     * 查询所有积分商品
     * @param loginUser
     * @return
     */
    List<IntegralExchangeGoodsVO> selectAll(UserVO loginUser);

    /**
     * 搜索商品带库存
     * @param key
     * @param loginUser
     * @return
     */
    List<StockGoodsVO> searchStockGoodsVO(String key, UserVO loginUser);

    /**
     * 搜索积分商品
     * @param key
     * @param loginUser
     * @return
     */
    List<IntegralExchangeGoodsVO> searchIntegralGoods(String key, UserVO loginUser);
}
