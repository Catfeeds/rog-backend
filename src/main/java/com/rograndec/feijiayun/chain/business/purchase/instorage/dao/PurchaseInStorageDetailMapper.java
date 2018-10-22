package com.rograndec.feijiayun.chain.business.purchase.instorage.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrPurchaseInstorageDetailShelfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrPurchaseInstorageDetailVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceInStoreResponseVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PurchaseInStorageDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseInStorageDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseInStorageDetail record);

    /**
     *
     * @mbg.generated
     */
    PurchaseInStorageDetail selectByPrimaryKey(Long id);

    List<PurchaseInStorageDetail> selectByInStorageId(Long id);

    List<PurchaseInStorageDetail> selectByInStorageIds(List<Long> list);

    List<DistrPurchaseInstorageDetailVO> selectByDistrPurchaseInStorageId(Long id);

    List<PurchaseInStorageDetail> selectByStorageDesc(@Param("enterpriseId") Long enterpriseId,  @Param("list") List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseInStorageDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseInStorageDetail record);

    List<PurchaseInStorageDetail> selectByEnterpriseIdAndId(@Param("enterpriseId") Long enterpriseId, @Param("id") Long id);

    /**
     * @Description: TODO批量保存
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月16日 上午11:17:26 
     * @param list
     * @return void
     */
	void batchSave(List<PurchaseInStorageDetail> list);

    /**
     * 根据商品Id获取最近一条入库记录
     * @param goodsId
     * @return
     * @author dongyang.du
     * @remark 采购计划:获取最近一次采购单价和供货单位
     */
    PurchaseInStorageDetail selectOneByGoodsIdOrderDate(@Param("goodsId")Long goodsId);

    List<DistrPurchaseInstorageDetailShelfVO> selectByPurchaseInstorageId(Long id);

    List<PurchaseInStorageDetail> selectByIds(@Param("enterpriseId") Long enterpriseId, @Param("list") List<Long> inStorageDtlS);
    List<PrepayInvoiceInStoreResponseVO> select2PrepayInvoice(@Param("enterpriseId") Long enterpriseId,
                                                              @Param("goodsId") Long goodsId,
                                                              @Param("startDate") Date startDate,
                                                              @Param("endDate") Date endDate,
                                                              @Param("list") List<Integer> list,@Param("supplierId") Long supplierId
    );


    DistrPurchaseInstorageDetailShelfVO getGoodsIdAndInStorageId(@Param("id")Long id, @Param("goodsId")Long goodsId);
}