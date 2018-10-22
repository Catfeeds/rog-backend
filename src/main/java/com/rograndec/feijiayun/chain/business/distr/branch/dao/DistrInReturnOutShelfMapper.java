package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutShelf;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.PurchaseAndDistrOutShelfVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.RequestGoodsParamVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrInReturnOutShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    int deleteByOutId(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInReturnOutShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInReturnOutShelf record);

    /**
     *
     * @mbg.generated
     */
    DistrInReturnOutShelf selectByPrimaryKey(Long id);


    List<DistrInReturnOutShelf> selectByOutIds(List<Long> list);
    List<DistrInReturnOutShelf> selectByOutId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInReturnOutShelf record);

    int updateByOutId(DistrInReturnOutShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInReturnOutShelf record);

    List<DistrInReturnOutShelf> selectByDtlIdAndEnterpriseId(@Param("dtlId") Long distrInReturnOutDtlId,@Param("enterpriseId") Long enterpriseId,@Param("parentId") Long parentId);

    /**
     * 应付贷项凭证调用配进退出出库明细
     * @return
     */
    List<PurchaseAndDistrOutShelfVO> getDistrReturnOutDetailByOutIdS(RequestGoodsParamVO paramVO);

    /**
     * 应付贷项凭证调用配进退出出库明细数量
     * @return
     */
    Integer getDistrReturnOutDetailByOutIdSCount(RequestGoodsParamVO paramVO);

    List<DistrInReturnOutShelf> getShelfOutInfoByOutId(@Param("outId") Long outId,@Param("enterpriseId")Long enterpriseId);

}