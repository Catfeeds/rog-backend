package com.rograndec.feijiayun.chain.business.aftersale.adverse.dao;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.entity.AdverseReactionTogetherGoods;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdverseReactionTogetherGoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AdverseReactionTogetherGoods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AdverseReactionTogetherGoods record);

    /**
     *
     * @mbg.generated
     */
    AdverseReactionTogetherGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdverseReactionTogetherGoods record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdverseReactionTogetherGoods record);

    List<ReportGoodsVO> selectByReportId(@Param("reportId") Long id);

    void deleteByReportId(@Param("reportId")Long id);
}