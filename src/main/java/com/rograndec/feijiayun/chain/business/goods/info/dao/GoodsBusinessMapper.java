package com.rograndec.feijiayun.chain.business.goods.info.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsBusinessMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsBusiness record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsBusiness record);

    /**
     *
     * @mbg.generated
     */
    GoodsBusiness selectByPrimaryKey(Long id);

    List<GoodsBusiness> selectByGoodsIds(@Param("list") List<Long> list,@Param("enterpriseId") Long enterpriseId);
    GoodsBusiness selectByGoodsId(@Param("goodsId") Long goodsId);

    List<GoodsBusiness> selectByEnterpriseId(Map map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsBusiness record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsBusiness record);

    int updateByGoodsId(GoodsBusiness record);

    GoodsBusiness getByGoodsId(Long goodsId);

    GoodsBusiness selectByEnterpriseIdAndGoodsId(Map<String, Object> paramMap);
    List<GoodsBusiness> selectByEnterpriseIdAndGoodsIds(Map<String, Object> paramMap);

}