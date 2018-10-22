package com.rograndec.feijiayun.chain.business.aftersale.adverse.dao;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.entity.AdverseReactionReport;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.GoodsReportVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportPageVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportRequestVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdverseReactionReportMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AdverseReactionReport record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AdverseReactionReport record);

    /**
     *
     * @mbg.generated
     */
    AdverseReactionReport selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdverseReactionReport record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdverseReactionReport record);

    List<ReportPageVO> selectReportPage(ReportRequestVO requestVO);

    /**
     * 查询商品，不加限制条件
     * @param map
     * @return
     */
    List<GoodsReportVO> selectGoods(Map<String, Object> map);

    /**
     * 报表分页列表查询
     * @param requestVO
     * @return
     */
    List<ReportPageVO> selectReportReportPage(ReportRequestVO requestVO);

    /**
     * 获取报表总数
     * @param requestVO
     * @return
     */
    Integer selectReportReportCount(ReportRequestVO requestVO);

    Integer selectReportPageCount(ReportRequestVO requestVO);
}