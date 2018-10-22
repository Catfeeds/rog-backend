package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInNoticeDetailVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DistrInNoticeDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInNoticeDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInNoticeDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrInNoticeDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInNoticeDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInNoticeDetail record);

    void updateStatusByOrderId(@Param("orderId") Long noticeId, @Param("status") Integer status);
    /** 
    * @Description: 更新状态和未清数量
    * @return:  
    * @Author: dongyang.du
    * @Date: 19/01/2018 
    */ 
    void updateStatusById(@Param("id") Long id, @Param("status") Integer status, @Param("enterpriseId") Long enterpriseId,@Param("arrivalQuantity") BigDecimal arrivalQuantity);

    List<DistrInNoticeDetail> selectByNoticeIdByEnterpriseId(@Param("orderId") Long id, @Param("enterpriseId") Long enterpriseId);

    List<DistrInNoticeDetail> selectByGoodsIdAndEnterpriseId(@Param("goodsId") Long goodsId, @Param("enterPriseId") Long enterPriseId);

    BigDecimal selectCountByEnterPriseIdAndGoodsIdAndOrderId(@Param("enterPriseId") Long enterPriseId, @Param("goodsId") Long goodsId, @Param("id") Long id);

    DistrInNoticeDetail selectByNoticeIdByEnterpriseIdAndGoodsId(@Param("enterPriseId")Long enterpriseId, @Param("id")Long id, @Param("goodsId")Long goodsId);

    DistrInNoticeDetailVO selectByDistrReqPlan(@Param("outboundUnitId")Long outboundUnitId, @Param("goodsId")Long goodsId);

    DistrInNoticeDetail selectByNoticeIdByEnterpriseIdAndNoticeDtlId(@Param("enterPriseId")Long enterpriseId, @Param("id")Long id, @Param("noticeDtlId")Long noticeDtlId);
}