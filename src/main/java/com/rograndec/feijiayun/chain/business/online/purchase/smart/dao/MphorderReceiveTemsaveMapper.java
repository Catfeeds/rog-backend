package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.MphorderReceiveTemsave;
import org.apache.ibatis.annotations.Param;

public interface MphorderReceiveTemsaveMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MphorderReceiveTemsave record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MphorderReceiveTemsave record);

    /**
     *
     * @mbg.generated
     */
    MphorderReceiveTemsave selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MphorderReceiveTemsave record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MphorderReceiveTemsave record);

    MphorderReceiveTemsave selectByEnterpriseIdAndOrderIdAndOrderCode(@Param("enterpriseId") Long enterpriseId,@Param("orderId") Long orderId, @Param("orderCode") String orderCode);
}