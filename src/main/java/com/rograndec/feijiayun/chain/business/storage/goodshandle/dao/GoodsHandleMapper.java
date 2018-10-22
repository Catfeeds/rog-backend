package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsHandle;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.RequestGoodsHandleListVo;

import java.util.List;

public interface GoodsHandleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsHandle record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsHandle record);

    /**
     *
     * @mbg.generated
     */
    GoodsHandle selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsHandle record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsHandle record);
    /**
     *
     * <获取商品处理单列表数据数量>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 14:06
     */
    int countGoodsLockDateByParam(RequestGoodsHandleListVo requestGoodsHanleListVo);
    /**
     *
     * <获取商品处理单列表数据>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 14:06
     */
    List<GoodsHandle> getGoodsLockDateByParam(RequestGoodsHandleListVo requestGoodsHanleListVo);
}