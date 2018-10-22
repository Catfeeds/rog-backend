package com.rograndec.feijiayun.chain.business.goods.info.dao;

import com.rograndec.feijiayun.chain.business.common.vo.GoodsStorageDataVO;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsStorage;
import org.apache.ibatis.annotations.Param;

public interface GoodsStorageMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsStorage record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsStorage record);

    /**
     *
     * @mbg.generated
     */
    GoodsStorage selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsStorage record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsStorage record);

    int updateByGoodsId(GoodsStorage record);

    GoodsStorage getByGoodsId(Long goodsId);

    GoodsStorageDataVO selectByGoodsidAndEnterpriseId(@Param("enterpriseId")Long enterpriseId, @Param("goodsId")Long goodsId);
}