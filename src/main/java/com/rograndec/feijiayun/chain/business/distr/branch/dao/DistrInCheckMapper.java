package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInCheck;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.CheckStatusType;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2DetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2ListVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.RequestParamForListVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInCheckVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrInCheckMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInCheck record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInCheck record);

    /**
     *
     * @mbg.generated
     */
    DistrInCheck selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInCheck record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInCheck record);

    List<DistrInCheck2ListVO> getDistrInCheckList(@Param("param")RequestParamForListVO param, @Param("enterpriseId")Long enterpriseId, @Param("statusType") CheckStatusType statusType);

    DistrInCheck2DetailVO getDistrInCheckById(@Param("enterpriseId") Long enterpriseId, @Param("id")Long id);
    
    List<DistrInCheckReportVO> getDistrInCheckReportList(RequestDistrInCheckVO map);
    
    Integer getDistrInCheckReportListTotalNum(RequestDistrInCheckVO map);
    
    DistrReportVo<DistrInCheckReportVO> getDistrReportVo(RequestDistrInCheckVO map);

    DistrInCheck selectByBaseOrderId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    Integer getDistrInCheckListCount(@Param("param")RequestParamForListVO param, @Param("enterpriseId")Long enterpriseId, @Param("statusType") CheckStatusType statusType);
}