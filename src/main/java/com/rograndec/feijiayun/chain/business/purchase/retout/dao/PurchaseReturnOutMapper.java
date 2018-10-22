package com.rograndec.feijiayun.chain.business.purchase.retout.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.PurchaseAndDistrOutVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.RequestPurDistrOutParamVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.RequestPurchaseReturnParamVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.ResponsePurchaseReturnOutDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.ResponsePurchaseReturnOutVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseReturnOutMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReturnOut record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReturnOut record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReturnOut selectByPrimaryKey(Long id);
    PurchaseReturnOut selectBaseOrderId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReturnOut record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReturnOut record);

    /**
     * 分页查询购进退出的主信息
     * @param paramVO
     * @return
     */
    List<ResponsePurchaseReturnOutVO> getPurchaseReturnOutByPage(RequestPurchaseReturnParamVO paramVO);

    /**
     * 分页查询购进退出的总数
     * @param paramVO
     * @return
     */
    Integer getCountPurchaseReturnOutByPage(RequestPurchaseReturnParamVO paramVO);

    /**
     * 根据id 查询购进出库明细
     * @param id
     * @return
     */
    ResponsePurchaseReturnOutDetailVO getPurchaseReturnOutDetail(Long id);

    /**
     * 应付贷项凭证调用购进退出出库单
     * @param purDistrOutParamVO
     * @return
     */
    List<PurchaseAndDistrOutVO> getPurReturnOutInfo(RequestPurDistrOutParamVO purDistrOutParamVO);

    /**
     * 应付贷项凭证调用购进退出出库单
     * @param purDistrOutParamVO
     * @return
     */
    Integer getPurReturnOutInfoCount(RequestPurDistrOutParamVO purDistrOutParamVO);


    BigDecimal getQuantityTotal(@Param("enterpriseId") Long enterpriseId, @Param("inStorageId") Long inStorageId, @Param("shelfId") Long shelfId);
}