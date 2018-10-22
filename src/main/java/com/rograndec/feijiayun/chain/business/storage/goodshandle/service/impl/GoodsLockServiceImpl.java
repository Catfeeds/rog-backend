package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.StockLockRecord;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.*;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.*;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsLockService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StartSaleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StopSaleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.StartSaleFromType;
import com.rograndec.feijiayun.chain.common.constant.StopSaleFromType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.common.vo.LockQtyArgVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: GoodsLockServiceImpl
 * @Description: 储存管理-商品处理-药品锁定-实现接口
 * @date 2017-09-27 17:26:40
 */
@Service
public class GoodsLockServiceImpl implements GoodsLockService {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(GoodsLockServiceImpl.class);

    @Autowired
    private GoodsLockMapper goodsLockMapper;
    @Autowired
    private GoodsLockDetailMapper goodsLockDetailMapper;
    @Autowired
    private GoodsLockShelfMapper goodsLockShelfMapper;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    @Lazy
    private StopSaleService stopSaleService;
    @Autowired
    private StopSaleMapper stopSaleMapper;
    @Autowired
    private StopSaleDetailMapper stopSaleDetailMapper;
    @Autowired
    @Lazy
    private StartSaleService startSaleService;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private StockLockRecordMapper stockLockRecordMapper;

    @Autowired
    private CostMapper costMapper;

    @Override
    public GoodsLock getGoodsLockData(Long lockId) {
        GoodsLock goodsLock = goodsLockMapper.selectByPrimaryKey(lockId);
        List<GoodsLockDetail> goodsLockDetailList = goodsLockDetailMapper.getGoodsLockDetailData(lockId);
        for (GoodsLockDetail goodsLockDetail : goodsLockDetailList) {
            List<GoodsLockShelf> goodsLockShelfList = goodsLockShelfMapper.getGoodsLockShelfData(goodsLockDetail.getId());
            goodsLockDetail.setGoodsLockShelfList(goodsLockShelfList);
        }
        goodsLock.setGoodsLockDetailList(goodsLockDetailList);
        return goodsLock;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int save(GoodsLockSaveOrupdateVO goodsLockSaveOrupdateVO, UserVO userVO) throws Exception {

        // 检验是否有商品明细
        //封装商品明细表数据
        List<GoodsLockDetailSaveOrupdateVO> goodsLockDetailSaveOrupdateVOList = goodsLockSaveOrupdateVO.getGoodsLockDetailSaveOrupdateVOList();

        if(goodsLockDetailSaveOrupdateVOList == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"请添加具体锁定的药品");
        }

        if(goodsLockDetailSaveOrupdateVOList.size() <= 0){
            throw new BusinessException(SysCode.FAIL.getCode(),"请添加具体锁定的药品");
        }


        GoodsLock goodsLock = (GoodsLock) EntityUtils.reflectAddSetDefaultValue(new GoodsLock().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLockSaveOrupdateVO, goodsLock);
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl = manage.getBusinessControl() == 0 ? true : false;
        if (zl) {
            //质量流程未开的时候前台必须提供采购人员id
            User user = userMapper.selectByPrimaryKey(goodsLock.getLockManId());
            if (user == null) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            if (user.getEnterpriseId().intValue() != userVO.getEnterpriseId().intValue()) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            goodsLock.setLockDate(DateUtils.StringToDate(goodsLockSaveOrupdateVO.getLockDateStr()));
            goodsLock.setLockManId(user.getId());
            goodsLock.setLockManCode(user.getCode());
            goodsLock.setLockManName(user.getName());
        } else {
            goodsLock.setLockDate(new Date());
            goodsLock.setLockManId(userVO.getUserId());
            goodsLock.setLockManCode(userVO.getUserCode());
            goodsLock.setLockManName(userVO.getUserName());
        }
        //设置锁定单号
        goodsLock.setCode(orderCodeComponent.generate(OrderRule.LOCK.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        goodsLock.setOrderType(OrderRule.LOCK.getType());
        goodsLock.setStatus(PurchaseStatus.WAIT_PAY.getStatus());
        //设置相关单据信息
        if (goodsLock.getBaseOrderId() != null) {
            StopSale stopSale = stopSaleMapper.selectByPrimaryKey(goodsLock.getBaseOrderId());
            if (stopSale == null) {
                throw new BusinessException("无法找到相关单据信息,请核对相关单据ID=" + goodsLock.getBaseOrderId());
            }
            goodsLock.setBaseOrderCode(stopSale.getCode());
            goodsLock.setBaseOrderDate(stopSale.getStopDate());
            goodsLock.setBaseOrderType(stopSale.getOrderType());
        }



        List<GoodsLockDetail> goodsLockDetailList = new ArrayList<>();
        BigDecimal quantityAmount = new BigDecimal(0);
        for (GoodsLockDetailSaveOrupdateVO goodsLockDetailSaveOrupdateVO : goodsLockDetailSaveOrupdateVOList) {
            //封装商品货位数据
            GoodsLockDetail goodsLockDetail = new GoodsLockDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLockDetailSaveOrupdateVO, goodsLockDetail);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLock, goodsLockDetail);
            //设置相关单据明细信息
            if (goodsLockDetail.getBaseOrderDtlId() != null) {
                StopSaleDetail stopSaleDetail = stopSaleDetailMapper.selectByPrimaryKey(goodsLockDetail.getBaseOrderDtlId());
                if (stopSaleDetail == null) {
                    throw new BusinessException("无法找到相关单据明细信息,请核对相关单据明细Id=" + goodsLockDetail.getBaseOrderDtlId());
                }
            }

            long gid = goodsLockDetail.getGoodsId();
            //查询药品信息
            GoodsVO goods = goodsMapper.selectGoodsInfoById(gid);
            if (goods == null) {
                throw new BusinessException("没有该商品，请核实商品ID=" + gid);
            }
            //填充药品信息
            this.fillGoodsInfo(goods, goodsLockDetail);
            BigDecimal quantity = new BigDecimal(0);
            List<GoodsLockShelfSaveOrupdateVO> goodsLockShelfSaveOrupdateVOList = goodsLockDetailSaveOrupdateVO.getGoodsLockShelfSaveOrupdateVOList();
            List<GoodsLockShelf> goodsLockShelfList = new ArrayList<>();
            for (GoodsLockShelfSaveOrupdateVO goodsLockShelfSaveOrupdateVO : goodsLockShelfSaveOrupdateVOList) {
                GoodsLockShelf goodsLockShelf = new GoodsLockShelf();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLockShelfSaveOrupdateVO, goodsLockShelf);
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLockDetail, goodsLockShelf);
                //根据货位id查询货位信息
                GoodsInfoStockShelfVO goodsInfoStockShelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(userVO.getEnterpriseId(),
                        goodsLockShelf.getGoodsId(),
                        goodsLockShelf.getShelfId(),
                        goodsLockShelf.getLotId());
                if (goodsInfoStockShelfVO == null) {
                    throw new BusinessException("货位信息不存在,请检查货位ID=" + goodsLockShelf.getShelfId());
                }
                goodsLockShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                goodsLockShelf.setValidDate(goodsInfoStockShelfVO.getValidUtil());
                goodsLockShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                goodsLockShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
                goodsLockShelf.setLotId(goodsInfoStockShelfVO.getLotNumberId());
                goodsLockShelf.setShelfStatusDesc(goodsInfoStockShelfVO.getShelfStatusDesc());
                quantity = quantity.add(goodsLockShelf.getQuantity());
                goodsLockShelfList.add(goodsLockShelf);
            }
            goodsLockDetail.setQuantity(quantity);
            goodsLockDetail.setGoodsLockShelfList(goodsLockShelfList);
            quantityAmount = quantityAmount.add(quantity);
            goodsLockDetailList.add(goodsLockDetail);
        }
        goodsLock.setQuantityTotal(quantityAmount);
        goodsLock.setVarietiesQuantity(goodsLockDetailList.size());
        goodsLock.setGoodsLockDetailList(goodsLockDetailList);
        goodsLockMapper.insertSelective(goodsLock);
        Long goodsLockId = goodsLock.getId();

        List<StopSaleDtlRequestVO> stopSaleDtlRequestVOList = new ArrayList<>();
        for (GoodsLockDetail goodsLockDetail : goodsLock.getGoodsLockDetailList()) {
            //封装禁售单数据
            StopSaleDtlRequestVO stopSaleDtlRequestVO = new StopSaleDtlRequestVO();
            stopSaleDtlRequestVO.setGoodsId(goodsLockDetail.getGoodsId());
            stopSaleDtlRequestVO.setLineNum(goodsLockDetail.getLineNum());
            Integer lockReason = goodsLock.getLockReason();
            String lockResonStr = null;//0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品
            if (lockReason == 0) {
                lockResonStr = "疑似质量问题";
            } else if (lockReason == 1) {
                lockResonStr = "疑似伪劣商品";
            } else if (lockReason == 3) {
                lockResonStr = "药品养护问题商品";
            } else if (lockReason == 4) {
                lockResonStr = "陈列检查问题商品";
            }
            stopSaleDtlRequestVO.setStopReason(lockResonStr);
            //设置锁定单明细信息
            goodsLockDetail.setLockId(goodsLockId);
            goodsLockDetail.setLockCode(goodsLock.getCode());
            goodsLockDetailMapper.insertSelective(goodsLockDetail);

            List<StopSaleShelfRequestVO> stopSaleShelfRequestVOList = new ArrayList<>();
            for (GoodsLockShelf goodsLockShelf : goodsLockDetail.getGoodsLockShelfList()) {
                //封装禁售单数据
                StopSaleShelfRequestVO stopSaleShelfRequestVO = new StopSaleShelfRequestVO();
                stopSaleShelfRequestVO.setLineNum(goodsLockShelf.getLineNum());
                stopSaleShelfRequestVO.setLotId(goodsLockShelf.getLotId());
                stopSaleShelfRequestVO.setQuantity(goodsLockShelf.getQuantity());
                stopSaleShelfRequestVO.setShelfId(goodsLockShelf.getShelfId());
                //设置锁定单货位信息
                goodsLockShelf.setLockId(goodsLockId);
                goodsLockShelf.setDtlId(goodsLockDetail.getId());
                goodsLockShelfMapper.insertSelective(goodsLockShelf);

                // 生成锁定记录
                genereteStcokLockRecord(goodsLock, goodsLockShelf);

                //根据商品id,锁定数量,货位id,批次id,企业id 修改商品锁定数量和可用数量
                int i = stockMapper.updateLockQuantity(
                        goodsLockShelf.getGoodsId(),
                        goodsLockShelf.getQuantity(),
                        goodsLockShelf.getShelfId(),
                        goodsLockShelf.getLotId(),
                        userVO.getEnterpriseId(),
                        userVO.getUserId(),
                        userVO.getUserName(),
                        userVO.getUserCode(),
                        new Date());

                // 生成成本锁定记录
                genereteCostLockRecord(goodsLock, goodsLockShelf, userVO);

                if (i != 1) {
                    throw new BusinessException("数据异常,无法锁定该商品,请检查商品可用数量");
                }
                stopSaleShelfRequestVOList.add(stopSaleShelfRequestVO);
            }
            stopSaleDtlRequestVO.setStopSaleShelfRequestVOList(stopSaleShelfRequestVOList);
            stopSaleDtlRequestVOList.add(stopSaleDtlRequestVO);
        }
        if (goodsLock.getBaseOrderId() == null) {
            //创建禁售单,发送通知
            StopSaleRequestVO stopSaleRequestVO = new StopSaleRequestVO();
            stopSaleRequestVO.setStopDate(new Date());
            stopSaleRequestVO.setStopManId(goodsLock.getLockManId());
            stopSaleRequestVO.setStopFrom(StopSaleFromType.GOODS_LOCK.getCode());
            stopSaleRequestVO.setBaseOrderCode(goodsLock.getCode());
            stopSaleRequestVO.setBaseOrderId(goodsLock.getId());
            stopSaleRequestVO.setBaseOrderType(goodsLock.getOrderType());
            stopSaleRequestVO.setBaseOrderDate(goodsLock.getLockDate());
            stopSaleRequestVO.setStopSaleDtlRequestVOList(stopSaleDtlRequestVOList);
            try {
                stopSaleService.saveStopSale(userVO, stopSaleRequestVO);
            } catch (Exception e) {
                throw new BusinessException("创建禁售单,发送通知失败:" + e.getMessage());
            }
        }
        return 1;
    }

    private void genereteCostLockRecord(GoodsLock goodsLock, GoodsLockShelf goodsLockShelf, UserVO user) {
        Long enterpriseId = goodsLock.getEnterpriseId();
        Long parentId = goodsLock.getParentId();
        Long goodsId = goodsLockShelf.getGoodsId();
        Long lotId = goodsLockShelf.getLotId();
        Long baseOrderId = goodsLock.getId();
        Integer baseOrderType = goodsLock.getOrderType();
        String baseOrderCode = goodsLock.getCode();
        Date baseOrderDate = goodsLock.getLockDate();
        BigDecimal quantity = goodsLockShelf.getQuantity();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("parentId", parentId);
        paramMap.put("goodsId", goodsId);
        paramMap.put("lotId", lotId);
        List<Cost> costList = costMapper.selectByParamMapWithFIFO(paramMap);

        commonComponent.doLockCost(enterpriseId, parentId, baseOrderId, baseOrderType, baseOrderCode, baseOrderDate, user, quantity, costList);
    }

    private void genereteStcokLockRecord(GoodsLock goodsLock, GoodsLockShelf goodsLockShelf) {
        StockLockRecord stockLockRecord = new StockLockRecord();
        stockLockRecord.setEnterpriseId(goodsLockShelf.getEnterpriseId());
        stockLockRecord.setParentId(goodsLockShelf.getParentId());
        stockLockRecord.setBaseOrderId(goodsLock.getId());
        stockLockRecord.setBaseOrderType(goodsLock.getOrderType());
        stockLockRecord.setBaseOrderCode(goodsLock.getCode());
        stockLockRecord.setBaseOrderDate(goodsLock.getLockDate());
        stockLockRecord.setGoodsId(goodsLockShelf.getGoodsId());
        stockLockRecord.setGoodsCode(goodsLockShelf.getGoodsCode());
        stockLockRecord.setGoodsName(goodsLockShelf.getGoodsName());
        stockLockRecord.setLotId(goodsLockShelf.getLotId());
        stockLockRecord.setLotNum(goodsLockShelf.getLotNumber());
        stockLockRecord.setShelfId(goodsLockShelf.getShelfId());
        stockLockRecord.setShelfName(goodsLockShelf.getShelfName());
        stockLockRecord.setLockQuantity(goodsLockShelf.getQuantity());
        stockLockRecord.setCreaterId(goodsLockShelf.getCreaterId());
        stockLockRecord.setCreaterCode(goodsLockShelf.getCreaterCode());
        stockLockRecord.setCreaterName(goodsLockShelf.getCreaterName());
        stockLockRecord.setCreateTime(new Date());
        stockLockRecordMapper.insertSelective(stockLockRecord);
    }

    /*private void lockStock(UserVO userVO, GoodsLockDetail detail) {
        LockQtyArgVO lockStockVO = new LockQtyArgVO();
        lockStockVO.setEnterpriseId(detail.getEnterpriseId());
        lockStockVO.setParentId(detail.getParentId());
        lockStockVO.setBaseOrderId(detail.getLockId());
        lockStockVO.setBaseOrderCode(detail.getLockCode());
        lockStockVO.setBaseOrderType(detail.getOrderType());
        lockStockVO.setBaseOrderDate(detail.getLockDate());
        lockStockVO.setGoodsId(detail.getGoodsId());
        lockStockVO.setLotId(detail.getLotId());
        lockStockVO.setQuantity(detail.getQuantity());
        lockStockVO.setUser(userVO);
        commonComponent.lockStockAndCost(lockStockVO);
    }*/

    /**
     * <药品锁定单明细填充药品基础信息>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/23 16:38
     */
    private void fillGoodsInfo(GoodsVO goods, GoodsLockDetail goodsLockDetail) {
        goodsLockDetail.setGoodsCode(goods.getCode());
        goodsLockDetail.setBarcode(goods.getBarcode());
        goodsLockDetail.setGoodsName(goods.getName());
        goodsLockDetail.setGoodsGenericName(goods.getGenericName());
        goodsLockDetail.setDosageId(goods.getDosageId());
        goodsLockDetail.setDosageName(goods.getDosageName());
        goodsLockDetail.setUnitId(goods.getUnitId());
        goodsLockDetail.setUnitName(goods.getUnitName());
        goodsLockDetail.setGoodsSpecification(goods.getSpecification());
        goodsLockDetail.setManufacturerId(goods.getManufacturerId());
        goodsLockDetail.setManufacturer(goods.getManufacturer());
        goodsLockDetail.setGoodsPlace(goods.getPlace());
        goodsLockDetail.setApprovalNumber(goods.getApprovalNumber());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int update(GoodsLockSaveOrupdateVO goodsLock, UserVO userVO) throws Exception {
        GoodsLock copy = (GoodsLock) EntityUtils.reflectUpdateSetDefaultValue(new GoodsLock().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLock, copy);
        return goodsLockMapper.updateByPrimaryKeySelective(copy);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int delete(Long id) throws Exception {
        return goodsLockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void getGoodsLockDateByParam(RequestGoodsLockListVo requestGoodsLockListVo, Page page) {
        int count = goodsLockMapper.countGoodsLockDateByParam(requestGoodsLockListVo);
        Integer sortDate=requestGoodsLockListVo.getSortDate();
        Integer sortCode=requestGoodsLockListVo.getSortCode();
        String sort="";
        if(sortDate==null&&sortCode==null){
            sort="a.lock_date desc,a.code desc,";
        }
        if(sortDate!=null&&sortDate==0){
            sort+="a.lock_date,";
        }
        if(sortDate!=null&&sortDate==1){
            sort+="a.lock_date desc,";
        }
        if(sortCode!=null&&sortCode==0){
            sort+="a.code,";
        }
        if(sortCode!=null&&sortCode==1){
            sort+="a.code desc,";
        }
        if(!"".equals(sort)){
            sort=sort.substring(0,sort.length()-1);
        }
        requestGoodsLockListVo.setSort(sort);
        List<GoodsLock> list = goodsLockMapper.getGoodsLockDateByParam(requestGoodsLockListVo);
        page.setTotalRecord(count);
        page.setResult(list);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int cancelGoodsLock(UserVO userVO, Long lockId, boolean flag) {
        GoodsLock goodsLock = this.getGoodsLockData(lockId);
        if (goodsLock.getStatus().intValue() != PurchaseStatus.WAIT_PAY.getStatus().intValue()) {
            throw new BusinessException("该锁定单状态已过期,请刷新后重试");
        }
        goodsLock.setModifierCode(userVO.getUserCode());
        goodsLock.setModifierId(userVO.getUserId());
        goodsLock.setModifierName(userVO.getUserName());
        goodsLock.setStatus(PurchaseStatus.CANCELED.getStatus());
        goodsLock.setUpdateTime(new Date());
        //解除锁定通知数据封装
        StartSaleRequestVO startSaleRequestVO = new StartSaleRequestVO();
        startSaleRequestVO.setBaseOrderCode(goodsLock.getCode());
        startSaleRequestVO.setBaseOrderDate(goodsLock.getLockDate());
        startSaleRequestVO.setBaseOrderId(goodsLock.getId());
        startSaleRequestVO.setBaseOrderType(goodsLock.getOrderType());
        startSaleRequestVO.setStartFrom(StartSaleFromType.GOODS_HANDLE.getCode());
        startSaleRequestVO.setStartDate(new Date());
        startSaleRequestVO.setStartManId(userVO.getUserId());
        List<StartSaleDtlRequestVO> startSaleDtlRequestVOList = new ArrayList<>();
        for (GoodsLockDetail goodsLockDetail : goodsLock.getGoodsLockDetailList()) {
            StartSaleDtlRequestVO startSaleDtlRequestVO = new StartSaleDtlRequestVO();
            startSaleDtlRequestVO.setGoodsId(goodsLockDetail.getGoodsId());
            startSaleDtlRequestVO.setLineNum(goodsLockDetail.getLineNum());
            startSaleDtlRequestVO.setQuantity(goodsLockDetail.getQuantity());
            startSaleDtlRequestVO.setStartReason("药品锁定单取消");
            List<StartSaleShelfRequestVO> startSaleShelfRequestVOList = new ArrayList<>();
            for (GoodsLockShelf goodsLockShelf : goodsLockDetail.getGoodsLockShelfList()) {
                StartSaleShelfRequestVO startSaleShelfRequestVO = new StartSaleShelfRequestVO();
                startSaleShelfRequestVO.setLineNum(goodsLockShelf.getLineNum());
                startSaleShelfRequestVO.setLotId(goodsLockShelf.getLotId());
                startSaleShelfRequestVO.setQuantity(goodsLockShelf.getQuantity());
                startSaleShelfRequestVO.setShelfId(goodsLockShelf.getShelfId());
                startSaleShelfRequestVOList.add(startSaleShelfRequestVO);
                //解除锁定数量

//                int j = stockMapper.updateUnLockQuantity(
//                        goodsLockShelf.getGoodsId(),
//                        goodsLockShelf.getQuantity(),
//                        goodsLockShelf.getShelfId(),
//                        goodsLockShelf.getLotId(),
//                        userVO.getEnterpriseId(),
//                        userVO.getUserId(),
//                        userVO.getUserName(),
//                        userVO.getUserCode(),
//                        new Date());
//                if (j != 1) {
//                    throw new BusinessException("数据异常,无法解锁该商品,请检查商品锁定数量");
//                }
            }
            startSaleDtlRequestVO.setStartSaleShelfRequestVOList(startSaleShelfRequestVOList);
            startSaleDtlRequestVOList.add(startSaleDtlRequestVO);
        }
        //创建解停通知单,发送通知
        if (flag) {
            try {
                startSaleRequestVO.setStartSaleDtlRequestVOList(startSaleDtlRequestVOList);
                startSaleService.saveStartSale(userVO, startSaleRequestVO);
            } catch (Exception e) {
                throw new BusinessException("创建解停通知单,发送通知错误:" + e.getMessage());
            }
        }

        // --------- dongyang.du   ------------------
        // --------- 释放锁定的库存  ------------------
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setBaseOrderId(goodsLock.getId());
        lockQtyArgVO.setBaseOrderType(goodsLock.getOrderType());
        lockQtyArgVO.setEnterpriseId(goodsLock.getEnterpriseId());
        lockQtyArgVO.setParentId(goodsLock.getParentId());
        commonComponent.releaseStockAndCost(lockQtyArgVO);
        //--------------------------------------------

        int i = goodsLockMapper.updateGoodsStockStatus(goodsLock);
        return i;
    }

    /**
     * <导出商品锁定单>
     *
     * @param goodsLock
     * @param userVO
     * @param output
     * @param name
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 10:20
     */
    @Override
    public void exportExcel(GoodsLock goodsLock, UserVO userVO, OutputStream output, String name) {
        //标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("商品锁定");
        //表头数据
        List<String> titleSecond = new ArrayList<>();
        StringBuffer title = new StringBuffer();
        String lockResonStr = null;//0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品
        Integer lockReason = goodsLock.getLockReason();
        if (lockReason == 0) {
            lockResonStr = "疑似质量问题";
        } else if (lockReason == 1) {
            lockResonStr = "疑似伪劣商品";
        } else if (lockReason == 3) {
            lockResonStr = "药品养护问题商品";
        } else if (lockReason == 4) {
            lockResonStr = "陈列检查问题商品";
        }
        title.append("锁定单号:").append(goodsLock.getCode())
                .append("   锁定日期:").append(DateUtils.DateToString(goodsLock.getLockDate(), "yyyy-MM-dd"))
                .append("   锁定人员:").append(goodsLock.getLockManName())
                .append("   锁定原因:").append(lockResonStr);
        titleSecond.add(title.toString());
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("manufacturer", "生产厂商");
        map.put("unitName", "单位");
        map.put("lotNumber", "批号");
        map.put("productDateStr", "生产日期");
        map.put("validDateStr", "有效期至");
        map.put("shelfName", "货位");
        map.put("quantity", "数量");
        List<GoodsLockExcelVO> goodsLockExcelVOList = new ArrayList<>();
        for (GoodsLockDetail goodsLockDetail : goodsLock.getGoodsLockDetailList()) {
            for (GoodsLockShelf goodsLockShelf : goodsLockDetail.getGoodsLockShelfList()) {
                GoodsLockExcelVO goodsLockExcelVO = new GoodsLockExcelVO();
                goodsLockExcelVO.setGoodsCode(goodsLockDetail.getGoodsCode());
                goodsLockExcelVO.setGoodsGenericName(goodsLockDetail.getGoodsGenericName());
                goodsLockExcelVO.setDosageName(goodsLockDetail.getDosageName());
                goodsLockExcelVO.setGoodsSpecification(goodsLockDetail.getGoodsSpecification());
                goodsLockExcelVO.setManufacturer(goodsLockDetail.getManufacturer());
                goodsLockExcelVO.setUnitName(goodsLockDetail.getUnitName());
                goodsLockExcelVO.setLotNumber(goodsLockShelf.getLotNumber());
                goodsLockExcelVO.setProductDate(goodsLockShelf.getProductDate());
                goodsLockExcelVO.setValidDate(goodsLockShelf.getValidDate());
                goodsLockExcelVO.setShelfName(goodsLockShelf.getShelfName());
                goodsLockExcelVO.setQuantity(goodsLockShelf.getQuantity());
                goodsLockExcelVOList.add(goodsLockExcelVO);
            }
        }
        purchaseGeneralComponent.commExcelExport(output, map, goodsLockExcelVOList, names, titleSecond, "", true, new ArrayList<>());
    }

    @Override
    public List<SelectPricingGoodsVO> selectGoodsByParam(GoodsParamStockVO goodsParamStockVO) {

        List<SelectPricingGoodsVO> list = goodsLockMapper.selectGoodsByParam(goodsParamStockVO);
        list.forEach(item->{
            item.setProductDateStr(DateUtils.DateToString(item.getProductDate(), DateStyle.YYYY_MM_DD));
            item.setValidUntilStr(DateUtils.DateToString(item.getValidUntil(), DateStyle.YYYY_MM_DD));
        });
        return list;
    }

}
