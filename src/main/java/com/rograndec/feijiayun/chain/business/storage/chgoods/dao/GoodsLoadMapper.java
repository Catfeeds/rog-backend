package com.rograndec.feijiayun.chain.business.storage.chgoods.dao;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodLoadReportPageVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsLoad;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsLoadOrderDtlListVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsLoadOrderListVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.GoodsStockListVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GoodsLoadMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsLoad record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsLoad record);

    /**
     *
     * @mbg.generated
     */
    GoodsLoad selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsLoad record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsLoad record);

    Long queryCountByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                            @Param("endTimes")String endTimes, @Param("code")String code, @Param("loadManName")String loadManName, @Param("auditManName")String auditManName);

    List<ChGoodsLoadOrderListVO> selectByParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes,
                                                @Param("endTimes")String endTimes, @Param("orderName")String orderName, @Param("orderType")String orderType, @Param("code")String code,
                                                @Param("loadManName")String loadManName, @Param("auditManName")String auditManName);

    ChGoodsLoadOrderDtlListVO selectByDtlParams(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    /**
     * 五合一查询  装斗时的库存信息   zeshi.sun
     * @return
     */
    List<GoodsStockListVO> selectGoodsStockList(Map<String, Object> map);

    List<GoodLoadReportPageVO> selectFatherGoodLoadReport(Map<String, Object> map);

    List<GoodLoadReportPageVO> selectSonGoodLoadReport(Map<String, Object> map);

    BigDecimal selectFatherQuantity(Map<String, Object> map);

    BigDecimal selectSonQuantity(Map<String, Object> map);

    Integer selectFatherStastic(Map<String, Object> map);

    Integer selectSonStastic(Map<String, Object> map);
}