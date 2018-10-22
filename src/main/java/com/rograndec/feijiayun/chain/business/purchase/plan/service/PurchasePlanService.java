package com.rograndec.feijiayun.chain.business.purchase.plan.service;

import com.rograndec.feijiayun.chain.business.purchase.plan.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author dongyang.du 2017年09月13日17:25:38
 *
 */
public interface PurchasePlanService {

	public Result<String> save(PurchasePlanVO purchasePlanVO, UserVO userVO) throws Exception;

	public PurchasePlanVO selectDetailByPlanId(Long planId, UserVO userVO) throws Exception;

	public void cancelPlan(Long planId, UserVO userVO);

	public void delete(Long planDetailId, UserVO loginUser);

	public Page<PurchasePlanReturnVO> getPurchasePlanPage(Page<PurchasePlanReturnVO> page, Integer status, String code, String pannerName,
			Date startDate, Date endDate, String order, String sort, UserVO loginUser);

	public Result<String> update(PurchasePlanVO purchasePlanVO, UserVO loginUser) throws Exception;

	public List<String> createOrder(Long planId, UserVO loginUser) throws Exception;

	/**
	 * 获取历史单价
	 * 
	 * @param goodsId
	 * @param limit
	 * @return
	 */
	public List<HistoricalUnitPriceVO> selectHistoryUnitPrice(Long goodsId, Integer limit);

	/**
	 * 导出计划明细
	 *  @param output
	 * @param planId
	 * @param userVO
	 */
	public void exportDetail(OutputStream output, Long planId, UserVO userVO) throws Exception;

	public List<SupplierVO> getBasicSupplier(UserVO userVO);

	/**
	 * 根据缺配单生成采购计划
	 * @param lackIds
	 * @return
	 */
	List<PurchasePlanGoodsVO> getByDistrLack(String lackIds,UserVO userVO);

	/**
	 * 智能采购
	 * @param intellectPurchaseReqVO
	 * @param userVO
	 * @return
	 */
	List<PurchasePlanGoodsVO> intelligentPurchase(IntellectPurchaseReqVO intellectPurchaseReqVO, UserVO userVO);

	/**
	 * 搜索商品
	 * @return
	 */

	List<PurchasePlanGoodsVO> getGoodsList(String param, UserVO userVO);

	/**
	 * 根据商品的经营范围获取供货单位
	 * @param userVO
	 * @param goodsId
	 * @return
	 */
    List<SupplierVO> getBasicSupplierByBusinessScope(UserVO userVO, Long goodsId);

	DraftCacheVO<PurchasePlanDraftCacheVO> saveDraftCache(UserVO userVO, DraftCacheVO<PurchasePlanDraftCacheVO> draftCacheVO);

	void removeDraftCach(Long enterpriseId, String type, String redisKeyValue);

	List<DraftCacheVO> getDraftCacheVO(UserVO userVO);

	/**
	 * 查询商品带分页
	 *
	 * @param page
	 * @param param
	 * @param userVO
	 * @return
	 */
    Page<List<PurchasePlanGoodsVO>> getGoodsListPage(Page<List<PurchasePlanGoodsVO>> page, String param, UserVO userVO);
}
