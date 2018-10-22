package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnCheckDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrReturnCheckDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnCheckDetail selectByPrimaryKey(Long id);

    List<DistrReturnCheckDetail> selectByhCheckId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnCheckDetail record);

    List<DistrReturnCheckDetailVO> selectByCheckIdAndEnterpriseId(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

    DistrReturnCheckDetailVO selectByCheckDtlIdAndEnterpriseId(@Param("dtlId")String dtlId, @Param("enterpriseId")Long enterpriseId);
}