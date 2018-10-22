package com.rograndec.feijiayun.chain.business.storage.move.dao;

import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMoveDetail;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ShelfMoveDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ShelfMoveDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ShelfMoveDetail record);

    /**
     *
     * @mbg.generated
     */
    ShelfMoveDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ShelfMoveDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ShelfMoveDetail record);

    List<ShelfMoveDetail> getByMoveId(@Param("enterpriseId") Long enterpriseId, @Param("id") Long id);

    BigDecimal getTotalQuantity(@Param("enterpriseId") Long enterpriseId, @Param("id") Long id);
}