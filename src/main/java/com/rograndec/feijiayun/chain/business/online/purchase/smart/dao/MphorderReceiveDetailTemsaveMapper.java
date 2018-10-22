package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.MphorderReceiveDetailTemsave;

import java.util.List;

public interface MphorderReceiveDetailTemsaveMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MphorderReceiveDetailTemsave record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MphorderReceiveDetailTemsave record);

    /**
     *
     * @mbg.generated
     */
    MphorderReceiveDetailTemsave selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MphorderReceiveDetailTemsave record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MphorderReceiveDetailTemsave record);

    List<MphorderReceiveDetailTemsave> selectByReceiveTemsaveId(Long id);

	void deleteByTemsaveId(Long id);
}