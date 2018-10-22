package com.rograndec.feijiayun.chain.common.component;


import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.auth.constant.Menu;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierBusinessMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierSalerMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierVarietiesMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.basic.supplier.exception.SupplierBizException;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfDefVO;
import com.rograndec.feijiayun.chain.business.common.vo.AccountingPeriodValidity;
import com.rograndec.feijiayun.chain.business.common.vo.StockLockShelfVO;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrAuditType;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.*;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.*;
import com.rograndec.feijiayun.chain.business.distr.branch.exception.DistrInReturnBizException;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.RequsetDistrReturnDtlSaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.RequsetDistrReturnInSaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationDtlVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationSlfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer.DistrReturnInDetailParam;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer.DistrReturnInParam;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer.DistrReturnInShelfParam;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo.PaymentInvoiceGoodsVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.exception.PrepayInvoiceBizException;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PerpayInvoiceCalcAmountParamVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceInStoreResponseVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodMapper;
import com.rograndec.feijiayun.chain.business.finance.set.period.entity.AccountingPeriod;
import com.rograndec.feijiayun.chain.business.finance.set.period.entity.AccountingPeriodDetail;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PrepayInvoiceGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.SpecialPriceGoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.SpecialPriceStrategyMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.SpecialPriceGoods;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.SpecialPriceStrategy;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.keytable.dao.*;
import com.rograndec.feijiayun.chain.business.keytable.entity.*;
import com.rograndec.feijiayun.chain.business.keytable.vo.AvgCostVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageDtlVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageVO;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectGoodsVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.TestReportIdsVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.SupplierVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnModifyRecord;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutDetail;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutOther;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutShelf;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.PurchaseReturnDetailOutVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.PurchaseReturnOutOtherVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.RequestCheckVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.RequestPurchaseReturnOutInfoVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.dao.PrescriptionRegisterMapper;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.storage.storehouse.dao.TemperatureHumidityDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.*;
import com.rograndec.feijiayun.chain.common.deleteValid.DeleteValidSerivce;
import com.rograndec.feijiayun.chain.common.model.CalculateResultModel;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.*;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @ClassName: CommonComponent
 * @Description: 通用组件
 * @date 2017年10月8日 下午7:43:38
 */
@Component
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class CommonComponent implements DeleteValidSerivce {


    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private CostMapper costMapper;

    @Autowired
    private ProfitMapper profitMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockLockRecordMapper stockLockRecordMapper;

    @Autowired
    private CostLockRecordMapper costLockRecordMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;
    @Autowired
    private SysActionMapper sysActionMapper;

    @Autowired
    private SysRoleActionMapper sysRoleActionMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private UserAdministrationMapper userAdministrationMapper;

    @Autowired
    private PickOrderMapper pickOrderMapper;
    @Autowired
    private PickDetailMapper pickDetailMapper;
    @Autowired
    private PickShelfMapper pickShelfMapper;

    @Autowired
    private TemperatureHumidityDetailMapper temperatureHumidityDetailMapper;
    @Autowired
    private ManageConfigService manageConfigService;
    @Autowired
    private DistrInMapper distrInMapper;
    @Autowired
    private DistrInReturnMapper distrInReturnMapper;
    @Autowired
    private DistrInDetailMapper distrInDetailMapper;
    @Autowired
    private DistrInShelfMapper distrInShelfMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierBusinessMapper supplierBusinessMapper;
    @Autowired
    private DistrInReturnDetailMapper distrInReturnDetailMapper;
    @Autowired
    private OrderCodeComponent orderCodeComponent;


    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DistrReturnReceiveMapper distrReturnReceiveMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private DistrReturnNoticeDetailMapper distrReturnNoticeDetailMapper;

    @Autowired
    private DistrReturnReceiveDetailMapper distrReturnReceiveDetailMapper;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private DistrComponent distrComponent;

    @Autowired
    private DistrReturnCheckMapper distrReturnCheckMapper;

    @Autowired
    private DistrReturnCheckDetailMapper distrReturnCheckDetailMapper;

    @Autowired
    private DistrReturnCheckLotMapper distrReturnCheckLotMapper;


    @Autowired
    private PurchaseCheckMapper purchaseCheckMapper;

    @Autowired
    private DistrReturnInMapper distrReturnInMapper;

    @Autowired
    private DistrReturnInDetailMapper distrReturnInDetailMapper;

    @Autowired
    private DistrReturnInShelfMapper distrReturnInShelfMapper;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;
    @Autowired
    private PurchaseReturnDetailMapper purchaseReturnDetailMapper;
    @Autowired
    private PurchaseReturnOtherMapper purchaseReturnOtherMapper;
    @Autowired
    private SupplierSalerMapper supplierSalerMapper;
    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;
    @Autowired
    private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;
    @Autowired
    private LotNumberMapper lotNumberMapper;
    @Autowired
    private GoodsComponent goodsComponent;
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;
    @Autowired
    private PurchaseReturnModifyRecordMapper purchaseReturnModifyRecordMapper;
    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private DistrReqPlanMapper distrReqPlanMapper;
    @Autowired
    private DistrReqPlanDetailMapper distrReqPlanDetailMapper;

    @Autowired
    private PurchaseReturnOutMapper purchaseReturnOutMapper;
    @Autowired
    private PurchaseReturnOutDetailMapper purchaseReturnOutDetailMapper;
    @Autowired
    private PurchaseReturnOutShelfMapper purchaseReturnOutShelfMapper;
    @Autowired
    private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;
    @Autowired
    private PurchaseReturnOutOtherMapper purchaseReturnOutOtherMapper;

    @Autowired
    private SupplierVarietiesMapper supplierVarietiesMapper;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private DistrSendMapper distrSendMapper;
    @Autowired
    private DistrSendDetailMapper distrSendDetailMapper;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private DistrLackMapper distrLackMapper;
    @Autowired
    private DistrLackDetailMapper distrLackDetailMapper;

    @Autowired
    private DistrOutShelfMapper distrOutShelfMapper;

    @Autowired
    private DistrOutMapper distrOutMapper;

    @Autowired
    private DistrOutDetailMapper distrOutDetailMapper;

    @Autowired
    private DistrInNoticeMapper distrInNoticeMapper;
    @Autowired
    private DistrInNoticeDetailMapper distrInNoticeDetailMapper;
    @Autowired
    private DistrInReturnOutMapper distrInReturnOutMapper;

    @Autowired
    private AccountingPeriodMapper accountingPeriodMapper;

    @Autowired
    private AccountingPeriodDetailMapper accountingPeriodDetailMapper;
    @Autowired
    private FinanceComponent financeComponent;

    @Autowired
    private PrescriptionRegisterMapper prescriptionRegisterMapper;

    @Autowired
    private SpecialPriceGoodsMapper specialPriceGoodsMapper;

    @Autowired
    private SpecialPriceStrategyMapper specialPriceStrategyMapper;

    /**
     * @param @param  parentId 总部ID
     * @param @param  enterpriseId 分店ID
     * @param @param  goodsId 商品ID
     * @param @param  quantity 出库数量
     * @param @return 设定文件
     * @return BigDecimal 返回类型 配送价格
     * @throws
     * @Title: getDistrPrice
     * @Description: 获取配送价格
     */
    public BigDecimal getDistrPrice(Long parentId, Long enterpriseId, Long goodsId, List<Long> lotIds, BigDecimal quantity) {
        BigDecimal distrPrice = BigDecimal.ZERO;
        EnterpriseBusiness enterpriseBusiness = getEnterpriseBusiness(enterpriseId);
        Integer distrPriceType = enterpriseBusiness.getDistrPriceType() == null ? 0 : enterpriseBusiness.getDistrPriceType();
        if (distrPriceType.equals(DistrPriceType.PRICE_LIST.getCode())) {
            Long distrPriceOrderId = enterpriseBusiness.getDistrPriceOrderId();
            PriceOrderDetail priceOrderDetail = getPriceOrderDetail(parentId, distrPriceOrderId, goodsId);
            if(priceOrderDetail == null){
                throw new BusinessException("请到【门店信息】- 业务信息页签检查配送价格清单设置是否正确！");
            }
            distrPrice = priceOrderDetail.getDistrPrice() == null ? BigDecimal.ZERO : priceOrderDetail.getDistrPrice();
        } else if (distrPriceType.equals(DistrPriceType.COST_PRICE.getCode())) {
            // 根据先进先出原则获取商品的成本单价
            distrPrice = getCostPriceWithLotIdList(parentId, goodsId, lotIds, quantity);
        } else if (distrPriceType.equals(DistrPriceType.COST_PLUS.getCode())) {
            BigDecimal addRate = enterpriseBusiness.getAddRate() == null ? BigDecimal.ZERO : enterpriseBusiness.getAddRate();
            BigDecimal costPrice = getCostPriceWithLotIdList(parentId, goodsId, lotIds, quantity);
            distrPrice = costPrice.multiply(addRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)).setScale(6, RoundingMode.HALF_UP);
        }
        return distrPrice;
    }


    /**
     * 从价格清单获取价格清单明细
     *
     * @param enterpriseId      企业ID
     * @param distrPriceOrderId 价格清单ID
     * @param goodsId           商品ID
     * @return
     */
    public PriceOrderDetail getPriceOrderDetail(Long enterpriseId, Long distrPriceOrderId, Long goodsId) {
        PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(enterpriseId, distrPriceOrderId, goodsId);
        return priceOrderDetail;
    }

    /**
     * 获取分店业务子表对象
     *
     * @param branchId
     * @return
     */
    public EnterpriseBusiness getEnterpriseBusiness(Long branchId) {
        return enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(branchId);
    }

    /**
     * @param @param  enterpriseId 企业ID
     * @param @param  goodsId 商品ID
     * @param @param  quantity 出库数量
     * @param @return 设定文件
     * @return BigDecimal 返回类型 成本价
     * @throws
     * @Title: getCostPrice
     * @Description: 根据先进先出原则获取成本价
     */
    public BigDecimal getCostPrice(Long enterpriseId, Long goodsId, BigDecimal quantity) {
        BigDecimal costPrice = BigDecimal.ZERO;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("goodsId", goodsId);
        List<Cost> costList = costMapper.selectByParamMapWithFIFO(paramMap);
        costPrice = calculateCostPrice(quantity, costList);
        return costPrice;
    }

    public BigDecimal getCostPrice4GoodsIdDesc(Long enterpriseId, Long goodsId, BigDecimal quantity) {
        BigDecimal costPrice = BigDecimal.ZERO;
        Cost costList = costMapper.selectByGoodsIdAndEnterpise(enterpriseId, goodsId);
        costPrice = calculateCostPrice(quantity, Arrays.asList(costList));
        return costPrice;
    }

    private BigDecimal calculateCostPrice(BigDecimal quantity, List<Cost> costList) {
        BigDecimal costPrice = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(costList)) {
            BigDecimal tmpCostAmountTotal = BigDecimal.ZERO;
            BigDecimal tmpQuantityTotal = BigDecimal.ZERO;
            for (Cost cost : costList) {
                if (null == cost) {
                    continue;
                }
                if (cost.getUsableQuantity().compareTo(quantity) >= 0) {
                    tmpCostAmountTotal = cost.getRealPrice().multiply(quantity).add(tmpCostAmountTotal);
                    tmpQuantityTotal = quantity.add(tmpQuantityTotal);
                    quantity = BigDecimal.ZERO;
                } else {
                    tmpCostAmountTotal = cost.getRealPrice().multiply(cost.getUsableQuantity()).add(tmpCostAmountTotal);
                    tmpQuantityTotal = cost.getUsableQuantity().add(tmpQuantityTotal);
                    quantity = quantity.subtract(cost.getUsableQuantity());
                }
                if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            if (tmpQuantityTotal.compareTo(BigDecimal.ZERO) > 0) {
                costPrice = tmpCostAmountTotal.divide(tmpQuantityTotal, 6, RoundingMode.HALF_UP);
            }
        }
        return costPrice;
    }

    /**
     * 获取成本价（通过企业ID，商品ID，批号ID集合获取平均成本价）
     *
     * @param enterpriseId 企业ID
     * @param goodsId      商品ID
     * @param lotId        批号集合
     * @param quantity     出库数量
     * @return
     */
    public BigDecimal getCostPriceWithLotId(Long enterpriseId, Long goodsId, Long lotId, BigDecimal quantity) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("goodsId", goodsId);
        paramMap.put("lotId", lotId);
        List<Cost> costList = costMapper.selectByParamMapWithFIFO(paramMap);
        BigDecimal costPrice = calculateCostPrice(quantity, costList);
        return costPrice;
    }

    public BigDecimal getCostPriceWithLotIdList(Long enterpriseId, Long goodsId, List<Long> lotIds, BigDecimal quantity) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("goodsId", goodsId);
        if (CollectionUtils.isNotEmpty(lotIds)) {
            paramMap.put("lotIds", lotIds);
        }
        List<Cost> costList = costMapper.selectByParamMapWithFIFO(paramMap);
        BigDecimal costPrice = calculateCostPrice(quantity, costList);
        return costPrice;
    }


    /**
     * 获取不含税单价（通过企业ID,商品ID，批次号）
     *
     * @param enterpriseId 企业ID
     * @param goodsId      商品ID
     * @param lotId        批次号
     * @param quantity     出库数量
     * @return
     */
    public BigDecimal getNoTaxPriceWithLotId(Long enterpriseId, Long goodsId, Long lotId, BigDecimal quantity) {
        BigDecimal noTaxPrice = BigDecimal.ZERO;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("goodsId", goodsId);
        paramMap.put("lotId", lotId);
        List<Cost> costList = costMapper.selectByParamMapWithFIFO(paramMap);
        if (CollectionUtils.isNotEmpty(costList)) {
            BigDecimal tmpCostAmountTotal = BigDecimal.ZERO;
            BigDecimal tmpQuantityTotal = BigDecimal.ZERO;
            BigDecimal tmpQuantity = quantity;// 临时未配数量
            boolean calculateAvgPriceFlag = false;
            for (Cost cost : costList) {
                BigDecimal usableQuantity = cost.getUsableQuantity();// 可用数量
                if (usableQuantity.compareTo(quantity) >= 0) {
                    noTaxPrice = cost.getNotaxRealPrice();
                    break;
                } else {
                    tmpCostAmountTotal = cost.getUsableRealAmout().add(tmpCostAmountTotal);
                    tmpQuantityTotal = cost.getUsableQuantity().add(tmpQuantityTotal);
                    tmpQuantity = tmpQuantity.subtract(cost.getUsableQuantity());
                    calculateAvgPriceFlag = true;
                }
                if (tmpQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            if (calculateAvgPriceFlag && tmpQuantityTotal.compareTo(BigDecimal.ZERO) > 0) {
                noTaxPrice = tmpCostAmountTotal.divide(tmpQuantityTotal, 6, RoundingMode.HALF_UP);
            }
        }
        return noTaxPrice;
    }

    /**
     * @param @param  enterpriseId 企业ID
     * @param @param  goodsId 商品ID
     * @param @param  lotIds 批号ID集合
     * @param @return 设定文件
     * @return List<Cost> 返回类型 成本列表
     * @throws
     * @Title: getCostList
     * @Description: 根据企业ID、商品ID、批号ID集合获取成本列表
     */
    public List<Cost> getCostList(Long enterpriseId, Long goodsId, List<Long> lotIds) {
        List<Cost> costList = new ArrayList<Cost>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("goodsId", goodsId);
        paramMap.put("lotIds", lotIds);
        costList = costMapper.selectByParamMapWithFIFO(paramMap);
        return costList;
    }

    /**
     * 按照先进先出获取平均成本价和平均不含税成本价
     * @param enterpriseId
     * @param goodsId
     * @param lotIds
     * @param quantity
     * @return
     */
    public AvgCostVO getAvgCostVO(Long enterpriseId, Long goodsId, List<Long> lotIds, BigDecimal quantity) {
        AvgCostVO avgCostVO = new AvgCostVO();
        List<Cost> costList = getCostList(enterpriseId, goodsId, lotIds);
        BigDecimal costPrice = BigDecimal.ZERO;
        BigDecimal notaxCostPrice = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(costList)) {
            BigDecimal tmpCostAmountTotal = BigDecimal.ZERO;
            BigDecimal tmpNotaxCostAmountTotal = BigDecimal.ZERO;
            BigDecimal tmpQuantityTotal = BigDecimal.ZERO;
            for (Cost cost : costList) {
                if (null == cost) {
                    continue;
                }
                if (cost.getUsableQuantity().compareTo(quantity) >= 0) {
                    tmpCostAmountTotal = cost.getRealPrice().multiply(quantity).add(tmpCostAmountTotal);
                    tmpNotaxCostAmountTotal = cost.getNotaxRealPrice().multiply(quantity).add(tmpNotaxCostAmountTotal);
                    tmpQuantityTotal = quantity.add(tmpQuantityTotal);
                    quantity = BigDecimal.ZERO;
                } else {
                    tmpCostAmountTotal = cost.getRealPrice().multiply(cost.getUsableQuantity()).add(tmpCostAmountTotal);
                    tmpNotaxCostAmountTotal = cost.getNotaxRealPrice().multiply(cost.getUsableQuantity()).add(tmpNotaxCostAmountTotal);
                    tmpQuantityTotal = cost.getUsableQuantity().add(tmpQuantityTotal);
                    quantity = quantity.subtract(cost.getUsableQuantity());
                }
                if (quantity.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            if (tmpQuantityTotal.compareTo(BigDecimal.ZERO) > 0) {
                costPrice = tmpCostAmountTotal.divide(tmpQuantityTotal, 6, RoundingMode.HALF_UP);
                notaxCostPrice = tmpNotaxCostAmountTotal.divide(tmpQuantityTotal,6,RoundingMode.HALF_UP);
                avgCostVO.setAvgCostPrice(costPrice);
                avgCostVO.setAvgNotaxCostPrice(notaxCostPrice);
            }else{
                avgCostVO.setAvgCostPrice(BigDecimal.ZERO);
                avgCostVO.setAvgNotaxCostPrice(BigDecimal.ZERO);
            }
        }
        return avgCostVO;
    }

    /**
     * <根据批号集合,商品id,企业id获取货位信息>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/10 15:39
     */
    public List<GoodsInfoStockShelfVO> getGoodsInfoStockShelfVOList(List<Long> lotIds, Long goodsId, Long enterpriseId) {
        Map map = new HashMap();
        map.put("lotIds", lotIds);
        map.put("goodsId", goodsId);
        map.put("enterpriseId", enterpriseId);
        return stockMapper.getGoodsInfoStockShelfVOList(map);
    }

    /**
     * <商品id,企业id,获取合格品货位信息>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/10 15:39
     */
    public List<GoodsInfoStockShelfVO> getQualifiedGoodsInfoShelf(Long goodsId, Long enterpriseId) {
        Map map = new HashMap();
        map.put("goodsId", goodsId);
        map.put("enterpriseId", enterpriseId);
        return stockMapper.getQualifiedGoodsInfoShelf(map);
    }


    /**
     * 商品出库选择默认货位信息
     *
     * @param goodsId  商品id
     * @param eId      企业id
     * @param lotId    批号id
     * @param quantity 数量
     * @return
     */
    public List<WarehouseShelfDefVO> getDefShef(Long goodsId, Long eId, Long lotId, BigDecimal quantity) {

        Map<Long, BigDecimal> shelfIds = new HashMap<>();

        Map<String, Object> map = new HashMap();
        map.put("goodsId", goodsId);
        map.put("lotId", lotId);
        map.put("enterpriseId", eId);

        List<Stock> defShelfs = stockMapper.selectByParamMap(map);

        /**
         * 先进行可以数量进行排序
         */
        TreeMap<BigDecimal, Stock> defShelfsMapPositive = new TreeMap<>();
        TreeMap<BigDecimal, Stock> defShelfsMapNegative = new TreeMap<>(new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal o1, BigDecimal o2) {
                //降序排序
                return o2.compareTo(o1);
            }
        });

        defShelfs.stream().forEach(defShelf -> {
            BigDecimal subtract = defShelf.getUsableQuantity().subtract(quantity);

            if (subtract.compareTo(new BigDecimal(0)) >= 0) {
                /**
                 * 取正整数,如果为负数表示,该库存信息不足以扣除需要出库的库存
                 */
                defShelfsMapPositive.put(subtract, defShelf);
            } else {
                /**
                 * 取负数
                 */
                defShelfsMapNegative.put(subtract, defShelf);
            }


        });

        /**
         * 先选择可用数量符合或者大于当前出库数量的货位
         */
        if (!org.springframework.util.CollectionUtils.isEmpty(defShelfsMapPositive)) {

            BigDecimal[] bArray = new BigDecimal[defShelfsMapPositive.keySet().size()];

            defShelfsMapPositive.keySet().toArray(bArray);
            BigDecimal firstKey = bArray[0];
            Stock stock = defShelfsMapPositive.get(firstKey);
            shelfIds.put(stock.getShelfId(), quantity);

        } else {

            BigDecimal q = BigDecimal.ZERO;

            for (Map.Entry<BigDecimal, Stock> se : defShelfsMapNegative.entrySet()) {
                Stock value = se.getValue();

                BigDecimal usableQuantity = value.getUsableQuantity();

                q = usableQuantity.subtract(quantity);

                if (q.compareTo(BigDecimal.ZERO) >= 0) {

                    BigDecimal qq = new BigDecimal(q.toString());
                    shelfIds.put(value.getShelfId(), qq);
                    break;
                } else {
                    shelfIds.put(value.getShelfId(), usableQuantity);
                }

                q = BigDecimal.ZERO.subtract(q);
            }

        }

        List<Long> ids = new ArrayList<>();
        ids.addAll(shelfIds.keySet());

        List<WarehouseShelfDefVO> warehouseShelfDefVOs = new ArrayList<>();

        if (!org.springframework.util.CollectionUtils.isEmpty(ids)) {
            List<WarehouseShelf> warehouseShelves = warehouseShelfMapper.selectByIds(ids);

            warehouseShelfDefVOs = WarehouseShelfDefVO.getWarehouseShelfDefVOs(warehouseShelves, shelfIds, defShelfs);
        }


        return warehouseShelfDefVOs;

    }

    /**
     * @param @param enterpriseId 企业ID
     * @param @param parentId 上级企业ID
     * @param @param chainType 企业类型
     * @param @param goodsId 商品ID
     * @param @param shelfId 货位ID
     * @param @param shelfName 货位名称
     * @return void 返回类型
     * @throws
     * @Title: updateGoodsDefShelf
     * @Description: 更新商品默认货位
     */
    public void updateGoodsDefShelf(Long enterpriseId, Long parentId, Integer chainType, Long goodsId, Long shelfId, String shelfName, UserVO user) {
        SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(goodsId, enterpriseId);
        if (safetyStock != null && safetyStock.getId() != null) {
            Long defaultShelfId = safetyStock.getDefaultShelfId();
            if (defaultShelfId == null || defaultShelfId.equals(0L)) {
                safetyStock.setDefaultShelfId(shelfId);
                safetyStock.setDefaultShelfName(shelfName);
                safetyStock.setUpdateTime(new Date());
                safetyStockMapper.updateByPrimaryKeySelective(safetyStock);
            }
        } else {
            SafetyStock newSafetyStock = new SafetyStock();
            newSafetyStock.setEnterpriseId(enterpriseId);
            newSafetyStock.setParentId(parentId);
            newSafetyStock.setChainType(chainType);
            newSafetyStock.setGoodsId(goodsId);
            newSafetyStock.setDefaultShelfId(shelfId);
            newSafetyStock.setDefaultShelfName(shelfName);
            newSafetyStock.setInventoryUp(BigDecimal.ZERO);
            newSafetyStock.setInventoryDown(BigDecimal.ZERO);
            newSafetyStock.setSafetyStock(BigDecimal.ZERO);
            newSafetyStock.setStatus(EnableStatus.ENABLE.getStatus());
            newSafetyStock.setCreaterId(user.getUserId());
            newSafetyStock.setCreaterCode(user.getUserCode());
            newSafetyStock.setCreaterName(user.getUserName());
            newSafetyStock.setCreateTime(new Date());
            newSafetyStock.setModifierId(user.getUserId());
            newSafetyStock.setModifierCode(user.getUserCode());
            newSafetyStock.setModifierName(user.getUserName());
            newSafetyStock.setUpdateTime(new Date());
            safetyStockMapper.insertSelective(newSafetyStock);
        }
    }

    /**
     * @param @param  enterpriseId
     * @param @param  goodsId
     * @param @param  lotId
     * @param @param  outQty
     * @param @param  amount
     * @param @param  notaxAmount
     * @param @return 设定文件
     * @return SimpleProfitVO 返回类型
     * @throws
     * @Title: calculateProfitInfo
     * @Description: 计算出库利润、利润率、不含税利润、不含税利润率
     */
    public SimpleProfitVO calculateProfitInfo(Long enterpriseId, Long goodsId, Long lotId, BigDecimal outQty, BigDecimal amount, BigDecimal notaxAmount) {
        SimpleProfitVO vo = new SimpleProfitVO();
        List<Long> lotIds = new ArrayList<Long>();
        lotIds.add(lotId);
        List<Cost> costList = getCostList(enterpriseId, goodsId, lotIds);
        if (CollectionUtils.isNotEmpty(costList)) {
            // 出库成本金额
            BigDecimal costAmount = BigDecimal.ZERO;
            // 出库不含税成本金额
            BigDecimal notaxCostAmount = BigDecimal.ZERO;
            // 临时未配数量
            BigDecimal tmpQuantity = outQty;
            for (Cost cost : costList) {
                // 可用数量
                BigDecimal usableQuantity = cost.getUsableQuantity();
                if (usableQuantity.compareTo(outQty) >= 0) {
                    costAmount = cost.getRealPrice().multiply(outQty).setScale(2, RoundingMode.HALF_UP);
                    notaxCostAmount = cost.getNotaxRealPrice().multiply(outQty).setScale(2, RoundingMode.HALF_UP);
                    break;
                } else {
                    costAmount = cost.getUsableRealAmout().add(costAmount);
                    notaxCostAmount = cost.getNotaxRealAmount().add(notaxCostAmount);
                    tmpQuantity = tmpQuantity.subtract(cost.getUsableQuantity());
                }
                if (tmpQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            // 利润
            BigDecimal profit = amount.subtract(costAmount);
            // 利润率
            BigDecimal profitRate = BigDecimal.ZERO;
            if (amount.compareTo(BigDecimal.ZERO) != 0) {
                // 利润率
                profitRate = profit.divide(amount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
            }
            // 不含税利润
            BigDecimal notaxProfit = notaxAmount.subtract(notaxCostAmount);
            // 不含税利润率
            BigDecimal notaxProfitRate = BigDecimal.ZERO;
            if (notaxAmount.compareTo(BigDecimal.ZERO) != 0) {
                // 不含税利润率
                notaxProfitRate = notaxProfit.divide(notaxAmount, 4, RoundingMode.HALF_UP);
            }
            vo = SimpleProfitVO.build(profit, profitRate, notaxProfit, notaxProfitRate);
        } else {
            vo = SimpleProfitVO.build(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return vo;
    }

    /**
     * @param @param  saleShelf
     * @param @param  enterpriseId
     * @param @param  goodsId
     * @param @param  lotId
     * @param @param  outQty
     * @param @param  amount
     * @param @param  notaxAmount
     * @param @return 设定文件
     * @return SimpleProfitVO 返回类型
     * @throws
     * @Title: calculateProfitInfo
     * @Description: 计算销售入库利润、利润率、不含税利润、不含税利润率
     */
    public SimpleProfitVO calculateSaleReturnProfitInfo(SaleShelf saleShelf,
                                                        Long enterpriseId, Long goodsId, Long lotId, BigDecimal inQty,
                                                        BigDecimal realAmount, BigDecimal notaxRealAmount) {
        SimpleProfitVO vo = new SimpleProfitVO();
        List<Profit> proList = getProfitList(saleShelf.getId(), saleShelf.getSaleId(), saleShelf.getLineNum(), enterpriseId, goodsId, lotId);
        if (CollectionUtils.isNotEmpty(proList)) {
            // 入库成本金额
            BigDecimal costAmount = BigDecimal.ZERO;
            // 入库不含税成本金额
            BigDecimal notaxCostAmount = BigDecimal.ZERO;
            // 临时未配数量
            BigDecimal tmpQuantity = inQty;
            for (Profit fit : proList) {
                // 可用数量
                BigDecimal usableQuantity = fit.getQuantity();
                if (usableQuantity.compareTo(inQty) >= 0) {
                    costAmount = fit.getInRealPrice().multiply(inQty).setScale(2, RoundingMode.HALF_UP);
                    notaxCostAmount = fit.getInNotaxRealPrice().multiply(inQty).setScale(2, RoundingMode.HALF_UP);
                    break;
                } else {
                    costAmount = fit.getInRealAmount().add(costAmount);
                    notaxCostAmount = fit.getInNotaxRealAmount().add(notaxCostAmount);
                    tmpQuantity = tmpQuantity.subtract(fit.getQuantity());
                }
                if (tmpQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            // 利润
            BigDecimal profit = realAmount.subtract(costAmount);
            // 利润率
            BigDecimal profitRate = BigDecimal.ZERO;
            if (realAmount.compareTo(BigDecimal.ZERO) != 0) {
                // 利润率
                profitRate = profit.divide(realAmount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
            }
            // 不含税利润
            BigDecimal notaxProfit = notaxRealAmount.subtract(notaxCostAmount);
            // 不含税利润率
            BigDecimal notaxProfitRate = BigDecimal.ZERO;
            if (notaxRealAmount.compareTo(BigDecimal.ZERO) != 0) {
                // 不含税利润率
                notaxProfitRate = notaxProfit.divide(notaxRealAmount, 4, RoundingMode.HALF_UP);
            }
            vo = SimpleProfitVO.build(profit, profitRate, notaxProfit, notaxProfitRate);
        } else {
            vo = SimpleProfitVO.build(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return vo;
    }


    /**
     * @param @param  id 出库单据明细ID
     * @param @param  enterpriseId 企业ID
     * @param @param  goodsId 商品ID
     * @param @param  lotIds 批号ID集合
     * @param @return 设定文件
     * @return List<Cost> 返回类型 成本列表
     * @throws
     * @Title: getCostList
     * @Description: 根据企业ID、商品ID、批号ID集合获取成本列表
     */
    private List<Profit> getProfitList(Long detailId, Long saleId, Integer lineNum, Long enterpriseId,
                                       Long goodsId, Long lotId) {

        List<Profit> proList = new ArrayList<Profit>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("detailId", detailId);
        paramMap.put("id", saleId);
        paramMap.put("lineNum", lineNum);
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("goodsId", goodsId);
        paramMap.put("lotId", lotId);
        proList = profitMapper.selectByParamMapWithFIFO(paramMap);
        return proList;
    }

    /**
     * @param @param  enterpriseId 企业ID
     * @param @param  goodsId 商品ID
     * @param @param  taxRateType 税率类型
     * @param @return 设定文件
     * @return GoodsDefTaxRateVO 商品默认税率对象
     * @throws
     * @Title: getGoodsDefTaxRateInfo
     * @Description: 获取商品默认税率
     */
    public GoodsDefTaxRateVO getGoodsDefTaxRateInfo(Long enterpriseId, Long goodsId, int taxRateType) {

        GoodsDefTaxRateVO goodsDefTaxRateVO = new GoodsDefTaxRateVO();
        goodsDefTaxRateVO.setTaxRateType(taxRateType);

        // 判断企业类型，取总部的商品默认税率
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        Integer chainType = enterprise.getChainType();
        if (chainType != 0) {
            enterpriseId = enterprise.getParentId();
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("goodsId", goodsId);
        GoodsBusiness goodsBusiness = goodsBusinessMapper.selectByEnterpriseIdAndGoodsId(paramMap);
        if (goodsBusiness == null) {
            Long taxRateId = goodsTaxRateMapper.selectDefaultTaxRate();
            goodsDefTaxRateVO.setTaxRateType(taxRateType);
            goodsDefTaxRateVO.setTaxRateId(taxRateId);
            goodsDefTaxRateVO.setTaxRate(BigDecimal.ZERO);
            return goodsDefTaxRateVO;
        }

        if (TaxRateType.PURCHASE.getType() == taxRateType) {
            Long purchaseTaxRateId = goodsBusiness.getPurchaseTaxRateId();
            BigDecimal purchaseTaxRate = goodsBusiness.getPurchaseTaxRate();

            goodsDefTaxRateVO.setTaxRateId(purchaseTaxRateId);
            goodsDefTaxRateVO.setTaxRate(purchaseTaxRate);
        } else if (TaxRateType.SALE.getType() == taxRateType) {
            Long saleTaxRateId = goodsBusiness.getSaleTaxRateId();
            BigDecimal saleTaxRate = goodsBusiness.getSaleTaxRate();

            goodsDefTaxRateVO.setTaxRateId(saleTaxRateId);
            goodsDefTaxRateVO.setTaxRate(saleTaxRate);
        } else if (TaxRateType.DISTRIBUTION.getType() == taxRateType) {
            Long distrTaxRateId = goodsBusiness.getDistrTaxRateId();
            BigDecimal distrTaxRate = goodsBusiness.getDistrTaxRate();

            goodsDefTaxRateVO.setTaxRateId(distrTaxRateId);
            goodsDefTaxRateVO.setTaxRate(distrTaxRate);
        } else {
            goodsDefTaxRateVO.setTaxRateId(0L);
            goodsDefTaxRateVO.setTaxRate(BigDecimal.ZERO);
        }
        return goodsDefTaxRateVO;
    }


    /**
     * @param @param  department
     * @param @return 设定文件
     * @param userVO
     * @return Tree    返回类型
     * @throws
     * @author Leisu
     * @Title: structureTree
     * @Description: 组织集合构造树形结构
     */
    public List<Tree> structureTree(List<Department> department, UserVO userVO) {
        /**
         * 如果当前部门下有岗位就不能增加部门
         */
        List<Tree> listParent = new ArrayList<>();
        for (Department dp : department) {
            if (0 == dp.getParentDeptId()) {
                Tree t = new Tree();
                t.setData(dp);
                t.setId(dp.getId());
                t.setLabel(dp.getCode() + "-" + dp.getName());
                boolean flag = isLeaf(dp.getId(), department);
                t.setLeaf(flag);
                boolean positionFlag = hasNextPosition(dp, userVO);
                t.setHasPosition(positionFlag);
                /**
                 * 系统默认无法删除
                 */
                if (dp.getSysType() == 1) {
                    t.setNodeShowDelete(false);
                    t.setNodeShowUpdate(false);
                } else {
                    t.setNodeShowDelete(true);
                    t.setNodeShowUpdate(true);
                    //如果第一级不是叶子结点那么不能删除
                    if (!flag) {
                        t.setNodeShowDelete(false);
                    }
                    Boolean validFlag = valid(dp);
                    if (!validFlag) {
                        t.setNodeShowDelete(false);
                    }
                    /**
                     * 如果是分店的人员登陆进来只能看不能增删改
                     */
                    if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()) {
                        t.setNodeShowDelete(false);
                        t.setNodeShowUpdate(false);
                    }
                }
                listParent.add(t);
            }
        }
        for (Tree lp : listParent) {
            Department data = (Department) lp.getData();
            lp.setChildren(getChilds(data.getId(), department, userVO));
        }
        return listParent;
    }

    /**
     * 判断当前部门下是否有岗位
     */
    private boolean hasNextPosition(Department dp, UserVO userVO) {
        List<Position> p = positionMapper.selectPositionByEnterPriseIdAndDepartmentId(userVO.getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId(), dp.getId());
        if (p != null && p.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param @param  department
     * @param @return 设定文件
     * @return Tree    返回类型
     * @throws
     * @author Leisu
     * @Title: structureTree
     * @Description: 组织集合构造树形结构&& 树形结构没有删除修改节点
     */
    public List<ConnectTree> structureTreeWithoutDeleteAndUpdate(List<Department> department) {
        List<ConnectTree> listParent = new ArrayList<ConnectTree>();
        for (Department dp : department) {
            if (0 == dp.getParentDeptId()) {
                ConnectTree t = new ConnectTree();
                t.setData(dp);
                t.setValue(dp.getId());
                t.setLabel(dp.getCode() + "-" + dp.getName());
                boolean flag = isLeaf(dp.getId(), department);
                t.setLeaf(flag);
                if (dp.getSysType() == 1) {
                    t.setNodeShowDelete(false);
                    t.setNodeShowUpdate(false);
                } else {
                    t.setNodeShowDelete(false);
                    t.setNodeShowUpdate(false);
                }
                //如果第一级不是叶子结点那么不能删除
                if (!flag) {
                    t.setNodeShowDelete(false);
                }
                listParent.add(t);
            }
        }
        for (ConnectTree lp : listParent) {
            Department data = (Department) lp.getData();
            lp.setChildren(getChildWithOutDeleteAndUpdate(data.getId(), department));
        }
        return listParent;
    }

    private boolean isLeaf(Long id, List<Department> department) {
        for (Department dp : department) {
            if (id.equals(dp.getParentDeptId())) {
                return false;
            }
        }
        return true;
    }

    private List<ConnectTree> getChildWithOutDeleteAndUpdate(Long id, List<Department> department) {
        List<ConnectTree> childList = new ArrayList<ConnectTree>();
        for (Department dp : department) {
            if (dp.getParentDeptId().equals(id)) {
                ConnectTree t = new ConnectTree();
                t.setData(dp);
                t.setValue(dp.getId());
                t.setLabel(dp.getCode() + "-" + dp.getName());
                boolean flag = isLeaf(dp.getId(), department);
                t.setLeaf(flag);
                if (dp.getSysType() == 1) {
                    t.setNodeShowDelete(false);
                    t.setNodeShowUpdate(false);
                } else {
                    t.setNodeShowDelete(false);
                    t.setNodeShowUpdate(false);
                }
                //递归的过程中如果遇见不是叶子结点就不能删除
                if (!flag) {
                    t.setNodeShowDelete(false);
                }
                childList.add(t);
            }
        }
        for (ConnectTree tree : childList) {
            for (Department d : department) {
                Department dt = (Department) tree.getData();
                List<ConnectTree> child = new ArrayList<ConnectTree>();
                if (dt.getId().equals(d.getParentDeptId())) {
                    child = getChildWithOutDeleteAndUpdate(dt.getId(), department);
                    tree.setChildren(child);
                }
            }
        }
        return childList;
    }

    private List<Tree> getChilds(Long id, List<Department> department, UserVO userVO) {
        List<Tree> childList = new ArrayList<Tree>();
        for (Department dp : department) {
            if (dp.getParentDeptId().equals(id)) {
                Tree t = new Tree();
                t.setData(dp);
                t.setId(dp.getId());
                t.setLabel(dp.getCode() + "-" + dp.getName());
                boolean flag = isLeaf(dp.getId(), department);
                t.setLeaf(flag);
                boolean positionFlag = hasNextPosition(dp, userVO);
                t.setHasPosition(positionFlag);
                if (dp.getSysType() == 1) {
                    t.setNodeShowDelete(false);
                    t.setNodeShowUpdate(false);
                } else {
                    t.setNodeShowDelete(true);
                    t.setNodeShowUpdate(true);
                    //递归的过程中如果遇见不是叶子结点就不能删除
                    if (!flag) {
                        t.setNodeShowDelete(false);
                    }
                    Boolean validFlag = valid(dp);
                    if (!validFlag) {
                        t.setNodeShowDelete(false);
                    }
                    /**
                     * 如果当前登录人是分店的人，那么只能看不能增删改
                     */
                    if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()) {
                        t.setNodeShowUpdate(false);
                        t.setNodeShowDelete(false);
                    }
                }
                childList.add(t);
            }
        }
        for (Tree tree : childList) {
            for (Department d : department) {
                Department dt = (Department) tree.getData();
                List<Tree> child = new ArrayList<Tree>();
                if (dt.getId().equals(d.getParentDeptId())) {
                    child = getChilds(dt.getId(), department, userVO);
                    tree.setChildren(child);
                }
            }
        }
        return childList;
    }


    /**
     * 组装tree公共方法
     *
     * @param treeVOS
     * @return
     */
    public List<Tree> structureCommTree(List<? extends BaseTreeVO> treeVOS) {
        List<Tree> listParent = new ArrayList<Tree>();
        for (BaseTreeVO dp : treeVOS) {
            if (0 == dp.getParentId()) {
                Tree commTree = getCommTree(dp, treeVOS);
                listParent.add(commTree);
            }
        }
        for (Tree lp : listParent) {
            BaseTreeVO data = (BaseTreeVO) lp.getData();
            lp.setChildren(getCommChild(data.getId(), treeVOS));
        }
        return listParent;
    }

    private boolean isCommLeaf(Long id, List<? extends BaseTreeVO> treeVOS) {
        for (BaseTreeVO dp : treeVOS) {
            if (id.equals(dp.getParentId())) {
                return false;
            }
        }
        return true;
    }

    private List<Tree> getCommChild(Long id, List<? extends BaseTreeVO> treeVOS) {
        List<Tree> childList = new ArrayList<Tree>();
        for (BaseTreeVO dp : treeVOS) {
            if (dp.getParentId().equals(id)) {

                Tree commTree = getCommTree(dp, treeVOS);

                childList.add(commTree);
            }
        }
        for (Tree tree : childList) {
            for (BaseTreeVO d : treeVOS) {
                BaseTreeVO dt = (BaseTreeVO) tree.getData();
                List<Tree> child = new ArrayList<Tree>();
                if (dt.getId().equals(d.getParentId())) {
                    child = getCommChild(dt.getId(), treeVOS);
                    tree.setChildren(child);
                }
            }
        }
        return childList;
    }


    private Tree getCommTree(BaseTreeVO dp, List<? extends BaseTreeVO> treeVOS) {
        Tree t = new Tree();
        t.setData(dp);
        t.setId(dp.getId());
        t.setLabel(dp.getCode() + "-" + dp.getName());
        boolean flag = isCommLeaf(dp.getId(), treeVOS);
        t.setLeaf(flag);
        if (dp.getSysType() == 1) {
            t.setNodeShowDelete(false);
            t.setNodeShowUpdate(false);
        } else {
            t.setNodeShowDelete(true);
            t.setNodeShowUpdate(true);
        }
        //递归的过程中如果遇见不是叶子结点就不能删除
        if (!flag) {
            t.setNodeShowDelete(false);
        }

        return t;
    }

    /**
     * 更新商品最近入库价相关信息
     *
     * @param lastInPriceVO
     */
    public void updateLastPriceInfo(LastInPriceVO lastInPriceVO) {
        Long enterpriseId = lastInPriceVO.getEnterpriseId();
        Long parentId = lastInPriceVO.getParentId();
        Integer chainType = lastInPriceVO.getChainType();
        Long goodsId = lastInPriceVO.getGoodsId();
        Long supplierId = lastInPriceVO.getSupplierId();
        Long purTaxRateId = lastInPriceVO.getPurTaxRateId();
        BigDecimal purTaxRate = lastInPriceVO.getPurTaxRate();
        BigDecimal purPrice = lastInPriceVO.getPurPrice();
        Long inTaxRateId = lastInPriceVO.getInTaxRateId();
        BigDecimal inTaxRate = lastInPriceVO.getInTaxRate();
        BigDecimal inPrice = lastInPriceVO.getInPrice();

        PriceOrder priceOrder = null;
        Long priceOrderId = null;

        // 获取企业默认价格清单
        // 总部
        if (chainType == 0) {
            priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);
        } else {// 分店
            priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);
        }
        if (priceOrder != null) {
            priceOrderId = priceOrder.getId();
            PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(enterpriseId, priceOrderId, goodsId);
            if (priceOrderDetail != null) {
                priceOrderDetail.setSupplierId(supplierId);
                priceOrderDetail.setPurTaxRateId(purTaxRateId);
                priceOrderDetail.setPurTaxRate(purTaxRate);
                priceOrderDetail.setPurPrice(purPrice);
                priceOrderDetail.setInTaxRateId(inTaxRateId);
                priceOrderDetail.setInTaxRate(inTaxRate);
                priceOrderDetail.setInPrice(inPrice);
                priceOrderDetail.setUpdateTime(new Date());
                priceOrderDetailMapper.updateByPrimaryKeySelective(priceOrderDetail);
            }
        }
    }

    /**
     * 锁定库存，涉及单据有：
     * 购进退出单、配进退出单、配货单、配货出库
     * <p>
     * 购进退出单、配进退出单、配货单
     * 新增或者勾选采购入库单生成的购进退出出库单
     *
     * @param lockQtyArgVO
     */
    public void lockStockAndCost(LockQtyArgVO lockQtyArgVO) {
        // 企业ID
        Long enterpriseId = lockQtyArgVO.getEnterpriseId();
        // 上级企业ID
        Long parentId = lockQtyArgVO.getParentId();
        // 基础单据ID
        Long baseOrderId = lockQtyArgVO.getBaseOrderId();
        // 基础单据类型
        Integer baseOrderType = lockQtyArgVO.getBaseOrderType();
        // 基础单据编码
        String baseOrderCode = lockQtyArgVO.getBaseOrderCode();
        // 基础单据日期
        Date baseOrderDate = lockQtyArgVO.getBaseOrderDate();
        // 商品ID
        Long goodsId = lockQtyArgVO.getGoodsId();
        // 批号ID
        Long lotId = lockQtyArgVO.getLotId();
        UserVO user = lockQtyArgVO.getUser();
        BigDecimal quantity = lockQtyArgVO.getQuantity() == null ? BigDecimal.ZERO : lockQtyArgVO.getQuantity();

        Map<String, Object> paramMap = new HashMap<>();
        if (baseOrderType.equals(OrderRule.PURCHASE_RETURN.getType()) || baseOrderType.equals(OrderRule.DISTR_IN_RETURN.getType())) {
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("parentId", parentId);
            paramMap.put("goodsId", goodsId);
            paramMap.put("lotId", lotId);
        } else if (baseOrderType.equals(OrderRule.DISTR_ORDER.getType())) {
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("parentId", parentId);
            paramMap.put("goodsId", goodsId);
        } else if (baseOrderType.equals(OrderRule.PURCHASE_RETURN_OUT.getType()) || baseOrderType.equals(OrderRule.DISTR_OUT.getType())) {
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("parentId", parentId);
            paramMap.put("goodsId", goodsId);
            paramMap.put("lotId", lotId);
            paramMap.put("shelfId", lockQtyArgVO.getShelfId());
        }
        // 获取库存列表并生成多库存锁定记录

        List<Stock> stockList = new ArrayList<>();
        List<Cost> costList = new ArrayList<>();


        //配货单和配货出库 要锁定的是合格和非过期的库存 和 成本金额
        if (baseOrderType.equals(OrderRule.DISTR_ORDER.getType()) || baseOrderType.equals(OrderRule.DISTR_OUT.getType())) {
            stockList = stockMapper.selectQuantityByParamMapWithFIFO(paramMap);
            costList = costMapper.selectQuantityByParamMapWithFIFO(paramMap);
        } else {
            stockList = stockMapper.selectByParamMapWithFIFO(paramMap);
            costList = costMapper.selectByParamMapWithFIFO(paramMap);
        }

        doLockStock(enterpriseId, parentId, baseOrderId, baseOrderType, baseOrderCode, baseOrderDate, goodsId, user, quantity, stockList);
        // 获取成本列表并生成成本锁定记录
        doLockCost(enterpriseId, parentId, baseOrderId, baseOrderType, baseOrderCode, baseOrderDate, user, quantity, costList);

    }

    public void doLockCost(Long enterpriseId, Long parentId, Long baseOrderId, Integer baseOrderType, String baseOrderCode, Date baseOrderDate, UserVO user, BigDecimal quantity, List<Cost> costList) {
        if (CollectionUtils.isNotEmpty(costList)) {
            BigDecimal lockQuantity = quantity;
            for (Cost cost : costList) {
                BigDecimal usableQuantity = cost.getUsableQuantity();
                // 先获取直接满足出库数量的成本记录
                if (usableQuantity.compareTo(lockQuantity) >= 0) {
                    // 生成锁定记录
                    generateCostLockRecord(enterpriseId, parentId, baseOrderId, baseOrderType, baseOrderCode, baseOrderDate, lockQuantity, cost, user);
                    // 更新成本可用量
                    updateCostUasbaleQuantity(lockQuantity, cost);
                    lockQuantity = BigDecimal.ZERO;
                    break;
                } else {
                    BigDecimal costUsableQuantity = cost.getUsableQuantity() == null ? BigDecimal.ZERO : cost.getUsableQuantity();
                    if (costUsableQuantity.compareTo(BigDecimal.ZERO) == 0) {
                        continue;
                    }
                    // 生成锁定记录
                    generateCostLockRecord(enterpriseId, parentId, baseOrderId, baseOrderType, baseOrderCode, baseOrderDate, costUsableQuantity, cost, user);
                    // 更新成本可用量
                    updateCostUasbaleQuantity(costUsableQuantity, cost);
                    lockQuantity = lockQuantity.subtract(costUsableQuantity);
                }
                if (lockQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            if (lockQuantity.compareTo(BigDecimal.ZERO) > 0) {
                throw new BusinessException("成本可用量不足！");
            }
        }
    }

    private void updateCostUasbaleQuantity(BigDecimal quantity, Cost cost) {
        BigDecimal costUsableQuantity = cost.getUsableQuantity() == null ? BigDecimal.ZERO : cost.getUsableQuantity();
        cost.setUsableQuantity(costUsableQuantity.subtract(quantity));
        costMapper.updateByPrimaryKeySelective(cost);
    }

    private void generateCostLockRecord(Long enterpriseId, Long parentId, Long baseOrderId, Integer baseOrderType, String baseOrderCode, Date baseOrderDate, BigDecimal lockQuantity, Cost cost, UserVO user) {
        CostLockRecord costLockRecord = new CostLockRecord();
        costLockRecord.setEnterpriseId(enterpriseId);
        costLockRecord.setParentId(parentId);
        costLockRecord.setBaseOrderId(baseOrderId);
        costLockRecord.setBaseOrderType(baseOrderType);
        costLockRecord.setBaseOrderCode(baseOrderCode);
        costLockRecord.setBaseOrderDate(baseOrderDate);
        costLockRecord.setGoodsId(cost.getGoodsId());
        costLockRecord.setGoodsCode(cost.getGoodsCode());
        costLockRecord.setGoodsName(cost.getGoodsName());
        costLockRecord.setLotId(cost.getLotId());
        costLockRecord.setLotNum(cost.getLotNum());
        costLockRecord.setBatchId(cost.getBatchId());
        costLockRecord.setBatchNum(cost.getBatchNum());
        costLockRecord.setLockQuantity(lockQuantity);
        costLockRecord.setCreaterId(user.getUserId());
        costLockRecord.setCreaterCode(user.getUserCode());
        costLockRecord.setCreaterName(user.getUserName());
        costLockRecord.setCreateTime(new Date());


        // dongyang.du
        // 当前业务单据存在锁定记录，则累加数量
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("parentId", parentId);
        paramMap.put("baseOrderId", baseOrderId);
        paramMap.put("baseOrderCode", baseOrderCode);
        paramMap.put("goodsId", cost.getGoodsId());
        paramMap.put("lotId", cost.getLotId());
        paramMap.put("batchId", cost.getBatchId());

        List<CostLockRecord> costLockRecordList = costLockRecordMapper.selectByParamMap(paramMap);

        if (CollectionUtils.isEmpty(costLockRecordList)) {
            costLockRecordMapper.insertSelective(costLockRecord);
        } else {
            CostLockRecord updateCostLockRecord = costLockRecordList.get(0);
            updateCostLockRecord.setLockQuantity(updateCostLockRecord.getLockQuantity().add(lockQuantity));
            costLockRecordMapper.updateByPrimaryKeySelective(updateCostLockRecord);
        }
    }

    private void doLockStock(Long enterpriseId, Long parentId, Long baseOrderId, Integer baseOrderType, String baseOrderCode, Date baseOrderDate, Long goodsId, UserVO user, BigDecimal quantity, List<Stock> stockList) {
        if (CollectionUtils.isNotEmpty(stockList)) {
            BigDecimal lockQuantity = quantity;
            for (Stock stock : stockList) {
                BigDecimal usableQuantity = stock.getUsableQuantity();
                // 先获取直接满足出库数量的库存记录
                if (usableQuantity.compareTo(lockQuantity) >= 0) {
                    // 生成锁定记录
                    generateStockLockRecord(enterpriseId, parentId, baseOrderId, baseOrderType, baseOrderCode, baseOrderDate, goodsId, lockQuantity, stock, user);
                    // 更新库存锁定数量和可用量
                    updateStockLockQuantity(lockQuantity, stock);
                    lockQuantity = BigDecimal.ZERO;
                    break;
                } else {
                    BigDecimal stockUsableQuantity = stock.getUsableQuantity() == null ? BigDecimal.ZERO : stock.getUsableQuantity();
                    if (stockUsableQuantity.compareTo(BigDecimal.ZERO) == 0) {
                        continue;
                    }
                    // 生成锁定记录
                    generateStockLockRecord(enterpriseId, parentId, baseOrderId, baseOrderType, baseOrderCode, baseOrderDate, goodsId, stockUsableQuantity, stock, user);
                    // 更新库存锁定数量和可用量
                    updateStockLockQuantity(stockUsableQuantity, stock);
                    lockQuantity = lockQuantity.subtract(stockUsableQuantity);
                }
                if (lockQuantity.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            if (lockQuantity.compareTo(BigDecimal.ZERO) > 0) {
                throw new BusinessException("库存可用量不足！");
            }
        }
    }

    private void updateStockLockQuantity(BigDecimal quantity, Stock stock) {
        BigDecimal stockUsableQuantity = stock.getUsableQuantity() == null ? BigDecimal.ZERO : stock.getUsableQuantity();
        BigDecimal stockLockQuantity = stock.getLockQuantity() == null ? BigDecimal.ZERO : stock.getLockQuantity();
        stock.setLockQuantity(stockLockQuantity.add(quantity));
        stock.setUsableQuantity(stockUsableQuantity.subtract(quantity));
        stockMapper.updateByPrimaryKeySelective(stock);
    }

    private void generateStockLockRecord(Long enterpriseId, Long parentId, Long baseOrderId, Integer baseOrderType, String baseOrderCode,
                                         Date baseOrderDate, Long goodsId, BigDecimal quantity, Stock stock, UserVO user) {
        StockLockRecord stockLockRecord = new StockLockRecord();
        stockLockRecord.setEnterpriseId(enterpriseId);
        stockLockRecord.setParentId(parentId);
        stockLockRecord.setBaseOrderId(baseOrderId);
        stockLockRecord.setBaseOrderType(baseOrderType);
        stockLockRecord.setBaseOrderCode(baseOrderCode);
        stockLockRecord.setBaseOrderDate(baseOrderDate);
        stockLockRecord.setGoodsId(goodsId);
        stockLockRecord.setGoodsCode(stock.getGoodsCode());
        stockLockRecord.setGoodsName(stock.getGoodsName());
        stockLockRecord.setLotId(stock.getLotId());
        stockLockRecord.setLotNum(stock.getLotNum());
        stockLockRecord.setShelfId(stock.getShelfId());
        stockLockRecord.setShelfName(stock.getShelfName());
        stockLockRecord.setLockQuantity(quantity);
        stockLockRecord.setCreaterId(user.getUserId());
        stockLockRecord.setCreaterCode(user.getUserCode());
        stockLockRecord.setCreaterName(user.getUserName());
        stockLockRecord.setCreateTime(new Date());
        // dongyang.du
        // 当前业务单据存在锁定记录，则累加数量
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("parentId", parentId);
        paramMap.put("baseOrderId", baseOrderId);
        paramMap.put("baseOrderCode", baseOrderCode);
        paramMap.put("goodsId", goodsId);
        paramMap.put("lotId", stock.getLotId());
        paramMap.put("shelfId", stock.getShelfId());

        List<StockLockRecord> stockLockRecordList = stockLockRecordMapper.selectByParamMap(paramMap);
        if (CollectionUtils.isEmpty(stockLockRecordList)) {
            stockLockRecordMapper.insertSelective(stockLockRecord);
        } else {
            StockLockRecord updateLockRecord = stockLockRecordList.get(0);
            updateLockRecord.setLockQuantity(updateLockRecord.getLockQuantity().add(quantity));
            stockLockRecordMapper.updateByPrimaryKeySelective(updateLockRecord);
        }
    }


    /**
     * 解锁库存，涉及单据有：
     * 购进退出出库、配进退出出库、配货出库
     *
     * @param lockQtyArgVO
     */
    public void releaseStockAndCost(LockQtyArgVO lockQtyArgVO) {
        // 企业ID
        Long enterpriseId = lockQtyArgVO.getEnterpriseId();
        // 上级企业ID
        Long parentId = lockQtyArgVO.getParentId();
        Long baseOrderId = lockQtyArgVO.getBaseOrderId();
        // 基础单据类型
        Integer baseOrderType = lockQtyArgVO.getBaseOrderType();

        if (baseOrderId == null || baseOrderType == null) {
            throw new BusinessException("释放库存基本单据id或者type为空，baseOrderId = " + baseOrderId + ",baseOrderType = " + baseOrderType);
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("parentId", parentId);
        paramMap.put("baseOrderId", baseOrderId);
        paramMap.put("baseOrderType", baseOrderType);

        // 获取库存锁定记录并进行释放
        List<StockLockRecord> stockLockRecordList = stockLockRecordMapper.selectByParamMap(paramMap);
        doReleaseStock(enterpriseId, parentId, stockLockRecordList);
        // 获取成本锁定记录并进行释放
        List<CostLockRecord> costLockRecordList = costLockRecordMapper.selectByParamMap(paramMap);
        doReleaseCost(enterpriseId, parentId, costLockRecordList);
    }

    private void doReleaseCost(Long enterpriseId, Long parentId, List<CostLockRecord> costLockRecordList) {
        if (CollectionUtils.isNotEmpty(costLockRecordList)) {
            for (CostLockRecord costLockRecord : costLockRecordList) {
                // 清除成本锁定记录
                costLockRecordMapper.deleteByPrimaryKey(costLockRecord.getId());

                Map<String, Object> costParamMap = new HashMap<>();
                costParamMap.put("enterpriseId", enterpriseId);
                costParamMap.put("parentId", parentId);
//				costParamMap.put("baseOrderId", costLockRecord.getBaseOrderId());
//				costParamMap.put("baseOrderType", costLockRecord.getBaseOrderType());
                costParamMap.put("goodsId", costLockRecord.getGoodsId());
                costParamMap.put("lotId", costLockRecord.getLotId());
                costParamMap.put("batchId", costLockRecord.getBatchId());
                BigDecimal lockQuantity = costLockRecord.getLockQuantity();
                // 释放成本
                List<Cost> costList = costMapper.selectByParamMap(costParamMap);
                if (CollectionUtils.isNotEmpty(costList)) {
                    Cost cost = costList.get(0);
                    cost.setUsableQuantity(cost.getUsableQuantity().add(lockQuantity));
                    costMapper.updateByPrimaryKeySelective(cost);
                }
            }
        }
    }

    private void doReleaseStock(Long enterpriseId, Long parentId, List<StockLockRecord> stockLockRecordList) {
        if (CollectionUtils.isNotEmpty(stockLockRecordList)) {
            for (StockLockRecord stockLockRecord : stockLockRecordList) {
                // 清除库存锁定记录
                stockLockRecordMapper.deleteByPrimaryKey(stockLockRecord.getId());
                Map<String, Object> stockParamMap = new HashMap<>();
                stockParamMap.put("enterpriseId", enterpriseId);
                stockParamMap.put("parentId", parentId);
                stockParamMap.put("goodsId", stockLockRecord.getGoodsId());
                stockParamMap.put("lotId", stockLockRecord.getLotId());
                stockParamMap.put("shelfId", stockLockRecord.getShelfId());
                BigDecimal lockQuantity = stockLockRecord.getLockQuantity();
                // 释放库存
                List<Stock> stockList = stockMapper.selectByParamMap(stockParamMap);
                if (CollectionUtils.isNotEmpty(stockList)) {
                    Stock stock = stockList.get(0);
                    stock.setLockQuantity(stock.getLockQuantity().subtract(lockQuantity));
                    stock.setUsableQuantity(stock.getUsableQuantity().add(lockQuantity));
                    stockMapper.updateByPrimaryKeySelective(stock);
                }
            }
        }
    }


    public List<StockLockRecordVO> getStockLockRecord(StockLockShelfVO stockLockShelfVO, Long enterpriseId) {
        Map<String, Object> paramMap = new HashMap<>();
        if (null != stockLockShelfVO.getGoodsId()) {
            paramMap.put("goodsId", stockLockShelfVO.getGoodsId());
        }
        if (null != stockLockShelfVO.getLotNum()) {
            paramMap.put("lotNum", stockLockShelfVO.getLotNum());
        }
        paramMap.put("enterpriseId", enterpriseId);

        if (!org.springframework.util.CollectionUtils.isEmpty(stockLockShelfVO.getLotIds())) {
            paramMap.put("list", stockLockShelfVO.getLotIds());
        }

        if (null != stockLockShelfVO.getBaseOrderId()) {
            paramMap.put("baseOrderId", stockLockShelfVO.getBaseOrderId());
        }

        List<StockLockRecordVO> stockLockRecordList = stockLockRecordMapper.selectStockLok(paramMap);

        return stockLockRecordList;

    }

    public List<BaseTreeVO> roles(UserVO userVO) {
        List<BaseTreeVO> baseTreeVOS = new ArrayList<>();

        List<UserRole> userRoles = userRoleMapper.selectByUserId(userVO.getUserId());

        List<Long> roleIds = userRoles.stream().map(r -> r.getRoleId()).collect(Collectors.toList());
        ;

        Map<String, Object> param = new HashMap<>();
        param.put("enterpriseId", userVO.getEnterpriseId());
        param.put("rList", roleIds);

        List<SysRoleAction> sysRoleActions = sysRoleActionMapper.selectByEnterpriseIdByRoleIds(param);
        if (!org.springframework.util.CollectionUtils.isEmpty(sysRoleActions)) {
            List<Long> actionIds = SysRoleAction.getActionIds(sysRoleActions);

            if (!org.springframework.util.CollectionUtils.isEmpty(actionIds)) {
                baseTreeVOS = sysActionMapper.selectByEnterpriseIds(actionIds);

            }

        }

        return baseTreeVOS;
    }


    /**
     * 安装编码排序菜单
     */

    public void cascadeSortAction(List<BaseTreeVO> baseTreeVOS) {

        Collections.sort(baseTreeVOS, new Comparator<BaseTreeVO>() {
            @Override
            public int compare(BaseTreeVO o1, BaseTreeVO o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });

        for (BaseTreeVO baseTreeVO : baseTreeVOS) {
            cascadeSortAction(baseTreeVO.getBaseTreeVOS());
        }

    }


    /**
     * 查询菜单管理
     */
    public List<BaseTreeVO> cascadeQuerySetAction(UserVO userVO, boolean isLogin) {
        List<BaseTreeVO> baseTreeVOS = new ArrayList<>();

        List<UserRole> userRoles = userRoleMapper.selectByUserId(userVO.getUserId());

        List<Long> roleIds = userRoles.stream().map(r -> r.getRoleId()).collect(Collectors.toList());

        Map<String, Object> param = new HashMap<>();
        param.put("enterpriseId", userVO.getEnterpriseId());
        if (!org.springframework.util.CollectionUtils.isEmpty(roleIds)) {
            param.put("rList", roleIds);
        }

        List<SysRoleAction> sysRoleActions = sysRoleActionMapper.selectByEnterpriseIdByRoleIds(param);
        if (!org.springframework.util.CollectionUtils.isEmpty(sysRoleActions)) {
            List<Long> actionIds = SysRoleAction.getActionIds(sysRoleActions);

            if (!org.springframework.util.CollectionUtils.isEmpty(actionIds)) {
                baseTreeVOS = sysActionMapper.selectByEnterpriseIds(actionIds);

                baseTreeVOS = generateActionTree(baseTreeVOS, userVO, isLogin);
            }

        }

        cascadeSortAction(baseTreeVOS);

        return baseTreeVOS;
    }

    private static final Integer DEFULT_LEVEL = 1;

    public List<BaseTreeVO> generateActionTree(List<BaseTreeVO> baseTreeVOS, UserVO userVO, boolean isLogin) {

        List<BaseTreeVO> parentList = new ArrayList<>();
        List<BaseTreeVO> childList = new ArrayList<>();

        for (BaseTreeVO bt : baseTreeVOS) {

            /**
             * 先判断 parentId 不为空,并且是一个url
             */
			/*if(!pattenMenuUrl(bt.getUrl(),bt.getParentId())){
				continue;
			}*/
            if (!addChildCheck(userVO.getChainType(), bt)) {
                continue;
            }

            /**
             * 筛除特殊菜单不需要显示
             */
            if (Menu.comper(bt.getCode(), userVO.getChainType()) && isLogin) {
                continue;
            }


            if (bt.getParentId() == null || bt.getParentId() == 0) {

                parentList.add(bt);
            } else {
                childList.add(bt);
            }
        }

        // 企业类型：0-总部；1-自营店；2-加盟店
        //Integer chainType = userVO.getChainType();

        for (BaseTreeVO bto : parentList) {

            bto.getBaseTreeVOS().addAll(getChild(bto.getId(), childList, bto.getLevel()));

        }

        cleanNullChild(parentList);

        return parentList;
    }


    /**
     * 递归删除子集为空并且不是功能菜单的菜单
     *
     * @param parentList
     * @return
     */
    private List<BaseTreeVO> cleanNullChild(List<BaseTreeVO> parentList) {

        Iterator<BaseTreeVO> sListIterator = parentList.iterator();
        while (sListIterator.hasNext()) {

            BaseTreeVO menu = sListIterator.next();
            List<BaseTreeVO> childs = menu.getBaseTreeVOS();

            //当子集为空并且,当前菜单层级并不包含url(即不是功能菜单)的时候才需要删除
            if (org.springframework.util.CollectionUtils.isEmpty(childs) && menu.getIsParent() == 1) {
                sListIterator.remove();

            } else {
                cleanNullChild(childs);
            }
        }
        return parentList;
    }


    private List<BaseTreeVO> getChild(Long id, List<BaseTreeVO> allList, int level) {
        level++;
        List<BaseTreeVO> childList = new ArrayList<>();
        for (BaseTreeVO all : allList) {
            if (all.getParentId().equals(id)) {
                childList.add(all);
            }
        }


        for (BaseTreeVO all : childList) {
            all.setLevel(level);
            List<BaseTreeVO> childs = getChild(all.getId(), allList, level);
            all.getBaseTreeVOS().addAll(childs);
        }
        return childList;
    }

    private boolean pattenMenuUrl(String url, Long parentId) {

        if (parentId != null && parentId != 0) {
            return true;
        }

		/*if(!StringUtils.isEmpty(url))
			return true;

		String regex = "/";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(url);
		return matcher.find();*/
        return false;
    }


    private boolean addChildCheck(Integer chainType, BaseTreeVO baseTreeVO) {


        /**
         * 如果匹配发现当前菜单的url为空或者并不是url表示不是一个功能菜单,
         * 属于顶级菜单或者中间菜单,这个种菜单并不需要过滤分店总部权限
         */
        /**
         *用于自营店（0-否；1-是）
         */
        int forBranch = baseTreeVO.getForBranch();

        /**
         *用于加盟店（0-否；1-是）
         */
        int forLeague = baseTreeVO.getForLeague();

        /**
         *用于总部（0-否；1-是）
         */
        int forParent = baseTreeVO.getForParent();

        if (chainType == 0 && forParent == 1) {
            return true;
        }

        if (chainType == 1 && forBranch == 1) {
            return true;
        }
        if (chainType == 2 && forLeague == 1) {
            return true;
        }

        return false;
    }

    /**
     * 通过单据编号获取锁定库存记录
     *
     * @param stockLockShelfVO
     * @param enterpriseId
     * @return
     */
    public List<StockLockRecordVO> getStockLockRecordByCode(StockLockShelfVO stockLockShelfVO, Long enterpriseId) {
        List<StockLockRecordVO> stockLockRecordList = stockLockRecordMapper.selectStockLockByCode(stockLockShelfVO.getCode());

        return stockLockRecordList;
    }


    @Override
    public Boolean valid(Object object) {
        Boolean flag = true;
        /**
         * 存在下级部门的；
         被岗位引用的；
         被员工引用的；
         都不可以删除
         */
        Department d = (Department) object;
        if (departmentMapper.selectSonDepartment(d.getId()) > 0) {
            flag = false;
        }
        if (positionMapper.selectPositionByDepartmentId(d.getId()) > 0) {
            flag = false;
        }
        if (sysRoleMapper.selectRoleByDepartmentId(d.getId()) > 0) {
            flag = false;
        }
        if (userAdministrationMapper.selectUserHasDepartment(d.getId()) > 0) {
            flag = false;
        }
        return flag;
    }

    /**
     * 是否质量控制
     *
     * @param loginUser
     * @return
     * @throws Exception
     */
    public Boolean getGspFlag(UserVO loginUser) throws Exception {
        ManageConfig manageConfig = manageConfigComponent.getCurrentUser(loginUser);
        return manageConfig.getBusinessControl() == 0 ? false : true;
    }


    /**
     * 生成配进退出单
     *
     * @param user                               登录用户uservo
     * @param requsetDistrReturnInSaveOrUpdateVO 数据需要的组装vo
     * @throws Exception
     */
    public DistrReturnVO generateAndSaveDistrInReturn(UserVO user, RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO, boolean isOut) throws Exception {

        DistrInReturn distrInReturn = new DistrInReturn();
        Long returnManId = requsetDistrReturnInSaveOrUpdateVO.getReturnManId();
        if (returnManId == null) throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, "缺少退货人员id");
        UserVO returnMan = temperatureHumidityDetailMapper.getUserById(returnManId, user.getEnterpriseId());
        if (returnMan == null) throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, "该企业不存在的人员");
        List<RequsetDistrReturnDtlSaveOrUpdateVO> list = requsetDistrReturnInSaveOrUpdateVO.getRequsetDistrReturnDtlSaveOrUpdateVO();
        if (list.isEmpty()) throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, "请至少添加一个商品");
        Integer distrType = requsetDistrReturnInSaveOrUpdateVO.getDistrType();
        if (distrType == null || DistributionType.getName(distrType) == null) throw new BusinessException("缺少配货类型");
        Long distrInId = requsetDistrReturnInSaveOrUpdateVO.getBaseOrderId();

        ManageConfig manageConfig = manageConfigService.getManageConfig(user);
        //设置配进退出单据基本信息
        setDistrInReturnBaseInfo(user, distrInReturn, requsetDistrReturnInSaveOrUpdateVO, returnMan, manageConfig);

        if (distrInId != null) {//distrInId为null的话，则为普通添加，不需要设置基础单据信息
            //若是调用添加，配进入库为总部配送或是直调配送类型
            if (DistributionType.DISTRIBUTION_HEAD.getCode() == distrType || DistributionType.DIRECT_DISTRIBUTION.getCode() == distrType) {
                DistrIn DistrIn = distrInMapper.selectByPrimaryKey(distrInId);
                if (DistrIn == null)
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, "不存在的配进入库单据");
                String checkReturnDateMsg = checkReturnDate(DistrIn.getInDate(), requsetDistrReturnInSaveOrUpdateVO.getInReturnDate());
                if (checkReturnDateMsg != null)
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, checkReturnDateMsg);
                //设置基础单据信息
                setDistrInReturnBaseOrderInfo(distrInReturn, DistrIn);
            } else {
                DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(distrInId);
                if (distrReqPlan == null) throw new BusinessException("不存在的要货计划单据");
                String checkReturnDateMsg = checkReturnDate(distrReqPlan.getPlanDate(), requsetDistrReturnInSaveOrUpdateVO.getInReturnDate());
                if (checkReturnDateMsg != null)
                    throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, checkReturnDateMsg);
                //设置基础单据的信息
                distrInReturn.setBaseOrderId(distrReqPlan.getId());
                distrInReturn.setBaseOrderType(distrReqPlan.getOrderType());
                distrInReturn.setBaseOrderCode(distrReqPlan.getCode());
                distrInReturn.setBaseOrderDate(distrReqPlan.getPlanDate());
            }

        }

        //设置默认状态为待出库，审批流开启则走审批流
        distrInReturn.setStatus(DistrInReturnStatus.PENDING_AUDIT);

        List<DistrInReturnDetail> paramList = new ArrayList<>(list.size());
        //设置品种总数和总数量
        setDistrInReturnMathInfo(distrInReturn, list);
        //设置行详情内容和计算金额信息
        String amountMsg = setDistrInReturnDetail(list, paramList, user, distrInReturn);
        if (amountMsg != null) throw new DistrInReturnBizException(DistrInReturnBizException.VALUE_CHECK, amountMsg);
        //保存信息
        distrInReturnMapper.insert(distrInReturn);
        for (DistrInReturnDetail detail : paramList) {
            //设置配进退出单id
            detail.setInReturnId(distrInReturn.getId());
            distrInReturnDetailMapper.insert(detail);
            // 锁定库存
            lockStockByDetail(user, detail);
        }


        if (!isOut) {
            /**
             * 添加/修改完价格调整单后需要申请一条审批工作流
             */
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());
            SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(
                    user.getEnterpriseId(),
                    enterprise.getName(),
                    user.getUserId(),
                    user.getUserName(),
                    user.getChainType(),
                    user.getParentId(),
                    user.getChainType().equals(ChainType.Headquarters.getCode()) ? user.getEnterpriseId() : user.getParentId(),
                    ApprovalFlowContentModel.getDisInReturnCode(),
                    distrInReturn.getId(),
                    distrInReturn.getCode(),
                    "配进退出审批流"
            );
            approvalFlowComponent.apply(submitApprovalFlowVO, user);
        }


        DistrReturnVO distrReturnVO = new DistrReturnVO();
        distrReturnVO.setDistrInReturn(distrInReturn);
        distrReturnVO.setParamList(paramList);

        return distrReturnVO;

    }

    public void lockStockByDetail(UserVO user, DistrInReturnDetail detail) {
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setEnterpriseId(detail.getEnterpriseId());
        lockQtyArgVO.setParentId(detail.getParentId());
        lockQtyArgVO.setBaseOrderId(detail.getInReturnId());
        lockQtyArgVO.setBaseOrderCode(detail.getInReturnCode());
        lockQtyArgVO.setBaseOrderType(detail.getOrderType());
        lockQtyArgVO.setBaseOrderDate(detail.getInReturnDate());
        lockQtyArgVO.setGoodsId(detail.getGoodsId());
        lockQtyArgVO.setLotId(detail.getLotId());
        lockQtyArgVO.setQuantity(detail.getQuantity());
        lockQtyArgVO.setUser(user);
        lockStockAndCost(lockQtyArgVO);
    }


    /**
     * 设置总数和种类总数
     *
     * @param distrInReturn
     * @param list
     */
    public void setDistrInReturnMathInfo(DistrInReturn distrInReturn, List<RequsetDistrReturnDtlSaveOrUpdateVO> list) {
        Set<Long> goodIds = new HashSet<>();
        BigDecimal totalQuantity = new BigDecimal(0);
        for (RequsetDistrReturnDtlSaveOrUpdateVO req : list) {
            totalQuantity = totalQuantity.add(req.getQuantity());
            goodIds.add(req.getGoodsId());
        }
        distrInReturn.setVarietiesQuantity(goodIds.size());
        distrInReturn.setQuantityTotal(totalQuantity);
    }

    //设置单行的单价和优惠前的总价
    public void setDistrInReturnDetailBaseMathInfo(DistrInReturnDetail distrInReturnDetail, RequsetDistrReturnDtlSaveOrUpdateVO req) {
        //获取单价
        BigDecimal unitPrice = req.getUnitPrice();
        //设置单价(单价保留六位小数)
        distrInReturnDetail.setUnitPrice(unitPrice.setScale(6, BigDecimal.ROUND_HALF_UP));
        BigDecimal lineDiscount = distrInReturnDetail.getLineDiscount();
        lineDiscount = lineDiscount.divide(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP);
        //计算单行优惠前的总价（数量*单价*行折扣）
        BigDecimal amount = unitPrice.multiply(distrInReturnDetail.getQuantity()).multiply(lineDiscount).setScale(2, BigDecimal.ROUND_HALF_UP);
        //计算的金额保留两位小数
        distrInReturnDetail.setAmount(amount);
    }


    //计算退出单的实际金额
    public void setRealAmount(DistrInReturn distrInReturn) {
        //总价*整单折扣-优惠分摊
        BigDecimal realAmountTotal = distrInReturn.getAmountTotal().multiply(distrInReturn.getWholeDiscount().divide(new BigDecimal(100)))
                .subtract(distrInReturn.getWholeDiscountAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
        distrInReturn.setRealAmountTotal(realAmountTotal);
    }


    /**
     * 设置退出详情信息和退出单计算信息
     *
     * @param list
     * @param paramList
     * @param user
     * @param distrInReturn
     * @return
     */
    public String setDistrInReturnDetail(List<RequsetDistrReturnDtlSaveOrUpdateVO> list, List<DistrInReturnDetail> paramList,
                                         UserVO user, DistrInReturn distrInReturn) {
        for (RequsetDistrReturnDtlSaveOrUpdateVO req : list) {
            DistrInReturnDetail distrInReturnDetail = new DistrInReturnDetail();

            //设置商品的基本信息
            String msg = setDistrInReturnDetailGoodsInfo(req, distrInReturnDetail);
            Long baseOrderDtlId = req.getBaseOrderDtlId();
            distrInReturnDetail.setBaseOrderDtlId(baseOrderDtlId);
            if (msg != null) return msg;
            if (distrInReturn.getDistrType() == DistributionType.SWAP_BETWEEN_STORES.getCode()) {
                if (baseOrderDtlId == null) throw new BusinessException("缺少要货详情数据");
            }
            //baseOrderDtlId为null的话，则为普通商品添加，不需要设置基础单据信息     通过配送类型判断所调单据
            if (baseOrderDtlId != null) {
                //若不是是门店间调剂类型则为调用配进订单
                if (distrInReturn.getDistrType() != DistributionType.SWAP_BETWEEN_STORES.getCode()) {
                    DistrInDetail distrInDetail = distrInDetailMapper.selectByPrimaryKey(baseOrderDtlId);
                    if (distrInDetail == null) return "行号为" + req.getLineNum() + "的入库详情单不存在";
                    //设置配进退出明细单的id
                    distrInReturnDetail.setBaseOrderDtlId(distrInDetail.getId());
                } else {//调用要货计划
                    DistrReqPlanDetail distrReqPlanDetail = distrReqPlanDetailMapper.selectByPrimaryKey(baseOrderDtlId);
                    if (distrReqPlanDetail == null) return "行号为" + req.getLineNum() + "的要货计划详情单不存在";
                    //设置配进退出明细单的id
                    distrInReturnDetail.setBaseOrderDtlId(distrReqPlanDetail.getId());
                }
            }
            //已清，未清数量
            distrInReturnDetail.setUnclearQuantity(req.getQuantity());
            distrInReturnDetail.setClearQuantity(BigDecimal.ZERO);
            //若是新增则  设置  企业信息,创建人信息和修改人信息,基础单据明细,状态,整单折扣
            BeanUtils.copyProperties(distrInReturn, distrInReturnDetail);
            //设置配进退出单号
            distrInReturnDetail.setInReturnCode(distrInReturn.getCode());
            distrInReturnDetail.setLineNum(req.getLineNum());
            distrInReturnDetail.setRemark(req.getRemark());
            //设置计算的基础数据
            distrInReturnDetail.setQuantity(req.getQuantity());
            distrInReturnDetail.setLineDiscount(req.getLineDiscount());
            distrInReturnDetail.setTaxRateId(req.getTaxRateId());
            //计算并设置基础金额信息（单个商品成本价，优惠前总价）
            setDistrInReturnDetailBaseMathInfo(distrInReturnDetail, req);
            //根据税率id设置税率
            String taxmsg = setDetailTaxRate(distrInReturnDetail);
            if (taxmsg != null) return taxmsg;
            //设置行id(对于新增的仍为null)
            distrInReturnDetail.setId(req.getId());
            paramList.add(distrInReturnDetail);
        }
        BigDecimal amountTotal = BigDecimal.ZERO;
        //统计总金额
        for (DistrInReturnDetail detail : paramList) {
            amountTotal = amountTotal.add(detail.getAmount());
        }
        //设置退出单金额合计（整单优惠前的金额合计）
        distrInReturn.setAmountTotal(amountTotal);
        //计算退出单实际金额
        setRealAmount(distrInReturn);
        //统计并设置行的金额
        BigDecimal wholeDiscountAmount = distrInReturn.getWholeDiscountAmount();
        BigDecimal wholeDiscount = distrInReturn.getWholeDiscount();
        int size = paramList.size();
        //BigDecimal sumLineDiscountAmount=new BigDecimal(0);
        //设置退出单的不含税总额，税额
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        //计算详情的行优惠金额和实际金额
        for (int i = 0; i < size; i++) {
            DistrInReturnDetail detail = paramList.get(i);

            //利用计算工具计算数值
            CalculateResultModel calculateResultModel = CalculateComponent.
                    getCalculateResult(detail.getQuantity(), detail.getUnitPrice(), detail.getLineDiscount()
                            , wholeDiscount, wholeDiscountAmount, detail.getTaxRate(), amountTotal);
            //detail.setWholeDiscount(wholeDiscount);
            //设置行优惠信息
            detail.setLineDiscountAmount(calculateResultModel.getLineRoundOff());
            //实际金额
            detail.setRealAmount(calculateResultModel.getRealAmount());
            //实际单价为 实际金额/数量
            detail.setRealPrice(calculateResultModel.getRealPrice());
            //不含税单价（实际单价/（税率+1））
            detail.setNotaxRealPrice(calculateResultModel.getNotaxPrice());
            //不含税金额（(实际金额/（税率+1））
            detail.setNotaxRealAmount(calculateResultModel.getNotaxAmount());
            //税额(实际金额-不含税金额)
            detail.setTaxAmount(calculateResultModel.getTaxAmount());
            notaxRealAmountTotal = notaxRealAmountTotal.add(detail.getNotaxRealAmount());
            taxAmountTotal = taxAmountTotal.add(detail.getTaxAmount());
        }
        //设置退出单的不含税总额，税额
        distrInReturn.setNotaxRealAmountTotal(notaxRealAmountTotal);
        distrInReturn.setTaxAmountTotal(taxAmountTotal);
        return null;
    }


    //设置行详情的商品信息
    public String setDistrInReturnDetailGoodsInfo(RequsetDistrReturnDtlSaveOrUpdateVO req,
                                                  DistrInReturnDetail distrInReturnDetail) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsId", req.getGoodsId());
        map.put("lotId", req.getLotId());
        DistrInReturnDetail goodsInfo = distrInReturnMapper.getDistrInReturnDetailGoodsInfo(map);
        if (goodsInfo == null) return "行号为" + req.getLineNum() + "的商品不存在";
        //设置商品的基本信息
        BeanUtils.copyProperties(goodsInfo, distrInReturnDetail);
        return null;
    }

    //计算详情的税率
    public String setDetailTaxRate(DistrInReturnDetail distrInReturnDetail) {
        Long taxTateId = distrInReturnDetail.getTaxRateId();
        BigDecimal taxRate = distrInReturnMapper.getGoodsTaxrateId(taxTateId);
        if (taxRate == null) return "行号为" + distrInReturnDetail.getLineNum() + "的商品税率id不存在";
        distrInReturnDetail.setTaxRate(taxRate);
        return null;
    }


    /**
     * 设置企业基础单据信息
     *
     * @param distrInReturn
     */
    public void setDistrInReturnBaseOrderInfo(DistrInReturn distrInReturn, DistrIn distrIn) {
        distrInReturn.setBaseOrderId(distrIn.getId());
        distrInReturn.setBaseOrderType(distrIn.getOrderType());
        distrInReturn.setBaseOrderCode(distrIn.getCode());
        distrInReturn.setBaseOrderDate(distrIn.getCreateTime());

        //设置出库单位的信息
        distrInReturn.setOutboundUnitId(distrIn.getOutboundUnitId());
        distrInReturn.setOutboundUnitCode(distrIn.getOutboundUnitCode());
        distrInReturn.setOutboundUnitName(distrIn.getOutboundUnitName());
    }

    public String checkReturnDate(Date inDate, Date returnDate) {
        if (inDate == null) return null;
        returnDate = returnDate == null ? new Date() : returnDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String strInDate = simpleDateFormat.format(inDate);
        String strReturnDate = simpleDateFormat.format(returnDate);
        Integer strIn = Integer.parseInt(strInDate);
        Integer strRe = Integer.parseInt(strReturnDate);
        if ((strIn - strRe) > 0) return "退货日期不能大于入库日期";
        return null;
    }


    /**
     * @param user
     * @param distrInReturn
     * @throws Exception
     */
    public void setDistrInReturnBaseInfo(UserVO user, DistrInReturn distrInReturn,
                                         RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO, UserVO returnMan, ManageConfig manageConfig) throws Exception {
        //设置时间格式
        requsetDistrReturnInSaveOrUpdateVO.setInReturnDate(StartAndEndDate.getStartTime(requsetDistrReturnInSaveOrUpdateVO.getInReturnDate()));
        //设置整单折扣，整单优惠，备注
        BeanUtils.copyProperties(requsetDistrReturnInSaveOrUpdateVO, distrInReturn);
        Boolean bus = manageConfig.getBusinessControl() == 0 ? false : true;//等于0的时候是关闭
        distrInReturn.setParentId(user.getParentId());
        distrInReturn.setEnterpriseId(user.getEnterpriseId());
        //设置配送类型
        distrInReturn.setDistrType(requsetDistrReturnInSaveOrUpdateVO.getDistrType());
        //若是新增则设置创建人信息和修改人信息，若是修改则设置修改人信息
        if (requsetDistrReturnInSaveOrUpdateVO.getId() == null) {
            //设置记录单类型和编号
            String code = orderCodeComponent.generate(OrderRule.DISTR_IN_RETURN.getCodePrefix(), user.getEnterpriseId(), user.getEnterpriseCode());
            distrInReturn.setCode(code);
            distrInReturn.setOrderType(OrderRule.DISTR_IN_RETURN.getType());
            distrInReturn.setCode(code);
            if (!bus) {//质量流程关闭的时候，
                distrInReturn.setReturnManId(returnMan.getUserId());
                distrInReturn.setReturnManName(returnMan.getUserName());
                distrInReturn.setReturnManCode(returnMan.getUserCode());
                //设置时间
                distrInReturn.setInReturnDate(requsetDistrReturnInSaveOrUpdateVO.getInReturnDate());
            } else {//质量流程开启，日期为当前日期，退货人员为当前人员
                distrInReturn.setReturnManId(user.getUserId());
                distrInReturn.setReturnManName(user.getUserName());
                distrInReturn.setReturnManCode(user.getUserCode());
                //设置时间
                distrInReturn.setInReturnDate(StartAndEndDate.getStartTime(new Date()));
            }

            distrInReturn.setCreaterId(user.getUserId());
            distrInReturn.setCreaterCode(user.getUserCode());
            distrInReturn.setCreaterName(user.getUserName());
            distrInReturn.setCreateTime(new Date());
            distrInReturn.setUpdateTime(new Date());
            distrInReturn.setModifierId(user.getUserId());
            distrInReturn.setModifierCode(user.getUserCode());
            distrInReturn.setModifierName(user.getUserName());

            //设置配货单位信息，配货单位只能是总部
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getParentId());
            distrInReturn.setDistrUnitId(user.getParentId());
            distrInReturn.setDistrUnitCode(enterprise.getCode());
            distrInReturn.setDistrUnitName(enterprise.getName());
            //设置出库单位的信息
            Long supplierId = requsetDistrReturnInSaveOrUpdateVO.getOutboundUnitId();
            //判断配货类型，直接调配，供货单位必填
            if (supplierId != null) {
                //若是直调配送
                if (requsetDistrReturnInSaveOrUpdateVO.getDistrType() == DistributionType.DIRECT_DISTRIBUTION.getCode()) {
                    Supplier supplier = supplierMapper.selectByPrimaryKey(supplierId);
                    if (supplier == null) throw new BusinessException("供货商不存在");
                    distrInReturn.setOutboundUnitId(supplier.getId());
                    distrInReturn.setOutboundUnitCode(supplier.getCode());
                    distrInReturn.setOutboundUnitName(supplier.getName());
                } else if (requsetDistrReturnInSaveOrUpdateVO.getDistrType() == DistributionType.SWAP_BETWEEN_STORES.getCode()) {
                    //若是调用要货计划门店间调剂类型
                    //先获取要货单位信息
                    Enterprise enterprise1 = enterpriseMapper.selectByPrimaryKey(supplierId);
                    if (enterprise1 == null) throw new BusinessException("要货单位不存在");
                    distrInReturn.setOutboundUnitId(enterprise1.getId());
                    distrInReturn.setOutboundUnitCode(enterprise1.getCode());
                    distrInReturn.setOutboundUnitName(enterprise1.getName());
                }
            } else {
                distrInReturn.setOutboundUnitId(user.getParentId());
                distrInReturn.setOutboundUnitCode(enterprise.getCode());
                distrInReturn.setOutboundUnitName(enterprise.getName());
            }
        } else {

            DistrInReturn dis = distrInReturnMapper.selectByPrimaryKey(requsetDistrReturnInSaveOrUpdateVO.getId());
            //若是门店间调剂的
            if (dis.getDistrType().equals(DistributionType.SWAP_BETWEEN_STORES.getCode())) {
                BeanUtils.copyProperties(dis, distrInReturn);
            }
            distrInReturn.setStatus(dis.getStatus());
            distrInReturn.setOrderType(OrderRule.DISTR_IN_RETURN.getType());
            distrInReturn.setCode(dis.getCode());
            distrInReturn.setUpdateTime(new Date());
            distrInReturn.setModifierId(user.getUserId());
            distrInReturn.setModifierCode(user.getUserCode());
            distrInReturn.setModifierName(user.getUserName());
            if (!bus) {//质量流程关闭的时候，日期，退货人员可以修改
                //设置退货人员信息
                distrInReturn.setReturnManId(returnMan.getUserId());
                distrInReturn.setReturnManName(returnMan.getUserName());
                distrInReturn.setReturnManCode(returnMan.getUserCode());
                //设置时间
                distrInReturn.setInReturnDate(requsetDistrReturnInSaveOrUpdateVO.getInReturnDate());
            } else {
                distrInReturn.setInReturnDate(dis.getInReturnDate());
            }
            distrInReturn.setDistrType(dis.getDistrType());
            distrInReturn.setCreaterId(dis.getCreaterId());
            distrInReturn.setCreaterCode(dis.getCreaterCode());
            distrInReturn.setCreaterName(dis.getCreaterName());
            distrInReturn.setCreateTime(dis.getCreateTime());
        }
    }


    /**
     * 生成和保存配后退回收货
     *
     * @param loginUser
     * @param distrReturnReceiveSaveVO
     * @return
     * @throws Exception
     */
    public Result<Map<String, Object>> generateAndSaveDistrReturnReceiveOrder(UserVO loginUser, DistrReturnReceiveSaveVO distrReturnReceiveSaveVO) throws Exception {

        Result<Map<String, Object>> msg = new Result<>();
        Map<String, Object> idMap = new HashMap<>();


        //保存总单
        DistrReturnReceive distrReturnReceive = new DistrReturnReceive();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrReturnReceiveSaveVO, distrReturnReceive);
        //set 单据类型
        distrReturnReceive.setOrderType(OrderRule.DISTR_RETURN_RECEIVE.getType());
        //set 单号
        distrReturnReceive.setCode(orderCodeComponent.generate(OrderRule.DISTR_RETURN_RECEIVE.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        //set 基础单据信息(从上游单据获取)
        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(distrReturnReceiveSaveVO.getBaseOrderId());
        //收货日期不能小于订单日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(distrReturnNotice.getNoticeDate());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (distrReturnReceive.getReceiveDate().before(calendar.getTime())) {
            throw new BusinessException(SysCode.FAIL.getCode(), "收货日期不能小于配后退回通知单日期");
        }
        distrReturnReceive.setBaseOrderCode(distrReturnNotice.getCode());
        distrReturnReceive.setBaseOrderDate(distrReturnNotice.getNoticeDate());
        distrReturnReceive.setBaseOrderType(distrReturnNotice.getOrderType());
        //set 要货单位(从上游单据获取)
        distrReturnReceive.setRequestUnitId(distrReturnNotice.getRequestUnitId());
        distrReturnReceive.setRequestUnitCode(distrReturnNotice.getRequestUnitCode());
        distrReturnReceive.setRequestUnitName(distrReturnNotice.getRequestUnitName());
        //set 配货类型(从上游单据获取)
        distrReturnReceive.setDistrType(distrReturnNotice.getDistrType());
        //set 收货人员
        User user = userMapper.selectByPrimaryKey(distrReturnReceiveSaveVO.getReceiverId());
        distrReturnReceive.setReceiverCode(user.getCode());
        distrReturnReceive.setReceiverName(user.getName());
        //有第二收货人时 set信息
        if (distrReturnReceiveSaveVO.getSecondReceiverId() != null) {
            user = userMapper.selectByPrimaryKey(distrReturnReceiveSaveVO.getSecondReceiverId());
            distrReturnReceive.setSecondReceiverCode(user.getCode());
            distrReturnReceive.setSecondReceiverName(user.getName());
        }
        //set 状态
        distrReturnReceive.setStatus(PubStatus.distrReturnNoticeStatus.WAIT_CHECK);
        //set 创建人信息
        UserEnterpriseUtils.setUserCreateOrModify(distrReturnReceive, loginUser, true);
        //set 企业信息
        distrReturnReceive.setEnterpriseId(loginUser.getEnterpriseId());
        distrReturnReceive.setParentId(loginUser.getParentId());
        distrReturnReceiveMapper.insertSelective(distrReturnReceive);
        idMap.put("baseOrderId", distrReturnReceive.getId());
        idMap.put("distrReturnReceive", distrReturnReceive);

        //保存细单
        boolean isWholeRefuseQuantity = false;//是否是整单拒收
        List<DistrReturnReceiveDetail> distrReturnReceiveDetails = new ArrayList<>();
        for (DistrReturnReceiveDetailSaveVO distrReturnReceiveDetailSaveVO : distrReturnReceiveSaveVO.getDistrReturnReceiveDetailSaveVOS()) {
            DistrReturnReceiveDetail distrReturnReceiveDetail = new DistrReturnReceiveDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrReturnReceiveDetailSaveVO, distrReturnReceiveDetail);
            //set 配后退回收货单ID/类型/单号/日期
            distrReturnReceiveDetail.setReceiveId(distrReturnReceive.getId());
            distrReturnReceiveDetail.setOrderType(distrReturnReceive.getOrderType());
            distrReturnReceiveDetail.setReceiveCode(distrReturnReceive.getCode());
            distrReturnReceiveDetail.setReceiveDate(distrReturnReceive.getReceiveDate());
            //set 基础单据信息
            distrReturnReceiveDetail.setBaseOrderCode(distrReturnReceive.getBaseOrderCode());
            distrReturnReceiveDetail.setBaseOrderDate(distrReturnReceive.getBaseOrderDate());
            distrReturnReceiveDetail.setBaseOrderType(distrReturnReceive.getBaseOrderType());
            //set 商品信息
            Goods goods = goodsMapper.selectByPrimaryKey(distrReturnReceiveDetailSaveVO.getGoodsId());
            if (goods.getSpecialDrug() != -1 && distrReturnReceiveSaveVO.getSecondReceiverId() == null) {
                throw new BusinessException("第" + distrReturnReceiveDetailSaveVO.getLineNum() + "行的" + goods.getGenericName() + "为特管商品,必须有第二收货人!");
            }
            distrReturnReceiveDetail.setGoodsName(goods.getName());
            distrReturnReceiveDetail.setGoodsCode(goods.getCode());
            distrReturnReceiveDetail.setBarcode(goods.getBarcode());
            distrReturnReceiveDetail.setGoodsGenericName(goods.getGenericName());
            distrReturnReceiveDetail.setDosageId(goods.getDosageId());
            distrReturnReceiveDetail.setDosageName(goods.getDosageName());
            distrReturnReceiveDetail.setUnitId(goods.getUnitId());
            distrReturnReceiveDetail.setUnitName(goods.getUnitName());
            distrReturnReceiveDetail.setGoodsSpecification(goods.getSpecification());
            distrReturnReceiveDetail.setManufacturerId(goods.getManufacturerId());
            distrReturnReceiveDetail.setManufacturer(goods.getManufacturer());
            distrReturnReceiveDetail.setGoodsPlace(goods.getPlace());
            distrReturnReceiveDetail.setApprovalNumber(goods.getApprovalNumber());
            //set 未清数量=收货数量 以清数量 = 0
            distrReturnReceiveDetail.setUnclearQuantity(distrReturnReceiveDetailSaveVO.getReceiveQuantity());//未清数量为收货数量
            distrReturnReceiveDetail.setClearQuantity(BigDecimal.ZERO);
            //set 状态
            //set 状态(如果收货数量为0,则状态为已完成)
            if (distrReturnReceiveDetail.getReceiveQuantity().compareTo(BigDecimal.ZERO) == 0) {
                isWholeRefuseQuantity = true;
                distrReturnReceiveDetail.setStatus(PubStatus.distrReturnNoticeStatus.FINISHED);
                //修改通知单细单状态 为已完成       及已清未清数量
                distrReturnNoticeDetailMapper.updateStatusById(distrReturnReceiveDetailSaveVO.getBaseOrderDtlId(),
                        PubStatus.distrReturnNoticeStatus.FINISHED, loginUser.getEnterpriseId(), BigDecimal.ZERO,
                        distrReturnReceiveDetailSaveVO.getArrivalQuantity());
            } else {
                isWholeRefuseQuantity = false;
                distrReturnReceiveDetail.setStatus(distrReturnReceive.getStatus());
                //修改通知单细单状态为待验收           及已清未清数量
                distrReturnNoticeDetailMapper.updateStatusById(distrReturnReceiveDetailSaveVO.getBaseOrderDtlId(),
                        distrReturnReceive.getStatus(), loginUser.getEnterpriseId(), BigDecimal.ZERO,
                        distrReturnReceiveDetailSaveVO.getArrivalQuantity());
            }

            distrReturnReceiveDetail.setStatus(distrReturnReceive.getStatus());
            //set 创建人信息
            UserEnterpriseUtils.setUserCreateOrModify(distrReturnReceiveDetail, loginUser, true);
            //set 企业信息
            distrReturnReceiveDetail.setEnterpriseId(loginUser.getEnterpriseId());
            distrReturnReceiveDetail.setParentId(loginUser.getParentId());
            distrReturnReceiveDetails.add(distrReturnReceiveDetail);
        }
        //细单保存
        distrReturnReceiveDetailMapper.batchInsert(distrReturnReceiveDetails);
        idMap.put("baseOrderDtlIdList", distrReturnReceiveDetails);
        idMap.put("distrReturnReceiveDetails", distrReturnReceiveDetails);
        //修改通知单状态
        if (isWholeRefuseQuantity)
            distrReturnNoticeMapper.updateStatus(PubStatus.distrReturnNoticeStatus.FINISHED, distrReturnReceive.getBaseOrderId(), distrReturnReceive.getEnterpriseId());
        else
            distrReturnNoticeMapper.updateStatus(PubStatus.distrReturnNoticeStatus.WAIT_CHECK, distrReturnReceive.getBaseOrderId(), distrReturnReceive.getEnterpriseId());
        getInNotice(distrReturnReceive, distrReturnReceiveDetails, loginUser);

        msg.setData(idMap);
        return msg;
    }


    private void getInNotice(DistrReturnReceive distrReturnReceive, List<DistrReturnReceiveDetail> distrReturnReceiveDetails, UserVO loginUser) throws Exception {
        //如果拒收数量不为零 则生成门店的配进订单
        Set<Long> goodsIdSet = new HashSet<>();
        BigDecimal quantityTotal = BigDecimal.ZERO;//数量合计
        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计
        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计
        //先获取要货单位信息
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrReturnReceive.getRequestUnitId());
        //待生成门店配进订单细单集合
        List<DistrInNoticeDetail> distrInNoticeDetails = new ArrayList<>();
        for (DistrReturnReceiveDetail distrReturnReceiveDetail : distrReturnReceiveDetails) {
            int lineNum = 1;
            if (distrReturnReceiveDetail.getRefuseQuantity().compareTo(BigDecimal.ZERO) == 0)
                continue;
            if (distrReturnReceiveDetail.getRefuseQuantity().compareTo(BigDecimal.ZERO) > 0) {
                DistrInNoticeDetail distrInNoticeDetail = new DistrInNoticeDetail();
                //set 创建人信息
                UserEnterpriseUtils.setUserCreateOrModify(distrInNoticeDetail, loginUser, true);
                //set 企业信息(要货单位信息)
                distrInNoticeDetail.setEnterpriseId(enterprise.getId());
                distrInNoticeDetail.setParentId(enterprise.getParentId());
                //set 订单日期
                distrInNoticeDetail.setOrderDate(new Date());
                //set 状态
                distrInNoticeDetail.setStatus(PubStatus.distrInStatus.WAIT_RECEIVE);
                //set 基础单据信息(收货单的信息)
                distrInNoticeDetail.setBaseOrderId(distrReturnReceiveDetail.getReceiveId());
                distrInNoticeDetail.setBaseOrderCode(distrReturnReceiveDetail.getBaseOrderCode());
                distrInNoticeDetail.setBaseOrderDate(distrReturnReceiveDetail.getReceiveDate());
                distrInNoticeDetail.setBaseOrderDtlId(distrReturnReceiveDetail.getId());
                distrInNoticeDetail.setBaseOrderType(distrReturnReceiveDetail.getOrderType());
                //set 商品信息
                distrInNoticeDetail.setGoodsId(distrReturnReceiveDetail.getGoodsId());
                distrInNoticeDetail.setGoodsName(distrReturnReceiveDetail.getGoodsName());
                distrInNoticeDetail.setGoodsCode(distrReturnReceiveDetail.getGoodsCode());
                distrInNoticeDetail.setBarcode(distrReturnReceiveDetail.getBarcode());
                distrInNoticeDetail.setGoodsGenericName(distrReturnReceiveDetail.getGoodsGenericName());
                distrInNoticeDetail.setDosageId(distrReturnReceiveDetail.getDosageId());
                distrInNoticeDetail.setDosageName(distrReturnReceiveDetail.getDosageName());
                distrInNoticeDetail.setUnitId(distrReturnReceiveDetail.getUnitId());
                distrInNoticeDetail.setUnitName(distrReturnReceiveDetail.getUnitName());
                distrInNoticeDetail.setGoodsSpecification(distrReturnReceiveDetail.getGoodsSpecification());
                distrInNoticeDetail.setManufacturerId(distrReturnReceiveDetail.getManufacturerId());
                distrInNoticeDetail.setManufacturer(distrReturnReceiveDetail.getManufacturer());
                distrInNoticeDetail.setGoodsPlace(distrReturnReceiveDetail.getGoodsPlace());
                distrInNoticeDetail.setApprovalNumber(distrReturnReceiveDetail.getApprovalNumber());
                //set 数量(拒收数量)
                distrInNoticeDetail.setQuantity(distrReturnReceiveDetail.getRefuseQuantity());
                //set 单价(配退通知单上的单价)
                DistrReturnNoticeDetail noticeDetail = distrReturnNoticeDetailMapper.selectByPrimaryKey(distrReturnReceiveDetail.getBaseOrderDtlId());
                distrInNoticeDetail.setUnitPrice(noticeDetail.getUnitPrice());
                //set 零售单价/会员单价(门店的价格清单,门店就是收货单的要货单位)
                PriceOrderDetail priceOrderDetail = getBasePriceOrderDetail(enterprise.getId(), enterprise.getParentId(), distrReturnReceiveDetail.getGoodsId());
/*
                PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByGoodsIdAndEnterpriseId(distrReturnReceiveDetail.getGoodsId(), distrReturnReceive.getRequestUnitId());
*/
                distrInNoticeDetail.setRetailPrice(priceOrderDetail.getRetailPrice());
                distrInNoticeDetail.setMemberPrice(priceOrderDetail.getMemberPrice());
                //set 行折扣
                distrInNoticeDetail.setLineDiscount(noticeDetail.getLineDiscount());
                //set 金额(数量*单价*行折扣)
                distrInNoticeDetail.setAmount(distrInNoticeDetail.getQuantity().multiply(noticeDetail.getUnitPrice().multiply(noticeDetail.getLineDiscount())).setScale(2, RoundingMode.HALF_UP));
                //set 整单折扣
                distrInNoticeDetail.setWholeDiscount(noticeDetail.getWholeDiscount());
                //set 行优惠
                distrInNoticeDetail.setLineDiscountAmount(noticeDetail.getLineDiscountAmount());
                //set 实际金额(数量*单价*行折扣*整单折扣-优惠分摊)
                distrInNoticeDetail.setRealAmount(distrInNoticeDetail.getQuantity().multiply(noticeDetail.getLineDiscount().multiply(noticeDetail.getWholeDiscount())).setScale(2, RoundingMode.HALF_UP).subtract(noticeDetail.getLineDiscountAmount()));
                //set 实际单价(实际金额/数量)
                distrInNoticeDetail.setRealPrice(distrInNoticeDetail.getRealAmount().divide(distrInNoticeDetail.getQuantity(), 2, RoundingMode.HALF_UP));
                //税率id/税率
                distrInNoticeDetail.setTaxRateId(noticeDetail.getTaxRateId());
                distrInNoticeDetail.setTaxRate(noticeDetail.getTaxRate());
                //set 不含税实际单价(实际单价/(1+税率))
                distrInNoticeDetail.setNotaxRealPrice(distrInNoticeDetail.getRealPrice().divide(noticeDetail.getTaxRate().divide(new BigDecimal(100)).add(BigDecimal.ONE), 2, RoundingMode.HALF_UP));
                //set 不含税实际金额(实际金额/(1+税率))
                distrInNoticeDetail.setNotaxRealAmount(distrInNoticeDetail.getRealAmount().divide(noticeDetail.getTaxRate().divide(new BigDecimal(100)).add(BigDecimal.ONE), 2, RoundingMode.HALF_UP));
                //set 税额(实际金额-不含税金额)
                distrInNoticeDetail.setTaxAmount(distrInNoticeDetail.getRealAmount().subtract(distrInNoticeDetail.getNotaxRealAmount()));
                //set 未清数量/以清数量
                distrInNoticeDetail.setUnclearQuantity(distrInNoticeDetail.getQuantity());
                distrInNoticeDetail.setClearQuantity(BigDecimal.ZERO);
                //set 行号
                distrInNoticeDetail.setLineNum(lineNum);
                lineNum++;
                //set 备注
                distrInNoticeDetail.setRemark(distrReturnReceiveDetail.getRemark());
                //总单金额信息
                goodsIdSet.add(distrInNoticeDetail.getGoodsId());
                quantityTotal = quantityTotal.add(distrInNoticeDetail.getQuantity());
                amountTotal = amountTotal.add(distrInNoticeDetail.getAmount());
                realAmountTotal = realAmountTotal.add(distrInNoticeDetail.getRealAmount());
                notaxRealAmountTotal = notaxRealAmountTotal.add(distrInNoticeDetail.getNotaxRealAmount());
                taxAmountTotal = taxAmountTotal.add(distrInNoticeDetail.getTaxAmount());
                distrInNoticeDetails.add(distrInNoticeDetail);
            }
        }
        if (distrInNoticeDetails.size() > 0) {
            DistrInNotice distrInNotice = new DistrInNotice();
            //set 创建人信息
            UserEnterpriseUtils.setUserCreateOrModify(distrInNotice, loginUser, true);
            //set 企业信息
            distrInNotice.setEnterpriseId(enterprise.getId());
            distrInNotice.setParentId(enterprise.getParentId());
            //set 订单日期
            distrInNotice.setOrderDate(new Date());
            //set 状态
            distrInNotice.setStatus(PubStatus.distrInStatus.WAIT_RECEIVE);
            //set 基础单据信息
            distrInNotice.setBaseOrderId(distrReturnReceive.getId());
            distrInNotice.setBaseOrderCode(distrReturnReceive.getCode());
            distrInNotice.setBaseOrderDate(distrReturnReceive.getBaseOrderDate());
            distrInNotice.setBaseOrderType(distrReturnReceive.getOrderType());
            //set 配货单位信息(总部)
            distrInNotice.setDistrUnitId(enterprise.getParentId());
            distrInNotice.setDistrUnitCode(loginUser.getEnterpriseCode());
            distrInNotice.setDistrUnitName(loginUser.getEnterpriseName());
            //set 配进人员信息(分店的配送人员)
            ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser);
            User user = userMapper.selectByPrimaryKey(manageConfig.getRequesterId());
            distrInNotice.setStorageManId(user.getId());
            distrInNotice.setStorageManCode(user.getCode());
            distrInNotice.setStorageManName(user.getName());
            //set 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
            distrInNotice.setDistrType(0);
            //set 数量合计
            distrInNotice.setQuantityTotal(quantityTotal);
            //set 品种数量
            distrInNotice.setVarietiesQuantity(goodsIdSet.size());
            //set 金额合计
            distrInNotice.setAmountTotal(amountTotal);
            //set 整单折扣(从通知单取)
            DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(distrReturnReceive.getBaseOrderId());
            distrInNotice.setWholeDiscount(distrReturnNotice.getWholeDiscount());
            //set 整单优惠金额(从通知单取)
            distrInNotice.setWholeDiscountAmount(distrReturnNotice.getWholeDiscountAmount());
            //set 实际金额合计
            distrInNotice.setRealAmountTotal(realAmountTotal);
            //set 不含税金额合计
            distrInNotice.setNotaxRealAmountTotal(notaxRealAmountTotal);
            //set 税额合计
            distrInNotice.setTaxAmountTotal(taxAmountTotal);
            //set 备注
            distrInNotice.setRemark(distrReturnReceive.getRemark());
            //set 细单集合
            distrInNotice.setDetailList(distrInNoticeDetails);
            distrComponent.saveInNotice(loginUser, distrInNotice);
        }
    }


    /**
     * 生成和保存配后退回验收单据
     *
     * @param loginUser
     * @param saveDistrReturnCheckVO
     * @throws Exception
     */
    public Result<Map<String, Object>> generateAndSaveDistrReturnCheck(UserVO loginUser, SaveDistrReturnCheckVO saveDistrReturnCheckVO) throws Exception {
        Result<Map<String, Object>> msg = new Result<>();
        Map<String, Object> ipMap = new HashMap<>();
        msg.setData(ipMap);

        SaveDistrReturnCheckHeadVO saveDistrReturnCheckHeadVO = saveDistrReturnCheckVO.getSaveDistrReturnCheckHeadVO();
        DistrReturnCheck distrReturnCheck = new DistrReturnCheck();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrReturnCheckHeadVO, distrReturnCheck);
        UserEnterpriseUtils.setUserCreateOrModify(distrReturnCheck, loginUser, true);
        distrReturnCheck.setEnterpriseId(loginUser.getEnterpriseId());
        distrReturnCheck.setParentId(loginUser.getParentId());
        //set验收人信息--start
        User user = userMapper.selectByPrimaryKey(distrReturnCheck.getCheckerId());
        if (user != null) {
            distrReturnCheck.setCheckerCode(user.getCode());
            distrReturnCheck.setCheckerName(user.getName());
            if (distrReturnCheck.getSecondCheckerId() != null) {
                user = userMapper.selectByPrimaryKey(distrReturnCheck.getSecondCheckerId());
                distrReturnCheck.setSecondCheckerCode(user.getCode());
                distrReturnCheck.setSecondCheckerName(user.getName());
            }
        }
        //set收货订单信息 基础单据--start
        DistrReturnReceive distrReturnReceive = distrReturnCheckMapper.selectByReceiveId(loginUser.getEnterpriseId(), saveDistrReturnCheckHeadVO.getReceiveId());
        Date orderDate = distrReturnReceive.getReceiveDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (distrReturnCheck.getCheckDate().before(calendar.getTime())) {
            throw new BusinessException("验收时间不能早于收货时间,否则无法保存");
        }
        //基础单据
        distrReturnCheck.setBaseOrderId(distrReturnReceive.getId());
        distrReturnCheck.setBaseOrderCode(distrReturnReceive.getCode());
        distrReturnCheck.setBaseOrderType(distrReturnReceive.getOrderType());
        distrReturnCheck.setBaseOrderDate(distrReturnReceive.getReceiveDate());
        distrReturnCheck.setQuantityTotal(BigDecimal.ZERO);
        distrReturnCheck.setVarietiesQuantity(0);

        //验收单号,单据类型
        distrReturnCheck.setOrderType(OrderRule.DISTR_RETURN_CHECK.getType());
        distrReturnCheck.setStatus(PubStatus.distrReturnNoticeStatus.WAIT_IN);
        distrReturnCheck.setCode(getCode(OrderRule.DISTR_RETURN_CHECK.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        //配后退回通知单ID
        distrReturnCheck.setNoticeId(distrReturnReceive.getBaseOrderId());
        //要货单位
        distrReturnCheck.setRequestUnitId(distrReturnReceive.getRequestUnitId());
        distrReturnCheck.setRequestUnitCode(distrReturnReceive.getRequestUnitCode());
        distrReturnCheck.setRequestUnitName(distrReturnReceive.getRequestUnitName());
        distrReturnCheck.setId(null);
        int MainId = distrReturnCheckMapper.insertSelective(distrReturnCheck);
        ipMap.put("baseOrderId", MainId);

        int reNum = saveDistrReturnCheckDetail(loginUser, distrReturnReceive, distrReturnCheck, saveDistrReturnCheckVO.getSaveDistrReturnCheckDetailVO(), ipMap);

        //更新总单品种数量和数量合计
        distrReturnCheckMapper.updateTotal(loginUser.getEnterpriseId(), distrReturnCheck.getId());
        distrReturnCheckMapper.updateVarieties(loginUser.getEnterpriseId(), distrReturnCheck.getId(), saveDistrReturnCheckVO.getSaveDistrReturnCheckDetailVO().size());

        //修改收货单状态,如果gsp不管控,修改其他信息
        distrReturnCheckMapper.updateByReceive(loginUser.getEnterpriseId(), distrReturnCheck.getBaseOrderId(), PubStatus.distrReturnNoticeStatus.WAIT_IN);
        distrReturnCheckMapper.updateByReceiveDetail(loginUser.getEnterpriseId(), distrReturnCheck.getBaseOrderId(), PubStatus.distrReturnNoticeStatus.WAIT_IN);

        //更改收货单已清未清数量
        List<DistrReturnReceiveDetail> receiveDtlList = distrReturnCheckMapper.selectByDistrReturnReceiveId(distrReturnCheck.getBaseOrderId());
        DistrReturnReceiveDetail receiveDtl = new DistrReturnReceiveDetail();
        for (DistrReturnReceiveDetail dtl : receiveDtlList) {
            receiveDtl.setUnclearQuantity(BigDecimal.ZERO);
            receiveDtl.setClearQuantity(dtl.getReceiveQuantity());
            receiveDtl.setId(dtl.getId());
            distrReturnReceiveDetailMapper.updateByPrimaryKeySelective(receiveDtl);
        }
        //修改订单总单状态,如果gsp不管控,修改其他信息
        distrReturnCheckMapper.updateByOrder(loginUser.getEnterpriseId(), distrReturnCheck.getNoticeId(), PubStatus.distrReturnNoticeStatus.WAIT_IN);
        distrReturnCheckMapper.updateByOrderDetail(loginUser.getEnterpriseId(), distrReturnCheck.getNoticeId(), PubStatus.distrReturnNoticeStatus.WAIT_IN);

        List<DistrReturnCheckDetail> distrReturnCheckDetailList = (List<DistrReturnCheckDetail>) ipMap.get("baseOrderDtlIdList");
        List<DistrReturnCheckLot> distrReturnCheckLots = (List<DistrReturnCheckLot>) ipMap.get("distrReturnCheckLots");

        BigDecimal reduce = distrReturnCheckDetailList.stream().map(DistrReturnCheckDetail::getQualifiedQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
        distrReturnCheck.setQuantityTotal(reduce);
        distrReturnCheck.setVarietiesQuantity(saveDistrReturnCheckVO.getSaveDistrReturnCheckDetailVO().size());

        ipMap.put("distrReturnCheck", distrReturnCheck);

        return msg;
    }

    //获取采购验收单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
    }


    private int saveDistrReturnCheckDetail(UserVO loginUser, DistrReturnReceive distrReturnReceive, DistrReturnCheck distrReturnCheck, List<SaveDistrReturnCheckDetailVO> saveDistrReturnCheckDetailVOs, Map<String, Object> idMap) throws Exception, BusinessException {

        List<DistrReturnCheckDetail> distrReturnCheckDetailList = new ArrayList<>();
        List<DistrReturnCheckLot> distrReturnCheckLots = new ArrayList<>();
        idMap.put("baseOrderDtlIdList", distrReturnCheckDetailList);
        idMap.put("distrReturnCheckLots", distrReturnCheckLots);
        if (saveDistrReturnCheckDetailVOs.size() > 0) {
            int number = 1;
            for (SaveDistrReturnCheckDetailVO saveDistrReturnCheckDetailVO : saveDistrReturnCheckDetailVOs) {
                BigDecimal summationRqNum = BigDecimal.ZERO;
                BigDecimal summationQqNum = BigDecimal.ZERO;
                DistrReturnCheckDetail distrReturnCheckDetail = new DistrReturnCheckDetail();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrReturnCheckDetailVO, distrReturnCheckDetail);
                if ((saveDistrReturnCheckDetailVO.getSpecialDrug() != null && saveDistrReturnCheckDetailVO.getSpecialDrug() != -1) && distrReturnCheck.getSecondCheckerId() == null) {
                    throw new BusinessException("细单中第" + number + "行为特殊管理药品,必须填写第二验收人,否则无法保存");
                }
                //查询收货信息
                DistrReturnReceiveDetail distrReturnReceiveDetail = distrReturnCheckMapper.selectByDistrReturnReceiveDetail(loginUser.getEnterpriseId(), saveDistrReturnCheckDetailVO.getDistrReturnReceiveDetailId());
                //set基础单据信息
                distrReturnCheckDetail.setBaseOrderId(distrReturnReceiveDetail.getReceiveId());
                distrReturnCheckDetail.setBaseOrderDate(distrReturnReceiveDetail.getReceiveDate());
                distrReturnCheckDetail.setBaseOrderType(distrReturnReceiveDetail.getOrderType());
                distrReturnCheckDetail.setBaseOrderCode(distrReturnReceiveDetail.getReceiveCode());
                distrReturnCheckDetail.setBaseOrderDtlId(distrReturnReceiveDetail.getId());
                //
                distrReturnCheckDetail.setNoticeId(distrReturnReceiveDetail.getBaseOrderId());
                distrReturnCheckDetail.setNoticeDtlId(distrReturnReceiveDetail.getBaseOrderDtlId());
                //set商品信息
                distrReturnCheckDetail.setGoodsName(distrReturnReceiveDetail.getGoodsName());
                distrReturnCheckDetail.setGoodsCode(distrReturnReceiveDetail.getGoodsCode());
                distrReturnCheckDetail.setBarcode(distrReturnReceiveDetail.getBarcode());
                distrReturnCheckDetail.setGoodsGenericName(distrReturnReceiveDetail.getGoodsGenericName());
                distrReturnCheckDetail.setDosageId(distrReturnReceiveDetail.getDosageId());
                distrReturnCheckDetail.setDosageName(distrReturnReceiveDetail.getDosageName());
                distrReturnCheckDetail.setUnitId(distrReturnReceiveDetail.getUnitId());
                distrReturnCheckDetail.setUnitName(distrReturnReceiveDetail.getUnitName());
                distrReturnCheckDetail.setGoodsSpecification(distrReturnReceiveDetail.getGoodsSpecification());
                distrReturnCheckDetail.setManufacturerId(distrReturnReceiveDetail.getManufacturerId());
                distrReturnCheckDetail.setManufacturer(distrReturnReceiveDetail.getManufacturer());
                distrReturnCheckDetail.setGoodsPlace(distrReturnReceiveDetail.getGoodsPlace());
                distrReturnCheckDetail.setApprovalNumber(distrReturnReceiveDetail.getApprovalNumber());
                //set状态
                distrReturnCheckDetail.setStatus(PubStatus.distrReturnNoticeStatus.WAIT_IN);
                //set未清数量/以清数量
                distrReturnCheckDetail.setUnclearQuantity(distrReturnCheckDetail.getReceiveQuantity());
                distrReturnCheckDetail.setClearQuantity(BigDecimal.ZERO);
                //set验收单据id/code/类型/日期
                distrReturnCheckDetail.setCheckId(distrReturnCheck.getId());
                distrReturnCheckDetail.setCheckCode(distrReturnCheck.getCode());
                distrReturnCheckDetail.setOrderType(OrderRule.DISTR_RETURN_CHECK.getType());
                distrReturnCheckDetail.setCheckDate(distrReturnCheck.getCheckDate());

                distrReturnCheckDetail.setEnterpriseId(loginUser.getEnterpriseId());
                distrReturnCheckDetail.setParentId(loginUser.getParentId());
                distrReturnCheckDetail.setId(null);
                UserEnterpriseUtils.setUserCreateOrModify(distrReturnCheckDetail, loginUser, true);
                distrReturnCheckDetailMapper.insertSelective(distrReturnCheckDetail);
                distrReturnCheckDetailList.add(distrReturnCheckDetail);

                //设置收货单细单的已清及未清数量
                DistrReturnReceiveDetail param = new DistrReturnReceiveDetail();
                param.setId(distrReturnReceiveDetail.getId());
                param.setClearQuantity(distrReturnReceiveDetail.getReceiveQuantity());
                param.setUnclearQuantity(BigDecimal.ZERO);

                if (saveDistrReturnCheckDetailVO.getSaveDistrReturnCheckDetailOneVO().size() > 0) {
                    for (SaveDistrReturnCheckDetailOneVO saveDistrReturnCheckDetailOneVO : saveDistrReturnCheckDetailVO.getSaveDistrReturnCheckDetailOneVO()) {
                        DistrReturnCheckLot distrReturnCheckLot = new DistrReturnCheckLot();

                        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrReturnCheckDetailOneVO, distrReturnCheckLot);

                        if (saveDistrReturnCheckDetailOneVO.getConclusionIds() == null) {
                            throw new BusinessException("验收结论必须填写,否则无法保存");
                        }
                        if (saveDistrReturnCheckDetailOneVO.getCheckProjectIds() == null) {
                            throw new BusinessException("检验项目必须填写,否则无法保存");
                        }

                        if (distrReturnCheckLot.getValidDate().before(distrReturnCheckLot.getProductDate())) {
                            throw new BusinessException("有效期必须大于生产日期,否则无法保存");
                        }

                        int num = distrReturnCheckDetail.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO); //和0，Zero比较
                        if (num > 0 && (saveDistrReturnCheckDetailOneVO.getUnqualifiedReasonIds() == null
                                || saveDistrReturnCheckDetailOneVO.getMeasuresIds() == null)) {
                            throw new BusinessException("存在验收不合格数量,必须填写不合格原因和处置措施,否则无法保存");
                        }
                        int numTwo = distrReturnCheckLot.getReceiveQuantity().compareTo(distrReturnCheckLot.getSamplingQuantity()); //和0，Zero比较
                        if (numTwo < 0) {
                            throw new BusinessException("抽样数量不能大于收货数量,否则无法保存");
                        }

                        summationRqNum = summationRqNum.add(distrReturnCheckLot.getReceiveQuantity());
                        summationQqNum = summationQqNum.add(distrReturnCheckLot.getQualifiedQuantity());
                        int numThree = summationRqNum.compareTo(distrReturnCheckDetail.getReceiveQuantity());
                        if (numThree > 0) {
                            throw new BusinessException("该验收单中的收货数量超出收货单中的收货数量,无法保存");
                        }
                        int numFore = summationQqNum.compareTo(distrReturnCheckDetail.getReceiveQuantity());
                        if (numFore > 0) {
                            throw new BusinessException("该验收单中的验收合格数量超出收货单中的收货数量,无法保存");
                        }

                        if (distrReturnCheckLot.getReceiveQuantity().compareTo(BigDecimal.ZERO) < 0) {
                            throw new BusinessException("收货数量必须大于等于0");
                        }
                        if (distrReturnCheckLot.getSamplingQuantity().compareTo(BigDecimal.ZERO) < 0) {
                            throw new BusinessException("抽样数量必须大于等于0");
                        }
                        if (distrReturnCheckLot.getQualifiedQuantity().compareTo(BigDecimal.ZERO) < 0) {
                            throw new BusinessException("验收合格数量必须大于等于0");
                        }
                        if (distrReturnCheckLot.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) < 0) {
                            throw new BusinessException("验收不合格数量必须大于等于0");
                        }

                        //
                        String[] checkProjectIds = saveDistrReturnCheckDetailOneVO.getCheckProjectIds();
                        String[] unqualifiedReasonIds = saveDistrReturnCheckDetailOneVO.getUnqualifiedReasonIds();
                        String checkProjectId = "";
                        for (int i = 0; i < checkProjectIds.length; i++) {
                            checkProjectId += checkProjectIds[i] + ",";
                        }
                        String unqualifiedReasonId = "";
                        for (int i = 0; i < unqualifiedReasonIds.length; i++) {
                            unqualifiedReasonId += unqualifiedReasonIds[i] + ",";
                        }
                        if (checkProjectId.trim().length() > 0) {
                            distrReturnCheckLot.setCheckProjectIds(checkProjectId.substring(0, checkProjectId.length() - 1));
                        }
                        if (unqualifiedReasonId.trim().length() > 0) {
                            distrReturnCheckLot.setUnqualifiedReasonIds(unqualifiedReasonId.substring(0, unqualifiedReasonId.length() - 1));
                        }

                        List<TestReportIdsVO> testReportIdsVO = saveDistrReturnCheckDetailOneVO.getTestReportIdsVO();
                        String testReportIds = getTestReportIds(testReportIdsVO);
                        distrReturnCheckLot.setTestReportIds(testReportIds);

                        //上级信息
                        distrReturnCheckLot.setDtlId(distrReturnCheckDetail.getId());
                        distrReturnCheckLot.setCheckId(distrReturnCheckDetail.getCheckId());
                        //set未清数量/以清数量
                        distrReturnCheckLot.setUnclearQuantity(distrReturnCheckLot.getReceiveQuantity());
                        distrReturnCheckLot.setClearQuantity(BigDecimal.ZERO);
                        //状态
                        distrReturnCheckLot.setStatus(PubStatus.distrReturnNoticeStatus.WAIT_IN);
                        //货品信息
                        distrReturnCheckLot.setGoodsCode(distrReturnCheckDetail.getGoodsCode());
                        distrReturnCheckLot.setGoodsName(distrReturnCheckDetail.getGoodsName());

                        distrReturnCheckLot.setEnterpriseId(loginUser.getEnterpriseId());
                        distrReturnCheckLot.setParentId(loginUser.getParentId());
                        UserEnterpriseUtils.setUserCreateOrModify(distrReturnCheckLot, loginUser, true);
                        distrReturnCheckLot.setId(null);
                        distrReturnCheckLotMapper.insertSelective(distrReturnCheckLot);

                        distrReturnCheckLots.add(distrReturnCheckLot);

                    }
                } else {
                    throw new BusinessException("没有得到明细单据批号的相关数据,无法保存");
                }
                number++;
            }
        } else {
            throw new BusinessException("没有得到明细单据的相关数据,无法保存");
        }


        return 1;
    }


    private String getTestReportIds(List<TestReportIdsVO> testReportIdsVOS) {

        String testReportIdsNames = "";
        if (!testReportIdsVOS.isEmpty()) {
            for (TestReportIdsVO testReportIdsVO : testReportIdsVOS) {
                String names = testReportIdsVO.getCheckProjectId() + ":" + testReportIdsVO.getFileId();
                testReportIdsNames += names + ",";
            }
            testReportIdsNames = testReportIdsNames.substring(0, testReportIdsNames.length() - 1);
        }
        return testReportIdsNames;
    }


    public CheckProjectGoodsVO getCheckProjects(List<String> codes, Long enterpriseId) {

        List<Goods> goods = goodsMapper.selectByCode(codes, enterpriseId);

        List<Long> goodsCheckTypeIds = Goods.getGoodsCheckTypeIds(goods);
        if (org.springframework.util.CollectionUtils.isEmpty(goodsCheckTypeIds)) {
            goodsCheckTypeIds = new ArrayList<>();
        }
        goodsCheckTypeIds.add(53L);

        List<CheckProjectVO> checkProjectVOS = purchaseCheckMapper.selectCheckProjectByCodes(enterpriseId, goodsCheckTypeIds);

        CheckProjectGoodsVO checkProjectGoodsVO = new CheckProjectGoodsVO();

        Map<Long, List<CheckProjectVO>> checkProjectVOMap = new HashMap<>();

        CheckProjectVO specialCheckProjectVO = checkProjectVOS.stream().filter(checkProjectVO -> 53 == checkProjectVO.getType()).findFirst().orElse(null);

        for (Goods g : goods) {

            for (CheckProjectVO checkProjectVO : checkProjectVOS) {

                if (g.getCheckTypeId().equals(Long.parseLong(checkProjectVO.getType().toString()))) {

                    List<CheckProjectVO> projectVOS = checkProjectVOMap.get(g.getId());

                    if (org.springframework.util.CollectionUtils.isEmpty(projectVOS)) {
                        projectVOS = new ArrayList<>();
                        projectVOS.add(checkProjectVO);
                        checkProjectVOMap.put(g.getId(), projectVOS);
                    } else {
                        projectVOS.add(checkProjectVO);
                    }


                }


            }
        }


        for (Goods g : goods) {
            List<CheckProjectVO> projectVOS = checkProjectVOMap.get(g.getId());
            if (CollectionUtils.isNotEmpty(projectVOS)) {
                projectVOS.add(specialCheckProjectVO);
            } else {
                projectVOS = new ArrayList<>();
                projectVOS.add(specialCheckProjectVO);
                checkProjectVOMap.put(g.getId(), projectVOS);
            }
        }

        checkProjectGoodsVO.setCheckProjectVOMap(checkProjectVOMap);

        return checkProjectGoodsVO;
    }


    /**
     * 生成和保存配后退回入库
     *
     * @param userVO
     * @param distrReturnInFormVO
     */
    @Transactional(rollbackFor = Exception.class)
    public DistrReturnInVO generateAndSaveDistrReturnIn(UserVO userVO, DistrReturnInFormVO distrReturnInFormVO) throws Exception {
        DistrReturnInParam distrReturnInParam = new DistrReturnInParam();

        distrReturnInParam.setUserVO(userVO);
        distrReturnInParam.setDistrReturnInFormVO(distrReturnInFormVO);

        String code = orderCodeComponent.generate(
                OrderRule.DISTR_RETURN_IN.getCodePrefix()
                , userVO.getEnterpriseId()
                , userVO.getEnterpriseCode()
        );
        distrReturnInParam.setCode(code);

        DistrReturnCheck distrReturnCheck = distrReturnCheckMapper.selectByPrimaryKey(distrReturnInFormVO.getCheckId());

        distrReturnInParam.setDistrReturnCheck(distrReturnCheck);

        List<DistrReturnCheckDetail> distrReturnCheckDetails = distrReturnCheckDetailMapper.selectByhCheckId(distrReturnInFormVO.getCheckId());

        Long storageManId = distrReturnInFormVO.getStorageManId();

        User user = userMapper.selectByPrimaryKey(storageManId);
        distrReturnInParam.setStorageMan(user);

        //总单为待开票
        DistrReturnIn distrReturnIn = DistrReturnIn.getDistrReturnIn(
                distrReturnInParam
        );

        //细单为待开票，已清数量为0，未清数量为验收的收货数量
        List<DistrReturnInDetail> distrReturnInDetails = getDistrReturnInDetails(
                userVO
                , distrReturnInFormVO
        );
        //总单及细单的状态根据配送类型及要货单位确定已清及未清数量
        //总部配送类型的-自营店为已完成状态，已清数量为入库数量，未清数量为0；加盟店为待开票状态，已清数量为0，未清数量为入库数量
        //门店间调剂的状态为待配送，已清数量为0，未清数量为入库数量
        //直调类型状态为已完成，已清为入库数量，未清为0
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrReturnIn.getRequestUnitId());
        Integer chainType = enterprise.getChainType();
        Integer disType = distrReturnIn.getDistrType();
        if (disType != null) {
            //直调配送
            if (DistributionType.DIRECT_DISTRIBUTION.getCode() == disType) {
                distrReturnIn.setStatus(DistrReturnStatus.FINISHED);
                for (DistrReturnInDetail dtl : distrReturnInDetails) {
                    dtl.setStatus(DistrReturnStatus.FINISHED);
                    dtl.setClearQuantity(dtl.getQuantity());
                    dtl.setUnclearQuantity(BigDecimal.ZERO);
                }
            } else if (DistributionType.SWAP_BETWEEN_STORES.getCode() == disType) {//门店间调剂
                distrReturnIn.setStatus(DistrReturnStatus.WAIT_SEND);
                for (DistrReturnInDetail dtl : distrReturnInDetails) {
                    dtl.setStatus(DistrReturnStatus.WAIT_SEND);
                    dtl.setClearQuantity(BigDecimal.ZERO);
                    dtl.setUnclearQuantity(dtl.getQuantity());
                }
            } else if (DistributionType.DISTRIBUTION_HEAD.getCode() == disType) {//总部配送
                if (chainType != null) {
                    if (ChainType.Selfoperatedshop.getCode() == chainType) {
                        distrReturnIn.setStatus(DistrReturnStatus.FINISHED);
                        for (DistrReturnInDetail dtl : distrReturnInDetails) {
                            dtl.setStatus(DistrReturnStatus.FINISHED);
                            dtl.setClearQuantity(dtl.getQuantity());
                            dtl.setUnclearQuantity(BigDecimal.ZERO);
                        }
                    } else if (ChainType.Division.getCode() == chainType) {
                        distrReturnIn.setStatus(DistrReturnStatus.WAIT_BILL);
                        for (DistrReturnInDetail dtl : distrReturnInDetails) {
                            dtl.setStatus(DistrReturnStatus.WAIT_BILL);
                            dtl.setClearQuantity(BigDecimal.ZERO);
                            dtl.setUnclearQuantity(dtl.getQuantity());
                        }
                    } else {
                        //没有匹配的类型
                    }
                }
            } else {
                //没有匹配的类型
            }
        }

        /**
         * afterGenerateDetailSetReturnIn()  -> save returnIn ->afterSaveDistrReturnInSetDetail()
         */

        distrReturnIn = DistrReturnIn.afterGenerateDetailSetReturnIn(
                distrReturnIn
                , distrReturnInDetails
        );

        for (DistrReturnInDetail distrReturnInDetail : distrReturnInDetails) {

            DistrReturnInDetail.afterGenerateReturnInSetdetail(
                    distrReturnInDetail
                    , distrReturnIn
            );

        }


        distrReturnIn = DistrReturnIn.afterGenerateDetailSetReturnInEnd(distrReturnIn, distrReturnInDetails);


        distrReturnInMapper.insertSelective(distrReturnIn);


        for (DistrReturnInDetail distrReturnInDetail : distrReturnInDetails) {

            distrReturnInDetail = DistrReturnInDetail.afterSaveDistrReturnInSetDetail(distrReturnInDetail
                    , distrReturnIn);
            distrReturnInDetailMapper.insertSelective(distrReturnInDetail);

            LastInPriceVO lastInPriceVO = new LastInPriceVO();

            /**
             更新商品最近入库价相关信息
             */
            lastInPriceVO.setEnterpriseId(userVO.getEnterpriseId());
            lastInPriceVO.setParentId(userVO.getParentId());
            lastInPriceVO.setChainType(userVO.getChainType());
            lastInPriceVO.setGoodsId(distrReturnInDetail.getGoodsId());
            lastInPriceVO.setInTaxRateId(distrReturnInDetail.getTaxRateId());
            lastInPriceVO.setInTaxRate(distrReturnInDetail.getTaxRate());
            lastInPriceVO.setInPrice(distrReturnInDetail.getRealPrice());
            updateLastPriceInfo(lastInPriceVO);
        }


        List<DistrReturnInDetailFormVO> distrReturnInDetailFormVOS = distrReturnInFormVO.getDistrReturnInDetailFormVOS();
        for (DistrReturnInDetailFormVO distrReturnInDetailFormVO : distrReturnInDetailFormVOS) {
            for (DistrReturnCheckDetail distrReturnCheckDetail : distrReturnCheckDetails) {
                if (distrReturnCheckDetail.getId().equals(distrReturnInDetailFormVO.getCheckDtId())) {
                    List<DistrReturnInShelfFormVO> distrReturnInShelfFormVOS = distrReturnInDetailFormVO.getDistrReturnInShelfFormVOS();
                    for (DistrReturnInShelfFormVO distrReturnInShelfFormVO : distrReturnInShelfFormVOS) {
                        distrReturnInShelfFormVO.setGoodsId(distrReturnCheckDetail.getGoodsId());
                    }

                }

            }

        }

        List<DistrReturnInShelf> distrReturnInShelfs = getDistrReturnInShelfs(
                userVO
                , distrReturnInFormVO
                , distrReturnInDetails
                , distrReturnIn
        );

        if (disType != null) {
            //直调配送
            if (DistributionType.DIRECT_DISTRIBUTION.getCode() == disType) {
                for (DistrReturnInShelf shelf : distrReturnInShelfs) {
                    shelf.setStatus(DistrReturnStatus.FINISHED);
                    shelf.setClearQuantity(shelf.getQuantity());
                    shelf.setUnclearQuantity(BigDecimal.ZERO);
                }
            } else if (DistributionType.SWAP_BETWEEN_STORES.getCode() == disType) {//门店间调剂
                for (DistrReturnInShelf shelf : distrReturnInShelfs) {
                    shelf.setStatus(DistrReturnStatus.WAIT_SEND);
                    shelf.setClearQuantity(BigDecimal.ZERO);
                    shelf.setUnclearQuantity(shelf.getQuantity());
                }
            } else if (DistributionType.DISTRIBUTION_HEAD.getCode() == disType) {//总部配送
                if (chainType != null) {
                    if (ChainType.Selfoperatedshop.getCode() == chainType) {
                        for (DistrReturnInShelf shelf : distrReturnInShelfs) {
                            shelf.setStatus(DistrReturnStatus.FINISHED);
                            shelf.setClearQuantity(shelf.getQuantity());
                            shelf.setUnclearQuantity(BigDecimal.ZERO);
                        }
                    } else if (ChainType.Division.getCode() == chainType) {
                        for (DistrReturnInShelf shelf : distrReturnInShelfs) {
                            shelf.setStatus(DistrReturnStatus.WAIT_BILL);
                            shelf.setClearQuantity(BigDecimal.ZERO);
                            shelf.setUnclearQuantity(shelf.getQuantity());
                        }
                    } else {
                        //没有匹配的类型
                    }
                }
            } else {
                //没有匹配的类型
            }
        }

        distrReturnInShelfs.forEach(
                dris -> {
                    distrReturnInShelfMapper.insertSelective(dris);

                    /**
                     *
                     * @Title: updateGoodsDefShelf
                     * @Description: 更新商品默认货位
                     * @param @param enterpriseId 企业ID
                     * @param @param parentId 上级企业ID
                     * @param @param chainType 企业类型
                     * @param @param goodsId 商品ID
                     * @param @param shelfId 货位ID
                     * @param @param shelfName 货位名称
                     * @return void 返回类型
                     * @throws
                     */
                    updateGoodsDefShelf(
                            userVO.getEnterpriseId()
                            , userVO.getParentId()
                            , userVO.getChainType()
                            , dris.getGoodsId()
                            , dris.getShelfId()
                            , dris.getShelfName()
                            , userVO
                    );


                }
        );


        /**
         * 级联修改 入库通知单 验收单的状态
         */
        cascadeSaveCheckAndOrder(distrReturnCheck, distrReturnCheckDetails, distrReturnIn.getStatus());

        /**
         * 生成关键表数据
         */
        OrderModelBuilder builder = new OrderModelBuilder(userVO);
        OrderModel orderModel = builder.buildOrderModel(OrderRule.DISTR_RETURN_IN, distrReturnIn, distrReturnInShelfs);
        inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);


        /**
         * 调用财务业务表数据
         */
        financeComponent.distrReturnInToBalanceAndVoucher(userVO, distrReturnIn);

        Map<String, Long> goodsLotNumMap = new HashMap<>();
        DistrReturnInVO distrReturnInVO = new DistrReturnInVO();
        distrReturnInVO.setDistrReturnIn(distrReturnIn);
        List<DistrReturnInDetail> distrReturnInDetails1 = distrReturnInDetailMapper.selectByReturnId(distrReturnIn.getId());
        distrReturnInVO.setDistrReturnInDetails(distrReturnInDetails1);
        List<OrderDtlVO> orderDtlList = orderModel.getOrderDtlList();
        for (OrderDtlVO orderDtlVO : orderDtlList) {
            for (DistrReturnInShelf distrReturnInShelf : distrReturnInShelfs) {
                if (orderDtlVO.getOrderDtlId().equals(distrReturnInShelf.getId())) {

                    goodsLotNumMap.put(distrReturnInShelf.getGoodsId() + "_" + distrReturnInShelf.getLotNumber(), orderDtlVO.getLotId());
                }
            }

        }

        List<DistrReturnInShelf> distrReturnInShelves = distrReturnInShelfMapper.selectByReturnId(distrReturnIn.getId());
        distrReturnInVO.setDistrReturnInShelfs(distrReturnInShelves);
        distrReturnInVO.setGoodsLotNumMap(goodsLotNumMap);
        return distrReturnInVO;
    }


    public List<DistrReturnInDetail> getDistrReturnInDetails(UserVO userVO, DistrReturnInFormVO distrReturnInFormVO) throws Exception {

        List<DistrReturnInDetail> distrReturnInDetails = new ArrayList<>();

        List<DistrReturnInDetailFormVO> distrReturnInDetailFormVOS = distrReturnInFormVO.getDistrReturnInDetailFormVOS();

        List<DistrReturnCheckDetail> distrReturnCheckDetails = distrReturnCheckDetailMapper.selectByhCheckId(distrReturnInFormVO.getCheckId());

        List<Long> taxRateIds = DistrReturnInDetailFormVO.getTaxRateIds(distrReturnInDetailFormVOS);

        List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectByIds(taxRateIds);


        for (DistrReturnInDetailFormVO distrReturnInDetailFormVO : distrReturnInDetailFormVOS) {

            DistrReturnInDetailParam distrReturnInDetailParam = new DistrReturnInDetailParam();

            distrReturnInDetailParam.setUserVO(userVO);

            distrReturnInDetailParam.setDistrReturnInFormVO(distrReturnInDetailFormVO);

            distrReturnInDetailParam.setDistrReturnCheckDetails(distrReturnCheckDetails);

            distrReturnInDetailParam.setGoodsTaxRates(goodsTaxRates);


            DistrReturnInDetail distrReturnInDetail = DistrReturnInDetail.getDistrReturnInDetail(
                    distrReturnInDetailParam
            );
            distrReturnInDetails.add(distrReturnInDetail);
        }

        return distrReturnInDetails;

    }

    @Transactional(rollbackFor = Exception.class)
    public void cascadeSaveCheckAndOrder(DistrReturnCheck distrReturnCheck, List<DistrReturnCheckDetail> distrReturnCheckDetails, Integer status) {

        DistrReturnCheck newCheck = new DistrReturnCheck();

        newCheck.setId(distrReturnCheck.getId());
        newCheck.setStatus(status);

        distrReturnCheckMapper.updateByPrimaryKeySelective(newCheck);

        distrReturnCheckDetails.forEach(disr -> {

            DistrReturnCheckDetail newDetail = new DistrReturnCheckDetail();
            newDetail.setId(disr.getId());
            newDetail.setStatus(newCheck.getStatus());
            newDetail.setUnclearQuantity(BigDecimal.ZERO);
            newDetail.setClearQuantity(disr.getReceiveQuantity());

            distrReturnCheckDetailMapper.updateByPrimaryKeySelective(newDetail);
        });

        List<DistrReturnCheckLot> shelfLots = distrReturnCheckLotMapper.selectByCheckId(distrReturnCheck.getId());
        shelfLots.forEach(shelflot -> {

            DistrReturnCheckLot newShelflot = new DistrReturnCheckLot();
            newShelflot.setId(shelflot.getId());
            newShelflot.setStatus(newCheck.getStatus());
            newShelflot.setUnclearQuantity(BigDecimal.ZERO);
            newShelflot.setClearQuantity(shelflot.getReceiveQuantity());

            distrReturnCheckLotMapper.updateByPrimaryKeySelective(newShelflot);
        });

        Long baseOrderId = distrReturnCheck.getBaseOrderId();

        DistrReturnReceive distrReturnReceive = distrReturnReceiveMapper.selectByPrimaryKey(baseOrderId);
        if (null != distrReturnReceive) {
            DistrReturnReceive newDistrReturnReceive = new DistrReturnReceive();
            newDistrReturnReceive.setId(distrReturnReceive.getId());
            newDistrReturnReceive.setStatus(status);
            distrReturnReceiveMapper.updateByPrimaryKeySelective(newDistrReturnReceive);

            List<DistrReturnReceiveDetailVO> distrReturnReceiveDetailVOS = distrReturnReceiveDetailMapper.selectByReceiveId(distrReturnReceive.getId(), distrReturnReceive.getEnterpriseId());
            for (DistrReturnReceiveDetailVO distrReturnReceiveDetailVO : distrReturnReceiveDetailVOS) {
                DistrReturnReceiveDetail newDistrReturnReceiveDetail = new DistrReturnReceiveDetail();
                newDistrReturnReceiveDetail.setId(distrReturnReceiveDetailVO.getId());
                newDistrReturnReceiveDetail.setStatus(status);
                distrReturnReceiveDetailMapper.updateByPrimaryKeySelective(newDistrReturnReceiveDetail);
            }
        }

        /**
         * 修改入库通知单的状态
         */
        distrComponent.updateInNoticeStatus4Parent(distrReturnCheck.getNoticeId(), status, distrReturnCheck.getEnterpriseId());
    }

    public List<DistrReturnInShelf> getDistrReturnInShelfs(UserVO userVO
            , DistrReturnInFormVO distrReturnInFormVO
            , List<DistrReturnInDetail> distrReturnInDetails
            , DistrReturnIn distrReturnIn) throws Exception {

        List<DistrReturnInShelf> distrReturnInShelves = new ArrayList<>();

        List<DistrReturnInDetailFormVO> distrReturnInDetailFormVOS = distrReturnInFormVO.getDistrReturnInDetailFormVOS();

        List<DistrReturnCheckLot> distrReturnCheckLots = distrReturnCheckLotMapper.selectByCheckId(distrReturnInFormVO.getCheckId());

        List<Long> shelfIds = DistrReturnInDetailFormVO.getShelfIds(distrReturnInDetailFormVOS);

        List<WarehouseShelf> warehouseShelves = warehouseShelfMapper.selectByIds(shelfIds);

        for (DistrReturnInDetailFormVO distrReturnInDetailFormVO : distrReturnInDetailFormVOS) {

            for (DistrReturnInDetail distrReturnInDetail : distrReturnInDetails) {

                if (distrReturnInDetailFormVO.getCheckDtId().equals(distrReturnInDetail.getBaseOrderDtlId())) {

                    List<DistrReturnInShelfFormVO> distrReturnInShelfFormVOS = distrReturnInDetailFormVO.getDistrReturnInShelfFormVOS();

                    for (DistrReturnInShelfFormVO distrReturnInShelfFormVO : distrReturnInShelfFormVOS) {

                        for (DistrReturnCheckLot distrReturnCheckLot : distrReturnCheckLots) {

                            if (distrReturnCheckLot.getLotNumber().equals(distrReturnInShelfFormVO.getLotNumber()) && distrReturnCheckLot.getGoodsId().equals(distrReturnInShelfFormVO.getGoodsId())) {

                                DistrReturnInShelfParam distrReturnInShelfParam = new DistrReturnInShelfParam();
                                distrReturnInShelfParam.setUserVO(userVO);

                                distrReturnInShelfParam.setDistrReturnInShelfFormVO(distrReturnInShelfFormVO);

                                distrReturnInShelfParam.setDistrReturnInDetail(distrReturnInDetail);

                                distrReturnInShelfParam.setDistrReturnIn(distrReturnIn);

                                distrReturnInShelfParam.setDistrReturnCheckLot(distrReturnCheckLot);

                                for (WarehouseShelf warehouseShelf : warehouseShelves) {
                                    if (warehouseShelf.getId().equals(distrReturnInShelfFormVO.getShelfId())) {

                                        distrReturnInShelfParam.setWarehouseShelf(warehouseShelf);
                                    }

                                }
                                if (null == distrReturnInShelfParam.getWarehouseShelf()) {
                                    throw new BusinessException("商品编号" + distrReturnInShelfParam.getDistrReturnInDetail().getGoodsCode() + "没有默认货位!");
                                }

                                DistrReturnInShelf distrReturnInShelf = DistrReturnInShelf.getDistrReturnInShelf(
                                        distrReturnInShelfParam
                                );


                                distrReturnInShelves.add(distrReturnInShelf);

                            }
                        }

                    }

                }

            }

        }

        return distrReturnInShelves;

    }


    @Transactional(rollbackFor = Exception.class)
    public PurchaseReturnsVO generateAndSavePurchaseReturn(UserVO userVO, PurchaseReturnSaveOrUpdateVO returnSaveOrUpdateVO, boolean isAdd, boolean isOut) throws Exception {

        PurchaseReturn oldPurchaseReturn = new PurchaseReturn();
        List<PurchaseReturnDetail> oldPurchaseReturnDetails = new ArrayList<>();

        PurchaseReturnOther oldPurchaseReturnOther = new PurchaseReturnOther();
        if (!isAdd) {

            oldPurchaseReturn = purchaseReturnMapper.selectByPrimaryKey(returnSaveOrUpdateVO.getId());

            List<PurchaseReturnDetailSaveOrUpdateVO> purchaseReturnDetailSaveOrUpdateVOS = returnSaveOrUpdateVO.getPurchaseReturnDetailSaveOrUpdateVOS();
            for (PurchaseReturnDetailSaveOrUpdateVO pr : purchaseReturnDetailSaveOrUpdateVOS) {
                PurchaseReturnDetail purchaseReturnDetail = purchaseReturnDetailMapper.selectByPrimaryKey(pr.getId());
                oldPurchaseReturnDetails.add(purchaseReturnDetail);
            }

            oldPurchaseReturnOther = purchaseReturnOtherMapper.selectByPrimaryKey(returnSaveOrUpdateVO.getPurchaseReturnOtherSaveOrUpdateVO().getId());

        }
        List<PurchaseReturnDetailSaveOrUpdateVO> detailVos = returnSaveOrUpdateVO.getPurchaseReturnDetailSaveOrUpdateVOS();
        Long baseOrderId = returnSaveOrUpdateVO.getBaseOrderId();

        List<PurchaseReturnDetail> purchaseReturnDetails = new ArrayList<>();
        List<PurchaseInStorageDetail> purchaseInStorageDetails = new ArrayList<>();
        /**
         * 生成一部分头信息
         */
        SaveOrUpdateBzVO saveOrUpdateBzVO = new SaveOrUpdateBzVO();
        saveOrUpdateBzVO.setUserVO(userVO);

        Long returnManId = returnSaveOrUpdateVO.getReturnManId();
        SupplierSaler supplierSaler = supplierSalerMapper.selectByPrimaryKey(returnSaveOrUpdateVO.getSupplierSalerId());
        saveOrUpdateBzVO.setSupplierUser(supplierSaler);

        User user = userMapper.selectByPrimaryKey(returnManId);
        saveOrUpdateBzVO.setUser(user);

        saveOrUpdateBzVO.setPurchaseReturnVO(returnSaveOrUpdateVO);

        /**
         * 购进退出单号
         */
        if (isAdd) {
            String code = orderCodeComponent.generate(
                    OrderRule.PURCHASE_RETURN.getCodePrefix()
                    , userVO.getEnterpriseId()
                    , userVO.getEnterpriseCode()
            );
            saveOrUpdateBzVO.setCode(code);
        } else {
            saveOrUpdateBzVO.setCode(oldPurchaseReturn.getCode());
        }


        PurchaseInStorage purchaseInStorage = null;
        if (null != baseOrderId && 0 != baseOrderId) {
            purchaseInStorage = purchaseInStorageMapper.selectByPrimaryKey(baseOrderId);
            purchaseInStorageDetails = purchaseInStorageDetailMapper.selectByInStorageId(baseOrderId);
        }
        saveOrUpdateBzVO.setPurchaseInStorage(purchaseInStorage);

        Supplier supplier = supplierMapper.selectByPrimaryKey(returnSaveOrUpdateVO.getSupplierId());
        saveOrUpdateBzVO.setSupplier(supplier);

        List<PurchaseReturnDetailSaveOrUpdateVO> vos = returnSaveOrUpdateVO.getPurchaseReturnDetailSaveOrUpdateVOS();
        List<BigDecimal> amounts = new ArrayList<>();
        for (PurchaseReturnDetailSaveOrUpdateVO vo : vos) {
            /**.
             *
             * 金额（整单优惠前金额）
             */
            BigDecimal amount = PurchaseReturnDetail.calcLineDiscount(vo);
            amounts.add(amount);
        }


        PurchaseReturn purchaseReturn = PurchaseReturn.getPurchaseReturn4VO(saveOrUpdateBzVO, isAdd);

        /**
         * 整单优惠前金额合计
         */
        BigDecimal amountTotal = PurchaseReturn.calcTotalAmount4List(amounts);
        purchaseReturn.setAmountTotal(amountTotal);

        /**
         * 生成明细行
         */

        Set<Long> goodsIds = PurchaseReturnDetailSaveOrUpdateVO.getGoodsIds(detailVos);
        List<Long> cs = new ArrayList(goodsIds);

        Long enterpriseId = userVO.getEnterpriseId();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        if (!enterprise.getChainType().equals(ChainType.Headquarters.getCode())) {
            enterpriseId = enterprise.getParentId();
        }
        if (org.springframework.util.CollectionUtils.isEmpty(goodsIds)) {
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK, "商品未找到");
        }
        List<Goods> goodsList = goodsMapper.selectByIds(cs, enterpriseId);

        Set<Long> lotNumberIds = PurchaseReturnDetailSaveOrUpdateVO.getLotNumberIds(detailVos);
        List<Long> lns = new ArrayList<>(lotNumberIds);
        List<LotNumber> lotNumbers = lotNumberMapper.selectByIds(lns);


        Set<Long> goodTaxRateIds = PurchaseReturnDetailSaveOrUpdateVO.getGoodTaxRateIds(detailVos);
        List<GoodsTaxRate> goodsTaxRates = goodsComponent.getGoodsTaxRates(goodTaxRateIds);
        for (PurchaseReturnDetailSaveOrUpdateVO vo : detailVos) {
            if (isAdd) {

            }
            SaveOrUpdateDetailBzVO saveOrUpdateDetailBzVO = new SaveOrUpdateDetailBzVO();
            saveOrUpdateDetailBzVO.setUserVO(userVO);
            vo.setLineNum(vo.getLineNum());
            saveOrUpdateDetailBzVO.setDetailVO(vo);
            saveOrUpdateDetailBzVO.setPurchaseReturn(purchaseReturn);
            for (PurchaseInStorageDetail bi : purchaseInStorageDetails) {
				/*if(bi.getGoodsId().equals(vo.getGoodsId())){
					saveOrUpdateDetailBzVO.setPurchaseInStorageDetail(bi);
				}*/

                if (bi.getId().equals(vo.getBaseOrderDtId())) {
                    saveOrUpdateDetailBzVO.setPurchaseInStorageDetail(bi);
                }
            }
            for (Goods goods : goodsList) {
                if (goods.getId().equals(vo.getGoodsId())) {
                    saveOrUpdateDetailBzVO.setGoods(goods);
                }
            }

            for (LotNumber lotNumber : lotNumbers) {
                if (lotNumber.getGoodsId().equals(vo.getGoodsId()) && lotNumber.getId().equals(vo.getLotNumberId())) {
                    saveOrUpdateDetailBzVO.setLotNumber(lotNumber);
                }
            }
            for (GoodsTaxRate goodsTaxRate : goodsTaxRates) {
                if (vo.getTaxRateId().equals(goodsTaxRate.getId())) {
                    saveOrUpdateDetailBzVO.setTaxRates(goodsTaxRate);
                }
            }
            PurchaseReturnDetail purchaseReturnDetail4VO = PurchaseReturnDetail.getPurchaseReturnDetail4VO(saveOrUpdateDetailBzVO, isAdd);

            purchaseReturnDetails.add(purchaseReturnDetail4VO);
        }

        /**
         * 补足头信息需要明细行统计的部分
         */

        purchaseReturn = PurchaseReturn.setPurchaseReturn4Detail(
                purchaseReturn
                , purchaseReturnDetails
        );

        /**
         * 保存明细行
         */
        if (purchaseReturn.getId() == null || purchaseReturn.getId() == 0) {
            purchaseReturnMapper.insertSelective(purchaseReturn);
        } else {
            purchaseReturnMapper.updateByPrimaryKeySelective(purchaseReturn);
        }

        // 释放原来购进退出单锁定的库存
        releaseStock(userVO, purchaseReturn);

        /**
         * 补足明细行需要头信息组成的部分
         */
        for (PurchaseReturnDetail detail : purchaseReturnDetails) {
            PurchaseReturnDetail purchaseReturnDetail = PurchaseReturnDetail.afterSetPurchaseReturnDetail(
                    detail
                    , purchaseReturn
            );

            if (null != purchaseReturnDetail.getId() && 0 != purchaseReturnDetail.getId()) {
                purchaseReturnDetailMapper.updateByPrimaryKeySelective(purchaseReturnDetail);
            } else {
                purchaseReturnDetailMapper.insertSelective(purchaseReturnDetail);
            }

            // 根据新的明细列表重新锁定库存
            lockStock(userVO, detail);
        }

        /**
         * 配送和结算方式
         */
        PurchaseReturnOtherSaveOrUpdateVO purchaseReturnOtherSaveOrUpdateVO = returnSaveOrUpdateVO.getPurchaseReturnOtherSaveOrUpdateVO();

        if (purchaseReturnOtherSaveOrUpdateVO.getCarriageMode() != DistributionType.DIRECT_DISTRIBUTION.getCode()) {
            //设置结算单位的信息为总部,配送类型不是直调配送的结算单位均为总部
            purchaseReturnOtherSaveOrUpdateVO.setSettlementUnitId(userVO.getEnterpriseId());
            purchaseReturnOtherSaveOrUpdateVO.setSettlementUnit(userVO.getEnterpriseName());
        }

        SaveOrUpdateOtherBzVO saveOrUpdateOtherBzVO = new SaveOrUpdateOtherBzVO();
        saveOrUpdateOtherBzVO.setUserVO(userVO);
        saveOrUpdateOtherBzVO.setOtherVO(purchaseReturnOtherSaveOrUpdateVO);
        if (null != purchaseInStorage) {
            Long purchaseOrderId = purchaseInStorage.getPurchaseOrderId();
            PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchaseOrderId);
            saveOrUpdateOtherBzVO.setPurchaseOrder(purchaseOrder);
        }

        saveOrUpdateOtherBzVO.setPurchaseReturn(purchaseReturn);

        PurchaseReturnOther purchaseReturnOther = PurchaseReturnOther.getPurchaseReturnOther4VO(saveOrUpdateOtherBzVO);
        if (purchaseReturnOther.getId() != null && purchaseReturnOther.getId() != 0) {

            purchaseReturnOtherMapper.updateByPrimaryKeySelective(purchaseReturnOther);
        } else {
            purchaseReturnOtherMapper.insertSelective(purchaseReturnOther);
        }

        /**
         * 添加/修改完价格调整单后需要申请一条审批工作流
         */
        /*        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());*/
        if (!isOut) {
            SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(
                    userVO.getEnterpriseId(),
                    enterprise.getName(),
                    userVO.getUserId(),
                    userVO.getUserName(),
                    userVO.getChainType(),
                    userVO.getParentId(),
                    userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                    ApprovalFlowContentModel.getReturnCode(),
                    purchaseReturn.getId(),
                    "",
                    ""
            );

            approvalFlowComponent.apply(submitApprovalFlowVO, userVO);

        }


        List<PurchaseReturnModifyRecord> saveModifyRecord = new ArrayList<>();

        List<PurchaseReturnModifyRecord> purchaseReturnModifyRecord = getPurchaseReturnModifyRecord(
                userVO,
                purchaseReturn
                , oldPurchaseReturn
                , new Date()
        );
        saveModifyRecord.addAll(purchaseReturnModifyRecord);

        for (PurchaseReturnDetail pr : purchaseReturnDetails) {
            for (PurchaseReturnDetail oldPr : oldPurchaseReturnDetails) {

                if (oldPr.getId().equals(pr.getId())) {
                    List<PurchaseReturnModifyRecord> purchaseReturnDetailModifyRecord = getPurchaseReturnDetailModifyRecord(
                            userVO
                            , oldPurchaseReturn
                            , pr
                            , oldPr
                            , new Date()
                    );

                    saveModifyRecord.addAll(purchaseReturnDetailModifyRecord);
                }
            }

        }

        List<PurchaseReturnModifyRecord> purchaseReturnModifyRecord1 = getPurchaseReturnModifyRecord(
                userVO
                , purchaseReturnOther
                , oldPurchaseReturn
                , oldPurchaseReturnOther
                , new Date()
        );
        saveModifyRecord.addAll(purchaseReturnModifyRecord1);

        saveModifyRecord.forEach(
                pr -> {
                    purchaseReturnModifyRecordMapper.insertSelective(pr);
                }
        );


        PurchaseReturnsVO purchaseReturnsVO = new PurchaseReturnsVO();
        purchaseReturnsVO.setPurchaseReturn(purchaseReturn);
        purchaseReturnsVO.setPurchaseReturnDetails(purchaseReturnDetails);
        purchaseReturnsVO.setPurchaseReturnOther(purchaseReturnOther);


        // 删除redis 缓存
        if (returnSaveOrUpdateVO.getRedisKeyValue() != null) {
            redisComponent.removeDraftCacheVO(userVO.getEnterpriseId(), OrderRule.PURCHASE_RETURN.getCodePrefix(), returnSaveOrUpdateVO.getRedisKeyValue());
        }

        return purchaseReturnsVO;
    }


    public List<PurchaseReturnModifyRecord> getPurchaseReturnModifyRecord(UserVO userVO,
                                                                          PurchaseReturn newPurchaseReturn, PurchaseReturn oldPurchaseReturn, Date updateDate) throws Exception {

        Map<String, Object> newPurchaseReturMap = modifyRecordCompoent.getFieldsMap(newPurchaseReturn);


        Map<String, Object> oldPurchaseReturnMap = modifyRecordCompoent.getFieldsMap(oldPurchaseReturn);

        Map<String, String> fieldMustMap = fieldMustMap();

        List<PurchaseReturnModifyRecord> purchaseReturnRecord = getModifyRecordList(userVO, oldPurchaseReturn, "saas_purchase_return", oldPurchaseReturn.getId()
                , updateDate, oldPurchaseReturnMap, newPurchaseReturMap, fieldMustMap);

        return purchaseReturnRecord;
    }

    public List<PurchaseReturnModifyRecord> getPurchaseReturnModifyRecord(UserVO userVO, PurchaseReturnOther newPurchaseReturnOther
            , PurchaseReturn oldPurchaseReturn, PurchaseReturnOther oldPurchaseReturnOther, Date updateDate) throws Exception {

        Map<String, Object> newPurchaseReturnOtherMap = modifyRecordCompoent.getFieldsMap(newPurchaseReturnOther);


        Map<String, Object> oldPurchaseReturnOtherlMap = modifyRecordCompoent.getFieldsMap(oldPurchaseReturnOther);

        Map<String, String> fieldMustMap = fieldMustMap();


        List<PurchaseReturnModifyRecord> purchaseReturnOhterRecord = getModifyRecordList(userVO, oldPurchaseReturn, "saas_purchase_return_other", newPurchaseReturnOther.getId()
                , updateDate, oldPurchaseReturnOtherlMap, newPurchaseReturnOtherMap, fieldMustMap);

        return purchaseReturnOhterRecord;
    }

    public List<PurchaseReturnModifyRecord> getPurchaseReturnDetailModifyRecord(UserVO userVO, PurchaseReturn oldPurchaseReturn,
                                                                                PurchaseReturnDetail newPurchaseReturnDetail, PurchaseReturnDetail oldPurchaseReturnDetail, Date updateDate) throws Exception {

        Map<String, Object> newPurchaseReturDetailMap = modifyRecordCompoent.getFieldsMap(newPurchaseReturnDetail);


        Map<String, Object> oldPurchaseReturnDetailMap = modifyRecordCompoent.getFieldsMap(oldPurchaseReturnDetail);

        Map<String, String> fieldMustMap = fieldMustMap();


        List<PurchaseReturnModifyRecord> purchaseReturnDetailRecord = getModifyRecordList(userVO, oldPurchaseReturn, "saas_purchase_return_detail", oldPurchaseReturnDetail.getId()
                , updateDate, oldPurchaseReturnDetailMap, newPurchaseReturDetailMap, fieldMustMap);
        return purchaseReturnDetailRecord;
    }

    private List<PurchaseReturnModifyRecord> getModifyRecordList(
            UserVO userVO, PurchaseReturn priceOrder, String tableName, Long keyId, Date updateTime
            , Map<String, Object> valMap
            , Map<String, Object> newMap, Map<String, String> fieldMustMap) {

        List<PurchaseReturnModifyRecord> modifyRecordWithBLOBs = new ArrayList<>();

        for (Map.Entry<String, String> entry : fieldMustMap.entrySet()) {
            Object obj = valMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if (null != obj && null != newObj && !isModify(newObj, obj)) {
                PurchaseReturnModifyRecord purchaseReturnModifyRecord = new PurchaseReturnModifyRecord();
                purchaseReturnModifyRecord.setReturnId(priceOrder.getId());
                purchaseReturnModifyRecord.setEnterpriseId(priceOrder.getEnterpriseId());
                if (userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
                    purchaseReturnModifyRecord.setParentId(0L);
                } else {
                    purchaseReturnModifyRecord.setParentId(userVO.getParentId());
                }
                purchaseReturnModifyRecord.setTableName(tableName);
                purchaseReturnModifyRecord.setKeyId(keyId);
                purchaseReturnModifyRecord.setColumnEnName(entry.getKey());
                purchaseReturnModifyRecord.setColumnChName(entry.getValue());
                purchaseReturnModifyRecord.setUpdateTime(updateTime);
                purchaseReturnModifyRecord.setCreaterId(userVO.getUserId());
                purchaseReturnModifyRecord.setCreaterCode(userVO.getUserCode());
                purchaseReturnModifyRecord.setCreaterName(userVO.getUserName());
                purchaseReturnModifyRecord.setCreateTime(new Date());
                purchaseReturnModifyRecord.setModifierId(userVO.getUserId());
                purchaseReturnModifyRecord.setModifierCode(userVO.getUserCode());
                purchaseReturnModifyRecord.setModifierName(userVO.getUserName());
                purchaseReturnModifyRecord.setUpdateTime(new Date());
                purchaseReturnModifyRecord.setOldContent(obj.toString());
                purchaseReturnModifyRecord.setNewContent(newObj.toString());
                modifyRecordWithBLOBs.add(purchaseReturnModifyRecord);
            }
        }

        return modifyRecordWithBLOBs;
    }

    public boolean isModify(Object newObj, Object oldObj) {

        if (newObj instanceof BigDecimal) {

            return ((BigDecimal) newObj).compareTo((BigDecimal) oldObj) == 0;
        } else {
            return newObj.equals(oldObj);
        }

    }


    private Map<String, String> fieldMustMap() {
        Map<String, String> fieldNames = new HashMap();

        /**
         * 企业ID
         */
        fieldNames.put("enterpriseId", "企业ID");
        fieldNames.put("parentId", "上级企业ID");
        fieldNames.put("orderType", "单据类型");
        fieldNames.put("code", "购进退出单号");
        fieldNames.put("returnDate", "购进退出日期");
        fieldNames.put("baseOrderId", "基础单据ID");
        fieldNames.put("baseOrderType", "基础单据类型");


        fieldNames.put("baseOrderCode", "基础单据编码");
        fieldNames.put("baseOrderDate", "基础单据日期");
        fieldNames.put("returnType", "退货类型（0-质量问题退货；1-非质量问题退货");
        fieldNames.put("supplierId", "供货单位ID");
        fieldNames.put("supplierCode", "供货单位编码");
        fieldNames.put("supplierName", "供货单位名称");
        fieldNames.put("telPhone", "联系电话");
        fieldNames.put("returnManId", "退货人员ID");


        fieldNames.put("returnManCode", "退货人员编码");
        fieldNames.put("returnManName", "退货人员ID");
        fieldNames.put("returnManId", "退货人员名称");
        fieldNames.put("supplierSalerId", "供货单位人员");
        fieldNames.put("supplierSalerCode", "供货单位编码");
        fieldNames.put("supplierSalerName", "供货单位姓名");
        fieldNames.put("supplierSalerPhone", "供货单位电话");
        fieldNames.put("quantityTotal", "数量合计");
        fieldNames.put("varietiesQuantity", "品种数量");


        fieldNames.put("amountTotal", "金额合计（整单优惠前的金额合计）");
        fieldNames.put("wholeDiscount", "整单折扣（%）");
        fieldNames.put("wholeDiscountAmount", "整单优惠金额");
        fieldNames.put("realAmountTotal", "实际金额合计");
        fieldNames.put("notaxRealAmountTotal", "不含税金额合计");
        fieldNames.put("taxAmountTotal", "税额合计");


        fieldNames.put("returnId", "购进退出单ID");
        fieldNames.put("orderType", "单据（购进退出单）类型（5214）");
        fieldNames.put("returnCode", "购进退出单编号");
        fieldNames.put("returnDate", "购进退出单日期");
        fieldNames.put("baseOrderDtlId", "基础单据明细ID");
        fieldNames.put("baseOrderId", "基础单据ID");
        fieldNames.put("baseOrderType", "基础单据类型");
        fieldNames.put("baseOrderCode", "基础单据编码");
        fieldNames.put("baseOrderDate", "基础单据日期");
        fieldNames.put("goodsId", "商品ID");
        fieldNames.put("goodsCode", "商品编码");
        fieldNames.put("barcode", "条形码");


        fieldNames.put("goodsName", "商品名称");
        fieldNames.put("goodsGenericName", "商品通用名称");
        fieldNames.put("dosageId", "剂型ID");
        fieldNames.put("dosageName", "剂型名称");
        fieldNames.put("unitId", "单位ID");
        fieldNames.put("unitName", "单位名称");
        fieldNames.put("goodsSpecification", "商品规格");
        fieldNames.put("manufacturerId", "生产厂商ID");
        fieldNames.put("manufacturer", "生产厂商");
        fieldNames.put("goodsPlace", "商品产地");
        fieldNames.put("approvalNumber", "批准文号");
        fieldNames.put("lotNumber", "批号");
        fieldNames.put("lotId", "批号id");


        fieldNames.put("productDate", "生产日期");
        fieldNames.put("validDate", "有效期");
        fieldNames.put("quantity", "数量");
        fieldNames.put("unitPrice", "单价");
        fieldNames.put("lineDiscount", "行折扣（%");
        fieldNames.put("amount", "金额（整单优惠前金额）");
        fieldNames.put("wholeDiscount", "整单折扣（%）");
        fieldNames.put("lineDiscountAmount", "行优惠（整单优惠分摊到行的金额）");

        fieldNames.put("realPrice", "实际单价（实际金额/数量）");
        fieldNames.put("realAmount", "实际金额");
        fieldNames.put("taxRate", "进项税");
        fieldNames.put("taxRateId", "税率id");

        fieldNames.put("notaxRealPrice", "不含税实际单价");
        fieldNames.put("notaxRealAmount", "不含税实际金额");
        fieldNames.put("taxAmount", "税额");
        fieldNames.put("unclearQuantity", "未清数量");
        fieldNames.put("clearQuantity", "已清数量");
        fieldNames.put("returnReasonIds", "退货原因ID集合，多个用逗号隔开");
        fieldNames.put("status", "状态");
        fieldNames.put("remark", "备注");


        fieldNames.put("settlementType", "结算方式（0-现结；1-账期）");
        fieldNames.put("settlementUnit", "结算单位");
        fieldNames.put("settlementUnitPhone", "结算单位电话");
        fieldNames.put("settlementUnitAddress", "结算单位地址");
        fieldNames.put("accountName", "开户户名");
        fieldNames.put("bank", "开户银行");

        fieldNames.put("invoiceType", "发票类型（0-普通发票；1-增值税发票）");
        fieldNames.put("taxpayerCode", "纳税人识别号");
        fieldNames.put("carriageMode", "承运方式（0-配送；1-委托运输；2-自提）");
        fieldNames.put("carriageUnit", "承运单位");
        fieldNames.put("transportMode", "运输方式（0-陆运；1-空运；2-海运）");
        fieldNames.put("tempControlMode", "温控方式（0-冷藏车；1-冷藏箱；2-保温箱）");
        fieldNames.put("receiver", "收货人员");
        fieldNames.put("receivePhone", "收货人员电话");
        fieldNames.put("receiveAddress", "收货地址");


        fieldNames.put("orderId", "订单ID");
        fieldNames.put("orderCode", "订单单号");
        fieldNames.put("orderDate", "订单日期");
        fieldNames.put("outFlag", "出库标志（0-未出库；1-已出库）");


        return fieldNames;
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
        lockStockAndCost(lockQtyArgVO);
    }

    public void releaseStock(UserVO userVO, PurchaseReturn purchaseReturn) {
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setEnterpriseId(purchaseReturn.getEnterpriseId());
        lockQtyArgVO.setParentId(purchaseReturn.getParentId());
        lockQtyArgVO.setBaseOrderId(purchaseReturn.getId());
        lockQtyArgVO.setBaseOrderType(purchaseReturn.getOrderType());
        lockQtyArgVO.setUser(userVO);
        releaseStockAndCost(lockQtyArgVO);
    }


    /**
     * 生成和保存购进退出出库
     *
     * @param purchaseReturnOutInfoVO
     * @param userVO
     */
    public PurchaseReturnOut generateAndSavePurchaseReturnOut(RequestPurchaseReturnOutInfoVO purchaseReturnOutInfoVO, UserVO userVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        Long returnId = purchaseReturnOutInfoVO.getId();//购进退出id

        //购进退出出库单号
        String code = orderCodeComponent.generate(OrderRule.PURCHASE_RETURN_OUT.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());

        //出库明细
        List<PurchaseReturnDetailOutVO> purchaseReturnDetailOutVOList = purchaseReturnOutInfoVO.getPurchaseReturnDetailVOList();


        PurchaseReturnOut purchaseReturnOut = new PurchaseReturnOut();
        //查询质量控制
        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);


//        if(returnOutVO != null){
        BeanUtils.copyProperties(purchaseReturnOutInfoVO, purchaseReturnOut);
        PurchaseReturnOut returnOut = (PurchaseReturnOut) EntityUtils.reflectAddSetDefaultValue(PurchaseReturnOut.class, userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(returnOut, purchaseReturnOut);
        PurchaseReturn purchaseReturn = purchaseReturnMapper.selectByPrimaryKey(returnId);//购进退出单
        purchaseReturnOut.setOutDate(new Date());
        purchaseReturnOut.setBaseOrderId(purchaseReturn.getId());
        purchaseReturnOut.setBaseOrderCode(purchaseReturn.getCode());
        purchaseReturnOut.setBaseOrderDate(purchaseReturn.getReturnDate());
        purchaseReturnOut.setBaseOrderType(purchaseReturn.getOrderType());
        purchaseReturnOut.setSupplierCode(purchaseReturn.getSupplierCode());
        purchaseReturnOut.setSupplierName(purchaseReturn.getSupplierName());
        purchaseReturnOut.setSupplierId(purchaseReturn.getSupplierId());
        purchaseReturnOut.setSupplierSalerId(purchaseReturn.getSupplierSalerId());
        purchaseReturnOut.setSupplierSalerCode(purchaseReturn.getSupplierSalerCode());
        purchaseReturnOut.setSupplierSalerName(purchaseReturn.getSupplierSalerName());
        purchaseReturnOut.setReturnType(purchaseReturn.getReturnType());
        purchaseReturnOut.setReturnManId(purchaseReturn.getReturnManId());
        purchaseReturnOut.setReturnManCode(purchaseReturn.getReturnManCode());
        purchaseReturnOut.setReturnManName(purchaseReturn.getReturnManName());
        purchaseReturnOut.setReturnDate(purchaseReturn.getReturnDate());
        purchaseReturnOut.setFlowCode(purchaseReturnOutInfoVO.getFlowCode());
        purchaseReturnOut.setVarietiesQuantity(purchaseReturnDetailOutVOList != null ? purchaseReturnDetailOutVOList.size() : 0);//品种合计
        //数量合计
        BigDecimal allQuantity = purchaseReturnDetailOutVOList.stream().filter(Objects::nonNull)
                .filter(c -> c.getQuantity() != null).map(PurchaseReturnDetailOutVO::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal allQuantity = BigDecimal.ZERO;
//        for (PurchaseReturnDetailOutVO item : purchaseReturnDetailOutVOList) {
//            allQuantity = allQuantity.add(item.getQuantity());
//        }
        purchaseReturnOut.setQuantityTotal(allQuantity);

        //金额计算
        purchaseReturnOut.setRealAmountTotal(BigDecimal.ZERO);
        purchaseReturnOut.setNotaxRealAmountTotal(BigDecimal.ZERO);
        purchaseReturnOut.setTaxAmountTotal(BigDecimal.ZERO);
        //出库人员
        if (mangeConfig.getBusinessControl() == 0) {//关闭
            Long outManId = purchaseReturnOutInfoVO.getOutManId();
            User user = userMapper.selectByPrimaryKey(outManId);
            if (user == null) {
                throw new BusinessException("出库人员不存在！");
            }
            purchaseReturnOut.setOutManId(user.getId());
            purchaseReturnOut.setOutManCode(user.getCode());
            purchaseReturnOut.setOutManName(user.getName());
        } else {
            //开启时当前登录人员
            purchaseReturnOut.setOutManId(userVO.getUserId());
            purchaseReturnOut.setOutManCode(userVO.getUserCode());
            purchaseReturnOut.setOutManName(userVO.getUserName());
        }


        purchaseReturnOut.setStatus(PurchaseStatus.WAIT_AUDIT.getStatus());
        purchaseReturnOut.setCode(code);
        purchaseReturnOut.setId(null);
        purchaseReturnOut.setOrderType(OrderRule.PURCHASE_RETURN_OUT.getType());
        purchaseReturnOutMapper.insertSelective(purchaseReturnOut);
//        }

        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
        BigDecimal wholeDiscount = purchaseReturnOutInfoVO.getWholeDiscount();//整单折扣

        for (PurchaseReturnDetailOutVO vo : purchaseReturnDetailOutVOList) {
            /**.
             * 金额（整单优惠前金额）
             */
            BigDecimal amount = calcLineDiscount(vo);
            amountTotal = amountTotal.add(amount);
        }

        BigDecimal wholeDiscountAmount = purchaseReturnOutInfoVO.getWholeDiscountAmount();//整单优惠金额
        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计

        //购进退出出库货位明细
        List<PurchaseReturnOutShelf> returnOutShelfList = new ArrayList<>();

        if (purchaseReturnDetailOutVOList != null && purchaseReturnDetailOutVOList.size() > 0) {
            for (PurchaseReturnDetailOutVO item : purchaseReturnDetailOutVOList) {
                //查询购进退出的明细
                PurchaseReturnDetail purchaseReturnDetail = purchaseReturnDetailMapper.selectByPrimaryKey(item.getId());
                //购进退出出库明细
                PurchaseReturnOutDetail purchaseReturnOutDetail = new PurchaseReturnOutDetail();
                BeanUtils.copyProperties(purchaseReturnDetail, purchaseReturnOutDetail);
                PurchaseReturnOutDetail returnOutDetail = (PurchaseReturnOutDetail) EntityUtils.reflectAddSetDefaultValue(PurchaseReturnOutDetail.class, userVO);
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(returnOutDetail, purchaseReturnOutDetail);
                //计算金额
                BigDecimal quantity = item.getQuantity();//数量
                BigDecimal unitPrice = item.getUnitPrice();//单价
                BigDecimal lineDiscount = item.getLineDiscount();//行折扣
                GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(item.getTaxRateId());
                if (goodsTaxRate == null) {
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

                purchaseReturnOutDetail.setQuantity(quantity);

                //税率设置
                purchaseReturnOutDetail.setTaxRate(taxRate);


                purchaseReturnOutDetail.setOutId(purchaseReturnOut.getId());//购进退出出库单ID

                purchaseReturnOutDetail.setOutCode(code);
                purchaseReturnOutDetail.setOutDate(new Date());
                purchaseReturnOutDetail.setOrderType(OrderRule.PURCHASE_RETURN_OUT.getType());
                purchaseReturnOutDetail.setBaseOrderId(purchaseReturn.getId());
                purchaseReturnOutDetail.setBaseOrderDtlId(purchaseReturnDetail.getId());
                purchaseReturnOutDetail.setBaseOrderCode(purchaseReturn.getCode());
                purchaseReturnOutDetail.setBaseOrderDate(purchaseReturn.getReturnDate());
                purchaseReturnOutDetail.setBaseOrderType(purchaseReturn.getBaseOrderType());
                purchaseReturnOutDetail.setStatus(PurchaseStatus.WAIT_AUDIT.getStatus());
                //已清 和 未清 数量
                purchaseReturnOutDetail.setUnclearQuantity(quantity);
                purchaseReturnOutDetail.setClearQuantity(BigDecimal.ZERO);
                purchaseReturnOutDetail.setId(null);
                purchaseReturnOutDetail.setOutId(purchaseReturnOut.getId());
                ///////////////////////////////////////////////////////////////////////////////////
                //插入出库明细
                purchaseReturnOutDetailMapper.insertSelective(purchaseReturnOutDetail);

                BigDecimal quantityShelfTmp = BigDecimal.ZERO;//货位数量总计
                //出库货位明细
                List<StockShelfVO> stockShelfVOList = item.getStockShelfVOList();
                if (org.springframework.util.CollectionUtils.isEmpty(stockShelfVOList))
                    throw new BusinessException("没有传购进退出出库货位明细");
                for (StockShelfVO shelfVO : stockShelfVOList) {//购进退出出库货位明细
                    PurchaseReturnOutShelf purchaseReturnOutShelf = (PurchaseReturnOutShelf) EntityUtils.reflectAddSetDefaultValue(PurchaseReturnOutShelf.class, userVO);
                    BeanUtils.copyProperties(purchaseReturnOutDetail, purchaseReturnOutShelf);
                    //批号
                    LotNumber lotNumber = lotNumberMapper.getLotNumInfo(enterpriseId, purchaseReturnOutDetail.getGoodsId(), shelfVO.getLotNumber());
                    purchaseReturnOutShelf.setProductDate(lotNumber.getProductDate());
                    purchaseReturnOutShelf.setValidDate(lotNumber.getValidUntil());
//                        //查询出同批号不同批次的指定货位的商品库存信息
//                        List<StockVO> stockVOByParam = stockMapper.getStockVOByParam(enterpriseId, purchaseReturnOutDetail.getGoodsId(), shelfVO.getLotNumber());
                    //统计同批号不同批次的指定货位的商品库存信息
//                        PurchaseReturnOutShelf purchaseReturnOutShelf = purchaseReturnOutShelf;

                    purchaseReturnOutShelf.setShelfId(shelfVO.getShelfId());
                    purchaseReturnOutShelf.setShelfName(shelfVO.getShelfName());
                    purchaseReturnOutShelf.setEnterpriseId(enterpriseId);
                    purchaseReturnOutShelf.setParentId(parentId);
                    purchaseReturnOutShelf.setGoodsId(purchaseReturnOutDetail.getGoodsId());
                    purchaseReturnOutShelf.setDtlId(purchaseReturnOutDetail.getId());
                    purchaseReturnOutShelf.setLotId(shelfVO.getLotId());
                    purchaseReturnOutShelf.setLotNumber(shelfVO.getLotNumber());
                    purchaseReturnOutShelf.setQuantity(shelfVO.getQuality());

                    quantityShelfTmp = quantityShelfTmp.add(shelfVO.getQuality());
                    purchaseReturnOutShelf.setAmount(amount);
                    purchaseReturnOutShelf.setLineDiscountAmount(lineRoundOff);
                    purchaseReturnOutShelf.setRealAmount(realAmount);
                    purchaseReturnOutShelf.setRealPrice(realPrice);
                    purchaseReturnOutShelf.setNotaxRealAmount(notaxRealAmount);
                    purchaseReturnOutShelf.setNotaxRealPrice(notaxRealPrice);
                    purchaseReturnOutShelf.setTaxAmount(taxAmount);
                    purchaseReturnOutShelf.setId(null);
                    purchaseReturnOutShelf.setStatus(PurchaseStatus.WAIT_AUDIT.getStatus());
                    purchaseReturnOutShelf.setShelfStatusDesc(shelfVO.getQualityState());
                    returnOutShelfList.add(purchaseReturnOutShelf);
                    purchaseReturnOutShelfMapper.insertSelective(purchaseReturnOutShelf);
                }

                //库存可用数量S，商品批号维度的库存可用量
                BigDecimal usableQuantity = stockMapper.getUsableQuantityFromStockLock(stockShelfVOList.get(0).getLotNumber(), enterpriseId, item.getGoodsId());
                if (mangeConfig.getBusinessControl() == 0) {
                    //业务控制按钮关闭

                    /**
                     * 如果为null,是通过新增方式添加的购进退出(A),否则为调用采购入库单添加的购进退出(B)
                     * A:出库数量Q1可编辑,Q1小于等于商品的库存可用数量S
                     * B:出库数量Q2,入库单的可退数量R1,库存可用数量S1.
                     * 当R1>S1;R1=S1-->Q2<=R1.
                     * 当R1<S1;Q2<=R1.
                     */
                    Long baseOrderId = purchaseReturnDetail.getBaseOrderId();
                    String lotNumber = purchaseReturnDetail.getLotNumber();
                    Long goodsId = purchaseReturnDetail.getGoodsId();
                    if (baseOrderId == null) {
                        if (quantityShelfTmp.compareTo(usableQuantity) == 1) {
                            throw new BusinessException("出库数量不能大于商品的库存可用数量");
                        }
                    } else {
                        List<PurchaseInStorageShelf> purchaseInStorageShelf = purchaseInStorageShelfMapper.getPurchaseInStorageShelf(userVO.getEnterpriseId(), baseOrderId, goodsId, lotNumber);
                        BigDecimal canReturnQuantity = purchaseInStorageShelf.stream().filter(Objects::nonNull).filter(c -> c.getCanReturnQuantity() != null).map(PurchaseInStorageShelf::getCanReturnQuantity)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                        if (canReturnQuantity.compareTo(usableQuantity) == 1) {
                            //可退数量大于库存可用数量情况，使用商品的可用数量
                            canReturnQuantity = usableQuantity;
                        }
                        if (quantityShelfTmp.compareTo(canReturnQuantity) == 1) {
                            throw new BusinessException("出库数量不能大于商品的库存可用数量");
                        }
                    }
                } else {
                    //业务控制按钮开启
                    if (quantityShelfTmp.compareTo(usableQuantity) == 1) {
                        throw new BusinessException("出库数量不能大于商品的库存可用数量");
                    }
                }


            }
        }

        //更新总单金额

        purchaseReturnOut.setAmountTotal(amountTotal);
        purchaseReturnOut.setRealAmountTotal(realAmountTotal);
        purchaseReturnOut.setNotaxRealAmountTotal(notaxRealAmountTotal);
        purchaseReturnOut.setTaxAmountTotal(taxAmountTotal);
        Integer varietiesQuantity = purchaseReturnOutDetailMapper.getCountVarietiesQuantity(purchaseReturnOut.getId());
        purchaseReturnOut.setVarietiesQuantity(varietiesQuantity);
        //更新出库总单
        purchaseReturnOutMapper.updateByPrimaryKeySelective(purchaseReturnOut);

        //购进退出出库配送和结算
        PurchaseReturnOutOtherVO purchaseReturnOutOtherVO = purchaseReturnOutInfoVO.getPurchaseReturnOtherVO();
        if (purchaseReturnOutOtherVO != null) {
            PurchaseReturnOther purchaseReturnOther = purchaseReturnOtherMapper.selectByPrimaryKey(purchaseReturnOutOtherVO.getId());
            PurchaseReturnOutOther purchaseReturnOutOther = new PurchaseReturnOutOther();
            BeanUtils.copyProperties(purchaseReturnOutOtherVO, purchaseReturnOutOther);//
            BeanUtils.copyProperties(purchaseReturnOther, purchaseReturnOutOther);//覆盖
            PurchaseReturnOutOther returnOutOther = (PurchaseReturnOutOther) EntityUtils.reflectAddSetDefaultValue(PurchaseReturnOutOther.class, userVO);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(returnOutOther, purchaseReturnOutOther);
            purchaseReturnOutOther.setId(null);
            purchaseReturnOutOther.setOutId(purchaseReturnOut.getId());
            purchaseReturnOutOther.setStatus(PurchaseStatus.WAIT_AUDIT.getStatus());
            purchaseReturnOutOtherMapper.insertSelective(purchaseReturnOutOther);
        }
        //更新购进退出状态TODO
        purchaseReturnMapper.updateReturnStatus(returnId, PurchaseStatus.OUT_ING.getStatus());
        purchaseReturnDetailMapper.updateReturnDetailStatus(returnId, PurchaseStatus.OUT_ING.getStatus());
        purchaseReturnOtherMapper.updateReturnOtherStatus(returnId, PurchaseStatus.OUT_ING.getStatus());

        return purchaseReturnOut;
    }


    /**
     * 获得优惠前金额
     *
     * @param detailVO
     * @return
     */
    public BigDecimal calcLineDiscount(PurchaseReturnDetailOutVO detailVO) {
        BigDecimal amountByQuantityAndPriceAndLineDiscount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
                detailVO.getQuantity()
                , detailVO.getUnitPrice()
                , detailVO.getLineDiscount()
        );

        return amountByQuantityAndPriceAndLineDiscount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    @Transactional(rollbackFor = Exception.class)
    public void auditPurchaseReturnOut(RequestCheckVO requestCheckVO, UserVO userVO) throws Exception {
        Integer status = PurchaseStatus.WAIT_BILL.getStatus();
        Long id = requestCheckVO.getId();
        PurchaseReturnOut purchaseReturnOut = purchaseReturnOutMapper.selectByPrimaryKey(id);
        Integer status1 = purchaseReturnOut.getStatus();
        if (status1 == status) throw new BusinessException("该单据已复核!");

        Long supplierId = purchaseReturnOut.getSupplierId();
        Supplier supplier = supplierMapper.selectByPrimaryKey(supplierId);
        if (supplier.getChainType() == ChainType.Division.getCode()) {
            status = PurchaseStatus.FINISHED.getStatus();
        }

        Long returnId = purchaseReturnOut.getBaseOrderId();//购进退出id

        if (requestCheckVO.getCheckStatus() == CheckStatus.ENABLE.getStatus()) {//同意

            PurchaseReturnOut returnOut = (PurchaseReturnOut) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturnOut.class, userVO);
            returnOut.setStatus(status);
            returnOut.setCheckDate(new Date());
            returnOut.setCheckReason(requestCheckVO.getCheckReason());
            returnOut.setCheckerId(requestCheckVO.getCheckId());
            returnOut.setCheckerCode(userVO.getUserCode());
            returnOut.setCheckerName(userVO.getUserName());
            returnOut.setId(id);
            purchaseReturnOutMapper.updateByPrimaryKeySelective(returnOut);

            List<PurchaseReturnOutDetail> returnOutDetailList = purchaseReturnOutDetailMapper.getPurchaseReturnOutDetailByOutId(purchaseReturnOut.getId());
            for (PurchaseReturnOutDetail outDetail : returnOutDetailList) {
                PurchaseReturnOutDetail newPurchaseReturnOutDetail = (PurchaseReturnOutDetail) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturnOutDetail.class, userVO);
                newPurchaseReturnOutDetail.setId(outDetail.getId());
                newPurchaseReturnOutDetail.setStatus(status);
                if (status == PurchaseStatus.FINISHED.getStatus()) {
                    newPurchaseReturnOutDetail.setClearQuantity(outDetail.getQuantity());
                    newPurchaseReturnOutDetail.setUnclearQuantity(BigDecimal.ZERO);
                }
                purchaseReturnOutDetailMapper.updateByPrimaryKeySelective(newPurchaseReturnOutDetail);
            }


            List<PurchaseReturnOutShelf> outShelfList = purchaseReturnOutShelfMapper.getShelfOutInfoByOutId(purchaseReturnOut.getId(), null);
            for (PurchaseReturnOutShelf outShelf : outShelfList) {
                PurchaseReturnOutShelf newPurchaseReturnOutShelf = (PurchaseReturnOutShelf) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturnOutShelf.class, userVO);
                newPurchaseReturnOutShelf.setId(outShelf.getId());
                newPurchaseReturnOutShelf.setStatus(status);
                if (status == PurchaseStatus.FINISHED.getStatus()) {
                    newPurchaseReturnOutShelf.setClearQuantity(outShelf.getQuantity());
                    newPurchaseReturnOutShelf.setUnclearQuantity(BigDecimal.ZERO);
                }
                purchaseReturnOutShelfMapper.updateByPrimaryKeySelective(newPurchaseReturnOutShelf);
            }
            PurchaseReturnOutOther newPurchaseReturnOutOther = (PurchaseReturnOutOther) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturnOutOther.class, userVO);
            newPurchaseReturnOutOther.setOutId(purchaseReturnOut.getId());
            newPurchaseReturnOutOther.setStatus(status);
            purchaseReturnOutOtherMapper.updateStatusByReturnOut(newPurchaseReturnOutOther);

            //查询质量控制
            ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);

            if (mangeConfig.getBusinessControl() == EnableStatus.DISABLE.getStatus()) {//关闭--更新购进退出的状态和数量，
                //业务质量控制（0关闭；1-开启）

                //更新退出明细 数量，已清数量 、未清数量
                List<PurchaseReturnOutDetail> purchaseReturnOutDetails = purchaseReturnOutDetailMapper.getPurchaseReturnOutDetailByOutId(id);
                for (PurchaseReturnOutDetail detail : purchaseReturnOutDetails) {
                    //购进退出明细单id
                    Long returnDetailId = detail.getBaseOrderDtlId();
                    PurchaseReturnDetail returnDetail = (PurchaseReturnDetail) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturnDetail.class, userVO);
                    returnDetail.setId(returnDetailId);
                    returnDetail.setQuantity(detail.getQuantity());
                    returnDetail.setUnclearQuantity(BigDecimal.ZERO);
                    returnDetail.setClearQuantity(detail.getQuantity());
                    returnDetail.setStatus(status);
                    purchaseReturnDetailMapper.updateReturnDetailQuantity(returnDetail);
                }

                BigDecimal quantityTotal = purchaseReturnOutDetails.stream().filter(Objects::nonNull)
                        .filter(c -> c.getQuantity() != null)
                        .map(PurchaseReturnOutDetail::getQuantity)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                PurchaseReturn purchaseReturn = (PurchaseReturn) EntityUtils.reflectUpdateSetDefaultValue(PurchaseReturn.class, userVO);
                purchaseReturn.setQuantityTotal(quantityTotal);
                purchaseReturn.setStatus(status);
                purchaseReturn.setId(returnId);
                purchaseReturnMapper.updateByPrimaryKeySelective(purchaseReturn);

                purchaseReturnOtherMapper.updateReturnOtherStatus(returnId, status);

            } else {
                //开启只需要更新购进退出的状态
                //更新购进退出总单状态
                purchaseReturnMapper.updateReturnStatus(returnId, status);
                purchaseReturnDetailMapper.updateReturnDetailStatus(returnId, status);
                purchaseReturnOtherMapper.updateReturnOtherStatus(returnId, status);
            }

            //更新采购入库的可退数量
            PurchaseReturn purchaseReturn = purchaseReturnMapper.selectByPrimaryKey(returnId);
            if (purchaseReturn.getBaseOrderId() != null) {
                //调用的采购入库单
                List<PurchaseReturnOutDetail> purchaseReturnOutDetails = purchaseReturnOutDetailMapper.getPurchaseReturnOutDetailByOutId(id);
                for (PurchaseReturnOutDetail detail : purchaseReturnOutDetails) {
                    //购进退出明细单id
                    Long returnDetailId = detail.getBaseOrderDtlId();
                    PurchaseReturnDetail returnDetail = purchaseReturnDetailMapper.selectByPrimaryKey(returnDetailId);
                    Long storageDtlId = returnDetail.getBaseOrderDtlId();//采购入库明细单
                    List<PurchaseInStorageShelf> inStorageShelfList = purchaseInStorageShelfMapper.selectByDetailIdAndGoods(storageDtlId, null, null);
                    BigDecimal outQuantity = detail.getQuantity();//指定商品的出库数量
                    for (PurchaseInStorageShelf storageShelf : inStorageShelfList) {
                        BigDecimal canReturnQuantity = storageShelf.getCanReturnQuantity();
                        BigDecimal diffQuantity = outQuantity.subtract(canReturnQuantity);
                        if (diffQuantity.compareTo(BigDecimal.ZERO) >= 1) {
                            storageShelf.setCanReturnQuantity(BigDecimal.ZERO);
                        } else {
                            storageShelf.setCanReturnQuantity(diffQuantity.multiply(new BigDecimal(-1)));
                        }
                        purchaseInStorageShelfMapper.updateByPrimaryKeySelective(storageShelf);
                    }
                }
            }


            // 释放购进退出单锁定的库存
            LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
            lockQtyArgVO.setEnterpriseId(purchaseReturnOut.getEnterpriseId());
            lockQtyArgVO.setParentId(purchaseReturnOut.getParentId());
            lockQtyArgVO.setBaseOrderId(returnId);
            lockQtyArgVO.setBaseOrderType(purchaseReturnOut.getBaseOrderType());
            lockQtyArgVO.setUser(userVO);
            releaseStockAndCost(lockQtyArgVO);

            // 生成或更新关键表数据（成本、毛利、库存、出入库明细）
            List<PurchaseReturnOutShelf> returnOutShelfList = purchaseReturnOutShelfMapper.getReturnOutShelfListByEnterpriseIdAndOutId(purchaseReturnOut.getEnterpriseId(), purchaseReturnOut.getId());
            OrderModelBuilder builder = new OrderModelBuilder(userVO);
            OrderModel orderModel = builder.buildOrderModel(OrderRule.PURCHASE_RETURN_OUT, purchaseReturnOut, returnOutShelfList);
            inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);

            //生产财务数据
            financeComponent.purchaseReturnOutToBalanceAndVoucher(userVO, purchaseReturnOut);

        }
    }


    public void updateSupplierVarieties(PurchaseInStorageDetail dtl,
                                        Long supplierId) throws Exception {
        List<SupplierVarieties> varList = supplierVarietiesMapper.selectSupplierVarietiesByParam(dtl.getEnterpriseId(), dtl.getGoodsId(), supplierId);
        if (varList != null && varList.size() > 0) {
            for (SupplierVarieties supplierVarieties : varList) {

                supplierVarieties.setLastPurPrice(dtl.getUnitPrice());
                supplierVarieties.setLastPurTaxRateId(dtl.getTaxRateId());
                supplierVarieties.setLastPurTaxRate(dtl.getTaxRate());
                supplierVarietiesMapper.updateByPrimaryKeySelective(supplierVarieties);
            }
        } else {
            SupplierVarieties es = new SupplierVarieties();
            es.setSupplierId(supplierId);
            es.setEnterpriseId(dtl.getEnterpriseId());
            es.setGoodsId(dtl.getGoodsId());
            es.setGoodsCode(dtl.getGoodsCode());
            es.setGoodsName(dtl.getGoodsName());
            es.setSupplierGoodsCode("");//默认空
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("enterpriseId", dtl.getEnterpriseId());
            paramMap.put("goodsId", dtl.getGoodsId());
            GoodsBusiness goodsBusiness = goodsBusinessMapper.selectByEnterpriseIdAndGoodsId(paramMap);
            if (goodsBusiness == null) {
                throw new Exception("企业ID：" + dtl.getEnterpriseId() + ",商品ID：" + dtl.getGoodsId() + ",saas_goods_business表查不到数据！");
            }
            es.setManagementMode(goodsBusiness.getManagementMode());
            es.setAgreementPrice(dtl.getUnitPrice());
            es.setTaxRateId(dtl.getTaxRateId());
            es.setTaxRate(dtl.getTaxRate());
            es.setSoleSupplier(0);//默认否
            es.setStatus(EnableStatus.ENABLE.getStatus());
            es.setLastPurPrice(dtl.getUnitPrice());
            es.setLastPurTaxRateId(dtl.getTaxRateId());
            es.setLastPurTaxRate(dtl.getTaxRate());
            es.setCreaterId(dtl.getCreaterId());
            es.setCreaterCode(dtl.getCreaterCode());
            es.setCreaterName(dtl.getCreaterName());
            es.setCreateTime(new Date());
            es.setModifierId(dtl.getCreaterId());
            es.setModifierCode(dtl.getCreaterCode());
            es.setModifierName(dtl.getCreaterName());
            es.setUpdateTime(new Date());

            supplierVarietiesMapper.insertSelective(es);
        }

    }


    @Transactional(rollbackFor = Exception.class)
    public DistrSend generateAndSaveDistrSendOrder(UserVO userVO, RequestDistrSendVO requestDistrSendVO, boolean type, boolean lockType) throws Exception {
        checkRequestParams(requestDistrSendVO);
        //插入配送单信息
        DistrSend distrSend = getDistrSend(userVO, requestDistrSendVO);
        if (type) {
            if (isCheckApply(userVO)) {
                distrSend.setStatus(DistrSendStatus.PENDING_AUDIT);//默认待审核，走审批流程时，修改状态
            } else {
                distrSend.setStatus(DistrSendStatus.WAIT_PICK);//没开启审批流程时，待出库，修改状态
            }
        } else {
            distrSend.setStatus(DistrSendStatus.WAIT_PICK);//逆向生成
        }
        distrSendMapper.insertSelective(distrSend);

        if (type) {
            addDistrSendApproval(userVO, distrSend);
        }

        //插入配送单明细
        DistrSendDetail distrSendDetail;
        Date createDate = new Date();
        List<DistrSendDetail> distrSendDetailList = new ArrayList<>();
        for (RequestDistrGoods distrGoods : requestDistrSendVO.getDistrGoogsList()) {
            // 填充配货明细信息
            distrSendDetail = getDistrSendDetail(userVO, distrSend, distrGoods);
            distrSendDetailList.add(distrSendDetail);
            //创建人信息
            UserEnterpriseUtils.setUserCreateOrModify(distrSendDetail, userVO, true);
            distrSendDetailMapper.insertSelective(distrSendDetail);

            if (lockType) {
                // 锁定库存
                lockStockByDetail(userVO, distrSendDetail);
            }
        }
        distrSend.setDistrSendDetailList(distrSendDetailList);
        return distrSend;
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateBaseOrderStatus(UserVO userVO, DistrSend distrSend, Boolean type) {


        Integer orderType = -1;

        Long baseOrderId = -1L;


        for (DistrSendDetail distrSendDetail : distrSend.getDistrSendDetailList()) {

            Integer baseOrderType = distrSendDetail.getBaseOrderType();
            orderType = baseOrderType;

            baseOrderId = distrSendDetail.getBaseOrderId();
            if (baseOrderType == null) {
                return;
            }
            if (OrderRule.DISTR_LACK.getType().equals(baseOrderType)) { // 缺配单
                updateDistrLackStatus(userVO, distrSendDetail.getBaseOrderId(), distrSendDetail.getBaseOrderDtlId(), distrSendDetail.getQuantity());
            }


            if (OrderRule.DISTR_RETURN_IN.getType().equals(baseOrderType)) {// 配后退回入库、配后退回验收、配后退回收货、配后退回通知


                Integer status = DistrReturnStatus.FINISHED;
                //配后退回入库
                //DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(distrSendDetail.getBaseOrderId());

                DistrReturnInDetail distrReturnInDetail = new DistrReturnInDetail();
                distrReturnInDetail.setId(distrSendDetail.getBaseOrderDtlId());
                distrReturnInDetail.setStatus(status);
                distrReturnInDetail.setClearQuantity(distrSendDetail.getQuantity());
                distrReturnInDetail.setUnclearQuantity(BigDecimal.ZERO);
                distrReturnInDetailMapper.updateByPrimaryKeySelective(distrReturnInDetail);

                List<DistrReturnInShelf> distrReturnInShelves = distrReturnInShelfMapper.getDistrReturnInShelf(distrSendDetail.getBaseOrderDtlId());
                for (DistrReturnInShelf distrReturnInShelf : distrReturnInShelves) {
                    distrReturnInShelf.setStatus(status);
                    distrReturnInShelf.setClearQuantity(distrReturnInShelf.getUnclearQuantity());
                    distrReturnInShelf.setUnclearQuantity(BigDecimal.ZERO);
                    distrReturnInShelfMapper.updateByPrimaryKeySelective(distrReturnInShelf);
                }


            }
        }

        if (baseOrderId.equals(-1L) && orderType.equals(-1)) {
            throw new BusinessException("");
        }


        if (OrderRule.PURCHASE_IN.getType().equals(orderType)) {//采购入库
            // 更新采购入库单已经被引用
            PurchaseInStorage inStorage = purchaseInStorageMapper.selectByPrimaryKey(baseOrderId);
            if (inStorage == null) {
                throw new BusinessException("无效的采购入库单ID" + baseOrderId);
            }

            inStorage.setId(baseOrderId);
            inStorage.setIsUse(1);
            purchaseInStorageMapper.updateByPrimaryKeySelective(inStorage);
        }

        if (OrderRule.DISTR_RETURN_IN.getType().equals(orderType)) {


            Integer status = DistrReturnStatus.FINISHED;
            DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(baseOrderId);
            Long enterpriseId = distrReturnIn.getEnterpriseId();


            if (distrReturnIn == null) {
                throw new BusinessException("无效的配后退回入库单ID：" + baseOrderId);
            }
            distrReturnIn.setReferenceOrderId(distrSend.getId());
            distrReturnIn.setReferenceOrderType(OrderRule.DISTR_ORDER.getType());
            distrReturnIn.setStatus(status);
            distrReturnInMapper.updateByPrimaryKeySelective(distrReturnIn);
            // 配后退回验收
            Long checkId = distrReturnIn.getBaseOrderId();
            DistrReturnCheck distrReturnCheck = distrReturnCheckMapper.selectByPrimaryKey(checkId);
            distrReturnCheck.setStatus(status);
            distrReturnCheckMapper.updateByPrimaryKeySelective(distrReturnCheck);

            //修改收货单状态,如果gsp不管控,修改其他信息
            distrReturnCheckMapper.updateByReceive(enterpriseId, distrReturnCheck.getBaseOrderId(), status);
            distrReturnCheckMapper.updateByReceiveDetail(enterpriseId, distrReturnCheck.getBaseOrderId(), status);


            //修改订单总单状态,如果gsp不管控,修改其他信息
            distrReturnCheckMapper.updateByOrder(enterpriseId, distrReturnCheck.getNoticeId(), status);
            distrReturnCheckMapper.updateByOrderDetail(enterpriseId, distrReturnCheck.getNoticeId(), status);

        }

    }

    /**
     * 缺配单状态、未请数量、已清数量更新
     * 规则：
     * 1、缺配单中所有明细未清数量为0，状态改为 已配送
     * 2、取消缺配单时，只要有一条明细的已清数量 >0，缺配单状态变为 已配送，而不是已取消
     * 3、总部的配送数量可以大于 未清数量
     *
     * @param lackId       缺配单ID
     * @param lackDtlId    缺配单明细ID
     * @param sendQuantity 配送数量
     */

    public void updateDistrLackStatus(UserVO userVO, Long lackId, Long lackDtlId, BigDecimal sendQuantity) {
        DistrLackDetail distrLackDetail = distrLackDetailMapper.selectByPrimaryKey(lackDtlId);
        //未清数量
        if (distrLackDetail.getUnclearQuantity().compareTo(sendQuantity) == 1) {
            BigDecimal unClearQuantity = distrLackDetail.getUnclearQuantity().subtract(sendQuantity);
            if (unClearQuantity.compareTo(BigDecimal.ZERO) < 0) {
                unClearQuantity = BigDecimal.ZERO;
            }
            distrLackDetail.setUnclearQuantity(unClearQuantity);
            distrLackDetail.setLackQuantity(unClearQuantity);
        } else {
            distrLackDetail.setUnclearQuantity(BigDecimal.ZERO);
            distrLackDetail.setLackQuantity(BigDecimal.ZERO);
        }

        //已清数量
        BigDecimal clearQuantity = sendQuantity.add(distrLackDetail.getClearQuantity());
        if (clearQuantity.compareTo(BigDecimal.ZERO) < 0) {
            clearQuantity = BigDecimal.ZERO;
        }
        distrLackDetail.setSendQuantity(clearQuantity);
        distrLackDetail.setClearQuantity(clearQuantity);
        //更新人信息
        distrLackDetail.setModifierId(userVO.getUserId());
        distrLackDetail.setModifierCode(userVO.getUserCode());
        distrLackDetail.setModifierName(userVO.getUserName());
        distrLackDetail.setUpdateTime(new Date());
        //更新明细
        if (distrLackDetailMapper.updateByPrimaryKeySelective(distrLackDetail) != 1) {
            throw new BuilderException("更新缺配单未清数量、已清数量失败！");
        }

        //判断是否更新缺配单状态
        List<DistrLackDetail> lackDetails = distrLackDetailMapper.getDistrLackDetailByLackId(lackId);
        if (lackDetails == null || lackDetails.size() == 0) {
            DistrLack distrLack = distrLackMapper.selectByPrimaryKey(lackId);
            distrLack.setStatus(DistrLackStatus.FINISHED);
            distrLack.setModifierId(userVO.getUserId());
            distrLack.setModifierCode(userVO.getUserCode());
            distrLack.setModifierName(userVO.getUserName());
            distrLack.setUpdateTime(new Date());
            if (distrLackMapper.updateByPrimaryKeySelective(distrLack) != 1) {
                throw new BuilderException("更新缺配单状态失败！");
            }
        }
    }

    public boolean isCheckApply(UserVO userVO) {
        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(userVO);
        if (manageConfig.getApprovalControl().equals(EnableStatus.ENABLE.getStatus())) {
            return true;
        } else {
            return false;
        }
    }

    public void lockStockByDetail(UserVO userVO, DistrSendDetail distrSendDetail) {
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setEnterpriseId(distrSendDetail.getEnterpriseId());
        lockQtyArgVO.setParentId(distrSendDetail.getParentId());
        lockQtyArgVO.setBaseOrderId(distrSendDetail.getDistrId());
        lockQtyArgVO.setBaseOrderCode(distrSendDetail.getDistrCode());
        lockQtyArgVO.setBaseOrderType(distrSendDetail.getOrderType());
        lockQtyArgVO.setBaseOrderDate(distrSendDetail.getDistrDate());
        lockQtyArgVO.setGoodsId(distrSendDetail.getGoodsId());
        lockQtyArgVO.setQuantity(distrSendDetail.getQuantity());
        lockQtyArgVO.setUser(userVO);
        lockStockAndCost(lockQtyArgVO);
    }

    /**
     * 封装配送单明细信息
     *
     * @param distrSend
     * @param distrGoods
     * @return
     */
    public DistrSendDetail getDistrSendDetail(UserVO userVO, DistrSend distrSend, RequestDistrGoods distrGoods) throws Exception {
        DistrSendDetail distrSendDetail = new DistrSendDetail();
        //企业信息
        distrSendDetail.setEnterpriseId(distrSend.getEnterpriseId());
        distrSendDetail.setParentId(distrSend.getParentId());

        //配货单ID
        distrSendDetail.setDistrId(distrSend.getId());
        //单据类型
        distrSendDetail.setOrderType(distrSend.getOrderType());
        //配货单单号
        distrSendDetail.setDistrCode(distrSend.getCode());
        //配货日期
        distrSendDetail.setDistrDate(distrSend.getDistrDate());

        //基础单号信息
        getBaseOrderInfo(distrSendDetail, distrGoods.getBaseOrderId(), distrGoods.getBaseOrderType());
        distrSendDetail.setBaseOrderDtlId(distrGoods.getBaseOrderDtlId());

        //基础单据类型为 缺配单时，需要更新缺配单的状态、未清数量、已清数量
//        if (distrGoods.getBaseOrderType() != null && OrderRule.DISTR_LACK.getType().equals(distrGoods.getBaseOrderType())) {
//            updateDistrLackStatus(userVO, distrSendDetail.getBaseOrderId(), distrSendDetail.getBaseOrderDtlId(), distrGoods.getQuantity());
//        }

        //商品信息
        Goods goodVO = goodsMapper.selectByPrimaryKey(distrGoods.getGoodsId());
        distrSendDetail.setGoodsId(goodVO.getId());
        distrSendDetail.setGoodsCode(goodVO.getCode());
        distrSendDetail.setBarcode(goodVO.getBarcode());
        distrSendDetail.setGoodsName(goodVO.getName());
        distrSendDetail.setGoodsGenericName(goodVO.getGenericName());
        distrSendDetail.setDosageId(goodVO.getDosageId());
        distrSendDetail.setDosageName(goodVO.getDosageName());
        distrSendDetail.setUnitId(goodVO.getUnitId());
        distrSendDetail.setUnitName(goodVO.getUnitName());
        distrSendDetail.setGoodsSpecification(goodVO.getSpecification());
        distrSendDetail.setManufacturerId(goodVO.getManufacturerId());
        distrSendDetail.setManufacturer(goodVO.getManufacturer());
        distrSendDetail.setGoodsPlace(goodVO.getPlace());
        distrSendDetail.setApprovalNumber(goodVO.getApprovalNumber());

        //计算价格
        calculationDistrSendDetailPrice(distrSend, distrSendDetail, distrGoods);

        //未请数量(配货数量)
        distrSendDetail.setUnclearQuantity(distrGoods.getQuantity());
        //已清数量（默认0）
        distrSendDetail.setClearQuantity(BigDecimal.ZERO);
        //行号
        distrSendDetail.setLineNum(distrGoods.getLineNum());
        //状态-默认总单状态
        distrSendDetail.setStatus(distrSend.getStatus());
        //备注
        distrSendDetail.setRemark(distrGoods.getRemark());

        //回填货位
        if (distrGoods.getSaveDistrOutShelfVOList() != null) {
            distrSendDetail.setSaveDistrOutShelfVOList(distrGoods.getSaveDistrOutShelfVOList());
        }
        return distrSendDetail;
    }

    /**
     * 获取门店对应商品的配送税率及税率ID
     *
     * @return
     */
    public Map<String, Object> getDistrTaxRate(Long enterpriseId, Long goodsId) {
        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        return priceOrderDetailMapper.selectDistrTaxRateByGoodsIdAndDistrPriceOrderId(goodsId, enterpriseBusiness.getDistrPriceOrderId(), enterpriseId);
    }


    /**
     * 计算 配送单明细 价格
     *
     * @param distrSend
     * @param distrSendDetail
     * @param distrGoods
     */
    public void calculationDistrSendDetailPrice(DistrSend distrSend, DistrSendDetail distrSendDetail, RequestDistrGoods distrGoods) throws Exception{
        //获取默认税率
        if (distrGoods.getTaxRateId() == null) {
            //税率
            Map<String, Object> distrTaxRateMap = getDistrTaxRate(distrSend.getRequestUnitId(), distrGoods.getGoodsId());
            if (distrTaxRateMap != null) {
                distrGoods.setTaxRateId(distrTaxRateMap.get("distrTaxRateId") == null ? 0 : (Long) distrTaxRateMap.get("distrTaxRateId"));
                distrGoods.setTaxRate(distrTaxRateMap.get("distrTaxRate") == null ? BigDecimal.ZERO : new BigDecimal((Double) distrTaxRateMap.get("distrTaxRate")));
            } else {
                distrGoods.setTaxRateId(new Long(0));
                distrGoods.setTaxRate(BigDecimal.ZERO);
            }
        }

        //总部库存使用量
        //BigDecimal parentUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getEnterpriseId(), distrSendDetail.getGoodsId());

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", distrSend.getEnterpriseId());
        paramMap.put("goodsId", distrSendDetail.getGoodsId());
        paramMap.put("today", new Date());
        BigDecimal parentUsableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);

        
        // 配货单修改时，要把这个单据锁定的数量取回来
        if(distrSend.getId() != null){// 代表修改
            paramMap.put("baseOrderId", distrSend.getId());
            paramMap.put("baseOrderType", OrderRule.DISTR_ORDER.getType());
            List<StockLockRecord> stockLockRecordList = stockLockRecordMapper.selectByParamMap(paramMap);

            for(StockLockRecord lockRecord : stockLockRecordList){
                parentUsableQuantity = parentUsableQuantity.add(lockRecord.getLockQuantity());
            }
        }

        if (parentUsableQuantity == null) {

            throw new BusinessException("总部可用库存不足");
            //parentUsableQuantity = BigDecimal.ZERO;
        }
        distrSendDetail.setParentUsableQuantity(parentUsableQuantity);
        // 要货数量
        BigDecimal quantity = distrGoods.getQuantity();
        //判断填写的配送单商品数量是否大于总店可用库存
        if (parentUsableQuantity.compareTo(quantity) < 0) {
            quantity = parentUsableQuantity;

        }

        //门店库存可用量
        BigDecimal usableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getRequestUnitId(), distrGoods.getGoodsId());
        if (usableQuantity == null) {
            usableQuantity = BigDecimal.ZERO;
        }
        distrSendDetail.setUsableQuantity(usableQuantity);

        distrSendDetail.setQuantity(quantity);
        //单价
        distrSendDetail.setUnitPrice(distrGoods.getUnitPrice());
        //行折扣
        distrSendDetail.setLineDiscount(distrGoods.getLineDiscount());
        //金额（整单优惠前金额） = 数量*单价*行折扣
        BigDecimal lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(quantity, distrGoods.getUnitPrice(), distrGoods.getLineDiscount());
        distrSendDetail.setAmount(lineAmount);
        //整单折扣（%）
        distrSendDetail.setWholeDiscount(distrSend.getWholeDiscount());
        //优惠分摊 = 整单优惠*(金额/sum(金额))
        BigDecimal lineDiscountAmount = BigDecimal.ZERO;
        if (distrSend.getAmountTotal().compareTo(BigDecimal.ZERO) == 1) {
            lineDiscountAmount = distrSend.getWholeDiscountAmount().multiply(lineAmount.divide(distrSend.getAmountTotal(), 2, BigDecimal.ROUND_HALF_UP));
        }
        distrSendDetail.setLineDiscountAmount(lineDiscountAmount);
        //行实际金额 = 数量*单价*行折扣*整单折扣-优惠分摊
        BigDecimal lineRealAmount = lineAmount
                .multiply(distrSend.getWholeDiscount())
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                .subtract(lineDiscountAmount);
        if (lineRealAmount.compareTo(BigDecimal.ZERO) < 0) {
            lineRealAmount = BigDecimal.ZERO;
        }
        distrSendDetail.setRealAmount(lineRealAmount);
        //实际单价：实际金额/数量
        distrSendDetail.setRealPrice(lineRealAmount.divide(quantity, 2, BigDecimal.ROUND_HALF_UP));
        //税率ID
        distrSendDetail.setTaxRateId(distrGoods.getTaxRateId());
        GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(distrGoods.getTaxRateId());
        //税率
        distrSendDetail.setTaxRate(goodsTaxRate.getTaxRate());
        //不含税实际金额
        BigDecimal notaxRealAmount = lineRealAmount.divide(new BigDecimal(1).add(goodsTaxRate.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
        distrSendDetail.setNotaxRealAmount(notaxRealAmount);
        //不含税实际单价
        distrSendDetail.setNotaxRealPrice(notaxRealAmount.divide(quantity, 2, BigDecimal.ROUND_HALF_UP));
        //税额
        distrSendDetail.setTaxAmount(lineRealAmount.subtract(notaxRealAmount));
    }

    /**
     * 获取基础单据信息
     */
    public void getBaseOrderInfo(DistrSendDetail distrDetailSend, Long baseOrderId, Integer baseOrderType) throws Exception {
        if (baseOrderId == null || baseOrderType == null) {
            return;
        }
        //基础单号
        String baseOrderCode = null;
        //基础单号日期
        Date baseOrderDate = null;
        //要货计划
        if (OrderRule.REQUIRE_PLAN.getType().compareTo(baseOrderType) == 0) {
            DistrReqPlan reqPlan = distrReqPlanMapper.selectByPrimaryKey(baseOrderId);
            baseOrderCode = reqPlan.getCode();
            baseOrderDate = reqPlan.getPlanDate();
        } else if (OrderRule.DISTR_LACK.getType().compareTo(baseOrderType) == 0) {//缺配单
            DistrLack distrLack = distrLackMapper.selectByPrimaryKey(baseOrderId);
            baseOrderCode = distrLack.getCode();
            baseOrderDate = distrLack.getSendDate();
        } else if (OrderRule.PURCHASE_IN.getType().compareTo(baseOrderType) == 0) {//采购入库
            PurchaseInStorage purchaseInStorage = purchaseInStorageMapper.selectByPrimaryKey(baseOrderId);
            baseOrderCode = purchaseInStorage.getCode();
            baseOrderDate = purchaseInStorage.getInStorageDate();
//            // 更新采购入库单已经被引用
//
//            PurchaseInStorage inStorage = new PurchaseInStorage();
//            inStorage.setId(baseOrderId);
//            inStorage.setIsUse(1);
//            purchaseInStorageMapper.updateByPrimaryKeySelective(inStorage);
        } else if (OrderRule.DISTR_RETURN_IN.getType().compareTo(baseOrderType) == 0) {//配后退回入库
            DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(baseOrderId);
            baseOrderCode = distrReturnIn.getCode();
            baseOrderDate = distrReturnIn.getReturnInDate();
        } else {
            return;
        }

        distrDetailSend.setBaseOrderId(baseOrderId);
        distrDetailSend.setBaseOrderCode(baseOrderCode);
        distrDetailSend.setBaseOrderDate(baseOrderDate);
        distrDetailSend.setBaseOrderType(baseOrderType);
    }

    /**
     * 生成配送单，添加审批流程判断
     *
     * @param userVO
     * @param distrSend
     */
    public void addDistrSendApproval(UserVO userVO, DistrSend distrSend) throws Exception {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(
                userVO.getEnterpriseId(),
                enterprise.getName(),
                userVO.getUserId(),
                userVO.getUserName(),
                userVO.getChainType(),
                userVO.getParentId(),
                userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                ApprovalFlowContentModel.getDistrSendCode(),
                distrSend.getId(),
                "",
                ""
        );
        approvalFlowComponent.apply(submitApprovalFlowVO, userVO);
    }

    /**
     * 封装配送单信息
     *
     * @param userVO
     * @param requestDistrSendVO
     * @return
     * @throws Exception
     */
    public DistrSend getDistrSend(UserVO userVO, RequestDistrSendVO requestDistrSendVO) throws Exception {
        //插入配货单
        DistrSend distrSend = new DistrSend();

        //企业信息
        distrSend.setEnterpriseId(userVO.getEnterpriseId());
        distrSend.setParentId(userVO.getParentId());
        //单据类型
        distrSend.setOrderType(OrderRule.DISTR_ORDER.getType());
        //配货单号生成规则
        String code = orderCodeComponent.generate(OrderRule.DISTR_ORDER.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        distrSend.setCode(code);
        //配货单日期
        distrSend.setDistrDate(requestDistrSendVO.getDistrDate());
        //要货单位信息
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(requestDistrSendVO.getRequestUnitId());
        if (enterprise == null) {
            throw new BusinessException("查询要货单位信息错误！");
        }
        distrSend.setRequestUnitId(enterprise.getId());
        distrSend.setRequestUnitCode(enterprise.getCode());
        distrSend.setRequestUnitName(enterprise.getName());
        //配货人员信息
        User distrManInfo = userMapper.selectByPrimaryKey(requestDistrSendVO.getDistrManId());
        if (distrManInfo == null) {
            throw new BusinessException("查询配货人信息错误！");
        }
        distrSend.setDistrManId(distrManInfo.getId());
        distrSend.setDistrManCode(distrManInfo.getCode());
        distrSend.setDistrManName(distrManInfo.getName());

        //配货类型：总部自己新增配货单，默认0-总部配送
        distrSend.setDistrType(0);
        //配货规则: 默认 0-按要货顺序
        distrSend.setDistrRule(requestDistrSendVO.getDistrRule());
        //缺配处理：默认1-不处理
        distrSend.setLackHandle(requestDistrSendVO.getLackHandle());
        distrSend.setRemark(requestDistrSendVO.getRemark());

        //计算价格
        calculationDistrSendPrice(distrSend, requestDistrSendVO);

        //创建人信息
        UserEnterpriseUtils.setUserCreateOrModify(distrSend, userVO, true);
        return distrSend;
    }

    /**
     * 检查保存配送单的请求参数
     *
     * @throws Exception
     */
    public void checkRequestParams(RequestDistrSendVO requestDistrSendVO) throws Exception {
        //要货单位判断
        if (requestDistrSendVO.getRequestUnitId() == 0) {
            throw new BusinessException("请选择要货单位！");
        }

        //配货单日期
        if (requestDistrSendVO.getDistrDate() == null) {
            throw new BusinessException("请选择配货日期！");
        }

        //配货人员
        if (requestDistrSendVO.getDistrManId() == 0) {
            throw new BusinessException("请选择配货人员！");
        }

//        //商品判断
//        if (requestDistrSendVO.getDistrGoogsList() == null || requestDistrSendVO.getDistrGoogsList().size() == 0) {
//            throw new BusinessException("请选择配货商品！");
//        }

        //行校验
        for (RequestDistrGoods distrGoods : requestDistrSendVO.getDistrGoogsList()) {
            if (BigDecimal.ZERO.compareTo(distrGoods.getQuantity()) == 1) {
                throw new BusinessException("商品数量配送数量错误！");
            }

            if (BigDecimal.ZERO.compareTo(distrGoods.getUnitPrice()) == 1) {
                throw new BusinessException("商品配送单价错误！");
            }

            if (BigDecimal.ZERO.compareTo(distrGoods.getLineDiscount()) == 1 || distrGoods.getLineDiscount().compareTo(new BigDecimal(100)) == 1) {
                throw new BusinessException("商品行折扣错误！");
            }

            if (distrGoods.getTaxRate() == null) {
                throw new BusinessException("商品税率为空！");
            }
        }
    }

    /**
     * 计算配货单据金额
     *
     * @param distrSend
     * @param requestDistrSendVO
     */
    public void calculationDistrSendPrice(DistrSend distrSend, RequestDistrSendVO requestDistrSendVO) {
        //数量合计
        BigDecimal quantityTotal = BigDecimal.ZERO;
        //整单金额
        BigDecimal amountTotal = BigDecimal.ZERO;
        //行金额
        BigDecimal lineAmount;
        for (RequestDistrGoods requestGoods : requestDistrSendVO.getDistrGoogsList()) {
            quantityTotal = quantityTotal.add(requestGoods.getQuantity());
            //行金额=数量*单价*行折扣
            lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(requestGoods.getQuantity(), requestGoods.getUnitPrice(), requestGoods.getLineDiscount());
            amountTotal = amountTotal.add(lineAmount);
        }

        //数量合计
        distrSend.setQuantityTotal(quantityTotal);
        //品种合计
        distrSend.setVarietiesQuantity(requestDistrSendVO.getDistrGoogsList().size());
        //金额合计（整单优惠前的金额合计）
        distrSend.setAmountTotal(amountTotal);
        //整单折扣
        distrSend.setWholeDiscount(requestDistrSendVO.getWholeDiscount());
        //整单优惠
        distrSend.setWholeDiscountAmount(requestDistrSendVO.getWholeDiscountAmount());
        //实际金额
        BigDecimal realAmountTotal = amountTotal.multiply(requestDistrSendVO.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).subtract(requestDistrSendVO.getWholeDiscountAmount());
        if (realAmountTotal.compareTo(BigDecimal.ZERO) < 0) {
            realAmountTotal = BigDecimal.ZERO;
        }
        distrSend.setRealAmountTotal(realAmountTotal);

        //行实际金额
        BigDecimal lineRealAmount;
        //行不含税实际金额
        BigDecimal notaxRealAmount;
        //行优惠（总优惠分摊）
        BigDecimal lineDiscountAmount = BigDecimal.ZERO;
        //行税额
        BigDecimal lineTaxAmount;
        //税额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        for (RequestDistrGoods requestGoods : requestDistrSendVO.getDistrGoogsList()) {
            //行金额=数量*单价*行折扣
            lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(requestGoods.getQuantity(), requestGoods.getUnitPrice(), requestGoods.getLineDiscount());
            //优惠分摊 = 整单优惠*(金额/sum(金额))
            if (amountTotal.compareTo(BigDecimal.ZERO) == 1) {
                lineDiscountAmount = requestDistrSendVO.getWholeDiscountAmount().multiply(lineAmount.divide(amountTotal, 2, BigDecimal.ROUND_HALF_UP));
            }
            //行实际金额 = 数量*单价*行折扣*整单折扣-优惠分摊
            lineRealAmount = lineAmount
                    .multiply(requestDistrSendVO.getWholeDiscount())
                    .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                    .subtract(lineDiscountAmount);
            if (lineRealAmount.compareTo(BigDecimal.ZERO) < 0) {
                lineRealAmount = BigDecimal.ZERO;
            }
            //不含税实际金额：
            notaxRealAmount = lineRealAmount.divide(new BigDecimal(1).add(requestGoods.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
            //行税额
            lineTaxAmount = lineRealAmount.subtract(notaxRealAmount);
            taxAmountTotal = taxAmountTotal.add(lineTaxAmount);
        }
        //税额合计
        distrSend.setTaxAmountTotal(taxAmountTotal);
        //不含税金额合计
        distrSend.setNotaxRealAmountTotal(distrSend.getRealAmountTotal().subtract(taxAmountTotal));
    }


    @Transactional(rollbackFor = Exception.class)
    public PickOrder saveDistrPick(DistrSendOperationVO vo, UserVO loginUser) throws Exception {


        DistrSend send = validateOperationVO(vo, loginUser);
        PickOrder order = setPickOrder(send, vo, loginUser);
        pickOrderMapper.insertSelective(order);


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", send.getEnterpriseId());
        paramMap.put("baseOrderId", send.getId());
        paramMap.put("baseOrderType", send.getOrderType());

        List<StockLockRecord> recordList = stockLockRecordMapper.selectByParamMap(paramMap);
        if (recordList != null) {
            //根据原来的更改库存可用数量
            for (StockLockRecord stockLockRecord : recordList) {
                updateStockByParam(stockLockRecord.getEnterpriseId(), stockLockRecord.getParentId(),
                        stockLockRecord.getGoodsId(), stockLockRecord.getLotId(), stockLockRecord.getShelfId(),
                        stockLockRecord.getLockQuantity(), "add");
                stockLockRecordMapper.deleteByPrimaryKey(stockLockRecord.getId());
            }
        }

        List<CostLockRecord> costLockList = costLockRecordMapper.selectByParamMap(paramMap);
        if (costLockList != null) {
            //根据原来的更改成本可用数量
            for (CostLockRecord lockRecord : costLockList) {
                updateCostByParam(lockRecord.getEnterpriseId(), lockRecord.getParentId(), lockRecord.getGoodsId(),
                        lockRecord.getLotId(), lockRecord.getLockQuantity(), "add", send, loginUser,
                        lockRecord.getBatchId(), lockRecord.getBatchNum());
                costLockRecordMapper.deleteByPrimaryKey(lockRecord.getId());
            }
        }

        List<DistrSendOperationDtlVO> dtlList = vo.getDtlList();
        for (DistrSendOperationDtlVO dtlvo : dtlList) {
            DistrSendDetail sendDetail = distrSendDetailMapper.selectByPrimaryKey(dtlvo.getSendDetailId());
            PickDetail pDtl = setPickDetail(sendDetail, order, dtlvo);
            pickDetailMapper.insertSelective(pDtl);
            order.getPickDetails().add(pDtl);
            if (sendDetail.getQuantity().compareTo(dtlvo.getQuantity()) != 0) {
                //与配货单细表数量不一致时更改配货单细表数量
                sendDetail.setQuantity(dtlvo.getQuantity());
                distrSendDetailMapper.updateByPrimaryKeySelective(sendDetail);
            }

            List<DistrSendOperationSlfVO> slfList = dtlvo.getShelfList();
            for (DistrSendOperationSlfVO distrSendOperationSlfVO : slfList) {
                PickShelf slf = setPickShelf(distrSendOperationSlfVO, pDtl);
                pickShelfMapper.insertSelective(slf);
                pDtl.getPickShelves().add(slf);
                //增加库存锁定记录
                StockLockRecord lock = setLockRecord(slf, send);
                stockLockRecordMapper.insertSelective(lock);
                updateStockByParam(lock.getEnterpriseId(), lock.getParentId(),
                        lock.getGoodsId(), lock.getLotId(), lock.getShelfId(),
                        lock.getLockQuantity(), "subtract");

                //增加成本锁定记录
                updateCostByParam(slf.getEnterpriseId(), slf.getParentId(), slf.getGoodsId(),
                        slf.getLotId(), slf.getQuantity(), "subtract", send, loginUser, 0L, "");
//				CostLockRecord costLock = setCostLockRecord(slf, send, cost);
//				costLockRecordMapper.insertSelective(costLock);

            }
        }

        if (send.getQuantityTotal().compareTo(vo.getQuantityTotal()) != 0) {
            //与配货单数量不一致时更改配货单数量
            send.setQuantityTotal(vo.getQuantityTotal());
        }

        distrSendMapper.updateByPrimaryKeySelective(send);

        return order;
    }

    private DistrSend validateOperationVO(DistrSendOperationVO vo, UserVO loginUser) {
        if (vo == null) {
            throw new InventoryBizException("", "保存拣货对象不能为空！");
        }
        DistrSend send = distrSendMapper.selectByPrimaryKey(vo.getId());
        if (send == null || !send.getEnterpriseId().equals(loginUser.getEnterpriseId())) {
            throw new InventoryBizException("", "查询配货单对象为空或配货单企业ID错误！");
        }
        if (vo.getPickDate() == null) {
            throw new InventoryBizException("", "拣货日期不能为空！");
        }
        if (vo.getPickManId() == null) {
            throw new InventoryBizException("", "拣货人员ID不能为空！");
        }

        if (vo.getDtlList() == null || vo.getDtlList().isEmpty()) {
            throw new InventoryBizException("", "拣货明细对象不能为空！");
        }

        ManageConfig config = manageConfigMapper.selectManageConfigByEnterpriseId(loginUser.getEnterpriseId());

        BigDecimal dtlQuantity = BigDecimal.ZERO;//商品明细数量

        for (DistrSendOperationDtlVO dtl : vo.getDtlList()) {
            if (dtl.getShelfList() == null || dtl.getShelfList().isEmpty()) {
                throw new InventoryBizException("", "拣货货位明细对象不能为空！");
            }
            if (dtl.getLineNum() == null) {
                throw new InventoryBizException("", "商品明细对象-行号不能为空！");
            }
            BigDecimal slfQuantity = BigDecimal.ZERO;//货位明细数量
            List<DistrSendOperationSlfVO> slfList = dtl.getShelfList();
            for (DistrSendOperationSlfVO slf : slfList) {
                if (slf.getLineNum() == null) {
                    throw new InventoryBizException("", "货位明细对象-行号不能为空！");
                }
                slfQuantity = slfQuantity.add(slf.getQuantity());
            }
            if (slfQuantity.compareTo(dtl.getQuantity()) != 0) {
                throw new InventoryBizException("", dtl.getGoodsGenericName() + "商品数量与货位明细数量之和不一致！");
            }
            dtlQuantity = dtlQuantity.add(dtl.getQuantity());

            DistrSendDetail sendDetail = distrSendDetailMapper.selectByPrimaryKey(dtl.getSendDetailId());
            if (sendDetail == null) {
                throw new InventoryBizException("", "配货单明细ID错误！" + dtl.getSendDetailId());
            }

            if (config != null && config.getBusinessControl() != null && config.getBusinessControl() == 1) {
                //业务质量流程开启
                if (dtl.getQuantity().compareTo(sendDetail.getQuantity()) != 0) {
                    throw new InventoryBizException("", "当前开启业务流程质量控制，" + dtl.getGoodsGenericName() + "商品重新选择的数量必须与配货单中一致！");
                }
            }
        }
        if (dtlQuantity.compareTo(vo.getQuantityTotal()) != 0) {
            throw new InventoryBizException("", "总数量与明细数量之和不一致！");
        }

        return send;
    }

    private PickOrder setPickOrder(DistrSend send, DistrSendOperationVO vo,
                                   UserVO loginUser) throws Exception {
        PickOrder order = new PickOrder();
        order.setEnterpriseId(send.getEnterpriseId());
        order.setParentId(send.getParentId());
        order.setOrderType(OrderRule.PICK.getType());
        order.setCode(orderCodeComponent.generate(OrderRule.PICK.getCodePrefix(),
                loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        order.setPickDate(vo.getPickDate());
        order.setRequestUnitId(send.getRequestUnitId());
        order.setRequestUnitCode(send.getRequestUnitCode());
        order.setRequestUnitName(send.getRequestUnitName());
        order.setBaseOrderId(send.getId());
        order.setBaseOrderType(send.getOrderType());
        order.setBaseOrderCode(send.getCode());
        order.setBaseOrderDate(send.getDistrDate());
        order.setPickManId(vo.getPickManId());
        User user = userMapper.selectByPrimaryKey(vo.getPickManId());
        if (user == null) {
            throw new InventoryBizException("", "拣货人员ID错误！");
        }
        order.setPickManCode(user.getCode());
        order.setPickManName(user.getName());
        order.setStatus(DistrSendStatus.WAIT_OUT);
        order.setCreaterId(loginUser.getUserId());
        order.setCreaterCode(loginUser.getUserCode());
        order.setCreaterName(loginUser.getUserName());
        order.setCreateTime(new Date());
        order.setModifierId(loginUser.getUserId());
        order.setModifierCode(loginUser.getUserCode());
        order.setModifierName(loginUser.getUserName());
        order.setUpdateTime(new Date());
        return order;
    }

    private void updateStockByParam(Long enterpriseId, Long parentId,
                                    Long goodsId, Long lotId, Long shelfId, BigDecimal lockQuantity, String type) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("parentId", parentId);
        paramMap.put("goodsId", goodsId);
        paramMap.put("lotId", lotId);
        paramMap.put("shelfId", shelfId);
        List<Stock> stockList = stockMapper.selectByParamMap(paramMap);
        if (stockList == null || stockList.size() == 0) {
            throw new InventoryBizException("", "企业ID" + enterpriseId + "商品ID" + goodsId + "批号ID" + lotId + "货位ID" + shelfId + "查不到SAAS_STOCK数据！");
        } else if ("add".equals(type)) {

            Stock stock = stockList.get(0);
            stock.setUsableQuantity(stock.getUsableQuantity().add(lockQuantity).setScale(6, RoundingMode.HALF_UP));

            if (stock.getLockQuantity().compareTo(lockQuantity) < 0) {
                throw new InventoryBizException("", "库存原锁定量不足！");
            }

            stock.setLockQuantity(stock.getLockQuantity().subtract(lockQuantity).setScale(6, RoundingMode.HALF_UP));
            stockMapper.updateByPrimaryKeySelective(stock);

        } else if ("subtract".equals(type)) {

            Stock stock = stockList.get(0);

            if (stock.getUsableQuantity().compareTo(lockQuantity) < 0) {
                throw new InventoryBizException("", "库存可用量不足！");
            }

            stock.setUsableQuantity(stock.getUsableQuantity().subtract(lockQuantity).setScale(6, RoundingMode.HALF_UP));
            stock.setLockQuantity(stock.getLockQuantity().add(lockQuantity).setScale(6, RoundingMode.HALF_UP));
            stockMapper.updateByPrimaryKeySelective(stock);

        }
    }

    private void updateCostByParam(Long enterpriseId, Long parentId, Long goodsId, Long lotId,
                                   BigDecimal lockQuantity, String type, DistrSend send, UserVO user, Long batchId, String batchNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("parentId", parentId);
        paramMap.put("goodsId", goodsId);
        paramMap.put("lotId", lotId);
        List<Cost> costList = costMapper.selectByParamMap(paramMap);
        if (costList == null || costList.size() == 0) {
            throw new InventoryBizException("", "企业ID" + enterpriseId + "商品ID" + goodsId + "批号ID" + lotId + "查不到SAAS_COST数据！");
        } else if ("add".equals(type)) {
            for (Cost cost : costList) {
                if (batchId.equals(cost.getBatchId()) && batchNum.equals(cost.getBatchNum())) {
                    cost.setUsableQuantity(cost.getUsableQuantity().add(lockQuantity).setScale(6, RoundingMode.HALF_UP));
                    costMapper.updateByPrimaryKeySelective(cost);
                }
            }
        } else if ("subtract".equals(type)) {
//			cost = costList.get(0);
//			cost.setUsableQuantity(cost.getUsableQuantity().subtract(lockQuantity).setScale(6, RoundingMode.HALF_UP));
//			costMapper.updateByPrimaryKey(cost);
            doLockCost(enterpriseId, parentId, send.getId(), send.getOrderType(),
                    send.getCode(), send.getDistrDate(), user, lockQuantity, costList);
        }
    }

    private PickDetail setPickDetail(DistrSendDetail sendDetail, PickOrder order,
                                     DistrSendOperationDtlVO dtlvo) {
        PickDetail dtl = new PickDetail();
        dtl.setEnterpriseId(order.getEnterpriseId());
        dtl.setParentId(order.getParentId());
        dtl.setPickId(order.getId());
        dtl.setOrderType(order.getOrderType());
        dtl.setPickCode(order.getCode());
        dtl.setPickDate(order.getPickDate());
        dtl.setBaseOrderDtlId(sendDetail.getId());
        dtl.setBaseOrderId(order.getBaseOrderId());
        dtl.setBaseOrderType(order.getBaseOrderType());
        dtl.setBaseOrderCode(order.getBaseOrderCode());
        dtl.setBaseOrderDate(order.getBaseOrderDate());
        dtl.setGoodsId(sendDetail.getGoodsId());
        dtl.setGoodsCode(sendDetail.getGoodsCode());
        dtl.setBarcode(sendDetail.getBarcode());
        dtl.setGoodsName(sendDetail.getGoodsName());
        dtl.setGoodsGenericName(sendDetail.getGoodsGenericName());
        dtl.setDosageId(sendDetail.getDosageId());
        dtl.setDosageName(sendDetail.getDosageName());
        dtl.setUnitId(sendDetail.getUnitId());
        dtl.setUnitName(sendDetail.getUnitName());
        dtl.setGoodsSpecification(sendDetail.getGoodsSpecification());
        dtl.setManufacturerId(sendDetail.getManufacturerId());
        dtl.setManufacturer(sendDetail.getManufacturer());
        dtl.setGoodsPlace(sendDetail.getGoodsPlace());
        dtl.setApprovalNumber(sendDetail.getApprovalNumber());
        dtl.setQuantity(dtlvo.getQuantity());
        dtl.setLineNum(dtlvo.getLineNum());
        dtl.setStatus(order.getStatus());
        dtl.setRemark(dtlvo.getRemark());
        dtl.setCreaterId(order.getCreaterId());
        dtl.setCreaterCode(order.getCreaterCode());
        dtl.setCreaterName(order.getCreaterName());
        dtl.setCreateTime(new Date());
        dtl.setModifierId(order.getCreaterId());
        dtl.setModifierCode(order.getCreaterCode());
        dtl.setModifierName(order.getCreaterName());
        dtl.setUpdateTime(new Date());
        return dtl;
    }

    private PickShelf setPickShelf(
            DistrSendOperationSlfVO distrSendOperationSlfVO, PickDetail pDtl) {
        PickShelf slf = new PickShelf();
        slf.setEnterpriseId(pDtl.getEnterpriseId());
        slf.setParentId(pDtl.getParentId());
        slf.setDtlId(pDtl.getId());
        slf.setPickId(pDtl.getPickId());
        slf.setGoodsId(pDtl.getGoodsId());
        slf.setGoodsCode(pDtl.getGoodsCode());
        slf.setGoodsName(pDtl.getGoodsName());
        slf.setLotId(distrSendOperationSlfVO.getLotId());
        LotNumber num = lotNumberMapper.selectByPrimaryKey(distrSendOperationSlfVO.getLotId());
        if (num == null) {
            throw new InventoryBizException("", "批号ID错误，查不到数据！");
        }
        slf.setLotNumber(num.getLotNum());
        slf.setProductDate(num.getProductDate());
        slf.setValidDate(num.getValidUntil());
        slf.setShelfId(distrSendOperationSlfVO.getShelfId());
        WarehouseShelf whSlf = warehouseShelfMapper.selectByPrimaryKey(distrSendOperationSlfVO.getShelfId());
        if (whSlf == null) {
            throw new InventoryBizException("", "货位ID错误，查不到数据！");
        }
        slf.setShelfName(whSlf.getName());
        slf.setShelfStatusDesc(distrSendOperationSlfVO.getShelfStatusDesc());
        slf.setQuantity(distrSendOperationSlfVO.getQuantity());
        slf.setLineNum(distrSendOperationSlfVO.getLineNum());
        slf.setStatus(pDtl.getStatus());
        slf.setRemark(pDtl.getRemark());
        slf.setCreaterId(pDtl.getCreaterId());
        slf.setCreaterCode(pDtl.getCreaterCode());
        slf.setCreaterName(pDtl.getCreaterName());
        slf.setCreateTime(new Date());
        slf.setModifierId(pDtl.getCreaterId());
        slf.setModifierCode(pDtl.getCreaterCode());
        slf.setModifierName(pDtl.getCreaterName());
        slf.setUpdateTime(new Date());
        return slf;
    }

    private StockLockRecord setLockRecord(PickShelf slf, DistrSend send) {
        StockLockRecord stockLockRecord = new StockLockRecord();
        stockLockRecord.setEnterpriseId(send.getEnterpriseId());
        stockLockRecord.setParentId(send.getParentId());
        stockLockRecord.setBaseOrderId(send.getId());
        stockLockRecord.setBaseOrderType(send.getOrderType());
        stockLockRecord.setBaseOrderCode(send.getCode());
        stockLockRecord.setBaseOrderDate(send.getDistrDate());
        stockLockRecord.setGoodsId(slf.getGoodsId());
        stockLockRecord.setGoodsCode(slf.getGoodsCode());
        stockLockRecord.setGoodsName(slf.getGoodsName());
        stockLockRecord.setLotId(slf.getLotId());
        stockLockRecord.setLotNum(slf.getLotNumber());
        stockLockRecord.setShelfId(slf.getShelfId());
        stockLockRecord.setShelfName(slf.getShelfName());
        stockLockRecord.setLockQuantity(slf.getQuantity());
        stockLockRecord.setCreaterId(send.getCreaterId());
        stockLockRecord.setCreaterCode(send.getCreaterCode());
        stockLockRecord.setCreaterName(send.getCreaterName());
        stockLockRecord.setCreateTime(new Date());
        return stockLockRecord;
    }

    @Transactional(rollbackFor = Exception.class)
    public DistrOut genrateAndSaveDistrOut(DistrOut distrOut, UserVO userVO) throws Exception {
        DistrOut copy = (DistrOut) EntityUtils.reflectAddSetDefaultValue(new DistrOut().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOut, copy);
        // 设置单据类型
        distrOut.setOrderType(OrderRule.DISTR_OUT.getType());
        distrOut.setUpdateTime(new Date());

        if (null == distrOut.getBaseOrderId()) {
            throw new BusinessException("配货出库单的基础单价id为空");
        }

        DistrSend distrSend = distrSendMapper.selectByPrimaryKey(distrOut.getBaseOrderId());
        if (distrSend == null) {
            throw new BusinessException("没有找到该配送单,请检查配送单id=" + distrOut.getBaseOrderId());
        }
        if (distrSend.getStatus() != DistrSendStatus.WAIT_OUT && distrSend.getStatus() != DistrSendStatus.OUTING && distrSend.getStatus() != DistrSendStatus.AUDIT_REBUT) {
            throw new BusinessException("该配送单无法出库,请检查配送单状态");
        }
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl = manage.getBusinessControl() == 0 ? true : false;
        if (zl) {
            //质量流程开的时候前台必须提供采购人员id
            User user = userMapper.selectByPrimaryKey(distrOut.getOutManId());
            if (user == null) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            if (user.getEnterpriseId().intValue() != userVO.getEnterpriseId().intValue()) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            distrOut.setOutDate(DateUtils.StringToDate(distrOut.getOutDateStr()));
            distrOut.setOutManId(user.getId());
            distrOut.setOutManCode(user.getCode());
            distrOut.setOutManName(user.getName());
        } else {
            distrOut.setOutDate(new Date());
            distrOut.setOutManId(userVO.getUserId());
            distrOut.setOutManCode(userVO.getUserCode());
            distrOut.setOutManName(userVO.getUserName());
        }
        calculationPrice(distrOut);
        distrOut.setStatus(DistrOutStatus.WAIT_AUDIT);
        distrOut.setCode(orderCodeComponent.generate(OrderRule.DISTR_OUT.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        distrOutMapper.insertSelective(distrOut);
        Long outId = distrOut.getId();
        for (DistrOutDetail distrOutDetail : distrOut.getDistrOutDetailList()) {
            BigDecimal quantity = new BigDecimal(0);
            for (DistrOutShelf distrOutShelf : distrOutDetail.getDistrOutShelfList()) {
                quantity = quantity.add(distrOutShelf.getQuantity());
            }
            distrOutDetail.setQuantity(quantity);
        }
        for (DistrOutDetail distrOutDetail : distrOut.getDistrOutDetailList()) {
            distrOutDetail.setOutId(outId);
            distrOutDetail.setOutCode(distrOut.getCode());
            distrOutDetail.setOutDate(distrOut.getOutDate());
            distrOutDetail.setOrderType(distrOut.getOrderType());
            distrOutDetail.setUpdateTime(new Date());
            distrOutDetail.setModifierCode(userVO.getUserCode());
            distrOutDetail.setModifierId(userVO.getUserId());
            distrOutDetail.setModifierName(userVO.getUserName());
            distrOutDetail.setBaseOrderId(distrOut.getBaseOrderId());
            distrOutDetail.setBaseOrderType(distrOut.getBaseOrderType());
            distrOutDetail.setBaseOrderCode(distrOut.getBaseOrderCode());
            distrOutDetail.setBaseOrderDate(distrOut.getBaseOrderDate());
            distrOutDetail.setStatus(DistrOutStatus.WAIT_AUDIT);

            distrOutDetailMapper.insertSelective(distrOutDetail);
            long outDtlId = distrOutDetail.getId();
            BigDecimal quantity = new BigDecimal(0);
            for (DistrOutShelf distrOutShelf : distrOutDetail.getDistrOutShelfList()) {
                distrOutShelf.setOutId(outId);
                distrOutShelf.setDtlId(outDtlId);
                distrOutShelf.setEnterpriseId(distrOutDetail.getEnterpriseId());
                distrOutShelf.setParentId(distrOutDetail.getParentId());
                distrOutShelf.setCreaterId(userVO.getUserId());
                distrOutShelf.setCreaterName(userVO.getUserName());
                distrOutShelf.setCreaterCode(userVO.getUserCode());
                distrOutShelf.setCreateTime(new Date());
                distrOutShelf.setUpdateTime(new Date());
                distrOutShelf.setModifierId(userVO.getUserId());
                distrOutShelf.setModifierCode(userVO.getUserCode());
                distrOutShelf.setModifierName(userVO.getUserName());

                //单价
                distrOutShelf.setUnitPrice(distrOutDetail.getUnitPrice());
                distrOutShelf.setStatus(DistrOutStatus.WAIT_AUDIT);

                distrOutShelf.setShelfId(distrOutShelf.getShelfId());
                distrOutShelf.setLotId(distrOutShelf.getLotId());
                distrOutShelf.setLotNumber(distrOutShelf.getLotNumber());
                distrOutShelf.setLineNum(distrOutShelf.getLineNum());
                BigDecimal quantityShelf = distrOutShelf.getQuantity();
                distrOutShelf.setQuantity(quantityShelf);

                // 重算单价金额等相关信息

                // 单价
                BigDecimal unitPrice = distrOutDetail.getUnitPrice();
                // 行折扣
                BigDecimal lineDiscount = distrOutDetail.getLineDiscount();
                // 整单折扣
                BigDecimal wholeDiscount = distrOutDetail.getWholeDiscount();
                // 整单优惠
                BigDecimal wholeRuondOff = distrOut.getWholeDiscountAmount();
                Long taxRateId = distrOutDetail.getTaxRateId();
                BigDecimal taxRate = distrOutDetail.getTaxRate();
                // 整单金额合计
                BigDecimal wholeAmountTotal = distrOutDetail.getAmount();
                // 重算货位明细单单价、金额信息
                CalculateResultModel resultModel = CalculateComponent.getCalculateResult(quantityShelf, distrOutDetail.getUnitPrice(), lineDiscount,
                        wholeDiscount, wholeRuondOff, taxRate, wholeAmountTotal);
                distrOutShelf.setUnitPrice(unitPrice);
                distrOutShelf.setLineDiscount(lineDiscount);
                distrOutShelf.setAmount(resultModel.getAmount());
                distrOutShelf.setWholeDiscount(wholeDiscount);
                distrOutShelf.setLineDiscountAmount(resultModel.getLineRoundOff());
                distrOutShelf.setRealPrice(resultModel.getRealPrice());
                distrOutShelf.setRealAmount(resultModel.getRealAmount());
                distrOutShelf.setTaxRateId(taxRateId);
                distrOutShelf.setTaxRate(taxRate);
                distrOutShelf.setNotaxRealPrice(resultModel.getNotaxPrice());
                distrOutShelf.setNotaxRealAmount(resultModel.getNotaxAmount());
                distrOutShelf.setTaxAmount(resultModel.getTaxAmount());
                distrOutShelf.setUnclearQuantity(quantityShelf);
                distrOutShelf.setClearQuantity(BigDecimal.ZERO);
                distrOutShelf.setVerificationQuantity(BigDecimal.ZERO);
                distrOutShelf.setUnverificationQuantity(quantityShelf);

                quantity = quantity.add(quantityShelf);
                distrOutShelfMapper.insertSelective(distrOutShelf);
            }
            if (distrOut.getBaseOrderId() != null) {
                Long baseDtlId = distrOutDetail.getBaseOrderDtlId();
                if (baseDtlId == null) {
                    throw new BusinessException("出库单明细基础明细id不能为空");
                }
                DistrSendDetail distrSendDetail = distrSendDetailMapper.selectByPrimaryKey(baseDtlId);
                if (distrSendDetail == null) {
                    throw new BusinessException("出库单明细基础明细id对应明细信息缺失,请检查数据");
                }
            }
            if (quantity.compareTo(distrOutDetail.getQuantity()) != 0) {
                throw new BusinessException("出库数量与配送单不一致,请检查出库数量!");
            }
            if (distrOutDetail.getQuantity().compareTo(distrOutDetail.getQuantity()) != 0) {
                throw new BusinessException("出库数量与明细数量不一致,请检查出库数量!");
            }
        }


        if (distrOut.getBaseOrderId() != null) {
            //修改出库单状态为出库中
            distrSend.setStatus(DistrSendStatus.OUTING);
            distrSend.setUpdateTime(new Date());
            distrSend.setModifierCode(userVO.getUserCode());
            distrSend.setModifierId(userVO.getUserId());
            distrSend.setModifierName(userVO.getUserName());
            distrSendMapper.updateByPrimaryKey(distrSend);
        }
        return distrOut;
    }


    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveDistrOut(UserVO loginUser, SaveDistrOutVO saveDistrOutVO, boolean isOut) throws Exception {

        if (saveDistrOutVO.getCallDataId() != null) {
            if (saveDistrOutVO.getCallDataType().intValue() == OrderRule.PURCHASE_IN.getType().intValue()) {//调用采购入库
                PurchaseInStorage purchaseInStorage = purchaseInStorageMapper.selectByPrimaryKey(saveDistrOutVO.getCallDataId());
                if (purchaseInStorage == null) {
                    throw new BusinessException("无效的采购入库单ID：" + saveDistrOutVO.getCallDataId());
                }
                purchaseInStorage.setIsUse(1);
                purchaseInStorageMapper.updateByPrimaryKeySelective(purchaseInStorage);
            } else if (saveDistrOutVO.getCallDataType().intValue() == OrderRule.DISTR_RETURN_IN.getType().intValue()) {//调用配后退回入库
                DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(saveDistrOutVO.getCallDataId());
                if (distrReturnIn == null) {
                    throw new BusinessException("无效的配后退回入库单ID：" + saveDistrOutVO.getCallDataId());
                }
                distrReturnIn.setReferenceOrderId(saveDistrOutVO.getCallDataId());
                distrReturnIn.setReferenceOrderType(OrderRule.DISTR_RETURN_IN.getType());
                distrReturnIn.setStatus(DistrReturnStatus.FINISHED);
                distrReturnInMapper.updateByPrimaryKeySelective(distrReturnIn);

                List<DistrReturnInDetail> distrReturnInDetails = distrReturnInDetailMapper.selectByReturnId(distrReturnIn.getId());
                for (DistrReturnInDetail distrReturnInDetail : distrReturnInDetails) {
                    distrReturnInDetail.setStatus(DistrReturnStatus.FINISHED);
                    distrReturnInDetail.setClearQuantity(distrReturnInDetail.getQuantity());
                    distrReturnInDetail.setUnclearQuantity(BigDecimal.ZERO);
                    distrReturnInDetailMapper.updateByPrimaryKeySelective(distrReturnInDetail);

                    List<DistrReturnInShelf> distrReturnInShelves = distrReturnInShelfMapper.getDistrReturnInShelf(distrReturnInDetail.getId());
                    for (DistrReturnInShelf distrReturnInShelf : distrReturnInShelves) {
                        distrReturnInShelf.setStatus(DistrReturnStatus.FINISHED);
                        distrReturnInShelf.setClearQuantity(distrReturnInShelf.getQuantity());
                        distrReturnInShelf.setUnclearQuantity(BigDecimal.ZERO);
                        distrReturnInShelfMapper.updateByPrimaryKeySelective(distrReturnInShelf);
                    }

                }

            }
        }
        //生成出库单
        DistrOut distrOut = new DistrOut();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrOutVO, distrOut);
        UserEnterpriseUtils.setUserCreateOrModify(distrOut, loginUser, true);
        if (saveDistrOutVO.getOutDate().before(saveDistrOutVO.getDistrDate())) {
            throw new BusinessException("出库日期不能早于配货日期");
        }
        distrOut.setEnterpriseId(loginUser.getEnterpriseId());
        distrOut.setParentId(loginUser.getParentId());
        distrOut.setOrderType(OrderRule.DISTR_OUT.getType());
        distrOut.setCode(orderCodeComponent.generate(OrderRule.DISTR_OUT.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        distrOut.setBaseOrderDate(saveDistrOutVO.getDistrDate());
        distrOut.setStatus(DistrOutStatus.WAIT_AUDIT);
        if (distrOut.getRequestUnitId() != null) {
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrOut.getRequestUnitId());
            if (enterprise != null) {
                distrOut.setRequestUnitCode(enterprise.getCode());
                distrOut.setRequestUnitName(enterprise.getName());
            }
        }
        if (distrOut.getSendManId() != null) {
            User user = userMapper.selectByPrimaryKey(distrOut.getSendManId());
            if (user != null) {
                distrOut.setSendManCode(user.getCode());
                distrOut.setSendManName(user.getName());
            }
        }
        if (distrOut.getOutManId() != null) {
            User user = userMapper.selectByPrimaryKey(distrOut.getOutManId());
            if (user != null) {
                distrOut.setOutManCode(user.getCode());
                distrOut.setOutManName(user.getName());
            }
        }
        //计算总单金额
        calculationDistrOutPrice(distrOut, saveDistrOutVO);
        distrOutMapper.insertSelective(distrOut);

        Map<Long, Long> distrOutDetailAndReturnInMap = saveDistrOutDetail(distrOut, saveDistrOutVO, loginUser);

        Map<String, Object> map = new HashMap<>();
        map.put("distrOut", distrOut);

        if (isOut) {
            DistrSend distrSend = saveDistrSend(loginUser, distrOut, saveDistrOutVO, distrOutDetailAndReturnInMap);
            map.put("distrSend", distrSend);
            DistrSendOperationVO distrSendOperationVO = DistrSendOperationVO.getDistrSendOperationVO(distrSend, distrOut);
            PickOrder pickOrder = saveDistrPick(distrSendOperationVO, loginUser);
            map.put("pickOrder", pickOrder);
        }


        return map;
    }

    private DistrSend saveDistrSend(UserVO userVO, DistrOut distrOut, SaveDistrOutVO saveDistrOutVO, Map<Long, Long> distrOutDetailAndReturnInMap) throws Exception {

        //生成配货单
        DistrSend distrSend = new DistrSend();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOut, distrSend);
        distrSend.setEnterpriseId(userVO.getEnterpriseId());
        distrSend.setParentId(userVO.getParentId());
        distrSend.setOrderType(OrderRule.DISTR_ORDER.getType());
        distrSend.setDistrDate(distrOut.getBaseOrderDate());
        distrSend.setCode(orderCodeComponent.generate(OrderRule.DISTR_ORDER.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        distrSend.setDistrManId(distrOut.getSendManId());
        distrSend.setDistrManName(distrOut.getSendManName());
        distrSend.setDistrManCode(distrOut.getSendManCode());
        distrSend.setDistrRule(DistributionRule.DISTRIBUTION_RULE_A.getCode());
        distrSend.setLackHandle(LackHandle.LackHandle_B.getCode());
        distrSend.setStatus(DistrSendStatus.OUTING);
        distrSend.setId(null);
        UserEnterpriseUtils.setUserCreateOrModify(distrSend, userVO, true);
        distrSendMapper.insertSelective(distrSend);

        distrOut.setBaseOrderId(distrSend.getId());
        distrOut.setBaseOrderCode(distrSend.getCode());
        distrOut.setBaseOrderType(distrSend.getOrderType());
        distrOut.setBaseOrderDate(distrSend.getDistrDate());
        distrOutMapper.updateByPrimaryKeySelective(distrOut);

        List<DistrOutDetail> distrOutDetails = distrOutDetailMapper.getDistrOutDetailList(distrOut.getId());
        for (DistrOutDetail distrOutDetail : distrOutDetails) {
            DistrSendDetail distrSendDetail = new DistrSendDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOutDetail, distrSendDetail);
            distrSendDetail.setId(null);
            distrSendDetail.setEnterpriseId(userVO.getEnterpriseId());
            distrSendDetail.setParentId(distrOutDetail.getParentId());
            distrSendDetail.setDistrId(distrSend.getId());
            distrSendDetail.setDistrDate(distrSend.getDistrDate());
            distrSendDetail.setDistrCode(distrSend.getCode());
            distrSendDetail.setOrderType(distrSend.getOrderType());
            distrSendDetail.setStatus(DistrSendStatus.OUTING);
            //上级单据
            if (saveDistrOutVO.getCallDataId() != null) {
                if (saveDistrOutVO.getCallDataType().intValue() == OrderRule.PURCHASE_IN.getType().intValue()) {//调用采购入库
                    PurchaseInStorage purchaseInStorage = purchaseInStorageMapper.selectByPrimaryKey(saveDistrOutVO.getCallDataId());
                    if (purchaseInStorage == null) {
                        throw new BusinessException("无效的采购入库单ID：" + saveDistrOutVO.getCallDataId());
                    }
                    distrSendDetail.setBaseOrderId(purchaseInStorage.getId());
                    distrSendDetail.setBaseOrderCode(purchaseInStorage.getCode());
                    distrSendDetail.setBaseOrderDate(purchaseInStorage.getInStorageDate());
                    distrSendDetail.setBaseOrderType(purchaseInStorage.getOrderType());

                    DistrPurchaseInstorageDetailShelfVO purchaseInStorageDetail = purchaseInStorageDetailMapper.getGoodsIdAndInStorageId(purchaseInStorage.getId(), distrSendDetail.getGoodsId());
                    if (purchaseInStorageDetail != null) {
                        distrSendDetail.setBaseOrderDtlId(purchaseInStorageDetail.getId());
                    }
                } else if (saveDistrOutVO.getCallDataType().intValue() == OrderRule.DISTR_RETURN_IN.getType().intValue()) {//调用配后退回入库
                    DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(saveDistrOutVO.getCallDataId());
                    if (distrReturnIn == null) {
                        throw new BusinessException("无效的配后退回入库单ID：" + saveDistrOutVO.getCallDataId());
                    }
                    distrSendDetail.setBaseOrderId(distrReturnIn.getId());
                    distrSendDetail.setBaseOrderCode(distrReturnIn.getCode());
                    distrSendDetail.setBaseOrderDate(distrReturnIn.getReturnInDate());
                    distrSendDetail.setBaseOrderType(distrReturnIn.getOrderType());
                    Long aLong = distrOutDetailAndReturnInMap.get(distrOutDetail.getId());
                    distrSendDetail.setBaseOrderDtlId(aLong);
					/*DistrReturnInDetailShelfVO distrReturnInDetail = distrReturnInDetailMapper.getGoodsIdAndInStorageId(distrReturnIn.getId(), distrSendDetail.getGoodsId());
					if (distrReturnInDetail != null) {
						distrSendDetail.setBaseOrderDtlId(distrReturnInDetail.getId());
					}*/
                }
            }

            //总部库存使用量
            BigDecimal parentUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getEnterpriseId(), distrSendDetail.getGoodsId());
            if (parentUsableQuantity == null) {
                parentUsableQuantity = BigDecimal.ZERO;
            }
            distrSendDetail.setParentUsableQuantity(parentUsableQuantity);

            //门店库存可用量
            BigDecimal usableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getRequestUnitId(), distrSendDetail.getGoodsId());
            if (usableQuantity == null) {
                usableQuantity = BigDecimal.ZERO;
            }
            distrSendDetail.setUsableQuantity(usableQuantity);

            UserEnterpriseUtils.setUserCreateOrModify(distrSendDetail, userVO, true);

            distrSendDetailMapper.insertSelective(distrSendDetail);


            DistrOutDetail newDistrOutDetail = new DistrOutDetail();
            newDistrOutDetail.setId(distrOutDetail.getId());
            newDistrOutDetail.setBaseOrderDtlId(distrSendDetail.getId());
            newDistrOutDetail.setBaseOrderId(distrSend.getId());
            newDistrOutDetail.setBaseOrderType(distrSend.getOrderType());
            newDistrOutDetail.setBaseOrderCode(distrSend.getCode());
            distrOutDetail.setBaseOrderDtlId(distrSendDetail.getId());
            distrOutDetail.setBaseOrderId(distrSend.getId());
            distrOutDetail.setBaseOrderType(distrSend.getOrderType());
            distrOutDetail.setBaseOrderCode(distrSend.getCode());
            distrOutDetailMapper.updateByPrimaryKeySelective(newDistrOutDetail);
            distrSend.getDistrSendDetailList().add(distrSendDetail);

            // 锁定库存
            lockStockByDetail(userVO, distrSend, distrOutDetail);

        }

        List<DistrOutDetail> oldDistrOutDetails = distrOut.getDistrOutDetailList();
        for (DistrOutDetail oldDistrOutDetail : oldDistrOutDetails) {

            for (DistrOutDetail distrOutDetail : distrOutDetails) {
                if (oldDistrOutDetail.getId().equals(distrOutDetail.getId())) {
                    distrOutDetail.setDistrOutShelfList(oldDistrOutDetail.getDistrOutShelfList());
                }
            }
        }
        distrOut.setDistrOutDetailList(distrOutDetails);
        return distrSend;
    }

    //新增单据时调用
    private void calculationDistrOutDetailPrice(DistrOut distrOut, DistrOutDetail distrOutDetail, SaveDistrOutDetailVO saveDistrOutDetailVO) {
        //获取默认税率
        if (saveDistrOutDetailVO.getTaxRateId() == null) {
            //税率
            Map<String, Object> distrTaxRateMap = getDistrTaxRate(distrOut.getRequestUnitId(), saveDistrOutDetailVO.getGoodsId());
            if (distrTaxRateMap != null) {
                saveDistrOutDetailVO.setTaxRateId(distrTaxRateMap.get("distrTaxRateId") == null ? 0 : (Long) distrTaxRateMap.get("distrTaxRateId"));
                saveDistrOutDetailVO.setTaxRate(distrTaxRateMap.get("distrTaxRate") == null ? BigDecimal.ZERO : new BigDecimal((Double) distrTaxRateMap.get("distrTaxRate")));
            } else {
                saveDistrOutDetailVO.setTaxRateId(new Long(0));
                saveDistrOutDetailVO.setTaxRate(BigDecimal.ZERO);
            }
        }


        distrOutDetail.setQuantity(saveDistrOutDetailVO.getQuantity());
        //单价
        distrOutDetail.setUnitPrice(saveDistrOutDetailVO.getUnitPrice());
        //行折扣
        distrOutDetail.setLineDiscount(saveDistrOutDetailVO.getLineDiscount());
        //金额（整单优惠前金额） = 数量*单价*行折扣
        BigDecimal lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(saveDistrOutDetailVO.getQuantity(), saveDistrOutDetailVO.getUnitPrice(), saveDistrOutDetailVO.getLineDiscount());
        distrOutDetail.setAmount(lineAmount);
        //整单折扣（%）
        distrOutDetail.setWholeDiscount(distrOut.getWholeDiscount());
        //优惠分摊 = 整单优惠*(金额/sum(金额))
        BigDecimal lineDiscountAmount = BigDecimal.ZERO;
        if (distrOut.getAmountTotal().compareTo(BigDecimal.ZERO) == 1) {
            lineDiscountAmount = distrOut.getWholeDiscountAmount().multiply(lineAmount.divide(distrOut.getAmountTotal(), 2, BigDecimal.ROUND_HALF_UP));
        }
        distrOutDetail.setLineDiscountAmount(lineDiscountAmount);
        //行实际金额 = 数量*单价*行折扣*整单折扣-优惠分摊
        BigDecimal lineRealAmount = lineAmount
                .multiply(distrOut.getWholeDiscount())
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                .subtract(lineDiscountAmount);
        if (lineRealAmount.compareTo(BigDecimal.ZERO) < 0) {
            lineRealAmount = BigDecimal.ZERO;
        }
        distrOutDetail.setRealAmount(lineRealAmount);
        //实际单价：实际金额/数量
        distrOutDetail.setRealPrice(lineRealAmount.divide(saveDistrOutDetailVO.getQuantity(), 2, BigDecimal.ROUND_HALF_UP));
        //税率ID
        distrOutDetail.setTaxRateId(saveDistrOutDetailVO.getTaxRateId());
        //税率
        distrOutDetail.setTaxRate(saveDistrOutDetailVO.getTaxRate());
        //不含税实际金额
        BigDecimal notaxRealAmount = lineRealAmount.divide(new BigDecimal(1).add(saveDistrOutDetailVO.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
        distrOutDetail.setNotaxRealAmount(notaxRealAmount);
        //不含税实际单价
        distrOutDetail.setNotaxRealPrice(notaxRealAmount.divide(saveDistrOutDetailVO.getQuantity(), 2, BigDecimal.ROUND_HALF_UP));
        //税额
        distrOutDetail.setTaxAmount(lineRealAmount.subtract(notaxRealAmount));
    }

    private Map<Long, Long> saveDistrOutDetail(DistrOut distrOut, SaveDistrOutVO saveDistrOutVO, UserVO loginUser) throws Exception {

        Map<Long, Long> distrOutDetailAndReturnInMap = new HashMap<>();

        for (SaveDistrOutDetailVO saveDistrOutDetailVO : saveDistrOutVO.getSaveDistrOutDetailVOList()) {
            DistrOutDetail distrOutDetail = new DistrOutDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrOutDetailVO, distrOutDetail);
            UserEnterpriseUtils.setUserCreateOrModify(distrOutDetail, loginUser, true);
            distrOutDetail.setEnterpriseId(loginUser.getEnterpriseId());
            distrOutDetail.setParentId(distrOut.getParentId());
            distrOutDetail.setOutId(distrOut.getId());
            distrOutDetail.setOutCode(distrOut.getCode());
            distrOutDetail.setOutDate(distrOut.getOutDate());
            distrOutDetail.setOrderType(distrOut.getOrderType());
            distrOutDetail.setBaseOrderDate(saveDistrOutVO.getDistrDate());

            //计算明细金额
            calculationDistrOutDetailPrice(distrOut, distrOutDetail, saveDistrOutDetailVO);

            distrOutDetail.setWholeDiscount(distrOut.getWholeDiscount());
            distrOutDetail.setUnclearQuantity(saveDistrOutDetailVO.getQuantity());
            distrOutDetail.setClearQuantity(BigDecimal.ZERO);
            distrOutDetail.setLineNum(saveDistrOutDetailVO.getLineNum());
            distrOutDetail.setStatus(DistrOutStatus.WAIT_AUDIT);
            distrOutDetail.setRemark(saveDistrOutDetailVO.getRemark());

            Goods goods = goodsMapper.selectByPrimaryKey(saveDistrOutDetailVO.getGoodsId());
            if (goods == null) {
                throw new BusinessException("无效的商品ID：" + saveDistrOutDetailVO.getGoodsId());
            }
            distrOutDetail.setGoodsCode(goods.getCode() == null ? "" : goods.getCode());
            distrOutDetail.setBarcode(goods.getBarcode() == null ? "" : goods.getBarcode());
            distrOutDetail.setGoodsName(goods.getName() == null ? "" : goods.getName());
            distrOutDetail.setGoodsGenericName(goods.getGenericName() == null ? "" : goods.getGenericName());
            distrOutDetail.setDosageId(goods.getDosageId());
            distrOutDetail.setDosageName(goods.getDosageName() == null ? "" : goods.getDosageName());
            distrOutDetail.setUnitId(goods.getUnitId());
            distrOutDetail.setUnitName(goods.getUnitName() == null ? "" : goods.getUnitName());
            distrOutDetail.setGoodsSpecification(goods.getSpecification() == null ? "" : goods.getSpecification());
            distrOutDetail.setManufacturerId(goods.getManufacturerId());
            distrOutDetail.setManufacturer(goods.getManufacturer() == null ? "" : goods.getManufacturer());
            distrOutDetail.setGoodsPlace(goods.getPlace() == null ? "" : goods.getPlace());
            distrOutDetail.setApprovalNumber(goods.getApprovalNumber() == null ? "" : goods.getApprovalNumber());
            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(saveDistrOutDetailVO.getTaxRateId());
            if (goodsTaxRate == null) {
                throw new BusinessException("没有查询到税率ID：" + saveDistrOutDetailVO.getTaxRateId() + "的税率值");
            }
            distrOutDetail.setTaxRate(goodsTaxRate.getTaxRate());


            distrOutDetailMapper.insertSelective(distrOutDetail);

            distrOutDetailAndReturnInMap.put(distrOutDetail.getId(), saveDistrOutDetailVO.getCallDataDtlId());

            distrOut.getDistrOutDetailList().add(distrOutDetail);

            BigDecimal qtyTotal = BigDecimal.ZERO;
            for (SaveDistrOutShelfVO saveDistrOutShelfVO : saveDistrOutDetailVO.getSaveDistrOutShelfVOList()) {
                DistrOutShelf distrOutShelf = new DistrOutShelf();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrOutShelfVO, distrOutShelf);
                UserEnterpriseUtils.setUserCreateOrModify(distrOutShelf, loginUser, true);
                qtyTotal = qtyTotal.add(distrOutShelf.getQuantity());
                distrOutShelf.setEnterpriseId(loginUser.getEnterpriseId());
                distrOutShelf.setParentId(loginUser.getParentId());
                distrOutShelf.setDtlId(distrOutDetail.getId());
                distrOutShelf.setOutId(distrOutDetail.getOutId());
                distrOutShelf.setGoodsCode(distrOutDetail.getGoodsCode());
                distrOutShelf.setGoodsName(distrOutDetail.getGoodsName());
                LotNumber lotNumber = new LotNumber();
                if (saveDistrOutShelfVO.getLotId() != null) {
                    lotNumber = lotNumberMapper.selectByPrimaryKey(saveDistrOutShelfVO.getLotId());
                    if (lotNumber == null) {
                        throw new BusinessException("无效的批号ID：" + saveDistrOutShelfVO.getLotId());
                    }
                } else {
                    lotNumber = lotNumberMapper.selectByLotNumAndGoodsId(saveDistrOutShelfVO.getGoodsId(), saveDistrOutShelfVO.getLotNum(), loginUser.getEnterpriseId());
                    if (lotNumber == null) {
                        throw new BusinessException("无效的批号：" + saveDistrOutShelfVO.getLotNum());
                    }
                }
                distrOutShelf.setLotId(lotNumber.getId());
                distrOutShelf.setLotNumber(lotNumber.getLotNum());
                distrOutShelf.setProductDate(lotNumber.getProductDate());
                distrOutShelf.setValidDate(lotNumber.getValidUntil());
                distrOutShelf.setQuantity(saveDistrOutShelfVO.getQuantity());
                distrOutShelf.setUnitPrice(distrOutDetail.getUnitPrice());
                // 重算货位明细单单价、金额信息
                CalculateResultModel resultModel = CalculateComponent.getCalculateResult(saveDistrOutShelfVO.getQuantity(), distrOutDetail.getUnitPrice(), distrOutDetail.getLineDiscount(),
                        distrOutDetail.getWholeDiscount(), distrOut.getWholeDiscountAmount(), distrOutDetail.getTaxRate(), distrOut.getAmountTotal());
                distrOutShelf.setLineDiscount(distrOutDetail.getLineDiscount());
                distrOutShelf.setAmount(resultModel.getAmount());
                distrOutShelf.setLineDiscountAmount(resultModel.getLineRoundOff());
                distrOutShelf.setRealPrice(resultModel.getRealPrice());
                distrOutShelf.setRealAmount(resultModel.getRealAmount());
                distrOutShelf.setNotaxRealAmount(resultModel.getNotaxAmount());
                distrOutShelf.setNotaxRealPrice(resultModel.getNotaxPrice());
                distrOutShelf.setTaxAmount(resultModel.getTaxAmount());
                distrOutShelf.setUnclearQuantity(saveDistrOutShelfVO.getQuantity());
                distrOutShelf.setWholeDiscount(distrOutDetail.getWholeDiscount());
                distrOutShelf.setClearQuantity(BigDecimal.ZERO);
                distrOutShelf.setVerificationQuantity(BigDecimal.ZERO);
                distrOutShelf.setUnverificationQuantity(saveDistrOutShelfVO.getQuantity());
                distrOutShelf.setLineNum(saveDistrOutShelfVO.getLineNum());
                distrOutShelf.setStatus(DistrOutStatus.WAIT_AUDIT);
                goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(distrOutDetail.getTaxRateId());
                if (goodsTaxRate == null) {
                    throw new BusinessException("没有查询到税率ID：" + distrOutDetail.getTaxRateId() + "的税率值");
                }
                distrOutShelf.setTaxRate(goodsTaxRate.getTaxRate());
                distrOutShelf.setTaxRateId(distrOutDetail.getTaxRateId());
                distrOutShelfMapper.insertSelective(distrOutShelf);
                distrOutDetail.getDistrOutShelfList().add(distrOutShelf);
            }
            if (qtyTotal.compareTo(distrOutDetail.getQuantity()) != 0) {
                throw new BusinessException("商品的批号货位数量与明细数量不一致");
            }
        }

        return distrOutDetailAndReturnInMap;
    }

    private void lockStockByDetail(UserVO userVO, DistrSend distrSend, DistrOutDetail distrOutDetail) {
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setEnterpriseId(distrSend.getEnterpriseId());
        lockQtyArgVO.setParentId(distrSend.getParentId());
        lockQtyArgVO.setBaseOrderId(distrSend.getId());
        lockQtyArgVO.setBaseOrderCode(distrSend.getCode());
        lockQtyArgVO.setBaseOrderType(distrSend.getOrderType());
        lockQtyArgVO.setBaseOrderDate(distrSend.getDistrDate());
        lockQtyArgVO.setGoodsId(distrOutDetail.getGoodsId());
        lockQtyArgVO.setQuantity(distrOutDetail.getQuantity());
        lockQtyArgVO.setUser(userVO);
        lockStockAndCost(lockQtyArgVO);
    }

	/*private void lockStockByDetail(UserVO userVO, DistrOutDetail distrOutDetail, DistrOutShelf distrOutShelf) {
		LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
		lockQtyArgVO.setEnterpriseId(distrOutDetail.getEnterpriseId());
		lockQtyArgVO.setParentId(distrOutDetail.getParentId());
		lockQtyArgVO.setBaseOrderId(distrOutDetail.getOutId());
		lockQtyArgVO.setBaseOrderCode(distrOutDetail.getOutCode());
		lockQtyArgVO.setBaseOrderType(distrOutDetail.getOrderType());
		lockQtyArgVO.setBaseOrderDate(distrOutDetail.getOutDate());
		lockQtyArgVO.setGoodsId(distrOutDetail.getGoodsId());
		lockQtyArgVO.setQuantity(distrOutShelf.getQuantity());
		lockQtyArgVO.setUser(userVO);
		lockQtyArgVO.setShelfId(distrOutShelf.getShelfId());
		lockStockAndCost(lockQtyArgVO);
	}*/

    //新增单据时调用
    private void calculationDistrOutPrice(DistrOut distrOut, SaveDistrOutVO saveDistrOutVO) {
        //数量合计
        BigDecimal quantityTotal = BigDecimal.ZERO;
        //整单金额
        BigDecimal amountTotal = BigDecimal.ZERO;
        //行金额
        BigDecimal lineAmount;
        for (SaveDistrOutDetailVO saveDistrOutDetailVO : saveDistrOutVO.getSaveDistrOutDetailVOList()) {
            quantityTotal = quantityTotal.add(saveDistrOutDetailVO.getQuantity());
            //行金额=数量*单价*行折扣
            lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(saveDistrOutDetailVO.getQuantity(), saveDistrOutDetailVO.getUnitPrice(), saveDistrOutDetailVO.getLineDiscount());
            amountTotal = amountTotal.add(lineAmount);
        }

        //数量合计
        distrOut.setQuantityTotal(quantityTotal);
        //品种合计
        distrOut.setVarietiesQuantity(saveDistrOutVO.getSaveDistrOutDetailVOList().size());
        //金额合计（整单优惠前的金额合计）
        distrOut.setAmountTotal(amountTotal);
        //整单折扣
        distrOut.setWholeDiscount(saveDistrOutVO.getWholeDiscount());
        //整单优惠
        distrOut.setWholeDiscountAmount(saveDistrOutVO.getWholeDiscountAmount());
        //实际金额
        BigDecimal realAmountTotal = amountTotal.multiply(saveDistrOutVO.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).subtract(saveDistrOutVO.getWholeDiscountAmount());
        if (realAmountTotal.compareTo(BigDecimal.ZERO) < 0) {
            realAmountTotal = BigDecimal.ZERO;
        }
        distrOut.setRealAmountTotal(realAmountTotal);

        //行实际金额
        BigDecimal lineRealAmount;
        //行不含税实际金额
        BigDecimal notaxRealAmount;
        //行优惠（总优惠分摊）
        BigDecimal lineDiscountAmount = BigDecimal.ZERO;
        //行税额
        BigDecimal lineTaxAmount;
        //税额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        for (SaveDistrOutDetailVO saveDistrOutDetailVO : saveDistrOutVO.getSaveDistrOutDetailVOList()) {
            //行金额=数量*单价*行折扣
            lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(saveDistrOutDetailVO.getQuantity(), saveDistrOutDetailVO.getUnitPrice(), saveDistrOutDetailVO.getLineDiscount());
            //优惠分摊 = 整单优惠*(金额/sum(金额))
            if (amountTotal.compareTo(BigDecimal.ZERO) == 1) {
                lineDiscountAmount = saveDistrOutVO.getWholeDiscountAmount().multiply(lineAmount.divide(amountTotal, 2, BigDecimal.ROUND_HALF_UP));
            }
            //行实际金额 = 数量*单价*行折扣*整单折扣-优惠分摊
            lineRealAmount = lineAmount
                    .multiply(saveDistrOutVO.getWholeDiscount())
                    .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                    .subtract(lineDiscountAmount);
            if (lineRealAmount.compareTo(BigDecimal.ZERO) < 0) {
                lineRealAmount = BigDecimal.ZERO;
            }
            //不含税实际金额：
            notaxRealAmount = lineRealAmount.divide(new BigDecimal(1).add(saveDistrOutDetailVO.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
            //行税额
            lineTaxAmount = lineRealAmount.subtract(notaxRealAmount);
            taxAmountTotal = taxAmountTotal.add(lineTaxAmount);
        }
        //税额合计
        distrOut.setTaxAmountTotal(taxAmountTotal);
        //不含税金额合计
        distrOut.setNotaxRealAmountTotal(distrOut.getRealAmountTotal().subtract(taxAmountTotal));
    }

    public void calculationPrice(DistrOut distrOut) {
        BigDecimal amountTotal = new BigDecimal(0);//金额合计
        BigDecimal quantityTotal = new BigDecimal(0);//数量合计
        for (DistrOutDetail distrOutDetail : distrOut.getDistrOutDetailList()) {
            //查询药品信息
            GoodsVO goods = goodsMapper.selectGoodsInfoById(distrOutDetail.getGoodsId());
            if (goods == null) {
                throw new BusinessException("没有该商品，请核实商品ID=" + distrOutDetail.getGoodsId());
            }
            if (distrOutDetail.getUnitPrice().compareTo(BigDecimal.ZERO) == -1) {//如果单价小于0
                throw new BusinessException("单价不能小于0");
            }
//            else if (distrOutDetail.getUnitPrice().compareTo(BigDecimal.ZERO) == 0) {//如果单价等于0
//                throw new BusinessException("单价不能等于0");
//            }
            if (distrOutDetail.getQuantity().compareTo(BigDecimal.ZERO) == -1) {//如果数量小于0
                throw new BusinessException("数量不能小于0");
            }
            if (distrOutDetail.getLineDiscount().compareTo(BigDecimal.ZERO) == -1) {//如果折扣小于0
                throw new BusinessException("折扣不能小于0");
            } else if (distrOutDetail.getLineDiscount().compareTo(BigDecimal.ZERO) == 0) {//如果折扣等于0
                throw new BusinessException("折扣不能等于0");
            }
            if (distrOutDetail.getTaxRateId() == null) {
                throw new BusinessException("税率ID不能为空");
            } else {
                GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(distrOutDetail.getTaxRateId());
                if (goodsTaxRate == null) {
                    throw new BusinessException("没有该税率值，请核实税率ID");
                } else {
                    distrOutDetail.setTaxRate(goodsTaxRate.getTaxRate());
                }

            }
            //计算金额合计
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(distrOutDetail.getQuantity(), distrOutDetail.getUnitPrice(), distrOutDetail.getLineDiscount());
            amountTotal = amountTotal.add(amount);
            //药品数量合计
            quantityTotal = quantityTotal.add(distrOutDetail.getQuantity());
        }
        if (distrOut.getWholeDiscount().compareTo(BigDecimal.ZERO) == -1) {//整单折扣不能小于0
            throw new BusinessException("整单折扣不能小于0");
        }
        if (distrOut.getWholeDiscountAmount().compareTo(BigDecimal.ZERO) == -1) {//整单优惠不能小于0
            throw new BusinessException("整单优惠不能小于0");
        }
        //以下是计算不含税金额与税额的（明细表）
        //定义不含税金额，与税额
        BigDecimal notaxRealAmountTotal = new BigDecimal(0);//不含税总额
        BigDecimal taxAmountTotal = new BigDecimal(0);//税额
        for (DistrOutDetail distrOutDetail : distrOut.getDistrOutDetailList()) {
            //根据数量、单价、行折扣获取金额（整单折扣金额）：数量*单价*行折扣 amount 有可能是0
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(distrOutDetail.getQuantity(), distrOutDetail.getUnitPrice(), distrOutDetail.getLineDiscount());
            BigDecimal lineDiscountAmount = new BigDecimal(0);

            if (amount.compareTo(BigDecimal.ZERO) == 1) {//大于0
                lineDiscountAmount = distrOut.getWholeDiscountAmount().multiply(amount.divide(amountTotal, 2, BigDecimal.ROUND_HALF_UP));
            }
            //计算实际金额，根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
            BigDecimal realAmount = CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(distrOutDetail.getQuantity(), distrOutDetail.getUnitPrice(), distrOutDetail.getLineDiscount(), distrOut.getWholeDiscount(), lineDiscountAmount);
            //计算实际单价   ：实际金额/数量
            BigDecimal realPrice = new BigDecimal(0);
            if (realAmount.compareTo(BigDecimal.ZERO) == 1) {//大于0
                realPrice = CalculateComponent.getRealPriceByRealAmountAndQuantity(realAmount, distrOutDetail.getQuantity());
            }
            //获取不含税金额
            BigDecimal val1 = new BigDecimal(1);
            BigDecimal notaxRealAmount = realAmount.divide(val1.add(distrOutDetail.getTaxRate().divide(new BigDecimal(100))), 2, BigDecimal.ROUND_HALF_UP);
            //计算不含税单价
            BigDecimal notaxRealPrice = new BigDecimal(0);
            if (notaxRealAmount.compareTo(BigDecimal.ZERO) == 1) {//如果不含税金额大于0的时候
                notaxRealPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxRealAmount, distrOutDetail.getQuantity());
            }
            //获取税额   实际金额-不含税金额
            BigDecimal taxAmount = realAmount.subtract(notaxRealAmount);
            distrOutDetail.setNotaxRealAmount(notaxRealAmount);//不含税金额
            distrOutDetail.setNotaxRealPrice(notaxRealPrice);//不含税实际单价
            distrOutDetail.setRealAmount(realAmount);//实际金额
            distrOutDetail.setRealPrice(realPrice);//实际单价
            distrOutDetail.setTaxAmount(taxAmount);//税额
            distrOutDetail.setUnclearQuantity(distrOutDetail.getQuantity());
            distrOutDetail.setClearQuantity(new BigDecimal(0));
            //计算订单详情里面的金额
            distrOutDetail.setAmount(amount);
            //行优惠（行舍入）优惠分摊    计算优惠分摊
            distrOutDetail.setWholeDiscount(distrOut.getWholeDiscount());//整单折扣
            distrOutDetail.setLineDiscountAmount(lineDiscountAmount);

            notaxRealAmountTotal = notaxRealAmountTotal.add(notaxRealAmount);//不含税金额总额
            taxAmountTotal = taxAmountTotal.add(taxAmount);
        }
        BigDecimal realAmountTotal = amountTotal.multiply((distrOut.getWholeDiscount().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_UP).subtract(distrOut.getWholeDiscountAmount());
        distrOut.setAmountTotal(amountTotal);//总额（优惠前金额合计）
        distrOut.setRealAmountTotal(realAmountTotal);//实际金额合计
        distrOut.setNotaxRealAmountTotal(notaxRealAmountTotal);
        distrOut.setTaxAmountTotal(taxAmountTotal);
        distrOut.setQuantityTotal(quantityTotal);
        distrOut.setVarietiesQuantity(distrOut.getDistrOutDetailList().size());
        distrOut.setQuantityTotal(quantityTotal);
    }


    /**
     * 配进订单
     *
     * @param userVO
     * @param addInstorageVO
     * @return
     */
    public Map<String, Object> addDistrNotice(UserVO userVO, AddInstorageVO addInstorageVO, Map<String, Object> distrOutMap) throws Exception {
        DistrOut out = (DistrOut) distrOutMap.get("distrOut");
        List<DistrOutDetail> distrOutDetailList = (List<DistrOutDetail>) distrOutMap.get("distrOutDetail");
        int status = DistrInStatus.FINISHED;// 直调配送 自营店为已完成，加盟店为 待开票

        if (ChainType.Division.getCode() == userVO.getChainType()) {
            status = DistrInStatus.WAIT_BILL;
        }

        Map<String, Object> result = new HashMap<>(3);
        // 配进订单
        DistrInNotice distr = (DistrInNotice) EntityUtils.reflectAddSetDefaultValue(DistrInNotice.class, userVO);
        distr.setOrderDate(addInstorageVO.getOrderDate());//配进日期
        distr.setOrderType(OrderRule.DISTR_IN_ORDER.getType());
        //生成单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_IN_ORDER.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        distr.setCode(code);
        //基础单据信息
        distr.setBaseOrderId(out.getId());
        distr.setBaseOrderCode(out.getCode());
        distr.setBaseOrderDate(out.getOutDate());
        distr.setBaseOrderType(out.getOrderType());
        //配货单位
        distr.setDistrUnitId(userVO.getParentId());
        distr.setDistrUnitCode(userVO.getParentCode());
        distr.setDistrUnitName(userVO.getParentName());
        //出库单位
        Integer distrType = addInstorageVO.getDistrType();
        if (DistributionType.DISTRIBUTION_HEAD.getCode() == distrType) {
            //总部配送
            distr.setOutboundUnitId(userVO.getParentId());
            distr.setOutboundUnitCode(userVO.getParentCode());
            distr.setOutboundUnitName(userVO.getParentName());
        } else {
            distr.setOutboundUnitId(addInstorageVO.getOutboundUnitId());
            distr.setOutboundUnitCode(addInstorageVO.getOutboundUnitCode());
            distr.setOutboundUnitName(addInstorageVO.getOutboundUnitName());
        }

        //配进人员
        Long noticeStorageManId = addInstorageVO.getNoticeStorageManId();
        User user = userMapper.selectByPrimaryKey(noticeStorageManId);
        if (user == null) throw new BusinessException("配进人员不存在！");
        distr.setStorageManId(noticeStorageManId);
        distr.setStorageManCode(user.getCode());
        distr.setStorageManName(user.getName());
        //配货类型
        distr.setDistrType(distrType);


        distr.setQuantityTotal(addInstorageVO.getQuantityTotal());
        distr.setVarietiesQuantity(addInstorageVO.getVarietiesQuantity());

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
        //状态
        distr.setStatus(status);

        distrInNoticeMapper.insertSelective(distr);

        List<DistrInNoticeDetail> distrInNoticeDetailList = new ArrayList<>();
        //计算明细
        List<AddInstorageDtlVO> addInstorageDtlVOList = addInstorageVO.getAddInstorageDtlVOList();
        int k = 1;
        for (AddInstorageDtlVO addInstorageDtlVO : addInstorageDtlVOList) {
            DistrInNoticeDetail detail = (DistrInNoticeDetail) EntityUtils.reflectAddSetDefaultValue(DistrInNoticeDetail.class, userVO);

            detail.setOrderId(distr.getId());
            detail.setOrderDate(distr.getOrderDate());
            detail.setOrderType(OrderRule.DISTR_IN_ORDER.getType());
            detail.setOrderCode(distr.getCode());

            Long goodsId = addInstorageDtlVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if (goods == null) throw new BusinessException("商品不存在");
//            if(goods.getSpecialDrug() != -1 && addInstorageVO.getSecondReceiverId() == null){
//                throw new BusinessException("第"+addInstorageDtlVO.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
//            }
            Optional<DistrOutDetail> distrFirst = distrOutDetailList.stream().filter(item -> goodsId.equals(item.getGoodsId())).findFirst();
            if (distrFirst.isPresent()) {

                detail.setBaseOrderCode(out.getCode());
                detail.setBaseOrderId(out.getId());
                detail.setBaseOrderDate(out.getOutDate());
                detail.setBaseOrderType(out.getOrderType());
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

                //计算金额

                detail.setQuantity(addInstorageDtlVO.getQuantity());

                detail.setUnitPrice(addInstorageDtlVO.getUnitPrice());
                detail.setLineDiscount(addInstorageDtlVO.getLineDiscount());

                detail.setAmount(addInstorageDtlVO.getAmount());
                detail.setWholeDiscount(addInstorageDtlVO.getWholeDiscount());
                detail.setLineDiscountAmount(addInstorageDtlVO.getLineDiscountAmount());
                detail.setRealPrice(addInstorageDtlVO.getUnitPrice());
                detail.setRealAmount(addInstorageDtlVO.getRealAmount());
                detail.setTaxAmount(addInstorageDtlVO.getTaxAmount());
                Long taxRateId = addInstorageDtlVO.getTaxRateId();
                GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(taxRateId);
                if (goodsTaxRate == null) throw new BusinessException("税率不存在");
                detail.setTaxRateId(taxRateId);
                detail.setTaxRate(goodsTaxRate.getTaxRate());
                detail.setNotaxRealAmount(addInstorageDtlVO.getNotaxRealAmount());
                detail.setNotaxRealPrice(addInstorageDtlVO.getNotaxRealPrice());
                detail.setUnclearQuantity(BigDecimal.ZERO);
                detail.setClearQuantity(addInstorageDtlVO.getQuantity());
                detail.setLineNum(k++);
                detail.setStatus(status);
                distrInNoticeDetailMapper.insertSelective(detail);
                distrInNoticeDetailList.add(detail);
            }


        }

        result.put("distrInNotice", distr);
        result.put("distrInNoticeDetail", distrInNoticeDetailList);
        return result;
    }


    @Transactional(rollbackFor = Exception.class)
    public DistrInNotice distrOutcheck(DistrOutCheckVo distrOutCheckVo, UserVO userVO) throws Exception {
        if (distrOutCheckVo.getStatus() == DistrAuditType.REFUSE.getCode()) {//被拒绝
//			throw new BusinessException("未选择通过复核,请检查入参sta="+distrOutCheckVo.getStatus());
            //更新配货单状态
            DistrOut distrOut = distrOutMapper.selectByPrimaryKey(distrOutCheckVo.getId());
            if (distrOut == null) {
                throw new BusinessException("无法查询到配货出库单ID：" + distrOutCheckVo.getId());
            }
            if (distrOut.getBaseOrderId() != null) {
                distrSendMapper.updateById(distrOut.getBaseOrderId(), userVO.getEnterpriseId(), DistrSendStatus.AUDIT_REBUT);
                distrSendDetailMapper.updateBySendId(distrOut.getBaseOrderId(), userVO.getEnterpriseId(), DistrSendStatus.AUDIT_REBUT);
            }
            //更新出库单据
            distrOut.setStatus(DistrOutStatus.AUDIT_REBUT);
            distrOutMapper.updateByPrimaryKeySelective(distrOut);
            List<DistrOutDetail> distrOutDetails = distrOutDetailMapper.getDistrOutDetailList(distrOut.getId());
            for (DistrOutDetail distrOutDetail : distrOutDetails) {
                distrOutDetail.setStatus(DistrOutStatus.AUDIT_REBUT);
                distrOutDetailMapper.updateByPrimaryKeySelective(distrOutDetail);
                List<DistrOutShelf> distrOutShelves = distrOutShelfMapper.getDistrOutShelfList(distrOutDetail.getId());
                for (DistrOutShelf distrOutShelf : distrOutShelves) {
                    distrOutShelf.setStatus(DistrOutStatus.AUDIT_REBUT);
                    distrOutShelfMapper.updateByPrimaryKeySelective(distrOutShelf);
                }
            }
        } else {
            DistrOut distrOut = distrOutMapper.selectByPrimaryKey(distrOutCheckVo.getId());
            if (distrOut == null) {
                throw new BusinessException("没有该配送出库单,请检查id=" + distrOutCheckVo.getId());
            }
            if (distrOut.getStatus() != DistrOutStatus.WAIT_AUDIT) {
                throw new BusinessException("该配送出库单无法复核,请检查出库单状态id=" + distrOutCheckVo.getId());
            }

            ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
            Boolean zl = manage.getBusinessControl() == 0 ? true : false;

//            DistrSend distrSend = new DistrSend();
//            if(distrOut.getBaseOrderId() != null) {//调用和新增生成的配货出库单 不执行
//                //设置相关配送单为完成状态

            //出库复核通过后，要货单位是自营店的状态为“已完成“，要货单位是加盟店的状态为“待开票"，配货单和拣货单也要统一改

            Integer status;
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrOut.getRequestUnitId());
            if (enterprise == null) {
                throw new BusinessException("要货单位ID无效");
            }
            if (enterprise.getChainType() != ChainType.Division.getCode()) {
                status = DistrOutStatus.FINISHED;
            } else {
                status = DistrOutStatus.WAIT_BILL;
            }

            DistrSend distrSend = distrSendMapper.selectByPrimaryKey(distrOut.getBaseOrderId());
            if (distrSend == null) {
                throw new BusinessException("该配送出库单无法找到对应配送单,请检查配送单id=" + distrOut.getBaseOrderId());
            }
            if (distrSend.getStatus() != DistrSendStatus.OUTING) {
                throw new BusinessException("该配送出库单对应的配送单状态无法复核,请检查配送单状态id=" + distrOut.getBaseOrderId());
            }


            //更新配货单状态
            distrSend.setStatus(status);
            distrSend.setUpdateTime(new Date());
            distrSend.setModifierCode(userVO.getUserCode());
            distrSend.setModifierId(userVO.getUserId());
            distrSend.setModifierName(userVO.getUserName());
            distrSendMapper.updateByPrimaryKey(distrSend);

//			List<DistrSendDetail> distrSendDetails = distrSendDetailMapper.listDistrSendDetailList(distrSend.getId());
//			if(distrSendDetails.isEmpty()){
//				throw new BusinessException("没有对应的配货单明细数据, 配货单ID：" + distrSend.getId());
//			}

            PickOrder pickOrder = pickOrderMapper.selectByBaseOrderId(distrSend.getId());
            if (pickOrder == null) {
                throw new BusinessException("该配送出库单无法找到对应拣货单,请检查拣货单id=" + distrOut.getBaseOrderId());
            }

//			for(DistrSendDetail distrSendDetail : distrSendDetails){
//				distrSendDetail.setStatus(DistrSendStatus.FINISHED);
//				distrSendDetail.setUpdateTime(new Date());
//				distrSendDetail.setModifierCode(userVO.getUserCode());
//				distrSendDetail.setModifierId(userVO.getUserId());
//				distrSendDetail.setModifierName(userVO.getUserName());
//				distrSendDetailMapper.updateByPrimaryKeySelective(distrSendDetail);
//			}
            //更新拣货单状态
            pickOrder.setStatus(status);
            pickOrder.setUpdateTime(new Date());
            pickOrder.setModifierCode(userVO.getUserCode());
            pickOrder.setModifierId(userVO.getUserId());
            pickOrder.setModifierName(userVO.getUserName());
            pickOrderMapper.updateByPrimaryKeySelective(pickOrder);

            List<PickDetail> pickDetails = pickDetailMapper.selectByPickId(pickOrder.getId());
            if (pickDetails.isEmpty()) {
                throw new BusinessException("没有对应的拣货单明细数据, 拣货单ID：" + pickOrder.getId());
            }
            for (PickDetail pickDetail : pickDetails) {
                pickDetail.setStatus(status);
                pickDetail.setUpdateTime(new Date());
                pickDetail.setModifierCode(userVO.getUserCode());
                pickDetail.setModifierId(userVO.getUserId());
                pickDetail.setModifierName(userVO.getUserName());
                pickDetailMapper.updateByPrimaryKeySelective(pickDetail);

                List<PickShelf> pickShelfList = pickShelfMapper.selectByDtlId(pickDetail.getId(), pickOrder.getId());
                if (pickShelfList.isEmpty()) {
                    throw new BusinessException("没有对应的拣货单货位明细数据, 拣货单ID：" + pickOrder.getId());
                }
                for (PickShelf pickShelf : pickShelfList) {
                    pickShelf.setStatus(status);
                    pickShelf.setUpdateTime(new Date());
                    pickShelf.setModifierCode(userVO.getUserCode());
                    pickShelf.setModifierId(userVO.getUserId());
                    pickShelf.setModifierName(userVO.getUserName());
                    pickShelfMapper.updateByPrimaryKeySelective(pickShelf);
                }
            }


            //出库复核通过后，要货单位是自营店的状态为“已完成“，要货单位是加盟店的状态为“待开票
//            Supplier supplier = supplierMapper.selectByPrimaryKey(distrOut.getRequestUnitId());
//            if(supplier == null){
//                throw new BusinessException("要货单位ID无效");
//            }


            distrOut.setStatus(status);
            distrOut.setUpdateTime(new Date());
            distrOut.setModifierCode(userVO.getUserCode());
            distrOut.setModifierId(userVO.getUserId());
            distrOut.setModifierName(userVO.getUserName());
            distrOut.setCheckerId(userVO.getUserId());
            distrOut.setCheckerCode(userVO.getUserCode());
            distrOut.setCheckerName(userVO.getUserName());
            distrOutMapper.updateByPrimaryKeySelective(distrOut);

            List<DistrOutDetail> distrOutDetails = distrOutDetailMapper.getDistrOutDetailList(distrOut.getId());
            if (distrOutDetails.isEmpty()) {
                throw new BusinessException("没有对应的配货出库单明细数据, 配货出库单ID：" + distrOut.getId());
            }
            for (DistrOutDetail distrOutDetail : distrOutDetails) {

                if (enterprise.getChainType() == ChainType.Division.getCode()) {
                    distrOutDetail.setClearQuantity(BigDecimal.ZERO);
                    distrOutDetail.setUnclearQuantity(distrOutDetail.getQuantity());
                } else {
                    distrOutDetail.setClearQuantity(distrOutDetail.getQuantity());
                    distrOutDetail.setUnclearQuantity(BigDecimal.ZERO);
                }


                distrOutDetail.setStatus(distrOut.getStatus());
                distrOutDetail.setUpdateTime(new Date());
                distrOutDetail.setModifierCode(userVO.getUserCode());
                distrOutDetail.setModifierId(userVO.getUserId());
                distrOutDetail.setModifierName(userVO.getUserName());
                distrOutDetailMapper.updateByPrimaryKeySelective(distrOutDetail);

                // 更新配货单的未清、已清数量
                DistrSendDetail distrSendDetail = new DistrSendDetail();
                distrSendDetail.setId(distrOutDetail.getBaseOrderDtlId());

                distrSendDetail.setUnclearQuantity(BigDecimal.ZERO);
                distrSendDetail.setClearQuantity(distrOutDetail.getQuantity());
                distrSendDetail.setUpdateTime(new Date());
                distrSendDetail.setModifierCode(userVO.getUserCode());
                distrSendDetail.setModifierId(userVO.getUserId());
                distrSendDetail.setModifierName(userVO.getUserName());
                distrSendDetail.setStatus(status);
                distrSendDetailMapper.updateByPrimaryKeySelective(distrSendDetail);

                List<DistrOutShelf> distrOutShelves = distrOutShelfMapper.getDistrOutShelfList(distrOutDetail.getId());
                for (DistrOutShelf distrOutShelf : distrOutShelves) {


                    if (enterprise.getChainType() == ChainType.Division.getCode()) {
                        distrOutShelf.setClearQuantity(BigDecimal.ZERO);
                        distrOutShelf.setUnclearQuantity(distrOutShelf.getQuantity());
                    } else {
                        distrOutShelf.setClearQuantity(distrOutShelf.getQuantity());
                        distrOutShelf.setUnclearQuantity(BigDecimal.ZERO);
                    }

                    distrOutShelf.setStatus(distrOut.getStatus());
                    distrOutShelf.setUpdateTime(new Date());
                    distrOutShelf.setModifierCode(userVO.getUserCode());
                    distrOutShelf.setModifierId(userVO.getUserId());
                    distrOutShelf.setModifierName(userVO.getUserName());
                    distrOutShelfMapper.updateByPrimaryKeySelective(distrOutShelf);
                }
            }


            //生成分店配进订单
            DistrInNotice distrInNotice = new DistrInNotice();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOut, distrInNotice);
            List<DistrInNoticeDetail> detailList = new ArrayList<>();
            List<DistrOutShelf> distrOutShelfList = new ArrayList<>();
            List<DistrOutDetail> distrOutDetailList = distrOutDetailMapper.getDistrOutDetailList(distrOut.getId());
            for (DistrOutDetail distrOutDetail : distrOutDetailList) {
                DistrInNoticeDetail distrInNoticeDetail = new DistrInNoticeDetail();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOutDetail, distrInNoticeDetail);
                distrInNoticeDetail.setId(null);
                //TODO:
                distrInNoticeDetail.setBaseOrderId(distrOut.getId());
                distrInNoticeDetail.setBaseOrderCode(distrOut.getCode());
                distrInNoticeDetail.setBaseOrderType(distrOut.getOrderType());
                distrInNoticeDetail.setBaseOrderDate(distrOut.getOutDate());
                distrInNoticeDetail.setBaseOrderDtlId(distrOutDetail.getId());

                detailList.add(distrInNoticeDetail);

                List<DistrOutShelf> distrOutShelves = distrOutShelfMapper.getDistrOutShelfList(distrOutDetail.getId());
                distrOutShelfList.addAll(distrOutShelves);

                if (distrOut.getBaseOrderId() != null) {//调用和新增生成的配货出库单 不执行
                    if (zl) {
                        /**
                         * 判断是否质量控制开启,如果关闭需要更新配货单的明细行数量
                         */
                        DistrSendDetail distrSendDetail = distrSendDetailMapper.selectByPrimaryKey(distrOutDetail.getBaseOrderDtlId());
                        DistrSendDetail newDistrSendDetail = new DistrSendDetail();
                        newDistrSendDetail.setId(distrSendDetail.getId());
                        newDistrSendDetail.setQuantity(distrOutDetail.getQuantity());

                        distrSendDetailMapper.updateByPrimaryKeySelective(newDistrSendDetail);
                    }
                }
            }
            if (distrOut.getDistrType() != DistributionType.DIRECT_DISTRIBUTION.getCode()) {
                distrInNotice.setDetailList(detailList);
                distrInNotice.setId(null);
                distrInNotice.setDistrUnitCode(userVO.getEnterpriseCode());
                distrInNotice.setDistrUnitId(userVO.getEnterpriseId());
                distrInNotice.setDistrUnitName(userVO.getEnterpriseName());
                distrInNotice.setOrderDate(new Date());
                distrInNotice.setBaseOrderCode(distrOut.getCode());
                distrInNotice.setBaseOrderDate(distrOut.getOutDate());
                distrInNotice.setBaseOrderId(distrOut.getId());
                distrInNotice.setBaseOrderType(distrOut.getOrderType());
                distrInNotice.setEnterpriseId(distrOut.getRequestUnitId());
                distrInNotice.setStatus(PubStatus.distrInStatus.WAIT_RECEIVE);

                if (distrOut.getDistrType().intValue() == DistributionType.DISTRIBUTION_HEAD.getCode()) {
                    distrInNotice.setOutboundUnitId(userVO.getEnterpriseId());
                    distrInNotice.setOutboundUnitCode(userVO.getEnterpriseCode());
                    distrInNotice.setOutboundUnitName(userVO.getEnterpriseName());
                } else if (distrOut.getDistrType().intValue() == DistributionType.SWAP_BETWEEN_STORES.getCode()) {
                    List<DistrSendDetail> distrSendDetail = distrSendDetailMapper.listDistrSendDetailList(distrOut.getBaseOrderId());
                    if (!distrSendDetail.isEmpty()) {
                        DistrReturnInStorageVO distrReturnInStorageVO = distrReturnInMapper.selectById(distrSendDetail.get(0).getBaseOrderId());
                        //查询调入单位信息
                        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(distrReturnInStorageVO.getDistrOrderId());
                        if (distrReturnNotice == null) {
                            throw new BusinessException("无效的配后退回通知单单据ID：" + distrReturnInStorageVO.getDistrOrderId());
                        }
                        DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(distrReturnNotice.getBaseOrderId());
                        if (distrInReturnOut == null) {
                            throw new BusinessException("无效的配进退出出库单单据ID：" + distrReturnNotice.getBaseOrderId());
                        }
                        distrInNotice.setOutboundUnitId(distrInReturnOut.getEnterpriseId());
                        Enterprise enterpriseNotice = enterpriseMapper.selectByPrimaryKey(distrInReturnOut.getEnterpriseId());
                        if (enterpriseNotice == null) {
                            throw new BusinessException("无效的企业ID：" + distrInReturnOut.getEnterpriseId());
                        }
                        distrInNotice.setOutboundUnitCode(enterpriseNotice.getCode());
                        distrInNotice.setOutboundUnitName(enterpriseNotice.getName());
                    }
                }
                //ManageConfig manageConfig=manageConfigComponent.getMangeConfigByEPId(distrOut.getRequestUnitId());
                //                ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(distrOut.getRequestUnitId());
                //                distrInNotice.setStorageManCode(manageConfig.getRequesterCode() == null ? "" : manageConfig.getRequesterCode());
                //                distrInNotice.setStorageManId(manageConfig.getRequesterId() == null ? 0 : manageConfig.getRequesterId());
                //                distrInNotice.setStorageManName(manageConfig.getRequesterName() == null ? "" : manageConfig.getRequesterName());
                //查询配货单中调用要货计划的要货人员信息
                if (distrOut.getBaseOrderId() != null) {
                    Long dtlBaseOrderId = distrSendDetailMapper.selectByBaseOrderId(distrOut.getBaseOrderId());
                    if (dtlBaseOrderId != null) {
                        DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(dtlBaseOrderId);
                        if (distrReqPlan != null) {
                            distrInNotice.setStorageManCode(distrReqPlan.getPlannerCode() == null ? "" : distrReqPlan.getPlannerCode());
                            distrInNotice.setStorageManId(distrReqPlan.getPlannerId() == null ? 0 : distrReqPlan.getPlannerId());
                            distrInNotice.setStorageManName(distrReqPlan.getPlannerName() == null ? "" : distrReqPlan.getPlannerName());
                        } else {
                            ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(distrOut.getRequestUnitId());
                            distrInNotice.setStorageManCode(manageConfig.getRequesterCode() == null ? "" : manageConfig.getRequesterCode());
                            distrInNotice.setStorageManId(manageConfig.getRequesterId() == null ? 0 : manageConfig.getRequesterId());
                            distrInNotice.setStorageManName(manageConfig.getRequesterName() == null ? "" : manageConfig.getRequesterName());
                        }
                    } else {
                        ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(distrOut.getRequestUnitId());
                        distrInNotice.setStorageManCode(manageConfig.getRequesterCode() == null ? "" : manageConfig.getRequesterCode());
                        distrInNotice.setStorageManId(manageConfig.getRequesterId() == null ? 0 : manageConfig.getRequesterId());
                        distrInNotice.setStorageManName(manageConfig.getRequesterName() == null ? "" : manageConfig.getRequesterName());
                    }
                } else {
                    ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(distrOut.getRequestUnitId());
                    distrInNotice.setStorageManCode(manageConfig.getRequesterCode() == null ? "" : manageConfig.getRequesterCode());
                    distrInNotice.setStorageManId(manageConfig.getRequesterId() == null ? 0 : manageConfig.getRequesterId());
                    distrInNotice.setStorageManName(manageConfig.getRequesterName() == null ? "" : manageConfig.getRequesterName());
                }

//                UserVO out = new UserVO();
//                out.setEnterpriseId(distrInNotice.getEnterpriseId());
//                Enterprise distrInNoticeEnterprise = enterpriseMapper.selectByPrimaryKey(distrInNotice.getEnterpriseId());
//                out.setEnterpriseCode(distrInNoticeEnterprise.getCode());
//                out.setEnterpriseName(distrInNoticeEnterprise.getName());
//                Enterprise parentEnterprise = enterpriseMapper.selectByPrimaryKey(distrInNoticeEnterprise.getParentId());
//                out.setParentId(parentEnterprise.getId());
//                out.setParentCode(parentEnterprise.getCode());
//                out.setParentName(parentEnterprise.getName());
//                out.setUserId(userVO.getUserId());
//                out.setUserName(userVO.getUserName());
//                out.setUserCode(userVO.getUserCode());
                distrInNotice = distrComponent.saveInNotice(userVO, distrInNotice);
            }


//            if(distrOut.getBaseOrderId() == null || distrOut.getBaseOrderCode() == null || distrOut.getBaseOrderType() == null) {//直接新增配货出库和调用单据的，生成配货单    不存在上级单据ID，CODE, 类型的单据  判断为直接新增和调用单据
            // 释放配送单锁定的库存
            LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
            lockQtyArgVO.setEnterpriseId(distrOut.getEnterpriseId());
            lockQtyArgVO.setParentId(distrOut.getParentId());
            lockQtyArgVO.setBaseOrderId(distrOut.getBaseOrderId());
            lockQtyArgVO.setBaseOrderType(distrOut.getBaseOrderType());
            lockQtyArgVO.setUser(userVO);
            releaseStockAndCost(lockQtyArgVO);
//            }else{
//                LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
//                lockQtyArgVO.setEnterpriseId(distrOut.getEnterpriseId());
//                lockQtyArgVO.setParentId(distrOut.getParentId());
//                lockQtyArgVO.setBaseOrderId(distrSend.getId());
//                lockQtyArgVO.setBaseOrderType(distrSend.getOrderType());
//                lockQtyArgVO.setUser(userVO);
//                commonComponent.releaseStockAndCost(lockQtyArgVO);
//            }


            //出库操作生成出入库业务表数据
            OrderModelBuilder orderModelBuilder = new OrderModelBuilder(userVO);
            OrderModel orderModel = orderModelBuilder.buildOrderModel(OrderRule.DISTR_OUT, distrOut, distrOutShelfList);
            inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);

            //调用财务公用接口
            financeComponent.distrOutToBalanceAndVoucher(userVO, distrOut);

            return distrInNotice;
        }

        return new DistrInNotice();
    }

    public boolean checkAccountingPeriodRange(UserVO userVO, String startDateStr, String endDateStr) {

        Long enterpriseId = userVO.getEnterpriseId();
        if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()) {
            enterpriseId = userVO.getParentId();
        }

        LocalDate startDate = DateUtils.string2Date(startDateStr, DateUtils.FMT_DATE);
        LocalDate endDate = DateUtils.string2Date(endDateStr, DateUtils.FMT_DATE);

        int startYear = startDate.getYear();
        int endYear = endDate.getYear();
        int startMonth = startDate.getMonthValue();
        int endMonth = endDate.getMonthValue();

        AccountingPeriod startAccountingPeriod = accountingPeriodMapper.selectByEnterpriseIdAndYear(enterpriseId, startYear, null);
        if (null == startAccountingPeriod) {
            return false;
        }

        AccountingPeriod endAccountingPeriod = accountingPeriodMapper.selectByEnterpriseIdAndYear(enterpriseId, endYear, null);

        if (null == endAccountingPeriod) {
            return false;
        }

        List<AccountingPeriodDetail> startAccountingPeriodDetails = accountingPeriodDetailMapper.selectByPeriodIdAndMonth(startAccountingPeriod.getId(), startMonth);
        if (org.springframework.util.CollectionUtils.isEmpty(startAccountingPeriodDetails)) {
            return false;
        }

        List<AccountingPeriodDetail> endAccountingPeriodDetails = accountingPeriodDetailMapper.selectByPeriodIdAndMonth(endAccountingPeriod.getId(), endMonth);
        if (org.springframework.util.CollectionUtils.isEmpty(endAccountingPeriodDetails)) {
            return false;
        }

        return true;
    }



    /**
     * 查询会计期间的时间范围
     *
     * @param userVO
     * @param isOnly 是否是单独会计期间 0 : 不是 1 : 是
     * @return
     */
    public AccountingPeriodValidity getAccountingPeriod(UserVO userVO, Integer isOnly,Integer status,Integer onlyStatus) throws ParseException {

        Long enterpriseId = userVO.getEnterpriseId();
        if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()) {
            enterpriseId = userVO.getParentId();
        }

        if (null != isOnly && 1 == isOnly) {
            Date currentDate = DateUtils.getCurrentDate(new Date());
            AccountingPeriodValidity accountingPeriodValidity = checkInAccountingPeriod(userVO, currentDate,onlyStatus);
            if (null == accountingPeriodValidity) {

                Date date = DateUtils.addMonth(currentDate, 1);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                Date time = calendar.getTime();
                accountingPeriodValidity = checkInAccountingPeriod(userVO, time,onlyStatus);
            }
            return accountingPeriodValidity;
        } else {
            List<AccountingPeriod> accountingPeriods = accountingPeriodMapper.selectByEnterpriseId(enterpriseId,status);

            if (org.springframework.util.CollectionUtils.isEmpty(accountingPeriods)) {

                return null;
            }

            List<Long> ids = AccountingPeriod.getIds(accountingPeriods);

            if (org.springframework.util.CollectionUtils.isEmpty(ids)) {

                return null;
            }
            List<AccountingPeriodDetail> accountingPeriodDetails = accountingPeriodDetailMapper.selectByPeriodIds(ids, status);

            if (org.springframework.util.CollectionUtils.isEmpty(accountingPeriodDetails)) {

                return null;

            }

            AccountingPeriodDetail topDatePeriodDetail = accountingPeriodDetails.get(0);

            AccountingPeriodDetail lowDatePeriodDetail = accountingPeriodDetails.get(accountingPeriodDetails.size() - 1);

            AccountingPeriodValidity accountingPeriodValidity = new AccountingPeriodValidity();

            accountingPeriodValidity.setEndTime(topDatePeriodDetail.getEndDate().getTime());

            accountingPeriodValidity.setBeginTime(lowDatePeriodDetail.getStartDate().getTime());

            return accountingPeriodValidity;
        }
    }

    public AccountingPeriodValidity checkInAccountingPeriod(UserVO userVO, Date date,Integer status) {

        Long enterpriseId = userVO.getEnterpriseId();
        if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()) {
            enterpriseId = userVO.getParentId();
        }

        LocalDate localDate = DateUtils.dateToLocalDate(date);

        /**
         * 查找指定年份并且已经激活的会计期间
         */
        AccountingPeriod accountingPeriod = accountingPeriodMapper.selectByEnterpriseIdAndYear(enterpriseId, localDate.getYear(), status);
        if (null == accountingPeriod) {
            /**
             * 找不到指定年份激活的会计期间
             */
            return null;
        }


        List<AccountingPeriodDetail> accountingPeriodDetails = accountingPeriodDetailMapper.selectByPeriodIdAndMonth(accountingPeriod.getId(), localDate.getMonthValue());

        if (org.springframework.util.CollectionUtils.isEmpty(accountingPeriodDetails)) {
            return null;
        }

        for (AccountingPeriodDetail accountingPeriodDetail : accountingPeriodDetails) {
            if (accountingPeriodDetail.getStatus().equals(0)) {
                Date startDate = accountingPeriodDetail.getStartDate();

                Date endDate = accountingPeriodDetail.getEndDate();

                if (date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
                    AccountingPeriodValidity accountingPeriodValidity = new AccountingPeriodValidity();
                    accountingPeriodValidity.setBeginTime(startDate.getTime());
                    accountingPeriodValidity.setEndTime(endDate.getTime());
                    return accountingPeriodValidity;
                }

            }


        }

        return null;

    }

    public List<QueryBean> getPrepayInvoiceGoodssSupplier(UserVO userVO, String key) {
        Map<String, Object> param = new HashMap<>();
        param.put("status", EnableStatus.ENABLE.getStatus());
        param.put("enterpriseId", UserEnterpriseUtils.getHeadquartersEnterpriseId(userVO));
        param.put("ownerId", userVO.getEnterpriseId());
        param.put("approveStatus", 1);
        param.put("param", key);
        List<SupplierVO> supplierVOS = supplierMapper.selectSupplier(param);

        if (org.springframework.util.CollectionUtils.isEmpty(supplierVOS)) {
            return Collections.emptyList();
        }

        List<QueryBean> collect = supplierVOS.stream().map(supplierVO -> {
            QueryBean queryBean = QueryBean.getQueryBean(supplierVO);
            return queryBean;
        }).collect(Collectors.toList());

        Integer chainType = userVO.getChainType();
        if (ChainType.Division.getCode() == chainType) {
            /**
             * 加盟店
             */
            Long parentId = userVO.getParentId();
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(parentId);
            QueryBean queryBean = QueryBean.getQueryBean(enterprise);
            collect.add(queryBean);
        }

        List<QueryBean> queryBeans = setInvoiceInfo(userVO, collect);

        return queryBeans;
    }


    public List<QueryBean> getOpeningInventorySupplier(UserVO userVO, String key) {

        Integer chainType = userVO.getChainType();


        Map<String, Object> param = new HashMap<>();
        param.put("status", EnableStatus.ENABLE.getStatus());
        param.put("enterpriseId", UserEnterpriseUtils.getHeadquartersEnterpriseId(userVO));

        /**
         * 自营店查询总部的,加盟店和总部查询自己的供货单位
         */
        if (ChainType.Selfoperatedshop.getCode() == chainType) {
            param.put("ownerId", userVO.getParentId());
        }else {
            param.put("ownerId", userVO.getEnterpriseId());
        }
        param.put("approveStatus", 1);
        param.put("param", key);
        List<SupplierVO> supplierVOS = supplierMapper.selectSupplier(param);

        if (org.springframework.util.CollectionUtils.isEmpty(supplierVOS)) {
            return Collections.emptyList();
        }

        List<QueryBean> collect = supplierVOS.stream().map(supplierVO -> {
            QueryBean queryBean = QueryBean.getQueryBean(supplierVO);
            return queryBean;
        }).collect(Collectors.toList());

        if (ChainType.Division.getCode() == chainType) {
            /**
             * 加盟店 要返回总部的企业
             */
            Long parentId = userVO.getParentId();
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(parentId);
            QueryBean queryBean = QueryBean.getQueryBean(enterprise);
            collect.add(queryBean);
        }

        List<QueryBean> queryBeans = setInvoiceInfo(userVO, collect);

        return queryBeans;
    }

    public List<PrepayInvoiceGoodsVO> getPrepayInvoiceGoodses(UserVO userVO, Integer type, Long supplierId, String param, Page page) {

        Long headquartersEnterpriseId = UserEnterpriseUtils.getHeadquartersEnterpriseId(userVO);

        if (null == supplierId) {
            throw new SupplierBizException(SupplierBizException.VALUE_CHECK, "需要选择供应商或者企业");
        }
        if (type == 2) {
            /**
             * 查询条件为供货单位id
             */
            com.github.pagehelper.Page hPage = null;
            if (null != page)
                hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

            List<PrepayInvoiceGoodsVO> prepayInvoiceGoodsVOS = new ArrayList<>();

            prepayInvoiceGoodsVOS = goodsMapper.selectPrepayInvoiceGoodsBySupplier(headquartersEnterpriseId, supplierId, userVO.getEnterpriseId(), param);

            List<Long> goodsIds = PrepayInvoiceGoodsVO.getGoodsIds(prepayInvoiceGoodsVOS);

            if (!org.springframework.util.CollectionUtils.isEmpty(goodsIds)) {
                List<SupplierVarieties> supplierVarieties = supplierVarietiesMapper.selectByGoodsIds(goodsIds);
                if (!org.springframework.util.CollectionUtils.isEmpty(supplierVarieties)) {
                    for (SupplierVarieties su : supplierVarieties) {

                        for (PrepayInvoiceGoodsVO prepayInvoiceGoodsVO : prepayInvoiceGoodsVOS) {

                            if (prepayInvoiceGoodsVO.getGoodsId().equals(su.getGoodsId())) {
                                prepayInvoiceGoodsVO.setUnitPrice(su.getLastPurPrice());
                                prepayInvoiceGoodsVO.setTaxRateId(su.getLastPurTaxRateId());
                                prepayInvoiceGoodsVO.setTaxRate(su.getLastPurTaxRate());
                            }
                        }

                    }
                }
            }

            /**
             * 设置不含税单价,不含税金额
             */
            PrepayInvoiceGoodsVO.setNotTax(prepayInvoiceGoodsVOS);
            if (null != page) {
                page.setResult(prepayInvoiceGoodsVOS);
                page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
                page.setTotalPage(hPage.getPages());
            }
            return prepayInvoiceGoodsVOS;
        } else {
            /**
             * 查询条件为总部企业id
             */
            com.github.pagehelper.Page hPage = null;
            if (null != page)
                hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

            List<PrepayInvoiceGoodsVO> prepayInvoiceGoodsVOS = goodsMapper.selectPrepayInvoiceGoodsByHeadEnterprise(headquartersEnterpriseId, supplierId, param);

            List<Integer> list = new ArrayList<>();
            list.add(DistrInStatus.WAIT_BILL);
            list.add(DistrInStatus.PART_BILL);

            for (PrepayInvoiceGoodsVO prepayInvoiceGoodsVO : prepayInvoiceGoodsVOS) {
                PrepayInvoiceGoodsVO distrInGoods = distrInDetailMapper.selectByPrepayInvoiceGoodses(
                        DistrType.DISTRIBUTION_HEAD.getCode(),
                        list,
                        supplierId,
                        prepayInvoiceGoodsVO.getGoodsId(),
                        param);
                if (null != distrInGoods) {


                    /**
                     * 最新一次入库单价
                     */
                    prepayInvoiceGoodsVO.setUnitPrice(prepayInvoiceGoodsVO.getUnitPrice());

                    /**
                     *最新采购税率ID
                     */
                    prepayInvoiceGoodsVO.setTaxRateId(prepayInvoiceGoodsVO.getTaxRateId());

                    /**
                     *最新采购税率
                     */
                    prepayInvoiceGoodsVO.setTaxRate(prepayInvoiceGoodsVO.getTaxRate());

                    /**
                     * 不含税实际单价
                     */
                    prepayInvoiceGoodsVO.setNotaxRealPrice(prepayInvoiceGoodsVO.getNotaxRealPrice());

                    /**
                     * 不含税实际金额
                     */
                    prepayInvoiceGoodsVO.setNotaxRealAmount(prepayInvoiceGoodsVO.getNotaxRealAmount());

                    /**
                     * 金额
                     */
                    prepayInvoiceGoodsVO.setAmount(prepayInvoiceGoodsVO.getAmount());

                    /**
                     * 税额
                     */
                    prepayInvoiceGoodsVO.setTaxAmount(prepayInvoiceGoodsVO.getTaxAmount());

                }

            }
            if (null != page) {
                page.setResult(prepayInvoiceGoodsVOS);
                page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
                page.setTotalPage(hPage.getPages());
            }
            return prepayInvoiceGoodsVOS;
        }

    }


    /**
     * 根据企业id 和 用户id 判断用户是否存在
     *
     * @param enterpriseId
     * @param userId
     * @param prefix       用户的业务信息，例如：过账，开票； 非必填
     * @return
     */
    public User getExistUser(Long enterpriseId, Long userId, String prefix) {
        User user = userMapper.selectByIdAndEnterpriseIdAndStatusAndApproveStatus(enterpriseId, userId, EnableStatus.ENABLE.getStatus(), ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue());
        if (user == null) throw new BusinessException(prefix + "用户不存在");
        return user;
    }

    /**
     * 根据企业id 和 用户id 判断供货单位是否存在
     *
     * @param enterpriseId
     * @param supplierId
     * @return
     */
    public Supplier getExistSupplier(Long enterpriseId, Long supplierId) {
        Supplier supplier = supplierMapper.getSupplierByIdAndOwnerId(supplierId, enterpriseId);
        if (supplier == null) throw new BusinessException("供货单位不存在");
        return supplier;
    }

    /**
     * 根据企业id 和 用户id 判断企业是否存在
     *
     * @param enterpriseId
     * @return
     */
    public Enterprise getExistEnterprise(Long enterpriseId) {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        if (enterprise == null) throw new BusinessException("企业单位不存在");
        return enterprise;
    }

    /**
     * 根据企业id 和 用户id 判断商品是否存在，并组装商品信息
     *
     * @param enterpriseId 暂时不根据企业查询商品
     * @param goodsId
     * @return
     */
    public <T> void getExistGoods(Long enterpriseId, Long goodsId, T clazz) throws Exception {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if (goods == null) throw new BusinessException("商品不存在");
        EntityUtils.setGoodsProperty(clazz, goods);
    }

    /**
     * 根据企业id 和 用户id 判断税率是否存在
     *
     * @param enterpriseId
     * @return
     */
    public GoodsTaxRate getExistGoodsTax(Long enterpriseId, Long taxId) throws Exception {
        GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.getTaxByEnterpriseIdAndId(enterpriseId, taxId);
        if (goodsTaxRate == null) throw new BusinessException("税率不存在");
        return goodsTaxRate;
    }

    /**
     * 根据行号是否存在
     *
     * @param lineNum
     * @return
     */
    public Integer getExistLineNum(Integer lineNum) throws Exception {
        if (lineNum == null) throw new BusinessException("行号不存在");
        return lineNum;
    }


    /**
     * 对账采购入库货位行
     */
    @Transactional(rollbackFor = Exception.class)
    public PurchaseInStorageDetail clearPurchaseInStorageDetail(UserVO userVO, PurchaseInStorageShelf storageShelf) {


        PurchaseInStorageDetail detail = purchaseInStorageDetailMapper.selectByPrimaryKey(storageShelf.getInStorageDtlId());

        List<PurchaseInStorageShelf> purchaseInStorageShelves = purchaseInStorageShelfMapper.selectByEnterpriseIdAndId(userVO.getEnterpriseId(), detail.getId());

        BigDecimal clearQuantityTotal = purchaseInStorageShelves.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getClearQuantity()).map(PurchaseInStorageShelf::getClearQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal unClearQuantityTotal = purchaseInStorageShelves.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getUnclearQuantity()).map(PurchaseInStorageShelf::getUnclearQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

        detail.setClearQuantity(clearQuantityTotal);
        detail.setUnclearQuantity(unClearQuantityTotal);

        if (clearQuantityTotal.compareTo(BigDecimal.ZERO) == 0) {
            /**
             * 明细行已清数量总和等于头单的数量总和,表示已完成
             */
            PurchaseInStorageDetail newPurchaseInStorageDetail = new PurchaseInStorageDetail();
            newPurchaseInStorageDetail.setId(detail.getId());
            newPurchaseInStorageDetail.setClearQuantity(clearQuantityTotal);
            newPurchaseInStorageDetail.setUnclearQuantity(unClearQuantityTotal);
            newPurchaseInStorageDetail.setStatus(PurchaseStatus.WAIT_BILL.getStatus());
            purchaseInStorageDetailMapper.updateByPrimaryKeySelective(newPurchaseInStorageDetail);

            detail.setStatus(newPurchaseInStorageDetail.getStatus());

        } else if (unClearQuantityTotal.compareTo(BigDecimal.ZERO) == 0) {
            PurchaseInStorageDetail newPurchaseInStorageDetail = new PurchaseInStorageDetail();
            newPurchaseInStorageDetail.setId(detail.getId());
            newPurchaseInStorageDetail.setClearQuantity(clearQuantityTotal);
            newPurchaseInStorageDetail.setUnclearQuantity(unClearQuantityTotal);
            newPurchaseInStorageDetail.setStatus(PurchaseStatus.FINISHED.getStatus());
            purchaseInStorageDetailMapper.updateByPrimaryKeySelective(newPurchaseInStorageDetail);

            detail.setStatus(newPurchaseInStorageDetail.getStatus());
        } else {
            PurchaseInStorageDetail newPurchaseInStorageDetail = new PurchaseInStorageDetail();
            newPurchaseInStorageDetail.setId(detail.getId());
            newPurchaseInStorageDetail.setClearQuantity(clearQuantityTotal);
            newPurchaseInStorageDetail.setUnclearQuantity(unClearQuantityTotal);
            newPurchaseInStorageDetail.setStatus(PurchaseStatus.PART_BILL.getStatus());
            purchaseInStorageDetailMapper.updateByPrimaryKeySelective(newPurchaseInStorageDetail);

            detail.setStatus(newPurchaseInStorageDetail.getStatus());
        }

        return detail;

    }


    /**
     * 对账采购入库头单
     *
     * @param detail
     */
    @Transactional(rollbackFor = Exception.class)
    public PurchaseInStorage clearPurchaseInStorage(PurchaseInStorageDetail detail) {

        PurchaseInStorage purchaseInStorage = purchaseInStorageMapper.selectByPrimaryKey(detail.getInStorageId());

        List<PurchaseInStorageDetail> purchaseInStorageDetails = purchaseInStorageDetailMapper.selectByInStorageId(purchaseInStorage.getId());

        BigDecimal clearQuantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getClearQuantity()).map(PurchaseInStorageDetail::getClearQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal unClearQuantityTotal = purchaseInStorageDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getUnclearQuantity()).map(PurchaseInStorageDetail::getUnclearQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (clearQuantityTotal.compareTo(BigDecimal.ZERO) == 0) {
            /**
             * 已清为0 表示 待开票
             */
            PurchaseInStorage newPurchaseInStorage = new PurchaseInStorage();
            newPurchaseInStorage.setId(purchaseInStorage.getId());
            newPurchaseInStorage.setStatus(PurchaseStatus.WAIT_BILL.getStatus());

            purchaseInStorageMapper.updateByPrimaryKeySelective(newPurchaseInStorage);

            purchaseInStorage.setStatus(newPurchaseInStorage.getStatus());

        } else if (unClearQuantityTotal.compareTo(BigDecimal.ZERO) == 0) {

            /**
             * 未清为0 表示已完成
             */
            PurchaseInStorage newPurchaseInStorage = new PurchaseInStorage();
            newPurchaseInStorage.setId(purchaseInStorage.getId());
            newPurchaseInStorage.setStatus(PurchaseStatus.FINISHED.getStatus());
            ;
            purchaseInStorageMapper.updateByPrimaryKeySelective(newPurchaseInStorage);

            purchaseInStorage.setStatus(newPurchaseInStorage.getStatus());

        } else {
            PurchaseInStorage newPurchaseInStorage = new PurchaseInStorage();
            newPurchaseInStorage.setId(purchaseInStorage.getId());
            newPurchaseInStorage.setStatus(PurchaseStatus.PART_BILL.getStatus());
            purchaseInStorageMapper.updateByPrimaryKeySelective(newPurchaseInStorage);

            purchaseInStorage.setStatus(newPurchaseInStorage.getStatus());
        }

        return purchaseInStorage;

    }


    /**
     * 对账配进入库货位行
     *
     * @param distrInShelf
     */
    @Transactional(rollbackFor = Exception.class)
    public DistrInDetail clearDistrInDetailDetail(DistrInShelf distrInShelf) {


        List<DistrInShelf> distrInShelves = distrInShelfMapper.selectByDtlId(distrInShelf.getDtlId());

        DistrInDetail distrInDetail = distrInDetailMapper.selectByPrimaryKey(distrInShelf.getDtlId());

        BigDecimal clearQuantityTotal = distrInShelves.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getClearQuantity()).map(DistrInShelf::getClearQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal unClearQuantityTotal = distrInShelves.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getUnclearQuantity()).map(DistrInShelf::getUnclearQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

        distrInDetail.setClearQuantity(clearQuantityTotal);
        distrInDetail.setUnclearQuantity(unClearQuantityTotal);

        if (clearQuantityTotal.compareTo(BigDecimal.ZERO) == 0) {
            /**
             * 明细行已清数量等于0时 表示是待开票
             */
            DistrInDetail newDistrInDetail = new DistrInDetail();
            newDistrInDetail.setId(distrInDetail.getId());
            newDistrInDetail.setClearQuantity(clearQuantityTotal);
            newDistrInDetail.setUnclearQuantity(unClearQuantityTotal);
            newDistrInDetail.setStatus(DistrInStatus.WAIT_BILL);

            distrInDetailMapper.updateByPrimaryKeySelective(newDistrInDetail);

            distrInDetail.setStatus(newDistrInDetail.getStatus());

        } else if (unClearQuantityTotal.compareTo(BigDecimal.ZERO) == 0) {
            /**
             * 明细行未清数量等于0时 表示是已完成
             */
            DistrInDetail newDistrInDetail = new DistrInDetail();
            newDistrInDetail.setId(distrInDetail.getId());
            newDistrInDetail.setClearQuantity(clearQuantityTotal);
            newDistrInDetail.setUnclearQuantity(unClearQuantityTotal);
            newDistrInDetail.setStatus(DistrInStatus.FINISHED);

            distrInDetailMapper.updateByPrimaryKeySelective(newDistrInDetail);

            distrInDetail.setStatus(newDistrInDetail.getStatus());
        } else {
            /**
             * 明细行未清数量等于0时 表示是待开票
             */
            DistrInDetail newDistrInDetail = new DistrInDetail();
            newDistrInDetail.setId(distrInDetail.getId());
            newDistrInDetail.setClearQuantity(clearQuantityTotal);
            newDistrInDetail.setUnclearQuantity(unClearQuantityTotal);
            newDistrInDetail.setStatus(DistrInStatus.PART_BILL);

            distrInDetailMapper.updateByPrimaryKeySelective(newDistrInDetail);

            distrInDetail.setStatus(newDistrInDetail.getStatus());
        }

        return distrInDetail;
    }


    /**
     * 对账配进入库头单
     */
    @Transactional(rollbackFor = Exception.class)
    public DistrIn clearDistrIn(DistrInDetail distrInDetail) {

        DistrIn distrIn = distrInMapper.selectByPrimaryKey(distrInDetail.getInId());

        List<DistrInDetail> distrInDetails = distrInDetailMapper.selectByInId(distrIn.getId());

        /*BigDecimal quantityTotal = distrIn.getQuantityTotal();*/

        BigDecimal clearQuantityTotal = distrInDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getClearQuantity()).map(DistrInDetail::getClearQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal unClearQuantityTotal = distrInDetails.stream().filter(Objects::nonNull)
                .filter(c -> null != c.getUnclearQuantity()).map(DistrInDetail::getUnclearQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (clearQuantityTotal.compareTo(BigDecimal.ZERO) == 0) {
            /**
             * 明细行已经清数量等于0时 表示待开票
             */
            DistrIn newDistrIn = new DistrIn();
            newDistrIn.setId(distrIn.getId());
            newDistrIn.setStatus(DistrInStatus.WAIT_BILL);

            distrInMapper.updateByPrimaryKeySelective(newDistrIn);

            distrIn.setStatus(newDistrIn.getStatus());

        } else if (unClearQuantityTotal.compareTo(BigDecimal.ZERO) == 0) {
            /**
             * 明细行未清数量等于0时 表示是已完成
             */
            DistrIn newDistrIn = new DistrIn();
            newDistrIn.setId(distrIn.getId());
            newDistrIn.setStatus(DistrInStatus.FINISHED);

            distrInMapper.updateByPrimaryKeySelective(newDistrIn);

            distrIn.setStatus(newDistrIn.getStatus());
        } else {
            DistrIn newDistrIn = new DistrIn();
            newDistrIn.setId(distrIn.getId());
            newDistrIn.setStatus(DistrInStatus.PART_BILL);

            distrInMapper.updateByPrimaryKeySelective(newDistrIn);

            distrIn.setStatus(newDistrIn.getStatus());
        }

        return distrIn;

    }

    public PerpayInvoiceCalcAmountParamVO calcAmount(BigDecimal clearQuantity, BigDecimal unitPrice, BigDecimal taxRate) {
        /**
         * 已清金额
         */
        BigDecimal clearQuantityAmount = clearQuantity.multiply(unitPrice);

        /**
         * 根据实际金额和税率获取不含税金额：金额/(1+税率)  不含税已清金额
         */
        BigDecimal clearQuantityNotTaxAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(clearQuantityAmount, taxRate);


        /**
         *
         * 根据实际金额和不含税金额获取税额：金额-不含税金额  已清税额
         */
        BigDecimal clearQuantityTaxAmount = CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(clearQuantityAmount, clearQuantityNotTaxAmount);

        PerpayInvoiceCalcAmountParamVO perpayInvoiceCalcAmountParamVO = new PerpayInvoiceCalcAmountParamVO();
        perpayInvoiceCalcAmountParamVO.setClearQuantityAmount(clearQuantityAmount);
        perpayInvoiceCalcAmountParamVO.setClearQuantityNotTaxAmount(clearQuantityNotTaxAmount);
        perpayInvoiceCalcAmountParamVO.setClearQuantityTaxAmount(clearQuantityTaxAmount);


        return perpayInvoiceCalcAmountParamVO;
    }

    public List<QueryBean> setInvoiceInfo(UserVO userVO, List<QueryBean> queryBeans) {

        List<QueryBean> supplierQueryBean = new ArrayList<>();

        List<QueryBean> enterpirseQueryBean = new ArrayList<>();

        List<Long> supplierIds = new ArrayList<>(queryBeans.size());
        List<Long> enterpirseIds = new ArrayList<>(queryBeans.size());

        for (QueryBean queryBean : queryBeans) {

            if (2 == queryBean.getType()) {
                supplierQueryBean.add(queryBean);
                supplierIds.add(queryBean.getId());
            } else {
                enterpirseQueryBean.add(queryBean);
                enterpirseIds.add(queryBean.getId());
            }

            setEnterpriseInvoiceInfo(userVO, queryBean);

        }


        if (!org.springframework.util.CollectionUtils.isEmpty(supplierIds)) {
            List<Supplier> suppliers = supplierMapper.selectByIds(supplierIds);
            for (QueryBean queryBean : supplierQueryBean) {

                /**
                 * 发票类型（0-增值税发票；1-普通发票；2-票据)
                 */
                queryBean.setInvoiceType(0);

                for (Supplier supplier : suppliers) {

                    if (queryBean.getId().equals(supplier.getId())) {

                        queryBean.setTaxpayerIdCode(supplier.getTaxpayerCode());

                        /**
                         * 供货单位开户户名
                         */
                        queryBean.setAccountName(supplier.getBankAccountName());

                        /**
                         * 供货单位开户银行
                         */
                        queryBean.setAccountBank(supplier.getBankName());

                        /**
                         * 供货单位开户账号
                         */
                        queryBean.setAccount(supplier.getBankAccount());

                        /**
                         * 供货单位地址
                         */
                        queryBean.setAddress(supplier.getCompanyAddress());

                        /**
                         * 供货单位电话
                         */
                        queryBean.setTelephone(supplier.getTel());

                    }

                }

            }
        }

        if (!org.springframework.util.CollectionUtils.isEmpty(enterpirseIds)) {
            List<Enterprise> enterprises = enterpriseMapper.selectByIds(enterpirseIds);

            for (QueryBean queryBean : enterpirseQueryBean) {

                /**
                 * 发票类型（0-增值税发票；1-普通发票；2-票据)
                 */
                queryBean.setInvoiceType(0);

                for (Enterprise enterprise : enterprises) {

                    if (queryBean.getId().equals(enterprise.getId())) {

                        queryBean.setTaxpayerIdCode(enterprise.getTaxCode());

                        /**
                         * 供货单位开户户名
                         */
                        queryBean.setAccountName(enterprise.getBankAccountName());

                        /**
                         * 供货单位开户银行
                         */
                        queryBean.setAccountBank(enterprise.getBankName());

                        /**
                         * 供货单位开户账号
                         */
                        queryBean.setAccount(enterprise.getBankAccount());

                        /**
                         * 供货单位地址
                         */
                        queryBean.setAddress(enterprise.getCompanyAddress());

                        /**
                         * 供货单位电话
                         */
                        queryBean.setTelephone(enterprise.getTel());

                    }

                }

            }
        }


        supplierQueryBean.addAll(enterpirseQueryBean);


        return supplierQueryBean;
    }


    private void setEnterpriseInvoiceInfo(UserVO userVO, QueryBean queryBean) {

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());

        /**
         * 企业名称
         */
        queryBean.setCompanyName(userVO.getEnterpriseName());

        /**
         * 企业地址
         */
        queryBean.setCompanyAddress(enterprise.getCompanyAddress());

        /**
         * 企业电话
         */
        queryBean.setCompanyTelephone(enterprise.getTel());

        /**
         * 企业纳税人识别号
         */
        queryBean.setCompanyTaxpayerIdCode(enterprise.getTaxCode());

        /**
         * 企业开户户名
         */
        queryBean.setCompanyAccountName(enterprise.getBankAccountName());

        /**
         * 企业开户银行
         */
        queryBean.setCompanyAccountBank(enterprise.getBankName());

        /**
         * 企业开户账号
         */
        queryBean.setCompanyAccount(enterprise.getBankAccount());
    }


    public List<User> getReceivableManNameSelector(UserVO loginUser, String param) {
        //员工编码/员工检索码/员工名称模糊搜索
        List<User> list = userMapper.getReceivableManNameSelector(param, loginUser.getEnterpriseId());
        return list;
    }


    public void validationAccountingDate(String dateStr, UserVO userVO) {
        Long enterpriseId = userVO.getEnterpriseId();
        if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()) {
            enterpriseId = userVO.getParentId();
        }
        int size = accountingPeriodDetailMapper.selectByParams(enterpriseId, DateUtils.StringToDate(dateStr, DateStyle.YYYY_MM_DD), PeriodStatus.OPEN.getCode());
        if (size == 0) {
            throw new BusinessException("所选日期已结账或未激活，请重新选择！");
        }
    }


    public List<SelectPricingGoodsVO> selectGoodsByParam(UserVO userVO, GoodsParamStockVO goodsParamStockVO) {

        EnterpriseBusiness enterpriseBus = queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());

        List<SelectPricingGoodsVO> list = prescriptionRegisterMapper.getGoodsInfoForStockByGoods(userVO.getEnterpriseId(), goodsParamStockVO.getParam(), goodsParamStockVO.getPrescriptionType(), goodsParamStockVO.getIsInChargeDrug());
        list.forEach(item -> {
            item.setProductDateStr(DateUtils.DateToString(item.getProductDate(), DateStyle.YYYY_MM_DD));
            item.setValidUntilStr(DateUtils.DateToString(item.getValidUntil(), DateStyle.YYYY_MM_DD));
            GoodsBusiness goodsBusiness = goodsBusinessMapper.getByGoodsId(item.getGoodsId());
            //如果是特价商品
            if (null != goodsBusiness && goodsBusiness.getBargainGoods() == 1) {
                Map map = new HashMap();
                if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                        && 0 == enterpriseBus.getSpecialPriceManage()) {
                    //如果是门店且开启特价管理总部控制，则获取商品的总部的特价策略
                    map.put("enterpriseId", userVO.getParentId());
                } else {
                    map.put("enterpriseId", userVO.getEnterpriseId());
                }
                map.put("goodsId", item.getGoodsId());
                SpecialPriceGoods specialPriceGoods = specialPriceGoodsMapper.selectByEnterpriseIdAndGoodsId(map);
                if (null != specialPriceGoods) {//如果有特价策略 按照特价策略的内容获取价格
                    SpecialPriceStrategy specialPriceStrategy = specialPriceStrategyMapper.selectByPrimaryKey(specialPriceGoods.getStrategyId());
                    /**
                     * 变价（0-禁止；1-允许）
                     */
                    item.setValence(specialPriceStrategy.getValence());
                    /**
                     * 折扣（0-禁止；1-允许）
                     */
                    item.setDiscount(specialPriceStrategy.getDiscount());
                    /**
                     * 会员策略（0-禁止；1-允许）
                     */
                    item.setMemberStrategy(specialPriceStrategy.getMemberStrategy());

                } else {//如果没有特价策略   如果没有--》只能去价格清单中的零售价格，不能改价格，不能改折扣
                    /**
                     * 变价（0-禁止；1-允许）
                     */
                    item.setValence(0);
                    /**
                     * 折扣（0-禁止；1-允许）
                     */
                    item.setDiscount(0);
                    /**
                     * 会员策略（0-禁止；1-允许）
                     */
                    item.setMemberStrategy(0);
                }
            } else {//如果不是特价商品
                /**
                 * 变价（0-禁止；1-允许）
                 */
                item.setValence(0);
                /**
                 * 折扣（0-禁止；1-允许）
                 */
                item.setDiscount(0);
                /**
                 * 会员策略（0-禁止；1-允许）
                 */
                item.setMemberStrategy(0);
            }
        });
        return list;
    }

    public EnterpriseBusiness queryEnterpriseBusinessByEnterpriseId(
            Long enterpriseId) {
        EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(enterpriseId);

        if (bus != null) {
            bus.setSettlementModeName(SettlementMode.getName(bus.getSettlementMode()));
            bus.setDistrPriceTypeName(DistrPriceType.getName(bus.getDistrPriceType()));
            bus.setPaymentProvisionName(PaymentProvision.getName(bus.getPaymentProvision()));
            bus.setPaymentPeriodUnitName(PaymentPeriodUnit.getName(bus.getPaymentPeriodUnit()));
            bus.setPaymentTimeUnitName(PaymentTimeUnit.getName(bus.getPaymentTimeUnit()));
        }

        return bus;
    }


	public void setPrepayInvoiceInStoreMaxQuantity(List<PrepayInvoiceInStoreResponseVO> purchaseInStorageDetails){

        Iterator<PrepayInvoiceInStoreResponseVO> iterator = purchaseInStorageDetails.iterator();

        while (iterator.hasNext()){

            PrepayInvoiceInStoreResponseVO pis = iterator.next();
            /**
             * 经营方式（0-购销；1-实销实结）
             */
            if(1 == pis.getManagementMode()){

                /**
                 * 未实销实结商品的时候 最大开票数量为 已核销数量
                 */

                BigDecimal subtract = pis.getVerificationQuantity().subtract(pis.getClearQuantity());
                if(subtract.compareTo(BigDecimal.ZERO) < 0){
                    /**
                     * 已核销数量-已清数量为实销实结的业务单据最大开票数量 如果为负数,则业务单据数据有问题
                     */

                    throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"实销实结 已核销数量小于已清数量,不能开票");

                }
                pis.setMaxQuantity(subtract);


            }else {

                /**
                 * 不是未实销实结商品的时候 最大开票数量为 未清数量
                 */
                pis.setMaxQuantity(pis.getUnclearQuantity());
            }
        }

    }


    public void setPaymentInvoiceInStoreMaxQuantity4Transfer(List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS,List<GoodsBusiness> goodsBusinesses){


        Iterator<PaymentInvoiceGoodsVO> iterator = paymentInvoiceGoodsVOS.iterator();

        while (iterator.hasNext()){

            PaymentInvoiceGoodsVO paymentInvoiceGoodsVO = iterator.next();

            for(GoodsBusiness goodsBusiness : goodsBusinesses){

                if(paymentInvoiceGoodsVO.getGoodsId().equals(goodsBusiness.getGoodsId())){

                    paymentInvoiceGoodsVO.setManagementMode(goodsBusiness.getManagementMode());

                    /**
                     * 经营方式（0-购销；1-实销实结）
                     */
                    if(1 == goodsBusiness.getManagementMode()){

                        /**
                         * 未实销实结商品的时候 最大开票数量为 已核销数量
                         */
                        BigDecimal subtract = paymentInvoiceGoodsVO.getVerificationQuantity().subtract(paymentInvoiceGoodsVO.getClearQuantity());

                        /**
                         * 已核销数量-已清数量为实销实结的业务单据最大开票数量
                         */
                        paymentInvoiceGoodsVO.setMaxQuantity(subtract);

                        paymentInvoiceGoodsVO.setAccountQuantity(subtract);

                        /**
                         * 金额
                         */
                        BigDecimal amount = paymentInvoiceGoodsVO.getAccountQuantity().multiply(paymentInvoiceGoodsVO.getUnitPrice());
                        paymentInvoiceGoodsVO.setAmount(amount);


                        /**
                         * 不含税实际金额
                         */
                        BigDecimal notaxRealAmount= paymentInvoiceGoodsVO.getAccountQuantity().multiply(paymentInvoiceGoodsVO.getNotaxRealPrice()).setScale(2,BigDecimal.ROUND_HALF_UP);
                        paymentInvoiceGoodsVO.setNotaxRealAmount(notaxRealAmount);

                        /**
                         *
                         * 税额 根据实际金额和不含税金额获取税额：金额-不含税金额
                         */
                        BigDecimal taxAmount= CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(amount, notaxRealAmount);
                        paymentInvoiceGoodsVO.setTaxAmount(taxAmount);

                        if(subtract.compareTo(BigDecimal.ZERO) < 0){
                            /**
                             * 已核销数量-已清数量为实销实结的业务单据最大开票数量 如果为负数,则业务单据数据有问题
                             */

                            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"实销实结 已核销数量小于已清数量,不能开票");

                        }else if(subtract.compareTo(BigDecimal.ZERO) == 0){
                            /**
                             * 如果 已核销-已清数量为0 则表示没有可以开票的数据了
                             */
                            iterator.remove();
                        }

                    }else {

                        /**
                         * 不是未实销实结商品的时候 最大开票数量为 未清数量
                         */
                        paymentInvoiceGoodsVO.setMaxQuantity(paymentInvoiceGoodsVO.getUnclearQuantity());
                    }

                }
            }
        }

    }

    public void setPaymentInvoiceInStoreMaxQuantity(List<PaymentInvoiceGoodsVO> paymentInvoiceGoodsVOS,List<GoodsBusiness> goodsBusinesses){


        for(PaymentInvoiceGoodsVO paymentInvoiceGoodsVO : paymentInvoiceGoodsVOS){

            for(GoodsBusiness goodsBusiness : goodsBusinesses){

                if(paymentInvoiceGoodsVO.getGoodsId().equals(goodsBusiness.getGoodsId())){

                    paymentInvoiceGoodsVO.setManagementMode(goodsBusiness.getManagementMode());

                    /**
                     * 经营方式（0-购销；1-实销实结）
                     */
                    if(1 == goodsBusiness.getManagementMode()){

                        /**
                         * 未实销实结商品的时候 最大开票数量为 已核销数量
                         */
                        BigDecimal subtract = paymentInvoiceGoodsVO.getVerificationQuantity().subtract(paymentInvoiceGoodsVO.getClearQuantity());
                        if(subtract.compareTo(BigDecimal.ZERO) < 0){
                            /**
                             * 已核销数量-已清数量为实销实结的业务单据最大开票数量 如果为负数,则业务单据数据有问题
                             */

                            throw new PrepayInvoiceBizException(PrepayInvoiceBizException.VALUE_CHECK,"实销实结 已核销数量小于已清数量,不能开票");

                        }
                        /**
                         * 已核销数量-已清数量为实销实结的业务单据最大开票数量
                         */
                        paymentInvoiceGoodsVO.setMaxQuantity(subtract);

                        paymentInvoiceGoodsVO.setAccountQuantity(subtract);

                        /**
                         * 金额
                         */
                        BigDecimal amount = paymentInvoiceGoodsVO.getAccountQuantity().multiply(paymentInvoiceGoodsVO.getUnitPrice());
                        paymentInvoiceGoodsVO.setAmount(amount);


                        /**
                         * 不含税实际金额
                         */
                        BigDecimal notaxRealAmount= paymentInvoiceGoodsVO.getAccountQuantity().multiply(paymentInvoiceGoodsVO.getNotaxRealPrice()).setScale(2,BigDecimal.ROUND_HALF_UP);
                        paymentInvoiceGoodsVO.setNotaxRealAmount(notaxRealAmount);

                        /**
                         *
                         * 税额 根据实际金额和不含税金额获取税额：金额-不含税金额
                         */
                        BigDecimal taxAmount= CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(amount, notaxRealAmount);
                        paymentInvoiceGoodsVO.setTaxAmount(taxAmount);
                    }else {

                        /**
                         * 不是未实销实结商品的时候 最大开票数量为 未清数量
                         */
                        paymentInvoiceGoodsVO.setMaxQuantity(paymentInvoiceGoodsVO.getUnclearQuantity());
                    }

                }

            }
        }


    }


    public GoodsTaxRateVO getTaxRate(List<GoodsTaxRateVO> goodsTaxRateVOS,Long taxId){
        if(taxId == null) throw new BusinessException("税率id不能为空");
        for (GoodsTaxRateVO item : goodsTaxRateVOS) {
            if (item.getId().equals(taxId)) {
                return item;
            }
        }
        throw new BusinessException("税率不存在");

    }

    /**
     *  获取基础价格清单明细
     * @param chainType
     * @param enterpriseId
     * @param parentId
     * @param goodsId
     * @return
     */
    public PriceOrderDetail getBasePriceOrderDetail(Long enterpriseId,Long parentId,Long goodsId){
        try{
            PriceOrder priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);
            return priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(enterpriseId,priceOrder.getId(),goodsId);
        }catch (TooManyResultsException exception){
            throw new BusinessException("企业ID[" + enterpriseId + "]的基础价格清单查出多条");
        }catch (Exception e){
            throw new BusinessException("查询基础价格清单异常");
        }

    }


}
