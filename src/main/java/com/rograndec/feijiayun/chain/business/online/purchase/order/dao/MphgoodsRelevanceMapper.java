package com.rograndec.feijiayun.chain.business.online.purchase.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.online.purchase.order.entity.MphgoodsRelevance;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.BoundGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectBoundGoodsVO;

public interface MphgoodsRelevanceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MphgoodsRelevance record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MphgoodsRelevance record);

    /**
     *
     * @mbg.generated
     */
    MphgoodsRelevance selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MphgoodsRelevance record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MphgoodsRelevance record);

    String querySaasMphgoodsRelevance(Map<String, Object> map);

    Integer querySaasGoodsByName(Map<String, Object> map);

    BoundGoodsVO selectBYGid(@Param("odGId")Long odGId, @Param("enterpriseId")Long enterpriseId);

    List<SelectBoundGoodsVO> selectByMPHData(@Param("enterpriseId")Long enterpriseId, @Param("list")List<Long> ownerIdList,
    		@Param("mphGoodsName")String mphGoodsName, @Param("mphGoodsManufacturer")String mphGoodsManufacturer, @Param("cargoAreaId")Long cargoAreaId);

    MphgoodsRelevance selectByMPHGoodsIdAndGoodsId(@Param("enterpriseId")Long enterpriseId, @Param("mphGoodsId")String mphGoodsId, @Param("goodsId")Long goodsId);

    int selectByMPHGoodsId(@Param("mphGoodsId")Long mphGoodsId, @Param("enterpriseId")Long enterpriseId);
}