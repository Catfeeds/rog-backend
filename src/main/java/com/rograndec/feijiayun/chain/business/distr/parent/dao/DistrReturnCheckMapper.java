package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheck;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnReceive;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnReceiveDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.ClickCheckDetailVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.ClickCheckHeadVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnCheckHeadVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnCheckVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReturnCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnCheckVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DistrReturnCheckMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnCheck record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnCheck record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnCheck selectByPrimaryKey(Long id);

    List<DistrReturnCheck> selectByParam(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnCheck record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnCheck record);

    ClickCheckHeadVO selectByReceiveHead(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<ClickCheckDetailVO> selectByReceiveDetail(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    DistrReturnReceive selectByReceiveId(@Param("enterpriseId")Long enterpriseId, @Param("receiveId")Long receiveId);

    List<DistrReturnReceiveDetail> selectByDistrReturnReceiveId(@Param("receiveId")Long receiveId);
    DistrReturnReceiveDetail selectByDistrReturnReceiveDetail(@Param("enterpriseId")Long enterpriseId, @Param("distrReturnReceiveDetailId")Long distrReturnReceiveDetailId);

    Long queryCountByDistrReturnCheckParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                            @Param("endTimes")String endTimes, @Param("requestUnitCode")String requestUnitCode, @Param("requestUnitName")String requestUnitName, @Param("code")String code,
                                            @Param("distrType")Integer distrType, @Param("checkerName")String checkerName, @Param("secondCheckerName")String secondCheckerName);

    List<DistrReturnCheckVO> selectByDistrReturnCheckParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                                            @Param("endTimes")String endTimes, @Param("requestUnitCode")String requestUnitCode, @Param("requestUnitName")String requestUnitName, @Param("code")String code,
                                                            @Param("distrType")Integer distrType, @Param("checkerName")String checkerName, @Param("secondCheckerName")String secondCheckerName, @Param("orderName")String orderName, @Param("orderType")String orderType);

    DistrReturnCheckHeadVO selectByIdAndEnterpriseId(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

    Long queryCountByDistrReturnCheckOtherParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                                 @Param("endTimes")String endTimes, @Param("requestUnitCode")String requestUnitCode, @Param("requestUnitName")String requestUnitName, @Param("code")String code,
                                                 @Param("distrType")Integer distrType, @Param("checkerName")String checkerName, @Param("secondCheckerName")String secondCheckerName,@Param("status")int status);

    List<DistrReturnCheckVO> selectByDistrReturnCheckOtherParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                                                 @Param("endTimes")String endTimes,@Param("requestUnitCode")String requestUnitCode, @Param("requestUnitName")String requestUnitName, @Param("code")String code,
                                                                 @Param("distrType")Integer distrType, @Param("checkerName")String checkerName, @Param("secondCheckerName")String secondCheckerName, @Param("orderName")String orderName, @Param("orderType")String orderType
                                                                ,@Param("status")int status);

    Integer updateByReceive(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer updateByReceiveDetail(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer updateByOrder(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer updateByOrderDetail(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer updateTotal(@Param("enterpriseId")Long enterpriseId, @Param("mainId")Long mainId);

    Integer updateVarieties(@Param("enterpriseId")Long enterpriseId, @Param("mainId")Long mainId, @Param("size")int size);

    List<DistrReturnCheckReportVO> getDistrReturnCheckReport(RequestDistrReturnCheckVO requestDistrReturnCheckVO);
    
    Integer getDistrReturnCheckReportTotalNum(RequestDistrReturnCheckVO requestDistrReturnCheckVO);
    
    DistrReportVo<DistrReturnCheckReportVO> getDistrReportVo(RequestDistrReturnCheckVO requestDistrReturnCheckVO);
}