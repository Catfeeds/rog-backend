package com.rograndec.feijiayun.chain.business.storage.split.dao;

import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitSet;
import com.rograndec.feijiayun.chain.business.storage.split.vo.CanSpitGoodVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitSetPageVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SplitSetMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SplitSet record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SplitSet record);

    /**
     *
     * @mbg.generated
     */
    SplitSet selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SplitSet record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SplitSet record);

    List<SplitSetPageVO> getSplitSetPage(Map<String, Object> map);

    //储存管理-拆零设置-查询可拆零的商品
    List<CanSpitGoodVO> getCanSplitGoods(Map map);

    //查询某商品的拆零商品数量
    int countSplitGoods(@Param("goodsId")Long goodsId, @Param("enterpriseId")Long enterpriseId);

    //根据拆零设置之间id，查询拆零设置信息
    SplitSetPageVO getSplitSetById(@Param("id")Long id);

    //更新被拆零商品下除自己外，其他拆零商品的启用状态
    int updateSplitGoodsStatus(@Param("goodsId")Long goodsId, @Param("splitGoodsId")Long splitGoodsId, @Param("enterpriseId")Long enterpriseId);

    int updateShelfAndPrice(SplitSet splitSet);

    /**
     * 根据整盒商品的ID，查询拆零商品ID列表
     * @param goodsId 整盒商品ID
     * @param status 拆零商品状态（1-查询启用状态的拆零商品，0-查询禁用状态的商品；传null查询所有）
     * @return 拆零商品ID列表
     */
    List<Long> getSplitGoodsIdsWithGoodsId(@Param("goodsId")Long goodsId, @Param("status")Long status);

    Integer getSplitSetPageCount(Map<String, Object> map);
}