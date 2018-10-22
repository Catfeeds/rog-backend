package com.rograndec.feijiayun.chain.business.purchase.ret.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierBusinessMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierSalerMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierVarietiesMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierBusiness;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.CargoQualityStateVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.keytable.dao.InOutRecordDetailMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.InOutRecordDetail;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageDetailForReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInStorageForReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.SupplierOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.approvalProcessor.ReturnApprovalProcessor;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnModifyRecord;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;
import com.rograndec.feijiayun.chain.business.purchase.ret.service.PurchaseReturnService;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.ShelfStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能描述：
 * Created by ST on 2017/9/20 13:55
 */
@Service
public class PurchaseReturnServiceImpl implements PurchaseReturnService {

    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    @Autowired
    private PurchaseReturnDetailMapper purchaseReturnDetailMapper;

    @Autowired
    private PurchaseReturnOtherMapper purchaseReturnOtherMapper;

    @Autowired
    private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private LotNumberMapper lotNumberMapper;

    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;

    @Autowired
    private SupplierSalerMapper supplierSalerMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;

    @Autowired
    private SupplierMapper supplierMapper;
    
    @Autowired
    private SupplierBusinessMapper supplierBusinessMapper;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PurchaseOrderOtherMapper purchaseOrderOtherMapper;

    @Autowired
    private QualitySetMapper qualitySetMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private ReturnApprovalProcessor returnApprovalProcessor;

    @Autowired
    private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private GoodsComponent goodsComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private SupplierVarietiesMapper supplierVarietiesMapper;

    @Autowired
    private PurchaseReturnModifyRecordMapper purchaseReturnModifyRecordMapper;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private SupplierComponent supplierComponent;

    @Autowired
    private InOutRecordDetailMapper inOutRecordDetailMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    /**
     * 分页查询商品信息
     *
     * @param param
     * @param enterpriseId
     * @return
     */
    @Override
    public List<PurchaseReturnGoodsVO> getGoodsInfoByPurchaseReturn(String param, Long enterpriseId, Long supplierId) {

    	SupplierBusiness bus = supplierBusinessMapper.getSupplierBuseinessBySupplierId(supplierId);
		if(bus == null){
			throw new BusinessException("该供应商业务子表查不到数据！");
		}
    	
        List<PurchaseReturnGoodsVO> goodsInfoByPurchaseReturns = purchaseReturnMapper.getGoodsInfoByPurchaseReturn(param, enterpriseId,enterpriseId,bus.getManagementMode());

        if(null != supplierId){
            if(!CollectionUtils.isEmpty(goodsInfoByPurchaseReturns)){


//                for(PurchaseReturnGoodsVO purchaseReturnGoodsVO : goodsInfoByPurchaseReturns){
                Iterator<PurchaseReturnGoodsVO> it = goodsInfoByPurchaseReturns.iterator();
                while (it.hasNext()){
                    PurchaseReturnGoodsVO purchaseReturnGoodsVO = it.next();
                    List<SupplierVarieties> supplierVarieties = supplierVarietiesMapper.selectSupplierVarietiesByParam(enterpriseId, purchaseReturnGoodsVO.getGoodsId(), supplierId);
                    if(CollectionUtils.isEmpty(supplierVarieties)){
                        it.remove();
                        continue;
                    }
//                    //set默认货位信息
//                    SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(purchaseReturnGoodsVO.getGoodsId(),enterpriseId);
//                    Map<String,Object> warehouseShelf = warehouseShelfMapper.getShelfInfoById(safetyStock.getDefaultShelfId());
//                    if(warehouseShelf == null){
//                        throw new BusinessException("货位不存在");
//                    }
//                    Object jobArea = warehouseShelf.get("jobArea");
//                    purchaseReturnGoodsVO.setShelfId(safetyStock.getDefaultShelfId());
//                    purchaseReturnGoodsVO.setShelfName(safetyStock.getDefaultShelfName());
//                    purchaseReturnGoodsVO.setShelfStatusDesc(ShelfStatus.getName((Integer) jobArea));
                    for(SupplierVarieties pd : supplierVarieties){
                        for(PurchaseReturnGoodsVO pr : goodsInfoByPurchaseReturns){

                            if(pr.getGoodsId().equals(pd.getGoodsId())){
                                pr.setTaxRate(pd.getLastPurTaxRate());
                                pr.setTaxRateId(pd.getLastPurTaxRateId());

                                /**
                                 * 单价
                                 */
                                pr.setUnitPrice(pd.getLastPurPrice() == null ? BigDecimal.ZERO : pd.getLastPurPrice());
                            }
                        }
                    }
                    /**
                     * 1.查找出入库明细[当前供应商采购入库的相同批号商品记录] 如果没有则不展示
                     * 2.如果找到了，数量大于可用数量，那么 显示的数量为可用数量
                     * 3.如果数量小于库存可用数量，那么展示的是当时入库数量
                     */
                    //param :supplierId,enterpriseId
                    Long goodsId = purchaseReturnGoodsVO.getGoodsId();
                    Long lotNumberId = purchaseReturnGoodsVO.getLotNumberId();
                    Integer type = OrderRule.PURCHASE_IN.getType();
                    List<InOutRecordDetail> details = inOutRecordDetailMapper.
                            getInOutRecordListByPurchaseIn(supplierId,enterpriseId,goodsId,lotNumberId,type);
                    if (details == null || details.size() <= 0){
                        it.remove();
                    }else {
                        //真实采购数量
                        BigDecimal realQuatity = BigDecimal.ZERO;
                        for (InOutRecordDetail d : details) {
                            realQuatity = realQuatity.add(d.getQuantity());
                        }
                        if (realQuatity.compareTo(purchaseReturnGoodsVO.getUsableQuantity()) <=  0){
                            purchaseReturnGoodsVO.setUsableQuantity(realQuatity);
                        }
                    }
                }
            }
        }

        return goodsInfoByPurchaseReturns;
    }


    @Override
    public List<PurchaseReturn> getPurchaseReturnsByParam(UserVO userVO, Page page, Map<String, Object> map){

        com.github.pagehelper.Page<List<PurchaseReturn>> hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        List<PurchaseReturn> purchaseReturns = purchaseReturnMapper.selectByParam(map);

        List<PurchaseReturnShowVO> purchaseReturnShowVOS = new ArrayList<>();
        for(PurchaseReturn pd : purchaseReturns){
            PurchaseReturnShowVO purchaseReturnShowVO = PurchaseReturnShowVO.getPurchaseReturnShowVO(pd);
            purchaseReturnShowVOS.add(purchaseReturnShowVO);
        }

        PurchaseReturnTotalShowVO purchaseReturnTotalShowVO = purchaseReturnMapper.selectCountByParam(map);

        purchaseReturnTotalShowVO.setPurchaseReturnShowVOList(purchaseReturnShowVOS);

        page.setResult(purchaseReturnTotalShowVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
        page.setTotalPage(hPage.getPages());

        return purchaseReturns;
    }

    /**
     * 导出
     *
     * @param output
     * @param id 购进退出出库单id
     */
    @Override
    public void exportExcel(OutputStream output, Long id) {

        PurchaseReturnShowVO returnShowVO = getPurchaseReturnDetails(id);
        PurchaseReturn purchaseReturn = purchaseReturnMapper.selectByPrimaryKey(id);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(purchaseReturn.getEnterpriseId());//企业
        List<String> headerList = new ArrayList<>();
        headerList.add(enterprise.getName() + "总部");
        headerList.add("购进退出单");

        StringBuilder titleSecondRow = new StringBuilder();

        titleSecondRow.append("供货单位编码:").append(Optional.ofNullable(returnShowVO.getSupplierCode()).orElse(""))
                .append("  供货单位名称:").append(Optional.ofNullable(returnShowVO.getSupplierName()).orElse(""))
                .append("  供货单位销售人员:").append(Optional.ofNullable(returnShowVO.getSupplierName()).orElse(""))
                .append("  联系电话：").append(Optional.ofNullable(returnShowVO.getSupplierSalerPhone()).orElse(""))
                .append("  出库单号：").append(Optional.ofNullable(returnShowVO.getCode()).orElse(""))
                .append("  订单日期：").append(Optional.ofNullable(DateUtils.getDate(returnShowVO.getReturnDate())).orElse(""))
                .append("  退货类型: ").append(Optional.ofNullable(returnShowVO.getReturnTypeDesc()).orElse(""))
                .append("  退货人员：").append(Optional.ofNullable(returnShowVO.getReturnManName()).orElse(""));

        List<String> titleSecondRowList = new ArrayList<>();
        titleSecondRowList.add(titleSecondRow.toString());

        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
        rowHeaderMap.put("goodsCode","商品编码");
        rowHeaderMap.put("goodsGenericName","通用名称");
        rowHeaderMap.put("dosageName","剂型");
        rowHeaderMap.put("goodsSpecification","规格");
        rowHeaderMap.put("unitName","单位");
        rowHeaderMap.put("manufacturer","生产厂商");
        rowHeaderMap.put("lotNumber","批号");

        rowHeaderMap.put("productDateDesc","生产日期");
        rowHeaderMap.put("validDateDesc","有效期至");
        rowHeaderMap.put("quantity","数量");
        rowHeaderMap.put("unitPrice","单价");
        rowHeaderMap.put("lineDiscount","折扣");
        rowHeaderMap.put("amount","金额");
        rowHeaderMap.put("taxRate","税率(%)");
        rowHeaderMap.put("notaxRealPrice","不含税单价");
        rowHeaderMap.put("notaxRealAmount","不含税金额");
        rowHeaderMap.put("returnReasonIdsDesc","退货原因");

        StringBuilder endString = new StringBuilder();
        endString.append("总商品金额：").append(returnShowVO.getAmountTotal()).append(";")
                .append("折扣： ").append(returnShowVO.getWholeDiscount()).append("%   ").append(returnShowVO.getAmountTotal().multiply(new BigDecimal(100).subtract(returnShowVO.getWholeDiscount()))).append(";")
                .append("优惠：").append(returnShowVO.getWholeDiscountAmount()).append(";")
                .append("总额：").append(returnShowVO.getAmountTotal().subtract(returnShowVO.getWholeDiscount().add(returnShowVO.getWholeDiscountAmount()))).append(";")
                .append("不含税总额：").append(returnShowVO.getNotaxRealAmountTotal()).append(";")
                .append("税额：").append(returnShowVO.getTaxAmountTotal());

        // 单元格合并
        // 四个参数分别是：起始行，起始列，结束行，结束列
//        CellRangeAddress r1 = new CellRangeAddress(0, (short) 0, 0,
//                (short) (rowHeaderMap.size()-1));
//
//        CellRangeAddress r2 = new CellRangeAddress(1, (short) 1, 1,
//                (short) (rowHeaderMap.size()-1));

        purchaseGeneralComponent.commExcelExport(
                output
                ,rowHeaderMap
                ,returnShowVO.getPurchaseReturnDetailShowVOS()
                ,headerList
                ,titleSecondRowList
                ,endString.toString()
                ,true
                ,new ArrayList<>()
        );
    }

    @Override
    public PurchaseReturnShowVO getPurchaseReturnDetails(Long id) {

        PurchaseReturn purchaseReturn = purchaseReturnMapper.selectByPrimaryKey(id);

        List<PurchaseReturnDetail> purchaseReturnDetails = purchaseReturnDetailMapper.selectByReturnId(purchaseReturn.getId());

        PurchaseReturnOther purchaseReturnOther = purchaseReturnOtherMapper.selectByReturnId(id);

        List<Long> returnReasonIds = PurchaseReturnDetail.getReturnReasonIds(purchaseReturnDetails);

        List<QualitySet> qualitySets = qualitySetMapper.selectByIds(returnReasonIds);


        PurchaseReturnShowVO purchaseReturnShowVO = PurchaseReturnShowVO.getPurchaseReturnShowVO(
                purchaseReturn
                , purchaseReturnDetails
                , purchaseReturnOther
                , qualitySets
        );

        //若是已完成单据，判断供货商和商品所属企业
        if(purchaseReturnShowVO.getApprovalStatus().equals(PurchaseStatus.FINISHED.getStatus())) setDifferentShow(purchaseReturnShowVO);
        return purchaseReturnShowVO;
    }
    
    /**
     * 将已完成的购进推出单据  ，仅属于加盟店的供货单位和商品信息字体颜色：#2d8cf0 ，添加供货单位所属的企业id和商品所属企业id以示区分
     * @param purchaseReturnShowVO
     */
    private void setDifferentShow(PurchaseReturnShowVO purchaseReturnShowVO) {
    	if(purchaseReturnShowVO.getSupplierId() != null) {
    		Supplier supplier = supplierMapper.selectByPrimaryKey(purchaseReturnShowVO.getSupplierId());
    		if(supplier != null) purchaseReturnShowVO.setEnterpriseId(supplier.getEnterpriseId());
    	}
    	List<PurchaseReturnDetailShowVO> purchaseReturnDetailShowVOS = purchaseReturnShowVO.getPurchaseReturnDetailShowVOS();
    	Set<Long> goodsIds = new HashSet<>();
    	for(PurchaseReturnDetailShowVO vo:purchaseReturnDetailShowVOS) {
    		goodsIds.add(vo.getGoodsId());
    	}
    	List<Long> paramlist = new ArrayList<>(goodsIds);
    	if(!goodsIds.isEmpty()) {
    		List<Goods> list = purchaseReturnMapper.selectGoodsEnterpriseIds(paramlist);
    		if(!list.isEmpty()) {
    			for(PurchaseReturnDetailShowVO vo:purchaseReturnDetailShowVOS) {
    				for(Goods g:list) {
    					if(vo.getGoodsId().equals(g.getId())) {
    						vo.setEnterpriseId(g.getEnterpriseId());
    						break;
    					}
    				}
    			}
    		}
    	}
    }
    /**
     * 分页查询采购入库信息
     *
     * @param page
     * @param getInStorageParamVO
     * @return
     */
    @Override
    public void getInStorageForReturn(Page<List<PurchaseInStorageForReturnVO>> page, GetInStorageParamVO getInStorageParamVO) {
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<PurchaseInStorageForReturnVO> purchaseInStorageForReturn = purchaseInStorageMapper.getPurchaseInStorageForReturn(getInStorageParamVO);

        page.setResult(purchaseInStorageForReturn);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void getInStorageDetailForReturn(Page<List<PurchaseInStorageDetailForReturnVO>> page, GetInStorageParamVO getInStorageParamVO) {
        PageHelper.startPage(page.getStart(),page.getPageSize());
        List<PurchaseInStorageDetailForReturnVO> detailForReturnVOList = purchaseInStorageShelfMapper.getInStorageDetail(getInStorageParamVO);
        int temp = 0;
        Iterator<PurchaseInStorageDetailForReturnVO> iterator = detailForReturnVOList.iterator();
        while (iterator.hasNext()){
            PurchaseInStorageDetailForReturnVO next = iterator.next();
            BigDecimal canReturnQuantity = next.getQuantity();
            Long inStorageId = next.getId();
            String lotNumber = next.getLotNumber();
            Long goodsId = next.getGoodsId();
            //查询批号id
            LotNumber lotNumInfo = lotNumberMapper.getLotNumInfo(getInStorageParamVO.getEnterpriseId(), goodsId, lotNumber);
            next.setLotNumberId(lotNumInfo != null ? lotNumInfo.getId() : null);
            //批号的库存可用数量
            BigDecimal usableQuantity = stockMapper.getUsableQuantity(lotNumber,getInStorageParamVO.getEnterpriseId(),goodsId);
            /**
             * 根据购进退出单更新入库单的可退数量
             */
            BigDecimal quantityTotal = purchaseReturnMapper.getQuantityTotal(getInStorageParamVO.getEnterpriseId(), inStorageId);
            if(quantityTotal != null){
                canReturnQuantity = canReturnQuantity.subtract(quantityTotal);
                if(canReturnQuantity.compareTo(BigDecimal.ZERO) == 0 || canReturnQuantity.compareTo(BigDecimal.ZERO) == -1){
                    iterator.remove();
                    temp++;
                    continue;
                }
            }

            /**
             * 1.基于入库单A,入库的时候,保存入库数量B等于可退数量C
             * 2.该批号可用库存数量D
             * 如果C>D,以库存可用数量D为准
             * 如果C<D,以C为准
             */
            if(usableQuantity != null){
                if(usableQuantity.compareTo(BigDecimal.ZERO) == 0){
                    iterator.remove();
                    temp++;
                    continue;
                }
                if(canReturnQuantity.compareTo(usableQuantity) == 1){
                    //可退数量大于库存数量
                    next.setQuantity(usableQuantity);
                    next.setUsableQuantity(usableQuantity);
                } else {
                    next.setUsableQuantity(canReturnQuantity);
                    next.setQuantity(canReturnQuantity);
                }
            }

        }
        Integer countInStorageDetail = purchaseInStorageShelfMapper.getCountInStorageDetail(getInStorageParamVO);
        page.setResult(detailForReturnVOList);
        page.setTotalRecord(countInStorageDetail - temp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserVO userVO, PurchaseReturnSaveOrUpdateVO returnSaveOrUpdateVO, boolean isAdd) throws Exception {

        commonComponent.generateAndSavePurchaseReturn(userVO,returnSaveOrUpdateVO,isAdd,false);

    }


    @Override
    public void cancel(Long id, UserVO userVO){
        PurchaseReturn purchaseReturn = purchaseReturnMapper.selectByPrimaryKey(id);
        approvalFlowComponent.cancel(
                ApprovalFlowContentModel.getReturnCode()
                ,purchaseReturn.getId()
                ,purchaseReturn.getEnterpriseId()
                , returnApprovalProcessor
        );

        // 释放购进退出单锁定的库存
        commonComponent.releaseStock(userVO, purchaseReturn);
    }


    @Override
    public Page<List<PurchaseReturnModifyRecord>> getPurchaseReturnModifyRecord(UserVO use, Long id, Page page){

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
        List<PurchaseReturnModifyRecord> purchaseReturnModifyRecords = purchaseReturnModifyRecordMapper.selectByReturnId(id);

        page.setResult(purchaseReturnModifyRecords);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public List<SupplierOrderVO> getSupplier(UserVO userVO) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("status", 1);
        param.put("validFlag", 1);
        param.put("enterpriseId", userVO.getEnterpriseId());
        param.put("ownerId", userVO.getEnterpriseId());
        List<SupplierOrderVO> supplierOrderVOS = purchaseOrderMapper.selectSupplierByReturn(param);
        Set<SupplierOrderVO> set = new HashSet();
        for(SupplierOrderVO orderVO : supplierOrderVOS){
            set.add(orderVO);
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO) {
        Class<PurchaseReturnSaveOrUpdateVO> clazz = PurchaseReturnSaveOrUpdateVO.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),OrderRule.PURCHASE_RETURN.getCodePrefix(),clazz);

    }

    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<PurchaseReturnSaveOrUpdateVO> draftCache) {
        String redisKeyValue = draftCache.getId();

        DraftCacheVO<PurchaseReturnSaveOrUpdateVO> draftCacheVO = new DraftCacheVO();

        draftCacheVO.setOrderCode(OrderRule.PURCHASE_RETURN.getCodePrefix());

        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());

        draftCacheVO.setOrderData(draftCache.getOrderData());
        draftCacheVO.setId(redisKeyValue);
        PurchaseReturnSaveOrUpdateVO returnSaveOrUpdateVO = draftCacheVO.getOrderData();
        Supplier supplier = supplierMapper.selectByPrimaryKey(returnSaveOrUpdateVO.getSupplierId());
        returnSaveOrUpdateVO.setSupplierName(supplier.getName());
        returnSaveOrUpdateVO.setSupplierCode(supplier.getCode());

        User user = userMapper.selectByPrimaryKey(returnSaveOrUpdateVO.getReturnManId());

        returnSaveOrUpdateVO.setReturnManName(user.getName());
        return redisComponent.saveDraftCacheVO(draftCacheVO);
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) {

        redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);

    }

	@Override
	public Map<String, Object> getDefaultDistrInfo(Long id) {
		
		Long orderId = purchaseReturnMapper.getPurchaseOrderId(id);
		if(orderId == null) return null;
		PurchaseOrderOther vo=purchaseOrderOtherMapper.selectByOrderId(orderId);
		if(vo == null) return null;
		Map<String,Object> resmap = new HashMap<>();
		resmap.put("carriageMode", vo.getCarriageMode());
		resmap.put("carriageModeName", DistributionType.getName(vo.getCarriageMode()));
		resmap.put("carriageUnit", vo.getCarriageUnit() == null?"":vo.getCarriageUnit());
		return resmap;
	}

}