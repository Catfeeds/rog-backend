package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInCheckDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheckDetail2Detail2ExcelVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheckDetail2DetailVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DistrInCheckDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrInCheckDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInCheckDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInCheckDetail record);

    List<DistrInCheckDetail2DetailVO> getInCheckDetail2Detail(@Param("enterpriseId")Long enterpriseId, @Param("checkId") Long checkId);

    List<DistrInCheckDetail2Detail2ExcelVO> getInCheckDetail2Detail2Excel(@Param("enterpriseId")Long enterpriseId, @Param("checkId") Long checkId);

    Map<String,BigDecimal> getQuantityTotalInCheckById(@Param("enterpriseId")Long enterpriseId, @Param("checkId") Long checkId);

    int updateStatusByCheckId(@Param("enterpriseId")Long enterpriseId,@Param("checkId")Long checkId,@Param("status")Integer status);

    List<DistrInCheckDetail> selectByDtlId(@Param("enterpriseId")Long enterpriseId,@Param("id")Long id);
}