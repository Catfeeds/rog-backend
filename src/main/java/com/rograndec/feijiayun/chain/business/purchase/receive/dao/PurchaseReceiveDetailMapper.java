package com.rograndec.feijiayun.chain.business.purchase.receive.dao;

import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveDetail;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseReceiveReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.ReceivePageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseReceiveDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReceiveDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReceiveDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReceiveDetail record);

    List<PurchaseReceiveDetail> selectByEnterpriseIdByReceiveId(@Param("enterpriseId") Long enterpriseId, @Param("receiveId") Long id);

    int batchInsert(List<PurchaseReceiveDetail> purchaseReceiveDetails);

	void updateStatusFinishedByReceiveId(@Param("status")Integer status, @Param("receiveId")Long receiveId);

    List<PurchaseReceiveReportVO> getPlanReportInfo(@Param("enterpriseId") Long enterpriseId, @Param("receivePageVO") ReceivePageVO receivePageVO);
}