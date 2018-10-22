package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.goods.info.vo.RequestGoodsVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsLock;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.RequestGoodsLockListVo;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;

import java.util.List;

public interface GoodsLockMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(GoodsLock record);

    /**
     * @mbg.generated
     */
    int insertSelective(GoodsLock record);

    /**
     * @mbg.generated
     */
    GoodsLock selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsLock record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsLock record);
    /**
     *
     * <查询商品锁定单数量>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/27 20:20
     */
    int countGoodsLockDateByParam(RequestGoodsLockListVo requestGoodsVO);
    /**
     *
     * <查询商品锁定单数据>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/27 20:21
     */
    List<GoodsLock> getGoodsLockDateByParam(RequestGoodsLockListVo requestGoodsVO);
    /**
     *
     * <更新商品锁定单状态>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/29 19:01
     */
    int updateGoodsStockStatus(GoodsLock goodsLock);

    /**
     * 查询商品: 合格品货位商品
     * @Author dongyang.du
     * @param goodsParamStockVO
     * @return
     */
    List<SelectPricingGoodsVO> selectGoodsByParam(GoodsParamStockVO goodsParamStockVO);
}