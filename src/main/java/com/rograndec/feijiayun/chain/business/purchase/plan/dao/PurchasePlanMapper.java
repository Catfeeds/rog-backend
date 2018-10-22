package com.rograndec.feijiayun.chain.business.purchase.plan.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.*;
import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.purchase.plan.entity.PurchasePlan;

public interface PurchasePlanMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(PurchasePlan record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(PurchasePlan record);

	/**
	 *
	 * @mbg.generated
	 */
	PurchasePlan selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(PurchasePlan record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(PurchasePlan record);

	void updateStatus(@Param("id") Long id, @Param("status") Integer status);

	List<PurchasePlanPageVO> selectPurchasePlanPage(Map<String, Object> map);

	List<HistoricalUnitPriceVO> selectHistoryUnitPrice(@Param("goodsId") Long goodsId, @Param("limit") Integer limit);

	/**
	 * 查询相应的统计
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> selectTotal(Map<String, Object> map);

	/**
	 * 查询供货单位 下拉列表: 根据商品的经营范围
	 * 
	 * @param param
	 * @return
	 */
	List<SupplierVO> selectSupplier(Map<String, Object> param);

	/**
	 * 智能采购 - 根据安全库存 生成计划明细
	 * @return
	 * @param param
	 */
    List<PurchasePlanGoodsVO> getPlanGoodsBySafety(Map<String, Object> param);


	/**
	 * 考虑在途库存
	 * @param param
	 */
	List<PurchaseOrderDetail> getOnPassage(Map<String, Object> param);

	/**
	 * 查询商品
	 * @param param
	 * @return
	 */
    List<PurchasePlanGoodsVO> getGoodsList(Map<String, Object> param);

	/**
	 * 查询销售数量和库存
	 * @param param
	 * @return
	 */
	List<PurchasePlanGoodsVO> getPlanGoodsBySale(Map<String, Object> param);

	/**
	 * 查询分页总数
	 * @param map
	 * @return
	 */
    Integer selectPurchasePlanPageCount(Map<String, Object> map);

	/**
	 * 查询配货出库数量  - 加盟店
	 * @param param
	 * @return
	 */
	List<PurchasePlanGoodsVO> getPlanGoodsByDistrOut(Map<String, Object> param);

	/**
	 *
	 * @param param
	 * @return
	 */
	List<PurchasePlanGoodsVO> getPlanGoodsByDistrReturnIn(Map<String, Object> param);


	/**
	 * 查询商品总数
	 * @param map
	 * @return
	 */
	Integer getGoodsListPageCount(Map<String, Object> map);

	/**
	 * 查询商品分页列表
	 * @param map
	 * @return
	 */
	List<PurchasePlanGoodsVO> getGoodsListPage(Map<String, Object> map);
}