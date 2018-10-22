package com.rograndec.feijiayun.chain.business.finance.commission.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.commission.dao.SaleCommissionDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.commission.dao.SaleCommissionMapper;
import com.rograndec.feijiayun.chain.business.finance.commission.dao.SaleCommissionModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.commission.dao.SaleCommissionRelationMapper;
import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommission;
import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommissionDetail;
import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommissionModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommissionRelation;
import com.rograndec.feijiayun.chain.business.finance.commission.service.CommissionService;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.*;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionStrategyDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionStrategyMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategy;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionStrategyDetail;
import com.rograndec.feijiayun.chain.business.retail.saleflow.constant.RoyaltyFlag;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.ResponseSaleMan;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.FinancePaymentStatus;
import com.rograndec.feijiayun.chain.common.constant.status.SaleCommissionStatus;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private SaleCommissionMapper saleCommissionMapper;
    @Autowired
    private SaleCommissionDetailMapper saleCommissionDetailMapper;

    @Autowired
    private CommissionStrategyMapper commissionStrategyMapper;

    @Autowired
    private CommissionStrategyDetailMapper commissionStrategyDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SaleDetailMapper saleDetailMapper;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private SaleCommissionRelationMapper saleCommissionRelationMapper;

    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private SaleCommissionModifyRecordMapper saleCommissionModifyRecordMapper;

    @Autowired
    private OrikaMapperFactory orikaMapperFactory;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public NoSaleCommissionResponseTotalVO getCommissions(Long branchId, String clerkId,
                                                                Date startTime, Date endTime, UserVO userVO) throws Exception {

        Map<String, Object> map = new HashMap();
        if (!StringUtils.isEmpty(clerkId)) {
            map.put("clerkId", clerkId);
        }
        if (null != startTime) {
            map.put("startTime", startTime);
        }
        if (null != endTime) {
            map.put("endTime", endTime);
        }
        map.put("enterpriseId", userVO.getEnterpriseId());
        if (userVO.getChainType().equals(ChainType.Headquarters.getCode())) {// 总部
            map.put("parentId", userVO.getEnterpriseId());

            if(null == branchId)
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"总部查询门店id不能为空");

            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(branchId);
            map.put("chainType", enterprise.getChainType());
            map.put("branchId", branchId);
        } else {// 门店
            EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper
                    .queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
            if (enterpriseBusiness.getRoyaltyRule().equals(1)) {// 自主控制
                map.put("royaltyRule", "royaltyRule");
            }

            map.put("chainType", userVO.getChainType());
            map.put("branchId", userVO.getEnterpriseId());
        }

        map.put("saleType", 0);
        map.put("direction", 1);
        map.put("orderType", 6204);
        List<NoSaleCommissionDetailResponseVO> saleCommissionDetailResponseVOS  = saleCommissionMapper.selectNoCommissionsByParam(map); //销售单
        if(CollectionUtils.isEmpty(saleCommissionDetailResponseVOS)){
            return new NoSaleCommissionResponseTotalVO();
        }

        map.put("saleType", 1);
        map.put("direction", 0);
        map.put("orderType", 6205);
        List<NoSaleCommissionDetailResponseVO> noSaleCommissionDetailResponseVO  = saleCommissionMapper.selectNoCommissionsByParam(map); //销退单
        if(CollectionUtils.isEmpty(noSaleCommissionDetailResponseVO)){
            noSaleCommissionDetailResponseVO = new ArrayList<>();
        }

        List<NoSaleCommissionDetailResponseVO> sale = NoSaleCommissionDetailResponseVO.generateNoSaleCommissionDetailResponseVOs(saleCommissionDetailResponseVOS);
        List<NoSaleCommissionDetailResponseVO> noSale = NoSaleCommissionDetailResponseVO.generateNoSaleCommissionDetailResponseVOs(noSaleCommissionDetailResponseVO);

        List<NoSaleCommissionDetailResponseVO> responseNoSaleRoyaltyDetailVOS = calcSaleOrders(sale, noSale);

        if (!CollectionUtils.isEmpty(responseNoSaleRoyaltyDetailVOS)) {
            List<Long> strategyIds = NoSaleCommissionDetailResponseVO.getStrategyIds(responseNoSaleRoyaltyDetailVOS);

            List<CommissionStrategy> royaltyStrategies = new ArrayList<>();
            if (!CollectionUtils.isEmpty(strategyIds)) {
                royaltyStrategies = commissionStrategyMapper.selectByIds(strategyIds, EnableStatus.ENABLE.getStatus());
            }

            List<Long> setIds = new ArrayList<>();

            for (CommissionStrategy ro : royaltyStrategies) {
                setIds.add(ro.getId());
            }
            List<CommissionStrategyDetail> royaltyStrategyDetails = new ArrayList<>();

            if (!CollectionUtils.isEmpty(setIds)) {
                royaltyStrategyDetails = commissionStrategyDetailMapper.selectBySetIds(setIds,EnableStatus.ENABLE.getStatus());
            }

            NoSaleCommissionDetailResponseVO.setRoyaltyAmount4Calc(royaltyStrategyDetails, royaltyStrategies,
                    responseNoSaleRoyaltyDetailVOS);


            Iterator<NoSaleCommissionDetailResponseVO> iterator = responseNoSaleRoyaltyDetailVOS.iterator();

            while (iterator.hasNext()){
                NoSaleCommissionDetailResponseVO next = iterator.next();
                if(null == next.getCommissionAmount() || next.getCommissionAmount().compareTo(BigDecimal.ZERO) == 0){

                    iterator.remove();

                }
            }

            if(CollectionUtils.isEmpty(responseNoSaleRoyaltyDetailVOS)){
                return new NoSaleCommissionResponseTotalVO();
            }else {
                NoSaleCommissionResponseTotalVO responseNoSaleRoyaltyTotalVO = NoSaleCommissionResponseTotalVO.getResponseNoSaleRoyaltyTotalVO(userVO,responseNoSaleRoyaltyDetailVOS);

                return responseNoSaleRoyaltyTotalVO;
            }
        }

        return new NoSaleCommissionResponseTotalVO();

    }


    public List<NoSaleCommissionDetailResponseVO> calcSaleOrders(List<NoSaleCommissionDetailResponseVO> responseSaleRoyaltyDetailVOS,List<NoSaleCommissionDetailResponseVO> noSaleCommissionDetailResponseVOS ){

        if(CollectionUtils.isEmpty(noSaleCommissionDetailResponseVOS)){
            return responseSaleRoyaltyDetailVOS;
        }

        for(NoSaleCommissionDetailResponseVO sale : responseSaleRoyaltyDetailVOS){

            for(NoSaleCommissionDetailResponseVO noSale : noSaleCommissionDetailResponseVOS){

                if(sale.getGoodsId().equals(noSale.getGoodsId())){

                    /**
                     * 数量
                     */
                    BigDecimal quantity = sale.getQuantity().subtract(noSale.getQuantity());
                    sale.setQuantity(quantity);

                    /**
                     * 销售总额
                     */
                    BigDecimal amount = sale.getAmount().subtract(noSale.getAmount());
                    sale.setAmount(amount);

                    /**
                     * 成本总额
                     */
                    BigDecimal costAmount = sale.getCostAmount().subtract(noSale.getCostAmount());
                    sale.setCostAmount(costAmount);

                    /**
                     * 利润总额
                     */

                    BigDecimal profit = sale.getProfit().subtract(noSale.getProfit());
                    sale.setProfit(profit);

                    //利润率
                    BigDecimal profitRate = profit.divide(amount,2, BigDecimal.ROUND_HALF_UP);
                    sale.setProfitRate(profitRate);


                }

            }
        }

       return responseSaleRoyaltyDetailVOS;

    }

    /**
     * 新增提成管理
     * @param userVO
     * @param noSaleCommissionResponseTotalVO
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaleCommission save(UserVO userVO, NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO) throws Exception {

        String code = orderCodeComponent.generate(
                OrderRule.COMMISSION.getCodePrefix()
                , userVO.getEnterpriseId()
                , userVO.getEnterpriseCode()
        );

        /**
         * 提成人员
         */
        User commissionMan = userMapper.selectByPrimaryKey(noSaleCommissionResponseTotalVO.getCommissionManId());
        if(null == commissionMan)
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未找到提成人员");

        noSaleCommissionResponseTotalVO.setCommissionManCode(commissionMan.getCode());
        noSaleCommissionResponseTotalVO.setCommissionManName(commissionMan.getName());

        /**
         * 营业人员
         */
        User clerk = userMapper.selectByPrimaryKey(noSaleCommissionResponseTotalVO.getClerkId());
        noSaleCommissionResponseTotalVO.setClerkCode(clerk.getCode());
        noSaleCommissionResponseTotalVO.setClerkName(clerk.getName());

        if(userVO.getChainType() != ChainType.Headquarters.getCode()){

            /**
             * 加盟店,分店 门店信息为自己
             */

            noSaleCommissionResponseTotalVO.setBranchId(userVO.getEnterpriseId());
            noSaleCommissionResponseTotalVO.setBranchCode(userVO.getEnterpriseCode());
            noSaleCommissionResponseTotalVO.setBranchName(userVO.getEnterpriseName());
        }else {

            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(noSaleCommissionResponseTotalVO.getBranchId());
            if(null == enterprise)
                throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未找到门店");

            noSaleCommissionResponseTotalVO.setBranchId(enterprise.getId());
            noSaleCommissionResponseTotalVO.setBranchCode(enterprise.getCode());
            noSaleCommissionResponseTotalVO.setBranchName(enterprise.getName());

        }

        SaleCommission saleCommission = SaleCommission.genreateSaleCommission(userVO, noSaleCommissionResponseTotalVO, code, true);

        saleCommissionMapper.insertSelective(saleCommission);

        List<Long> goodsIds = NoSaleCommissionDetailResponseVO.getGoodsIds(noSaleCommissionResponseTotalVO.getResponseNoSaleRoyaltyDetailVOS());

        if(CollectionUtils.isEmpty(goodsIds)){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未找到商品");
        }

        List<Goods> goods = goodsMapper.selectByIdsNotStatus(goodsIds);

        List<SaleCommissionDetail> saleCommissionDetails = SaleCommissionDetail.generateSaleCommissionDetails(userVO, saleCommission, goods, noSaleCommissionResponseTotalVO.getResponseNoSaleRoyaltyDetailVOS(), true);

        for(SaleCommissionDetail saleCommissionDetail : saleCommissionDetails){

            BigDecimal profitRate = saleCommissionDetail.getProfitRate();
            if(null != profitRate) {
                BigDecimal bigDecimal = profitRate.multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP);
                saleCommissionDetail.setProfitRate(bigDecimal);
            }
            saleCommissionDetailMapper.insertSelective(saleCommissionDetail);
        }

        List<Long> slDtlIds = NoSaleCommissionDetailResponseVO.getSlDtlIds(noSaleCommissionResponseTotalVO.getResponseNoSaleRoyaltyDetailVOS());

        if(CollectionUtils.isEmpty(slDtlIds))
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未找到销售单明细");


        List<SaleDetail> saleDetails = saleDetailMapper.selectByIds(slDtlIds);

        for(SaleDetail saleDetail : saleDetails){

            SaleDetail newSaleDetail = new SaleDetail();
            newSaleDetail.setId(saleDetail.getId());
            newSaleDetail.setRoyaltyFlag(RoyaltyFlag.ROYALTY.getCode());

            saleDetailMapper.updateByPrimaryKeySelective(newSaleDetail);
        }

        List<Long> saleIds = SaleDetail.getSaleIds(saleDetails);

        if(CollectionUtils.isEmpty(saleIds))
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未找到销售单");


        List<Sale> sales = saleMapper.selectByIds(saleIds);


        List<SaleCommissionRelation> saleCommissionRelations = new ArrayList<>();

        for(SaleCommissionDetail saleCommissionDetail : saleCommissionDetails){
            List<SaleCommissionRelation> scs = SaleCommissionRelation.generateSaleCommissionRelations(userVO, saleCommission, sales, saleDetails, saleCommissionDetail);

            if(!CollectionUtils.isEmpty(scs))
                saleCommissionRelations.addAll(scs);
        }


        for(SaleCommissionRelation saleCommissionRelation : saleCommissionRelations){

            saleCommissionRelationMapper.insertSelective(saleCommissionRelation);
        }

        //删除草稿
        removeDraftCach(userVO.getEnterpriseId(), OrderRule.COMMISSION.getCodePrefix(), noSaleCommissionResponseTotalVO.getRedisKeyValue());

        return saleCommission;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaleCommission update(UserVO userVO, NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO) throws Exception {

        Long id = noSaleCommissionResponseTotalVO.getId();

        SaleCommission oldSaleCommission = new SaleCommission();

        SaleCommission saleCommission = saleCommissionMapper.selectByPrimaryKey(id);

        oldSaleCommission.setId(saleCommission.getId());
        oldSaleCommission.setCommissionDate(saleCommission.getCommissionDate());
        oldSaleCommission.setCommissionManId(saleCommission.getCommissionManId());
        oldSaleCommission.setCommissionManName(saleCommission.getCommissionManName());
        oldSaleCommission.setCommissionManCode(saleCommission.getCommissionManCode());
        oldSaleCommission.setRemark(saleCommission.getRemark());


        saleCommission.setCommissionDate(noSaleCommissionResponseTotalVO.getCommissionDate());
        saleCommission.setCommissionManId(noSaleCommissionResponseTotalVO.getCommissionManId());

        User user = userMapper.selectByPrimaryKey(noSaleCommissionResponseTotalVO.getCommissionManId());

        if(null == user){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未找到提成人员");
        }

        saleCommission.setCommissionManName(user.getName());
        saleCommission.setCommissionManCode(user.getCode());
        saleCommission.setRemark(noSaleCommissionResponseTotalVO.getRemark());


        Map<String,Object> newSaleCommissionMap = modifyRecordCompoent.getFieldsMap(saleCommission);



        Map<String,Object> oldSaleCommissionMap = modifyRecordCompoent.getFieldsMap(oldSaleCommission);

        List<SaleCommissionModifyRecord> paymentInvoiceInfoModifyRecords = getModifyRecordList(userVO, "saas_sale_commission_modify_record",
                oldSaleCommission.getId(), new Date(),
                oldSaleCommissionMap, newSaleCommissionMap, fieldMustMap());


        saleCommissionMapper.updateByPrimaryKeySelective(saleCommission);


        for(SaleCommissionModifyRecord scmy : paymentInvoiceInfoModifyRecords){

            scmy.setReason(noSaleCommissionResponseTotalVO.getReason());
            saleCommissionModifyRecordMapper.insertSelective(scmy);
        }


        return saleCommission;

    }

    private Map<String,String> fieldMustMap(){
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("commissionDate","提成日期");
        fieldNames.put("commissionManCode","提成人员编码");
        fieldNames.put("commissionManName","提成人员名称");
        fieldNames.put("remark","备注");

        return fieldNames;
    }

    private List<SaleCommissionModifyRecord> getModifyRecordList(UserVO userVO , String tableName, Long keyId, Date updateTime
            , Map<String,Object> oldMap
            , Map<String,Object> newMap , Map<String,String> fieldMustMap){

        List<SaleCommissionModifyRecord> modifyRecordWithBLOBs = new ArrayList<>();

        for(Map.Entry<String,String> entry : fieldMustMap.entrySet()){
            Object obj = oldMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if(null != obj && null != newObj && !obj.equals(newObj)){
                SaleCommissionModifyRecord userModify = new SaleCommissionModifyRecord();
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
                if(obj instanceof Date){
                    Date oldDate = (Date) obj;
                    String date = DateUtils.getDate(oldDate);
                    userModify.setOldContent(date);
                }else {
                    userModify.setOldContent(obj.toString());
                }
                if(newObj instanceof Date){
                    Date newDate = (Date) newObj;
                    String date = DateUtils.getDate(newDate);
                    userModify.setNewContent(date);
                }else {
                    userModify.setNewContent(newObj.toString());
                }
                modifyRecordWithBLOBs.add(userModify);
            }
        }

        return modifyRecordWithBLOBs;
    }


    @Override
    public List<NewSelectBean> queryBranch(UserVO userVO, String param){

        /**
         * 查询总部控制的门店
         */
        List<NewSelectBean> enterprises = enterpriseMapper.selectHeadControlBranchByParentId(userVO.getEnterpriseId(),param);

        return enterprises;

    }


    @Override
    public List<ResponseSaleMan> getSaleMans(Map<String, Object> map){
        List<SaleDetail> saleDetails = saleDetailMapper.selectUserByParam(map);
        List<ResponseSaleMan> responseSaleMans = ResponseSaleMan.getResponseSaleMan(saleDetails);
        return CollectionUtils.isEmpty(responseSaleMans) ? Collections.emptyList() : responseSaleMans;
    }

    @Override
    public CommissionResponseTotalVO queryCommissions(UserVO userVO, CommissionQueryParamVO commissionQueryParamVO, Page page) throws InvocationTargetException, IntrospectionException, IllegalAccessException, NoSuchFieldException, ParseException {

        List<NoSaleCommissionResponseTotalVO> noSaleCommissionResponseTotalVOS = new ArrayList<>();

        Map<String, Object> notNullBeanFeild2Map = ReflectUtils.getNotNullBeanFeild2Map(commissionQueryParamVO);
        notNullBeanFeild2Map.put("enterpriseId",userVO.getEnterpriseId());
     /*   String startDate = commissionQueryParamVO.getStartDate();
        String endDate = commissionQueryParamVO.getEndDate();
        if(!StringUtils.isEmpty(startDate)) {
            notNullBeanFeild2Map.put("startDate", DateUtils.StringToDate(startDate));
        }
        if(!StringUtils.isEmpty(startDate)) {
            notNullBeanFeild2Map.put("endDate",DateUtils.StringToDate(endDate));
        }*/
        Object orderName = notNullBeanFeild2Map.get("orderName");

        if(null != orderName){

            if(orderName.toString().equals("commissionDate")){
                notNullBeanFeild2Map.put("orderName","commission_date");
            }


        }
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
        List<SaleCommission> saleCommissions = saleCommissionMapper.selectByParam(notNullBeanFeild2Map);


        Object status = notNullBeanFeild2Map.get("status");
        List<Integer> list = new ArrayList<>();
        if(null == status){

            /**
             * 状态（1-已完成；2-已冲销）
             */
            list.add(1);
            notNullBeanFeild2Map.put("list",list);
        }else {
            list.add(Integer.parseInt(status.toString()));
            notNullBeanFeild2Map.put("list",list);
        }

        CommissionResponseTotalVO commissionResponseTotalVO = saleCommissionMapper.selectByParamSum(notNullBeanFeild2Map);
        if(null == commissionResponseTotalVO){
            commissionResponseTotalVO = new CommissionResponseTotalVO();
        }

        commissionResponseTotalVO.setTotalVOS(noSaleCommissionResponseTotalVOS);

        if(!CollectionUtils.isEmpty(saleCommissions)){


            for(SaleCommission saleCommission : saleCommissions){

                NoSaleCommissionResponseTotalVO ntv = new NoSaleCommissionResponseTotalVO();
                ntv = orikaMapperFactory.copyBean(ntv, saleCommission);
                noSaleCommissionResponseTotalVOS.add(ntv);
            }

        }


        page.setResult(commissionResponseTotalVO);
        page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
        page.setTotalPage(hPage.getPages());

        return commissionResponseTotalVO;

    }


    @Override
    public NoSaleCommissionResponseTotalVO queryCommission(Long commissionId){


        SaleCommission saleCommission = saleCommissionMapper.selectByPrimaryKey(commissionId);

        if(null == saleCommission){
            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"未找到提成单");
        }

        NoSaleCommissionResponseTotalVO ntv = new NoSaleCommissionResponseTotalVO();
        ntv = orikaMapperFactory.copyBean(ntv, saleCommission);

        List<SaleCommissionDetail> saleCommissionDetails = saleCommissionDetailMapper.selectByCommissionId(commissionId);

        List<NoSaleCommissionDetailResponseVO> detailVOs = saleCommissionDetails.stream().map(saleCommissionDetail -> {
            NoSaleCommissionDetailResponseVO noSaleCommissionDetailResponseVO = new NoSaleCommissionDetailResponseVO();
            noSaleCommissionDetailResponseVO = orikaMapperFactory.copyBean(noSaleCommissionDetailResponseVO, saleCommissionDetail);
            BigDecimal profitRate = noSaleCommissionDetailResponseVO.getProfitRate();
            if(null != profitRate && profitRate.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal bigDecimal = profitRate.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);
                noSaleCommissionDetailResponseVO.setProfitRate(bigDecimal);
            }

            noSaleCommissionDetailResponseVO.setCommissionStrategy(saleCommissionDetail.getCommissionStrategy());
            return noSaleCommissionDetailResponseVO;
        }).collect(Collectors.toList());

        NoSaleCommissionResponseTotalVO.setTotal(ntv,detailVOs);

        ntv.setResponseNoSaleRoyaltyDetailVOS(detailVOs);

        return ntv;
    }


    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO draftCache) throws BusinessException {

        draftCache.setOrderCode(OrderRule.COMMISSION.getCodePrefix());

        draftCache.setEnterpriseId(userVO.getEnterpriseId());
        draftCache = redisComponent.saveDraftCacheVO(draftCache);

        return draftCache;
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) throws BusinessException{

        redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO){
        DraftCacheVO draftCacheVO = new DraftCacheVO();
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderCode(OrderRule.COMMISSION.getCodePrefix());
        return redisComponent.getDraftCacheVO(draftCacheVO);
    }


    @Override
    public List<SaleCommissionModifyRecordResponseVO> queryModifyRecords(UserVO userVO, Long id, Page page){

        com.github.pagehelper.Page hPage = null;
        if(null != page) {
            hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        }

        List<SaleCommissionModifyRecord> saleCommissionModifyRecords = saleCommissionModifyRecordMapper.selectByEnterpriseId(userVO.getEnterpriseId(), id);

        List<SaleCommissionModifyRecordResponseVO> collect = saleCommissionModifyRecords.stream().map(paymentInvoiceModifyRecord -> {

            SaleCommissionModifyRecordResponseVO vo = new SaleCommissionModifyRecordResponseVO();

            return orikaMapperFactory.copyBean(vo, paymentInvoiceModifyRecord);
        }).collect(Collectors.toList());

        if(null != page) {
            page.setResult(CollectionUtils.isEmpty(collect) ? Collections.emptyList() : collect);
            page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
            page.setTotalPage(hPage.getPages());
        }

        return collect;
    }

    @Override
    public void export(OutputStream output, UserVO userVO, NoSaleCommissionResponseTotalVO noSaleCommissionResponseTotalVO) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>(11);
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("quantity","数量");
        map.put("amount","销售总额");
        map.put("costAmount","成本总额");
        map.put("profit","利润总额");
        map.put("profitRate","利润率(%)");
        map.put("commissionStrategy","提成策略");
        map.put("commissionAmount","应提金额");


        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("门店编码：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getBranchCode() == null ? "" : noSaleCommissionResponseTotalVO.getBranchCode());
        titleSecondRow.append("  门店名称：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getBranchName() == null ? "" : noSaleCommissionResponseTotalVO.getBranchName());
        titleSecondRow.append("  营业人员：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getClerkName() == null ? "" : noSaleCommissionResponseTotalVO.getClerkName());
        titleSecondRow.append("  提成日期：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getCommissionDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(noSaleCommissionResponseTotalVO.getCommissionDate()));
        titleSecondRow.append("  提成人员：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getCommissionManName() == null ? "" : noSaleCommissionResponseTotalVO.getCommissionManName());
        titleSecondRow.append("  单号：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getCode() == null ? "" : noSaleCommissionResponseTotalVO.getCode());
        titleSecondRow.append("  销售日期（从）：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getSaleDateFrom() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(noSaleCommissionResponseTotalVO.getSaleDateFrom()));
        titleSecondRow.append("  销售日期（至）：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getSaleDateTo() == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(noSaleCommissionResponseTotalVO.getSaleDateTo()));
        titleSecondRow.append("  备注：");
        titleSecondRow.append(noSaleCommissionResponseTotalVO.getRemark() == null ? "" : noSaleCommissionResponseTotalVO.getRemark());

        secondTitle.add(titleSecondRow.toString());


        List<String> name = new ArrayList<>();
        //第一行的企业名
        name.add(enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId()).getName());
        //第二行的
        name.add("销售提成");

        StringBuilder end = new StringBuilder();
        end.append("应提金额：");
        end.append(noSaleCommissionResponseTotalVO.getAmountTotal());
        end.append(";");
        end.append("实提金额：");
        end.append(noSaleCommissionResponseTotalVO.getRealAmountTotal());
        end.append(";");
        end.append("提成差额：");
        end.append(noSaleCommissionResponseTotalVO.getDiffAmountTotal());
        end.append(";");

        purchaseGeneralComponent.commExcelDistrExport(output,map,noSaleCommissionResponseTotalVO.getResponseNoSaleRoyaltyDetailVOS(),name,secondTitle,end.toString(),null,Collections.emptyList());
    }


    @Override
    public void exportUpdateRecord(OutputStream output, List<SaleCommissionModifyRecordResponseVO> saleCommissionModifyRecordResponseVOS, UserVO loginUser) {
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
        name.add("提成管理修改记录");
        purchaseGeneralComponent.commExcelExport(output,map,saleCommissionModifyRecordResponseVOS,name,secondTitle,end,true,new ArrayList<>());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void wariteOff(UserVO userVO, Long id) throws Exception {

        SaleCommission saleCommission = saleCommissionMapper.selectByPrimaryKey(id);

        saleCommission.setStatus(SaleCommissionStatus.WARITE_OF);
        saleCommissionMapper.updateByPrimaryKeySelective(saleCommission);

        List<SaleCommissionDetail> saleCommissionDetails = saleCommissionDetailMapper.selectByCommissionId(saleCommission.getId());

        for(SaleCommissionDetail saleCommissionDetail :  saleCommissionDetails){
            saleCommissionDetail.setStatus(SaleCommissionStatus.WARITE_OF);

            saleCommissionDetailMapper.updateByPrimaryKeySelective(saleCommissionDetail);
        }

        List<SaleCommissionRelation> saleCommissionRelations = saleCommissionRelationMapper.selectByCommissionId(saleCommission.getId());

        for(SaleCommissionRelation saleCommissionRelation : saleCommissionRelations){
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setId(saleCommissionRelation.getSaleDtlId());
            saleDetail.setRoyaltyFlag(RoyaltyFlag.NO_ROYALTY.getCode());
            saleDetail.setModifierId(userVO.getUserId());
            saleDetail.setModifierCode(userVO.getUserCode());
            saleDetail.setModifierName(userVO.getUserName());
            saleDetail.setUpdateTime(new Date());
            saleDetailMapper.updateRoyaltyFlag(saleDetail);
        }


    }

}
