package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInCheckLot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrInCheckLotMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInCheckLot record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInCheckLot record);

    /**
     *
     * @mbg.generated
     */
    DistrInCheckLot selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInCheckLot record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInCheckLot record);

    List<DistrInCheckLot> selectByDtlId(Long id);
    DistrInCheckLot getCheckLotInfo(@Param("enterpriseId")Long enterpriseId, @Param("checkId")Long checkId,
                    @Param("goodsId")Long goodsId,@Param("lotNumber")String lotNumber );
}