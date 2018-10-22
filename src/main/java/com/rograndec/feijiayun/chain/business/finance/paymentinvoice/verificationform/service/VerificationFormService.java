package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.service;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.PurchaseInStorageReportPageVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.SaleOutORreturnVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormCountVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface VerificationFormService {

    void save(UserVO userVO, VerificationFormVO verificationFormVO) throws BusinessException,Exception;


    VerificationFormVO getVerificationFormVOById(Long id);


    List<Supplier> getActualSalesSettlementSuppliers(UserVO userVO);

    List<SaleOutORreturnVO> getSaleOutORreturn(UserVO userVO, String supplierId,Date startDate, Date endDate);

    List<PurchaseInStorageReportPageVO> getPurchaseInStorageReportPage(UserVO userVO, String supplierId, String lotNumber, Long goodsId);

    String writeOffVerificationFormById(UserVO userVO,Long id);

    void modifyVerificationForm(UserVO userVO, VerificationFormVO verificationFormVO,String reason);

    void getVerificationFormVOPage(Page<VerificationFormCountVO> page, UserVO userVO, String supplierCode, String supplierName, String operatorName, String code, String sort, Long status, String sortField, Date startDate, Date endDate);

    void getModifyRecord(Long id,Page<List<VerificationFormModifyRecord>> page);

    /**
     * 保存草稿
     * @param draftCacheVO
     */
    public DraftCacheVO saveDraft(UserVO userVO, DraftCacheVO draftCacheVO) throws Exception;

    /**
     * 删除草稿
     * @param enterpriseId
     * @param type
     * @param redisKeyValue
     * @throws Exception
     */
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) throws Exception ;

    /**
     * 查询草稿
     * @param userVO
     * @return
     * @throws Exception
     */
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO) throws Exception ;

    public void exportExcel(OutputStream output,VerificationFormVO verificationFormVO, UserVO userVO) throws Exception;

    List<VerificationFormModifyRecord> getVerificationFormModifyRecord(Long id);

    void exportExcelModifyRecord(OutputStream output, List<VerificationFormModifyRecord> verificationFormModifyRecordList, UserVO loginUser);

    /**
     * 自动核销销售清单
     * @param userVO
     * @param supplierId
     * @param startDate
     * @param endDate
     * @return
     */
    List<SaleOutORreturnVO> antoSaleOutORreturn(UserVO userVO, String supplierId,Date startDate, Date endDate);

}
