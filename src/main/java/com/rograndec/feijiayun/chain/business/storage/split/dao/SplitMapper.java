package com.rograndec.feijiayun.chain.business.storage.split.dao;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsSplitReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.GoodsSplitStasticVO;
import com.rograndec.feijiayun.chain.business.storage.split.entity.Split;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitGoodsStockVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitOrderPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SplitMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Split record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Split record);

    /**
     *
     * @mbg.generated
     */
    Split selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Split record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Split record);

    /**
     * 查询可进行拆零的商品列表：1、库存>0；2、存在启用状态的拆零商品
     * @param enterpriseId 企业id
     * @param param 搜索关键字
     * @param type
     * @return
     */
    List<SplitGoodsStockVO> selectGoodsStock(@Param("enterpriseId") Long enterpriseId, @Param("currentEId") Long currentEId, @Param("param") String param, @Param("type") Integer type);

    /**
     * 查询拆零单据列表
     * @param map 参数集合
     * @return
     */
    List<SplitOrderPageVO> getSplitOrderList(Map<String, Object> map);

    /**
     * 根据商品id，拆零商品id，商品批次id、商品货位id、企业id查询拆零信息
     * @param map 参数集合
     * @return
     */
    SplitGoodsStockVO selectGoodsStockById(Map<String, Object> map);

    List<GoodsSplitReportPageVO> selectFatherGoodSplitReport(Map<String, Object> map);

    List<GoodsSplitReportPageVO> selectSonGoodSplitReport(Map<String, Object> map);

    Integer selectFatherTotalRecord(Map<String, Object> map);

    Integer selectSonTotalRecord(Map<String, Object> map);

    GoodsSplitStasticVO selectFatherGoodsSplitStastic(Map<String, Object> map);

    GoodsSplitStasticVO selectSonGoodsSplitStastic(Map<String, Object> map);
}