package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticePageVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeStasticVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReturnNoticeReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnNoticeVO;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DistrReturnNoticeMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnNotice record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnNotice record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnNotice selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnNotice record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnNotice record);

    List<DistrReturnNotice> getReturnNoticePage(Map<String, Object> map);

    int updateStatus(@Param("status") Integer status, @Param("id") Long id, @Param("enterpriseId") Long enterpriseId);
    
    List<DistrReturnNoticeReportVO> getDistrReturnNoticeReport(RequestDistrReturnNoticeVO requestDistrReturnNoticeVO);
    
    Integer getDistrReturnNoticeReportTotalNum(RequestDistrReturnNoticeVO requestDistrReturnNoticeVO);
    
    DistrReportVo<DistrReturnNoticeReportVO> getDistrReportVo(RequestDistrReturnNoticeVO requestDistrReturnNoticeVO);

    /**
     * 获取配后退回列表
     * @param map
     * @return
     */
    List<DistrReturnNoticePageVO> getDistrReturnNoticeList(Map<String, Object> map);

    Integer getTotalRecord(Map<String, Object> map);

    DistrReturnNoticeStasticVO selectReturnNoticeStastic(Map<String, Object> map);
}