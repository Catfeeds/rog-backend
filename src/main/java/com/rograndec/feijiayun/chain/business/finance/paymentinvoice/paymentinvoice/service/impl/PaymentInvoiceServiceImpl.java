package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceInfoMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao.PaymentInvoiceModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoiceInfo;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoiceModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.service.PaymentInvoiceService;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo.*;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.IntrospectionException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentInvoiceServiceImpl implements PaymentInvoiceService{

    @Autowired
    private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;

    @Autowired
    private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;

    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;

    @Autowired
    private DistrInMapper distrInMapper;

    @Autowired
    private DistrInDetailMapper distrInDetailMapper;

    @Autowired
    private DistrInShelfMapper distrInShelfMapper;

    @Autowired
    private PaymentInvoiceMapper paymentInvoiceMapper;

    @Autowired
    private PaymentInvoiceDetailMapper paymentInvoiceDetailMapper;

    @Autowired
    private PaymentInvoiceModifyRecordMapper paymentInvoiceModifyRecordMapper;

    @Autowired
    private PaymentInvoiceInfoMapper paymentInvoiceInfoMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private OrikaMapperFactory orikaMapperFactory;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private FinanceComponent financeComponent;

    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaymentInvoice save(UserVO userVO, PaymentInvoiceSaveVO paymentInvoiceSaveVO) throws Exception {


        Long billManId = paymentInvoiceSaveVO.getBillManId();

        User billMan = userMapper.selectByPrimaryKey(billManId);

        /**
         * 数据类型,默认为价格清单:0,1:企业,2:供应商"
         */
        Integer type = paymentInvoiceSaveVO.getType();
        Long supplierId = paymentInvoiceSaveVO.getSupplierId();
        Supplier supplier = null;
        Enterprise enterprise = null;
        if(2 == type){
            supplier = supplierMapper.selectByPrimaryKey(supplierId);
        }else if(1 == type){
            enterprise = enterpriseMapper.selectByPrimaryKey(supplierId);
        }
        String code = orderCodeComponent.generate(
                OrderRule.PAYMENT_INVOICE.getCodePrefix()
                , userVO.getEnterpriseId()
                , userVO.getEnterpriseCode()
        );

        boolean isAdd = null == paymentInvoiceSaveVO.getId() ? true : false;

        PaymentInvoice paymentInvoice = PaymentInvoice.generatePaymentInvoice(
                userVO,
                code,
                billMan,
                supplier,
                enterprise,
                paymentInvoiceSaveVO,
                isAdd
                );


        List<PaymentInvoiceDetailSaveVO> paymentInvoiceDetailSaveVOS = paymentInvoiceSaveVO.getPaymentInvoiceDetails();



        List<Long> baseShelfDtlIds = PaymentInvoiceDetailSaveVO.getBaseShelfDtlIds(paymentInvoiceDetailSaveVOS);

        List<Long> baseDtlIds = PaymentInvoiceDetailSaveVO.getBaseDtlIds(paymentInvoiceDetailSaveVOS);

        List<Long> baseIds = PaymentInvoiceDetailSaveVO.getBaseIds(paymentInvoiceDetailSaveVOS);

        List<Long> taxRateIds = PaymentInvoiceDetailSaveVO.getTaxRateIds(paymentInvoiceDetailSaveVOS);

        List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectByIds(taxRateIds);


        if(ChainType.Headquarters.getCode() == userVO.getChainType()){

            List<PurchaseInStorageShelf> purchaseInStorageShelves = purchaseInStorageShelfMapper.selectByIds(baseShelfDtlIds);

            List<PurchaseInStorageDetail> purchaseInStorageDetails = purchaseInStorageDetailMapper.selectByIds(userVO.getEnterpriseId(), baseDtlIds);

            List<PurchaseInStorage> purchaseInStorages = purchaseInStorageMapper.selectByIds(baseIds);

            List<PaymentInvoiceDetail> paymentInvoiceDetails = PaymentInvoiceDetail.getPaymentInvoiceDetails2PurchaseInStorage(
                    userVO,
                    goodsTaxRates,
                    purchaseInStorages,
                    purchaseInStorageDetails,
                    purchaseInStorageShelves,
                    paymentInvoiceDetailSaveVOS
            );

            PaymentInvoice.afterSetPrepayInvoice(paymentInvoice,paymentInvoiceDetails);

            paymentInvoiceMapper.insertSelective(paymentInvoice);

            PaymentInvoiceDetail.afterSet(paymentInvoice,paymentInvoiceDetails);

            for(PaymentInvoiceDetail paymentInvoiceDetail : paymentInvoiceDetails){

                for(PurchaseInStorageShelf purchaseInStorageShelf : purchaseInStorageShelves){

                    if(purchaseInStorageShelf.getId().equals(paymentInvoiceDetail.getBaseShelfDtlId())){

                        PaymentInvoiceDetail.afterSetDiff2PurchaseInStorageShelf(paymentInvoiceDetail,purchaseInStorageShelf);

                    }

                }

            }

            PaymentInvoice.afterSetPrepayInvoiceDiff(paymentInvoice,paymentInvoiceDetails);

            paymentInvoiceMapper.updateByPrimaryKeySelective(paymentInvoice);


            for(PaymentInvoiceDetail paymentInvoiceDetail : paymentInvoiceDetails) {
                paymentInvoiceDetailMapper.insertSelective(paymentInvoiceDetail);
            }


            for(PurchaseInStorageShelf purchaseInStorageShelf : purchaseInStorageShelves){

                GoodsBusiness goods = goodsBusinessMapper.selectByGoodsId(purchaseInStorageShelf.getGoodsId());


                Integer managementMode = goods.getManagementMode();

                /**
                 * 经营方式（0-购销；1-实销实结）
                 */
                if(1 == managementMode){

                    /**
                     *已核销数量
                     */
                    BigDecimal verificationQuantity = purchaseInStorageShelf.getVerificationQuantity();

                    /**
                     * 已清数量
                     */
                    BigDecimal cq = purchaseInStorageShelf.getClearQuantity();

                    /**
                     * 实销实结商品 已清不能大于已核销数量
                     */
                    if(cq.compareTo(verificationQuantity) > 0){
                        throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"实销实结商品 已清数量大于已核销数量");

                    }

                }

                purchaseInStorageShelfMapper.updateByPrimaryKeySelective(purchaseInStorageShelf);
            }

            for(PurchaseInStorageShelf purchaseInStorageShelf : purchaseInStorageShelves){
                PurchaseInStorageDetail detail = commonComponent.clearPurchaseInStorageDetail(userVO, purchaseInStorageShelf);
                PurchaseInStorage purchaseInStorage = commonComponent.clearPurchaseInStorage(detail);
            }

            /**
             * 财务关键表数据
             */
            financeComponent.paymentInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(userVO, paymentInvoice, paymentInvoiceDetails, "save");


        }else if(ChainType.Division.getCode() == userVO.getChainType()){

            List<DistrInShelf> distrInShelfs = distrInShelfMapper.selectByIds(baseShelfDtlIds);

            List<DistrInDetail> distrInDetails = distrInDetailMapper.selectByIds(baseDtlIds);

            List<DistrIn> distrIns = distrInMapper.selectByIds(baseIds);

            List<PaymentInvoiceDetail> paymentInvoiceDetails = PaymentInvoiceDetail.getPaymentInvoiceDetails2DistrIn(
                    userVO,
                    goodsTaxRates,
                    distrIns,
                    distrInDetails,
                    distrInShelfs,
                    paymentInvoiceDetailSaveVOS
            );

            PaymentInvoice.afterSetPrepayInvoice(paymentInvoice,paymentInvoiceDetails);

            paymentInvoiceMapper.insertSelective(paymentInvoice);

            PaymentInvoiceDetail.afterSet(paymentInvoice,paymentInvoiceDetails);

            for(PaymentInvoiceDetail paymentInvoiceDetail : paymentInvoiceDetails){

                for(DistrInShelf distrInShelf : distrInShelfs){

                    if(distrInShelf.getId().equals(paymentInvoiceDetail.getBaseShelfDtlId())){

                        PaymentInvoiceDetail.afterSetDiff2DistrInShelf(paymentInvoiceDetail,distrInShelf);

                    }

                }

            }

            PaymentInvoice.afterSetPrepayInvoiceDiff(paymentInvoice,paymentInvoiceDetails);
            paymentInvoiceMapper.updateByPrimaryKeySelective(paymentInvoice);

            for(PaymentInvoiceDetail paymentInvoiceDetail : paymentInvoiceDetails) {
                paymentInvoiceDetailMapper.insertSelective(paymentInvoiceDetail);
            }


            for(DistrInShelf distrInShelf : distrInShelfs){

                GoodsBusiness goods = goodsBusinessMapper.selectByGoodsId(distrInShelf.getGoodsId());


                Integer managementMode = goods.getManagementMode();

                /**
                 * 经营方式（0-购销；1-实销实结）
                 */
                if(1 == managementMode){

                    /**
                     *已核销数量
                     */
                    BigDecimal verificationQuantity = distrInShelf.getVerificationQuantity();

                    /**
                     * 已清数量
                     */
                    BigDecimal cq = distrInShelf.getClearQuantity();

                    /**
                     * 实销实结商品 已清不能大于已核销数量
                     */
                    if(cq.compareTo(verificationQuantity) > 0){
                        throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"实销实结商品 已清数量大于已核销数量");

                    }

                }

                distrInShelfMapper.updateByPrimaryKeySelective(distrInShelf);
            }

            for(DistrInShelf distrInShelf : distrInShelfs){
                DistrInDetail detail = commonComponent.clearDistrInDetailDetail(distrInShelf);
                commonComponent.clearDistrIn(detail);
            }


            /**
             * 财务关键表数据
             */
            financeComponent.paymentInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(userVO, paymentInvoice, paymentInvoiceDetails, "save");

        }

        PaymentInvoiceInfoSaveVO paymentInvoiceInfoSaveVO = paymentInvoiceSaveVO.getPaymentInvoiceInfo();
        if(null != paymentInvoiceInfoSaveVO && null !=paymentInvoiceInfoSaveVO.getInvoiceType()){
            PaymentInvoiceInfo paymentInvoiceInfo = new PaymentInvoiceInfo();
            paymentInvoiceInfo = orikaMapperFactory.copyBean(paymentInvoiceInfo, paymentInvoiceInfoSaveVO);
            paymentInvoiceInfo.setEnterpriseId(paymentInvoice.getEnterpriseId());
            paymentInvoiceInfo.setParentId(paymentInvoice.getParentId());
            paymentInvoiceInfo.setInvoiceId(paymentInvoice.getId());
            paymentInvoiceInfo.setId(null);
            paymentInvoiceInfoMapper.insertSelective(paymentInvoiceInfo);
        }

        //删除草稿
        removeDraftCach(paymentInvoice.getSupplierId(),userVO.getEnterpriseId(), OrderRule.PAYMENT_INVOICE.getCodePrefix(), paymentInvoiceSaveVO.getRedisKeyValue());

        return paymentInvoice;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaymentInvoice update(UserVO userVO, PaymentInvoiceSaveVO paymentInvoiceSaveVO) throws Exception {

        /**
         * 只允许编辑：开票日期、开票人员、备注字段
            已冲销单据不允许修改
         */

        Long id = paymentInvoiceSaveVO.getId();

        PaymentInvoice paymentInvoice = paymentInvoiceMapper.selectByPrimaryKey(id);

        User billMan = userMapper.selectByPrimaryKey(paymentInvoiceSaveVO.getBillManId());

        PaymentInvoice newPaymentInvoice = PaymentInvoice.generateUpdatePaymentInvoice(userVO,paymentInvoice, billMan, paymentInvoiceSaveVO);

        paymentInvoiceMapper.updateByPrimaryKeySelective(newPaymentInvoice);

        newPaymentInvoice = paymentInvoiceMapper.selectByPrimaryKey(paymentInvoice.getId());

        List<PaymentInvoiceModifyRecord> paymentInvoiceModifyRecords = PaymentInvoiceModifyRecord.generatePaymentInvoiceModifyRecords(userVO, paymentInvoice, newPaymentInvoice, paymentInvoiceSaveVO);


        paymentInvoiceModifyRecords.forEach(paymentInvoiceModifyRecord -> {

            paymentInvoiceModifyRecord.setReason(paymentInvoiceSaveVO.getReason());
            paymentInvoiceModifyRecordMapper.insertSelective(paymentInvoiceModifyRecord);
        });

        PaymentInvoiceInfoSaveVO paymentInvoiceInfoSaveVO = paymentInvoiceSaveVO.getPaymentInvoiceInfo();

        if(null != paymentInvoiceInfoSaveVO && null != paymentInvoiceInfoSaveVO.getInvoiceType()){

            PaymentInvoiceInfo oldPaymentInvoiceInfo = paymentInvoiceInfoMapper.selectByPrimaryKey(paymentInvoiceInfoSaveVO.getId());

            PaymentInvoiceInfo paymentInvoiceInfo = new PaymentInvoiceInfo();
            paymentInvoiceInfo = orikaMapperFactory.copyBean(paymentInvoiceInfo, paymentInvoiceInfoSaveVO);
            paymentInvoiceInfo.setEnterpriseId(paymentInvoice.getEnterpriseId());
            paymentInvoiceInfo.setParentId(paymentInvoice.getParentId());
            paymentInvoiceInfo.setInvoiceId(paymentInvoice.getId());
            if(null != paymentInvoiceInfoSaveVO.getId())
                paymentInvoiceInfoMapper.updateByPrimaryKeySelective(paymentInvoiceInfo);
            else
                paymentInvoiceInfoMapper.insertSelective(paymentInvoiceInfo);


            PaymentInvoiceInfo newPaymentInvoiceInfo = paymentInvoiceInfoMapper.selectByPrimaryKey(paymentInvoiceInfo.getId());


            if(null != oldPaymentInvoiceInfo){
                Map<String,Object> newPaymentInvoiceInfoMap = modifyRecordCompoent.getFieldsMap(newPaymentInvoiceInfo);

                Map<String,Object> oldPaymentInvoiceInfoMap = modifyRecordCompoent.getFieldsMap(oldPaymentInvoiceInfo);


                List<PaymentInvoiceModifyRecord> paymentInvoiceInfoModifyRecords = getModifyRecordList(userVO, "saas_payment_invoice_info", paymentInvoice.getId(), new Date(), oldPaymentInvoiceInfoMap, newPaymentInvoiceInfoMap, fieldMustMap());

                paymentInvoiceInfoModifyRecords.forEach(paymentInvoiceModifyRecord -> {
                    paymentInvoiceModifyRecord.setReason(paymentInvoiceSaveVO.getReason());
                    paymentInvoiceModifyRecordMapper.insertSelective(paymentInvoiceModifyRecord);
                });
            }


        }


        return paymentInvoice;

    }


    /**
     * 查询应付发票列表
     * @param userVO
     * @param paymentInvoiceQueryParamVO
     * @return
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Override
    public List<PaymentInvoiceResponseVO> queryPaymentInvoiceResponseVOs(UserVO userVO, PaymentInvoiceQueryParamVO paymentInvoiceQueryParamVO, Page page) throws InvocationTargetException, IntrospectionException, IllegalAccessException, NoSuchFieldException {

        Map<String, Object> notNullBeanFeild2Map = ReflectUtils.getNotNullBeanFeild2Map(paymentInvoiceQueryParamVO);
        notNullBeanFeild2Map.put("enterpriseId",userVO.getEnterpriseId());
        String startDate = paymentInvoiceQueryParamVO.getStartDate();
        String endDate = paymentInvoiceQueryParamVO.getEndDate();
        if(!StringUtils.isEmpty(startDate)) {
            notNullBeanFeild2Map.put("startDate",DateUtils.StringToDate(startDate));
        }
        if(!StringUtils.isEmpty(endDate)) {
            notNullBeanFeild2Map.put("endDate",DateUtils.StringToDate(endDate));
        }

        if(null != notNullBeanFeild2Map.get("orderName")){
            String orderName = notNullBeanFeild2Map.get("orderName").toString();
            if(orderName.equals("billDate")){
                notNullBeanFeild2Map.put("orderName","bill_date");
            }

        }

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
        List<PaymentInvoice> paymentInvoices = paymentInvoiceMapper.selectByParam(notNullBeanFeild2Map);

        List<PaymentInvoiceResponseVO> prepayInvoiceResponseVOS = paymentInvoices.stream().map(paymentInvoice -> {

            PaymentInvoiceResponseVO paymentInvoiceResponseVO = new PaymentInvoiceResponseVO();
            paymentInvoiceResponseVO = orikaMapperFactory.copyBean(paymentInvoiceResponseVO, paymentInvoice);
            return paymentInvoiceResponseVO;

        }).collect(Collectors.toList());

        /**
         * 合计信息
         */

        Object status = notNullBeanFeild2Map.get("status");
        List<Integer> list = new ArrayList<>();
        if(null == status){

            list.add(FinancePaymentStatus.PRE_PAYMENT);
            list.add(FinancePaymentStatus.PARTIAL_PAYMENT);
            list.add(FinancePaymentStatus.PAID);
            notNullBeanFeild2Map.put("list",list);
        }else {
            list.add(Integer.parseInt(status.toString()));
            notNullBeanFeild2Map.put("list",list);
        }
        PaymentInvoiceResponseTotalVO paymentInvoiceResponseTotalVO = paymentInvoiceMapper.selectSumByParam(notNullBeanFeild2Map);
        if(null != paymentInvoiceResponseTotalVO){
            paymentInvoiceResponseTotalVO.setPaymentInvoices(prepayInvoiceResponseVOS);
            page.setResult(paymentInvoiceResponseTotalVO);
            page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
            page.setTotalPage(hPage.getPages());
        }else {
            page.setResult(new PaymentInvoiceResponseTotalVO());
            page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
            page.setTotalPage(hPage.getPages());
        }

        return prepayInvoiceResponseVOS;
    }


    @Override
    public PaymentInvoiceResponseVO queryPaymentInvoiceResponseVO(UserVO userVO, Long prepayInvoiceId){

        PaymentInvoice paymentInvoice = paymentInvoiceMapper.selectByPrimaryKey(prepayInvoiceId);

        List<PaymentInvoiceDetail> paymentInvoiceDetails = paymentInvoiceDetailMapper.selectByInvoiceId(paymentInvoice.getId());

        PaymentInvoiceInfo paymentInvoiceInfo = paymentInvoiceInfoMapper.selectByInvoiceId(paymentInvoice.getId());

        PaymentInvoiceResponseVO paymentInvoiceResponseVO = new PaymentInvoiceResponseVO();
        paymentInvoiceResponseVO = orikaMapperFactory.copyBean(paymentInvoiceResponseVO,paymentInvoice);

        List<PaymentInvoiceDetailResponseVO> paymentInvoiceDetailResponseVOS = new ArrayList<>(paymentInvoiceDetails.size());

        for(PaymentInvoiceDetail paymentInvoiceDetail : paymentInvoiceDetails){

            PaymentInvoiceDetailResponseVO paymentInvoiceDetailResponseVO = new PaymentInvoiceDetailResponseVO();

            paymentInvoiceDetailResponseVO = orikaMapperFactory.copyBean(paymentInvoiceDetailResponseVO,paymentInvoiceDetail);

            paymentInvoiceDetailResponseVO.setCode(paymentInvoiceDetail.getBaseOrderCode());

            paymentInvoiceDetailResponseVO.setInDate(paymentInvoiceDetail.getBaseOrderDate());

            paymentInvoiceDetailResponseVO.setAccountQuantity(paymentInvoiceDetail.getQuantity());
            paymentInvoiceDetailResponseVO.setOldAccountQuantity(paymentInvoiceDetail.getQuantity());
            paymentInvoiceDetailResponseVO.setOldUnitPrice(paymentInvoiceDetail.getUnitPrice());
            paymentInvoiceDetailResponseVO.setOldTaxRate(paymentInvoiceDetail.getTaxRate());

            paymentInvoiceDetailResponseVOS.add(paymentInvoiceDetailResponseVO);
        }

        paymentInvoiceResponseVO.setPaymentInvoiceDetails(paymentInvoiceDetailResponseVOS);

        if(null != paymentInvoiceInfo){
            PaymentInvoiceInfoResponseVO paymentInvoiceInfoResponseVO = new PaymentInvoiceInfoResponseVO();
            paymentInvoiceInfoResponseVO =  orikaMapperFactory.copyBean(paymentInvoiceInfoResponseVO,paymentInvoiceInfo);
            paymentInvoiceResponseVO.setPaymentInvoiceInfo(paymentInvoiceInfoResponseVO);
        }


        return paymentInvoiceResponseVO;

    }

    @Override
    public List<PaymentInvoiceGoodsVO> queryPaymentInvoiceGoods(UserVO userVO,Long supplierId, Integer type,String param,Page page){

        if(ChainType.Headquarters.getCode() == userVO.getChainType()){

            /**
             * 总部查询 查询采购入库单
             */
            List<Integer> list = new ArrayList<>();
            list.add(PurchaseStatus.WAIT_BILL.getStatus());
            list.add(PurchaseStatus.PART_BILL.getStatus());

            com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

            List<PurchaseInStorageShelf> purchaseInStorageShelves = purchaseInStorageShelfMapper.selectByStorageIdsAndStatusAndParam(list,param,supplierId);

            List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS = new ArrayList<>();

            if(!CollectionUtils.isEmpty(purchaseInStorageShelves)){

                List<Long> inStorageIds = PurchaseInStorageShelf.getInStorageIds(purchaseInStorageShelves);

                List<Long> inStorageDtlIds = PurchaseInStorageShelf.getInStorageDtlIds(purchaseInStorageShelves);

                List<PurchaseInStorageDetail> purchaseInStorageDetails = purchaseInStorageDetailMapper.selectByIds(userVO.getEnterpriseId(), inStorageDtlIds);

                List<PurchaseInStorage> purchaseInStorages = purchaseInStorageMapper.selectByIds(inStorageIds);

                List<Long> goodsIds = PurchaseInStorageShelf.getGoodsIds(purchaseInStorageShelves);

                List<GoodsBusiness> goodsBusinesses = goodsBusinessMapper.selectByGoodsIds(goodsIds,null);

                paymentInvoiceGoodsVOS = setPurchaseInStore(purchaseInStorages, purchaseInStorageShelves, purchaseInStorageDetails);

                commonComponent.setPaymentInvoiceInStoreMaxQuantity(paymentInvoiceGoodsVOS,goodsBusinesses);


            }

            page.setResult(paymentInvoiceGoodsVOS);
            page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
            page.setTotalPage(hPage.getPages());

            return paymentInvoiceGoodsVOS;

        }else if(ChainType.Division.getCode() == userVO.getChainType()){

            int distrType= 0;

            List<Integer> list = new ArrayList<>();
            list.add(DistrInStatus.WAIT_BILL);
            list.add(DistrInStatus.PART_BILL);

            List<DistrInShelf> distrInShelfList = new ArrayList<>();
            /**
             * 加盟店查询 并且供应商类型是总部 查询配进入库单 总部配送类型
             */
            com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
            if(1 == type){

                distrType = DistrType.DISTRIBUTION_HEAD.getCode();


                distrInShelfList = distrInShelfMapper.selectEnterpriseOrderByInIdsAndStatusAndParam(supplierId, list, param, distrType);


            }else if(2 == type){

                /**
                 * 加盟店查询 并且供应商类型是供货单位 查询配进入库单 总部直调类型
                 */
                distrType = DistrType.DIRECT_DISTRIBUTION.getCode();
                distrInShelfList = distrInShelfMapper.selectSupplierOrderByInIdsAndStatusAndParam(supplierId, list, param, distrType);

            }

            List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS = new ArrayList<>();
            if(!CollectionUtils.isEmpty(distrInShelfList)){
                List<Long> inStorageDtlIds = DistrInShelf.getInStorageDtlIds(distrInShelfList);

                List<Long> inStorageIds = DistrInShelf.getInStorageIds(distrInShelfList);

                List<DistrIn> distrIns = distrInMapper.selectByIds(inStorageIds);


                List<DistrInDetail> distrInDetails = distrInDetailMapper.selectByIds(inStorageDtlIds);

                List<Long> goodsIds = DistrInShelf.getGoodsIds(distrInShelfList);

                List<GoodsBusiness> goodsBusinesses = goodsBusinessMapper.selectByGoodsIds(goodsIds,null);

                paymentInvoiceGoodsVOS = setDistrIn(distrIns, distrInDetails, distrInShelfList);

                commonComponent.setPaymentInvoiceInStoreMaxQuantity(paymentInvoiceGoodsVOS,goodsBusinesses);
            }

            page.setResult(paymentInvoiceGoodsVOS);
            page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
            page.setTotalPage(hPage.getPages());
            return paymentInvoiceGoodsVOS;

        }


        return Collections.emptyList();
    }


    @Override
    public List<PaymentInvoiceModifyRecordResponseVO> queryModifyRecords(UserVO userVO, Long paymentInvoiceId, Page page){

        com.github.pagehelper.Page hPage = null;
        if(null != page) {
            hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        }
        List<PaymentInvoiceModifyRecord> paymentInvoiceModifyRecords = paymentInvoiceModifyRecordMapper.selectByEnterpriseId(userVO.getEnterpriseId(),paymentInvoiceId);

        List<PaymentInvoiceModifyRecordResponseVO> collect = paymentInvoiceModifyRecords.stream().map(paymentInvoiceModifyRecord -> {

            PaymentInvoiceModifyRecordResponseVO vo = new PaymentInvoiceModifyRecordResponseVO();

            return orikaMapperFactory.copyBean(vo, paymentInvoiceModifyRecord);
        }).collect(Collectors.toList());

        if(null != page) {
            page.setResult(collect);
            page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
            page.setTotalPage(hPage.getPages());
        }

        return collect;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void wariteOff(UserVO userVO, Long id) throws Exception {

        PaymentInvoice paymentInvoice = paymentInvoiceMapper.selectByPrimaryKey(id);

        if(null == paymentInvoice){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"找不到应付发票信息");
        }


        List<PaymentInvoiceDetail> paymentInvoiceDetails = paymentInvoiceDetailMapper.selectByInvoiceId(id);

        for(PaymentInvoiceDetail pia : paymentInvoiceDetails){

            if(ChainType.Division.getCode() == userVO.getChainType()){

                DistrInShelf distrInShelf = distrInShelfMapper.selectByPrimaryKey(pia.getBaseShelfDtlId());
                DistrIn distrIn = distrInMapper.selectByPrimaryKey(pia.getBaseOrderId());
                BigDecimal shelfClearQuantity = distrInShelf.getClearQuantity();
                BigDecimal subtract = shelfClearQuantity.subtract(pia.getQuantity());
                /**
                 * 当未清数量不够扣除对账数量时抛出异常,数据异常
                 */
                if(subtract.compareTo(BigDecimal.ZERO) < 0){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"扣除配进入库单:"+distrIn.getCode()+"已清数量后为负数");
                }
                distrInShelf.setClearQuantity(
                        subtract
                );

                distrInShelf.setUnclearQuantity(
                                distrInShelf.getUnclearQuantity().add(pia.getQuantity())
                );

                if(distrInShelf.getClearQuantity().compareTo(BigDecimal.ZERO) == 0){
                    distrInShelf.setStatus(DistrInStatus.WAIT_BILL);
                }else if(distrInShelf.getUnclearQuantity().compareTo(BigDecimal.ZERO) == 0){
                    distrInShelf.setStatus(DistrInStatus.FINISHED);
                }

                distrInShelfMapper.updateByPrimaryKeySelective(distrInShelf);

                DistrInDetail distrInDetail = commonComponent.clearDistrInDetailDetail(distrInShelf);

                commonComponent.clearDistrIn(distrInDetail);

            }else if(ChainType.Headquarters.getCode() == userVO.getChainType()){

                PurchaseInStorageShelf purchaseInStorageShelf = purchaseInStorageShelfMapper.selectByPrimaryKey(pia.getBaseShelfDtlId());

                PurchaseInStorage purchaseInStorage = purchaseInStorageMapper.selectByPrimaryKey(pia.getBaseOrderId());

                BigDecimal shelfClearQuantity = purchaseInStorageShelf.getClearQuantity();
                BigDecimal subtract = shelfClearQuantity.subtract(pia.getQuantity());
                /**
                 * 当未清数量不够扣除对账数量时抛出异常,数据异常
                 */
                if(subtract.compareTo(BigDecimal.ZERO) < 0){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"扣除配进入库单:"+purchaseInStorage.getCode()+"已清数量后为负数");
                }
                purchaseInStorageShelf.setClearQuantity(
                        subtract
                );

                purchaseInStorageShelf.setUnclearQuantity(
                                purchaseInStorageShelf.getUnclearQuantity().add(pia.getQuantity())
                );

                if(purchaseInStorageShelf.getClearQuantity().compareTo(BigDecimal.ZERO) == 0){
                    purchaseInStorageShelf.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
                }else if(purchaseInStorageShelf.getUnclearQuantity().compareTo(BigDecimal.ZERO) == 0){
                    purchaseInStorageShelf.setStatus(PurchaseStatus.FINISHED.getStatus());
                }

                purchaseInStorageShelfMapper.updateByPrimaryKeySelective(purchaseInStorageShelf);

                PurchaseInStorageDetail detail = commonComponent.clearPurchaseInStorageDetail(userVO, purchaseInStorageShelf);

                commonComponent.clearPurchaseInStorage(detail);

            }else {
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.NOT_DATA,"找不到当前登录用户");
            }

            PaymentInvoiceDetail newPaymentInvoiceDetail = new PaymentInvoiceDetail();
            newPaymentInvoiceDetail.setStatus(FinancePaymentStatus.WARITE_OF);
            newPaymentInvoiceDetail.setId(pia.getId());

            paymentInvoiceDetailMapper.updateByPrimaryKeySelective(newPaymentInvoiceDetail);

        }

        PaymentInvoice newPaymentInvoice = new PaymentInvoice();
        newPaymentInvoice.setId(paymentInvoice.getId());
        newPaymentInvoice.setStatus(FinancePaymentStatus.WARITE_OF);
        paymentInvoiceMapper.updateByPrimaryKeySelective(newPaymentInvoice);


        /**
         * 财务关键表数据
         */
        List<PaymentInvoiceDetail> pis = paymentInvoiceDetailMapper.selectByInvoiceId(id);
        financeComponent.paymentInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(userVO, paymentInvoice, pis, "writeOff");

    }

    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) throws BusinessException {

        draftCache.setOrderCode(OrderRule.PAYMENT_INVOICE.getCodePrefix());

        draftCache.setEnterpriseId(userVO.getEnterpriseId());
        draftCache = redisComponent.saveDraftCacheVO(draftCache);

        return draftCache;
    }

    @Override
    public void removeDraftCach(Long supplierId, Long enterpriseId, String type, String redisKeyValue) throws BusinessException{

        redisComponent.removeDraftCacheVO(supplierId,enterpriseId,type,redisKeyValue);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(Long supplierId, UserVO userVO){
        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.PAYMENT_INVOICE.getCodePrefix());
        draftCacheVO.setSupplierId(supplierId);
        return redisComponent.getDraftCacheVO(draftCacheVO);
    }

    @Override
    public void export(OutputStream output, PaymentInvoiceResponseVO paymentInvoiceResponseVO) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>(11);
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("approvalNumber","批准文号");
        map.put("lotNumber","批号");
        map.put("validDate","有效期");
        map.put("quantity","数量");
        map.put("unitPrice","单价");
        map.put("amount","金额");
        map.put("taxRate","税率");
        map.put("notaxAmount","不含税金额");
        map.put("taxAmount","税额");


        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("供货单位编码：");
        titleSecondRow.append(paymentInvoiceResponseVO.getSupplierCode() == null ? "" : paymentInvoiceResponseVO.getSupplierCode());
        titleSecondRow.append("  供货单位名称：");
        titleSecondRow.append(paymentInvoiceResponseVO.getSupplierName() == null ? "" : paymentInvoiceResponseVO.getSupplierName());
        titleSecondRow.append("  开票日期：");
        titleSecondRow.append(paymentInvoiceResponseVO.getBillDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paymentInvoiceResponseVO.getBillDate()));
        titleSecondRow.append("  开票人员：");
        titleSecondRow.append(paymentInvoiceResponseVO.getBillManName() == null ? "" : paymentInvoiceResponseVO.getBillManName());
        titleSecondRow.append("  单号:");
        titleSecondRow.append(paymentInvoiceResponseVO.getCode() == null ? "" : paymentInvoiceResponseVO.getCode());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(paymentInvoiceResponseVO.getRemark() == null ? "" : paymentInvoiceResponseVO.getRemark());
        secondTitle.add(titleSecondRow.toString());


        List<String> name = new ArrayList<>();
        //第一行的企业名
        name.add(enterpriseMapper.selectByPrimaryKey(paymentInvoiceResponseVO.getEnterpriseId()).getName());
        //第二行的
        name.add("应付发票");

        /**
         * 记录导出尾行的合计中数值数据
         */
        List<String> needTotalName = new ArrayList<>(7);
        needTotalName.add("quantityTotal");
        needTotalName.add("amountTotal");
        needTotalName.add("notaxAmountTotal");
        needTotalName.add("taxAmountTotal");
        purchaseGeneralComponent.commExcelDistrExport(output,map,paymentInvoiceResponseVO.getPaymentInvoiceDetails(),name,secondTitle,null,null,needTotalName);
    }

    @Override
    public void exportUpdateRecord(OutputStream output, List<PaymentInvoiceModifyRecordResponseVO> paymentInvoiceModifyRecordResponseVOS, UserVO loginUser) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("updateTime","修改时间");
        map.put("modifierName","修改人员");
        map.put("reason","修改原因");
        map.put("columnChName","修改项目");
        map.put("oldContent","原内容");
        map.put("newContent","新内容");
        List<String> secondTitle = new ArrayList<String>();
        String end = "";
        List<String> name = new ArrayList<String>();
        //第一行的企业名
        name.add(loginUser.getEnterpriseName());
        //第二行的
        name.add("应付发票修改记录");
        purchaseGeneralComponent.commExcelExport(output,map,paymentInvoiceModifyRecordResponseVOS,name,secondTitle,end,true,new ArrayList<>());
    }

    /**
     * 调用采购入库/配进入库
     * @param userVO
     * @param suppilerId
     * @param startDate
     * @param endDate
     * @param type
     * @return
     */
    @Override
    public List<PaymentInvoiceInStorageVO> queryInStores(UserVO userVO, Long suppilerId, String startDate, String endDate, Integer type, Page page){


        Date startDateTime = null;
        Date endDateTime = null;
        if(!StringUtils.isEmpty(startDate))
            startDateTime = DateUtils.StringToDate(startDate);
        if(!StringUtils.isEmpty(endDate))
            endDateTime = DateUtils.StringToDate(endDate);

        if(ChainType.Headquarters.getCode() == userVO.getChainType()){

            /**
             * 总部查询 查询采购入库单
             */

            List<Integer> list = new ArrayList<>();
            list.add(PurchaseStatus.WAIT_BILL.getStatus());
            list.add(PurchaseStatus.PART_BILL.getStatus());

            com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
            List<PurchaseInStorage> purchaseInStorages = purchaseInStorageMapper.select2PaymentInvoice(userVO.getEnterpriseId(), startDateTime, endDateTime, list, suppilerId);

            List<PaymentInvoiceInStorageVO> collect = purchaseInStorages.stream().map(purchaseInStorage -> {

                PaymentInvoiceInStorageVO paymentInvoiceInStorageVO = new PaymentInvoiceInStorageVO();

                paymentInvoiceInStorageVO = orikaMapperFactory.copyBean(paymentInvoiceInStorageVO, purchaseInStorage);
                paymentInvoiceInStorageVO.setInDate(purchaseInStorage.getInStorageDate());

                return paymentInvoiceInStorageVO;

            }).collect(Collectors.toList());

            page.setResult(collect);
            page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
            page.setTotalPage(hPage.getPages());
            return collect;

        }else if(ChainType.Division.getCode() == userVO.getChainType()){

            int distrType= 0;


            List<Integer> list = new ArrayList<>();
            list.add(DistrInStatus.WAIT_BILL);
            list.add(DistrInStatus.PART_BILL);

            List<DistrIn> distrIns = new ArrayList<>();
            /**
             * 加盟店查询 并且供应商类型是总部 查询配进入库单 总部配送类型
             */

            com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
            if(1 == type){

                distrType = DistrType.DISTRIBUTION_HEAD.getCode();

                distrIns = distrInMapper.selectEnterprisePaymentInvoice(
                        userVO.getEnterpriseId(),
                        startDateTime, endDateTime,
                        distrType,
                        list,
                        suppilerId);


            }else if(2 == type){

                /**
                 * 加盟店查询 并且供应商类型是供货单位 查询配进入库单 总部直调类型
                 */
                distrType = DistrType.DIRECT_DISTRIBUTION.getCode();

                distrIns = distrInMapper.selectSupplier2PaymentInvoice(
                        userVO.getEnterpriseId(),
                        startDateTime, endDateTime,
                        distrType,
                        list,
                        suppilerId);
            }


            List<PaymentInvoiceInStorageVO> collect = distrIns.stream().map(distrIn -> {

                PaymentInvoiceInStorageVO paymentInvoiceInStorageVO = new PaymentInvoiceInStorageVO();

                paymentInvoiceInStorageVO = orikaMapperFactory.copyBean(paymentInvoiceInStorageVO, distrIn);

                paymentInvoiceInStorageVO.setSupplierId(distrIn.getOutboundUnitId());
                paymentInvoiceInStorageVO.setSupplierCode(distrIn.getOutboundUnitCode());
                paymentInvoiceInStorageVO.setSupplierName(distrIn.getOutboundUnitName());

                return paymentInvoiceInStorageVO;

            }).collect(Collectors.toList());

            page.setResult(collect);
            page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
            page.setTotalPage(hPage.getPages());
            return collect;
        }

        return Collections.emptyList();

    }


    @Override
    public List<PaymentInvoiceGoodsVO> queryPaymentInvoiceGoods(UserVO userVO, List<Long> inStorageIds){


        if(ChainType.Headquarters.getCode() == userVO.getChainType()){

            /**
             * 总部查询 查询采购入库单
             */

            List<Integer> list = new ArrayList<>();
            list.add(PurchaseStatus.WAIT_BILL.getStatus());
            list.add(PurchaseStatus.PART_BILL.getStatus());


            if(!CollectionUtils.isEmpty(inStorageIds)) {


                List<PurchaseInStorageDetail> purchaseInStorageDetails = purchaseInStorageDetailMapper.selectByInStorageIds(inStorageIds);


                List<PurchaseInStorageShelf> purchaseInStorageShelves = purchaseInStorageShelfMapper.selectByStorageIdsAndStatus(inStorageIds,list);

                List<PurchaseInStorage> purchaseInStorages = purchaseInStorageMapper.selectByIds(inStorageIds);

                List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS = setPurchaseInStore(purchaseInStorages, purchaseInStorageShelves, purchaseInStorageDetails);

                List<Long> goodsIds = PurchaseInStorageShelf.getGoodsIds(purchaseInStorageShelves);

                List<GoodsBusiness> goodsBusinesses = goodsBusinessMapper.selectByGoodsIds(goodsIds,null);

                commonComponent.setPaymentInvoiceInStoreMaxQuantity4Transfer(paymentInvoiceGoodsVOS,goodsBusinesses);

                return paymentInvoiceGoodsVOS;

            }


        }else if(ChainType.Division.getCode() == userVO.getChainType()){

            List<Integer> list = new ArrayList<>();
            list.add(DistrInStatus.WAIT_BILL);
            list.add(DistrInStatus.PART_BILL);

            if(!CollectionUtils.isEmpty(inStorageIds)){

                List<DistrInDetail> distrInDetails = distrInDetailMapper.selectByInIds(inStorageIds);

                List<DistrInShelf> distrInShelfList = distrInShelfMapper.selectByInIdsAndStatus(inStorageIds,list);

                List<DistrIn> distrIns = distrInMapper.selectByIds(inStorageIds);

                List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS = setDistrIn(distrIns, distrInDetails, distrInShelfList);

                List<Long> goodsIds = DistrInShelf.getGoodsIds(distrInShelfList);

                List<GoodsBusiness> goodsBusinesses = goodsBusinessMapper.selectByGoodsIds(goodsIds,null);

                commonComponent.setPaymentInvoiceInStoreMaxQuantity4Transfer(paymentInvoiceGoodsVOS,goodsBusinesses);

                return paymentInvoiceGoodsVOS;
            }


        }

        return Collections.emptyList();
    }

    private List<PaymentInvoiceGoodsVO> setPurchaseInStore(List<PurchaseInStorage> purchaseInStorages,List<PurchaseInStorageShelf> purchaseInStorageShelves,List<PurchaseInStorageDetail> purchaseInStorageDetails){
        List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS = purchaseInStorageShelves.stream().map(purchaseInStorageShelf -> {

            PaymentInvoiceGoodsVO paymentInvoiceGoodsVO = new PaymentInvoiceGoodsVO();

            paymentInvoiceGoodsVO = orikaMapperFactory.copyBean(paymentInvoiceGoodsVO, purchaseInStorageShelf);

            PaymentInvoiceGoodsVO.afterCopySetBean4PurchaseInStorage(purchaseInStorageShelf, purchaseInStorages, paymentInvoiceGoodsVO, purchaseInStorageDetails);

            return paymentInvoiceGoodsVO;

        }).collect(Collectors.toList());

        return paymentInvoiceGoodsVOS;
    }

    private List<PaymentInvoiceGoodsVO>  setDistrIn(List<DistrIn> distrIns,List<DistrInDetail> distrInDetails,List<DistrInShelf> distrInShelfList){

        List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS = distrInShelfList.stream().map(distrInShelf -> {
            PaymentInvoiceGoodsVO paymentInvoiceGoodsVO = new PaymentInvoiceGoodsVO();
            paymentInvoiceGoodsVO = orikaMapperFactory.copyBean(paymentInvoiceGoodsVO, distrInShelf);
            PaymentInvoiceGoodsVO.afterCopySetBean4DistrIn(distrInShelf, distrIns, paymentInvoiceGoodsVO, distrInDetails);

            return paymentInvoiceGoodsVO;

        }).collect(Collectors.toList());

        return paymentInvoiceGoodsVOS;
    }

    private List<PaymentInvoiceModifyRecord> getModifyRecordList(UserVO userVO ,String tableName,Long keyId,Date updateTime
            , Map<String,Object> oldMap
            ,Map<String,Object> newMap ,Map<String,String> fieldMustMap){

        List<PaymentInvoiceModifyRecord> modifyRecordWithBLOBs = new ArrayList<>();

        for(Map.Entry<String,String> entry : fieldMustMap.entrySet()){
            Object obj = oldMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if(null != obj && null != newObj && !obj.equals(newObj)){
                PaymentInvoiceModifyRecord userModify = new PaymentInvoiceModifyRecord();
                userModify.setEnterpriseId(userVO.getEnterpriseId());
                userModify.setParentId(userVO.getParentId());
                userModify.setTableName(tableName);
                userModify.setKeyId(keyId);
                userModify.setColumnEnName(entry.getKey());
                userModify.setColumnChName(entry.getValue());
                userModify.setUpdateTime(updateTime);
                userModify.setCreaterId(userVO.getUserId());
                userModify.setCreaterCode(userVO.getUserCode());
                userModify.setCreaterName(userVO.getUserName());
                userModify.setCreateTime(new Date());
                userModify.setModifierId(userVO.getUserId());
                userModify.setModifierCode(userVO.getUserCode());
                userModify.setModifierName(userVO.getUserName());
                userModify.setUpdateTime(new Date());
                userModify.setOldContent(obj.toString());
                userModify.setNewContent(newObj.toString());
                modifyRecordWithBLOBs.add(userModify);
            }
        }

        return modifyRecordWithBLOBs;
    }

    private Map<String,String> fieldMustMap(){
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("enterpriseId","企业ID");
        fieldNames.put("parentId","上级企业ID");
        fieldNames.put("invoiceId","应付发票总单ID");
        fieldNames.put("invoiceType","发票类型");
        fieldNames.put("invoiceCode","发票代码");
        fieldNames.put("invoiceNumber","发票号码");
        fieldNames.put("checkCode","检验码");
        fieldNames.put("taxpayerIdCode","供货单位纳税人识别号");
        fieldNames.put("accountName","供货单位开户户名");
        fieldNames.put("accountBank","供货单位开户银行");
        fieldNames.put("account","供货单位开户账号");
        fieldNames.put("address","供货单位地址");
        fieldNames.put("telephone","供货单位电话");
        fieldNames.put("companyName","企业名称");
        fieldNames.put("companyAddress","企业地址");
        fieldNames.put("companyTelephone","企业电话");
        fieldNames.put("companyTaxpayerIdCode","企业纳税人识别号");
        fieldNames.put("companyAccountName","企业开户户名");
        fieldNames.put("companyAccountBank","企业开户银行");
        fieldNames.put("companyAccount","企业开户账号");
        fieldNames.put("fileId","附件ID");
        fieldNames.put("remark","备注");

        return fieldNames;
    }

}
