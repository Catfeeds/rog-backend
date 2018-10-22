package com.rograndec.feijiayun.chain.business.goods.info.dao;

import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsModifyRecord;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.business.goods.info.vo.ResponseGoodsModifyRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    GoodsModifyRecordWithBLOBs selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(GoodsModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsModifyRecord record);

    List<ResponseGoodsModifyRecordVO> getGoodsModifyRecordPage(@Param("enterpriseId") Long enterpriseId, @Param("parentId") Long parentId,@Param("goodsId")Long goodsId);

}