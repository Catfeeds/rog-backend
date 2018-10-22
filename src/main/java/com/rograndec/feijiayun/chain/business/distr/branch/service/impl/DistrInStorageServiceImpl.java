package com.rograndec.feijiayun.chain.business.distr.branch.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierVarietiesMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.*;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.*;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInCheckService;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReceiveService;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInStorageService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.*;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageGoodsLotNumberVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageVO;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrOutService;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.service.PurchaseAddInStorageService;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageDtlVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageGoodsLotNumberVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageOtherVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageVO;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheck;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.ConclusionVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrSendStatus;
import com.rograndec.feijiayun.chain.common.model.CalculateResultModel;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.LastInPriceVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DistrInStorageServiceImpl implements DistrInStorageService{

    @Autowired
    PurchaseCheckMapper purchaseCheckMapper;
    @Autowired
    private DistrInMapper distrInMapper;

    @Autowired
    private DistrInDetailMapper distrInDetailMapper;

    @Autowired
    private DistrInShelfMapper distrInShelfMapper;

    @Autowired
    private DistrInCheckMapper distrInCheckMapper;

    @Autowired
    private DistrInCheckDetailMapper distrInCheckDetailMapper;


    @Autowired
    private DistrInNoticeDetailMapper distrInNoticeDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private DistrInNoticeMapper distrInNoticeMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private DistrComponent distrComponent;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private LotNumberMapper lotNumberMapper;

    @Autowired
    private DistrOutMapper distrOutMapper;

    @Autowired
    private DistrOutDetailMapper distrOutDetailMapper;

    @Autowired
    private DistrOutShelfMapper distrOutShelfMapper;

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    DistrInCheckService distrInCheckService;

    @Autowired
    DistrInReceiveService distrInReceiveService;

    @Autowired
    DistrInStorageService distrInStorageService;

    @Autowired
    private DistrOutService distrOutService;

    @Autowired
    private GoodsBusinessMapper businessMapper;

    @Autowired
    PurchaseOrderMapper orderMapper;
    @Autowired
    PurchaseOrderDetailMapper orderDetailMapper;
    @Autowired
    PurchaseOrderOtherMapper orderOtherMapper;

    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    private DistrInReceiveMapper distrInReceiveMapper;
    @Autowired
    private DistrInReceiveDetailMapper distrInReceiveDetailMapper;

    @Autowired
    private PurchaseAddInStorageService purchaseAddInStorageService;

    @Autowired
    private DistrSendMapper distrSendMapper;

    @Autowired
    private DistrSendDetailMapper distrSendDetailMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private DistrReqPlanMapper distrReqPlanMapper;

    @Autowired
    private DistrReqPlanDetailMapper distrReqPlanDetailMapper;

    @Autowired
    EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private DistrInCheckLotMapper distrInCheckLotMapper;

    @Autowired
    private SupplierVarietiesMapper supplierVarietiesMapper;

    @Autowired
    private FinanceComponent financeComponent;

    @Override
    public Page getInstorageHasBeenPage(Page page, Date startTime, Date endTime, String distrUnitCode, String distrUnitName, String code, String storageManName, Integer distrType, String order, String sort, Long enterpriseId) {
        if("inDate".equals(order)){
            order = "in_date";
        }else if("code".equals(order)){
            order = "code";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("distrUnitCode",distrUnitCode);
        map.put("distrUnitName",distrUnitName);
        map.put("code",code);
        map.put("distrType",distrType);
        map.put("storageManName",storageManName);
        map.put("order",order);
        map.put("sort",sort);
        map.put("enterpriseId",enterpriseId);
        List<Integer> statusList = new ArrayList<>();
        statusList.add(DistrInStatus.WAIT_BILL);
        statusList.add(DistrInStatus.FINISHED);
        statusList.add(DistrInStatus.PART_BILL);
        map.put("statusList",statusList);
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        List<DistrIn> distrInstorageHasBeenList = distrInMapper.getInstorageHasBeenPage(map);
        DistrInstorageStasticVO stasticVO = distrInMapper.selectStastic(map);
        List<DistrInstoragePageVO> pageVOList = new ArrayList<DistrInstoragePageVO>();
        for (DistrIn d:distrInstorageHasBeenList) {
            DistrInstoragePageVO vo = new DistrInstoragePageVO();
            vo = DistrInstoragePageVO.converToVO(d);
            if (stasticVO == null){
                vo.setStasticRealAmountTotal(BigDecimal.ZERO);
                vo.setStasticNotaxRealAmountTotal(BigDecimal.ZERO);
                vo.setStasticTaxAmountTotal(BigDecimal.ZERO);
            }else {
                vo.setStasticRealAmountTotal(stasticVO.getStasticRealAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticRealAmountTotal());
                vo.setStasticNotaxRealAmountTotal(stasticVO.getStasticNotaxRealAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticNotaxRealAmountTotal());
                vo.setStasticTaxAmountTotal(stasticVO.getStasticTaxAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticTaxAmountTotal());
            }
            pageVOList.add(vo);
        }
        page.setResult(pageVOList);
        Integer totalRecord = distrInMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public DistrInstorageFormVO getInstorageHasBeenDeatil(Long enterpriseId, Long id) {
        DistrInstorageFormVO vo = new DistrInstorageFormVO();
        DistrIn distrIn = distrInMapper.selectByPrimaryKey(id);
        vo = DistrInstorageFormVO.convertToVO(distrIn);
        //查询品种明细单
        List<DistrInDetail> detailList = distrInDetailMapper.selectByEnterpriseIdAndInId(id);
        List<DistrInstorageDtlVO>  detailVOList = new ArrayList<DistrInstorageDtlVO>();
        for (DistrInDetail d : detailList) {
            DistrInstorageDtlVO dtlVO = new DistrInstorageDtlVO();
            dtlVO = DistrInstorageDtlVO.convertToVO(d);
            //查询货位明细
            List<DistrInStorageShelfDtlVO> shelfDtlVOList = new ArrayList<DistrInStorageShelfDtlVO>();
            List<DistrInShelf> inShelfList = distrInShelfMapper.selectByEnterpriseIdAndGoodIdAndInIdAndDtlId(enterpriseId,d.getGoodsId(),id,d.getId());
            for (DistrInShelf shelf:inShelfList) {
                DistrInStorageShelfDtlVO shelfDtlVO = new DistrInStorageShelfDtlVO();
                shelfDtlVO = DistrInStorageShelfDtlVO.convertTo(shelf);
                shelfDtlVOList.add(shelfDtlVO);
            }
            dtlVO.setDistrInStorageShelfDtlVOList(shelfDtlVOList);
            detailVOList.add(dtlVO);
        }
        vo.setDistrInstorageDtlList(detailVOList);
        return vo;
    }

    @Override
    public void export(OutputStream output, DistrInstorageFormVO distrInstorageFormVO, UserVO loginUser) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("shelfStatusDesc","质量状况");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        map.put("shelfName","货位");
        //#####
        map.put("quantity","数量");
        map.put("unitPrice","单价");
        map.put("lineDiscount","折扣");
        map.put("amount","金额");
        map.put("taxRate","税率");
        map.put("notaxRealPrice","不含税单价");
        map.put("notaxRealAmount","不含税金额");
        map.put("taxAmount","税额");
        map.put("remark","备注");
        map.put("retailPrice","零售单价");
        map.put("memberPrice","会员单价");
        List<DistrInstorageDtlVO> distrInstorageDtlList = distrInstorageFormVO.getDistrInstorageDtlList();
        List<DistrInStorageExcelVO> excelVOList = new ArrayList<DistrInStorageExcelVO>();
        if (distrInstorageDtlList != null && distrInstorageDtlList.size() > 0){
            for (DistrInstorageDtlVO dtlVO : distrInstorageDtlList) {
                List<DistrInStorageShelfDtlVO> distrInStorageShelfDtlVOList = dtlVO.getDistrInStorageShelfDtlVOList();
                if (distrInStorageShelfDtlVOList != null && distrInStorageShelfDtlVOList.size() > 0){
                    for (DistrInStorageShelfDtlVO shelfVO :distrInStorageShelfDtlVOList) {
                        DistrInStorageExcelVO excelVO = new DistrInStorageExcelVO();
                        excelVO = DistrInStorageExcelVO.convertToVO(shelfVO,dtlVO);
                        excelVOList.add(excelVO);
                    }
                }
            }
        }
        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("配货单位编码:");
        titleSecondRow.append(distrInstorageFormVO.getDistrUnitCode() == null ? "" : distrInstorageFormVO.getDistrUnitCode());
        titleSecondRow.append("   ");
        titleSecondRow.append("配货单位名称:");
        titleSecondRow.append(distrInstorageFormVO.getDistrUnitName() == null ? "" : distrInstorageFormVO.getDistrUnitName());
        titleSecondRow.append("   ");
        titleSecondRow.append("配进入库单号:");
        titleSecondRow.append(distrInstorageFormVO.getCode() == null ? "" : distrInstorageFormVO.getCode());
        titleSecondRow.append("   ");
        titleSecondRow.append("入库日期");
        titleSecondRow.append(distrInstorageFormVO.getInDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(distrInstorageFormVO.getInDate()));
        titleSecondRow.append("   ");
        titleSecondRow.append("入库人员:");
        titleSecondRow.append(distrInstorageFormVO.getStorageManName() == null ? "" : distrInstorageFormVO.getStorageManName());
        titleSecondRow.append("   ");
        titleSecondRow.append("配货类型:");
        titleSecondRow.append(distrInstorageFormVO.getDistrType() == 0 ? "总部配送" : (distrInstorageFormVO.getDistrType() == 3 ? "分店间调剂" : (distrInstorageFormVO.getDistrType() == 4 ? "直调配送": "")));
        titleSecondRow.append("   ");
        titleSecondRow.append("备注:");
        titleSecondRow.append(distrInstorageFormVO.getRemark() == null ? "" : distrInstorageFormVO.getRemark());
        titleSecondRow.append("   ");
        secondTitle.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        end.append(distrInstorageFormVO.getQuantityTotal() == null ? "数量数据为空" : distrInstorageFormVO.getQuantityTotal());
        end.append("件商品  ");
        end.append("总金额为：");
        end.append(distrInstorageFormVO.getAmountTotal() == null ? "金额合计为空" : distrInstorageFormVO.getAmountTotal());
        //每行以分号隔开
        end.append(";");
        end.append("折扣：");
        end.append(distrInstorageFormVO.getWholeDiscount());
        end.append("%");
        end.append("   ");
        if (distrInstorageFormVO.getAmountTotal() != null && distrInstorageFormVO.getWholeDiscount() != null){
            end.append(distrInstorageFormVO.getAmountTotal().
                    subtract(distrInstorageFormVO.getAmountTotal().
                            multiply(distrInstorageFormVO.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)));
        }
        end.append(";");
        end.append("优惠：");
        end.append(distrInstorageFormVO.getWholeDiscountAmount());
        end.append(";");
        end.append("总额：");
        end.append(distrInstorageFormVO.getRealAmountTotal());
        end.append(";");
        end.append("不含税总额：");
        end.append(distrInstorageFormVO.getNotaxRealAmountTotal());
        end.append(";");
        end.append("税额：");
        end.append(distrInstorageFormVO.getTaxAmountTotal());
        end.append(";");
        List<String> name = new ArrayList<String>();
        name.add(loginUser.getEnterpriseName());
        name.add("配进入库单");
        BigDecimal totalQuantity = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNotaxRealAmount = BigDecimal.ZERO;
        BigDecimal totalTaxAmount = BigDecimal.ZERO;
        for (DistrInstorageDtlVO d:distrInstorageDtlList) {
            totalQuantity = totalQuantity.add(d.getQuantity());
            totalAmount = totalAmount.add(d.getAmount());
            totalNotaxRealAmount = totalNotaxRealAmount.add(d.getNotaxRealAmount());
            totalTaxAmount = totalTaxAmount.add(d.getTaxAmount());
        }
        StringBuilder endTotal = new StringBuilder();
        endTotal.append(totalQuantity);
        endTotal.append(";");
        endTotal.append(totalAmount);
        endTotal.append(";");
        endTotal.append(totalNotaxRealAmount);
        endTotal.append(";");
        endTotal.append(totalTaxAmount);
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("amount");
        locationList.add("notaxRealAmount");
        locationList.add("taxAmount");
        purchaseGeneralComponent.commExcelDistrExport(output,map,excelVOList,name,secondTitle,end.toString(),endTotal.toString(),locationList);
    }

    @Override
    public void getDistrInCheckList(Page<List<DistrInCheck2ListVO>> page, RequestParamForListVO param, Long enterpriseId) {
//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getStart(), page.getPageSize());
        param.setStart(page.getStart());
        Integer count = distrInCheckMapper.getDistrInCheckListCount(param, enterpriseId, new CheckStatusType(0));
        List<DistrInCheck2ListVO> inCheckList = distrInCheckMapper.getDistrInCheckList(param, enterpriseId, new CheckStatusType(0));
        inCheckList.forEach(item->{
            item.setDistrTypeName(DistrType.getValue(item.getDistrType()));
            item.setStatusName(DistrInStatus.getStatusDesc(item.getStatus()));
        });
        page.setResult(inCheckList);
        page.setTotalRecord(count);

    }

    @Override
    public DistrInstorageFormVO queryStayInstorageFormByCheckId(Long id, UserVO loginUser) {
        DistrInstorageFormVO vo = new DistrInstorageFormVO();
        vo.setCheckId(id);
        /**
         * 验收数据明细列表以下
         */
        DistrInCheck2DetailVO inCheck2DetailVO = distrInCheckMapper.getDistrInCheckById(loginUser.getEnterpriseId(), id);
        List<DistrInCheckDetail2DetailVO> inCheckDetail2DetailList = new ArrayList<DistrInCheckDetail2DetailVO>();
        if(inCheck2DetailVO != null){
            inCheckDetail2DetailList = distrInCheckDetailMapper.getInCheckDetail2Detail(loginUser.getEnterpriseId(), id);
            inCheck2DetailVO.setDetailVOList(inCheckDetail2DetailList);
        }
        /**
         * 验收数据明细列表以上
         */
        //构建头数据
        vo.setDistrUnitId(inCheck2DetailVO.getDistrUnitId());
        vo.setDistrUnitCode(inCheck2DetailVO.getDistrUnitCode());
        vo.setDistrUnitName(inCheck2DetailVO.getDistrUnitName());
        vo.setDistrType(inCheck2DetailVO.getDistrType());
        vo.setOutboundUnitId(inCheck2DetailVO.getOutboundUnitId());
        vo.setOutboundUnitCode(inCheck2DetailVO.getOutboundUnitCode());
        vo.setOutboundUnitName(inCheck2DetailVO.getOutboundUnitName());
        //不变的整单折扣和整单优惠金额不变
        DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(inCheck2DetailVO.getNoticeId());
        vo.setWholeDiscount(distrInNotice.getWholeDiscount());
        vo.setWholeDiscountAmount(distrInNotice.getWholeDiscountAmount());
        //数量 + 品种
        BigDecimal quantity = BigDecimal.ZERO;
        HashSet set = new HashSet();
        //金额合计（整单优惠前的金额合计）
        BigDecimal amountTotal = BigDecimal.ZERO;
        //实际金额合计===订单的实际单价 * 验收数量
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        //不含税金额合计
        BigDecimal noTaxAmountTotal = BigDecimal.ZERO;
        //构建主表中的基础单据数据
        //构建中间商品数据
        List<DistrInstorageDtlVO> detailList = new ArrayList<DistrInstorageDtlVO>();
        if (inCheckDetail2DetailList != null){
            for (DistrInCheckDetail2DetailVO checkDetailVO:inCheckDetail2DetailList) {
                DistrInstorageDtlVO detailVO = new DistrInstorageDtlVO();
                detailVO.setBaseOrderDtlId(checkDetailVO.getId());
                detailVO.setGoodsId(checkDetailVO.getGoodsId());
                set.add(checkDetailVO.getGoodsId());
                detailVO.setGoodsCode(checkDetailVO.getGoodsCode());
                detailVO.setGoodsGenericName(checkDetailVO.getGoodsGenericName());
                detailVO.setDosageId(checkDetailVO.getDosageId());
                detailVO.setDosageName(checkDetailVO.getDosageName());
                detailVO.setGoodsSpecification(checkDetailVO.getGoodsSpecification());
                detailVO.setUnitId(checkDetailVO.getUnitId());
                detailVO.setUnitName(checkDetailVO.getUnitName());
                detailVO.setManufacturerId(checkDetailVO.getManufacturerId());
                detailVO.setManufacturer(checkDetailVO.getManufacturer());
                detailVO.setGoodsPlace(checkDetailVO.getGoodsPlace());

                //单价+折扣+金额+税率+不含税单价+不含税金额+税额
                DistrInCheckDetail distrInCheckDetail = distrInCheckDetailMapper.selectByPrimaryKey(checkDetailVO.getId());
                //查找配进订单的明细
                DistrInNoticeDetail distrInNoticeDetail = distrInNoticeDetailMapper.selectByPrimaryKey(distrInCheckDetail.getNoticeDtlId());
                //从上上游取出的实际单价
                BigDecimal realPrice = distrInNoticeDetail.getRealPrice();
                //从上上游取出的不含税单价
                BigDecimal noTaxPrice = distrInNoticeDetail.getNotaxRealPrice();
                //零售单价和会员单价********查价格清单
                detailVO.setRetailPrice(distrInNoticeDetail.getRetailPrice());
                detailVO.setMemberPrice(distrInNoticeDetail.getMemberPrice());
                detailVO.setUnitPrice(distrInNoticeDetail.getUnitPrice());
                //算整单实际金额
                realAmountTotal = realAmountTotal.add(realPrice.multiply(checkDetailVO.getReceiveQuantity()).setScale(2, RoundingMode.HALF_UP));
                //算整单不含税实际金额
                noTaxAmountTotal = noTaxAmountTotal.add(noTaxPrice.multiply(checkDetailVO.getReceiveQuantity()).setScale(2, RoundingMode.HALF_UP));
                //数量从上游验收单取收货数量
                detailVO.setQuantity(checkDetailVO.getReceiveQuantity());
                quantity = quantity.add(checkDetailVO.getReceiveQuantity());
                //行折扣
                detailVO.setLineDiscount(distrInNoticeDetail.getLineDiscount());
                //整单优惠前金额需要自己算---因为是获取收货数量
                BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(checkDetailVO.getReceiveQuantity(), distrInNoticeDetail.getUnitPrice(), distrInNoticeDetail.getLineDiscount());
                detailVO.setAmount(amount);
                amountTotal = amountTotal.add(amount);
                //税率ID和税率
                detailVO.setTaxRateId(distrInNoticeDetail.getTaxRateId());
                detailVO.setTaxRate(distrInNoticeDetail.getTaxRate());
                //不含税单价
                detailVO.setNotaxRealPrice(distrInNoticeDetail.getNotaxRealPrice());
                //不含税金额[不含税单价 * 验收数量]
                BigDecimal notaxRealAmount = checkDetailVO.getReceiveQuantity().multiply(distrInNoticeDetail.getNotaxRealPrice()).setScale(2, RoundingMode.HALF_UP);
                detailVO.setNotaxRealAmount(notaxRealAmount);
                //税额(实际单价 * 验收数量 = 实际金额 - 不含税金额)
                BigDecimal realAmout = distrInNoticeDetail.getRealPrice().multiply(checkDetailVO.getReceiveQuantity()).setScale(2, RoundingMode.HALF_UP);
                detailVO.setTaxAmount(CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(realAmout,notaxRealAmount));
                //当前一条明细中的所有批号信息
                List<DistrInCheckLot2DetailVO> lot2DetailVOList = checkDetailVO.getLot2DetailVOList();
                //定义批号明细
                List<DistrInStorageShelfDtlVO> shelfDtlVOList = new ArrayList<DistrInStorageShelfDtlVO>();
                if (lot2DetailVOList != null){
                    for (DistrInCheckLot2DetailVO lotVO:lot2DetailVOList) {
                        //质量状况 + 批号 + 生产日期 + 有效期至 + 货位(用户选择点击入库) + 数量
                        //如果同时有合格和不合格两种验收商品则拆成两条入
                        //验收合格品数大于0
                        if (lotVO.getQualifiedQuantity().compareTo(BigDecimal.ZERO) == 1){
                            DistrInStorageShelfDtlVO shelfDtlVO = new DistrInStorageShelfDtlVO();
                            shelfDtlVO.setLotNumber(lotVO.getLotNumber());
                            shelfDtlVO.setProductDate(lotVO.getProductDate());
                            shelfDtlVO.setValidDate(lotVO.getValidDate());
                            shelfDtlVO.setShelfStatus(ShelfStatus.QUALIFIED.getCode());
                            shelfDtlVO.setShelfStatusDesc("合格品");
                            shelfDtlVO.setQuantity(lotVO.getQualifiedQuantity());
                            //合格的时候将安全库存的默认或为带过去
                            SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(detailVO.getGoodsId(), loginUser.getEnterpriseId());
                            if (safetyStock != null){
                                if (safetyStock.getDefaultShelfId() != 0){
                                    shelfDtlVO.setShelfId(safetyStock.getDefaultShelfId());
                                }
                                if (!"0".equals(safetyStock.getDefaultShelfName())){
                                    shelfDtlVO.setShelfName(safetyStock.getDefaultShelfName());
                                }
                            }
                            shelfDtlVOList.add(shelfDtlVO);
                        }
                        //验收不合格品数大于0
                        if (lotVO.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) == 1){
                            DistrInStorageShelfDtlVO shelfDtlVO = new DistrInStorageShelfDtlVO();
                            shelfDtlVO.setLotNumber(lotVO.getLotNumber());
                            shelfDtlVO.setProductDate(lotVO.getProductDate());
                            shelfDtlVO.setValidDate(lotVO.getValidDate());
                            shelfDtlVO.setShelfStatus(ShelfStatus.UNQULIFIED.getCode());
                            shelfDtlVO.setShelfStatusDesc("不合格品");
                            shelfDtlVO.setQuantity(lotVO.getUnqualifiedQuantity());
                            shelfDtlVOList.add(shelfDtlVO);
                        }
                    }
                }
                detailVO.setDistrInStorageShelfDtlVOList(shelfDtlVOList);
                detailList.add(detailVO);
            }
            vo.setDistrInstorageDtlList(detailList);
        }
        //插入数量 + 品种数量
        vo.setQuantityTotal(quantity);
        vo.setVarietiesQuantity(set.size());
        //插入总单优惠前金额
        vo.setAmountTotal(amountTotal);
        //插入整单实际金额
        vo.setRealAmountTotal(realAmountTotal);
        //插入不含税金额合计
        vo.setNotaxRealAmountTotal(noTaxAmountTotal);
        //税额合计
        vo.setTaxAmountTotal(realAmountTotal.subtract(noTaxAmountTotal));
        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<String> saveStayInstorageForm(DistrInstorageFormVO distrInstorageFormVO, UserVO loginUser) throws Exception{
        List<String> resultList = new ArrayList<>();
        //质量控制判断
        ManageConfig mangeConfigByEPId = manageConfigComponent.getMangeConfigByEPId(loginUser);

        Integer distrType = distrInstorageFormVO.getDistrType();

        /**
         * 验收数据明细列表以下
         */
        Long checkId = distrInstorageFormVO.getCheckId();
        DistrInCheck2DetailVO inCheck2DetailVO = distrInCheckMapper.getDistrInCheckById(loginUser.getEnterpriseId(), checkId);
        List<DistrInCheckDetail2DetailVO> inCheckDetail2DetailList = new ArrayList<DistrInCheckDetail2DetailVO>();
        if(inCheck2DetailVO != null){
            inCheckDetail2DetailList = distrInCheckDetailMapper.getInCheckDetail2Detail(loginUser.getEnterpriseId(), checkId);
            inCheck2DetailVO.setDetailVOList(inCheckDetail2DetailList);
        }
        /**
         * 验收数据明细列表以上
         */
       //构建主单
        DistrIn in = new DistrIn();
        in.setEnterpriseId(loginUser.getEnterpriseId());
        in.setParentId(loginUser.getParentId());
        in.setOrderType(OrderRule.DISTR_IN.getType());
        //生成的移动单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_IN.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode());
        in.setCode(code);
        in.setInDate(new Date());
        //设置基础单据信息
        in.setBaseOrderId(inCheck2DetailVO.getId());
        in.setBaseOrderType(OrderRule.DISTR_IN_CHECK.getType());
        in.setBaseOrderCode(inCheck2DetailVO.getCode());
        in.setBaseOrderDate(inCheck2DetailVO.getCheckDate());
        in.setNoticeId(inCheck2DetailVO.getNoticeId());
        in.setDistrUnitId(distrInstorageFormVO.getDistrUnitId());
        in.setDistrUnitCode(distrInstorageFormVO.getDistrUnitCode());
        in.setDistrUnitName(distrInstorageFormVO.getDistrUnitName());
        in.setDistrType(distrType);
        //质量控制关闭
        if (mangeConfigByEPId.getQualityControl() == 0){
            in.setStorageManId(distrInstorageFormVO.getStorageManId());
            User user = userMapper.selectByPrimaryKey(distrInstorageFormVO.getStorageManId());
            in.setStorageManCode(user.getCode());
            in.setStorageManName(user.getName());
        }else{
            in.setStorageManId(loginUser.getUserId());
            in.setStorageManCode(loginUser.getUserCode());
            in.setStorageManName(loginUser.getUserName());
        }
        in.setQuantityTotal(distrInstorageFormVO.getQuantityTotal());
        in.setVarietiesQuantity(distrInstorageFormVO.getVarietiesQuantity());
        in.setAmountTotal(distrInstorageFormVO.getAmountTotal());
        in.setWholeDiscount(distrInstorageFormVO.getWholeDiscount());
        in.setWholeDiscountAmount(distrInstorageFormVO.getWholeDiscountAmount());
        in.setRealAmountTotal(distrInstorageFormVO.getRealAmountTotal());
        in.setNotaxRealAmountTotal(distrInstorageFormVO.getNotaxRealAmountTotal());
        in.setTaxAmountTotal(distrInstorageFormVO.getTaxAmountTotal());
        //总部配送，设置出库单位当前企业
        in.setOutboundUnitId(distrInstorageFormVO.getDistrUnitId());
        in.setOutboundUnitCode(distrInstorageFormVO.getDistrUnitCode());
        in.setOutboundUnitName(distrInstorageFormVO.getDistrUnitName());

        Integer status = getStatusByOrderTypeAndUser(distrType,loginUser);

        //状态
        in.setStatus(status);
        //备注
        in.setRemark(distrInstorageFormVO.getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(in,loginUser,true);
        distrInMapper.insertSelective(in);
        //构建明细
        List<DistrInstorageDtlVO> distrInstorageDtlList = distrInstorageFormVO.getDistrInstorageDtlList();
        if (distrInstorageDtlList != null){
            for (DistrInstorageDtlVO dtlVO:distrInstorageDtlList) {
                DistrInDetail dtl = new DistrInDetail();
                dtl.setEnterpriseId(loginUser.getEnterpriseId());
                dtl.setParentId(loginUser.getParentId());
                dtl.setInId(in.getId());
                dtl.setOrderType(OrderRule.DISTR_IN_CHECK.getType());
                dtl.setInCode(code);
                dtl.setInDate(new Date());
                dtl.setBaseOrderDtlId(dtlVO.getBaseOrderDtlId());
                dtl.setBaseOrderId(inCheck2DetailVO.getId());
                dtl.setBaseOrderType(OrderRule.DISTR_IN_CHECK.getType());
                dtl.setBaseOrderCode(inCheck2DetailVO.getCode());
                dtl.setBaseOrderDate(inCheck2DetailVO.getCheckDate());
                //商品信息
                dtl.setGoodsId(dtlVO.getGoodsId());
                Goods goods = goodsMapper.selectByPrimaryKey(dtlVO.getGoodsId());
                dtl.setGoodsCode(goods.getCode());
                dtl.setBarcode(goods.getBarcode());
                dtl.setGoodsName(goods.getName());
                dtl.setGoodsGenericName(goods.getGenericName());
                dtl.setDosageId(goods.getDosageId());
                dtl.setDosageName(goods.getDosageName());
                dtl.setUnitId(goods.getUnitId());
                dtl.setUnitName(goods.getUnitName());
                dtl.setGoodsSpecification(goods.getSpecification());
                dtl.setManufacturerId(goods.getManufacturerId());
                dtl.setManufacturer(goods.getManufacturer());
                dtl.setGoodsPlace(goods.getPlace());
                dtl.setApprovalNumber(goods.getApprovalNumber());
                dtl.setQuantity(dtlVO.getQuantity());
                dtl.setUnitPrice(dtlVO.getUnitPrice());
                dtl.setRetailPrice(dtlVO.getRetailPrice());
                dtl.setMemberPrice(dtlVO.getMemberPrice());
                dtl.setLineDiscount(dtlVO.getLineDiscount());
                dtl.setAmount(dtlVO.getAmount());
                dtl.setWholeDiscount(in.getWholeDiscount());
                //行优惠
                BigDecimal lineDiscount = CalculateComponent.getLineRoundOffByLineAmountAndWholeAmountTotal(in.getWholeDiscountAmount(), dtlVO.getAmount(), in.getAmountTotal());
                dtl.setLineDiscountAmount(lineDiscount);
                //getRealAmountByQuantityAndPriceAndLineDiscount
                //行的实际金额
                BigDecimal lineRealAmount = CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(dtlVO.getQuantity(), dtlVO.getUnitPrice(), dtlVO.getLineDiscount(), in.getWholeDiscount(), lineDiscount);
                BigDecimal realPrice = CalculateComponent.getRealPriceByRealAmountAndQuantity(lineRealAmount, dtlVO.getQuantity());
                dtl.setRealPrice(realPrice);
                dtl.setRealAmount(lineRealAmount);
                dtl.setTaxRateId(dtlVO.getTaxRateId());
                dtl.setTaxRate(dtlVO.getTaxRate());

                //明细的不含税实际金额
                BigDecimal notaxLineRealAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(lineRealAmount, dtlVO.getTaxRate());
                dtl.setNotaxRealAmount(notaxLineRealAmount);
                //明细的不含税实际单价
                BigDecimal notaxRealPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxLineRealAmount, dtlVO.getQuantity());
                dtl.setNotaxRealPrice(notaxRealPrice);
                BigDecimal taxAmount = CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(lineRealAmount, notaxLineRealAmount);
                dtl.setTaxAmount(taxAmount);
                
                if(ChainType.Selfoperatedshop.getCode() == loginUser.getChainType() ){// 自营店
                	//已清数量 + 未清数量
                    dtl.setUnclearQuantity(BigDecimal.ZERO);
                    dtl.setClearQuantity(dtlVO.getQuantity());
                    
                } else if (ChainType.Division.getCode() == loginUser.getChainType()){// 加盟店
                	//已清数量 + 未清数量
                    dtl.setUnclearQuantity(dtlVO.getQuantity());
                    dtl.setClearQuantity(BigDecimal.ZERO);
                }
                //行号
                dtl.setLineNum(dtlVO.getLineNum());
                dtl.setStatus(status);
                dtl.setRemark(dtlVO.getRemark());
                UserEnterpriseUtils.setUserCreateOrModify(dtl,loginUser,true);
                distrInDetailMapper.insertSelective(dtl);

                // 更新验收单据的已清、未清数量
                DistrInCheckDetail incheckDetail = new DistrInCheckDetail();
                incheckDetail.setId(dtlVO.getBaseOrderDtlId());
                incheckDetail.setUnclearQuantity(BigDecimal.ZERO);
                incheckDetail.setClearQuantity(dtlVO.getQuantity());
                incheckDetail.setStatus(status);
                distrInCheckDetailMapper.updateByPrimaryKeySelective(incheckDetail);



                //构建货位信息
                List<DistrInStorageShelfDtlVO> distrInStorageShelfDtlVOList = dtlVO.getDistrInStorageShelfDtlVOList();
                if (distrInStorageShelfDtlVOList != null){
                    for (DistrInStorageShelfDtlVO shelfDtlVO:distrInStorageShelfDtlVOList) {
                        DistrInShelf shelf = new DistrInShelf();
                        shelf.setEnterpriseId(loginUser.getEnterpriseId());
                        shelf.setParentId(loginUser.getParentId());
                        shelf.setDtlId(dtl.getId());
                        shelf.setInId(in.getId());
                        shelf.setGoodsId(dtl.getGoodsId());
                        shelf.setGoodsCode(dtl.getGoodsCode());
                        shelf.setGoodsName(dtl.getGoodsName());
                        shelf.setLotNumber(shelfDtlVO.getLotNumber());
                        shelf.setProductDate(shelfDtlVO.getProductDate());
                        shelf.setValidDate(shelfDtlVO.getValidDate());
                        //货位ID====前台给我传
                        shelf.setShelfId(shelfDtlVO.getShelfId());
                        WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(shelfDtlVO.getShelfId());
                        if (warehouseShelf != null){
                            shelf.setShelfName(warehouseShelf.getName());
                        } else {
                            throw new BusinessException("货位" + shelfDtlVO.getShelfId() +"不存在");
                        }
                        shelf.setShelfStatusDesc(shelfDtlVO.getShelfStatusDesc());
                        shelf.setQuantity(shelfDtlVO.getQuantity());
                        shelf.setCanReturnQuantity(shelfDtlVO.getQuantity());
                        shelf.setUnitPrice(dtl.getUnitPrice());
                        shelf.setLineDiscount(dtl.getLineDiscount());
                        //此时的金额需要重算 --因为分合格品和不合格品
                        BigDecimal shelfAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(shelfDtlVO.getQuantity(), dtl.getUnitPrice(), dtl.getLineDiscount());
                        shelf.setAmount(shelfAmount);
                        shelf.setWholeDiscount(dtl.getWholeDiscount());
                        //行优惠(这里因为只分合格和不合格品，而其它状态信息全部相同)所以用细单的行优惠分到每一个货位上
                        BigDecimal shelfLineDiscountAmount = dtl.getLineDiscountAmount().multiply(shelfDtlVO.getQuantity().divide(dtl.getQuantity(),6,RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP);
                        shelf.setLineDiscountAmount(shelfLineDiscountAmount);
                        shelf.setRealPrice(dtl.getRealPrice());
                        //实际金额 = 实际单价 * （合格或者不合格的数量）
                        BigDecimal shelfRealAmount = dtl.getRealPrice().multiply(shelfDtlVO.getQuantity()).setScale(2, RoundingMode.HALF_UP);
                        shelf.setRealAmount(shelfRealAmount);
                        shelf.setTaxRateId(dtl.getTaxRateId());
                        shelf.setTaxRate(dtl.getTaxRate());
                        //不含税实际单价 + 不含税实际金额 + 税额
                        //货位的不含税实际金额
                        BigDecimal shelfNotaxLineRealAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(shelfRealAmount, dtl.getTaxRate());
                        //货位的不含税实际单价
                        BigDecimal shelfNotaxRealPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(shelfNotaxLineRealAmount, shelfDtlVO.getQuantity());
                        shelf.setNotaxRealPrice(shelfNotaxRealPrice);
                        shelf.setNotaxRealAmount(shelfNotaxLineRealAmount);
                        //货位的税额(实际单价 * 验收数量 = 实际金额 - 不含税金额)
                        BigDecimal shelfRealAmout = dtl.getRealPrice().multiply(shelfDtlVO.getQuantity()).setScale(2, RoundingMode.HALF_UP);
                        BigDecimal shelfTaxAmount = CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(shelfRealAmout, shelfNotaxLineRealAmount);
                        shelf.setTaxAmount(shelfTaxAmount);

                        if(ChainType.Selfoperatedshop.getCode() == loginUser.getChainType() ){// 自营店
                        	//已清数量 + 未清数量
                        	shelf.setUnclearQuantity(BigDecimal.ZERO);
                        	shelf.setClearQuantity(shelfDtlVO.getQuantity());
                        	shelf.setUnverificationQuantity(BigDecimal.ZERO);
                        	shelf.setVerificationQuantity(shelfDtlVO.getQuantity());
                        	shelf.setCanReturnQuantity(shelfDtlVO.getQuantity());
                            
                        } else if (ChainType.Division.getCode() == loginUser.getChainType()){// 加盟店
                        	//已清数量 + 未清数量
                        	shelf.setUnclearQuantity(shelfDtlVO.getQuantity());
                        	shelf.setClearQuantity(BigDecimal.ZERO);
                        	shelf.setUnverificationQuantity(shelfDtlVO.getQuantity());
                        	shelf.setVerificationQuantity(BigDecimal.ZERO);
                        	shelf.setCanReturnQuantity(shelfDtlVO.getQuantity());
                        }
                        //前端传的行号
                        shelf.setLineNum(shelfDtlVO.getLineNum());
                        shelf.setStatus(status);
                        UserEnterpriseUtils.setUserCreateOrModify(shelf,loginUser,true);
                        distrInShelfMapper.insertSelective(shelf);

                        // 更新验收单,批号货位数量

                        DistrInCheckLot checkLotInfo = distrInCheckLotMapper.getCheckLotInfo(shelf.getEnterpriseId(), dtl.getBaseOrderId(), shelf.getGoodsId(), shelf.getLotNumber());
                        checkLotInfo.setClearQuantity(shelf.getQuantity());
                        checkLotInfo.setUnclearQuantity(BigDecimal.ZERO);
                        checkLotInfo.setStatus(status);

                        distrInCheckLotMapper.updateByPrimaryKeySelective(checkLotInfo);

                    }
                }
            }
            //更新订单状态
            distrComponent.updateInNoticeStatusForBranch(inCheck2DetailVO.getNoticeId(),status);
            //更新收货单据的状态
            distrInReceiveMapper.updateStatusById(loginUser.getEnterpriseId(),inCheck2DetailVO.getBaseOrderId(),status);
            distrInReceiveDetailMapper.updateDetailStatus(loginUser.getEnterpriseId(),inCheck2DetailVO.getBaseOrderId(),status);
            //更新验收主单的状态
            DistrInCheck updateCheck = new DistrInCheck();
            updateCheck.setId(inCheck2DetailVO.getId());
            updateCheck.setStatus(status);
            distrInCheckMapper.updateByPrimaryKeySelective(updateCheck);
            //更新验收细单的状态
            //distrInCheckDetailMapper.updateStatusByCheckId(loginUser.getEnterpriseId(),inCheck2DetailVO.getId(),DistrInStatus.WAIT_BILL);

            if(distrType == DistributionType.DIRECT_DISTRIBUTION.getCode()){
                resultList = generateByCheck(distrInstorageFormVO, loginUser);
            }
            /**
             * 影响关键表
             */
            OrderModelBuilder builder = new OrderModelBuilder(loginUser);
            DistrIn distrIn = distrInMapper.selectByPrimaryKey(in.getId());
            List<DistrInShelf> distrShelf = distrInShelfMapper.selectByEnterpriseIdAndInId(in.getId());
            List<DistrInDetail> distrInDetails = distrInDetailMapper.selectByEnterpriseIdAndInId(in.getId());
            OrderModel orderModel = builder.buildOrderModel(OrderRule.DISTR_IN,distrIn, distrShelf);
            inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);

            /**
             * 更新商品默认货位
             */
            /**
             * 修改商品的默认货位
             */
            distrShelf.forEach(
                    shelf -> {
                        commonComponent.updateGoodsDefShelf(
                                loginUser.getEnterpriseId()
                                ,loginUser.getParentId()
                                ,loginUser.getChainType()
                                ,shelf.getGoodsId()
                                ,shelf.getShelfId()
                                ,shelf.getShelfName()
                                ,loginUser
                        );
                    }
            );

            /**
             * 构建priceVO
             */
            distrInDetails.forEach(
                    detail -> {
                        LastInPriceVO lastInPriceVO = new LastInPriceVO();
                        /**
                         private Long inTaxRateId;// 入库税率ID
                         private BigDecimal inTaxRate;// 入库税率
                         private BigDecimal inPrice;// 入库单价
                         */
                        lastInPriceVO.setEnterpriseId(loginUser.getEnterpriseId());
                        lastInPriceVO.setParentId(loginUser.getParentId());
                        lastInPriceVO.setChainType(loginUser.getChainType());
                        lastInPriceVO.setGoodsId(detail.getGoodsId());
                        lastInPriceVO.setInTaxRateId(detail.getTaxRateId());
                        lastInPriceVO.setInTaxRate(detail.getTaxRate());
                        lastInPriceVO.setInPrice(detail.getUnitPrice());
                        commonComponent.updateLastPriceInfo(lastInPriceVO);
                    }
            );
            //财务通用接口
            financeComponent.distrInToBalanceAndVoucher(loginUser,distrIn);
        }

        resultList.add("已经成功生成配进入库单:" + code);


        return resultList;
    }

    /** 
    * @Description: 根据配送类型获取状态
     *
     * 门店间调剂 -> 已完成,(只有自营店才有门店间调剂吧)
     * 总部配送  ->  自营店 已完成，加盟店待开票
     * 直调类型  ->  自营店 已完成，加盟店待开票
    * @return:
    * @Author: dongyang.du
    * @Date: 20/01/2018 
    */ 
    private Integer getStatusByOrderTypeAndUser(Integer distrType, UserVO loginUser) {

        if(ChainType.Selfoperatedshop.getCode() == loginUser.getChainType() ){// 自营店
            return DistrInStatus.FINISHED;// 已完成
        } else if (ChainType.Division.getCode() == loginUser.getChainType()){// 加盟店
            return DistrInStatus.WAIT_BILL;// 待开票
        }

        return null;
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

        List<DistrInShelf> distrInShelf = distrInShelfMapper.selectByLotNumAndGoodsId(enterpriseId, goodsId, lotNum);

        List<StockShelfVO> collect = distrInShelf.stream().map(dif -> {
            LotNumber lotNumber = lotNumberMapper.selectByLotNum(dif.getLotNumber());
            StockShelfVO stockShelfVO = StockShelfVO.getStockShelfVO(dif, lotNumber);
            return stockShelfVO;
        }).collect(Collectors.toList());

        //查收所有的货位
        return collect;
    }

    @Override
    public DistrInstorageVO getInStorageInfoByPlan(Long planId, UserVO userVO) {

        DistrInstorageVO distrInstorageVO = new DistrInstorageVO();
        DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(planId);
        distrInstorageVO.setOrderDate(new Date());
        distrInstorageVO.setDistrType(DistributionType.DIRECT_DISTRIBUTION.getCode());
        distrInstorageVO.setDistrTypeName(DistributionType.DIRECT_DISTRIBUTION.getName());
        distrInstorageVO.setStorageManId(userVO.getUserId());
        distrInstorageVO.setStorageManName(userVO.getUserName());
        distrInstorageVO.setRemark(distrReqPlan.getRemark());
        distrInstorageVO.setOutboundUnitId(distrReqPlan.getOutboundUnitId());
        distrInstorageVO.setOutboundUnitCode(distrReqPlan.getOutboundUnitCode());
        distrInstorageVO.setOutboundUnitName(distrReqPlan.getOutboundUnitName());
        distrInstorageVO.setDistrUnitId(distrReqPlan.getDistrUnitId());
        distrInstorageVO.setPlanId(distrReqPlan.getId());
        distrInstorageVO.setQuantityTotal(BigDecimal.ZERO);
        distrInstorageVO.setAmountTotal(BigDecimal.ZERO);
        distrInstorageVO.setWholeDiscount(new BigDecimal(100));
        distrInstorageVO.setWholeDiscountAmount(BigDecimal.ZERO);
        distrInstorageVO.setRealAmountTotal(BigDecimal.ZERO);
        distrInstorageVO.setNotaxRealAmountTotal(BigDecimal.ZERO);
        distrInstorageVO.setTaxAmountTotal(BigDecimal.ZERO);

        List<com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageDtlVO > distrInstorageDtlVOList = new ArrayList<>();
        List<DistrReqPlanDetail> distrReqPlanDetails = distrReqPlanDetailMapper.selectByDistrReqPlanId(planId);
        for(DistrReqPlanDetail planDetail : distrReqPlanDetails){
            com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageDtlVO  distrInstorageDtlVO = new com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageDtlVO ();
            //商品信息
            distrInstorageDtlVO.setGoodsId(planDetail.getGoodsId());
            distrInstorageDtlVO.setGoodsCode(planDetail.getGoodsCode());
            distrInstorageDtlVO.setGoodsGenericName(planDetail.getGoodsGenericName());
            distrInstorageDtlVO.setGoodsPlace(planDetail.getGoodsPlace());
            distrInstorageDtlVO.setGoodsSpecification(planDetail.getGoodsSpecification());
            distrInstorageDtlVO.setUnitId(planDetail.getUnitId());
            distrInstorageDtlVO.setUnitName(planDetail.getUnitName());
            List<SupplierVarieties> supplierVarieties = supplierVarietiesMapper.selectSupplierVarietiesByParam(userVO.getParentId(), planDetail.getGoodsId(), distrReqPlan.getId());
            if(CollectionUtils.isEmpty(supplierVarieties)){
                GoodsBusiness goodsBusiness = businessMapper.getByGoodsId(planDetail.getGoodsId());
                distrInstorageDtlVO.setUnitPrice(BigDecimal.ZERO);
                distrInstorageDtlVO.setTaxRateId(goodsBusiness.getPurchaseTaxRateId());
                distrInstorageDtlVO.setTaxRate(goodsBusiness.getPurchaseTaxRate());
            } else {
                distrInstorageDtlVO.setUnitPrice(supplierVarieties.get(0).getLastPurPrice());
                distrInstorageDtlVO.setTaxRateId(supplierVarieties.get(0).getLastPurTaxRateId());
                distrInstorageDtlVO.setTaxRate(supplierVarieties.get(0).getLastPurTaxRate());
            }

            distrInstorageDtlVO.setManufacturer(planDetail.getManufacturer());
            distrInstorageDtlVO.setManufacturerId(planDetail.getManufacturerId());
            distrInstorageDtlVO.setQuantity(planDetail.getQuantity());
            distrInstorageDtlVO.setReceiveQuantity(planDetail.getQuantity());
            distrInstorageDtlVO.setQualifiedQuantity(planDetail.getQuantity());
            distrInstorageDtlVO.setRemark(planDetail.getRemark());
            distrInstorageDtlVO.setLineNum(planDetail.getLineNum());
            distrInstorageDtlVO.setLineDiscount(new BigDecimal(100));
            distrInstorageDtlVO.setAmount(BigDecimal.ZERO);
            distrInstorageDtlVO.setNotaxRealAmount(BigDecimal.ZERO);
            distrInstorageDtlVO.setNotaxRealPrice(BigDecimal.ZERO);
            distrInstorageDtlVO.setTaxAmount(BigDecimal.ZERO);
            List<DistrInstorageGoodsLotNumberVO> goodsLotNumberVOList = new ArrayList<>();
            //配进入库，查询默认货位
            SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(planDetail.getGoodsId(), userVO.getEnterpriseId());
            if(safetyStock == null) throw new BusinessException("商品编码[" + planDetail.getGoodsCode() + "]的默认货位不存在");

            if (safetyStock != null){
                if (safetyStock.getDefaultShelfId() != 0){
                    distrInstorageDtlVO.setDefaultShelfId(safetyStock.getDefaultShelfId());
                }
                if (!"0".equals(safetyStock.getDefaultShelfName())){
                    distrInstorageDtlVO.setDefaultShelfName(safetyStock.getDefaultShelfName());
                }
            }

            distrInstorageDtlVO.setGoodsLotNumberVOList(goodsLotNumberVOList);
            distrInstorageDtlVOList.add(distrInstorageDtlVO);
        }
        distrInstorageVO.setAddInstorageDtlVOList(distrInstorageDtlVOList);


        return distrInstorageVO;
    }

    @Override
    public DistrInstorageVO getInStorageInfoByNoticeId(Long noticeId, UserVO userVO) {

        DistrInstorageVO distrInstorageVO = new DistrInstorageVO();
        DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(noticeId);
        distrInstorageVO.setOrderDate(new Date());
        distrInstorageVO.setDistrType(distrInNotice.getDistrType());
        distrInstorageVO.setDistrTypeName(DistributionType.getName(distrInNotice.getDistrType()));
        distrInstorageVO.setNoticeStorageManId(distrInNotice.getStorageManId());
        distrInstorageVO.setNoticeStorageManName(distrInNotice.getStorageManName());
        distrInstorageVO.setRemark(distrInNotice.getRemark());
        distrInstorageVO.setOutboundUnitId(distrInNotice.getOutboundUnitId());
        distrInstorageVO.setOutboundUnitCode(distrInNotice.getOutboundUnitCode());
        distrInstorageVO.setOutboundUnitName(distrInNotice.getOutboundUnitName());
        if(DistributionType.SWAP_BETWEEN_STORES.getCode() == distrInNotice.getDistrType()){
            distrInstorageVO.setDistrUnitId(userVO.getEnterpriseId());
            distrInstorageVO.setDistrUnitCode(userVO.getEnterpriseCode());
            distrInstorageVO.setDistrUnitName(userVO.getEnterpriseName());
        }else {
            distrInstorageVO.setDistrUnitId(distrInNotice.getDistrUnitId());
            distrInstorageVO.setDistrUnitCode(distrInNotice.getDistrUnitCode());
            distrInstorageVO.setDistrUnitName(distrInNotice.getDistrUnitName());
        }

        distrInstorageVO.setNoticeId(distrInNotice.getId());
        distrInstorageVO.setQuantityTotal(distrInNotice.getQuantityTotal());
        distrInstorageVO.setAmountTotal(distrInNotice.getAmountTotal());
        distrInstorageVO.setWholeDiscount(distrInNotice.getWholeDiscount());
        distrInstorageVO.setWholeDiscountAmount(distrInNotice.getWholeDiscountAmount());
        distrInstorageVO.setRealAmountTotal(distrInNotice.getRealAmountTotal());
        distrInstorageVO.setNotaxRealAmountTotal(distrInNotice.getNotaxRealAmountTotal());
        distrInstorageVO.setTaxAmountTotal(distrInNotice.getTaxAmountTotal());

        List<com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageDtlVO > distrInstorageDtlVOList = new ArrayList<>();
        List<DistrInNoticeDetail> distrInNoticeDetailList = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseId(noticeId, userVO.getEnterpriseId());
        for(DistrInNoticeDetail noticeDetail : distrInNoticeDetailList){
            com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageDtlVO  distrInstorageDtlVO = new com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage.DistrInstorageDtlVO ();
            //商品信息
            distrInstorageDtlVO.setGoodsId(noticeDetail.getGoodsId());
            distrInstorageDtlVO.setGoodsCode(noticeDetail.getGoodsCode());
            distrInstorageDtlVO.setGoodsGenericName(noticeDetail.getGoodsGenericName());
            distrInstorageDtlVO.setGoodsPlace(noticeDetail.getGoodsPlace());
            distrInstorageDtlVO.setGoodsSpecification(noticeDetail.getGoodsSpecification());
            distrInstorageDtlVO.setUnitId(noticeDetail.getUnitId());
            distrInstorageDtlVO.setUnitName(noticeDetail.getUnitName());
            distrInstorageDtlVO.setUnitPrice(noticeDetail.getUnitPrice());
            distrInstorageDtlVO.setManufacturer(noticeDetail.getManufacturer());
            distrInstorageDtlVO.setManufacturerId(noticeDetail.getManufacturerId());
            distrInstorageDtlVO.setTaxRateId(noticeDetail.getTaxRateId());
            distrInstorageDtlVO.setTaxRate(noticeDetail.getTaxRate());
            distrInstorageDtlVO.setQuantity(noticeDetail.getQuantity());
            distrInstorageDtlVO.setReceiveQuantity(noticeDetail.getQuantity());
            distrInstorageDtlVO.setRemark(noticeDetail.getRemark());
            distrInstorageDtlVO.setLineNum(noticeDetail.getLineNum());
            distrInstorageDtlVO.setLineDiscount(noticeDetail.getLineDiscount());
            distrInstorageDtlVO.setAmount(noticeDetail.getAmount());
            distrInstorageDtlVO.setNotaxRealAmount(noticeDetail.getNotaxRealAmount());
            distrInstorageDtlVO.setNotaxRealPrice(noticeDetail.getNotaxRealPrice());
            distrInstorageDtlVO.setTaxAmount(noticeDetail.getTaxAmount());
            List<DistrInstorageGoodsLotNumberVO> goodsLotNumberVOList = new ArrayList<>();


            //配进入库，查询默认货位
            SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(noticeDetail.getGoodsId(), userVO.getEnterpriseId());
            if(safetyStock == null) throw new BusinessException("商品编码[" + noticeDetail.getGoodsCode() + "]的默认货位不存在");


            //直调配送
            if(distrInNotice.getDistrType() == DistributionType.DIRECT_DISTRIBUTION.getCode()){
                DistrInstorageGoodsLotNumberVO lotNumberVO = new DistrInstorageGoodsLotNumberVO();
                lotNumberVO.setQualifiedQuantity(noticeDetail.getQuantity());
                lotNumberVO.setSamplingQuantity(BigDecimal.ONE);
                lotNumberVO.setReceiveQuantity(noticeDetail.getQuantity());
                lotNumberVO.setUnqualifiedQuantity(BigDecimal.ZERO);
                if (safetyStock != null){
                    if (safetyStock.getDefaultShelfId() != 0){
                        lotNumberVO.setDefaultShelfId(safetyStock.getDefaultShelfId());
                    }
                    if (!"0".equals(safetyStock.getDefaultShelfName())){
                        lotNumberVO.setDefaultShelfName(safetyStock.getDefaultShelfName());
                    }
                }
                goodsLotNumberVOList.add(lotNumberVO);
                distrInstorageDtlVO.setGoodsLotNumberVOList(goodsLotNumberVOList);
            } else if(distrInNotice.getDistrType() == DistributionType.DISTRIBUTION_HEAD.getCode() || distrInNotice.getDistrType() == DistributionType.SWAP_BETWEEN_STORES.getCode()){

                Long baseOrderDtlId = noticeDetail.getBaseOrderDtlId();//配货出库明细id
                List<DistrInstorageGoodsLotNumberVO> lotNumberVOList = distrOutShelfMapper.getGoodsLotInfoByDtlId(userVO.getParentId(), baseOrderDtlId, noticeDetail.getGoodsId());
                for(DistrInstorageGoodsLotNumberVO lotNumberVO : lotNumberVOList){
                    lotNumberVO.setQualifiedQuantity(noticeDetail.getQuantity());
                    lotNumberVO.setReceiveQuantity(noticeDetail.getQuantity());
                    lotNumberVO.setSamplingQuantity(BigDecimal.ONE);
                    lotNumberVO.setUnqualifiedQuantity(BigDecimal.ZERO);
                    if (safetyStock != null){
                        if (safetyStock.getDefaultShelfId() != 0){
                            lotNumberVO.setDefaultShelfId(safetyStock.getDefaultShelfId());
                        }
                        if (!"0".equals(safetyStock.getDefaultShelfName())){
                            lotNumberVO.setDefaultShelfName(safetyStock.getDefaultShelfName());
                        }
                    }
                }
                distrInstorageDtlVO.setGoodsLotNumberVOList(lotNumberVOList);

            }

            distrInstorageDtlVOList.add(distrInstorageDtlVO);


        }
        distrInstorageVO.setAddInstorageDtlVOList(distrInstorageDtlVOList);
        return distrInstorageVO;
    }

    @Override
    public Page getGoodsByParam(Page page, UserVO userVO, Long supplierId, String param) {

        CommonParamSupplierAndGoods commonParam = new CommonParamSupplierAndGoods();
        ParamUtils.packParam(userVO,commonParam);
        List<GoodsInNoticeVO> goodsInNoticeVOS = distrInNoticeMapper.getGoodsByParamWithPage(commonParam,supplierId,param,page);
        Integer totalRecord = distrInNoticeMapper.getGoodsByParamWithPageCount(commonParam,supplierId,param);
        for(GoodsInNoticeVO goodsInNoticeVO : goodsInNoticeVOS){
            List<SupplierVarieties> supplierVarieties = supplierVarietiesMapper.selectSupplierVarietiesByParam(userVO.getEnterpriseId(), goodsInNoticeVO.getId(), supplierId);
            if(CollectionUtils.isEmpty(supplierVarieties)){
                if(goodsInNoticeVO.getTaxRateId() == null){
                    goodsInNoticeVO.setTaxRateId(goodsInNoticeVO.getPurchaseTaxRateId());
                }
                if(goodsInNoticeVO.getTaxRate() == null){
                    goodsInNoticeVO.setTaxRate(goodsInNoticeVO.getPurchaseTaxRate());
                }
                goodsInNoticeVO.setUnitPrice(BigDecimal.ZERO);
            } else {
                goodsInNoticeVO.setTaxRateId(supplierVarieties.get(0).getLastPurTaxRateId());
                goodsInNoticeVO.setTaxRate(supplierVarieties.get(0).getLastPurTaxRate());
                goodsInNoticeVO.setUnitPrice(supplierVarieties.get(0).getLastPurPrice());
            }
            // 查询商品的默认货位

            //将安全库存的默认货位带过去，配进入库新增
            SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(goodsInNoticeVO.getId(), userVO.getEnterpriseId());
            if (safetyStock != null){
                if (safetyStock.getDefaultShelfId() != 0){
                    goodsInNoticeVO.setDefaultShelfId(safetyStock.getDefaultShelfId());
                }
                if (!"0".equals(safetyStock.getDefaultShelfName())){
                    goodsInNoticeVO.setDefaultShelfName(safetyStock.getDefaultShelfName());
                }
            }

        }
        page.setResult(goodsInNoticeVOS);
        page.setTotalRecord(totalRecord);
        return page;
    }


    /**
     * 查询货位
     * @param enterpriseId
     * @param goodsId
     * @param lotNum
     * @return
     */
    @Override
    public List<StockLockRecordVO> getShelfByLotNum(Long enterpriseId, Long goodsId, String lotNum, UserVO userVO, Integer distrType) {

        Map<String,Object> map=new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("goodsId", goodsId);
        map.put("lotNum", lotNum);
       /* //若是总部配送
        if(DistributionType.DISTRIBUTION_HEAD.getCode() == distrType) map.put("enterpriseId", userVO.getParentId());
        //enterpriseId
        if(DistributionType.DIRECT_DISTRIBUTION.getCode() == distrType || DistributionType.SWAP_BETWEEN_STORES.getCode() == distrType) {
            Integer distributionManage = distrReqPlanMapper.getEnterpriseDistrType(userVO.getEnterpriseId());
            if(distributionManage == null || distributionManage == 0)  map.put("enterpriseId", userVO.getParentId());
        }*/
        List<StockLockRecordVO> stockLockRecordVOS = stockMapper.selectByEIdAndGoodsId(map);

        //查收所有的货位
        return stockLockRecordVOS;
    }

    /**
     * 由于根据配进验收保存入库,直调配送需要 需要逆向生成采购订单、采购收货、采购验收、采购入库、配货单、配货出库,
     * 该方法把DistrInstorageFormVO转换
     * @param distrInstorageFormVO
     */
    private AddInstorageVO transformDistrInstorageFormVO2AddInstorageVO(DistrInstorageFormVO distrInstorageFormVO,UserVO userVO){
        Long checkId = distrInstorageFormVO.getCheckId();
        AddInstorageVO addInstorageVO = new AddInstorageVO();
        //配送类型
        addInstorageVO.setDistrType(DistributionType.DIRECT_DISTRIBUTION.getCode());
        //入库日期
        addInstorageVO.setInStorageDate(distrInstorageFormVO.getInDate());
        //入库人员
        addInstorageVO.setStorageManId(distrInstorageFormVO.getStorageManId());
        addInstorageVO.setReceiveDate(new Date());
        addInstorageVO.setCheckDate(new Date());
        //备注
        addInstorageVO.setRemark(distrInstorageFormVO.getRemark());
        //出库单位
        addInstorageVO.setOutboundUnitId(distrInstorageFormVO.getOutboundUnitId());
        //出库单位编码
        addInstorageVO.setOutboundUnitCode(distrInstorageFormVO.getOutboundUnitCode());
        //出库单位名称
        addInstorageVO.setOutboundUnitName(distrInstorageFormVO.getOutboundUnitName());

        //主单金额
        // 金额合计（整单优惠前的金额合计）
        addInstorageVO.setAmountTotal(distrInstorageFormVO.getAmountTotal());
        addInstorageVO.setQuantityTotal(distrInstorageFormVO.getQuantityTotal());
        //整单折扣
        addInstorageVO.setWholeDiscount(distrInstorageFormVO.getWholeDiscount());
        addInstorageVO.setWholeDiscountAmount(distrInstorageFormVO.getWholeDiscountAmount());


        //组装明细
        List<AddInstorageDtlVO> addInstorageDtlVOList = new ArrayList<>();
        List<DistrInstorageDtlVO> distrInstorageDtlList = distrInstorageFormVO.getDistrInstorageDtlList();
        for(DistrInstorageDtlVO distrInstorageDtlVO : distrInstorageDtlList){
            AddInstorageDtlVO addInstorageDtlVO = new AddInstorageDtlVO();
            //商品id
            addInstorageDtlVO.setGoodsId(distrInstorageDtlVO.getGoodsId());
            //数量
            addInstorageDtlVO.setQuantity(distrInstorageDtlVO.getQuantity());
            addInstorageDtlVO.setReceiveQuantity(distrInstorageDtlVO.getQuantity());

            //单价
            addInstorageDtlVO.setUnitPrice(distrInstorageDtlVO.getUnitPrice());
            //行折扣
            addInstorageDtlVO.setLineDiscount(distrInstorageDtlVO.getLineDiscount());
            //税率ID
            addInstorageDtlVO.setTaxRateId(distrInstorageDtlVO.getTaxRateId());

            //行号
            addInstorageDtlVO.setLineNum(distrInstorageDtlVO.getLineNum());
            List<DistrInStorageShelfDtlVO> distrInStorageShelfDtlVOList = distrInstorageDtlVO.getDistrInStorageShelfDtlVOList();

            List<AddInstorageGoodsLotNumberVO> goodsLotNumberVOList = new ArrayList<>();

            BigDecimal qualifiedQuantity = BigDecimal.ZERO;
            BigDecimal unqualifiedQuantity = BigDecimal.ZERO;
            //批号货位维度
            //相同批号的合格数量不合格数量多条记录合并成相同批号的合格数量
            Set<String> setGoodsLotNum = new HashSet<>();

            for(DistrInStorageShelfDtlVO shelfDtlVO : distrInStorageShelfDtlVOList){
                String goodsLotNum = distrInstorageDtlVO.getGoodsId() + "" + shelfDtlVO.getLotNumber();
                if(!setGoodsLotNum.contains(goodsLotNum)){

                    setGoodsLotNum.add(goodsLotNum);

                    DistrInCheckLot checkLotInfo = distrInCheckLotMapper.getCheckLotInfo(userVO.getEnterpriseId(), checkId, distrInstorageDtlVO.getGoodsId(), shelfDtlVO.getLotNumber());
//                Long shelfId = shelfDtlVO.getShelfId();
//                Map<String, Object> shelfInfo = warehouseShelfMapper.getShelfInfoById(shelfId);
//                if(shelfInfo == null) throw new BusinessException("货位不存在");
//                Integer jobArea = (Integer) shelfInfo.get("jobArea");
                    AddInstorageGoodsLotNumberVO lotNumberVO = new AddInstorageGoodsLotNumberVO();
//                if(jobArea == ShelfStatus.QUALIFIED.getCode()){
                    //合格
                    lotNumberVO.setQualifiedQuantity(checkLotInfo.getReceiveQuantity());

                    lotNumberVO.setUnqualifiedQuantity(BigDecimal.ZERO);
//                } else if(jobArea == ShelfStatus.UNQULIFIED.getCode()){
//                    //不合格
//                    lotNumberVO.setUnqualifiedQuantity(shelfDtlVO.getQuantity());
//                    lotNumberVO.setQualifiedQuantity(BigDecimal.ZERO);
//                }
                    lotNumberVO.setReceiveQuantity(checkLotInfo.getReceiveQuantity());
                    lotNumberVO.setLotNumber(shelfDtlVO.getLotNumber());
                    lotNumberVO.setProductDate(shelfDtlVO.getProductDate());
                    lotNumberVO.setValidDate(shelfDtlVO.getProductDate());
                    lotNumberVO.setSamplingQuantity(BigDecimal.ZERO);
                    lotNumberVO.setLineNum(shelfDtlVO.getLineNum());

                    lotNumberVO.setCheckProjectIds(checkLotInfo.getCheckProjectIds());

                    List<ConclusionVO> conclusionVO = purchaseCheckMapper.selectConclusion(userVO.getParentId(), 6L, EnableStatus.ENABLE.getStatus(), 0);
                    String conclusion = "";
                    for(ConclusionVO con : conclusionVO){
                        conclusion += con.getId() + ",";
                    }
                    lotNumberVO.setConclusionIds(conclusion);
                    goodsLotNumberVOList.add(lotNumberVO);
                    qualifiedQuantity = qualifiedQuantity.add(lotNumberVO.getQualifiedQuantity());
                    unqualifiedQuantity = unqualifiedQuantity.add(lotNumberVO.getUnqualifiedQuantity());

                }


            }
            addInstorageDtlVO.setQualifiedQuantity(qualifiedQuantity);
            addInstorageDtlVO.setUnqualifiedQuantity(unqualifiedQuantity);
            addInstorageDtlVO.setGoodsLotNumberVOList(goodsLotNumberVOList);
            addInstorageDtlVOList.add(addInstorageDtlVO);
        }
        addInstorageVO.setAddInstorageDtlVOList(addInstorageDtlVOList);
        return addInstorageVO;
    }

    /**
     * 初始化公用VO
     * @param addInstorageVO
     */
    public void initAddInstorageVO(AddInstorageVO addInstorageVO){
        setCalcul(addInstorageVO);
    }

    /**
     * 保存入库单
     * @param addInstorageVO
     * @param userVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> saveInstorage(AddInstorageVO addInstorageVO, UserVO userVO) throws Exception {
        List<String> resultList = new ArrayList<>();
        addInstorageVO.setType(1);
        Long noticeId = addInstorageVO.getNoticeId();//配进订单id
        Long planId = addInstorageVO.getPlanId();//要货计划id
        Integer distrType = addInstorageVO.getDistrType();
        if(noticeId != null && planId == null){

            if(DistributionType.DISTRIBUTION_HEAD.getCode() == distrType){
                //配进订单-总部配送
                resultList  =   generateByDistrNotice(addInstorageVO,userVO);
            } else if (DistributionType.SWAP_BETWEEN_STORES.getCode() == distrType){
                //配进订单-门店间调剂
                resultList =  generateByDistrNotice(addInstorageVO,userVO);

            } else if(DistributionType.DIRECT_DISTRIBUTION.getCode() == distrType){
                //配进订单-直调配送 （采购订单、采购收货、采购验收、采购入库、配货单、配货出库、配进收货、配进验收）
                resultList =  generateDirectAdd(addInstorageVO,userVO,3);
            }

        } else if (planId != null && noticeId == null ){
            EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
            if(bus.getDistributionManage() == DistributionManage.DISTRIBUTIONMANAGE_A.getStatus()){
                throw new BusinessException("该企业不允许调用操作");
            }
            DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(planId);
            if(distrReqPlan == null) throw new BusinessException("要货计划不存在");
            if(DistributionType.DIRECT_DISTRIBUTION.getCode() == distrType){
                //要货计划直调配送
                resultList =  generateDirectAdd(addInstorageVO,userVO,0);
            }

        } else if(planId == null  && noticeId == null ){
            EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
            if(bus.getDistributionManage() == DistributionManage.DISTRIBUTIONMANAGE_A.getStatus()){
                throw new BusinessException("该企业不允许调用操作");
            }
            //参数校验
            //新增-直调配送
            resultList = generateDirectAdd(addInstorageVO,userVO,1);
        } else {
            throw new BusinessException("配送类型不存在");
        }




        return  resultList;
    }

    /**
     * 新增直调配送
     * 1.采购订单
     * 2.采购收货
     * 3.采购验收
     * 4.采购入库
     * 5.配货单
     * 6.配货出库
     * 7.要货计划
     * 8.配进订单
     * 9.配进收货
     * 10.配进验收
     */
    private List<String> generateDirectAdd(AddInstorageVO addInstorageVO, UserVO userVO, int type) throws Exception {

        Long noticeStorageManId = addInstorageVO.getNoticeStorageManId();
        if(noticeStorageManId == null) throw new BusinessException("配进人员不能为空");

        Long storageManId = addInstorageVO.getStorageManId();
        if(storageManId == null) throw new BusinessException("入库人员不能为空");

        Long receiverId = addInstorageVO.getReceiverId();
        if(receiverId == null) throw new BusinessException("收货人员不能为空");

        Long checkerId = addInstorageVO.getCheckerId();
        if(checkerId == null) throw new BusinessException("验收人员不能为空");


        List<String> resultList = new ArrayList<>();
        //当前企业配置信息
        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(userVO);
        Integer chainType = userVO.getChainType();
        /**
         * 1.采购订单
         * 2.采购收货
         * 3.采购验收
         * 4.采购入库
         */
        //设置配送信息
        AddInstorageOtherVO addInstorageOtherVO = new AddInstorageOtherVO();
        packInstorageOther(userVO, chainType, addInstorageOtherVO);
        addInstorageOtherVO.setCarriageMode(addInstorageVO.getDistrType());
        addInstorageVO.setAddInstorageOtherVO(addInstorageOtherVO);
        Map<String,Object> orderMap = purchaseAddInStorageService.addOrder(userVO, addInstorageVO);
        Map<String,Object> receiveMap = purchaseAddInStorageService.addReceive(userVO, addInstorageVO, orderMap);
        Map<String,Object> checkMap = purchaseAddInStorageService.addCheck(userVO, addInstorageVO, receiveMap);
        Map<String, Object> storageMap = purchaseAddInStorageService.addInstorage(userVO, addInstorageVO, checkMap, orderMap);
        /**
         * 5.配货单
         * 6.配货出库
         * 7.配进订单
         * 8.配进收货
         * 9.配进验收
         */
        //总部的企业配置信息
        ManageConfig mangeConfigParent = manageConfigMapper.getMangeConfigByEPId(userVO.getParentId());
        initAddInstorageVO(addInstorageVO);
        Map<String, Object> distrSendMap = addDistrSend(userVO, addInstorageVO, mangeConfigParent);
        Map<String, Object> distrOutMap = addDistrOut(userVO, addInstorageVO, mangeConfigParent, (Map<String,SafetyStock>) storageMap.get("shelfVOMap"),distrSendMap);
        if(type == 1){//新增配进入库
            addDistrReqPlan(userVO,addInstorageVO,manageConfig);
        }
        Map<String, Object> distrNoticeMap = new HashMap<>();
        if(type == 3){
            //调用配进订单
//            result.put("distrOut",distrOut);
//            result.put("distrOutDetail",distrOutDetailList);
//            result.put("distrOutShelf",outShelfList);
            DistrOut distrOut = (DistrOut) distrOutMap.get("distrOut");
            List<DistrOutDetail> distrOutDetailList = (List<DistrOutDetail>) distrOutMap.get("distrOutDetail");

            DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(addInstorageVO.getNoticeId());
            distrInNotice.setBaseOrderType(OrderRule.DISTR_OUT.getType());
            distrInNotice.setBaseOrderDate(distrOut.getOutDate());
            distrInNotice.setBaseOrderCode(distrOut.getCode());
            distrInNotice.setBaseOrderId(distrOut.getId());

            distrInNoticeMapper.updateByPrimaryKeySelective(distrInNotice);

            List<DistrInNoticeDetail> distrInNoticeDetailList = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseId(addInstorageVO.getNoticeId(), userVO.getEnterpriseId());
            for(DistrInNoticeDetail noticeDetail : distrInNoticeDetailList){
                Optional<DistrOutDetail> distrFirst = distrOutDetailList.stream().filter(item -> noticeDetail.getGoodsId().equals(item.getGoodsId())).findFirst();
                if(distrFirst.isPresent()) {
                    noticeDetail.setBaseOrderCode(distrOut.getCode());
                    noticeDetail.setBaseOrderId(distrOut.getId());
                    noticeDetail.setBaseOrderDate(distrOut.getOutDate());
                    noticeDetail.setBaseOrderType(distrOut.getOrderType());
                    noticeDetail.setBaseOrderDtlId(distrFirst.get().getId());
                    distrInNoticeDetailMapper.updateByPrimaryKeySelective(noticeDetail);
                }
            }
            distrNoticeMap.put("distrInNotice",distrInNotice);
            distrNoticeMap.put("distrInNoticeDetail",distrInNoticeDetailList);
        } else {
            //调用要货计划 直接新增
            distrNoticeMap = commonComponent.addDistrNotice(userVO, addInstorageVO, distrOutMap);
        }


        Map<String, Object> distrInReceiveMap = addDistrInReceive(userVO, addInstorageVO, distrNoticeMap, manageConfig);

        Map<String, Object> distrInCheckMap = addDistrInCheck(userVO, addInstorageVO, distrInReceiveMap, distrNoticeMap,manageConfig);

        Map<String, Object> distrInStorageMap = addDistrInStorage(userVO, addInstorageVO, distrInCheckMap, manageConfig);

        //返回单据LIST
        resultList.add("已经成功生成总部采购订单:" + ((PurchaseOrder)orderMap.get("purchaseOrder")).getCode());
        resultList.add("已经成功生成总部采购收货:" + ((PurchaseReceive)receiveMap.get("purchaseReceive")).getCode());
        resultList.add("已经成功生成总部采购验收:" + ((PurchaseCheck)checkMap.get("purchaseCheck")).getCode());
        resultList.add("已经成功生成总部采购入库:" + ((PurchaseInStorage)storageMap.get("storage")).getCode());
        resultList.add("已经成功生成总部配货单:" + ((DistrSend)distrSendMap.get("distrSend")).getCode());
        resultList.add("已经成功生成总部配货出库:" + ((DistrOut)distrOutMap.get("distrOut")).getCode());

        resultList.add("已经成功生成门店配进订单:" + ((DistrInNotice)distrNoticeMap.get("distrInNotice")).getCode());
        resultList.add("已经成功生成门店配进收货:" + ((DistrInReceive)distrInReceiveMap.get("distrInReceive")).getCode());
        resultList.add("已经成功生成门店配进验收:" + ((DistrInCheck)distrInCheckMap.get("distrInCheck")).getCode());
        resultList.add("已经成功生成门店配进入库:" + ((DistrIn)distrInStorageMap.get("distrInStorage")).getCode());

        Integer status = DistrInStatus.FINISHED;
        if(ChainType.Division.getCode() == userVO.getChainType()){
            status = DistrInStatus.WAIT_BILL;
        }

        if(type == 0){//调用要货计划，需要更新要货计划的状态
            //更新要货计划状态
            DistrReqPlan distrReqPlan = (DistrReqPlan) EntityUtils.reflectUpdateSetDefaultValue(DistrReqPlan.class,userVO);
            distrReqPlan.setId(addInstorageVO.getPlanId());
            distrReqPlan.setStatus(status);
            distrReqPlanMapper.updateStatusById(distrReqPlan);

            DistrReqPlanDetail distrReqPlanDetail = (DistrReqPlanDetail) EntityUtils.reflectUpdateSetDefaultValue(DistrReqPlanDetail.class,userVO);
            distrReqPlanDetail.setPlanId(addInstorageVO.getPlanId());
            distrReqPlanDetail.setStatus(status);
            distrReqPlanDetailMapper.updateByPlanIdSelective(distrReqPlanDetail);
        }
        if(type == 3){
            //调用直调类型更新配进订单的状态
            Long noticeId = addInstorageVO.getNoticeId();
            distrInNoticeMapper.updateStatus(noticeId,status);
            distrInNoticeDetailMapper.updateStatusByOrderId(noticeId,status);
        }
        setCommonData(userVO, distrInStorageMap);


        return resultList;
    }

    private void setCommonData(UserVO userVO, Map<String, Object> distrInStorageMap) throws Exception {
        /**
         * 影响关键表
         */
        DistrIn distrInStorage = (DistrIn) distrInStorageMap.get("distrInStorage");
        OrderModelBuilder builder = new OrderModelBuilder(userVO);
        DistrIn distrIn = distrInMapper.selectByPrimaryKey(distrInStorage.getId());
        List<DistrInShelf> distrShelf = distrInShelfMapper.selectByEnterpriseIdAndInId(distrIn.getId());
        List<DistrInDetail> distrInDetails = distrInDetailMapper.selectByEnterpriseIdAndInId(distrIn.getId());
        OrderModel orderModel = builder.buildOrderModel(OrderRule.DISTR_IN,distrIn, distrShelf);
        inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);

        /**
         * 更新商品默认货位
         */
        /**
         * 修改商品的默认货位
         */
        distrShelf.forEach(
                shelf -> {
                    commonComponent.updateGoodsDefShelf(
                            userVO.getEnterpriseId()
                            ,userVO.getParentId()
                            ,userVO.getChainType()
                            ,shelf.getGoodsId()
                            ,shelf.getShelfId()
                            ,shelf.getShelfName()
                            ,userVO
                    );

                }
        );

        /**
         * 构建priceVO
         */
        distrInDetails.forEach(
                detail -> {
                    LastInPriceVO lastInPriceVO = new LastInPriceVO();
                    /**
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
                    commonComponent.updateLastPriceInfo(lastInPriceVO);
                }
        );

        //财务通用接口
        financeComponent.distrInToBalanceAndVoucher(userVO,distrIn);
    }

    /**
     * 配送类型是总部配送的， 逆向生成：配进收货、配进验收
      配送类型是门店间调剂的， 逆向生成：配进收货、配进验收
     * 8.配进收货
     * 9.配进验收
     */
    private List<String> generateByDistrNotice(AddInstorageVO addInstorageVO, UserVO userVO) throws Exception {
        List<String> resultList = new ArrayList<>();
        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(userVO);

        /**
         * 8.配进收货
         * 9.配进验收
         */
        Long noticeId = addInstorageVO.getNoticeId();
        DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(noticeId);
        if(distrInNotice == null) throw new BusinessException("配进订单不存在");
        List<DistrInNoticeDetail> distrInNoticeDetailList = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseId(noticeId, userVO.getEnterpriseId());
        if(CollectionUtils.isEmpty(distrInNoticeDetailList)) throw new BusinessException("配进订单明细不存在");
        Map<String,Object> distrNoticeMap = new HashMap<>();
        distrNoticeMap.put("distrInNotice",distrInNotice);
        distrNoticeMap.put("distrInNoticeDetail",distrInNoticeDetailList);

        initAddInstorageVO(addInstorageVO);
        Map<String, Object> distrInReceiveMap = addDistrInReceive(userVO, addInstorageVO, distrNoticeMap, manageConfig);

        Map<String, Object> distrInCheckMap = addDistrInCheck(userVO, addInstorageVO, distrInReceiveMap,distrNoticeMap, manageConfig);

        Map<String, Object> distrInStorage = addDistrInStorage(userVO, addInstorageVO, distrInCheckMap, manageConfig);

        //更新配进订单状态
        resultList.add("已经成功生成门店配进收货:" + ((DistrInReceive)distrInReceiveMap.get("distrInReceive")).getCode());
        resultList.add("已经成功生成门店配进验收:" + ((DistrInCheck)distrInCheckMap.get("distrInCheck")).getCode());
        resultList.add("已经成功生成门店配进入库:" + ((DistrIn)distrInStorage.get("distrInStorage")).getCode());

        Integer status = DistrInStatus.FINISHED;
        if(ChainType.Division.getCode() == userVO.getChainType()){
            status = DistrInStatus.WAIT_BILL;
        }
        distrInNoticeMapper.updateStatus(noticeId,status);
        distrInNoticeDetailMapper.updateStatusByOrderId(noticeId,status);

        setCommonData(userVO, distrInStorage);



        return resultList;
    }

    /**
     * 门店基于配进验收，入库
     * 1.采购订单
     * 2.采购收货
     * 3.采购验收
     * 4.采购入库
     * 5.配货单
     * 6.配货出库
     */
    private List<String> generateByCheck(DistrInstorageFormVO distrInstorageFormVO, UserVO userVO) throws Exception {
        Integer chainType = userVO.getChainType();
        List<String> resultList = new ArrayList<>();
        AddInstorageVO addInstorageVO = transformDistrInstorageFormVO2AddInstorageVO(distrInstorageFormVO,userVO);
        addInstorageVO.setType(1);
        //设置配送信息
        AddInstorageOtherVO addInstorageOtherVO = new AddInstorageOtherVO();
        packInstorageOther(userVO, chainType, addInstorageOtherVO);
        addInstorageOtherVO.setCarriageMode(addInstorageVO.getDistrType());
        addInstorageVO.setAddInstorageOtherVO(addInstorageOtherVO);

        ManageConfig manageConfig = manageConfigMapper.getMangeConfigByEPId(userVO.getParentId());

        Long checkId = distrInstorageFormVO.getCheckId();
        DistrInCheck distrInCheck = distrInCheckMapper.selectByPrimaryKey(checkId);
        addInstorageVO.setCheckerId(distrInCheck.getCheckerId());
        addInstorageVO.setSecondCheckerId(distrInCheck.getSecondCheckerId());

        Long distrReceiveId = distrInCheck.getBaseOrderId();
        DistrInReceive receive = distrInReceiveMapper.selectByPrimaryKey(distrReceiveId);
        addInstorageVO.setReceiverId(receive.getReceiverId());
        addInstorageVO.setSecondReceiverId(receive.getSecondReceiverId());
        Long distrInNoticeId = receive.getBaseOrderId();
        List<DistrInNoticeDetail> distrInNoticeDetailList = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseId(distrInNoticeId,userVO.getEnterpriseId());

        /**
         * 1.采购订单
         * 2.采购收货
         * 3.采购验收
         * 4.采购入库
         */
        Map<String,Object> orderMap = purchaseAddInStorageService.addOrder(userVO, addInstorageVO);
        Map<String,Object> receiveMap = purchaseAddInStorageService.addReceive(userVO, addInstorageVO, orderMap);
        Map<String,Object> checkMap = purchaseAddInStorageService.addCheck(userVO, addInstorageVO, receiveMap);
        Map<String, Object> storageMap = purchaseAddInStorageService.addInstorage(userVO, addInstorageVO, checkMap, orderMap);
        /**
         * 5.配货单
         * 6.配货出库
         */
        initAddInstorageVO(addInstorageVO);
        Map<String, Object> distrSendMap = addDistrSend(userVO, addInstorageVO, manageConfig);
        Map<String, Object> distrOutMap = addDistrOut(userVO, addInstorageVO, manageConfig, (Map<String, SafetyStock>) storageMap.get("shelfVOMap"),distrSendMap);

        //根据总部的配货出库单明细的code ,更新门店的配进订单的base_order_id
        List<DistrOutDetail> distrOutDetailList = (List<DistrOutDetail>) distrOutMap.get("distrOutDetail");
        for(DistrOutDetail distrOutDetail : distrOutDetailList){
            Long goodsId = distrOutDetail.getGoodsId();
            for(DistrInNoticeDetail inNoticeDetail : distrInNoticeDetailList){
                if(goodsId.equals(inNoticeDetail.getGoodsId())){
                    //更新配进订单
                    DistrInNoticeDetail updateInNoticeDetail = (DistrInNoticeDetail) EntityUtils.reflectUpdateSetDefaultValue(DistrInNoticeDetail.class, userVO);
                    updateInNoticeDetail.setBaseOrderDtlId(distrOutDetail.getId());//配货出库明细id
                    updateInNoticeDetail.setBaseOrderId(distrOutDetail.getOutId());//配货单id
                    updateInNoticeDetail.setBaseOrderType(distrOutDetail.getOrderType());
                    updateInNoticeDetail.setBaseOrderDate(distrOutDetail.getOutDate());
                    updateInNoticeDetail.setBaseOrderCode(distrOutDetail.getOutCode());//配货单编码
                    updateInNoticeDetail.setId(inNoticeDetail.getId());
                    distrInNoticeDetailMapper.updateByPrimaryKeySelective(updateInNoticeDetail);
                }
            }
        }





        //返回单据LIST
        resultList.add("已经成功生成总部采购订单:" + ((PurchaseOrder)orderMap.get("purchaseOrder")).getCode());
        resultList.add("已经成功生成总部采购收货:" + ((PurchaseReceive)receiveMap.get("purchaseReceive")).getCode());
        resultList.add("已经成功生成总部采购验收:" + ((PurchaseCheck)checkMap.get("purchaseCheck")).getCode());
        resultList.add("已经成功生成总部采购入库:" + ((PurchaseInStorage)storageMap.get("storage")).getCode());
        resultList.add("已经成功生成总部配货单:" + ((DistrSend)distrSendMap.get("distrSend")).getCode());
        resultList.add("已经成功生成总部配货出库:" + ((DistrOut)distrOutMap.get("distrOut")).getCode());

//        setCommonData(userVO, distrInStorage);

        return resultList;

    }

    private void packInstorageOther(UserVO userVO, Integer chainType, AddInstorageOtherVO addInstorageOtherVO) {
        if(chainType == ChainType.Selfoperatedshop.getCode()){
            addInstorageOtherVO.setSettlementUnitId(userVO.getParentId());
            addInstorageOtherVO.setReceiveUnitId(userVO.getEnterpriseId());
        } else if(chainType == ChainType.Division.getCode()){
            addInstorageOtherVO.setSettlementUnitId(userVO.getEnterpriseId());
            addInstorageOtherVO.setReceiveUnitId(userVO.getEnterpriseId());
        }
    }


    /**
     * 配货单
     * @param userVO
     * @param addInstorageVO
     * @return
     */
    private Map<String,Object> addDistrSend(UserVO userVO,AddInstorageVO addInstorageVO,ManageConfig manageConfig) throws Exception {
        int status = DistrSendStatus.FINISHED;

        Map<String,Object> result = new HashMap<>(2);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getParentId() == 0L ? userVO.getEnterpriseId() : userVO.getParentId());
        //配货单
        DistrSend distrSend = (DistrSend) EntityUtils.reflectAddSetDefaultValue(DistrSend.class,userVO);
        distrSend.setEnterpriseId(enterprise.getId());
        distrSend.setParentId(enterprise.getParentId());
        distrSend.setDistrDate(new Date());//配货日期
        distrSend.setOrderType(OrderRule.DISTR_ORDER.getType());
        //生成单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_ORDER.getCodePrefix(), userVO.getParentId(), userVO.getParentCode());
        distrSend.setCode(code);
        //要货单位信息
        distrSend.setRequestUnitId(userVO.getEnterpriseId());
        distrSend.setRequestUnitCode(userVO.getEnterpriseCode());
        distrSend.setRequestUnitName(userVO.getEnterpriseName());
        //配货人员信息
        if(manageConfig.getDistrManId() == null || manageConfig.getDistrManCode() == null || manageConfig.getDistrManName() == null){
            throw new BusinessException("请设置总部默认配货人员");
        }

        distrSend.setDistrManId(manageConfig.getDistrManId());
        distrSend.setDistrManCode(manageConfig.getDistrManCode());
        distrSend.setDistrManName(manageConfig.getDistrManName());
        //配货类型
        distrSend.setDistrType(addInstorageVO.getDistrType());
        //配货规则
        distrSend.setDistrRule(DistributionRule.DISTRIBUTION_RULE_A.getCode());
        //缺配处理
        distrSend.setLackHandle(LackHandle.LackHandle_B.getCode());
        //数量合计
        distrSend.setQuantityTotal(addInstorageVO.getQuantityTotal());
        //品种合计
        distrSend.setVarietiesQuantity(addInstorageVO.getVarietiesQuantity());
        //金额合计
        distrSend.setAmountTotal(addInstorageVO.getAmountTotal());
        //整单折扣
        distrSend.setWholeDiscount(addInstorageVO.getWholeDiscount());
        //整单优惠金额
        distrSend.setWholeDiscountAmount(addInstorageVO.getWholeDiscountAmount());
        //实际金额合计
        distrSend.setRealAmountTotal(addInstorageVO.getRealAmountTotal());
        //不含税金额合计
        distrSend.setNotaxRealAmountTotal(addInstorageVO.getNotaxRealAmountTotal());
        //税额合计
        distrSend.setTaxAmountTotal(addInstorageVO.getTaxAmountTotal());
        //状态
        distrSend.setStatus(status);

        distrSendMapper.insertSelective(distrSend);

        List<DistrSendDetail> distrSendDetailList = new ArrayList<>();
        //计算明细
        List<AddInstorageDtlVO> addInstorageDtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        int k = 1;
        for(AddInstorageDtlVO addInstorageDtlVO : addInstorageDtlVOList){
            DistrSendDetail distrSendDetail = (DistrSendDetail) EntityUtils.reflectAddSetDefaultValue(DistrSendDetail.class,userVO);
            distrSendDetail.setEnterpriseId(enterprise.getId());
            distrSendDetail.setParentId(enterprise.getParentId());
            distrSendDetail.setDistrDate(new Date());
            distrSendDetail.setDistrId(distrSend.getId());
            distrSendDetail.setOrderType(OrderRule.DISTR_ORDER.getType());
            distrSendDetail.setDistrCode(distrSend.getCode());

            Long goodsId = addInstorageDtlVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if(goods == null) throw new BusinessException("商品不存在");
            if(goods.getSpecialDrug() != -1 && addInstorageVO.getSecondReceiverId() == null){
                throw new BusinessException("第"+addInstorageDtlVO.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
            }
            distrSendDetail.setGoodsId(goodsId);
            distrSendDetail.setGoodsCode(goods.getCode());
            distrSendDetail.setGoodsGenericName(goods.getGenericName());
            distrSendDetail.setGoodsName(goods.getName());
            distrSendDetail.setGoodsPlace(goods.getPlace());
            distrSendDetail.setGoodsSpecification(goods.getSpecification());
            distrSendDetail.setUnitId(goods.getUnitId());
            distrSendDetail.setUnitName(goods.getUnitName());
            distrSendDetail.setApprovalNumber(goods.getApprovalNumber());
            distrSendDetail.setManufacturer(goods.getManufacturer());
            distrSendDetail.setManufacturerId(goods.getManufacturerId());
            distrSendDetail.setDosageId(goods.getDosageId());
            distrSendDetail.setDosageName(goods.getDosageName());

            //计算金额
            //总部库存使用量
            BigDecimal parentUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getEnterpriseId(), goodsId);
            if (parentUsableQuantity == null) {
                parentUsableQuantity = BigDecimal.ZERO;
            }
            distrSendDetail.setParentUsableQuantity(parentUsableQuantity);

            //门店库存可用量
            BigDecimal usableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getRequestUnitId(), goodsId);
            if (usableQuantity == null) {
                usableQuantity = BigDecimal.ZERO;
            }
            distrSendDetail.setUsableQuantity(usableQuantity);
            distrSendDetail.setQuantity(addInstorageDtlVO.getQuantity());

            distrSendDetail.setUnitPrice(addInstorageDtlVO.getUnitPrice());
            distrSendDetail.setLineDiscount(addInstorageDtlVO.getLineDiscount());

            distrSendDetail.setAmount(addInstorageDtlVO.getAmount());
            distrSendDetail.setWholeDiscount(addInstorageDtlVO.getWholeDiscount());
            distrSendDetail.setLineDiscountAmount(addInstorageDtlVO.getLineDiscountAmount());
            distrSendDetail.setRealPrice(addInstorageDtlVO.getUnitPrice());
            distrSendDetail.setRealAmount(addInstorageDtlVO.getRealAmount());
            distrSendDetail.setTaxAmount(addInstorageDtlVO.getTaxAmount());
            Long taxRateId = addInstorageDtlVO.getTaxRateId();
            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(taxRateId);
            if(goodsTaxRate == null) throw new BusinessException("税率不存在");
            distrSendDetail.setTaxRateId(taxRateId);
            distrSendDetail.setTaxRate(goodsTaxRate.getTaxRate());
            distrSendDetail.setNotaxRealAmount(addInstorageDtlVO.getNotaxRealAmount());
            distrSendDetail.setNotaxRealPrice(addInstorageDtlVO.getNotaxRealPrice());
            distrSendDetail.setUnclearQuantity(BigDecimal.ZERO);
            distrSendDetail.setClearQuantity(addInstorageDtlVO.getQuantity());
            distrSendDetail.setLineNum(k++);
            distrSendDetail.setStatus(status);

            distrSendDetailMapper.insertSelective(distrSendDetail);
//            // 锁定库存
//            lockStockByDetail(userVO, distrSendDetail);
            distrSendDetailList.add(distrSendDetail);
        }

        result.put("distrSend",distrSend);
        result.put("distrSendDetail",distrSendDetailList);
        return result;
    }
//    private void lockStockByDetail(UserVO userVO, DistrSendDetail distrSendDetail) {
//        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
//        lockQtyArgVO.setEnterpriseId(distrSendDetail.getEnterpriseId());
//        lockQtyArgVO.setParentId(distrSendDetail.getParentId());
//        lockQtyArgVO.setBaseOrderId(distrSendDetail.getDistrId());
//        lockQtyArgVO.setBaseOrderCode(distrSendDetail.getDistrCode());
//        lockQtyArgVO.setBaseOrderType(distrSendDetail.getOrderType());
//        lockQtyArgVO.setBaseOrderDate(distrSendDetail.getDistrDate());
//        lockQtyArgVO.setGoodsId(distrSendDetail.getGoodsId());
//        lockQtyArgVO.setQuantity(distrSendDetail.getQuantity());
//        lockQtyArgVO.setUser(userVO);
//        commonComponent.lockStockAndCost(lockQtyArgVO);
//    }


    /**
     * 要货计划
     * @param userVO
     * @param addInstorageVO
     * @return
     */
    private Map<String,Object> addDistrReqPlan(UserVO userVO,AddInstorageVO addInstorageVO,ManageConfig manageConfig) throws Exception {
        int status = DistrReqPlanStatus.FINISHED;
        Map<String,Object> result = new HashMap<>(3);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getParentId() == 0L ? userVO.getEnterpriseId() : userVO.getParentId());
        //要货计划
        DistrReqPlan distrReqPlan = (DistrReqPlan) EntityUtils.reflectAddSetDefaultValue(DistrReqPlan.class,userVO);

        //生成单号
        String code = orderCodeComponent.
                generate(OrderRule.REQUIRE_PLAN.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        distrReqPlan.setCode(code);
        distrReqPlan.setPlanDate(new Date());//要货计划日期
        distrReqPlan.setOrderType(OrderRule.REQUIRE_PLAN.getType());
        //配货单位信息
        distrReqPlan.setDistrUnitId(userVO.getParentId());
        distrReqPlan.setDistrUnitCode(userVO.getParentCode());
        distrReqPlan.setDistrUnitName(userVO.getParentName());
        //出库单位信息
        distrReqPlan.setOutboundUnitId(addInstorageVO.getOutboundUnitId());
        distrReqPlan.setOutboundUnitCode(addInstorageVO.getOutboundUnitCode());
        distrReqPlan.setOutboundUnitName(addInstorageVO.getOutboundUnitName());
        //配货类型
        distrReqPlan.setRequestType(addInstorageVO.getDistrType());
        //计划人员
        distrReqPlan.setPlannerId(userVO.getUserId());
        distrReqPlan.setPlannerCode(userVO.getUserCode());
        distrReqPlan.setPlannerName(userVO.getUserName());


        //数量合计
        distrReqPlan.setQuantityTotal(addInstorageVO.getQuantityTotal());
        //品种合计
        distrReqPlan.setVarietiesQuantity(addInstorageVO.getVarietiesQuantity());

        //状态
        distrReqPlan.setStatus(status);

        distrReqPlanMapper.insertSelective(distrReqPlan);

        List<DistrReqPlanDetail> distrReqPlanDetailList = new ArrayList<>();
        //计算明细
        List<AddInstorageDtlVO> addInstorageDtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        int k = 1;
        for(AddInstorageDtlVO addInstorageDtlVO : addInstorageDtlVOList){
            DistrReqPlanDetail detail = (DistrReqPlanDetail) EntityUtils.reflectAddSetDefaultValue(DistrReqPlanDetail.class,userVO);

            detail.setPlanDate(new Date());
            detail.setPlanId(distrReqPlan.getId());
            detail.setOrderType(OrderRule.REQUIRE_PLAN.getType());
            detail.setPlanCode(distrReqPlan.getCode());
            detail.setQuantity(addInstorageDtlVO.getQuantity());



            Long goodsId = addInstorageDtlVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if(goods == null) throw new BusinessException("商品不存在");
            detail.setGoodsId(goodsId);
            detail.setGoodsCode(goods.getCode());
            detail.setGoodsGenericName(goods.getGenericName());
            detail.setGoodsName(goods.getName());
            detail.setGoodsPlace(goods.getPlace());
            detail.setGoodsSpecification(goods.getSpecification());
            detail.setUnitId(goods.getUnitId());
            detail.setUnitName(goods.getUnitName());
            detail.setApprovalNumber(goods.getApprovalNumber());
            detail.setManufacturer(goods.getManufacturer());
            detail.setManufacturerId(goods.getManufacturerId());
            detail.setDosageId(goods.getDosageId());
            detail.setDosageName(goods.getDosageName());

            //配货机构库存可用量
            BigDecimal orgUsableQuantity = stockMapper.getQuantityTotalByGoodsId(goodsId, userVO.getParentId());

            //门店库存可用量
            BigDecimal usableQuantity = stockMapper.getQuantityTotalByGoodsId(goodsId, userVO.getEnterpriseId());
            detail.setUsableQuantity(usableQuantity);
            detail.setOrgUsableQuantity(orgUsableQuantity);

            detail.setLineNum(k++);
            detail.setStatus(status);
            distrReqPlanDetailMapper.insertSelective(detail);
            distrReqPlanDetailList.add(detail);
        }

        result.put("distrReqPlan",distrReqPlan);
        result.put("distrReqPlanDetail",distrReqPlanDetailList);
        return result;
    }

    /**
     * 配货出库
     * @param userVO
     * @param addInstorageVO
     * @return
     */
    private Map<String,Object> addDistrOut(UserVO userVO,AddInstorageVO addInstorageVO,ManageConfig manageConfig,Map<String,SafetyStock> shelfVOMap,Map<String,Object> distSendMap) throws Exception {
        int status = DistrSendStatus.FINISHED;
        DistrSend distrSend = (DistrSend) distSendMap.get("distrSend");
        List<DistrSendDetail> distrSendDetailList = (List<DistrSendDetail>) distSendMap.get("distrSendDetail");
        Map<String,Object> result = new HashMap<>(3);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getParentId() == 0L ? userVO.getEnterpriseId() : userVO.getParentId());
        //配货单
        DistrOut distrOut = (DistrOut) EntityUtils.reflectAddSetDefaultValue(DistrOut.class,userVO);
        distrOut.setEnterpriseId(enterprise.getId());
        distrOut.setParentId(enterprise.getParentId());
        distrOut.setOutDate(new Date());//配货出库日期
        distrOut.setOrderType(OrderRule.DISTR_OUT.getType());
        distrOut.setBaseOrderId(distrSend.getId());
        distrOut.setBaseOrderDate(distrSend.getDistrDate());
        distrOut.setBaseOrderCode(distrSend.getCode());
        distrOut.setBaseOrderType(distrSend.getOrderType());
        //生成单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_OUT.getCodePrefix(), userVO.getParentId(), userVO.getParentCode());
        distrOut.setCode(code);
        //要货单位信息
        distrOut.setRequestUnitId(userVO.getEnterpriseId());
        distrOut.setRequestUnitCode(userVO.getEnterpriseCode());
        distrOut.setRequestUnitName(userVO.getEnterpriseName());
        //配货人员信息
        //配货人员信息
        if(manageConfig.getDistrManId() == null || manageConfig.getDistrManCode() == null || manageConfig.getDistrManName() == null){
            throw new BusinessException("请设置总部默认配货人员");
        }
        distrOut.setSendManId(manageConfig.getDistrManId());
        distrOut.setSendManCode(manageConfig.getDistrManCode());
        distrOut.setSendManName(manageConfig.getDistrManName());
        //配货类型
        distrOut.setDistrType(addInstorageVO.getDistrType());
        //出库人员
        if(manageConfig.getInOutManId() == null || manageConfig.getInOutManCode() == null || manageConfig.getInOutManName() == null){
            throw new BusinessException("请设置总部默认出库人员");
        }
        distrOut.setOutManId(manageConfig.getInOutManId());
        distrOut.setOutManCode(manageConfig.getInOutManCode());
        distrOut.setOutManName(manageConfig.getInOutManName());
        //复核人员
        //出库人员
        if(manageConfig.getCheckerId() == null || manageConfig.getCheckerCode() == null || manageConfig.getCheckerName() == null){
            throw new BusinessException("请设置总部默认出库人员");
        }
        distrOut.setCheckerId(manageConfig.getCheckerId());
        distrOut.setCheckerCode(manageConfig.getCheckerCode());
        distrOut.setCheckerName(manageConfig.getCheckerName());
        //流通监管码
        distrOut.setFlowCode(addInstorageVO.getFlowCode());
        //数量合计
        distrOut.setQuantityTotal(addInstorageVO.getQuantityTotal());
        //品种合计
        distrOut.setVarietiesQuantity(addInstorageVO.getVarietiesQuantity());
        //金额合计
        distrOut.setAmountTotal(addInstorageVO.getAmountTotal());
        //整单折扣
        distrOut.setWholeDiscount(addInstorageVO.getWholeDiscount());
        //整单优惠金额
        distrOut.setWholeDiscountAmount(addInstorageVO.getWholeDiscountAmount());
        //实际金额合计
        distrOut.setRealAmountTotal(addInstorageVO.getRealAmountTotal());
        //不含税金额合计
        distrOut.setNotaxRealAmountTotal(addInstorageVO.getNotaxRealAmountTotal());
        //税额合计
        distrOut.setTaxAmountTotal(addInstorageVO.getTaxAmountTotal());
        //状态
//        Enterprise enterpriseStatus = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
//        if(enterpriseStatus == null){
//            throw new BusinessException("要货单位ID无效");
//        }
//        if(enterpriseStatus.getChainType() != ChainType.Division.getCode()){
//            distrOut.setStatus(DistrOutStatus.FINISHED);
//        }else{
//            distrOut.setStatus(DistrOutStatus.WAIT_BILL);
//        }
        distrOut.setStatus(status);
        distrOutMapper.insertSelective(distrOut);

        List<DistrOutDetail> distrOutDetailList = new ArrayList<>();
        List<DistrOutShelf> outShelfList = new ArrayList<>();
        //计算明细
        List<AddInstorageDtlVO> addInstorageDtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        int k = 1;
        for(AddInstorageDtlVO addInstorageDtlVO : addInstorageDtlVOList){
            DistrOutDetail detail = (DistrOutDetail) EntityUtils.reflectAddSetDefaultValue(DistrOutDetail.class,userVO);
            detail.setEnterpriseId(enterprise.getId());
            detail.setParentId(enterprise.getParentId());
            detail.setOutDate(new Date());
            detail.setOutId(distrOut.getId());
            detail.setOrderType(OrderRule.DISTR_OUT.getType());
            detail.setOutCode(distrOut.getCode());
            Long goodsId = addInstorageDtlVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if(goods == null) throw new BusinessException("商品不存在");

            Optional<DistrSendDetail> sendDetail = distrSendDetailList.stream().filter(item -> item.getGoodsId().equals(goodsId)).findFirst();
            if(!sendDetail.isPresent()) throw new BusinessException("直调生成配货出库单明细，获取配货单异常");
            detail.setBaseOrderId(distrSend.getId());
            detail.setBaseOrderDtlId(sendDetail.get().getId());
            detail.setBaseOrderCode(distrSend.getCode());
            detail.setBaseOrderDate(distrSend.getDistrDate());
            detail.setBaseOrderType(distrSend.getOrderType());
            detail.setGoodsId(goodsId);
            detail.setGoodsCode(goods.getCode());
            detail.setGoodsGenericName(goods.getGenericName());
            detail.setGoodsName(goods.getName());
            detail.setGoodsPlace(goods.getPlace());
            detail.setGoodsSpecification(goods.getSpecification());
            detail.setUnitId(goods.getUnitId());
            detail.setUnitName(goods.getUnitName());
            detail.setApprovalNumber(goods.getApprovalNumber());
            detail.setManufacturer(goods.getManufacturer());
            detail.setManufacturerId(goods.getManufacturerId());
            detail.setDosageId(goods.getDosageId());
            detail.setDosageName(goods.getDosageName());

            //计算金额

            detail.setQuantity(addInstorageDtlVO.getQuantity());

            BigDecimal lineDiscount = addInstorageDtlVO.getLineDiscount();
            detail.setUnitPrice(addInstorageDtlVO.getUnitPrice());
            detail.setLineDiscount(lineDiscount);

            detail.setAmount(addInstorageDtlVO.getAmount());
            detail.setWholeDiscount(addInstorageDtlVO.getWholeDiscount());
            detail.setLineDiscountAmount(addInstorageDtlVO.getLineDiscountAmount());
            detail.setRealPrice(addInstorageDtlVO.getUnitPrice());
            detail.setRealAmount(addInstorageDtlVO.getRealAmount());
            detail.setTaxAmount(addInstorageDtlVO.getTaxAmount());
            Long taxRateId = addInstorageDtlVO.getTaxRateId();
            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(taxRateId);
            if(goodsTaxRate == null) throw new BusinessException("税率不存在");
            detail.setTaxRateId(taxRateId);
            detail.setTaxRate(goodsTaxRate.getTaxRate());
            detail.setNotaxRealAmount(addInstorageDtlVO.getNotaxRealAmount());
            detail.setNotaxRealPrice(addInstorageDtlVO.getNotaxRealPrice());
            detail.setUnclearQuantity(BigDecimal.ZERO);
            detail.setClearQuantity(addInstorageDtlVO.getQuantity());
            detail.setLineNum(k++);

//            if(enterpriseStatus.getChainType() != ChainType.Division.getCode()){
//                detail.setStatus(DistrOutStatus.FINISHED);
//            }else{
//                detail.setStatus(DistrOutStatus.WAIT_BILL);
//            }
            detail.setStatus(status);
            distrOutDetailMapper.insertSelective(detail);
            distrOutDetailList.add(detail);
            //出库货位明细
            List<AddInstorageGoodsLotNumberVO> goodsLotNumberVOList = addInstorageDtlVO.getGoodsLotNumberVOList();
            int kk = 1;
            for(AddInstorageGoodsLotNumberVO lotNumberVO : goodsLotNumberVOList){
                DistrOutShelf outShelf = (DistrOutShelf) EntityUtils.reflectAddSetDefaultValue(DistrOutShelf.class,userVO);
                outShelf.setEnterpriseId(enterprise.getId());
                outShelf.setParentId(enterprise.getParentId());
//                if(enterpriseStatus.getChainType() != ChainType.Division.getCode()){
//                    outShelf.setStatus(DistrOutStatus.FINISHED);
//                }else{
//                    outShelf.setStatus(DistrOutStatus.WAIT_BILL);
//                }
                outShelf.setStatus(status);

                outShelf.setDtlId(detail.getId());
                outShelf.setOutId(detail.getOutId());
                outShelf.setGoodsId(goodsId);
                outShelf.setGoodsCode(goods.getCode());
                outShelf.setGoodsName(goods.getName());
                outShelf.setLotNumber(lotNumberVO.getLotNumber());

                BigDecimal qualifiedQuantity = lotNumberVO.getQualifiedQuantity();
                BigDecimal unqualifiedQuantity = lotNumberVO.getUnqualifiedQuantity();


                if(qualifiedQuantity != null && unqualifiedQuantity != null){
                     outShelf.setQuantity(qualifiedQuantity.add(unqualifiedQuantity));
                     outShelf.setUnclearQuantity(BigDecimal.ZERO);
                     outShelf.setClearQuantity(qualifiedQuantity.add(unqualifiedQuantity));
                     outShelf.setVerificationQuantity(qualifiedQuantity.add(unqualifiedQuantity));
                     outShelf.setUnverificationQuantity(BigDecimal.ZERO);
                } else if(qualifiedQuantity != null){
                     outShelf.setQuantity(qualifiedQuantity);
                     outShelf.setUnclearQuantity(BigDecimal.ZERO);
                     outShelf.setClearQuantity(qualifiedQuantity);
                     outShelf.setVerificationQuantity(qualifiedQuantity);
                     outShelf.setUnverificationQuantity(BigDecimal.ZERO);
                } else if(unqualifiedQuantity != null){
                     outShelf.setQuantity(unqualifiedQuantity);
                     outShelf.setUnclearQuantity(BigDecimal.ZERO);
                     outShelf.setClearQuantity(unqualifiedQuantity);
                     outShelf.setVerificationQuantity(unqualifiedQuantity);
                     outShelf.setUnverificationQuantity(BigDecimal.ZERO);
                } else {
                     outShelf.setQuantity(BigDecimal.ZERO);
                     outShelf.setUnclearQuantity(BigDecimal.ZERO);
                     outShelf.setClearQuantity(BigDecimal.ZERO);
                     outShelf.setVerificationQuantity(BigDecimal.ZERO);
                     outShelf.setUnverificationQuantity(BigDecimal.ZERO);
                }
                /**
                 * 在这做小于等于0判断，减少上面判断的逻辑
                 */
                if(outShelf.getQuantity().compareTo(BigDecimal.ZERO) <= 0) throw new BusinessException("数量不能小于等于0");
                LotNumber lotNumber = lotNumberMapper.getLotNumInfo(enterprise.getId(),goodsId,lotNumberVO.getLotNumber());
                if(lotNumber == null) throw new BusinessException("配货出库批号" + lotNumberVO.getLotNumber() + "不存在");
                //货位safetyStockMap
                SafetyStock safetyStock = shelfVOMap.get(lotNumberVO.getLotNumber() + "" + goodsId);
                Long defaultShelfId = safetyStock.getDefaultShelfId();
                outShelf.setShelfId(defaultShelfId);
                outShelf.setShelfName(safetyStock.getDefaultShelfName());
                Map<String, Object> shelfMap = warehouseShelfMapper.getShelfInfoById(defaultShelfId);
                outShelf.setShelfStatusDesc(ShelfStatus.getName((Integer) shelfMap.get("jobArea"),0));
                outShelf.setLotId(lotNumber.getId());
                //计算金额
                setCalculLotShelf(lotNumberVO,outShelf.getQuantity(),goodsTaxRate.getTaxRate(),addInstorageDtlVO.getWholeDiscount(),
                        addInstorageDtlVO.getLineDiscountAmount(),addInstorageDtlVO.getAmount(),detail.getUnitPrice(), lineDiscount);
                outShelf.setUnitPrice(detail.getUnitPrice());
                outShelf.setAmount(lotNumberVO.getAmount());
                outShelf.setWholeDiscount(lotNumberVO.getWholeDiscount());
                outShelf.setLineDiscount(lotNumberVO.getLineDiscount());
                outShelf.setLineDiscountAmount(lotNumberVO.getLineDiscountAmount());
                outShelf.setRealPrice(lotNumberVO.getRealPrice());
                outShelf.setRealAmount(lotNumberVO.getRealAmount());
                outShelf.setTaxRateId(detail.getTaxRateId());
                outShelf.setTaxRate(detail.getTaxRate());
                outShelf.setNotaxRealPrice(lotNumberVO.getNotaxRealPrice());
                outShelf.setNotaxRealAmount(lotNumberVO.getNotaxRealAmount());
                outShelf.setTaxAmount(lotNumberVO.getTaxAmount());
                outShelf.setProductDate(lotNumber.getProductDate());
                outShelf.setValidDate(lotNumber.getValidUntil());
                outShelf.setLineNum(kk++);

                distrOutShelfMapper.insertSelective(outShelf);
                outShelfList.add(outShelf);
            }
        }

//        // 释放配送单锁定的库存
//        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
//        lockQtyArgVO.setEnterpriseId(distrOut.getEnterpriseId());
//        lockQtyArgVO.setParentId(distrOut.getParentId());
//        lockQtyArgVO.setBaseOrderId(distrOut.getBaseOrderId());
//        lockQtyArgVO.setBaseOrderType(distrOut.getBaseOrderType());
//        lockQtyArgVO.setUser(userVO);
//        commonComponent.releaseStockAndCost(lockQtyArgVO);

        List<DistrOutShelf> distrOutShelfList = distrOutShelfMapper.getDistrOutShelfListByOutId(distrOut.getId(), enterprise.getId());

        //出库操作生成出入库业务表数据
        OrderModelBuilder orderModelBuilder = new OrderModelBuilder(userVO);
        try {
            OrderModel orderModel = orderModelBuilder.buildOrderModel(OrderRule.DISTR_OUT, distrOut, distrOutShelfList);
            inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);
        } catch (Exception e) {
            throw new BusinessException("出入库业务生成关键表数据出错" + e.getMessage());
        }

        //生成财务数据
        financeComponent.distrOutToBalanceAndVoucher(userVO, distrOut);

        result.put("distrOut",distrOut);
        result.put("distrOutDetail",distrOutDetailList);
        result.put("distrOutShelf",outShelfList);
        return result;
    }




    /**
     * 配进收货
     * @param userVO
     * @param addInstorageVO
     * @return
     */
    private Map<String,Object> addDistrInReceive(UserVO userVO,AddInstorageVO addInstorageVO,Map<String,Object> map,ManageConfig manageConfig) throws Exception {
        DistrInNotice notice = (DistrInNotice) map.get("distrInNotice");
        List<DistrInNoticeDetail> distrInNoticeDetailList = (List<DistrInNoticeDetail>) map.get("distrInNoticeDetail");
        int status = DistrInStatus.FINISHED;

        if(ChainType.Division.getCode() == userVO.getChainType()){
            status = DistrInStatus.WAIT_BILL;
        }
        Map<String,Object> result = new HashMap<>(2);
//        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getParentId() == 0L ? userVO.getEnterpriseId() : userVO.getParentId());
        // 配进订单
        DistrInReceive distr = (DistrInReceive) EntityUtils.reflectAddSetDefaultValue(DistrInReceive.class,userVO);
        distr.setReceiveDate(addInstorageVO.getReceiveDate());//配进日期
        distr.setOrderType(OrderRule.DISTR_IN_RECEIVE.getType());
        //生成单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_IN_RECEIVE.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        distr.setCode(code);
        //基础单据信息
        distr.setBaseOrderId(notice.getId());
        distr.setBaseOrderCode(notice.getCode());
        distr.setBaseOrderDate(notice.getOrderDate());
        distr.setBaseOrderType(notice.getOrderType());
        //配货单位
        distr.setDistrUnitId(notice.getDistrUnitId());
        distr.setDistrUnitCode(notice.getDistrUnitCode());
        distr.setDistrUnitName(notice.getDistrUnitName());
        //出库单位
        distr.setOutboundUnitId(notice.getOutboundUnitId());
        distr.setOutboundUnitCode(notice.getOutboundUnitCode());
        distr.setOutboundUnitName(notice.getOutboundUnitName());

        //收货人员
        Long receiverId = addInstorageVO.getReceiverId();
        User user = userMapper.selectByPrimaryKey(receiverId);
        if(user == null){
            throw new BusinessException("收货人员不存在");
        }
        distr.setReceiverId(receiverId);
        distr.setReceiverCode(user.getCode());
        distr.setReceiverName(user.getName());

        Long secondReceiverId = addInstorageVO.getSecondReceiverId();
        if(secondReceiverId != null){
            User user2 = userMapper.selectByPrimaryKey(secondReceiverId);
            if(user2 == null) throw new BusinessException("第2收货人员不存在");
            distr.setSecondReceiverId(user2.getId());
            distr.setSecondReceiverCode(user2.getCode());
            distr.setSecondReceiverName(user2.getName());
        }

        //配货类型
        distr.setDistrType(addInstorageVO.getDistrType());


        //数量合计
        distr.setQuantityTotal(addInstorageVO.getQuantityTotal());
        //品种合计
        distr.setVarietiesQuantity(addInstorageVO.getVarietiesQuantity());
        //状态
        distr.setStatus(status);

        distrInReceiveMapper.insertSelective(distr);

        List<DistrInReceiveDetail> distrInReceiveDetailList = new ArrayList<>();
        //计算明细
        List<AddInstorageDtlVO> addInstorageDtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        int k = 1;
        for(AddInstorageDtlVO addInstorageDtlVO : addInstorageDtlVOList){
            DistrInReceiveDetail detail = (DistrInReceiveDetail) EntityUtils.reflectAddSetDefaultValue(DistrInReceiveDetail.class,userVO);

            detail.setReceiveId(distr.getId());
            detail.setReceiveDate(distr.getReceiveDate());
            detail.setOrderType(OrderRule.DISTR_IN_RECEIVE.getType());
            detail.setReceiveCode(distr.getCode());

            Long goodsId = addInstorageDtlVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if(goods == null) throw new BusinessException("商品不存在");
//            if(goods.getSpecialDrug() != -1 && addInstorageVO.getSecondReceiverId() == null){
//                throw new BusinessException("第"+addInstorageDtlVO.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
//            }
            Optional<DistrInNoticeDetail> distrFirst = distrInNoticeDetailList.stream().filter(item -> goodsId.equals(item.getGoodsId())).findFirst();
            if(distrFirst.isPresent()){
                detail.setBaseOrderCode(notice.getCode());
                detail.setBaseOrderId(notice.getId());
                detail.setBaseOrderDate(notice.getOrderDate());
                detail.setBaseOrderType(notice.getOrderType());
                detail.setBaseOrderDtlId(distrFirst.get().getId());

                detail.setGoodsId(goodsId);
                detail.setGoodsCode(goods.getCode());
                detail.setGoodsGenericName(goods.getGenericName());
                detail.setGoodsName(goods.getName());
                detail.setGoodsPlace(goods.getPlace());
                detail.setGoodsSpecification(goods.getSpecification());
                detail.setUnitId(goods.getUnitId());
                detail.setUnitName(goods.getUnitName());
                detail.setApprovalNumber(goods.getApprovalNumber());
                detail.setManufacturer(goods.getManufacturer());
                detail.setManufacturerId(goods.getManufacturerId());
                detail.setDosageId(goods.getDosageId());
                detail.setDosageName(goods.getDosageName());

                //数量统计
                //到货数量
                detail.setArrivalQuantity(addInstorageDtlVO.getReceiveQuantity());
                detail.setReceiveQuantity(addInstorageDtlVO.getReceiveQuantity());
                detail.setRefuseQuantity(addInstorageDtlVO.getRefuseQuantity());

                detail.setUnclearQuantity(BigDecimal.ZERO);
                detail.setClearQuantity(addInstorageDtlVO.getReceiveQuantity());

                detail.setUnclearQuantity(BigDecimal.ZERO);
                detail.setClearQuantity(addInstorageDtlVO.getQuantity());
                detail.setLineNum(k++);
                detail.setStatus(status);
                distrInReceiveDetailMapper.insertSelective(detail);
                distrInReceiveDetailList.add(detail);
            }


        }

        result.put("distrInReceive",distr);
        result.put("distrInReceiveDetail",distrInReceiveDetailList);
        return result;
    }


    /**
     * 配进验收
     * @param userVO
     * @param addInstorageVO
     * @return
     */
    private Map<String,Object> addDistrInCheck(UserVO userVO,AddInstorageVO addInstorageVO,Map<String,Object> map,Map<String,Object> noticeMap,ManageConfig manageConfig) throws Exception {
        DistrInReceive receive = (DistrInReceive) map.get("distrInReceive");
        List<DistrInReceiveDetail> distrInReceiveDetailList = (List<DistrInReceiveDetail>) map.get("distrInReceiveDetail");
        DistrInNotice  distrInNotice = (DistrInNotice) noticeMap.get("distrInNotice");
        List<DistrInNoticeDetail>  distrInNoticeList = (List<DistrInNoticeDetail>) noticeMap.get("distrInNoticeDetail");
        int status = DistrInStatus.FINISHED;

        if(ChainType.Division.getCode() == userVO.getChainType()){
            status = DistrInStatus.WAIT_BILL;
        }

        Map<String,Object> result = new HashMap<>(2);
//        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getParentId() == 0L ? userVO.getEnterpriseId() : userVO.getParentId());
        // 配进验收
        DistrInCheck distr = (DistrInCheck) EntityUtils.reflectAddSetDefaultValue(DistrInCheck.class,userVO);
        distr.setCheckDate(addInstorageVO.getCheckDate());//验收日期
        distr.setOrderType(OrderRule.DISTR_IN_CHECK.getType());
        //生成单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_IN_CHECK.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        distr.setCode(code);
        distr.setNoticeId(distrInNotice.getId());
        //基础单据信息
        distr.setBaseOrderId(receive.getId());
        distr.setBaseOrderCode(receive.getCode());
        distr.setBaseOrderDate(receive.getReceiveDate());
        distr.setBaseOrderType(receive.getOrderType());
        //配货单位
        distr.setDistrUnitId(receive.getDistrUnitId());
        distr.setDistrUnitCode(receive.getDistrUnitCode());
        distr.setDistrUnitName(receive.getDistrUnitName());
        //出库单位
        distr.setOutboundUnitId(distrInNotice.getOutboundUnitId());
        distr.setOutboundUnitCode(distrInNotice.getOutboundUnitCode());
        distr.setOutboundUnitName(distrInNotice.getOutboundUnitName());

        //验收人员
        Long checkerId = addInstorageVO.getCheckerId();
        User user = userMapper.selectByPrimaryKey(checkerId);
        if(user == null) throw new BusinessException("验收人员不存在");
        distr.setCheckerId(checkerId);
        distr.setCheckerCode(user.getCode());
        distr.setCheckerName(user.getName());


        Long secondCheckerId = addInstorageVO.getSecondCheckerId();
        if(secondCheckerId != null){
            User user2 = userMapper.selectByPrimaryKey(secondCheckerId);
            if(user2 == null) throw new BusinessException("第2验收人员不存在");
            distr.setSecondCheckerId(user2.getId());
            distr.setSecondCheckerCode(user2.getCode());
            distr.setSecondCheckerName(user2.getName());
        }

        //配货类型
        distr.setDistrType(addInstorageVO.getDistrType());



        //数量合计
        distr.setQuantityTotal(addInstorageVO.getQuantityTotal());
        //品种合计
        distr.setVarietiesQuantity(addInstorageVO.getVarietiesQuantity());

        //状态
        distr.setStatus(status);

        distrInCheckMapper.insertSelective(distr);

        List<DistrInCheckDetail> distrInCheckDetailList = new ArrayList<>();
        //计算明细
        List<AddInstorageDtlVO> addInstorageDtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        int k = 1;
        for(AddInstorageDtlVO addInstorageDtlVO : addInstorageDtlVOList){
            DistrInCheckDetail detail = (DistrInCheckDetail) EntityUtils.reflectAddSetDefaultValue(DistrInCheckDetail.class,userVO);

            detail.setCheckId(distr.getId());
            detail.setCheckDate(distr.getCheckDate());
            detail.setOrderType(OrderRule.DISTR_IN_CHECK.getType());
            detail.setCheckCode(distr.getCode());

            Long goodsId = addInstorageDtlVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if(goods == null) throw new BusinessException("商品不存在");
            if(goods.getSpecialDrug() != -1 && addInstorageVO.getSecondCheckerId() == null){
                throw new BusinessException("第"+addInstorageDtlVO.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二验收人!");
            }
            Optional<DistrInReceiveDetail> distrFirst = distrInReceiveDetailList.stream().filter(item -> goodsId.equals(item.getGoodsId())).findFirst();
            if(distrFirst.isPresent()){
                detail.setBaseOrderCode(receive.getCode());
                detail.setBaseOrderId(receive.getId());
                detail.setBaseOrderDate(receive.getReceiveDate());
                detail.setBaseOrderType(receive.getOrderType());
                detail.setBaseOrderDtlId(distrFirst.get().getId());
                detail.setNoticeId(distrInNotice.getId());
                Optional<DistrInNoticeDetail> distrNoticeFirst = distrInNoticeList.stream().filter(item1 -> goodsId.equals(item1.getGoodsId())).findFirst();
                if(distrNoticeFirst.isPresent()){
                    detail.setNoticeDtlId(distrNoticeFirst.get().getId());
                }
                detail.setGoodsId(goodsId);
                detail.setGoodsCode(goods.getCode());
                detail.setGoodsGenericName(goods.getGenericName());
                detail.setGoodsName(goods.getName());
                detail.setGoodsPlace(goods.getPlace());
                detail.setGoodsSpecification(goods.getSpecification());
                detail.setUnitId(goods.getUnitId());
                detail.setUnitName(goods.getUnitName());
                detail.setApprovalNumber(goods.getApprovalNumber());
                detail.setManufacturer(goods.getManufacturer());
                detail.setManufacturerId(goods.getManufacturerId());
                detail.setDosageId(goods.getDosageId());
                detail.setDosageName(goods.getDosageName());

                //数量统计
                //到货数量
                detail.setReceiveQuantity(addInstorageDtlVO.getReceiveQuantity());
                detail.setQualifiedQuantity(addInstorageDtlVO.getQualifiedQuantity());
                detail.setUnqualifiedQuantity(addInstorageDtlVO.getUnqualifiedQuantity());

                detail.setUnclearQuantity(BigDecimal.ZERO);
                detail.setClearQuantity(addInstorageDtlVO.getReceiveQuantity());

                detail.setUnclearQuantity(BigDecimal.ZERO);
                detail.setClearQuantity(addInstorageDtlVO.getQuantity());
                detail.setLineNum(k++);
                detail.setStatus(status);
                distrInCheckDetailMapper.insertSelective(detail);
                distrInCheckDetailList.add(detail);

                //配进验收品种明细
                List<AddInstorageGoodsLotNumberVO> goodsLotNumberVOList = addInstorageDtlVO.getGoodsLotNumberVOList();
                int kk = 1;
                for (AddInstorageGoodsLotNumberVO lotNumberVO : goodsLotNumberVOList){
                    DistrInCheckLot distrInCheckLot = (DistrInCheckLot) EntityUtils.reflectAddSetDefaultValue(DistrInCheckLot.class,userVO);

                    distrInCheckLot.setDtlId(detail.getId());
                    distrInCheckLot.setCheckId(distr.getId());
                    distrInCheckLot.setGoodsId(goodsId);
                    distrInCheckLot.setGoodsCode(goods.getCode());
                    distrInCheckLot.setGoodsName(goods.getName());

                    distrInCheckLot.setLotNumber(lotNumberVO.getLotNumber());
                    distrInCheckLot.setProductDate(lotNumberVO.getProductDate());
                    distrInCheckLot.setValidDate(lotNumberVO.getValidDate());

                    distrInCheckLot.setTestReportIds(lotNumberVO.getTestReportIds());
                    if(lotNumberVO.getCheckProjectIds() == null){
                        throw new BusinessException("检验项目不能为空");
                    }
                    distrInCheckLot.setCheckProjectIds(lotNumberVO.getCheckProjectIds());

                    distrInCheckLot.setReceiveQuantity(lotNumberVO.getReceiveQuantity());
                    distrInCheckLot.setSamplingQuantity(lotNumberVO.getSamplingQuantity());
                    distrInCheckLot.setQualifiedQuantity(lotNumberVO.getQualifiedQuantity());
                    distrInCheckLot.setUnqualifiedQuantity(lotNumberVO.getUnqualifiedQuantity());
                    if(lotNumberVO.getConclusionIds() == null){
                        throw new BusinessException("验收结论不能为空");
                    }
                    distrInCheckLot.setConclusionIds(lotNumberVO.getConclusionIds());
                    distrInCheckLot.setUnqualifiedReasonIds(lotNumberVO.getUnqualifiedReasonIds());
                    distrInCheckLot.setMeasuresIds(lotNumberVO.getMeasuresIds());

                    distrInCheckLot.setUnclearQuantity(BigDecimal.ZERO);
                    distrInCheckLot.setClearQuantity(lotNumberVO.getReceiveQuantity());
                    distrInCheckLot.setLineNum(kk++);
                    distrInCheckLot.setStatus(status);
                    distrInCheckLotMapper.insertSelective(distrInCheckLot);

                }
            }


        }

        result.put("distrInCheck",distr);
        result.put("distrInCheckDetail",distrInCheckDetailList);
        return result;
    }

    /**
     * 配进入库
     * @param userVO
     * @param addInstorageVO
     * @return
     */
    private Map<String,Object> addDistrInStorage(UserVO userVO,AddInstorageVO addInstorageVO,Map<String,Object> map,ManageConfig manageConfig) throws Exception {
        int status = DistrInStatus.FINISHED;
        Integer chainType = userVO.getChainType();
        if(ChainType.Division.getCode() == chainType){
            status = DistrInStatus.WAIT_BILL;
        }
        DistrInCheck check = (DistrInCheck) map.get("distrInCheck");
        List<DistrInCheckDetail> distrInCheckDetailList = (List<DistrInCheckDetail>) map.get("distrInCheckDetail");
        Map<String,Object> result = new HashMap<>(2);
//        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getParentId() == 0L ? userVO.getEnterpriseId() : userVO.getParentId());
        // 配进入库
        DistrIn distr = (DistrIn) EntityUtils.reflectAddSetDefaultValue(DistrIn.class,userVO);
        distr.setInDate(addInstorageVO.getInDate());//入库日期
        distr.setOrderType(OrderRule.DISTR_IN.getType());
        //生成单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_IN.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        distr.setCode(code);
        //基础单据信息
        distr.setBaseOrderId(check.getId());
        distr.setBaseOrderCode(check.getCode());
        distr.setBaseOrderDate(check.getCheckDate());
        distr.setBaseOrderType(check.getOrderType());
        //配货单位
        distr.setDistrUnitId(userVO.getParentId());
        distr.setDistrUnitCode(userVO.getParentCode());
        distr.setDistrUnitName(userVO.getParentName());
        //出库单位
        Integer distrType = addInstorageVO.getDistrType();

        distr.setOutboundUnitId(check.getOutboundUnitId());
        distr.setOutboundUnitCode(check.getOutboundUnitCode());
        distr.setOutboundUnitName(check.getOutboundUnitName());

        //入库人员
        Long storageManId = addInstorageVO.getStorageManId();
        User user = userMapper.selectByPrimaryKey(storageManId);
        if(user == null) throw new BusinessException("入库人员不存在");
        distr.setStorageManId(storageManId);
        distr.setStorageManCode(user.getCode());
        distr.setStorageManName(user.getName());

        distr.setFlowCode(addInstorageVO.getFlowCode());


        //配货类型
        distr.setDistrType(distrType);

        //状态
        distr.setStatus(status);

        //数量合计
        distr.setQuantityTotal(addInstorageVO.getQuantityTotal());
        //品种合计
        distr.setVarietiesQuantity(addInstorageVO.getVarietiesQuantity());



        //金额计算

        //金额合计
        distr.setAmountTotal(addInstorageVO.getAmountTotal());
        //整单折扣
        distr.setWholeDiscount(addInstorageVO.getWholeDiscount());
        //整单优惠金额
        distr.setWholeDiscountAmount(addInstorageVO.getWholeDiscountAmount());
        //实际金额合计
        distr.setRealAmountTotal(addInstorageVO.getRealAmountTotal());
        //不含税金额合计
        distr.setNotaxRealAmountTotal(addInstorageVO.getNotaxRealAmountTotal());
        //税额合计
        distr.setTaxAmountTotal(addInstorageVO.getTaxAmountTotal());

        distrInMapper.insertSelective(distr);

        List<DistrInDetail> distrInDetailList = new ArrayList<>();
        //计算明细
        List<AddInstorageDtlVO> addInstorageDtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        int k = 1;
        for(AddInstorageDtlVO addInstorageDtlVO : addInstorageDtlVOList){
            DistrInDetail detail = (DistrInDetail) EntityUtils.reflectAddSetDefaultValue(DistrInDetail.class,userVO);

            detail.setInId(distr.getId());
            detail.setInDate(distr.getInDate());
            detail.setOrderType(OrderRule.DISTR_IN.getType());
            detail.setInCode(distr.getCode());

            Long goodsId = addInstorageDtlVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if(goods == null) throw new BusinessException("商品不存在");
//            if(goods.getSpecialDrug() != -1 && addInstorageVO.getSecondReceiverId() == null){
//                throw new BusinessException("第"+addInstorageDtlVO.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
//            }
            Optional<DistrInCheckDetail> distrFirst = distrInCheckDetailList.stream().filter(item -> goodsId.equals(item.getGoodsId())).findFirst();
            if(distrFirst.isPresent()){
                detail.setBaseOrderCode(check.getCode());
                detail.setBaseOrderId(check.getId());
                detail.setBaseOrderDate(check.getCheckDate());
                detail.setBaseOrderType(check.getOrderType());
                detail.setBaseOrderDtlId(distrFirst.get().getId());

                detail.setGoodsId(goodsId);
                detail.setGoodsCode(goods.getCode());
                detail.setGoodsGenericName(goods.getGenericName());
                detail.setGoodsName(goods.getName());
                detail.setGoodsPlace(goods.getPlace());
                detail.setGoodsSpecification(goods.getSpecification());
                detail.setUnitId(goods.getUnitId());
                detail.setUnitName(goods.getUnitName());
                detail.setApprovalNumber(goods.getApprovalNumber());
                detail.setManufacturer(goods.getManufacturer());
                detail.setManufacturerId(goods.getManufacturerId());
                detail.setDosageId(goods.getDosageId());
                detail.setDosageName(goods.getDosageName());

                BigDecimal lineDiscount = addInstorageDtlVO.getLineDiscount();
                //数量统计
                //到货数量
                detail.setQuantity(addInstorageDtlVO.getQuantity());
                detail.setUnitPrice(addInstorageDtlVO.getUnitPrice());
                detail.setLineDiscount(lineDiscount);

                detail.setAmount(addInstorageDtlVO.getAmount());
                detail.setWholeDiscount(addInstorageDtlVO.getWholeDiscount());
                detail.setLineDiscountAmount(addInstorageDtlVO.getLineDiscountAmount());
                detail.setRealPrice(addInstorageDtlVO.getUnitPrice());
                detail.setRealAmount(addInstorageDtlVO.getRealAmount());
                detail.setTaxAmount(addInstorageDtlVO.getTaxAmount());
                Long taxRateId = addInstorageDtlVO.getTaxRateId();
                GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(taxRateId);
                if(goodsTaxRate == null) throw new BusinessException("税率不存在");
                detail.setTaxRateId(taxRateId);
                detail.setTaxRate(goodsTaxRate.getTaxRate());
                detail.setNotaxRealAmount(addInstorageDtlVO.getNotaxRealAmount());
                detail.setNotaxRealPrice(addInstorageDtlVO.getNotaxRealPrice());
//                detail.setUnclearQuantity(BigDecimal.ZERO);
//                detail.setClearQuantity(addInstorageDtlVO.getQuantity());
                if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType() ){// 自营店
                    //已清数量 + 未清数量
                    detail.setUnclearQuantity(BigDecimal.ZERO);
                    detail.setClearQuantity(addInstorageDtlVO.getQuantity());

                } else if (ChainType.Division.getCode() == userVO.getChainType()){// 加盟店
                    //已清数量 + 未清数量
                    detail.setUnclearQuantity(addInstorageDtlVO.getQuantity());
                    detail.setClearQuantity(BigDecimal.ZERO);
                }
                detail.setLineNum(k++);
                detail.setStatus(status);
                distrInDetailMapper.insertSelective(detail);
                distrInDetailList.add(detail);

                //配进入库货位明细
                List<AddInstorageGoodsLotNumberVO> goodsLotNumberVOList = addInstorageDtlVO.getGoodsLotNumberVOList();
                int kk = 1;
                for (AddInstorageGoodsLotNumberVO lotNumberVO : goodsLotNumberVOList){
                    DistrInShelf distrInShelf = (DistrInShelf) EntityUtils.reflectAddSetDefaultValue(DistrInShelf.class,userVO);

                    distrInShelf.setDtlId(detail.getId());
                    distrInShelf.setInId(distr.getId());
                    distrInShelf.setGoodsId(goodsId);
                    distrInShelf.setGoodsCode(goods.getCode());
                    distrInShelf.setGoodsName(goods.getName());

                    distrInShelf.setLotNumber(lotNumberVO.getLotNumber());
                    distrInShelf.setProductDate(lotNumberVO.getProductDate());
                    distrInShelf.setValidDate(lotNumberVO.getValidDate());
                    distrInShelf.setTaxRateId(goodsTaxRate.getId());
                    distrInShelf.setTaxRate(goodsTaxRate.getTaxRate());

                    BigDecimal unqualifiedQuantity = lotNumberVO.getUnqualifiedQuantity();
                    BigDecimal qualifiedQuantity = lotNumberVO.getQualifiedQuantity();
                    Long qualifiedShelfId = lotNumberVO.getQualifiedShelfId();
                    Long unqualifiedShelfId = lotNumberVO.getUnqualifiedShelfId();


                    if(BigDecimal.ZERO.compareTo(unqualifiedQuantity) < 0 ){
                        if(unqualifiedShelfId != null && unqualifiedShelfId != 0){
                            distrInShelf.setId(null);
                            //存在不合格数量,不合格货位
                            //数量统计
                            distrInShelf.setQuantity(unqualifiedQuantity);
                            distrInShelf.setCanReturnQuantity(unqualifiedQuantity);
                            distrInShelf.setUnclearQuantity(unqualifiedQuantity);
                            distrInShelf.setClearQuantity(BigDecimal.ZERO);
                            if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType() ){// 自营店
                                //已清数量 + 未清数量
                                distrInShelf.setCanReturnQuantity(unqualifiedQuantity);
                                distrInShelf.setUnclearQuantity(BigDecimal.ZERO);
                                distrInShelf.setClearQuantity(unqualifiedQuantity);
                                distrInShelf.setUnverificationQuantity(BigDecimal.ZERO);
                                distrInShelf.setVerificationQuantity(unqualifiedQuantity);

                            } else if (ChainType.Division.getCode() == userVO.getChainType()){// 加盟店
                                //已清数量 + 未清数量
                                distrInShelf.setCanReturnQuantity(unqualifiedQuantity);
                                distrInShelf.setUnclearQuantity(unqualifiedQuantity);
                                distrInShelf.setClearQuantity(BigDecimal.ZERO);
                                distrInShelf.setUnverificationQuantity(unqualifiedQuantity);
                                distrInShelf.setVerificationQuantity(BigDecimal.ZERO);
                            }
                            //货位统计
                            distrInShelf.setShelfId(unqualifiedShelfId);
                            WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(unqualifiedShelfId);
                            if(warehouseShelf == null){
                                throw new BusinessException("货位不存在");
                            }
                            distrInShelf.setShelfName(warehouseShelf.getName());
                            distrInShelf.setShelfStatusDesc(inOutComponent.getShelfStatusDesc(unqualifiedShelfId));

                            distrInShelf.setLineNum(kk++);
                            distrInShelf.setStatus(status);

                            //计算金额
                            setCalculLotShelf(lotNumberVO,unqualifiedQuantity,goodsTaxRate.getTaxRate(),addInstorageDtlVO.getWholeDiscount(),
                                    addInstorageDtlVO.getLineDiscountAmount(),addInstorageDtlVO.getAmount(),detail.getUnitPrice(),lineDiscount);
                            distrInShelf.setUnitPrice(detail.getUnitPrice());
                            distrInShelf.setAmount(lotNumberVO.getAmount());
                            distrInShelf.setWholeDiscount(lotNumberVO.getWholeDiscount());
                            distrInShelf.setLineDiscount(lotNumberVO.getLineDiscount());
                            distrInShelf.setLineDiscountAmount(lotNumberVO.getLineDiscountAmount());
                            distrInShelf.setRealPrice(lotNumberVO.getRealPrice());
                            distrInShelf.setRealAmount(lotNumberVO.getRealAmount());
                            distrInShelf.setTaxRateId(detail.getTaxRateId());
                            distrInShelf.setNotaxRealPrice(lotNumberVO.getNotaxRealPrice());
                            distrInShelf.setNotaxRealAmount(lotNumberVO.getNotaxRealAmount());
                            distrInShelf.setTaxAmount(lotNumberVO.getTaxAmount());
                            distrInShelfMapper.insertSelective(distrInShelf);
                        } else {
                            throw new BusinessException("商品编码‘" + detail.getGoodsCode() + "’，商品名称‘" + detail.getGoodsName() +"'的门店不合格货位不能为空" );
                        }


                    }
                    if(BigDecimal.ZERO.compareTo(qualifiedQuantity) < 0 ){
                        if(qualifiedShelfId != null && qualifiedShelfId != 0){
                            distrInShelf.setId(null);
                            //存在合格数量,合格货位
                            //数量统计
                            distrInShelf.setQuantity(qualifiedQuantity);

//                            distrInShelf.setUnclearQuantity(qualifiedQuantity);
//                            distrInShelf.setClearQuantity(BigDecimal.ZERO);

                            if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType() ){// 自营店
                                //已清数量 + 未清数量
                                distrInShelf.setCanReturnQuantity(qualifiedQuantity);
                                distrInShelf.setUnclearQuantity(BigDecimal.ZERO);
                                distrInShelf.setClearQuantity(qualifiedQuantity);
                                distrInShelf.setUnverificationQuantity(BigDecimal.ZERO);
                                distrInShelf.setVerificationQuantity(qualifiedQuantity);

                            } else if (ChainType.Division.getCode() == userVO.getChainType()){// 加盟店
                                //已清数量 + 未清数量
                                distrInShelf.setCanReturnQuantity(qualifiedQuantity);
                                distrInShelf.setUnclearQuantity(qualifiedQuantity);
                                distrInShelf.setClearQuantity(BigDecimal.ZERO);
                                distrInShelf.setUnverificationQuantity(qualifiedQuantity);
                                distrInShelf.setVerificationQuantity(BigDecimal.ZERO);
                            }


                            //货位统计
                            distrInShelf.setShelfId(qualifiedShelfId);
//                            Map<String, Object> shelf = warehouseShelfMapper.getShelfInfoById(qualifiedShelfId);
//                            distrInShelf.setShelfName((String) shelf.get("name"));
//                            Integer jobArea = (Integer) shelf.get("jobArea");
                            WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(qualifiedShelfId);
                            if(warehouseShelf == null){
                                throw new BusinessException("货位不存在");
                            }
                            distrInShelf.setShelfName(warehouseShelf.getName());
                            distrInShelf.setShelfStatusDesc(inOutComponent.getShelfStatusDesc(qualifiedShelfId));

                            distrInShelf.setLineNum(kk++);
                            distrInShelf.setStatus(status);

                            //计算金额
                            setCalculLotShelf(lotNumberVO,qualifiedQuantity,goodsTaxRate.getTaxRate(),addInstorageDtlVO.getWholeDiscount(),
                                    addInstorageDtlVO.getLineDiscountAmount(),addInstorageDtlVO.getAmount(),detail.getUnitPrice(),lineDiscount);
                            distrInShelf.setUnitPrice(detail.getUnitPrice());
                            distrInShelf.setAmount(lotNumberVO.getAmount());
                            distrInShelf.setWholeDiscount(lotNumberVO.getWholeDiscount());
                            distrInShelf.setLineDiscount(lotNumberVO.getLineDiscount());
                            distrInShelf.setLineDiscountAmount(lotNumberVO.getLineDiscountAmount());
                            distrInShelf.setRealPrice(lotNumberVO.getRealPrice());
                            distrInShelf.setRealAmount(lotNumberVO.getRealAmount());
                            distrInShelf.setTaxRateId(detail.getTaxRateId());
                            distrInShelf.setNotaxRealPrice(lotNumberVO.getNotaxRealPrice());
                            distrInShelf.setNotaxRealAmount(lotNumberVO.getNotaxRealAmount());
                            distrInShelf.setTaxAmount(lotNumberVO.getTaxAmount());
                            distrInShelfMapper.insertSelective(distrInShelf);
                        } else {
                            throw new BusinessException("商品编码‘" + detail.getGoodsCode() + "’，商品名称‘" + detail.getGoodsName() +"'的门店合格货位不能为空" );
                        }

                    }
                }
            }
        }

        result.put("distrInStorage",distr);
        return result;
    }



    //公共的计算
    public void setCalcul(AddInstorageVO addInstorageVO) {

        //整单优惠金额
        BigDecimal wholeDiscountAmount = addInstorageVO.getWholeDiscountAmount();
        //整单折扣
        BigDecimal wholeDiscount = addInstorageVO.getWholeDiscount();
        //金额合计（整单优惠前的金额合计）
        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
        List<AddInstorageDtlVO> addInstorageDtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        for(AddInstorageDtlVO vo : addInstorageDtlVOList){
            /**.
             * 金额（整单优惠前金额）
             */
            BigDecimal amount = calcLineDiscount(vo);
            amountTotal = amountTotal.add(amount);
        }
        addInstorageVO.setAmountTotal(amountTotal);

        addInstorageVO.setVarietiesQuantity(addInstorageDtlVOList != null ? addInstorageDtlVOList.size() : 0);//品种合计
        //数量合计
        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计
        BigDecimal quantityTotal = BigDecimal.ZERO;
        for(AddInstorageDtlVO dtlVO : addInstorageDtlVOList){
            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(dtlVO.getTaxRateId());
            if (goodsTaxRate == null) {
                throw new BusinessException("没有该税率值，请核实税率ID");
            }
            // 计算金额
            BigDecimal quantity = dtlVO.getQuantity();//数量
            BigDecimal unitPrice = dtlVO.getUnitPrice();//单价
            BigDecimal lineDiscount = dtlVO.getLineDiscount();// 行折扣
            BigDecimal taxRate = goodsTaxRate.getTaxRate();//税率

            if(BigDecimal.ZERO.compareTo(quantity) >= 0) throw new BusinessException("商品数量不能为0");
//            if(BigDecimal.ZERO.compareTo(unitPrice) >= 0) throw new BusinessException("商品单价不能为0");

            CalculateResultModel calculateResultModel = CalculateComponent.getCalculateResult(quantity, unitPrice,
                    lineDiscount, wholeDiscount, wholeDiscountAmount, taxRate, amountTotal);

            // 设置计算结果
            dtlVO.setQuantity(quantity);
            dtlVO.setUnitPrice(unitPrice);
            dtlVO.setLineDiscount(lineDiscount);
            dtlVO.setWholeDiscount(wholeDiscount);
            dtlVO.setLineDiscountAmount(calculateResultModel.getLineRoundOff());
            dtlVO.setNotaxRealAmount(calculateResultModel.getNotaxAmount());// 不含税金额
            dtlVO.setNotaxRealPrice(calculateResultModel.getNotaxPrice());// 不含税实际单价
            dtlVO.setRealAmount(calculateResultModel.getRealAmount());// 实际金额
            dtlVO.setRealPrice(calculateResultModel.getRealPrice());// 实际单价
            dtlVO.setTaxAmount(calculateResultModel.getTaxAmount());// 税额
            dtlVO.setAmount(calculateResultModel.getAmount());

            // 主表的计算
            quantityTotal = quantityTotal.add(quantity);
            realAmountTotal = realAmountTotal.add(calculateResultModel.getRealAmount());
            notaxRealAmountTotal = notaxRealAmountTotal.add(calculateResultModel.getNotaxAmount());// 不含税金额总额
            taxAmountTotal = taxAmountTotal.add(calculateResultModel.getTaxAmount());

        }
        addInstorageVO.setQuantityTotal(quantityTotal);
        addInstorageVO.setAmountTotal(amountTotal);
        addInstorageVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        addInstorageVO.setTaxAmountTotal(taxAmountTotal);
        addInstorageVO.setRealAmountTotal(realAmountTotal);


    }

    private void setCalculLotShelf(AddInstorageGoodsLotNumberVO lotNumberVO,BigDecimal quantity,BigDecimal taxRate,BigDecimal wholeDiscount,BigDecimal wholeDiscountAmount,
                    BigDecimal amountTotal,BigDecimal unitPrice,BigDecimal lineDiscount){
        // 计算金额
//        BigDecimal lineDiscount = new BigDecimal(100);// 行折扣


        CalculateResultModel calculateResultModel = CalculateComponent.getCalculateResult(quantity, unitPrice,
                lineDiscount, wholeDiscount, wholeDiscountAmount, taxRate, amountTotal);
        lotNumberVO.setUnitPrice(unitPrice);
        lotNumberVO.setLineDiscount(lineDiscount);
        lotNumberVO.setWholeDiscount(wholeDiscount);
        lotNumberVO.setLineDiscountAmount(calculateResultModel.getLineRoundOff());
        lotNumberVO.setNotaxRealAmount(calculateResultModel.getNotaxAmount());// 不含税金额
        lotNumberVO.setNotaxRealPrice(calculateResultModel.getNotaxPrice());// 不含税实际单价
        lotNumberVO.setRealAmount(calculateResultModel.getRealAmount());// 实际金额
        lotNumberVO.setRealPrice(calculateResultModel.getRealPrice());// 实际单价
        lotNumberVO.setTaxAmount(calculateResultModel.getTaxAmount());// 税额
        lotNumberVO.setAmount(calculateResultModel.getAmount());

    }

    /**
     * 获得优惠前金额
     * @param detailVO
     * @return
     */
    private BigDecimal calcLineDiscount(AddInstorageDtlVO detailVO){
        BigDecimal amountByQuantityAndPriceAndLineDiscount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
                detailVO.getQuantity()
                , detailVO.getUnitPrice()
                , detailVO.getLineDiscount()
        );

        return amountByQuantityAndPriceAndLineDiscount.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
















}
