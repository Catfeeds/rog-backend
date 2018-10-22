package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReceiveDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveDetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheckDetail2DetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DistrInReceiveDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrInReceiveDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInReceiveDetail record);

    int batchInsert(List<DistrInReceiveDetail> distrInReceiveDetails);

    List<DistrInCheckDetail2DetailVO> getReceiveDetail2DetailForCheck(@Param("enterpriseId")Long enterpriseId, @Param("receiveId") Long receiveId);

    List<DistrInReceiveDetailVO> selectByReceiveIdByEnterpriseId(@Param("receiveId") Long receiveId, @Param("enterpriseId") Long enterpriseId);

    int updateDetailStatus(@Param("enterpriseId")Long enterpriseId,@Param("receiveId")Long receiveId,@Param("status")Integer status);
}