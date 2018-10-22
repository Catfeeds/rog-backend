package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucher;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo.CallDistrReturnInPageVO;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo.DistrReturnInPageListVO;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo.ReveivableVoucherPageVO;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo.SaveReveivableVoucherVO;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.PayDocumentsPageVO;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.PayDocumentsVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReceivableVoucherMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ReceivableVoucher record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ReceivableVoucher record);

    /**
     *
     * @mbg.generated
     */
    ReceivableVoucher selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReceivableVoucher record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReceivableVoucher record);

    List<PayDocumentsPageVO> selectWithUnclear(Map<String, Object> map);

    Integer selectTotalRecord(Map<String, Object> map);

    PayDocumentsVO selectAmountTotal(Map<String, Object> map);

    Integer getReceivableVoucherPageCount(@Param("enterpriseId")Long enterpriseId, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes, @Param("purchaseUnitCode")String purchaseUnitCode, @Param("purchaseUnitName")String purchaseUnitName,
                                          @Param("code")String code, @Param("postManName")String postManName, @Param("status")Integer status);

    List<ReveivableVoucherPageVO> getReceivableVoucherPage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes,
                                                           @Param("purchaseUnitCode")String purchaseUnitCode, @Param("purchaseUnitName")String purchaseUnitName, @Param("code")String code, @Param("postManName")String postManName,
                                                           @Param("status")Integer status, @Param("orderName")String orderName, @Param("orderType")String orderType);

    Integer getDistrReturnInTotalCount(@Param("enterpriseId")Long enterpriseId, @Param("purchaseUnitId")Long purchaseUnitId, @Param("status")List<Integer> status, @Param("key")String key);

    List<DistrReturnInPageListVO> getDistrReturnInTotal(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("purchaseUnitId")Long purchaseUnitId,
                                                        @Param("status")List<Integer> status, @Param("key")String key);

    Integer getCallDistrReturnInPageCount(@Param("enterpriseId")Long enterpriseId,  @Param("startTimes")String startTimes, @Param("endTimes")String endTimes, @Param("orderName")String orderName, @Param("orderType")String orderType,
                                          @Param("chainType")int chainType, @Param("status")List<Integer> status, @Param("supplierId")Long supplierId);

    List<CallDistrReturnInPageVO> getCallDistrReturnInPage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize,  @Param("startTimes")String startTimes, @Param("endTimes")String endTimes,
                                                           @Param("orderName")String orderName, @Param("orderType")String orderType, @Param("chainType")int chainType, @Param("status")List<Integer> status, @Param("supplierId")Long supplierId);

    List<DistrReturnInPageListVO> getCallDistrReturnInDeatil(@Param("enterpriseId")Long enterpriseId, @Param("ids")List<Long> ids, @Param("status")List<Integer> status);

    Integer getDistrReturnInShelfCount(@Param("id")Long id, @Param("status")int status);

    Integer getDistrReturnInDetailsCount(@Param("baseOrderId")Long baseOrderId, @Param("status")int status);

    SaveReveivableVoucherVO getReceivableVoucher(Long id);
}