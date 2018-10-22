package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.MphorderReceiveShelfTemsave;

import java.util.List;

public interface MphorderReceiveShelfTemsaveMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MphorderReceiveShelfTemsave record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MphorderReceiveShelfTemsave record);

    /**
     *
     * @mbg.generated
     */
    MphorderReceiveShelfTemsave selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MphorderReceiveShelfTemsave record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MphorderReceiveShelfTemsave record);

    List<MphorderReceiveShelfTemsave> selectByReceiveTemsaveId(Long id);

    void deleteByDtlId(Long id);

	void deleteByTemsaveId(Long id);
}