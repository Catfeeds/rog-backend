package com.rograndec.feijiayun.chain.business.storage.chgoods.dao;

import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClearShelf;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsClearOrderDtlShelfListOneVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsClearOrderDtlShelfListVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.GoodsClearShelfSumVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsClearShelfMapper {

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsClearShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsClearShelf record);

    /**
     *
     * @mbg.generated
     */
    GoodsClearShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsClearShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsClearShelf record);

    List<ChGoodsClearOrderDtlShelfListVO> selectByDtlShelfListParams(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<ChGoodsClearOrderDtlShelfListOneVO> selectByDtlShelfListOneParams(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id, @Param("goodsId")Long goodsid);

    void updateStockByGoodsIdAndShelfIdAndLotId(@Param("enterpriseId")Long enterpriseId, @Param("goodsId")Long goodsId, @Param("shelfId")Long shelfId,
                                                @Param("lotId")Long lotId, @Param("subUsableQty")Double subUsableQty, @Param("subQty")Double subQty);

    GoodsClearShelfSumVO selectBySum(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

//    List<GoodsClearShelf> getClearShelfRecord(Long clearId);
}