package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInPageTotalVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInStorageVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrRetuenInReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnInVO;

import java.util.List;
import java.util.Map;

public interface DistrReturnInMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnIn record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnIn record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnIn selectByPrimaryKey(Long id);

    List<DistrReturnIn> selectByParam(Map<String, Object> map);

    DistrReturnInPageTotalVO selectByParamCount(Map<String, Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnIn record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnIn record);

    /**
     * 获取企业的配后退回入库单（总部-配送单-调用配后退回入库单）
     * @param enterpriseId 企业ID
     * @return
     */
    List<DistrReturnIn> getDistrReturnInListByEnterpiseId(Map<String, Object> map);
    
    List<DistrRetuenInReportVO> getDistrReturnInReport(RequestDistrReturnInVO requestDistrReturnInVO);
    
    Integer getDistrReturnInReportTotalNum(RequestDistrReturnInVO requestDistrReturnInVO);
    
    DistrReportVo<DistrRetuenInReportVO> getDistrReportVo(RequestDistrReturnInVO requestDistrReturnInVO);

    DistrReturnInStorageVO selectById(Long id);

    int getDistrReturnInListByEnterpiseIdCount(Map<String, Object> map);
}