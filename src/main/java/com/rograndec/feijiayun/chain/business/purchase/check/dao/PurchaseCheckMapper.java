package com.rograndec.feijiayun.chain.business.purchase.check.dao;

import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheck;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveDetail;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseCheckMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(PurchaseCheck record);

    /**
     * @mbg.generated
     */
    int insertSelective(PurchaseCheck record);

    /**
     * @mbg.generated
     */
    PurchaseCheck selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseCheck record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseCheck record);




    Long queryCountByPurchaseCheckParams(@Param("enterpriseId") Long enterpriseId, @Param("start") int start, @Param("pageSize") int pageSize,
                                         @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("supplierCode") String supplierCode, @Param("supplierName") String supplierName,
                                         @Param("code") String code, @Param("checkerName") String checkerName);


    List<PurchaseCheckVO> selectPurchaseCheckVoByPurchaseCheckParams(@Param("enterpriseId") Long enterpriseId, @Param("start") int start, @Param("pageSize") int pageSize,
                                                                     @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("supplierCode") String supplierCode, @Param("supplierName") String supplierName,
                                                                     @Param("code") String code, @Param("checkerName") String checkerName,@Param("orderName")String orderName, @Param("orderType")String orderType);


    Long queryCountByOtherParams(@Param("enterpriseId") Long enterpriseId, @Param("start") int start, @Param("pageSize") int pageSize,
                                         @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("supplierCode") String supplierCode, @Param("supplierName") String supplierName,
                                         @Param("code") String code, @Param("checkerName") String checkerName,@Param("WAIT_STORAGE")int WAIT_STORAGE);


    List<PurchaseCheckVO> selectPurchaseCheckVoByOtherParams(@Param("enterpriseId") Long enterpriseId, @Param("start") int start, @Param("pageSize") int pageSize,
                                                                     @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("supplierCode") String supplierCode, @Param("supplierName") String supplierName,
                                                                     @Param("code") String code, @Param("checkerName") String checkerName,@Param("orderName")String orderName, @Param("orderType")String orderType,
                                                                     @Param("WAIT_STORAGE")int WAIT_STORAGE);


    Long queryCountByWaitParams(@Param("enterpriseId") Long enterpriseId, @Param("start") int start, @Param("pageSize") int pageSize,
                                @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("supplierCode") String supplierCode, @Param("supplierName") String supplierName,
                                @Param("code") String code, @Param("receiverName") String receiverName, @Param("WAIT_STORAGE")int WAIT_STORAGE);


    List<PurchaseReceiveReVO> selectPurchaseCheckVoByWaitParams(@Param("enterpriseId") Long enterpriseId, @Param("start") int start, @Param("pageSize") int pageSize,
                                                              @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("supplierCode") String supplierCode, @Param("supplierName") String supplierName,
                                                              @Param("code") String code, @Param("receiverName") String receiverName, @Param("orderName")String orderName, @Param("orderType")String orderType,
                                                              @Param("WAIT_STORAGE")int WAIT_STORAGE);


    List<PurchaseCheckHeadVO> selectHead(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    PurchaseCheck selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("id") Long id);

    List<WaitPurchaseCheckHeadVO> selectWaitHead(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<WaitPurchaseCheckDeatilVO> selectWaitDeatil(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<PurchaseCheckDeatilVO> selectDeatil(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<PurchaseCheckDeatilTwoVO> selectDeatilTwo(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    String selectById(@Param("enterpriseId")Long enterpriseId,@Param("id")String[] id,@Param("setType")Long setType);

    PurchaseCheckOtherVO selectDistributionByCheckId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id, @Param("type")Long type);

    CheckHeadVO selectCheckHead(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<CheckDeatilVO> selectCheckDeatil(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<CheckProjectVO> selectCheckProject(@Param("enterpriseId")Long enterpriseId, @Param("code")String code);
    List<CheckProjectVO> selectCheckProjectByCodes(@Param("enterpriseId")Long enterpriseId,@Param("list") List<Long> list);

    List<ConclusionVO> selectConclusion(@Param("enterpriseId")Long enterpriseId, @Param("setType")Long setType,@Param("status")Integer status,@Param("type") Integer type);

    PurchaseReceive selectByReceive(@Param("enterpriseId")Long enterpriseId, @Param("BaseOrderId")Long BaseOrderId);

    PurchaseReceiveDetail selectByReceiveDetail(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderDtlId")Long baseOrderDtlId);

    Integer updateByReceive(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer updateByReceiveDetail(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer updateByOrder(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer updateByOrderDetail(@Param("enterpriseId")Long enterpriseId, @Param("baseOrderId")Long baseOrderId, @Param("status")Integer status);

    Integer updateTotal(@Param("enterpriseId")Long enterpriseId, @Param("mainId")Long mainId, @Param("total")BigDecimal total);

    BigDecimal selectTotal(@Param("enterpriseId")Long enterpriseId, @Param("mainId")Long mainId);

    Integer updateVarieties(@Param("enterpriseId")Long enterpriseId, @Param("mainId")Long mainId, @Param("size")int size);

    List<PurchaseCheck> selectByBaseOrderId(@Param("enterpriseId") Long enterpriseId, @Param("baseOrderId") Long baseOrderId);
}