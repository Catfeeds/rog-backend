package com.rograndec.feijiayun.chain.business.storage.move.dao;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherInDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OtherInDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OtherInDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OtherInDetail record);

    /**
     *
     * @mbg.generated
     */
    OtherInDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OtherInDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OtherInDetail record);

    List<OtherInDetail> selectByEnterpriseIdAndInId(@Param("enterpriseId") Long enterpriseId, @Param("id") Long id);
    OtherInDetail selectByEnterpriseIdAndGoodsId(@Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId,@Param("lotNumber") String lotNumber);

    void updateOtherInDetailStatusByOtherInIdAndStatus(@Param("id") Long id, @Param("approvalStatus") Integer approvalStatus);
}