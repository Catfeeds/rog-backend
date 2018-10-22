package com.rograndec.feijiayun.chain.business.purchase.plan.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PlanPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchasePlanReportVO;
import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.purchase.plan.entity.PurchasePlanDetail;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanVO;

public interface PurchasePlanDetailMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(PurchasePlanDetail record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(PurchasePlanDetail record);

	/**
	 *
	 * @mbg.generated
	 */
	PurchasePlanDetail selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(PurchasePlanDetail record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(PurchasePlanDetail record);

	PurchasePlanVO selectDetailByPlanId(Long planId);

	void updateStatusByPlanId(@Param("planId") Long planId, @Param("status") Integer status);

	/**
	 * 查询供货单位: 去重
	 * 
	 * @param planId
	 * @return
	 */
	List<PurchasePlanDetail> selectDistinctSupplierIdByPlanId(@Param("planId") Long planId);

	/**
	 * 根据计划ID和供货单位ID查询
	 * 
	 * @param planId
	 * @param supplierId
	 * 
	 * @return
	 */
	List<PurchasePlanDetail> selectBySupplierIdAndPlanId(@Param("supplierId") Long supplierId, @Param("planId") Long planId);

    List<PurchasePlanReportVO> getPlanReportInfo(@Param("enterpriseId") Long enterpriseId, @Param("planPageVO") PlanPageVO planPageVO);
}