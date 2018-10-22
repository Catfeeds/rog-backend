package com.rograndec.feijiayun.chain.business.storage.chgoods.dao;

import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsLoadDetail;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsLoadOrderDtlListOneVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsLoadDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsLoadDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsLoadDetail record);

    /**
     *
     * @mbg.generated
     */
    GoodsLoadDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsLoadDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsLoadDetail record);

    List<ChGoodsLoadOrderDtlListOneVO> selectByDtlListParams(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    Double selectSumTotal(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);
}