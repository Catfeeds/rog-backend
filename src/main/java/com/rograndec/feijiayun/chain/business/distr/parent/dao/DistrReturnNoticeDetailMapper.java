package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeDtlVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DistrReturnNoticeDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReturnNoticeDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReturnNoticeDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrReturnNoticeDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReturnNoticeDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReturnNoticeDetail record);

    List<DistrReturnNoticeDetail> selectByEnterpriseIdAndNoticeId(@Param("enterpriseId") Long enterpriseId,@Param("id") Long id);

    int updateStatus(@Param("status") Integer status, @Param("noticeId") Long noticeId, @Param("enterpriseId") Long enterpriseId);

    int updateStatusById(@Param("id") Long id, @Param("status") Integer status, @Param("enterpriseId") Long enterpriseId, @Param("unclearQuantity") BigDecimal unclearQuantity, @Param("clearQuantity") BigDecimal clearQuantity);

    List<Integer> selectSpecialDrugByEnterpriseIdAndNoticeId(@Param("enterpriseId") Long enterpriseId, @Param("id") Long id);

    List<DistrReturnNoticeDetail> selectByEnterPriseIdAndGoodsId(@Param("enterpriseId")Long enterPriseId, @Param("goodsId") Long goodsId);

    BigDecimal selectCountByEnterPriseIdAndGoodsIdAndNoticeId(@Param("enterpriseId")Long enterPriseId, @Param("goodsId") Long goodsId, @Param("id") Long id);

}