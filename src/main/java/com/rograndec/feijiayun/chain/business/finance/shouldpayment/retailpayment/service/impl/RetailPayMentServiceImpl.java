package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.constant.status.DailySettleStatusType;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao.RetailDailySettleDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao.RetailDailySettleMapper;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao.RetailDailySettleModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettle;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettleDetail;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.dao.*;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity.*;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.service.RetailPayMentService;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.*;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RetailPayMentServiceImpl implements RetailPayMentService {

    @Autowired
    private RetailPaymentMapper paymentMapper;
    @Autowired
    private RetailPaymentItemMapper paymentItemMapper;
    @Autowired
    private RetailPaymentDetailMapper paymentDetailMapper;
    @Autowired
    private RetailPaymentPaydtlMapper paymentPaydtlMapper;
    @Autowired
    private RetailPaymentModifyRecordMapper paymentModifyRecordMapper;
    @Autowired
    private RetailDailySettleMapper settleMapper;
    @Autowired
    private RetailDailySettleDetailMapper settleDetailMapper;
    @Autowired
    private RetailDailySettleModifyRecordMapper settleModifyRecordMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private PayeeOpeningShiftMapper shiftMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private FinanceComponent financeComponent;


    @Override
    public Page<RetailPayMentResponseVO> getPayMentByParam(RetailPayMentRageParamVO paramVO, UserVO loginUser) throws Exception {
        if(paramVO.getPageNo() == null || paramVO.getPageSize() == null){
            throw new BusinessException("翻页参数不能为空");
        }
        if(paramVO.getPageNo() <= 0 || paramVO.getPageSize() <= 0){
            throw new BusinessException("翻页参数必须大于0");
        }
        if("paymentDate".equals(paramVO.getOrderName())){
            paramVO.setOrderName("payment_date");
        }else if("code".equals(paramVO.getOrderName())){
            paramVO.setOrderName("code");
        }else {
            paramVO.setOrderName(null);
        }
        paramVO.setEnterpriseId(loginUser.getEnterpriseId());
        paramVO.setParentId(loginUser.getParentId());
        List<RetailPaymentVO> paymentCountVOS = paymentMapper.selectCountByPageParam(paramVO);
        int count = paymentCountVOS.size();
        RetailPayMentResponseVO reponseVO = new RetailPayMentResponseVO();
        for (RetailPaymentVO paymentVO : paymentCountVOS){
            if(paymentVO.getStatus().equals(DailySettleStatusType.CHARGEGAINSTED.getCode())){
                continue;
            }
            reponseVO.setBankAmountTotal(reponseVO.getBankAmountTotal().add(paymentVO.getBankAmountTotal()));
            reponseVO.setCashTotal(reponseVO.getCashTotal().add(paymentVO.getCashTotal()));
            reponseVO.setRealCashTotal(reponseVO.getRealCashTotal().add(paymentVO.getRealCashTotal()));
            reponseVO.setDiffCashTotal(reponseVO.getDiffCashTotal().add(paymentVO.getDiffCashTotal()));
            reponseVO.setOtherAmountTotal(reponseVO.getOtherAmountTotal().add(paymentVO.getOtherAmountTotal()));
        }
        Page<RetailPayMentResponseVO> page = new Page<>(paramVO.getPageNo(),paramVO.getPageSize());
        List<RetailPaymentVO> retailPaymentVOS = paymentMapper.selectByPageParam(paramVO);
        for(RetailPaymentVO paymentVO : retailPaymentVOS){
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(paymentVO.getEnterpriseId());
            paymentVO.setEnterpriseCode(enterprise.getCode());
            paymentVO.setEnterpriseName(enterprise.getName());
            if(DailySettleStatusType.PAYED.getCode() == paymentVO.getStatus()){
                paymentVO.setUpdateFlag(true);
                paymentVO.setChargrAgainstFlag(true);
            }else {
                paymentVO.setUpdateFlag(false);
                paymentVO.setChargrAgainstFlag(false);
            }
            paymentVO.setStatusName(DailySettleStatusType.getValue(paymentVO.getStatus()));
        }
        reponseVO.setRetailPaymentVOS(retailPaymentVOS);
        page.setResult(reponseVO);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public RetailPaymentVO getPayMentDetail(Long id, UserVO loginUser) throws Exception {
        RetailPaymentVO retailPaymentVO = new RetailPaymentVO();
        RetailPayment retailPayment = paymentMapper.selectByPrimaryKey(id);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(retailPayment,retailPaymentVO);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(retailPaymentVO.getEnterpriseId());
        retailPaymentVO.setEnterpriseCode(enterprise.getCode());
        retailPaymentVO.setEnterpriseName(enterprise.getName());
        List<RetailPaymentItemVO> paymentItemVOS = paymentItemMapper.selectRetailPaymentItemVOByPayMentId(id);
        for(RetailPaymentItemVO itemVO : paymentItemVOS){
            RetailDailySettle settle = settleMapper.selectByPrimaryKey(itemVO.getSettleId());
            itemVO.setSettleManId(settle.getSettleManId());
            itemVO.setSettleManCode(settle.getSettleManCode());
            itemVO.setSettleManName(settle.getSettleManName());
            itemVO.setSalePens(settle.getSalePensTotal());
            itemVO.setSaleAmount(settle.getSaleAmountTotal());
            itemVO.setReturnPens(settle.getReturnPensTotal());
            itemVO.setReturnAmount(settle.getReturnAmountTotal());
            List<RetailPaymentDetailVO> paymentDetailVOS = paymentDetailMapper.selectRetailPaymentDetailVOByItemId(itemVO.getId());
            itemVO.setRetailPaymentDetailVOList(paymentDetailVOS);
            for(RetailPaymentDetailVO paymentDetailVO : paymentDetailVOS){
                List<RetailPaymentPaydtlVO> paymentPaydtlVOS = paymentPaydtlMapper.selectRetailPaymentPaydtlVOByDetailId(paymentDetailVO.getId());
                paymentDetailVO.setRetailPaymentPaydtlVOS(paymentPaydtlVOS);
            }
        }
        retailPaymentVO.setRetailPaymentItemVOS(paymentItemVOS);
        return retailPaymentVO;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String updatePayMent(RetailPaymentVO paymentVO, UserVO loginUser) throws Exception {
        if(StringUtils.isEmpty(paymentVO.getUpdateReason())){
            throw new BusinessException("修改原因必填");
        }
        User user = userMapper.selectByPrimaryKey(paymentVO.getPaymentManId());
        if(user == null){
            throw new BusinessException("该缴款人员不存在!");
        }
        RetailPayment payment = new RetailPayment();
        RetailPayment oldPay = paymentMapper.selectByPrimaryKey(paymentVO.getId());
        if(!oldPay.getStatus().equals(DailySettleStatusType.PAYED.getCode())){
            throw new BusinessException("只能修改已缴款的单据!");
        }
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(paymentVO,payment);
        payment.setPaymentManName(user.getName());
        payment.setPaymentManCode(user.getCode());
        payment.setPaymentManId(user.getId());
        UserEnterpriseUtils.setUserCreateOrModify(paymentVO,loginUser,false);
        paymentMapper.updateByPrimaryKeySelective(payment);
        Map<String,Object> oldMap = getFieldsMap(oldPay);
        Map<String,Object> newMap = getFieldsMap(payment);
        Map<String, String> fieldNames = fieldSkipMap();
        genModifyRecord(oldMap,newMap,fieldNames,loginUser,paymentVO);

        StringBuilder result = new StringBuilder("修改成功!").append("单号:").append(oldPay.getCode());
        return result.toString();
    }
    private void genModifyRecord(Map<String, Object> oldMap, Map<String, Object> newMap, Map<String, String> fieldNames, UserVO loginUser, RetailPaymentVO paymentVO) throws Exception{
        for (Map.Entry<String, String> entry : fieldNames.entrySet()){
            Object obj = oldMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if(newObj == null){
                continue;
            }
            if(obj == null){
                obj = "";
            }
            if(obj instanceof Date && newObj instanceof Date){
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                Date pre = (Date)obj;
                String preStr = df.format(pre);
                Date now = (Date)newObj;
                String nowStr = df.format(now);
                if (!preStr.equals(nowStr)){
                    genModifyRecord(preStr,nowStr,"saas_retail_payment",entry,paymentVO,loginUser);
                }
            }else if(!obj.equals(newObj)){
                genModifyRecord(obj.toString(),newObj.toString(),"saas_retail_payment",entry,paymentVO,loginUser);
            }
        }
    }

    private void genModifyRecord(String preStr, String nowStr, String tableName, Map.Entry<String, String> entry, RetailPaymentVO paymentVO, UserVO loginUser) throws Exception {
        RetailPaymentModifyRecord modifyRecord = new RetailPaymentModifyRecord();
        modifyRecord.setTableName(tableName);
        modifyRecord.setKeyId(paymentVO.getId());
        modifyRecord.setColumnEnName(entry.getKey());
        modifyRecord.setColumnChName(entry.getValue());
        modifyRecord.setOldContent(preStr);
        modifyRecord.setNewContent(nowStr);
        modifyRecord.setReason(paymentVO.getUpdateReason());
        RetailPayment payment = paymentMapper.selectByPrimaryKey(paymentVO.getId());
        modifyRecord.setEnterpriseId(payment.getEnterpriseId());
        modifyRecord.setParentId(payment.getParentId());
        UserEnterpriseUtils.setUserCreateOrModify(modifyRecord,loginUser,true);
        paymentModifyRecordMapper.insertSelective(modifyRecord);
    }

    private Map<String,String> fieldSkipMap() {
        Map<String, String> fieldNames = new HashMap();
        fieldNames.put("paymentDate", "缴款日期");
        fieldNames.put("paymentManName", "缴款人员");
        fieldNames.put("remark", "备注");
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

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String chargrAgainstPayMent(List<Long> ids, UserVO loginUser) throws Exception {
        if(ids.isEmpty()){
            throw new BusinessException("请至少选择一笔单据进行冲销!");
        }
        List<RetailPayment> payments = paymentMapper.selectByIds(ids);
        for(RetailPayment payment : payments){
            if(!payment.getStatus().equals(DailySettleStatusType.PAYED.getCode())){
                throw new BusinessException("只能冲销已缴款的单据!");
            }
            List<RetailPaymentItem> items = paymentItemMapper.selectByPayMentId(payment.getId());
            for(RetailPaymentItem item : items){
                //根据日结单id修改日结单状态为待缴款
                settleMapper.updateStatus(item.getSettleId(),DailySettleStatusType.WILLPAY.getCode());
                settleDetailMapper.updateStatus(item.getSettleId(),DailySettleStatusType.WILLPAY.getCode());
                //修改缴款清单状态为已冲销
                item.setStatus(DailySettleStatusType.CHARGEGAINSTED.getCode());
                paymentItemMapper.updateByPrimaryKeySelective(item);
                //修改缴款明细状态为已冲销
                paymentDetailMapper.updateStatus(item.getId(),DailySettleStatusType.CHARGEGAINSTED.getCode());
                //修改缴款支付方式状态为已冲销
                paymentPaydtlMapper.updateStatus(item.getId(),DailySettleStatusType.CHARGEGAINSTED.getCode());
                //调用中义的component方法

            }
            //修改缴款总单状态为已冲销
            payment.setStatus(DailySettleStatusType.CHARGEGAINSTED.getCode());
            paymentMapper.updateByPrimaryKeySelective(payment);
            //生成财务凭证
            financeComponent.retailPaymentToBalanceAndVoucherWhenSaveOrWriteOff(loginUser, payment, "writeOff");
        }
        return "冲销成功!";
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String savePayMent(RetailPaymentVO retailPaymentVO, UserVO loginUser) throws Exception {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(retailPaymentVO.getEnterpriseId());
        if(enterprise.getChainType()!=1 && loginUser.getParentId() == 0L){
            throw new BusinessException("选取的门店不是自营店,请重新选择门店进行缴款!");
        }
        if(enterprise.getParentId() == 0L){
            throw new BusinessException("选取的门店是总部,请重新选择门店进行缴款!");
        }

        if(retailPaymentVO.getPaymentDate() == null){
            throw new BusinessException("缴款日期必填!");
        }else {
            commonComponent.validationAccountingDate(DateUtils.DateToString(retailPaymentVO.getPaymentDate(), DateStyle.YYYY_MM_DD),loginUser);
//            String payDate = DateUtils.DateToString(retailPaymentVO.getPaymentDate(), DateStyle.YYYY_MM_DD);
//            List<RetailPayment> paymentList = paymentMapper.selectByPayDate(payDate,enterprise.getId());
//            if(!paymentList.isEmpty()){
//                throw new BusinessException("该日期:"+DateUtils.DateToString(retailPaymentVO.getPaymentDate(), DateStyle.YYYY_MM_DD_HH_MM_SS)+"已经缴款完毕,不能再缴款!");
//            }

        }
        if(retailPaymentVO.getPaymentManId() == null){
            throw new BusinessException("缴款人员必填!");
        }
        User user = userMapper.selectByPrimaryKey(retailPaymentVO.getPaymentManId());
        if(user == null){
            throw new BusinessException("查无此缴款人员!");
        }
        String code = orderCodeComponent.generate(OrderRule.RETAIL_PAYMENT.getCodePrefix(),enterprise.getId(),enterprise.getCode());
        RetailPayment payment = new RetailPayment();
        payment.setEnterpriseId(enterprise.getId());
        payment.setParentId(enterprise.getParentId());
        payment.setOrderType(OrderRule.RETAIL_PAYMENT.getType());
        payment.setPaymentDate(retailPaymentVO.getPaymentDate());
        payment.setCode(code);
        payment.setPaymentManId(user.getId());
        payment.setPaymentManCode(user.getCode());
        payment.setPaymentManName(user.getName());
        payment.setStatus(DailySettleStatusType.PAYED.getCode());
        payment.setRemark(retailPaymentVO.getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(payment,loginUser,true);

        BigDecimal cashTotal = BigDecimal.ZERO;
        BigDecimal realCashTotal = BigDecimal.ZERO;
        BigDecimal diffCashTotal = BigDecimal.ZERO;
        BigDecimal bankAmountTotal = BigDecimal.ZERO;
        BigDecimal otherAmountTotal = BigDecimal.ZERO;
        //先将总单的金额置为0保存,待后续单据生成时更改金额;
        payment.setCashTotal(cashTotal);
        payment.setRealCashTotal(realCashTotal);
        payment.setDiffCashTotal(diffCashTotal);
        payment.setBankAmountTotal(bankAmountTotal);
        payment.setOtherAmountTotal(otherAmountTotal);
        //保存总单
        paymentMapper.insertSelective(payment);

        for(RetailPaymentItemVO itemVO : retailPaymentVO.getRetailPaymentItemVOS()){
            RetailPaymentItem item = new RetailPaymentItem();
            item.setEnterpriseId(enterprise.getId());
            item.setParentId(enterprise.getParentId());
            RetailDailySettle settle = settleMapper.selectByPrimaryKey(itemVO.getSettleId());
            if(settle == null){
                throw new BusinessException("查无此日结单据:"+itemVO.getSettleCode());
            }
            item.setDocId(payment.getId());
            item.setSettleId(settle.getId());
            item.setSettleCode(settle.getCode());
            item.setSettleDate(settle.getSettleDate());
            item.setSettleOrderType(settle.getOrderType());
            item.setSalePens(settle.getSalePensTotal());
            item.setSaleAmount(settle.getSaleAmountTotal());
            item.setReturnPens(settle.getReturnPensTotal());
            item.setReturnAmount(settle.getReturnAmountTotal());
            item.setAmount(settle.getAmountTotal());
            item.setLineNum(itemVO.getLineNum());
            item.setStatus(DailySettleStatusType.PAYED.getCode());
            item.setRemark(item.getRemark());
            UserEnterpriseUtils.setUserCreateOrModify(item,loginUser,true);
            //保存清单
            paymentItemMapper.insertSelective(item);
            //修改日结单的状态
            settleMapper.updateStatus(settle.getId(),DailySettleStatusType.PAYED.getCode());
            settleDetailMapper.updateStatus(settle.getId(),DailySettleStatusType.PAYED.getCode());

            for(RetailPaymentDetailVO detailVO : itemVO.getRetailPaymentDetailVOList()){
                RetailPaymentDetail detail = new RetailPaymentDetail();
                detail.setEnterpriseId(enterprise.getId());
                detail.setParentId(enterprise.getParentId());
                PayeeOpeningShift shift = shiftMapper.selectByPrimaryKey(detailVO.getShiftId());
                if(shift == null){
                    throw new BusinessException("查无此交班单据:"+detailVO.getShiftId());
                }
                detail.setItemId(item.getId());
                detail.setDocId(payment.getId());
                detail.setShiftId(shift.getId());
                detail.setShiftManId(shift.getCreaterId());
                detail.setShiftManCode(shift.getCreaterCode());
                detail.setShiftManName(shift.getCreaterName());
                detail.setPayableCash(shift.getPayableCash());
                detail.setCash(shift.getCash());
                detail.setDiffCash(shift.getPayableCash().subtract(shift.getCash()));
                cashTotal = cashTotal.add(detail.getPayableCash());
                realCashTotal = realCashTotal.add(detail.getCash());
                diffCashTotal = diffCashTotal.add(detail.getDiffCash());
                detail.setLineNum(detailVO.getLineNum());
                detail.setStatus(DailySettleStatusType.PAYED.getCode());
                detail.setRemark(detailVO.getRemark());
                UserEnterpriseUtils.setUserCreateOrModify(detail,loginUser,true);
                //保存缴款明细
                paymentDetailMapper.insertSelective(detail);

                for (RetailPaymentPaydtlVO paydtlVO : detailVO.getRetailPaymentPaydtlVOS()){
                    RetailPaymentPaydtl paydtl = new RetailPaymentPaydtl();
                    paydtl.setDocId(payment.getId());
                    paydtl.setItemId(item.getId());
                    paydtl.setDtlId(detail.getId());
                    paydtl.setEnterpriseId(enterprise.getId());
                    paydtl.setParentId(enterprise.getParentId());
                    paydtl.setPayTypeId(paydtlVO.getPayTypeId());
                    paydtl.setPayTypeName(paydtlVO.getPayTypeName());
                    paydtl.setPayAmount(paydtlVO.getPayAmount());
                    paydtl.setStatus(DailySettleStatusType.PAYED.getCode());
                    paydtl.setRemark(paydtlVO.getRemark());
                    UserEnterpriseUtils.setUserCreateOrModify(paydtl,loginUser,true);
                    //保存支付明细
                    paymentPaydtlMapper.insertSelective(paydtl);
                    if("银行".equals(paydtlVO.getPayTypeName())){
                        bankAmountTotal = bankAmountTotal.add(paydtl.getPayAmount());
                    }else {
                        if(!"现金".equals(paydtlVO.getPayTypeName())){
                            otherAmountTotal = otherAmountTotal.add(paydtl.getPayAmount());
                        }
                    }
                }
            }
        }
        //修改总单金额
        payment.setCashTotal(cashTotal);
        payment.setRealCashTotal(realCashTotal);
        payment.setDiffCashTotal(diffCashTotal);
        payment.setBankAmountTotal(bankAmountTotal);
        payment.setOtherAmountTotal(otherAmountTotal);
        paymentMapper.updateByPrimaryKeySelective(payment);
        //生成财务凭证
        financeComponent.retailPaymentToBalanceAndVoucherWhenSaveOrWriteOff(loginUser, payment,"save");
        return new StringBuilder("保存成功!单号:").append(code).append("!").toString();
    }

    @Override
    public Page<List<RetailPaymentModifyRecordVO>> getPayMentModifyRecord(Integer pageNo, Integer pageSize, Long id, UserVO loginUser) throws Exception {
        if(pageNo == null || pageSize == null){
            throw new BusinessException("翻页参数不能为空");
        }
        if(pageNo <= 0 || pageSize <= 0){
            throw new BusinessException("翻页参数必须大于0");
        }
        Page<List<RetailPaymentModifyRecordVO>> page = new Page<>(pageNo,pageSize);
        List<RetailPaymentModifyRecordVO> recordVOS = paymentModifyRecordMapper.selectByPageParam((pageNo-1)*pageSize,pageSize,id);
        Integer count = paymentModifyRecordMapper.selectCountByKeyId(id);
        page.setResult(recordVOS);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, Long id, UserVO loginUser) throws Exception {
        RetailPaymentExportVO exportVO = getPayMentPrint(loginUser,id);
        ExcelWriter writer = new ExcelWriter() {
            @Override
            public void generate() throws Exception {
                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                short cellStrIndex = styleMap.get("cell_string").getIndex();
                //标题行
                this.insertRow(kk++);
                this.createCell(0, loginUser.getEnterpriseName(),cellStrIndex);
                this.endRow();
                //表名
                this.insertRow(kk++);
                this.createCell(0,"零售缴款",cellStrIndex);
                this.endRow();
                //第一行
                this.insertRow(kk++);
                this.createCell(0, "门店编码:",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(exportVO.getEnterpriseCode()),cellStrIndex);
                this.createCell(2,"门店名称:",cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(exportVO.getEnterpriseName()),cellStrIndex);
                this.createCell(4,"缴款日期:",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(exportVO.getPaymentDate()),cellStrIndex);
                this.createCell(6,"缴款人员:",cellStrIndex);
                this.createCell(7,StringUtil.transferTrimStr(exportVO.getPaymentManName()),cellStrIndex);
                this.createCell(8,"单号:",cellStrIndex);
                this.createCell(9,StringUtil.transferTrimStr(exportVO.getCode()),cellStrIndex);
                this.createCell(10,"备注:",cellStrIndex);
                this.createCell(11,StringUtil.transferTrimStr(exportVO.getRemark()),cellStrIndex);
                this.endRow();
                //明细列名
                this.insertRow(kk++);
                int row = 0;
                this.createCell(row++, "序号",cellStrIndex);
                this.createCell(row++, "日结日期",cellStrIndex);
                this.createCell(row++, "日结单号",cellStrIndex);
                this.createCell(row++, "日结人员",cellStrIndex);
                this.createCell(row++, "交班人员",cellStrIndex);
                this.createCell(row++, "应收现金",cellStrIndex);
                this.createCell(row++, "实收现金",cellStrIndex);
                this.createCell(row++, "现金差异",cellStrIndex);
                //取收款方式最多的一条
                List<RetailPaymentDetailExportVO> detailExportVOS = exportVO.getRetailPaymentDetailExportVOS();
                List<RetailPaymentPaydtlVO> paydtlVOS = new ArrayList<>();
                for(int i = 0; i < detailExportVOS.size(); i++){
                    RetailPaymentDetailExportVO detailExportVO = detailExportVOS.get(i);
                    if(i == 0){
                        paydtlVOS = detailExportVO.getRetailPaymentPaydtlVOS();
                    }else {
                        if(detailExportVO.getRetailPaymentPaydtlVOS().size()>paydtlVOS.size()){
                            paydtlVOS = detailExportVO.getRetailPaymentPaydtlVOS();
                        }
                    }
                }
                int autoRowStart = row;
                //按照顺序记录 收款方式跟收款金额,没有的金额为0,所以初始化所有金额为0
                Map<String,BigDecimal> amountMap = new LinkedHashMap<>();
                for(RetailPaymentPaydtlVO paydtlVO : paydtlVOS){
                    this.createCell(row++, paydtlVO.getPayTypeName(),cellStrIndex);
                    amountMap.put(paydtlVO.getPayTypeName(),BigDecimal.ZERO.setScale(2));
                }
                int autoRowEnd = row;
                this.createCell(row++, "应收金额",cellStrIndex);
                this.createCell(row++, "备注",cellStrIndex);
                this.endRow();
                int index = 1;//序号
                List<BigDecimal> totalAmont = new ArrayList<>();
                for(int cloum = 0; cloum< detailExportVOS.size(); cloum++){
                    RetailPaymentDetailExportVO detailExportVO = detailExportVOS.get(cloum);
                    for(int i = 0; i<detailExportVO.getRetailPaymentPaydtlVOS().size();i++){
                        RetailPaymentPaydtlVO paydtlVO = detailExportVO.getRetailPaymentPaydtlVOS().get(i);
                        amountMap.replace(paydtlVO.getPayTypeName(),paydtlVO.getPayAmount());
                    }

                    this.insertRow(kk++);
                    int dtlRow = 0;
                    this.createCell(dtlRow++, index++,cellStrIndex);
                    this.createCell(dtlRow++, StringUtil.transferTrimStr(DateUtils.DateToString(detailExportVO.getSettleDate(),DateUtils.FMT_DATE)),cellStrIndex);
                    this.createCell(dtlRow++, StringUtil.transferTrimStr(detailExportVO.getSettleCode()),cellStrIndex);
                    this.createCell(dtlRow++, StringUtil.transferTrimStr(detailExportVO.getSettleManName()),cellStrIndex);
                    this.createCell(dtlRow++, StringUtil.transferTrimStr(detailExportVO.getShiftManName()),cellStrIndex);
                    this.createCell(dtlRow++, StringUtil.transferTrimStr(detailExportVO.getPayableCash()),cellStrIndex);
                    this.createCell(dtlRow++, StringUtil.transferTrimStr(detailExportVO.getCash()),cellStrIndex);
                    this.createCell(dtlRow++, StringUtil.transferTrimStr(detailExportVO.getDiffCash()),cellStrIndex);
                    if(cloum == 0){
                        totalAmont.add(detailExportVO.getPayableCash());
                        totalAmont.add(detailExportVO.getCash());
                        totalAmont.add(detailExportVO.getDiffCash());
                    }else {
                        totalAmont.set(0,totalAmont.get(0).add(detailExportVO.getPayableCash()));
                        totalAmont.set(1,totalAmont.get(1).add(detailExportVO.getCash()));
                        totalAmont.set(2,totalAmont.get(2).add(detailExportVO.getDiffCash()));
                    }
                    Iterator<Map.Entry<String,BigDecimal>> it = amountMap.entrySet().iterator();
                    //下方循环统计合计时,从下标3开始
                    int mark = 2;
                    //计算行合计
                    BigDecimal amount = BigDecimal.ZERO;
                    while (it.hasNext()){
                        Map.Entry<String,BigDecimal> entry = it.next();
                        this.createCell(dtlRow++, StringUtil.transferTrimStr(entry.getValue()), cellStrIndex);
                        if(cloum == 0){
                            totalAmont.add(entry.getValue());
                            amount = amount.add(entry.getValue());
                        }else {
                            mark++;
                            totalAmont.set(mark, totalAmont.get(mark).add(entry.getValue()));
                            amount = amount.add(entry.getValue());
                        }
                    }
                    this.createCell(dtlRow++, StringUtil.transferTrimStr(amount),cellStrIndex);
                    if(cloum == 0){
                        totalAmont.add(amount);
                    }else {
                        totalAmont.set(++mark,totalAmont.get(mark).add(amount));
                    }
                    this.endRow();
                }
                //合计
                this.insertRow(kk++);
                this.createCell(0, "合计:",cellStrIndex);
                for(int i = 5;i<totalAmont.size()+5;i++){
                    this.createCell(i, StringUtil.transferTrimStr(totalAmont.get(i-5)),cellStrIndex);
                }
                this.endRow();
                this.endSheet();
                this.beginMergerCell();
                //标题列合并
                this.setMergeCell(0,0,0,row);
                //第二行合并
                this.setMergeCell(1,0,1,row);
                this.endMergerCell();
                this.endWorkSheet();
            }
        };
        writer.process(output);
    }

    @Override
    public void exportExcelModifyRecord(OutputStream output, Long id, UserVO loginUser) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("updateTime","修改时间");
        map.put("modifierName","修改人员");
        map.put("reason","修改原因");
        map.put("columnChName","修改项目");
        map.put("oldContent","原内容");
        map.put("newContent","新内容");
        List<String> titleSecond = new ArrayList<>();
        List<String> name = new ArrayList<>();
        RetailPayment payment = paymentMapper.selectByPrimaryKey(id);
        List<RetailPaymentModifyRecord> records = paymentModifyRecordMapper.selectByKeyId(id);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(payment.getEnterpriseId());
        name.add(enterprise.getName());
        name.add("修改记录");
        purchaseGeneralComponent.commExcelExport(output,map,records,name,titleSecond,"",true,new ArrayList<>());
    }

    @Override
    public RetailPaymentExportVO getPayMentPrint(UserVO loginUser, Long id) throws Exception {
        RetailPaymentExportVO exportVO = new RetailPaymentExportVO();
        RetailPayment payment = paymentMapper.selectByPrimaryKey(id);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(payment,exportVO);
        exportVO.setTitleFirstRow(loginUser.getEnterpriseName());
        exportVO.setTitleSecondRow("零售缴款");
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(payment.getEnterpriseId());
        exportVO.setEnterpriseCode(enterprise.getCode());
        exportVO.setEnterpriseName(enterprise.getName());
        List<RetailPaymentDetailExportVO> exportDetailVOS = new ArrayList<>();
        List<RetailPaymentItem> items = paymentItemMapper.selectByPayMentId(id);
        for(RetailPaymentItem item : items){
            List<RetailPaymentDetail> details = paymentDetailMapper.selectByItemId(item.getId());
            for(RetailPaymentDetail detail : details){
                RetailPaymentDetailExportVO exportDetailVO = new RetailPaymentDetailExportVO();
                RetailDailySettle settle = settleMapper.selectByPrimaryKey(item.getSettleId());
                exportDetailVO.setId(item.getId());
                exportDetailVO.setDocId(id);
                exportDetailVO.setSettleId(item.getSettleId());
                exportDetailVO.setSettleCode(item.getSettleCode());
                exportDetailVO.setSettleDate(item.getSettleDate());
                exportDetailVO.setSettleManId(settle.getSettleManId());
                exportDetailVO.setSettleManCode(settle.getSettleManCode());
                exportDetailVO.setSettleManName(settle.getSettleManName());
                exportDetailVO.setSettleOrderType(item.getSettleOrderType());
                exportDetailVO.setShiftManId(detail.getShiftManId());
                exportDetailVO.setShiftManCode(detail.getShiftManCode());
                exportDetailVO.setShiftManName(detail.getShiftManName());
                exportDetailVO.setPayableCash(detail.getPayableCash());
                exportDetailVO.setCash(detail.getCash());
                exportDetailVO.setDiffCash(detail.getDiffCash());
                exportDetailVO.setAmount(item.getAmount());
                exportVO.setRemark(detail.getRemark());
                List<RetailPaymentPaydtlVO> paydtlVOS = paymentPaydtlMapper.selectRetailPaymentPaydtlVOByDetailId(detail.getId());
                exportDetailVO.setRetailPaymentPaydtlVOS(paydtlVOS);
                exportDetailVOS.add(exportDetailVO);
            }
        }
        exportVO.setRetailPaymentDetailExportVOS(exportDetailVOS);
        return exportVO;
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO) throws Exception {
        return null;
    }

    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<RetailPaymentVO> draftCacheVO) throws Exception {
        return null;
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue) throws Exception {

    }

    @Override
    public List<RetailPaymentItemVO> getWillPayMentByStore(Long storeId, UserVO loginUser) throws Exception {
        if(loginUser.getParentId() == 0 && storeId == null){
            throw new BusinessException("当前登录人员为总部人员,门店信息必传!");
        }
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(storeId==null?loginUser.getEnterpriseId():storeId);
        if(enterprise.getChainType()!=1 && loginUser.getParentId() == 0L){
            throw new BusinessException("选取的门店不是自营店,请重新选择门店进行日结!");
        }
        List<RetailDailySettle> settles = settleMapper.selectByEnterpriseIdByStatus(enterprise.getId(),DailySettleStatusType.WILLPAY.getCode());
        List<RetailPaymentItemVO> itemVOS = new ArrayList<>();
        for(RetailDailySettle settle : settles){
            RetailPaymentItemVO itemVO = new RetailPaymentItemVO();
            itemVO.setSettleId(settle.getId());
            itemVO.setSettleCode(settle.getCode());
            itemVO.setSettleDate(settle.getSettleDate());
            itemVO.setSettleOrderType(settle.getOrderType());
            itemVO.setSettleManId(settle.getSettleManId());
            itemVO.setSettleManCode(settle.getSettleManCode());
            itemVO.setSettleManName(settle.getSettleManName());
            itemVO.setAmount(settle.getAmountTotal());
            itemVO.setStatus(settle.getStatus());
            itemVO.setSalePens(settle.getSalePensTotal());
            itemVO.setSaleAmount(settle.getSaleAmountTotal());
            itemVO.setReturnPens(settle.getReturnPensTotal());
            itemVO.setReturnAmount(settle.getReturnAmountTotal());
            List<RetailPaymentDetailVO> detailVOS = new ArrayList<>();
            List<RetailDailySettleDetail> settleDetails = settleDetailMapper.selectBySettleId(settle.getId());
            List<Long> shiftIds = new ArrayList<>();
            for(RetailDailySettleDetail settleDetail : settleDetails){
                shiftIds.add(settleDetail.getShiftId());
            }
            List<PayeeOpeningShift> shifts = shiftMapper.selectByIds(shiftIds);
            for(PayeeOpeningShift shift : shifts){
                RetailPaymentDetailVO detailVO = new RetailPaymentDetailVO();
                detailVO.setShiftId(shift.getId());
                detailVO.setShiftManId(shift.getCreaterId());
                detailVO.setShiftManCode(shift.getCreaterCode());
                detailVO.setShiftManName(shift.getCreaterName());
                detailVO.setPayableCash(shift.getPayableCash());
                detailVO.setCash(shift.getCash());
                detailVO.setDiffCash(shift.getPayableCash().subtract(shift.getCash()));
                List<RetailPaymentPaydtlVO> paymentPaydtlVOS = paymentPaydtlMapper.selectByShiftId(shift.getId());
                //如果收款方式是现金 不展示
                Iterator<RetailPaymentPaydtlVO> it = paymentPaydtlVOS.iterator();
                while (it.hasNext()){
                    RetailPaymentPaydtlVO paydtlVO = it.next();
                    if("现金".equals(paydtlVO.getPayTypeName())){
                        it.remove();
                    }
                }
                detailVO.setRetailPaymentPaydtlVOS(paymentPaydtlVOS);
                detailVOS.add(detailVO);
            }
            itemVO.setRetailPaymentDetailVOList(detailVOS);
            itemVOS.add(itemVO);
        }
        return itemVOS;
    }
}
