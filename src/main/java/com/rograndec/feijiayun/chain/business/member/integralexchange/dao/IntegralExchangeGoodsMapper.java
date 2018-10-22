package com.rograndec.feijiayun.chain.business.member.integralexchange.dao;

import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeGoods;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeGoodsRequestVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeGoodsVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.StockGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralExchangeGoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(IntegralExchangeGoods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(IntegralExchangeGoods record);

    /**
     *
     * @mbg.generated
     */
    IntegralExchangeGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IntegralExchangeGoods record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IntegralExchangeGoods record);

    /**
     *  获取积分商品列表
     * @param eId
     * @return
     */
    List<IntegralExchangeGoodsVO> selectAll(@Param("enterpriseId") Long eId);


    /**
     * 搜索商品，带库存
     * @param enterpriseIdList
     * @param key
     * @return
     */
    List<StockGoodsVO> searchStockGoodsVO(@Param("enterpriseIdList")List<Long> enterpriseIdList,  @Param("key")String key);


    /**
     * 搜索积分商品带库存 ,门店搜索自己的库存， 业务信息会员管理总部控制时，读取总部设置的积分商品
     * @param key
     * @param eId
     * @return
     */
    List<IntegralExchangeGoodsVO> searchIntegralGoods(@Param("stockEnterpriseId")Long stockEnterpriseId,@Param("enterpriseId")Long eId,  @Param("key")String key);

    /**
     *根据商品ID 搜索 积分商品
     * @param goodsId
     * @param enterpriseId
     * @return
     */
    IntegralExchangeGoods selectByGoodsId(@Param("goodsId") Long goodsId,@Param("enterpriseId") Long enterpriseId,@Param("parentId") Long parentId);

    /**
     * 查询当前企业积分兑换商品(用于判断列表哪些删除了)
     * @param eId
     * @return
     */
    List<IntegralExchangeGoodsRequestVO> selectExchangeGoodsRequestVO(Long eId);
}