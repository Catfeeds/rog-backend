package com.rograndec.feijiayun.chain.business.purchase.retout.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierSalerMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.CargoQualityStateVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.RequestPurchaseReturnParamVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.ResponsePurchaseReturnDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.ResponsePurchaseReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutDetail;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutOther;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutShelf;
import com.rograndec.feijiayun.chain.business.purchase.retout.service.PurchaseReturnOutService;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.CargoAreaWorkingArea;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.ShelfStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.CalculateResultModel;
import com.rograndec.feijiayun.chain.common.vo.LockQtyArgVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 功能描述：
 * Created by ST on 2017/9/15 13:30
 */
@Service
public class PurchaseReturnOutServiceImpl implements PurchaseReturnOutService {

    @Autowired
    private PurchaseReturnComponent purchaseReturnComponent;

    @Autowired
    private PurchaseReturnOutMapper purchaseReturnOutMapper;

    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private LotNumberMapper lotNumberMapper;

    @Autowired
    private PurchaseReturnOutDetailMapper purchaseReturnOutDetailMapper;

    @Autowired
    private PurchaseReturnOutOtherMapper purchaseReturnOutOtherMapper;

    @Autowired
    private PurchaseReturnOutShelfMapper purchaseReturnOutShelfMapper;
    @Autowired
    private WarehouseCargoAreaMapper cargoAreaMapper;

    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    @Autowired
    private PurchaseReturnDetailMapper purchaseReturnDetailMapper;

    @Autowired
    private PurchaseReturnOtherMapper purchaseReturnOtherMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private CommonComponent commonComponent;


    @Autowired
    private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;

    @Autowired
    private StockLockRecordMapper stockLockRecordMapper;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;

    @Autowired
    private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierSalerMapper supplierSalerMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private OrikaMapperFactory orikaMapperFactory;

    @Override
    public Page<List<ResponsePurchaseReturnOutVO>> getReturnOutListForOutBound(RequestPurchaseReturnParamVO paramVO, Page<List<ResponsePurchaseReturnOutVO>> page) {
//        PageHelper.startPage(page.getStart(),page.getPageSize());
        List<ResponsePurchaseReturnVO> purchaseReturnVOByPages = purchaseReturnComponent.getPurchaseReturnVOByPage(paramVO);
        List<ResponsePurchaseReturnOutVO> purchaseReturnOutVOS = new ArrayList<>();
        purchaseReturnVOByPages.forEach(item->{
            ResponsePurchaseReturnOutVO purchaseReturnOutVO = new ResponsePurchaseReturnOutVO();
            BeanUtils.copyProperties(item,purchaseReturnOutVO);
            purchaseReturnOutVOS.add(purchaseReturnOutVO);
        });
        page.setResult(purchaseReturnOutVOS);
        page.setTotalRecord(purchaseReturnComponent.getCountPurchaseReturnVOByPage(paramVO));
        return page;
    }

    /**
     * 根据id 查询购进出库明细
     * @param id 购进出库id
     * @return
     */
    public ResponsePurchaseReturnDetailVO getPurchaseReturnDetail(Long id,UserVO userVO){
        Long enterpriseId = userVO.getEnterpriseId();
        ResponsePurchaseReturnDetailVO purchaseReturnDetailVO = purchaseReturnComponent.getPurchaseReturnDetail(id);
        //查询质量控制
        List<PurchaseReturnDetailVO> purchaseReturnDetailVOList = purchaseReturnDetailVO.getPurchaseReturnDetailVOList();
        for (PurchaseReturnDetailVO purchaseReturnDetail: purchaseReturnDetailVOList) {
            Long lotId = purchaseReturnDetail.getLotId();
            Long goodsId = purchaseReturnDetail.getGoodsId();
            List<StockShelfVO> stockLockRecords = stockLockRecordMapper.selectByGoodsIdAndLotId(purchaseReturnDetailVO.getId(), goodsId, lotId, enterpriseId);
            purchaseReturnDetail.setStockShelfVOList(stockLockRecords);
        }
        return purchaseReturnDetailVO;
    }

    /**
     * 查询已出库待复核的数据、已完成复核的数据
     *
     * @param paramVO
     * @param page
     * @return
     */
    @Override
    public Page<List<ResponsePurchaseReturnOutVO>> getReturnOutListByParam(UserVO userVO,RequestPurchaseReturnParamVO paramVO, Page<List<ResponsePurchaseReturnOutVO>> page) {
//        PageHelper.startPage(page.getStart(),page.getPageSize());
        paramVO.setCodeOrder(Optional.ofNullable(paramVO.getCodeOrder()).orElse(0));
        paramVO.setDateOrder(Optional.ofNullable(paramVO.getDateOrder()).orElse(0));
        Integer status = paramVO.getStatus();//
        Integer count = 0;
        List<Integer> statusList = new ArrayList<>();
        if(status != null && status == 1){
            //已完成（待开票）
            statusList.add(PurchaseStatus.WAIT_BILL.getStatus());
            statusList.add(PurchaseStatus.WAIT_PAY.getStatus());
            statusList.add(PurchaseStatus.PART_BILL.getStatus());
            statusList.add(PurchaseStatus.FINISHED.getStatus());
            paramVO.setStatusList(statusList);
            List<ResponsePurchaseReturnOutVO> purchaseReturnOutByPage = purchaseReturnOutMapper.getPurchaseReturnOutByPage(paramVO);
            purchaseReturnOutByPage.stream().filter(p -> p.getStatus() == 1).forEach(p ->{
                p.setCheckerId(userVO.getUserId());
                p.setCheckerName(userVO.getUserName());
            });
            count += purchaseReturnOutMapper.getCountPurchaseReturnOutByPage(paramVO);
            page.setResult(purchaseReturnOutByPage);
            page.setTotalRecord(count);
            return page;

        } else {
            //待复核
            statusList.add(PurchaseStatus.WAIT_AUDIT.getStatus());
            paramVO.setStatusList(statusList);
            List<ResponsePurchaseReturnOutVO> purchaseReturnOutByPage = purchaseReturnOutMapper.getPurchaseReturnOutByPage(paramVO);
            purchaseReturnOutByPage.stream().filter(p -> p.getStatus() == 1).forEach(p ->{
                p.setCheckerId(userVO.getUserId());
                p.setCheckerName(userVO.getUserName());
            });
            count += purchaseReturnOutMapper.getCountPurchaseReturnOutByPage(paramVO);

            page.setResult(purchaseReturnOutByPage);
            page.setTotalRecord(count);
            return page;
        }

    }

    /**
     * 查询已出库待复核的数据、已完成复核的的购进退出出库详情
     * @param id
     * @return
     */
    @Override
    public ResponsePurchaseReturnOutDetailVO getPurchaseReturnOutDetail(Long id) {

        ResponsePurchaseReturnOutDetailVO purchaseReturnOutDetail = purchaseReturnOutMapper.getPurchaseReturnOutDetail(id);
        if(purchaseReturnOutDetail != null){
            List<PurchaseReturnDetailVO2> purchaseReturnDetailVO2List = purchaseReturnOutDetail.getPurchaseReturnDetailVOList();
            if(!CollectionUtils.isEmpty(purchaseReturnDetailVO2List)){
                purchaseReturnDetailVO2List.forEach(item->{
                    item.setReturnOutShelfVOList(purchaseReturnOutShelfMapper.getShelfInfoByDtlId(item.getId()));
                });
            }
        }
        return purchaseReturnOutDetail;
    }

    /**
     * 查询货位
     * @param enterpriseId
     * @param goodsId
     * @param lotNum
     * @return
     */
    @Override
    public List<StockShelfVO> getShelfByLotNum(Long enterpriseId, Long goodsId, String lotNum) {
        //查收所有的货位
        List<StockVO> stockVOS = stockMapper.getStockVOByParam(enterpriseId,goodsId,lotNum);
        List<StockShelfVO> stockShelfVOS = new ArrayList<>();
        stockVOS.forEach(item->{
            StockShelfVO stockShelfVO = new StockShelfVO();
            BeanUtils.copyProperties(item,stockShelfVO);
            stockShelfVO.setQuality(item.getQuantity());
            stockShelfVO.setLotNumber(item.getLotNum());
            LotNumber lotNumber = lotNumberMapper.selectByLotNumAndGoodsId(goodsId,lotNum,enterpriseId);
            stockShelfVO.setProductDate(lotNumber.getProductDate());
            stockShelfVO.setValidDate(lotNumber.getValidUntil());
            Map<Long,CargoQualityStateVO> cargoQualityStateVOMap = new HashMap<>();
            CargoQualityStateVO cargoQualityStateVO = cargoQualityStateVOMap.get(stockShelfVO.getShelfId()) ;
            if(cargoQualityStateVO == null){
                cargoQualityStateVO = cargoAreaMapper.getCargoByShelfId(stockShelfVO.getShelfId());
                cargoQualityStateVOMap.put(stockShelfVO.getShelfId(),cargoQualityStateVO);
            }
            if(cargoQualityStateVO != null){
                if(cargoQualityStateVO.getJobArea() != null){
                    stockShelfVO.setQualityState(CargoAreaWorkingArea.getName(cargoQualityStateVO.getJobArea()));
                }
            }
            stockShelfVOS.add(stockShelfVO);
        });
        return stockShelfVOS;
    }

    /**
     * 出库
     *
     * @param purchaseReturnOutInfoVO
     * @param userVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOutStock(RequestPurchaseReturnOutInfoVO purchaseReturnOutInfoVO, UserVO userVO) throws Exception {

        commonComponent.generateAndSavePurchaseReturnOut(purchaseReturnOutInfoVO,userVO);
    }

    /**
     * 复核
     *
     * @param requestCheckVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateReCheck(RequestCheckVO requestCheckVO,UserVO userVO) throws Exception {

        commonComponent.auditPurchaseReturnOut(requestCheckVO,userVO);

    }

    private void genPurchaseReturn(UserVO loginUser, PurchaseReturnOut purchaseReturnOut,PurchaseReturnOutInfoVO purchaseReturnOutInfoVO,PurchaseReturnOutOther outOther) throws Exception{
        //新增购进退出总单
        PurchaseReturn purchaseReturn = new PurchaseReturn();
        purchaseReturn.setParentId(loginUser.getParentId());
        purchaseReturn.setEnterpriseId(loginUser.getEnterpriseId());
        purchaseReturn.setOrderType(OrderRule.PURCHASE_RETURN.getType());
        purchaseReturn.setCode(orderCodeComponent.generate(OrderRule.PURCHASE_RETURN.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
        purchaseReturn.setReturnDate(purchaseReturnOut.getReturnDate());
//        List<Map<Long,String>> list = (List<Map<Long,String>>) redisComponent.get("PurchaseReturnOut:"+purchaseReturnOut.getId());
//        if(list != null || list.size()>0){//如果有值,则证明是勾选采购入库单生成的,需要回写基本单据
//            PurchaseInStorageDetail purchaseInStorageDetail = purchaseInStorageDetailMapper.selectByPrimaryKey(Long.valueOf(list.get(0).values().iterator().next().split(":")[0]));
//            purchaseReturn.setBaseOrderId(purchaseInStorageDetail.getInStorageId());
//            purchaseReturn.setBaseOrderCode(purchaseInStorageDetail.getInStorageCode());
//            purchaseReturn.setBaseOrderDate(purchaseInStorageDetail.getInStorageDate());
//            purchaseReturn.setBaseOrderType(purchaseInStorageDetail.getOrderType());
//            redisComponent.remove("PurchaseReturnOut:"+purchaseReturnOut.getId());
//        }
        if(purchaseReturnOutInfoVO.getInStorageId() != null){
            PurchaseInStorage purchaseInStorage = purchaseInStorageMapper.selectByPrimaryKey(purchaseReturnOutInfoVO.getInStorageId());
            purchaseReturn.setBaseOrderId(purchaseInStorage.getId());
            purchaseReturn.setBaseOrderCode(purchaseInStorage.getCode());
            purchaseReturn.setBaseOrderDate(purchaseInStorage.getInStorageDate());
            purchaseReturn.setBaseOrderType(purchaseInStorage.getOrderType());

        }
        purchaseReturn.setReturnType(purchaseReturnOut.getReturnType());
        purchaseReturn.setSupplierId(purchaseReturnOut.getSupplierId());
        purchaseReturn.setSupplierCode(purchaseReturnOut.getSupplierCode());
        purchaseReturn.setSupplierName(purchaseReturnOut.getSupplierName());
        purchaseReturn.setSupplierSalerId(purchaseReturnOut.getSupplierSalerId());
        purchaseReturn.setSupplierSalerCode(purchaseReturnOut.getSupplierSalerCode());
        purchaseReturn.setSupplierSalerName(purchaseReturnOut.getSupplierSalerName());
        purchaseReturn.setSupplierSalerPhone(purchaseReturnOut.getSupplierSalerPhone());
        purchaseReturn.setReturnManId(purchaseReturnOut.getReturnManId());
        purchaseReturn.setReturnManCode(purchaseReturnOut.getReturnManCode());
        purchaseReturn.setReturnManName(purchaseReturnOut.getReturnManName());
        purchaseReturn.setTelPhone(purchaseReturnOut.getTelPhone());
        purchaseReturn.setQuantityTotal(purchaseReturnOut.getQuantityTotal());
        purchaseReturn.setWholeDiscount(purchaseReturnOut.getWholeDiscount());
        purchaseReturn.setWholeDiscountAmount(purchaseReturnOut.getWholeDiscountAmount());
        purchaseReturn.setRealAmountTotal(purchaseReturnOut.getRealAmountTotal());
        purchaseReturn.setNotaxRealAmountTotal(purchaseReturnOut.getNotaxRealAmountTotal());
        purchaseReturn.setTaxAmountTotal(purchaseReturnOut.getTaxAmountTotal());
        purchaseReturn.setStatus(PurchaseStatus.OUT_ING.getStatus());
        purchaseReturn.setQuantityTotal(purchaseReturnOut.getQuantityTotal());
        purchaseReturn.setVarietiesQuantity(purchaseReturnOut.getVarietiesQuantity());
        purchaseReturn.setAmountTotal(purchaseReturnOut.getAmountTotal());
        UserEnterpriseUtils.setUserCreateOrModify(purchaseReturn,loginUser,true);
        purchaseReturnMapper.insertSelective(purchaseReturn);
        // 释放原来购进退出单锁定的库存
        releaseStock(loginUser, purchaseReturn);
        //新增购进退出明细
        List<PurchaseReturnOutDetail> outDetails = purchaseReturnOutDetailMapper.getPurchaseReturnOutDetailByOutId(purchaseReturnOut.getId());
        List<PurchaseReturnDetail> returnDetails = new ArrayList<>();
        List<PurchaseReturnDetail> tempReturnDetails = new ArrayList<>();
        int lineNum = 0;
        for(PurchaseReturnOutDetail outDetail : outDetails){
            PurchaseReturnDetail detail = new PurchaseReturnDetail();
            detail.setEnterpriseId(loginUser.getEnterpriseId());
            detail.setParentId(loginUser.getParentId());
            detail.setReturnId(purchaseReturn.getId());
            detail.setOrderType(purchaseReturn.getOrderType());
            detail.setReturnCode(purchaseReturn.getCode());
            detail.setReturnDate(purchaseReturn.getReturnDate());
//            if(list != null || list.size()>0){//如果有值,则证明是勾选采购入库单生成的,需要回写基本单据
//                for(Map<Long,String> map : list){
//                    Long key = map.keySet().iterator().next();
//                    if(outDetail.getId().equals(key)){
//                        detail.setBaseOrderId(purchaseReturn.getBaseOrderId());
//                        detail.setBaseOrderCode(purchaseReturn.getBaseOrderCode());
//                        detail.setBaseOrderDate(purchaseReturn.getBaseOrderDate());
//                        detail.setBaseOrderType(purchaseReturn.getBaseOrderType());
//                        detail.setBaseOrderDtlId(Long.valueOf(map.get(key).split(":")[0]));
//                        detail.setReturnReasonIds(map.get(key).split(":")[1]);
//                    }
//                }
//            }
            for(PurchaseReturnDetailOutVO vo : purchaseReturnOutInfoVO.getPurchaseReturnDetailVOList()){
                if(vo.getInStorageDtlId()!=null){
                    detail.setBaseOrderId(purchaseReturn.getBaseOrderId());
                    detail.setBaseOrderCode(purchaseReturn.getBaseOrderCode());
                    detail.setBaseOrderDate(purchaseReturn.getBaseOrderDate());
                    detail.setBaseOrderType(purchaseReturn.getBaseOrderType());
                    detail.setBaseOrderDtlId(vo.getInStorageDtlId());
                }
                detail.setReturnReasonIds(vo.getReturnReasonIds());
            }
            detail.setGoodsId(outDetail.getGoodsId());
            detail.setGoodsCode(outDetail.getGoodsCode());
            detail.setBarcode(outDetail.getBarcode());
            detail.setGoodsName(outDetail.getGoodsName());
            detail.setGoodsGenericName(outDetail.getGoodsGenericName());
            detail.setDosageId(outDetail.getDosageId());
            detail.setDosageName(outDetail.getDosageName());
            detail.setUnitId(outDetail.getUnitId());
            detail.setUnitName(outDetail.getUnitName());
            detail.setGoodsSpecification(outDetail.getGoodsSpecification());
            detail.setManufacturerId(outDetail.getManufacturerId());
            detail.setManufacturer(outDetail.getManufacturer());
            detail.setGoodsPlace(outDetail.getGoodsPlace());
            detail.setApprovalNumber(outDetail.getApprovalNumber());
            detail.setUnitPrice(outDetail.getUnitPrice());
            detail.setLineDiscount(outDetail.getLineDiscount());
            detail.setWholeDiscount(outDetail.getWholeDiscount());
            detail.setTaxRate(outDetail.getTaxRate());
            detail.setTaxRateId(outDetail.getTaxRateId());
            List<PurchaseReturnOutShelfVO> shelves = purchaseReturnOutShelfMapper.getShelfInfoByDtlId(outDetail.getId());
            for(int i = 0; i<shelves.size(); i++){
                PurchaseReturnOutShelfVO shelfVO = shelves.get(i);
                BigDecimal lineDiscountAmount = outDetail.getLineDiscount().divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP).multiply(shelfVO.getQuantity()).multiply(outDetail.getUnitPrice()).setScale(2,RoundingMode.HALF_UP);
                CalculateResultModel resultModel = CalculateComponent.getCalculateResult(shelfVO.getQuantity(),detail.getUnitPrice(),detail.getLineDiscount(),detail.getWholeDiscount(),BigDecimal.ZERO,detail.getTaxRate(),BigDecimal.ONE);
                if(i == 0){
                    genDtl(loginUser, purchaseReturn, lineNum, outDetail, detail, shelfVO, lineDiscountAmount, resultModel);
                    returnDetails.add(detail);
                }else{
                    for(PurchaseReturnDetail returnDetail : returnDetails){
                        if(detail.getGoodsId().equals(returnDetail.getGoodsId())){
                            detail.setQuantity(detail.getQuantity().add(shelfVO.getQuantity()));
                            detail.setLineDiscountAmount(detail.getLineDiscountAmount().add(lineDiscountAmount));
                            detail.setAmount(detail.getAmount().add(resultModel.getAmount()));
                            detail.setRealPrice(detail.getRealPrice().add(resultModel.getRealPrice()));
                            detail.setRealAmount(detail.getRealAmount().add(resultModel.getRealAmount()));
                            detail.setNotaxRealAmount(detail.getNotaxRealAmount().add(resultModel.getNotaxAmount()));
                            detail.setNotaxRealPrice(detail.getNotaxRealPrice().add(resultModel.getNotaxPrice()));
                            detail.setTaxAmount(detail.getTaxAmount().add(resultModel.getTaxAmount()));
                            detail.setClearQuantity(detail.getClearQuantity().add(shelfVO.getQuantity()));

                        }
                    }
                }
            }
            purchaseReturnDetailMapper.insertSelective(detail);
            outDetail.setBaseOrderDtlId(detail.getId());
            outDetail.setBaseOrderCode(purchaseReturn.getCode());
            outDetail.setBaseOrderDate(purchaseReturn.getReturnDate());
            outDetail.setBaseOrderId(purchaseReturn.getId());
            outDetail.setBaseOrderType(purchaseReturn.getOrderType());
            purchaseReturnOutDetailMapper.updateByPrimaryKeySelective(outDetail);
            lockStock(loginUser,detail);
        }
//        purchaseReturnDetailMapper.batchInsert(returnDetails);
        //新增配送和结算
        PurchaseReturnOther other = new PurchaseReturnOther();
//        PurchaseReturnOutOther outOther = purchaseReturnOutOtherMapper.selectByOutId(purchaseReturn.getId());
        other.setParentId(loginUser.getParentId());
        other.setEnterpriseId(loginUser.getEnterpriseId());
        other.setReturnId(purchaseReturn.getId());
        other.setSettlementType(outOther.getSettlementType());
        other.setSettlementUnit(outOther.getSettlementUnit());
        other.setSettlementUnitPhone(outOther.getSettlementUnitPhone());
        other.setSettlementUnitAddress(outOther.getSettlementUnitAddress());
        other.setAccount(outOther.getAccount());
        other.setAccountName(outOther.getAccountName());
        other.setBank(outOther.getBank());
        other.setInvoiceType(outOther.getInvoiceType());
        other.setTaxpayerCode(outOther.getTaxpayerCode());
        other.setCarriageMode(outOther.getCarriageMode());
        other.setCarriageUnit(outOther.getCarriageUnit());
        other.setTransportMode(outOther.getTransportMode());
        other.setTempControlMode(outOther.getTempControlMode());
        other.setReceiver(outOther.getReceiver());
        other.setReceivePhone(outOther.getReceivePhone());
        other.setReceiveAddress(outOther.getReceiveAddress());
        other.setOutFlag(1);
        other.setStatus(purchaseReturn.getStatus());
        UserEnterpriseUtils.setUserCreateOrModify(other,loginUser,true);
        purchaseReturnOtherMapper.insertSelective(other);

        //修改配进退出出库的基本单据信息
        purchaseReturnOut.setBaseOrderId(purchaseReturn.getId());
        purchaseReturnOut.setBaseOrderCode(purchaseReturn.getCode());
        purchaseReturnOut.setBaseOrderDate(purchaseReturn.getReturnDate());
        purchaseReturnOut.setBaseOrderType(purchaseReturn.getOrderType());
        purchaseReturnOutMapper.updateByPrimaryKeySelective(purchaseReturnOut);
    }

    private void genDtl(UserVO loginUser, PurchaseReturn purchaseReturn, int lineNum, PurchaseReturnOutDetail outDetail, PurchaseReturnDetail detail, PurchaseReturnOutShelfVO shelfVO, BigDecimal lineDiscountAmount, CalculateResultModel resultModel) throws Exception {
        detail.setLotId(Long.valueOf(shelfVO.getLotId()));
        detail.setLotNumber(shelfVO.getLotNumber());
        detail.setProductDate(shelfVO.getProductDate());
        detail.setValidDate(shelfVO.getValidDate());
        detail.setQuantity(shelfVO.getQuantity());
        detail.setLineDiscountAmount(lineDiscountAmount);
        detail.setAmount(resultModel.getAmount());
        detail.setRealPrice(resultModel.getRealPrice());
        detail.setRealAmount(resultModel.getRealAmount());
        detail.setNotaxRealAmount(resultModel.getNotaxAmount());
        detail.setNotaxRealPrice(resultModel.getNotaxPrice());
        detail.setTaxAmount(resultModel.getTaxAmount());
        detail.setUnclearQuantity(BigDecimal.ZERO);
        detail.setClearQuantity(shelfVO.getQuantity());
        detail.setLineNum(lineNum++);
        detail.setStatus(purchaseReturn.getStatus());
        UserEnterpriseUtils.setUserCreateOrModify(detail,loginUser,true);
    }

    /**
     * 导出
     *
     * @param output
     * @param id 购进退出出库单id
     */
    @Override
    public void exportExcel(OutputStream output, Long id) {

        //退出出库总单
        PurchaseReturnOut purchaseReturnOut = purchaseReturnOutMapper.selectByPrimaryKey(id);//
        //
//        List<PurchaseReturnDetailVO> purchaseReturnDetailVOList = purchaseReturnOut.getPurchaseReturnDetailVOList();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(purchaseReturnOut.getEnterpriseId());//企业

        List<String> headerList = new ArrayList<>();
        headerList.add(enterprise.getName() + "总部");
        headerList.add("购进退出出库单");

        StringBuilder titleSecondRow = new StringBuilder();

        titleSecondRow.append("供货单位编码:").append(Optional.ofNullable(purchaseReturnOut.getSupplierCode()).orElse(""))
        .append("  供货单位名称:").append(Optional.ofNullable(purchaseReturnOut.getSupplierName()).orElse(""))
        .append("  供货单位销售人员:").append(Optional.ofNullable(purchaseReturnOut.getSupplierName()).orElse(""))
        .append("  联系电话：").append(Optional.ofNullable(purchaseReturnOut.getSupplierSalerPhone()).orElse(""))
        .append("  出库单号：").append(Optional.ofNullable(purchaseReturnOut.getCode()).orElse(""))
        .append("  出库日期：").append(Optional.ofNullable(DateUtils.getDate(purchaseReturnOut.getOutDate())).orElse(""))
        .append("  出库人员: ").append(Optional.ofNullable(purchaseReturnOut.getOutManName()).orElse(""))
        .append("  复核人员：").append(Optional.ofNullable(purchaseReturnOut.getCheckerName()).orElse(""))
        .append("  流通监管码：").append(Optional.ofNullable(purchaseReturnOut.getFlowCode()).orElse(""));

        List<String> titleSecondRowList = new ArrayList<>();
        titleSecondRowList.add(titleSecondRow.toString());

        //
        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
        rowHeaderMap.put("goodsCode","商品编码");
        rowHeaderMap.put("genericName","通用名称");
        rowHeaderMap.put("dosageName","剂型");
        rowHeaderMap.put("specification","规格");
        rowHeaderMap.put("unitName","单位");
        rowHeaderMap.put("manufacturer","生产厂商");
        rowHeaderMap.put("lotNumber","批号");
        rowHeaderMap.put("productDateStr","生产日期");
        rowHeaderMap.put("validDateStr","有效期至");
        rowHeaderMap.put("shelfName","货位");
        rowHeaderMap.put("quantity","数量");
        rowHeaderMap.put("unitPrice","单价");
        rowHeaderMap.put("lineDiscount","折扣");
        rowHeaderMap.put("amount","金额");
        rowHeaderMap.put("taxRate","税率(%)");
        rowHeaderMap.put("notaxRealPrice","不含税单价");
        rowHeaderMap.put("notaxRealAmount","不含税金额");
        rowHeaderMap.put("shelfStatusDesc","质量状况");

        //
        StringBuilder endString = new StringBuilder();
        endString.append("总商品金额：").append(purchaseReturnOut.getAmountTotal()).append(";")
        .append("折扣： ").append(purchaseReturnOut.getWholeDiscount()).append("%   ").append(purchaseReturnOut.getAmountTotal().multiply(new BigDecimal(100).subtract(purchaseReturnOut.getWholeDiscount()))).append(";")
        .append("优惠：").append(purchaseReturnOut.getWholeDiscountAmount()).append(";")
        .append("总额：").append(purchaseReturnOut.getAmountTotal().subtract(purchaseReturnOut.getWholeDiscount().add(purchaseReturnOut.getWholeDiscountAmount()))).append(";")
        .append("不含税总额：").append(purchaseReturnOut.getNotaxRealAmountTotal()).append(";")
        .append("税额：").append(purchaseReturnOut.getTaxAmountTotal());


        List<PurchaseReturnOutExcelVO> returnDetailShelf = purchaseReturnOutShelfMapper.getReturnDetailShelf(id);

        returnDetailShelf.forEach(item->{
            item.setProductDateStr(DateUtils.DateToString(item.getProductDate(),"yyyy-MM-dd"));
            item.setValidDateStr(DateUtils.DateToString(item.getValidDate(),"yyyy-MM-dd"));
        });
        purchaseGeneralComponent.commExcelExport(output,rowHeaderMap,returnDetailShelf,headerList,titleSecondRowList,endString.toString(),true,new ArrayList<>());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePurchaseReturnOut(PurchaseReturnOutInfoVO purchaseReturnOutInfoVO, UserVO loginUser) throws Exception {
        //先检查是否有多个批号的数据,如果有则拆单
        purchaseReturnOutInfoVO = splitReturnOut(purchaseReturnOutInfoVO);
        Long enterpriseId = loginUser.getEnterpriseId();
        Long parentId = loginUser.getParentId();
        Long inStorageId = purchaseReturnOutInfoVO.getInStorageId();//不为空 则该单据为调用采购入库生成的单据
        //购进退出出库单号
        String code = orderCodeComponent.generate(OrderRule.PURCHASE_RETURN_OUT.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode());

        //出库明细
        List<PurchaseReturnDetailOutVO> purchaseReturnDetailOutVOList = purchaseReturnOutInfoVO.getPurchaseReturnDetailVOList();

        PurchaseReturnOut purchaseReturnOut = new PurchaseReturnOut();
        //查询质量控制
        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(loginUser);
        BeanUtils.copyProperties(purchaseReturnOutInfoVO,purchaseReturnOut);
        PurchaseReturnOut returnOut = (PurchaseReturnOut) EntityUtils.reflectAddSetDefaultValue(PurchaseReturnOut.class,loginUser);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(returnOut,purchaseReturnOut);
        Supplier supplier = supplierMapper.selectByPrimaryKey(purchaseReturnOutInfoVO.getSupplierId());
        if(supplier == null) throw new BusinessException("没有供应商");
        SupplierSaler supplierSaler = supplierSalerMapper.selectByPrimaryKey(purchaseReturnOutInfoVO.getSupplierSalerId());
        if(supplierSaler == null) throw new BusinessException("没有供应商销售人员");
        purchaseReturnOut.setOutDate(new Date());
        purchaseReturnOut.setBaseOrderId(new Date().getTime());
        purchaseReturnOut.setBaseOrderCode(new Date().toString());
        purchaseReturnOut.setBaseOrderType(OrderRule.PURCHASE_RETURN.getType());
        purchaseReturnOut.setBaseOrderDate(new Date());
        purchaseReturnOut.setSupplierCode(supplier.getCode());
        purchaseReturnOut.setSupplierName(supplier.getName());
        purchaseReturnOut.setSupplierId(supplier.getId());
        purchaseReturnOut.setSupplierSalerCode(supplierSaler.getCode());
        purchaseReturnOut.setSupplierSalerId(supplierSaler.getId());
        purchaseReturnOut.setSupplierSalerName(supplierSaler.getName());
        purchaseReturnOut.setSupplierSalerPhone(supplierSaler.getMobilePhone());
        purchaseReturnOut.setTelPhone(purchaseReturnOutInfoVO.getTelPhone());
        purchaseReturnOut.setReturnType(purchaseReturnOutInfoVO.getReturnType());
        purchaseReturnOut.setReturnDate(purchaseReturnOutInfoVO.getReturnDate());
        purchaseReturnOut.setVarietiesQuantity(purchaseReturnDetailOutVOList != null ? purchaseReturnDetailOutVOList.size() : 0);//品种合计
        //数量合计
        BigDecimal allQuantity = purchaseReturnDetailOutVOList.stream().filter(Objects::nonNull)
                .filter(c->c.getQuantity() != null).map(PurchaseReturnDetailOutVO::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
        purchaseReturnOut.setQuantityTotal(allQuantity);

        //金额计算
        purchaseReturnOut.setRealAmountTotal(BigDecimal.ZERO);
        purchaseReturnOut.setNotaxRealAmountTotal(BigDecimal.ZERO);
        purchaseReturnOut.setTaxAmountTotal(BigDecimal.ZERO);
        //出库人员
        if(mangeConfig.getBusinessControl() == 0){//关闭
            Long outManId = purchaseReturnOutInfoVO.getOutManId();
            Long returnManId = purchaseReturnOutInfoVO.getReturnManId();
            User user = userMapper.selectByPrimaryKey(outManId);
            User returnUser = userMapper.selectByPrimaryKey(returnManId);
            if(user == null){
                throw new BusinessException("出库人员不存在！");
            }
            if(returnUser == null){
                throw new BusinessException("退货人员不存在！");
            }
            purchaseReturnOut.setOutManId(user.getId());
            purchaseReturnOut.setOutManCode(user.getCode());
            purchaseReturnOut.setOutManName(user.getName());
            purchaseReturnOut.setReturnManId(returnUser.getId());
            purchaseReturnOut.setReturnManCode(returnUser.getCode());
            purchaseReturnOut.setReturnManName(returnUser.getName());
        } else {
            //开启时当前登录人员
            purchaseReturnOut.setOutManId(loginUser.getUserId());
            purchaseReturnOut.setOutManCode(loginUser.getUserCode());
            purchaseReturnOut.setOutManName(loginUser.getUserName());
            purchaseReturnOut.setReturnManId(loginUser.getUserId());
            purchaseReturnOut.setReturnManCode(loginUser.getUserCode());
            purchaseReturnOut.setReturnManName(loginUser.getUserName());

        }

        purchaseReturnOut.setStatus(PurchaseStatus.WAIT_AUDIT.getStatus());
        purchaseReturnOut.setCode(code);
        purchaseReturnOut.setId(null);
        purchaseReturnOut.setOrderType(OrderRule.PURCHASE_RETURN_OUT.getType());
        purchaseReturnOutMapper.insertSelective(purchaseReturnOut);
        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
        BigDecimal wholeDiscount = purchaseReturnOutInfoVO.getWholeDiscount();//整单折扣

        for(PurchaseReturnDetailOutVO vo : purchaseReturnDetailOutVOList){
            /**.
             * 金额（整单优惠前金额）
             */
            BigDecimal amount = commonComponent.calcLineDiscount(vo);
            amountTotal = amountTotal.add(amount);
        }

        BigDecimal wholeDiscountAmount = purchaseReturnOutInfoVO.getWholeDiscountAmount();//整单优惠金额
        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计

        //购进退出出库货位明细
        List<PurchaseReturnOutShelf> returnOutShelfList = new ArrayList<>();
        List<Map<Long,String>> inStorage = new ArrayList<>();//购进退出出库与采购入库单的对应关系 购进退出出库明细ID : 采购入库单明细ID
        Set<Long> goodsSet = new HashSet<>();//货品集合
        if(purchaseReturnDetailOutVOList != null && purchaseReturnDetailOutVOList.size() > 0){
            for (PurchaseReturnDetailOutVO item : purchaseReturnDetailOutVOList) {
                //查询购进退出的明细
                //购进退出出库明细
                PurchaseReturnOutDetail purchaseReturnOutDetail = new PurchaseReturnOutDetail();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(item, purchaseReturnOutDetail);
                    //商品信息
                    Goods goods = goodsMapper.selectByPrimaryKey(purchaseReturnOutDetail.getGoodsId());
                    if(goods==null) throw new BusinessException("查无此商品");
                    goodsSet.add(goods.getId());
                    purchaseReturnOutDetail.setGoodsCode(goods.getCode());
                    purchaseReturnOutDetail.setBarcode(goods.getBarcode());
                    purchaseReturnOutDetail.setGoodsName(goods.getName());
                    purchaseReturnOutDetail.setGoodsGenericName(goods.getGenericName());
                    purchaseReturnOutDetail.setDosageId(goods.getDosageId());
                    purchaseReturnOutDetail.setDosageName(goods.getDosageName());
                    purchaseReturnOutDetail.setUnitId(goods.getUnitId());
                    purchaseReturnOutDetail.setUnitName(goods.getUnitName());
                    purchaseReturnOutDetail.setGoodsSpecification(goods.getSpecification());
                    purchaseReturnOutDetail.setManufacturerId(goods.getManufacturerId());
                    purchaseReturnOutDetail.setManufacturer(goods.getManufacturer());
                    purchaseReturnOutDetail.setGoodsPlace(goods.getPlace());
                    purchaseReturnOutDetail.setApprovalNumber(goods.getApprovalNumber());

                    purchaseReturnOutDetail.setEnterpriseId(enterpriseId);
                    purchaseReturnOutDetail.setParentId(parentId);
                    UserEnterpriseUtils.setUserCreateOrModify(purchaseReturnOutDetail,loginUser,true);
                    //计算金额
                    BigDecimal quantity = item.getQuantity();//数量
                    BigDecimal unitPrice = item.getUnitPrice();//单价
                    BigDecimal lineDiscount = item.getLineDiscount();//行折扣
                    GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(item.getTaxRateId());
                    if(goodsTaxRate == null){
                        throw new BusinessException("税率不存在");
                    }
                    BigDecimal taxRate = goodsTaxRate.getTaxRate();//税率

                    CalculateResultModel calculateResultModel = CalculateComponent.getCalculateResult(quantity, unitPrice, lineDiscount, wholeDiscount, wholeDiscountAmount, taxRate, amountTotal);

                    //金额
                    BigDecimal amount = calculateResultModel.getAmount();
                    //行优惠
                    BigDecimal lineRoundOff = calculateResultModel.getLineRoundOff();
                    //实际金额
                    BigDecimal realAmount = calculateResultModel.getRealAmount();
                    realAmountTotal = realAmountTotal.add(realAmount);
                    //实际单价
                    BigDecimal realPrice = calculateResultModel.getRealPrice();

                    //不含税金额
                    BigDecimal notaxRealAmount = calculateResultModel.getNotaxAmount();
                    notaxRealAmountTotal = notaxRealAmountTotal.add(notaxRealAmount);
                    //不含税实际单价
                    BigDecimal notaxRealPrice = calculateResultModel.getNotaxPrice();
                    //税额
                    BigDecimal taxAmount = calculateResultModel.getTaxAmount();
                    taxAmountTotal = taxAmountTotal.add(taxAmount);

                    purchaseReturnOutDetail.setAmount(amount);
                    purchaseReturnOutDetail.setLineDiscountAmount(lineRoundOff);
                    purchaseReturnOutDetail.setRealAmount(realAmount);
                    purchaseReturnOutDetail.setRealPrice(realPrice);
                    purchaseReturnOutDetail.setNotaxRealAmount(notaxRealAmount);
                    purchaseReturnOutDetail.setNotaxRealPrice(notaxRealPrice);
                    purchaseReturnOutDetail.setTaxAmount(taxAmount);
                    purchaseReturnOutDetail.setWholeDiscount(wholeDiscount);
                    //税率设置
                    purchaseReturnOutDetail.setTaxRate(taxRate);


                    purchaseReturnOutDetail.setOutId(purchaseReturnOut.getId());//购进退出出库单ID

                    purchaseReturnOutDetail.setOutCode(code);
                    purchaseReturnOutDetail.setOutDate(new Date());
                    purchaseReturnOutDetail.setOrderType(OrderRule.PURCHASE_RETURN_OUT.getType());
                    purchaseReturnOutDetail.setStatus(PurchaseStatus.WAIT_AUDIT.getStatus());
                    //已清 和 未清 数量
                    purchaseReturnOutDetail.setUnclearQuantity(quantity);
                    purchaseReturnOutDetail.setClearQuantity(BigDecimal.ZERO);
                    purchaseReturnOutDetail.setId(null);
                    purchaseReturnOutDetail.setOutId(purchaseReturnOut.getId());
                    //基础单据Id
                    purchaseReturnOutDetail.setBaseOrderId(purchaseReturnOut.getBaseOrderId());
                    purchaseReturnOutDetail.setBaseOrderCode(purchaseReturnOut.getBaseOrderCode());
                    purchaseReturnOutDetail.setBaseOrderType(OrderRule.PURCHASE_RETURN.getType());
                    purchaseReturnOutDetail.setBaseOrderDate(purchaseReturnOut.getBaseOrderDate());
                    purchaseReturnOutDetail.setBaseOrderDtlId(new Date().getTime());

                    ///////////////////////////////////////////////////////////////////////////////////
                    //插入出库明细
                    purchaseReturnOutDetailMapper.insertSelective(purchaseReturnOutDetail);
                    item.setId(purchaseReturnOutDetail.getId());
//                    if(inStorageId != null){
//                        Map<Long,String> map = new HashMap<>();
//                        map.put(purchaseReturnOutDetail.getId(),item.getId()+":"+item.getReturnReasonIds());
//                        inStorage.add(map);
//                    }

                    BigDecimal quantityShelfTmp = BigDecimal.ZERO;//货位数量总计
                    //出库货位明细
                    List<StockShelfVO> stockShelfVOList = item.getStockShelfVOList();
                    if(CollectionUtils.isEmpty(stockShelfVOList)) throw new BusinessException("没有传购进退出出库货位明细");
                    for (int i = 0; i<stockShelfVOList.size(); i++) {//购进退出出库货位明细
                        StockShelfVO shelfVO = stockShelfVOList.get(i);
                        PurchaseReturnOutShelf purchaseReturnOutShelf = (PurchaseReturnOutShelf) EntityUtils.reflectAddSetDefaultValue(PurchaseReturnOutShelf.class, loginUser);
                        BeanUtils.copyProperties(purchaseReturnOutDetail, purchaseReturnOutShelf);
                        //批号
                        LotNumber lotNumber = lotNumberMapper.getLotNumInfo(enterpriseId, purchaseReturnOutDetail.getGoodsId(), shelfVO.getLotNumber());
                        purchaseReturnOutShelf.setProductDate(lotNumber.getProductDate());
                        purchaseReturnOutShelf.setValidDate(lotNumber.getValidUntil());
                        purchaseReturnOutShelf.setShelfId(shelfVO.getShelfId());
                        purchaseReturnOutShelf.setShelfName(shelfVO.getShelfName());
                        Map<String,Object> warehouseShelf = warehouseShelfMapper.getShelfInfoById(shelfVO.getShelfId());
                        if(warehouseShelf == null){
                            throw new BusinessException("货位不存在");
                        }
                        Object jobArea = warehouseShelf.get("jobArea");
                        purchaseReturnOutShelf.setShelfStatusDesc(ShelfStatus.getName((Integer) jobArea));
                        purchaseReturnOutShelf.setEnterpriseId(enterpriseId);
                        purchaseReturnOutShelf.setParentId(parentId);
                        purchaseReturnOutShelf.setGoodsId(purchaseReturnOutDetail.getGoodsId());
                        purchaseReturnOutShelf.setDtlId(purchaseReturnOutDetail.getId());
                        purchaseReturnOutShelf.setLotId(shelfVO.getLotId());
                        purchaseReturnOutShelf.setLotNumber(shelfVO.getLotNumber());
                        purchaseReturnOutShelf.setQuantity(shelfVO.getUsableQuantity());
//                        quantityShelfTmp = quantityShelfTmp.add(shelfVO.getQuality());
                        quantityShelfTmp = quantityShelfTmp.add(shelfVO.getUsableQuantity());
                        purchaseReturnOutShelf.setAmount(amount);
                        purchaseReturnOutShelf.setLineDiscountAmount(lineRoundOff);
                        purchaseReturnOutShelf.setRealAmount(realAmount);
                        purchaseReturnOutShelf.setRealPrice(realPrice);
                        purchaseReturnOutShelf.setNotaxRealAmount(notaxRealAmount);
                        purchaseReturnOutShelf.setNotaxRealPrice(notaxRealPrice);
                        purchaseReturnOutShelf.setTaxAmount(taxAmount);
                        purchaseReturnOutShelf.setId(null);
                        purchaseReturnOutShelf.setStatus(PurchaseStatus.WAIT_AUDIT.getStatus());
                        returnOutShelfList.add(purchaseReturnOutShelf);
                        purchaseReturnOutShelfMapper.insertSelective(purchaseReturnOutShelf);
                        //锁库存
//                        lockStock(loginUser,purchaseReturnOutShelf,purchaseReturnOutDetail);
                        //库存可用数量S
                        BigDecimal usableQuantity = stockMapper.getUsableQuantityFromStock(shelfVO.getLotNumber(), enterpriseId, item.getGoodsId());
                        if(mangeConfig.getBusinessControl() == 0){
                            //业务控制按钮关闭

                            /**
                             * 如果为null,是通过新增方式添加的购进退出(A),否则为调用采购入库单添加的购进退出(B)
                             * A:出库数量Q1可编辑,Q1小于等于商品的库存可用数量S
                             * B:出库数量Q2,入库单的可退数量R1,库存可用数量S1.
                             * 当R1>S1;R1=S1-->Q2<=R1.
                             * 当R1<S1;Q2<=R1.
                             */
//                            Long baseOrderId = purchaseReturnOutDetail.getBaseOrderId();
//                            String lotNumber = purchaseReturnOutShelf.getLotNumber();
                            Long goodsId = purchaseReturnOutDetail.getGoodsId();
                            if(inStorageId == null){
                                if(quantityShelfTmp.compareTo(usableQuantity) == 1){
                                    throw new BusinessException("出库数量不能大于商品的库存可用数量");
                                }
                            } else {
//                                List<PurchaseInStorageShelf> purchaseInStorageShelf = purchaseInStorageShelfMapper.getPurchaseInStorageShelf(loginUser.getEnterpriseId(), inStorageId, goodsId,lotNumber.getLotNum());
//                                BigDecimal canReturnQuantity = purchaseInStorageShelf.stream().filter(Objects::nonNull).filter(c->c.getCanReturnQuantity() != null).map(PurchaseInStorageShelf::getCanReturnQuantity)
//                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                BigDecimal canReturnQuantity = stockShelfVOList.stream().filter(Objects::nonNull).filter(c->c.getUsableQuantity() != null).map(StockShelfVO::getUsableQuantity)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                if(canReturnQuantity.compareTo(usableQuantity) == 1){
                                    canReturnQuantity = usableQuantity;
                                }
                                if(quantityShelfTmp.compareTo(canReturnQuantity) == 1){
                                    throw new BusinessException("出库数量不能大于商品的库存可用数量");
                                }
                            }
                        } else {
                            //业务控制按钮开启
                            if(quantityShelfTmp.compareTo(quantity) == 1){
                                throw new BusinessException("出库数量不能大于商品的库存可用数量");
                            }
                        }
                    }
//                    if(lotIdSet.size() > 1){
//                        throw new BusinessException("一条购进退出明细只能含有一个批号");
//                    }
            }
        }

        //更新总单金额

        purchaseReturnOut.setAmountTotal(amountTotal);
        purchaseReturnOut.setRealAmountTotal(realAmountTotal);
        purchaseReturnOut.setNotaxRealAmountTotal(notaxRealAmountTotal);
        purchaseReturnOut.setTaxAmountTotal(taxAmountTotal);
        Integer varietiesQuantity = goodsSet.size();
        purchaseReturnOut.setVarietiesQuantity(varietiesQuantity);
        //更新出库总单
        purchaseReturnOutMapper.updateByPrimaryKeySelective(purchaseReturnOut);

        //购进退出出库配送和结算
        PurchaseReturnOutOtherVO purchaseReturnOutOtherVO = purchaseReturnOutInfoVO.getPurchaseReturnOtherVO();
        if(purchaseReturnOutOtherVO != null){
            PurchaseReturnOutOther purchaseReturnOutOther = new PurchaseReturnOutOther();
            BeanUtils.copyProperties(purchaseReturnOutOtherVO,purchaseReturnOutOther);//
            PurchaseReturnOutOther returnOutOther = (PurchaseReturnOutOther) EntityUtils.reflectAddSetDefaultValue(PurchaseReturnOutOther.class,loginUser);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(returnOutOther,purchaseReturnOutOther);
            purchaseReturnOutOther.setId(null);
            purchaseReturnOutOther.setOutId(purchaseReturnOut.getId());
            purchaseReturnOutOther.setStatus(PurchaseStatus.WAIT_AUDIT.getStatus());
            purchaseReturnOutOther.setCarriageMode(DistributionType.DISTRIBUTION_HEAD.getCode());
            purchaseReturnOutOtherMapper.insertSelective(purchaseReturnOutOther);
            genPurchaseReturn(loginUser,purchaseReturnOut,purchaseReturnOutInfoVO,purchaseReturnOutOther);
        }else {
            throw new BusinessException("没有配送和结算信息");
        }
        //先生成购进退出单
//        //如果purchaseReturnOutInfoVO.getId() != null 则说明是由采购入库单生成,存入redis,待复核完成后删除
//        if(inStorageId != null && inStorage.size() > 0){
//            redisComponent.set("PurchaseReturnOut:"+purchaseReturnOut.getId(),inStorage);
//        }
    }

    private PurchaseReturnOutInfoVO splitReturnOut(PurchaseReturnOutInfoVO purchaseReturnOutInfoVO) {
        List<PurchaseReturnDetailOutVO> tempDetailVOS = new ArrayList<>();

        List<PurchaseReturnDetailOutVO> purchaseReturnDetailVOs = purchaseReturnOutInfoVO.getPurchaseReturnDetailVOList();

        List<PurchaseReturnDetailOutVO> newDetails = new ArrayList<>();
        int lineNum = 0;
        for(PurchaseReturnDetailOutVO purchaseReturnDetailOutVO : purchaseReturnDetailVOs){

            List<StockShelfVO> stockShelfVOs = purchaseReturnDetailOutVO.getStockShelfVOList();

            Map<String,List<StockShelfVO>> stringListMap = new HashMap<>();

            for(StockShelfVO stockShelfVO : stockShelfVOs){

                List<StockShelfVO> stockShelfVOS = stringListMap.get(stockShelfVO.getLotNumber());
                if(CollectionUtils.isEmpty(stockShelfVOS)){
                    stockShelfVOS = new ArrayList<>();
                    stockShelfVOS.add(stockShelfVO);
                    stringListMap.put(stockShelfVO.getLotNumber(),stockShelfVOS);
                }else {
                    stockShelfVOS.add(stockShelfVO);
                }
            }
            for(Map.Entry<String,List<StockShelfVO>> entry : stringListMap.entrySet()){
                lineNum ++;
                PurchaseReturnDetailOutVO newDetailVO = new PurchaseReturnDetailOutVO();
                newDetailVO = orikaMapperFactory.copyBean(newDetailVO,purchaseReturnDetailOutVO);

                List<StockShelfVO> value = entry.getValue();
                newDetailVO.setStockShelfVOList(value);
                newDetailVO.setLineNum(lineNum);
                newDetails.add(newDetailVO);
            }

        }

        for(PurchaseReturnDetailOutVO detailOutVO : newDetails){
            BigDecimal quantity = BigDecimal.ZERO;
            for(StockShelfVO stockShelfVO : detailOutVO.getStockShelfVOList()){
                detailOutVO.setQuantity(quantity.add(stockShelfVO.getUsableQuantity()));
            }
        }

        purchaseReturnOutInfoVO.setPurchaseReturnDetailVOList(newDetails);


        return purchaseReturnOutInfoVO;
       /* for(PurchaseReturnDetailOutVO detailVO : purchaseReturnOutInfoVO.getPurchaseReturnDetailVOList()){
            List<Long> lotIdList = new ArrayList<>();//记录lotId
            List<StockShelfVO> stockShelfVOS = detailVO.getStockShelfVOList();
            Iterator<StockShelfVO> it = stockShelfVOS.iterator();
            int i = 0;
            while(it.hasNext()){
                StockShelfVO shelfVO = it.next();
                List<StockShelfVO> stockShelfVOList = new ArrayList<>();
                if(i == 0){
                    lotIdList.add(shelfVO.getLotId());
                }
                i++;
                if(!lotIdList.contains(shelfVO.getLotId())){
                    lotIdList.add(shelfVO.getLotId());
                    PurchaseReturnDetailOutVO detailOutVO = new PurchaseReturnDetailOutVO();
                    detailOutVO = orikaMapperFactory.copyBean(detailOutVO,detailVO);
                    stockShelfVOS.add(shelfVO);
                    detailOutVO.setStockShelfVOList(stockShelfVOS);
                    tempDetailVOS.add(detailOutVO);
                    it.remove();
                }
            }
            if(!tempDetailVOS.isEmpty()){
                List<PurchaseReturnDetailOutVO> outVOS = purchaseReturnOutInfoVO.getPurchaseReturnDetailVOList();
                outVOS.addAll(tempDetailVOS);
                purchaseReturnOutInfoVO.setPurchaseReturnDetailVOList(outVOS);
            }
        }*/
    }

    @Override
    public ManageConfig getDefaultReturnMan(UserVO loginUser) throws Exception {
        return manageConfigComponent.getMangeConfigByEPId(loginUser);
    }

    @Override
    public PurchaseReturnOutInfoVO getPurchaseReturnOutFromInStorage(UserVO loginUser, List<Long> inStorageDtlS) throws Exception {
        PurchaseReturnOutInfoVO infoVO = new PurchaseReturnOutInfoVO();
        List<PurchaseInStorageDetail> storageDetails = purchaseInStorageDetailMapper.selectByIds(loginUser.getEnterpriseId(),inStorageDtlS);
        if(storageDetails == null || storageDetails.isEmpty()){
            throw new BusinessException("查无入库信息");
        }
        PurchaseInStorage inStorage = purchaseInStorageMapper.selectByPrimaryKey(storageDetails.get(0).getInStorageId());
        infoVO.setInStorageId(inStorage.getId());
        infoVO.setSupplierId(inStorage.getSupplierId());
        infoVO.setSupplierCode(inStorage.getSupplierCode());
        infoVO.setSupplierName(inStorage.getSupplierName());
        infoVO.setSupplierSalerId(inStorage.getSupplierSalerId());
        infoVO.setSupplierSalerCode(inStorage.getSupplierSalerCode());
        infoVO.setSupplierSalerName(inStorage.getSupplierSalerName());
        infoVO.setSupplierSalerPhone(inStorage.getSupplierSalerPhone());
        infoVO.setAmountTotal(inStorage.getAmountTotal());
        infoVO.setVarietiesQuantity(inStorage.getVarietiesQuantity());
        infoVO.setWholeDiscount(inStorage.getWholeDiscount());
        infoVO.setWholeDiscountAmount(inStorage.getWholeDiscountAmount());
        List<PurchaseReturnDetailOutVO> detailOutVOS = new ArrayList<>();
        Iterator<PurchaseInStorageDetail> it = storageDetails.iterator();
        while(it.hasNext()){
            PurchaseInStorageDetail inStorageDetail = it.next();
            PurchaseReturnDetailOutVO detailOutVO = new PurchaseReturnDetailOutVO();
            detailOutVO.setInStorageDtlId(inStorageDetail.getId());
            detailOutVO.setGoodsId(inStorageDetail.getGoodsId());
            detailOutVO.setGoodsCode(inStorageDetail.getGoodsCode());
            detailOutVO.setBarcode(inStorageDetail.getBarcode());
            detailOutVO.setGoodsName(inStorageDetail.getGoodsName());
            detailOutVO.setGoodsGenericName(inStorageDetail.getGoodsGenericName());
            detailOutVO.setDosageId(inStorageDetail.getDosageId());
            detailOutVO.setDosageName(inStorageDetail.getDosageName());
            detailOutVO.setUnitId(inStorageDetail.getUnitId());
            detailOutVO.setUnitName(inStorageDetail.getUnitName());
            detailOutVO.setGoodsSpecification(inStorageDetail.getGoodsSpecification());
            detailOutVO.setManufacturerId(inStorageDetail.getManufacturerId());
            detailOutVO.setManufacturer(inStorageDetail.getManufacturer());
            detailOutVO.setGoodsPlace(inStorageDetail.getGoodsPlace());
            detailOutVO.setApprovalNumber(inStorageDetail.getApprovalNumber());

//            detailOutVO.setQuantity(inStorageDetail.getQuantity());
            detailOutVO.setUnitPrice(inStorageDetail.getUnitPrice());
            detailOutVO.setLineDiscount(inStorageDetail.getLineDiscount());
            detailOutVO.setTaxRateId(inStorageDetail.getTaxRateId());
            detailOutVO.setTaxRate(inStorageDetail.getTaxRate());
            List<PurchaseInStorageShelf> storageShelves = purchaseInStorageShelfMapper.selectByEnterpriseIdAndId(loginUser.getEnterpriseId(),inStorageDetail.getId());
            List<StockShelfVO> shelfVOS = new ArrayList<>();
            BigDecimal canReturnQuantityTotal = BigDecimal.ZERO;
            //货位库存的可用数量
            BigDecimal usableQuantity = BigDecimal.ZERO;
            for(int i = 0; i < storageShelves.size(); i++){
                PurchaseInStorageShelf storageShelf = storageShelves.get(i);
                StockShelfVO stockShelfVO = new StockShelfVO();
                LotNumber lotNumber = lotNumberMapper.getLotNumInfo(loginUser.getEnterpriseId(),detailOutVO.getGoodsId(),storageShelf.getLotNumber());
                usableQuantity = stockMapper.getShelfUsableQuantity(storageShelf.getLotNumber(),storageShelf.getEnterpriseId(),storageShelf.getGoodsId(),storageShelf.getShelfId());
                BigDecimal canReturnQuantity = storageShelf.getCanReturnQuantity();
                BigDecimal quantityTotal = purchaseReturnOutMapper.getQuantityTotal(inStorageDetail.getEnterpriseId(), inStorageDetail.getId(),stockShelfVO.getShelfId());
                if(quantityTotal != null){
                    canReturnQuantity = canReturnQuantity.subtract(quantityTotal);
                }
                /**
                 * 1.基于入库单A,入库的时候,保存入库数量B等于可退数量C
                 * 2.该批号可用库存数量D
                 * 如果C>D,以库存可用数量D为准
                 * 如果C<D,以C为准
                 */
                if(usableQuantity != null){
//                    if(usableQuantity.compareTo(BigDecimal.ZERO) == 0){
//                        it.remove();
//                        continue;
//                    }
                    if(canReturnQuantity.compareTo(usableQuantity) == 1){
                        //可退数量大于库存数量
                        canReturnQuantity = usableQuantity;
                    }
                }
                stockShelfVO.setQuality(storageShelf.getQuantity());
                stockShelfVO.setUsableQuantity(canReturnQuantity);
                stockShelfVO.setLotId(lotNumber.getId());
                stockShelfVO.setLotNumber(storageShelf.getLotNumber());
                stockShelfVO.setProductDate(lotNumber.getProductDate());
                stockShelfVO.setValidDate(lotNumber.getValidUntil());
                stockShelfVO.setShelfId(storageShelf.getShelfId());
                stockShelfVO.setShelfName(storageShelf.getShelfName());
                shelfVOS.add(stockShelfVO);
                canReturnQuantityTotal = canReturnQuantityTotal.add(canReturnQuantity);
            }
//            /**
//             * 根据购进退出出库单更新入库单的可退数量
//             */
//            BigDecimal quantityTotal = purchaseReturnOutMapper.getQuantityTotal(inStorageDetail.getEnterpriseId(), inStorageDetail.getId());
//            if(quantityTotal != null){
//                canReturnQuantity = canReturnQuantity.subtract(quantityTotal);
//                if(canReturnQuantity.compareTo(BigDecimal.ZERO) == 0 || canReturnQuantity.compareTo(BigDecimal.ZERO) == -1){
//                    it.remove();
//                    continue;
//                }
//            }
            /**
             * 1.基于入库单A,入库的时候,保存入库数量B等于可退数量C
             * 2.该批号可用库存数量D
             * 如果C>D,以库存可用数量D为准
             * 如果C<D,以C为准
             */
//            if(usableQuantity != null){
//                if(usableQuantity.compareTo(BigDecimal.ZERO) == 0){
//                    it.remove();
//                    continue;
//                }
//                if(canReturnQuantity.compareTo(usableQuantity) == 1){
//                    //可退数量大于库存数量
//                    detailOutVO.setQuantity(usableQuantity);
//                } else {
//                    detailOutVO.setQuantity(canReturnQuantity);
//                }
//            }
            detailOutVO.setQuantity(canReturnQuantityTotal);
            detailOutVO.setStockShelfVOList(shelfVOS);
            detailOutVOS.add(detailOutVO);
        }
        infoVO.setPurchaseReturnDetailVOList(detailOutVOS);
        PurchaseReturnOutOtherVO outOtherVO = new PurchaseReturnOutOtherVO();
        infoVO.setPurchaseReturnOtherVO(outOtherVO);
        return infoVO;
    }

    //锁库存
    @Deprecated
    private void lockStock(UserVO userVO, PurchaseReturnOutShelf shelf, PurchaseReturnOutDetail outDetail) {
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setEnterpriseId(shelf.getEnterpriseId());
        lockQtyArgVO.setParentId(shelf.getParentId());
        lockQtyArgVO.setBaseOrderId(shelf.getOutId());
        lockQtyArgVO.setBaseOrderCode(outDetail.getOutCode());
        lockQtyArgVO.setBaseOrderType(outDetail.getOrderType());
        lockQtyArgVO.setBaseOrderDate(outDetail.getOutDate());
        lockQtyArgVO.setGoodsId(shelf.getGoodsId());
        lockQtyArgVO.setLotId(shelf.getLotId());
        lockQtyArgVO.setQuantity(shelf.getQuantity());
        lockQtyArgVO.setShelfId(shelf.getShelfId());
        lockQtyArgVO.setUser(userVO);
        commonComponent.lockStockAndCost(lockQtyArgVO);
    }

    //释放库存
    private void releaseStock(UserVO userVO, PurchaseReturn purchaseReturn) {
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setEnterpriseId(purchaseReturn.getEnterpriseId());
        lockQtyArgVO.setParentId(purchaseReturn.getParentId());
        lockQtyArgVO.setBaseOrderId(purchaseReturn.getId());
        lockQtyArgVO.setBaseOrderType(purchaseReturn.getOrderType());
        lockQtyArgVO.setUser(userVO);
        commonComponent.releaseStockAndCost(lockQtyArgVO);
    }

    public void lockStock(UserVO userVO, PurchaseReturnDetail detail) {
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setEnterpriseId(detail.getEnterpriseId());
        lockQtyArgVO.setParentId(detail.getParentId());
        lockQtyArgVO.setBaseOrderId(detail.getReturnId());
        lockQtyArgVO.setBaseOrderCode(detail.getReturnCode());
        lockQtyArgVO.setBaseOrderType(detail.getOrderType());
        lockQtyArgVO.setBaseOrderDate(detail.getReturnDate());
        lockQtyArgVO.setGoodsId(detail.getGoodsId());
        lockQtyArgVO.setLotId(detail.getLotId());
        lockQtyArgVO.setQuantity(detail.getQuantity());
        lockQtyArgVO.setUser(userVO);
        commonComponent.lockStockAndCost(lockQtyArgVO);
    }



}