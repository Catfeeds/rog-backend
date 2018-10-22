package com.rograndec.feijiayun.chain.business.goods.pharmacy.service;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.SpecialGoods;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SelectGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.SpecialGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


import java.util.List;
import java.util.Map;

/**
 * Created by zeshi.sun on 2017/9/7.
 */
public interface SpecialGoodsService {

    List<SelectGoodsVO> selectGoodsVoPage(String key, Long enterpriseId, String specification, String formulationType);

    List<SpecialGoodsVO> specialGoodsVoPage(int pageNo, int pageSize, Long enterpriseId, Page page, String key, String orderName, String orderType, UserVO loginUser);

    int insertSpecialGoods(UserVO loginUser, SpecialGoods specialGoods) throws Exception;

    int updateSpecialGoods(UserVO loginUser, SpecialGoods specialGoods) throws Exception;

    int deleteSpecialGoods(Long id, UserVO loginUser) throws Exception;

    void canSave(SpecialGoods specialGoods, UserVO loginUser) throws Exception;

    Long selectSpecilGoodsLimit(UserVO loginUser, String goodsId);

}
