package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyRVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StockDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.UserDestroyVO;

public interface GoodsDestroyMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsDestroy record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsDestroy record);

    /**
     *
     * @mbg.generated
     */
    GoodsDestroy selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsDestroy record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsDestroy record);
    /**
     * 查询用户信息列表
     * */
    List<UserDestroyVO> selectUserList(Map map);
    
    /**
     * 查询商品列表
     * */
    List<GoodsDestroyVO> selectGoodsList(Map map);
    /**
     * 查询商品
     * */
    GoodsDestroyVO selectGoods(Map map);
    /**
     * 查询库存
     * */
    List<StockDestroyVO> selectStockList(Map map);
    
    StockDestroyVO selectStock(Map map);
    /**
     * 分页展示销毁单列表
     * */
    List<GoodsDestroyRVO> selectGoodsDestroyList(Map map);
    Long selectCount(Map map);
    /**
     * 获取销毁单详情
     * */
    GoodsDestroyRVO selectById(Long Id);
}