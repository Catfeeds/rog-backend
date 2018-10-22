package com.rograndec.feijiayun.chain.business.purchase.order.dao;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.*;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierFileReportDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PurchaseOrderMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseOrder record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseOrder record);

    /**
     *
     * @mbg.generated
     */
    PurchaseOrder selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseOrder record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseOrder record);
    /**
     * 根据企业id获取采购订单列表
     * */
    List<PurchaseOrderResponseVO> selectByEnterpriseId(@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize, @Param("enterpriseId")Long enterpriseId,@Param("supplier_code")String supplier_code, @Param("supplier_name")String supplier_name,
			@Param("code")String code, @Param("purchaser_name")String purchaser_name,@Param("sort")String sort,@Param("status")List<Integer> statusList,@Param("sortField") String sortField,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    /**
     * 获取采购订单的数量
     * */
    Long queryCountByEnterpriseId(@Param("enterpriseId")Long enterpriseId,@Param("supplier_code")String supplier_code, @Param("supplier_name")String supplier_name,
			@Param("code")String code, @Param("purchaser_name")String purchaser_name,@Param("status")List<Integer> statusList,@Param("sortField") String sortField,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
    /**
     * 根据订单id获取订单数据，包括详情等
     * */
    PurchaseOrderResponseVO selectById(Long id);

    List<PurchaseOrder> getWillReceive(Map param);
    
    /**
     * 获取采购订单中的，金额合计，不含税金额合计，税额合计
     * */
    PurchaseOrderCountVO selectCount(@Param("pageNo")int pageNo,
			@Param("pageSize")int pageSize, @Param("enterpriseId")Long enterpriseId,@Param("supplier_code")String supplier_code, @Param("supplier_name")String supplier_name,
			@Param("code")String code, @Param("purchaser_name")String purchaser_name,@Param("sort")String sort,@Param("status")List<Integer> statusList,@Param("sortField") String sortField,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

	void updateStatusFinishedById(@Param("status")Integer status, @Param("id")Long id);
	
	List<SupplierOrderVO> selectSupplier(Map<String, Object> param);
	
	List<SaleManOrderVO> selectSaleMan(Long supplierId);
	
	List<EnterpriseOrderVO> selectEnterprise(Long parentId);
	
	List<UserOrderVO> selectUser(Long enterpriseId);
	
	List<PurchaseUserOrderVO> selectPurchaseUser(Long enterpriseId);
	
	List<GoodsOrderVO> selectGoods(Map map);
	
	List<RecordVO> selectRecord(Map map);

    /**
     * 供货单位的订单数据统计
     * @param supplierId
     * @param enterpriseId
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    SupplierFileReportDetailVO getPurchaseOrderCount2Supplier(@Param("supplierId")Long supplierId, @Param("enterpriseId")Long enterpriseId, @Param("canceled")Integer canceled);

    /**
     * 供货单位的订单品种统计
     * @param supplierId
     * @param enterpriseId
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    Long getPurchaseOrderVarietiesQuantity2Supplier(@Param("supplierId")Long supplierId,@Param("enterpriseId")Long enterpriseId,@Param("canceled")Integer canceled);


    /**
     * 采购拒收数量统计
     * @param supplierId 供货单位
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    SupplierFileReportDetailVO  getPurchaseReceiveCount2Supplier(@Param("supplierId")Long supplierId);


    /**
     * 采购拒收品种统计
     * @param receiveId
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    Long getPurchaseReceiveVarietiesQuantity2Supplier(@Param("supplierId")Long supplierId);

    /**
     * 采购验收不合格数量统计
     * @param supplierId 供货单位id
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    SupplierFileReportDetailVO  getPurchaseCheckStatistics2Supplier(@Param("supplierId")Long supplierId);

    /**
     * 采购验收不合格品种统计
     * @param supplierId
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    Long getPurchaseCheckUnVarietiesQuantityStatistics2Supplier(@Param("supplierId")Long supplierId);


    /**
     * 采购入库数量统计
     * @param supplierId 供货单位id
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    SupplierFileReportDetailVO  getPurchaseInStorageStatistics2Supplier(@Param("supplierId")Long supplierId);


    /**
     * 采购入库品种统计
     * @param supplierId
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    Long getPurchaseInStorageVarietiesQuantityStatistics2Supplier(@Param("supplierId")Long supplierId);

    /**
     * 购进退出出库数量统计
     * @param supplierId 供货单位id
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    SupplierFileReportDetailVO   getPurchaseReturnOutStatistics2Supplier(@Param("supplierId")Long supplierId);

    /**
     * 购进退出出库品种统计
     * @param supplierId
     * @param statusDraft
     * @param statusCancel
     * @return
     */
    Long getPurchaseReturnOutVarietiesQuantityStatistics2Supplier(@Param("supplierId")Long supplierId);

    UserOrderVO selectDefaultUser(Long enterpriseId);

    List<SupplierOrderVO> selectSupplierByReturn(Map<String, Object> param);

    Integer getWillReceiveCount(Map param);
    //分页获取商品
    List<GoodsOrderVO> queryGoodsList(Map map);
    Long queryGoodsCount(Map map);
}