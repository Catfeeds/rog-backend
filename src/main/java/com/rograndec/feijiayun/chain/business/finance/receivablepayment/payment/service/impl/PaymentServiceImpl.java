package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.PrepayInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao.ReceivableVoucherMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucher;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.dao.FinancePaymentDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.dao.FinancePaymentMapper;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.dao.FinancePaymentModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePayment;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePaymentDetail;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePaymentModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.service.PaymentService;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.*;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.common.CompanyType;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.common.ReceivableStatus;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.*;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosBankMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosBank;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
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
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private FinancePaymentMapper financePaymentMapper;

    @Autowired
    private FinancePaymentDetailMapper financePaymentDetailMapper;

    @Autowired
    private FinancePaymentModifyRecordMapper financePaymentModifyRecordMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private RedisComponent redisComponent;

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
    private PrepayInvoiceMapper prepayInvoiceMapper;

    @Autowired
    private ReceivableVoucherMapper receivableVoucherMapper;

    @Autowired
    private FinanceComponent financeComponent;

    @Autowired
    private PaymentInvoiceMapper paymentInvoiceMapper;

     @Override
    public Page getPayPage(Page page, RequestPayPageVO requestPayPageVO, UserVO loginUser) {

        Map<String,Object> map = new HashMap<>(14);
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        map.put("startDate",requestPayPageVO.getStartDate());
        map.put("endDate",requestPayPageVO.getEndDate());
        map.put("companyType",requestPayPageVO.getCompanyType());
        map.put("companyCode",requestPayPageVO.getCompanyCode());
        map.put("companyName",requestPayPageVO.getCompanyName());
        map.put("code",requestPayPageVO.getCode());
        map.put("paymentManName",requestPayPageVO.getPaymentManName());
        map.put("status",requestPayPageVO.getStatus());
        /**
         * 前台order接收的是paymentDate或者code
         * 将paymentDate转换成payment_date
         */
        if ("paymentDate".equals(requestPayPageVO.getOrder())){
            requestPayPageVO.setOrder("payment_date");
        }
        map.put("order",requestPayPageVO.getOrder());
        map.put("sort",requestPayPageVO.getSort());
        //都是自己门店看自己的内容
        map.put("enterpriseId",loginUser.getEnterpriseId());
        /**
         * 返回的结果集
         */
        PayTotalPageVO returnVO = new PayTotalPageVO();
        List<PaymentPageVO> list = financePaymentMapper.getPayPage(map);
        PaymentPageTotal pageTotal = financePaymentMapper.selectTotal(map);
        if (pageTotal == null){
            returnVO.setPaymentAmountSummary(BigDecimal.ZERO);
            returnVO.setRealAmountSummary(BigDecimal.ZERO );
            returnVO.setDiscountAmountSummary(BigDecimal.ZERO);
        }else {
            returnVO.setPaymentAmountSummary(pageTotal.getPaymentAmountSummary() == null ? BigDecimal.ZERO : pageTotal.getPaymentAmountSummary());
            returnVO.setRealAmountSummary(pageTotal.getRealAmountSummary() == null ? BigDecimal.ZERO : pageTotal.getRealAmountSummary());
            returnVO.setDiscountAmountSummary(pageTotal.getDiscountAmountSummary() == null ? BigDecimal.ZERO : pageTotal.getDiscountAmountSummary());
        }

        returnVO.setList(list);
        page.setResult(returnVO);
        Integer totalRecord = financePaymentMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public PayFormVO getPayDetail(Long id) {
        FinancePayment financePayment = financePaymentMapper.selectByPrimaryKey(id);
        List<FinancePaymentDetail> detailList = financePaymentDetailMapper.selectByPaymentId(id);
        PayFormVO formVO = new PayFormVO();
        formVO = formVO.convertToVO(financePayment,detailList);
        return formVO;
    }


    @Override
    public void export(OutputStream output, PayFormVO payFormVO, UserVO loginUser) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>(11);
        map.put("baseOrderDate","日期");
        map.put("baseOrderCode","单号");
        map.put("orderTypeName","单据类型");
        map.put("amount","单据金额");
        map.put("clearAmount","已清金额");
        map.put("unclearAmount","未清金额");
        map.put("paymentAmount","本次付款金额");
        map.put("discountAmount","本次优惠金额");
        map.put("realAmount","本次实收金额");
        map.put("unclearBalance","未清余额");
        map.put("remark","备注");
        List<PayExcelVO> list = new ArrayList<>();
        /**
         * 记录导出尾行的合计中数值数据
         */
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal clearAmount = BigDecimal.ZERO;
        BigDecimal unclearAmount = BigDecimal.ZERO;
        BigDecimal paymentAmount = BigDecimal.ZERO;
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal realAmount = BigDecimal.ZERO;
        BigDecimal unclearBalance = BigDecimal.ZERO;
        List<PayDetailVO> payList = payFormVO.getList();
        for (PayDetailVO d : payList) {
            PayExcelVO excel = convertToExcel(d);
            amount = amount.add(d.getAmount());
            clearAmount = clearAmount.add(d.getClearAmount());
            unclearAmount = unclearAmount.add(d.getUnclearAmount());
            paymentAmount = paymentAmount.add(d.getPaymentAmount());
            discountAmount = discountAmount.add(d.getDiscountAmount());
            realAmount = realAmount.add(d.getRealAmount());
            unclearBalance = unclearBalance.add(d.getUnclearBalance());
            list.add(excel);
        }
        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("收款单位编码:");
        titleSecondRow.append(payFormVO.getCompanyCode() == null ? "" : payFormVO.getCompanyCode());
        titleSecondRow.append("  收款单位名称:");
        titleSecondRow.append(payFormVO.getCompanyName() == null ? "" : payFormVO.getCompanyName());
        titleSecondRow.append("  收款单位类型:");
        titleSecondRow.append(payFormVO.getCompanyType() == CompanyType.SUPPLIER.getCode() ?
                CompanyType.SUPPLIER.getName() :
                (payFormVO.getCompanyType() == CompanyType.PURCHASE.getCode() ? CompanyType.PURCHASE.getName() : ""));
        titleSecondRow.append("  付款日期:");
        titleSecondRow.append(payFormVO.getPaymentDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(payFormVO.getPaymentDate()));
        titleSecondRow.append("  付款人员:");
        titleSecondRow.append(payFormVO.getPaymentManName() == null ? "" : payFormVO.getPaymentManName());
        titleSecondRow.append("  单号:");
        titleSecondRow.append(payFormVO.getCode() == null ? "" : payFormVO.getCode());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(payFormVO.getRemark() == null ? "" : payFormVO.getRemark());
        secondTitle.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        end.append("付款总额:");
        end.append(payFormVO.getTotalPay());
        end.append(";");
        end.append("银行转账:   ");
        end.append("         付款日期:");
        end.append(payFormVO.getTransferDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(payFormVO.getTransferDate()));
        end.append(";");
        end.append("         开户银行:");
        end.append(payFormVO.getBankName() == null ? "" : payFormVO.getBankName());
        end.append(";");
        end.append("         开户账号:");
        end.append(payFormVO.getBankAccount() == null ? "" : payFormVO.getBankAccount());
        end.append(";");
        end.append("         支付金额:");
        end.append(payFormVO.getBankAmountTotal());
        end.append(";");
        end.append("现金支付:   ");
        end.append("         支付金额:");
        end.append(payFormVO.getCashAmountTotal());
        end.append(";");
        end.append("         未清余额:");
        end.append(payFormVO.getUnclearBalanceTotal());
        List<String> name = new ArrayList<String>();
        //第一行的企业名
        name.add(enterpriseMapper.selectByPrimaryKey(financePaymentMapper.selectByPrimaryKey(payFormVO.getId()).getEnterpriseId()).getName());
        //第二行的
        name.add("付款");
        StringBuilder sb = new StringBuilder();
        sb.append(amount)
                .append(";")
                .append(clearAmount)
                .append(";")
                .append(unclearAmount)
                .append(";")
                .append(paymentAmount)
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
        needTotalName.add("paymentAmount");
        needTotalName.add("discountAmount");
        needTotalName.add("realAmount");
        needTotalName.add("unclearBalance");
        purchaseGeneralComponent.commExcelDistrExport(output,map,list,name,secondTitle,end.toString(),sb.toString(),needTotalName);
    }

    private PayExcelVO convertToExcel(PayDetailVO d) {
        PayExcelVO excelVO = new PayExcelVO();
        excelVO.setBaseOrderDate(d.getBaseOrderDate() == null ? "" :
                new SimpleDateFormat("yyyy-MM-dd").format(d.getBaseOrderDate()));
        excelVO.setBaseOrderCode(d.getBaseOrderCode());
        excelVO.setOrderTypeName(OrderRule.getName(d.getBaseOrderType()));
        excelVO.setAmount(d.getAmount());
        excelVO.setClearAmount(d.getClearAmount());
        excelVO.setUnclearAmount(d.getUnclearAmount());
        excelVO.setPaymentAmount(d.getPaymentAmount());
        excelVO.setDiscountAmount(d.getDiscountAmount());
        excelVO.setRealAmount(d.getRealAmount());
        excelVO.setUnclearBalance(d.getUnclearBalance());
        excelVO.setRemark(d.getRemark());
        return excelVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addOrUpdatePayDetail(PayFormVO payFormVO, UserVO loginUser) throws Exception{
        //返回信息
        String msg = "";
        //表单数据验证
        List<PayDetailVO> list = payFormVO.getList();
        if (list == null || list.size() <= 0){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前保存功能无细单数据,请先添加之后再保存!");
        }
        if (payFormVO.getPaymentDate() == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"付款日期为必填!");
        }
        if (payFormVO.getId() == null){
            //新增情况
            //************************************用于从细表取数据求和更新主表*********
            FinancePayment financePayment = FormToFinancePay(payFormVO,loginUser);
            //插入主表
            financePaymentMapper.insertSelective(financePayment);
            //插入细表(需要更新主单数据)
            List<FinancePaymentDetail> detailList =  FormToFinancePaymentDetail(financePayment,list,loginUser);
            detailList.forEach(detail -> {
                financePaymentDetailMapper.insertSelective(detail);
            });
            //更新主表数据
            FinancePayment fp = updateMajorPayment(detailList, financePayment);
            msg = "单号 :" + financePayment.getCode();
            //删除草稿
            removeDraftCach(loginUser.getEnterpriseId(), OrderRule.FINANCE_PAYMENT.getCodePrefix(), payFormVO.getRedisKeyValue(),financePayment.getCompanyId(), financePayment.getCompanyType());
            //更新对应上游单据状态和金额
            updatePreviousMoneyAndStatus(fp,detailList,loginUser);
            //财务付款保存
            financeComponent.financePaymentToBalanceAndVoucherWhenSaveOrWriteOff(loginUser, fp,"save");
        }else{
            if (payFormVO.getUpdateDetail() == null || "".equals(payFormVO.getUpdateDetail())){
                throw new BusinessException(SysCode.FAIL.getCode(),"修改记录不能为空!");
            }
            //修改情况
            Long payId = payFormVO.getId();
            FinancePayment oldFinancePayment = financePaymentMapper.selectByPrimaryKey(payId);
            //1.修改主表数据
            FinancePayment financePayment = FormToUpdateFinancePay(payFormVO,loginUser);
            financePaymentMapper.updateByPrimaryKeySelective(financePayment);
            //2.修改细表数据
            //明细数据无须修改
            //3.记录修改记录信息
            /**
             * 记录保存记录
             */
            List<PayUpdateRecordWithBLOBs> payUpdateRecordWithBLOB = getPayModifyRecord(payFormVO,loginUser,oldFinancePayment,financePayment);
            for(PayUpdateRecordWithBLOBs ur : payUpdateRecordWithBLOB){
                ur.setRemark(payFormVO.getUpdateDetail());
                ur.setReason(payFormVO.getUpdateDetail());
                financePaymentModifyRecordMapper.insertSelective(ur);
            }
            msg = "单号 :" + oldFinancePayment.getCode();
        }
        return msg;
    }

    private void updatePreviousMoneyAndStatus(FinancePayment financePayment, List<FinancePaymentDetail> detailList, UserVO loginUser) {
        detailList.forEach(detail->{
            Long baseOrderId = detail.getBaseOrderId();
            Integer baseOrderType = detail.getBaseOrderType();
            //实付金额
            BigDecimal paymentAmount = detail.getPaymentAmount();
            //收款单位选择供货单位搜索未清的预付发票和应付发票
            //收款单位选择购货单位搜索未清的应收贷项凭证
            if (OrderRule.PREPAY_INVOICE.getType().equals(baseOrderType)){
                PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(baseOrderId);
                BigDecimal clearAmountTotal = prepayInvoice.getClearAmountTotal();
                BigDecimal unclearAmountTotal = prepayInvoice.getUnclearAmountTotal();
                //0成本入库情况
                if (clearAmountTotal.compareTo(BigDecimal.ZERO) == 0
                        && unclearAmountTotal.compareTo(BigDecimal.ZERO) == 0 && paymentAmount.compareTo(BigDecimal.ZERO) == 0){
                    prepayInvoice.setStatus(FinancePaymentStatus.PAID);
                    prepayInvoiceMapper.updateByPrimaryKeySelective(prepayInvoice);
                }else {
                    if (paymentAmount.compareTo(BigDecimal.ZERO) >= 0){
                        BigDecimal clear = prepayInvoice.getClearAmountTotal().add(paymentAmount);
                        BigDecimal unclear = prepayInvoice.getUnclearAmountTotal().subtract(paymentAmount);
                        if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                            throw new BusinessException(SysCode.FAIL.getCode(),"更新预付发票单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                        }
                        prepayInvoice.setClearAmountTotal(clear);
                        prepayInvoice.setUnclearAmountTotal(unclear);
                        if (clear.compareTo(BigDecimal.ZERO) == 0){
                            prepayInvoice.setStatus(FinancePaymentStatus.PRE_PAYMENT);
                        }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                            prepayInvoice.setStatus(FinancePaymentStatus.PAID);
                        }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                            prepayInvoice.setStatus(FinancePaymentStatus.PARTIAL_PAYMENT);
                        }
                        prepayInvoiceMapper.updateByPrimaryKeySelective(prepayInvoice);
                    }
                }
            }else if (OrderRule.PAYMENT_INVOICE.getType().equals(baseOrderType)){
                PaymentInvoice paymentInvoice = paymentInvoiceMapper.selectByPrimaryKey(baseOrderId);
                BigDecimal clearAmountTotal = paymentInvoice.getClearAmountTotal();
                BigDecimal unclearAmountTotal = paymentInvoice.getUnclearAmountTotal();
                //0成本入库情况
                if (clearAmountTotal.compareTo(BigDecimal.ZERO) == 0
                        && unclearAmountTotal.compareTo(BigDecimal.ZERO) == 0 && paymentAmount.compareTo(BigDecimal.ZERO) == 0){
                    paymentInvoice.setStatus(FinancePaymentStatus.PAID);
                    paymentInvoiceMapper.updateByPrimaryKeySelective(paymentInvoice);
                }else {
                    if (paymentAmount.compareTo(BigDecimal.ZERO) >= 0){
                        BigDecimal clear = paymentInvoice.getClearAmountTotal().add(paymentAmount);
                        BigDecimal unclear = paymentInvoice.getUnclearAmountTotal().subtract(paymentAmount);
                        if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                            throw new BusinessException(SysCode.FAIL.getCode(),"更新应付发票单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                        }
                        paymentInvoice.setClearAmountTotal(clear);
                        paymentInvoice.setUnclearAmountTotal(unclear);
                        if (clear.compareTo(BigDecimal.ZERO) == 0){
                            paymentInvoice.setStatus(FinancePaymentStatus.PRE_PAYMENT);
                        }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                            paymentInvoice.setStatus(FinancePaymentStatus.PAID);
                        }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                            paymentInvoice.setStatus(FinancePaymentStatus.PARTIAL_PAYMENT);
                        }
                        paymentInvoiceMapper.updateByPrimaryKeySelective(paymentInvoice);
                    }
                }
            }else if (OrderRule.RECEIVABLE_VOUCHER.getType().equals(baseOrderType)){
                ReceivableVoucher receivableVoucher = receivableVoucherMapper.selectByPrimaryKey(baseOrderId);
                BigDecimal clearAmountTotal = receivableVoucher.getClearAmountTotal();
                BigDecimal unclearAmountTotal = receivableVoucher.getUnclearAmountTotal();
                //0成本入库情况
                if (clearAmountTotal.compareTo(BigDecimal.ZERO) == 0
                        && unclearAmountTotal.compareTo(BigDecimal.ZERO) == 0 && paymentAmount.compareTo(BigDecimal.ZERO) == 0){
                    receivableVoucher.setStatus(FinancePaymentStatus.PAID);
                    receivableVoucherMapper.updateByPrimaryKeySelective(receivableVoucher);
                }else {
                    if (paymentAmount.compareTo(BigDecimal.ZERO) >= 0){
                        BigDecimal clear = receivableVoucher.getClearAmountTotal().add(paymentAmount);
                        BigDecimal unclear = receivableVoucher.getUnclearAmountTotal().subtract(paymentAmount);
                        if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                            throw new BusinessException(SysCode.FAIL.getCode(),"更新应收贷项凭证错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                        }
                        receivableVoucher.setClearAmountTotal(clear);
                        receivableVoucher.setUnclearAmountTotal(unclear);
                        if (clear.compareTo(BigDecimal.ZERO) == 0){
                            receivableVoucher.setStatus(FinancePaymentStatus.PRE_PAYMENT);
                        }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                            receivableVoucher.setStatus(FinancePaymentStatus.PAID);
                        }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                            receivableVoucher.setStatus(FinancePaymentStatus.PARTIAL_PAYMENT);
                        }
                        receivableVoucherMapper.updateByPrimaryKeySelective(receivableVoucher);
                    }
                }
            }
        });
    }

    private List<PayUpdateRecordWithBLOBs> getPayModifyRecord(PayFormVO payFormVO, UserVO loginUser, FinancePayment oldFinancePayment, FinancePayment financePayment) throws Exception {
        Map<String,Object> newFinancePayMap = getFieldsMap(financePayment);
        Map<String,Object> financePayMap = getFieldsMap(oldFinancePayment);

        Map<String,String> fieldSkipMap = fieldSkipMap();

        List<PayUpdateRecordWithBLOBs> payUpdateRecordWithBLOBs = getModifyRecordList(loginUser,financePayment,"saas_finance_payment"
                ,financePayMap,newFinancePayMap,fieldSkipMap);
        return payUpdateRecordWithBLOBs;
    }

    private List<PayUpdateRecordWithBLOBs> getModifyRecordList(UserVO loginUser, FinancePayment financePayment, String tableName, Map<String, Object> valMap, Map<String, Object> newMap, Map<String, String> fieldSkipMap) {
        List<PayUpdateRecordWithBLOBs> payUpdateRecordWithBLOBs = new ArrayList<>();
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
                        excuteSupplierModifyRecord(loginUser, financePayment, tableName, payUpdateRecordWithBLOBs, entry, obj, newObj);
                    }
                } else if (!obj.equals(newObj)) {
                    excuteSupplierModifyRecord(loginUser, financePayment, tableName, payUpdateRecordWithBLOBs, entry, obj, newObj);
                }
            }
            if (obj == null && newObj != null) {
                excuteSupplierModifyRecord(loginUser, financePayment, tableName, payUpdateRecordWithBLOBs, entry, obj, newObj);
            }
        }
        return payUpdateRecordWithBLOBs;
    }

    private void excuteSupplierModifyRecord(UserVO loginUser, FinancePayment financePayment, String tableName, List<PayUpdateRecordWithBLOBs> payUpdateRecordWithBLOBs, Map.Entry<String, String> entry, Object obj, Object newObj) {
        PayUpdateRecordWithBLOBs modify = new PayUpdateRecordWithBLOBs();
        modify.setEnterpriseId(loginUser.getEnterpriseId());
        modify.setParentId(loginUser.getParentId());
        modify.setTableName(tableName);
        modify.setKeyId(financePayment.getId());
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
        payUpdateRecordWithBLOBs.add(modify);
    }

    private Map<String,String> fieldSkipMap() {
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("paymentDate","付款日期");
        fieldNames.put("paymentManName","付款人员");
        fieldNames.put("remark","备注");
        return fieldNames;
    }

    private Map<String,Object> getFieldsMap(Object obj) throws Exception {
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

    private FinancePayment FormToUpdateFinancePay(PayFormVO payFormVO, UserVO loginUser) throws Exception {
        FinancePayment financePayment = new FinancePayment();
        financePayment.setId(payFormVO.getId());
        //能修改的内容包含 付款日期,付款人员,备注 tips:已冲销单据无法修改
        financePayment.setPaymentDate(payFormVO.getPaymentDate());
        financePayment.setPaymentManId(payFormVO.getPaymentManId());
        if (payFormVO.getPaymentManId() != null){
            User user = userMapper.selectByPrimaryKey(payFormVO.getPaymentManId());
            if (user != null){
                financePayment.setPaymentManCode(user.getCode());
                financePayment.setPaymentManName(user.getName());
            }
        }
        financePayment.setRemark(payFormVO.getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(financePayment,loginUser,false);
        return financePayment;
    }

    private FinancePayment updateMajorPayment(List<FinancePaymentDetail> detailList, FinancePayment financePayment) {
        //单据金额合计 已清金额合计 未清金额合计 应付金额合计 优惠金额合计 实收金额合计 未清余额
        //amount_total clear_amount_total unclear_amount_total payment_amount_total
        //discount_amount_total real_amount_total unclear_balance_total
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal clearAmountTotal = BigDecimal.ZERO;
        BigDecimal unclearAmountTotal = BigDecimal.ZERO;
        BigDecimal paymentAmountTotal = BigDecimal.ZERO;
        BigDecimal discountAmountTotal = BigDecimal.ZERO;
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        BigDecimal unclearBalanceTotal = BigDecimal.ZERO;
        for (FinancePaymentDetail d : detailList) {
            amountTotal = amountTotal.add(d.getAmount());
            clearAmountTotal = clearAmountTotal.add(d.getClearAmount());
            unclearAmountTotal = unclearAmountTotal.add(d.getUnclearAmount());
            paymentAmountTotal = paymentAmountTotal.add(d.getPaymentAmount());
            discountAmountTotal = discountAmountTotal.add(d.getDiscountAmount());
            realAmountTotal = realAmountTotal.add(d.getRealAmount());
            unclearBalanceTotal = unclearBalanceTotal.add(d.getUnclearBalance());
        }
        FinancePayment f = new FinancePayment();
        f.setId(financePayment.getId());
        f.setAmountTotal(amountTotal);
        f.setClearAmountTotal(clearAmountTotal);
        f.setUnclearAmountTotal(unclearAmountTotal);
        f.setPaymentAmountTotal(paymentAmountTotal);
        f.setDiscountAmountTotal(discountAmountTotal);
        f.setRealAmountTotal(realAmountTotal);
        f.setUnclearBalanceTotal(unclearBalanceTotal);
        financePaymentMapper.updateByPrimaryKeySelective(f);
        return financePaymentMapper.selectByPrimaryKey(f.getId());
    }

    private List<FinancePaymentDetail> FormToFinancePaymentDetail(FinancePayment financePayment, List<PayDetailVO> list, UserVO loginUser) {
        List<FinancePaymentDetail> result = new ArrayList<>();
        list.forEach(d ->{
            FinancePaymentDetail detail = new FinancePaymentDetail();
            detail.setEnterpriseId(loginUser.getEnterpriseId());
            detail.setParentId(loginUser.getParentId());
            detail.setPaymentId(financePayment.getId());
            detail.setBaseOrderId(d.getBaseOrderId());
            detail.setBaseOrderCode(d.getBaseOrderCode());
            detail.setBaseOrderType(d.getBaseOrderType());
            detail.setBaseOrderDate(d.getBaseOrderDate());
            detail.setAmount(d.getAmount());
            detail.setClearAmount(d.getClearAmount());
            detail.setUnclearAmount(d.getUnclearAmount());
            detail.setPaymentAmount(d.getPaymentAmount());
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

    private FinancePayment FormToFinancePay(PayFormVO payFormVO, UserVO loginUser) throws Exception{
        FinancePayment financePayment = new FinancePayment();
        financePayment.setEnterpriseId(loginUser.getEnterpriseId());
        financePayment.setParentId(loginUser.getParentId());
        String code = orderCodeComponent.
                generate(OrderRule.FINANCE_PAYMENT.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode());
        financePayment.setCode(code);
        financePayment.setOrderType(OrderRule.FINANCE_PAYMENT.getType());
        //总部:供货单位->总部的供应商 购货单位->加盟店   加盟店:供货单位->总部+加盟店的供货单位
        if (loginUser.getChainType() == ChainType.Headquarters.getCode()){
            if (PayCompanyType.SUPPLIER.getCode() == payFormVO.getCompanyType()){
                financePayment.setFinanceAccountType(FinanceAccountType.SUPPLIER.getType());
            }else if (PayCompanyType.PURCHASEUNIT.getCode() == payFormVO.getCompanyType()){
                financePayment.setFinanceAccountType(FinanceAccountType.LEAGUE.getType());
            }
        }else if (loginUser.getChainType() == ChainType.Division.getCode()){
            //供货单位->总部+加盟店的供货单位
            financePayment.setFinanceAccountType(FinanceAccountType.SUPPLIER.getType());
        }
        financePayment.setPaymentDate(payFormVO.getPaymentDate());
        financePayment.setPaymentManId(payFormVO.getPaymentManId());
        //收货人员
        User payMan = userMapper.selectByPrimaryKey(payFormVO.getPaymentManId());
        if (payMan != null){
            financePayment.setPaymentManCode(payMan.getCode());
            financePayment.setPaymentManName(payMan.getName());
        }
        financePayment.setCompanyType(payFormVO.getCompanyType());
        financePayment.setCompanyId(payFormVO.getCompanyId());
        /**
         * 总部:供货单位->总部的供应商 购货单位->加盟店   加盟店:供货单位->总部+加盟店的供货单位
         */
        financePayment.setCompanyCode(payFormVO.getCompanyCode());
        financePayment.setCompanyName(payFormVO.getCompanyName());
        //单据金额合计 已清金额合计 未清金额合计 应收金额合计 优惠金额合计 实收金额合计 未清余额
        //amount_total clear_amount_total unclear_amount_total receivable_amount_total
        //discount_amount_total real_amount_total unclear_balance_total
        //1.付款总额 = 实收金额合计
        //2.默认现金支付收款金额 = 付款总额，随着用户手动修改切换 要保证现金支付 + 银行转账 = 付款总额
        financePayment.setAmountTotal(BigDecimal.ZERO);
        financePayment.setClearAmountTotal(BigDecimal.ZERO);
        financePayment.setUnclearAmountTotal(BigDecimal.ZERO);
        financePayment.setPaymentAmountTotal(BigDecimal.ZERO);
        financePayment.setDiscountAmountTotal(BigDecimal.ZERO);
        financePayment.setRealAmountTotal(BigDecimal.ZERO);
        financePayment.setUnclearBalanceTotal(BigDecimal.ZERO);
        financePayment.setTransferDate(payFormVO.getTransferDate());
        financePayment.setBankId(payFormVO.getBankId());
        PosBank posBank = posBankMapper.selectByPrimaryKey(payFormVO.getBankId());
        if (posBank != null){
            financePayment.setBankName(posBank.getName());
            financePayment.setBankAccount(posBank.getAccount());
        }
        financePayment.setBankAmountTotal(payFormVO.getBankAmountTotal());
        financePayment.setCashAmountTotal(payFormVO.getCashAmountTotal());

        financePayment.setStatus(ReceivableStatus.HAS_FINISHED.getCode());
        financePayment.setRemark(payFormVO.getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(financePayment,loginUser,true);
        return financePayment;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sterilisation(List<Long> list, UserVO loginUser) throws Exception{
        if (list.size() <= 0){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前无冲销对象!");
        }
        list.forEach(l ->{
            //更新主表 冲销状态
            FinancePayment f = new FinancePayment();
            f.setId(l);
            f.setStatus(ReceivableStatus.HAS_WRITTEN.getCode());
            financePaymentMapper.updateByPrimaryKeySelective(f);
            //更新细表 冲销状态
            financePaymentDetailMapper.updateStatus(l,ReceivableStatus.HAS_WRITTEN.getCode());
            List<FinancePaymentDetail> details = financePaymentDetailMapper.selectByPaymentId(l);
            updateSterilisation(details);
        });
        for (Long id : list) {
            FinancePayment financePayment = financePaymentMapper.selectByPrimaryKey(id);
            //财务付款冲销
            financeComponent.financePaymentToBalanceAndVoucherWhenSaveOrWriteOff(loginUser, financePayment, "writeOff");
        }

    }

    private void updateSterilisation(List<FinancePaymentDetail> detailList) {
        detailList.forEach(detail->{
            Long baseOrderId = detail.getBaseOrderId();
            Integer baseOrderType = detail.getBaseOrderType();
            //实付金额
            BigDecimal realAmount = detail.getRealAmount();
            //收款单位选择供货单位搜索未清的预付发票和应付发票
            //收款单位选择购货单位搜索未清的应收贷项凭证
            if (OrderRule.PREPAY_INVOICE.getType().equals(baseOrderType)){
                PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(baseOrderId);
                if (realAmount.compareTo(BigDecimal.ZERO) >= 0){
                    BigDecimal clear = prepayInvoice.getClearAmountTotal().subtract(realAmount);
                    BigDecimal unclear = prepayInvoice.getUnclearAmountTotal().add(realAmount);
                    if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                        throw new BusinessException(SysCode.FAIL.getCode(),"更新预付发票单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                    }
                    prepayInvoice.setClearAmountTotal(clear);
                    prepayInvoice.setUnclearAmountTotal(unclear);
                    if (clear.compareTo(BigDecimal.ZERO) == 0){
                        prepayInvoice.setStatus(FinancePaymentStatus.PRE_PAYMENT);
                    }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                        prepayInvoice.setStatus(FinancePaymentStatus.PAID);
                    }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                        prepayInvoice.setStatus(FinancePaymentStatus.PARTIAL_PAYMENT);
                    }
                    prepayInvoiceMapper.updateByPrimaryKeySelective(prepayInvoice);
                }
            }else if (OrderRule.PAYMENT_INVOICE.getType().equals(baseOrderType)){
                PaymentInvoice paymentInvoice = paymentInvoiceMapper.selectByPrimaryKey(baseOrderId);
                if (realAmount.compareTo(BigDecimal.ZERO) >= 0){
                    BigDecimal clear = paymentInvoice.getClearAmountTotal().subtract(realAmount);
                    BigDecimal unclear = paymentInvoice.getUnclearAmountTotal().add(realAmount);
                    if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                        throw new BusinessException(SysCode.FAIL.getCode(),"更新应付发票单据错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                    }
                    paymentInvoice.setClearAmountTotal(clear);
                    paymentInvoice.setUnclearAmountTotal(unclear);
                    if (clear.compareTo(BigDecimal.ZERO) == 0){
                        paymentInvoice.setStatus(FinancePaymentStatus.PRE_PAYMENT);
                    }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                        paymentInvoice.setStatus(FinancePaymentStatus.PAID);
                    }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                        paymentInvoice.setStatus(FinancePaymentStatus.PARTIAL_PAYMENT);
                    }
                    paymentInvoiceMapper.updateByPrimaryKeySelective(paymentInvoice);
                }
            }else if (OrderRule.RECEIVABLE_VOUCHER.getType().equals(baseOrderType)){
                ReceivableVoucher receivableVoucher = receivableVoucherMapper.selectByPrimaryKey(baseOrderId);
                if (realAmount.compareTo(BigDecimal.ZERO) >= 0){
                    BigDecimal clear = receivableVoucher.getClearAmountTotal().subtract(realAmount);
                    BigDecimal unclear = receivableVoucher.getUnclearAmountTotal().add(realAmount);
                    if (clear.compareTo(BigDecimal.ZERO) < 0 || unclear.compareTo(BigDecimal.ZERO) < 0){
                        throw new BusinessException(SysCode.FAIL.getCode(),"更新应收贷项凭证错误已清金额更新后=" + clear + "未清金额更新后=" + unclear + "单据ID是" + baseOrderId);
                    }
                    receivableVoucher.setClearAmountTotal(clear);
                    receivableVoucher.setUnclearAmountTotal(unclear);
                    if (clear.compareTo(BigDecimal.ZERO) == 0){
                        receivableVoucher.setStatus(FinancePaymentStatus.PRE_PAYMENT);
                    }else if (unclear.compareTo(BigDecimal.ZERO) == 0){
                        receivableVoucher.setStatus(FinancePaymentStatus.PAID);
                    }else if (clear.compareTo(BigDecimal.ZERO) > 0 && unclear.compareTo(BigDecimal.ZERO) > 0){
                        receivableVoucher.setStatus(FinancePaymentStatus.PARTIAL_PAYMENT);
                    }
                    receivableVoucherMapper.updateByPrimaryKeySelective(receivableVoucher);
                }
            }
        });
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO, Long companyId, Integer companyType) {
        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.FINANCE_PAYMENT.getCodePrefix());
        draftCacheVO.setSupplierId(companyId);
        draftCacheVO.setSupplierType(companyType);
        return redisComponent.getDraftCacheVO(draftCacheVO);
    }

    @Override
    public DraftCacheVO<String> saveDraftCache(UserVO userVO, DraftCacheVO<String> draftCache) {
        draftCache.setOrderCode(OrderRule.FINANCE_PAYMENT.getCodePrefix());
        draftCache.setEnterpriseId(userVO.getEnterpriseId());
        draftCache = redisComponent.saveDraftCacheVO(draftCache);
        return draftCache;
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue, Long companyId, Integer companyType) {
        redisComponent.removeDraftCacheVO(companyId,companyType,enterpriseId,type,redisKeyValue);
    }

    @Override
    public Page getPayUpdateRecord(Page page, RequestModifyPageVO requestModifyPageVO, UserVO loginUser) {
        Long id = requestModifyPageVO.getId();
        Map<String,Object> map = new HashMap<>(3);
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        map.put("id",id);
        List<FinancePaymentModifyRecord> modifyRecords = financePaymentModifyRecordMapper.selectByKeyId(map);
        List<PayUpdateRecordVO> resultList = new ArrayList<>();
        modifyRecords.forEach(
                record ->{
                    PayUpdateRecordVO recordVO = new PayUpdateRecordVO();
                    recordVO = recordVO.convertToVO(record);
                    resultList.add(recordVO);
                }
        );
        page.setResult(resultList);
        Integer totalRecord = financePaymentModifyRecordMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public List<PayUpdateRecordVO> getUpdateRecordWithNoPage(Long id) {
        Map<String,Object> map = new HashMap<>(1);
        map.put("id",id);
        List<FinancePaymentModifyRecord> modifyRecords = financePaymentModifyRecordMapper.selectByKeyId(map);
        List<PayUpdateRecordVO> resultList = new ArrayList<>();
        modifyRecords.forEach(
                record ->{
                    PayUpdateRecordVO recordVO = new PayUpdateRecordVO();
                    recordVO = recordVO.convertToVO(record);
                    resultList.add(recordVO);
                }
        );
        return resultList;
    }

    @Override
    public void exportUpdateRecord(OutputStream output, List<PayUpdateRecordVO> list, UserVO loginUser) {
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
        name.add("付款修改记录");
        purchaseGeneralComponent.commExcelExport(output,map,list,name,secondTitle,end,true,new ArrayList<String>());
    }

    @Override
    public Page getPayDocuments(Page page, RequestPayDocuments requestPayDocuments, UserVO loginUser) {
        /**
         * 验证条件
         */
        if (requestPayDocuments.getCompanyId() == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"请先选择收款单位!");
        }
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal clearAmountTotal = BigDecimal.ZERO;
        BigDecimal unclearAmountTotal = BigDecimal.ZERO;
        Map<String,Object> map = new HashMap<>(13);
        map.put("enterpriseId",loginUser.getEnterpriseId());
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        map.put("startDate",requestPayDocuments.getStartDate());
        map.put("endDate",requestPayDocuments.getEndDate());
        /**
         * 前台order接收的是paymentDate或者code
         * 将paymentDate转换成payment_date
         */
        Integer companyType = requestPayDocuments.getCompanyType();

        if (companyType == CompanyType.SUPPLIER.getCode()) {
            if ("paymentDate".equals(requestPayDocuments.getOrder())){
                requestPayDocuments.setOrder("baseOrderDate");
            }
            if ("code".equals(requestPayDocuments.getOrder())){
                requestPayDocuments.setOrder("baseOrderCode");
            }
            List<Integer> list = new ArrayList<>();
            //都是应收应付状态的 1，2 公用 这个状态
            list.add(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
            list.add(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
            map.put("list",list);
            //预付发票和应付发票
            map.put("supplier",requestPayDocuments.getCompanyId());
        }else if (companyType == CompanyType.PURCHASE.getCode()){
            if ("paymentDate".equals(requestPayDocuments.getOrder())) {
                requestPayDocuments.setOrder("post_date");
            }
            List<Integer> list = new ArrayList<>();
            list.add(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
            list.add(ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus());
            map.put("list",list);
            //应收贷项凭证
            map.put("purchase",requestPayDocuments.getCompanyId());
        }
        map.put("order",requestPayDocuments.getOrder());
        map.put("sort",requestPayDocuments.getSort());

        //收款单位选择供货单位搜索未清的预付发票和应付发票
        //收款单位选择购货单位搜索未清的应收贷项凭证

        Integer totalRecord = 0;
        PayDocumentsVO payDocumentsVO = new PayDocumentsVO();
        List<PayDocumentsPageVO> list = new ArrayList();
        if (companyType == CompanyType.SUPPLIER.getCode()){
            list = prepayInvoiceMapper.selectWithUnclear(map);
            totalRecord = prepayInvoiceMapper.selectTotalRecord(map);
            payDocumentsVO = prepayInvoiceMapper.selectAmountTotal(map);
        }else if (companyType == CompanyType.PURCHASE.getCode()){
            list = receivableVoucherMapper.selectWithUnclear(map);
            totalRecord = receivableVoucherMapper.selectTotalRecord(map);
            payDocumentsVO = receivableVoucherMapper.selectAmountTotal(map);
        }
        list.forEach(l ->{
            l.setOrderTypeName(OrderRule.getName(l.getBaseOrderType()));
        });
        if (payDocumentsVO == null){
            payDocumentsVO = new PayDocumentsVO();
            payDocumentsVO.setAmountTotal(BigDecimal.ZERO);
            payDocumentsVO.setClearAmountTotal(BigDecimal.ZERO);
            payDocumentsVO.setUnclearAmountTotal(BigDecimal.ZERO);
        }
        payDocumentsVO.setList(list == null ? new ArrayList<>() : list);
        page.setResult(payDocumentsVO);
        page.setTotalRecord(totalRecord);
        return page;
    }
}
