package com.rograndec.feijiayun.chain.business.purchase.retout.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.PurchaseAndDistrOutShelfVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.RequestGoodsParamVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutShelf;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.PurchaseReturnOutExcelVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.PurchaseReturnOutShelfVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseReturnOutShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReturnOutShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReturnOutShelf record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReturnOutShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReturnOutShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReturnOutShelf record);

    int updateStatusByReturnOut(PurchaseReturnOutShelf record);

    List<PurchaseReturnOutExcelVO> getReturnDetailShelf(Long id);

    List<PurchaseReturnOutShelfVO> getShelfInfoByDtlId(Long dtlId);

    List<PurchaseReturnOutShelf> getReturnOutShelfListByEnterpriseIdAndOutId(@Param("enterpriseId") Long enterpriseId,@Param("outId") Long returnId);

    List<PurchaseAndDistrOutShelfVO> getPurchaseReturnOutDetailByOutIdS(RequestGoodsParamVO paramVO);

    Integer getPurchaseReturnOutDetailByOutIdSCount(RequestGoodsParamVO paramVO);

    List<PurchaseReturnOutShelf> getShelfOutInfoByDtlId(@Param("dtlId")Long dtlId,@Param("enterpriseId")Long enterpriseId);

    List<PurchaseReturnOutShelf> getShelfOutInfoByOutId(@Param("outId")Long outId,@Param("enterpriseId")Long enterpriseId);

}