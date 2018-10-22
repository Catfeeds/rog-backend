package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao.PaymentVoucherMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucher;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao.AdvanceReceivableInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao.ReceivableInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.CollectMoneyPageTotal;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.common.CompanyType;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.common.ReceivableStatus;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.dao.FinanceReceivableDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.dao.FinanceReceivableMapper;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.dao.FinanceReceivableModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivable;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivableDetail;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivableModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.service.CollectMoneyService;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.*;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosBankMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosBank;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.*;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.ReceivableInvoiceStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CollectMoneyServiceImpl implements CollectMoneyService {

    @Autowired
    private FinanceReceivableMapper financeReceivableMapper;

    @Autowired
    private FinanceReceivableDetailMapper financeReceivableDetailMapper;

    @Autowired
    private FinanceReceivableModifyRecordMapper financeReceivableModifyRecordMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private PosBankMapper posBankMapper;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private PaymentVoucherMapper paymentVoucherMapper;

    @Autowired
    private AdvanceReceivableInvoiceMapper advanceReceivableInvoiceMapper;

    @Autowired
    private ReceivableInvoiceMapper receivableInvoiceMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private FinanceComponent financeComponent;

    @Override
    public Page getReceivePage(Page page, RequestReceivePageVO requestReceivePageVO, UserVO loginUser) {
        /**
         * 日期默认从当前日期到当前日期 ??(默认没有展示的数据)
         *总部只搜索由总部产生的应付发票
         *加盟店只搜索由加盟店产生的应付发票
         */
        Map<String,Object> map = new HashMap<>(14);
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        map.put("startDate",requestReceivePageVO.getStartDate());
        map.put("endDate",requestReceivePageVO.getEndDate());
        map.put("companyType",requestReceivePageVO.getCompanyType());
        map.put("companyCode",requestReceivePageVO.getCompanyCode());
        map.put("companyName",requestReceivePageVO.getCompanyName());
        map.put("code",requestReceivePageVO.getCode());
        map.put("receivableManName",requestReceivePageVO.getReceivableManName());
        map.put("status",requestReceivePageVO.getStatus());
        /**
         * 前台order接收的是receivableDate或者code
         * 将receivableDate转换成receivable_date
         */
        if ("receivableDate".equals(requestReceivePageVO.getOrder())){
            requestReceivePageVO.setOrder("receivable_date");
        }
        map.put("order",requestReceivePageVO.getOrder());
        map.put("sort",requestReceivePageVO.getSort());
        //都是自己门店看自己的内容
        map.put("enterpriseId",loginUser.getEnterpriseId());
        /**
         * 返回的结果集
         */
        ReceiveTotalPageVO returnVO = new ReceiveTotalPageVO();
        List<ReceiveMoneyPageVO> list = financeReceivableMapper.getReceivePage(map);
        CollectMoneyPageTotal pageTotal = financeReceivableMapper.selectTotal(map);
        if (pageTotal == null){
            returnVO.setReceivableAmountSummary(BigDecimal.ZERO);
            returnVO.setRealAmountSummary(BigDecimal.ZERO);
            returnVO.setDiscountAmountSummary(BigDecimal.ZERO);
        }else {
            returnVO.setReceivableAmountSummary(pageTotal.getReceivableAmountSummary() == null ? BigDecimal.ZERO : pageTotal.getReceivableAmountSummary());
            returnVO.setRealAmountSummary(pageTotal.getRealAmountSummary() == null ? BigDecimal.ZERO : pageTotal.getRealAmountSummary());
            returnVO.setDiscountAmountSummary(pageTotal.getDiscountAmountSummary() == null ? BigDecimal.ZERO : pageTotal.getDiscountAmountSummary());
        }
        returnVO.setList(list);
        page.setResult(returnVO);
        Integer totalRecord = financeReceivableMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public ReceiveFormVO getReceiveDetail(Long id) {
        FinanceReceivable financeReceivable = financeReceivableMapper.selectByPrimaryKey(id);
        List<FinanceReceivableDetail> detailList = financeReceivableDetailMapper.selectByReceivableId(id);
        ReceiveFormVO formVO = new ReceiveFormVO();
        formVO = formVO.convertToVO(financeReceivable,detailList);
        return formVO;
    }

    @Override
    public Page getReceiveUpdateRecord(Page page,RequestModifyPageVO requestModifyPageVO, UserVO loginUser) {
        Long id = requestModifyPageVO.getId();
        Map<String,Object> map = new HashMap<>(3);
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        map.put("id",id);
        List<FinanceReceivableModifyRecord> modifyRecords = financeReceivableModifyRecordMapper.selectByKeyId(map);
        List<ReceiveUpdateRecordVO> resultList = new ArrayList<>();
        modifyRecords.forEach(
                record ->{
                    ReceiveUpdateRecordVO recordVO = new ReceiveUpdateRecordVO();
                    recordVO = recordVO.convertToVO(record);
                    resultList.add(recordVO);
                }
        );
        page.setResult(resultList);
        Integer totalRecord = financeReceivableModifyRecordMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public void export(OutputStream output, ReceiveFormVO receiveFormVO, UserVO loginUser) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>(11);
        map.put("baseOrderDate","日期");
        map.put("baseOrderCode","单号");
        map.put("orderTypeName","单据类型");
        map.put("amount","单据金额");
        map.put("clearAmount","已清金额");
        map.put("unclearAmount","未清金额");
        map.put("receivableAmount","本次收款金额");
        map.put("discountAmount","本次优惠金额");
        map.put("realAmount","本次实收金额");
        map.put("unclearBalance","未清余额");
        map.put("remark","备注");
        List<ReceivableExcelVO> list = new ArrayList<>();
        /**
         * 记录导出尾行的合计中数值数据
         */
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal clearAmount = BigDecimal.ZERO;
        BigDecimal unclearAmount = BigDecimal.ZERO;
        BigDecimal receivableAmount = BigDecimal.ZERO;
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal realAmount = BigDecimal.ZERO;
        BigDecimal unclearBalance = BigDecimal.ZERO;
        List<ReceiveDetailVO> detailVOList = receiveFormVO.getList();
        for (ReceiveDetailVO d : detailVOList) {
            ReceivableExcelVO excel = convertToExcel(d);
            amount = amount.add(d.getAmount());
            clearAmount = clearAmount.add(d.getClearAmount());
            unclearAmount = unclearAmount.add(d.getUnclearAmount());
            receivableAmount = receivableAmount.add(d.getReceivableAmount());
            discountAmount = discountAmount.add(d.getDiscountAmount());
            realAmount = realAmount.add(d.getRealAmount());
            unclearBalance = unclearBalance.add(d.getUnclearBalance());
            list.add(excel);
        }
        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("付款单位编码:");
        titleSecondRow.append(receiveFormVO.getCompanyCode() == null ? "" : receiveFormVO.getCompanyCode());
        titleSecondRow.append("  付款单位名称:");
        titleSecondRow.append(receiveFormVO.getCompanyName() == null ? "" : receiveFormVO.getCompanyName());
        titleSecondRow.append("  付款单位类型:");
        titleSecondRow.append(receiveFormVO.getCompanyType() == CompanyType.SUPPLIER.getCode() ?
                CompanyType.SUPPLIER.getName() :
                (receiveFormVO.getCompanyType() == CompanyType.PURCHASE.getCode() ? CompanyType.PURCHASE.getName() : ""));
        titleSecondRow.append("  收款日期:");
        titleSecondRow.append(receiveFormVO.getReceivableDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(receiveFormVO.getReceivableDate()));
        titleSecondRow.append("  收款人员:");
        titleSecondRow.append(receiveFormVO.getReceivableManName() == null ? "" : receiveFormVO.getReceivableManName());
        titleSecondRow.append("  单号:");
        titleSecondRow.append(receiveFormVO.getCode() == null ? "" : receiveFormVO.getCode());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(receiveFormVO.getRemark() == null ? "" : receiveFormVO.getRemark());
        secondTitle.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        end.append("        收款总额:");
        end.append(receiveFormVO.getTotalReceipts());
        end.append(";");
        end.append("银行转账:   ");
        end.append("        转账日期:");
        end.append(receiveFormVO.getTransferDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(receiveFormVO.getTransferDate()));
        end.append(";");
        end.append("        开户银行:");
        end.append(receiveFormVO.getBankName() == null ? "" : receiveFormVO.getBankName());
        end.append(";");
        end.append("        开户账号:");
        end.append(receiveFormVO.getBankAccount() == null ? "" : receiveFormVO.getBankAccount());
        end.append(";");
        end.append("        收入金额:");
        end.append(receiveFormVO.getBankAmountTotal());
        end.append(";");
        end.append("现金支付:   ");
        end.append("        收入金额:");
        end.append(receiveFormVO.getCashAmountTotal());
        end.append(";");
        end.append("        未清金额:");
        end.append(receiveFormVO.getUnclearBalanceTotal());
        List<String> name = new ArrayList<String>();
        //第一行的企业名
        name.add(enterpriseMapper.selectByPrimaryKey(financeReceivableMapper.selectByPrimaryKey(receiveFormVO.getId()).getEnterpriseId()).getName());
        //第二行的
        name.add("收款");
        StringBuilder sb = new StringBuilder();
        sb.append(amount)
                .append(";")
                .append(clearAmount)
                .append(";")
                .append(unclearAmount)
                .append(";")
                .append(receivableAmount)
                .append(";")
                .append(discountAmount)
                .append(";")
                .append(realAmount)
                .append(";")
                .append(unclearBalance);
        List<String> needTotalName = new ArrayList<>(7);
        needTotalName.add("amount");
        needTotalName.add("clearAmount");
        needTotalName.add("unclearAmount");
        needTotalName.add("receivableAmount");
        needTotalName.add("discountAmount");
        needTotalName.add("realAmount");
        needTotalName.add("unclearBalance");
        purchaseGeneralComponent.commExcelDistrExport(output,map,list,name,secondTitle,end.toString(),sb.toString(),needTotalName);
    }

    private ReceivableExcelVO convertToExcel(ReceiveDetailVO d) {
        ReceivableExcelVO excelVO = new ReceivableExcelVO();
        excelVO.setBaseOrderDate(d.getBaseOrderDate() == null ? "" :
                new SimpleDateFormat("yyyy-MM-dd").format(d.getBaseOrderDate()));
        excelVO.setBaseOrderCode(d.getBaseOrderCode());
        excelVO.setOrderTypeName(OrderRule.getName(d.getBaseOrderType()));
        excelVO.setAmount(d.getAmount());
        excelVO.setClearAmount(d.getClearAmount());
        excelVO.setUnclearAmount(d.getUnclearAmount());
        excelVO.setReceivableAmount(d.getReceivableAmount());
        excelVO.setDiscountAmount(d.getDiscountAmount());
        excelVO.setRealAmount(d.getRealAmount());
        excelVO.setUnclearBalance(d.getUnclearBalance());
        excelVO.setRemark(d.getRemark());
        return excelVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addOrUpdateReceiveDetail(ReceiveFormVO receiveFormVO, UserVO loginUser) throws Exception{
        //返回信息
        String msg = "";
        //表单数据验证
        List<ReceiveDetailVO> list = receiveFormVO.getList();
        if (list == null || list.size() <= 0){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前保存功能无细单数据,请先添加之后再保存!");
        }
        if (receiveFormVO.getReceivableDate() == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"收款日期为必填!");
        }
        if (receiveFormVO.getId() == null){
            //新增情况
            //************************************用于从细表取数据求和更新主表*********
            FinanceReceivable finance = FormToFinanceReceive(receiveFormVO,loginUser);
            //插入主表
            financeReceivableMapper.insertSelective(finance);
            //插入细表(需要更新主单数据)
            List<FinanceReceivableDetail> detailList =  FormToFinanceReceiveDetail(finance,list,loginUser);
            detailList.forEach(detail -> {
                financeReceivableDetailMapper.insertSelective(detail);
            });
            //更新主表数据
            FinanceReceivable financeReceivable = updateMajorReceivable(detailList, finance);
            msg = "单号 :" + finance.getCode();
            //删除草稿
            removeDraftCach(loginUser.getEnterpriseId(), OrderRule.FINANCE_RECEIVABLE.getCodePrefix(), receiveFormVO.getRedisKeyValue(),finance.getCompanyId(),finance.getCompanyType());
            //更新对应上游单据状态和金额
            updatePreviousMoneyAndStatus(finance,detailList,loginUser);
            //财务付款保存：
            financeComponent.financeReceivableToBalanceAndVoucherWhenSaveOrWriteOff(loginUser, financeReceivable,"save");
        }else{
            if (receiveFormVO.getUpdateDetail() == null || "".equals(receiveFormVO.getUpdateDetail())){
                throw new BusinessException(SysCode.FAIL.getCode(),"修改原因不能为空!");
            }
            //修改情况
            Long financeId = receiveFormVO.getId();
            FinanceReceivable oldFinanceReceivable = financeReceivableMapper.selectByPrimaryKey(financeId);
            //1.修改主表数据
            FinanceReceivable finance = FormToUpdateFinanceReceive(receiveFormVO,loginUser);
            financeReceivableMapper.updateByPrimaryKeySelective(finance);
            //2.修改细表数据
                //明细数据无须修改
            //3.记录修改记录信息
            /**
             * 记录保存记录
             */
            List<ReceiveUpdateRecordWithBLOBs> receiveUpdateRecordWithBLOB = getReceiveModifyRecord(receiveFormVO,loginUser,oldFinanceReceivable,finance);
            for(ReceiveUpdateRecordWithBLOBs ur : receiveUpdateRecordWithBLOB){
                ur.setRemark(receiveFormVO.getUpdateDetail());
                ur.setReason(receiveFormVO.getUpdateDetail());
                financeReceivableModifyRecordMapper.insertSelective(ur);
            }
            msg = "单号 :" + oldFinanceReceivable.getCode();
        }
        return msg;
    }

    private void updatePreviousMoneyAndStatus(FinanceReceivable finance, List<FinanceReceivableDetail> detailList, UserVO loginUser) {
        detailList.forEach(detail -> {
            Long baseOrderId = detail.getBaseOrderId();
            Integer baseOrderType = detail.getBaseOrderType();
            //应收金额
            BigDecimal receivableAmount = detail.getReceivableAmount();
            //付款单位选择购货单位搜索未清的预收发票和应收发票
            //付款单位选择供货单位搜索未清的应付贷项凭证
            //操作：用实收金额 更新上游单据 未清金额减少，已清金额增加
            if (OrderRule.ADVANCE_RECEIVABLE_INVOICE.getType().equals(baseOrderType)){
                AdvanceReceivableInvoice advanceReceivableInvoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(baseOrderId);
                BigDecimal clearAmountTotal = advanceReceivableInvoice.getClearAmountTotal();
                BigDecimal unclearAmountTotal = advanceReceivableInvoice.getUnclearAmountTotal();
                //0成本入库情况
                if (clearAmountTotal.compareTo(BigDecimal.ZERO) == 0
                        && unclearAmountTotal.compareTo(BigDecimal.ZERO) == 0 && receivableAmount.compareTo(BigDecimal.ZERO) == 0){
                    advanceReceivableInvoice.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                    advanceReceivableInvoiceMapper.updateByPrimaryKeySelective(advanceReceivableInvoice);
                }else{
                    if (receivableAmount.compareTo(BigDecimal.ZERO) >= 0){
                        BigDecimal clear = advanceReceivableInvoice.getClearAmountTotal().add(receivableAmount);
                        BigDecimal unclear = advanceReceivableInvoice.getUnclearAmountTotal().subtract(receivableAmount);
                        if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                            throw new BusinessException(SysCode.FAIL.getCode(),"更新预收发票单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                        }
                        advanceReceivableInvoice.setClearAmountTotal(clear);
                        advanceReceivableInvoice.setUnclearAmountTotal(unclear);
                        if (clear.compareTo(BigDecimal.ZERO) == 0){
                            advanceReceivableInvoice.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
                        }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                            advanceReceivableInvoice.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                        }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                            advanceReceivableInvoice.setStatus(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
                        }
                        advanceReceivableInvoiceMapper.updateByPrimaryKeySelective(advanceReceivableInvoice);
                    }
                }
            }else if (OrderRule.RECEIVABLE_INVOICE.getType().equals(baseOrderType)){
                ReceivableInvoice receivableInvoice = receivableInvoiceMapper.selectByPrimaryKey(baseOrderId);
                BigDecimal clearAmountTotal = receivableInvoice.getClearAmountTotal();
                BigDecimal unclearAmountTotal = receivableInvoice.getUnclearAmountTotal();
                //0成本入库情况
                if (clearAmountTotal.compareTo(BigDecimal.ZERO) == 0
                        && unclearAmountTotal.compareTo(BigDecimal.ZERO) == 0 && receivableAmount.compareTo(BigDecimal.ZERO) == 0){
                    receivableInvoice.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                    receivableInvoiceMapper.updateByPrimaryKeySelective(receivableInvoice);
                }else {
                    if (receivableAmount.compareTo(BigDecimal.ZERO) >= 0){
                        BigDecimal clear = receivableInvoice.getClearAmountTotal().add(receivableAmount);
                        BigDecimal unclear = receivableInvoice.getUnclearAmountTotal().subtract(receivableAmount);
                        if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                            throw new BusinessException(SysCode.FAIL.getCode(),"更新应收收发票单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                        }
                        receivableInvoice.setClearAmountTotal(clear);
                        receivableInvoice.setUnclearAmountTotal(unclear);
                        if (clear.compareTo(BigDecimal.ZERO) == 0){
                            receivableInvoice.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
                        }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                            receivableInvoice.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                        }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                            receivableInvoice.setStatus(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
                        }
                        receivableInvoiceMapper.updateByPrimaryKeySelective(receivableInvoice);
                    }
                }
            }else if (OrderRule.PAYMENT_VOUCHER.getType().equals(baseOrderType)){
                PaymentVoucher paymentVoucher = paymentVoucherMapper.selectByPrimaryKey(baseOrderId);
                BigDecimal clearAmountTotal = paymentVoucher.getClearAmountTotal();
                BigDecimal unclearAmountTotal = paymentVoucher.getUnclearAmountTotal();
                //0成本入库情况
                if (clearAmountTotal.compareTo(BigDecimal.ZERO) == 0
                        && unclearAmountTotal.compareTo(BigDecimal.ZERO) == 0 && receivableAmount.compareTo(BigDecimal.ZERO) == 0){
                    paymentVoucher.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                    paymentVoucherMapper.updateByPrimaryKeySelective(paymentVoucher);
                }else {
                    if (receivableAmount.compareTo(BigDecimal.ZERO) >= 0){
                        BigDecimal clear = paymentVoucher.getClearAmountTotal().add(receivableAmount);
                        BigDecimal unclear = paymentVoucher.getUnclearAmountTotal().subtract(receivableAmount);
                        if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                            throw new BusinessException(SysCode.FAIL.getCode(),"更新应付贷项凭证单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                        }
                        paymentVoucher.setClearAmountTotal(clear);
                        paymentVoucher.setUnclearAmountTotal(unclear);
                        if (clear.compareTo(BigDecimal.ZERO) == 0){
                            paymentVoucher.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
                        }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                            paymentVoucher.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                        }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                            paymentVoucher.setStatus(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
                        }
                        paymentVoucherMapper.updateByPrimaryKeySelective(paymentVoucher);
                    }
                }
            }
        });
    }


    private List<ReceiveUpdateRecordWithBLOBs> getReceiveModifyRecord(ReceiveFormVO receiveFormVO, UserVO loginUser, FinanceReceivable oldFinanceReceivable, FinanceReceivable finance) throws Exception {
        Map<String,Object> newFinanceReceiveMap = getFieldsMap(finance);
        Map<String,Object> financeReceiveMap = getFieldsMap(oldFinanceReceivable);

        Map<String,String> fieldSkipMap = fieldSkipMap();

        List<ReceiveUpdateRecordWithBLOBs> receiveUpdateRecordWithBLOBs = getModifyRecordList(loginUser,finance,"saas_finance_receivable"
                ,financeReceiveMap,newFinanceReceiveMap,fieldSkipMap);
        return receiveUpdateRecordWithBLOBs;
    }

    private List<ReceiveUpdateRecordWithBLOBs> getModifyRecordList(UserVO loginUser, FinanceReceivable finance, String tableName, Map<String, Object> valMap, Map<String, Object> newMap, Map<String, String> fieldSkipMap) {
        List<ReceiveUpdateRecordWithBLOBs> receiveUpdateRecordWithBLOBs = new ArrayList<>();
        for(Map.Entry<String,String> entry : fieldSkipMap.entrySet()) {
            Object obj = valMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if (obj != null) {
                if (obj instanceof Date && newObj instanceof Date) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date pre = (Date) obj;
                    String preStr = df.format(pre);
                    Date now = (Date) newObj;
                    String nowStr = df.format(now);
                    if (!preStr.equals(nowStr)) {
                        obj = preStr;
                        newObj = nowStr;
                        excuteSupplierModifyRecord(loginUser, finance, tableName, receiveUpdateRecordWithBLOBs, entry, obj, newObj);
                    }
                } else if (!obj.equals(newObj)) {
                    excuteSupplierModifyRecord(loginUser, finance, tableName, receiveUpdateRecordWithBLOBs, entry, obj, newObj);
                }
            }
            if (obj == null && newObj != null) {
                excuteSupplierModifyRecord(loginUser, finance, tableName, receiveUpdateRecordWithBLOBs, entry, obj, newObj);
            }
        }
        return receiveUpdateRecordWithBLOBs;
    }

    private void excuteSupplierModifyRecord(UserVO loginUser, FinanceReceivable finance, String tableName, List<ReceiveUpdateRecordWithBLOBs> receiveUpdateRecordWithBLOBs, Map.Entry<String, String> entry, Object obj, Object newObj) {
        ReceiveUpdateRecordWithBLOBs modify = new ReceiveUpdateRecordWithBLOBs();
        modify.setEnterpriseId(loginUser.getEnterpriseId());
        modify.setParentId(loginUser.getParentId());
        modify.setTableName(tableName);
        modify.setKeyId(finance.getId());
        modify.setColumnEnName(entry.getKey());
        modify.setColumnChName(entry.getValue());
        modify.setUpdateTime(new Date());
        modify.setCreaterId(loginUser.getUserId());
        modify.setCreaterCode(loginUser.getUserCode());
        modify.setCreaterName(loginUser.getUserName());
        modify.setModifierId(loginUser.getUserId());
        modify.setModifierCode(loginUser.getUserCode());
        modify.setModifierName(loginUser.getUserName());
        modify.setCreateTime(new Date());
        modify.setOldContent(obj == null ? "" : obj.toString());
        modify.setNewContent(newObj == null ? "" : newObj.toString());
        receiveUpdateRecordWithBLOBs.add(modify);
    }

    private FinanceReceivable FormToUpdateFinanceReceive(ReceiveFormVO receiveFormVO, UserVO loginUser) throws Exception{
        FinanceReceivable finance = new FinanceReceivable();
        finance.setId(receiveFormVO.getId());
        //能修改的内容包含 收款日期,收款人员,备注 tips:已冲销单据无法修改
        finance.setReceivableDate(receiveFormVO.getReceivableDate());
        finance.setReceivableManId(receiveFormVO.getReceivableManId());
        if (receiveFormVO.getReceivableManId() != null){
            User user = userMapper.selectByPrimaryKey(receiveFormVO.getReceivableManId());
            if (user != null){
                finance.setReceivableManCode(user.getCode());
                finance.setReceivableManName(user.getName());
            }
        }
        finance.setRemark(receiveFormVO.getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(finance,loginUser,false);
        return finance;
    }

    public Map<String,Object> getFieldsMap(Object obj) throws Exception {
        Class newUserClazz = obj.getClass();
        Field[] feilds = newUserClazz.getDeclaredFields();
        Map<String,Object> feildMap = new HashMap<>();
        for(Field field : feilds){
            field.setAccessible(true);
            Object valObj = ReflectUtils.getValueOfGet(obj,field.getName());
            feildMap.put(field.getName(),valObj);
        }
        return feildMap;
    }

    private Map<String,String> fieldSkipMap(){
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("receivableDate","收款日期");
        fieldNames.put("receivableManName","收款人员");
        fieldNames.put("remark","备注");
        return fieldNames;
    }

    private FinanceReceivable updateMajorReceivable(List<FinanceReceivableDetail> detailList, FinanceReceivable finance) {
        //单据金额合计 已清金额合计 未清金额合计 应收金额合计 优惠金额合计 实收金额合计 未清余额
        //amount_total clear_amount_total unclear_amount_total receivable_amount_total
        //discount_amount_total real_amount_total unclear_balance_total
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal clearAmountTotal = BigDecimal.ZERO;
        BigDecimal unclearAmountTotal = BigDecimal.ZERO;
        BigDecimal receivableAmountTotal = BigDecimal.ZERO;
        BigDecimal discountAmountTotal = BigDecimal.ZERO;
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        BigDecimal unclearBalanceTotal = BigDecimal.ZERO;
        for (FinanceReceivableDetail d : detailList) {
            amountTotal = amountTotal.add(d.getAmount());
            clearAmountTotal = clearAmountTotal.add(d.getClearAmount());
            unclearAmountTotal = unclearAmountTotal.add(d.getUnclearAmount());
            receivableAmountTotal = receivableAmountTotal.add(d.getReceivableAmount());
            discountAmountTotal = discountAmountTotal.add(d.getDiscountAmount());
            realAmountTotal = realAmountTotal.add(d.getRealAmount());
            unclearBalanceTotal = unclearBalanceTotal.add(d.getUnclearBalance());
        }
        FinanceReceivable f = new FinanceReceivable();
        f.setId(finance.getId());
        f.setAmountTotal(amountTotal);
        f.setClearAmountTotal(clearAmountTotal);
        f.setUnclearAmountTotal(unclearAmountTotal);
        f.setReceivableAmountTotal(receivableAmountTotal);
        f.setDiscountAmountTotal(discountAmountTotal);
        f.setRealAmountTotal(realAmountTotal);
        f.setUnclearBalanceTotal(unclearBalanceTotal);
        financeReceivableMapper.updateByPrimaryKeySelective(f);
        return financeReceivableMapper.selectByPrimaryKey(f.getId());
    }

    private List<FinanceReceivableDetail> FormToFinanceReceiveDetail(FinanceReceivable finance, List<ReceiveDetailVO> list, UserVO loginUser){
        List<FinanceReceivableDetail> result = new ArrayList<>();
        list.forEach(d ->{
            FinanceReceivableDetail detail = new FinanceReceivableDetail();
            detail.setEnterpriseId(loginUser.getEnterpriseId());
            detail.setParentId(loginUser.getParentId());
            detail.setReceivableId(finance.getId());
            detail.setBaseOrderId(d.getBaseOrderId());
            detail.setBaseOrderCode(d.getBaseOrderCode());
            detail.setBaseOrderType(d.getBaseOrderType());
            detail.setBaseOrderDate(d.getBaseOrderDate());
            detail.setAmount(d.getAmount());
            detail.setClearAmount(d.getClearAmount());
            detail.setUnclearAmount(d.getUnclearAmount());
            detail.setReceivableAmount(d.getReceivableAmount());
            detail.setDiscountAmount(d.getDiscountAmount());
            detail.setRealAmount(d.getRealAmount());
            detail.setUnclearBalance(d.getUnclearBalance());
            detail.setLineNum(d.getLineNum());
            detail.setStatus(ReceivableStatus.HAS_FINISHED.getCode());
            detail.setRemark(d.getRemark());
            try {
                UserEnterpriseUtils.setUserCreateOrModify(detail,loginUser,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.add(detail);
        });
        return result;
    }

    /**
     * 转换为实体类
     */
    private FinanceReceivable FormToFinanceReceive(ReceiveFormVO receiveFormVO, UserVO loginUser) throws Exception{
        FinanceReceivable finance = new FinanceReceivable();
        finance.setEnterpriseId(loginUser.getEnterpriseId());
        finance.setParentId(loginUser.getParentId());
        String code = orderCodeComponent.
                generate(OrderRule.FINANCE_RECEIVABLE.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode());
        finance.setCode(code);
        finance.setOrderType(OrderRule.FINANCE_RECEIVABLE.getType());
        ////总部:供货单位->总部的供应商 购货单位->加盟店   加盟店:供货单位->总部+加盟店的供货单位
        if (loginUser.getChainType() == ChainType.Headquarters.getCode()){
            if (PayCompanyType.SUPPLIER.getCode() == receiveFormVO.getCompanyType()){
                finance.setFinanceAccountType(FinanceAccountType.SUPPLIER.getType());
            }else if (PayCompanyType.PURCHASEUNIT.getCode() == receiveFormVO.getCompanyType()){
                finance.setFinanceAccountType(FinanceAccountType.LEAGUE.getType());
            }
        }else if (loginUser.getChainType() == ChainType.Division.getCode()){
            //供货单位->总部+加盟店的供货单位
            finance.setFinanceAccountType(FinanceAccountType.SUPPLIER.getType());
        }
        finance.setReceivableDate(receiveFormVO.getReceivableDate());
        finance.setReceivableManId(receiveFormVO.getReceivableManId());
        //收货人员
        User receiveMan = userMapper.selectByPrimaryKey(receiveFormVO.getReceivableManId());
        if (receiveMan != null){
            finance.setReceivableManCode(receiveMan.getCode());
            finance.setReceivableManName(receiveMan.getName());
        }
        finance.setCompanyType(receiveFormVO.getCompanyType());
        finance.setCompanyId(receiveFormVO.getCompanyId());
        finance.setCompanyCode(receiveFormVO.getCompanyCode());
        finance.setCompanyName(receiveFormVO.getCompanyName());
        //单据金额合计 已清金额合计 未清金额合计 应收金额合计 优惠金额合计 实收金额合计 未清余额
        //amount_total clear_amount_total unclear_amount_total receivable_amount_total discount_amount_total real_amount_total unclear_balance_total
        //1.付款总额 = 实收金额合计
        //2.默认现金支付收款金额 = 付款总额，随着用户手动修改切换 要保证现金支付 + 银行转账 = 付款总额
        finance.setAmountTotal(BigDecimal.ZERO);
        finance.setClearAmountTotal(BigDecimal.ZERO);
        finance.setUnclearAmountTotal(BigDecimal.ZERO);
        finance.setReceivableAmountTotal(BigDecimal.ZERO);
        finance.setDiscountAmountTotal(BigDecimal.ZERO);
        finance.setRealAmountTotal(BigDecimal.ZERO);
        finance.setUnclearBalanceTotal(BigDecimal.ZERO);
        finance.setTransferDate(receiveFormVO.getTransferDate());
        finance.setBankId(receiveFormVO.getBankId());
        PosBank posBank = posBankMapper.selectByPrimaryKey(receiveFormVO.getBankId());
        if (posBank != null){
            finance.setBankName(posBank.getName());
            finance.setBankAccount(posBank.getAccount());
        }
        finance.setBankAmountTotal(receiveFormVO.getBankAmountTotal());
        finance.setCashAmountTotal(receiveFormVO.getCashAmountTotal());

        finance.setStatus(ReceivableStatus.HAS_FINISHED.getCode());
        finance.setRemark(receiveFormVO.getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(finance,loginUser,true);
        return finance;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sterilisation(List<Long> list, UserVO loginUser) throws Exception{
        if (list.size() <= 0){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前无冲销对象!");
        }
        list.forEach(l ->{
            //更新主表 冲销状态
            FinanceReceivable f = new FinanceReceivable();
            f.setId(l);
            f.setStatus(ReceivableStatus.HAS_WRITTEN.getCode());
            financeReceivableMapper.updateByPrimaryKeySelective(f);
            //更新细表 冲销状态
            financeReceivableDetailMapper.updateStatus(l,ReceivableStatus.HAS_WRITTEN.getCode());
            List<FinanceReceivableDetail> detailList = financeReceivableDetailMapper.selectByReceivableId(l);
            //更新上游单据金额和数据
            updateSterilisation(detailList);
        });

        for (Long id : list) {
            FinanceReceivable financeReceivable = financeReceivableMapper.selectByPrimaryKey(id);
            //财务付款冲销：
            financeComponent.financeReceivableToBalanceAndVoucherWhenSaveOrWriteOff(loginUser, financeReceivable,"writeOff");
        }
    }

    private void updateSterilisation(List<FinanceReceivableDetail> detailList) {
        detailList.forEach(detail -> {
            Long baseOrderId = detail.getBaseOrderId();
            Integer baseOrderType = detail.getBaseOrderType();
            //实收金额
            BigDecimal realAmount = detail.getRealAmount();
            //付款单位选择购货单位搜索未清的预收发票和应收发票
            //付款单位选择供货单位搜索未清的应付贷项凭证
            //操作：用实收金额 更新上游单据 未清金额增加，已清金额减少
            if (OrderRule.ADVANCE_RECEIVABLE_INVOICE.getType().equals(baseOrderType)){
                AdvanceReceivableInvoice advanceReceivableInvoice = advanceReceivableInvoiceMapper.selectByPrimaryKey(baseOrderId);
                if (realAmount.compareTo(BigDecimal.ZERO) >= 0){
                    BigDecimal clear = advanceReceivableInvoice.getClearAmountTotal().subtract(realAmount);
                    BigDecimal unclear = advanceReceivableInvoice.getUnclearAmountTotal().add(realAmount);
                    if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                        throw new BusinessException(SysCode.FAIL.getCode(),"更新预收发票单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                    }
                    advanceReceivableInvoice.setClearAmountTotal(clear);
                    advanceReceivableInvoice.setUnclearAmountTotal(unclear);
                    if (clear.compareTo(BigDecimal.ZERO) == 0){
                        advanceReceivableInvoice.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
                    }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                        advanceReceivableInvoice.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                    }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                        advanceReceivableInvoice.setStatus(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
                    }
                    advanceReceivableInvoiceMapper.updateByPrimaryKeySelective(advanceReceivableInvoice);
                }
            }else if (OrderRule.RECEIVABLE_INVOICE.getType().equals(baseOrderType)){
                ReceivableInvoice receivableInvoice = receivableInvoiceMapper.selectByPrimaryKey(baseOrderId);
                if (realAmount.compareTo(BigDecimal.ZERO) >= 0){
                    BigDecimal clear = receivableInvoice.getClearAmountTotal().subtract(realAmount);
                    BigDecimal unclear = receivableInvoice.getUnclearAmountTotal().add(realAmount);
                    if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                        throw new BusinessException(SysCode.FAIL.getCode(),"更新应收收发票单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                    }
                    receivableInvoice.setClearAmountTotal(clear);
                    receivableInvoice.setUnclearAmountTotal(unclear);
                    if (clear.compareTo(BigDecimal.ZERO) == 0){
                        receivableInvoice.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
                    }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                        receivableInvoice.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                    }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                        receivableInvoice.setStatus(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
                    }
                    receivableInvoiceMapper.updateByPrimaryKeySelective(receivableInvoice);
                }
            }else if (OrderRule.PAYMENT_VOUCHER.getType().equals(baseOrderType)){
                PaymentVoucher paymentVoucher = paymentVoucherMapper.selectByPrimaryKey(baseOrderId);
                if (realAmount.compareTo(BigDecimal.ZERO) >= 0){
                    BigDecimal clear = paymentVoucher.getClearAmountTotal().subtract(realAmount);
                    BigDecimal unclear = paymentVoucher.getUnclearAmountTotal().add(realAmount);
                    if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                        throw new BusinessException(SysCode.FAIL.getCode(),"更新应付贷项凭证单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                    }
                    paymentVoucher.setClearAmountTotal(clear);
                    paymentVoucher.setUnclearAmountTotal(unclear);
                    if (clear.compareTo(BigDecimal.ZERO) == 0){
                        paymentVoucher.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
                    }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                        paymentVoucher.setStatus(ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus());
                    }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                        paymentVoucher.setStatus(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
                    }
                    paymentVoucherMapper.updateByPrimaryKeySelective(paymentVoucher);
                }
            }
        });
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO, Long companyId, Integer companyType) {
        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.FINANCE_RECEIVABLE.getCodePrefix());
        draftCacheVO.setSupplierId(companyId);
        draftCacheVO.setSupplierType(companyType);
        return redisComponent.getDraftCacheVO(draftCacheVO);
    }

    @Override
    public DraftCacheVO<String> saveDraftCache(UserVO userVO, DraftCacheVO<String> draftCache) {
        draftCache.setOrderCode(OrderRule.FINANCE_RECEIVABLE.getCodePrefix());
        draftCache.setEnterpriseId(userVO.getEnterpriseId());
        draftCache = redisComponent.saveDraftCacheVO(draftCache);
        return draftCache;
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue, Long companyId, Integer companyType) {
        redisComponent.removeDraftCacheVO(companyId,companyType,enterpriseId,type,redisKeyValue);
    }

    @Override
    public List<User> getReceivableManNameSelector(UserVO loginUser, String param) {
        //员工编码/员工检索码/员工名称模糊搜索
        List<User> list = userMapper.getReceivableManNameSelector(param,loginUser.getEnterpriseId());
        return list;
    }

    @Override
    public List<SelectBeanWithCode> getPayCompanyCodeSelector(Integer companyType, UserVO loginUser, String param) {
        //总部:供货单位->总部的供应商 购货单位->加盟店   加盟店:供货单位->总部+加盟店的供货单位
        //付款单位编码/检索码/名称模糊搜索
        List<SelectBeanWithCode> list = new ArrayList<>();
        if (loginUser.getChainType() == ChainType.Headquarters.getCode()){
            if (PayCompanyType.SUPPLIER.getCode() == companyType){
                List<Supplier> suppliers = supplierMapper.selectByEnterpriseIdWithParam(loginUser.getEnterpriseId(),param);
                list = packIn(suppliers,null);
            }else if (PayCompanyType.PURCHASEUNIT.getCode() == companyType){
                List<Enterprise> enterprises = enterpriseMapper.selectDivisionByHeadquartersIdWithParam(loginUser.getEnterpriseId(),param);
                list = packIn(null,enterprises);
            }
        }else if (loginUser.getChainType() == ChainType.Division.getCode()){
            //供货单位->总部+加盟店的供货单位
            List<Supplier> suppliers = supplierMapper.selectHeadquartersAndDivisionByHeadquartersIdWithParam(loginUser.getEnterpriseId(),param);
            List<SelectBeanWithCode> newlist = packIn(suppliers,null);
            SelectBeanWithCode s = new SelectBeanWithCode();
            //总部ID
            Long parentId = loginUser.getParentId();
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(parentId);
            s.setId(enterprise.getId());
            s.setCode(enterprise.getCode());
            s.setName(enterprise.getName());
            //总部需要放在下拉框第一个位置
            list.add(s);
            list.addAll(newlist);
        }
        return list;
    }

    @Override
    public String getPayCompanyName(Integer companyType, UserVO loginUser, Long id) {
        String name = "";
        if (loginUser.getChainType() == ChainType.Headquarters.getCode()){
            if (PayCompanyType.SUPPLIER.getCode() == companyType){
                Supplier supplier = supplierMapper.selectByPrimaryKey(id);
                if (supplier != null){
                    name = supplier.getName();
                }
            }else if (PayCompanyType.PURCHASEUNIT.getCode() == companyType){
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(id);
                if (enterprise != null){
                    name = enterprise.getName();
                }
            }
        }else if (loginUser.getChainType() == ChainType.Division.getCode()){
            Supplier supplier = supplierMapper.selectByPrimaryKey(id);
            if (supplier != null){
                name = supplier.getName();
            }
        }
        return name;
    }

    @Override
    public List<SelectThreeBean> getBankSelector(UserVO loginUser) {
        List<SelectThreeBean> list = new ArrayList<>();
        //总部的开户银行下拉框拉出来的是自己的
        //分店 1.总部控制 : 总部系统默认 + 总部数据 2 自主控制 自己的数据 + 自己的默认数据
        Long enterpriseId = loginUser.getEnterpriseId();
        if (loginUser.getChainType() != ChainType.Headquarters.getCode()){
            EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
            //0-总部控制；1-自主控制
            Integer posSet = bus.getPosSet();
            if (0 == posSet){
                enterpriseId = loginUser.getParentId();
            }
        }
        List<PosBank> posBanks = posBankMapper.selectByEnterpriseIdWithDefault(enterpriseId);
        posBanks.forEach(posBank -> {
            SelectThreeBean s = new SelectThreeBean();
            s.setId(posBank.getId());
            s.setAccount(posBank.getAccount());
            s.setName(posBank.getName());
            list.add(s);
        });
        return list;
    }

    @Override
    public Page getReceivableDocuments(Page page, RequestReceivableDocuments requestReceivableDocuments, UserVO loginUser) {
        /**
         * 验证条件
         */
        if (requestReceivableDocuments.getCompanyId() == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"请先选择付款单位!");
        }
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal clearAmountTotal = BigDecimal.ZERO;
        BigDecimal unclearAmountTotal = BigDecimal.ZERO;
        Map<String,Object> map = new HashMap<>(13);
        map.put("enterpriseId",loginUser.getEnterpriseId());
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        map.put("startDate",requestReceivableDocuments.getStartDate());
        map.put("endDate",requestReceivableDocuments.getEndDate());
        /**
         * 前台order接收的是receivableDate或者code
         * 将receivableDate转换成receivable_date
         */
        Integer companyType = requestReceivableDocuments.getCompanyType();

        if (companyType == CompanyType.SUPPLIER.getCode()) {
            if ("receivableDate".equals(requestReceivableDocuments.getOrder())) {
                requestReceivableDocuments.setOrder("post_date");
            }
            List<Integer> list = new ArrayList<>();
            list.add(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
            list.add(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
            map.put("list",list);
            //收款 >> 供货单位 >> 应付贷项凭证 的供货单位
            map.put("supplier",requestReceivableDocuments.getCompanyId());
        }else if (companyType == CompanyType.PURCHASE.getCode()){
            if ("receivableDate".equals(requestReceivableDocuments.getOrder())){
                requestReceivableDocuments.setOrder("baseOrderDate");
            }
            if ("code".equals(requestReceivableDocuments.getOrder())){
                requestReceivableDocuments.setOrder("baseOrderCode");
            }
            List<Integer> list = new ArrayList<>();
            //都是应收应付状态的 1，2 公用 这个状态
            list.add(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
            list.add(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
            map.put("list",list);
            //收款 >> 购货单位 >> 应收发票 和 预收发票 的供货单位
            map.put("purchase",requestReceivableDocuments.getCompanyId());
        }
        map.put("order",requestReceivableDocuments.getOrder());
        map.put("sort",requestReceivableDocuments.getSort());

        //付款单位选择购货单位搜索未清的预收发票和应收发票(不是已收款和已冲销)
        //付款单位选择供货单位搜索未清的应付贷项凭证(不是已收款+不是已冲销的)

        Integer totalRecord = 0;
        ReceivableDocumentsVO receivableDocumentsVO = new ReceivableDocumentsVO();
        List<ReceivableDocumentsPageVO> list = new ArrayList();
        if (companyType == CompanyType.SUPPLIER.getCode()){
            list = paymentVoucherMapper.selectWithUnclear(map);
            totalRecord = paymentVoucherMapper.selectTotalRecord(map);
            receivableDocumentsVO = paymentVoucherMapper.selectAmountTotal(map);
        }else if (companyType == CompanyType.PURCHASE.getCode()){
            list = advanceReceivableInvoiceMapper.selectWithUnclear(map);
            totalRecord = advanceReceivableInvoiceMapper.selectTotalRecord(map);
            receivableDocumentsVO = advanceReceivableInvoiceMapper.selectAmountTotal(map);
        }
        list.forEach(l ->{
            l.setOrderTypeName(OrderRule.getName(l.getBaseOrderType()));
        });
        if (receivableDocumentsVO == null){
            receivableDocumentsVO = new ReceivableDocumentsVO();
            receivableDocumentsVO.setAmountTotal(BigDecimal.ZERO);
            receivableDocumentsVO.setClearAmountTotal(BigDecimal.ZERO);
            receivableDocumentsVO.setUnclearAmountTotal(BigDecimal.ZERO);
        }
        receivableDocumentsVO.setList(list == null ? new ArrayList() : list);
        page.setResult(receivableDocumentsVO);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public List<ReceiveUpdateRecordVO> getUpdateRecordWithNoPage(Long id) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("id",id);
        List<FinanceReceivableModifyRecord> modifyRecords = financeReceivableModifyRecordMapper.selectByKeyId(map);
        List<ReceiveUpdateRecordVO> resultList = new ArrayList<>();
        modifyRecords.forEach(
                record ->{
                    ReceiveUpdateRecordVO recordVO = new ReceiveUpdateRecordVO();
                    recordVO = recordVO.convertToVO(record);
                    resultList.add(recordVO);
                }
        );
        return resultList;
    }

    @Override
    public void exportUpdateRecord(OutputStream output, List<ReceiveUpdateRecordVO> list, UserVO loginUser) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("updateTime","修改时间");
        map.put("modifierName","修改人员");
        map.put("reason","修改原因");
        map.put("columnChName","修改项目");
        map.put("oldContent","原内容");
        map.put("newContent","新内容");
        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        String end = "";
        List<String> name = new ArrayList<String>();
        //第一行的企业名
        name.add(loginUser.getEnterpriseName());
        //第二行的
        name.add("收款修改记录");
        purchaseGeneralComponent.commExcelExport(output,map,list,name,secondTitle,end,true,new ArrayList<String>());
    }

    private List<SelectBeanWithCode> packIn(List<Supplier> suppliers,List<Enterprise> enterprises) {
        List<SelectBeanWithCode> list = new ArrayList<>();
        if (suppliers != null){
            suppliers.forEach(s ->{
                SelectBeanWithCode n = new SelectBeanWithCode();
                n.setId(s.getId());
                n.setCode(s.getCode());
                n.setName(s.getName());
                list.add(n);
            });
        }
        if (enterprises != null){
            enterprises.forEach(e -> {
                SelectBeanWithCode n = new SelectBeanWithCode();
                n.setId(e.getId());
                n.setCode(e.getCode());
                n.setName(e.getName());
                list.add(n);
            });
        }
        return list;
    }

}
