package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnReceiveDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrReturnReceiveDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnReceiveDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnReceiveDetail record);

    List<DistrReturnReceiveDetailVO> selectByReceiveId(@Param("receiveId") Long id, @Param("enterpriseId") Long enterpriseId);

    int batchInsert(List<DistrReturnReceiveDetail> distrReturnReceiveDetails);


}