package com.rograndec.feijiayun.chain.business.goods.sale.service;

import com.rograndec.feijiayun.chain.business.goods.sale.vo.SpecialPriceStrategyVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * Created by madong on 2017/9/5.
 */
public interface SpecialPriceStrategyService {
    int saveSpecialPrice(SpecialPriceStrategyVO specialPriceStrategyVO, UserVO loginUser) throws Exception;

    int updateSpecialPrice(SpecialPriceStrategyVO specialPriceStrategyVO, UserVO loginUser) throws Exception;

    int deleteSpecialPrice(Long id, UserVO loginUser) throws Exception;

    List<SpecialPriceStrategyVO> getSpecialPrice(UserVO loginUser, String orderName, String orderType);

    boolean canDeleteSpecialPrice(Long id, UserVO loginUser);

    Long checkExistsCode(UserVO loginUser, String code) throws Exception ;
}
