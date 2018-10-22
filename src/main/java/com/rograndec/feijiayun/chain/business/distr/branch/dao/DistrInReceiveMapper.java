package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReceive;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2DetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInReceiveReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInReceive;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DistrInReceiveMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInReceive record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInReceive record);

    /**
     *
     * @mbg.generated
     */
    DistrInReceive selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInReceive record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInReceive record);

    DistrInCheck2DetailVO getDistrReceive2InCheckById(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<DistrInReceiveVO> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("distrUnitCode") String distrUnitCode, @Param("distrUnitName") String distrUnitName, @Param("code") String code, @Param("distrType") Integer distrType, @Param("status") Integer status, @Param("receiverName") String receiverName, @Param("secondReceiverName") String secondReceiverName, @Param("orderName") String orderName, @Param("orderType") String orderType, @Param("start") Integer start, @Param("pageSize") Integer pageSize);
    Integer selectCountByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("distrUnitCode") String distrUnitCode, @Param("distrUnitName") String distrUnitName, @Param("code") String code, @Param("distrType") Integer distrType, @Param("status") Integer status, @Param("receiverName") String receiverName, @Param("secondReceiverName") String secondReceiverName);


	int getDistrInReceiveListCount(RequestDistrInReceive requestDistrInReceive);

    List<DistrInReceiveReportVo> getDistrInReceiveList(RequestDistrInReceive requestDistrInReceive);

    List<DistrInReceive> selectByEnterpriseIdByBaseOrderId(@Param("enterpriseId") Long enterpriseId, @Param("baseOrderId") Long baseOrderId);

    DistrInReceive selectByBaseOrderId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    int updateStatusById(@Param("enterpriseId")Long enterpriseId,@Param("id")Long id,@Param("status")Integer status);
}