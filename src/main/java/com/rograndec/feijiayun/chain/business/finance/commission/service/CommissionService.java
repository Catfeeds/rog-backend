package com.rograndec.feijiayun.chain.business.finance.commission.service;

import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommission;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.CommissionQueryParamVO;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.CommissionResponseTotalVO;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.NoSaleCommissionResponseTotalVO;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.SaleCommissionModifyRecordResponseVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.ResponseSaleMan;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import java.beans.IntrospectionException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommissionService {
    NoSaleCommissionResponseTotalVO getCommissions(Long branchId, String clerkId,
                                                         Date startTime, Date endTime, UserVO userVO) throws Exception;

    SaleCommission save(UserVO userVO, NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO) throws Exception;

    SaleCommission update(UserVO userVO, NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO) throws Exception;

    List<NewSelectBean> queryBranch(UserVO userVO, String param);

    List<ResponseSaleMan> getSaleMans(Map<String, Object> map);

    CommissionResponseTotalVO queryCommissions(UserVO userVO, CommissionQueryParamVO commissionQueryParamVO, Page page) throws InvocationTargetException, IntrospectionException, IllegalAccessException, NoSuchFieldException, ParseException;

    NoSaleCommissionResponseTotalVO queryCommission(Long commissionId);

    DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) throws BusinessException;

    void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) throws BusinessException;

    List<DraftCacheVO> getDraftCacheVO(UserVO userVO);

    List<SaleCommissionModifyRecordResponseVO> queryModifyRecords(UserVO userVO, Long id, Page page);

    void export(OutputStream output, UserVO userVO, NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO);

    void exportUpdateRecord(OutputStream output, List<SaleCommissionModifyRecordResponseVO> saleCommissionModifyRecordResponseVOS, UserVO loginUser);

    void wariteOff(UserVO userVO, Long id) throws Exception;
}
