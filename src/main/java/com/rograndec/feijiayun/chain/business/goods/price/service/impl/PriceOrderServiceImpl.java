package com.rograndec.feijiayun.chain.business.goods.price.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
import com.rograndec.feijiayun.chain.business.goods.price.constant.PriceOrderConstant;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.price.excel.PriceOrderIRowReader;
import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import com.rograndec.feijiayun.chain.business.goods.price.service.PriceOrderService;
import com.rograndec.feijiayun.chain.business.goods.price.vo.*;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.PriceType;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.ExcelMethodUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelReaderUtil;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhaiwei on 2017/9/6.
 */
@Service
@Scope(value = "prototype")
public class PriceOrderServiceImpl implements PriceOrderService{

    private Logger logger = LoggerFactory.getLogger(PriceOrderServiceImpl.class);

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;

    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private PriceModifyRecordMapper priceModifyRecordMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private GoodsComponent goodsComponent;

    @Autowired
    private SupplierComponent supplierComponent;
    @Autowired
    private PriceExcelComponent<PriceOrderDetailReturnVO> priceExcelComponent;
    @Autowired
    private PriceOrderIRowReader priceOrderIRowReader;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    private static Long NOT_HAS_PARENTORDER = 0L;
    /**
     * 新增价格清单头信息
     * @param userVO
     * @param addOrUpdatePriceOrderVO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePriceOrder(UserVO userVO, AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO) throws Exception {
        PriceOrder priceOrder = PriceOrder.getPriceOrder(userVO,addOrUpdatePriceOrderVO,true);

        priceOrderMapper.insertSelective(priceOrder);

        Long parentOrderId = addOrUpdatePriceOrderVO.getParentOrderId();
//        addOrUpdatePriceOrderVO.setParentOrderId(priceOrder.getParentOrderId());

        if(!NOT_HAS_PARENTORDER.equals(parentOrderId)){
            List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByPriceOrderId(parentOrderId);
            priceOrderDetails.stream().forEach(priceOrderDetail -> {
                priceOrderDetail.setId(null);
                priceOrderDetail.setPriceOrderId(priceOrder.getId());
                priceOrderDetailMapper.insertSelective(priceOrderDetail);
            });
         /*   List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOs4Entitys = UpdatePriceOrderDetailVO.getAddPriceOrderDetailVOs4Entitys(priceOrderDetails,priceOrder);
            priceOrderDetailVOS.addAll(updatePriceOrderDetailVOs4Entitys);*/

        }else {
            savePriceOrderDetail(userVO,priceOrder,true);
        }



    }

    /**
     * 修改清单头和明细信息
     * @param userVO
     * @param addOrUpdatePriceOrderVO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePriceOrder(UserVO userVO, AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO) throws Exception {
//        PriceOrder oldPriceOrder = priceOrderMapper.selectByPrimaryKey(addOrUpdatePriceOrderVO.getId());

        List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS = addOrUpdatePriceOrderVO.getUpdatePriceOrderDetailVOS();

        if(CollectionUtils.isEmpty(updatePriceOrderDetailVOS)){
            return;
        }

        List<Long> detailIds = UpdatePriceOrderDetailVO.getIds(updatePriceOrderDetailVOS);
        //价格管理（0-总部控制；1-自主控制）门店验证权限
        Map<Long,Boolean> goodsIdMap = new HashMap<>();
        if(userVO.getChainType() != ChainType.Headquarters.getCode()) {
            goodsIdMap = validStorePower(userVO, addOrUpdatePriceOrderVO);
    	}else {
            goodsIdMap = validHeadPower(userVO, addOrUpdatePriceOrderVO);
    	}

        PriceOrder priceOrder = PriceOrder.getPriceOrder(userVO,addOrUpdatePriceOrderVO,false);


        priceOrderMapper.updateByPrimaryKeySelective(priceOrder);


        List<PriceOrderDetail> oldPriceOrderDetail = priceOrderDetailMapper.selectByIds(detailIds);
/*
        List<PriceOrderDetail> oldPriceOrderDetail = priceOrderDetailMapper.selectByPriceOrderId(addOrUpdatePriceOrderVO.getId());
*/
        if(CollectionUtils.isEmpty(updatePriceOrderDetailVOS)){
            return;
        }

        List<PriceOrderDetail> newPriceOrderDetail = new ArrayList<>();

        Set<Long> taxRateIds = UpdatePriceOrderDetailVO.getTaxRateIds(updatePriceOrderDetailVOS);

        List<GoodsTaxRate> goodsTaxRates = goodsComponent.getGoodsTaxRates(taxRateIds);

        Map<Long,GoodsTaxRate> goodsTaxRateMap = new HashMap<>();
        for(GoodsTaxRate goodsTaxRate : goodsTaxRates){
            goodsTaxRateMap.put(goodsTaxRate.getId(),goodsTaxRate);
        }

        List<Long> priceIds = new ArrayList<>();

        batchUpdatePriceOrderDetails( userVO,
                goodsTaxRateMap,
                 addOrUpdatePriceOrderVO,
                updatePriceOrderDetailVOS,
                goodsIdMap,
                priceIds,
                newPriceOrderDetail);
       /* for(UpdatePriceOrderDetailVO updatePriceOrderDetailVO : updatePriceOrderDetailVOS){
            Boolean isTrue = goodsIdMap.get(updatePriceOrderDetailVO.getGoodsId());
            if(null == isTrue){
                priceIds.add(updatePriceOrderDetailVO.getId());
                PriceOrderDetail priceOrderDetail = PriceOrderDetail.getPriceOrderDetail4VO(userVO
                        ,addOrUpdatePriceOrderVO.getStatus(),updatePriceOrderDetailVO,goodsTaxRateMap,false);
                priceOrderDetailMapper.updateByPrimaryKeySelective(priceOrderDetail);

                newPriceOrderDetail.add(priceOrderDetail);
            }

        }*/

        Set<Long> goodsIds = PriceOrderDetail.getGoodsIds(oldPriceOrderDetail);
        if(CollectionUtils.isEmpty(goodsIds)){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"商品未找到");
        }
        List<Long> cs = new ArrayList(goodsIds);

        Long enterpriseId = userVO.getEnterpriseId();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        if(!enterprise.getChainType().equals(ChainType.Headquarters.getCode())){
            enterpriseId = enterprise.getParentId();
        }

        List<Goods> goodsList = goodsMapper.selectByIds(cs,enterpriseId);

        List<PriceModifyRecordWithBLOBs> priceModifyRecordWithBLOBs = getPriceModifyRecordWithBLOBs(userVO, goodsList, newPriceOrderDetail, oldPriceOrderDetail, priceOrder.getUpdateTime());

        for(PriceModifyRecordWithBLOBs prw : priceModifyRecordWithBLOBs){
            prw.setKeyId(priceOrder.getId());
            priceModifyRecordMapper.insertSelective(prw);
        }


        List<GoodsBusiness> goodsBusinesses = goodsBusinessMapper.selectByGoodsIds(cs,enterpriseId);

        List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByIds(priceIds);
/*
        List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByPriceOrderId(priceOrder.getId());
*/

        /**
         * 级联修改价格清单父价格清单或子价格清单的税和金额
         */

        cascadeParentAndChildPriceOrder(priceOrderDetails,priceOrder);

        /**
         * 修改价格清单后要级联修改商品
         */
        updateSystemPriceOrder2Goods(goodsBusinesses,priceOrderDetails,priceOrder);

    }


    public void batchUpdatePriceOrderDetails(UserVO userVO,Map<Long,GoodsTaxRate> goodsTaxRateMap,
                                             AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO,
                                             List<UpdatePriceOrderDetailVO>  updatePriceOrderDetailVOS,
                                             Map<Long,Boolean> goodsIdMap,List<Long> priceIds,List<PriceOrderDetail> newPriceOrderDetail){
        // 新获取一个模式为BATCH，自动提交为false的session
        // 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
/*
        String sql = "update t_user set name=?, age=? where id=?";
        jdbcTemplate.batchUpdate(sql,);*/
        try {
            PriceOrderDetailMapper mapper = session.getMapper(PriceOrderDetailMapper.class);
            int i = 0 ;

            for(UpdatePriceOrderDetailVO updatePriceOrderDetailVO : updatePriceOrderDetailVOS){
                Boolean isTrue = goodsIdMap.get(updatePriceOrderDetailVO.getGoodsId());
                if(null == isTrue){
                    priceIds.add(updatePriceOrderDetailVO.getId());
                    PriceOrderDetail priceOrderDetail = PriceOrderDetail.getPriceOrderDetail4VO(userVO
                            ,addOrUpdatePriceOrderVO.getStatus(),updatePriceOrderDetailVO,goodsTaxRateMap,false);
                    mapper.updateByPrimaryKeySelective(priceOrderDetail);
                    newPriceOrderDetail.add(priceOrderDetail);
                    i++;
                    if(i % 1000 == 0) {
                        session.commit();
                        session.clearCache();
                    }
                }

            }
            session.commit();
        }catch (Exception e){
            session.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }

    /*@Override
    @Transactional(rollbackFor = Exception.class)
    public void testTh(){



        User user = userMapper.selectByPrimaryKey(320L);
        user.setName("翟伟.测试3");
        userMapper.updateByPrimaryKeySelective(user);

        int i = 0;
            System.out.println(3/i);
        testtt();


    }

    public void testtt(){
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            UserAdministrationMapper mapper = session.getMapper(UserAdministrationMapper.class);

            UserAdministration userAdministration = new UserAdministration();
            userAdministration.setId(291L);
            userAdministration.setLoginAccount("zjtest02");
            mapper.updateByPrimaryKeySelective(userAdministration);
            session.commit();

        }catch (Exception e){
            session.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
    }*/

    public void cascadeParentAndChildPriceOrder(List<PriceOrderDetail> priceOrderDetails,PriceOrder prd){

        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(prd.getId());
        Long parentOrderId = priceOrder.getParentOrderId();

        List<PriceOrderDetail> priceOrderDetailList = new ArrayList<>();
        /**
         * 如果引用了基础价格清单,基础价格清单的所有价格和税率需要更改
         */
        if(null != parentOrderId && 0 != parentOrderId) {
            PriceOrder basePriceOrder = priceOrderMapper.selectByPrimaryKey(parentOrderId);

            List<PriceOrderDetail> prds = priceOrderDetailMapper.selectByPriceOrderId(basePriceOrder.getId());

            for(PriceOrderDetail cascadePod : prds){
                for(PriceOrderDetail nowPod: priceOrderDetails){
                    if(nowPod.getGoodsId().equals(cascadePod.getGoodsId())){

                        PriceOrderDetail newPriceOrderDetail = getNewPriceOrderDetail(nowPod, cascadePod.getId());
                        priceOrderDetailList.add(newPriceOrderDetail);

                        /*priceOrderDetailMapper.updateByPrimaryKeySelective(newPriceOrderDetail);*/
                    }
                }
            }

        }

        List<PriceOrder> childPriceOrders = priceOrderMapper.selectByParentOrderId(priceOrder.getId());

        for(PriceOrder childPod : childPriceOrders){


            List<PriceOrderDetail> childPodds = priceOrderDetailMapper.selectByPriceOrderId(childPod.getId());

            for(PriceOrderDetail nowPod: priceOrderDetails){
                for(PriceOrderDetail childPodd : childPodds){
                    if(nowPod.getGoodsId().equals(childPodd.getGoodsId())){

                        PriceOrderDetail newPriceOrderDetail = getNewPriceOrderDetail(nowPod, childPodd.getId());

                        priceOrderDetailList.add(newPriceOrderDetail);
                        /*priceOrderDetailMapper.updateByPrimaryKeySelective(newPriceOrderDetail);*/
                    }
                }

            }

        }

        // 新获取一个模式为BATCH，自动提交为false的session
        // 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        try {
            PriceOrderDetailMapper mapper = session.getMapper(PriceOrderDetailMapper.class);
            int i = 0 ;

            for(PriceOrderDetail priceOrderDetail : priceOrderDetailList){

                mapper.updateByPrimaryKeySelective(priceOrderDetail);

                i++;
                if(i % 1000 == 0) {
                    session.commit();
                    session.clearCache();
                }

            }
            session.commit();
        }catch (Exception e){
            session.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

    }


    public PriceOrderDetail getNewPriceOrderDetail(PriceOrderDetail nowPod,Long cascadePodId){

        PriceOrderDetail cascadePod = new PriceOrderDetail();

        cascadePod.setId(cascadePodId);
        /**
         * 配货价格
         */
        cascadePod.setDistrPrice(nowPod.getDistrPrice());

        /**
         * 配货税率
         */
        cascadePod.setDistrTaxRate(nowPod.getDistrTaxRate());
        cascadePod.setDistrTaxRateId(nowPod.getDistrTaxRateId());

        /**
         * 不含税配货单价
         */
        cascadePod.setNotaxDistrPrice(nowPod.getNotaxDistrPrice());

        /**
         * 零售单价
         */
        cascadePod.setRetailPrice(nowPod.getRetailPrice());

        /**
         * 会员单价
         */
        cascadePod.setMemberPrice(nowPod.getMemberPrice());

        /**
         * 销项税
         */
        cascadePod.setSaleTaxRate(nowPod.getSaleTaxRate());
        cascadePod.setSaleTaxRateId(nowPod.getSaleTaxRateId());

        /**
         * 不含税零售单价
         */
        cascadePod.setNotaxRetailPrice(nowPod.getNotaxRetailPrice());

        /**
         * 不含税会员单价
         */
        cascadePod.setNotaxMemberPrice(nowPod.getNotaxMemberPrice());

        return cascadePod;
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateSystemPriceOrder2Goods(List<GoodsBusiness> goodsBusinesses, List<PriceOrderDetail> priceOrderDetails,PriceOrder prd){

        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(prd.getId());

        /**
         * 0-自定义价格清单；1-系统价格清单
         * 如果修改的是系统价格清单需要修改商品价格,税率
         */
        if(1 != priceOrder.getSysType()){

            Long parentOrderId = priceOrder.getParentOrderId();

            if(null != parentOrderId && 0 != parentOrderId){
                PriceOrder basePriceOrder = priceOrderMapper.selectByPrimaryKey(parentOrderId);

                /**
                 * 如果引用的基础价格清单 为基础价格清单也需要同步修改商品的价格
                 */
                if(basePriceOrder != null && basePriceOrder.getSysType() != null && 1 != basePriceOrder.getSysType()){

                    return;
                }
            }else {
                return;
            }

        }

        // 新获取一个模式为BATCH，自动提交为false的session
        // 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        try {
            GoodsBusinessMapper mapper = session.getMapper(GoodsBusinessMapper.class);
            int i = 0 ;
            for(PriceOrderDetail pd : priceOrderDetails){

                for(GoodsBusiness go : goodsBusinesses){
                    if(pd.getGoodsId().equals(go.getGoodsId())){
                        GoodsBusiness newGoodsBusiness = new GoodsBusiness();
                        newGoodsBusiness.setId(go.getId());

                        /**
                         * 销售税（%）
                         */
                        newGoodsBusiness.setSaleTaxRate(pd.getSaleTaxRate());
                        newGoodsBusiness.setSaleTaxRateId(pd.getSaleTaxRateId());
                        /**
                         * 零售基价
                         */
                        newGoodsBusiness.setRetailPrice(pd.getRetailPrice());

                        /**
                         * 会员基价
                         */
                        newGoodsBusiness.setMemberPrice(pd.getMemberPrice());

                        /**
                         * 配货基价
                         */
                        newGoodsBusiness.setDistrPrice(pd.getDistrPrice());

                        /**
                         *  配货税率（%）
                         */
                        newGoodsBusiness.setDistrTaxRate(pd.getDistrTaxRate());
                        newGoodsBusiness.setDistrTaxRateId(pd.getDistrTaxRateId());

                        mapper.updateByPrimaryKeySelective(newGoodsBusiness);

                        i++;
                        if(i % 1000 == 0) {
                            session.commit();
                            session.clearCache();
                        }
                    }
                }

            }
            session.commit();
        }catch (Exception e){
            session.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }



    }

    /**
     * ,PriceOrder newPriceOrder ,PriceOrder oldPriceOrder
     * @param loginUser
     * @param goods
     * @param newPriceOrderDetails
     * @param oldPriceOrderDetails
     * @param updateDate
     * @return
     * @throws Exception
     */
    public List<PriceModifyRecordWithBLOBs> getPriceModifyRecordWithBLOBs(UserVO loginUser
        ,List<Goods> goods, List<PriceOrderDetail> newPriceOrderDetails
        ,List<PriceOrderDetail> oldPriceOrderDetails,Date updateDate) throws Exception {

        List<PriceModifyRecordWithBLOBs> priceModifyRecordWithBLOBs = new ArrayList<>();

        for(PriceOrderDetail oldPriceOrderDetail : oldPriceOrderDetails){
            for(PriceOrderDetail newPriceOrderDetail : newPriceOrderDetails) {
                if(oldPriceOrderDetail.getId().equals(newPriceOrderDetail.getId())){
                    for(Goods g : goods){
                        if(g.getId().equals(newPriceOrderDetail.getGoodsId())){
                            Map<String, Object> oldPriceOrderDetailMap = modifyRecordCompoent.getFieldsMap(oldPriceOrderDetail);
                            Map<String, Object> newPriceOrderDetailMap = modifyRecordCompoent.getFieldsMap(newPriceOrderDetail);
                            List<PriceModifyRecordWithBLOBs> modifyPriceOrderDetail = getModifyRecordList(loginUser, "saas_price_order_detail", g, updateDate, oldPriceOrderDetailMap, newPriceOrderDetailMap, fieldMustMap());
                            priceModifyRecordWithBLOBs.addAll(modifyPriceOrderDetail);
                        }
                    }

                }

            }
        }

        return priceModifyRecordWithBLOBs;
    }

    private List<PriceModifyRecordWithBLOBs> getModifyRecordList(
            UserVO userVO ,String tableName,Goods goods,Date updateTime
            , Map<String,Object> valMap
            ,Map<String,Object> newMap ,Map<String,String> fieldMustMap){

        List<PriceModifyRecordWithBLOBs> modifyRecordWithBLOBs = new ArrayList<>();

        for(Map.Entry<String,String> entry : fieldMustMap.entrySet()){
            Object obj = valMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if(null != obj && null != newObj){
                BigDecimal oldBigDecimal = new BigDecimal(0);
                BigDecimal newBigDecimal = new BigDecimal(0);
                boolean isUpdate = false;
                if(obj instanceof BigDecimal && newObj instanceof BigDecimal){
                    oldBigDecimal = (BigDecimal) obj;
                    newBigDecimal = (BigDecimal) newObj;
                    if(oldBigDecimal.compareTo(newBigDecimal) != 0){
                        isUpdate = true;
                    }
                }else {
                    if(!obj.equals(newObj))
                        isUpdate = true;
                }


                if(isUpdate){
                    PriceModifyRecordWithBLOBs priceModify = new PriceModifyRecordWithBLOBs();
                    priceModify.setEnterpriseId(userVO.getEnterpriseId());
                    if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
                        priceModify.setParentId(0L);
                    }else {
                        priceModify.setParentId(userVO.getParentId());
                    }
                    priceModify.setTableName(tableName);
                    priceModify.setGoodsId(goods.getId());
                    priceModify.setGoodsCode(goods.getCode());
                    priceModify.setGoodsName(goods.getGenericName());
                    priceModify.setColumnEnName(entry.getKey());
                    priceModify.setColumnChName(entry.getValue());
                    priceModify.setUpdateTime(updateTime);
                    priceModify.setCreaterId(userVO.getUserId());
                    priceModify.setCreaterCode(userVO.getUserCode());
                    priceModify.setCreaterName(userVO.getUserName());
                    priceModify.setCreateTime(new Date());
                    priceModify.setUpdateTime(new Date());
                    priceModify.setOldContent(obj.toString());
                    priceModify.setNewContent(newObj.toString());
                    /**
                     * 价格清单 去掉了修改功能只能通过导入和价格调整来修改价格清单,价格调整不需要有修改记录,本身价格调整的单据就具有修改记录功能
                     */
                    priceModify.setRemark("导入");
                    modifyRecordWithBLOBs.add(priceModify);
                }

            }
        }

        return modifyRecordWithBLOBs;
    }


    private Map<String,String> fieldMustMap(){
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("parentOrderId","基础价格清单ID");
        fieldNames.put("status","状态");
        fieldNames.put("distrPrice","配货价格");
        fieldNames.put("distrTaxRate","配货税率");
        fieldNames.put("notaxDistrPrice","不含税配货单价");
        fieldNames.put("retailPrice","零售单价");
        fieldNames.put("memberPrice","会员单价");
        fieldNames.put("saleTaxRate","销项税");
        fieldNames.put("notaxRetailPrice","不含税零售单价");
        fieldNames.put("notaxMemberPrice","不含税会员单价");


        return fieldNames;
    }


    /**
     * 新增价格清单明细信息
     * @param userVO
     * @param isAdd
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePriceOrderDetail(UserVO userVO, PriceOrder priceOrder, boolean isAdd) throws Exception {
        Map<String,Object> map = new HashMap<>();
        supplierComponent.setParamMap(userVO,map);
        List<GoodsBusiness> goodsBusinesses = goodsBusinessMapper.selectByEnterpriseId(map);

        Set<Long> taxRateIds = GoodsBusiness.getTaxRateIds(goodsBusinesses);
        List<GoodsTaxRate> goodsTaxRates = new ArrayList<>();
        if(!CollectionUtils.isEmpty(taxRateIds)) {
            goodsTaxRates = goodsComponent.getGoodsTaxRates(taxRateIds);
        }

        Map<Long,GoodsTaxRate> goodsTaxRateMap = new HashMap<>();
        for(GoodsTaxRate goodsTaxRate : goodsTaxRates){
            goodsTaxRateMap.put(goodsTaxRate.getId(),goodsTaxRate);
        }

        List<PriceOrderDetail> orderDetails = new ArrayList<>();

        for (GoodsBusiness goodsBusiness : goodsBusinesses) {

                UpdatePriceOrderDetailVO priceOrderDetailVO = new UpdatePriceOrderDetailVO();
                priceOrderDetailVO.setDistrPrice(goodsBusiness.getDistrPrice());

                priceOrderDetailVO.setRetailPrice(goodsBusiness.getRetailPrice());
                priceOrderDetailVO.setMemberPrice(goodsBusiness.getMemberPrice());

                priceOrderDetailVO.setGoodsId(goodsBusiness.getGoodsId());
                priceOrderDetailVO.setDistrTaxRateId(goodsBusiness.getDistrTaxRateId());
                priceOrderDetailVO.setSaleTaxRateId(goodsBusiness.getSaleTaxRateId());
                priceOrderDetailVO.setPriceOrderId(priceOrder.getId());
                PriceOrderDetail priceOrderDetail = PriceOrderDetail.getPriceOrderDetail4VO(userVO,priceOrder.getStatus(),priceOrderDetailVO,goodsTaxRateMap,isAdd);

               /* priceOrderDetail.setPurTaxRateId(goodsBusiness.getPurchaseTaxRateId());
                priceOrderDetail.setPurTaxRate(goodsBusiness.getPurchaseTaxRate());*/
                orderDetails.add(priceOrderDetail);

        }

        for(PriceOrderDetail p :  orderDetails){
            priceOrderDetailMapper.insertSelective(p);
        }

    }





    /**
     * 初始化修改时 不同价格类型 可以被显示和修改的字段集合
     * @return
     */
    @Override
    public PriceOrderInitVO init(){

        PriceOrderInitVO priceOrderInitVO = new PriceOrderInitVO();

        /**
         * 配货不可显示和不可修改的字段集合
         */
        List<String> notShowAndUpdateDisParam = new ArrayList<>();

        notShowAndUpdateDisParam.add("retailPrice");
        notShowAndUpdateDisParam.add("memberPrice");
        notShowAndUpdateDisParam.add("saleTaxRate");
        notShowAndUpdateDisParam.add("notaxRetailPrice");
        notShowAndUpdateDisParam.add("notaxMemberPrice");
        priceOrderInitVO.setNotShowAndUpdateDisParam(notShowAndUpdateDisParam);

        /**
         * 零售不可显示和不可修改的字段集合
         */
        List<String> notShowAndUpdateRetailParam = new ArrayList<>();
        notShowAndUpdateRetailParam.add("distrPrice");
        notShowAndUpdateRetailParam.add("distrTaxRate");
        notShowAndUpdateRetailParam.add("notaxDistrPrice");
        priceOrderInitVO.setNotShowAndUpdateRetailParam(notShowAndUpdateRetailParam);

        return priceOrderInitVO;
    }


    /**
     * 查询价格清单列表
     * @param userVO
     * @param queryType 查询类型 2 是全部 0:自定义 1:系统默认
     * @return
     */
    @Override
    public List<PriceOrderReturnVO> queryPriceOrders(UserVO userVO , Integer queryType,Page page,String order,String sort) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("queryType",queryType);
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("parentId",userVO.getParentId());

        if(!StringUtils.isEmpty(order) && !StringUtils.isEmpty(sort)){
            map.put("order",order);
            map.put("sort",sort);
        }


        List<Long> list = new ArrayList<>();
        List<Enterprise> enterpriseList = enterpriseMapper.selectChildrenByPrimaryKey(userVO.getEnterpriseId());
        enterpriseList.stream().forEach(item->{
            list.add(item.getId());
        });
        map.put("list",list);
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
        List<PriceOrder> priceOrders = priceOrderMapper.selectByQueryType(map);

        List<Long> pIds = PriceOrder.getPriceOrderPIds(priceOrders);

        List<PriceOrderReturnVO> priceOrderReturnVOS = new ArrayList<>();

        if(!CollectionUtils.isEmpty(pIds)) {
            List<PriceOrder> parentPriceOrders = priceOrderMapper.selectByIds(pIds);
            priceOrderReturnVOS = PriceOrderReturnVO.getPriceOrders(userVO,parentPriceOrders,priceOrders);
        }else {
            priceOrderReturnVOS = PriceOrderReturnVO.getPriceOrders(userVO,null,priceOrders);
        }

        //价格清单属于总部控制的企业id集合
        List<Long> withControlIdList = priceOrderMapper.getEnterpriseIdsWithControl(userVO.getEnterpriseId());
        withControlIdList.add(userVO.getEnterpriseId());
        for (PriceOrderReturnVO priceOrder: priceOrderReturnVOS) {

            if (!canDelete(priceOrder,withControlIdList)){
                priceOrder.setDeleteFlag(Boolean.FALSE);
            }

            if(!canUpdate(priceOrder,withControlIdList)){
                priceOrder.setUpdateFlag(false);
            }
        }

        page.setResult(priceOrderReturnVOS);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
        page.setTotalPage(hPage.getPages());

        return priceOrderReturnVOS;

    }

    /**
     * 查询价格清单明细表
     * @param priceOrderId
     * @return
     */
    @Override
    public Page<PriceOrderReturnVO> queryPriceOrderDetails(Long priceOrderId,UserVO userVO,Page<PriceOrderReturnVO> page) throws Exception {

        List<PriceOrderDetail> details = priceOrderDetailMapper.selectByPriceOrderIdPage(priceOrderId,null,page.getStart(),page.getPageSize());
        Integer size = priceOrderDetailMapper.selectByPriceOrderIdCount(priceOrderId);
        if(CollectionUtils.isEmpty(details)) {
            page.setTotalRecord(0);
            page.setResult(new PriceOrderReturnVO());
            return page;
        }


        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(priceOrderId);

        PriceOrder parentOrder = new PriceOrder();
        if(null != priceOrder.getParentOrderId()){

            parentOrder = priceOrderMapper.selectByPrimaryKey(priceOrderId);
        }

        if(null == priceOrder){
            page.setTotalRecord(0);
            page.setResult(new PriceOrderReturnVO());
            return page;
        }

        Set<Long> gooodsIds = PriceOrderDetail.getGoodsIds(details);
        List<Goods> goodsList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(gooodsIds)) {
            List<Long> cs = new ArrayList(gooodsIds);

            Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
            goodsList = goodsMapper.selectByIds(cs,headEnterpriseId);
        }

        List<PriceOrderDetailReturnVO> priceOrderDetails = PriceOrderDetailReturnVO.getPriceOrderDetailReturnVOs4Entitys(goodsList,details, userVO);

        PriceOrderReturnVO priceOrderReturnVO = PriceOrderReturnVO.getPriceOrder(userVO, parentOrder, priceOrder);



        //设置是否可以修改
        /**
         * 自主控制的总部点击查看详情页面不显示修改按钮
         */

        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(priceOrder.getEnterpriseId());
        if(enterpriseBusiness.getPriceManage() == null || (enterpriseBusiness.getPriceManage() != null && enterpriseBusiness.getPriceManage() == 1)){
            priceOrderReturnVO.setUpdateFlag(false);
        }

        priceOrderReturnVO.setPriceOrderDetailReturnVOS(priceOrderDetails);

        page.setResult(priceOrderReturnVO);
        page.setTotalRecord(size);

        return page;
    }


    /**
     * 查询价格清单明细表
     * @param priceOrderId
     * @return
     */
    public PriceOrderReturnVO queryPriceOrderDetails(Long priceOrderId,UserVO userVO,int sign) throws Exception {

        List<PriceOrderDetail> details = priceOrderDetailMapper.selectByPriceOrderIdPage(priceOrderId,null,null,null);

        if(CollectionUtils.isEmpty(details)) {
            return new PriceOrderReturnVO();
        }

        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(priceOrderId);

        PriceOrder parentOrder = new PriceOrder();
        if(null != priceOrder.getParentOrderId()){

            parentOrder = priceOrderMapper.selectByPrimaryKey(priceOrderId);
        }

        if(null == priceOrder){
            return new PriceOrderReturnVO();
        }

        Set<Long> gooodsIds = PriceOrderDetail.getGoodsIds(details);
        List<Goods> goodsList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(gooodsIds)) {
            List<Long> cs = new ArrayList(gooodsIds);

            Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
//            if(sign == 1){
//                //导出模板
//                goodsList = goodsMapper.selectByIdsAndOwnerId(cs,headEnterpriseId);
//            } else {
//                goodsList = goodsMapper.selectByIds(cs,headEnterpriseId);
//            }

            goodsList = goodsMapper.selectByIds(cs,headEnterpriseId);

        }

        List<PriceOrderDetailReturnVO> priceOrderDetails = PriceOrderDetailReturnVO.getPriceOrderDetailReturnVOs4Entitys(goodsList,details, userVO);

        PriceOrderReturnVO priceOrderReturnVO = PriceOrderReturnVO.getPriceOrder(userVO, parentOrder, priceOrder);

        priceOrderReturnVO.setPriceOrderDetailReturnVOS(priceOrderDetails);
        return priceOrderReturnVO;
    }



    /**
     * 查询价格清单明细表
     * @param priceOrderId
     * @return
     */
    @Override
    public void queryPriceOrderDetails4Param(Long priceOrderId, String param, UserVO userVO, Page<List<PriceOrderDetailReturnVO>> page){

        List<PriceOrderDetail> details = priceOrderDetailMapper.selectByPriceOrderIdAndParam(priceOrderId,param,page.getStart(),page.getPageSize());

       Integer size = priceOrderDetailMapper.selectByPriceOrderIdAndParamCount(priceOrderId,param);

        Set<Long> gooodsIds = PriceOrderDetail.getGoodsIds(details);
        List<Goods> goodsList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(gooodsIds)) {
            List<Long> cs = new ArrayList(gooodsIds);

            Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
            goodsList = goodsMapper.selectByIds(cs,headEnterpriseId);
        }

        List<PriceOrderDetailReturnVO> priceOrderDetails = PriceOrderDetailReturnVO.getPriceOrderDetailReturnVOs4Entitys(goodsList,details, userVO);
        page.setTotalRecord(size);
        page.setResult(priceOrderDetails);


    }

    private boolean canDelete(PriceOrderReturnVO priceOrder,List<Long> withControlIdList) {

        if(priceOrder.getSysType() == SysType.SYSTEM.getCode()) return  false;

        List<EnterpriseBusiness> businessList = priceOrderMapper.selectEnterpriseByOrderPriceId(priceOrder.getId());

        if(businessList.size() > 0) return false;

        if(!withControlIdList.contains(priceOrder.getEnterpriseId())) return false;

        return  true;
    }
    private boolean canUpdate(PriceOrderReturnVO priceOrder,List<Long> withControlIdList) {

        if(!withControlIdList.contains(priceOrder.getEnterpriseId())) return false;

        return  true;
    }

    /**
     * 删除价格清单及其子明细
     * @param priceOrderId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePriceOrder(Long priceOrderId){

        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(priceOrderId);

        if(priceOrder.getSysType().equals(SysType.SYSTEM.getCode())) {
            throw  new BusinessException("系统默认价格清单，不允许删除");
        }

        List<EnterpriseBusiness> businessList = priceOrderMapper.selectEnterpriseByOrderPriceId(priceOrder.getId());

        if(businessList.size() > 0) {
            throw  new BusinessException("企业存在关联数据，不允许删除");
        }

        priceOrderDetailMapper.deleteByPriceOrderId(priceOrderId);

        priceOrderMapper.deleteByPrimaryKey(priceOrderId);
    }

    @Override
    public List<QueryBean> queryPriceOrderIdAndNameList(Long eId,Integer type,Long id){

        List<Integer> types = null;
        if(null != type){
            types = new ArrayList<>();
            types.add(PriceType.BASE_PRICE.getCode());
            types.add(type);
        }


        List<QueryBean> selectBeans = priceOrderMapper.selectStorePriceOrderIdAndNames(eId,types);

        if(null != id){

            Iterator<QueryBean> iterator = selectBeans.iterator();
            while (iterator.hasNext()){
                QueryBean next = iterator.next();

                if(next.getId().equals(id)){
                    iterator.remove();
                }
            }
        }

        return selectBeans;
    }


    @Override
    public List<PriceModifyRecordWithBLOBs> getPriceModifyRecordWithBLOBs(Map<String, Object> map, Page page){
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
        List<PriceModifyRecordWithBLOBs> priceModifyRecordWithBLOBs = priceModifyRecordMapper.selectByParam(map);
        page.setResult(priceModifyRecordWithBLOBs);
        page.setTotalRecord(Integer.parseInt(String.valueOf(hPage.getTotal())));
        page.setTotalPage(hPage.getPages());
        return priceModifyRecordWithBLOBs;
    }

    @Override
    public void queryPriceOrderDetails2SelectOrderDetails(Long oldPriceOrderId, Long newPriceOrderId,UserVO userVO,Page<List<PriceOrderDetailReturnVO>> page){

        List<PriceOrderDetail> oldPriceOrderDetails = priceOrderDetailMapper.selectByPriceOrderIdPage(oldPriceOrderId,null,page.getStart(),page.getPageSize());
        Integer size = priceOrderDetailMapper.selectByPriceOrderIdCount(oldPriceOrderId);
        List<Long> goodsIdList = new ArrayList<>();
        oldPriceOrderDetails.stream().forEach(item -> {
            goodsIdList.add(item.getGoodsId());
        });

        List<PriceOrderDetail> newPriceOrderDetails = priceOrderDetailMapper.selectByPriceOrderIdPage(newPriceOrderId,goodsIdList,page.getStart(),page.getPageSize());

        oldPriceOrderDetails = new2OldPriceOrderPriceAndTaxRate(oldPriceOrderDetails,newPriceOrderDetails);

        Set<Long> gooodsIds = PriceOrderDetail.getGoodsIds(oldPriceOrderDetails);
        List<Goods> goodsList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(gooodsIds)) {
            List<Long> cs = new ArrayList(gooodsIds);

            Long enterpriseId = userVO.getEnterpriseId();
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
            if(!enterprise.getChainType().equals(ChainType.Headquarters.getCode())){
                enterpriseId = enterprise.getParentId();
            }

            goodsList = goodsMapper.selectByIds(cs,enterpriseId);
        }

        List<PriceOrderDetailReturnVO> priceOrderDetails = PriceOrderDetailReturnVO.getPriceOrderDetailReturnVOs4Entitys(goodsList,oldPriceOrderDetails,userVO);

        page.setResult(priceOrderDetails);
        page.setTotalRecord(size);

    }

    private List<PriceOrderDetail> new2OldPriceOrderPriceAndTaxRate(List<PriceOrderDetail> oldPriceOrderDetails,List<PriceOrderDetail> newPriceOrderDetails){

        for(PriceOrderDetail oldPo : oldPriceOrderDetails){
            for(PriceOrderDetail newPo : newPriceOrderDetails){
                if(oldPo.getGoodsId().equals(newPo.getGoodsId())){

                    /**
                     * 配货价格
                     */
                    oldPo.setDistrPrice(newPo.getDistrPrice());

                    /**
                     * 配货税率
                     */
                    oldPo.setDistrTaxRate(newPo.getDistrTaxRate());
                    oldPo.setDistrTaxRateId(newPo.getDistrTaxRateId());

                    /**
                     * 不含税配货单价
                     */
                    oldPo.setNotaxDistrPrice(newPo.getNotaxDistrPrice());

                    /**
                     * 零售单价
                     *
                     */
                    oldPo.setRetailPrice(newPo.getRetailPrice());

                    /**
                     * 会员单价
                     */
                    oldPo.setMemberPrice(newPo.getMemberPrice());

                    /**
                     * 销项税
                     */
                    oldPo.setSaleTaxRate(newPo.getSaleTaxRate());
                    oldPo.setSaleTaxRateId(newPo.getSaleTaxRateId());

                    /**
                     * 不含税零售单价
                     */
                    oldPo.setNotaxRetailPrice(newPo.getNotaxRetailPrice());

                    /**
                     * 不含税会员单价
                     */
                    oldPo.setNotaxMemberPrice(newPo.getNotaxMemberPrice());

                }

            }

        }


        return oldPriceOrderDetails;
    }

	@Override
	public List<PriceOrderDetailReturnVO> queryPriceOrderDetailsSetUpdateFlag(UserVO userVO,
			List<PriceOrderDetailReturnVO> details) {
		Integer priceManage=priceOrderMapper.getEnterpriseBusinessPriceManage(userVO.getEnterpriseId());
		if(priceManage==null) {
			  throw  new BusinessException("门店不存在价格清单数据");
		}
		//价格管理（0-总部控制；1-自主控制）,若是0则设置为false不可更改
		if(priceManage.equals(0)) {
			for(PriceOrderDetailReturnVO d:details) {
				d.setUpdateFlag(false);
			}
		}
		return details;
	}

	@Override
	public Page<PriceOrderReturnVO> getPriceOrderDetail(UserVO userVO,Page<PriceOrderReturnVO> page) throws Exception {
		PriceOrderReturnVO priceOrderReturnVO = priceOrderMapper.getStorePriceOrderByEnterpriseId(userVO.getEnterpriseId());
		if(priceOrderReturnVO == null) throw  new BusinessException("不存在价格清单数据");
		//状态（0-禁用；1-启用）
		if(priceOrderReturnVO.getStatus()==0) {
			priceOrderReturnVO.setStatusDesc("禁用");
		}else {
			priceOrderReturnVO.setStatusDesc("启用");
		}
		//设置基础价格清单
		priceOrderReturnVO.setParentOrderName("无");
        Page<PriceOrderReturnVO> priceOrderReturnVOPage = queryPriceOrderDetails(priceOrderReturnVO.getId(), userVO,page);
        List<PriceOrderDetailReturnVO> priceOrderDetailReturnVOS = priceOrderReturnVOPage.getResult().getPriceOrderDetailReturnVOS();
        priceOrderReturnVO.setUpdateFlag(false);
		priceOrderReturnVO.setDeleteFlag(false);
		if(priceOrderDetailReturnVOS !=null && !priceOrderDetailReturnVOS.isEmpty()) {
			//获取门店价格管理状态
			Integer priceManage=priceOrderMapper.getEnterpriseBusinessPriceManage(userVO.getEnterpriseId());
			//若是加盟店
			if(userVO.getChainType().equals(2)) {
				priceOrderReturnVO.setUpdateFlag(true);
				//价格管理（0-总部控制；1-自主控制），自己的商品可以修改 
				if(priceManage.equals(0)) {
					List<Long> goodsIds=new ArrayList<>(priceOrderDetailReturnVOS.size());
					for(PriceOrderDetailReturnVO vo:priceOrderDetailReturnVOS) {
						goodsIds.add(vo.getGoodsId());
					}
					List<Map<String,Object>> goodsPriMags=priceOrderMapper.getGoodsBusPriceManage(goodsIds);
					for(PriceOrderDetailReturnVO vo:priceOrderDetailReturnVOS) {
						Long gId=vo.getGoodsId();
						for(Map<String,Object> map:goodsPriMags) {
							Long goodsId=Long.parseLong(map.get("goodsId").toString());
							if(gId.equals(goodsId)) {
								//
								Long enterpriseId=Long.parseLong(map.get("ownerId").toString());
								if(enterpriseId.equals(userVO.getEnterpriseId())) vo.setUpdateFlag(true);
								break;
							}
						}
					}
				}else {
					//自主控制所有商品的价格都可以修改
					for(PriceOrderDetailReturnVO vo:priceOrderDetailReturnVOS) {
						vo.setUpdateFlag(true);
					}
				}
			}else {//自营店
				//价格管理（0-总部控制；1-自主控制）
				if(priceManage.equals(0)) {
					//若是0则设置为false不可更改
					priceOrderReturnVO.setUpdateFlag(false);
				}else {
					//若是1则设置为true可更改
					priceOrderReturnVO.setUpdateFlag(true);
					//若是自营店  判断商品信息销售定价price_manage,0总部统一定价,1允许门店自主定价
					List<Long> goodsIds=new ArrayList<>(priceOrderDetailReturnVOS.size());
					for(PriceOrderDetailReturnVO vo:priceOrderDetailReturnVOS) {
						goodsIds.add(vo.getGoodsId());
					}
					List<Map<String,Object>> goodsPriMags=priceOrderMapper.getGoodsBusPriceManage(goodsIds);
					for(PriceOrderDetailReturnVO vo:priceOrderDetailReturnVOS) {
						Long gId=vo.getGoodsId();
						for(Map<String,Object> map:goodsPriMags) {
							Long goodsId=Long.parseLong(map.get("goodsId").toString());
							if(gId.equals(goodsId)) {
								Integer pricingPolicy=Integer.parseInt(map.get("pricingPolicy").toString());
								if(pricingPolicy.equals(1)) vo.setUpdateFlag(true);
								break;
							}
						}
					}
				}
			}
		}
		priceOrderReturnVO.setPriceOrderDetailReturnVOS(priceOrderDetailReturnVOS);
		page.setTotalRecord(priceOrderReturnVOPage.getTotalRecord());
		page.setResult(priceOrderReturnVO);
		return page;
	}

	/**
	 * 若是门店，验证是否有修改价格清单的权限
	 * @param addOrUpdatePriceOrderVO
	 */
	private Map<Long,Boolean> validStorePower(UserVO userVO,AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO) {

        Map<Long,Boolean> goodsIdMap = new HashMap<>();
		List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS=addOrUpdatePriceOrderVO.getUpdatePriceOrderDetailVOS();
		if(updatePriceOrderDetailVOS !=null && !updatePriceOrderDetailVOS.isEmpty()) {
			//获取门店价格管理状态（0-总部控制；1-自主控制）
			Integer priceManage=priceOrderMapper.getEnterpriseBusinessPriceManage(userVO.getEnterpriseId());
			if(priceManage == null) throw  new BusinessException("门店不存在价格清单数据");
			List<Long> goodsIds=new ArrayList<>(updatePriceOrderDetailVOS.size());
			for(UpdatePriceOrderDetailVO vo:updatePriceOrderDetailVOS) {
				goodsIds.add(vo.getGoodsId());
			}
			//若是加盟店
			if(userVO.getChainType().equals(2)) {
				if(priceManage.equals(0)) {
					List<Map<String,Object>> goodsPriMags=priceOrderMapper.getGoodsBusPriceManage(goodsIds);
					for(Map<String,Object> map:goodsPriMags) {
						Long enterpriseId=Long.parseLong(map.get("ownerId").toString());
						//若不是加盟店自己的商品则无权修改
						if(!enterpriseId.equals(userVO.getEnterpriseId())){
                            Long goodsId=Long.parseLong(map.get("goodsId").toString());
						    goodsIdMap.put(goodsId,true);
						}
					}
				}
			}else {//若是自营店
				if(priceManage.equals(0)) throw  new BusinessException("无操作权限");
				List<Map<String,Object>> goodsPriMags=priceOrderMapper.getGoodsBusPriceManage(goodsIds);
				for(Map<String,Object> map:goodsPriMags) {
					if(Integer.parseInt(map.get("pricingPolicy").toString()) == 0) {
                        Long goodsId=Long.parseLong(map.get("goodsId").toString());
                        goodsIdMap.put(goodsId, true);
                    }
				}
			}
		}

		return goodsIdMap;
	}
	
	private Map<Long,Boolean> validHeadPower(UserVO userVO,AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO) {
        Map<Long,Boolean> goodsIdMap = new HashMap<>();

		List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS=addOrUpdatePriceOrderVO.getUpdatePriceOrderDetailVOS();
		if(updatePriceOrderDetailVOS !=null && !updatePriceOrderDetailVOS.isEmpty()) {
			List<Long> goodsIds=new ArrayList<>(updatePriceOrderDetailVOS.size());
			for(UpdatePriceOrderDetailVO vo:updatePriceOrderDetailVOS) {
				goodsIds.add(vo.getGoodsId());
			}
            List<Map<String,Object>> goodsPriMags=priceOrderMapper.getGoodsBusPriceManage(goodsIds);
            for(Map<String,Object> map:goodsPriMags) {
                Long enterpriseId=Long.parseLong(map.get("ownerId").toString());
                //若不是总部自己的商品则无权修改
                if(!enterpriseId.equals(userVO.getEnterpriseId())) {
                    Long goodsId=Long.parseLong(map.get("goodsId").toString());
                    goodsIdMap.put(goodsId, true);
                }
            }
		}
		return goodsIdMap;
	}

	@Override
	public PriceOrderReturnVO excelExportDate(UserVO userVO, Long id,Integer sign) throws Exception {
		PriceOrderReturnVO priceOrderReturnVO=priceOrderMapper.getPriceOrderById(id);
		List<PriceOrderDetailReturnVO> priceOrderDetailReturnVOS = null;
		if(priceOrderReturnVO == null) { 
			priceOrderReturnVO = new PriceOrderReturnVO();
		
			priceOrderReturnVO.setName("");
			priceOrderReturnVO.setCode("");
			priceOrderReturnVO.setStatusDesc("");
			priceOrderReturnVO.setParentOrderName("");
			priceOrderReturnVO.setEnterpriseName(userVO.getEnterpriseName());
			priceOrderDetailReturnVOS = new ArrayList<>();
			priceOrderReturnVO.setPriceOrderDetailReturnVOS(priceOrderDetailReturnVOS);
		}else {
//            PriceOrderReturnVO priceOrderReturnVO1 = queryPriceOrderDetails(priceOrderReturnVO.getId(), userVO,null);
            PriceOrderReturnVO priceOrderReturnVO1 = queryPriceOrderDetails(priceOrderReturnVO.getId(), userVO,sign);
            priceOrderDetailReturnVOS = priceOrderReturnVO1.getPriceOrderDetailReturnVOS();
			//状态（0-禁用；1-启用）
			if(priceOrderReturnVO.getStatus()==0) {
				priceOrderReturnVO.setStatusDesc("禁用");
			}else {
				priceOrderReturnVO.setStatusDesc("启用");
			}
			if(priceOrderReturnVO.getParentOrderName() == null) priceOrderReturnVO.setParentOrderName("");
			Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(priceOrderReturnVO.getEnterpriseId());
			priceOrderReturnVO.setEnterpriseName(enterprise.getName());
			priceOrderReturnVO.setPriceOrderDetailReturnVOS(priceOrderDetailReturnVOS);
			for(PriceOrderDetailReturnVO vo : priceOrderDetailReturnVOS ) {
				vo.setDistrTaxRateStr(OpeningTaxDetailVO.getTaxRateWithOutZero(vo.getDistrTaxRate()));
				vo.setSaleTaxRateStr(OpeningTaxDetailVO.getTaxRateWithOutZero(vo.getSaleTaxRate()));
			}
		}
		return priceOrderReturnVO;
	}

	@Override
	public void excelExport(OutputStream output, UserVO userVO, Long id, PriceOrderReturnVO priceOrderReturnVO,Integer sign) {
		
		LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();

		rowHeaderList.put("goodsCode", "商品编码");
        rowHeaderList.put("goodsOldCode", "原商品编码");
        rowHeaderList.put("goodsName", "通用名称");
		rowHeaderList.put("dosageName", "剂型");
		rowHeaderList.put("specification", "规格");	
		rowHeaderList.put("manufacturer", "生产厂商");
		rowHeaderList.put("unitName", "单位");
		
		Integer priceType = priceOrderReturnVO.getPriceType();
		//价格类型（0-基础价格；1-配货价格；2-零售价格）
		if(priceType == null || priceType == 0 || priceType == 1) {
			rowHeaderList.put("distrPrice", "配货单价");
			rowHeaderList.put("distrTaxRateStr", "税率(%)");
			if(sign != 1) {
				rowHeaderList.put("notaxDistrPrice", "不含税配货单价");
			}
		}
		
		if(priceType == null || priceType == 0 || priceType == 2 ) {
			rowHeaderList.put("retailPrice", "零售单价");
			rowHeaderList.put("memberPrice", "会员单价");
			rowHeaderList.put("saleTaxRateStr", "税率(%)");
			if(sign != 1) {
				rowHeaderList.put("notaxRetailPrice", "不含税零售单价");
				rowHeaderList.put("notaxMemberPrice", "不含税会员单价");
			}
		}
		
		priceExcelComponent.commExcelExport(output, rowHeaderList, priceOrderReturnVO.getPriceOrderDetailReturnVOS(), userVO, priceOrderReturnVO,sign);
		
	}


	@Override
    public ResponsePriceOrderExcelVO excelImport(UserVO userVO,Long priceOrderId,MultipartFile file) throws Exception {


        InputStream inputStream = file.getInputStream();

        priceOrderIRowReader.setUserVO(userVO);

        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(priceOrderId);
        priceOrderIRowReader.setPriceOrder(priceOrder);

        if(null == priceOrder){
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"未找到价格清单");
        }

        Integer priceType = priceOrder.getPriceType();

        int minColumns = 0;
        if(PriceType.BASE_PRICE.getCode() == priceType){
            minColumns = 12;

        }else if(PriceType.DIS_PRICE.getCode() == priceType){

            minColumns = 9;

        }else if(PriceType.RETAIL_PRICE.getCode() == priceType){
            minColumns = 10;
        }

        ExcelReaderUtil.excelToArrayList(priceOrderIRowReader, file.getOriginalFilename(), inputStream, minColumns, 0);

        List<PriceOrderExcelVO> qualifiedList = priceOrderIRowReader.getQualifiedList();

        Map<String, List> stringListMap = checkExcelImport(userVO, priceOrder, qualifiedList);

        ResponsePriceOrderExcelVO responsePriceOrderExcelVO = new ResponsePriceOrderExcelVO();

        responsePriceOrderExcelVO.setQualifiedCount(stringListMap.get("qualifiedList").size());
        responsePriceOrderExcelVO.setDisqualificationCount(stringListMap.get("disqualificationList").size());
        responsePriceOrderExcelVO.setTotalCount(responsePriceOrderExcelVO.getQualifiedCount()+responsePriceOrderExcelVO.getDisqualificationCount());
        responsePriceOrderExcelVO.setDisqualificationMsg(stringListMap.get("errors"));

        Long key = System.currentTimeMillis();

        String redisDisQualtfiedKey = PriceOrderConstant.DISQUALIFIED_FIELD + userVO.getEnterpriseId() + key;
        String redisQualtfiedKey = PriceOrderConstant.QUALIFIED_FIELD + userVO.getEnterpriseId() + key;
        redisComponent.set(redisDisQualtfiedKey, JSON.toJSONString(stringListMap.get("disqualificationList")));
        redisComponent.set(redisQualtfiedKey, JSON.toJSONString(stringListMap.get("qualifiedList")));

        responsePriceOrderExcelVO.setKey(key.toString());

        logger.info("商品信息导入保存到Redis的key,成功数据={},失败的数据={}",redisQualtfiedKey,redisDisQualtfiedKey);
        return responsePriceOrderExcelVO;
    }


    public Map<String,List> checkExcelImport(UserVO userVO,PriceOrder priceOrder,List<PriceOrderExcelVO> list){

        Long headquartersEnterpriseId = UserEnterpriseUtils.getHeadquartersEnterpriseId(userVO);
	     List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByPriceOrderId(priceOrder.getId());

	     if(CollectionUtils.isEmpty(priceOrderDetails)) throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"未找到价格清单明细");

	     Map<Long, PriceOrderDetail> map = PriceOrderDetail.generatePriceOrderDetailMap(priceOrderDetails);

        List<PriceOrderExcelVO> qualifiedList = new ArrayList<>();
        List<PriceOrderExcelVO> hasQualifiedList = new ArrayList<>();

        List<PriceOrderExcelVO> notNullqualifiedList = new ArrayList<>();

        List<PriceOrderExcelVO> disqualificationList = new ArrayList<>();

        List<String> errors = new ArrayList<>();

        //合格数据的编码set
        List<String> codeSet = new ArrayList<>();


        List<BigDecimal> taxs = PriceOrderExcelVO.getGoodsTaxRates(list);

        if(CollectionUtils.isEmpty(taxs)) throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"模板内的税率都为空,请核对价格清单");

        List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectByCodeOrRates(headquartersEnterpriseId,taxs);

        if(CollectionUtils.isEmpty(goodsTaxRates)) throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"模板内的税率都为空,请核对价格清单");

        Map<BigDecimal, GoodsTaxRate> goodsTaxMap = GoodsTaxRate.getGoodsTaxMap(goodsTaxRates);

        for(PriceOrderExcelVO excelVO :list){
            if(StringUtils.isEmpty(excelVO.getGoodsCode()) && StringUtils.isEmpty(excelVO.getGoodsOldCode())){
                throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"模板内的商品编码、原商品编码不能都为空,请核对价格清单");
            }
        }

        List<String> goodsCodes = PriceOrderExcelVO.getGoodsCode(list);
        List<String> goodsOldCodes = PriceOrderExcelVO.getGoodsOldCode(list);
        if(CollectionUtils.isEmpty(goodsCodes) && CollectionUtils.isEmpty(goodsOldCodes))
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"模板内的商品编码、原商品编码不能都为空,请核对价格清单");
        List<Goods> goodsList1 = new ArrayList<>();
        if(!CollectionUtils.isEmpty(goodsCodes)){
            goodsList1 = goodsMapper.selectByCodes(goodsCodes, headquartersEnterpriseId);
        }

        List<Goods> goodsList2 = new ArrayList<>();
        if(!CollectionUtils.isEmpty(goodsOldCodes)){
            goodsList2 = goodsMapper.selectByOldCodes(goodsOldCodes, headquartersEnterpriseId);
        }

        goodsList1.addAll(goodsList2);

        // 去掉重复的商品
        Set<Goods> goodsSet = new HashSet<>();
        goodsSet.addAll(goodsList1);

        List<Goods> goodsList = new ArrayList<>();
        goodsList.addAll(goodsSet);

        if(CollectionUtils.isEmpty(goodsList)) throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"模板内的商品都不存在,请核对价格清单");

        Map<String,Goods> goodsCodesMap = Goods.getGoodsCodeMap(goodsList);

        Map<String,Goods> goodsIdsMap = Goods.getGoodsIdsMap(goodsList);


        Map<String,List> excelMap = new HashMap<>();

        if(CollectionUtils.isEmpty(goodsCodesMap)) return excelMap;


        excelMap.put("qualifiedList",qualifiedList);
        excelMap.put("disqualificationList",disqualificationList);
        excelMap.put("errors",errors);
        excelMap.put("codeSet",codeSet);

        Map<String,PriceOrderExcelVO> priceOrderExcelVOHashMap = new HashMap<>();

        for(PriceOrderExcelVO excelVO :list){

	        String code = excelVO.getGoodsCode();
	        String oldGoodsCode = excelVO.getGoodsOldCode();
	        String genericName = excelVO.getGoodsGenericName();
	        String specification = excelVO.getGoodsSpecification();
	        String manufacturer = excelVO.getManufacturer();
	        String distrPrice = excelVO.getDistrPrice();
	        String retailPrice = excelVO.getRetailPrice();
	        String memberPrice = excelVO.getMemberPrice();
	        String distrTaxRate = excelVO.getDistrTaxRate();
	        String saleTaxRate = excelVO.getSaleTaxRate();
	        String lineNum = excelVO.getLineNum();

            PriceOrderExcelVO priceOrderExcelVO = checkField(
                    userVO,
                    excelVO.getGoodsCode(),
                    excelVO.getGoodsOldCode(),
                    excelVO.getGoodsGenericName(),
                     excelVO.getRetailPrice(),
                     excelVO.getDistrPrice(),
                     excelVO.getMemberPrice(),
                     excelVO.getDistrTaxRate(),
                     excelVO.getSaleTaxRate(),
                    priceOrder.getPriceType(),
                    goodsTaxMap,goodsCodesMap
            );


            if(null == priceOrderExcelVO.getGoodsId()){
                //商品id为空表示这条记录不合格

                List<String> errorList = disqualificationMsg(lineNum,priceOrderExcelVO);
                errors.addAll(errorList);

                if(StringUtils.isEmpty(priceOrderExcelVO.getGoodsCode()))
                    priceOrderExcelVO.setGoodsCode(code);

                if(StringUtils.isEmpty(priceOrderExcelVO.getGoodsOldCode()))
                    priceOrderExcelVO.setGoodsOldCode(oldGoodsCode);

                if(StringUtils.isEmpty(priceOrderExcelVO.getGoodsGenericName()))
                    priceOrderExcelVO.setGoodsGenericName(genericName);

                if(StringUtils.isEmpty(priceOrderExcelVO.getGoodsSpecification()))
                    priceOrderExcelVO.setGoodsSpecification(specification);

                if(StringUtils.isEmpty(priceOrderExcelVO.getManufacturer()))
                    priceOrderExcelVO.setManufacturer(manufacturer);

                if(StringUtils.isEmpty(priceOrderExcelVO.getDistrPrice()))
                    priceOrderExcelVO.setDistrPrice(distrPrice);

                if(StringUtils.isEmpty(priceOrderExcelVO.getRetailPrice()))
                    priceOrderExcelVO.setRetailPrice(retailPrice);

                if(StringUtils.isEmpty(priceOrderExcelVO.getMemberPrice()))
                    priceOrderExcelVO.setMemberPrice(memberPrice);

                if(StringUtils.isEmpty(priceOrderExcelVO.getDistrTaxRate()))
                    priceOrderExcelVO.setDistrTaxRate(distrTaxRate);

                if(StringUtils.isEmpty(priceOrderExcelVO.getSaleTaxRate()))
                    priceOrderExcelVO.setSaleTaxRate(saleTaxRate);



                disqualificationList.add(priceOrderExcelVO);

            } else {

                //成功的商品
                priceOrderExcelVO.setGoodsCode(code);
                priceOrderExcelVO.setGoodsOldCode(oldGoodsCode);
                priceOrderExcelVO.setGoodsGenericName(genericName);
                priceOrderExcelVO.setGoodsSpecification(specification);
                priceOrderExcelVO.setManufacturer(manufacturer);
                priceOrderExcelVO.setDistrPrice(distrPrice);
                priceOrderExcelVO.setRetailPrice(retailPrice);
                priceOrderExcelVO.setMemberPrice(memberPrice);
                priceOrderExcelVO.setDistrTaxRate(distrTaxRate);
                priceOrderExcelVO.setSaleTaxRate(saleTaxRate);

                notNullqualifiedList.add(priceOrderExcelVO);

                priceOrderExcelVOHashMap.put(priceOrderExcelVO.getGoodsId()+"",priceOrderExcelVO);

            }
        }

        List<Long> goodsIds = new ArrayList<>();

        for(Map.Entry<String,PriceOrderExcelVO> entry : priceOrderExcelVOHashMap.entrySet()){

            Goods goods = goodsIdsMap.get(entry.getKey());

            if(null == goods){

                errors.add("商品未找到");
                disqualificationList.add(entry.getValue());
            }else {

                PriceOrderDetail priceOrderDetail = map.get(goods.getId());

                if(null == priceOrderDetail){

                    errors.add(goods.getCode()+":商品和价格清单不对应");

                    PriceOrderExcelVO priceOrderExcelVO = priceOrderExcelVOHashMap.get(goods.getId()+"");

                    disqualificationList.add(priceOrderExcelVO);

                }else {

                    PriceOrderExcelVO priceOrderExcelVO = priceOrderExcelVOHashMap.get(goods.getId()+"");
                    hasQualifiedList.add(priceOrderExcelVO);
                    goodsIds.add(goods.getId());
                }


            }

        }

        if(CollectionUtils.isEmpty(goodsIds)){
            return excelMap;
        }

        //价格管理（0-总部控制；1-自主控制）门店验证权限
        Map<Long,Boolean> goodsIdMap = new HashMap<>();
        if(userVO.getChainType() != ChainType.Headquarters.getCode()) {
            goodsIdMap = validStorePower(userVO, goodsIds);
        }else {
            goodsIdMap = validHeadPower(userVO, goodsIds);
        }

        for(PriceOrderExcelVO pvo : hasQualifiedList){

            Goods goods = goodsIdsMap.get(pvo.getGoodsId()+"");

            Boolean isTrue = goodsIdMap.get(goods.getId());
            if(null != isTrue){
                errors.add(goods.getCode()+":商品和价格清单不对应");

                PriceOrderExcelVO priceOrderExcelVO = priceOrderExcelVOHashMap.get(goods.getId()+"");
                priceOrderExcelVO.setGoodsCode(ExcelErrorCodeEnum.PRICE_ORDER_NOT_HAS_GOODS.getName());
                disqualificationList.add(priceOrderExcelVO);

            }else {
                PriceOrderExcelVO priceOrderExcelVO = priceOrderExcelVOHashMap.get(goods.getId()+"");

                priceOrderExcelVO.setGoodsId(goods.getId());
                String distrTaxRate = priceOrderExcelVO.getDistrTaxRate();

                String saleTaxRate = priceOrderExcelVO.getSaleTaxRate();

                String goodsCode = priceOrderExcelVO.getGoodsCode();

                if(!StringUtils.isEmpty(distrTaxRate)){
                    if(distrTaxRate.endsWith("%"))
                        distrTaxRate = distrTaxRate.substring(0,distrTaxRate.lastIndexOf('%'));

                    priceOrderExcelVO.setDistrTaxRate(distrTaxRate);
                    BigDecimal tax = new BigDecimal(distrTaxRate).setScale(4,BigDecimal.ROUND_HALF_UP);

                    GoodsTaxRate taxRate = goodsTaxMap.get(tax);
                    priceOrderExcelVO.setDistrTaxRateId(taxRate.getId());
                }

                if(!StringUtils.isEmpty(saleTaxRate)){
                    if(saleTaxRate.endsWith("%"))
                        saleTaxRate = saleTaxRate.substring(0,saleTaxRate.lastIndexOf('%'));

                    priceOrderExcelVO.setSaleTaxRate(saleTaxRate);
                    BigDecimal tax = new BigDecimal(saleTaxRate).setScale(4,BigDecimal.ROUND_HALF_UP);
                    GoodsTaxRate taxRate = goodsTaxMap.get(tax);
                    priceOrderExcelVO.setSaleTaxRateId(taxRate.getId());
                }

                codeSet.add(goodsCode);

                PriceOrderDetail priceOrderDetail = map.get(goods.getId());
                priceOrderExcelVO.setId(priceOrderDetail.getId());
                qualifiedList.add(priceOrderExcelVO);
            }
        }



        return excelMap;

    }


    public List<String> disqualificationMsg(String lineNum , PriceOrderExcelVO priceOrderExcelVO){

        List<String> list = new ArrayList<>();

        String goodsCode = priceOrderExcelVO.getGoodsCode();
        if(!StringUtils.isEmpty(goodsCode)){
            list.add(lineNum+":"+goodsCode);
        }

        String oldCode = priceOrderExcelVO.getGoodsOldCode();
        if(!StringUtils.isEmpty(oldCode)){
            list.add(lineNum+":"+oldCode);
        }

        String goodsGenericName = priceOrderExcelVO.getGoodsGenericName();
        if(!StringUtils.isEmpty(goodsGenericName)){
            list.add(lineNum+":"+goodsGenericName);
        }

        /**
         * 零售基价
         */
        String retailPrice = priceOrderExcelVO.getRetailPrice();
        if(!StringUtils.isEmpty(retailPrice)){
            list.add(lineNum+":"+retailPrice);
        }

        /**
         * 配货价格
         */
        String distrPrice = priceOrderExcelVO.getDistrPrice();
        if(!StringUtils.isEmpty(distrPrice)){
            list.add(lineNum+":"+distrPrice);
        }


        String memberPrice = priceOrderExcelVO.getMemberPrice();
        if(!StringUtils.isEmpty(memberPrice)){
            list.add(lineNum+":"+memberPrice);
        }


        String distrTaxRate = priceOrderExcelVO.getDistrTaxRate();
        if(!StringUtils.isEmpty(distrTaxRate)){
            list.add(lineNum+":"+distrTaxRate);
        }


        /**
         * 销项税
         */

        String saleTaxRate = priceOrderExcelVO.getSaleTaxRate();
        if(!StringUtils.isEmpty(saleTaxRate)){
            list.add(lineNum+":"+saleTaxRate);
        }

        return list;

    }

    /**
     *
     * 价格类型
     * 0-基础价格: 配货单价   税率	零售单价	会员单价	税率
     * 1-配货价格: 配货单价	税率
     * 2-零售价格: 零售单价	会员单价	税率
     * @return
     */
    private PriceOrderExcelVO checkField(UserVO userVO,
                                         String goodsCodede,
                                         String oldGoodsCode,
                                         String goodsGenericName,
                                         String retailPrice,
                                         String distrPrice,
                                         String memberPrice,
                                         String distrTaxRate,
                                         String saleTaxRate,
                                         Integer priceType,Map<BigDecimal, GoodsTaxRate> goodsTaxMap, Map<String,Goods> goodsCodesMap){

        boolean flag = true;
        PriceOrderExcelVO priceOrderExcelVO = new PriceOrderExcelVO();
        if(StringUtils.isEmpty(goodsCodede)){
            //如果商品编码为空，判断原始编码是否为空，如果原始编码也为空，记录不合格，否则判断原始编码是否存在
            if(StringUtils.isEmpty(oldGoodsCode)){
                flag = false;
                priceOrderExcelVO.setGoodsCode(ExcelErrorCodeEnum.GOODS_CODE_NULL.getName());
                priceOrderExcelVO.setGoodsOldCode(ExcelErrorCodeEnum.OLD_GOODS_CODE_NULL.getName());
            } else {
                Goods goodsByOldCode = goodsMapper.getGoodsByOldCode(oldGoodsCode, ChainType.getHeadEnterpriseId(userVO));
                if(goodsByOldCode == null){
                    flag = false;
                    priceOrderExcelVO.setGoodsOldCode(ExcelErrorCodeEnum.INVALID_OLD_CODE.getName());
                } else {
                    priceOrderExcelVO.setGoodsId(goodsByOldCode.getId());
                }
            }


        } else {
            Goods goods = goodsCodesMap.get(goodsCodede);

            if(null == goods){
                priceOrderExcelVO.setGoodsCode(ExcelErrorCodeEnum.INVALID_CODE.getName());
                flag = false;
            } else {
                priceOrderExcelVO.setGoodsId(goods.getId());
            }
        }

        if(StringUtils.isEmpty(goodsGenericName)){

            priceOrderExcelVO.setGoodsGenericName(ExcelErrorCodeEnum.GOODS_GENERICNAME_NULL.getName());
            flag = false;
        }


        /**
         * 1-配货价格: 配货单价 税率
         */
        if(PriceType.DIS_PRICE.getCode() == priceType){

            PriceOrderExcelVO distrPriceExcelVO = checkPrice(distrPrice,6);
            if(null != distrPriceExcelVO){
                priceOrderExcelVO.setDistrPrice(distrPriceExcelVO.getRetailPrice());
                flag = false;
            }


            PriceOrderExcelVO distrTaxRateExcelVO = checkTaxRate(distrTaxRate,goodsTaxMap);
            if(null != distrTaxRateExcelVO){
                priceOrderExcelVO.setDistrTaxRate(distrTaxRateExcelVO.getSaleTaxRate());
                flag = false;
            }

        }else if(PriceType.RETAIL_PRICE.getCode() == priceType){

            /**
             * 2-零售价格: 零售单价	会员单价	税率
             */

            PriceOrderExcelVO retailPriceExcelVO = checkPrice(retailPrice,6);
            if(null != retailPriceExcelVO){
                priceOrderExcelVO.setRetailPrice(retailPriceExcelVO.getRetailPrice());
                flag = false;
            }

            PriceOrderExcelVO memberPriceExcelVO = checkPrice(memberPrice,6);

            if(null != memberPriceExcelVO){
                priceOrderExcelVO.setMemberPrice(memberPriceExcelVO.getRetailPrice());
                flag = false;
            }


            PriceOrderExcelVO saleTaxRateRateExcelVO = checkTaxRate(saleTaxRate,goodsTaxMap);

            if(null != saleTaxRateRateExcelVO){
                priceOrderExcelVO.setSaleTaxRate(saleTaxRateRateExcelVO.getSaleTaxRate());
                flag = false;
            }

        }else if(PriceType.BASE_PRICE.getCode() == priceType){


            PriceOrderExcelVO retailPriceExcelVO = checkPrice(retailPrice,6);
            if(null != retailPriceExcelVO){
                priceOrderExcelVO.setRetailPrice(retailPriceExcelVO.getRetailPrice());
                flag = false;
            }

            PriceOrderExcelVO memberPriceExcelVO = checkPrice(memberPrice,6);

            if(null != memberPriceExcelVO){
                priceOrderExcelVO.setMemberPrice(memberPriceExcelVO.getRetailPrice());
                flag = false;
            }


            PriceOrderExcelVO saleTaxRateRateExcelVO = checkTaxRate(saleTaxRate,goodsTaxMap);

            if(null != saleTaxRateRateExcelVO){
                priceOrderExcelVO.setSaleTaxRate(saleTaxRateRateExcelVO.getSaleTaxRate());
                flag = false;
            }

            PriceOrderExcelVO distrPriceExcelVO = checkPrice(distrPrice,6);
            if(null != distrPriceExcelVO){
                priceOrderExcelVO.setDistrPrice(distrPriceExcelVO.getRetailPrice());
                flag = false;
            }


            PriceOrderExcelVO distrTaxRateExcelVO = checkTaxRate(distrTaxRate,goodsTaxMap);
            if(null != distrTaxRateExcelVO){
                priceOrderExcelVO.setDistrTaxRate(distrTaxRateExcelVO.getSaleTaxRate());
                flag = false;
            }
        }

        if(flag){
            return priceOrderExcelVO;
        } else {
            priceOrderExcelVO.setGoodsId(null);
        }
        return priceOrderExcelVO;

    }

    private PriceOrderExcelVO checkPrice(String price,Integer number){
        if(StringUtils.isEmpty(price)){

            PriceOrderExcelVO priceOrderExcelVO = new PriceOrderExcelVO();
            priceOrderExcelVO.setRetailPrice(ExcelErrorCodeEnum.GOODS_PRICE_NULL.getName());
            return priceOrderExcelVO;
        }else {

            boolean isDecimals = ExcelMethodUtils.isDecimals(price);
            if(!isDecimals){
                PriceOrderExcelVO priceOrderExcelVO = new PriceOrderExcelVO();
                priceOrderExcelVO.setRetailPrice(ExcelErrorCodeEnum.PRICE_IS_NOT_NUMBER.getName());
                return priceOrderExcelVO;
            }

            if (price.contains(".")) {
                String[] split = price.split("\\.");
                if (split[1].length() > number) {
                    PriceOrderExcelVO priceOrderExcelVO = new PriceOrderExcelVO();
                    priceOrderExcelVO.setRetailPrice(ExcelErrorCodeEnum.PRICE_SIZE_ERROR.getName());
                    return priceOrderExcelVO;
                }
            }
        }

        return null;
    }

    private PriceOrderExcelVO checkTaxRate(String taxRate,Map<BigDecimal, GoodsTaxRate> goodsTaxMap){
        if(StringUtils.isEmpty(taxRate)){

            PriceOrderExcelVO priceOrderExcelVO = new PriceOrderExcelVO();
            priceOrderExcelVO.setSaleTaxRate(ExcelErrorCodeEnum.TAXRATE_NULL.getName());
            return priceOrderExcelVO;
        }else {
            if(taxRate.endsWith("%")){
                taxRate = taxRate.substring(0,taxRate.lastIndexOf('%'));
            }

            boolean isDecimals = ExcelMethodUtils.isDecimals(taxRate);
            if(!isDecimals){
                PriceOrderExcelVO priceOrderExcelVO = new PriceOrderExcelVO();
                priceOrderExcelVO.setSaleTaxRate(ExcelErrorCodeEnum.TAXRATE_IS_NOT_NUMBER.getName());
                return priceOrderExcelVO;
            }

            BigDecimal tax = new BigDecimal(taxRate).setScale(4,BigDecimal.ROUND_HALF_UP);
            GoodsTaxRate t = goodsTaxMap.get(tax);
            if(null == t){

                PriceOrderExcelVO priceOrderExcelVO = new PriceOrderExcelVO();
                priceOrderExcelVO.setSaleTaxRate(ExcelErrorCodeEnum.TAXRATE_NOT_EXSIST.getName());
                return priceOrderExcelVO;
            }

        }

        return null;
    }

    private Map<Long,Boolean> validStorePower(UserVO userVO, List<Long> goodsIds) {

        if(CollectionUtils.isEmpty(goodsIds)){
            return Collections.emptyMap();
        }

        Map<Long,Boolean> goodsIdMap = new HashMap<>();
        //获取门店价格管理状态（0-总部控制；1-自主控制）
        Integer priceManage=priceOrderMapper.getEnterpriseBusinessPriceManage(userVO.getEnterpriseId());
        if(priceManage == null) throw  new BusinessException("门店不存在价格清单数据");
        //若是加盟店
        if(userVO.getChainType().equals(2)) {
            if(priceManage.equals(0)) {
                List<Map<String,Object>> goodsPriMags=priceOrderMapper.getGoodsBusPriceManage(goodsIds);
                for(Map<String,Object> map:goodsPriMags) {
                    Long enterpriseId=Long.parseLong(map.get("ownerId").toString());
                    //若不是加盟店自己的商品则无权修改
                    if(!enterpriseId.equals(userVO.getEnterpriseId())){
                        Long goodsId=Long.parseLong(map.get("goodsId").toString());
                        goodsIdMap.put(goodsId,true);
                    }
                }
            }
        }else {//若是自营店
            if(priceManage.equals(0)) throw  new BusinessException("无操作权限");
            List<Map<String,Object>> goodsPriMags=priceOrderMapper.getGoodsBusPriceManage(goodsIds);
            for(Map<String,Object> map:goodsPriMags) {
                if(Integer.parseInt(map.get("pricingPolicy").toString()) == 0) {
                    Long goodsId=Long.parseLong(map.get("goodsId").toString());
                    goodsIdMap.put(goodsId, true);
                }
            }
        }

        return goodsIdMap;
    }

    private Map<Long,Boolean> validHeadPower(UserVO userVO,List<Long> goodsIds) {
        Map<Long,Boolean> goodsIdMap = new HashMap<>();
        List<Map<String,Object>> goodsPriMags=priceOrderMapper.getGoodsBusPriceManage(goodsIds);
        for(Map<String,Object> map:goodsPriMags) {
            Long enterpriseId=Long.parseLong(map.get("ownerId").toString());
            //若不是总部自己的商品则无权修改
            if(!enterpriseId.equals(userVO.getEnterpriseId())) {
                Long goodsId=Long.parseLong(map.get("goodsId").toString());
                goodsIdMap.put(goodsId, true);
            }
        }
        return goodsIdMap;
    }

    @Override
    public PriceOrder queryPriceOrder4Id(Long id){
        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(id);

        return priceOrder;
    }

    @Override
    public void excelDisQualtfiedExport(OutputStream output, PriceOrder priceOrder, UserVO userVO, String key,Integer type) {

        String redisDisQualtfiedKey = PriceOrderConstant.DISQUALIFIED_FIELD + userVO.getEnterpriseId() + key;
        /**
         * 成功
         */
        if(1 == type){

            /**
             * 失败
             */
            redisDisQualtfiedKey = PriceOrderConstant.QUALIFIED_FIELD + userVO.getEnterpriseId() + key;
        }


        Object json = redisComponent.get(redisDisQualtfiedKey);

        Class<PriceOrderExcelVO> clazz = PriceOrderExcelVO.class;
        List<PriceOrderExcelVO> priceOrderExcelVOS = JSON.parseArray(json.toString(),clazz);


        LinkedHashMap<String, String> rowHeaderList=new LinkedHashMap<String, String>();


        Integer priceType = priceOrder.getPriceType();

        if(PriceType.BASE_PRICE.getCode() == priceType){

            rowHeaderList.put("goodsCode", "商品编码");
            rowHeaderList.put("goodsOldCode", "原商品编码");
            rowHeaderList.put("goodsGenericName", "通用名称");
            rowHeaderList.put("dosageName", "剂型");
            rowHeaderList.put("goodsSpecification", "规格");
            rowHeaderList.put("manufacturer", "生产厂商");
            rowHeaderList.put("unitId", "单位");
            rowHeaderList.put("distrPrice", "配货单价");
            rowHeaderList.put("distrTaxRate", "税率(%)");
            rowHeaderList.put("retailPrice", "零售单价");
            rowHeaderList.put("memberPrice", "会员单价");
            rowHeaderList.put("saleTaxRate", "税率(%)");

        }else if(PriceType.DIS_PRICE.getCode() == priceType){

            rowHeaderList.put("goodsCode", "商品编码");
            rowHeaderList.put("goodsOldCode", "原商品编码");
            rowHeaderList.put("goodsGenericName", "通用名称");
            rowHeaderList.put("dosageName", "剂型");
            rowHeaderList.put("goodsSpecification", "规格");
            rowHeaderList.put("manufacturer", "生产厂商");
            rowHeaderList.put("unitId", "单位");
            rowHeaderList.put("distrPrice", "配货单价");
            rowHeaderList.put("distrTaxRate", "税率(%)");


        }else if(PriceType.RETAIL_PRICE.getCode() == priceType){

            rowHeaderList.put("goodsCode", "商品编码");
            rowHeaderList.put("goodsOldCode", "原商品编码");
            rowHeaderList.put("goodsGenericName", "通用名称");
            rowHeaderList.put("dosageName", "剂型");
            rowHeaderList.put("goodsSpecification", "规格");
            rowHeaderList.put("manufacturer", "生产厂商");
            rowHeaderList.put("unitId", "单位");
            rowHeaderList.put("retailPrice", "零售单价");
            rowHeaderList.put("memberPrice", "会员单价");
            rowHeaderList.put("saleTaxRate", "税率(%)");
        }


        priceExcelComponent.commPriceOrderExcelExport(output, rowHeaderList, priceOrderExcelVOS, userVO);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importPriceOrder(UserVO userVO, Long priceOrderId, String redisKey) throws Exception {

        String redisQualtfiedKey = PriceOrderConstant.QUALIFIED_FIELD + userVO.getEnterpriseId() + redisKey;
        Object json = redisComponent.get(redisQualtfiedKey);
        Class<PriceOrderExcelVO> clazz = PriceOrderExcelVO.class;
        List<PriceOrderExcelVO> priceOrderExcelVOS = JSON.parseArray(json.toString(),clazz);

        PriceOrder priceOrder = priceOrderMapper.selectByPrimaryKey(priceOrderId);

        AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO = new AddOrUpdatePriceOrderVO();
        addOrUpdatePriceOrderVO.setId(priceOrderId);
        addOrUpdatePriceOrderVO.setStatus(EnableStatus.ENABLE.getStatus());
        addOrUpdatePriceOrderVO.setParentOrderId(priceOrder.getParentOrderId());

        List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS = UpdatePriceOrderDetailVO.generateUpdatePriceOrderDetailVOs(priceOrderExcelVOS, priceOrderId);
        addOrUpdatePriceOrderVO.setUpdatePriceOrderDetailVOS(updatePriceOrderDetailVOS);

        updatePriceOrder(userVO,addOrUpdatePriceOrderVO);

    }
}
