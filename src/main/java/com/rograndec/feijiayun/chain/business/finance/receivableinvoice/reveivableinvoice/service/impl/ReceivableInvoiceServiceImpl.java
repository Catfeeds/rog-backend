package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao.ReceivableInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao.ReceivableInvoiceInfoMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao.ReceivableInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao.ReceivableInvoiceModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoiceInfo;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoiceModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.service.ReceivableInvoiceService;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo.*;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.*;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReceivableInvoiceServiceImpl implements ReceivableInvoiceService {

    @Autowired
    private ReceivableInvoiceMapper receivableInvoiceMapper;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private DistrOutDetailMapper distrOutDetailMapper;
    @Autowired
    private DistrOutShelfMapper distrOutShelfMapper;
    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;
    @Autowired
    private ReceivableInvoiceDetailMapper receivableInvoiceDetailMapper;
    @Autowired
    private ReceivableInvoiceInfoMapper receivableInvoiceInfoMapper;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private DistrOutMapper distrOutMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private ReceivableInvoiceModifyRecordMapper receivableInvoiceModifyRecordMapper;
    @Autowired
    PurchaseGeneralComponent<SaveReceivableInvoiceDetailVO> purchaseGeneralComponent;
    @Autowired
    PurchaseGeneralComponent<InvoiceModifyRecordPageVO> purchaseGeneralComponentModify;
    @Autowired
    private FinanceComponent financeComponent;
    @Autowired
    private PickOrderMapper pickOrderMapper;
    @Autowired
    private PickDetailMapper pickDetailMapper;
    @Autowired
    private PickShelfMapper pickShelfMapper;
    @Autowired
    private DistrSendMapper distrSendMapper;
    @Autowired
    private DistrSendDetailMapper distrSendDetailMapper;

    @Override
    public TabableTotalVO getReveivableInvoicePage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String purchaseUnitCode, String purchaseUnitName, String code, String billManName, Integer status, String orderName, String orderType) {

        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd").format(startTime) + " 00:00:00";
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd").format(endTime) + " 23:59:59";
        }
        if (orderName != null && orderName.equals("billDate"))
            orderName = "bill_date";

        TabableTotalVO tabableTotalVO = new TabableTotalVO();
        BigDecimal tabAmountTotal = BigDecimal.ZERO;
        BigDecimal tabNotaxAmountTotal = BigDecimal.ZERO;
        BigDecimal tabTaxAmountTotal = BigDecimal.ZERO;

        Integer count = receivableInvoiceMapper.getReveivableinvoicePageCount(loginUser.getEnterpriseId(), startTimes, endTimes, purchaseUnitCode, purchaseUnitName, code, billManName, status);
        List<ReveivableInvoicePageVO> reveivableInvoicePageVOList = receivableInvoiceMapper.getReveivableinvoicePage(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes, purchaseUnitCode,
                purchaseUnitName, code, billManName, status, orderName, orderType);
        if (!reveivableInvoicePageVOList.isEmpty()) {
            for (ReveivableInvoicePageVO reveivableInvoicePageVO : reveivableInvoicePageVOList) {
                reveivableInvoicePageVO.setStatusName(ReceivableInvoiceStatus.getName(reveivableInvoicePageVO.getStatus()));
                tabAmountTotal = tabAmountTotal.add(reveivableInvoicePageVO.getAmountTotal());
                tabNotaxAmountTotal = tabNotaxAmountTotal.add(reveivableInvoicePageVO.getNotaxAmountTotal());
                tabTaxAmountTotal = tabTaxAmountTotal.add(reveivableInvoicePageVO.getTaxAmountTotal());
            }
            tabableTotalVO.setTabAmountTotal(tabAmountTotal);
            tabableTotalVO.setTabNotaxAmountTotal(tabNotaxAmountTotal);
            tabableTotalVO.setTabTaxAmountTotal(tabTaxAmountTotal);
            tabableTotalVO.setReveivableInvoicePageVO(reveivableInvoicePageVOList);
        } else {
            tabableTotalVO.setTabAmountTotal(tabAmountTotal);
            tabableTotalVO.setTabNotaxAmountTotal(tabNotaxAmountTotal);
            tabableTotalVO.setTabTaxAmountTotal(tabTaxAmountTotal);
        }
        page.setTotalRecord(count == null ? 0 : count.intValue());
        return tabableTotalVO;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    public String saveReveivableInvoice(UserVO loginUser, SaveReveivableInvoiceVO saveReveivableInvoiceVO) throws Exception {

        if (saveReveivableInvoiceVO.getId() != null) {//更新

            if(saveReveivableInvoiceVO.getUpdateReason() == null){
                throw new BusinessException("请填写修改原因");
            }

            if (saveReveivableInvoiceVO.getStatus() == ReceivableInvoiceStatus.ALREADY_WRITE.getStatus()) {
                throw new BusinessException("无法修改已冲销的应收贷项凭证");
            }

            ReceivableInvoice receivableInvoice = receivableInvoiceMapper.selectByPrimaryKey(saveReveivableInvoiceVO.getId());
            if (receivableInvoice == null) {
                throw new BusinessException("无效的应收发票ID：" + saveReveivableInvoiceVO.getId());
            }

            //插入修改记录表
            //修改主表
            SaveReveivableInvoiceVO oldReceivableInvoice = receivableInvoiceMapper.getInvoice(saveReveivableInvoiceVO.getId());
            if (oldReceivableInvoice == null) {
                throw new BusinessException("无效的应收发票ID：" + saveReveivableInvoiceVO.getId());
            }
            //修改开票信息表
            SaveReceivableInvoiceInfoVO oldReceivableInvoiceInfoVO = receivableInvoiceInfoMapper.getInvoiceInfo(saveReveivableInvoiceVO.getId());
            if (oldReceivableInvoiceInfoVO == null) {
                throw new BusinessException("无效的应收发票ID：" + saveReveivableInvoiceVO.getId());
            }
            //修改记录
            User user = userMapper.selectByPrimaryKey(saveReveivableInvoiceVO.getBillManId());
            if(user == null){
                throw new BusinessException("无效的人员ID：" + saveReveivableInvoiceVO.getBillManId());
            }
            saveReveivableInvoiceVO.setBillManCode(user.getCode());
            saveReveivableInvoiceVO.setBillManName(user.getName());
            if(oldReceivableInvoiceInfoVO.getFileId() != null) {
                File file = fileMapper.selectByPrimaryKey(oldReceivableInvoiceInfoVO.getFileId());
                if(file == null){
                    throw new BusinessException("无效的附件ID：" + oldReceivableInvoiceInfoVO.getFileId());
                }
                oldReceivableInvoiceInfoVO.setFileName(file.getFileName());
            }
            List<ReceivableInvoiceModifyRecord> receivableInvoiceModifyRecordList = getReceivableInvoiceModifyRecordList(loginUser, saveReveivableInvoiceVO, oldReceivableInvoice, saveReveivableInvoiceVO.getSaveReceivableInvoiceInfoVO(), oldReceivableInvoiceInfoVO);
            for (ReceivableInvoiceModifyRecord ur : receivableInvoiceModifyRecordList) {
                ur.setRemark(saveReveivableInvoiceVO.getUpdateReason());
                ur.setReason(saveReveivableInvoiceVO.getUpdateReason());
                receivableInvoiceModifyRecordMapper.insertSelective(ur);
            }

            receivableInvoice.setBillManId(saveReveivableInvoiceVO.getBillManId());
            receivableInvoice.setBillManName(user.getName());
            receivableInvoice.setBillManCode(user.getCode());
            receivableInvoice.setBillDate(saveReveivableInvoiceVO.getBillDate());
            if (saveReveivableInvoiceVO.getRemark() != null) {
                receivableInvoice.setRemark(saveReveivableInvoiceVO.getRemark());
            }
            UserEnterpriseUtils.setUserCreateOrModify(receivableInvoice, loginUser, false);
            receivableInvoiceMapper.updateByPrimaryKeySelective(receivableInvoice);

            ReceivableInvoiceInfo receivableInvoiceInfo = receivableInvoiceInfoMapper.selectByPrimaryKey(saveReveivableInvoiceVO.getSaveReceivableInvoiceInfoVO().getId());
            if (receivableInvoiceInfo == null) {
                throw new BusinessException("无效的应收发票开票信息ID：" + saveReveivableInvoiceVO.getSaveReceivableInvoiceInfoVO().getId());
            }
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveReveivableInvoiceVO.getSaveReceivableInvoiceInfoVO(), receivableInvoiceInfo);
            UserEnterpriseUtils.setUserCreateOrModify(receivableInvoiceInfo, loginUser, false);
            receivableInvoiceInfoMapper.updateByPrimaryKeySelective(receivableInvoiceInfo);

            return "单号：" + receivableInvoice.getCode();

        } else {//新增

            //删除缓存
            if (saveReveivableInvoiceVO.getRedisKeyValue() != null) {
                redisComponent.removeDraftCacheVO(saveReveivableInvoiceVO.getPurchaseUnitId(), loginUser.getEnterpriseId(), OrderRule.RECEIVABLE_INVOICE.getCodePrefix(), saveReveivableInvoiceVO.getRedisKeyValue());
            }

            ReceivableInvoice receivableInvoice = new ReceivableInvoice();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveReveivableInvoiceVO, receivableInvoice);
            receivableInvoice.setId(null);
            receivableInvoice.setEnterpriseId(loginUser.getEnterpriseId());
            receivableInvoice.setParentId(loginUser.getParentId());
            UserEnterpriseUtils.setUserCreateOrModify(receivableInvoice, loginUser, true);
            receivableInvoice.setFinanceAccountType(FinanceAccountType.LEAGUE.getType());

            receivableInvoice.setCode(getCode(OrderRule.RECEIVABLE_INVOICE.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
            receivableInvoice.setOrderType(OrderRule.RECEIVABLE_INVOICE.getType());

            if (receivableInvoice.getBillManId() == null) {
                throw new BusinessException("缺少必传字段开票人员ID");
            }
            User user = userMapper.selectByPrimaryKey(receivableInvoice.getBillManId());
            if (user == null) {
                throw new BusinessException("无效的开票人员ID：" + receivableInvoice.getBillManId());
            }
            receivableInvoice.setBillManCode(user.getCode());
            receivableInvoice.setBillManName(user.getName());

            if (receivableInvoice.getPurchaseUnitId() == null) {
                throw new BusinessException("缺少必传字段购货单位ID");
            }
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(receivableInvoice.getPurchaseUnitId());
            if (enterprise == null) {
                throw new BusinessException("无效的购货单位ID：" + receivableInvoice.getPurchaseUnitId());
            }
            receivableInvoice.setPurchaseUnitCode(enterprise.getCode());
            receivableInvoice.setPurchaseUnitName(enterprise.getName());

            receivableInvoice.setQuantityTotal(BigDecimal.ZERO);
            receivableInvoice.setVarietiesQuantity(0);
            receivableInvoice.setAmountTotal(BigDecimal.ZERO);
            receivableInvoice.setNotaxAmountTotal(BigDecimal.ZERO);
            receivableInvoice.setTaxAmountTotal(BigDecimal.ZERO);
            receivableInvoice.setDiffAmountTotal(BigDecimal.ZERO);
            receivableInvoice.setDiffNotaxAmountTotal(BigDecimal.ZERO);
            receivableInvoice.setDiffTaxAmountTotal(BigDecimal.ZERO);
            receivableInvoice.setUnclearAmountTotal(BigDecimal.ZERO);
            receivableInvoice.setClearAmountTotal(BigDecimal.ZERO);

            receivableInvoice.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());
            receivableInvoiceMapper.insertSelective(receivableInvoice);

            saveReveivableInvoiceDetail(loginUser, receivableInvoice, saveReveivableInvoiceVO);

            saveReceivableInvoiceInfo(loginUser, receivableInvoice, saveReveivableInvoiceVO);

            return "单号：" + receivableInvoice.getCode();
        }
    }

    private List<ReceivableInvoiceModifyRecord> getReceivableInvoiceModifyRecordList(UserVO loginUser, Object saveReveivableinvoiceVO, SaveReveivableInvoiceVO OldReceivableInvoice,
                                                                                     SaveReceivableInvoiceInfoVO saveReceivableInvoiceInfoVO, SaveReceivableInvoiceInfoVO oldReceivableInvoiceInfoVO) throws Exception {

        Map<String, Object> newInvoiceMap = getFieldsMap(saveReveivableinvoiceVO);
        Map<String, Object> oldInvoiceMap = getFieldsMap(OldReceivableInvoice);

        Map<String, Object> newInvoiceInfoMap = getFieldsMap(saveReceivableInvoiceInfoVO);
        Map<String, Object> oldInvoiceInfoMap = getFieldsMap(oldReceivableInvoiceInfoVO);

        Map<String, String> invoiceFieldSkipMap = invoiceFieldSkipMap();
        Map<String, String> invoiceInfoFieldSkipMap = invoiceInfoFieldSkipMap();

        List<ReceivableInvoiceModifyRecord> receivableInvoiceModifyRecordList = new ArrayList<>();
        receivableInvoiceModifyRecordList = getModifyRecordList(loginUser, OldReceivableInvoice, "saas_receivable_invoice", newInvoiceMap, oldInvoiceMap, invoiceFieldSkipMap, receivableInvoiceModifyRecordList);
        receivableInvoiceModifyRecordList = getModifyRecordList(loginUser, OldReceivableInvoice, "saas_receivable_invoice_info", newInvoiceInfoMap, oldInvoiceInfoMap, invoiceInfoFieldSkipMap, receivableInvoiceModifyRecordList);

        return receivableInvoiceModifyRecordList;

    }

    private List<ReceivableInvoiceModifyRecord> getModifyRecordList(UserVO loginUser, SaveReveivableInvoiceVO receivableInvoice, String tableName, Map<String, Object> newInvoiceMap,
                                                                    Map<String, Object> oldInvoiceMap, Map<String, String> fieldMap, List<ReceivableInvoiceModifyRecord> receivableInvoiceModifyRecordList) {

        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            Object obj = oldInvoiceMap.get(entry.getKey());
            Object newObj = newInvoiceMap.get(entry.getKey());
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
                        excuteModifyRecord(loginUser, receivableInvoice, tableName, receivableInvoiceModifyRecordList, entry, obj, newObj);
                    }
                } else if (!obj.equals(newObj)) {
                    excuteModifyRecord(loginUser, receivableInvoice, tableName, receivableInvoiceModifyRecordList, entry, obj, newObj);
                }
            }
            if (obj == null && newObj != null) {
                excuteModifyRecord(loginUser, receivableInvoice, tableName, receivableInvoiceModifyRecordList, entry, obj, newObj);
            }
        }


        return receivableInvoiceModifyRecordList;
    }

    private void excuteModifyRecord(UserVO loginUser, SaveReveivableInvoiceVO receivableInvoice, String tableName, List<ReceivableInvoiceModifyRecord> receivableInvoiceModifyRecordList, Map.Entry<String, String> entry, Object obj, Object newObj) {

        ReceivableInvoiceModifyRecord receivableInvoiceModifyRecord = new ReceivableInvoiceModifyRecord();
        receivableInvoiceModifyRecord.setEnterpriseId(loginUser.getEnterpriseId());
        receivableInvoiceModifyRecord.setParentId(loginUser.getParentId());
        receivableInvoiceModifyRecord.setTableName(tableName);
        receivableInvoiceModifyRecord.setKeyId(receivableInvoice.getId());
        receivableInvoiceModifyRecord.setColumnEnName(entry.getKey());
        receivableInvoiceModifyRecord.setColumnChName(entry.getValue());
        receivableInvoiceModifyRecord.setUpdateTime(new Date());
        receivableInvoiceModifyRecord.setCreaterId(loginUser.getUserId());
        receivableInvoiceModifyRecord.setCreaterCode(loginUser.getUserCode());
        receivableInvoiceModifyRecord.setCreaterName(loginUser.getUserName());
        receivableInvoiceModifyRecord.setModifierId(loginUser.getUserId());
        receivableInvoiceModifyRecord.setModifierCode(loginUser.getUserCode());
        receivableInvoiceModifyRecord.setModifierName(loginUser.getUserName());
        receivableInvoiceModifyRecord.setCreateTime(new Date());
        receivableInvoiceModifyRecord.setOldContent(obj == null ? "" : obj.toString());
        receivableInvoiceModifyRecord.setNewContent(newObj == null ? "" : newObj.toString());

        receivableInvoiceModifyRecordList.add(receivableInvoiceModifyRecord);

    }


    public Map<String, Object> getFieldsMap(Object obj) throws Exception {
        Class newUserClazz = obj.getClass();
        Field[] feilds = newUserClazz.getDeclaredFields();
        Map<String, Object> feildMap = new HashMap<>();
        for (Field field : feilds) {
            field.setAccessible(true);
            Object valObj = ReflectUtils.getValueOfGet(obj, field.getName());
            feildMap.put(field.getName(), valObj);
        }
        return feildMap;
    }

    private Map<String, String> invoiceFieldSkipMap() {
        Map<String, String> fieldNames = new HashMap();
        fieldNames.put("billDate", "开票日期");
//        fieldNames.put("billManId", "开票人员ID");
        fieldNames.put("billManCode", "开票人员编码");
        fieldNames.put("billManName", "开票人员");
        fieldNames.put("remark", "备注");
        return fieldNames;
    }

    private Map<String, String> invoiceInfoFieldSkipMap() {
        Map<String, String> fieldNames = new HashMap();
        fieldNames.put("taxpayerIdCode", "购货单位纳税人识别号");
        fieldNames.put("accountName", "购货单位开户户名");
        fieldNames.put("accountBank", "购货单位开户银行");
        fieldNames.put("account", "购货单位开户账号");
        fieldNames.put("address", "购货单位地址");
        fieldNames.put("telephone", "购货单位电话");
        fieldNames.put("invoiceType", "发票类型");
        fieldNames.put("invoiceCode", "发票代码");
        fieldNames.put("invoiceNumber", "发票号码");
        fieldNames.put("checkCode", "校验码");
        fieldNames.put("companyTaxpayerIdCode", "企业纳税人识别号");
        fieldNames.put("companyAccountName", "企业开户户名");
        fieldNames.put("companyAccountBank", "企业开户银行");
        fieldNames.put("companyAccount", "企业开户账号");
        fieldNames.put("companyName", "企业企业名称");
        fieldNames.put("companyAddress", "企业地址");
        fieldNames.put("companyTelephone", "企业电话");
        fieldNames.put("fileId", "附件");
        fieldNames.put("fileName", "附件名称");
        return fieldNames;
    }

    @Override
    public DistrOutPageTotalVO getDistrOutPage(int pageNo, int pageSize, UserVO loginUser, Page page, Long purchaseUnitId, String key) {

        DistrOutPageTotalVO distrOutPageTotalVO = new DistrOutPageTotalVO();

        List<Integer> status = new ArrayList<>();
        status.add(DistrOutStatus.WAIT_BILL);
        status.add(DistrOutStatus.PART_BILL);

        Integer count = receivableInvoiceMapper.getDistrOutPageCount(loginUser.getEnterpriseId(), purchaseUnitId, status, key);
        List<DistrOutPageVO> distrOutPageVOS = receivableInvoiceMapper.getDistrOutPage(loginUser.getEnterpriseId(), page.getStart(), pageSize, purchaseUnitId, status, key);
        distrOutPageTotalVO.setDistrOutPageVO(distrOutPageVOS);

        BigDecimal tabQuantityTotal = BigDecimal.ZERO;
        BigDecimal tabAmountTotal = BigDecimal.ZERO;
        BigDecimal tabWholeDiscountAmountTotal = BigDecimal.ZERO;
        BigDecimal tabLineDiscountAmountTotal = BigDecimal.ZERO;
        BigDecimal tabNotaxAmountTotal = BigDecimal.ZERO;
        BigDecimal tabRealAmountTotal = BigDecimal.ZERO;
        BigDecimal tabTaxAmountTotal = BigDecimal.ZERO;
        BigDecimal tabClearQuantityTotal = BigDecimal.ZERO;
        BigDecimal tabUnclearQuantityTotal = BigDecimal.ZERO;

        for (DistrOutPageVO distrOutPageVO : distrOutPageVOS) {
            tabQuantityTotal = tabQuantityTotal.add(distrOutPageVO.getQuantity());
            tabAmountTotal = tabAmountTotal.add(distrOutPageVO.getBaseOrderAmount());
            tabWholeDiscountAmountTotal = tabWholeDiscountAmountTotal.add(distrOutPageVO.getWholeDiscountAmount());
            tabLineDiscountAmountTotal = tabLineDiscountAmountTotal.add(distrOutPageVO.getLineDiscountAmount());
            tabNotaxAmountTotal = tabNotaxAmountTotal.add(distrOutPageVO.getBaseOrderNotaxAmount());
            tabRealAmountTotal = tabRealAmountTotal.add(distrOutPageVO.getRealAmount());
            tabTaxAmountTotal = tabTaxAmountTotal.add(distrOutPageVO.getBaseOrderTaxAmount());
            tabClearQuantityTotal = tabClearQuantityTotal.add(distrOutPageVO.getClearQuantity());
            tabUnclearQuantityTotal = tabUnclearQuantityTotal.add(distrOutPageVO.getUnclearQuantity());
        }
        distrOutPageTotalVO.setTabQuantityTotal(tabQuantityTotal);
        distrOutPageTotalVO.setTabAmountTotal(tabAmountTotal);
        distrOutPageTotalVO.setTabWholeDiscountAmountTotal(tabWholeDiscountAmountTotal);
        distrOutPageTotalVO.setTabLineDiscountAmountTotal(tabLineDiscountAmountTotal);
        distrOutPageTotalVO.setTabNotaxAmountTotal(tabNotaxAmountTotal);
        distrOutPageTotalVO.setTabRealAmountTotal(tabRealAmountTotal);
        distrOutPageTotalVO.setTabTaxAmountTotal(tabTaxAmountTotal);
        distrOutPageTotalVO.setTabClearQuantityTotal(tabClearQuantityTotal);
        distrOutPageTotalVO.setTabUnclearQuantityTotal(tabUnclearQuantityTotal);

        page.setTotalRecord(count == null ? 0 : count.intValue());
        return distrOutPageTotalVO;
    }

    @Override
    public List<PurchaseUnitVO> getPurchaseUnit(UserVO userVO, String key) {

        List<PurchaseUnitVO> purchaseUnitVOList = receivableInvoiceMapper.getPurchaseUnit(userVO.getEnterpriseId(), ChainType.Division.getCode(), key);
        return purchaseUnitVOList;
    }

    @Override
    public SaveReveivableInvoiceVO getSaveReveivableInvoice(UserVO userVO, Long id) {

        ReceivableInvoice receivableInvoice = receivableInvoiceMapper.selectByPrimaryKey(id);
        if (receivableInvoice == null) {
            throw new BusinessException("无效的应收发票ID：" + id);
        }
        SaveReveivableInvoiceVO saveReveivableInvoiceVO = new SaveReveivableInvoiceVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(receivableInvoice, saveReveivableInvoiceVO);
        saveReveivableInvoiceVO.setStatus(receivableInvoice.getStatus());

        List<ReceivableInvoiceDetail> receivableInvoiceDetailList = receivableInvoiceDetailMapper.getReceivableInvoiceDetailList(receivableInvoice.getId());
        if (receivableInvoiceDetailList.isEmpty()) {
            throw new BusinessException("没有查询到应收发票ID：" + receivableInvoice.getId() + "的明细数据");
        }
        List<SaveReceivableInvoiceDetailVO> saveReceivableInvoiceDetailVOList = new ArrayList<>();
        for (ReceivableInvoiceDetail receivableInvoiceDetail : receivableInvoiceDetailList) {
            SaveReceivableInvoiceDetailVO saveReceivableInvoiceDetailVO = new SaveReceivableInvoiceDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(receivableInvoiceDetail, saveReceivableInvoiceDetailVO);
            saveReceivableInvoiceDetailVOList.add(saveReceivableInvoiceDetailVO);
        }
        saveReveivableInvoiceVO.setSaveReceivableInvoiceDetailVO(saveReceivableInvoiceDetailVOList);

        ReceivableInvoiceInfo receivableInvoiceInfo = receivableInvoiceInfoMapper.getReceivableInvoiceInfo(receivableInvoice.getId());
        if (receivableInvoiceInfo == null) {
            throw new BusinessException("没有查询到应收发票ID：" + receivableInvoice.getId() + "的开票信息数据");
        }

        SaveReceivableInvoiceInfoVO saveReceivableInvoiceInfoVO = new SaveReceivableInvoiceInfoVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(receivableInvoiceInfo, saveReceivableInvoiceInfoVO);
        if (receivableInvoiceInfo.getFileId() != null) {
            File file = fileMapper.selectByPrimaryKey(receivableInvoiceInfo.getFileId());
            if (file == null) {
                throw new BusinessException("无效的附件ID：" + receivableInvoiceInfo.getFileId());
            }
            saveReceivableInvoiceInfoVO.setFileName(file.getFileName());
        }
        if(saveReceivableInvoiceInfoVO.getInvoiceType() == null){
            throw new BusinessException("没有有效的发票类型ID");
        }
        saveReceivableInvoiceInfoVO.setInvoiceTypeName(AdvanceInvoiceType.getName(saveReceivableInvoiceInfoVO.getInvoiceType()));
        saveReveivableInvoiceVO.setSaveReceivableInvoiceInfoVO(saveReceivableInvoiceInfoVO);

        return saveReveivableInvoiceVO;
    }

    private void saveReceivableInvoiceInfo(UserVO loginUser, ReceivableInvoice receivableInvoice, SaveReveivableInvoiceVO saveReveivableInvoiceVO) {

        if (saveReveivableInvoiceVO.getSaveReceivableInvoiceInfoVO() == null) {
            throw new BusinessException("缺少必传应收发票开票信息数据");
        }
        if (saveReveivableInvoiceVO.getSaveReceivableInvoiceInfoVO().getInvoiceType() == null) {
            throw new BusinessException("缺少必传字段发票类型数据");
        }
        ReceivableInvoiceInfo receivableInvoiceInfo = new ReceivableInvoiceInfo();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveReveivableInvoiceVO.getSaveReceivableInvoiceInfoVO(), receivableInvoiceInfo);
        receivableInvoiceInfo.setId(null);
        receivableInvoiceInfo.setEnterpriseId(loginUser.getEnterpriseId());
        receivableInvoiceInfo.setParentId(loginUser.getParentId());
        receivableInvoiceInfo.setInvoiceId(receivableInvoice.getId());

        receivableInvoiceInfoMapper.insertSelective(receivableInvoiceInfo);

    }

    private void saveReveivableInvoiceDetail(UserVO loginUser, ReceivableInvoice receivableInvoice, SaveReveivableInvoiceVO saveReveivableInvoiceVO) throws Exception {

        if (saveReveivableInvoiceVO.getSaveReceivableInvoiceDetailVO().isEmpty()) {
            throw new BusinessException("缺少必传应收发票明细数据");
        }
        BigDecimal quantityTotal = BigDecimal.ZERO;
        Integer varietiesQuantity = saveReveivableInvoiceVO.getSaveReceivableInvoiceDetailVO().size();
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;

        BigDecimal diffAmountTotal = BigDecimal.ZERO;
        BigDecimal diffNotaxAmountTotal = BigDecimal.ZERO;
        BigDecimal diffTaxAmountTotal = BigDecimal.ZERO;

        BigDecimal clearAmountTotal = BigDecimal.ZERO;
        BigDecimal unclearAmountTotal = BigDecimal.ZERO;

        BigDecimal invoiceAmount = BigDecimal.ZERO;
        BigDecimal distrOutAmount = BigDecimal.ZERO;
        BigDecimal invoiceDiffNotaxTotal = BigDecimal.ZERO;
        BigDecimal distrOutDiffNotaxTotal = BigDecimal.ZERO;

        List<ReceivableInvoiceDetail> receivableInvoiceDetailList = new ArrayList<>();
        for (SaveReceivableInvoiceDetailVO saveReceivableInvoiceDetailVO : saveReveivableInvoiceVO.getSaveReceivableInvoiceDetailVO()) {
            if (saveReceivableInvoiceDetailVO.getBaseOrderId() == null || saveReceivableInvoiceDetailVO.getBaseOrderType() == null ||
                    saveReceivableInvoiceDetailVO.getBaseDtlId() == null || saveReceivableInvoiceDetailVO.getBaseShelfDtlId() == null) {
                throw new BusinessException("缺少必传上级引用单据数据");
            }
            if (saveReceivableInvoiceDetailVO.getBaseOrderTaxRate() == null || saveReceivableInvoiceDetailVO.getBaseOrderNotaxPrice() == null ||
                    saveReceivableInvoiceDetailVO.getBaseOrderUnitPrice() == null || saveReceivableInvoiceDetailVO.getBaseOrderAmount() == null ||
                    saveReceivableInvoiceDetailVO.getBaseOrderNotaxAmount() == null || saveReceivableInvoiceDetailVO.getBaseOrderTaxAmount() == null) {
                throw new BusinessException("缺少必要的原始单据价格等信息");
            }
            if (saveReceivableInvoiceDetailVO.getQuantity() == null || saveReceivableInvoiceDetailVO.getUnitPrice() == null ||
                    saveReceivableInvoiceDetailVO.getTaxRateId() == null) {
                throw new BusinessException("缺少必要的单据价格等信息");
            }
            ReceivableInvoiceDetail receivableInvoiceDetail = new ReceivableInvoiceDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveReceivableInvoiceDetailVO, receivableInvoiceDetail);
            receivableInvoiceDetail.setId(null);
            receivableInvoiceDetail.setEnterpriseId(loginUser.getEnterpriseId());
            receivableInvoiceDetail.setParentId(loginUser.getParentId());
            UserEnterpriseUtils.setUserCreateOrModify(receivableInvoiceDetail, loginUser, true);

            receivableInvoiceDetail.setInvoiceId(receivableInvoice.getId());

            if (saveReceivableInvoiceDetailVO.getBaseOrderType().intValue() != OrderRule.DISTR_OUT.getType()) {
                throw new BusinessException("单据类型不是配货出库类型ID");
            }
            DistrOutDetail distrOutDetail = distrOutDetailMapper.selectByPrimaryKey(saveReceivableInvoiceDetailVO.getBaseDtlId());
            if (distrOutDetail == null) {
                throw new BusinessException("无效的配货出库明细ID：" + saveReceivableInvoiceDetailVO.getBaseDtlId());
            }
            receivableInvoiceDetail.setBaseOrderCode(distrOutDetail.getOutCode());
            receivableInvoiceDetail.setBaseOrderDate(distrOutDetail.getOutDate());

            receivableInvoiceDetail.setGoodsId(distrOutDetail.getGoodsId());
            receivableInvoiceDetail.setGoodsCode(distrOutDetail.getGoodsCode());
            receivableInvoiceDetail.setGoodsName(distrOutDetail.getGoodsName());
            receivableInvoiceDetail.setGoodsGenericName(distrOutDetail.getGoodsGenericName());
            receivableInvoiceDetail.setDosageId(distrOutDetail.getDosageId());
            receivableInvoiceDetail.setDosageName(distrOutDetail.getDosageName());
            receivableInvoiceDetail.setUnitId(distrOutDetail.getUnitId());
            receivableInvoiceDetail.setUnitName(distrOutDetail.getUnitName());

            if (distrOutDetail.getBarcode() != null) {
                receivableInvoiceDetail.setBarcode(distrOutDetail.getBarcode());
            }
            if (distrOutDetail.getGoodsSpecification() != null) {
                receivableInvoiceDetail.setGoodsSpecification(distrOutDetail.getGoodsSpecification());
            }
            if (distrOutDetail.getManufacturerId() != null) {
                receivableInvoiceDetail.setManufacturerId(distrOutDetail.getManufacturerId());
            }
            if (distrOutDetail.getManufacturer() != null) {
                receivableInvoiceDetail.setManufacturer(distrOutDetail.getManufacturer());
            }
            if (distrOutDetail.getGoodsPlace() != null) {
                receivableInvoiceDetail.setGoodsPlace(distrOutDetail.getGoodsPlace());
            }
            if (distrOutDetail.getApprovalNumber() != null) {
                receivableInvoiceDetail.setApprovalNumber(distrOutDetail.getApprovalNumber());
            }

            DistrOutShelf distrOutShelf = distrOutShelfMapper.selectByPrimaryKey(saveReceivableInvoiceDetailVO.getBaseShelfDtlId());
            if (distrOutShelf == null) {
                throw new BusinessException("无效的配货出库货位明细ID：" + saveReceivableInvoiceDetailVO.getBaseShelfDtlId());
            }
            if (saveReceivableInvoiceDetailVO.getQuantity().compareTo(distrOutShelf.getUnclearQuantity()) > 0) {
                throw new BusinessException("填写数量超出原始单据未清数量");
            }
            receivableInvoiceDetail.setLotNumber(distrOutShelf.getLotNumber());
            receivableInvoiceDetail.setProductDate(distrOutShelf.getProductDate());
            receivableInvoiceDetail.setValidDate(distrOutShelf.getValidDate());
            receivableInvoiceDetail.setQuantity(saveReceivableInvoiceDetailVO.getQuantity());
            receivableInvoiceDetail.setUnitPrice(saveReceivableInvoiceDetailVO.getUnitPrice());
            receivableInvoiceDetail.setTaxRateId(saveReceivableInvoiceDetailVO.getTaxRateId());

            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(saveReceivableInvoiceDetailVO.getTaxRateId());
            if (goodsTaxRate == null) {
                throw new BusinessException("无效的税率ID：" + saveReceivableInvoiceDetailVO.getTaxRateId());
            }
            receivableInvoiceDetail.setTaxRate(goodsTaxRate.getTaxRate());


            receivableInvoiceDetail.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());


            if (saveReceivableInvoiceDetailVO.getUnitPrice().compareTo(distrOutShelf.getUnitPrice()) == 0 && saveReceivableInvoiceDetailVO.getTaxRateId() == distrOutShelf.getTaxRateId()) {//如果单价都一致  没有差额  0.00

                receivableInvoiceDetail.setDiffAmount(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));//金额差额
                receivableInvoiceDetail.setDiffNotaxAmount(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));//不含税金额差额
                receivableInvoiceDetail.setDiffTaxAmount(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));//税额差额
            } else {
                //计算-------------------start--------------------------------------------
                //发票单价*发票数量
                invoiceAmount = saveReceivableInvoiceDetailVO.getUnitPrice().multiply(saveReceivableInvoiceDetailVO.getQuantity());

                //配货出库单价*发票数量
                distrOutAmount = distrOutShelf.getRealPrice().multiply(saveReceivableInvoiceDetailVO.getQuantity());

                //(发票单价*发票数量)/发票税率
                invoiceDiffNotaxTotal = (saveReceivableInvoiceDetailVO.getUnitPrice().multiply(saveReceivableInvoiceDetailVO.getQuantity())).divide(new BigDecimal(1).add(goodsTaxRate.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);

                //(配货出库实际单价*发票数量)/配货出库税率
                distrOutDiffNotaxTotal =(distrOutShelf.getRealPrice().multiply(saveReceivableInvoiceDetailVO.getQuantity())).divide(new BigDecimal(1).add(distrOutShelf.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
                //计算-------------------end--------------------------------------------

                // 发票单价*发票数量 - 配货出库单价*发票数量 = 金额差额
                receivableInvoiceDetail.setDiffAmount(invoiceAmount.subtract(distrOutAmount));//金额差额合计
                // (发票单价*发票数量)/发票税率 - (配货出库实际单价*发票数量)/配货出库税率 = 不含税金额差额
                receivableInvoiceDetail.setDiffNotaxAmount(invoiceDiffNotaxTotal.subtract(distrOutDiffNotaxTotal));//不含税金额差额合计
                // (发票单价*发票数量 - (发票单价*发票数量)/发票税率) -(配货出库单价*发票数量 - (配货出库实际单价*发票数量)/配货出库税率) = 税额差额
                receivableInvoiceDetail.setDiffTaxAmount(invoiceAmount.subtract(invoiceDiffNotaxTotal).subtract(distrOutAmount.subtract(distrOutDiffNotaxTotal)));//税额差额合计
            }

            // 发票数量 * 发票单价 = 发票金额
            BigDecimal amount = saveReceivableInvoiceDetailVO.getQuantity().multiply(saveReceivableInvoiceDetailVO.getUnitPrice());
            // （发票金额 / 税率）/数量 = 不含税单价
            BigDecimal notaxPrice = (amount.divide(new BigDecimal(1).add(goodsTaxRate.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 6, BigDecimal.ROUND_HALF_UP)).divide(saveReceivableInvoiceDetailVO.getQuantity(),6, BigDecimal.ROUND_HALF_UP);
            // 不含税单价 * 发票数量 = 不含税金额
            BigDecimal notaxAmount = notaxPrice.multiply(saveReceivableInvoiceDetailVO.getQuantity());
            // 发票金额 - 不含税金额 = 税额
            BigDecimal taxAmount = amount.subtract(notaxAmount);

            receivableInvoiceDetail.setAmount(amount);//发票金额
            receivableInvoiceDetail.setNotaxPrice(notaxPrice);//不含税单价
            receivableInvoiceDetail.setNotaxAmount(notaxAmount);//不含税金额
            receivableInvoiceDetail.setTaxAmount(taxAmount);//税额

            receivableInvoiceDetailMapper.insertSelective(receivableInvoiceDetail);

            //合计
            quantityTotal = quantityTotal.add(saveReceivableInvoiceDetailVO.getQuantity());
            amountTotal = amountTotal.add(saveReceivableInvoiceDetailVO.getAmount());
            notaxAmountTotal = notaxAmountTotal.add(saveReceivableInvoiceDetailVO.getNotaxAmount());
            taxAmountTotal = taxAmountTotal.add(saveReceivableInvoiceDetailVO.getTaxAmount());
            unclearAmountTotal = unclearAmountTotal.add(saveReceivableInvoiceDetailVO.getQuantity().multiply(saveReceivableInvoiceDetailVO.getUnitPrice()));


            // 发票单价*发票数量 -配货出库单价*发票数量  = 金额差额
            diffAmountTotal = diffAmountTotal.add(
                    invoiceAmount.subtract(distrOutAmount)
            );

            //(发票单价*发票数量)/发票税率 - (配货出库不含税实际单价*发票数量)/配货出库税率 = 不含税金额差额
            diffNotaxAmountTotal = diffNotaxAmountTotal.add(
                    invoiceDiffNotaxTotal.subtract(distrOutDiffNotaxTotal)
            );

            //(发票单价*发票数量 - (发票单价*发票数量)/发票税率) - (配货出库单价*发票数量 - (配货出库不含税实际单价*发票数量)/配货出库税率) = 税额差额
            diffTaxAmountTotal = diffTaxAmountTotal.add(
                    (invoiceAmount.subtract(invoiceDiffNotaxTotal)).subtract(distrOutAmount.subtract(distrOutDiffNotaxTotal))
            );

            //更新配货出库未清，已清数量
            //未清
            BigDecimal unclearQuantity = distrOutShelf.getUnclearQuantity().subtract(saveReceivableInvoiceDetailVO.getQuantity());
            if (unclearQuantity.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("如果继续配货出库中货位明细未清数量会变为负数");
            }
            //已清
            BigDecimal clearQuantity = distrOutShelf.getClearQuantity().add(saveReceivableInvoiceDetailVO.getQuantity());
            if (clearQuantity.compareTo(distrOutShelf.getQuantity()) > 0) {
                throw new BusinessException("如果继续配货出库中货位明细已清数量会超出原有单据数量");
            }
            distrOutShelf.setUnclearQuantity(unclearQuantity);
            distrOutShelf.setClearQuantity(clearQuantity);
            if(unclearQuantity.compareTo(BigDecimal.ZERO) == 0 ) {
                distrOutShelf.setStatus(DistrOutStatus.FINISHED);
            }else {
                distrOutShelf.setStatus(DistrOutStatus.PART_BILL);
            }
            UserEnterpriseUtils.setUserCreateOrModify(distrOutShelf, loginUser, false);
            distrOutShelfMapper.updateByPrimaryKeySelective(distrOutShelf);

            BigDecimal unclearQuantityDtl = distrOutDetail.getUnclearQuantity().subtract(saveReceivableInvoiceDetailVO.getQuantity());
            if (unclearQuantityDtl.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("如果继续配货出库中明细未清数量会变为负数");
            }
            BigDecimal clearQuantityDtl = distrOutDetail.getClearQuantity().add(saveReceivableInvoiceDetailVO.getQuantity());
            if (clearQuantityDtl.compareTo(distrOutDetail.getQuantity()) > 0) {
                throw new BusinessException("如果继续配货出库中明细已清数量会超出原有单据数量");
            }
            distrOutDetail.setUnclearQuantity(unclearQuantityDtl);
            distrOutDetail.setClearQuantity(clearQuantityDtl);
            UserEnterpriseUtils.setUserCreateOrModify(distrOutDetail, loginUser, false);
            distrOutDetailMapper.updateByPrimaryKeySelective(distrOutDetail);

            //判断配货出库状态 (部分开票，已开票)
            List<DistrOutShelf> distrOutShelves = distrOutShelfMapper.getDistrOutShelfList(distrOutDetail.getId());
            Integer distrOutShelvesCount = receivableInvoiceMapper.getDistrOutShelfCount(distrOutDetail.getId(), DistrOutStatus.FINISHED);
            if (BigDecimal.valueOf(distrOutShelves.size()).compareTo(BigDecimal.valueOf(distrOutShelvesCount)) > 0) {
                distrOutDetail.setStatus(DistrOutStatus.PART_BILL);//部分开票
            } else if (BigDecimal.valueOf(distrOutShelves.size()).compareTo(BigDecimal.valueOf(distrOutShelvesCount)) == 0) {
                distrOutDetail.setStatus(DistrOutStatus.FINISHED);//已开票
            }
            UserEnterpriseUtils.setUserCreateOrModify(distrOutDetail, loginUser, false);
            distrOutDetailMapper.updateByPrimaryKeySelective(distrOutDetail);

            DistrOut distrOut = distrOutMapper.selectByPrimaryKey(saveReceivableInvoiceDetailVO.getBaseOrderId());
            List<DistrOutDetail> distrOutDetails = distrOutDetailMapper.getDistrOutDetailList(saveReceivableInvoiceDetailVO.getBaseOrderId());
            Integer distrOutDetailsCount = receivableInvoiceMapper.getDistrOutDetailsCount(saveReceivableInvoiceDetailVO.getBaseOrderId(), DistrOutStatus.FINISHED);
            if (BigDecimal.valueOf(distrOutDetails.size()).compareTo(BigDecimal.valueOf(distrOutDetailsCount)) > 0) {
                distrOut.setStatus(DistrOutStatus.PART_BILL);//部分开票
            } else if (BigDecimal.valueOf(distrOutDetails.size()).compareTo(BigDecimal.valueOf(distrOutDetailsCount)) == 0) {
                distrOut.setStatus(DistrOutStatus.FINISHED);//已开票
            }
            UserEnterpriseUtils.setUserCreateOrModify(distrOut, loginUser, false);
            distrOutMapper.updateByPrimaryKeySelective(distrOut);

            receivableInvoiceDetailList.add(receivableInvoiceDetail);

            //更新上游单据状态
            updateBaseOrderStatus(distrOut, distrOutDetail, distrOutShelf, 0);
        }

        receivableInvoice.setQuantityTotal(quantityTotal);
        receivableInvoice.setVarietiesQuantity(varietiesQuantity);
        receivableInvoice.setAmountTotal(amountTotal);
        receivableInvoice.setNotaxAmountTotal(notaxAmountTotal);
        receivableInvoice.setTaxAmountTotal(taxAmountTotal);
        receivableInvoice.setDiffTaxAmountTotal(diffTaxAmountTotal);
        receivableInvoice.setDiffNotaxAmountTotal(diffNotaxAmountTotal);
        receivableInvoice.setDiffAmountTotal(diffAmountTotal);
        receivableInvoice.setUnclearAmountTotal(unclearAmountTotal);
        receivableInvoice.setClearAmountTotal(clearAmountTotal);
        UserEnterpriseUtils.setUserCreateOrModify(receivableInvoice, loginUser, false);
        receivableInvoiceMapper.updateByPrimaryKeySelective(receivableInvoice);

        //调用公用接口
        financeComponent.receivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(loginUser, receivableInvoice, receivableInvoiceDetailList, "save");

    }

    /**
     * @param distrOut
     * @param distrOutDetail
     * @param distrOutShelf
     * @param type           0:适用保存   1:适用冲销
     */
    private void updateBaseOrderStatus(DistrOut distrOut, DistrOutDetail distrOutDetail, DistrOutShelf distrOutShelf, Integer type) {

        PickDetail pickDetail = pickDetailMapper.selectByBaseOrderDtlId(distrOutDetail.getBaseOrderDtlId());

        PickShelf pickShelf = pickShelfMapper.getUpdatePickShelf(pickDetail.getId(), distrOutShelf.getGoodsId(), distrOutShelf.getLotId(), distrOutShelf.getShelfId());
        if (pickShelf == null) {
            throw new BusinessException(new StringBuilder("无法通过明细ID,商品ID,批号,货位ID,查询到拣货单货位明细单,参数：").append(pickDetail.getId() + "," + distrOutShelf.getGoodsId() + "," + distrOutShelf.getLotId() + "," + distrOutShelf.getShelfId()).toString());
        }

        if(type == 0) {
            if (distrOutShelf.getUnclearQuantity().compareTo(BigDecimal.ZERO) == 0) {
                pickShelf.setStatus(DistrSendStatus.FINISHED);
            } else {
                pickShelf.setStatus(DistrSendStatus.PART_BILL);
            }
        }else if(type == 1){
            pickShelf.setStatus(DistrSendStatus.WAIT_BILL);
        }

        pickShelfMapper.updateByPrimaryKeySelective(pickShelf);

        if (pickDetail == null) {
            throw new BusinessException("无法通过ID：" + distrOutDetail.getBaseOrderDtlId() + "查询到拣货单明细");
        }
        List<PickShelf> pickShelfList = pickShelfMapper.selectByDtlId(pickDetail.getId(), pickDetail.getPickId());
        Integer pickShelfNum = pickShelfMapper.getPickShelfStatus(pickDetail.getId(), DistrSendStatus.FINISHED);
        Integer pickShelfWaitNum = pickShelfMapper.getPickShelfStatus(pickDetail.getId(), DistrSendStatus.WAIT_BILL);
        if (pickShelfList.isEmpty()) {
            throw new BusinessException("无法通过ID：" + pickDetail.getId() + "查询到拣货单货位明细");
        }
        if (pickShelfList.size() == pickShelfWaitNum) {
            pickDetail.setStatus(DistrSendStatus.WAIT_BILL);
        } else if (pickShelfList.size() != pickShelfNum) {
            pickDetail.setStatus(DistrSendStatus.PART_BILL);
        } else {
            pickDetail.setStatus(DistrSendStatus.FINISHED);
        }
        pickDetailMapper.updateByPrimaryKeySelective(pickDetail);


        PickOrder pickOrder = pickOrderMapper.selectByBaseOrderId(distrOut.getBaseOrderId());
        if (pickOrder == null) {
            throw new BusinessException("无法通过ID：" + distrOut.getBaseOrderId() + "查询到拣货单");
        }
        List<PickDetail> pickDetailList = pickDetailMapper.selectByPickId(pickDetail.getPickId());
        Integer pickDetailNum = pickDetailMapper.getPickDeatilStatus(pickDetail.getPickId(), DistrSendStatus.FINISHED);
        Integer pickDetailWaitNum = pickDetailMapper.getPickDeatilStatus(pickDetail.getPickId(), DistrSendStatus.WAIT_BILL);
        if (pickDetailList.isEmpty()) {
            throw new BusinessException("无法通过ID：" + pickDetail.getPickId() + "查询到拣货单明细");
        }
        if (pickDetailList.size() == pickDetailWaitNum) {
            pickOrder.setStatus(DistrSendStatus.WAIT_BILL);
        } else if (pickDetailList.size() != pickDetailNum) {
            pickOrder.setStatus(DistrSendStatus.PART_BILL);
        } else {
            pickOrder.setStatus(DistrSendStatus.FINISHED);
        }
        pickOrderMapper.updateByPrimaryKeySelective(pickOrder);

        DistrSend distrSend = distrSendMapper.selectByPrimaryKey(distrOut.getBaseOrderId());
        if (distrSend == null) {
            throw new BusinessException("无法通过ID：" + distrOut.getBaseOrderId() + "查询到配货单");
        }
        distrSend.setStatus(pickOrder.getStatus());
        distrSendMapper.updateByPrimaryKeySelective(distrSend);

        DistrSendDetail distrSendDetail = distrSendDetailMapper.selectByPrimaryKey(pickDetail.getBaseOrderDtlId());
        if (distrSendDetail == null) {
            throw new BusinessException("无法通过ID：" + pickDetail.getBaseOrderDtlId() + "查询到配货单明细");
        }
        distrSendDetail.setStatus(pickDetail.getStatus());
        distrSendDetailMapper.updateByPrimaryKeySelective(distrSendDetail);

    }

    //获取采购验收单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO, Long supplierId) throws Exception {
        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.RECEIVABLE_INVOICE.getCodePrefix());
        draftCacheVO.setSupplierId(supplierId);
        return redisComponent.getDraftCacheVO(draftCacheVO);
    }

    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) throws Exception {
        if (draftCache.getOrderStr() == null || "".equals(draftCache.getOrderStr())) {
            throw new BusinessException("数据实体不能为空");
        }

        draftCache.setOrderCode(OrderRule.RECEIVABLE_INVOICE.getCodePrefix());

        draftCache.setEnterpriseId(userVO.getEnterpriseId());
        draftCache = redisComponent.saveDraftCacheVO(draftCache);
        return draftCache;
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue, Long supplierId) throws Exception {
        redisComponent.removeDraftCacheVO(supplierId, enterpriseId, type, redisKeyValue);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    public void getAlreadyWriteInvoice(UserVO userVO, Long id) throws Exception {

        //更改应收发票状态
        ReceivableInvoice receivableInvoice = receivableInvoiceMapper.selectByPrimaryKey(id);
        if (receivableInvoice == null) {
            throw new BusinessException("无效的应收发票ID：" + id);
        }
        if (receivableInvoice.getStatus() != ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus()) {
            throw new BusinessException("只允许待收款状态进行冲销");
        }
        receivableInvoice.setStatus(ReceivableInvoiceStatus.ALREADY_WRITE.getStatus());
        UserEnterpriseUtils.setUserCreateOrModify(receivableInvoice, userVO, false);
        receivableInvoiceMapper.updateByPrimaryKeySelective(receivableInvoice);

        List<ReceivableInvoiceDetail> receivableInvoiceDetailList = receivableInvoiceDetailMapper.getReceivableInvoiceDetailList(receivableInvoice.getId());
        if (receivableInvoiceDetailList.isEmpty()) {
            throw new BusinessException("没有查询到应收发票ID：" + receivableInvoice.getId() + "的明细数据");
        }
        for (ReceivableInvoiceDetail receivableInvoiceDetail : receivableInvoiceDetailList) {
            if (receivableInvoiceDetail.getStatus() == ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus()) {
                continue;
            }
            receivableInvoiceDetail.setStatus(ReceivableInvoiceStatus.ALREADY_WRITE.getStatus());
            UserEnterpriseUtils.setUserCreateOrModify(receivableInvoiceDetail, userVO, false);
            receivableInvoiceDetailMapper.updateByPrimaryKeySelective(receivableInvoiceDetail);

            //更新还原已清和未清数量
            DistrOutShelf distrOutShelf = distrOutShelfMapper.selectByPrimaryKey(receivableInvoiceDetail.getBaseShelfDtlId());
            if (distrOutShelf == null) {
                throw new BusinessException("没有查询到配货出库货位明细ID：" + receivableInvoiceDetail.getBaseShelfDtlId() + "的明细数据");
            }

            BigDecimal unclearQuantity = distrOutShelf.getUnclearQuantity().add(receivableInvoiceDetail.getQuantity());
            BigDecimal clearQuantity = distrOutShelf.getClearQuantity().subtract(receivableInvoiceDetail.getQuantity());
            if (clearQuantity.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("如果继续配货出库中货位明细已清数量会变为负数");
            }
            if (receivableInvoiceDetail.getQuantity().compareTo(distrOutShelf.getClearQuantity()) > 0) {
                throw new BusinessException("应收发票冲销明细ID：" + receivableInvoiceDetail.getId() + "冲销数量超出配货出库货位明细ID：" + receivableInvoiceDetail.getBaseShelfDtlId() + "的已清数量");
            }
            distrOutShelf.setUnclearQuantity(unclearQuantity);
            distrOutShelf.setClearQuantity(clearQuantity);
            UserEnterpriseUtils.setUserCreateOrModify(distrOutShelf, userVO, false);
            if(clearQuantity.compareTo(BigDecimal.ZERO) == 0) {
                distrOutShelf.setStatus(DistrOutStatus.WAIT_BILL);
            }else{
                distrOutShelf.setStatus(DistrOutStatus.PART_BILL);
            }
            distrOutShelfMapper.updateByPrimaryKeySelective(distrOutShelf);

            DistrOutDetail distrOutDetail = distrOutDetailMapper.selectByPrimaryKey(receivableInvoiceDetail.getBaseDtlId());
            if (distrOutDetail == null) {
                throw new BusinessException("没有查询到配货出库明细ID：" + receivableInvoiceDetail.getBaseDtlId() + "的明细数据");
            }
            BigDecimal unclearQuantityDtl = distrOutDetail.getUnclearQuantity().add(receivableInvoiceDetail.getQuantity());
            BigDecimal clearQuantityDtl = distrOutDetail.getClearQuantity().subtract(receivableInvoiceDetail.getQuantity());
            if (clearQuantityDtl.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("如果继续配货出库中明细已清数量会变为负数");
            }
            distrOutDetail.setUnclearQuantity(unclearQuantityDtl);
            distrOutDetail.setClearQuantity(clearQuantityDtl);
            distrOutDetailMapper.updateByPrimaryKeySelective(distrOutDetail);

            //
            //判断配货出库状态 (部分开票，已开票)
            List<DistrOutShelf> distrOutShelves = distrOutShelfMapper.getDistrOutShelfList(receivableInvoiceDetail.getBaseDtlId());
            Integer alreadyInvoiceCount = receivableInvoiceMapper.getDistrOutShelfCount(receivableInvoiceDetail.getBaseDtlId(), DistrOutStatus.FINISHED);
            Integer waitBillCount = receivableInvoiceMapper.getDistrOutShelfCount(receivableInvoiceDetail.getBaseDtlId(), DistrOutStatus.WAIT_BILL);

            if (BigDecimal.valueOf(distrOutShelves.size()).compareTo(BigDecimal.valueOf(waitBillCount)) == 0) {
                distrOutDetail.setStatus(DistrOutStatus.WAIT_BILL);//待开票
            } else {
                if (BigDecimal.valueOf(distrOutShelves.size()).compareTo(BigDecimal.valueOf(alreadyInvoiceCount)) > 0) {
                    distrOutDetail.setStatus(DistrOutStatus.PART_BILL);//部分开票
                } else if (BigDecimal.valueOf(distrOutShelves.size()).compareTo(BigDecimal.valueOf(alreadyInvoiceCount)) == 0) {
                    distrOutDetail.setStatus(DistrOutStatus.FINISHED);//已开票
                }
            }
            distrOutDetailMapper.updateByPrimaryKeySelective(distrOutDetail);

            DistrOut distrOut = distrOutMapper.selectByPrimaryKey(receivableInvoiceDetail.getBaseOrderId());
            List<DistrOutDetail> distrOutDetails = distrOutDetailMapper.getDistrOutDetailList(receivableInvoiceDetail.getBaseOrderId());
            Integer disAlreadyInvoiceCount = receivableInvoiceMapper.getDistrOutDetailsCount(receivableInvoiceDetail.getBaseOrderId(), DistrOutStatus.FINISHED);
            Integer disWaitBillCount = receivableInvoiceMapper.getDistrOutDetailsCount(receivableInvoiceDetail.getBaseOrderId(), DistrOutStatus.WAIT_BILL);
            if (BigDecimal.valueOf(distrOutDetails.size()).compareTo(BigDecimal.valueOf(disWaitBillCount)) == 0) {
                distrOut.setStatus(DistrOutStatus.WAIT_BILL);//待开票
            } else {
                if (BigDecimal.valueOf(distrOutDetails.size()).compareTo(BigDecimal.valueOf(disAlreadyInvoiceCount)) == 0) {
                    distrOut.setStatus(DistrOutStatus.FINISHED);//已开票
                } else if (BigDecimal.valueOf(distrOutDetails.size()).compareTo(BigDecimal.valueOf(disAlreadyInvoiceCount)) > 0) {
                    distrOut.setStatus(DistrOutStatus.PART_BILL);//部分开票
                }
            }
            distrOutMapper.updateByPrimaryKeySelective(distrOut);

            //更新上游单据状态
            updateBaseOrderStatus(distrOut, distrOutDetail, distrOutShelf, 1);
        }

        //调用公共接口
        financeComponent.receivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(userVO, receivableInvoice, receivableInvoiceDetailList, "writeOff");
    }

    @Override
    public List<CallDistrOutPageVO> getCallDistrOutPage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String orderName, String orderType, Long supplierId) {

        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd").format(startTime) + " 00:00:00";
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd").format(endTime) + " 23:59:59";
        }
        if (orderName != null && orderName.equals("baseOrderDate")) {
            orderName = "out_date";
        } else if (orderName != null && orderName.equals("baseOrderCode")) {
            orderName = "code";
        }

        List<Integer> status = new ArrayList<>();
        status.add(DistrOutStatus.WAIT_BILL);
        status.add(DistrOutStatus.PART_BILL);

        Integer count = receivableInvoiceMapper.getCallDistrOutPageCount(loginUser.getEnterpriseId(), startTimes, endTimes, orderName, orderType, ChainType.Division.getCode(), status, supplierId);
        List<CallDistrOutPageVO> callDistrOutPageVOS = receivableInvoiceMapper.getCallDistrOutPage(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes, orderName, orderType,
                ChainType.Division.getCode(), status, supplierId);
        page.setTotalRecord(count == null ? 0 : count.intValue());
        return callDistrOutPageVOS;
    }

    @Override
    public List<DistrOutPageVO> getCallDistrOutDeatil(UserVO loginUser, List<Long> ids) {

        List<Integer> status = new ArrayList<>();
        status.add(DistrOutStatus.WAIT_BILL);
        status.add(DistrOutStatus.PART_BILL);

        List<DistrOutPageVO> distrOutPageVOS = receivableInvoiceMapper.getCallDistrOutDeatil(loginUser.getEnterpriseId(), ids, status);
        return distrOutPageVOS;
    }

    @Override
    public List<InvoiceModifyRecordPageVO> getInvoiceModifyRecordPage(int pageNo, int pageSize, UserVO loginUser, Page page, Long id) {

        Integer count = receivableInvoiceInfoMapper.getInvoiceModifyRecordPageCount(loginUser.getEnterpriseId(), id);
        List<InvoiceModifyRecordPageVO> invoiceModifyRecordPageVOS = receivableInvoiceInfoMapper.getInvoiceModifyRecordPage(loginUser.getEnterpriseId(), page.getStart(), pageSize, id);
        page.setTotalRecord(count == null ? 0 : count.intValue());
        return invoiceModifyRecordPageVOS;
    }

    @Override
    public void exportExcel(OutputStream output, SaveReveivableInvoiceVO saveReveivableinvoice, UserVO loginUser) {

        //转换一下显示日期
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("baseOrderDate", "日期");
        map.put("baseOrderCode", "单号");
        map.put("goodsCode", "商品编码");
        map.put("barcode", "条形码");
        map.put("goodsGenericName", "通用名称");
        map.put("goodsName", "商品名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("approvalNumber", "批准文号");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("quantity", "数量");
        map.put("unitPrice", "单价");
        map.put("amount", "金额");
        map.put("taxRate", "税率");
        map.put("notaxAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("diffAmount", "金额差额");
        map.put("diffNotaxAmount", "不含税金额差额");
        map.put("diffTaxAmount", "税额差额");
        map.put("remark", "备注");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("购货单位编码:");
        titleSecondRow.append(saveReveivableinvoice.getPurchaseUnitCode() == null ? "" : saveReveivableinvoice.getPurchaseUnitCode());
        titleSecondRow.append("  购货单位名称:");
        titleSecondRow.append(saveReveivableinvoice.getPurchaseUnitName() == null ? "" : saveReveivableinvoice.getPurchaseUnitName());
        titleSecondRow.append("  开票日期:");
        titleSecondRow.append(saveReveivableinvoice.getBillDate() == null ? "" : DateUtils.DateToString(saveReveivableinvoice.getBillDate(),DateUtils.FMT_DATE));
        titleSecondRow.append("  开票人员:");
        titleSecondRow.append(saveReveivableinvoice.getBillManName() == null ? "" : saveReveivableinvoice.getBillManName());
        titleSecondRow.append("  单号:");
        titleSecondRow.append(saveReveivableinvoice.getCode() == null ? "" : saveReveivableinvoice.getCode());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(saveReveivableinvoice.getRemark() == null ? "" : saveReveivableinvoice.getRemark());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();

        //总数量
        BigDecimal quantity = BigDecimal.ZERO;//数量
        BigDecimal amount = BigDecimal.ZERO;//金额
        BigDecimal notaxAmount = BigDecimal.ZERO;//不含税金额
        BigDecimal taxAmount = BigDecimal.ZERO;//税额
        BigDecimal diffAmount = BigDecimal.ZERO;//金额差额
        BigDecimal diffNotaxAmount = BigDecimal.ZERO;//不含税金额差额
        BigDecimal diffTaxAmount = BigDecimal.ZERO;//税额差额

        for (SaveReceivableInvoiceDetailVO saveReceivableInvoiceDetailVO : saveReveivableinvoice.getSaveReceivableInvoiceDetailVO()) {
            quantity = quantity.add(saveReceivableInvoiceDetailVO.getQuantity());
            amount = amount.add(saveReceivableInvoiceDetailVO.getAmount());
            notaxAmount = notaxAmount.add(saveReceivableInvoiceDetailVO.getNotaxAmount());
            taxAmount = taxAmount.add(saveReceivableInvoiceDetailVO.getTaxAmount());
            diffAmount = diffAmount.add(saveReceivableInvoiceDetailVO.getDiffAmount());
            diffNotaxAmount = diffNotaxAmount.add(saveReceivableInvoiceDetailVO.getDiffNotaxAmount());
            diffTaxAmount = diffTaxAmount.add(saveReceivableInvoiceDetailVO.getDiffTaxAmount());
        }
        end.append(quantity);
        end.append(";");
        end.append(amount);
        end.append(";");
        end.append(notaxAmount);
        end.append(";");
        end.append(taxAmount);
        end.append(";");
        end.append(diffAmount);
        end.append(";");
        end.append(diffNotaxAmount);
        end.append(";");
        end.append(diffTaxAmount);

        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("quantity");
        needTotalName.add("amount");
        needTotalName.add("notaxAmount");
        needTotalName.add("taxAmount");
        needTotalName.add("diffAmount");
        needTotalName.add("diffNotaxAmount");
        needTotalName.add("diffTaxAmount");

        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(receivableInvoiceMapper.selectByPrimaryKey(saveReveivableinvoice.getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("应收发票");
        purchaseGeneralComponent.commExcelExport(output, map, saveReveivableinvoice.getSaveReceivableInvoiceDetailVO(), name, titleSecond, end.toString(), false, needTotalName);

    }

    @Override
    public List<InvoiceModifyRecordPageVO> getInvoiceModifyRecord(UserVO loginUser, Long id) {

        List<InvoiceModifyRecordPageVO> invoiceModifyRecordPageVOList = receivableInvoiceModifyRecordMapper.getInvoiceModifyRecord(loginUser.getEnterpriseId(), id);
        return invoiceModifyRecordPageVOList;
    }

    @Override
    public void exportExcelModifyRecord(OutputStream output, List<InvoiceModifyRecordPageVO> invoiceModifyRecordPage, UserVO loginUser) {

        //转换一下显示日期
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("updateTime", "修改时间");
        map.put("modifierName", "修改人员");
        map.put("reason", "修改原因");
        map.put("columnChName", "修改项目");
        map.put("oldContent", "原内容");
        map.put("newContent", "新内容");
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();

        List<String> needTotalName = new ArrayList<>();

        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(receivableInvoiceModifyRecordMapper.selectByPrimaryKey(invoiceModifyRecordPage.get(0).getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("修改记录");
        purchaseGeneralComponentModify.commExcelExport(output, map, invoiceModifyRecordPage, name, titleSecond, end.toString(), false, needTotalName);
    }

    @Override
    public SaveReveivableInvoiceVO calculationReveivableInvoice(UserVO loginUser, SaveReveivableInvoiceVO saveReveivableInvoiceVO) {
        if (saveReveivableInvoiceVO.getSaveReceivableInvoiceDetailVO().isEmpty()) {
            throw new BusinessException("没有有效的明细数据");
        }
        BigDecimal quantityTotal = BigDecimal.ZERO;
        Integer varietiesQuantity = saveReveivableInvoiceVO.getSaveReceivableInvoiceDetailVO().size();
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;

        for (SaveReceivableInvoiceDetailVO saveReceivableInvoiceDetailVO : saveReveivableInvoiceVO.getSaveReceivableInvoiceDetailVO()) {

            //计算-------------------start--------------------------------------------
            // 发票数量 * 发票单价 = 发票金额
            BigDecimal amount = saveReceivableInvoiceDetailVO.getQuantity().multiply(saveReceivableInvoiceDetailVO.getUnitPrice());
            // 发票金额 / 税率 = 不含税单价
            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(saveReceivableInvoiceDetailVO.getTaxRateId());
            BigDecimal notaxPrice = amount.divide(new BigDecimal(1).add(goodsTaxRate.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
            // 不含税单价 * 发票数量 = 不含税金额
            BigDecimal notaxAmount = notaxPrice.multiply(saveReceivableInvoiceDetailVO.getQuantity());
            // 发票金额 - 不含税金额 = 税额
            BigDecimal taxAmount = amount.subtract(notaxAmount);


            //发票单价*发票数量
            BigDecimal invoiceAmount = saveReceivableInvoiceDetailVO.getUnitPrice().multiply(saveReceivableInvoiceDetailVO.getQuantity());
            //配货出库单价*发票数量
            BigDecimal distrOutAmount = saveReceivableInvoiceDetailVO.getBaseOrderUnitPrice().multiply(saveReceivableInvoiceDetailVO.getQuantity());
            //(发票单价*发票数量)/发票税率
            BigDecimal invoiceDiffNotaxTotal = saveReceivableInvoiceDetailVO.getUnitPrice().multiply(saveReceivableInvoiceDetailVO.getQuantity()).divide(new BigDecimal(1).add(goodsTaxRate.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
            //(配货出库不含税实际单价*发票数量)/配货出库税率
            BigDecimal distrOutDiffNotaxTotal = saveReceivableInvoiceDetailVO.getBaseOrderNotaxPrice().multiply(saveReceivableInvoiceDetailVO.getQuantity()).divide(new BigDecimal(1).add(saveReceivableInvoiceDetailVO.getBaseOrderTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);

            // 配货出库单价*发票数量 - 发票单价*发票数量 = 金额差额
            BigDecimal diffAmount = distrOutAmount.subtract(invoiceAmount);
            // (配货出库不含税实际单价*发票数量)/配货出库税率 - (发票单价*发票数量)/发票税率 = 不含税金额差额
            BigDecimal diffNotaxAmount = distrOutDiffNotaxTotal.subtract(invoiceDiffNotaxTotal);
            // (配货出库单价*发票数量 - (配货出库不含税实际单价*发票数量)/配货出库税率) - (发票单价*发票数量 - (发票单价*发票数量)/发票税率) = 税额差额
            BigDecimal diffTaxAmount = distrOutAmount.subtract(distrOutDiffNotaxTotal).subtract(invoiceAmount.subtract(invoiceDiffNotaxTotal));
            //计算-------------------end--------------------------------------------

            saveReceivableInvoiceDetailVO.setAmount(amount);//发票金额
            saveReceivableInvoiceDetailVO.setNotaxPrice(notaxPrice);//不含税单价
            saveReceivableInvoiceDetailVO.setNotaxAmount(notaxAmount);//不含税金额
            saveReceivableInvoiceDetailVO.setTaxAmount(taxAmount);//税额

            saveReceivableInvoiceDetailVO.setDiffAmount(diffAmount);//金额差额合计
            saveReceivableInvoiceDetailVO.setDiffNotaxAmount(diffNotaxAmount);//不含税金额差额合计
            saveReceivableInvoiceDetailVO.setDiffTaxAmount(diffTaxAmount);//税额差额合计

            quantityTotal = quantityTotal.add(saveReceivableInvoiceDetailVO.getQuantity());
            amountTotal = amountTotal.add(saveReceivableInvoiceDetailVO.getAmount());
            notaxAmountTotal = notaxAmountTotal.add(saveReceivableInvoiceDetailVO.getNotaxAmount());
            taxAmountTotal = taxAmountTotal.add(saveReceivableInvoiceDetailVO.getTaxAmount());

        }
        saveReveivableInvoiceVO.setQuantityTotal(quantityTotal);
        saveReveivableInvoiceVO.setVarietiesQuantity(varietiesQuantity);
        saveReveivableInvoiceVO.setAmountTotal(amountTotal);
        saveReveivableInvoiceVO.setNotaxAmountTotal(notaxAmountTotal);
        saveReveivableInvoiceVO.setTaxAmountTotal(taxAmountTotal);

        return saveReveivableInvoiceVO;
    }

    @Override
    public SaveReceivableInvoiceInfoVO getSaveReveivableInvoiceInfo(UserVO userVO, Long supplierId) throws Exception {

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
        if (enterprise == null) {
            throw new BusinessException("无效的企业ID：" + userVO.getEnterpriseId());
        }
        SaveReceivableInvoiceInfoVO saveReceivableInvoiceInfoVO = new SaveReceivableInvoiceInfoVO();
        saveReceivableInvoiceInfoVO.setCompanyName(enterprise.getName());
        saveReceivableInvoiceInfoVO.setInvoiceType(AdvanceInvoiceType.TYPE_0.getCode());
        saveReceivableInvoiceInfoVO.setInvoiceTypeName(AdvanceInvoiceType.TYPE_0.getName());
        if (enterprise.getCompanyAddress() != null) {
            saveReceivableInvoiceInfoVO.setCompanyAddress(enterprise.getCompanyAddress());
        }
        if (enterprise.getTel() != null) {
            saveReceivableInvoiceInfoVO.setCompanyTelephone(enterprise.getTel());
        }
        if (enterprise.getBankAccountName() != null) {
            saveReceivableInvoiceInfoVO.setCompanyAccountName(enterprise.getBankAccountName());
        }
        if (enterprise.getBankName() != null) {
            saveReceivableInvoiceInfoVO.setCompanyAccountBank(enterprise.getBankName());
        }
        if (enterprise.getBankAccount() != null) {
            saveReceivableInvoiceInfoVO.setCompanyAccount(enterprise.getBankAccount());
        }

        if (supplierId != null) {
            enterprise = enterpriseMapper.selectByPrimaryKey(supplierId);
            if (enterprise == null) {
                throw new BusinessException("无效的购货单位企业ID：" + supplierId);
            }
            if (enterprise.getBankAccountName() != null) {
                saveReceivableInvoiceInfoVO.setAccountName(enterprise.getBankAccountName());
            }
            if (enterprise.getBankName() != null) {
                saveReceivableInvoiceInfoVO.setAccountBank(enterprise.getBankName());
            }
            if (enterprise.getBankAccount() != null) {
                saveReceivableInvoiceInfoVO.setAccount(enterprise.getBankAccount());
            }
            if (enterprise.getCompanyAddress() != null) {
                saveReceivableInvoiceInfoVO.setAddress(enterprise.getCompanyAddress());
            }
            if (enterprise.getTel() != null) {
                saveReceivableInvoiceInfoVO.setTelephone(enterprise.getTel());
            }
        }
        return saveReceivableInvoiceInfoVO;
    }
}
