package com.rograndec.feijiayun.chain.business.purchase.plan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.purchase.plan.entity.PurchasePlanModifyRecord;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanModifyRecordVO;

public interface PurchasePlanModifyRecordMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(PurchasePlanModifyRecord record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(PurchasePlanModifyRecord record);

	/**
	 *
	 * @mbg.generated
	 */
	PurchasePlanModifyRecord selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(PurchasePlanModifyRecord record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(PurchasePlanModifyRecord record);

	List<PurchasePlanModifyRecordVO> getRecordPageByPlanID(@Param("planId") Long planId);
}