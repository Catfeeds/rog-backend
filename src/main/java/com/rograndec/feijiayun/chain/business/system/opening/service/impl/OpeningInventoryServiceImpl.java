package com.rograndec.feijiayun.chain.business.system.opening.service.impl;

import com.alibaba.fastjson.JSON;
import com.rograndec.feijiayun.chain.app.SpringBeanFactory;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.RequestGoodsVO2;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.system.opening.common.OpeningGoodsInfoConstant;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningInventoryDetailMapper;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningInventoryMapper;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventory;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventoryDetail;
import com.rograndec.feijiayun.chain.business.system.opening.excel.OpeningIRowReader;
import com.rograndec.feijiayun.chain.business.system.opening.service.OpeningInventoryService;
import com.rograndec.feijiayun.chain.business.system.opening.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.LastInPriceVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.FileUtils;
import com.rograndec.feijiayun.chain.utils.TwoTuple;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelReaderUtil;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by ST on 2017/8/22.
 */
@Service
public class OpeningInventoryServiceImpl implements OpeningInventoryService {

    private static final Logger logger = LoggerFactory.getLogger(OpeningInventoryServiceImpl.class);

    @Autowired
    private OpeningInventoryMapper openingInventoryMapper;
    @Autowired
    private OpeningInventoryDetailMapper openingInventoryDetailMapper;
//    @Autowired
//    private OpeningIRowReader openingIRowReader;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private SupplierMapper supplierMapper;


    @Autowired
    private RedisComponent redisComponent;


    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    FinanceComponent financeComponent;


    @Override
    public TwoTuple<OpeningInventory,Page<List<OpeningInventoryDetail>>> getInventoryRecord(Long enterpriseId,Long parentId, Page page) {
        /**
         * 期初数据，总部查看总部的，门店查看门店的
         */
        OpeningInventory openingInventory = openingInventoryMapper.getInventoryByEnterpId(enterpriseId,parentId);
        List<OpeningInventoryDetail> list = new ArrayList<>();
        if(openingInventory != null){
            int count = openingInventoryDetailMapper.countInventoryRecordByParam(openingInventory.getId());
            if (count > 0){
                list = openingInventoryDetailMapper.getInventoryRecordPageByParam(openingInventory.getId(),page.getStart(),page.getPageSize());
            }
            page.setResult(list);
            page.setTotalRecord(count);
            return  new TwoTuple<>(openingInventory,page);
        } else {
            page.setResult(list);
            page.setTotalRecord(0);
            return  new TwoTuple<>(new OpeningInventory(),page);
        }

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveGoodsToInventory(OpeningGoodsInfoVO goodsVO, UserVO userVO) throws Exception {
    	
    	logger.error("保存期初数据===========================================================================");

        int status = OpeningInventoryStatus.ENABLE.getCode();
        OpeningInventory openingInventory = (OpeningInventory) EntityUtils.reflectAddSetDefaultValue(OpeningInventory.class,userVO);

        String code = orderCodeComponent.generate(OrderRule.OPENING_INVENTORY.getCodePrefix(),userVO.getEnterpriseId(),userVO.getEnterpriseCode());
        openingInventory.setCode(code);
        openingInventory.setOrderType(OrderRule.OPENING_INVENTORY.getType());
        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        if(mangeConfig.getQualityControl() == 0){
            //关闭
            User user = userMapper.selectByPrimaryKey(goodsVO.getStorageManId());
            openingInventory.setStorageManId(user.getId());
            openingInventory.setStorageManName(user.getName());
            openingInventory.setStorageManCode(user.getCode());
            openingInventory.setStorageDate(goodsVO.getStorageDate());
        } else {
            //开启
            openingInventory.setStorageManId(userVO.getUserId());
            openingInventory.setStorageManName(userVO.getUserName());
            openingInventory.setStorageManCode(userVO.getUserCode());
            openingInventory.setStorageDate(new Date());
        }
        //入库
        openingInventory.setStatus(status);
        openingInventory.setTaxAmountTotal(BigDecimal.ZERO);
        openingInventory.setAmountTotal(BigDecimal.ZERO);
        openingInventory.setQuantityTotal(BigDecimal.ZERO);
        openingInventory.setNotaxAmountTotal(BigDecimal.ZERO);
        openingInventory.setVarietiesQuantity(0);

        List<OpeningGoodsExcelVO> openingGoodsExcelVOList = new ArrayList<>();
        //redis中key
        String key = goodsVO.getKey();
        if(!StringUtils.isBlank(key)) {
            String kk = (String) redisComponent.get(OpeningGoodsInfoConstant.OPENING_QUALIFIED_FIELD + key);
            logger.warn("redis 中的成功数据key" + kk);
            //获取所有导入的期初数据
            openingGoodsExcelVOList = JSON.parseArray(kk, OpeningGoodsExcelVO.class);
        }

        //删除的商品id集合
        List<Long> errorIds = goodsVO.getErrorIds();
        if(!CollectionUtils.isEmpty(errorIds)){
            Set<Long> setIds = new HashSet<>(errorIds);

            Iterator<OpeningGoodsExcelVO> iteratorAll = openingGoodsExcelVOList.iterator();
            while (iteratorAll.hasNext()){
                OpeningGoodsExcelVO openingGoodsExcelVO = iteratorAll.next();
                Long excelVOGoodsId = openingGoodsExcelVO.getGoodsId();
                if(setIds.contains(excelVOGoodsId)){
                    iteratorAll.remove();
                }
            }
        }


        List<OpeningInventoryDetailVO> newOpeningGoodsVOS = new ArrayList<>();
        Iterator<OpeningGoodsExcelVO> iterator = openingGoodsExcelVOList.iterator();
        List<OpeningInventoryDetailVO> openingGoodsVOS = goodsVO.getOpeningGoodsVOS();
        while (iterator.hasNext()){
            OpeningGoodsExcelVO openingGoodsExcelVO = iterator.next();
            Long excelVOGoodsId = openingGoodsExcelVO.getGoodsId();
            long count = openingGoodsVOS.stream().filter(item -> item.getGoodsId().equals(excelVOGoodsId)).count();
            if(count > 0){
                iterator.remove();
            }
        }
        Iterator<OpeningGoodsExcelVO> iteratorTmp = openingGoodsExcelVOList.iterator();
        while (iteratorTmp.hasNext()){
            OpeningGoodsExcelVO openingGoodsExcelVO = iteratorTmp.next();
            OpeningInventoryDetailVO detailVO = new OpeningInventoryDetailVO();
            detailVO.setGoodsId(openingGoodsExcelVO.getGoodsId());

            detailVO.setLotNumber(openingGoodsExcelVO.getLotNumber());
            detailVO.setShelfId(openingGoodsExcelVO.getShelfId());
            detailVO.setShelfName(openingGoodsExcelVO.getShelfName());

            detailVO.setManufacturer(openingGoodsExcelVO.getManufacturer());


            detailVO.setProductDate(DateUtils.StringToDate(openingGoodsExcelVO.getProductDate()));
            detailVO.setValidDate(DateUtils.StringToDate(openingGoodsExcelVO.getValidDate()));

            BigDecimal quantity = new BigDecimal(openingGoodsExcelVO.getQuantity());
            detailVO.setQuantity(new BigDecimal(openingGoodsExcelVO.getQuantity()));
            detailVO.setRetailPrice(new BigDecimal(openingGoodsExcelVO.getRetailPrice()));
            detailVO.setMemberPrice(new BigDecimal(openingGoodsExcelVO.getMemberPrice()));

            BigDecimal unitPrice = new BigDecimal(openingGoodsExcelVO.getUnitPrice());
            detailVO.setUnitPrice(unitPrice);
            //金额
            BigDecimal amount = quantity.multiply(unitPrice);
            detailVO.setAmount(amount);//数量*单价

            detailVO.setTaxRateId(openingGoodsExcelVO.getTaxRateId());
            detailVO.setTaxRate(new BigDecimal(openingGoodsExcelVO.getTaxRate()));

            detailVO.setSupplierId(openingGoodsExcelVO.getSupplierId());

            newOpeningGoodsVOS.add(detailVO);
        }
        openingGoodsVOS.addAll(newOpeningGoodsVOS);
        Set<String> varietiesQuantitySet = new HashSet<>();


        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        BigDecimal quantityTotal = BigDecimal.ZERO;

        List<GoodsTaxRateVO> goodsTaxRateVOS = goodsTaxRateMapper.selectAllVO(ChainType.getHeadEnterpriseId(userVO), EnableStatus.ENABLE.getStatus());

        for (OpeningInventoryDetailVO openingGoodsVO : openingGoodsVOS) {
            varietiesQuantitySet.add(openingGoodsVO.getGoodsId()+"");
            BigDecimal quantity = openingGoodsVO.getQuantity();

            BigDecimal unitPrice = openingGoodsVO.getUnitPrice();
            //金额
            BigDecimal amount = quantity.multiply(unitPrice);
            Long taxRateId = openingGoodsVO.getTaxRateId();//税率id
            GoodsTaxRateVO goodsTaxRate = commonComponent.getTaxRate(goodsTaxRateVOS,taxRateId);
            BigDecimal taxRate = goodsTaxRate.getTaxRate();
            //不含税金额
            BigDecimal notaxAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(amount,taxRate);
            //税额
            BigDecimal taxAmount = amount.subtract(notaxAmount);
            //合计
            amountTotal = amountTotal.add(amount);
            notaxAmountTotal = notaxAmountTotal.add(notaxAmount);
            taxAmountTotal = taxAmountTotal.add(taxAmount);
            quantityTotal = quantityTotal.add(quantity);
        }

        openingInventory.setAmountTotal(amountTotal);
        openingInventory.setNotaxAmountTotal(notaxAmountTotal);
        openingInventory.setQuantityTotal(quantityTotal);
        openingInventory.setVarietiesQuantity(varietiesQuantitySet.size());
        openingInventory.setTaxAmountTotal(taxAmountTotal);

        openingInventoryMapper.insertSelective(openingInventory);
//        openingInventoryMapper.updateByPrimaryKeySelective(openingInventory);

        logger.warn("期初保存前大小：" + openingGoodsVOS.size());
        //保存唯一商品信息
        List<String>  uniqueGoodsList = new CopyOnWriteArrayList<>();
        List<OpeningInventoryDetail> detailList = new CopyOnWriteArrayList<>();
        for(int i = 0; i < openingGoodsVOS.size(); i++){
            saveOpeningDetail(userVO, status, openingInventory, uniqueGoodsList, goodsTaxRateVOS, detailList, openingGoodsVOS.get(i));
        }
        List<OpeningInventoryDetail> afterRmRepetitionList = new CopyOnWriteArrayList<>();
        //商品id ,批号，货位id,单价 相同的数据，合并该记录的数量
        Map<String, List<OpeningInventoryDetail>> collect = detailList.parallelStream().collect(Collectors.groupingBy(OpeningInventoryDetail::getKeyStr));
        collect.forEach((String k, List<OpeningInventoryDetail> v) ->{
            BigDecimal sumOfBigDecimals = v.stream().filter(Objects::nonNull)
                    .filter(c->c.getQuantity() != null)
                    .map(OpeningInventoryDetail::getQuantity)
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
            Optional<OpeningInventoryDetail> first = v.stream().findFirst();
            if(first.isPresent()){
                first.get().setQuantity(sumOfBigDecimals);
                afterRmRepetitionList.add(first.get());
            }

        });


        //批量入库
        openingInventoryDetailMapper.insertBatchOpeningGoods(afterRmRepetitionList);

        logger.warn("期初明细数据大小 " + afterRmRepetitionList.size());
        logger.warn("期初明细数据内容 " + afterRmRepetitionList);


        //生成关键表数据（批次、成本、库存、出入库明细）
        OrderModelBuilder builder = new OrderModelBuilder(userVO);
        OrderModel orderModel = builder.buildOrderModel(OrderRule.OPENING_INVENTORY, openingInventory, afterRmRepetitionList);
        inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);

        List<OpeningInventoryDetail> inventoryRecordDetailList = openingInventoryDetailMapper.getOpeningDetailGroupByGoodsId(openingInventory.getId());

        /**
         * 构建priceVO
         */
        inventoryRecordDetailList.forEach(
                detail -> {
                    LastInPriceVO lastInPriceVO = new LastInPriceVO();
                    /**
                     *
                     *
                     private Long inTaxRateId;// 入库税率ID
                     private BigDecimal inTaxRate;// 入库税率
                     private BigDecimal inPrice;// 入库单价
                     */
                    lastInPriceVO.setEnterpriseId(userVO.getEnterpriseId());
                    lastInPriceVO.setParentId(userVO.getParentId());
                    lastInPriceVO.setChainType(userVO.getChainType());
                    lastInPriceVO.setGoodsId(detail.getGoodsId());
                    lastInPriceVO.setInTaxRateId(detail.getTaxRateId());
                    lastInPriceVO.setInTaxRate(detail.getTaxRate());
                    lastInPriceVO.setInPrice(detail.getUnitPrice());
                    if(detail.getSupplierId() != null){
//                        如果期初导入的数据有提供供货单位，则更新价格清单表中的供货单位id,最近采购入库价,最近采购税
                        lastInPriceVO.setSupplierId(detail.getSupplierId());
                        lastInPriceVO.setPurTaxRate(detail.getTaxRate());
                        lastInPriceVO.setPurTaxRateId(detail.getTaxRateId());
                        lastInPriceVO.setPurPrice(detail.getUnitPrice());
                    }

                    commonComponent.updateLastPriceInfo(lastInPriceVO);
                }
        );

        //财务数据更新
        financeComponent.openingInventoryToBalanceAndVoucher(userVO, openingInventory);

        logger.warn("期初保存结束");

//        //保存唯一商品信息
//        List<String>  uniqueGoodsList = new CopyOnWriteArrayList<>();
//        List<OpeningInventoryDetail> detailList = new CopyOnWriteArrayList<>();
//        ExecutorService executorServiceMain = Executors.newFixedThreadPool(2);
//
//        ResourceCondition resourceBy2Condition = new ResourceCondition();
//
//        executorServiceMain.submit(()->{
//            resourceBy2Condition.product(userVO,status,openingInventory,uniqueGoodsList,goodsTaxRateVOS,detailList,openingGoodsVOS);
//        });
//
//        executorServiceMain.submit(()->{
//            resourceBy2Condition.consume(userVO,detailList,openingInventory);
//        });

    }


//    class ResourceCondition {
//
//        ThreadLocal<Integer> threadLocal = new ThreadLocal();
//        private boolean flag = false;
//
//        //创建一个锁对象
//        Lock lock = new ReentrantLock();
//
//        //通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
//        Condition producer_con = lock.newCondition();
//        Condition consumer_con = lock.newCondition();
//
//        /**
//         * 生产
//         *
//         * @param name
//         */
//        public void product(UserVO userVO, int status, OpeningInventory openingInventory,
//                            List<String> uniqueGoodsList, List<GoodsTaxRateVO> goodsTaxRateVOS,
//                            List<OpeningInventoryDetail> detailList,List<OpeningInventoryDetailVO> openingGoodsVOS) {
//            lock.lock();
//            try {
//                while (flag) {
//                    try {
//                        producer_con.await();
//                    } catch (InterruptedException e) {
//                    }
//                }
//                System.out.println("while循环外面的线程" + Thread.currentThread().getName());
//                Runtime runtime = Runtime.getRuntime();
//                //JVM可以使用的处理器个数
//                int processors = runtime.availableProcessors();
//                logger.info("JVM可以使用的处理器个数 processors = " + processors);
//                ExecutorService executorService = Executors.newFixedThreadPool(processors - 1);
//
//                threadLocal.set(0);
//                int i = threadLocal.get();
//                System.out.println("i初始化i= " + i);
//                while (true){
//                    System.out.println("FOR外i = " + threadLocal.get());
//                    System.out.println("FOR循环外面的线程" + Thread.currentThread().getName());
////                    if(i < openingGoodsVOS.size()){
//                        for(; i < openingGoodsVOS.size(); i++){
//                            threadLocal.set(i);
//                            System.out.println("FOR中 =" + threadLocal.get());
//                            System.out.println("i变化 = " + i);
//                            OpeningInventoryDetailVO openingGoodsVO = openingGoodsVOS.get(i);
//                            executorService.submit(() -> {
//                                System.out.println("+++++++++线程" + Thread.currentThread().getName() + " 开始导入" + openingGoodsVO.getGoodsId() + "++++++++");
//                                try {
//                                    saveOpeningDetail(userVO, status, openingInventory, uniqueGoodsList, goodsTaxRateVOS, detailList, openingGoodsVO);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    throw new RuntimeException();
//                                }
//                            });
//                        }
////                    }
//
//                    System.out.println("i =" + i + "  ==openingGoodsVOS.size()=" + (openingGoodsVOS.size()));
//                    System.out.println("detailList.size() =" + detailList.size());
//                    if(i == openingGoodsVOS.size() && detailList.size() == openingGoodsVOS.size()){
//                        flag = true;
//                        consumer_con.signal();//直接唤醒消费线程
//                        break;
//                    } else {
//                        System.out.println("detailList数量不够，主线程先睡一会。。");
//                        Thread.sleep(1000);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new BusinessException("期初保存失败！");
//            } finally {
//                lock.unlock();
//            }
//        }
//
//        /**
//         * 消费
//         */
//        public void consume(UserVO userVO,List<OpeningInventoryDetail> detailList,OpeningInventory openingInventory) {
//            lock.lock();
//            try {
//                System.out.println("consume之前----" + flag);
//                while (!flag) {
//                    try {
//                        System.out.println("consume消费者线程等待中，，，，");
//                        consumer_con.await();
//                    } catch (InterruptedException e) {
//                    }
//                }
//                System.out.println("consume之后----" + flag);
//
//                //批量入库
//                openingInventoryDetailMapper.insertBatchOpeningGoods(detailList);
//
//                //生成关键表数据（批次、成本、库存、出入库明细）
//                OrderModelBuilder builder = new OrderModelBuilder(userVO);
//                OrderModel orderModel = builder.buildOrderModel(OrderRule.OPENING_INVENTORY, openingInventory, detailList);
//                inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);
//
//                //财务数据更新
//                financeComponent.openingInventoryToBalanceAndVoucher(userVO, openingInventory);
//
////                flag = false;
////                producer_con.signal();//直接唤醒生产线程
//            } catch (Exception e){
//                throw new BusinessException("期初保存失败！");
//            }finally {
//                lock.unlock();
//            }
//        }
//    }

    /**
     *
     * @param userVO
     * @param status
     * @param openingInventory
     * @param uniqueGoodsList
     * @param goodsTaxRateVOS
     * @param detailList
     * @param openingGoodsVO
     * @throws Exception
     */
    private void saveOpeningDetail(UserVO userVO, int status, OpeningInventory openingInventory,
                                   List<String> uniqueGoodsList, List<GoodsTaxRateVO> goodsTaxRateVOS,
                                   List<OpeningInventoryDetail> detailList, OpeningInventoryDetailVO openingGoodsVO
                                   ) throws Exception {
        OpeningInventoryDetail openingInventoryDetail = (OpeningInventoryDetail) EntityUtils.reflectAddSetDefaultValue(OpeningInventoryDetail.class,userVO);
        Long goodsId = openingGoodsVO.getGoodsId();
        String lotNumber = openingGoodsVO.getLotNumber();
        Long shelfId = openingGoodsVO.getShelfId();
        BigDecimal unitPrice = openingGoodsVO.getUnitPrice();

        if(goodsId == null){
            throw new BusinessException("商品ID不能为空");
        }
        if( StringUtils.isBlank(lotNumber)){
            throw new BusinessException("批号不能为空");
        }
        if(shelfId == null){
            throw new BusinessException("货位ID不能为空");
        }
        WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(shelfId);
        if(unitPrice == null){
            throw new BusinessException("单价不能为空");
        }



        setOpeningInventoryDetail(userVO, status, openingInventory, goodsTaxRateVOS, openingGoodsVO, openingInventoryDetail, warehouseShelf);

        // 更新商品的默认货位
        String keyStr = goodsId + "" + lotNumber + "" + shelfId + "" + unitPrice;
        if(uniqueGoodsList.contains(keyStr)){
            logger.error("重复的期初数据----商品id" + goodsId + "-批号" +lotNumber + "-货位id" + shelfId +"-单价" + unitPrice +"");
        } else {
            uniqueGoodsList.add(keyStr);
            commonComponent.updateGoodsDefShelf(userVO.getEnterpriseId(),userVO.getParentId(),userVO.getChainType(),goodsId,shelfId,warehouseShelf.getName(),userVO);
        }
        logger.warn("期初商品明细信息：" + openingInventoryDetail.toString());
        detailList.add(openingInventoryDetail);
    }

    private void setOpeningInventoryDetail(UserVO userVO, int status, OpeningInventory openingInventory,
                                           List<GoodsTaxRateVO> goodsTaxRateVOS, OpeningInventoryDetailVO openingGoodsVO,
                                           OpeningInventoryDetail openingInventoryDetail,  WarehouseShelf warehouseShelf) {
        openingInventoryDetail.setStatus(status);
        openingInventoryDetail.setGoodsCode(openingGoodsVO.getGoodsCode());
        openingInventoryDetail.setOrderId(openingInventory.getId());
        openingInventoryDetail.setEnterpriseId(userVO.getEnterpriseId());
        openingInventoryDetail.setParentId(userVO.getParentId());

        Goods goods = goodsMapper.selectByPrimaryKey(openingGoodsVO.getGoodsId());
        openingInventoryDetail.setGoodsId(goods.getId());
        openingInventoryDetail.setGoodsCode(goods.getCode());
        openingInventoryDetail.setGoodsGenericName(goods.getGenericName());
        openingInventoryDetail.setGoodsName(goods.getName());
        openingInventoryDetail.setGoodsSpecification(goods.getSpecification());
        openingInventoryDetail.setDosageId(goods.getDosageId());
        openingInventoryDetail.setDosageName(goods.getDosageName());
        openingInventoryDetail.setUnitId(goods.getUnitId());
        openingInventoryDetail.setUnitName(goods.getUnitName());

        String manufactureName = openingGoodsVO.getManufacturer();
        openingInventoryDetail.setManufacturer(manufactureName);
        openingInventoryDetail.setProductDate(openingGoodsVO.getProductDate());
        openingInventoryDetail.setValidDate(openingGoodsVO.getValidDate());
        BigDecimal quantity = openingGoodsVO.getQuantity();
        openingInventoryDetail.setQuantity(quantity);
        openingInventoryDetail.setRetailPrice(openingGoodsVO.getRetailPrice());
        openingInventoryDetail.setMemberPrice(openingGoodsVO.getMemberPrice());
        openingInventoryDetail.setUnitPrice(openingGoodsVO.getUnitPrice());
        //金额
        BigDecimal amount = quantity.multiply(openingGoodsVO.getUnitPrice());
        openingInventoryDetail.setAmount(amount);//数量*单价
        Long taxRateId = openingGoodsVO.getTaxRateId();//税率id
        GoodsTaxRateVO goodsTaxRate = commonComponent.getTaxRate(goodsTaxRateVOS,taxRateId);

        BigDecimal taxRate = goodsTaxRate.getTaxRate();
        openingInventoryDetail.setTaxRate(goodsTaxRate.getTaxRate());
        openingInventoryDetail.setTaxRateId(goodsTaxRate.getId());
        //不含税金额
        BigDecimal notaxAmount = BigDecimal.ZERO;
        //不含税单价
        BigDecimal notaxPrice = BigDecimal.ZERO;
        if(taxRate != null && taxRate.equals(BigDecimal.ZERO)){
            notaxAmount = amount;
            notaxPrice = openingGoodsVO.getUnitPrice();
        }else{
            notaxAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(amount,taxRate);
            notaxPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmount, quantity);
        }
        openingInventoryDetail.setNotaxAmount(notaxAmount);
        openingInventoryDetail.setNotaxPrice(notaxPrice);
        //税额
        BigDecimal taxAmount = amount.subtract(notaxAmount);
        openingInventoryDetail.setTaxAmount(taxAmount);
        openingInventoryDetail.setLotNumber(openingGoodsVO.getLotNumber());

        if(warehouseShelf == null){
            throw new BusinessException("货位不存在");
        }
        openingInventoryDetail.setShelfId(openingGoodsVO.getShelfId());
        openingInventoryDetail.setShelfName(warehouseShelf.getName());


        Long supplierId = openingGoodsVO.getSupplierId();
        Integer type = openingGoodsVO.getType();//1:企业 2 供货单位 如果为空表示没有供货单位

        if(supplierId != null && type != null){

            if(type == 1){
                //总部
                openingInventoryDetail.setSupplierId(userVO.getParentId());
                openingInventoryDetail.setSupplierCode(userVO.getParentCode());
                openingInventoryDetail.setSupplierName(userVO.getParentName());
            } else {
                Supplier supplier = supplierMapper.selectByPrimaryKey(supplierId);
                if(supplier == null){
                    throw new BusinessException("供货商不存在");
                }
                openingInventoryDetail.setSupplierId(supplier.getId());
                openingInventoryDetail.setSupplierCode(supplier.getCode());
                openingInventoryDetail.setSupplierName(supplier.getName());
            }

        }
    }


    @Override
    public void batchInsert(List<OpeningInventoryDetail> detailList){
        openingInventoryDetailMapper.insertBatchOpeningGoods(detailList);
    }


    /**
     * 期初导入
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public ResponseOpeningGoodsExcelVO excelOpeningImport(HttpServletRequest request) throws Exception {
        OpeningIRowReader openingIRowReader = SpringBeanFactory.getBean(OpeningIRowReader.class);
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");

        Long enterpriseId = ChainType.getHeadEnterpriseId(userVO);
        openingIRowReader.setUserVO(userVO);
        Part part = request.getPart("file");
        InputStream input = part.getInputStream();
        //合格产品
        List<OpeningGoodsExcelVO> qualifiedList = new ArrayList<>();
        //不合格产品
        List<OpeningGoodsExcelVO> disqualificationList = new ArrayList<>();

        openingIRowReader.setQualifiedList(qualifiedList);
        openingIRowReader.setDisqualificationList(disqualificationList);

        //税收
        List<GoodsTaxRateVO> goodsTaxRateVOs = goodsTaxRateMapper.selectAllVO(enterpriseId, EnableStatus.ENABLE.getStatus());
        openingIRowReader.setTaxRateVOList(goodsTaxRateVOs);


        ExcelReaderUtil.excelToArrayList(openingIRowReader, FileUtils.getFileName(part), input, 17, 0);
        ResponseOpeningGoodsExcelVO goodsExcelVO = new ResponseOpeningGoodsExcelVO();
        //goodsExcelVO.setQualifiedGoodsList(openingIRowReader.getQualifiedList());
        //goodsExcelVO.setDisqualificationGoodsList(openingIRowReader.getDisqualificationList());

        goodsExcelVO.setQualifiedCount(qualifiedList.size());
        goodsExcelVO.setDisqualificationCount(disqualificationList.size());

        //向redis中存储合格/不合格的商品
        Long timestamp = System.currentTimeMillis();
        List<OpeningGoodsExcelVO> qualifiedList2Redis = openingIRowReader.getQualifiedList();
        List<OpeningGoodsExcelVO> disQualifiedList2Redis = openingIRowReader.getDisqualificationList();


        redisComponent.set(OpeningGoodsInfoConstant.OPENING_QUALIFIED_FIELD + timestamp, JSON.toJSONString(qualifiedList2Redis));
        redisComponent.set(OpeningGoodsInfoConstant.OPENING_DISQUALIFIED_FIELD + timestamp,JSON.toJSONString(disQualifiedList2Redis));
        logger.error("期初信息导入保存到Redis的key,成功数据={},失败的数据={},成功标准数据={}", OpeningGoodsInfoConstant.OPENING_QUALIFIED_FIELD + timestamp,OpeningGoodsInfoConstant.OPENING_DISQUALIFIED_FIELD + timestamp,OpeningGoodsInfoConstant.OPENING_QUALIFIED_FIELD + timestamp);
        goodsExcelVO.setTimestamp(timestamp.toString());
        return goodsExcelVO;
    }

    @Override
    public void exportOpeningGoods(OutputStream output, String key,Integer type) throws Exception {
        List<OpeningGoodsExcelVO> list = new ArrayList<>();
        if(type == 1){
            list = JSON.parseArray((String) redisComponent.get(OpeningGoodsInfoConstant.OPENING_QUALIFIED_FIELD + key),OpeningGoodsExcelVO.class);
        } else {
            list = JSON.parseArray((String) redisComponent.get(OpeningGoodsInfoConstant.OPENING_DISQUALIFIED_FIELD + key),OpeningGoodsExcelVO.class);
        }
        excelExport(output, list);
    }

    @Override
    public void excelExport(OutputStream output,  List<OpeningGoodsExcelVO> list) {
        try {
            ExcelWriter writer = new ExcelWriter() {
                public void generate() throws Exception {
//
                    // 电子表格开始
                    this.beginWorkSheet();
                    this.beginSheet();
                    createRowHeader(this);
//                    createFirstRowData(this);

                    Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                    short cellStrIndex = styleMap.get("cell_string").getIndex();
                    for (int rowNum = 0; rowNum < list.size(); rowNum++) {
                        OpeningGoodsExcelVO goodsVO = list.get(rowNum);
                        // 插入新行
                        this.insertRow(rowNum + 1);
                        // 建立新单元格,索引值从0开始,表示第一列
                        int k = 0;
                        this.createCell(k++, goodsVO.getCode(),cellStrIndex);
                        this.createCell(k++, goodsVO.getOldCode(),cellStrIndex);
                        this.createCell(k++, goodsVO.getGenericName(),cellStrIndex);
                        this.createCell(k++, goodsVO.getDosageName(),cellStrIndex);
                        this.createCell(k++, goodsVO.getUnitName(),cellStrIndex);
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getSpecification()),cellStrIndex);
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getManufacturer()),cellStrIndex);
                        this.createCell(k++, goodsVO.getLotNumber(),cellStrIndex);
                        this.createCell(k++, goodsVO.getProductDate(),cellStrIndex);
                        this.createCell(k++, goodsVO.getValidDate(),cellStrIndex);
                        this.createCell(k++, goodsVO.getQuantity(),cellStrIndex);
                        this.createCell(k++, goodsVO.getUnitPrice(),cellStrIndex);
                        this.createCell(k++, goodsVO.getTaxRate(),cellStrIndex);
                        this.createCell(k++, goodsVO.getShelfName(),cellStrIndex);
                        this.createCell(k++, goodsVO.getRetailPrice(),cellStrIndex);
                        this.createCell(k++, goodsVO.getMemberPrice(),cellStrIndex);
                        this.createCell(k++, goodsVO.getSupplierCode(),cellStrIndex);
                        this.createCell(k, goodsVO.getSupplierName(),cellStrIndex);
                        // 结束行
                        this.endRow();
                    }

                    // 电子表格结束
                    this.endSheet();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getOpeningInventoryCount(Long enterpriseId){
        return openingInventoryMapper.getInventoryCount(enterpriseId);
    }

    @Override
    public List<OpeningGoodsGet> getGoodsListByParam(String param, UserVO userVO) {

        if(userVO.getChainType() == ChainType.Division.getCode()){
            //加盟店 搜索加盟点和总部的商品
            //查询加盟店商品
            Long enterpriseId = userVO.getEnterpriseId();
            Long parentId = userVO.getParentId();
            List<OpeningGoodsGet> goodsList = goodsMapper.getOpeningGoodsListByParam(param,userVO.getChainType(), enterpriseId,parentId);
            return goodsList;
        } else {
            //总部和门店 搜索只属于总部的商品
            return goodsMapper.getOpeningGoodsListByParam(param,userVO.getChainType(), ChainType.getHeadEnterpriseId(userVO), null);
        }

    }

    @Override
    public void importSuccessGoods(UserVO userVO, String key, Page2OpeningInventory<List<OpeningGoodsExcelVO>> page) throws Exception {

        String kk = (String) redisComponent.get(OpeningGoodsInfoConstant.OPENING_QUALIFIED_FIELD + key);
        List<OpeningGoodsExcelVO> openingGoodsExcelVOList = JSON.parseArray(kk, OpeningGoodsExcelVO.class);
        if(CollectionUtils.isEmpty(openingGoodsExcelVOList)) {
            page.setResult(new ArrayList<>());
        } else {
            int size = openingGoodsExcelVOList.size();
            int start = page.getStart();
            Integer pageSize = page.getPageSize();
            if(start > size){
                page.setResult(new ArrayList<>());
                page.setRemainCount(0);
            } else {
                int end = start + pageSize;
                if(end > size) {
                    end = size;
                }
                List<OpeningGoodsExcelVO> openingGoodsExcelVOS = openingGoodsExcelVOList.subList(start, end);
                page.setResult(openingGoodsExcelVOS);
                page.setRemainCount(size - start - openingGoodsExcelVOS.size());
            }
        }

    }



    private void createRowHeader(ExcelWriter writer) throws IOException {
        // 插入新行
        writer.insertRow(0);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "商品编码");
        writer.createCell(k++, "商品原编码");
        writer.createCell(k++, "通用名称");
        writer.createCell(k++, "剂型");
        writer.createCell(k++, "单位");
        writer.createCell(k++, "规格");
        writer.createCell(k++, "生成厂商");
        writer.createCell(k++, "批号");
        writer.createCell(k++, "生产日期");
        writer.createCell(k++, "有效期至");
        writer.createCell(k++, "数量");
        writer.createCell(k++, "单价");
        writer.createCell(k++, "税率");
        writer.createCell(k++, "货位");
        writer.createCell(k++, "零售单价");
        writer.createCell(k++, "会员单价");
        writer.createCell(k++, "供货单位编码");
        writer.createCell(k, "供货单位名称");
        // 结束行
        writer.endRow();
    }

//    private void createFirstRowData(ExcelWriter writer) throws IOException {
//        // 插入新行
//        writer.insertRow(0);
//        // 建立新单元格,索引值从0开始,表示第一列
//        int k = 0;
//        writer.createCell(k++, "Y00001");
//        writer.createCell(k++, "123456");
//        writer.createCell(k++, "复方丹参片");
//        writer.createCell(k++, "片剂");
//        writer.createCell(k++, "盒");
//        writer.createCell(k++, "每支装10ml");
//        writer.createCell(k++, "同仁堂");
//        writer.createCell(k++, "20130830001");
//        writer.createCell(k++, "20130701");
//        writer.createCell(k++, "20130701");
//        writer.createCell(k++, "200");
//        writer.createCell(k++, "10.000000");
//        writer.createCell(k++, "20%");
//        writer.createCell(k++, "货位");
//        writer.createCell(k++, "1");
//        writer.createCell(k++, "10.000000");
//        writer.createCell(k++, "s001");
//        writer.createCell(k, "XX总部供货商");
//        // 结束行
//        writer.endRow();
//    }



    /**
     * 商品信息首页查询导出excel
     */
    @Override
    public void exportGoodsInfo(OutputStream outputStream, UserVO userVO){
        RequestGoodsVO2 requestGoodsVO2 = new RequestGoodsVO2();
        ParamUtils.packParam(userVO,requestGoodsVO2);
        List<GoodsVO> list = goodsMapper.getMainGoodsList(requestGoodsVO2);
        createExcel(outputStream, list);
    }


    private void createExcel(OutputStream output, List<GoodsVO> list) {
        try {
            final List<GoodsVO> finalList = list;
            ExcelWriter writer = new ExcelWriter() {
                public void generate() throws Exception {
                    // 电子表格开始
                    this.beginWorkSheet();
                    this.beginSheet();
                    createRowHeader(this);
                    createFirstRow(this);

                    Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                    short cellStrIndex = styleMap.get("cell_string").getIndex();
                    for (int rowNum = 0; rowNum < finalList.size(); rowNum++) {
                        GoodsVO goodsVO = finalList.get(rowNum);
                        // 插入新行
                        this.insertRow(rowNum + 2);
                        // 建立新单元格,索引值从0开始,表示第一列
                        int k = 0;
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getCode()),cellStrIndex);
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getOldCode()),cellStrIndex);
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getGenericName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getDosageName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getUnitName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getSpecification()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getManufacturer()));

                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k++, "",cellStrIndex);
                        this.createCell(k, "",cellStrIndex);
                        // 结束行
                        this.endRow();
                    }
                    // 电子表格结束
                    this.endSheet();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private void createFirstRow(ExcelWriter writer) throws IOException {
        // 插入新行
        writer.insertRow(1);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "01010001");
        writer.createCell(k++, "原商品编码");
        writer.createCell(k++, "阿莫西林胶囊");
        writer.createCell(k++, "片剂");
        writer.createCell(k++, "盒");
        writer.createCell(k++, "0.25g*10粒*2板/盒");
        writer.createCell(k++, "吉林显锋科技制药有限公司");
        writer.createCell(k++, "批号");
        writer.createCell(k++, "2014-12-31");
        writer.createCell(k++, "2019-12-31");
        writer.createCell(k++, "11");
        writer.createCell(k++, "20");
        writer.createCell(k++, "");
        writer.createCell(k++, "货位");
        writer.createCell(k++, "10");
        writer.createCell(k++, "20");
        writer.createCell(k++, "供货单位编码");
        writer.createCell(k, "供货单位名称");

        // 结束行
        writer.endRow();
    }




}
