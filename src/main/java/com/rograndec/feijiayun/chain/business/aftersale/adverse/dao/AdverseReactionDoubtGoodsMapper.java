package com.rograndec.feijiayun.chain.business.aftersale.adverse.dao;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.entity.AdverseReactionDoubtGoods;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdverseReactionDoubtGoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AdverseReactionDoubtGoods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AdverseReactionDoubtGoods record);

    /**
     *
     * @mbg.generated
     */
    AdverseReactionDoubtGoods selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdverseReactionDoubtGoods record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdverseReactionDoubtGoods record);

    List<ReportGoodsVO> selectByReportId(@Param("reportId") Long id);

    void deleteByReportId(@Param("reportId")Long id);
}