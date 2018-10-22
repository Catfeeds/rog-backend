package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LotNumberMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(LotNumber record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(LotNumber record);

    /**
     *
     * @mbg.generated
     */
    LotNumber selectByPrimaryKey(Long id);
    @Deprecated
    LotNumber selectByLotNum(@Param("lotNum") String lotNum);

    List<LotNumber> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LotNumber record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LotNumber record);

    LotNumber getLotNumInfo(@Param("enterpriseId")Long enterpriseId,@Param("goodsId")Long goodsId,@Param("lotNum")String lotNum);

    LotNumber selectByLotNumAndEnterPriseId(@Param("enterpriseId") Long enterpriseId, @Param("lotNumber") String lotNumber);

    LotNumber selectByLotNumAndGoodsId(@Param("goodsId")Long goodsId, @Param("lotNum")String lotNum, @Param("enterpriseId")Long enterpriseId);
}