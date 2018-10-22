package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.*;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.*;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao.PaymentVoucherDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao.PaymentVoucherMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.dao.PaymentVoucherModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucher;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucherDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucherModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.service.PaymentVoucherService;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo.*;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutDetail;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutShelf;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInReturnStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.constant.status.ReceivableInvoiceStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.TwoTuple;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能描述：
 * Created by ST on 2018/1/8 14:07
 */
@Service
public class PaymentVoucherServiceImpl implements PaymentVoucherService {

    @Autowired
    private PaymentVoucherMapper paymentVoucherMapper;

    @Autowired
    private PaymentVoucherDetailMapper paymentVoucherDetailMapper;

    @Autowired
    private PaymentVoucherModifyRecordMapper paymentVoucherModifyRecordMapper;


    @Autowired
    private PurchaseReturnOutMapper purchaseReturnOutMapper;

    @Autowired
    private PurchaseReturnOutDetailMapper purchaseReturnOutDetailMapper;

    @Autowired
    private PurchaseReturnOutShelfMapper purchaseReturnOutShelfMapper;

    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    @Autowired
    private PurchaseReturnDetailMapper purchaseReturnDetailMapper;


    @Autowired
    private DistrInReturnOutMapper distrInReturnOutMapper;

    @Autowired
    private DistrInReturnOutDetailMapper distrInReturnOutDetailMapper;

    @Autowired
    private DistrInReturnOutShelfMapper distrInReturnOutShelfMapper;

    @Autowired
    private DistrInReturnMapper distrInReturnMapper;

    @Autowired
    private DistrInReturnDetailMapper distrInReturnDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private FinanceComponent financeComponent;

    //@Autowired
    //private CalculateComponent calculateComponent;

    /**
     * 应付贷项凭证列表
     *
     * @param voucherTotalVOPage
     * @param requestVoucherParamVO
     * @param userVO
     */
    @Override
    public void getPaymentVoucherListPage(Page<PaymentVoucherTotalVO> voucherTotalVOPage, RequestVoucherParamVO requestVoucherParamVO, UserVO userVO) {
        requestVoucherParamVO.setEnterpriseId(userVO.getEnterpriseId());
        requestVoucherParamVO.setStart(voucherTotalVOPage.getStart());
        Integer count = paymentVoucherMapper.getPaymentVoucherListPageCount(requestVoucherParamVO);
        PaymentVoucherTotalVO voucherTotalVO;
        if(count == null || count == 0){
            voucherTotalVO = new PaymentVoucherTotalVO();
            List<PaymentVoucherListVO> paymentVoucherListVOList = new ArrayList<>();
            voucherTotalVO.setPaymentVoucherListVOList(paymentVoucherListVOList);
            voucherTotalVOPage.setResult(voucherTotalVO);
            voucherTotalVOPage.setTotalRecord(0);
            return;
        }

        //合计
        if(requestVoucherParamVO.getStatus() != null ){
            requestVoucherParamVO.setStatusList(Arrays.asList(requestVoucherParamVO.getStatus()));
        } else {
            requestVoucherParamVO.setStatusList(Arrays.asList(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus(),
                    ReceivableInvoiceStatus.PART_RECEIVABLES.getStatus(),
                    ReceivableInvoiceStatus.ALREADY_RECEIVABLES.getStatus()));
        }
        voucherTotalVO = paymentVoucherMapper.getPaymentVoucherListTotal(requestVoucherParamVO);
        if(voucherTotalVO == null){
            voucherTotalVO = new PaymentVoucherTotalVO();
        }
        //分页获取数据
        List<PaymentVoucherListVO> paymentVoucherListPage = paymentVoucherMapper.getPaymentVoucherListPage(requestVoucherParamVO);
        voucherTotalVO.setPaymentVoucherListVOList(paymentVoucherListPage);
        voucherTotalVOPage.setResult(voucherTotalVO);
        voucherTotalVOPage.setTotalRecord(count);
    }

    /**
     * 应付贷项凭证详情
     *
     * @param id
     * @param userVO
     * @return
     */
    @Override
    public PaymentVoucherVO getPaymentVoucherDetailById(Long id, UserVO userVO) {
        return paymentVoucherMapper.getPaymentVoucherDetailById(id,userVO.getEnterpriseId());
    }



    /**
     * 应付贷项凭证冲销
     *
     * @param id
     * @param userVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reversal(Long id, UserVO userVO) throws Exception {
        PaymentVoucher paymentVoucher = paymentVoucherMapper.selectByPrimaryKey(id);
        PaymentVoucher paymentVoucherUpdate = (PaymentVoucher) EntityUtils.reflectUpdateSetDefaultValue(PaymentVoucher.class,userVO);
        paymentVoucherUpdate.setId(id);
        paymentVoucherUpdate.setStatus(ReceivableInvoiceStatus.ALREADY_WRITE.getStatus());
        //已清金额合计
        paymentVoucherUpdate.setClearAmountTotal(BigDecimal.ZERO);
        //未清金额合计
        paymentVoucherUpdate.setUnclearAmountTotal(paymentVoucher.getAmountTotal());
        paymentVoucherMapper.updateByPrimaryKeySelective(paymentVoucherUpdate);

        PaymentVoucherDetail paymentVoucherDetail = (PaymentVoucherDetail) EntityUtils.reflectUpdateSetDefaultValue(PaymentVoucherDetail.class,userVO);
        paymentVoucherDetail.setVoucherId(id);
        paymentVoucherDetail.setStatus(ReceivableInvoiceStatus.ALREADY_WRITE.getStatus());
        paymentVoucherDetail.setEnterpriseId(userVO.getEnterpriseId());
        paymentVoucherDetailMapper.updateStatusByVoucherId(paymentVoucherDetail);



        List<PaymentVoucherDetail> paymentVoucherDetails = paymentVoucherDetailMapper.queryByVoucherId(paymentVoucher.getId(), userVO.getEnterpriseId());

        //明细id
        Set<Long> dtlIdSet = new HashSet<>();
        //主单id
        Set<Long> orderIdSet = new HashSet<>();

        //更新上游单据状态 已清 未清 数据

        for(PaymentVoucherDetail voucherDetail : paymentVoucherDetails){

            Long baseShelfDtlId = voucherDetail.getBaseShelfDtlId();//出库货位id
            Long baseDtlId = voucherDetail.getBaseDtlId();//出库明细单id
            Long baseOrderId = voucherDetail.getBaseOrderId();//出库单id
            BigDecimal quantity = voucherDetail.getQuantity();//出库数量

            if(userVO.getChainType() == ChainType.Headquarters.getCode()) {
                //总部
                //货位数据更新
                PurchaseReturnOutShelf purchaseReturnOutShelf = purchaseReturnOutShelfMapper.selectByPrimaryKey(baseShelfDtlId);
                PurchaseReturnOutShelf returnOutShelf = (PurchaseReturnOutShelf) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturnOutShelf.class,userVO);
                returnOutShelf.setId(baseShelfDtlId);
                returnOutShelf.setClearQuantity(purchaseReturnOutShelf.getClearQuantity().subtract(quantity));
                BigDecimal tmp = purchaseReturnOutShelf.getUnclearQuantity().add(quantity);
                returnOutShelf.setUnclearQuantity(tmp);
                returnOutShelf.setQuantity(purchaseReturnOutShelf.getQuantity());
                setPurchaseShelfStatus(returnOutShelf);
                purchaseReturnOutShelfMapper.updateByPrimaryKeySelective(returnOutShelf);

            } else if(userVO.getChainType() == ChainType.Division.getCode()){
                //加盟店

                DistrInReturnOutShelf distrInReturnOutShelf1 = distrInReturnOutShelfMapper.selectByPrimaryKey(baseShelfDtlId);

                DistrInReturnOutShelf distrInReturnOutShelf = (DistrInReturnOutShelf) EntityUtils.reflectUpdateSetDefaultValue(DistrInReturnOutShelf.class,userVO);
                distrInReturnOutShelf.setId(baseShelfDtlId);
                distrInReturnOutShelf.setClearQuantity(distrInReturnOutShelf1.getClearQuantity().subtract(quantity));
                BigDecimal tmp = distrInReturnOutShelf1.getUnclearQuantity().add(quantity);
                distrInReturnOutShelf.setUnclearQuantity(tmp);
                distrInReturnOutShelf.setQuantity(distrInReturnOutShelf.getQuantity());
                setDistrShelfStatus(distrInReturnOutShelf);
                distrInReturnOutShelfMapper.updateByPrimaryKeySelective(distrInReturnOutShelf);

            }

            dtlIdSet.add(baseDtlId);
            orderIdSet.add(baseOrderId);

        }

        //更新明细单已清 未清 数量， 主单 未清 已清数量
        updateReversalBaseOrderData(userVO, dtlIdSet, orderIdSet);


        //财务基础数据更新
        financeComponent.paymentVoucherToBalanceAndVoucherWhenSaveOrWirteOff(userVO, paymentVoucher, paymentVoucherDetails, "writeOff");

    }

    private void setPurchaseShelfStatus(PurchaseReturnOutShelf returnOutShelf) {
        if(returnOutShelf.getClearQuantity().compareTo(BigDecimal.ZERO) == 0) {
            //已清数量等于0，待开票
            returnOutShelf.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
        } else if (returnOutShelf.getClearQuantity().compareTo(returnOutShelf.getQuantity()) == 0) {
            //已清数量等于业务出库数量，已完成
            returnOutShelf.setStatus(PurchaseStatus.FINISHED.getStatus());
        } else {
            //部分开票
            returnOutShelf.setStatus(PurchaseStatus.PART_BILL.getStatus());
        }
    }

    /**
     * 调用购进退出出库单/配进退出出库单
     *
     * @param userVO
     */
    @Override
    public void getPurchaseAndDistrOutInfo(UserVO userVO, Page<List<PurchaseAndDistrOutVO>> page, RequestPurDistrOutParamVO purDistrOutParamVO) {
        Integer chainType = userVO.getChainType();
        purDistrOutParamVO.setEnterpriseId(userVO.getEnterpriseId());

        if(chainType == ChainType.Headquarters.getCode()){
            //总部 购进退出出库
            purDistrOutParamVO.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
            purDistrOutParamVO.setPartBillStatus(PurchaseStatus.PART_BILL.getStatus());
            getPurReturnOutInfo(page,purDistrOutParamVO);
        } else if(chainType == ChainType.Division.getCode()){
            //加盟店  配进退出出库
            purDistrOutParamVO.setStatus(PubStatus.distrInReturnStatus.WAIT_BILL);
            purDistrOutParamVO.setPartBillStatus(PubStatus.distrInReturnStatus.PART_BILL);
            getDistrReturnOutInfo(page,purDistrOutParamVO);
        } else {
            page.setResult(new ArrayList<>());
            page.setTotalRecord(0);
            return;
        }
    }

    private void getPurReturnOutInfo(Page<List<PurchaseAndDistrOutVO>> page,RequestPurDistrOutParamVO purDistrOutParamVO){
        Integer purReturnOutInfoCount = purchaseReturnOutMapper.getPurReturnOutInfoCount(purDistrOutParamVO);
        if(purReturnOutInfoCount == null || purReturnOutInfoCount == 0){
            page.setTotalRecord(0);
            page.setResult(new ArrayList<>());
            return;
        }
        page.setTotalRecord(purReturnOutInfoCount);
        page.setResult(purchaseReturnOutMapper.getPurReturnOutInfo(purDistrOutParamVO));
    }

    private void getDistrReturnOutInfo(Page<List<PurchaseAndDistrOutVO>> page,RequestPurDistrOutParamVO purDistrOutParamVO){
        Integer distrInReturnOutInfoCount = distrInReturnOutMapper.getDistrInReturnOutInfoCount(purDistrOutParamVO);
        if(distrInReturnOutInfoCount == null || distrInReturnOutInfoCount == 0){
            page.setTotalRecord(0);
            page.setResult(new ArrayList<>());
            return;
        }
        page.setTotalRecord(distrInReturnOutInfoCount);
        page.setResult(distrInReturnOutMapper.getDistrInReturnOutInfo(purDistrOutParamVO));
    }



    /**
     * 调用购进退出出库单/配进退出出库单 明细
     *
     * @param userVO
     */
    @Override
    public List<PurchaseAndDistrOutShelfVO> getPurReturnOutDetailInfo(UserVO userVO,String ids) throws Exception {

        List<String> idList = Arrays.asList(ids.split(","));

        RequestGoodsParamVO paramVO = new RequestGoodsParamVO();
        paramVO.setEnterpriseId(userVO.getEnterpriseId());
        paramVO.setIds(idList);

        Integer chainType = userVO.getChainType();
        if(chainType == ChainType.Headquarters.getCode()){
            //总部 购进退出出库
            paramVO.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
            paramVO.setPartBillStatus(PurchaseStatus.PART_BILL.getStatus());
            List<PurchaseAndDistrOutShelfVO> outShelfVOList = purchaseReturnOutShelfMapper.getPurchaseReturnOutDetailByOutIdS(paramVO);
            return outShelfVOList;
        } else if(chainType == ChainType.Division.getCode()){
            //加盟店  配进退出出库
            paramVO.setStatus(PubStatus.distrInReturnStatus.WAIT_BILL);
            paramVO.setPartBillStatus(PubStatus.distrInReturnStatus.PART_BILL);
            List<PurchaseAndDistrOutShelfVO> outShelfVOList = distrInReturnOutShelfMapper.getDistrReturnOutDetailByOutIdS(paramVO);
            return outShelfVOList;
        } else {
            return new ArrayList<>(1);
        }


    }




    /**
     * 保存应付贷项凭证
     * @param paymentVoucherVO
     */

    @Override
    public String savePaymentVoucher(PaymentVoucherVO paymentVoucherVO,UserVO userVO) throws Exception {

        PaymentVoucher paymentVoucher = (PaymentVoucher) EntityUtils.reflectAddSetDefaultValue(PaymentVoucher.class, userVO);

        String code = orderCodeComponent.generate(OrderRule.PAYMENT_VOUCHER.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());

        paymentVoucher.setCode(code);
        paymentVoucher.setOrderType(OrderRule.PAYMENT_VOUCHER.getType());
        //封装属性
        generatePaymentVoucher(paymentVoucherVO, userVO, paymentVoucher);

        //单位类型（1:企业,2:供应商）
        Integer type = paymentVoucherVO.getType();
        if(type == null || (type != 1 && type != 2)) {
            throw new BusinessException("供货单位的单位类型错误！");
        }
        if(type == 1){
            paymentVoucher.setFinanceAccountType(FinanceAccountType.PARENT.getType());
        } else {
            paymentVoucher.setFinanceAccountType(FinanceAccountType.SUPPLIER.getType());
        }

        //插入应付贷项凭证主单
        paymentVoucherMapper.insertSelective(paymentVoucher);


        //明细行操作
        List<PaymentVoucherDetailVO> paymentVoucherDetailVOList = paymentVoucherVO.getPaymentVoucherDetailVOList();
        if(CollectionUtils.isEmpty(paymentVoucherDetailVOList)) throw new BusinessException("明细数据不能为空");


        BigDecimal quantityTotal = BigDecimal.ZERO;
        Set<Long> varietiesQuantitySet = new HashSet<>();

        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;

        BigDecimal diffAmountTotal = BigDecimal.ZERO;
        BigDecimal diffNotaxAmountTotal = BigDecimal.ZERO;
        BigDecimal diffTaxAmountTotal = BigDecimal.ZERO;

        for(PaymentVoucherDetailVO detailVO : paymentVoucherDetailVOList){
            PaymentVoucherDetail voucherDetail = (PaymentVoucherDetail) EntityUtils.reflectAddSetDefaultValue(PaymentVoucherDetail.class, userVO);

            //设置上游单据属性
            generatePaymentVoucherDetail(userVO, paymentVoucher, detailVO, voucherDetail);

            quantityTotal = quantityTotal.add(voucherDetail.getQuantity());
            varietiesQuantitySet.add(voucherDetail.getGoodsId());

            amountTotal = amountTotal.add(voucherDetail.getAmount());
            notaxAmountTotal = notaxAmountTotal.add(voucherDetail.getNotaxAmount());
            taxAmountTotal = taxAmountTotal.add(voucherDetail.getTaxAmount());

            diffAmountTotal = diffAmountTotal.add(voucherDetail.getDiffAmount());
            diffNotaxAmountTotal = diffNotaxAmountTotal.add(voucherDetail.getDiffNotaxAmount());
            diffTaxAmountTotal = diffAmountTotal.add(voucherDetail.getDiffTaxAmount());


            paymentVoucherDetailMapper.insertSelective(voucherDetail);

        }

        paymentVoucher.setQuantityTotal(quantityTotal);
        paymentVoucher.setVarietiesQuantity(varietiesQuantitySet.size());
        paymentVoucher.setAmountTotal(amountTotal);
        paymentVoucher.setNotaxAmountTotal(notaxAmountTotal);
        paymentVoucher.setTaxAmountTotal(taxAmountTotal);

        paymentVoucher.setDiffAmountTotal(diffAmountTotal);
        paymentVoucher.setDiffNotaxAmountTotal(diffNotaxAmountTotal);
        paymentVoucher.setDiffTaxAmountTotal(diffTaxAmountTotal);

        //已清金额合计
        paymentVoucher.setClearAmountTotal(BigDecimal.ZERO);
        //未清金额合计
        paymentVoucher.setUnclearAmountTotal(amountTotal);


        paymentVoucherMapper.updateByPrimaryKeySelective(paymentVoucher);

        List<PaymentVoucherDetail> paymentVoucherDetails = paymentVoucherDetailMapper.queryByVoucherId(paymentVoucher.getId(), userVO.getEnterpriseId());

        //更新出库单已清、未清数量和状态
        Integer chainType = userVO.getChainType();

        //明细id
        Set<Long> dtlIdSet = new HashSet<>();
        //主单id
        Set<Long> orderIdSet = new HashSet<>();


        for(PaymentVoucherDetail voucherDetail : paymentVoucherDetails){

            Long baseShelfDtlId = voucherDetail.getBaseShelfDtlId();//出库货位id
            Long baseDtlId = voucherDetail.getBaseDtlId();//出库明细单id
            Long baseOrderId = voucherDetail.getBaseOrderId();//出库单id
            BigDecimal quantity = voucherDetail.getQuantity();//出库数量

            if(chainType == ChainType.Headquarters.getCode()) {
                //总部
                //货位数据更新
                PurchaseReturnOutShelf returnOutShelf = purchaseReturnOutShelfMapper.selectByPrimaryKey(baseShelfDtlId);
                returnOutShelf.setId(baseShelfDtlId);
                returnOutShelf.setClearQuantity(returnOutShelf.getClearQuantity().add(quantity));

                BigDecimal tmp = returnOutShelf.getQuantity().subtract(returnOutShelf.getClearQuantity());
                returnOutShelf.setUnclearQuantity(tmp.compareTo(BigDecimal.ZERO) < 0 ? returnOutShelf.getQuantity() : tmp);
                setPurchaseShelfStatus(returnOutShelf);
                purchaseReturnOutShelfMapper.updateByPrimaryKeySelective(returnOutShelf);

            } else if(chainType == ChainType.Division.getCode()){
                //加盟店
                DistrInReturnOutShelf distrInReturnOutShelf = distrInReturnOutShelfMapper.selectByPrimaryKey(baseShelfDtlId);
                distrInReturnOutShelf.setId(baseShelfDtlId);
                distrInReturnOutShelf.setClearQuantity(distrInReturnOutShelf.getClearQuantity().add(quantity));
                BigDecimal tmp = distrInReturnOutShelf.getQuantity().subtract(distrInReturnOutShelf.getClearQuantity());
                distrInReturnOutShelf.setUnclearQuantity(tmp.compareTo(BigDecimal.ZERO) < 0 ? distrInReturnOutShelf.getQuantity() : tmp);

                setDistrShelfStatus(distrInReturnOutShelf);
                distrInReturnOutShelfMapper.updateByPrimaryKeySelective(distrInReturnOutShelf);

            }

            dtlIdSet.add(baseDtlId);
            orderIdSet.add(baseOrderId);

        }

        //更新购进退出、退出出库明细单已清 未清 数量， 主单 未清 已清数量
        updateBaseOrderData(userVO, dtlIdSet, orderIdSet);

        //财务更新
        financeComponent.paymentVoucherToBalanceAndVoucherWhenSaveOrWirteOff(userVO, paymentVoucher, paymentVoucherDetails, "save");

        //删除草稿
        removeDraftCache(paymentVoucherVO.getSupplierId(),userVO.getEnterpriseId(),OrderRule.PAYMENT_VOUCHER.getCodePrefix(),paymentVoucherVO.getRedisKeyValue());

        return code;

    }

    private void setDistrShelfStatus(DistrInReturnOutShelf distrInReturnOutShelf) {
        if(distrInReturnOutShelf.getClearQuantity().compareTo(BigDecimal.ZERO) == 0) {
            //已清数量等于0，待开票
            distrInReturnOutShelf.setStatus(DistrInReturnStatus.WAIT_BILL);
        } else if (distrInReturnOutShelf.getClearQuantity().compareTo(distrInReturnOutShelf.getQuantity()) == 0) {
            //已清数量等于业务出库数量，已完成
            distrInReturnOutShelf.setStatus(DistrInReturnStatus.FINISHED);
        } else {
            //部分开票
            distrInReturnOutShelf.setStatus(DistrInReturnStatus.PART_BILL);
        }
    }

    /**
     * 保存应付贷项凭证更新上游单据数据状态
     * @param userVO
     * @param dtlIdSet
     * @param orderIdSet
     */
    private void updateBaseOrderData(UserVO userVO, Set<Long> dtlIdSet, Set<Long> orderIdSet) throws Exception {
        if(userVO.getChainType() == ChainType.Headquarters.getCode()){
            for(Long dtlId : dtlIdSet){
                //根据明细id，获取该明细下所有的货位信息的数量
                List<PurchaseReturnOutShelf> outShelfVOList = purchaseReturnOutShelfMapper.getShelfOutInfoByDtlId(dtlId,userVO.getEnterpriseId());

                //货位的已清数量
                BigDecimal shelfClearQuantity = outShelfVOList.stream().filter(Objects::nonNull)
                        .filter(c->c.getClearQuantity() != null)
                        .map(PurchaseReturnOutShelf::getClearQuantity)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

                PurchaseReturnOutDetail returnOutDetail = purchaseReturnOutDetailMapper.selectByPrimaryKey(dtlId);

                setPurchaseReturnOutDetailStatus(shelfClearQuantity, returnOutDetail);

                //更新明细的状态 和 已清 未清数量
                purchaseReturnOutDetailMapper.updateByPrimaryKeySelective(returnOutDetail);

                //更新购进退出明细的状态 和已清、未清数量
                setPurchaseReturnDetailStatus(userVO, returnOutDetail);

            }

            for(Long outId : orderIdSet){

                PurchaseReturnOut purchaseReturnOut = purchaseReturnOutMapper.selectByPrimaryKey(outId);
                List<PurchaseReturnOutShelf> returnOutShelfList = purchaseReturnOutShelfMapper.getShelfOutInfoByOutId(outId, userVO.getEnterpriseId());

                //货位的已清数量
                BigDecimal shelfClearQuantity = returnOutShelfList.stream().filter(Objects::nonNull)
                        .filter(c->c.getClearQuantity() != null)
                        .map(PurchaseReturnOutShelf::getClearQuantity)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

                setPurchaseReturnOutStatus(purchaseReturnOut, shelfClearQuantity);
                purchaseReturnOutMapper.updateByPrimaryKeySelective(purchaseReturnOut);

                //更新购进退出状态，已清，未清数量
                setPurchaseReturnStatus(userVO, purchaseReturnOut);

            }
        } else if(userVO.getChainType() == ChainType.Division.getCode()) {
            //加盟店
            for(Long dtlId : dtlIdSet){
                //根据明细id，获取该明细下所有的货位信息的数量
                List<DistrInReturnOutShelf> outShelfVOList = distrInReturnOutShelfMapper.selectByDtlIdAndEnterpriseId(dtlId,userVO.getEnterpriseId(),userVO.getParentId());

                //货位的已清数量
                BigDecimal shelfClearQuantity = outShelfVOList.stream().filter(Objects::nonNull)
                        .filter(c->c.getClearQuantity() != null)
                        .map(DistrInReturnOutShelf::getClearQuantity)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

                DistrInReturnOutDetail returnOutDetail = distrInReturnOutDetailMapper.selectByPrimaryKey(dtlId);

                setDistrInReturnOutDetailStatus(shelfClearQuantity, returnOutDetail);
                //更新明细的状态 和 已清 未清数量
                distrInReturnOutDetailMapper.updateByPrimaryKeySelective(returnOutDetail);

                //更新配进退出明细的状态 和已清、未清数量
                setDistrInReturnStatus(userVO, returnOutDetail);
            }

            for(Long outId : orderIdSet){

                DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(outId);

                List<DistrInReturnOutShelf> returnOutShelfList = distrInReturnOutShelfMapper.getShelfOutInfoByOutId(outId, userVO.getEnterpriseId());

                //货位的已清数量
                BigDecimal shelfClearQuantity = returnOutShelfList.stream().filter(Objects::nonNull)
                        .filter(c->c.getClearQuantity() != null)
                        .map(DistrInReturnOutShelf::getClearQuantity)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

                setDistrInReturnOutStatus(distrInReturnOut, shelfClearQuantity);
                distrInReturnOutMapper.updateByPrimaryKeySelective(distrInReturnOut);

                //更新配进退出状态，已清，未清数量
                setDistrInReturnStatus(userVO, distrInReturnOut);
            }

        }
    }

    private void setDistrInReturnStatus(UserVO userVO, DistrInReturnOut distrInReturnOut) throws Exception {
        DistrInReturn distrInReturn = (DistrInReturn) EntityUtils.reflectUpdateSetDefaultValue(DistrInReturn.class,userVO);
        distrInReturn.setId(distrInReturnOut.getBaseOrderId());
        distrInReturn.setStatus(distrInReturnOut.getStatus());
        distrInReturnMapper.updateByPrimaryKeySelective(distrInReturn);
    }

    private void setDistrInReturnStatus(UserVO userVO, DistrInReturnOutDetail returnOutDetail) throws Exception {
        Long returnDtlId = returnOutDetail.getBaseOrderDtlId();
        DistrInReturnDetail distrInReturnDetail = (DistrInReturnDetail) EntityUtils.reflectUpdateSetDefaultValue(DistrInReturnDetail.class,userVO);
        distrInReturnDetail.setId(returnDtlId);
        distrInReturnDetail.setStatus(returnOutDetail.getStatus());
        distrInReturnDetailMapper.updateByPrimaryKeySelective(distrInReturnDetail);
    }

    private void setDistrInReturnOutDetailStatus(BigDecimal shelfClearQuantity, DistrInReturnOutDetail returnOutDetail) {
        if(shelfClearQuantity.compareTo(BigDecimal.ZERO) == 0) {
            //已清数量等于0，待开票
            returnOutDetail.setClearQuantity(BigDecimal.ZERO);
            returnOutDetail.setUnclearQuantity(shelfClearQuantity);
            returnOutDetail.setStatus(DistrInReturnStatus.WAIT_BILL);
        } else if (shelfClearQuantity.compareTo(returnOutDetail.getQuantity()) == 0) {
            //已清数量等于业务出库数量，已完成
            returnOutDetail.setClearQuantity(shelfClearQuantity);
            returnOutDetail.setUnclearQuantity(BigDecimal.ZERO);
            returnOutDetail.setStatus(DistrInReturnStatus.FINISHED);
        } else {
            //部分开票
            returnOutDetail.setClearQuantity(shelfClearQuantity);
            returnOutDetail.setUnclearQuantity(returnOutDetail.getQuantity().subtract(shelfClearQuantity));
            returnOutDetail.setStatus(DistrInReturnStatus.PART_BILL);
        }
    }

    private void setPurchaseReturnDetailStatus(UserVO userVO, PurchaseReturnOutDetail returnOutDetail) throws Exception {
        Long returnDtId = returnOutDetail.getBaseOrderDtlId();
        PurchaseReturnDetail purchaseReturnDetail = (PurchaseReturnDetail) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturnDetail.class,userVO);
        purchaseReturnDetail.setId(returnDtId);
        purchaseReturnDetail.setStatus(returnOutDetail.getStatus());
        purchaseReturnDetailMapper.updateByPrimaryKeySelective(purchaseReturnDetail);
    }

    private void setPurchaseReturnStatus(UserVO userVO, PurchaseReturnOut purchaseReturnOut) throws Exception {
        PurchaseReturn purchaseReturn = (PurchaseReturn) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturn.class,userVO);
        purchaseReturn.setId(purchaseReturnOut.getBaseOrderId());
        purchaseReturn.setStatus(purchaseReturnOut.getStatus());
        purchaseReturnMapper.updateByPrimaryKeySelective(purchaseReturn);
    }

    private void setPurchaseReturnOutDetailStatus(BigDecimal shelfClearQuantity, PurchaseReturnOutDetail returnOutDetail) {
        if(shelfClearQuantity.compareTo(BigDecimal.ZERO) == 0) {
            //已清数量等于0，待开票
            returnOutDetail.setClearQuantity(BigDecimal.ZERO);
            returnOutDetail.setUnclearQuantity(shelfClearQuantity);
            returnOutDetail.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
        } else if (shelfClearQuantity.compareTo(returnOutDetail.getQuantity()) == 0) {
            //已清数量等于业务出库数量，已完成
            returnOutDetail.setClearQuantity(shelfClearQuantity);
            returnOutDetail.setUnclearQuantity(BigDecimal.ZERO);
            returnOutDetail.setStatus(PurchaseStatus.FINISHED.getStatus());
        } else {
            //部分开票
            returnOutDetail.setClearQuantity(shelfClearQuantity);
            returnOutDetail.setUnclearQuantity(returnOutDetail.getQuantity().subtract(shelfClearQuantity));
            returnOutDetail.setStatus(PurchaseStatus.PART_BILL.getStatus());
        }
    }

    private void setPurchaseReturnOutStatus(PurchaseReturnOut purchaseReturnOut, BigDecimal shelfClearQuantity) {
        if(shelfClearQuantity.compareTo(BigDecimal.ZERO) == 0) {
            //已清数量等于0，待开票
            purchaseReturnOut.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
        } else if (shelfClearQuantity.compareTo(purchaseReturnOut.getQuantityTotal()) == 0) {
            //已清数量等于业务出库数量，已完成
            purchaseReturnOut.setStatus(PurchaseStatus.FINISHED.getStatus());
        } else {
            //部分开票
            purchaseReturnOut.setStatus(PurchaseStatus.PART_BILL.getStatus());
        }
    }


    /**
     * 冲销还原更新上游单据数据状态
     * @param userVO
     * @param dtlIdSet
     * @param orderIdSet
     */
    private void updateReversalBaseOrderData(UserVO userVO, Set<Long> dtlIdSet, Set<Long> orderIdSet) throws Exception {
        if(userVO.getChainType() == ChainType.Headquarters.getCode()){
            for(Long dtlId : dtlIdSet){
                //根据明细id，获取该明细下所有的货位信息的数量
                List<PurchaseReturnOutShelf> outShelfVOList = purchaseReturnOutShelfMapper.getShelfOutInfoByDtlId(dtlId,userVO.getEnterpriseId());

                //货位的已清数量
                BigDecimal shelfClearQuantity = outShelfVOList.stream().filter(Objects::nonNull)
                        .filter(c->c.getClearQuantity() != null)
                        .map(PurchaseReturnOutShelf::getClearQuantity)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

                PurchaseReturnOutDetail returnOutDetail = purchaseReturnOutDetailMapper.selectByPrimaryKey(dtlId);
                returnOutDetail.setClearQuantity(shelfClearQuantity);
                BigDecimal unClear = returnOutDetail.getQuantity().subtract(shelfClearQuantity);
                returnOutDetail.setUnclearQuantity(unClear);

                setPurchaseDetailStatus(returnOutDetail);

                //更新明细的状态 和 已清 未清数量
                purchaseReturnOutDetailMapper.updateByPrimaryKeySelective(returnOutDetail);

                //更新购进退出明细的状态 和已清、未清数量
                setPurchaseReturnDetailStatus(userVO, returnOutDetail);
            }

            for(Long outId : orderIdSet){

                PurchaseReturnOut purchaseReturnOut = purchaseReturnOutMapper.selectByPrimaryKey(outId);
                List<PurchaseReturnOutShelf> returnOutShelfList = purchaseReturnOutShelfMapper.getShelfOutInfoByOutId(outId, userVO.getEnterpriseId());

                //货位的已清数量
                BigDecimal shelfClearQuantity = returnOutShelfList.stream().filter(Objects::nonNull)
                        .filter(c->c.getClearQuantity() != null)
                        .map(PurchaseReturnOutShelf::getClearQuantity)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);


                setPurchaseReturnOutStatus(purchaseReturnOut, shelfClearQuantity);

                purchaseReturnOutMapper.updateByPrimaryKeySelective(purchaseReturnOut);
                //更新购进退出状态，已清，未清数量
                setPurchaseReturnStatus(userVO, purchaseReturnOut);
            }
        } else if(userVO.getChainType() == ChainType.Division.getCode()) {
            //加盟店
            for(Long dtlId : dtlIdSet){
                //根据明细id，获取该明细下所有的货位信息的数量
                List<DistrInReturnOutShelf> outShelfVOList = distrInReturnOutShelfMapper.selectByDtlIdAndEnterpriseId(dtlId,userVO.getEnterpriseId(),userVO.getParentId());

                //货位的已清数量
                BigDecimal shelfClearQuantity = outShelfVOList.stream().filter(Objects::nonNull)
                        .filter(c->c.getClearQuantity() != null)
                        .map(DistrInReturnOutShelf::getClearQuantity)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

                DistrInReturnOutDetail returnOutDetail = distrInReturnOutDetailMapper.selectByPrimaryKey(dtlId);

                returnOutDetail.setClearQuantity(shelfClearQuantity);

                BigDecimal unClear = returnOutDetail.getQuantity().subtract(shelfClearQuantity);
                returnOutDetail.setUnclearQuantity(unClear);
                setDistrDetailStatus(returnOutDetail);
                //更新明细的状态 和 已清 未清数量
                distrInReturnOutDetailMapper.updateByPrimaryKeySelective(returnOutDetail);

                //更新配进退出明细的状态 和已清、未清数量
                setDistrInReturnStatus(userVO, returnOutDetail);
            }

            for(Long outId : orderIdSet){

                DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(outId);

                List<DistrInReturnOutShelf> returnOutShelfList = distrInReturnOutShelfMapper.getShelfOutInfoByOutId(outId, userVO.getEnterpriseId());

                //货位的已清数量
                BigDecimal shelfClearQuantity = returnOutShelfList.stream().filter(Objects::nonNull)
                        .filter(c->c.getClearQuantity() != null)
                        .map(DistrInReturnOutShelf::getClearQuantity)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

                setDistrInReturnOutStatus(distrInReturnOut, shelfClearQuantity);

                distrInReturnOutMapper.updateByPrimaryKeySelective(distrInReturnOut);
                //更新配进退出状态，已清，未清数量
                setDistrInReturnStatus(userVO, distrInReturnOut);
            }

        }
    }

    private void setDistrDetailStatus(DistrInReturnOutDetail returnOutDetail) {
        if(returnOutDetail.getClearQuantity().compareTo(BigDecimal.ZERO) == 0) {
            //已清数量等于0，待开票
            returnOutDetail.setStatus(DistrInReturnStatus.WAIT_BILL);
        } else if (returnOutDetail.getClearQuantity().compareTo(returnOutDetail.getQuantity()) == 0) {
            //已清数量等于业务出库数量，已完成
            returnOutDetail.setStatus(DistrInReturnStatus.FINISHED);
        } else {
            //部分开票
            returnOutDetail.setStatus(DistrInReturnStatus.PART_BILL);
        }
    }

    private void setPurchaseDetailStatus(PurchaseReturnOutDetail returnOutDetail) {
        if(returnOutDetail.getClearQuantity().compareTo(BigDecimal.ZERO) == 0) {
            //已清数量等于0，待开票
            returnOutDetail.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
        } else if (returnOutDetail.getClearQuantity().compareTo(returnOutDetail.getQuantity()) == 0) {
            //已清数量等于业务出库数量，已完成
            returnOutDetail.setStatus(PurchaseStatus.FINISHED.getStatus());
        } else {
            //部分开票
            returnOutDetail.setStatus(PurchaseStatus.PART_BILL.getStatus());
        }
    }

    private void setDistrInReturnOutStatus(DistrInReturnOut distrInReturnOut, BigDecimal shelfClearQuantity) {
        if(shelfClearQuantity.compareTo(BigDecimal.ZERO) == 0) {
            //已清数量等于0，待开票
            distrInReturnOut.setStatus(DistrInReturnStatus.WAIT_BILL);
        } else if (shelfClearQuantity.compareTo(distrInReturnOut.getQuantityTotal()) == 0) {
            //已清数量等于业务出库数量，已完成
            distrInReturnOut.setStatus(DistrInReturnStatus.FINISHED);
        } else {
            //部分开票
            distrInReturnOut.setStatus(DistrInReturnStatus.PART_BILL);
        }
    }


    /**
     * 保存或者修改应付贷项凭证
     *
     * @param paymentVoucherVO
     * @param userVO
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveAndUpdatePaymentVoucher(PaymentVoucherVO paymentVoucherVO, UserVO userVO) throws Exception {
        if(paymentVoucherVO.getId() == null){
            //新增
            return savePaymentVoucher(paymentVoucherVO,userVO);
        } else {
            //修改
            return updatePaymentVoucher(paymentVoucherVO,userVO);
        }

    }

    /**
     * 保存应付贷项凭证草稿
     *
     * @param userVO
     * @param draftCache
     * @return
     */
    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) {


        draftCache.setOrderCode(OrderRule.PAYMENT_VOUCHER.getCodePrefix());

        draftCache.setEnterpriseId(userVO.getEnterpriseId());

        draftCache = redisComponent.saveDraftCacheVO(draftCache);
        return draftCache;
    }

    /**
     * 删除缓存
     *
     * @param enterpriseId
     * @param type
     * @param redisKeyValue
     */
    @Override
    public void removeDraftCache(Long supplierId,Long enterpriseId, String type, String redisKeyValue) {
        redisComponent.removeDraftCacheVO(supplierId,enterpriseId,type,redisKeyValue);
    }

    /**
     * 获取缓存
     *
     * @param userVO
     * @return
     */
    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO,Long supplierId) {
        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.PAYMENT_VOUCHER.getCodePrefix());
        draftCacheVO.setSupplierId(supplierId);
        return redisComponent.getDraftCacheVO(draftCacheVO);
    }

    /**
     * 修改应付贷项凭证
     *
     * @param paymentVoucherVO
     */
    @Override
    public String updatePaymentVoucher(PaymentVoucherVO paymentVoucherVO,UserVO userVO) throws Exception {
        PaymentVoucher paymentVoucher = (PaymentVoucher) EntityUtils.reflectUpdateSetDefaultValue(PaymentVoucher.class, userVO);

        if(paymentVoucherVO.getId() == null) throw new BusinessException("业务主单id不能为空");
        paymentVoucher.setId(paymentVoucherVO.getId());

        PaymentVoucher voucher = paymentVoucherMapper.selectByPrimaryKey(paymentVoucherVO.getId());
        if(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus() != voucher.getStatus()) throw new BusinessException("只有待付款的可以修改");

        paymentVoucher.setPostDate(paymentVoucherVO.getPostDate());
        //过账人员
        Long postManId = paymentVoucherVO.getPostManId();
        User postMan = commonComponent.getExistUser(userVO.getEnterpriseId(), postManId, "过账");
        paymentVoucher.setPostManId(postMan.getId());
        paymentVoucher.setPostManCode(postMan.getCode());
        paymentVoucher.setPostManName(postMan.getName());
        paymentVoucher.setRemark(paymentVoucherVO.getRemark());

        //查询原有应付贷项凭证记录
        PaymentVoucher oldPaymentVoucher = voucher;

        //插入应付贷项凭证主单
        paymentVoucherMapper.updateByPrimaryKeySelective(paymentVoucher);

        List<PaymentVoucherModifyRecord> paymentVoucherModifyRecords = getPaymentVoucherModifyRecord(userVO, paymentVoucherVO, paymentVoucher, oldPaymentVoucher);
        for (PaymentVoucherModifyRecord paymentVoucherModifyRecord : paymentVoucherModifyRecords) {
            paymentVoucherModifyRecordMapper.insertSelective(paymentVoucherModifyRecord);
        }
        return voucher.getCode();

    }

    private void generatePaymentVoucherDetail(UserVO userVO, PaymentVoucher paymentVoucher, PaymentVoucherDetailVO detailVO, PaymentVoucherDetail voucherDetail) throws Exception {
        //设置上游单据属性
        voucherDetail.setVoucherId(paymentVoucher.getId());
        voucherDetail.setBaseOrderId(detailVO.getBaseOrderId());
        voucherDetail.setBaseOrderCode(detailVO.getBaseOrderCode());
        Integer baseOrderType = detailVO.getBaseOrderType();
        voucherDetail.setBaseOrderType(baseOrderType);
        voucherDetail.setBaseOrderDate(detailVO.getBaseOrderDate());
        Long baseDtlId = detailVO.getBaseDtlId();
        voucherDetail.setBaseDtlId(baseDtlId);
        voucherDetail.setBaseShelfDtlId(detailVO.getBaseShelfDtlId());

        //设置商品属性
        commonComponent.getExistGoods(userVO.getEnterpriseId(),detailVO.getGoodsId(),voucherDetail);

        //批号，生产日期，有效期
        voucherDetail.setLotNumber(detailVO.getLotNumber());
        voucherDetail.setProductDate(detailVO.getProductDate());
        voucherDetail.setValidDate(detailVO.getValidDate());

        //数量,单价
        voucherDetail.setQuantity(detailVO.getQuantity());
        voucherDetail.setUnitPrice(detailVO.getUnitPrice());


        //税率
        GoodsTaxRate existGoodsTax = commonComponent.getExistGoodsTax(userVO.getEnterpriseId(), detailVO.getTaxRateId());
        voucherDetail.setTaxRateId(existGoodsTax.getId());
        voucherDetail.setTaxRate(existGoodsTax.getTaxRate());

        //行号
        commonComponent.getExistLineNum(detailVO.getLineNum());
        voucherDetail.setLineNum(detailVO.getLineNum());

        //状态
        voucherDetail.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());

        //金额计算
        BigDecimal amount = detailVO.getUnitPrice().multiply(detailVO.getQuantity());
        voucherDetail.setAmount(amount);

        //不含税金额
        BigDecimal notaxAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(amount,detailVO.getTaxRate());
        voucherDetail.setNotaxAmount(notaxAmount);
        // 不含税单价：金额/(1+税率)
        BigDecimal notaxPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmount, detailVO.getQuantity());
        voucherDetail.setNotaxPrice(notaxPrice);
        //税额detailVO.getAmount()
        BigDecimal taxAmount = detailVO.getAmount().subtract(detailVO.getNotaxAmount());
        voucherDetail.setTaxAmount(taxAmount);

        //
        if(OrderRule.PURCHASE_RETURN_OUT.getType().equals(baseOrderType) && userVO.getChainType() == ChainType.Headquarters.getCode()){
            //购进退出出库
            PurchaseReturnOutShelf returnOutShelf = purchaseReturnOutShelfMapper.selectByPrimaryKey(detailVO.getBaseShelfDtlId());
            if(returnOutShelf == null) throw new BusinessException("购进退出出库货位不单据信息存在");

            BigDecimal quantity = returnOutShelf.getQuantity();
            if(quantity.compareTo(detailVO.getQuantity()) == -1){
                throw new BusinessException("数量不能大于原单据数量");
            }
            BigDecimal orgUnitPrice = returnOutShelf.getRealPrice();//原业务单据实际单价
            BigDecimal orgNotaxRealPrice = returnOutShelf.getNotaxRealPrice();//原业务的不含税实际单价
            if(orgUnitPrice.compareTo(detailVO.getUnitPrice()) == 0 && returnOutShelf.getTaxRateId().equals(detailVO.getTaxRateId())){
                calculateDiffZero(voucherDetail);
            } else {
                //金额差额，不含税金额差额，税额差额
                calculateDiff(detailVO, voucherDetail, orgUnitPrice, orgNotaxRealPrice);
            }

        } else if(OrderRule.DISTR_IN_RETURN_OUT.getType().equals(baseOrderType) && userVO.getChainType() == ChainType.Division.getCode()){
            //配进退出出库
            DistrInReturnOutShelf distrInReturnOutShelf = distrInReturnOutShelfMapper.selectByPrimaryKey(detailVO.getBaseShelfDtlId());
            if(distrInReturnOutShelf == null) throw new BusinessException("配进退出出库货位单据信息不存在");

            BigDecimal quantity = distrInReturnOutShelf.getQuantity();
            if(quantity.compareTo(detailVO.getQuantity()) == -1){
                throw new BusinessException("数量不能大于原单据数量");
            }

            BigDecimal orgUnitPrice = distrInReturnOutShelf.getRealPrice();//原业务单据实际单价
            BigDecimal orgNotaxRealPrice = distrInReturnOutShelf.getNotaxRealPrice();//原业务的不含税实际单价

            if(orgUnitPrice.compareTo(detailVO.getUnitPrice()) == 0 && distrInReturnOutShelf.getTaxRateId().equals(detailVO.getTaxRateId())){
                calculateDiffZero(voucherDetail);
            } else {
                //金额差额，不含税金额差额，税额差额
                calculateDiff(detailVO, voucherDetail, orgUnitPrice, orgNotaxRealPrice);
            }
        } else {
            throw new BusinessException("业务单据类型和企业不匹配");
        }


    }

    private void calculateDiffZero(PaymentVoucherDetail voucherDetail) {
        voucherDetail.setDiffAmount(BigDecimal.ZERO);
        voucherDetail.setDiffNotaxAmount(BigDecimal.ZERO);
        voucherDetail.setDiffTaxAmount(BigDecimal.ZERO);
    }

    private void calculateDiff(PaymentVoucherDetailVO detailVO, PaymentVoucherDetail voucherDetail, BigDecimal orgUnitPrice, BigDecimal orgNotaxRealPrice) {

        BigDecimal diffUnitPrice = voucherDetail.getUnitPrice().subtract(orgUnitPrice);//单价差额
        BigDecimal diffNotaxPrice = voucherDetail.getNotaxPrice().subtract(orgNotaxRealPrice);//不含税单价差额

        //金额差额，不含税金额差额，税额差额
        BigDecimal diffAmount = diffUnitPrice.multiply(detailVO.getQuantity());
        voucherDetail.setDiffAmount(diffAmount);
        BigDecimal diffNotaxAmount = diffNotaxPrice.multiply(detailVO.getQuantity());
        voucherDetail.setDiffNotaxAmount(diffNotaxAmount);
        voucherDetail.setDiffTaxAmount(diffAmount.subtract(diffNotaxAmount));
    }

    private void generatePaymentVoucher(PaymentVoucherVO paymentVoucherVO, UserVO userVO, PaymentVoucher paymentVoucher) {
        paymentVoucher.setPostDate(paymentVoucherVO.getPostDate());
        //过账人员
        Long postManId = paymentVoucherVO.getPostManId();
        User postMan = commonComponent.getExistUser(userVO.getEnterpriseId(), postManId, "过账");
        paymentVoucher.setPostManId(postMan.getId());
        paymentVoucher.setPostManCode(postMan.getCode());
        paymentVoucher.setPostManName(postMan.getName());

        //供货单位
        Long supplierId = paymentVoucherVO.getSupplierId();
        if(userVO.getChainType() == ChainType.Headquarters.getCode() && paymentVoucherVO.getType() == 2){
            Supplier supplier = commonComponent.getExistSupplier(userVO.getEnterpriseId(), supplierId);
            paymentVoucher.setSupplierId(supplier.getId());
            paymentVoucher.setSupplierCode(supplier.getCode());
            paymentVoucher.setSupplierName(supplier.getName());
        } else if(userVO.getChainType() == ChainType.Division.getCode()){
            if(paymentVoucherVO.getType() == 2){
                //供货单位类型
                Supplier supplier = commonComponent.getExistSupplier(userVO.getEnterpriseId(), supplierId);
                paymentVoucher.setSupplierId(supplier.getId());
                paymentVoucher.setSupplierCode(supplier.getCode());
                paymentVoucher.setSupplierName(supplier.getName());
            } else if(paymentVoucherVO.getType() == 1){
                //企业类型
                Enterprise enterprise = commonComponent.getExistEnterprise(userVO.getParentId());
                paymentVoucher.setSupplierId(enterprise.getId());
                paymentVoucher.setSupplierCode(enterprise.getCode());
                paymentVoucher.setSupplierName(enterprise.getName());
            } else {
                throw new BusinessException("供货单位类型TYPE错误");
            }
        } else {
            throw new BusinessException("供货单位类型TYPE错误");
        }


        //单据状态
        paymentVoucher.setStatus(ReceivableInvoiceStatus.WAIT_RECEIVABLES.getStatus());

        //备注
        paymentVoucher.setRemark(paymentVoucherVO.getRemark());

        paymentVoucher.setQuantityTotal(BigDecimal.ZERO);
        paymentVoucher.setAmountTotal(BigDecimal.ZERO);
        paymentVoucher.setNotaxAmountTotal(BigDecimal.ZERO);
        paymentVoucher.setTaxAmountTotal(BigDecimal.ZERO);
        paymentVoucher.setDiffAmountTotal(BigDecimal.ZERO);
        paymentVoucher.setDiffNotaxAmountTotal(BigDecimal.ZERO);
        paymentVoucher.setDiffTaxAmountTotal(BigDecimal.ZERO);
        paymentVoucher.setClearAmountTotal(BigDecimal.ZERO);
        paymentVoucher.setUnclearAmountTotal(BigDecimal.ZERO);
        paymentVoucher.setVarietiesQuantity(0);
    }






    public List<PaymentVoucherModifyRecord> getPaymentVoucherModifyRecord(UserVO userVO,
                                                                          PaymentVoucherVO paymentVoucherVO,
                                                                          PaymentVoucher newPaymentVoucher,
                                                                          PaymentVoucher oldPaymentVoucher
                                                                          ) throws Exception {

        Map<String,Object> newPaymentVoucherMap = modifyRecordCompoent.getFieldsMap(newPaymentVoucher);


        Map<String,Object> paymentVoucherMap = modifyRecordCompoent.getFieldsMap(oldPaymentVoucher);

        Map<String,String> fieldMustMap = fieldMustMap();

        List<PaymentVoucherModifyRecord> goodsRecord = getModifyRecordList( userVO,
                                                                            paymentVoucherVO.getId(),
                                                                            paymentVoucherVO.getUpdateReason(),
                                                                            "saas_payment_voucher",
                                                                            paymentVoucherMap,
                                                                            newPaymentVoucherMap,
                                                                            fieldMustMap);
        return goodsRecord;
    }






    /**
     *
     * @param userVO
     * @param id
     * @param updateReason
     * @param tableName
     * @param keyId
     * @param updateTime
     * @param valMap
     * @param newMap
     * @param fieldMustMap
     * @return
     */
    private List<PaymentVoucherModifyRecord> getModifyRecordList(UserVO userVO ,
                                                                 Long id,
                                                                 String updateReason,
                                                                 String tableName,
                                                                 Map<String,Object> valMap,
                                                                 Map<String,Object> newMap ,
                                                                 Map<String,String> fieldMustMap) throws Exception {

        List<PaymentVoucherModifyRecord> modifyRecordWithBLOBs = new ArrayList<>();

        for(Map.Entry<String,String> entry : fieldMustMap.entrySet()){
            Object obj = valMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if(obj != null ){
                if(obj instanceof BigDecimal && newObj instanceof BigDecimal && ((BigDecimal) obj).compareTo((BigDecimal) newObj) == 0){
                    continue;
                } else if(!obj.equals(newObj)){
                    executeGoodsModifyRecord(userVO, id,updateReason, tableName, modifyRecordWithBLOBs, entry, obj, newObj);
                }

            }
            if(obj == null && newObj != null){
                executeGoodsModifyRecord(userVO, id,updateReason, tableName, modifyRecordWithBLOBs, entry, obj, newObj);
            }
        }

        return modifyRecordWithBLOBs;
    }

    /**
     *
     * @param userVO
     * @param id 被修改记录的主键
     * @param updateReason 修改原因
     * @param tableName 表名
     * @param modifyRecordWithBLOBs
     * @param entry
     * @param obj 修改的值
     * @param newObj  原值
     * @throws Exception
     */
    private void executeGoodsModifyRecord(UserVO userVO,
                                         Long id,
                                         String updateReason,
                                         String tableName,
                                         List<PaymentVoucherModifyRecord> modifyRecordWithBLOBs,
                                         Map.Entry<String, String> entry,
                                         Object obj,
                                         Object newObj) throws Exception {

        PaymentVoucherModifyRecord voucherModifyRecord = (PaymentVoucherModifyRecord) EntityUtils.reflectAddSetDefaultValue(PaymentVoucherModifyRecord.class,userVO);

        voucherModifyRecord.setKeyId(id);
        voucherModifyRecord.setEnterpriseId(userVO.getEnterpriseId());
        voucherModifyRecord.setParentId(userVO.getParentId());
        voucherModifyRecord.setTableName(tableName);
        voucherModifyRecord.setColumnEnName(entry.getKey());
        voucherModifyRecord.setColumnChName(entry.getValue());


        voucherModifyRecord.setOldContent(obj == null ? "" : obj.toString());
        voucherModifyRecord.setNewContent(newObj == null ? "" : newObj.toString());
        voucherModifyRecord.setReason(updateReason == null ? "" : updateReason);
        modifyRecordWithBLOBs.add(voucherModifyRecord);
    }


    private Map<String,String> fieldMustMap(){
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("postDate","过账日期");
        fieldNames.put("postManId","过账人Id");
        fieldNames.put("postManCode","过账人员编码");
        fieldNames.put("postManName","过账人员名称");

        fieldNames.put("remark","备注");

        fieldNames.put("createrId","创建人ID");
        fieldNames.put("createrCode","创建人编码");
        fieldNames.put("createrName","创建人名称");
        fieldNames.put("createTime","创建时间");
        fieldNames.put("modifierId","最后修改人ID");
        fieldNames.put("modifierCode","最后修改人编码");
        fieldNames.put("modifierName","最后修改人名称");
        fieldNames.put("updateTime","更新时间");

        return fieldNames;
    }




    @Override
    public void getPaymentVoucherModifyRecord(UserVO userVO, Page<List<PaymentVoucherModifyRecord>> page, Long paymentId){
        Integer count = paymentVoucherModifyRecordMapper.getPaymentVoucherModifyRecordCount(userVO.getEnterpriseId(), paymentId);
        if(count == null || count == 0){
            page.setTotalRecord(count);
            page.setResult(new ArrayList<>(1));
            return;
        }
        page.setTotalRecord(count);
        page.setResult(paymentVoucherModifyRecordMapper.getPaymentVoucherModifyRecord(userVO.getEnterpriseId(),paymentId,page.getStart(),page.getPageSize()));
    }

    @Override
    public void export(OutputStream output, UserVO userVO, Long paymentId) throws Exception {

        PaymentVoucherVO paymentVoucherVO = paymentVoucherMapper.getPaymentVoucherDetailById(paymentId, userVO.getEnterpriseId());

        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "应付贷项凭证";
        //副标题内容
        List<TwoTuple<String,String>> twoTupleList = new ArrayList<>(Arrays.asList(
                new TwoTuple<>("供货单位编码",StringUtil.transferTrimStr(paymentVoucherVO.getSupplierCode())),
                new TwoTuple<>("供货单位名称",StringUtil.transferTrimStr(paymentVoucherVO.getSupplierName())),
                new TwoTuple<>("过账日期",StringUtil.transferTrimStr(paymentVoucherVO.getPostDate())),
                new TwoTuple<>("过账人员",StringUtil.transferTrimStr(paymentVoucherVO.getPostManName())),
                new TwoTuple<>("单号",StringUtil.transferTrimStr(paymentVoucherVO.getCode())),
                new TwoTuple<>("备注 ",StringUtil.transferTrimStr(paymentVoucherVO.getRemark()))));

        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("baseOrderDate","日期");
        headerHashMap.put("baseOrderCode","单号");
        headerHashMap.put("goodsCode","商品编码");
        headerHashMap.put("barcode","条形码");
        headerHashMap.put("goodsGenericName","通用名称");
        headerHashMap.put("goodsName","商品名称");
        headerHashMap.put("dosageName","剂型");
        headerHashMap.put("goodsSpecification","规格");
        headerHashMap.put("unitName","单位");
        headerHashMap.put("manufacturer","生产厂商");
        headerHashMap.put("goodsPlace","产地");
        headerHashMap.put("approvalNumber","批准文号");
        headerHashMap.put("lotNumber","批号");
        headerHashMap.put("productDate","生产日期");
        headerHashMap.put("validDate","有效期至");

        headerHashMap.put("quantity","数量");
        headerHashMap.put("unitPrice","单价");
//        headerHashMap.put("lineDiscount","折扣");
        headerHashMap.put("amount","金额");

        headerHashMap.put("taxRate","税率");
        headerHashMap.put("notaxPrice","不含税单价");

        headerHashMap.put("notaxAmount","不含税金额");
        headerHashMap.put("taxAmount","税额");
        headerHashMap.put("diffAmount","金额差额");
        headerHashMap.put("diffNotaxAmount","不含税金额差额");
        headerHashMap.put("diffTaxAmount","税额差额");

        headerHashMap.put("remark","备注");


        //合计内容
        Map<String,String> totalFieldMap = new HashMap<>();

        totalFieldMap.put("quantity", StringUtil.transferTrimStr(paymentVoucherVO.getQuantityTotal()));//数量合计
        totalFieldMap.put("amount",StringUtil.transferTrimStr(paymentVoucherVO.getAmountTotal()));//金额合计
        totalFieldMap.put("notaxAmount",StringUtil.transferTrimStr(paymentVoucherVO.getNotaxAmountTotal()));//不含税金额合计
        totalFieldMap.put("taxAmount",StringUtil.transferTrimStr(paymentVoucherVO.getTaxAmountTotal()));//税额
        totalFieldMap.put("diffAmount",StringUtil.transferTrimStr(paymentVoucherVO.getDiffAmountTotal()));//金额差额
        totalFieldMap.put("diffNotaxAmount",StringUtil.transferTrimStr(paymentVoucherVO.getDiffNotaxAmountTotal()));//不含税金额差额
        totalFieldMap.put("diffTaxAmount",StringUtil.transferTrimStr(paymentVoucherVO.getDiffTaxAmountTotal()));//税额差额

        excelComponent.exportExcel(output,firstTitle,secondTitle,twoTupleList,headerHashMap,paymentVoucherVO.getPaymentVoucherDetailVOList(),totalFieldMap);


    }

    @Override
    public void getGoodsInfoByParam(UserVO userVO,Page<List<PurchaseAndDistrOutShelfVO>> page,String param,Long supplierId) {
        RequestGoodsParamVO paramVO = new RequestGoodsParamVO();
        paramVO.setParam(param);
        paramVO.setSupplierId(supplierId);
        paramVO.setEnterpriseId(userVO.getEnterpriseId());
        paramVO.setStart(page.getStart());
        paramVO.setPageSize(page.getPageSize());


        Integer chainType = userVO.getChainType();
        if(chainType == ChainType.Headquarters.getCode()){
            //总部 购进退出出库
            paramVO.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
            paramVO.setPartBillStatus(PurchaseStatus.PART_BILL.getStatus());
            Integer count = purchaseReturnOutShelfMapper.getPurchaseReturnOutDetailByOutIdSCount(paramVO);
            List<PurchaseAndDistrOutShelfVO> outShelfVOList = purchaseReturnOutShelfMapper.getPurchaseReturnOutDetailByOutIdS(paramVO);
            page.setTotalRecord(count);
            page.setResult(outShelfVOList);
        } else if(chainType == ChainType.Division.getCode()){
            //加盟店  配进退出出库
            paramVO.setStatus(PubStatus.distrInReturnStatus.WAIT_BILL);
            paramVO.setPartBillStatus(PubStatus.distrInReturnStatus.PART_BILL);
            Integer count = distrInReturnOutShelfMapper.getDistrReturnOutDetailByOutIdSCount(paramVO);
            List<PurchaseAndDistrOutShelfVO> outShelfVOList = distrInReturnOutShelfMapper.getDistrReturnOutDetailByOutIdS(paramVO);
            page.setResult(outShelfVOList);
            page.setTotalRecord(count);
        } else {
            return ;
        }
    }

    @Override
    public void exportUpdateRecord(OutputStream output, Long paymentId, UserVO userVO) throws Exception {

        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "应付贷项凭证修改记录";

        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("updateTime","修改时间");
        headerHashMap.put("modifierName","修改人员");
        headerHashMap.put("reason","修改原因");
        headerHashMap.put("columnChName","修改项目");
        headerHashMap.put("oldContent","原内容");
        headerHashMap.put("newContent","新内容");

        List<PaymentVoucherModifyRecord> list = paymentVoucherModifyRecordMapper.getPaymentVoucherModifyRecord(userVO.getEnterpriseId(), paymentId, null, null);

        excelComponent.exportExcel(output,firstTitle,secondTitle,null,headerHashMap,list,null);
    }



}