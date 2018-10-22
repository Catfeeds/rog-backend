package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnReceive;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReturnReceiveReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnReceiveVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DistrReturnReceiveMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnReceive record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnReceive record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnReceive selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnReceive record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnReceive record);

    List<DistrReturnReceiveVO> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("requestUnitCode") String requestUnitCode, @Param("requestUnitName") String requestUnitName, @Param("code") String code, @Param("distrType") Integer distrType, @Param("status") Integer status, @Param("receiverName") String receiverName, @Param("secondReceiverName") String secondReceiverName, @Param("orderName") String orderName, @Param("orderType") String orderType, @Param("start") Integer start, @Param("pageSize") Integer pageSize);
    Integer selectCountByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("requestUnitCode") String requestUnitCode, @Param("requestUnitName") String requestUnitName, @Param("code") String code, @Param("distrType") Integer distrType, @Param("status") Integer status, @Param("receiverName") String receiverName, @Param("secondReceiverName") String secondReceiverName, @Param("orderName") String orderName, @Param("orderType") String orderType);

    List<DistrReturnReceiveReportVO> getDistrReturnReceiveReport(RequestDistrReturnReceiveVO requestDistrReturnReceiveVO);
    
    Integer getDistrReturnReceiveReportTotalNum(RequestDistrReturnReceiveVO requestDistrReturnReceiveVO);
    
    DistrReportVo<DistrReturnReceiveReportVO> getDistrReportVo(RequestDistrReturnReceiveVO requestDistrReturnReceiveVO);


    List<DistrReturnReceive> selectByEnterpriseIdByBaseOrderId(@Param("enterpriseId") Long enterpriseId, @Param("baseOrderId") Long baseOrderId);
}