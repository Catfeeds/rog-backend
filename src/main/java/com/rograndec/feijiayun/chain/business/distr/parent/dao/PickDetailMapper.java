package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.PickDetail;
import org.apache.ibatis.annotations.Param;

public interface PickDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PickDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PickDetail record);

    /**
     *
     * @mbg.generated
     */
    PickDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PickDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PickDetail record);

	List<PickDetail> selectByPickId(Long id);

    Integer getPickDeatilStatus(@Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    PickDetail selectByBaseOrderDtlId(Long baseOrderDtlId);
}