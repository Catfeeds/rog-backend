package com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrEnterpriseVO;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao.RetailDailySettleDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao.RetailDailySettleMapper;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.dao.RetailDailySettleModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettle;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettleDetail;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettleModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.service.RetailDailySettleService;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.vo.*;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DailySettleStatusType;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.pos.sale.constant.ExcessiveSale;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RetailDailySettleServiceImpl implements RetailDailySettleService{

    @Autowired
    private RetailDailySettleMapper settleMapper;
    @Autowired
    private RetailDailySettleDetailMapper settleDetailMapper;
    @Autowired
    private RetailDailySettleModifyRecordMapper settleModifyRecordMapper;
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private SaleDetailMapper saleDetailMapper;
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
    public Page<RetailDailySettleReponseVO> getDailySettleByParam(DailtSettleRequestPageParamVO paramVO, UserVO loginUser) throws Exception {
        if(paramVO.getPageNo() == null || paramVO.getPageSize() == null){
            throw new BusinessException("翻页参数不能为空");
        }
        if(paramVO.getPageNo() <= 0 || paramVO.getPageSize() <= 0){
            throw new BusinessException("翻页参数必须大于0");
        }
        if("settleDate".equals(paramVO.getOrderName())){
            paramVO.setOrderName("settle_date");
        }else if("code".equals(paramVO.getOrderName())){
            paramVO.setOrderName("code");
        }else {
            paramVO.setOrderName(null);
        }
        paramVO.setEnterpriseId(loginUser.getEnterpriseId());
        paramVO.setParentId(loginUser.getParentId());
        List<RetailDailySettleVO> settleCountVOS = settleMapper.selectCountByPageParam(paramVO);
        int count = settleCountVOS.size();
        RetailDailySettleReponseVO reponseVO = new RetailDailySettleReponseVO();
        for(RetailDailySettleVO settleVO : settleCountVOS){
            if(settleVO.getStatus().equals(DailySettleStatusType.CHARGEGAINSTED.getCode())){
                continue;
            }
            reponseVO.setSaleAmountTotal(reponseVO.getSaleAmountTotal().add(settleVO.getAmountTotal()));
            reponseVO.setSalePensTotal(reponseVO.getSalePensTotal()+settleVO.getSalePensTotal());
            reponseVO.setReturnPensTotal(reponseVO.getReturnPensTotal()+settleVO.getReturnPensTotal());
            reponseVO.setReturnAmountTotal(reponseVO.getReturnAmountTotal().add(settleVO.getReturnAmountTotal()));
            reponseVO.setAmountTotal(reponseVO.getAmountTotal().add(settleVO.getAmountTotal()));
        }
        Page<RetailDailySettleReponseVO> page = new Page<>(paramVO.getPageNo(),paramVO.getPageSize());
        List<RetailDailySettleVO> dailySettleVOS = settleMapper.selectByPageParam(paramVO);
        for(RetailDailySettleVO settleVO : dailySettleVOS){
            if(DailySettleStatusType.WILLPAY.getCode() == settleVO.getStatus()){
                settleVO.setUpdateFlag(true);
                settleVO.setChargrAgainstFlag(true);
            }else {
                settleVO.setUpdateFlag(false);
                settleVO.setChargrAgainstFlag(false);
            }
            settleVO.setStatusName(DailySettleStatusType.getValue(settleVO.getStatus()));
        }
        reponseVO.setDailySettleVOS(dailySettleVOS);
        page.setResult(reponseVO);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public RetailDailySettleVO getDailySettleDetail(Long settleId, UserVO loginUser) throws Exception {
        RetailDailySettleVO retailDailySettleVO = new RetailDailySettleVO();
        RetailDailySettle retailDailySettle = settleMapper.selectByPrimaryKey(settleId);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(retailDailySettle,retailDailySettleVO);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(retailDailySettleVO.getEnterpriseId());
        retailDailySettleVO.setEnterpriseCode(enterprise.getCode());
        retailDailySettleVO.setEnterpriseName(enterprise.getName());
        List<RetailDailySettleDetail> retailDailySettleDetailS = settleDetailMapper.selectBySettleId(settleId);
        List<RetailDailySettleDetailVO> retailDailySettleDetailVOS = new ArrayList<>();
        for(RetailDailySettleDetail detail : retailDailySettleDetailS){
            RetailDailySettleDetailVO detailVO = new RetailDailySettleDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(detail,detailVO);
            retailDailySettleDetailVOS.add(detailVO);
        }
        retailDailySettleVO.setRetailDailySettleDetailVOS(retailDailySettleDetailVOS);
        return retailDailySettleVO;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String updateDailySettle(RetailDailySettleVO dailySettleVO, UserVO loginUser) throws Exception {
        if(StringUtils.isEmpty(dailySettleVO.getUpdateReason())){
            throw new BusinessException("修改原因必填");
        }
        User user = userMapper.selectByPrimaryKey(dailySettleVO.getSettleManId());
        if(user == null){
            throw new BusinessException("该日结人员不存在!");
        }
        RetailDailySettle settle = new RetailDailySettle();
        RetailDailySettle oldSettle = settleMapper.selectByPrimaryKey(dailySettleVO.getId());
        if(!oldSettle.getStatus().equals(DailySettleStatusType.WILLPAY.getCode())){
            throw new BusinessException("不能修改已缴款或者已冲销的单据!");
        }
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(dailySettleVO,settle);
        settle.setSettleManId(user.getId());
        settle.setSettleManName(user.getName());
        settle.setSettleManCode(user.getCode());
        UserEnterpriseUtils.setUserCreateOrModify(settle,loginUser,false);
        settleMapper.updateByPrimaryKeySelective(settle);
        Map<String,Object> oldMap = getFieldsMap(oldSettle);
        Map<String,Object> newMap = getFieldsMap(settle);
        Map<String, String> fieldNames = fieldSkipMap();
        genModifyRecord(oldMap,newMap,fieldNames,loginUser,dailySettleVO);
        StringBuilder result = new StringBuilder("修改成功!").append("单号:").append(oldSettle.getCode());
        return result.toString();
    }

    private void genModifyRecord(Map<String, Object> oldMap, Map<String, Object> newMap, Map<String, String> fieldNames, UserVO loginUser, RetailDailySettleVO dailySettleVO) throws Exception{
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
                    genModifyRecord(preStr,nowStr,"saas_retail_daily_settle",entry,dailySettleVO,loginUser);
                }
            }else if(!obj.equals(newObj)){
                genModifyRecord(obj.toString(),newObj.toString(),"saas_retail_daily_settle",entry,dailySettleVO,loginUser);
            }
        }
    }

    private void genModifyRecord(String preStr, String nowStr, String tableName, Map.Entry<String, String> entry, RetailDailySettleVO dailySettleVO, UserVO loginUser) throws Exception {
        RetailDailySettleModifyRecord modifyRecord = new RetailDailySettleModifyRecord();
        modifyRecord.setTableName(tableName);
        modifyRecord.setKeyId(dailySettleVO.getId());
        modifyRecord.setColumnEnName(entry.getKey());
        modifyRecord.setColumnChName(entry.getValue());
        modifyRecord.setOldContent(preStr);
        modifyRecord.setNewContent(nowStr);
        modifyRecord.setReason(dailySettleVO.getUpdateReason());
        RetailDailySettle settle = settleMapper.selectByPrimaryKey(dailySettleVO.getId());
        modifyRecord.setEnterpriseId(settle.getEnterpriseId());
        modifyRecord.setParentId(settle.getParentId());
        UserEnterpriseUtils.setUserCreateOrModify(modifyRecord,loginUser,true);
        settleModifyRecordMapper.insertSelective(modifyRecord);
    }

    private Map<String,String> fieldSkipMap() {
        Map<String, String> fieldNames = new HashMap();
        fieldNames.put("settleDate", "日结日期");
        fieldNames.put("settleManName", "日结人员");
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
    public String chargrAgainstDailySettle(List<Long> settleId, UserVO loginUser) throws Exception {
        if(settleId.isEmpty()){
            throw new BusinessException("请至少选择一笔单据进行冲销!");
        }
        List<RetailDailySettle> dailySettleList = settleMapper.selectByIds(settleId);
        for(RetailDailySettle dailySettle : dailySettleList){
            if(!dailySettle.getStatus().equals(DailySettleStatusType.WILLPAY.getCode())){
                throw new BusinessException("不能冲销已缴款或者已冲销的单据!");
            }
            List<RetailDailySettleDetail> dailySettleDetailList = settleDetailMapper.selectBySettleId(dailySettle.getId());
            List<SaleDetail> saleDetailList = new ArrayList<>();
            for(RetailDailySettleDetail settleDetail : dailySettleDetailList){
                //根据细单的交班单id修改交班单的状态为未日结 daily_settle_flag日结标识（0-未日结；1-已日结）
                shiftMapper.updateDailySettleFlagByShiftId(settleDetail.getShiftId(),0);
                //修改日结明细状态为已冲销
                settleDetail.setStatus(DailySettleStatusType.CHARGEGAINSTED.getCode());
                settleDetailMapper.updateByPrimaryKeySelective(settleDetail);
                //查询对应的销售明细
                List<SaleDetail> saleDetails = saleDetailMapper.selectByShiftId(settleDetail.getShiftId());
                saleDetailList.addAll(saleDetails);

            }
            //修改日结状态为已冲销
            dailySettle.setStatus(DailySettleStatusType.CHARGEGAINSTED.getCode());
            settleMapper.updateByPrimaryKeySelective(dailySettle);
            //生成财务凭证
            financeComponent.retailDailySettleToBalanceAndVoucherWhenSaveOrWriteOff(loginUser,dailySettle,saleDetailList,"writeOff");
        }
        return "冲销成功!";
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String saveDailySettle(RetailDailySettleVO retailDailySettleVO, UserVO loginUser) throws Exception {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(retailDailySettleVO.getEnterpriseId());
        if(enterprise.getChainType()!=1 && loginUser.getParentId() == 0L){
            throw new BusinessException("选取的门店不是自营店,请重新选择门店进行日结!");
        }
        if(enterprise.getParentId() == 0L){
            throw new BusinessException("选取的门店是总部,请重新选择门店进行日结!");
        }
        if(retailDailySettleVO.getSettleDate() == null){
            throw new BusinessException("日结日期必填!");
        }else {
            commonComponent.validationAccountingDate(DateUtils.DateToString(retailDailySettleVO.getSettleDate(), DateStyle.YYYY_MM_DD),loginUser);
//            String settleDate = DateUtils.DateToString(retailDailySettleVO.getSettleDate(), DateStyle.YYYY_MM_DD);
//            List<RetailDailySettle> settleList = settleMapper.selectBySettleDate(settleDate,enterprise.getId());
//            if(!settleList.isEmpty()){
//                throw new BusinessException("该日期:"+DateUtils.DateToString(retailDailySettleVO.getSettleDate(), DateStyle.YYYY_MM_DD_HH_MM_SS)+"已经日结完毕,不能再日结!");
//            }
        }
        if(retailDailySettleVO.getSettleManId() == null){
            throw new BusinessException("日结人员必填!");
        }
        User user = userMapper.selectByPrimaryKey(retailDailySettleVO.getSettleManId());
        if(user == null){
            throw new BusinessException("查无此日结人员!");
        }
        String code = orderCodeComponent.generate(OrderRule.RETAIL_DAILY_SETTLE.getCodePrefix(),enterprise.getId(),enterprise.getCode());
        RetailDailySettle settle = new RetailDailySettle();
        settle.setCode(code);
        settle.setEnterpriseId(enterprise.getId());
        settle.setParentId(enterprise.getParentId());
        settle.setOrderType(OrderRule.RETAIL_DAILY_SETTLE.getType());
        settle.setSettleDate(retailDailySettleVO.getSettleDate());
        settle.setSettleManId(user.getId());
        settle.setSettleManCode(user.getCode());
        settle.setSettleManName(user.getName());
        settle.setStatus(DailySettleStatusType.WILLPAY.getCode());
        settle.setRemark(retailDailySettleVO.getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(settle,loginUser,true);

        Integer salePensTotal = 0;
        BigDecimal saleAmountTotal = BigDecimal.ZERO;
        Integer returnPensTotal = 0;
        BigDecimal returnAmountTotal = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        List<RetailDailySettleDetail> settleDetails = new ArrayList<>();
        for(RetailDailySettleDetailVO settleDetailVO : retailDailySettleVO.getRetailDailySettleDetailVOS()){
            RetailDailySettleDetail settleDetail = new RetailDailySettleDetail();
            PayeeOpeningShift shift = shiftMapper.selectByPrimaryKey(settleDetailVO.getShiftId());
            //检查是否有超量销售,若有超量销售 给出提示 不能继续做单据
            checkExcessiveSale(settleDetailVO,shift);
            settleDetail.setShiftId(shift.getId());
            settleDetail.setShiftManId(shift.getCreaterId());
            settleDetail.setShiftManCode(shift.getCreaterCode());
            settleDetail.setShiftManName(shift.getCreaterName());
            settleDetail.setShiftStartDate(shift.getOpeningTime());
            settleDetail.setShiftEndDate(shift.getShiftTime());
            settleDetail.setSalePens(shift.getSalePens());
            settleDetail.setSaleAmount(shift.getSaleAmount());
            settleDetail.setReturnPens(shift.getReturnPens());
            settleDetail.setReturnAmount(shift.getReturnAmount());
            settleDetail.setAmount(shift.getSaleAmount().subtract(shift.getReturnAmount()));
            settleDetail.setEnterpriseId(shift.getEnterpriseId());
            settleDetail.setParentId(shift.getParentId());
            settleDetail.setRemark(settleDetailVO.getRemark());
            settleDetail.setStatus(DailySettleStatusType.WILLPAY.getCode());
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(settleDetailVO,settleDetail);
            UserEnterpriseUtils.setUserCreateOrModify(settleDetail,loginUser,true);
            settleDetails.add(settleDetail);

            //计算总单的合计
            salePensTotal += settleDetail.getSalePens();
            saleAmountTotal = saleAmountTotal.add(settleDetail.getSaleAmount());
            returnPensTotal += settleDetail.getReturnPens();
            returnAmountTotal = returnAmountTotal.add(settleDetail.getReturnAmount());
            amountTotal = amountTotal.add(settleDetail.getAmount());
        }

        //set总单的合计
        settle.setSalePensTotal(salePensTotal);
        settle.setSaleAmountTotal(saleAmountTotal);
        settle.setReturnPensTotal(returnPensTotal);
        settle.setReturnAmountTotal(returnAmountTotal);
        settle.setAmountTotal(amountTotal);
        settleMapper.insertSelective(settle);

        List<SaleDetail> saleDetailList = new ArrayList<>();
        for(RetailDailySettleDetail settleDetail : settleDetails){
            settleDetail.setSettleId(settle.getId());
            settleDetailMapper.insertSelective(settleDetail);
            shiftMapper.updateDailySettleFlagByShiftId(settleDetail.getShiftId(),1);
            //查询对应的销售明细
            List<SaleDetail> saleDetails = saleDetailMapper.selectByShiftId(settleDetail.getShiftId());
            saleDetailList.addAll(saleDetails);
        }
        //生成财务凭证
        financeComponent.retailDailySettleToBalanceAndVoucherWhenSaveOrWriteOff(loginUser,settle,saleDetailList,"save");
        return new StringBuilder("保存成功!").append("单号:").append(code).toString();
    }

    private void checkExcessiveSale(RetailDailySettleDetailVO settleDetailVO, PayeeOpeningShift shift) {
        List<Sale> sales = saleMapper.selectByShiftId(shift.getId());
        for(Sale sale : sales){
            if(ExcessiveSale.YES.getCode().equals(sale.getExcessiveSale())){
                throw new BusinessException(new StringBuilder("第").
                        append(settleDetailVO.getLineNum()).
                        append("行交班单存在超量销售的单据,请处理后再进行日结操作!").toString());
            }
        }
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue) throws Exception {

    }

    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<RetailDailySettleVO> draftCacheVO) throws Exception {
        return null;
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO) throws Exception {
        return null;
    }

    @Override
    public void exportExcel(OutputStream output, Long id, UserVO loginUser) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("shiftManName","交班人员");
        map.put("shiftStartDate","开班时间");
        map.put("shiftEndDate","交班时间");
        map.put("salePens","销售笔数");
        map.put("saleAmount","销售金额");
        map.put("returnPens","销退笔数");
        map.put("returnAmount","销退金额");
        map.put("amount","应收金额");
        map.put("remark","备注");
        //标题栏下第一行
        List<String> titleSecond = new ArrayList<>();
        RetailDailySettle settle = settleMapper.selectByPrimaryKey(id);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(settle.getEnterpriseId());
        StringBuilder titleSecondRow = new StringBuilder();
        if(loginUser.getParentId() == 0L) {
            titleSecondRow.append("门店编码:");
            titleSecondRow.append(enterprise.getCode());
            titleSecondRow.append(" 门店名称:");
            titleSecondRow.append(enterprise.getName());
        }
        titleSecondRow.append(" 日结日期:");
        titleSecondRow.append(DateUtils.DateToString(settle.getSettleDate(),DateUtils.FMT_DATE));
        titleSecondRow.append(" 日结人员:");
        titleSecondRow.append(settle.getSettleManName());
        titleSecondRow.append(" 单号:");
        titleSecondRow.append(settle.getCode());
        titleSecondRow.append(" 备注:");
        titleSecondRow.append(settle.getRemark()==null?"":settle.getRemark());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        Integer salePensTotal = 0;
        BigDecimal saleAmountTotal = BigDecimal.ZERO;
        Integer returnPensTotal = 0;
        BigDecimal returnAmountTotal = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        end.append(settle.getSalePensTotal());
        end.append(";");
        end.append(settle.getSaleAmountTotal());
        end.append(";");
        end.append(settle.getReturnPensTotal());
        end.append(";");
        end.append(settle.getReturnAmountTotal());
        end.append(";");
        end.append(settle.getAmountTotal());
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("salePens");
        needTotalName.add("saleAmount");
        needTotalName.add("returnPens");
        needTotalName.add("returnAmount");
        needTotalName.add("amount");
        List<String> name = new ArrayList<>();
        name.add(enterprise.getName());
        name.add("零售日结");
        List<RetailDailySettleDetail> details = settleDetailMapper.selectBySettleId(id);
        purchaseGeneralComponent.commExcelExport(output,map,details,name,titleSecond,end.toString(),false,needTotalName);
    }

    @Override
    public RetailDailySettleExportVO getDailySettlePrint(UserVO loginUser, Long id) throws Exception {
        RetailDailySettleExportVO exportVO = new RetailDailySettleExportVO();
        RetailDailySettle settle = settleMapper.selectByPrimaryKey(id);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(settle,exportVO);
        List<RetailDailySettleDetailVO> detailVOS = settleDetailMapper.selectRetailDailySettleDetailVOBySettleId(id);
        exportVO.setRetailDailySettleDetailVOS(detailVOS);
        exportVO.setTitleFirstRow(loginUser.getEnterpriseName());
        exportVO.setTitleSecondRow("零售日结");
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(settle.getEnterpriseId());
        exportVO.setEnterpriseCode(enterprise.getCode());
        exportVO.setEnterpriseName(enterprise.getName());
        return exportVO;
    }

    @Override
    public Page<List<RetailDailySettleModifyRecordVO>> getDailySettleModifyRecord(Integer pageNo, Integer pageSize, Long id, UserVO loginUser) throws Exception {
        if(pageNo == null || pageSize == null){
            throw new BusinessException("翻页参数不能为空");
        }
        if(pageNo <= 0 || pageSize <= 0){
            throw new BusinessException("翻页参数必须大于0");
        }
        List<RetailDailySettleModifyRecordVO> modifyRecordVOS = settleModifyRecordMapper.selectBySettleIdByPageParam((pageNo-1)*pageSize,pageSize,id);
        Integer count = settleModifyRecordMapper.selectCountBySettleId(id);
        Page<List<RetailDailySettleModifyRecordVO>> page = new Page<>(pageNo,pageSize);
        page.setTotalRecord(count);
        page.setResult(modifyRecordVOS);
        return page;
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
        RetailDailySettle settle = settleMapper.selectByPrimaryKey(id);
        List<RetailDailySettleModifyRecord> records = settleModifyRecordMapper.selectByKeyId(id);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(settle.getEnterpriseId());
        name.add(enterprise.getName());
        name.add("修改记录");
        purchaseGeneralComponent.commExcelExport(output,map,records,name,titleSecond,"",true,new ArrayList<>());

    }

    @Override
    public List<RetailDailySettleDetailVO> getWillDailySettleByStore(Long storeId, UserVO loginUser) throws Exception {
        if(loginUser.getParentId() == 0 && storeId == null){
            throw new BusinessException("当前登录人员为总部人员,门店信息必传!");
        }
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(storeId==null?loginUser.getEnterpriseId():storeId);
        if(enterprise.getChainType()!=1 && loginUser.getParentId() == 0L){
            throw new BusinessException("选取的门店不是自营店,请重新选择门店进行日结!");
        }
        List<PayeeOpeningShift> shifts = shiftMapper.selectByEnterpriseId(enterprise.getId());
        List<RetailDailySettleDetailVO> settleDetailVOs = new ArrayList<>();
        for(PayeeOpeningShift shift : shifts){
            RetailDailySettleDetailVO settleDetailVO = new RetailDailySettleDetailVO();
            settleDetailVO.setEnterpriseId(enterprise.getId());
            settleDetailVO.setParentId(enterprise.getParentId());
            settleDetailVO.setShiftId(shift.getId());
            settleDetailVO.setShiftManId(shift.getCreaterId());
            settleDetailVO.setShiftManCode(shift.getCreaterCode());
            settleDetailVO.setShiftManName(shift.getCreaterName());
            settleDetailVO.setShiftStartDate(shift.getOpeningTime());
            settleDetailVO.setShiftEndDate(shift.getShiftTime());
            settleDetailVO.setSalePens(shift.getSalePens());
            settleDetailVO.setSaleAmount(shift.getSaleAmount());
            settleDetailVO.setReturnPens(shift.getReturnPens());
            settleDetailVO.setReturnAmount(shift.getReturnAmount());
            settleDetailVO.setAmount(shift.getSaleAmount().subtract(shift.getReturnAmount()));
            settleDetailVOs.add(settleDetailVO);
        }
        return settleDetailVOs;
    }

    @Override
    public List<DistrEnterpriseVO> getDirectSaleStore(UserVO loginUser) throws Exception {
        if(loginUser.getParentId() != 0L){
            throw new BusinessException("当前登录人是门店!");
        }
        return enterpriseMapper.getDirectSaleStore(loginUser.getEnterpriseId());
    }
}
