package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.PickShelf;

public interface PickShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PickShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PickShelf record);

    /**
     *
     * @mbg.generated
     */
    PickShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PickShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PickShelf record);

	List<PickShelf> selectByDtlId(@Param("dtlId")Long dtlId, @Param("id")Long id);

    PickShelf getUpdatePickShelf(@Param("baseOrderDtlId")Long baseOrderDtlId, @Param("goodsId")Long goodsId, @Param("lotId")Long lotId, @Param("shelfId")Long shelfId);

    Integer getPickShelfStatus(@Param("baseOrderDtlId")Long baseOrderDtlId, @Param("status")Integer status);
}