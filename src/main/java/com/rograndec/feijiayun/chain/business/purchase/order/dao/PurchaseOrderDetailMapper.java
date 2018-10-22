package com.rograndec.feijiayun.chain.business.purchase.order.dao;

import java.math.BigDecimal;
import java.util.List;

import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.PurchaseOrderGoodsVO;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.UnitPriceVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailVO;

import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.OrderPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase.PurchaseOrderReportVO;
import org.apache.ibatis.annotations.Param;

public interface PurchaseOrderDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);
    
    int deleteByOrderId(Long orderId);
    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseOrderDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseOrderDetail record);

    /**
     *
     * @mbg.generated
     */
    PurchaseOrderDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseOrderDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseOrderDetail record);
    
    List<PurchaseOrderDetail> selectByOrderId(long orderId);

    List<UnitPriceVO> selectUnitPriceVOInId(List<Long> list);

	List<PurchaseOrderDetail> selectOrderDetailInId(List<Long> list);


    List<PurchaseOrderDetail> selectByEnterpriseIdByOrderId(@Param("enterpriseId") Long enterpriseId, @Param("orderId") Long id);

	void updateStatusFinishedByOrderId(@Param("status")Integer status, @Param("orderId")Long orderId);
	void updateStatusFinishedById(@Param("status")Integer status, @Param("enterpriseId") Long enterpriseId, @Param("id")Long id);

    List<PurchaseOrderReportVO> getPlanReportInfo(@Param("enterpriseId") Long enterpriseId, @Param("orderPageVO") OrderPageVO orderPageVO);

    List<PurchaseOrderDetail> selectOrderByGoodsIdAndEnterpriseId(@Param("enterpriseId")Long enterPriseId, @Param("goodsId") Long goodsId);

    BigDecimal selectCountByEnterPriseIdAndGoodsIdAndOrderId(@Param("enterPriseId") Long enterPriseId, @Param("goodsId") Long goodsId, @Param("id") Long id);

    List<PurchaseOrderDetail> selectOrderByGoodsIdAndEnterpriseIdAndSupplierId(@Param("goodsId") Long goodsId, @Param("supplierId") Long supplierId, @Param("enterpriseId") Long enterpriseId);

    /**
     * 
     * @Description: 获取采购订单的商品信息
     * @author yuting.li
     * @version 1.0 
     * @date 2017年12月1日 下午5:27:16 
     * @param pageNo
     * @param pageSize
     * @param enterpriseId
     * @param orderId
     * @return 
     * @return List<PurchaseOrderGoodsVO>
     */
    List<PurchaseOrderGoodsVO> getOrderGoods(@Param("pageNo")int pageNo,@Param("pageSize")int pageSize, @Param("enterpriseId")Long enterpriseId,@Param("orderId") Long orderId);
    Long countOrderGoods(@Param("enterpriseId")Long enterpriseId,@Param("orderId") Long orderId);
    
    /**
     * 
     * @Description: 批量新增
     * @author yuting.li
     * @version 1.0 
     * @date 2017年12月2日 上午10:35:23 
     * @param list
     * @return 
     * @return int
     */
    int batchInsert(List<PurchaseOrderDetail> list);
    
}