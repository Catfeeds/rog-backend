package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.service.impl;

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
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao.*;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.*;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.service.PrepayInvoiceService;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.*;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
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
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.constant.status.FinanceReconciliationStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
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
public class PrepayInvoiceServiceImpl implements PrepayInvoiceService {

    @Autowired
    private PrepayInvoiceMapper prepayInvoiceMapper;

    @Autowired
    private PrepayInvoiceDetailMapper prepayInvoiceDetailMapper;

    @Autowired
    private PrepayInvoiceInfoMapper prepayInvoiceInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private PrepayInvoiceModifyRecordMapper prepayInvoiceModifyRecordMapper;

    @Autowired
    private OrikaMapperFactory orikaMapperFactory;
    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;
    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;
    @Autowired
    private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;

    @Autowired
    private DistrInDetailMapper distrInDetailMapper;

    @Autowired
    private DistrInShelfMapper distrInShelfMapper;
    @Autowired
    private DistrInMapper distrInMapper;
    @Autowired
    private PrepayInvoiceAccountMapper prepayInvoiceAccountMapper;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private FinanceComponent financeComponent;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PrepayInvoice save(UserVO userVO, PrepayInvoiceSaveVO prepayInvoiceSaveVO) throws Exception {


        Long billManId = prepayInvoiceSaveVO.getBillManId();

        User billMan = userMapper.selectByPrimaryKey(billManId);

        /**
         * 数据类型,默认为价格清单:0,1:企业,2:供应商"
         */
        Integer type = prepayInvoiceSaveVO.getType();
        Long supplierId = prepayInvoiceSaveVO.getSupplierId();
        Supplier supplier = null;
        Enterprise enterprise = null;

        Long goodsEnterpriseId = UserEnterpriseUtils.getHeadquartersEnterpriseId(userVO);

        if(2 == type){

            /**
             * 代表是加盟店的配进入库的商品,配送类型为直调类型,或者是总部的采购入库
             * 所以当前企业id为商品的企业id
             */
            supplier = supplierMapper.selectByPrimaryKey(supplierId);
/*
            goodsEnterpriseId = userVO.getEnterpriseId();
*/
        }else if(1 == type){

            /**
             * 代表是加盟店的是配进入库的商品,配送类型为总部配送, 商品的企业id肯定为总部id
             */
            enterprise = enterpriseMapper.selectByPrimaryKey(supplierId);
/*
            goodsEnterpriseId = userVO.getParentId();
*/
        }

        String code = orderCodeComponent.generate(
                OrderRule.PREPAY_INVOICE.getCodePrefix()
                , userVO.getEnterpriseId()
                , userVO.getEnterpriseCode()
        );

        boolean isAdd = null == prepayInvoiceSaveVO.getId() ? true : false;
        PrepayInvoice prepayInvoice = PrepayInvoice.generatePrepayInvoice(userVO, prepayInvoiceSaveVO, billMan, supplier, enterprise, code, isAdd);


        List<PrepayInvoiceDetailSaveVO> prepayInvoiceDetailSaveVOS = prepayInvoiceSaveVO.getPrepayInvoiceDetailSaveVOS();

        List<Long> goodsIds = PrepayInvoiceDetailSaveVO.getGoodsIds(prepayInvoiceDetailSaveVOS);

        if(CollectionUtils.isEmpty(goodsIds)){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"找不到对应的商品");
        }

        List<Goods> goods = goodsMapper.selectByIdsNotStatus(goodsIds);

        List<Long> goodsTaxRateIds = PrepayInvoiceDetailSaveVO.getGoodsTaxRateIds(prepayInvoiceDetailSaveVOS);

        List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectByIds(goodsTaxRateIds);


        List<PrepayInvoiceDetail> prepayInvoiceDetails = PrepayInvoiceDetail.generatePrepayInvoiceDetails(userVO, goods, goodsTaxRates, prepayInvoiceDetailSaveVOS, prepayInvoice, isAdd);

        PrepayInvoice.afterSetPrepayInvoice(prepayInvoice,prepayInvoiceDetails);

        prepayInvoiceMapper.insertSelective(prepayInvoice);

        prepayInvoiceDetails.forEach(prepayInvoiceDetail -> {

            PrepayInvoiceDetail.setAfterPrepayInvoiceDetail(prepayInvoiceDetail,prepayInvoice);

            prepayInvoiceDetailMapper.insertSelective(prepayInvoiceDetail);
        }
        );

        PrepayInvoiceInfoSaveVO prepayInvoiceInfoSaveVO = prepayInvoiceSaveVO.getPrepayInvoiceInfoSaveVO();
        if(null != prepayInvoiceInfoSaveVO && null !=prepayInvoiceInfoSaveVO.getInvoiceType()){
            PrepayInvoiceInfo prepayInvoiceInfo = new PrepayInvoiceInfo();
            prepayInvoiceInfo = orikaMapperFactory.copyBean(prepayInvoiceInfo, prepayInvoiceInfoSaveVO);
            prepayInvoiceInfo.setEnterpriseId(prepayInvoice.getEnterpriseId());
            prepayInvoiceInfo.setParentId(prepayInvoice.getParentId());
            prepayInvoiceInfo.setInvoiceId(prepayInvoice.getId());
            prepayInvoiceInfo.setId(null);
            prepayInvoiceInfoMapper.insertSelective(prepayInvoiceInfo);
        }

        //删除草稿
        removeDraftCach(prepayInvoice.getSupplierId(),userVO.getEnterpriseId(), OrderRule.PREPAY_INVOICE.getCodePrefix(), prepayInvoiceSaveVO.getRedisKeyValue());


        /**
         * 预付发票保存 更新财务业务表数据
         */
        financeComponent.prepayInvoiceToBalanceAndVoucherWhenSaveOrWriteOff(userVO, prepayInvoice, prepayInvoiceDetails, "save");

        return prepayInvoice;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PrepayInvoice update(UserVO userVO, PrepayInvoiceSaveVO prepayInvoiceSaveVO) throws Exception {

        /**
         * 只允许编辑：开票日期、开票人员、备注字段
            已冲销单据不允许修改
         */

        Long id = prepayInvoiceSaveVO.getId();

        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(id);

        User billMan = userMapper.selectByPrimaryKey(prepayInvoiceSaveVO.getBillManId());

        PrepayInvoice newPrepayInvoice = PrepayInvoice.generateUpdatePrepayInvoice(userVO,prepayInvoice, billMan, prepayInvoiceSaveVO);

        prepayInvoiceMapper.updateByPrimaryKeySelective(newPrepayInvoice);

        newPrepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(prepayInvoice.getId());

        List<PrepayInvoiceModifyRecord> prepayInvoiceModifyRecords = PrepayInvoiceModifyRecord.generatePrepayInvoiceModifyRecords(userVO, prepayInvoice, newPrepayInvoice, prepayInvoiceSaveVO);


        prepayInvoiceModifyRecords.forEach(prepayInvoiceModifyRecord -> {

            prepayInvoiceModifyRecord.setReason(prepayInvoiceSaveVO.getReason());
            prepayInvoiceModifyRecordMapper.insertSelective(prepayInvoiceModifyRecord);
        });

        PrepayInvoiceInfoSaveVO prepayInvoiceInfoSaveVO = prepayInvoiceSaveVO.getPrepayInvoiceInfoSaveVO();
        if(null != prepayInvoiceInfoSaveVO && null != prepayInvoiceInfoSaveVO.getInvoiceType()){

            PrepayInvoiceInfo oldPrepayInvoiceInfo = prepayInvoiceInfoMapper.selectByPrimaryKey(prepayInvoiceInfoSaveVO.getId());

            PrepayInvoiceInfo prepayInvoiceInfo = new PrepayInvoiceInfo();
            prepayInvoiceInfo = orikaMapperFactory.copyBean(prepayInvoiceInfo, prepayInvoiceInfoSaveVO);
            prepayInvoiceInfo.setEnterpriseId(prepayInvoice.getEnterpriseId());
            prepayInvoiceInfo.setParentId(prepayInvoice.getParentId());
            prepayInvoiceInfo.setInvoiceId(prepayInvoice.getId());
            if(null != prepayInvoiceInfoSaveVO.getId())
                prepayInvoiceInfoMapper.updateByPrimaryKeySelective(prepayInvoiceInfo);
            else
                prepayInvoiceInfoMapper.insertSelective(prepayInvoiceInfo);

            PrepayInvoiceInfo newPrepayInvoiceInfo = prepayInvoiceInfoMapper.selectByPrimaryKey(prepayInvoiceInfo.getId());

            if(null != oldPrepayInvoiceInfo){
                Map<String,Object> newPrepayInvoiceInfoMap = modifyRecordCompoent.getFieldsMap(newPrepayInvoiceInfo);

                Map<String,Object> oldPrepayInvoiceInfoMap = modifyRecordCompoent.getFieldsMap(oldPrepayInvoiceInfo);


                List<PrepayInvoiceModifyRecord> prepayInvoiceInfoModifyRecords = getModifyRecordList(userVO, "saas_prepay_invoice_info", prepayInvoice.getId(), new Date(), oldPrepayInvoiceInfoMap, newPrepayInvoiceInfoMap, fieldMustMap());

                prepayInvoiceInfoModifyRecords.forEach(prepayInvoiceInfoModifyRecord -> {

                    prepayInvoiceInfoModifyRecord.setReason(prepayInvoiceSaveVO.getReason());
                    prepayInvoiceModifyRecordMapper.insertSelective(prepayInvoiceInfoModifyRecord);
                });
            }


        }


        return prepayInvoice;

    }

    /**
     * 查询预开发票列表
     * @param userVO
     * @param prepayInvoiceQueryParamVO
     * @return
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Override
    public List<PrepayInvoiceResponseVO> queryPrepayInvoiceResponseVOs(UserVO userVO, PrepayInvoiceQueryParamVO prepayInvoiceQueryParamVO, Page page) throws InvocationTargetException, IntrospectionException, IllegalAccessException, NoSuchFieldException {

        Map<String, Object> notNullBeanFeild2Map = ReflectUtils.getNotNullBeanFeild2Map(prepayInvoiceQueryParamVO);
        notNullBeanFeild2Map.put("enterpriseId",userVO.getEnterpriseId());
        String startDate = prepayInvoiceQueryParamVO.getStartDate();
        String endDate = prepayInvoiceQueryParamVO.getEndDate();
        if(!StringUtils.isEmpty(startDate)) {
            notNullBeanFeild2Map.put("startDate",DateUtils.StringToDate(startDate));
        }
        if(!StringUtils.isEmpty(endDate)) {
            notNullBeanFeild2Map.put("endDate",DateUtils.StringToDate(endDate));
        }

        if(null != notNullBeanFeild2Map.get("orderName")){
            String orderName = notNullBeanFeild2Map.get("orderName").toString();
            if(orderName.equals("billDate")){
                notNullBeanFeild2Map.put("billDate","bill_date");
            }

        }

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
        List<PrepayInvoice> prepayInvoices = prepayInvoiceMapper.selectByParam(notNullBeanFeild2Map);

        List<PrepayInvoiceResponseVO> prepayInvoiceResponseVOS = prepayInvoices.stream().map(prepayInvoice -> {

            PrepayInvoiceResponseVO prepayInvoiceResponseVO = new PrepayInvoiceResponseVO();
            prepayInvoiceResponseVO = orikaMapperFactory.copyBean(prepayInvoiceResponseVO, prepayInvoice);
            return prepayInvoiceResponseVO;

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
            notNullBeanFeild2Map.put("statusList",list);
        }else {
            list.add(Integer.parseInt(status.toString()));
            notNullBeanFeild2Map.put("statusList",list);
        }

        Object accountStatus = notNullBeanFeild2Map.get("accountStatus");
        List<Integer> accountStatusList = new ArrayList<>();
        if(null == accountStatus){

            accountStatusList.add(FinanceReconciliationStatus.PRE_RECONCILIATION);
            accountStatusList.add(FinanceReconciliationStatus.PARTIAL_RECONCILIATION);
            accountStatusList.add(FinanceReconciliationStatus.RECONCILED);
            notNullBeanFeild2Map.put("accountStatusList",accountStatusList);
        }else {
            accountStatusList.add(Integer.parseInt(accountStatus.toString()));
            notNullBeanFeild2Map.put("accountStatusList",accountStatusList);
        }

        PrepayInvoiceResponseTotalVO prepayInvoiceResponseTotalVO = prepayInvoiceMapper.selectSumByParam(notNullBeanFeild2Map);
        if(null != prepayInvoiceResponseTotalVO){
            prepayInvoiceResponseTotalVO.setPrepayInvoiceResponseVOS(prepayInvoiceResponseVOS);
            page.setResult(prepayInvoiceResponseTotalVO);
            page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
            page.setTotalPage(hPage.getPages());
        }else {
            page.setResult(new PrepayInvoiceResponseTotalVO());
            page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
            page.setTotalPage(hPage.getPages());
        }

        return prepayInvoiceResponseVOS;
    }

    @Override
    public PrepayInvoiceResponseVO queryPrepayInvoiceResponseVO(UserVO userVO, Long prepayInvoiceId){

        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(prepayInvoiceId);

        List<PrepayInvoiceDetail> prepayInvoiceDetails = prepayInvoiceDetailMapper.selectByInvoiceId(prepayInvoice.getId());

        PrepayInvoiceInfo prepayInvoiceInfo = prepayInvoiceInfoMapper.selectByInvoiceId(prepayInvoice.getId());

        PrepayInvoiceResponseVO prepayInvoiceResponseVO = new PrepayInvoiceResponseVO();
        prepayInvoiceResponseVO = orikaMapperFactory.copyBean(prepayInvoiceResponseVO,prepayInvoice);

        List<PrepayInvoiceDetailResponseVO> prepayInvoiceDetailResponseVOS = new ArrayList<>();

        for(PrepayInvoiceDetail prepayInvoiceDetail : prepayInvoiceDetails){

            PrepayInvoiceDetailResponseVO prepayInvoiceDetailResponseVO = new PrepayInvoiceDetailResponseVO();

            prepayInvoiceDetailResponseVO = orikaMapperFactory.copyBean(prepayInvoiceDetailResponseVO,prepayInvoiceDetail);

            prepayInvoiceDetailResponseVOS.add(prepayInvoiceDetailResponseVO);
        }

        prepayInvoiceResponseVO.setPrepayInvoiceDetailSaveVOS(prepayInvoiceDetailResponseVOS);

        if(null != prepayInvoiceInfo){
            PrepayInvoiceInfoResponseVO prepayInvoiceInfoResponseVO = new PrepayInvoiceInfoResponseVO();
            prepayInvoiceInfoResponseVO =  orikaMapperFactory.copyBean(prepayInvoiceInfoResponseVO,prepayInvoiceInfo);
            prepayInvoiceResponseVO.setPrepayInvoiceInfoSaveVO(prepayInvoiceInfoResponseVO);
        }

        PrepayInvoiceResponseVO.setTotal(prepayInvoiceResponseVO);

        return prepayInvoiceResponseVO;

    }

    /**
     * 对账发票清单查询
     * @param userVO
     * @param prepayInvoiceId
     * @return
     */
    @Override
    public PrepayInvoiceResponseVO queryPrepayInvoiceList(UserVO userVO, Long prepayInvoiceId){

        List<Integer> list = new ArrayList<>();
        list.add(FinanceReconciliationStatus.PRE_RECONCILIATION);
        list.add(FinanceReconciliationStatus.PARTIAL_RECONCILIATION);

        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByIdAndAccountStatus(prepayInvoiceId,list);

        List<PrepayInvoiceDetail> prepayInvoiceDetails = prepayInvoiceDetailMapper.selectByInvoiceIdAndAccountStatus(prepayInvoice.getId(),list);

        PrepayInvoiceResponseVO prepayInvoiceResponseVO = new PrepayInvoiceResponseVO();
        prepayInvoiceResponseVO = orikaMapperFactory.copyBean(prepayInvoiceResponseVO,prepayInvoice);

        List<PrepayInvoiceDetailResponseVO> prepayInvoiceDetailResponseVOS = new ArrayList<>();

        for(PrepayInvoiceDetail prepayInvoiceDetail : prepayInvoiceDetails){

            PrepayInvoiceDetailResponseVO prepayInvoiceDetailResponseVO = new PrepayInvoiceDetailResponseVO();

            prepayInvoiceDetailResponseVO = orikaMapperFactory.copyBean(prepayInvoiceDetailResponseVO,prepayInvoiceDetail);
            prepayInvoiceDetailResponseVO.setClearQuantity(prepayInvoiceDetail.getAccountQuantity());
            prepayInvoiceDetailResponseVO.setUnclearQuantity(prepayInvoiceDetail.getUnAccountQuantity());

            prepayInvoiceDetailResponseVOS.add(prepayInvoiceDetailResponseVO);
        }

        prepayInvoiceResponseVO.setPrepayInvoiceDetailSaveVOS(prepayInvoiceDetailResponseVOS);

        for(PrepayInvoiceDetailResponseVO prepayInvoiceDetailResponseVO : prepayInvoiceDetailResponseVOS){
            PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO = queryInStoreList(userVO, prepayInvoiceDetailResponseVO.getId(),null, null, prepayInvoiceResponseVO.getFinanceAccountType());
            prepayInvoiceDetailResponseVO.setPrepayInvoiceInStoreResponseTotalVO(prepayInvoiceInStoreResponseTotalVO);
        }



        PrepayInvoiceResponseVO.setTotal(prepayInvoiceResponseVO);

        return prepayInvoiceResponseVO;

    }


    /**
     *  对账入库清单查询
     * @param userVO
     * @param startDate
     * @param endDate
     * @param financeAccountType 0-总部；1-自营店；2-加盟店；3-供货单位；4-购货单位；5-税率
     * @return
     */
    @Override
    public PrepayInvoiceInStoreResponseTotalVO queryInStoreList(UserVO userVO,Long prepayDetailId, String startDate, String endDate, Integer financeAccountType){

        PrepayInvoiceDetail prepayInvoiceDetail = prepayInvoiceDetailMapper.selectByPrimaryKey(prepayDetailId);

        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(prepayInvoiceDetail.getInvoiceId());

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
            List<PrepayInvoiceInStoreResponseVO> purchaseInStorageDetails = purchaseInStorageDetailMapper.select2PrepayInvoice(userVO.getEnterpriseId(), prepayInvoiceDetail.getGoodsId(), startDateTime, endDateTime,list,prepayInvoice.getSupplierId());

            commonComponent.setPrepayInvoiceInStoreMaxQuantity(purchaseInStorageDetails);

            PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO = PrepayInvoiceInStoreResponseTotalVO.getPrepayInvoiceInStoreResponseTotalVO(purchaseInStorageDetails);
            return prepayInvoiceInStoreResponseTotalVO;

        }else if(ChainType.Division.getCode() == userVO.getChainType()){
            /**
             * 加盟店查询 并且供应商类型是总部 查询配进入库单 总部配送类型
             */

            List<Integer> list = new ArrayList<>();
            list.add(DistrInStatus.WAIT_BILL);
            list.add(DistrInStatus.PART_BILL);

            if(FinanceAccountType.PARENT.getType() == financeAccountType){
                List<PrepayInvoiceInStoreResponseVO> prepayInvoiceInStoreResponseVOS = distrInDetailMapper.select2PrepayInvoice(userVO.getEnterpriseId(), prepayInvoiceDetail.getGoodsId(), startDateTime, endDateTime, DistrType.DISTRIBUTION_HEAD.getCode(),list,prepayInvoice.getSupplierId());
                commonComponent.setPrepayInvoiceInStoreMaxQuantity(prepayInvoiceInStoreResponseVOS);

                PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO = PrepayInvoiceInStoreResponseTotalVO.getPrepayInvoiceInStoreResponseTotalVO(prepayInvoiceInStoreResponseVOS);

                return prepayInvoiceInStoreResponseTotalVO;
            }

            /**
             * 加盟店查询 并且供应商类型是供货单位 查询配进入库单 总部直调类型
             */
            if(FinanceAccountType.SUPPLIER.getType() == financeAccountType){
                List<PrepayInvoiceInStoreResponseVO> prepayInvoiceInStoreResponseVOS = distrInDetailMapper.select2PrepayInvoice(userVO.getEnterpriseId(), prepayInvoiceDetail.getGoodsId(), startDateTime, endDateTime, DistrType.DIRECT_DISTRIBUTION.getCode(),list,prepayInvoice.getSupplierId());
                commonComponent.setPrepayInvoiceInStoreMaxQuantity(prepayInvoiceInStoreResponseVOS);

                PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO = PrepayInvoiceInStoreResponseTotalVO.getPrepayInvoiceInStoreResponseTotalVO(prepayInvoiceInStoreResponseVOS);
                return prepayInvoiceInStoreResponseTotalVO;
            }

        }

        return new PrepayInvoiceInStoreResponseTotalVO();
    }

    /**
     * 批量对账
     * @param userVO
     * @param prepayinvoiceId
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PrepayInvoiceResponseVO autoReconciliation(UserVO userVO,Long prepayinvoiceId) throws Exception {

        PrepayInvoiceResponseVO prepayInvoiceResponseVO = queryPrepayInvoiceList(userVO, prepayinvoiceId);

        List<PrepayInvoiceDetailResponseVO> prepayInvoiceDetailResponseVOS = prepayInvoiceResponseVO.getPrepayInvoiceDetailSaveVOS();

        for(PrepayInvoiceDetailResponseVO prepayInvoiceDetailResponseVO : prepayInvoiceDetailResponseVOS){
            // 已对账数量
            BigDecimal clearQuantityP =  BigDecimal.ZERO;

            PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO = queryInStoreList(userVO, prepayInvoiceDetailResponseVO.getId(),null, null, prepayInvoiceResponseVO.getFinanceAccountType());
            List<PrepayInvoiceInStoreResponseVO> prepayInvoiceInStoreResponseVOS = prepayInvoiceInStoreResponseTotalVO.getPrepayInvoiceInStoreResponseVOS();

            /**
             * 对账单未清数量(需要对账的数量)
             */
            BigDecimal accountQuantity = prepayInvoiceDetailResponseVO.getUnclearQuantity();

            for(PrepayInvoiceInStoreResponseVO prepayInvoiceInStoreResponseVO : prepayInvoiceInStoreResponseVOS){



                if(accountQuantity.compareTo(BigDecimal.ZERO) > 0){



                    /**
                     * 业务单据未清数量
                     */
                    BigDecimal unclearQuantity = prepayInvoiceInStoreResponseVO.getUnclearQuantity();

                    Integer managementMode = prepayInvoiceInStoreResponseVO.getManagementMode();

                    if(1 == managementMode){
                        unclearQuantity = prepayInvoiceInStoreResponseVO.getVerificationQuantity().subtract(prepayInvoiceInStoreResponseVO.getClearQuantity());
                        if(unclearQuantity.compareTo(BigDecimal.ZERO) < 0){
                            /**
                             * 已核销数量-已清数量为实销实结的业务单据最大开票数量 如果为负数,则业务单据数据有问题
                             */

                            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"实销实结 已核销数量小于已清数量,不能开票");

                        }
                    }

                    BigDecimal subtract = accountQuantity.subtract(unclearQuantity);
                    /**
                     * 对账数量减去 业务单据未清数量 余数大于等于0,表示 对账当并未完全对账完,剩下的数量需要继续对账
                     */
                    PerpayInvoiceAccountParamVO perpayInvoiceAccountParamVO = new PerpayInvoiceAccountParamVO();

                    perpayInvoiceAccountParamVO.setId(prepayInvoiceDetailResponseVO.getId());


                    PerpayInvoiceAccountDetailParamVO perpayInvoiceAccountDetailParamVO = new PerpayInvoiceAccountDetailParamVO();
                    perpayInvoiceAccountDetailParamVO.setOrderShelfId(prepayInvoiceInStoreResponseVO.getOrderShelfId());

                    /**
                     * 对账数量减去 业务单据未清数量 余数大于等于0 则对账数量等于业务单据未清数量 , 小于0时 对账数量等于对账单未清数量
                     */
                    perpayInvoiceAccountDetailParamVO.setAccountQuantity(
                            subtract.compareTo(BigDecimal.ZERO) >= 0 ? unclearQuantity : accountQuantity

                    );

                    //记录已对账数量
                    clearQuantityP = clearQuantityP.add(perpayInvoiceAccountDetailParamVO.getAccountQuantity()).setScale(2,BigDecimal.ROUND_HALF_UP);
                    prepayInvoiceInStoreResponseVO.setAccountQuantity(prepayInvoiceInStoreResponseVO.getAccountQuantity().add(perpayInvoiceAccountDetailParamVO.getAccountQuantity()));

                    PerpayInvoiceCalcAmountParamVO inStoreAmount = commonComponent.calcAmount(perpayInvoiceAccountDetailParamVO.getAccountQuantity(), prepayInvoiceInStoreResponseVO.getUnitPrice(), prepayInvoiceInStoreResponseVO.getTaxRate());


                    /**
                     * 本次对账金额
                     */
                    prepayInvoiceInStoreResponseVO.setAccountAmount(inStoreAmount.getClearQuantityAmount());

                    /**
                     * 本次对账不含税金额
                     */
                    prepayInvoiceInStoreResponseVO.setAccountNotaxAmount(inStoreAmount.getClearQuantityNotTaxAmount());

                    /**
                     * 本次对账税额
                     */
                    prepayInvoiceInStoreResponseVO.setAccountTaxAmount(inStoreAmount.getClearQuantityTaxAmount());

                    /**
                     * 将余数引用赋值给未清数量,方便判断该对账单明细是否还需要继续对账
                     */
                    accountQuantity = subtract;
                }
            }

            prepayInvoiceDetailResponseVO.setTimeCheckAccountQty(clearQuantityP);

            PerpayInvoiceCalcAmountParamVO inStoreAmount = commonComponent.calcAmount(clearQuantityP, prepayInvoiceDetailResponseVO.getUnitPrice(), prepayInvoiceDetailResponseVO.getTaxRate());
            prepayInvoiceDetailResponseVO.setTimeCheckAccountAmount(inStoreAmount.getClearQuantityAmount());
            prepayInvoiceDetailResponseVO.setTimeCheckAccountNotaxAmount(inStoreAmount.getClearQuantityNotTaxAmount());
            prepayInvoiceDetailResponseVO.setTimeCheckAccountTaxAmount(inStoreAmount.getClearQuantityTaxAmount());


        }

        for(PrepayInvoiceDetailResponseVO prepayInvoiceDetailResponseVO : prepayInvoiceDetailResponseVOS){


            PrepayInvoiceInStoreResponseTotalVO prepayInvoiceInStoreResponseTotalVO = prepayInvoiceDetailResponseVO.getPrepayInvoiceInStoreResponseTotalVO();

            List<PrepayInvoiceInStoreResponseVO> prepayInvoiceInStoreResponseVOS = prepayInvoiceInStoreResponseTotalVO.getPrepayInvoiceInStoreResponseVOS();

            PrepayInvoiceInStoreResponseTotalVO.setPrepayInvoiceInStoreResponseTotalVO(prepayInvoiceInStoreResponseTotalVO,prepayInvoiceInStoreResponseVOS);


            BigDecimal accountAmountTotal = BigDecimal.ZERO;
            BigDecimal accountNotaxAmountTotal = BigDecimal.ZERO;
            BigDecimal accountTaxAmountTotal = BigDecimal.ZERO;
            for(PrepayInvoiceInStoreResponseVO prepayInvoiceInStoreResponseVO : prepayInvoiceInStoreResponseVOS){

                BigDecimal accountAmount = prepayInvoiceInStoreResponseVO.getAccountAmount();
                accountAmountTotal = accountAmountTotal.add(accountAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal accountNotaxAmount = prepayInvoiceInStoreResponseVO.getAccountNotaxAmount();
                accountNotaxAmountTotal = accountNotaxAmountTotal.add(accountNotaxAmount).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal accountTaxAmount = prepayInvoiceInStoreResponseVO.getAccountTaxAmount();
                accountTaxAmountTotal = accountTaxAmountTotal.add(accountTaxAmount).setScale(2,BigDecimal.ROUND_HALF_UP);


            }

            BigDecimal timeCheckAccountAmount = prepayInvoiceDetailResponseVO.getTimeCheckAccountAmount();
            BigDecimal timeCheckAccountNotaxAmount = prepayInvoiceDetailResponseVO.getTimeCheckAccountNotaxAmount();
            BigDecimal timeCheckAccountTaxAmount = prepayInvoiceDetailResponseVO.getTimeCheckAccountTaxAmount();


            BigDecimal diffAmount = timeCheckAccountAmount.subtract(accountAmountTotal).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal diffNotaxAmount = timeCheckAccountNotaxAmount.subtract(accountNotaxAmountTotal).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal diffTaxAmount = timeCheckAccountTaxAmount.subtract(accountTaxAmountTotal).setScale(2, BigDecimal.ROUND_HALF_UP);

            prepayInvoiceDetailResponseVO.setDiffAmount(diffAmount);
            prepayInvoiceDetailResponseVO.setDiffNotaxAmount(diffNotaxAmount);
            prepayInvoiceDetailResponseVO.setDiffTaxAmount(diffTaxAmount);

        }



        PrepayInvoiceResponseVO.setTotal(prepayInvoiceResponseVO);

        return prepayInvoiceResponseVO;

    }




    /**
     * 单个对账
     * @param userVO
     * @param perpayInvoiceAccountParams
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reconciliations(UserVO userVO, List<PerpayInvoiceAccountParamVO> perpayInvoiceAccountParams) throws Exception {

        for(PerpayInvoiceAccountParamVO perpayInvoiceAccountParamVO : perpayInvoiceAccountParams){
            reconciliation(userVO,perpayInvoiceAccountParamVO);
        }


    }

    /**
     * 对账
     * @param userVO
     * @param perpayInvoiceAccountParam
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reconciliation(UserVO userVO, PerpayInvoiceAccountParamVO perpayInvoiceAccountParam) throws Exception {

        List<PerpayInvoiceAccountDetailParamVO> perpayInvoiceAccountDetailParamVOS = perpayInvoiceAccountParam.getPerpayInvoiceAccountDetailParamVOS();

        PrepayInvoiceDetail prepayInvoiceDetail = prepayInvoiceDetailMapper.selectByPrimaryKey(perpayInvoiceAccountParam.getId());

        for(PerpayInvoiceAccountDetailParamVO perpayInvoiceAccountParamVO : perpayInvoiceAccountDetailParamVOS){

            if(null == perpayInvoiceAccountParamVO.getAccountQuantity() || perpayInvoiceAccountParamVO.getAccountQuantity().compareTo(BigDecimal.ZERO) == 0){
                continue;
            }



            PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(prepayInvoiceDetail.getInvoiceId());
            /**
             * 未清数量
             */
            BigDecimal unclearQuantity = prepayInvoiceDetail.getUnAccountQuantity();

            BigDecimal subtract = unclearQuantity.subtract(perpayInvoiceAccountParamVO.getAccountQuantity());
            /**
             * 当未清数量不够扣除对账数量时抛出异常,数据异常
             */
            if(subtract.compareTo(BigDecimal.ZERO) < 0){
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未清数量扣除对账数量为负数");
            }

            prepayInvoiceDetail.setUnAccountQuantity(
                    subtract
            );
            BigDecimal clearQuantity = prepayInvoiceDetail.getAccountQuantity().add(perpayInvoiceAccountParamVO.getAccountQuantity()).setScale(2, BigDecimal.ROUND_HALF_UP);
            prepayInvoiceDetail.setAccountQuantity(
                    clearQuantity
            );

            if(subtract.compareTo(BigDecimal.ZERO) == 0){

                prepayInvoiceDetail.setAccountStatus(FinanceReconciliationStatus.RECONCILED);

            }else if(subtract.compareTo(BigDecimal.ZERO) > 0){
                prepayInvoiceDetail.setAccountStatus(FinanceReconciliationStatus.PARTIAL_RECONCILIATION);
            }

/*
            PerpayInvoiceCalcAmountParamVO perpayInvoiceDetailAmount = commonComponent.calcAmount(prepayInvoiceDetail.getAccountQuantity(), prepayInvoiceDetail.getUnitPrice(), prepayInvoiceDetail.getTaxRate());
*/

            PerpayInvoiceCalcAmountParamVO reconciliationAccount = commonComponent.calcAmount(perpayInvoiceAccountParamVO.getAccountQuantity(), prepayInvoiceDetail.getUnitPrice(), prepayInvoiceDetail.getTaxRate());

            if(ChainType.Headquarters.getCode() == userVO.getChainType()){
                /**
                 * 总部查询 查询采购入库单
                 */

                PurchaseInStorageShelf purchaseInStorageShelf = purchaseInStorageShelfMapper.selectByPrimaryKey(perpayInvoiceAccountParamVO.getOrderShelfId());

                /**
                 * 未清数量
                 */
                BigDecimal unclearQuantityInStorage = purchaseInStorageShelf.getUnclearQuantity();

                BigDecimal subtractInStorage = unclearQuantityInStorage.subtract(perpayInvoiceAccountParamVO.getAccountQuantity());
                /**
                 * 当未清数量不够扣除对账数量时抛出异常,数据异常
                 */
                if(subtractInStorage.compareTo(BigDecimal.ZERO) < 0){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未清数量扣除对账数量为负数");
                }

                purchaseInStorageShelf.setUnclearQuantity(subtractInStorage);
                purchaseInStorageShelf.setClearQuantity(purchaseInStorageShelf.getClearQuantity().add(perpayInvoiceAccountParamVO.getAccountQuantity()).setScale(2,BigDecimal.ROUND_HALF_UP));

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

                if(subtractInStorage.compareTo(BigDecimal.ZERO) == 0){

                    purchaseInStorageShelf.setStatus(PurchaseStatus.FINISHED.getStatus());

                }else {
                    purchaseInStorageShelf.setStatus(PurchaseStatus.PART_BILL.getStatus());
                }



                PerpayInvoiceCalcAmountParamVO purchaseInStorageDetailAmount = commonComponent.calcAmount(perpayInvoiceAccountParamVO.getAccountQuantity(), purchaseInStorageShelf.getRealPrice(), purchaseInStorageShelf.getTaxRate());

                /**
                 * 计算差额
                 */
                /**
                 * 金额差额
                 */
                BigDecimal diffAmount = reconciliationAccount.getClearQuantityAmount().subtract(purchaseInStorageDetailAmount.getClearQuantityAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                /**
                 * 不含税金额差额
                 */
                BigDecimal diffNotaxAmount = reconciliationAccount.getClearQuantityNotTaxAmount().subtract(purchaseInStorageDetailAmount.getClearQuantityNotTaxAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);

                /**
                 * 税额差额
                 */
                BigDecimal diffTaxAmount = reconciliationAccount.getClearQuantityTaxAmount().subtract(purchaseInStorageDetailAmount.getClearQuantityTaxAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);


                prepayInvoiceDetail.setDiffAmount(
                        null == prepayInvoiceDetail.getDiffAmount() ?
                                diffAmount.setScale(2,BigDecimal.ROUND_HALF_UP)
                                :
                                prepayInvoiceDetail.getDiffAmount().add(diffAmount).setScale(2,BigDecimal.ROUND_HALF_UP)
                );
                prepayInvoiceDetail.setDiffNotaxAmount(
                        null == prepayInvoiceDetail.getDiffNotaxAmount() ?
                                diffNotaxAmount.setScale(2,BigDecimal.ROUND_HALF_UP)
                                :
                                prepayInvoiceDetail.getDiffNotaxAmount().add(diffNotaxAmount).setScale(2,BigDecimal.ROUND_HALF_UP)
                );
                prepayInvoiceDetail.setDiffTaxAmount(
                        null == prepayInvoiceDetail.getDiffTaxAmount() ?
                                diffTaxAmount.setScale(2,BigDecimal.ROUND_HALF_UP)
                                :
                                prepayInvoiceDetail.getDiffTaxAmount().add(diffTaxAmount).setScale(2,BigDecimal.ROUND_HALF_UP)
                );
                prepayInvoiceDetailMapper.updateByPrimaryKeySelective(prepayInvoiceDetail);


                purchaseInStorageShelfMapper.updateByPrimaryKeySelective(purchaseInStorageShelf);


                prepayInvoice = clearPrepayInvoiceHeadOrder(prepayInvoiceDetail);

                PurchaseInStorageDetail detail = commonComponent.clearPurchaseInStorageDetail(userVO, purchaseInStorageShelf);

                PurchaseInStorage purchaseInStorage = commonComponent.clearPurchaseInStorage(detail);


                PrepayInvoiceAccount prepayInvoiceAccount = PrepayInvoiceAccount.getPrepayInvoiceAccount(
                        userVO,
                        prepayInvoice.getId(),
                        prepayInvoiceDetail.getId(),
                        purchaseInStorage.getId(),
                        purchaseInStorage.getCode(),
                        purchaseInStorage.getOrderType(),
                        purchaseInStorage.getInStorageDate(),
                        detail.getId(),
                        purchaseInStorageShelf.getId(),
                        perpayInvoiceAccountParamVO.getAccountQuantity(),
                        reconciliationAccount.getClearQuantityAmount(),
                        reconciliationAccount.getClearQuantityNotTaxAmount(),
                        reconciliationAccount.getClearQuantityTaxAmount(),
                        perpayInvoiceAccountParamVO.getLineNum(),
                        prepayInvoiceDetail.getAccountStatus().equals(FinanceReconciliationStatus.RECONCILED) ? 1 : 0,
                        diffAmount,
                        diffNotaxAmount,
                        diffTaxAmount,
                        "");

                prepayInvoiceAccountMapper.insertSelective(prepayInvoiceAccount);

            }else if(ChainType.Division.getCode() == userVO.getChainType()){

                /**
                 * 配进入库
                 */
                DistrInShelf distrInShelf = distrInShelfMapper.selectByPrimaryKey(perpayInvoiceAccountParamVO.getOrderShelfId());
                /**
                 * 未清数量
                 */
                BigDecimal unclearQuantityDistrInDetail = distrInShelf.getUnclearQuantity();

                BigDecimal subtractDistrInDetail = unclearQuantityDistrInDetail.subtract(perpayInvoiceAccountParamVO.getAccountQuantity());
                /**
                 * 当未清数量不够扣除对账数量时抛出异常,数据异常
                 */
                if(subtractDistrInDetail.compareTo(BigDecimal.ZERO) < 0){
                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未清数量扣除对账数量为负数");
                }

                distrInShelf.setUnclearQuantity(subtractDistrInDetail);
                distrInShelf.setClearQuantity(distrInShelf.getClearQuantity().add(perpayInvoiceAccountParamVO.getAccountQuantity()).setScale(2,BigDecimal.ROUND_HALF_UP));

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

                if(subtractDistrInDetail.compareTo(BigDecimal.ZERO) == 0){

                    distrInShelf.setStatus(DistrInStatus.FINISHED);

                }else {
                    distrInShelf.setStatus(DistrInStatus.PART_BILL);
                }

                PerpayInvoiceCalcAmountParamVO purchaseDistrInDetailAmount = commonComponent.calcAmount(perpayInvoiceAccountParamVO.getAccountQuantity(), distrInShelf.getRealPrice(), distrInShelf.getTaxRate());

                /**
                 * 计算差额
                 */
                /**
                 * 金额差额
                 */
                BigDecimal diffAmount = reconciliationAccount.getClearQuantityAmount().subtract(purchaseDistrInDetailAmount.getClearQuantityAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
                /**
                 * 不含税金额差额
                 */
                BigDecimal diffNotaxAmount = reconciliationAccount.getClearQuantityNotTaxAmount().subtract(purchaseDistrInDetailAmount.getClearQuantityNotTaxAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);

                /**
                 * 税额差额
                 */
                BigDecimal diffTaxAmount = reconciliationAccount.getClearQuantityTaxAmount().subtract(purchaseDistrInDetailAmount.getClearQuantityTaxAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);


                prepayInvoiceDetail.setDiffAmount(
                        null == prepayInvoiceDetail.getDiffAmount() ?
                                diffAmount.setScale(2,BigDecimal.ROUND_HALF_UP)
                                :
                                prepayInvoiceDetail.getDiffAmount().add(diffAmount).setScale(2,BigDecimal.ROUND_HALF_UP)
                );
                prepayInvoiceDetail.setDiffNotaxAmount(
                        null == prepayInvoiceDetail.getDiffNotaxAmount() ?
                                diffNotaxAmount.setScale(2,BigDecimal.ROUND_HALF_UP)
                                :
                                prepayInvoiceDetail.getDiffNotaxAmount().add(diffNotaxAmount).setScale(2,BigDecimal.ROUND_HALF_UP)
                );
                prepayInvoiceDetail.setDiffTaxAmount(
                        null == prepayInvoiceDetail.getDiffTaxAmount() ?
                                diffTaxAmount.setScale(2,BigDecimal.ROUND_HALF_UP)
                                :
                                prepayInvoiceDetail.getDiffTaxAmount().add(diffTaxAmount).setScale(2,BigDecimal.ROUND_HALF_UP)
                );


                prepayInvoiceDetailMapper.updateByPrimaryKeySelective(prepayInvoiceDetail);

                distrInShelfMapper.updateByPrimaryKeySelective(distrInShelf);

                prepayInvoice = clearPrepayInvoiceHeadOrder(prepayInvoiceDetail);

                DistrInDetail distrInDetail = commonComponent.clearDistrInDetailDetail(distrInShelf);

                DistrIn distrIn = commonComponent.clearDistrIn(distrInDetail);

                PrepayInvoiceAccount prepayInvoiceAccount = PrepayInvoiceAccount.getPrepayInvoiceAccount(
                        userVO,
                        prepayInvoice.getId(),
                        prepayInvoiceDetail.getId(),
                        distrIn.getId(),
                        distrIn.getCode(),
                        distrIn.getOrderType(),
                        distrIn.getInDate(),
                        distrInDetail.getId(),
                        distrInShelf.getId(),
                        perpayInvoiceAccountParamVO.getAccountQuantity(),
                        reconciliationAccount.getClearQuantityAmount(),
                        reconciliationAccount.getClearQuantityNotTaxAmount(),
                        reconciliationAccount.getClearQuantityTaxAmount(),
                        perpayInvoiceAccountParamVO.getLineNum(),
                        prepayInvoiceDetail.getAccountStatus().equals(FinanceReconciliationStatus.RECONCILED) ? 1 : 0,
                        diffAmount,
                        diffNotaxAmount,
                        diffTaxAmount,
                        "");

                prepayInvoiceAccountMapper.insertSelective(prepayInvoiceAccount);

            }
        }


        /**
         * 对账保存财务关键表数据
         */
        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(prepayInvoiceDetail.getInvoiceId());

        List<PrepayInvoiceDetail> prepayInvoiceDetails = prepayInvoiceDetailMapper.selectByInvoiceId(prepayInvoice.getId());

        financeComponent.prepayInvoiceToBalanceAndVoucherWhenCheckAccount(userVO, prepayInvoice, prepayInvoiceDetails);


    }



    /**
     * 修改预付发票头单信息
     * @param prepayInvoiceDetail
     */
    private PrepayInvoice clearPrepayInvoiceHeadOrder(PrepayInvoiceDetail prepayInvoiceDetail){

        List<PrepayInvoiceDetail> prepayInvoiceDetails = prepayInvoiceDetailMapper.selectByInvoiceId(prepayInvoiceDetail.getInvoiceId());

        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(prepayInvoiceDetail.getInvoiceId());


        prepayInvoice.setAccountStatus(FinanceReconciliationStatus.PARTIAL_RECONCILIATION);

        BigDecimal unclearQuantityTotal = prepayInvoiceDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getUnAccountQuantity()).map(PrepayInvoiceDetail::getUnAccountQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal clearQuantityTotal = prepayInvoiceDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getAccountQuantity()).map(PrepayInvoiceDetail::getAccountQuantity).reduce(BigDecimal.ZERO,BigDecimal::add);

        /*BigDecimal quantityTotal = prepayInvoice.getQuantityTotal();*/

        /*BigDecimal add = unclearQuantityTotal.add(clearQuantityTotal);
        if(add.compareTo(quantityTotal) != 0){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"明细总数和总单数据不一致");
        }*/

        if(unclearQuantityTotal.compareTo(BigDecimal.ZERO) == 0){
            /**
             * 明细行未清数量总和等于头单的数量总和,表示待对账
             */
            prepayInvoice.setAccountStatus(FinanceReconciliationStatus.RECONCILED);

        }else if(clearQuantityTotal.compareTo(BigDecimal.ZERO) == 0){
            /**
             * 明细行已清数量总和等于头单的数量总和,表示已对账
             */
            prepayInvoice.setAccountStatus(FinanceReconciliationStatus.PRE_RECONCILIATION);

        }else {
            /**
             * 明细行已清数量总和和未清数量总和都不为0 表示部分对账
             */
            prepayInvoice.setAccountStatus(FinanceReconciliationStatus.PARTIAL_RECONCILIATION);
        }

        prepayInvoiceMapper.updateByPrimaryKeySelective(prepayInvoice);

        return prepayInvoice;

    }




    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) throws BusinessException {

        draftCache.setOrderCode(OrderRule.PREPAY_INVOICE.getCodePrefix());

        draftCache.setEnterpriseId(userVO.getEnterpriseId());
        draftCache = redisComponent.saveDraftCacheVO(draftCache);

        return draftCache;
    }

    @Override
    public void removeDraftCach(Long supplierId,Long enterpriseId, String type, String redisKeyValue) throws BusinessException{

        redisComponent.removeDraftCacheVO(supplierId,enterpriseId,type,redisKeyValue);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(Long supplierId,UserVO userVO){
        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.PREPAY_INVOICE.getCodePrefix());
        draftCacheVO.setSupplierId(supplierId);
        return redisComponent.getDraftCacheVO(draftCacheVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void wariteOff(UserVO userVO,Long id) throws Exception {

        PrepayInvoice prepayInvoice = prepayInvoiceMapper.selectByPrimaryKey(id);

        if(null == prepayInvoice){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"找不到预付发票信息");
        }


        List<PrepayInvoiceAccount> prepayInvoiceAccounts = prepayInvoiceAccountMapper.selectByInvoiceId(id);

        for(PrepayInvoiceAccount pia : prepayInvoiceAccounts){

            PrepayInvoiceAccount newPrepayInvoiceAccount = new PrepayInvoiceAccount();
            newPrepayInvoiceAccount.setId(pia.getId());
            newPrepayInvoiceAccount.setStatus(0);
            prepayInvoiceAccountMapper.updateByPrimaryKeySelective(newPrepayInvoiceAccount);


            if(ChainType.Division.getCode() == userVO.getChainType()){

                DistrInShelf distrInShelf = distrInShelfMapper.selectByPrimaryKey(pia.getBaseShelfDtlId());
                DistrIn distrIn = distrInMapper.selectByPrimaryKey(pia.getBaseOrderId());
                BigDecimal shelfClearQuantity = distrInShelf.getClearQuantity();
                BigDecimal subtract = shelfClearQuantity.subtract(pia.getAccountQuantity());
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
                        null == distrInShelf.getUnclearQuantity() ?
                                pia.getAccountQuantity()
                                :
                                distrInShelf.getUnclearQuantity().add(pia.getAccountQuantity())
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
                BigDecimal subtract = shelfClearQuantity.subtract(pia.getAccountQuantity());
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
                        null == purchaseInStorageShelf.getUnclearQuantity() ?
                                pia.getAccountQuantity()
                                :
                                purchaseInStorageShelf.getUnclearQuantity().add(pia.getAccountQuantity())
                );

                if(purchaseInStorageShelf.getClearQuantity().compareTo(BigDecimal.ZERO) == 0){
                    purchaseInStorageShelf.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
                }else if(purchaseInStorageShelf.getUnclearQuantity().compareTo(BigDecimal.ZERO) == 0){
                    purchaseInStorageShelf.setStatus(PurchaseStatus.FINISHED.getStatus());
                }

                purchaseInStorageShelfMapper.updateByPrimaryKeySelective(purchaseInStorageShelf);

                PurchaseInStorageDetail detail = commonComponent.clearPurchaseInStorageDetail(userVO, purchaseInStorageShelf);

                commonComponent.clearPurchaseInStorage(detail);

            }


        }

        PrepayInvoice newPrepayInvoice = new PrepayInvoice();
        newPrepayInvoice.setId(prepayInvoice.getId());
        newPrepayInvoice.setAccountStatus(FinanceReconciliationStatus.WARITE_OF);
        newPrepayInvoice.setStatus(FinancePaymentStatus.WARITE_OF);
        UserEnterpriseUtils.setUserCreateOrModify(newPrepayInvoice,userVO,false);
        prepayInvoiceMapper.updateByPrimaryKeySelective(newPrepayInvoice);



        /**
         * 业务表关键数据
         */
        List<PrepayInvoiceDetail> prepayInvoiceDetails = prepayInvoiceDetailMapper.selectByInvoiceId(prepayInvoice.getId());
        for(PrepayInvoiceDetail prepayInvoiceDetail : prepayInvoiceDetails){
            prepayInvoiceDetail.setId(prepayInvoiceDetail.getId());
            prepayInvoiceDetail.setAccountStatus(FinanceReconciliationStatus.WARITE_OF);
            prepayInvoiceDetail.setStatus(FinancePaymentStatus.WARITE_OF);
            prepayInvoiceDetail.setUnAccountQuantity(prepayInvoiceDetail.getQuantity());
            prepayInvoiceDetail.setAccountQuantity(BigDecimal.ZERO);
            UserEnterpriseUtils.setUserCreateOrModify(prepayInvoiceDetail,userVO,false);
            prepayInvoiceDetailMapper.updateByPrimaryKeySelective(prepayInvoiceDetail);
        }
        PrepayInvoice pii = prepayInvoiceMapper.selectByPrimaryKey(id);
        financeComponent.prepayInvoiceToBalanceAndVoucherWhenSaveOrWriteOff(userVO, pii, prepayInvoiceDetails, "writeOff");




    }

    @Override
    public List<PrepayInvoiceModifyRecordResponseVO> queryModifyRecords(UserVO userVO,Long prepayInvoiceId,Page page){

        com.github.pagehelper.Page hPage = null;
        if(null != page) {
            hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        }
        List<PrepayInvoiceModifyRecord> prepayInvoiceModifyRecords = prepayInvoiceModifyRecordMapper.selectByEnterpriseId(userVO.getEnterpriseId(),prepayInvoiceId);

        List<PrepayInvoiceModifyRecordResponseVO> collect = prepayInvoiceModifyRecords.stream().map(prepayInvoiceModifyRecord -> {

            PrepayInvoiceModifyRecordResponseVO vo = new PrepayInvoiceModifyRecordResponseVO();

            return orikaMapperFactory.copyBean(vo, prepayInvoiceModifyRecord);
        }).collect(Collectors.toList());

        if(null != page) {
            page.setResult(collect);
            page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
            page.setTotalPage(hPage.getPages());
        }

        return collect;
    }


    @Override
    public void export(OutputStream output, PrepayInvoiceResponseVO prepayInvoiceResponseVO) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>(11);
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("approvalNumber","批准文号");
        map.put("quantity","数量");
        map.put("unitPrice","单价");
        map.put("amount","金额");
        map.put("taxRate","税率");
        map.put("notaxAmount","不含税金额");
        map.put("taxAmount","税额");


        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("供货单位编码：");
        titleSecondRow.append(prepayInvoiceResponseVO.getSupplierCode() == null ? "" : prepayInvoiceResponseVO.getSupplierCode());
        titleSecondRow.append("  供货单位名称：");
        titleSecondRow.append(prepayInvoiceResponseVO.getSupplierName() == null ? "" : prepayInvoiceResponseVO.getSupplierName());
        titleSecondRow.append("  开票日期：");
        titleSecondRow.append(prepayInvoiceResponseVO.getBillDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prepayInvoiceResponseVO.getBillDate()));
        titleSecondRow.append("  开票人员：");
        titleSecondRow.append(prepayInvoiceResponseVO.getBillManName() == null ? "" : prepayInvoiceResponseVO.getBillManName());
        titleSecondRow.append("  单号:");
        titleSecondRow.append(prepayInvoiceResponseVO.getCode() == null ? "" : prepayInvoiceResponseVO.getCode());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(prepayInvoiceResponseVO.getRemark() == null ? "" : prepayInvoiceResponseVO.getRemark());
        secondTitle.add(titleSecondRow.toString());


        List<String> name = new ArrayList<>();
        //第一行的企业名
        name.add(enterpriseMapper.selectByPrimaryKey(prepayInvoiceResponseVO.getEnterpriseId()).getName());
        //第二行的
        name.add("预付发票");

        /**
         * 记录导出尾行的合计中数值数据
         */
        List<String> needTotalName = new ArrayList<>(7);
        needTotalName.add("quantityTotal");
        needTotalName.add("amountTotal");
        needTotalName.add("notaxAmountTotal");
        needTotalName.add("taxAmountTotal");
        purchaseGeneralComponent.commExcelDistrExport(output,map,prepayInvoiceResponseVO.getPrepayInvoiceDetailSaveVOS(),name,secondTitle,null,null,needTotalName);
    }


    @Override
    public void exportUpdateRecord(OutputStream output, List<PrepayInvoiceModifyRecordResponseVO> list, UserVO loginUser) {
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
        name.add("预付发票修改记录");
        purchaseGeneralComponent.commExcelExport(output,map,list,name,secondTitle,end,true,new ArrayList<>());
    }

    private List<PrepayInvoiceModifyRecord> getModifyRecordList(UserVO userVO ,String tableName,Long keyId,Date updateTime
            , Map<String,Object> oldMap
            ,Map<String,Object> newMap ,Map<String,String> fieldMustMap){

        List<PrepayInvoiceModifyRecord> modifyRecordWithBLOBs = new ArrayList<>();

        for(Map.Entry<String,String> entry : fieldMustMap.entrySet()){
            Object obj = oldMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if(null != obj && null != newObj && !obj.equals(newObj)){
                PrepayInvoiceModifyRecord userModify = new PrepayInvoiceModifyRecord();
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
        fieldNames.put("invoiceId","预付发票总单ID");
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
