package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceivableInvoiceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ReceivableInvoice record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ReceivableInvoice record);

    /**
     *
     * @mbg.generated
     */
    ReceivableInvoice selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReceivableInvoice record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReceivableInvoice record);

    Integer getReveivableinvoicePageCount(@Param("enterpriseId")Long enterpriseId, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes, @Param("purchaseUnitCode")String purchaseUnitCode,
                                          @Param("purchaseUnitName")String purchaseUnitName, @Param("code")String code, @Param("billManName")String billManName, @Param("status")Integer status);

    List<ReveivableInvoicePageVO> getReveivableinvoicePage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes,
                                                           @Param("purchaseUnitCode")String purchaseUnitCode, @Param("purchaseUnitName")String purchaseUnitName, @Param("code")String code, @Param("billManName")String billManName,
                                                           @Param("status")Integer status, @Param("orderName")String orderName, @Param("orderType")String orderType);

    Integer getDistrOutPageCount(@Param("enterpriseId")Long enterpriseId, @Param("purchaseUnitId")Long purchaseUnitId, @Param("status")List<Integer> status,  @Param("key")String key);

    List<DistrOutPageVO> getDistrOutPage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("purchaseUnitId")Long purchaseUnitId, @Param("status")List<Integer> status, @Param("key")String key);

    List<PurchaseUnitVO> getPurchaseUnit(@Param("enterpriseId")Long enterpriseId, @Param("chainType")Integer chainType, @Param("key")String key);

    Integer getDistrOutShelfCount(@Param("id")Long id, @Param("status")Integer status);

    Integer getDistrOutDetailsCount(@Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer getCallDistrOutPageCount(@Param("enterpriseId")Long enterpriseId, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes, @Param("orderName")String orderName, @Param("orderType")String orderType,
                                     @Param("chainType")Integer chainType, @Param("status")List<Integer> status, @Param("supplierId")Long supplierId);

    List<CallDistrOutPageVO> getCallDistrOutPage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes,
                                                 @Param("orderName")String orderName, @Param("orderType")String orderType, @Param("chainType")Integer chainType, @Param("status")List<Integer> status, @Param("supplierId")Long supplierId);

    List<DistrOutPageVO> getCallDistrOutDeatil(@Param("enterpriseId")Long enterpriseId, @Param("ids")List<Long> ids, @Param("status")List<Integer> status);

    SaveReveivableInvoiceVO getInvoice(Long id);
}