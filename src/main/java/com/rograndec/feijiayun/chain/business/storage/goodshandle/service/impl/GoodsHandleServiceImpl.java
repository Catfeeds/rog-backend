package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.CostLockRecord;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.entity.StockLockRecord;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.*;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.*;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsHandleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StartSaleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.StartSaleFromType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: GoodsHandleServiceImpl
 * @Description: 储存管理-商品处理-药品处理-实现接口
 * @date 2017-09-27 17:27:38
 */
@Service
public class GoodsHandleServiceImpl implements GoodsHandleService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsHandleServiceImpl.class);

    @Autowired
    private GoodsHandleMapper goodsHandleMapper;
    @Autowired
    private GoodsHandleDetailMapper goodsHandleDetailMapper;
    @Autowired
    private GoodsHandleShelfMapper goodsHandleShelfMapper;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private StartSaleMapper startSaleMapper;
    @Autowired
    private StartSaleDetailMapper startSaleDetailMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StartSaleService startSaleService;
    @Autowired
    private GoodsLockMapper goodsLockMapper;
    @Autowired
    private StockLockRecordMapper stockLockRecordMapper;
    @Autowired
    private GoodsLockShelfMapper goodsLockShelfMapper;
    @Autowired
    private GoodsLockDetailMapper goodsLockDetailMapper;

    @Autowired
    private CostLockRecordMapper costLockRecordMapper;
    @Autowired
    private CostMapper costMapper;

    @Override
    public GoodsHandle getGoodsHandleData(Long handleId) {
        GoodsHandle goodsHandle = goodsHandleMapper.selectByPrimaryKey(handleId);
        if (goodsHandle == null) {
            return null;
        }
        List<GoodsHandleDetail> goodsHandleDetailList = goodsHandleDetailMapper.getGoodsHandleDetailByHandleId(handleId);
        for (GoodsHandleDetail goodsHandleDetail : goodsHandleDetailList) {
            List<GoodsHandleShelf> goodsHandleShelfList = goodsHandleShelfMapper.getGoodsHandleShelfByDtId(goodsHandleDetail.getId());
            goodsHandleDetail.setGoodsHandleShelfList(goodsHandleShelfList);
        }
        goodsHandle.setGoodsHandleDetailList(goodsHandleDetailList);
        return goodsHandle;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int save(GoodsHandleSaveOrupdateVO goodsHandleVo, UserVO userVO) throws Exception,BusinessException {
        GoodsHandle goodsHandle = (GoodsHandle) EntityUtils.reflectAddSetDefaultValue(new GoodsHandle().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandleVo, goodsHandle);
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl = manage.getBusinessControl() == 0 ? true : false;
        if (zl) {
            //质量流程未开的时候前台必须提供采购人员id
            User user = userMapper.selectByPrimaryKey(goodsHandle.getHandleManId());
            if (user == null) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            if (user.getEnterpriseId().intValue() != userVO.getEnterpriseId().intValue()) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            goodsHandle.setHandleDate(DateUtils.StringToDate(goodsHandleVo.getHandleDateStr()));
            goodsHandle.setHandleManId(user.getId());
            goodsHandle.setHandleManCode(user.getCode());
            goodsHandle.setHandleManName(user.getName());
        } else {
            goodsHandle.setHandleDate(new Date());
            goodsHandle.setHandleManId(userVO.getUserId());
            goodsHandle.setHandleManCode(userVO.getUserCode());
            goodsHandle.setHandleManName(userVO.getUserName());
        }
        //设置锁定单号
        goodsHandle.setCode(orderCodeComponent.generate(OrderRule.HANDLE.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        goodsHandle.setOrderType(OrderRule.HANDLE.getType());
        goodsHandle.setStatus(PurchaseStatus.FINISHED.getStatus());

        //封装商品明细表数据
        List<GoodsHandleDetailSaveOrupdateVO> goodshandleDetailSaveOrupdateVOList = goodsHandleVo.getGoodsHandleDetailSaveOrupdateVOList();
        List<GoodsHandleDetail> goodsHandleDetailList = new ArrayList<>();
        BigDecimal quantityAmount = new BigDecimal(0);
        for (GoodsHandleDetailSaveOrupdateVO goodsHandleDetailSaveOrupdateVO : goodshandleDetailSaveOrupdateVOList) {
            //封装商品货位数据
            GoodsHandleDetail goodsHandleDetail = new GoodsHandleDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandleDetailSaveOrupdateVO, goodsHandleDetail);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandle, goodsHandleDetail);
            //设置相关单据明细信息
            if (goodsHandleDetail.getBaseOrderDtlId() != null) {
                StartSaleDetail startSaleDetail = startSaleDetailMapper.selectByPrimaryKey(goodsHandleDetail.getBaseOrderDtlId());
                if (startSaleDetail == null) {
                    throw new BusinessException("无法找到相关单据明细信息,请核对相关单据明细Id=" + goodsHandleDetail.getBaseOrderDtlId());
                }
            }

            long gid = goodsHandleDetail.getGoodsId();
            //查询药品信息
            GoodsVO goods = goodsMapper.selectGoodsInfoById(gid);
            if (goods == null) {
                throw new BusinessException("没有该商品，请核实商品ID=" + gid);
            }
            //填充药品信息
            this.fillGoodsInfo(goods, goodsHandleDetail);
            BigDecimal quantity = new BigDecimal(0);
            List<GoodsHandleShelfSaveOrupdateVO> goodsHandleShelfSaveOrupdateVOList = goodsHandleDetailSaveOrupdateVO.getGoodsHandleShelfSaveOrupdateVOList();
            List<GoodsHandleShelf> goodsHandleShelfList = new ArrayList<>();
            for (GoodsHandleShelfSaveOrupdateVO goodsHandleShelfSaveOrupdateVO : goodsHandleShelfSaveOrupdateVOList) {
                GoodsHandleShelf goodsHandleShelf = new GoodsHandleShelf();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandleShelfSaveOrupdateVO, goodsHandleShelf);
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandleDetail, goodsHandleShelf);
                //根据货位id查询货位信息
                GoodsInfoStockShelfVO goodsInfoStockShelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(userVO.getEnterpriseId(),
                        goodsHandleShelf.getGoodsId(),
                        goodsHandleShelf.getShelfId(),
                        goodsHandleShelf.getLotId());
                if (goodsInfoStockShelfVO == null) {
                    throw new BusinessException("货位信息不存在,请检查货位ID=" + goodsHandleShelf.getShelfId());
                }
                goodsHandleShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                goodsHandleShelf.setValidDate(goodsInfoStockShelfVO.getValidUtil());
                goodsHandleShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                goodsHandleShelf.setShelfStatusDesc(goodsInfoStockShelfVO.getShelfStatusDesc());
                goodsHandleShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
                goodsHandleShelf.setLotId(goodsInfoStockShelfVO.getLotNumberId());
                goodsHandleShelf.setLockId(goodsHandleShelfSaveOrupdateVO.getLockId());
                quantity = quantity.add(goodsHandleShelf.getQuantity());
                goodsHandleShelfList.add(goodsHandleShelf);
            }
            goodsHandleDetail.setQuantity(quantity);
            goodsHandleDetail.setGoodsHandleShelfList(goodsHandleShelfList);
            quantityAmount = quantityAmount.add(quantity);
            goodsHandleDetailList.add(goodsHandleDetail);
        }
        goodsHandle.setQuantityTotal(quantityAmount);
        goodsHandle.setVarietiesQuantity(goodsHandleDetailList.size());
        goodsHandle.setGoodsHandleDetailList(goodsHandleDetailList);
        goodsHandleMapper.insertSelective(goodsHandle);
        /**
         * 更新商品锁定记录
         */

        Long goodsHandleId = goodsHandle.getId();
        StartSaleRequestVO startSaleRequestVO = new StartSaleRequestVO();
        startSaleRequestVO.setBaseOrderCode(goodsHandle.getCode());
        startSaleRequestVO.setBaseOrderDate(goodsHandle.getHandleDate());
        startSaleRequestVO.setBaseOrderId(goodsHandle.getId());
        startSaleRequestVO.setBaseOrderType(goodsHandle.getOrderType());
        startSaleRequestVO.setStartFrom(StartSaleFromType.GOODS_HANDLE.getCode());
        startSaleRequestVO.setStartDate(new Date());
        startSaleRequestVO.setStartManId(userVO.getUserId());
        List<StartSaleDtlRequestVO> startSaleDtlRequestVOList = new ArrayList<>();
        for (GoodsHandleDetail goodsHandleDetail : goodsHandle.getGoodsHandleDetailList()) {
            goodsHandleDetail.setHandleCode(goodsHandle.getCode());
            goodsHandleDetail.setHandleId(goodsHandleId);
            goodsHandleDetailMapper.insertSelective(goodsHandleDetail);
            StartSaleDtlRequestVO startSaleDtlRequestVO = new StartSaleDtlRequestVO();
            startSaleDtlRequestVO.setGoodsId(goodsHandleDetail.getGoodsId());
            startSaleDtlRequestVO.setLineNum(goodsHandleDetail.getLineNum());
            startSaleDtlRequestVO.setQuantity(goodsHandleDetail.getQuantity());
            startSaleDtlRequestVO.setStartReason("药品处理解除锁定");
            List<StartSaleShelfRequestVO> startSaleShelfRequestVOList = new ArrayList<>();
            for (GoodsHandleShelf goodsHandleShelf : goodsHandleDetail.getGoodsHandleShelfList()) {
                StartSaleShelfRequestVO startSaleShelfRequestVO = new StartSaleShelfRequestVO();
                startSaleShelfRequestVO.setLineNum(goodsHandleShelf.getLineNum());
                startSaleShelfRequestVO.setLotId(goodsHandleShelf.getLotId());
                startSaleShelfRequestVO.setQuantity(goodsHandleShelf.getQuantity());
                startSaleShelfRequestVO.setShelfId(goodsHandleShelf.getShelfId());
                startSaleShelfRequestVOList.add(startSaleShelfRequestVO);
                //设置锁定单货位信息
                goodsHandleShelf.setHandleId(goodsHandleId);
                goodsHandleShelf.setDtlId(goodsHandleDetail.getId());
                goodsHandleShelfMapper.insertSelective(goodsHandleShelf);

                //释放库存锁定记录
                releaseStockAndCost(goodsHandleShelf,goodsHandle,userVO);
                //更新saas_stock_lock_record数据
                //updateStockLockRecord(goodsHandleShelf,goodsHandle,userVO);

                if (goodsHandle.getHandleResult() == 0) {
                    //解除锁定数量
//                    int j = stockMapper.updateUnLockQuantity(
//                            goodsHandleShelf.getGoodsId(),
//                            goodsHandleShelf.getQuantity(),
//                            goodsHandleShelf.getShelfId(),
//                            goodsHandleShelf.getLotId(),
//                            userVO.getEnterpriseId(),
//                            userVO.getUserId(),
//                            userVO.getUserName(),
//                            userVO.getUserCode(),
//                            new Date());
//                    if (j != 1) {
//                        throw new BusinessException("数据异常,无法解锁该商品,请检查商品锁定数量");
//                    }

                    //更新成本锁定记录saas_cost_lock_record数据
                    //updateCostLockRecord(goodsHandleShelf,goodsHandle,userVO);

                    startSaleDtlRequestVO.setStartSaleShelfRequestVOList(startSaleShelfRequestVOList);
                    startSaleDtlRequestVOList.add(startSaleDtlRequestVO);
                }

            }

        }


        //创建解停通知单,发送通知
        if (goodsHandle.getBaseOrderId() == null && goodsHandle.getHandleResult() == 0) {
            try {
                startSaleRequestVO.setStartSaleDtlRequestVOList(startSaleDtlRequestVOList);
                startSaleService.saveStartSale(userVO, startSaleRequestVO);
            } catch (Exception e) {
                throw new BusinessException("创建解停通知单,发送通知错误:" + e.getMessage());
            }
        }
        return 1;

    }

    /**
     * @auther  dongyang.du
     * 根据处理方式释放库存或者生成新的锁定记录
     * @param goodsHandleShelf
     * @param goodsHandle
     * @param userVO
     */
    private void releaseStockAndCost(GoodsHandleShelf goodsHandleShelf, GoodsHandle goodsHandle, UserVO userVO) throws Exception{

        //0 - 解除锁定 1 - 移动到不合格货位
        Integer handleResult = goodsHandle.getHandleResult();
        GoodsLock goodsLock = goodsLockMapper.selectByPrimaryKey(goodsHandleShelf.getLockId());
        StockLockRecord stockLockRecord = stockLockRecordMapper.selectByGoodsIdAndLotIdAndShelfId(goodsLock.getId(),goodsHandleShelf.getGoodsId(),goodsHandleShelf.getLotId(),goodsHandleShelf.getShelfId(),goodsHandleShelf.getEnterpriseId(),goodsLock.getCode());
        if(stockLockRecord == null){
            throw new BusinessException("没有查询到上级单据锁定库存信息！");
        }
        if((goodsHandleShelf.getQuantity().subtract(stockLockRecord.getLockQuantity())).compareTo(BigDecimal.ZERO) > 0){
            throw new BusinessException("处理数量不能超出原锁定商品数量！");
        }
        int num = stockLockRecord.getLockQuantity().compareTo(goodsHandleShelf.getQuantity());

        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId", goodsHandle.getEnterpriseId());
        map.put("parentId", goodsHandle.getParentId());
        map.put("baseOrderId", goodsLock.getId());// 锁定单的id
        map.put("baseOrderType", goodsLock.getOrderType());// 锁定单的Type
        map.put("baseOrderCode", goodsLock.getCode());
        map.put("goodsId", goodsHandleShelf.getGoodsId());
        map.put("lotId", goodsHandleShelf.getLotId());

        List<CostLockRecord> costLockRecordList = costLockRecordMapper.selectByParamMap(map);

        if(handleResult == 0){// 解除锁定，更新库存和成本表的可用库存
            // 库存表
            updateStock(stockLockRecord,goodsHandleShelf.getQuantity());
            // 成本表
            updateCost(costLockRecordList,goodsHandleShelf.getQuantity());// 处理的数量
        } else if (handleResult == 1){// 生成相应的商品处理锁定单据
            insertStockLockRecord(goodsHandle,goodsHandleShelf,userVO);
            insertCostLockRecord(costLockRecordList,goodsHandle,goodsHandleShelf,userVO);
        } else {
            throw  new  BusinessException("商品处理方式不存在，请检查");
        }


        if(num > 0){ // 锁定数量 > 处理数量，更新锁定的数量

            // 库存锁定记录表
            BigDecimal remainderLockQuantity = stockLockRecord.getLockQuantity().subtract(goodsHandleShelf.getQuantity());
            stockLockRecord.setLockQuantity(remainderLockQuantity);
            stockLockRecordMapper.updateByPrimaryKeySelective(stockLockRecord);
            // 成本锁定记录表

            for(CostLockRecord costLockRecord:costLockRecordList){// 应该就一条
                // 更新成本锁定记录
                costLockRecord.setLockQuantity(remainderLockQuantity);
                costLockRecord.setCreaterId(userVO.getUserId());
                costLockRecord.setCreaterCode(userVO.getUserCode());
                costLockRecord.setCreaterName(userVO.getUserName());
                costLockRecord.setCreateTime(new Date());
                costLockRecordMapper.updateByPrimaryKey(costLockRecord);
            }


        } else if (num == 0){ // 锁定数量 == 处理数量 ，直接删除

            // 清除库存锁定记录表
            stockLockRecordMapper.deleteByPrimaryKey(stockLockRecord.getId());
            for(CostLockRecord costLockRecord:costLockRecordList){// 应该就一条
                // 清除成本锁定记录
                costLockRecordMapper.deleteByPrimaryKey(costLockRecord.getId());
            }

            //判断商品锁定表是否已经处理完成
            if(ishandle(goodsLock.getId(), goodsLock.getCode())){
                //处理完成后更新商品锁定状态
                List<GoodsLockShelf> goodsLockShelfS = goodsLockShelfMapper.selectByLockId(goodsLock.getId());
                for(GoodsLockShelf goodsLockShelf : goodsLockShelfS){
                    goodsLockShelf.setStatus(PurchaseStatus.FINISHED.getStatus());
                    goodsLockShelfMapper.updateByPrimaryKeySelective(goodsLockShelf);
                }

                List<GoodsLockDetail> goodsLockDetailS = goodsLockDetailMapper.getGoodsLockDetailData(goodsLock.getId());
                for(GoodsLockDetail goodsLockDetail : goodsLockDetailS){
                    goodsLockDetail.setStatus(PurchaseStatus.FINISHED.getStatus());
                    goodsLockDetailMapper.updateByPrimaryKeySelective(goodsLockDetail);
                }
                goodsLock.setStatus(PurchaseStatus.FINISHED.getStatus());
                goodsLockMapper.updateByPrimaryKeySelective(goodsLock);
            }

        } else {
            throw  new BusinessException("商品处理的数量不能大于锁定的数量");
        }

    }

    /**
     * 插入成本锁定记录表
     * @param costLockRecordList
     * @param goodsHandleShelf
     */
    private void insertCostLockRecord(List<CostLockRecord> costLockRecordList,GoodsHandle goodsHandle,GoodsHandleShelf goodsHandleShelf, UserVO userVO) {

        if(CollectionUtils.isNotEmpty(costLockRecordList)){

            CostLockRecord costLockRecord  =  costLockRecordList.get(0);
            CostLockRecord newCostLockRecord = new CostLockRecord();
            newCostLockRecord.setEnterpriseId(costLockRecord.getEnterpriseId());
            newCostLockRecord.setParentId(costLockRecord.getParentId());
            newCostLockRecord.setBaseOrderId(goodsHandle.getId());
            newCostLockRecord.setBaseOrderType(goodsHandle.getOrderType());
            newCostLockRecord.setBaseOrderCode(goodsHandle.getCode());
            newCostLockRecord.setBaseOrderDate(goodsHandle.getHandleDate());
            newCostLockRecord.setGoodsId(costLockRecord.getGoodsId());
            newCostLockRecord.setGoodsCode(costLockRecord.getGoodsCode());
            newCostLockRecord.setGoodsName(costLockRecord.getGoodsName());
            newCostLockRecord.setLotId(costLockRecord.getLotId());
            newCostLockRecord.setLotNum(costLockRecord.getLotNum());
            newCostLockRecord.setBatchId(costLockRecord.getBatchId());
            newCostLockRecord.setBatchNum(costLockRecord.getBatchNum());
            newCostLockRecord.setLockQuantity(goodsHandleShelf.getQuantity());// 处理的数量
            newCostLockRecord.setCreaterId(userVO.getUserId());
            newCostLockRecord.setCreaterCode(userVO.getUserCode());
            newCostLockRecord.setCreaterName(userVO.getUserName());
            newCostLockRecord.setCreateTime(new Date());

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId",costLockRecord.getEnterpriseId());
            paramMap.put("parentId",costLockRecord.getParentId());
            paramMap.put("baseOrderId",goodsHandle.getId());
            paramMap.put("baseOrderCode",goodsHandle.getOrderType());
            paramMap.put("goodsId",costLockRecord.getGoodsId());
            paramMap.put("lotId",costLockRecord.getLotId());
            paramMap.put("batchId",costLockRecord.getBatchId());

            List<CostLockRecord> tempCostLockRecordList = costLockRecordMapper.selectByParamMap(paramMap);

            if(CollectionUtils.isEmpty(tempCostLockRecordList)){
                costLockRecordMapper.insertSelective(newCostLockRecord);
            } else {
                CostLockRecord updateCostLockRecord = tempCostLockRecordList.get(0);
                updateCostLockRecord.setLockQuantity(updateCostLockRecord.getLockQuantity().add(goodsHandleShelf.getQuantity()));
                costLockRecordMapper.updateByPrimaryKeySelective(updateCostLockRecord);
            }

        }


    }

    /**
     * 插入库存锁定记录表
     * @param goodsHandle
     * @param goodsHandleShelf
     */
    private void insertStockLockRecord(GoodsHandle goodsHandle, GoodsHandleShelf goodsHandleShelf,UserVO userVO) throws Exception {

        StockLockRecord st = new StockLockRecord();
        st.setEnterpriseId(goodsHandle.getEnterpriseId());
        st.setParentId(goodsHandle.getParentId());
        st.setBaseOrderId(goodsHandle.getId());
        st.setBaseOrderType(goodsHandle.getOrderType());
        st.setBaseOrderCode(goodsHandle.getCode());
        st.setBaseOrderDate(goodsHandle.getHandleDate());
        st.setGoodsId(goodsHandleShelf.getGoodsId());
        st.setGoodsCode(goodsHandleShelf.getGoodsCode());
        st.setGoodsName(goodsHandleShelf.getGoodsName());
        st.setLotId(goodsHandleShelf.getLotId());
        st.setLotNum(goodsHandleShelf.getLotNumber());
        st.setLockQuantity(goodsHandleShelf.getQuantity());// 处理的数量
        st.setShelfId(goodsHandleShelf.getShelfId());
        st.setShelfName(goodsHandleShelf.getShelfName());
        UserEnterpriseUtils.setUserCreateOrModify(st,userVO,true);
        stockLockRecordMapper.insertSelective(st);
    }

    /**
     * 更新成本表
     * @param costLockRecordList
     * @param handleQuantity
     */
    private void updateCost(List<CostLockRecord> costLockRecordList, BigDecimal handleQuantity) {

        if(CollectionUtils.isNotEmpty(costLockRecordList)){
            for(CostLockRecord costLockRecord:costLockRecordList){

                Map<String, Object> stockParamMap = new HashMap<>();
                stockParamMap.put("enterpriseId", costLockRecord.getEnterpriseId());
                stockParamMap.put("parentId", costLockRecord.getParentId());
                stockParamMap.put("goodsId", costLockRecord.getGoodsId());
                stockParamMap.put("lotId", costLockRecord.getLotId());
                stockParamMap.put("batchId", costLockRecord.getBatchId());
                // 释放成本
                List<Cost> costList = costMapper.selectByParamMap(stockParamMap);
                if(CollectionUtils.isNotEmpty(costList)){
                    Cost cost = costList.get(0);
                    cost.setUsableQuantity(cost.getUsableQuantity().add(handleQuantity));
                    costMapper.updateByPrimaryKeySelective(cost);
                }
            }
        }
    }

    /**
     * 更新库存
     * @param stockLockRecord
     */
    private void updateStock(StockLockRecord stockLockRecord,BigDecimal handleQuantity) {
        Map<String, Object> stockParamMap = new HashMap<>();
        stockParamMap.put("enterpriseId", stockLockRecord.getEnterpriseId());
        stockParamMap.put("parentId", stockLockRecord.getParentId());
        stockParamMap.put("goodsId", stockLockRecord.getGoodsId());
        stockParamMap.put("lotId", stockLockRecord.getLotId());
        stockParamMap.put("shelfId", stockLockRecord.getShelfId());
        // 释放库存
        List<Stock> stockList = stockMapper.selectByParamMap(stockParamMap);
        if(CollectionUtils.isNotEmpty(stockList)){
            Stock stock = stockList.get(0);
            stock.setLockQuantity(stock.getLockQuantity().subtract(handleQuantity));
            stock.setUsableQuantity(stock.getUsableQuantity().add(handleQuantity));
            stockMapper.updateByPrimaryKeySelective(stock);
        }

    }


//    private void updateCostLockRecord(GoodsHandleShelf goodsHandleShelf, GoodsHandle goodsHandle, UserVO userVO) throws Exception {
//        Map<String,Object> map = new HashMap<>();
//        Long enterpriseId = goodsHandle.getEnterpriseId();
//        Long parentId = goodsHandle.getParentId();
//        Long baseOrderId = goodsHandle.getBaseOrderId();
//        Integer baseOrderType = goodsHandle.getBaseOrderType();
//        String baseOrderCode = goodsHandle.getBaseOrderCode();
//        Long goodsId = goodsHandleShelf.getGoodsId();
//        Long lotId = goodsHandleShelf.getLotId();
//
//        map.put("enterpriseId", enterpriseId);
//        map.put("parentId", parentId);
//        map.put("baseOrderId", baseOrderId);
//        map.put("baseOrderType", baseOrderType);
//        map.put("baseOrderCode", baseOrderCode);
//        map.put("goodsId", goodsId);
//        map.put("lotId", lotId);
//
//        List<CostLockRecord> costLockRecordList = costLockRecordMapper.selectByParamMap(map);
//        BigDecimal handleQty = goodsHandleShelf.getQuantity();
//        for(CostLockRecord costLockRecord:costLockRecordList){
//            BigDecimal lockQuantity = costLockRecord.getLockQuantity();
//
//            // 锁定数量<=处理数量
//            if(lockQuantity.compareTo(handleQty) <= 0){
//
//                if(goodsHandle.getHandleResult() == 1){// 移动到不合格品货位
//                    // 生成以处理单作为源单的新的锁定记录
//                    generateCostLockRecord(userVO,goodsHandle,costLockRecord);
//                }
//
//                // 清除成本锁定记录
//                costLockRecordMapper.deleteByPrimaryKey(costLockRecord.getId());
//                handleQty = handleQty.subtract(lockQuantity);
//            }else {
//                if(goodsHandle.getHandleResult() == 1){// 移动到不合格品货位
//                    // 生成以处理单作为源单的新的锁定记录
//                    generateCostLockRecord(userVO,goodsHandle,costLockRecord);
//
//                }
//
//                // 更新成本锁定记录
//                costLockRecord.setBaseOrderId(goodsHandle.getId());
//                costLockRecord.setBaseOrderType(goodsHandle.getOrderType());
//                costLockRecord.setBaseOrderCode(goodsHandle.getCode());
//                costLockRecord.setBaseOrderDate(goodsHandle.getHandleDate());
//                costLockRecord.setLockQuantity(costLockRecord.getLockQuantity().subtract(handleQty));
//                costLockRecord.setCreaterId(userVO.getUserId());
//                costLockRecord.setCreaterCode(userVO.getUserCode());
//                costLockRecord.setCreaterName(userVO.getUserName());
//                costLockRecord.setCreateTime(new Date());
//                costLockRecordMapper.updateByPrimaryKey(costLockRecord);
//                // TODO: 更新 成本表
//
//                handleQty=BigDecimal.ZERO;
//            }
//            if(handleQty.compareTo(BigDecimal.ZERO)<=0){
//                break;
//            }
//        }
//        if(handleQty.compareTo(BigDecimal.ZERO) > 0){
//            throw new BusinessException("药品处理，处理的数量不能超出药品锁定的数量！");
//        }
//
//    }

//    /**
//     * 锁定成本
//     * @param userVO
//     * @param goodsHandle
//     * @param costLockRecord
//     */
//    private void generateCostLockRecord(UserVO userVO, GoodsHandle goodsHandle, CostLockRecord costLockRecord) {
//
//        CostLockRecord newCostLockRecord = new CostLockRecord();
//        newCostLockRecord.setEnterpriseId(costLockRecord.getEnterpriseId());
//        newCostLockRecord.setParentId(costLockRecord.getParentId());
//        newCostLockRecord.setBaseOrderId(goodsHandle.getId());
//        newCostLockRecord.setBaseOrderType(goodsHandle.getOrderType());
//        newCostLockRecord.setBaseOrderCode(goodsHandle.getCode());
//        newCostLockRecord.setBaseOrderDate(goodsHandle.getHandleDate());
//        newCostLockRecord.setGoodsId(costLockRecord.getGoodsId());
//        newCostLockRecord.setGoodsCode(costLockRecord.getGoodsCode());
//        newCostLockRecord.setGoodsName(costLockRecord.getGoodsName());
//        newCostLockRecord.setLotId(costLockRecord.getLotId());
//        newCostLockRecord.setLotNum(costLockRecord.getLotNum());
//        newCostLockRecord.setBatchId(costLockRecord.getBatchId());
//        newCostLockRecord.setBatchNum(costLockRecord.getBatchNum());
//        newCostLockRecord.setLockQuantity(costLockRecord.getLockQuantity());
//        newCostLockRecord.setCreaterId(userVO.getUserId());
//        newCostLockRecord.setCreaterCode(userVO.getUserCode());
//        newCostLockRecord.setCreaterName(userVO.getUserName());
//        newCostLockRecord.setCreateTime(new Date());
//        costLockRecordMapper.insertSelective(newCostLockRecord);
//    }


//    private void updateStockLockRecord(GoodsHandleShelf goodsHandleShelf, GoodsHandle goodsHandle, UserVO userVO) throws Exception {
//
//        //0 - 解除锁定 1 - 移动到不合格货位
//        Integer handleResult = goodsHandle.getHandleResult();
//        GoodsLock goodsLock = goodsLockMapper.selectByPrimaryKey(goodsHandleShelf.getLockId());
//        StockLockRecord stockLockRecord = stockLockRecordMapper.selectByGoodsIdAndLotIdAndShelfId(goodsLock.getId(),goodsHandleShelf.getGoodsId(),goodsHandleShelf.getLotId(),goodsHandleShelf.getShelfId(),goodsHandleShelf.getEnterpriseId(),goodsLock.getCode());
//        if(stockLockRecord == null){
//            throw new BusinessException("没有查询到上级单据锁定库存信息！");
//        }
//        if((goodsHandleShelf.getQuantity().subtract(stockLockRecord.getLockQuantity())).compareTo(BigDecimal.ZERO) > 0){
//            throw new BusinessException("处理数量不能超出原锁定商品数量！");
//        }
//        int num = stockLockRecord.getLockQuantity().compareTo(goodsHandleShelf.getQuantity());
//        if(num > 0){//锁定数量 > 处理数量  更新
//            BigDecimal lockQuantity = stockLockRecord.getLockQuantity().subtract(goodsHandleShelf.getQuantity());
//            stockLockRecord.setLockQuantity(lockQuantity);
//            stockLockRecordMapper.updateByPrimaryKeySelective(stockLockRecord);
//            if (handleResult == 1){
//                /**
//                 * 生成一条新的商品处理的锁定记录 数量就是goodsHandleShelf.getQuantity()
//                 */
//                StockLockRecord st = new StockLockRecord();
//                st.setEnterpriseId(userVO.getEnterpriseId());
//                st.setParentId(userVO.getParentId());
//                st.setBaseOrderId(goodsHandle.getId());
//                st.setBaseOrderType(goodsHandle.getOrderType());
//                st.setBaseOrderCode(goodsHandle.getCode());
//                st.setBaseOrderDate(goodsHandle.getHandleDate());
//                st.setGoodsId(goodsHandleShelf.getGoodsId());
//                st.setGoodsCode(goodsHandleShelf.getGoodsCode());
//                st.setGoodsName(goodsHandleShelf.getGoodsName());
//                st.setLotId(goodsHandleShelf.getLotId());
//                st.setLotNum(goodsHandleShelf.getLotNumber());
//                st.setLockQuantity(goodsHandleShelf.getQuantity());
//                st.setShelfId(goodsHandleShelf.getShelfId());
//                st.setShelfName(goodsHandleShelf.getShelfName());
//                UserEnterpriseUtils.setUserCreateOrModify(st,userVO,true);
//                stockLockRecordMapper.insertSelective(st);
//            }
//        }else {//锁定数量 = 处理数量   删除
//            StockLockRecord handleRecord = new StockLockRecord();
//            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(stockLockRecord,handleRecord);
//            if (handleResult == 1){
//                handleRecord.setId(null);
//                handleRecord.setBaseOrderId(goodsHandle.getId());
//                handleRecord.setBaseOrderCode(goodsHandle.getCode());
//                handleRecord.setBaseOrderType(goodsHandle.getOrderType());
//                handleRecord.setBaseOrderDate(goodsHandle.getHandleDate());
//                handleRecord.setCreaterId(userVO.getUserId());
//                handleRecord.setCreaterCode(userVO.getUserCode());
//                handleRecord.setCreaterName(userVO.getUserName());
//                handleRecord.setCreateTime(new Date());
//                stockLockRecordMapper.insertSelective(handleRecord);
//            }
//            stockLockRecordMapper.deleteByPrimaryKey(stockLockRecord.getId());
//            //判断商品锁定表是否已经处理完成
//            if(ishandle(goodsLock.getId(), goodsLock.getCode())){
//                //处理完成后更新商品锁定状态
//                List<GoodsLockShelf> goodsLockShelfS = goodsLockShelfMapper.selectByLockId(goodsLock.getId());
//                for(GoodsLockShelf goodsLockShelf : goodsLockShelfS){
//                    goodsLockShelf.setStatus(PurchaseStatus.FINISHED.getStatus());
//                    goodsLockShelfMapper.updateByPrimaryKeySelective(goodsLockShelf);
//                }
//
//                List<GoodsLockDetail> goodsLockDetailS = goodsLockDetailMapper.getGoodsLockDetailData(goodsLock.getId());
//                for(GoodsLockDetail goodsLockDetail : goodsLockDetailS){
//                    goodsLockDetail.setStatus(PurchaseStatus.FINISHED.getStatus());
//                    goodsLockDetailMapper.updateByPrimaryKeySelective(goodsLockDetail);
//                }
//                goodsLock.setStatus(PurchaseStatus.FINISHED.getStatus());
//                goodsLockMapper.updateByPrimaryKeySelective(goodsLock);
//            }
//
//        }
//    }


    private boolean ishandle(Long baseOrderId, String baseOrderCode) {

        List<StockLockRecord> stockLockRecord = stockLockRecordMapper.selectByLockId(baseOrderId, baseOrderCode);
        if(stockLockRecord.isEmpty() || (stockLockRecord == null)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * <药品处理单明细填充药品基础信息>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/23 16:38
     */
    private void fillGoodsInfo(GoodsVO goods, GoodsHandleDetail goodsHandleDetail) {
        goodsHandleDetail.setGoodsCode(goods.getCode());
        goodsHandleDetail.setBarcode(goods.getBarcode());
        goodsHandleDetail.setGoodsName(goods.getName());
        goodsHandleDetail.setGoodsGenericName(goods.getGenericName());
        goodsHandleDetail.setDosageId(goods.getDosageId());
        goodsHandleDetail.setDosageName(goods.getDosageName());
        goodsHandleDetail.setUnitId(goods.getUnitId());
        goodsHandleDetail.setUnitName(goods.getUnitName());
        goodsHandleDetail.setGoodsSpecification(goods.getSpecification());
        goodsHandleDetail.setManufacturerId(goods.getManufacturerId());
        goodsHandleDetail.setManufacturer(goods.getManufacturer());
        goodsHandleDetail.setGoodsPlace(goods.getPlace());
        goodsHandleDetail.setApprovalNumber(goods.getApprovalNumber());
    }

    @Override
    public int update(GoodsHandleSaveOrupdateVO goodsHandle, UserVO userVO) throws Exception {
        GoodsHandle copy = (GoodsHandle) EntityUtils.reflectUpdateSetDefaultValue(new GoodsHandle().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsHandle, copy);
        return goodsHandleMapper.updateByPrimaryKeySelective(copy);
    }

    @Override
    public int delete(Long id) throws Exception {
        return goodsHandleMapper.deleteByPrimaryKey(id);
    }

    /**
     * <获取商品处理单列表数据>
     *
     * @param requestGoodsHanleListVo
     * @param page
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 13:36
     */
    @Override
    public void getGoodshandleListData(RequestGoodsHandleListVo requestGoodsHanleListVo, Page page) {
        requestGoodsHanleListVo.setPageNo(page.getStart());
        int count = goodsHandleMapper.countGoodsLockDateByParam(requestGoodsHanleListVo);
        Integer sortDate=requestGoodsHanleListVo.getSortDate();
        Integer sortCode=requestGoodsHanleListVo.getSortCode();
        String sort="";
        if(sortDate==null&&sortCode==null){
            sort="a.handle_date desc,a.code desc,";
        }
        if(sortDate!=null&&sortDate==0){
            sort+="a.handle_date,";
        }
        if(sortDate!=null&&sortDate==1){
            sort+="a.handle_date desc,";
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
        requestGoodsHanleListVo.setSort(sort);
        List<GoodsHandle> list = goodsHandleMapper.getGoodsLockDateByParam(requestGoodsHanleListVo);
        page.setTotalRecord(count);
        page.setResult(list);
    }

    /**
     * <导出商品处理单明细>
     *
     * @param goodsHandle
     * @param userVO
     * @param output
     * @param name
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 16:44
     */
    @Override
    public void exportExcel(GoodsHandle goodsHandle, UserVO userVO, OutputStream output, String name) {
        //标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("商品锁定");
        //表头数据
        List<String> titleSecond = new ArrayList<>();
        StringBuffer title = new StringBuffer();
        String handleResultStr = null;//处理结果（0-解除锁定；1-移动到不合格品货位）
        Integer handleResult = goodsHandle.getHandleResult();
        if (handleResult == 0) {
            handleResultStr = "解除锁定";
        } else if (handleResult == 1) {
            handleResultStr = "移动到不合格品货位";
        }
        title.append("锁定单号:").append(goodsHandle.getCode())
                .append("   锁定日期:").append(DateUtils.DateToString(goodsHandle.getHandleDate(), "yyyy-MM-dd"))
                .append("   锁定人员:").append(goodsHandle.getHandleManName())
                .append("   锁定原因:").append(handleResultStr);
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
        List<GoodsHandleExcelVO> goodsHandleExcelVOList = new ArrayList<>();
        for (GoodsHandleDetail goodsHandleDetail : goodsHandle.getGoodsHandleDetailList()) {
            for (GoodsHandleShelf goodsHandleShelf : goodsHandleDetail.getGoodsHandleShelfList()) {
                GoodsHandleExcelVO goodsHandleExcelVO = new GoodsHandleExcelVO();
                goodsHandleExcelVO.setGoodsCode(goodsHandleDetail.getGoodsCode());
                goodsHandleExcelVO.setGoodsGenericName(goodsHandleDetail.getGoodsGenericName());
                goodsHandleExcelVO.setDosageName(goodsHandleDetail.getDosageName());
                goodsHandleExcelVO.setGoodsSpecification(goodsHandleDetail.getGoodsSpecification());
                goodsHandleExcelVO.setManufacturer(goodsHandleDetail.getManufacturer());
                goodsHandleExcelVO.setUnitName(goodsHandleDetail.getUnitName());
                goodsHandleExcelVO.setLotNumber(goodsHandleShelf.getLotNumber());
                goodsHandleExcelVO.setProductDate(goodsHandleShelf.getProductDate());
                goodsHandleExcelVO.setValidDate(goodsHandleShelf.getValidDate());
                goodsHandleExcelVO.setShelfName(goodsHandleShelf.getShelfName());
                goodsHandleExcelVO.setQuantity(goodsHandleShelf.getQuantity());
                goodsHandleExcelVOList.add(goodsHandleExcelVO);
            }
        }
        purchaseGeneralComponent.commExcelExport(output, map, goodsHandleExcelVOList, names, titleSecond, "", true, new ArrayList<>());
    }

}
