package com.rograndec.feijiayun.chain.business.purchase.check.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseCheckReportPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.RequestParamForCheckReportListVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.TotalPurchaseCheckReportVO;
import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckLot;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageDetailVO;

public interface PurchaseCheckLotMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseCheckLot record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseCheckLot record);

    /**
     *
     * @mbg.generated
     */
    PurchaseCheckLot selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseCheckLot record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseCheckLot record);

    /**
     * @Description: TODO查询采购验收明细品种
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月14日 下午1:29:42 
     * @param id
     * @return 
     * @return List<StayInstorageDetailVO>
     */
	List<StayInstorageDetailVO> selectStayInstorageDetailListByCheckId(Long id);

	/**
	 * @Description: TODO根据ids查询所有PurchaseCheckLot
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月16日 上午10:47:10 
	 * @param
	 * @return 
	 * @return List<PurchaseCheckLot>
	 */
	List<PurchaseCheckLot> selectByIds(List<Long> list);


	void updateStatusFinishedByCheckId(@Param("status")Integer status, @Param("checkId")Long checkId);

    int batchInsert(List<PurchaseCheckLot> purchaseCheckLots);

    List<PurchaseCheckLot> selectByEnterpriseIdByCheckId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<PurchaseCheckReportPageVO> getPurchaseCheckReportPageList(@Param("paramForListVO")RequestParamForCheckReportListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);

    TotalPurchaseCheckReportVO<PurchaseCheckReportPageVO> totalPurchaseCheckReport(@Param("paramForListVO")RequestParamForCheckReportListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);

    List<PurchaseCheckLot> selectByDtlId(Long id);

    Integer getPurchaseCheckReportPageCount(@Param("paramForListVO")RequestParamForCheckReportListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);
}