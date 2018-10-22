package com.rograndec.feijiayun.chain.business.purchase.instorage.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrPurchaseInStorageVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageForReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageStasticVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.GetInStorageParamVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PurchaseInStorageMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseInStorage record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseInStorage record);

    /**
     *
     * @mbg.generated
     */
    PurchaseInStorage selectByPrimaryKey(Long id);

    List<PurchaseInStorage> selectByIds(@Param("list") List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseInStorage record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseInStorage record);


    List<PurchaseInStorage> selectHasBeenInstoragePage(Map<String, Object> map);


    List<PurchaseInStorageForReturnVO> getPurchaseInStorageForReturn(GetInStorageParamVO getInStorageParamVO);


    /**
     * 总部配送单-获取采购入库单列表
     * @param map
     * @return
     */
    List<PurchaseInStorage> getParchaseInOrderList(Map<String, Object> map);

    List<PurchaseInStorage> selectByCheckIdByEnterpriseId(@Param("checkId") Long checkId, @Param("enterpriseId") Long enterpriseId);

    Integer getTotalRecord(Map<String, Object> map);

    PurchaseInStorageStasticVO selectStasticMoney(Map<String, Object> map);

    DistrPurchaseInStorageVO selectById(Long id);

    Integer getParchaseInOrderListCount(Map<String, Object> map);

    /**
     * 查询总部应付账款未开票数据记录数
     * @param map
     * @return
     */
    Integer getPaymentPendingInvoicingCount(Map<String, Object> map);
    /**
     * 查询总部应付账款未开票分页数据
     * @param map
     * @return
     */
    List<PendingInvoicingVO> getPaymentPendingInvoicingList(Map<String, Object> map);

    List<PurchaseInStorage> select2PaymentInvoice(@Param("enterpriseId") Long enterpriseId,
                                                  @Param("startDate") Date startDate,
                                                  @Param("endDate") Date endDate,
                                                  @Param("list") List<Integer> list, @Param("supplierId") Long supplierId
    );
}