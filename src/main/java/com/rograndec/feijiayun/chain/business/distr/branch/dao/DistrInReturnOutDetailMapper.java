package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrInReturnOutDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    int deleteByOutId(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInReturnOutDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInReturnOutDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrInReturnOutDetail selectByPrimaryKey(Long id);

    List<DistrInReturnOutDetail> selectByParam(@Param("outId") Long outId,@Param("dtId") Long dtId,@Param("enterpriseId") Long enterpriseId);

    List<DistrInReturnOutDetail> selectByOutId(Long id);

    List<DistrInReturnOutDetail> selectByOutIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInReturnOutDetail record);

    int updateByOutId(DistrInReturnOutDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInReturnOutDetail record);


    DistrInReturnOutDetail getByPrimaryIdAndEID(@Param("id") Long id,@Param("enterpriseId")Long enterpriseId);
}