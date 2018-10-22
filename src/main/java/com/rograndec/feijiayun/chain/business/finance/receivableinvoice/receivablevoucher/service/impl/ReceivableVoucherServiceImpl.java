package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao.ReceivableVoucherDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao.ReceivableVoucherMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao.ReceivableVoucherModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucher;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucherDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucherModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.service.ReceivableVoucherService;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo.*;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import com.rograndec.feijiayun.chain.common.constant.status.ReceivableVoucherStatus;
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
public class ReceivableVoucherServiceImpl implements ReceivableVoucherService {

    @Autowired
    private ReceivableVoucherMapper receivableVoucherMapper;
    @Autowired
    private ReceivableVoucherDetailMapper receivableVoucherDetailMapper;
    @Autowired
    private ReceivableVoucherModifyRecordMapper receivableVoucherModifyRecordMapper;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent<SaveReceivableVoucherDetailVO> purchaseGeneralComponent;
    @Autowired
    PurchaseGeneralComponent<VoucherModifyRecordPageVO> purchaseGeneralComponentModify;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DistrReturnInDetailMapper distrReturnInDetailMapper;
    @Autowired
    private DistrReturnInShelfMapper distrReturnInShelfMapper;
    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;
    @Autowired
    private DistrReturnInMapper distrReturnInMapper;
    @Autowired
    private FinanceComponent financeComponent;
    @Autowired
    private DistrReturnCheckMapper distrReturnCheckMapper;
    @Autowired
    private DistrReturnCheckDetailMapper distrReturnCheckDetailMapper;
    @Autowired
    private DistrReturnCheckLotMapper distrReturnCheckLotMapper;
    @Autowired
    private DistrReturnReceiveMapper distrReturnReceiveMapper;
    @Autowired
    private DistrReturnReceiveDetailMapper distrReturnReceiveDetailMapper;
    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;
    @Autowired
    private DistrReturnNoticeDetailMapper distrReturnNoticeDetailMapper;

    @Override
    public TabableTotalVoucherVO getReceivableVoucherPage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String purchaseUnitCode, String purchaseUnitName,
                                                          String code, String postManName, Integer status, String orderName, String orderType) {

        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd").format(startTime) + " 00:00:00";
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd").format(endTime) + " 23:59:59";
        }
        if (orderName != null && orderName.equals("postDate"))
            orderName = "post_date";

        TabableTotalVoucherVO tabableTotalVoucherVO = new TabableTotalVoucherVO();
        BigDecimal tabAmountTotal = BigDecimal.ZERO;
        BigDecimal tabNotaxAmountTotal = BigDecimal.ZERO;
        BigDecimal tabTaxAmountTotal = BigDecimal.ZERO;

        Integer count = receivableVoucherMapper.getReceivableVoucherPageCount(loginUser.getEnterpriseId(), startTimes, endTimes, purchaseUnitCode, purchaseUnitName, code, postManName, status);
        List<ReveivableVoucherPageVO> reveivableVoucherPageVOList = receivableVoucherMapper.getReceivableVoucherPage(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes, purchaseUnitCode,
                purchaseUnitName, code, postManName, status, orderName, orderType);
        if (!reveivableVoucherPageVOList.isEmpty()) {
            for (ReveivableVoucherPageVO reveivableVoucherPageVO : reveivableVoucherPageVOList) {
                reveivableVoucherPageVO.setStatusName(ReceivableVoucherStatus.getName(reveivableVoucherPageVO.getStatus()));
                tabAmountTotal = tabAmountTotal.add(reveivableVoucherPageVO.getAmountTotal());
                tabNotaxAmountTotal = tabNotaxAmountTotal.add(reveivableVoucherPageVO.getNotaxAmountTotal());
                tabTaxAmountTotal = tabTaxAmountTotal.add(reveivableVoucherPageVO.getTaxAmountTotal());
            }
            tabableTotalVoucherVO.setTabAmountTotal(tabAmountTotal);
            tabableTotalVoucherVO.setTabNotaxAmountTotal(tabNotaxAmountTotal);
            tabableTotalVoucherVO.setTabTaxAmountTotal(tabTaxAmountTotal);
            tabableTotalVoucherVO.setReveivableVoucherPageVO(reveivableVoucherPageVOList);
        } else {
            tabableTotalVoucherVO.setTabAmountTotal(tabAmountTotal);
            tabableTotalVoucherVO.setTabNotaxAmountTotal(tabNotaxAmountTotal);
            tabableTotalVoucherVO.setTabTaxAmountTotal(tabTaxAmountTotal);
        }

        page.setTotalRecord(count == null ? 0 : count.intValue());
        return tabableTotalVoucherVO;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Throwable.class)
    public String saveReveivableVoucher(UserVO loginUser, SaveReveivableVoucherVO saveReveivableVoucherVO) throws Exception {

        if (saveReveivableVoucherVO.getId() != null) {//更新

            if(saveReveivableVoucherVO.getUpdateReason() == null){
                throw new BusinessException("请填写修改原因");
            }

            if (saveReveivableVoucherVO.getStatus() == ReceivableVoucherStatus.ALREADY_WRITE.getStatus()) {
                throw new BusinessException("无法修改已冲销的应收贷项凭证");
            }

            ReceivableVoucher receivableVoucher = receivableVoucherMapper.selectByPrimaryKey(saveReveivableVoucherVO.getId());
            if (receivableVoucher == null) {
                throw new BusinessException("无效的应收贷项凭证ID：" + saveReveivableVoucherVO.getId());
            }

            //插入修改记录表
            User user = userMapper.selectByPrimaryKey(saveReveivableVoucherVO.getPostManId());
            if(user == null){
                throw new BusinessException("无效的人员ID：" + saveReveivableVoucherVO.getPostManId());
            }
            saveReveivableVoucherVO.setPostManCode(user.getCode());
            saveReveivableVoucherVO.setPostManName(user.getName());
            //修改主表
            SaveReveivableVoucherVO oldReveivableVoucherVO = receivableVoucherMapper.getReceivableVoucher(saveReveivableVoucherVO.getId());
            List<ReceivableVoucherModifyRecord> receivableVoucherModifyRecords = getReceivableVoucherModifyRecordList(loginUser, saveReveivableVoucherVO, oldReveivableVoucherVO);
            for (ReceivableVoucherModifyRecord ur : receivableVoucherModifyRecords) {
                ur.setRemark(saveReveivableVoucherVO.getUpdateReason());
                ur.setReason(saveReveivableVoucherVO.getUpdateReason());
                receivableVoucherModifyRecordMapper.insertSelective(ur);
            }


            //修改主表
            receivableVoucher.setPostDate(saveReveivableVoucherVO.getPostDate());
            receivableVoucher.setPostManId(saveReveivableVoucherVO.getPostManId());
            receivableVoucher.setPostManCode(user.getCode());
            receivableVoucher.setPostManName(user.getName());
            if (saveReveivableVoucherVO.getRemark() != null) {
                receivableVoucher.setRemark(saveReveivableVoucherVO.getRemark());
            }
            UserEnterpriseUtils.setUserCreateOrModify(receivableVoucher, loginUser, false);
            receivableVoucherMapper.updateByPrimaryKeySelective(receivableVoucher);

            return "单号：" + receivableVoucher.getCode();

        } else {//新增

            //删除缓存
            if (saveReveivableVoucherVO.getRedisKeyValue() != null) {
                redisComponent.removeDraftCacheVO(saveReveivableVoucherVO.getPurchaseUnitId(), loginUser.getEnterpriseId(), OrderRule.RECEIVABLE_VOUCHER.getCodePrefix(), saveReveivableVoucherVO.getRedisKeyValue());
            }

            ReceivableVoucher receivableVoucher = new ReceivableVoucher();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveReveivableVoucherVO, receivableVoucher);
            receivableVoucher.setId(null);
            receivableVoucher.setEnterpriseId(loginUser.getEnterpriseId());
            receivableVoucher.setParentId(loginUser.getParentId());
            UserEnterpriseUtils.setUserCreateOrModify(receivableVoucher, loginUser, true);
            receivableVoucher.setFinanceAccountType(FinanceAccountType.LEAGUE.getType());

            receivableVoucher.setCode(getCode(OrderRule.RECEIVABLE_VOUCHER.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
            receivableVoucher.setOrderType(OrderRule.RECEIVABLE_VOUCHER.getType());

            if (receivableVoucher.getPostManId() == null) {
                throw new BusinessException("缺少必传字段过账人员ID");
            }
            User user = userMapper.selectByPrimaryKey(receivableVoucher.getPostManId());
            if (user == null) {
                throw new BusinessException("无效的过账人员ID：" + receivableVoucher.getPostManId());
            }
            receivableVoucher.setPostManCode(user.getCode());
            receivableVoucher.setPostManName(user.getName());

            if (receivableVoucher.getPurchaseUnitId() == null) {
                throw new BusinessException("缺少必传字段购货单位ID");
            }
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(receivableVoucher.getPurchaseUnitId());
            if (enterprise == null) {
                throw new BusinessException("无效的购货单位ID：" + receivableVoucher.getPurchaseUnitId());
            }
            receivableVoucher.setPurchaseUnitCode(enterprise.getCode());
            receivableVoucher.setPurchaseUnitName(enterprise.getName());

            receivableVoucher.setQuantityTotal(BigDecimal.ZERO);
            receivableVoucher.setVarietiesQuantity(0);
            receivableVoucher.setAmountTotal(BigDecimal.ZERO);
            receivableVoucher.setNotaxAmountTotal(BigDecimal.ZERO);
            receivableVoucher.setTaxAmountTotal(BigDecimal.ZERO);
            receivableVoucher.setDiffAmountTotal(BigDecimal.ZERO);
            receivableVoucher.setDiffNotaxAmountTotal(BigDecimal.ZERO);
            receivableVoucher.setDiffTaxAmountTotal(BigDecimal.ZERO);
            receivableVoucher.setUnclearAmountTotal(BigDecimal.ZERO);
            receivableVoucher.setClearAmountTotal(BigDecimal.ZERO);

            receivableVoucher.setStatus(ReceivableVoucherStatus.WAIT_PAYMENT.getStatus());
            receivableVoucherMapper.insertSelective(receivableVoucher);

            saveReveivableVoucherDetail(loginUser, receivableVoucher, saveReveivableVoucherVO);

            return "单号：" + receivableVoucher.getCode();
        }

    }

    private List<ReceivableVoucherModifyRecord> getReceivableVoucherModifyRecordList(UserVO loginUser, SaveReveivableVoucherVO saveReveivableVoucherVO, SaveReveivableVoucherVO oldReveivableVoucherVO) throws Exception {

        Map<String, Object> newVoucherMap = getFieldsMap(saveReveivableVoucherVO);
        Map<String, Object> oldVoucherMap = getFieldsMap(oldReveivableVoucherVO);

        Map<String, String> voucherFieldSkipMap = invoiceFieldSkipMap();

        List<ReceivableVoucherModifyRecord> receivableVoucherModifyRecords = new ArrayList<>();
        receivableVoucherModifyRecords = getModifyRecordList(loginUser, oldReveivableVoucherVO, "saas_receivable_voucher", newVoucherMap, oldVoucherMap, voucherFieldSkipMap, receivableVoucherModifyRecords);

        return receivableVoucherModifyRecords;
    }

    private List<ReceivableVoucherModifyRecord> getModifyRecordList(UserVO loginUser, SaveReveivableVoucherVO oldReveivableVoucherVO, String tableName, Map<String, Object> newVoucherMap,
                                                                    Map<String, Object> oldVoucherMap, Map<String, String> fieldMap, List<ReceivableVoucherModifyRecord> receivableVoucherModifyRecords) {

        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            Object newObj = newVoucherMap.get(entry.getKey());
            Object obj = oldVoucherMap.get(entry.getKey());
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
                        excuteModifyRecord(loginUser, oldReveivableVoucherVO, tableName, receivableVoucherModifyRecords, entry, obj, newObj);
                    }
                } else if (!obj.equals(newObj)) {
                    excuteModifyRecord(loginUser, oldReveivableVoucherVO, tableName, receivableVoucherModifyRecords, entry, obj, newObj);
                }
            }
            if (obj == null && newObj != null) {
                excuteModifyRecord(loginUser, oldReveivableVoucherVO, tableName, receivableVoucherModifyRecords, entry, obj, newObj);
            }
        }


        return receivableVoucherModifyRecords;
    }

    private void excuteModifyRecord(UserVO loginUser, SaveReveivableVoucherVO oldReveivableVoucherVO, String tableName, List<ReceivableVoucherModifyRecord> receivableVoucherModifyRecords, Map.Entry<String, String> entry, Object obj, Object newObj) {

        ReceivableVoucherModifyRecord receivableVoucherModifyRecord = new ReceivableVoucherModifyRecord();
        receivableVoucherModifyRecord.setEnterpriseId(loginUser.getEnterpriseId());
        receivableVoucherModifyRecord.setParentId(loginUser.getParentId());
        receivableVoucherModifyRecord.setTableName(tableName);
        receivableVoucherModifyRecord.setKeyId(oldReveivableVoucherVO.getId());
        receivableVoucherModifyRecord.setColumnEnName(entry.getKey());
        receivableVoucherModifyRecord.setColumnChName(entry.getValue());
        receivableVoucherModifyRecord.setUpdateTime(new Date());
        receivableVoucherModifyRecord.setCreaterId(loginUser.getUserId());
        receivableVoucherModifyRecord.setCreaterCode(loginUser.getUserCode());
        receivableVoucherModifyRecord.setCreaterName(loginUser.getUserName());
        receivableVoucherModifyRecord.setModifierId(loginUser.getUserId());
        receivableVoucherModifyRecord.setModifierCode(loginUser.getUserCode());
        receivableVoucherModifyRecord.setModifierName(loginUser.getUserName());
        receivableVoucherModifyRecord.setCreateTime(new Date());
        receivableVoucherModifyRecord.setOldContent(obj == null ? "" : obj.toString());
        receivableVoucherModifyRecord.setNewContent(newObj == null ? "" : newObj.toString());

        receivableVoucherModifyRecords.add(receivableVoucherModifyRecord);
    }


    private void saveReveivableVoucherDetail(UserVO loginUser, ReceivableVoucher receivableVoucher, SaveReveivableVoucherVO saveReveivableVoucherVO) throws Exception {

        if (saveReveivableVoucherVO.getSaveReceivableVoucherDetailVO().isEmpty()) {
            throw new BusinessException("缺少必传应收贷项凭证明细数据");
        }

        BigDecimal quantityTotal = BigDecimal.ZERO;
        Integer varietiesQuantity = saveReveivableVoucherVO.getSaveReceivableVoucherDetailVO().size();
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

        List<ReceivableVoucherDetail> receivableVoucherDetailList = new ArrayList<>();
        for (SaveReceivableVoucherDetailVO saveReceivableVoucherDetailVO : saveReveivableVoucherVO.getSaveReceivableVoucherDetailVO()) {

            if (saveReceivableVoucherDetailVO.getBaseOrderId() == null || saveReceivableVoucherDetailVO.getBaseOrderType() == null ||
                    saveReceivableVoucherDetailVO.getBaseDtlId() == null || saveReceivableVoucherDetailVO.getBaseShelfDtlId() == null) {
                throw new BusinessException("缺少必传上级引用单据数据");
            }
            if(saveReceivableVoucherDetailVO.getBaseOrderTaxRate() == null || saveReceivableVoucherDetailVO.getBaseOrderNotaxPrice() == null ||
                    saveReceivableVoucherDetailVO.getBaseOrderUnitPrice() == null || saveReceivableVoucherDetailVO.getBaseOrderAmount() == null ||
                    saveReceivableVoucherDetailVO.getBaseOrderNotaxAmount() == null || saveReceivableVoucherDetailVO.getBaseOrderTaxAmount() == null){
                throw new BusinessException("缺少必要的原始单据价格等信息");
            }
            if(saveReceivableVoucherDetailVO.getQuantity() == null || saveReceivableVoucherDetailVO.getUnitPrice() == null ||
                    saveReceivableVoucherDetailVO.getTaxRateId() == null){
                throw new BusinessException("缺少必要的单据价格等信息");
            }
            ReceivableVoucherDetail receivableVoucherDetail = new ReceivableVoucherDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveReceivableVoucherDetailVO, receivableVoucherDetail);
            receivableVoucherDetail.setId(null);
            receivableVoucherDetail.setEnterpriseId(loginUser.getEnterpriseId());
            receivableVoucherDetail.setParentId(loginUser.getParentId());
            UserEnterpriseUtils.setUserCreateOrModify(receivableVoucherDetail, loginUser, true);

            receivableVoucherDetail.setVoucherId(receivableVoucher.getId());

            if (saveReceivableVoucherDetailVO.getBaseOrderType().intValue() != OrderRule.DISTR_RETURN_IN.getType()) {
                throw new BusinessException("单据类型不是配后退回入库类型ID");
            }
            DistrReturnInDetail distrReturnInDetail = distrReturnInDetailMapper.selectByPrimaryKey(saveReceivableVoucherDetailVO.getBaseDtlId());
            if (distrReturnInDetail == null) {
                throw new BusinessException("无效的配后退回入库明细ID：" + saveReceivableVoucherDetailVO.getBaseDtlId());
            }
            receivableVoucherDetail.setBaseOrderCode(distrReturnInDetail.getReturnInCode());
            receivableVoucherDetail.setBaseOrderDate(distrReturnInDetail.getReturnInDate());

            receivableVoucherDetail.setGoodsId(distrReturnInDetail.getGoodsId());
            receivableVoucherDetail.setGoodsCode(distrReturnInDetail.getGoodsCode());
            receivableVoucherDetail.setGoodsName(distrReturnInDetail.getGoodsName());
            receivableVoucherDetail.setGoodsGenericName(distrReturnInDetail.getGoodsGenericName());
            receivableVoucherDetail.setDosageId(distrReturnInDetail.getDosageId());
            receivableVoucherDetail.setDosageName(distrReturnInDetail.getDosageName());
            receivableVoucherDetail.setUnitId(distrReturnInDetail.getUnitId());
            receivableVoucherDetail.setUnitName(distrReturnInDetail.getUnitName());

            if (distrReturnInDetail.getBarcode() != null) {
                receivableVoucherDetail.setBarcode(distrReturnInDetail.getBarcode());
            }
            if (distrReturnInDetail.getGoodsSpecification() != null) {
                receivableVoucherDetail.setGoodsSpecification(distrReturnInDetail.getGoodsSpecification());
            }
            if (distrReturnInDetail.getManufacturerId() != null) {
                receivableVoucherDetail.setManufacturerId(distrReturnInDetail.getManufacturerId());
            }
            if (distrReturnInDetail.getManufacturer() != null) {
                receivableVoucherDetail.setManufacturer(distrReturnInDetail.getManufacturer());
            }
            if (distrReturnInDetail.getGoodsPlace() != null) {
                receivableVoucherDetail.setGoodsPlace(distrReturnInDetail.getGoodsPlace());
            }
            if (distrReturnInDetail.getApprovalNumber() != null) {
                receivableVoucherDetail.setApprovalNumber(distrReturnInDetail.getApprovalNumber());
            }

            DistrReturnInShelf distrReturnInShelf = distrReturnInShelfMapper.selectByPrimaryKey(saveReceivableVoucherDetailVO.getBaseShelfDtlId());
            if (distrReturnInShelf == null) {
                throw new BusinessException("无效的配后退回入库货位明细ID：" + saveReceivableVoucherDetailVO.getBaseShelfDtlId());
            }
            if (saveReceivableVoucherDetailVO.getQuantity().compareTo(distrReturnInShelf.getUnclearQuantity()) > 0) {
                throw new BusinessException("填写数量超出原始单据未清数量");
            }
            receivableVoucherDetail.setLotNumber(distrReturnInShelf.getLotNumber());
            receivableVoucherDetail.setProductDate(distrReturnInShelf.getProductDate());
            receivableVoucherDetail.setValidDate(distrReturnInShelf.getValidDate());
            receivableVoucherDetail.setQuantity(saveReceivableVoucherDetailVO.getQuantity());
            receivableVoucherDetail.setUnitPrice(saveReceivableVoucherDetailVO.getUnitPrice());
            receivableVoucherDetail.setTaxRateId(saveReceivableVoucherDetailVO.getTaxRateId());

            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(saveReceivableVoucherDetailVO.getTaxRateId());
            if (goodsTaxRate == null) {
                throw new BusinessException("无效的税率ID：" + saveReceivableVoucherDetailVO.getTaxRateId());
            }
            receivableVoucherDetail.setTaxRate(goodsTaxRate.getTaxRate());

            receivableVoucherDetail.setStatus(ReceivableVoucherStatus.WAIT_PAYMENT.getStatus());

            if (saveReceivableVoucherDetailVO.getUnitPrice().compareTo(distrReturnInShelf.getUnitPrice()) == 0 && saveReceivableVoucherDetailVO.getTaxRateId() == distrReturnInShelf.getTaxRateId()) {//如果单价都一致  没有差额  0.00

                receivableVoucherDetail.setDiffAmount(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));//金额差额
                receivableVoucherDetail.setDiffTaxAmount(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));//税额差额
                receivableVoucherDetail.setDiffNotaxAmount(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP));//不含税金额差额

            } else {

                //计算-------------------start--------------------------------------------
                //发票单价*发票数量
                invoiceAmount = saveReceivableVoucherDetailVO.getUnitPrice().multiply(saveReceivableVoucherDetailVO.getQuantity());

                //配后退回入库单价*发票数量
                distrOutAmount = distrReturnInShelf.getRealPrice().multiply(saveReceivableVoucherDetailVO.getQuantity());

                //(发票单价*发票数量)/发票税率
                invoiceDiffNotaxTotal = (saveReceivableVoucherDetailVO.getUnitPrice().multiply(saveReceivableVoucherDetailVO.getQuantity())).divide(new BigDecimal(1).add(goodsTaxRate.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);

                //(配后退回入库不含税实际单价*发票数量)/配后退回入库税率
                distrOutDiffNotaxTotal = (distrReturnInShelf.getRealPrice().multiply(saveReceivableVoucherDetailVO.getQuantity())).divide(new BigDecimal(1).add(distrReturnInShelf.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
                //计算-------------------end--------------------------------------------


                // (发票单价*发票数量)/发票税率 - (配后退回入库不含税实际单价*发票数量)/配后退回入库税率 = 不含税金额差额
                receivableVoucherDetail.setDiffNotaxAmount(invoiceDiffNotaxTotal.subtract(distrOutDiffNotaxTotal));//不含税金额差额合计
                // 发票单价*发票数量 - 配后退回入库单价*发票数量  = 金额差额
                receivableVoucherDetail.setDiffAmount(invoiceAmount.subtract(distrOutAmount));//金额差额合计
                // (发票单价*发票数量 - (发票单价*发票数量)/发票税率) - (配后退回入库单价*发票数量 - (配后退回入库不含税实际单价*发票数量)/配后退回入库税率) = 税额差额
                receivableVoucherDetail.setDiffTaxAmount((invoiceAmount.subtract(invoiceDiffNotaxTotal)).subtract(distrOutAmount.subtract(distrOutDiffNotaxTotal)));//税额差额合计
            }
            // 发票数量 * 发票单价 = 发票金额
            BigDecimal amount = saveReceivableVoucherDetailVO.getQuantity().multiply(saveReceivableVoucherDetailVO.getUnitPrice());
            // （发票金额 / 税率）/数量 = 不含税单价
            BigDecimal notaxPrice = (amount.divide(new BigDecimal(1).add(goodsTaxRate.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 6, BigDecimal.ROUND_HALF_UP)).divide(saveReceivableVoucherDetailVO.getQuantity(),6, BigDecimal.ROUND_HALF_UP);
            // 不含税单价 * 发票数量 = 不含税金额
            BigDecimal notaxAmount = notaxPrice.multiply(saveReceivableVoucherDetailVO.getQuantity());
            // 发票金额 - 不含税金额 = 税额
            BigDecimal taxAmount = amount.subtract(notaxAmount);
            receivableVoucherDetail.setAmount(amount);//发票金额
            receivableVoucherDetail.setNotaxPrice(notaxPrice);//不含税单价
            receivableVoucherDetail.setNotaxAmount(notaxAmount);//不含税金额
            receivableVoucherDetail.setTaxAmount(taxAmount);//税额

            receivableVoucherDetailMapper.insertSelective(receivableVoucherDetail);

            //合计
            quantityTotal = quantityTotal.add(saveReceivableVoucherDetailVO.getQuantity());
            amountTotal = amountTotal.add(saveReceivableVoucherDetailVO.getAmount());
            notaxAmountTotal = notaxAmountTotal.add(saveReceivableVoucherDetailVO.getNotaxAmount());
            taxAmountTotal = taxAmountTotal.add(saveReceivableVoucherDetailVO.getTaxAmount());
            unclearAmountTotal = unclearAmountTotal.add(saveReceivableVoucherDetailVO.getQuantity().multiply(saveReceivableVoucherDetailVO.getUnitPrice()));


            //发票单价*发票数量 - 配后退回入库单价*发票数量 = 金额差额
            diffAmountTotal = diffAmountTotal.add(
                    invoiceAmount.subtract(distrOutAmount)
            );

            //(发票单价*发票数量)/发票税率 - (配后退回入库不含税实际单价*发票数量)/配后退回入库税率  = 不含税金额差额
            diffNotaxAmountTotal = diffNotaxAmountTotal.add(
                    invoiceDiffNotaxTotal.subtract(distrOutDiffNotaxTotal)
            );

            //(发票单价*发票数量 - (发票单价*发票数量)/发票税率) - (配后退回入库单价*发票数量 - (配后退回入库不含税实际单价*发票数量)/配后退回入库税率) = 税额差额
            diffTaxAmountTotal = diffTaxAmountTotal.add(
                    (invoiceAmount.subtract(invoiceDiffNotaxTotal)).subtract(distrOutAmount.subtract(distrOutDiffNotaxTotal))
            );

            //更新配后退回入库未清，已清数量
            //未清
            BigDecimal unclearQuantity = distrReturnInShelf.getUnclearQuantity().subtract(saveReceivableVoucherDetailVO.getQuantity());
            if (unclearQuantity.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("如果继续配后退回入库中货位明细未清数量会变为负数");
            }
            //已清
            BigDecimal clearQuantity = distrReturnInShelf.getClearQuantity().add(saveReceivableVoucherDetailVO.getQuantity());
            if (clearQuantity.compareTo(distrReturnInShelf.getQuantity()) > 0) {
                throw new BusinessException("如果继续配后退回入库中货位明细已清数量会超出原有单据数量");
            }
            distrReturnInShelf.setUnclearQuantity(unclearQuantity);
            distrReturnInShelf.setClearQuantity(clearQuantity);
            if(unclearQuantity.compareTo(BigDecimal.ZERO) == 0 ) {
                distrReturnInShelf.setStatus(DistrReturnStatus.FINISHED);
            }else {
                distrReturnInShelf.setStatus(DistrReturnStatus.PART_BILL);
            }
            UserEnterpriseUtils.setUserCreateOrModify(distrReturnInShelf, loginUser, false);
            distrReturnInShelfMapper.updateByPrimaryKeySelective(distrReturnInShelf);

            BigDecimal unclearQuantityDtl = distrReturnInDetail.getUnclearQuantity().subtract(saveReceivableVoucherDetailVO.getQuantity());
            if (unclearQuantityDtl.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("如果继续配后退回入库中明细未清数量会变为负数");
            }
            BigDecimal clearQuantityDtl = distrReturnInDetail.getClearQuantity().add(saveReceivableVoucherDetailVO.getQuantity());
            if (clearQuantityDtl.compareTo(distrReturnInDetail.getQuantity()) > 0) {
                throw new BusinessException("如果继续配后退回入库中明细已清数量会超出原有单据数量");
            }
            distrReturnInDetail.setUnclearQuantity(unclearQuantityDtl);
            distrReturnInDetail.setClearQuantity(clearQuantityDtl);
            UserEnterpriseUtils.setUserCreateOrModify(distrReturnInDetail, loginUser, false);
            distrReturnInDetailMapper.updateByPrimaryKeySelective(distrReturnInDetail);

            //判断配后退回入库状态 (部分开票，已开票)
            List<DistrReturnInShelf> distrReturnInShelfList = distrReturnInShelfMapper.getDistrReturnInShelf(distrReturnInDetail.getId());
            Integer distrOutShelvesCount = receivableVoucherMapper.getDistrReturnInShelfCount(distrReturnInDetail.getId(), DistrReturnStatus.FINISHED);
            if (BigDecimal.valueOf(distrReturnInShelfList.size()).compareTo(BigDecimal.valueOf(distrOutShelvesCount)) > 0) {
                distrReturnInDetail.setStatus(DistrReturnStatus.PART_BILL);//部分开票
            } else if (BigDecimal.valueOf(distrReturnInShelfList.size()).compareTo(BigDecimal.valueOf(distrOutShelvesCount)) == 0) {
                distrReturnInDetail.setStatus(DistrReturnStatus.FINISHED);//已开票
            }
            UserEnterpriseUtils.setUserCreateOrModify(distrReturnInDetail, loginUser, false);
            distrReturnInDetailMapper.updateByPrimaryKeySelective(distrReturnInDetail);

            DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(saveReceivableVoucherDetailVO.getBaseOrderId());
            List<DistrOutDetail> distrOutDetails = distrReturnInDetailMapper.getDistrReturnInDetailList(saveReceivableVoucherDetailVO.getBaseOrderId());
            Integer distrOutDetailsCount = receivableVoucherMapper.getDistrReturnInDetailsCount(saveReceivableVoucherDetailVO.getBaseOrderId(), DistrReturnStatus.FINISHED);
            if (BigDecimal.valueOf(distrOutDetails.size()).compareTo(BigDecimal.valueOf(distrOutDetailsCount)) > 0) {
                distrReturnIn.setStatus(DistrReturnStatus.PART_BILL);//部分开票
            } else if (BigDecimal.valueOf(distrOutDetails.size()).compareTo(BigDecimal.valueOf(distrOutDetailsCount)) == 0) {
                distrReturnIn.setStatus(DistrReturnStatus.FINISHED);//已开票
            }
            UserEnterpriseUtils.setUserCreateOrModify(distrReturnIn, loginUser, false);
            distrReturnInMapper.updateByPrimaryKeySelective(distrReturnIn);

            //更新上游单据状态
            updateBaseOrderStatus(distrReturnIn, distrReturnInDetail, distrReturnInShelf, 0);

            receivableVoucherDetailList.add(receivableVoucherDetail);
        }

        receivableVoucher.setQuantityTotal(quantityTotal);
        receivableVoucher.setVarietiesQuantity(varietiesQuantity);
        receivableVoucher.setAmountTotal(amountTotal);
        receivableVoucher.setNotaxAmountTotal(notaxAmountTotal);
        receivableVoucher.setTaxAmountTotal(taxAmountTotal);
        receivableVoucher.setDiffTaxAmountTotal(diffTaxAmountTotal);
        receivableVoucher.setDiffNotaxAmountTotal(diffNotaxAmountTotal);
        receivableVoucher.setDiffAmountTotal(diffAmountTotal);
        receivableVoucher.setUnclearAmountTotal(unclearAmountTotal);
        receivableVoucher.setClearAmountTotal(clearAmountTotal);
        UserEnterpriseUtils.setUserCreateOrModify(receivableVoucher, loginUser, false);
        receivableVoucherMapper.updateByPrimaryKeySelective(receivableVoucher);

        //调用财务公用接口
        financeComponent.receivableVoucherToBalanceAndVoucherWhenSaveOrWirteOff(loginUser, receivableVoucher, receivableVoucherDetailList, "save");

    }

    /**
     *
     * @param distrReturnIn
     * @param distrReturnInDetail
     * @param distrReturnInShelf
     * @param type 0:适用保存   1:适用冲销
     */
    private void updateBaseOrderStatus(DistrReturnIn distrReturnIn, DistrReturnInDetail distrReturnInDetail, DistrReturnInShelf distrReturnInShelf, Integer type) {

        //判断配后退回验收状态 (部分开票，已开票)
        DistrReturnCheck distrReturnCheck = distrReturnCheckMapper.selectByPrimaryKey(distrReturnIn.getBaseOrderId());
        if(distrReturnCheck == null){
            throw new BusinessException("无法通过ID：" + distrReturnIn.getBaseOrderId() + "查询到配后退回验收单");
        }
        distrReturnCheck.setStatus(distrReturnIn.getStatus());
        distrReturnCheckMapper.updateByPrimaryKeySelective(distrReturnCheck);

        DistrReturnCheckDetail distrReturnCheckDetail = distrReturnCheckDetailMapper.selectByPrimaryKey(distrReturnInDetail.getBaseOrderDtlId());
        if(distrReturnCheckDetail == null){
            throw new BusinessException("无法通过ID：" + distrReturnInDetail.getBaseOrderDtlId() + "查询到配后退回验收明细单");
        }
        distrReturnCheckDetail.setStatus(distrReturnInDetail.getStatus());
        distrReturnCheckDetailMapper.updateByPrimaryKeySelective(distrReturnCheckDetail);

        DistrReturnCheckLot distrReturnCheckLot = distrReturnCheckLotMapper.getUpdateReturnCheckLot(distrReturnInDetail.getBaseOrderDtlId(), distrReturnInShelf.getGoodsId(), distrReturnInShelf.getLotNumber());
        if(distrReturnCheckDetail == null){
            throw new BusinessException("无法通过明细ID,商品ID,批号,查询到配后退回验收货位明细单,参数：" + distrReturnInDetail.getBaseOrderDtlId() + ","+ distrReturnInShelf.getGoodsId() + ","+  distrReturnInShelf.getLotNumber());
        }
        if(type == 0) {
            if (distrReturnInShelf.getUnclearQuantity().compareTo(BigDecimal.ZERO) == 0) {
                distrReturnCheckLot.setStatus(DistrReturnStatus.FINISHED);
            } else {
                distrReturnCheckLot.setStatus(DistrReturnStatus.PART_BILL);
            }
        }else if(type == 1){
            distrReturnCheckLot.setStatus(DistrReturnStatus.WAIT_BILL);
        }
        distrReturnCheckLotMapper.updateByPrimaryKeySelective(distrReturnCheckLot);

        //判断配后退回收货状态 (部分开票，已开票)
        DistrReturnReceive distrReturnReceive = distrReturnReceiveMapper.selectByPrimaryKey(distrReturnCheck.getBaseOrderId());
        if(distrReturnReceive == null){
            throw new BusinessException("无法通过ID：" + distrReturnCheck.getBaseOrderId() + "查询到配后退回收货单");
        }
        distrReturnReceive.setStatus(distrReturnCheck.getStatus());
        distrReturnReceiveMapper.updateByPrimaryKeySelective(distrReturnReceive);

        DistrReturnReceiveDetail distrReturnReceiveDetail = distrReturnReceiveDetailMapper.selectByPrimaryKey(distrReturnCheckDetail.getBaseOrderDtlId());
        if(distrReturnReceiveDetail == null){
            throw new BusinessException("无法通过ID：" + distrReturnCheckDetail.getBaseOrderDtlId() + "查询到配后退回收货明细单");
        }
        distrReturnReceiveDetail.setStatus(distrReturnCheckDetail.getStatus());
        distrReturnReceiveDetailMapper.updateByPrimaryKeySelective(distrReturnReceiveDetail);

        //判断配后退回通知单状态 (部分开票，已开票)
        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(distrReturnReceive.getBaseOrderId());
        if(distrReturnNotice == null){
            throw new BusinessException("无法通过ID：" + distrReturnReceive.getBaseOrderId() + "查询到配后退回通知单");
        }
        distrReturnNotice.setStatus(distrReturnReceive.getStatus());
        distrReturnNoticeMapper.updateByPrimaryKeySelective(distrReturnNotice);

        DistrReturnNoticeDetail distrReturnNoticeDetail = distrReturnNoticeDetailMapper.selectByPrimaryKey(distrReturnReceiveDetail.getBaseOrderDtlId());
        if(distrReturnNoticeDetail == null){
            throw new BusinessException("无法通过ID：" + distrReturnReceiveDetail.getBaseOrderDtlId() + "查询到配后退回通知明细单");
        }
        distrReturnNoticeDetail.setStatus(distrReturnReceiveDetail.getStatus());
        distrReturnNoticeDetailMapper.updateByPrimaryKeySelective(distrReturnNoticeDetail);

    }

    @Override
    public SaveReveivableVoucherVO getSaveReveivableVoucher(UserVO userVO, Long id) {

        ReceivableVoucher receivableVoucher = receivableVoucherMapper.selectByPrimaryKey(id);
        if (receivableVoucher == null) {
            throw new BusinessException("无效的应收贷项凭证ID：" + id);
        }
        SaveReveivableVoucherVO saveReveivableVoucherVO = new SaveReveivableVoucherVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(receivableVoucher, saveReveivableVoucherVO);
        saveReveivableVoucherVO.setStatus(receivableVoucher.getStatus());

        List<ReceivableVoucherDetail> receivableVoucherDetailList = receivableVoucherDetailMapper.getReceivableInvoiceDetailList(receivableVoucher.getId());
        if (receivableVoucherDetailList.isEmpty()) {
            throw new BusinessException("没有查询到应收贷项凭证ID：" + receivableVoucher.getId() + "的明细数据");
        }
        List<SaveReceivableVoucherDetailVO> saveReceivableVoucherDetailVOList = new ArrayList<>();
        for (ReceivableVoucherDetail receivableVoucherDetail : receivableVoucherDetailList) {
            SaveReceivableVoucherDetailVO saveReceivableVoucherDetailVO = new SaveReceivableVoucherDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(receivableVoucherDetail, saveReceivableVoucherDetailVO);
            saveReceivableVoucherDetailVOList.add(saveReceivableVoucherDetailVO);
        }
        saveReveivableVoucherVO.setSaveReceivableVoucherDetailVO(saveReceivableVoucherDetailVOList);

        return saveReveivableVoucherVO;
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO, Long supplierId) throws Exception {
        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.RECEIVABLE_VOUCHER.getCodePrefix());
        draftCacheVO.setSupplierId(supplierId);
        return redisComponent.getDraftCacheVO(draftCacheVO);
    }

    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) throws Exception {
        if (draftCache.getOrderStr() == null || "".equals(draftCache.getOrderStr())) {
            throw new BusinessException("数据实体不能为空");
        }

        draftCache.setOrderCode(OrderRule.RECEIVABLE_VOUCHER.getCodePrefix());

        draftCache.setEnterpriseId(userVO.getEnterpriseId());
        draftCache = redisComponent.saveDraftCacheVO(draftCache);
        return draftCache;
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue, Long supplierId) throws Exception {
        redisComponent.removeDraftCacheVO(supplierId,enterpriseId, type, redisKeyValue);
    }

    @Override
    public List<VoucherModifyRecordPageVO> getVoucherModifyRecordPage(int pageNo, int pageSize, UserVO loginUser, Page page, Long id) {

        Integer count = receivableVoucherModifyRecordMapper.getVoucherModifyRecordPageCount(loginUser.getEnterpriseId(), id);
        List<VoucherModifyRecordPageVO> voucherModifyRecordPageVOList = receivableVoucherModifyRecordMapper.getVoucherModifyRecordPage(loginUser.getEnterpriseId(), page.getStart(), pageSize, id);

        page.setTotalRecord(count == null ? 0 : count.intValue());
        return voucherModifyRecordPageVOList;
    }

    @Override
    public void exportExcel(OutputStream output, SaveReveivableVoucherVO saveReveivableVoucher, UserVO loginUser) {

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
        titleSecondRow.append(saveReveivableVoucher.getPurchaseUnitCode() == null ? "" : saveReveivableVoucher.getPurchaseUnitCode());
        titleSecondRow.append("  购货单位名称:");
        titleSecondRow.append(saveReveivableVoucher.getPurchaseUnitName() == null ? "" : saveReveivableVoucher.getPurchaseUnitName());
        titleSecondRow.append("  过账日期:");
        titleSecondRow.append(saveReveivableVoucher.getPostDate() == null ? "" : DateUtils.DateToString(saveReveivableVoucher.getPostDate(),DateUtils.FMT_DATE));
        titleSecondRow.append("   过账人员:");
        titleSecondRow.append(saveReveivableVoucher.getPostManName() == null ? "" : saveReveivableVoucher.getPostManName());
        titleSecondRow.append("  单号:");
        titleSecondRow.append(saveReveivableVoucher.getCode() == null ? "" : saveReveivableVoucher.getCode());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(saveReveivableVoucher.getRemark() == null ? "" : saveReveivableVoucher.getRemark());
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

        for (SaveReceivableVoucherDetailVO saveReceivableVoucherDetailVO : saveReveivableVoucher.getSaveReceivableVoucherDetailVO()) {
            quantity = quantity.add(saveReceivableVoucherDetailVO.getQuantity());
            amount = amount.add(saveReceivableVoucherDetailVO.getAmount());
            notaxAmount = notaxAmount.add(saveReceivableVoucherDetailVO.getNotaxAmount());
            taxAmount = taxAmount.add(saveReceivableVoucherDetailVO.getTaxAmount());
            diffAmount = diffAmount.add(saveReceivableVoucherDetailVO.getDiffAmount());
            diffTaxAmount = diffTaxAmount.add(saveReceivableVoucherDetailVO.getDiffTaxAmount());
            diffNotaxAmount = diffNotaxAmount.add(saveReceivableVoucherDetailVO.getDiffNotaxAmount());
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
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(receivableVoucherMapper.selectByPrimaryKey(saveReveivableVoucher.getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("应收贷项凭证");
        purchaseGeneralComponent.commExcelExport(output, map, saveReveivableVoucher.getSaveReceivableVoucherDetailVO(), name, titleSecond, end.toString(), false, needTotalName);


    }

    @Override
    public List<VoucherModifyRecordPageVO> getVoucherModifyRecordPageList(UserVO loginUser, Long id) {

        List<VoucherModifyRecordPageVO> voucherModifyRecordPageVOList = receivableVoucherModifyRecordMapper.getVoucherModifyRecordPageList(loginUser.getEnterpriseId(), id);
        return voucherModifyRecordPageVOList;
    }

    @Override
    public void exportExcelModifyRecord(OutputStream output, List<VoucherModifyRecordPageVO> voucherModifyRecordPageVOS, UserVO loginUser) {

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
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(receivableVoucherModifyRecordMapper.selectByPrimaryKey(voucherModifyRecordPageVOS.get(0).getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("修改记录");
        purchaseGeneralComponentModify.commExcelExport(output, map, voucherModifyRecordPageVOS, name, titleSecond, end.toString(), false, needTotalName);
    }

    @Override
    public DistrReturnInTotalVO getDistrReturnInTotal(int pageNo, int pageSize, UserVO loginUser, Page page, Long purchaseUnitId, String key) {

        DistrReturnInTotalVO distrReturnInTotalVO = new DistrReturnInTotalVO();
        List<Integer> status = new ArrayList<>();
        status.add(DistrReturnStatus.WAIT_BILL);
        status.add(DistrReturnStatus.PART_BILL);

        Integer count = receivableVoucherMapper.getDistrReturnInTotalCount(loginUser.getEnterpriseId(), purchaseUnitId, status, key);
        List<DistrReturnInPageListVO> distrReturnInPageListVOS = receivableVoucherMapper.getDistrReturnInTotal(loginUser.getEnterpriseId(), page.getStart(), pageSize, purchaseUnitId, status, key);
        distrReturnInTotalVO.setDistrReturnInPageListVO(distrReturnInPageListVOS);

        BigDecimal tabQuantityTotal = BigDecimal.ZERO;
        BigDecimal tabAmountTotal = BigDecimal.ZERO;
        BigDecimal tabWholeDiscountAmountTotal = BigDecimal.ZERO;
        BigDecimal tabLineDiscountAmountTotal = BigDecimal.ZERO;
        BigDecimal tabNotaxAmountTotal = BigDecimal.ZERO;
        BigDecimal tabRealAmountTotal = BigDecimal.ZERO;
        BigDecimal tabTaxAmountTotal = BigDecimal.ZERO;
        BigDecimal tabClearQuantityTotal = BigDecimal.ZERO;
        BigDecimal tabUnclearQuantityTotal = BigDecimal.ZERO;

        for (DistrReturnInPageListVO distrReturnInPageListVO : distrReturnInPageListVOS) {
            tabQuantityTotal = tabQuantityTotal.add(distrReturnInPageListVO.getQuantity());
            tabAmountTotal = tabAmountTotal.add(distrReturnInPageListVO.getBaseOrderAmount());
            tabWholeDiscountAmountTotal = tabWholeDiscountAmountTotal.add(distrReturnInPageListVO.getWholeDiscountAmount());
            tabLineDiscountAmountTotal = tabLineDiscountAmountTotal.add(distrReturnInPageListVO.getLineDiscountAmount());
            tabNotaxAmountTotal = tabNotaxAmountTotal.add(distrReturnInPageListVO.getBaseOrderNotaxAmount());
            tabRealAmountTotal = tabRealAmountTotal.add(distrReturnInPageListVO.getRealAmount());
            tabTaxAmountTotal = tabTaxAmountTotal.add(distrReturnInPageListVO.getBaseOrderTaxAmount());
            tabUnclearQuantityTotal = tabUnclearQuantityTotal.add(distrReturnInPageListVO.getUnclearQuantity());
            tabClearQuantityTotal = tabClearQuantityTotal.add(distrReturnInPageListVO.getClearQuantity());
        }
        distrReturnInTotalVO.setTabQuantityTotal(tabQuantityTotal);
        distrReturnInTotalVO.setTabAmountTotal(tabAmountTotal);
        distrReturnInTotalVO.setTabWholeDiscountAmountTotal(tabWholeDiscountAmountTotal);
        distrReturnInTotalVO.setTabLineDiscountAmountTotal(tabLineDiscountAmountTotal);
        distrReturnInTotalVO.setTabNotaxAmountTotal(tabNotaxAmountTotal);
        distrReturnInTotalVO.setTabRealAmountTotal(tabRealAmountTotal);
        distrReturnInTotalVO.setTabTaxAmountTotal(tabTaxAmountTotal);
        distrReturnInTotalVO.setTabClearQuantityTotal(tabClearQuantityTotal);
        distrReturnInTotalVO.setTabUnclearQuantityTotal(tabUnclearQuantityTotal);

        page.setTotalRecord(count == null ? 0 : count.intValue());
        return distrReturnInTotalVO;
    }

    @Override
    public List<CallDistrReturnInPageVO> getCallDistrReturnInPage(int pageNo, int pageSize, UserVO loginUser, Page page, Date startTime, Date endTime, String orderName, String orderType, Long supplierId) {

        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd").format(startTime) + " 00:00:00";
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd").format(endTime) + " 23:59:59";
        }
        if (orderName != null && orderName.equals("baseOrderDate")) {
            orderName = "return_in_date";
        } else if (orderName != null && orderName.equals("baseOrderCode")) {
            orderName = "code";
        }

        List<Integer> status = new ArrayList<>();
        status.add(DistrReturnStatus.WAIT_BILL);
        status.add(DistrReturnStatus.PART_BILL);

        Integer count = receivableVoucherMapper.getCallDistrReturnInPageCount(loginUser.getEnterpriseId(), startTimes, endTimes, orderName, orderType, ChainType.Division.getCode(), status, supplierId);
        List<CallDistrReturnInPageVO> callDistrReturnInPageVOList = receivableVoucherMapper.getCallDistrReturnInPage(loginUser.getEnterpriseId(), page.getStart(), pageSize, startTimes, endTimes, orderName, orderType, ChainType.Division.getCode(), status, supplierId);
        page.setTotalRecord(count == null ? 0 : count.intValue());

        return callDistrReturnInPageVOList;
    }

    @Override
    public List<DistrReturnInPageListVO> getCallDistrReturnInDeatil(UserVO loginUser, List<Long> ids) {

        List<Integer> status = new ArrayList<>();
        status.add(DistrReturnStatus.WAIT_BILL);
        status.add(DistrReturnStatus.PART_BILL);

        List<DistrReturnInPageListVO> distrReturnInPageListVOList = receivableVoucherMapper.getCallDistrReturnInDeatil(loginUser.getEnterpriseId(), ids, status);
        return distrReturnInPageListVOList;
    }

    @Override
    public void getAlreadyWriteVoucher(UserVO userVO, Long id) throws Exception {

        //更改应收发票状态
        ReceivableVoucher receivableVoucher = receivableVoucherMapper.selectByPrimaryKey(id);
        if (receivableVoucher == null) {
            throw new BusinessException("无效的应收贷项凭证ID：" + id);
        }
        if (receivableVoucher.getStatus() != ReceivableVoucherStatus.WAIT_PAYMENT.getStatus()) {
            throw new BusinessException("只允许待付款状态进行冲销");
        }

        receivableVoucher.setStatus(ReceivableVoucherStatus.ALREADY_WRITE.getStatus());
        UserEnterpriseUtils.setUserCreateOrModify(receivableVoucher, userVO, false);
        receivableVoucherMapper.updateByPrimaryKeySelective(receivableVoucher);

        List<ReceivableVoucherDetail> receivableVoucherDetailList = receivableVoucherDetailMapper.getReceivableInvoiceDetailList(receivableVoucher.getId());
        if (receivableVoucherDetailList.isEmpty()) {
            throw new BusinessException("没有查询到应收贷项凭证ID：" + receivableVoucher.getId() + "的明细数据");
        }
        for (ReceivableVoucherDetail receivableVoucherDetail : receivableVoucherDetailList) {
            if (receivableVoucherDetail.getStatus() == ReceivableVoucherStatus.ALREADY_PAYMENT.getStatus()) {
                continue;
            }
            receivableVoucherDetail.setStatus(ReceivableVoucherStatus.ALREADY_WRITE.getStatus());
            UserEnterpriseUtils.setUserCreateOrModify(receivableVoucherDetail, userVO, false);
            receivableVoucherDetailMapper.updateByPrimaryKeySelective(receivableVoucherDetail);

            //更新还原已清和未清数量
            DistrReturnInShelf distrReturnInShelf = distrReturnInShelfMapper.selectByPrimaryKey(receivableVoucherDetail.getBaseShelfDtlId());
            if (distrReturnInShelf == null) {
                throw new BusinessException("没有查询到配后退回入库货位明细ID：" + receivableVoucherDetail.getBaseShelfDtlId() + "的明细数据");
            }
            BigDecimal unclearQuantity = distrReturnInShelf.getUnclearQuantity().add(receivableVoucherDetail.getQuantity());
            BigDecimal clearQuantity = distrReturnInShelf.getClearQuantity().subtract(receivableVoucherDetail.getQuantity());
            if (clearQuantity.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("如果继续配后退回入库中货位明细已清数量会变为负数");
            }
            if (receivableVoucherDetail.getQuantity().compareTo(distrReturnInShelf.getClearQuantity()) > 0) {
                throw new BusinessException("应收发票冲销明细ID：" + receivableVoucherDetail.getId() + "冲销数量超出配后退回入库货位明细ID：" + receivableVoucherDetail.getBaseShelfDtlId() + "的已清数量");
            }
            distrReturnInShelf.setUnclearQuantity(unclearQuantity);
            distrReturnInShelf.setClearQuantity(clearQuantity);
            UserEnterpriseUtils.setUserCreateOrModify(distrReturnInShelf, userVO, false);
            if(clearQuantity.compareTo(BigDecimal.ZERO) == 0) {
                distrReturnInShelf.setStatus(DistrReturnStatus.WAIT_BILL);
            }else{
                distrReturnInShelf.setStatus(DistrReturnStatus.PART_BILL);
            }
            distrReturnInShelfMapper.updateByPrimaryKeySelective(distrReturnInShelf);

            DistrReturnInDetail distrReturnInDetail = distrReturnInDetailMapper.selectByPrimaryKey(receivableVoucherDetail.getBaseDtlId());
            if (distrReturnInDetail == null) {
                throw new BusinessException("没有查询到配后退回入库明细ID：" + receivableVoucherDetail.getBaseDtlId() + "的明细数据");
            }
            BigDecimal unclearQuantityDtl = distrReturnInDetail.getUnclearQuantity().add(receivableVoucherDetail.getQuantity());
            BigDecimal clearQuantityDtl = distrReturnInDetail.getClearQuantity().subtract(receivableVoucherDetail.getQuantity());
            if (clearQuantityDtl.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("如果继续配后退回入库中明细已清数量会变为负数");
            }
            distrReturnInDetail.setUnclearQuantity(unclearQuantityDtl);
            distrReturnInDetail.setClearQuantity(clearQuantityDtl);
            distrReturnInDetailMapper.updateByPrimaryKeySelective(distrReturnInDetail);

            //判断配货出库状态 (部分开票，已开票)
            List<DistrReturnInShelf> distrReturnInShelves = distrReturnInShelfMapper.getDistrReturnInShelf(receivableVoucherDetail.getBaseDtlId());
            Integer alreadyVoucherCount = receivableVoucherMapper.getDistrReturnInShelfCount(receivableVoucherDetail.getBaseDtlId(), DistrReturnStatus.FINISHED);
            Integer waitBillCount = receivableVoucherMapper.getDistrReturnInShelfCount(receivableVoucherDetail.getBaseDtlId(), DistrReturnStatus.WAIT_BILL);

            if (BigDecimal.valueOf(distrReturnInShelves.size()).compareTo(BigDecimal.valueOf(waitBillCount)) == 0) {
                distrReturnInDetail.setStatus(DistrReturnStatus.WAIT_BILL);//待开票
            } else {
                if (BigDecimal.valueOf(distrReturnInShelves.size()).compareTo(BigDecimal.valueOf(alreadyVoucherCount)) > 0) {
                    distrReturnInDetail.setStatus(DistrReturnStatus.PART_BILL);//部分开票
                } else if (BigDecimal.valueOf(distrReturnInShelves.size()).compareTo(BigDecimal.valueOf(alreadyVoucherCount)) == 0) {
                    distrReturnInDetail.setStatus(DistrReturnStatus.FINISHED);//已开票
                }
            }
            distrReturnInDetailMapper.updateByPrimaryKeySelective(distrReturnInDetail);

            DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(receivableVoucherDetail.getBaseOrderId());
            List<DistrReturnInDetail> distrReturnInDetails = distrReturnInDetailMapper.selectByReturnId(receivableVoucherDetail.getBaseOrderId());
            Integer disAlreadyVoucherCount = receivableVoucherMapper.getDistrReturnInDetailsCount(receivableVoucherDetail.getBaseOrderId(), DistrReturnStatus.FINISHED);
            Integer disWaitBillCount = receivableVoucherMapper.getDistrReturnInDetailsCount(receivableVoucherDetail.getBaseOrderId(), DistrReturnStatus.WAIT_BILL);
            if (BigDecimal.valueOf(distrReturnInDetails.size()).compareTo(BigDecimal.valueOf(disWaitBillCount)) == 0) {
                distrReturnIn.setStatus(DistrReturnStatus.WAIT_BILL);//待开票
            } else {
                if (BigDecimal.valueOf(distrReturnInDetails.size()).compareTo(BigDecimal.valueOf(disAlreadyVoucherCount)) == 0) {
                    distrReturnIn.setStatus(DistrReturnStatus.FINISHED);//已开票
                } else if (BigDecimal.valueOf(distrReturnInDetails.size()).compareTo(BigDecimal.valueOf(disAlreadyVoucherCount)) > 0) {
                    distrReturnIn.setStatus(DistrReturnStatus.PART_BILL);//部分开票
                }
            }
            distrReturnInMapper.updateByPrimaryKeySelective(distrReturnIn);

            //更新上游单据状态
            updateBaseOrderStatus(distrReturnIn, distrReturnInDetail, distrReturnInShelf, 1);
        }

        //调用公共接口
        financeComponent.receivableVoucherToBalanceAndVoucherWhenSaveOrWirteOff(userVO, receivableVoucher, receivableVoucherDetailList, "writeOff");

    }

    //获取采购验收单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
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
        fieldNames.put("postDate", "过账日期");
        fieldNames.put("postManName", "过账人员");
        fieldNames.put("postManCode", "过账人员编码");
//        fieldNames.put("postManId", "过账人员ID");
        fieldNames.put("remark", "备注");
        return fieldNames;
    }
}
