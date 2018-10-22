package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutPageTotalVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnPageTotalVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInReturnVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.PurchaseAndDistrOutVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.RequestPurDistrOutParamVO;

import java.util.List;
import java.util.Map;

public interface DistrInReturnOutMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInReturnOut record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInReturnOut record);

    /**
     *
     * @mbg.generated
     */
    DistrInReturnOut selectByPrimaryKey(Long id);

    List<DistrInReturnOut> selectByParam(Map<String,Object> map);
    List<DistrInReturnOut> selectByMap(Map<String,Object> map);

    List<ResponseDistrInReturnVO> getDistrReturnInOrderList(Map<String,Object> map);
    List<ResponseDistrInReturnVO> getDistrReturnInOrders(Map<String,Object> map);

    DistrInReturnOutPageTotalVO selectByParamCount(Map<String,Object> map);

    DistrInReturnPageTotalVO getDistrReturnInOrderListCount(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInReturnOut record);
    int updateByAudit(DistrInReturnOut record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInReturnOut record);



    /**
     * 应付贷项凭证调用配进退出出库单
     * @param purDistrOutParamVO
     * @return
     */
    List<PurchaseAndDistrOutVO> getDistrInReturnOutInfo(RequestPurDistrOutParamVO purDistrOutParamVO);

    /**
     * 应付贷项凭证调用配进退出出库单
     * @param purDistrOutParamVO
     * @return
     */
    Integer getDistrInReturnOutInfoCount(RequestPurDistrOutParamVO purDistrOutParamVO);
}