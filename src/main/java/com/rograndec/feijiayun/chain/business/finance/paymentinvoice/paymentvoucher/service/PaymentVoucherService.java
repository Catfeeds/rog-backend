package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.service;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucherModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2018/1/8 14:06
 */
public interface PaymentVoucherService {

    /**
     * 应付贷项凭证列表
     * @param voucherTotalVOPage
     * @param requestVoucherParamVO
     * @param userVO
     */
    void getPaymentVoucherListPage(Page<PaymentVoucherTotalVO> voucherTotalVOPage, RequestVoucherParamVO requestVoucherParamVO, UserVO userVO);

    /**
     *  应付贷项凭证详情
     * @param id
     * @return
     */
    PaymentVoucherVO getPaymentVoucherDetailById(Long id,UserVO userVO);

    /**
     * 修改应付贷项凭证
     * @param paymentVoucherVO
     */
    String updatePaymentVoucher(PaymentVoucherVO paymentVoucherVO,UserVO userVO) throws Exception;

    /**
     * 应付贷项凭证冲销
     * @param id
     * @param userVO
     */
    void reversal(Long id,UserVO userVO) throws Exception;

    /**
     * 应付贷项凭证--调用购进退出出库
     * @param userVO
     */
    void getPurchaseAndDistrOutInfo(UserVO userVO, Page<List<PurchaseAndDistrOutVO>> page, RequestPurDistrOutParamVO purDistrOutParamVO);

    /**
     * 调用购进退出出库明细
     * @param userVO
     */
    List<PurchaseAndDistrOutShelfVO> getPurReturnOutDetailInfo(UserVO userVO,String ids) throws Exception;

    /**
     *  保存应付贷项凭证
     * @param paymentVoucherVO
     * @param userVO
     * @throws Exception
     */
    String savePaymentVoucher(PaymentVoucherVO paymentVoucherVO,UserVO userVO) throws Exception;

    /**
     * 保存或者修改应付贷项凭证
     * @param paymentVoucherVO
     * @param userVO
     * @throws Exception
     */
    String saveAndUpdatePaymentVoucher(PaymentVoucherVO paymentVoucherVO,UserVO userVO) throws Exception;

    /**
     * 保存应付贷项凭证草稿
     * @param userVO
     * @param draftCacheVO
     * @return
     */
    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<PaymentVoucherVO> draftCacheVO);

    /**
     * 删除缓存
     * @param enterpriseId
     * @param type
     * @param redisKeyValue
     */
    void removeDraftCache(Long supplierId, Long enterpriseId, String type, String redisKeyValue);

    /**
     * 获取缓存
     * @param userVO
     * @return
     */
    List<DraftCacheVO> getDraftCacheVO(UserVO userVO,Long supplierId);

    void getPaymentVoucherModifyRecord(UserVO userVO, Page<List<PaymentVoucherModifyRecord>> page, Long paymentId);

    void export(OutputStream output, UserVO userVO, Long paymentId) throws Exception;

    void getGoodsInfoByParam(UserVO userVO,Page<List<PurchaseAndDistrOutShelfVO>> page,String param,Long supplierId);

    void exportUpdateRecord(OutputStream output, Long paymentId, UserVO userVO) throws Exception;
}
