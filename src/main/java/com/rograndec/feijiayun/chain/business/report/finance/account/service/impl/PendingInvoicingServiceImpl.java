package com.rograndec.feijiayun.chain.business.report.finance.account.service.impl;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.report.finance.account.service.PendingInvoicingService;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingPrintVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.status.*;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.*;

/**
 * @author zhangyu
 * @create 2018-01-11
 */
@Service
public class PendingInvoicingServiceImpl implements PendingInvoicingService {

    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;
    @Autowired
    private DistrInMapper distrInMapper;
    @Autowired
    private DistrOutMapper distrOutMapper;
    @Autowired
    private PurchaseGeneralComponent component;
    @Autowired
    private CommonComponent commonComponent;

    @Override
    public Page<List<PendingInvoicingVO>> getPageList(UserVO userVO, Page<List<PendingInvoicingVO>> page, Integer accountType, Date startDate, Date endDate,
                                                      String code, String invoiceName, Integer orderType) throws BusinessException {
        //验证会计期间
        checkAccountingPeriodRange(userVO, startDate, endDate);
        Map<String, Object> paramMap = getParamMap(userVO.getEnterpriseId(), startDate, endDate, code, invoiceName, orderType);
        paramMap.put("start", page.getStart());
        paramMap.put("pageSize", page.getPageSize());
        Integer chainType = userVO.getChainType();
        List<PendingInvoicingVO> pendingInvoicingVOS = getPendingInvoicingVOList(accountType, paramMap, chainType);
        Integer count = 0;
        if (accountType == 0) {
            if (chainType == ChainType.Headquarters.getCode()) {
                count = purchaseInStorageMapper.getPaymentPendingInvoicingCount(paramMap);
            } else if (chainType == ChainType.Division.getCode()) {
                count = distrInMapper.getPaymentPendingInvoicingCount(paramMap);
            }
        } else if (accountType == 1 && chainType == ChainType.Headquarters.getCode()) {
            count = distrOutMapper.getReceivablePendingInvoicingCount(paramMap);
        }
        page.setTotalRecord(count);
        page.setResult(pendingInvoicingVOS);
        return page;
    }

    @Override
    public PendingInvoicingPrintVO getPrintList(UserVO userVO, Integer accountType, Date startDate, Date endDate, String code, String invoiceName, Integer orderType) throws BusinessException {
        //验证会计期间
        checkAccountingPeriodRange(userVO, startDate, endDate);
        Map<String, Object> paramMap = getParamMap(userVO.getEnterpriseId(), startDate, endDate, code, invoiceName, orderType);
        PendingInvoicingPrintVO pendingInvoicingPrintVO = new PendingInvoicingPrintVO();
        pendingInvoicingPrintVO.setEnterpriseName(userVO.getEnterpriseName());
        pendingInvoicingPrintVO.setPendingInvoicingVOS(getPendingInvoicingVOList(accountType, paramMap, userVO.getChainType()));
        return pendingInvoicingPrintVO;
    }

    @Override
    public void excelExport(OutputStream output, UserVO userVO, Integer accountType, Date startDate, Date endDate, String code, String invoiceName, Integer orderType) {
        List<PendingInvoicingVO> pendingInvoicingVO = new ArrayList<>();

        if (endDate != null && startDate != null) {
            //验证会计期间
            boolean flag = commonComponent.checkAccountingPeriodRange(userVO, DateUtils.DateToString(startDate, DateUtils.FMT_DATE), DateUtils.DateToString(endDate, DateUtils.FMT_DATE));
            if (flag) {
                Map<String, Object> paramMap = getParamMap(userVO.getEnterpriseId(), startDate, endDate, code, invoiceName, orderType);
                pendingInvoicingVO = getPendingInvoicingVOList(accountType, paramMap, userVO.getChainType());
            }
        }

        String typeName = "应付待开票单据";
        String uniyType = "供货单位";
        if (accountType == 1) {
            typeName = "应收待开票单据";
            uniyType = "购货单位";
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("invoiceCode", uniyType + "编码");
        map.put("invoiceName", uniyType + "名称");
        map.put("orderDate", "单据日期");
        map.put("orderCode", "单据编号");
        map.put("orderType", "单据编号");
        map.put("orderAmount", "单据金额");
        map.put("clearAmount", "已清金额");
        map.put("unClearAmount", "未清金额");

        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add(typeName);
        component.commExcelExport(output, map, pendingInvoicingVO, names, null, null, false, new ArrayList<>());
    }

    private Map<String, Object> getParamMap(Long enterpriseId, Date startDate, Date endDate, String code, String invoiceName, Integer orderType) {
        Map<String, Object> map = new HashMap();
        map.put("enterpriseId", enterpriseId);
        map.put("startDate", DateUtils.DateToString(startDate, DateUtils.FMT_DATE) + " 00:00:00");
        map.put("endDate", DateUtils.DateToString(endDate, DateUtils.FMT_DATE) + " 23:59:59");
        map.put("code", StringUtils.isBlank(code) ? null : code.trim());
        map.put("invoiceName", StringUtils.isBlank(invoiceName) ? null : invoiceName.trim());
        map.put("orderType", orderType == null || orderType.compareTo(1) > 0 ? null : orderType);
        return map;
    }

    private List<PendingInvoicingVO> getPendingInvoicingVOList(Integer accountType, Map<String, Object> paramMap, Integer chainType) {
        List<PendingInvoicingVO> pendingInvoicingVOS = new ArrayList<>();
        List<Integer> status0 = new ArrayList<>();
        List<Integer> status1 = new ArrayList<>();
        if (accountType == 0) {
            if (chainType == ChainType.Headquarters.getCode()) {
                status0.add(PurchaseStatus.WAIT_BILL.getStatus());
                status0.add(PurchaseStatus.PART_BILL.getStatus());
                paramMap.put("status0", status0);
                pendingInvoicingVOS = purchaseInStorageMapper.getPaymentPendingInvoicingList(paramMap);
            } else if (chainType == ChainType.Division.getCode()) {
                status0.add(DistrInStatus.WAIT_BILL);
                status0.add(DistrInStatus.PART_BILL);
                paramMap.put("status0", status0);
                status1.add(DistrInReturnStatus.WAIT_BILL);
                status1.add(DistrInReturnStatus.PART_BILL);
                paramMap.put("status1", status1);
                List<Integer> distrType = new ArrayList<>();
                distrType.add(DistrType.DISTRIBUTION_HEAD.getCode());
                distrType.add(DistrType.DIRECT_DISTRIBUTION.getCode());
                paramMap.put("distrType", distrType);
                pendingInvoicingVOS = distrInMapper.getPaymentPendingInvoicingList(paramMap);
            }
        } else if (accountType == 1 && chainType == ChainType.Headquarters.getCode()) {
            status0.add(DistrOutStatus.WAIT_BILL);
            status0.add(DistrOutStatus.PART_BILL);
            paramMap.put("status0", status0);
            status1.add(DistrReturnStatus.WAIT_BILL);
            status1.add(DistrReturnStatus.PART_BILL);
            paramMap.put("status1", status1);
            paramMap.put("distrType", DistrType.DISTRIBUTION_HEAD.getCode());
            paramMap.put("chainType", ChainType.Division.getCode());
            pendingInvoicingVOS = distrOutMapper.getReceivablePendingInvoicingList(paramMap);
        }
        return pendingInvoicingVOS;
    }

    /**
     * 验证日期范围是否在会计期间内
     *
     * @param userVO
     * @param startDate
     * @param endDate
     * @return
     */
    private void checkAccountingPeriodRange(UserVO userVO, Date startDate, Date endDate) {
        boolean flag = commonComponent.checkAccountingPeriodRange(userVO, DateUtils.DateToString(startDate, DateUtils.FMT_DATE), DateUtils.DateToString(endDate, DateUtils.FMT_DATE));
        if (!flag) {
            throw new BusinessException("日期范围不在会计期间范围内，请检查!");
        }
    }
}
