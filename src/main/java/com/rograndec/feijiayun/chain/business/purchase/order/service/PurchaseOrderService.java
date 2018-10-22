package com.rograndec.feijiayun.chain.business.purchase.order.service;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.EnterpriseOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.GoodsOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderCountVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderReqVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderResponseVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseUserOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.RecordVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.SaleManOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.SupplierOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.UserOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanDraftCacheVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by ST on 2017/8/23.
 */
public interface PurchaseOrderService {
    /**
     * 添加采购订单
     * @param PurchaseOrder
     * @return
     * @throws Exception 
     */
    void addOrder(UserVO userVO,PurchaseOrderReqVO purchaseOrderRequestVO) throws Exception;
    /**
     * 通过采购计划添加采购订单
     * @param PurchaseOrder
     * @return
     * @throws Exception 
     */
    String addOrderByPlan(UserVO userVO,PurchaseOrderVO purchaseOrderVO) throws Exception;

    /**
     * 修改采购订单
     * @param PurchaseOrder
     * @return
     * @throws Exception 
     */
    void updateOrder(UserVO userVO,PurchaseOrderReqVO purchaseOrderRequestVO) throws Exception;
    /**
     * 根据采购订单id查询采购订单
     * @param PurchaseOrder
     * @return
     */
    PurchaseOrder selectByPrimaryKey(Long id);
    /**
     * 根据企业id查询订单列表
     * @param PurchaseOrder
     * @return
     * start
     * @throws ParseException 
     * 
     */
    void selectByEnterpriseId(Page page,Long enterpriseId,String supplier_code,String supplier_name,
			String code,String purchaser_name,String sort,String status,String sortField,Date startDate,Date endDate) throws ParseException;
    
    /**
     * 根据采购订单id查询采购基本信息，采购订单详情列表，配送和计算
     * @param PurchaseOrder
     * @return
     */
    PurchaseOrderResponseVO selectById(Long id, UserVO userVO);
    
    void exportExcel(OutputStream output, Long id, UserVO loginUser);
    
    void changeStatus(UserVO userVO,PurchaseOrder order) throws Exception;
    
    List<SupplierOrderVO> getSupplier(UserVO userVO);
    
    List<SaleManOrderVO> getSaleMan(Long supplierId);
    
    public List<EnterpriseOrderVO> getEnterprise(Long parentId);
    
    public List<UserOrderVO> getUser(Long enterpriseId);
    
    public List<PurchaseUserOrderVO> getPurchaseUser(Long enterpriseId);
    
    public List<GoodsOrderVO> getGoods(Map map);
    
	public List<RecordVO> getRecord(Map map);
	
	public UserOrderVO getManageConfigByEnterpriseId(Long enterpriseId);
	void temporarySave(UserVO userVO, PurchaseOrderReqVO purchaseOrderRequestVO);
	List<PurchaseOrderReqVO> temporaryGet(UserVO userVO);
	void getGoodsByPage(Page page, Map map) throws ParseException;

    DraftCacheVO<PurchaseOrderReqVO> saveDraftCache(UserVO userVO, DraftCacheVO<PurchaseOrderReqVO> draftCacheVO);

	void removeDraftCach(Long enterpriseId, String type, String redisKeyValue);

	List<DraftCacheVO> getDraftCacheVO(UserVO userVO);
}
