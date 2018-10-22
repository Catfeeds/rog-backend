package com.rograndec.feijiayun.chain.business.storage.chgoods.dao;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodClearReportPageVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClear;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsClearOrderDtlListVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsClearOrderListVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GoodsClearMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsClear record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsClear record);

    /**
     *
     * @mbg.generated
     */
    GoodsClear selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsClear record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsClear record);

    Long queryCountByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                            @Param("endTimes")String endTimes, @Param("code")String code, @Param("clearManName")String clearManName, @Param("auditManName")String auditManName);

    List<ChGoodsClearOrderListVO> selectByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                                 @Param("endTimes")String endTimes, @Param("orderName")String orderName, @Param("orderType")String orderType, @Param("code")String code,
                                                 @Param("clearManName")String clearManName, @Param("auditManName")String auditManName);

    ChGoodsClearOrderDtlListVO selectByDtlParams(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<GoodClearReportPageVO> selectFatherGoodClearReport(Map<String, Object> map);

    List<GoodClearReportPageVO> selectSonGoodClearReport(Map<String, Object> map);

    BigDecimal selectFatherQuantity(Map<String, Object> map);

    BigDecimal selectSonQuantity(Map<String, Object> map);

    Integer selectFatherStastic(Map<String, Object> map);

    Integer selectSonStastic(Map<String, Object> map);
}