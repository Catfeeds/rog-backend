package com.rograndec.feijiayun.chain.business.online.purchase.smart.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInCheckLotMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInCheckMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReceiveMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInCheck;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInCheckDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNotice;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReceive;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInCheckService;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReceiveService;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInStorageService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveDetailSaveVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveDetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveSaveVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInStorageShelfDtlVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInstorageDtlVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInstorageFormVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2DetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheckDetail2DetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheckLot2DetailVO;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.PickOrderMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSend;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSendDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.PickOrder;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrOutService;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrPickService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutCheckVo;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationDtlVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationSlfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.dao.ActivityGoodsMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.component.CommonQueryConfiguration;
import com.rograndec.feijiayun.chain.business.online.purchase.constant.SmartCartConstant;
import com.rograndec.feijiayun.chain.business.online.purchase.order.dao.MphgoodsRelevanceMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.order.dao.MphsupplierRelevanceMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.order.entity.MphgoodsRelevance;
import com.rograndec.feijiayun.chain.business.online.purchase.order.entity.MphsupplierRelevance;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.MphorderReceiveDetailTemsaveMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.MphorderReceiveShelfTemsaveMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.MphorderReceiveTemsaveMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.dao.SupplierDisplayDetailMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.MphorderReceiveDetailTemsave;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.MphorderReceiveShelfTemsave;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.MphorderReceiveTemsave;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SupplierDisplayDetail;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.service.OnlineMarketService;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.BoundGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.GetReceiptDataVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.GetReceiptGoodsDataVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.GetReceiptGoodsLotDataVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.OnlineMarketVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SaveBoundGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SaveBoundSupplyVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectBoundGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectBoundSupplyVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectSmartEntrepriseVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectSmartGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectSmartSupplierVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectSupplierVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingCartVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingGoodsVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSourcingSupplierVO;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckLotMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheck;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckDetail;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckLot;
import com.rograndec.feijiayun.chain.business.purchase.check.service.PurchaseCheckService;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.purchase.instorage.service.StayInstorageService;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.CheckLotDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseOrderOtherVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageDetailSaveVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageSaveVO;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveDetail;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveOther;
import com.rograndec.feijiayun.chain.business.purchase.receive.service.PurchaseReceiveService;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.DistrComponent;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrSendStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.LockQtyArgVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.mph.AcceptSaasOrder;
import com.rograndec.feijiayun.chain.inf.mph.vo.OrderDetail;
import com.rograndec.feijiayun.chain.inf.search.vo.MphGoods;
import com.rograndec.feijiayun.chain.inf.search.vo.MphSupplier;
import com.rograndec.feijiayun.chain.inf.search.vo.MphSupplierGoods;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphGroupSupplierGoodsResult;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphSupplierGoodsResult;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

@Service
public class OnlineMarketServiceImpl implements OnlineMarketService {

    private final static Logger logger = LoggerFactory.getLogger(OnlineMarketServiceImpl.class);

    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    SupplierDisplayDetailMapper supplierDisplayDetailMapper;
    @Autowired
    ActivityGoodsMapper activityGoodsMapper;
    @Autowired
    CommonQueryConfiguration commonQueryConfiguration;
    @Autowired
    MphgoodsRelevanceMapper mphgoodsRelevanceMapper;
    @Autowired
    MphsupplierRelevanceMapper mphsupplierRelevanceMapper;
    @Autowired
    OrderCodeComponent codeComponent;
    @Autowired
    ManageConfigMapper configMapper;
    @Autowired
    SafetyStockMapper safetyStockMapper;
    @Autowired
    GoodsBusinessMapper businessMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    PurchaseOrderMapper orderMapper;
    @Autowired
    PurchaseOrderDetailMapper orderDetailMapper;
    @Autowired
    PurchaseOrderOtherMapper orderOtherMapper;
    @Autowired
    PurchaseReceiveService receiveService;
    @Autowired
    PurchaseReceiveMapper receiveMapper;
    @Autowired
    PurchaseReceiveDetailMapper receiveDetailMapper;
    @Autowired
    PurchaseReceiveOtherMapper receiveOtherMapper;
    @Autowired
    QualitySetMapper qualitySetMapper;
    @Autowired
    PurchaseCheckService checkService;
    @Autowired
    PurchaseCheckMapper checkMapper;
    @Autowired
    PurchaseCheckDetailMapper checkDetailMapper;
    @Autowired
    PurchaseCheckLotMapper checkLotMapper;
    @Autowired
    StayInstorageService stayInstorageService;
    @Autowired
    GoodsTaxRateMapper taxRateMapper;
    @Autowired
    StayInstorageService inStorageService;
    @Autowired
    PurchaseInStorageMapper inStorageMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    ManageConfigComponent manageConfigComponent;
    @Autowired
    DistrInReceiveMapper distrInReceiveMapper;
    @Autowired
    DistrInReceiveDetailMapper distrInReceiveDetailMapper;
    @Autowired
    DistrInCheckMapper distrInCheckMapper;
    @Autowired
    DistrInCheckDetailMapper distrInCheckDetailMapper;
    @Autowired
    DistrInCheckLotMapper distrInCheckLotMapper;
    @Autowired
    PurchaseCheckLotMapper purchaseCheckLotMapper;
    @Autowired
    DistrInReceiveService distrInReceiveService;
    @Autowired
    DistrInNoticeDetailMapper distrInNoticeDetailMapper;
    @Autowired
    DistrInNoticeMapper distrInNoticeMapper;
    @Autowired
    DistrInCheckService distrInCheckService;
    @Autowired
    PurchaseCheckDetailMapper purchaseCheckDetailMapper;
    @Autowired
    DistrInStorageService distrInStorageService;
    @Autowired
    DistrInMapper distrInMapper;
    @Autowired
    PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;
    @Autowired
    PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;
    @Autowired
    private DistrSendMapper distrSendMapper;
    @Autowired
    private DistrSendDetailMapper distrSendDetailMapper;
    @Autowired
	private PickOrderMapper pickOrderMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
	private DistrPickService distrPickService;
    @Autowired
    private DistrOutService distrOutService;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;
    @Autowired
    private WarehouseCargoAreaMapper warehouseCargoAreaMapper;
    @Autowired
    private LotNumberMapper lotNumberMapper;
    @Autowired
    private DistrComponent distrComponent;
    @Autowired
    private MphorderReceiveTemsaveMapper mphorderReceiveTemsaveMapper;
    @Autowired
    private MphorderReceiveDetailTemsaveMapper mphorderReceiveDetailTemsaveMapper;
    @Autowired
    private MphorderReceiveShelfTemsaveMapper mphorderReceiveShelfTemsaveMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Autowired
    private AcceptSaasOrder acceptSaasOrder;
    
    @Autowired
	private StockLockRecordMapper stockLockRecordMapper;
    
    private ExecutorService fixedThread = Executors.newFixedThreadPool(20);
    
    @Override
    public OnlineMarketVO selectOnlineMarket(UserVO userVO, int pageNo, int pageSize, String searchValues) {

        OnlineMarketVO onlineMarketVO = new OnlineMarketVO();
        onlineMarketVO.setKeyword(StringUtils.isBlank(searchValues)?"":searchValues);
        onlineMarketVO.setEnterpriseId(Integer.valueOf(userVO.getEnterpriseId().toString()));
        onlineMarketVO.setUserId(Integer.valueOf(userVO.getRgtUserId().toString()));
        onlineMarketVO.setRows(pageSize);
        onlineMarketVO.setStart(commonQueryConfiguration.getOffsetByPageNoAndPageSize(pageNo, pageSize));

        onlineMarketVO.setSupplierId(commonQueryConfiguration.getSmartDisplay(userVO.getEnterpriseId()));
//        if(commonQueryConfiguration.getSupplierDisplayDetailList(userVO.getEnterpriseId()) != null ){
        onlineMarketVO.setOrderSupplierIds(getSmartDisplaySupplierIdS(commonQueryConfiguration.getSupplierDisplayDetailList(userVO.getEnterpriseId())));
//        }else
//            onlineMarketVO.setSupplierIds(null);

        return onlineMarketVO;
    }



    @Override
    public SelectSmartEntrepriseVO getOnlineMarket(SearchMphGroupSupplierGoodsResult searchMphGroupSupplierGoodsResult,Page page) {

    	SelectSmartEntrepriseVO selectSmartEntrepriseVO = new SelectSmartEntrepriseVO();
    	
        if(searchMphGroupSupplierGoodsResult != null && searchMphGroupSupplierGoodsResult.getSupplierGoodsList() != null
        		&& searchMphGroupSupplierGoodsResult.getSupplierGoodsList().size() > 0) {

            selectSmartEntrepriseVO.setStart(searchMphGroupSupplierGoodsResult.getStart());
            selectSmartEntrepriseVO.setRows(searchMphGroupSupplierGoodsResult.getRows());
            selectSmartEntrepriseVO.setTotalCount(searchMphGroupSupplierGoodsResult.getTotalCount());

            List<String> gcName1S = new ArrayList<>();
            List<String> gcName2S = new ArrayList<>();
            List<String> manufacturerS = new ArrayList<>();

            String manufacturer = "";
            String gcName1 = "";
            String gcName2 = "";

            List<SelectSmartSupplierVO> selectSmartSupplierVOS = new ArrayList<>();

            List<MphSupplierGoods> supplierGoodsListS = searchMphGroupSupplierGoodsResult.getSupplierGoodsList();
            for (MphSupplierGoods supplierGoodsList : supplierGoodsListS) {

                SelectSmartSupplierVO selectSmartSupplierVO = new SelectSmartSupplierVO();
                MphSupplier supplier = supplierGoodsList.getSupplier();
                selectSmartSupplierVO.setMphSupplierId(Long.valueOf(supplier.getId()));
                selectSmartSupplierVO.setMphSupplierName(supplier.getName());
                selectSmartSupplierVO.setMatchingAmount(BigDecimal.valueOf(supplier.getMinPurchaseAmount()));
                if (supplier.getIsMinPurchased() == 0) {
                    selectSmartSupplierVO.setMatchingAmount(BigDecimal.valueOf(-1));
                }
                selectSmartSupplierVO.setIsMinPurchased(supplier.getIsMinPurchased());

                List<MphGoods> goodsListS = supplierGoodsList.getGoodsList();
                List<SelectSmartGoodsVO> selectSmartGoodsVOS = new ArrayList<>();
                for (int i = 0; i < goodsListS.size(); i++) {
                    SelectSmartGoodsVO selectSmartGoodsVO = new SelectSmartGoodsVO();
                    selectSmartGoodsVO.setBatchNo(goodsListS.get(i).getBatchNo());
                    selectSmartGoodsVO.setCanPurchased(goodsListS.get(i).getCanPurchased());
                    selectSmartGoodsVO.setCanSplit(goodsListS.get(i).getgCanSplit());
                    selectSmartGoodsVO.setExpiryDate(goodsListS.get(i).getExpiryDate());
                    selectSmartGoodsVO.setGcName1(goodsListS.get(i).getCategory1Name());
                    selectSmartGoodsVO.setGcName2(goodsListS.get(i).getCategory2Name());
                    selectSmartGoodsVO.setGoodsId(Long.valueOf(goodsListS.get(i).getId()));
                    selectSmartGoodsVO.setGoodsName(goodsListS.get(i).getName());
                    selectSmartGoodsVO.setInventoryQuantity(goodsListS.get(i).getInventoryQuantity());
                    selectSmartGoodsVO.setManufacturer(goodsListS.get(i).getManufacturer());
                    selectSmartGoodsVO.setMiddlePackage(goodsListS.get(i).getgMiddlePackage());
                    selectSmartGoodsVO.setPictureAddress(goodsListS.get(i).getImage());
                    selectSmartGoodsVO.setPromotionDescription(goodsListS.get(i).getPromotionDescription());
                    selectSmartGoodsVO.setPromotionPrice(goodsListS.get(i).getPromotionPrice());
                    selectSmartGoodsVO.setPromotionType(goodsListS.get(i).getPromotionType());
                    selectSmartGoodsVO.setPurchasePrice(goodsListS.get(i).getPurchasePrice());
                    selectSmartGoodsVO.setRetailPrice(BigDecimal.valueOf(goodsListS.get(i).getRetailPrice()));
                    selectSmartGoodsVO.setSpecification(goodsListS.get(i).getSpecification());
                    selectSmartGoodsVOS.add(selectSmartGoodsVO);

                    manufacturer += goodsListS.get(i).getManufacturer() + ",";
                    gcName1 += goodsListS.get(i).getCategory1Name() + ",";
                    gcName2 += goodsListS.get(i).getCategory2Name() + ",";
                    if (gcName1 != null) {
                        if (i != 0) {
                            if (checkRepeat(gcName1S, goodsListS.get(i).getCategory1Name())) {
                                gcName1S.add(goodsListS.get(i).getCategory1Name());
                            }
                        } else {
                            gcName1S.add(goodsListS.get(i).getCategory1Name());
                        }
                    }
                    if (gcName2 != null) {
                        if (i != 0) {
                            if (checkRepeat(gcName2S, goodsListS.get(i).getCategory2Name())) {
                                gcName2S.add(goodsListS.get(i).getCategory2Name());
                            }
                        } else {
                            gcName2S.add(goodsListS.get(i).getCategory2Name());
                        }
                    }
                    if (manufacturer != null) {
                        if (i != 0) {
                            if (checkRepeat(manufacturerS, goodsListS.get(i).getManufacturer())) {
                                manufacturerS.add(goodsListS.get(i).getManufacturer());
                            }
                        } else {
                            manufacturerS.add(goodsListS.get(i).getManufacturer());
                        }
                    }
                }
                selectSmartSupplierVO.setSelectSmartGoodsVO(selectSmartGoodsVOS);
                selectSmartSupplierVOS.add(selectSmartSupplierVO);
            }

            selectSmartEntrepriseVO.setManufacturer(manufacturerS);
            selectSmartEntrepriseVO.setGcName1S(gcName1S);
            selectSmartEntrepriseVO.setGcName2S(gcName2S);
            selectSmartEntrepriseVO.setSelectSmartSupplierVO(selectSmartSupplierVOS);

            page.setTotalRecord(selectSmartEntrepriseVO.getTotalCount() == null ? 0 : selectSmartEntrepriseVO.getTotalCount().intValue());

        }else{
        	selectSmartEntrepriseVO.setStart(0);
            selectSmartEntrepriseVO.setRows(0);
            selectSmartEntrepriseVO.setTotalCount(0);
            selectSmartEntrepriseVO.setManufacturer(new ArrayList<String>());
            selectSmartEntrepriseVO.setGcName1S(new ArrayList<String>());
            selectSmartEntrepriseVO.setGcName2S(new ArrayList<String>());
            selectSmartEntrepriseVO.setSelectSmartSupplierVO(new ArrayList<SelectSmartSupplierVO>());
            page.setTotalRecord(0);
        }
        return selectSmartEntrepriseVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GetReceiptDataVO geteceiptRData(String mphResult, Long orderId, String orderCode, String supplyName, UserVO loginUser, Date oaddTime) throws Exception{
        /**
         * 返回结果集
         */
        GetReceiptDataVO getReceiptDataVO = new GetReceiptDataVO();
        /**
         * tips:以订单ID + 订单编号 + 企业ID 确定唯一
         * 如果查出数据就显示本地库存在的数据,如果没有就调用MPH接口返回数据（并且插入数据库数据）
         */
        MphorderReceiveTemsave mph = mphorderReceiveTemsaveMapper.
                selectByEnterpriseIdAndOrderIdAndOrderCode(loginUser.getEnterpriseId(),orderId,orderCode);
        List<MphorderReceiveDetailTemsave> mphGoodList = new ArrayList<>();
        List<MphorderReceiveShelfTemsave> mphLotList = new ArrayList<>();
        if (mph != null){
            mphGoodList = mphorderReceiveDetailTemsaveMapper.selectByReceiveTemsaveId(mph.getId());
            mphLotList = mphorderReceiveShelfTemsaveMapper.selectByReceiveTemsaveId(mph.getId());
        }

        // 存放处理后的数据;
        if (mph != null){
            /**
             * 将查询出的对象转换为GetReceiptDataVO
             */
            getReceiptDataVO.setId(mph.getId());
            getReceiptDataVO.setOrderId(orderId);
            getReceiptDataVO.setOrderCode(orderCode);
            getReceiptDataVO.setMphSupplyName(supplyName);
            getReceiptDataVO.setMphSupplyId(mph.getMphSupplierId());
            getReceiptDataVO.setPurchaseDate(mph.getPurchaseDate());
            getReceiptDataVO.setReceiveDate(mph.getReceiveDate());
            getReceiptDataVO.setCheckDate(mph.getCheckDate());
            getReceiptDataVO.setInDate(mph.getInDate());
            getReceiptDataVO.setPurchaseManId(mph.getPurchaseManId());
            getReceiptDataVO.setReceiveManId(mph.getReceiveManId());
            getReceiptDataVO.setSecondReceiveManId(mph.getSecondReceiveManId());
            getReceiptDataVO.setCheckManId(mph.getCheckManId());
            getReceiptDataVO.setSecondCheckManId(mph.getSecondCheckManId());
            getReceiptDataVO.setInManId(mph.getInManId());
            getReceiptDataVO.setFlowCode(mph.getFlowCode());
            if(mph.getMphSupplierId() != null){
                SelectSupplierVO supplierVO = mphsupplierRelevanceMapper.selectByMPHSupplyId(mph.getMphSupplierId(),loginUser.getEnterpriseId());
                if(supplierVO != null){
                    getReceiptDataVO.setSupplyId(supplierVO.getId());
                    getReceiptDataVO.setSupplyName(supplierVO.getName());
                    getReceiptDataVO.setSupplyCode(supplierVO.getCode());
                    getReceiptDataVO.setSupplierSalerId(supplierVO.getSaleManId());
                    getReceiptDataVO.setSupplierSalerCode(supplierVO.getSaleManCode());
                    getReceiptDataVO.setSupplierSalerName(supplierVO.getSaleManName());
                    getReceiptDataVO.setSupplierSalerPhone(supplierVO.getSaleManPhone());
                }
            }
            List<GetReceiptGoodsDataVO> getReceiptGoodsDataVOS = new ArrayList<>();
            for (MphorderReceiveDetailTemsave good :mphGoodList) {
                GetReceiptGoodsDataVO getReceiptGoodsDataVO = new GetReceiptGoodsDataVO();
                getReceiptGoodsDataVO.setId(good.getId());
                getReceiptGoodsDataVO.setOrderDetailId(good.getOrderDetailId());
                getReceiptGoodsDataVO.setMphGoodsId(good.getMphGoodsId());// MPH商品ID
                getReceiptGoodsDataVO.setMphGoodsName(good.getMphGoodsName());// MPH商品
                getReceiptGoodsDataVO.setDeliverAmount(good.getSendAmount());// 发货金额
                getReceiptGoodsDataVO.setDeliverQty(good.getSendQuantity());// 发货数量
                getReceiptGoodsDataVO.setPurchasePrice(good.getUnitPrice());//采购单价
                getReceiptGoodsDataVO.setPurchaseQty(good.getPurchaseQuantity());//采购数量
                getReceiptGoodsDataVO.setSpecification(good.getMphSpecification());//规格
                getReceiptGoodsDataVO.setMphGoodsManufacturer(good.getMphManufacturer());//生产厂家
                //拒收数量
                getReceiptGoodsDataVO.setReturnQty(good.getPurchaseQuantity().subtract(good.getSendQuantity()));
                getReceiptGoodsDataVO.setTaxRateId(good.getTaxRateId());//税率
                getReceiptGoodsDataVO.setNotaxRealAmount(good.getNotaxRealAmount());//不含税金额
                getReceiptGoodsDataVO.setNotaxRealPrice(good.getNotaxRealPrice());//不含税单价
                BoundGoodsVO boundGoods = mphgoodsRelevanceMapper.selectBYGid(good.getMphGoodsId(), loginUser.getEnterpriseId());
                if (boundGoods != null) {
                    getReceiptGoodsDataVO.setBoundGoods(boundGoods.getBoundGoods());//绑定商品
                    getReceiptGoodsDataVO.setGoodsId(boundGoods.getGoodsId());//saas对应商品ID
                    Goods goods = goodsMapper.selectByPrimaryKey(boundGoods.getGoodsId());
                    getReceiptGoodsDataVO.setGoodsCode(goods.getCode()); 
                    getReceiptGoodsDataVO.setGoods(goods);
                }
                Long saasGoodsId = null;
                if (boundGoods != null) {
                    saasGoodsId = boundGoods.getGoodsId();
                }
                //判断订单中的商品是不是新品
                Integer newGoods = 0; //0：不是新品 1:是新品
                newGoods = getGoodsStatus(newGoods,loginUser,good.getMphGoodsId(),good.getMphGoodsName(),good.getManufacturer(),good.getMphSpecification());
                getReceiptGoodsDataVO.setNewGoods(newGoods);//是否为新品 0：不是新品 1:是新品
                List<GetReceiptGoodsLotDataVO> getReceiptGoodsLotDataVO = new ArrayList<>();
                for (MphorderReceiveShelfTemsave lot :mphLotList) {
                    GetReceiptGoodsLotDataVO goodsLotDataVO = new GetReceiptGoodsLotDataVO();
                    if (lot.getReceiveDetailTemsaveId().equals(good.getId())){
                        goodsLotDataVO.setId(lot.getId());
                        goodsLotDataVO.setLotNum(lot.getLotNumber());
                        goodsLotDataVO.setProductDate(lot.getProductDate());
                        goodsLotDataVO.setValidDate(lot.getValidDate());
                        goodsLotDataVO.setCheckProjectIds(lot.getCheckProjectIds());
                        goodsLotDataVO.setCheckResult(lot.getCheckResult());
                        goodsLotDataVO.setReceiveQuantity(lot.getReceiveQuantity());
                        if (saasGoodsId != null) {
                            goodsLotDataVO.setGoodsId(saasGoodsId);//saas对应商品ID
                            goodsLotDataVO.setShelfId(lot.getShelfId());
                            goodsLotDataVO.setShelfName(lot.getShelfName());
                        }
                        getReceiptGoodsLotDataVO.add(goodsLotDataVO);
                    }
                }
                getReceiptGoodsDataVO.setGetReceiptGoodsLotDataVO(getReceiptGoodsLotDataVO);
                getReceiptGoodsDataVOS.add(getReceiptGoodsDataVO);
            }
            getReceiptDataVO.setGetReceiptGoodsDataVO(getReceiptGoodsDataVOS);
        }else {
            /**
             * 调用MPH接口并插入数据库，给前端返回封装后的MPH数据GetReceiptDataVO
             */
            if("".equals(mphResult) || mphResult == null){
                return null;
            }
            JSONObject jasonObject = new JSONObject();
            jasonObject = JSONObject.parseObject(mphResult);
            getReceiptDataVO.setOrderId(orderId);
            getReceiptDataVO.setOrderCode(orderCode);
            getReceiptDataVO.setMphSupplyName(supplyName);
            /**
             * 人员信息
             */
            ManageConfig config =  getConfigInfo(loginUser.getEnterpriseId());

            if (loginUser.getChainType() == ChainType.Headquarters.getCode()){
                getReceiptDataVO.setPurchaseManId(config.getPurchaserId());//采购人员：总部
            }else {
                getReceiptDataVO.setPurchaseManId(config.getRequesterId());
            }
            getReceiptDataVO.setPurchaseDate(oaddTime);
            getReceiptDataVO.setReceiveManId(config.getReceiverId());//收货人员
            getReceiptDataVO.setSecondReceiveManId(config.getSecondReceiverId());//第二收货人员
            getReceiptDataVO.setCheckManId(config.getCheckerId());
            getReceiptDataVO.setSecondCheckManId(config.getSecondCheckerId());
            getReceiptDataVO.setInManId(config.getInOutManId());

            List<GetReceiptGoodsDataVO> getReceiptGoodsDataVOS = new ArrayList<>();

            if (jasonObject.getBooleanValue("success") == true && jasonObject.getString("data") != null) {
                // 获取主表JSONArray数据
                JSONArray json_array = (JSONArray) jasonObject.get("data");
                // 存放处理后的数据;
                Long odSellerId = null;
                for (int i = 0; i < json_array.size(); i++) {

                    GetReceiptGoodsDataVO getReceiptGoodsDataVO = new GetReceiptGoodsDataVO();
                    JSONObject jsonObject = json_array.getJSONObject(i);
                    if (jsonObject.isEmpty() || null == jsonObject) {
                        break;
                    }
                    Long odId = jsonObject.get("odId") == null ? 0L : Long.parseLong(jsonObject.get("odId").toString());// 明细ID
                    odSellerId = jsonObject.get("odSellerId") == null ? 0L : Long.parseLong(jsonObject.get("odSellerId").toString());// 供应商ID
                    Long odGId = jsonObject.get("odGId") == null ? 0L
                            : Long.parseLong(jsonObject.get("odGId").toString());// 商品ID
                    String odName = jsonObject.get("odName") == null ? "" : jsonObject.get("odName").toString();// 商品名称
                    String odSpecifications = jsonObject.get("odSpecifications") == null ? ""
                            : jsonObject.get("odSpecifications").toString();// 规格
                    String odLicenseNumber = jsonObject.get("odLicenseNumber") == null ? ""
                            : jsonObject.get("odLicenseNumber").toString();// 批准文号
                    String odManufacture = jsonObject.get("odManufacture") == null ? ""
                            : jsonObject.get("odManufacture").toString();// 厂家
                    Double odPrice = jsonObject.get("odPrice") == null ? 0.0
                            : Double.parseDouble(jsonObject.get("odPrice").toString());// 单价
                    Integer odNumber = jsonObject.get("odNumber") == null ? 0
                            : Integer.parseInt(jsonObject.get("odNumber").toString());// 购买数量
                    Double odSubtotal = jsonObject.get("odSubtotal") == null ? 0.0
                            : Double.parseDouble(jsonObject.get("odSubtotal").toString());// 总价
                    Integer odSendNumber = jsonObject.get("odSendNumber") == null ? 0
                            : Integer.parseInt(jsonObject.get("odSendNumber").toString());// 发货数量

                    getReceiptGoodsDataVO.setMphGoodsId(odGId);// MPH商品ID
                    getReceiptGoodsDataVO.setMphGoodsName(odName);// MPH商品
                    getReceiptGoodsDataVO.setDeliverAmount(BigDecimal.valueOf(odSubtotal));// 发货金额
                    getReceiptGoodsDataVO.setDeliverQty(BigDecimal.valueOf(odSendNumber));// 发货数量
                    getReceiptGoodsDataVO.setPurchasePrice(BigDecimal.valueOf(odPrice));//采购单价
                    getReceiptGoodsDataVO.setPurchaseQty(BigDecimal.valueOf(odNumber));//采购数量
                    getReceiptGoodsDataVO.setSpecification(odSpecifications);//规格
                    getReceiptGoodsDataVO.setMphGoodsManufacturer(odManufacture);//生产厂家
                    getReceiptGoodsDataVO.setOrderDetailId(odId);//订单明细ID
                    //拒收数量
                    getReceiptGoodsDataVO.setReturnQty(BigDecimal.valueOf(odNumber).subtract(BigDecimal.valueOf(odSendNumber)));
                    BoundGoodsVO boundGoods = mphgoodsRelevanceMapper.selectBYGid(odGId, loginUser.getEnterpriseId());
                    if (boundGoods != null) {
                        getReceiptGoodsDataVO.setBoundGoods(boundGoods.getBoundGoods());//绑定商品
                        getReceiptGoodsDataVO.setGoodsId(boundGoods.getGoodsId());//saas对应商品ID

                        Goods goods = goodsMapper.selectByPrimaryKey(boundGoods.getGoodsId());
                        getReceiptGoodsDataVO.setGoods(goods);
                        getReceiptGoodsDataVO.setGoodsCode(goods.getCode());
                    }

                    //判断订单中的商品是不是新品
                    Integer newGoods = 0; //0：不是新品 1:是新品
                    newGoods = getGoodsStatus(newGoods,loginUser,odGId,odName,odManufacture,odSpecifications);
                    getReceiptGoodsDataVO.setNewGoods(newGoods);//是否为新品 0：不是新品 1:是新品
                    Long saasGoodsId = null;
                    if (boundGoods != null) {
                        saasGoodsId = boundGoods.getGoodsId();
                        // 商品默认税率
                        GoodsBusiness gdb = getGoodsRateInfo(saasGoodsId, loginUser);
                        getReceiptGoodsDataVO.setTaxRateId(gdb.getPurchaseTaxRateId());
                        BigDecimal notaxAmountByRealAmountAndTaxRate = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(BigDecimal.valueOf(odSendNumber).multiply(BigDecimal.valueOf(odPrice)), getTaxRate(gdb.getPurchaseTaxRateId()));
                        getReceiptGoodsDataVO.setNotaxRealAmount(notaxAmountByRealAmountAndTaxRate);//不含税金额
                        getReceiptGoodsDataVO.setNotaxRealPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmountByRealAmountAndTaxRate,BigDecimal.valueOf(odSendNumber)));//不含税单价
                    }else {
                        getReceiptGoodsDataVO.setTaxRateId(1L);
                        getReceiptGoodsDataVO.setNotaxRealPrice(BigDecimal.valueOf(odPrice));
                        getReceiptGoodsDataVO.setNotaxRealAmount(BigDecimal.valueOf(odSubtotal));
                    }

                    getReceiptGoodsDataVOS.add(getReceiptGoodsDataVO);

                    JSONArray batchList = jsonObject.getJSONArray("orderSendBatchList");
                    List<GetReceiptGoodsLotDataVO> getReceiptGoodsLotDataVO = new ArrayList<>();
                    GetReceiptGoodsLotDataVO goodsLotDataVO = null;
                    if (batchList != null) {
                        JSONObject ob = null;
                        for (Object object : batchList) {
                            // 商品对应MPH批次信息
                            goodsLotDataVO = new GetReceiptGoodsLotDataVO();
                            ob = (JSONObject) object;
                            @SuppressWarnings("unused")
                            String place = ob.get("produceAddress") == null ? "" : ob.get("produceAddress").toString(); //产地
                            String sendBatchNo = ob.getString("sendBatchNo") == null ? "" : ob.getString("sendBatchNo");//批号
                            String produceDate = ob.get("produceDate") == null ? "" : ob.get("produceDate").toString(); // 订单ID
                            String validPeriod = ob.get("validPeriod") == null ? "" : ob.get("validPeriod").toString(); // 订单ID
                            String sendNum = (ob.get("sendNum") == null ? "0" : ("".equals(ob.get("sendNum")) ? "0" : ob.get("sendNum").toString()));//收货数量
                            goodsLotDataVO.setLotNum(sendBatchNo);
                            goodsLotDataVO.setReceiveQuantity(new BigDecimal(sendNum));
                            goodsLotDataVO.setProductDate(StringUtils.isBlank(produceDate) ? null : DateUtils.StringToDate(produceDate, DateStyle.YYYY_MM_DD));
                            goodsLotDataVO.setValidDate(StringUtils.isBlank(validPeriod) ? null : DateUtils.StringToDate(validPeriod, DateStyle.YYYY_MM_DD));
                            if (boundGoods != null) {
                                goodsLotDataVO.setGoodsId(saasGoodsId);//saas对应商品ID
                                // 商品默认货位
                                SafetyStock sfs = getDefauleShelf(saasGoodsId, loginUser);
                                goodsLotDataVO.setShelfId(sfs.getDefaultShelfId());
                                goodsLotDataVO.setShelfName(sfs.getDefaultShelfName());
                            }
                            getReceiptGoodsLotDataVO.add(goodsLotDataVO);
                        }
                    } else {
                        if (boundGoods != null) {
                            goodsLotDataVO = new GetReceiptGoodsLotDataVO();
                            //saas对应商品ID
                            goodsLotDataVO.setGoodsId(saasGoodsId);
                            // 商品默认货位
                            SafetyStock sfs = getDefauleShelf(saasGoodsId, loginUser);
                            goodsLotDataVO.setShelfId(sfs.getDefaultShelfId());
                            goodsLotDataVO.setShelfName(sfs.getDefaultShelfName());
                            getReceiptGoodsLotDataVO.add(goodsLotDataVO);
                        }
                    }
                    getReceiptGoodsDataVO.setGetReceiptGoodsLotDataVO(getReceiptGoodsLotDataVO);
                }
                getReceiptDataVO.setGetReceiptGoodsDataVO(getReceiptGoodsDataVOS);
                getReceiptDataVO.setMphSupplyId(odSellerId);
                if(odSellerId != null){
                    SelectSupplierVO supplierVO = mphsupplierRelevanceMapper.selectByMPHSupplyId(odSellerId,loginUser.getEnterpriseId());
                    if(supplierVO != null){
                        getReceiptDataVO.setSupplyId(supplierVO.getId());
                        getReceiptDataVO.setSupplyName(supplierVO.getName());
                        getReceiptDataVO.setSupplyCode(supplierVO.getCode());
                        getReceiptDataVO.setSupplierSalerId(supplierVO.getSaleManId());
                        getReceiptDataVO.setSupplierSalerCode(supplierVO.getSaleManCode());
                        getReceiptDataVO.setSupplierSalerName(supplierVO.getSaleManName());
                        getReceiptDataVO.setSupplierSalerPhone(supplierVO.getSaleManPhone());
                    }
                }
            }
            /**
             * 将getReceiptDataVO保存到数据库
             */
            getReceiptDataVO  = insertIntoDataBase(getReceiptDataVO,loginUser);
        }

        return getReceiptDataVO;
    }

    /**
     * 将收货信息暂存数据库
     */
    private GetReceiptDataVO insertIntoDataBase(GetReceiptDataVO getReceiptDataVO, UserVO loginUser) throws Exception {
        /**
         * 插主表
         */
        MphorderReceiveTemsave mph = new MphorderReceiveTemsave();
        mph.setEnterpriseId(loginUser.getEnterpriseId());
        mph.setParentId(loginUser.getParentId());
        mph.setOrderId(getReceiptDataVO.getOrderId());
        mph.setOrderCode(getReceiptDataVO.getOrderCode());
        mph.setMphSupplierId(getReceiptDataVO.getMphSupplyId());
        mph.setMphSupplierName(getReceiptDataVO.getMphSupplyName());
        mph.setSupplierId(getReceiptDataVO.getSupplyId());
        mph.setSupplierName(getReceiptDataVO.getSupplyName());
        UserEnterpriseUtils.setUserCreateOrModify(mph,loginUser,true);
        mph.setPurchaseDate(getReceiptDataVO.getPurchaseDate());
        mph.setReceiveDate(getReceiptDataVO.getReceiveDate());
        mph.setCheckDate(getReceiptDataVO.getCheckDate());
        mph.setInDate(getReceiptDataVO.getInDate());
        User purchaseMan = userMapper.selectByPrimaryKey(getReceiptDataVO.getPurchaseManId());
        if (purchaseMan != null){
            mph.setPurchaseManId(purchaseMan.getId());
            mph.setPurchaseManCode(purchaseMan.getCode());
            mph.setPurchaseManName(purchaseMan.getName());
        }
        User receive = userMapper.selectByPrimaryKey(getReceiptDataVO.getReceiveManId());
        if (receive != null){
            mph.setReceiveManId(receive.getId());
            mph.setReceiveManCode(receive.getCode());
            mph.setReceiveManName(receive.getName());
        }
        User secondReceive = userMapper.selectByPrimaryKey(getReceiptDataVO.getSecondReceiveManId());
        if (secondReceive != null){
            mph.setSecondReceiveManId(secondReceive.getId());
            mph.setSecondReceiveManCode(secondReceive.getCode());
            mph.setSecondReceiveManName(secondReceive.getName());
        }
        User check = userMapper.selectByPrimaryKey(getReceiptDataVO.getCheckManId());
        if (check != null){
            mph.setCheckManId(check.getId());
            mph.setCheckManCode(check.getCode());
            mph.setCheckManName(check.getName());
        }
        User secondCheck = userMapper.selectByPrimaryKey(getReceiptDataVO.getSecondCheckManId());
        if (secondCheck != null){
            mph.setSecondCheckManId(secondCheck.getId());
            mph.setSecondCheckManCode(secondCheck.getCode());
            mph.setSecondCheckManName(secondCheck.getName());
        }
        User inMan = userMapper.selectByPrimaryKey(getReceiptDataVO.getInManId());
        if (inMan != null){
            mph.setInManId(inMan.getId());
            mph.setInManCode(inMan.getCode());
            mph.setInManName(inMan.getName());
        }
        mph.setFlowCode(getReceiptDataVO.getFlowCode());
        mphorderReceiveTemsaveMapper.insertSelective(mph);
        getReceiptDataVO.setId(mph.getId());
        /**
         * 插细表
         */
        List<GetReceiptGoodsDataVO> goodsList = getReceiptDataVO.getGetReceiptGoodsDataVO();
        if (goodsList != null && goodsList.size() > 0){
            goodsList.forEach(good ->{
                MphorderReceiveDetailTemsave detail = new MphorderReceiveDetailTemsave();
                detail.setEnterpriseId(loginUser.getEnterpriseId());
                detail.setParentId(loginUser.getParentId());
                detail.setReceiveTemsaveId(mph.getId());
                detail.setMphGoodsId(good.getMphGoodsId());
                detail.setMphGoodsName(good.getMphGoodsName());
                detail.setMphManufacturer(good.getMphGoodsManufacturer());
                detail.setMphSpecification(good.getSpecification());
                detail.setUnitPrice(good.getPurchasePrice());
                detail.setPurchaseQuantity(good.getPurchaseQty());
                detail.setSendQuantity(good.getDeliverQty());
                detail.setSendAmount(good.getDeliverAmount());
                detail.setGoodsId(good.getGoodsId());
                detail.setTaxRateId(good.getTaxRateId());
                detail.setTaxRate(getTaxRate(good.getTaxRateId()));
                detail.setOrderDetailId(good.getOrderDetailId());
                BigDecimal notaxAmountByRealAmountAndTaxRate = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(good.getDeliverQty().multiply(good.getPurchasePrice()), getTaxRate(good.getTaxRateId()));
                detail.setNotaxRealAmount(notaxAmountByRealAmountAndTaxRate);
                detail.setNotaxRealPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmountByRealAmountAndTaxRate,good.getDeliverQty()));
                if (good.getGoodsId() != null){
                    Goods goods = goodsMapper.selectByPrimaryKey(good.getGoodsId());
                    detail.setGoodsName(goods.getName());
                    detail.setManufacturerId(goods.getManufacturerId());
                    detail.setManufacturer(goods.getManufacturer());
                }
                try {
                    UserEnterpriseUtils.setUserCreateOrModify(detail,loginUser,true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mphorderReceiveDetailTemsaveMapper.insertSelective(detail);
                good.setId(detail.getId());
                List<GetReceiptGoodsLotDataVO> lotList = good.getGetReceiptGoodsLotDataVO();
                if (lotList != null && lotList.size() > 0){
                    lotList.forEach(lot ->{
                        MphorderReceiveShelfTemsave shelf = new MphorderReceiveShelfTemsave();
                        shelf.setEnterpriseId(loginUser.getEnterpriseId());
                        shelf.setParentId(loginUser.getParentId());
                        shelf.setReceiveTemsaveId(mph.getId());
                        shelf.setReceiveDetailTemsaveId(detail.getId());
                        shelf.setLotNumber(lot.getLotNum());
                        shelf.setProductDate(lot.getProductDate());
                        shelf.setValidDate(lot.getValidDate());
                        shelf.setReceiveQuantity(lot.getReceiveQuantity());
                        shelf.setShelfId(lot.getShelfId());
                        shelf.setShelfName(getShelfName(lot.getShelfId()));
                        shelf.setCheckProjectIds(lot.getCheckProjectIds());
                        shelf.setCheckResult(lot.getCheckResult());
                        try {
                            UserEnterpriseUtils.setUserCreateOrModify(shelf,loginUser,true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mphorderReceiveShelfTemsaveMapper.insertSelective(shelf);
                        lot.setId(shelf.getId());
                    });
                }
            });
        }
        return getReceiptDataVO;
    }

    private String getShelfName(Long shelfId) {
        WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(shelfId);
        if (warehouseShelf != null){
            return warehouseShelf.getName();
        }
        return "";
    }

    private Integer getGoodsStatus(Integer newGoods,UserVO loginUser, Long odGId, String odName, String odManufacture, String odSpecifications) {
        Integer saasGoodsByName = 0;//条件三
        Map<String, Object> map = new HashMap<>(5);
        if (loginUser.getChainType() != 0) {
            map.put("entrepriseId", loginUser.getParentId());
        } else {
            map.put("entrepriseId", loginUser.getEnterpriseId());
        }
        map.put("odGId", odGId);
        map.put("odName", odName);
        map.put("odManufacture", odManufacture);
        map.put("odSpecifications", odSpecifications);
        String goodsId = mphgoodsRelevanceMapper.querySaasMphgoodsRelevance(map);
        if (StringUtils.isBlank(goodsId)) {//查不到
            saasGoodsByName = mphgoodsRelevanceMapper.querySaasGoodsByName(map);
            if (saasGoodsByName > 0) {
                newGoods = 0;
            } else {
                newGoods = 1;
            }
        } else {//查到
            newGoods = 0;
        }
        return newGoods;
    }


    private String getSmartDisplaySupplierIdS(List<SupplierDisplayDetail> SupplierDisplayDetailS) {
//        List<Map<String,Object>> supplierIds = new ArrayList<>();
    	JSONArray ar = new JSONArray();
        if(SupplierDisplayDetailS != null){
            for(SupplierDisplayDetail supplierDisplayDetail : SupplierDisplayDetailS){
//                Map<String,Object> supplierId = new HashMap<>();
            	JSONObject ob = new JSONObject();
//                supplierId.put("standard_library_id", supplierDisplayDetail.getMphSupplierId());
//                supplierId.put("order_num", String.valueOf(supplierDisplayDetail.getPriority()));
//                supplierIds.add(supplierId);
            	ob.put("standard_library_id", supplierDisplayDetail.getMphSupplierId());
            	ob.put("order_num", supplierDisplayDetail.getPriority());
            	ar.add(ob);
            }
        }    
        return ar.toJSONString();
    }

    private Boolean checkRepeat(List<String> data, String dataTwo) {

        String dataS = "";
        for (int i = 0; i < data.size(); i++){
            dataS += data.get(i) + ",";
        }
        if(dataS.equals("") || dataS == null){
            return true;
        }
        int num = activityGoodsMapper.checkRepeat(dataS.substring(0,dataS.length()-1),dataTwo);
        if(num > 0){
            return false;
        }else
            return true;
    }

    @Autowired
    private RedisComponent redisComponent;
    @Override
    public void insertCart(UserVO loginUser, SelectSmartSupplierVO supplierVO) throws Exception {
        SelectSmartGoodsVO smartGoodsVO = supplierVO.getSelectSmartGoodsVO().get(0);
        if(smartGoodsVO.getCanSplit() == 0 && smartGoodsVO.getQuantity()%smartGoodsVO.getMiddlePackage() != 0){
            throw new BusinessException(smartGoodsVO.getGoodsName()+"的数量"+smartGoodsVO.getQuantity()+"必须是中包装数量"+smartGoodsVO.getMiddlePackage()+"的整数倍!");
        }
        SmartSourcingCartVO cartVO = (SmartSourcingCartVO)redisComponent.get(getRedisKey(loginUser));
        if(cartVO == null){
            List<SmartSourcingSupplierVO> supplierList = new ArrayList<>();
            List<SmartSourcingGoodsVO> goodsList = new ArrayList<SmartSourcingGoodsVO>();
            cartVO = new SmartSourcingCartVO();
            for(SelectSmartGoodsVO goodsVO : supplierVO.getSelectSmartGoodsVO()){
                SmartSourcingGoodsVO sourcingGoodsVO = new SmartSourcingGoodsVO();
                sourcingGoodsVO.setgId(goodsVO.getGoodsId());
                sourcingGoodsVO.setgManufacturer(goodsVO.getManufacturer());
                sourcingGoodsVO.setgName(goodsVO.getGoodsName());
                sourcingGoodsVO.setgSpecification(goodsVO.getSpecification());
                sourcingGoodsVO.setInventoryQuantity((int)goodsVO.getInventoryQuantity());
                sourcingGoodsVO.setgCanSplit(goodsVO.getCanSplit());
                sourcingGoodsVO.setgMiddlePackage(goodsVO.getMiddlePackage());
                sourcingGoodsVO.setEntrepriseId(loginUser.getEnterpriseId());
                sourcingGoodsVO.setCreaterId(loginUser.getUserId());
                sourcingGoodsVO.setCreaterName(loginUser.getUserName());
                sourcingGoodsVO.setAddCartTime(new Date());
                sourcingGoodsVO.setChecked(true);
                sourcingGoodsVO.setQuantity(goodsVO.getQuantity());
                sourcingGoodsVO.setRetailPrice(new BigDecimal(goodsVO.getPurchasePrice()));
                sourcingGoodsVO.setSubtotal(sourcingGoodsVO.getRetailPrice().multiply(new BigDecimal(sourcingGoodsVO.getQuantity())));
                goodsList.add(sourcingGoodsVO);
            }
            SmartSourcingSupplierVO smartSourcingSupplierVO = new SmartSourcingSupplierVO();
            smartSourcingSupplierVO.setGoodsList(goodsList);
            smartSourcingSupplierVO.setMphSupplierId(supplierVO.getMphSupplierId());
            smartSourcingSupplierVO.setMphSupplierName(supplierVO.getMphSupplierName());
            smartSourcingSupplierVO.setEntrepriseId(loginUser.getEnterpriseId());
            smartSourcingSupplierVO.setMatchingAmount(supplierVO.getMatchingAmount());
            smartSourcingSupplierVO.setChecked(true);
            supplierList.add(smartSourcingSupplierVO);
            cartVO.setSupplierList(supplierList);
        }else {
            List<SmartSourcingSupplierVO> supplierVOS = cartVO.getSupplierList();
            boolean supplierEqual = false;
            for(SmartSourcingSupplierVO sourcingSupplierVO : supplierVOS){
                if(sourcingSupplierVO.getMphSupplierId().equals(supplierVO.getMphSupplierId())){
                    supplierEqual = true;
                }
            }
            if(supplierEqual){//如果供应商相同
                for(SmartSourcingSupplierVO sourcingSupplierVO : supplierVOS){
                    if(sourcingSupplierVO.getMphSupplierId().equals(supplierVO.getMphSupplierId())) {
                        
                        List<SelectSmartGoodsVO> smartGoodsVOList = supplierVO.getSelectSmartGoodsVO();
                    	
                        Map<Long, SmartSourcingGoodsVO> cartGoodsData = new HashMap<Long, SmartSourcingGoodsVO>(); 
                        
                        for (SmartSourcingGoodsVO goodsVO : sourcingSupplierVO.getGoodsList()) {
                        	cartGoodsData.put(goodsVO.getgId(), goodsVO);
                		}
                        
                    	for (SelectSmartGoodsVO goodsVO : smartGoodsVOList) {
                    		if(!cartGoodsData.containsKey(goodsVO.getGoodsId()))	{
                    			SmartSourcingGoodsVO sourcingGoodsVO = new SmartSourcingGoodsVO();
                                sourcingGoodsVO.setgId(goodsVO.getGoodsId());
                                sourcingGoodsVO.setgManufacturer(goodsVO.getManufacturer());
                                sourcingGoodsVO.setgName(goodsVO.getGoodsName());
                                sourcingGoodsVO.setgSpecification(goodsVO.getSpecification());
                                sourcingGoodsVO.setInventoryQuantity((int)goodsVO.getInventoryQuantity());
                                sourcingGoodsVO.setgCanSplit(goodsVO.getCanSplit());
                                sourcingGoodsVO.setgMiddlePackage(goodsVO.getMiddlePackage());
                                sourcingGoodsVO.setEntrepriseId(loginUser.getEnterpriseId());
                                sourcingGoodsVO.setCreaterId(loginUser.getUserId());
                                sourcingGoodsVO.setCreaterName(loginUser.getUserName());
                                sourcingGoodsVO.setAddCartTime(new Date());
                                sourcingGoodsVO.setChecked(true);
                                sourcingGoodsVO.setQuantity(goodsVO.getQuantity());
                                sourcingGoodsVO.setRetailPrice(new BigDecimal(goodsVO.getPurchasePrice()));
                                sourcingGoodsVO.setSubtotal(sourcingGoodsVO.getRetailPrice().multiply(new BigDecimal(sourcingGoodsVO.getQuantity())));
                                List<SmartSourcingGoodsVO> godosList = sourcingSupplierVO.getGoodsList();
                                godosList.add(sourcingGoodsVO);
                                sourcingSupplierVO.setGoodsList(godosList);
                    		}else{
                    			
                    			SmartSourcingGoodsVO goods = cartGoodsData.get(goodsVO.getGoodsId());
                    			goods.setQuantity(goodsVO.getQuantity()+goods.getQuantity());
                    		}
						}
                        
                    }
                }
            }else {//如果供应商不同,则添加供应商
                List<SmartSourcingSupplierVO> supplierList = cartVO.getSupplierList();
                List<SmartSourcingGoodsVO> goodsList = new ArrayList<SmartSourcingGoodsVO>();
                for(SelectSmartGoodsVO goodsVO : supplierVO.getSelectSmartGoodsVO()){
                    SmartSourcingGoodsVO sourcingGoodsVO = new SmartSourcingGoodsVO();
                    sourcingGoodsVO.setgId(goodsVO.getGoodsId());
                    sourcingGoodsVO.setgManufacturer(goodsVO.getManufacturer());
                    sourcingGoodsVO.setgName(goodsVO.getGoodsName());
                    sourcingGoodsVO.setgSpecification(goodsVO.getSpecification());
                    sourcingGoodsVO.setInventoryQuantity((int)goodsVO.getInventoryQuantity());
                    sourcingGoodsVO.setgCanSplit(goodsVO.getCanSplit());
                    sourcingGoodsVO.setgMiddlePackage(goodsVO.getMiddlePackage());
                    sourcingGoodsVO.setEntrepriseId(loginUser.getEnterpriseId());
                    sourcingGoodsVO.setCreaterId(loginUser.getUserId());
                    sourcingGoodsVO.setCreaterName(loginUser.getUserName());
                    sourcingGoodsVO.setAddCartTime(new Date());
                    sourcingGoodsVO.setChecked(true);
                    sourcingGoodsVO.setQuantity(goodsVO.getQuantity());
                    sourcingGoodsVO.setRetailPrice(new BigDecimal(goodsVO.getPurchasePrice()));
                    sourcingGoodsVO.setSubtotal(sourcingGoodsVO.getRetailPrice().multiply(new BigDecimal(sourcingGoodsVO.getQuantity())));
                    goodsList.add(sourcingGoodsVO);
                }
                SmartSourcingSupplierVO smartSourcingSupplierVO = new SmartSourcingSupplierVO();
                smartSourcingSupplierVO.setGoodsList(goodsList);
                smartSourcingSupplierVO.setMphSupplierId(supplierVO.getMphSupplierId());
                smartSourcingSupplierVO.setMphSupplierName(supplierVO.getMphSupplierName());
                smartSourcingSupplierVO.setEntrepriseId(loginUser.getEnterpriseId());
                smartSourcingSupplierVO.setMatchingAmount(supplierVO.getMatchingAmount());
                smartSourcingSupplierVO.setChecked(true);
                supplierList.add(smartSourcingSupplierVO);
                cartVO.setSupplierList(supplierList);
            }
        }
        reCalculate(cartVO);
        redisComponent.set(getRedisKey(loginUser),cartVO);
    }

    private void reCalculate(SmartSourcingCartVO cartVO) {
        //商品已选择总数量
        Integer selectQuantity = new Integer(0);
        // 当前应付总价格
        BigDecimal totalAmount = BigDecimal.ZERO;
        for(SmartSourcingSupplierVO supplierVO : cartVO.getSupplierList()){
            BigDecimal billingAmount = new BigDecimal(0.00);
            for(SmartSourcingGoodsVO goodsVO : supplierVO.getGoodsList()){
                billingAmount = billingAmount.add(goodsVO.getRetailPrice().multiply(new BigDecimal(goodsVO.getQuantity())).setScale(2, RoundingMode.HALF_UP));
                selectQuantity += goodsVO.getQuantity();
            }
            totalAmount = totalAmount.add(billingAmount);
        }
    }

    @Override
    public String getRedisKey(UserVO loginUser) throws Exception {
        StringBuilder redisKey = new StringBuilder();
        redisKey.append(SmartCartConstant.SMART_PURCHASE_CART);
        redisKey.append(loginUser.getEnterpriseId());
        return redisKey.toString();
    }

    @Override
    public SelectSmartEntrepriseVO getOnlineMarketAll(UserVO loginUser, SearchMphSupplierGoodsResult searchMphSupplierGoodsResult, Long gcName1Type,Long gcName2Type,Long manufacturerType,Long dosageNamesType) throws Exception {
        SelectSmartEntrepriseVO selectSmartEntrepriseVO = new SelectSmartEntrepriseVO();
        selectSmartEntrepriseVO.setGcName1S(searchMphSupplierGoodsResult.getCategory1NameFacet());
        if(gcName1Type == 0L && selectSmartEntrepriseVO.getGcName1S().size()>5){
            List<String> gcName1S = selectSmartEntrepriseVO.getGcName1S().subList(0,5);
            selectSmartEntrepriseVO.setGcName1S(gcName1S);
        }
        selectSmartEntrepriseVO.setGcName2S(searchMphSupplierGoodsResult.getCategory2NameFacet());
        if(gcName2Type == 0L && selectSmartEntrepriseVO.getGcName2S().size()>5){
            List<String> gcName2S = selectSmartEntrepriseVO.getGcName2S().subList(0,5);
            selectSmartEntrepriseVO.setGcName2S(gcName2S);
        }
        selectSmartEntrepriseVO.setManufacturer(searchMphSupplierGoodsResult.getManufacturerFacet());
        if(manufacturerType == 0L && selectSmartEntrepriseVO.getManufacturer().size()>5){
            List<String> manufacturer = selectSmartEntrepriseVO.getManufacturer().subList(0,5);
            selectSmartEntrepriseVO.setManufacturer(manufacturer);
        }
        selectSmartEntrepriseVO.setDosageNames(searchMphSupplierGoodsResult.getDosageFormNameFacet());
        if(dosageNamesType == 0L && selectSmartEntrepriseVO.getDosageNames().size()>5){
            List<String> dosageNames = selectSmartEntrepriseVO.getDosageNames().subList(0,5);
            selectSmartEntrepriseVO.setDosageNames(dosageNames);
        }
        selectSmartEntrepriseVO.setTotalCount(searchMphSupplierGoodsResult.getTotalCount());
        selectSmartEntrepriseVO.setStart(searchMphSupplierGoodsResult.getStart());
        selectSmartEntrepriseVO.setRows(searchMphSupplierGoodsResult.getRows());
        List<SelectSmartSupplierVO> supplierVOS = new ArrayList<>();
        SelectSmartSupplierVO supplierVO = new SelectSmartSupplierVO();
        supplierVO.setIsMinPurchased(searchMphSupplierGoodsResult.getSupplier().getIsMinPurchased());
        if(supplierVO.getIsMinPurchased() == 0){
            supplierVO.setMatchingAmount(new BigDecimal(-1));
        }
        supplierVO.setMatchingAmount(new BigDecimal(searchMphSupplierGoodsResult.getSupplier().getMinPurchaseAmount()));
        supplierVO.setMphSupplierId(Long.valueOf(searchMphSupplierGoodsResult.getSupplier().getId()));
        supplierVO.setMphSupplierName(searchMphSupplierGoodsResult.getSupplier().getName());
        List<SelectSmartGoodsVO> goodsVOS = new ArrayList<>();
        for(MphGoods mphGoods : searchMphSupplierGoodsResult.getGoodsList()){
            SelectSmartGoodsVO selectSmartGoodsVO  = new SelectSmartGoodsVO();
            selectSmartGoodsVO.setBatchNo(mphGoods.getBatchNo());
            selectSmartGoodsVO.setCanPurchased(mphGoods.getCanPurchased());
            selectSmartGoodsVO.setCanSplit(mphGoods.getgCanSplit());
            selectSmartGoodsVO.setExpiryDate(mphGoods.getExpiryDate());
            selectSmartGoodsVO.setGcName1(mphGoods.getCategory1Name());
            selectSmartGoodsVO.setGcName2(mphGoods.getCategory2Name());
            selectSmartGoodsVO.setGoodsId(Long.valueOf(mphGoods.getId()));
            selectSmartGoodsVO.setGoodsName(mphGoods.getName());
            selectSmartGoodsVO.setInventoryQuantity(mphGoods.getInventoryQuantity());
            selectSmartGoodsVO.setManufacturer(mphGoods.getManufacturer());
            selectSmartGoodsVO.setMiddlePackage(mphGoods.getgMiddlePackage());
            selectSmartGoodsVO.setPictureAddress(mphGoods.getImage());
            selectSmartGoodsVO.setPromotionDescription(mphGoods.getPromotionDescription());
            selectSmartGoodsVO.setPromotionPrice(mphGoods.getPromotionPrice());
            selectSmartGoodsVO.setPromotionType(mphGoods.getPromotionType());
            selectSmartGoodsVO.setPurchasePrice(mphGoods.getPurchasePrice());
            selectSmartGoodsVO.setRetailPrice(BigDecimal.valueOf(mphGoods.getRetailPrice()));
            selectSmartGoodsVO.setSpecification(mphGoods.getSpecification());
            goodsVOS.add(selectSmartGoodsVO);
        }
        supplierVO.setSelectSmartGoodsVO(goodsVOS);
        supplierVOS.add(supplierVO);
        selectSmartEntrepriseVO.setSelectSmartSupplierVO(supplierVOS);
        return selectSmartEntrepriseVO;
    }

    @Override
    public Page boundGoods(Page page, UserVO loginUser, String mphGoodsName, String mphGoodsManufacturer, Long cargoAreaId) throws InterruptedException{

            Long enterpriseId = loginUser.getEnterpriseId();
            if(loginUser.getChainType() != 0){
                enterpriseId = loginUser.getParentId();
            }
            
            List<Long> ownerIdList = new ArrayList<Long>();
            if(loginUser.getChainType() == ChainType.Headquarters.getCode()){
            	ownerIdList.add(loginUser.getEnterpriseId());
            }else{
            	ownerIdList.add(loginUser.getEnterpriseId());
            	ownerIdList.add(loginUser.getParentId());
            }
            
            com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
            List<SelectBoundGoodsVO> selectBoundGoodsVO = mphgoodsRelevanceMapper.selectByMPHData(enterpriseId, ownerIdList, mphGoodsName, mphGoodsManufacturer, cargoAreaId);
            /*if(null != selectBoundGoodsVO && selectBoundGoodsVO.size() > 0) {
            	selectBoundGoodsVO.forEach(g->{
            		Long saasGoodsId = g.getGoodsId();
            		// 商品默认税率
					GoodsBusiness gdb = getGoodsRateInfo(saasGoodsId, loginUser);
					g.setTaxRate(gdb.getPurchaseTaxRate());
					g.setTaxRateID(gdb.getPurchaseTaxRateId());
					// 商品默认货位
					SafetyStock sfs = getDefauleShelf(saasGoodsId, loginUser);
					g.setShelfId(sfs.getDefaultShelfId());
					g.setShelfName(sfs.getDefaultShelfName());
            	});
            }*/
            List<Long> goodsIds = SelectBoundGoodsVO.getGoodsIds(selectBoundGoodsVO);
            
            if(goodsIds != null && goodsIds.size() > 0){
            
	            ExecutorService executorService = Executors.newFixedThreadPool(2);
	            Runnable syncRunnable = new Runnable(){
	                @Override
	                public void run() {
	                    Map<String,Object> map = new HashMap<>(2);
	                    Long enterpriseId = (loginUser.getChainType() == ChainType.Headquarters.getCode() ? loginUser.getEnterpriseId(): loginUser.getParentId());
	                    map.put("enterpriseId",enterpriseId);
	                    map.put("list",goodsIds);
	                    List<GoodsBusiness> business = businessMapper.selectByEnterpriseIdAndGoodsIds(map);
	                    for (SelectBoundGoodsVO o : selectBoundGoodsVO) {
	                        for(GoodsBusiness g : business){
	                            if(o.getGoodsId().equals(g.getGoodsId())){
	                                if (g.getPurchaseTaxRateId() == null){
	                                    Goods goods = goodsMapper.selectByPrimaryKey(g.getGoodsId());
	                                    throw new BusinessException(SysCode.FAIL.getCode(),goods.getName() + "没有设置商品的默认税率,请先设置!");
	                                }
	                                o.setTaxRate(g.getPurchaseTaxRate());
	                                o.setTaxRateID(g.getPurchaseTaxRateId());
	                            }
	                        }
	                    }
	                    System.out.println("子线程syncRunnable" + Thread.currentThread() + "执行完毕");
	                }
	            };
	            executorService.execute(syncRunnable);
	            Runnable syncRunnable1 = new Runnable(){
	
	                @Override
	                public void run() {
	                    List<SafetyStock> safetyStock = safetyStockMapper.selectByGoodsIdsAndEnterpriseId(goodsIds,loginUser.getEnterpriseId());
	                    for (SelectBoundGoodsVO o : selectBoundGoodsVO) {
	                        for(SafetyStock s : safetyStock){
	                            if(o.getGoodsId().equals(s.getGoodsId())){
	                                if (s.getDefaultShelfId() == null){
	                                    Goods goods = goodsMapper.selectByPrimaryKey(s.getGoodsId());
	                                    throw new BusinessException(goods.getName() + "未设置默认货位，请先设置默认货位\n!");
	                                }
	                                o.setShelfId(s.getDefaultShelfId());
	                                o.setShelfName(s.getDefaultShelfName());
	                            }
	                        }
	                    }
	                    System.out.println("子线程syncRunnable1" + Thread.currentThread() + "执行完毕");
	                }
	            };
	
	            executorService.execute(syncRunnable1);
	            // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
	            executorService.shutdown();
	            // awaitTermination返回false即超时会继续循环，返回true即线程池中的线程执行完成主线程跳出循环往下执行，每隔1秒循环一次
	            while(!executorService.awaitTermination(1, TimeUnit.SECONDS)){}
            }    
            page.setResult(selectBoundGoodsVO);
            page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
            page.setTotalPage(hPage.getPages());
            return page;
    }

    @Override
    public void unBoundGoods(UserVO loginUser, String mphGoodsId, Long goodsId) throws Exception {

        Long enterpriseId = loginUser.getEnterpriseId();
        MphgoodsRelevance mphgoodsRelevance = mphgoodsRelevanceMapper.selectByMPHGoodsIdAndGoodsId(enterpriseId, mphGoodsId, goodsId);
        if(mphgoodsRelevance != null){
            mphgoodsRelevanceMapper.deleteByPrimaryKey(mphgoodsRelevance.getId());
        }else{
            throw new BusinessException("没有查询到MPH商品与SAAS商品的关联绑定数据,无法解绑");
        }

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveBoundGoods(UserVO loginUser, SaveBoundGoodsVO saveBoundGoods) throws Exception {
        if (saveBoundGoods == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前无绑定商品信息,无法保存!");
        }
        MphgoodsRelevance mphgoodsRelevance = new MphgoodsRelevance();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveBoundGoods, mphgoodsRelevance);
        mphgoodsRelevance.setEnterpriseId(loginUser.getEnterpriseId());
        UserEnterpriseUtils.setUserCreateOrModify(mphgoodsRelevance, loginUser, true);

        int num = mphgoodsRelevanceMapper.selectByMPHGoodsId(mphgoodsRelevance.getMphGoodsId(),loginUser.getEnterpriseId());
        if(num > 0){
            throw new BusinessException("该MPH商品:"+mphgoodsRelevance.getMphGoodsName()+"已经与SAAS商品进行关联绑定,无法继续绑定!");
        }else{
            mphgoodsRelevanceMapper.insertSelective(mphgoodsRelevance);
        }

    }

    @Override
    public List<SelectBoundSupplyVO> boundSupply(UserVO loginUser, String mphSupplyName) {

        Long enterpriseId = loginUser.getEnterpriseId();
        if(loginUser.getChainType() == ChainType.Selfoperatedshop.getCode()){
            enterpriseId = loginUser.getParentId();
        }
        List<SelectBoundSupplyVO>  selectBoundSupplyVO = mphsupplierRelevanceMapper.selectByMPHSupplyName(enterpriseId, mphSupplyName);
        return selectBoundSupplyVO;
    }

    @Override
    public void unBoundSupply(UserVO loginUser, String mphSupplyId, Long supplyId) throws Exception{

        Long enterpriseId = loginUser.getEnterpriseId();
        MphsupplierRelevance mphsupplierRelevance = mphsupplierRelevanceMapper.selectByMPHSupplyIdForUnBoundSupply(enterpriseId,mphSupplyId,supplyId);
        if(mphsupplierRelevance != null){
            mphsupplierRelevanceMapper.deleteByPrimaryKey(mphsupplierRelevance.getId());
        }else{
            throw new BusinessException("没有查询到MPH供应商与SAAS供应商的关联绑定数据,无法解绑");
        }
    }

    @Override
    public void saveBoundSupply(UserVO loginUser, SaveBoundSupplyVO saveBoundSupply) throws Exception{

        MphsupplierRelevance mphsupplierRelevance = new MphsupplierRelevance();
        if (saveBoundSupply == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前关联信息,无法保存!");
        }
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveBoundSupply, mphsupplierRelevance);
        mphsupplierRelevance.setEnterpriseId(loginUser.getEnterpriseId());
        UserEnterpriseUtils.setUserCreateOrModify(mphsupplierRelevance, loginUser, true);
        int num = mphsupplierRelevanceMapper.selectByMPHSupplyIdForSave(mphsupplierRelevance.getMphSupplierId(),loginUser.getEnterpriseId());
        if(num > 0){
            throw new BusinessException("该MPH供应商已经与SAAS供应商进行关联绑定,无法继续绑定!");
        }else{
            mphsupplierRelevanceMapper.insertSelective(mphsupplierRelevance);
        }
    }

    @Override
    public void updateOrder(GetReceiptDataVO getReceiptDataVO, UserVO loginUser) throws Exception{
        /**
         * 1.第一层VO + 第二层VO update
         * 2.第三层VO delete + insert
         */
         if (getReceiptDataVO == null){
             throw new BusinessException(SysCode.FAIL.getCode(),"当前订单对象为空,无法进行暂存!");
         }
         MphorderReceiveTemsave mph = new MphorderReceiveTemsave();
         mph.setId(getReceiptDataVO.getId());
         mph.setMphSupplierId(getReceiptDataVO.getMphSupplyId());
         mph.setMphSupplierName(getReceiptDataVO.getMphSupplyName());
         mph.setSupplierId(getReceiptDataVO.getSupplyId());
         mph.setSupplierName(getReceiptDataVO.getSupplyName());
         UserEnterpriseUtils.setUserCreateOrModify(mph,loginUser,false);
         mph.setPurchaseDate(getReceiptDataVO.getPurchaseDate());
         mph.setReceiveDate(getReceiptDataVO.getReceiveDate());
         mph.setCheckDate(getReceiptDataVO.getReceiveDate());
         mph.setInDate(getReceiptDataVO.getInDate());
         User purchaseMan = userMapper.selectByPrimaryKey(getReceiptDataVO.getPurchaseManId());
         if (purchaseMan != null){
             mph.setPurchaseManId(purchaseMan.getId());
             mph.setPurchaseManCode(purchaseMan.getCode());
             mph.setPurchaseManName(purchaseMan.getName());
         }
         User receive = userMapper.selectByPrimaryKey(getReceiptDataVO.getReceiveManId());
         if (receive != null){
             mph.setReceiveManId(receive.getId());
             mph.setReceiveManCode(receive.getCode());
             mph.setReceiveManName(receive.getName());
         }
         User secondReceive = userMapper.selectByPrimaryKey(getReceiptDataVO.getSecondReceiveManId());
         if (secondReceive != null){
             mph.setSecondReceiveManId(secondReceive.getId());
             mph.setSecondReceiveManCode(secondReceive.getCode());
             mph.setSecondReceiveManName(secondReceive.getName());
         }
         User check = userMapper.selectByPrimaryKey(getReceiptDataVO.getCheckManId());
         if (check != null){
             mph.setCheckManId(check.getId());
             mph.setCheckManCode(check.getCode());
             mph.setCheckManName(check.getName());
         }
         User secondCheck = userMapper.selectByPrimaryKey(getReceiptDataVO.getSecondCheckManId());
         if (secondCheck != null){
             mph.setSecondCheckManId(secondCheck.getId());
             mph.setSecondCheckManCode(secondCheck.getCode());
             mph.setSecondCheckManName(secondCheck.getName());
         }
         User inMan = userMapper.selectByPrimaryKey(getReceiptDataVO.getInManId());
         if (inMan != null){
             mph.setInManId(inMan.getId());
             mph.setInManCode(inMan.getCode());
             mph.setInManName(inMan.getName());
         }
         mph.setFlowCode(getReceiptDataVO.getFlowCode());
         mphorderReceiveTemsaveMapper.updateByPrimaryKeySelective(mph);
        /**
         * 修改第二层VO
         */
          List<GetReceiptGoodsDataVO> goodsList = getReceiptDataVO.getGetReceiptGoodsDataVO();
         goodsList.forEach(good ->{
             MphorderReceiveDetailTemsave detail = new MphorderReceiveDetailTemsave();
             detail.setId(good.getId());
             detail.setSendQuantity(good.getDeliverQty());
             detail.setSendAmount(good.getDeliverQty().multiply(good.getPurchasePrice()));
             detail.setGoodsId(good.getGoodsId());
             detail.setTaxRateId(good.getTaxRateId());
             detail.setTaxRate(getTaxRate(good.getTaxRateId()));
             BigDecimal notaxAmountByRealAmountAndTaxRate = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(good.getDeliverQty().multiply(good.getPurchasePrice()), getTaxRate(good.getTaxRateId()));
             detail.setNotaxRealAmount(notaxAmountByRealAmountAndTaxRate);
             detail.setNotaxRealPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmountByRealAmountAndTaxRate,good.getDeliverQty()));
             if (good.getGoodsId() != null){
                 Goods goods = goodsMapper.selectByPrimaryKey(good.getGoodsId());
                 detail.setGoodsName(goods.getName());
                 detail.setManufacturerId(goods.getManufacturerId());
                 detail.setManufacturer(goods.getManufacturer());
             }
             try {
                 UserEnterpriseUtils.setUserCreateOrModify(detail,loginUser,false);
             } catch (Exception e) {
                 e.printStackTrace();
             }
             mphorderReceiveDetailTemsaveMapper.updateByPrimaryKeySelective(detail);
             List<GetReceiptGoodsLotDataVO> lotList = good.getGetReceiptGoodsLotDataVO();
             //先删除所有shelf数据再插入
             mphorderReceiveShelfTemsaveMapper.deleteByDtlId(detail.getId());
             lotList.forEach(lot ->{
                     /**
                      * 新增一条批号信息
                      */
                     MphorderReceiveShelfTemsave shelf = new MphorderReceiveShelfTemsave();
                     shelf.setEnterpriseId(loginUser.getEnterpriseId());
                     shelf.setParentId(loginUser.getParentId());
                     shelf.setReceiveTemsaveId(mph.getId());
                     shelf.setReceiveDetailTemsaveId(detail.getId());
                     shelf.setLotNumber(lot.getLotNum());
                     shelf.setProductDate(lot.getProductDate());
                     shelf.setValidDate(lot.getValidDate());
                     shelf.setReceiveQuantity(lot.getReceiveQuantity());
                     shelf.setShelfId(lot.getShelfId());
                     shelf.setShelfName(getShelfName(lot.getShelfId()));
                     shelf.setCheckProjectIds(lot.getCheckProjectIds());
                     shelf.setCheckResult(lot.getCheckResult());
                     try {
                         UserEnterpriseUtils.setUserCreateOrModify(shelf,loginUser,true);
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                     mphorderReceiveShelfTemsaveMapper.insertSelective(shelf);
             });
         });
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String reward(GetReceiptDataVO getReceiptDataVO, UserVO loginUser) throws Exception{
        /**
         * 先判断是否绑定saas商品和供应商[验证]
         */
        if (getReceiptDataVO == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前无收货信息!");
        }
        if (getReceiptDataVO.getSupplyId() == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前" + getReceiptDataVO.getMphSupplyName() + "还未绑定供应商,请先绑定!");
        }
        boolean specialDrug = false;//是否含有特殊管理药品
        List<GetReceiptGoodsDataVO> getReceiptGoodsDataVO = getReceiptDataVO.getGetReceiptGoodsDataVO();
        if (getReceiptGoodsDataVO != null){
            for (GetReceiptGoodsDataVO goodVO : getReceiptGoodsDataVO) {
                if (goodVO.getGoodsId() == null){
                    throw new BusinessException(SysCode.FAIL.getCode(),"当前" + goodVO.getMphGoodsName() + "还未绑定商品，请先绑定!");
                }else if(!specialDrug){
                	Goods goods = goodsMapper.selectByPrimaryKey(goodVO.getGoodsId());
                	if(goods == null){
                		throw new BusinessException(SysCode.FAIL.getCode(),"当前绑定商品ID" + goodVO.getGoodsId() + "找不到数据!");
                	}
                	goodVO.setGoods(goods);
                	if(goods.getInChargeDrug() != null && goods.getSpecialDrug() != -1){
                		specialDrug = true;
                	}
                }
                if(goodVO.getGoods() == null){
                	Goods goods = goodsMapper.selectByPrimaryKey(goodVO.getGoodsId());
                	if(goods == null){
                		throw new BusinessException(SysCode.FAIL.getCode(),"当前绑定的商品ID" + goodVO.getGoodsId() + "查不到数据!");
                	}
                	goodVO.setGoods(goods);
                }
            }
        }

        validateData(getReceiptDataVO, specialDrug);

        //判断总部是否有默认人员
        ManageConfig config = null;
        if(loginUser.getParentId() != null && loginUser.getParentId() != 0){//门店用户
        	config =  getConfigInfo(loginUser.getParentId());
        	
        	validateManData(config, specialDrug);
        }
        /**
         * 生成（分店收货情况）总部收货只有前四个单子
         * 1.总部采购订单
         * 2.总部采购收货
         * 3.总部采购验收
         * 4.总部采购入库
         * 5.总部配货单
         * 6.总部配货出库
         * 7.门店配进订单
         * 8.门店配进收货
         * 9.门店配进验收
         * 10.门店配进入库
         */
        
        StringBuilder sb = new StringBuilder();
        
        UserVO userVO = getUserVO(loginUser, config);
        PurchaseOrder order = generatePurchaseOrder(getReceiptDataVO,config,userVO, loginUser);
        PurchaseReceive receive = generatePurchaseReceive(getReceiptDataVO, order, config,userVO,loginUser);
        PurchaseCheck check = generatePurchaseCheck(order, receive,config,getReceiptDataVO,userVO, loginUser);
        PurchaseInStorage storage = generatePurchaseIn(receive,check,config,getReceiptDataVO,userVO, loginUser);
        
        sb.append("已经成功生成采购订单：").append(order.getCode()).append(",").append("已经成功生成采购收货：").append(receive.getCode()).append(",")
        	.append("已经成功生成采购验收：").append(check.getCode()).append(",").append("已经成功生成采购入库：").append(storage.getCode());
        
        if (loginUser.getChainType() != ChainType.Headquarters.getCode()){
            DistrSend send = generateDistrSend(getReceiptDataVO,userVO,loginUser,storage);
            PickOrder pick = generateDistrPick(getReceiptDataVO,userVO,loginUser,send);
            DistrOut out = generateDistrOut(getReceiptDataVO,send,storage,userVO,loginUser);
            DistrInNotice inNotice = generateDistrOrder(out,loginUser,getReceiptDataVO);
            DistrInReceive inReceive = generateDistrReceive(inNotice,loginUser,getReceiptDataVO);
            DistrInCheck inCheck = generateDistrCheck(inReceive,check,loginUser,getReceiptDataVO);
            DistrIn distrIn = generateDistrIn(inCheck,storage,inNotice,getReceiptDataVO,loginUser);
            
            sb.append(",").append("已经成功生成总部配货单：").append(send.getCode())
            .append(",").append("已经成功生成总部拣货单：").append(pick.getCode())
            .append(",").append("已经成功生成总部配货出库：").append(out.getCode())
            .append(",").append("已经成功生成门店配进订单：").append(inNotice.getCode())
            .append(",").append("已经成功生成门店配进收货：").append(inReceive.getCode())
            .append(",").append("已经成功生成门店配进验收：").append(inCheck.getCode())
            .append(",").append("已经成功生成门店配进入库：").append(distrIn.getCode());
        }
        
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		try {
			fixedThread.execute(() -> {
				acceptOrder(getReceiptDataVO, loginUser);
		        deleteTemSaveData(getReceiptDataVO.getId());
			});
		} catch (Exception e) {
			logger.error("确认收货生成MPH确认收货及删除暂存数据异常！", e);
			e.printStackTrace();
		}
		/*finally{
			int waitTime = 500;  
			Thread.sleep(waitTime);
			fixedThread.shutdown();
			fixedThread.awaitTermination(waitTime, TimeUnit.MILLISECONDS);
		}*/
        
        return sb.toString();
    }



	private PickOrder generateDistrPick(GetReceiptDataVO getReceiptDataVO,
			UserVO userVO, UserVO loginUser, DistrSend send) throws Exception{
		if(send == null){
			throw new InventoryBizException("","配货单ID错误，查不到配货单信息！");
		}
		if(send.getEnterpriseId() == null || !send.getEnterpriseId().equals(userVO.getEnterpriseId())){
			throw new InventoryBizException("","当前用户无操作此配货单权限！");
		}
		ManageConfig manageConfig = configMapper.getMangeConfigByEPId(userVO.getEnterpriseId());
		DistrSendOperationVO vo = new DistrSendOperationVO();
		vo.setId(send.getId());
		vo.setPickDate(send.getDistrDate());
		vo.setPickManId(manageConfig.getInOutManId());
		vo.setPickManName(manageConfig.getInOutManName());
		vo.setRequestUnitCode(send.getRequestUnitCode());
		vo.setRequestUnitName(send.getRequestUnitName());
		vo.setDistrCode(send.getCode());
		
		List<DistrSendDetail> dtlList = distrSendDetailMapper.listDistrSendDetailList(send.getId());
		if(dtlList != null){
			List<DistrSendOperationDtlVO> dtlViewList = new ArrayList<DistrSendOperationDtlVO>();
			DistrSendOperationDtlVO dtlVO = null;
			BigDecimal quantityTotal = BigDecimal.ZERO;
			for (int i=0; i<dtlList.size(); i++) {
				DistrSendDetail dtl = dtlList.get(i);
				dtlVO = new DistrSendOperationDtlVO();
				dtlVO.setSendDetailId(dtl.getId());
				dtlVO.setGoodsId(dtl.getGoodsId());
				dtlVO.setGoodsCode(dtl.getGoodsCode());
				dtlVO.setGoodsName(dtl.getGoodsName());
				dtlVO.setGoodsGenericName(dtl.getGoodsGenericName());
				dtlVO.setDosageId(dtl.getDosageId());
				dtlVO.setDosageName(dtl.getDosageName());
				dtlVO.setUnitId(dtl.getUnitId());
				dtlVO.setUnitName(dtl.getUnitName());
				dtlVO.setGoodsSpecification(dtl.getGoodsSpecification());
				dtlVO.setManufacturerId(dtl.getManufacturerId());
				dtlVO.setManufacturer(dtl.getManufacturer());
				dtlVO.setGoodsPlace(dtl.getGoodsPlace());
				dtlVO.setQuantity(dtl.getQuantity());
				dtlVO.setRemark(dtl.getRemark());
				dtlVO.setLineNum(i+1);
				
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("enterpriseId", send.getEnterpriseId());
				paramMap.put("baseOrderId", send.getId());
				paramMap.put("baseOrderType", send.getOrderType());
				paramMap.put("goodsId", dtl.getGoodsId());
				List<StockLockRecordVO> lockRecordList = stockLockRecordMapper.selectLockVOByParamMap(paramMap);
				if(lockRecordList != null){
					List<DistrSendOperationSlfVO> shelfList = new ArrayList<DistrSendOperationSlfVO>();
					DistrSendOperationSlfVO slfVO = null;
					for (int j=0; j<lockRecordList.size(); j++) {
						StockLockRecordVO lockRecord = lockRecordList.get(j);
						slfVO = new DistrSendOperationSlfVO();
						
						setSlfVOData(slfVO, lockRecord);
						slfVO.setLineNum(j);
						
						shelfList.add(slfVO);
					}
					dtlVO.setShelfList(shelfList);
				}
				
				quantityTotal = quantityTotal.add(dtl.getQuantity());
				dtlViewList.add(dtlVO);
			}
			vo.setDtlList(dtlViewList);
			vo.setQuantityTotal(quantityTotal);
		}
		distrPickService.saveDistrPick(vo, userVO);
		List<PickOrder> order = pickOrderMapper.selectByBaseOrderIdAndType(send.getId(),send.getOrderType(), send.getEnterpriseId());
		return (order!=null&&order.size()>0)?order.get(0):null;
	}



	private void deleteTemSaveData(Long id) {
    	if(id != null && id != 0){
    		mphorderReceiveTemsaveMapper.deleteByPrimaryKey(id);
    		mphorderReceiveDetailTemsaveMapper.deleteByTemsaveId(id);
    		mphorderReceiveShelfTemsaveMapper.deleteByTemsaveId(id);
    	}
	}



	private void acceptOrder(GetReceiptDataVO getReceiptDataVO, UserVO loginUser) {

    	List<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
    	
    	List<GetReceiptGoodsDataVO> goodsData = getReceiptDataVO.getGetReceiptGoodsDataVO();
    	OrderDetail detail = null;
    	for (GetReceiptGoodsDataVO getReceiptGoodsDataVO : goodsData) {
			detail = new OrderDetail();
			detail.setOrderDetailId(getReceiptGoodsDataVO.getOrderDetailId());
			detail.setMphGoodsId(getReceiptGoodsDataVO.getMphGoodsId());
			detail.setReceiveQuantity(getReceiptGoodsDataVO.getDeliverQty().doubleValue());
			orderDetail.add(detail);
		}
    	acceptSaasOrder.acceptSaasOrder(loginUser.getRgtUserId().longValue(), loginUser.getRgtEnterpriseId().longValue(), 
    			getReceiptDataVO.getOrderId(), getReceiptDataVO.getMphSupplyId(), getReceiptDataVO.getOrderCode(), orderDetail, loginUser.getEnterpriseId());
	}
	
	private void setSlfVOData(DistrSendOperationSlfVO slfVO,
			StockLockRecordVO lockRecord) {
		slfVO.setSendSlfId(lockRecord.getStockLockRecordId());
		slfVO.setLotId(lockRecord.getLotId());
		slfVO.setLotNumber(lockRecord.getLotNum());
		slfVO.setProductDate(lockRecord.getProductDate());
		slfVO.setValidDate(lockRecord.getValidDate());
		slfVO.setShelfId(lockRecord.getShelfId());
		slfVO.setShelfName(lockRecord.getShelfName());
		slfVO.setShelfStatusDesc(lockRecord.getShelfStatusDesc());
		slfVO.setQuantity(lockRecord.getUsableQuantity());
		
	}


	private void validateManData(ManageConfig config, boolean specialDrug) {
		if(config.getPurchaserId() == null || config.getPurchaserId() == 0){
    		throw new BusinessException(SysCode.FAIL.getCode(),"总部无默认采购人员!");
    	}
		if(config.getReceiverId() == null || config.getReceiverId() == 0){
    		throw new BusinessException(SysCode.FAIL.getCode(),"总部无默认收货人员!");
    	}
		if(config.getCheckerId() == null || config.getCheckerId() == 0){
    		throw new BusinessException(SysCode.FAIL.getCode(),"总部无默认验收人员!");
    	}
		if(config.getInOutManId() == null || config.getInOutManId() == 0){
    		throw new BusinessException(SysCode.FAIL.getCode(),"总部无默认入库人员!");
    	}
		
		if(specialDrug){
        	if(config.getSecondReceiverId() == null || config.getSecondReceiverId() == 0){
        		throw new BusinessException(SysCode.FAIL.getCode(),"当前含特殊管理药品，总部无默认第二收货人员!");
        	}
        	if(config.getSecondCheckerId() == null || config.getSecondCheckerId() == 0){
        		throw new BusinessException(SysCode.FAIL.getCode(),"当前含特殊管理药品，总部无默认第二验收人员!");
        	}
        }
		
	}
	

	private void validateData(GetReceiptDataVO getReceiptDataVO,
			boolean specialDrug) {

    	if(getReceiptDataVO.getPurchaseManId() == null || getReceiptDataVO.getPurchaseManId() == 0){
    		throw new BusinessException(SysCode.FAIL.getCode(),"采购人员为必填项!");
    	}
    	if(getReceiptDataVO.getReceiveManId() == null || getReceiptDataVO.getReceiveManId() == 0){
    		throw new BusinessException(SysCode.FAIL.getCode(),"收货人员为必填项!");
    	}
    	if(getReceiptDataVO.getCheckManId() == null || getReceiptDataVO.getCheckManId() == 0){
    		throw new BusinessException(SysCode.FAIL.getCode(),"验收人员为必填项!");
    	}
    	if(getReceiptDataVO.getInManId() == null || getReceiptDataVO.getInManId() == 0){
    		throw new BusinessException(SysCode.FAIL.getCode(),"入库人员为必填项!");
    	}
        if(specialDrug){
        	if(getReceiptDataVO.getSecondReceiveManId() == null || getReceiptDataVO.getSecondReceiveManId() == 0){
        		throw new BusinessException(SysCode.FAIL.getCode(),"当前含特殊管理药品，收货人员2为必填项!");
        	}
        	if(getReceiptDataVO.getSecondCheckManId() == null || getReceiptDataVO.getSecondCheckManId() == 0){
        		throw new BusinessException(SysCode.FAIL.getCode(),"当前含特殊管理药品，验收人员2为必填项!");
        	}
        }
        if(getReceiptDataVO.getPurchaseDate() == null){
    		throw new BusinessException(SysCode.FAIL.getCode(),"采购日期为必填项!");
    	}
        if(getReceiptDataVO.getReceiveDate() == null){
    		throw new BusinessException(SysCode.FAIL.getCode(),"收货日期为必填项!");
    	}
        if(getReceiptDataVO.getCheckDate() == null){
    		throw new BusinessException(SysCode.FAIL.getCode(),"验收日期为必填项!");
    	}
        if(getReceiptDataVO.getInDate() == null){
    		throw new BusinessException(SysCode.FAIL.getCode(),"入库日期为必填项!");
    	}
        if(getReceiptDataVO.getGetReceiptGoodsDataVO() == null || getReceiptDataVO.getGetReceiptGoodsDataVO().isEmpty()){
        	throw new BusinessException(SysCode.FAIL.getCode(),"入库商品不能为空!");
        }
        
        if(DateUtils.diffInDays(getReceiptDataVO.getPurchaseDate(),getReceiptDataVO.getReceiveDate()).longValue()<0) {
        	throw new BusinessException("采购日期不能大于收货日期!");
        }
        if(DateUtils.diffInDays(getReceiptDataVO.getReceiveDate(),getReceiptDataVO.getCheckDate())<0) {
        	throw new BusinessException("收货日期不能大于验收日期!");
        }
        if(DateUtils.diffInDays(getReceiptDataVO.getCheckDate(),getReceiptDataVO.getInDate())<0) {
        	throw new BusinessException("验收日期不能大于入库日期!");
        }
        
        for (GetReceiptGoodsDataVO goodsData : getReceiptDataVO.getGetReceiptGoodsDataVO()) {
        	if(goodsData.getDeliverQty() == null || goodsData.getDeliverQty().compareTo(BigDecimal.ZERO) <= 0){
        		throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"商品收货数量必须大于0!");
        	}
        	if(goodsData.getTaxRateId() == null){
        		throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"商品税率为必填项!");
        	}
        	if(goodsData.getGetReceiptGoodsLotDataVO() != null || !goodsData.getGetReceiptGoodsLotDataVO().isEmpty()){
        		for (GetReceiptGoodsLotDataVO lotvo : goodsData.getGetReceiptGoodsLotDataVO()) {
        			if(StringUtils.isBlank(lotvo.getLotNum())){
        				throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"商品批号为必填项!");
        			}
        			if(lotvo.getProductDate()==null){
        				throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"商品生产日期为必填项!");
        			}
        			if(lotvo.getValidDate()==null){
        				throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"商品有效期为必填项!");
        			}
        			if(lotvo.getReceiveQuantity() == null || lotvo.getReceiveQuantity().compareTo(BigDecimal.ZERO) <= 0){
                		throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"-"+lotvo.getLotNum()+"商品收货数量必须大于0!");
                	}
        			if(lotvo.getShelfId() == null || lotvo.getShelfId().equals(0L)){
                		throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"-"+lotvo.getLotNum()+"商品货位为必填项!");
                	}
                    if(StringUtils.isBlank(lotvo.getCheckProjectIds())){
                        throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"-"+lotvo.getLotNum()+"检验项目为必填项!");
                    }
                    if(lotvo.getCheckResult() == null || lotvo.getCheckResult().equals(0L)){
                        throw new BusinessException(SysCode.FAIL.getCode(), goodsData.getMphGoodsName()+"-"+lotvo.getLotNum()+"验收结论为必填项!");
                    }
				}
        	}
        }
	}



	//门店配进入库
    private DistrIn generateDistrIn(DistrInCheck inCheck, PurchaseInStorage storage, DistrInNotice inNotice,
    		GetReceiptDataVO getReceiptDataVO, UserVO loginUser) throws Exception {
        DistrInstorageFormVO  distrInstorageFormVO = new DistrInstorageFormVO();

        distrInstorageFormVO.setCheckId(inCheck.getId());
        distrInstorageFormVO.setDistrUnitId(inCheck.getDistrUnitId());
        distrInstorageFormVO.setDistrUnitCode(inCheck.getDistrUnitCode());
        distrInstorageFormVO.setDistrUnitName(inCheck.getDistrUnitName());
        distrInstorageFormVO.setDistrType(inCheck.getDistrType());
//        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser.getEnterpriseId());
//        if(manageConfig.getReceiverId() == null){
//            throw new BusinessException("生成配进入库时没有取到门店的默认入库人ID信息");
//        }
        distrInstorageFormVO.setStorageManId(getReceiptDataVO.getInManId());
//        if(manageConfig.getReceiverCode() == null){
//            throw new BusinessException("生成配进入库时没有取到门店的默认入库人编码信息");
//        }
//        distrInstorageFormVO.setStorageManCode(manageConfig.getInOutManCode());
//        if(manageConfig.getReceiverName() == null){
//            throw new BusinessException("生成配进入库时没有取到门店的默认入库人名字信息");
//        }
//        distrInstorageFormVO.setStorageManName(manageConfig.getInOutManName());
        distrInstorageFormVO.setQuantityTotal(inCheck.getQuantityTotal());
        distrInstorageFormVO.setVarietiesQuantity(inCheck.getVarietiesQuantity());
        distrInstorageFormVO.setAmountTotal(inCheck.getQuantityTotal());
        distrInstorageFormVO.setWholeDiscount(BigDecimal.valueOf(100));
        distrInstorageFormVO.setWholeDiscountAmount(BigDecimal.ZERO);
        distrInstorageFormVO.setRealAmountTotal(inCheck.getQuantityTotal());
        distrInstorageFormVO.setNotaxRealAmountTotal(storage.getNotaxRealAmountTotal());
        distrInstorageFormVO.setTaxAmountTotal(storage.getTaxAmountTotal());

        List<DistrInstorageDtlVO> distrInstorageDtlVOS = new ArrayList<>();
        List<DistrInCheckDetail> distrInCheckDetails = distrInCheckDetailMapper.selectByDtlId(loginUser.getEnterpriseId(),inCheck.getId());
        for(DistrInCheckDetail distrInCheckDetail : distrInCheckDetails){

            DistrInstorageDtlVO distrInstorageDtlVO = new DistrInstorageDtlVO();
            distrInstorageDtlVO.setBaseOrderDtlId(distrInCheckDetail.getId());
            distrInstorageDtlVO.setGoodsId(distrInCheckDetail.getGoodsId());
            distrInstorageDtlVO.setQuantity(distrInCheckDetail.getQualifiedQuantity());
            DistrInNoticeDetail distrInNoticeDetail = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseIdAndNoticeDtlId(loginUser.getEnterpriseId(),inNotice.getId(),distrInCheckDetail.getNoticeDtlId());
            distrInstorageDtlVO.setUnitPrice(distrInNoticeDetail.getUnitPrice());
            distrInstorageDtlVO.setRetailPrice(distrInNoticeDetail.getRetailPrice());
            distrInstorageDtlVO.setMemberPrice(distrInNoticeDetail.getMemberPrice());
            distrInstorageDtlVO.setLineDiscount(BigDecimal.valueOf(100));
            distrInstorageDtlVO.setAmount(distrInNoticeDetail.getAmount());
            distrInstorageDtlVO.setTaxRateId(distrInNoticeDetail.getTaxRateId());
            distrInstorageDtlVO.setTaxRate(distrInNoticeDetail.getTaxRate());
            distrInstorageDtlVO.setLineNum(distrInNoticeDetail.getLineNum());

            List<PurchaseInStorageDetail> detailList = purchaseInStorageDetailMapper.selectByInStorageId(storage.getId());
            
            if(detailList != null){
            	for (PurchaseInStorageDetail purchaseInStorageDetail : detailList) {
					if(distrInCheckDetail.getGoodsId().equals(purchaseInStorageDetail.getGoodsId()) &&
							distrInCheckDetail.getLineNum().equals(purchaseInStorageDetail.getLineNum())){
						
						List<DistrInStorageShelfDtlVO> distrInCheckLot2DetailVO = new ArrayList<>();
						List<PurchaseInStorageShelf> shelfList = purchaseInStorageShelfMapper.selectByEnterpriseIdAndId(purchaseInStorageDetail.getEnterpriseId(), purchaseInStorageDetail.getId());
						
						for (PurchaseInStorageShelf shelf : shelfList) {
							
							DistrInStorageShelfDtlVO distrInStorageShelfDtlVO = new DistrInStorageShelfDtlVO();
							distrInStorageShelfDtlVO.setLotNumber(shelf.getLotNumber());
							distrInStorageShelfDtlVO.setProductDate(shelf.getProductDate());
							distrInStorageShelfDtlVO.setValidDate(shelf.getValidDate());
							distrInStorageShelfDtlVO.setShelfId(shelf.getShelfId());
							distrInStorageShelfDtlVO.setQuantity(shelf.getQuantity());
							distrInStorageShelfDtlVO.setLineNum(shelf.getLineNum());
							distrInCheckLot2DetailVO.add(distrInStorageShelfDtlVO);
						}
						distrInstorageDtlVO.setDistrInStorageShelfDtlVOList(distrInCheckLot2DetailVO);
					}	
				}
            }
            
            /*List<GetReceiptGoodsDataVO> getReceiptGoodsDataVOS = getReceiptDataVO.getGetReceiptGoodsDataVO();
            for(GetReceiptGoodsDataVO getReceiptGoodsDataVO : getReceiptGoodsDataVOS){
                if(getReceiptGoodsDataVO.getGoodsId() == distrInCheckDetail.getGoodsId()){
//                    List<DistrInCheckLot> distrInCheckLots = distrInCheckLotMapper.selectByDtlId(distrInCheckDetail.getId());
                    List<GetReceiptGoodsLotDataVO> getReceiptGoodsLotDataVOS = getReceiptGoodsDataVO.getGetReceiptGoodsLotDataVO();
                    for(GetReceiptGoodsLotDataVO getReceiptGoodsLotDataVO : getReceiptGoodsLotDataVOS){

                        DistrInStorageShelfDtlVO distrInStorageShelfDtlVO = new DistrInStorageShelfDtlVO();
                        distrInStorageShelfDtlVO.setLotNumber(getReceiptGoodsLotDataVO.getLotNum());
                        distrInStorageShelfDtlVO.setProductDate(getReceiptGoodsLotDataVO.getProductDate());
                        distrInStorageShelfDtlVO.setValidDate(getReceiptGoodsLotDataVO.getValidDate());
//                        SafetyStock safetyStock = getDefauleShelf(distrInstorageDtlVO.getGoodsId(),loginUser);
                        distrInStorageShelfDtlVO.setShelfId(getReceiptGoodsLotDataVO.getShelfId());
                        distrInStorageShelfDtlVO.setQuantity(getReceiptGoodsLotDataVO.getReceiveQuantity());
                        distrInStorageShelfDtlVO.setLineNum(i);
                        distrInCheckLot2DetailVO.add(distrInStorageShelfDtlVO);
                    }
                    distrInstorageDtlVO.setDistrInStorageShelfDtlVOList(distrInCheckLot2DetailVO);
                }
            }
            i++;*/
            distrInstorageDtlVOS.add(distrInstorageDtlVO);
        }
        distrInstorageFormVO.setDistrInstorageDtlList(distrInstorageDtlVOS);
        distrInStorageService.saveStayInstorageForm(distrInstorageFormVO, loginUser);
        DistrIn distrIn = distrInMapper.selectByBaseOrderId(loginUser.getEnterpriseId(),inCheck.getId());

        return distrIn;
    }

    //门店配进验收
    private DistrInCheck generateDistrCheck(DistrInReceive inReceive, PurchaseCheck purchaseCheck, 
    		UserVO loginUser, GetReceiptDataVO getReceiptDataVO) throws Exception {

        DistrInCheck2DetailVO inCheck = new DistrInCheck2DetailVO();
        inCheck.setId(inReceive.getId());
        inCheck.setCheckDate(new Date());
        inCheck.setNoticeId(inReceive.getBaseOrderId());
        inCheck.setDistrUnitId(inReceive.getDistrUnitId());
        inCheck.setDistrUnitCode(inReceive.getDistrUnitCode());
        inCheck.setDistrUnitName(inReceive.getDistrUnitName());
        inCheck.setDistrType(inReceive.getDistrType());
//        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser.getEnterpriseId());
//        if(manageConfig.getReceiverId() == null){
//            throw new BusinessException("生成配进验收时没有取到门店的默认验收人ID信息");
//        }
        inCheck.setCheckerId(getReceiptDataVO.getCheckManId());
//        if(manageConfig.getReceiverName() == null){
//            throw new BusinessException("生成配进验收时没有取到门店的默认验收人名字信息");
//        }
//        inCheck.setCheckerName(manageConfig.getCheckerName());
//        if(manageConfig.getSecondReceiverId() == null){
//            throw new BusinessException("生成配进验收时没有取到门店的默认第二验收人ID信息");
//        }
        inCheck.setSecondCheckerId(getReceiptDataVO.getSecondCheckManId());
//        if(manageConfig.getSecondReceiverName() == null){
//            throw new BusinessException("生成配进验收时没有取到门店的默认第二验收人名字信息");
//        }
//        inCheck.setSecondCheckerName(manageConfig.getSecondCheckerName());

        List<DistrInReceiveDetailVO> distrInReceiveDetailVOS = distrInReceiveDetailMapper.selectByReceiveIdByEnterpriseId(inReceive.getId(),loginUser.getEnterpriseId());
        List<DistrInCheckDetail2DetailVO> distrInCheckDetail2DetailVOS = new ArrayList<>();
        for(DistrInReceiveDetailVO distrInReceiveDetailVO : distrInReceiveDetailVOS){

            DistrInCheckDetail2DetailVO distrInCheckDetail2DetailVO = new DistrInCheckDetail2DetailVO();
            distrInCheckDetail2DetailVO.setId(distrInReceiveDetailVO.getId());
            distrInCheckDetail2DetailVO.setGoodsId(distrInReceiveDetailVO.getGoodsId());
            distrInCheckDetail2DetailVO.setLineNum(distrInReceiveDetailVO.getLineNum());
            distrInCheckDetail2DetailVO.setReceiveQuantity(distrInReceiveDetailVO.getReceiveQuantity());

            List<PurchaseCheckDetail> purchaseCheckDetails = purchaseCheckDetailMapper.selectByCheckId(purchaseCheck.getId());
            List<DistrInCheckLot2DetailVO> distrInCheckLot2DetailVOS = new ArrayList<>();
            for(PurchaseCheckDetail purchaseCheckDetail : purchaseCheckDetails){
                if(distrInReceiveDetailVO.getGoodsId().equals(purchaseCheckDetail.getGoodsId()) &&
                		distrInReceiveDetailVO.getLineNum().equals(purchaseCheckDetail.getLineNum())){
                    List<PurchaseCheckLot> purchaseCheckLots = purchaseCheckLotMapper.selectByDtlId(purchaseCheckDetail.getId());
                    for(PurchaseCheckLot purchaseCheckLot : purchaseCheckLots){

                        DistrInCheckLot2DetailVO distrInCheckLot2DetailVO = new DistrInCheckLot2DetailVO();
                        distrInCheckLot2DetailVO.setLotNumber(purchaseCheckLot.getLotNumber());
                        distrInCheckLot2DetailVO.setProductDate(purchaseCheckLot.getProductDate());
                        distrInCheckLot2DetailVO.setValidDate(purchaseCheckLot.getValidDate());
                        distrInCheckLot2DetailVO.setTestReportIds(purchaseCheckLot.getTestReportIds());
                        distrInCheckLot2DetailVO.setCheckProjectIds(purchaseCheckLot.getCheckProjectIds());
                        distrInCheckLot2DetailVO.setConclusionIds(purchaseCheckLot.getConclusionIds());
                        distrInCheckLot2DetailVO.setMeasuresIds(purchaseCheckLot.getMeasuresIds());
                        distrInCheckLot2DetailVO.setReceiveQuantity(purchaseCheckLot.getReceiveQuantity());
                        distrInCheckLot2DetailVO.setSamplingQuantity(purchaseCheckLot.getSamplingQuantity());
                        distrInCheckLot2DetailVO.setQualifiedQuantity(purchaseCheckLot.getQualifiedQuantity());
                        distrInCheckLot2DetailVO.setUnqualifiedQuantity(purchaseCheckLot.getUnqualifiedQuantity());
                        distrInCheckLot2DetailVO.setLineNum(purchaseCheckLot.getLineNum());
                        distrInCheckLot2DetailVOS.add(distrInCheckLot2DetailVO);
                    }
                    distrInCheckDetail2DetailVO.setLot2DetailVOList(distrInCheckLot2DetailVOS);
                }
            }
            
            distrInCheckDetail2DetailVOS.add(distrInCheckDetail2DetailVO);
        }
        inCheck.setDetailVOList(distrInCheckDetail2DetailVOS);

        distrInCheckService.saveDistrInCheck(loginUser,inCheck);

        DistrInCheck distrInCheck = distrInCheckMapper.selectByBaseOrderId(loginUser.getEnterpriseId(),inReceive.getId());

        return distrInCheck;
    }

    //门店配进收货
    private DistrInReceive generateDistrReceive(DistrInNotice inNotice, UserVO loginUser, GetReceiptDataVO getReceiptDataVO) throws Exception {
        DistrInReceiveSaveVO inReceive = new DistrInReceiveSaveVO();
        inReceive.setReceiveDate(new Date());
        inReceive.setBaseOrderId(inNotice.getId());
//        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser.getEnterpriseId());
//        if(manageConfig.getReceiverId() == null){
//            throw new BusinessException("生成配进收货时没有取到门店的默认收货人ID信息");
//        }
        inReceive.setReceiverId(getReceiptDataVO.getReceiveManId());
//        if(manageConfig.getSecondReceiverId() == null){
//            throw new BusinessException("生成配进收货时没有取到门店的默认第二收货人ID信息");
//        }
        inReceive.setSecondReceiverId(getReceiptDataVO.getSecondReceiveManId());

        inReceive.setQuantityTotal(inNotice.getQuantityTotal());
        inReceive.setVarietiesQuantity(inNotice.getVarietiesQuantity());

        List<DistrInNoticeDetail> distrInNoticeDetails = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseId(inNotice.getId(), loginUser.getEnterpriseId());
        List<DistrInReceiveDetailSaveVO> distrInReceiveDetailSaveVOS = new ArrayList<>();
        for(DistrInNoticeDetail distrInNoticeDetail : distrInNoticeDetails){

            DistrInReceiveDetailSaveVO distrInReceiveDetailSaveVO = new DistrInReceiveDetailSaveVO();
            distrInReceiveDetailSaveVO.setArrivalQuantity(distrInNoticeDetail.getQuantity());
            distrInReceiveDetailSaveVO.setBaseOrderDtlId(distrInNoticeDetail.getId());
            distrInReceiveDetailSaveVO.setBaseOrderId(distrInNoticeDetail.getOrderId());
            distrInReceiveDetailSaveVO.setClearQuantity(BigDecimal.ZERO);
            distrInReceiveDetailSaveVO.setGoodsId(distrInNoticeDetail.getGoodsId());
            distrInReceiveDetailSaveVO.setLineNum(distrInNoticeDetail.getLineNum());
            distrInReceiveDetailSaveVO.setReceiveQuantity(distrInNoticeDetail.getQuantity());
            distrInReceiveDetailSaveVO.setRefuseQuantity(BigDecimal.ZERO);
            distrInReceiveDetailSaveVO.setUnclearQuantity(distrInNoticeDetail.getQuantity());
            distrInReceiveDetailSaveVOS.add(distrInReceiveDetailSaveVO);
        }
        inReceive.setDistrInReceiveDetailSaveVOS(distrInReceiveDetailSaveVOS);
        distrInReceiveService.saveDistrInReceiveOrder(loginUser, inReceive);
        DistrInReceive distrInReceive = distrInReceiveMapper.selectByBaseOrderId(loginUser.getEnterpriseId(),inNotice.getId());

        return distrInReceive;
    }

    //门店配进订单
    private DistrInNotice generateDistrOrder(DistrOut out, UserVO loginUser, GetReceiptDataVO getReceiptDataVO) throws Exception {
        DistrInNotice inNotice = new DistrInNotice();
        List<DistrInNoticeDetail> details = new ArrayList<>();
        inNotice.setEnterpriseId(loginUser.getEnterpriseId());
        inNotice.setParentId(loginUser.getParentId());
        inNotice.setOrderDate(new Date());
        inNotice.setBaseOrderId(out.getId());
        inNotice.setBaseOrderType(out.getOrderType());
        inNotice.setBaseOrderCode(out.getBaseOrderCode());
        inNotice.setBaseOrderDate(out.getBaseOrderDate());
        inNotice.setDistrUnitId(out.getEnterpriseId());
        //配货单位
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(out.getEnterpriseId());
        inNotice.setDistrUnitCode(enterprise.getCode());
        inNotice.setDistrUnitName(enterprise.getName());
        inNotice.setStorageManId(getReceiptDataVO.getPurchaseManId());
        
        User clerker = userMapper.selectByPrimaryKey(getReceiptDataVO.getPurchaseManId());
		if(clerker == null) {
			throw new BusinessException("门店采购人员ID不存在！");
		}
        inNotice.setStorageManCode(clerker.getCode());
        inNotice.setStorageManName(clerker.getName());
        inNotice.setDistrType(DistributionType.DIRECT_DISTRIBUTION.getCode());
        inNotice.setQuantityTotal(out.getQuantityTotal());
        inNotice.setVarietiesQuantity(out.getVarietiesQuantity());
        inNotice.setAmountTotal(out.getAmountTotal());
        inNotice.setWholeDiscount(out.getWholeDiscount());
        inNotice.setWholeDiscountAmount(out.getWholeDiscountAmount());
        inNotice.setRealAmountTotal(out.getRealAmountTotal());
        inNotice.setNotaxRealAmountTotal(out.getNotaxRealAmountTotal());
        inNotice.setTaxAmountTotal(out.getTaxAmountTotal());
        inNotice.setStatus(DistrInStatus.FINISHED);
        inNotice.setOutboundUnitId(inNotice.getDistrUnitId());
        inNotice.setOutboundUnitCode(inNotice.getDistrUnitCode());
        inNotice.setOutboundUnitName(inNotice.getDistrUnitName());
        UserEnterpriseUtils.setUserCreateOrModify(inNotice,loginUser,true);
        List<DistrOutDetail> distrOutDetailList = out.getDistrOutDetailList();
        List<DistrInNoticeDetail> detailList = new ArrayList<>();
        for (DistrOutDetail d : distrOutDetailList) {
            DistrInNoticeDetail detail = new DistrInNoticeDetail();
            detail.setEnterpriseId(loginUser.getEnterpriseId());
            detail.setParentId(loginUser.getParentId());
            detail.setBaseOrderDtlId(d.getId());
            detail.setBaseOrderId(out.getId());
            detail.setBaseOrderType(out.getOrderType());
            detail.setBaseOrderCode(out.getCode());
            detail.setBaseOrderDate(out.getOutDate());
            detail.setGoodsId(d.getGoodsId());
            detail.setGoodsCode(d.getGoodsCode());
            detail.setBarcode(d.getBarcode());
            detail.setGoodsName(d.getGoodsName());
            detail.setGoodsGenericName(d.getGoodsGenericName());
            detail.setDosageId(d.getDosageId());
            detail.setDosageName(d.getDosageName());
            detail.setUnitId(d.getUnitId());
            detail.setUnitName(d.getUnitName());
            detail.setGoodsSpecification(d.getGoodsSpecification());
            detail.setManufacturerId(d.getManufacturerId());
            detail.setManufacturer(d.getManufacturer());
            detail.setGoodsPlace(d.getGoodsPlace());
            detail.setApprovalNumber(d.getApprovalNumber());
            detail.setQuantity(d.getQuantity());
            detail.setUnitPrice(d.getUnitPrice());
            detail.setLineDiscount(d.getLineDiscount());
            detail.setAmount(d.getAmount());
            detail.setWholeDiscount(d.getWholeDiscount());
            detail.setLineDiscountAmount(d.getLineDiscountAmount());
            detail.setRealPrice(d.getRealPrice());
            detail.setRealAmount(d.getRealAmount());
            detail.setTaxRateId(d.getTaxRateId());
            detail.setTaxRate(d.getTaxRate());
            detail.setNotaxRealPrice(d.getNotaxRealPrice());
            detail.setNotaxRealAmount(d.getNotaxRealAmount());
            detail.setTaxAmount(d.getTaxAmount());
            detail.setUnclearQuantity(d.getUnclearQuantity());
            detail.setClearQuantity(d.getClearQuantity());
            detail.setLineNum(d.getLineNum());
            detail.setStatus(DistrInStatus.FINISHED);
            UserEnterpriseUtils.setUserCreateOrModify(detail,loginUser,true);
            detailList.add(detail);
        }
        inNotice.setDetailList(detailList);
        distrComponent.saveInNotice(loginUser,inNotice);
        return inNotice;
    }

    //总部配货出库
    private DistrOut generateDistrOut(GetReceiptDataVO getReceiptDataVO, DistrSend send, PurchaseInStorage storage, UserVO userVO, UserVO loginUser) throws Exception {
        DistrOut out = new DistrOut();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getParentId() == 0L?loginUser.getEnterpriseId():loginUser.getParentId());
        UserEnterpriseUtils.setUserCreateOrModify(out,userVO,true);
        out.setEnterpriseId(userVO.getEnterpriseId());
        out.setParentId(userVO.getParentId());
        out.setBaseOrderId(send.getId());
        out.setBaseOrderCode(send.getCode());
        out.setBaseOrderDate(send.getDistrDate());
        out.setBaseOrderType(send.getOrderType());
        ManageConfig manageConfig = configMapper.getMangeConfigByEPId(userVO.getEnterpriseId());
        if(manageConfig == null){
            throw new BusinessException("生成总部配货出库时没有取到总部默认配货人员ID信息");
        }
        if(manageConfig.getDistrManId() == null){
            throw new BusinessException("生成总部配货出库时没有取到总部默认配货人员ID信息");
        }
        out.setOutManId(manageConfig.getInOutManId());
        out.setOutDate(send.getDistrDate());
        out.setOutDateStr(DateUtils.DateToString(send.getDistrDate(), DateUtils.FMT_DATE_TIME));
        out.setWholeDiscount(send.getWholeDiscount());
        out.setWholeDiscountAmount(send.getWholeDiscountAmount());
        out.setRequestUnitId(loginUser.getEnterpriseId());
        out.setDistrType(DistributionType.DIRECT_DISTRIBUTION.getCode());
        ManageConfig mangeConfigByEPId = configMapper.getMangeConfigByEPId(loginUser.getParentId());
        out.setSendManId(mangeConfigByEPId.getDistrManId());
        out.setSendManCode(mangeConfigByEPId.getDistrManCode());
        out.setSendManName(mangeConfigByEPId.getDistrManName());
        Enterprise subvision = enterpriseMapper.selectByPrimaryKey(loginUser.getEnterpriseId());
        out.setRequestUnitCode(subvision.getCode());
        out.setRequestUnitName(subvision.getName());
        List<DistrOutDetail> detailList = new ArrayList<>();
        List<DistrSendDetail> details = distrSendDetailMapper.listDistrSendDetailList(send.getId());
        for (DistrSendDetail p : details) {
            DistrOutDetail d = new DistrOutDetail();
            d.setEnterpriseId(enterprise.getId());
            d.setParentId(0L);
            d.setOrderType(OrderRule.DISTR_OUT.getType());
            d.setBaseOrderDtlId(p.getId());
            d.setGoodsId(p.getGoodsId());
            d.setGoodsCode(p.getGoodsCode());
            d.setBarcode(p.getBarcode());
            d.setGoodsName(p.getGoodsName());
            d.setGoodsGenericName(p.getGoodsGenericName());
            d.setDosageId(p.getDosageId());
            d.setDosageName(p.getDosageName());
            d.setUnitId(p.getUnitId());
            d.setUnitName(p.getUnitName());
            d.setGoodsSpecification(p.getGoodsSpecification());
            d.setManufacturerId(p.getManufacturerId());
            d.setManufacturer(p.getManufacturer());
            d.setGoodsPlace(p.getGoodsPlace());
            d.setApprovalNumber(p.getApprovalNumber());
            d.setQuantity(p.getQuantity());
            d.setUnitPrice(p.getUnitPrice());
            d.setLineDiscount(p.getLineDiscount());
            d.setAmount(p.getAmount());
            d.setWholeDiscount(p.getWholeDiscount());
            d.setLineDiscountAmount(p.getLineDiscountAmount());
            d.setRealPrice(p.getRealPrice());
            d.setRealAmount(p.getRealAmount());
            d.setTaxRateId(p.getTaxRateId());
            d.setTaxRate(p.getTaxRate());
            d.setNotaxRealPrice(p.getNotaxRealPrice());
            d.setNotaxRealAmount(p.getNotaxRealAmount());
            d.setTaxAmount(p.getTaxAmount());
            d.setUnclearQuantity(p.getUnclearQuantity());
            d.setClearQuantity(p.getClearQuantity());
            d.setLineNum(p.getLineNum());
            d.setStatus(DistrInStatus.FINISHED);
            d.setRemark("由集采 + 智采生成");
            UserEnterpriseUtils.setUserCreateOrModify(d,userVO,true);
            Long storageId = storage.getId();
            
            List<PurchaseInStorageDetail> inStorageDetailList = purchaseInStorageDetailMapper.selectByInStorageId(storage.getId());
            
            if(inStorageDetailList != null){
            	for (PurchaseInStorageDetail purchaseInStorageDetail : inStorageDetailList) {
					if(p.getGoodsId().equals(purchaseInStorageDetail.getGoodsId()) &&
							p.getLineNum().equals(purchaseInStorageDetail.getLineNum())){
						
						List<DistrInStorageShelfDtlVO> distrInCheckLot2DetailVO = new ArrayList<>();
						List<PurchaseInStorageShelf> shelfList = purchaseInStorageShelfMapper.selectByEnterpriseIdAndId(purchaseInStorageDetail.getEnterpriseId(), purchaseInStorageDetail.getId());
					
						List<DistrOutShelf> requestShelfList = new ArrayList<>();
			            for (PurchaseInStorageShelf s : shelfList) {
			            	if(p.getGoodsId().equals(s.getGoodsId())){
				                DistrOutShelf shelf = new DistrOutShelf();
				                shelf.setEnterpriseId(enterprise.getId());
				                shelf.setParentId(0L);
				                shelf.setGoodsId(s.getGoodsId());
				                shelf.setGoodsCode(s.getGoodsCode());
				                shelf.setGoodsName(s.getGoodsName());
				                //获取批号ID
				                LotNumber l = lotNumberMapper.getLotNumInfo(enterprise.getId(), s.getGoodsId(), s.getLotNumber());
				                if (l != null){
				                    shelf.setLotId(l.getId());
				                }
				                shelf.setLotNumber(s.getLotNumber());
				                shelf.setProductDate(s.getProductDate());
				                shelf.setValidDate(s.getValidDate());
				                shelf.setShelfId(s.getShelfId());
				                shelf.setShelfName(s.getShelfName());
				                Integer jobArea = warehouseCargoAreaMapper.selectByPrimaryKey(warehouseShelfMapper.selectByPrimaryKey(s.getShelfId()).getCargoAreaId()).getJobArea();
				                shelf.setShelfStatusDesc(jobArea == 0 ? "合格品" : (jobArea == 2 ?  "不合格品" : ""));
				                shelf.setQuantity(s.getQuantity());
				                shelf.setUnitPrice(s.getUnitPrice());
				                shelf.setLineDiscount(s.getLineDiscount());
				                shelf.setAmount(s.getAmount());
				                shelf.setWholeDiscount(s.getWholeDiscount());
				                shelf.setLineDiscountAmount(s.getLineDiscountAmount());
				                shelf.setRealPrice(s.getRealPrice());
				                shelf.setRealAmount(s.getRealAmount());
				                shelf.setTaxRateId(s.getTaxRateId());
				                shelf.setTaxRate(s.getTaxRate());
				                shelf.setNotaxRealPrice(s.getNotaxRealPrice());
				                shelf.setNotaxRealAmount(s.getNotaxRealAmount());
				                shelf.setTaxAmount(s.getTaxAmount());
				                shelf.setUnclearQuantity(s.getUnclearQuantity());
				                shelf.setClearQuantity(s.getClearQuantity());
				                shelf.setLineNum(s.getLineNum());
				                shelf.setStatus(DistrInStatus.FINISHED);
				                UserEnterpriseUtils.setUserCreateOrModify(shelf,loginUser,true);
				                requestShelfList.add(shelf);
			            	}    
			            }
			            d.setDistrOutShelfList(requestShelfList);
			            detailList.add(d);
					}
            	}
            }	
//            List<PurchaseInStorageShelf> shelfList = purchaseInStorageShelfMapper.selectByStorageId(storageId);
            /*List<DistrOutShelf> requestShelfList = new ArrayList<>();
            for (PurchaseInStorageShelf s : shelfList) {
            	if(p.getGoodsId().equals(s.getGoodsId())){
	                DistrOutShelf shelf = new DistrOutShelf();
	                shelf.setEnterpriseId(enterprise.getId());
	                shelf.setParentId(0L);
	                shelf.setGoodsId(s.getGoodsId());
	                shelf.setGoodsCode(s.getGoodsCode());
	                shelf.setGoodsName(s.getGoodsName());
	                //获取批号ID
	                LotNumber l = lotNumberMapper.getLotNumInfo(enterprise.getId(), s.getGoodsId(), s.getLotNumber());
	                if (l != null){
	                    shelf.setLotId(l.getId());
	                }
	                shelf.setLotNumber(s.getLotNumber());
	                shelf.setProductDate(s.getProductDate());
	                shelf.setValidDate(s.getValidDate());
	                shelf.setShelfId(s.getShelfId());
	                shelf.setShelfName(s.getShelfName());
	                Integer jobArea = warehouseCargoAreaMapper.selectByPrimaryKey(warehouseShelfMapper.selectByPrimaryKey(s.getShelfId()).getCargoAreaId()).getJobArea();
	                shelf.setShelfStatusDesc(jobArea == 0 ? "合格品" : (jobArea == 2 ?  "不合格品" : ""));
	                shelf.setQuantity(s.getQuantity());
	                shelf.setUnitPrice(s.getUnitPrice());
	                shelf.setLineDiscount(s.getLineDiscount());
	                shelf.setAmount(s.getAmount());
	                shelf.setWholeDiscount(s.getWholeDiscount());
	                shelf.setLineDiscountAmount(s.getLineDiscountAmount());
	                shelf.setRealPrice(s.getRealPrice());
	                shelf.setRealAmount(s.getRealAmount());
	                shelf.setTaxRateId(s.getTaxRateId());
	                shelf.setTaxRate(s.getTaxRate());
	                shelf.setNotaxRealPrice(s.getNotaxRealPrice());
	                shelf.setNotaxRealAmount(s.getNotaxRealAmount());
	                shelf.setTaxAmount(s.getTaxAmount());
	                shelf.setUnclearQuantity(s.getUnclearQuantity());
	                shelf.setClearQuantity(s.getClearQuantity());
	                shelf.setLineNum(s.getLineNum());
	                shelf.setStatus(DistrInStatus.FINISHED);
	                UserEnterpriseUtils.setUserCreateOrModify(shelf,loginUser,true);
	                requestShelfList.add(shelf);
            	}    
            }
            d.setDistrOutShelfList(requestShelfList);
            detailList.add(d);*/
        }
        out.setDistrOutDetailList(detailList);
        distrOutService.save(out,userVO);
        
        DistrOutCheckVo checkvo = new DistrOutCheckVo();
        checkvo.setId(out.getId());
        checkvo.setStatus(1);
        userVO.setUserId(manageConfig.getOutCheckerId());
        userVO.setUserCode(manageConfig.getOutCheckerCode());
        userVO.setUserName(manageConfig.getOutCheckerName());
        distrOutService.check(checkvo, userVO);
        return out;
    }

    //总部配货单
    private DistrSend generateDistrSend(GetReceiptDataVO getReceiptDataVO, UserVO userVO, UserVO loginUser, PurchaseInStorage storage) throws Exception {
        DistrSend distrSend = new DistrSend();
        /**
         * 构建主单
         */
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getParentId() == 0L?loginUser.getEnterpriseId():loginUser.getParentId());
        distrSend.setEnterpriseId(enterprise.getId());
        distrSend.setParentId(enterprise.getParentId());
        distrSend.setOrderType(OrderRule.DISTR_ORDER.getType());
        distrSend.setCode(codeComponent.generate(OrderRule.DISTR_ORDER.getCodePrefix(),enterprise.getId(),enterprise.getCode() ));
        distrSend.setDistrDate(storage.getInStorageDate());
        Long enterpriseId = loginUser.getEnterpriseId();
        Enterprise division = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        distrSend.setRequestUnitId(division.getId());
        distrSend.setRequestUnitCode(division.getCode());
        distrSend.setRequestUnitName(division.getName());
        ManageConfig mangeConfig = configMapper.getMangeConfigByEPId(enterprise.getId());
        if (mangeConfig.getDistrManId() == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前总部未设置配货人员，请先去设置配货人员!");
        }
        distrSend.setDistrManId(mangeConfig.getDistrManId());
        distrSend.setDistrManCode(mangeConfig.getDistrManCode());
        distrSend.setDistrManName(mangeConfig.getDistrManName());
        distrSend.setDistrType(DistributionType.DIRECT_DISTRIBUTION.getCode());
        distrSend.setDistrRule(0);
        distrSend.setLackHandle(1);
        //计算总单金额相关
        //数量合计
        distrSend.setQuantityTotal(storage.getQuantityTotal());
        //品种数量
        distrSend.setVarietiesQuantity(storage.getVarietiesQuantity());
        //金额合计
        distrSend.setAmountTotal(storage.getAmountTotal());
        //整单折扣
        distrSend.setWholeDiscount(BigDecimal.valueOf(100));
        //整单优惠金额
        distrSend.setWholeDiscountAmount(storage.getWholeDiscountAmount());
        //实际金额合计
        distrSend.setRealAmountTotal(storage.getRealAmountTotal());
        //不含税金额合计
        distrSend.setNotaxRealAmountTotal(storage.getNotaxRealAmountTotal());
        //税额合计
        distrSend.setTaxAmountTotal(storage.getTaxAmountTotal());
        //状态
        distrSend.setStatus(DistrSendStatus.WAIT_OUT);
        UserEnterpriseUtils.setUserCreateOrModify(distrSend,userVO,true);
        distrSendMapper.insertSelective(distrSend);
        //插细表
        List<PurchaseInStorageDetail> purchaseInStorageDetails = purchaseInStorageDetailMapper.selectByInStorageId(storage.getId());
        for (PurchaseInStorageDetail p : purchaseInStorageDetails) {
            DistrSendDetail d = new DistrSendDetail();
            d.setEnterpriseId(enterprise.getId());
            d.setParentId(enterprise.getParentId());
            d.setDistrId(distrSend.getId());
            d.setOrderType(OrderRule.DISTR_ORDER.getType());
            d.setDistrCode(distrSend.getCode());
            d.setDistrDate(distrSend.getDistrDate());
            d.setGoodsId(p.getGoodsId());
            d.setGoodsCode(p.getGoodsCode());
            d.setBarcode(p.getBarcode());
            d.setGoodsName(p.getGoodsName());
            d.setGoodsGenericName(p.getGoodsGenericName());
            d.setDosageId(p.getDosageId());
            d.setDosageName(p.getDosageName());
            d.setUnitId(p.getUnitId());
            d.setUnitName(p.getUnitName());
            d.setGoodsSpecification(p.getGoodsSpecification());
            d.setManufacturerId(p.getManufacturerId());
            d.setManufacturer(p.getManufacturer());
            d.setGoodsPlace(p.getGoodsPlace());
            d.setApprovalNumber(p.getApprovalNumber());
            //总部库存使用量
            BigDecimal parentUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getEnterpriseId(), p.getGoodsId());
            if (parentUsableQuantity == null) {
                parentUsableQuantity = BigDecimal.ZERO;
            }
            d.setParentUsableQuantity(parentUsableQuantity);

            //门店库存可用量
            BigDecimal usableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getRequestUnitId(), p.getGoodsId());
            if (usableQuantity == null) {
                usableQuantity = BigDecimal.ZERO;
            }
            d.setUsableQuantity(usableQuantity);
            d.setQuantity(p.getQuantity());
            d.setUnitPrice(p.getUnitPrice());
            d.setLineDiscount(p.getLineDiscount());
            d.setAmount(p.getAmount());
            d.setWholeDiscount(p.getWholeDiscount());
            d.setLineDiscountAmount(p.getLineDiscountAmount());
            d.setRealPrice(p.getRealPrice());
            d.setRealAmount(p.getRealAmount());
            d.setTaxRateId(p.getTaxRateId());
            d.setTaxRate(p.getTaxRate());
            d.setNotaxRealPrice(p.getNotaxRealPrice());
            d.setNotaxRealAmount(p.getNotaxRealAmount());
            d.setTaxAmount(p.getTaxAmount());
            d.setUnclearQuantity(p.getUnclearQuantity());
            d.setClearQuantity(p.getClearQuantity());
            d.setLineNum(p.getLineNum());
            d.setStatus(DistrSendStatus.WAIT_OUT);
            d.setRemark("由集采智采生成");
            UserEnterpriseUtils.setUserCreateOrModify(d,userVO,true);
            distrSendDetailMapper.insertSelective(d);
            // 锁定库存
            lockStockByDetail(loginUser,d);
        }
        return distrSend;
    }

    // 锁定库存
    private void lockStockByDetail(UserVO userVO, DistrSendDetail distrSendDetail) {
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
        commonComponent.lockStockAndCost(lockQtyArgVO);
    }

    //总部采购入库
    private PurchaseInStorage generatePurchaseIn(PurchaseReceive receive, PurchaseCheck check, ManageConfig config, GetReceiptDataVO getReceiptDataVO, 
    		UserVO loginUser, UserVO loginUser2) throws Exception {
        StayInstorageSaveVO saveVO = new StayInstorageSaveVO();
        saveVO.setId(check.getId());

        if(loginUser2.getChainType() == ChainType.Headquarters.getCode()){//总部用户

        	saveVO.setStorageManId(getReceiptDataVO.getInManId());
	        User clerker = userMapper.selectByPrimaryKey(getReceiptDataVO.getCheckManId());
			if(clerker == null) {
				throw new BusinessException("收货人员ID不存在！");
			}
        }else{//门店用户

        	saveVO.setStorageManId(config.getInOutManId());
        }
        saveVO.setSupplierCode(receive.getSupplierCode());
        saveVO.setSupplierName(receive.getSupplierName());
        saveVO.setSupplierSalerName(receive.getSupplierSalerName());
        saveVO.setSupplierSalerPhone(receive.getSupplierSalerPhone());
        saveVO.setStorageManName(loginUser.getUserName());
        saveVO.setInStorageDate(getReceiptDataVO.getInDate());
        saveVO.setVarietiesQuantity(receive.getVarietiesQuantity());
        PurchaseOrderOther purchaseOrderOther = orderOtherMapper.selectByEnterpriseIdByOrderId(loginUser.getEnterpriseId(),check.getPurchaseOrderId());
        PurchaseOrderOtherVO orderOtherVO = new PurchaseOrderOtherVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseOrderOther,orderOtherVO);
        saveVO.setFlowCode(getReceiptDataVO.getFlowCode());
        saveVO.setPurchaseOrderOtherVO(orderOtherVO);
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        
        List<StayInstorageDetailSaveVO> detailIn = new ArrayList<StayInstorageDetailSaveVO>();
        StayInstorageDetailSaveVO detailSave = null;
        List<GetReceiptGoodsDataVO> goodsDataList = getReceiptDataVO.getGetReceiptGoodsDataVO();
        for (GetReceiptGoodsDataVO getReceiptGoodsDataVO : goodsDataList) {
        	detailSave = new StayInstorageDetailSaveVO();
        	PurchaseCheckDetail purchaseCheckDetail = getReceiptGoodsDataVO.getPurchaseCheckDetail();
        	detailSave.setCheckDtlId(purchaseCheckDetail.getId());
        	detailSave.setGoodsId(purchaseCheckDetail.getGoodsId());
        	detailSave.setTaxRateId(getReceiptGoodsDataVO.getTaxRateId());
        	
        	List<CheckLotDetailVO> checkLotDetailList = new ArrayList<CheckLotDetailVO>();
        	List<GetReceiptGoodsLotDataVO> lotDataList = getReceiptGoodsDataVO.getGetReceiptGoodsLotDataVO();
        	
        	for (GetReceiptGoodsLotDataVO getReceiptGoodsLotDataVO : lotDataList) {
        		PurchaseCheckLot checkLot = getReceiptGoodsLotDataVO.getPurchaseCheckLot();
        		CheckLotDetailVO lotDetailVO = new CheckLotDetailVO();
        		lotDetailVO.setCheckLotId(checkLot.getId());
        		lotDetailVO.setJobArea(0);
        		lotDetailVO.setJobAreaName("合格品");
        		lotDetailVO.setLotNumber(checkLot.getLotNumber());
        		lotDetailVO.setProductDate(checkLot.getProductDate());
        		lotDetailVO.setValidDate(checkLot.getValidDate());
        		lotDetailVO.setShelfId(getReceiptGoodsLotDataVO.getShelfId());
        		lotDetailVO.setShelfName(getReceiptGoodsLotDataVO.getShelfName());
        		lotDetailVO.setReceiveQuantity(getReceiptGoodsLotDataVO.getReceiveQuantity());
        		lotDetailVO.setLineNum(checkLot.getLineNum());
        		lotDetailVO.setQualifiedQuantity(getReceiptGoodsLotDataVO.getReceiveQuantity());
        		lotDetailVO.setUnqualifiedQuantity(BigDecimal.ZERO);
        		lotDetailVO.setQuantity(getReceiptGoodsLotDataVO.getReceiveQuantity());
        		checkLotDetailList.add(lotDetailVO);
			}
        			
        	detailSave.setCheckLotDetailList(checkLotDetailList);
        	detailSave.setRemark("");
        	detailSave.setGoodsCode(purchaseCheckDetail.getGoodsCode());
        	detailSave.setGoodsName(purchaseCheckDetail.getGoodsName());
        	detailSave.setGoodsGenericName(purchaseCheckDetail.getGoodsGenericName());
        	detailSave.setDosageName(purchaseCheckDetail.getDosageName());
        	detailSave.setGoodsSpecification(purchaseCheckDetail.getGoodsSpecification());
        	detailSave.setManufacturer(purchaseCheckDetail.getManufacturer());
        	detailSave.setUnitName(purchaseCheckDetail.getUnitName());
        	detailSave.setQuantity(purchaseCheckDetail.getQuantity());
        	detailSave.setUnitPrice(getReceiptGoodsDataVO.getPurchasePrice());
        	detailSave.setLineDiscount(BigDecimal.valueOf(100L));
//        	detailSave.setAmount(amount);
//        	detailSave.setTaxRate(taxRate);
//        	detailSave.setNotaxRealPrice(notaxRealPrice);
//        	detailSave.setNotaxRealAmount(notaxRealAmount);
//        	detailSave.setTaxAmount(taxAmount);
        	detailSave.setLineNum(purchaseCheckDetail.getLineNum());
        	detailIn.add(detailSave);
		}
        
        
        saveVO.setStayInstorageDetailSaveVO(detailIn);
        saveVO.setAmountTotal(amountTotal);
        saveVO.setWholeDiscountAmount(BigDecimal.ZERO);
        saveVO.setWholeDiscount(new BigDecimal(100));
        saveVO.setWholeDiscountAfter(amountTotal);
        saveVO.setRealAmountTotal(amountTotal);
        saveVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
        saveVO.setTaxAmountTotal(taxAmountTotal);
        inStorageService.saveStayInstorage(saveVO,loginUser);
        List<PurchaseInStorage> storages = inStorageMapper.selectByCheckIdByEnterpriseId(check.getId(),loginUser.getEnterpriseId());
        return storages.get(0);
    }

    private BigDecimal getTaxRate(Long taxRateId) {
        GoodsTaxRate goodsTaxRate = taxRateMapper.selectByPrimaryKey(taxRateId);
        if (goodsTaxRate != null){
            return goodsTaxRate.getTaxRate();
        }
        return BigDecimal.ZERO;
    }

    //总部采购验收
    @Transactional(rollbackFor = Exception.class)
    private PurchaseCheck generatePurchaseCheck(PurchaseOrder order, PurchaseReceive receive, ManageConfig config, 
    		GetReceiptDataVO getReceiptDataVO, UserVO loginUser, UserVO loginUser2) throws Exception {
    	Date receiveDate = receive.getReceiveDate();
        Date checkDate = getReceiptDataVO.getCheckDate();
        if(receiveDate.getTime() > checkDate.getTime()) {
        	throw new BusinessException("收货时间不能大于验收时间!");
        }
    	
		PurchaseCheck purchaseCheck = new PurchaseCheck();
		purchaseCheck = (PurchaseCheck) EntityUtils.reflectAddSetDefaultValue(purchaseCheck.getClass(),loginUser);//复制用户
		purchaseCheck.setEnterpriseId(receive.getEnterpriseId());
		purchaseCheck.setParentId(receive.getParentId());
		purchaseCheck.setOrderType(OrderRule.PURCHASE_CHECK.getType());
		String checkCode = orderCodeComponent.generate(OrderRule.PURCHASE_CHECK.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode());
		purchaseCheck.setCode(checkCode);
		purchaseCheck.setCheckDate(getReceiptDataVO.getCheckDate());
		
		purchaseCheck.setBaseOrderId(receive.getId());
		purchaseCheck.setBaseOrderDate(receive.getReceiveDate());
		purchaseCheck.setBaseOrderCode(receive.getCode());
		purchaseCheck.setBaseOrderType(receive.getOrderType());

		//供应商信息
		purchaseCheck.setSupplierId(receive.getSupplierId());
		purchaseCheck.setSupplierCode(receive.getSupplierCode());
		purchaseCheck.setSupplierName(receive.getSupplierName());
		//供应商销售人员信息
		purchaseCheck.setSupplierSalerId(receive.getSupplierSalerId());
		purchaseCheck.setSupplierSalerCode(receive.getSupplierSalerCode());
		purchaseCheck.setSupplierSalerName(receive.getSupplierSalerName());
		purchaseCheck.setSupplierSalerPhone(receive.getSupplierSalerPhone());

		if(loginUser2.getChainType() == ChainType.Headquarters.getCode()){//总部用户
			User user = userMapper.selectByPrimaryKey(getReceiptDataVO.getCheckManId());
			if(user != null) {
				purchaseCheck.setCheckerId(user.getId());
				purchaseCheck.setCheckerCode(user.getCode());
				purchaseCheck.setCheckerName(user.getName());
			} else {
				throw new BusinessException("验收人员不存在！");
			}
			if(getReceiptDataVO.getSecondCheckManId() != null){
	            user = userMapper.selectByPrimaryKey(getReceiptDataVO.getSecondCheckManId());
	            if(user != null) {
	            	purchaseCheck.setSecondCheckerId(user.getId());
	            	purchaseCheck.setSecondCheckerCode(user.getCode());
	            	purchaseCheck.setSecondCheckerName(user.getName());
	            } else {
	            	throw new BusinessException("验收人员2不存在！");
	            }
	        }
		}else{
			purchaseCheck.setCheckerId(config.getCheckerId());
			purchaseCheck.setCheckerCode(config.getCheckerCode());
			purchaseCheck.setCheckerName(config.getCheckerName());
			purchaseCheck.setSecondCheckerId(config.getSecondCheckerId());
        	purchaseCheck.setSecondCheckerCode(config.getSecondCheckerCode());
        	purchaseCheck.setSecondCheckerName(config.getSecondCheckerName());
		}	
		purchaseCheck.setQuantityTotal(receive.getQuantityTotal());
		purchaseCheck.setVarietiesQuantity(receive.getVarietiesQuantity());
        //验收单号,单据类型
        purchaseCheck.setStatus(PurchaseStatus.FINISHED.getStatus());
        purchaseCheck.setPurchaseOrderId(receive.getBaseOrderId());
        checkMapper.insertSelective(purchaseCheck);
    	
        List<GetReceiptGoodsDataVO> goodsList = getReceiptDataVO.getGetReceiptGoodsDataVO();
        if(goodsList != null){
        	for (GetReceiptGoodsDataVO goodsDataVO : goodsList) {
        		
        		PurchaseCheckDetail purchaseCheckDetail = new PurchaseCheckDetail();
        		PurchaseReceiveDetail receiveDetail = goodsDataVO.getPurchaseReceiveDetail();
        		
        		purchaseCheckDetail = (PurchaseCheckDetail) EntityUtils.reflectAddSetDefaultValue(purchaseCheckDetail.getClass(), loginUser);//复制用户
            	//包含商品信息复制
            	CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(receiveDetail,purchaseCheckDetail);
            	purchaseCheckDetail.setId(null);
            	//set基础单据信息
                purchaseCheckDetail.setBaseOrderId(receiveDetail.getReceiveId());
                purchaseCheckDetail.setBaseOrderDate(receiveDetail.getReceiveDate());
                purchaseCheckDetail.setBaseOrderType(receiveDetail.getOrderType());
                purchaseCheckDetail.setBaseOrderCode(receiveDetail.getReceiveCode());
                purchaseCheckDetail.setBaseOrderDtlId(receiveDetail.getId());
                //set状态
                purchaseCheckDetail.setStatus(PurchaseStatus.FINISHED.getStatus());
                //set验收单据id/code/类型/日期
                purchaseCheckDetail.setCheckId(purchaseCheck.getId());
                purchaseCheckDetail.setCheckCode(purchaseCheck.getCode());
                purchaseCheckDetail.setOrderType(OrderRule.PURCHASE_RECEIVE.getType());
                purchaseCheckDetail.setCheckDate(purchaseCheck.getCheckDate());
                
                purchaseCheckDetail.setQuantity(receiveDetail.getReceiveQuantity());
                purchaseCheckDetail.setQualifiedQuantity(receiveDetail.getReceiveQuantity());
                purchaseCheckDetail.setUnqualifiedQuantity(BigDecimal.ZERO);
                //收货表细单id设置
                purchaseCheckDetail.setPurchaseOrderId(receiveDetail.getBaseOrderId());
                purchaseCheckDetail.setPurchaseOrderDtlId(receiveDetail.getBaseOrderDtlId());
                purchaseCheckDetailMapper.insertSelective(purchaseCheckDetail);
        		
                goodsDataVO.setPurchaseCheckDetail(purchaseCheckDetail);
                
                List<GetReceiptGoodsLotDataVO> lotList = goodsDataVO.getGetReceiptGoodsLotDataVO();
        		if(lotList != null){
        			int lotNum = 1;
        			for (GetReceiptGoodsLotDataVO gl : lotList) {
        				PurchaseCheckLot purchaseCheckLot = new PurchaseCheckLot();
        				purchaseCheckLot = (PurchaseCheckLot) EntityUtils.reflectAddSetDefaultValue(purchaseCheckLot.getClass(), loginUser);//复制用户
            			//验收单，明细单ID
        				purchaseCheckLot.setEnterpriseId(purchaseCheckDetail.getEnterpriseId());
        				purchaseCheckLot.setParentId(purchaseCheckDetail.getParentId());
                        purchaseCheckLot.setCheckDtlId(purchaseCheckDetail.getId());
                        purchaseCheckLot.setCheckId(purchaseCheck.getId());
                        
                        purchaseCheckLot.setOrderType(purchaseCheckDetail.getOrderType());
                        purchaseCheckLot.setGoodsId(purchaseCheckDetail.getGoodsId());
                        purchaseCheckLot.setLotNumber(gl.getLotNum());
                        purchaseCheckLot.setProductDate(gl.getProductDate());
                        purchaseCheckLot.setValidDate(gl.getValidDate());
                        
                        purchaseCheckLot.setCheckProjectIds(gl.getCheckProjectIds());
//                        purchaseCheckLot.setTestReportIds(gl.get());检验报告书
                        purchaseCheckLot.setReceiveQuantity(gl.getReceiveQuantity());
                        purchaseCheckLot.setSamplingQuantity(BigDecimal.ONE);
                        purchaseCheckLot.setQualifiedQuantity(gl.getReceiveQuantity());
                        purchaseCheckLot.setConclusionIds(gl.getCheckResult()==null?"":gl.getCheckResult().toString());
                        purchaseCheckLot.setUnqualifiedQuantity(BigDecimal.ZERO);
                        purchaseCheckLot.setUnqualifiedReasonIds("");
                        purchaseCheckLot.setMeasuresIds("");
                        purchaseCheckLot.setUnclearQuantity(BigDecimal.ZERO);
                        purchaseCheckLot.setClearQuantity(gl.getReceiveQuantity());
                        purchaseCheckLot.setLineNum(lotNum);
                        //状态
                        purchaseCheckLot.setStatus(PurchaseStatus.FINISHED.getStatus());
                        purchaseCheckLot.setOrderType(OrderRule.PURCHASE_CHECK.getType());
                        //set未清数量/以清数量
                        checkLotMapper.insert(purchaseCheckLot);
                        
                        lotNum++;
                        gl.setPurchaseCheckLot(purchaseCheckLot);
					}
        		}
			}
        	
        }
        
        return purchaseCheck;
    }

    //总部采购收货
    private PurchaseReceive generatePurchaseReceive(GetReceiptDataVO getReceiptDataVO, PurchaseOrder purchaseOrder, ManageConfig config, UserVO loginUser, UserVO loginUser2) throws Exception {
        
    	List<GetReceiptGoodsDataVO> goodsDataList = getReceiptDataVO.getGetReceiptGoodsDataVO();
    	
    	Date orderDate = purchaseOrder.getOrderDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if(getReceiptDataVO.getReceiveDate().before(calendar.getTime())){
            throw new Exception("收货日期不能小于订单日期!");
        }
        
        PurchaseReceive purchaseReceive = new PurchaseReceive();
        UserEnterpriseUtils.setUserCreateOrModify(purchaseReceive,loginUser,true);
        purchaseReceive.setEnterpriseId(loginUser.getEnterpriseId());
        purchaseReceive.setParentId(loginUser.getParentId());
        purchaseReceive.setOrderType(OrderRule.PURCHASE_RECEIVE.getType());
        purchaseReceive.setCode(getCode(OrderRule.PURCHASE_RECEIVE.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
        purchaseReceive.setReceiveDate(getReceiptDataVO.getReceiveDate());

        purchaseReceive.setBaseOrderId(purchaseOrder.getId());
        purchaseReceive.setBaseOrderCode(purchaseOrder.getCode());
        purchaseReceive.setBaseOrderType(purchaseOrder.getOrderType());
        purchaseReceive.setBaseOrderDate(purchaseOrder.getOrderDate());
        //供应商信息
        purchaseReceive.setSupplierId(purchaseOrder.getSupplierId());
        purchaseReceive.setSupplierCode(purchaseOrder.getSupplierCode());
        purchaseReceive.setSupplierName(purchaseOrder.getSupplierName());
        purchaseReceive.setSupplierSalerId(purchaseOrder.getSupplierSalerId());
        purchaseReceive.setSupplierSalerCode(purchaseOrder.getSupplierSalerCode());
        purchaseReceive.setSupplierSalerName(purchaseOrder.getSupplierSalerName());
        purchaseReceive.setSupplierSalerPhone(purchaseOrder.getSupplierSalerPhone());
        //供应商销售人员信息
        
        User user = null;
        if(loginUser2.getChainType() == ChainType.Headquarters.getCode()){//总部用户
        	user = userMapper.selectByPrimaryKey(getReceiptDataVO.getReceiveManId());
            if(user == null){
            	throw new Exception("不存在收货人员ID"+getReceiptDataVO.getReceiveManId()+";");
            }
        	purchaseReceive.setReceiverId(user.getId());
        	purchaseReceive.setReceiverCode(user.getCode());
        	purchaseReceive.setReceiverName(user.getName());
        	if(getReceiptDataVO.getSecondReceiveManId() != null){
            	user = userMapper.selectByPrimaryKey(getReceiptDataVO.getSecondReceiveManId());
            	if(user == null){
                	throw new Exception("不存在收货人员ID"+getReceiptDataVO.getReceiveManId()+";");
                }
            	purchaseReceive.setSecondReceiverId(user.getId());
            	purchaseReceive.setSecondReceiverCode(user.getCode());
            	purchaseReceive.setSecondReceiverName(user.getName());
            }
        }else{
        	purchaseReceive.setReceiverId(config.getReceiverId());
        	purchaseReceive.setReceiverCode(config.getReceiverCode());
        	purchaseReceive.setReceiverName(config.getReceiverName());
        	purchaseReceive.setSecondReceiverId(config.getSecondReceiverId());
        	purchaseReceive.setSecondReceiverCode(config.getSecondReceiverCode());
        	purchaseReceive.setSecondReceiverName(config.getSecondReceiverName());
        }
        
        purchaseReceive.setQuantityTotal(purchaseOrder.getQuantityTotal());
        Set goodsSet = new HashSet();//统计商品种类
        for(GetReceiptGoodsDataVO purchaseReceiveDetailReturnVO : goodsDataList){
        	goodsSet.add(purchaseReceiveDetailReturnVO.getGoodsId());
        }
        purchaseReceive.setVarietiesQuantity(goodsSet.size());
        purchaseReceive.setStatus(PurchaseStatus.FINISHED.getStatus());
        receiveMapper.insertSelective(purchaseReceive);
        
    	for (GetReceiptGoodsDataVO getReceiptGoodsDataVO : goodsDataList) {
    		
    		PurchaseOrderDetail od = getReceiptGoodsDataVO.getPurchaseOrderDetail();
    		
    		PurchaseReceiveDetail purchaseReceiveDetail = new PurchaseReceiveDetail();
    		purchaseReceiveDetail.setEnterpriseId(loginUser.getEnterpriseId());
    		purchaseReceiveDetail.setParentId(loginUser.getParentId());
    		purchaseReceiveDetail.setReceiveId(purchaseReceive.getId());
    		purchaseReceiveDetail.setOrderType(OrderRule.PURCHASE_RECEIVE.getType());
    		
    		purchaseReceiveDetail.setReceiveCode(purchaseReceive.getCode());
    		purchaseReceiveDetail.setReceiveDate(purchaseReceive.getReceiveDate());
    		purchaseReceiveDetail.setBaseOrderDtlId(od.getId());
    		purchaseReceiveDetail.setBaseOrderId(purchaseOrder.getId());
    		purchaseReceiveDetail.setBaseOrderCode(od.getOrderCode());
            purchaseReceiveDetail.setBaseOrderDate(od.getOrderDate());
            purchaseReceiveDetail.setBaseOrderType(od.getOrderType());
            Goods goods = goodsMapper.selectByPrimaryKey(od.getGoodsId());
            if(goods.getSpecialDrug() != -1 && purchaseReceive.getSecondReceiverId() == null){
                throw new BusinessException("第"+od.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
            }
            purchaseReceiveDetail.setGoodsId(od.getGoodsId());
            purchaseReceiveDetail.setGoodsCode(od.getGoodsCode());
            purchaseReceiveDetail.setGoodsName(od.getGoodsName());
            purchaseReceiveDetail.setBarcode(goods.getBarcode());
            purchaseReceiveDetail.setGoodsGenericName(goods.getGenericName());
            purchaseReceiveDetail.setDosageId(goods.getDosageId());
            purchaseReceiveDetail.setDosageName(goods.getDosageName());
            purchaseReceiveDetail.setUnitId(goods.getUnitId());
            purchaseReceiveDetail.setUnitName(goods.getUnitName());
            purchaseReceiveDetail.setGoodsSpecification(goods.getSpecification());
            purchaseReceiveDetail.setManufacturerId(goods.getManufacturerId());
            purchaseReceiveDetail.setManufacturer(goods.getManufacturer());
            purchaseReceiveDetail.setGoodsPlace(goods.getPlace());
            purchaseReceiveDetail.setApprovalNumber(goods.getApprovalNumber());
            //set状态
            purchaseReceiveDetail.setOrderQuantity(od.getQuantity());
            purchaseReceiveDetail.setArrivalQuantity(od.getQuantity());
            purchaseReceiveDetail.setReceiveQuantity(getReceiptGoodsDataVO.getDeliverQty());
            purchaseReceiveDetail.setRefuseQuantity(od.getQuantity().subtract(getReceiptGoodsDataVO.getDeliverQty()));
            if(purchaseReceiveDetail.getRefuseQuantity().compareTo(BigDecimal.ZERO)>0){
            	purchaseReceiveDetail.setRefuseReasonIds("6");// 其他情况
            }
            purchaseReceiveDetail.setUnclearQuantity(BigDecimal.ZERO);
            purchaseReceiveDetail.setClearQuantity(purchaseReceiveDetail.getReceiveQuantity());
            purchaseReceiveDetail.setLineNum(od.getLineNum());
            purchaseReceiveDetail.setStatus(PurchaseStatus.FINISHED.getStatus());
            //set未清数量/以清数量
            //set收货单据id/code/类型/日期
            UserEnterpriseUtils.setUserCreateOrModify(purchaseReceiveDetail,loginUser,true);
            receiveDetailMapper.insert(purchaseReceiveDetail);
            
            getReceiptGoodsDataVO.setPurchaseReceiveDetail(purchaseReceiveDetail);
		}
        //保存收货配送跟结算
        PurchaseReceiveOther purchaseReceiveOther = new PurchaseReceiveOther();
        UserEnterpriseUtils.setUserCreateOrModify(purchaseReceiveOther,loginUser,true);
        purchaseReceiveOther.setEnterpriseId(loginUser.getEnterpriseId());
        purchaseReceiveOther.setParentId(loginUser.getParentId());
        purchaseReceiveOther.setStatus(PurchaseStatus.FINISHED.getStatus());
        purchaseReceiveOther.setReceiveId(purchaseReceive.getId());
        purchaseReceiveOther.setOrderId(purchaseReceive.getBaseOrderId());
        purchaseReceiveOther.setOrderCode(purchaseReceive.getBaseOrderCode());
        receiveOtherMapper.insertSelective(purchaseReceiveOther);
        
        return purchaseReceive;
    }

    private UserVO getUserVO(UserVO loginUser, ManageConfig config) {
        UserVO userVO = new UserVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(loginUser,userVO);
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getParentId() == 0L?loginUser.getEnterpriseId():loginUser.getParentId());

        if(loginUser.getParentId() != null && loginUser.getParentId() != 0){//门店用户
	        List<User> userList = userMapper.selectByEnterpriseId(enterprise.getId());
	        if(userList != null && userList.size()>0){
	          	userVO.setUserId(userList.get(0).getId());
	        	userVO.setUserCode(userList.get(0).getCode());
	        	userVO.setUserName(userList.get(0).getName());
	        }else{
	        	User u = userMapper.selectByPrimaryKey(config.getPurchaserId());
	        	userVO.setUserId(u.getId());
	        	userVO.setUserCode(u.getCode());
	        	userVO.setUserName(u.getName());
            }
        }
        userVO.setEnterpriseId(enterprise.getId());
        userVO.setChainType(enterprise.getChainType());
        userVO.setEnterpriseCode(enterprise.getCode());
        userVO.setEnterpriseName(enterprise.getName());
        userVO.setParentId(enterprise.getParentId());
        return userVO;
    }

    //总部采购订单
    private PurchaseOrder generatePurchaseOrder(GetReceiptDataVO getReceiptDataVO, ManageConfig config,  UserVO loginUser, UserVO loginUser2) throws Exception {
        PurchaseOrder order = new PurchaseOrder();
        List<PurchaseOrderDetail> orderDetails = new ArrayList<>();
        PurchaseOrderOther orderOther = new PurchaseOrderOther();
        order.setEnterpriseId(loginUser.getEnterpriseId());
        order.setParentId(loginUser.getParentId());
        order.setOrderType(OrderRule.PURCHASE_ORDER.getType());
        order.setOrderMode(0);//0是mph采购

        order.setBaseOrderId(getReceiptDataVO.getOrderId());
        order.setBaseOrderCode(getReceiptDataVO.getOrderCode());
        order.setBaseOrderDate(getReceiptDataVO.getPurchaseDate());
        
        order.setOrderDate(getReceiptDataVO.getPurchaseDate());
        order.setCode(codeComponent.generate(OrderRule.PURCHASE_ORDER.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
        order.setSupplierId(getReceiptDataVO.getSupplyId());
        order.setSupplierCode(getReceiptDataVO.getSupplyCode());
        order.setSupplierName(getReceiptDataVO.getSupplyName());
        order.setSupplierSalerId(getReceiptDataVO.getSupplierSalerId());
        order.setSupplierSalerCode(getReceiptDataVO.getSupplierSalerCode());
        order.setSupplierSalerName(getReceiptDataVO.getSupplierSalerName());
        order.setSupplierSalerPhone(getReceiptDataVO.getSupplierSalerPhone());
        BigDecimal quantityTotal = BigDecimal.ZERO;
        order.setQuantityTotal(quantityTotal);//数量合计
        Set goodsSet = new HashSet();
        order.setVarietiesQuantity(0);//品种合计
        BigDecimal amountTotal = BigDecimal.ZERO;
        order.setAmountTotal(amountTotal);//金额合计
        order.setWholeDiscount(BigDecimal.valueOf(100));
        order.setWholeDiscountAmount(BigDecimal.ZERO);
        BigDecimal realAmountTotal = BigDecimal.ZERO;
        order.setRealAmountTotal(realAmountTotal);//实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        order.setNotaxRealAmountTotal(notaxRealAmountTotal);//无税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        order.setTaxAmountTotal(taxAmountTotal);//税额合计

        if(loginUser2.getChainType() == ChainType.Headquarters.getCode()){//总部用户
        	order.setPurchaserId(getReceiptDataVO.getPurchaseManId());
	        User clerker = userMapper.selectByPrimaryKey(getReceiptDataVO.getPurchaseManId());
			if(clerker == null) {
				throw new BusinessException("采购人员ID不存在！");
			}
	        order.setPurchaserCode(clerker.getCode());
	        order.setPurchaserName(clerker.getName());
        }else{//门店用户
        	order.setPurchaserId(config.getPurchaserId());
        	order.setPurchaserCode(config.getPurchaserCode());
	        order.setPurchaserName(config.getPurchaserName());
        }
        order.setArrivalTime(new Date());
        order.setStatus(PurchaseStatus.FINISHED.getStatus());
        UserEnterpriseUtils.setUserCreateOrModify(order,loginUser,true);
        orderMapper.insert(order);
        int lineNum = 0;
        for(GetReceiptGoodsDataVO goodsDataVO : getReceiptDataVO.getGetReceiptGoodsDataVO()){
            PurchaseOrderDetail orderDetail = new PurchaseOrderDetail();
            orderDetail.setEnterpriseId(loginUser.getEnterpriseId());
            orderDetail.setParentId(loginUser.getParentId());
            
            orderDetail.setBaseOrderId(getReceiptDataVO.getOrderId());
            orderDetail.setBaseOrderCode(getReceiptDataVO.getOrderCode());
            orderDetail.setBaseOrderDate(getReceiptDataVO.getPurchaseDate());
            orderDetail.setBaseOrderDtlId(goodsDataVO.getOrderDetailId());
            
            orderDetail.setOrderId(order.getId());
            orderDetail.setOrderCode(order.getCode());
            orderDetail.setOrderDate(order.getOrderDate());
            orderDetail.setOrderType(order.getOrderType());
            orderDetail.setGoodsId(goodsDataVO.getGoodsId());
            orderDetail.setGoodsCode(goodsDataVO.getGoods().getCode());
            orderDetail.setBarcode(goodsDataVO.getGoods().getBarcode());
            orderDetail.setGoodsName(goodsDataVO.getGoods().getName());
            orderDetail.setGoodsGenericName(goodsDataVO.getGoods().getGenericName());
            orderDetail.setDosageId(goodsDataVO.getGoods().getDosageId());
            orderDetail.setDosageName(goodsDataVO.getGoods().getDosageName());
            orderDetail.setUnitId(goodsDataVO.getGoods().getUnitId());
            orderDetail.setUnitName(goodsDataVO.getGoods().getUnitName());
            orderDetail.setGoodsSpecification(goodsDataVO.getGoods().getSpecification());
            orderDetail.setManufacturerId(goodsDataVO.getGoods().getManufacturerId());
            orderDetail.setManufacturer(goodsDataVO.getGoods().getManufacturer());
            orderDetail.setGoodsPlace(goodsDataVO.getGoods().getPlace());
            orderDetail.setApprovalNumber(goodsDataVO.getGoods().getApprovalNumber());
            BigDecimal quantity = BigDecimal.ZERO;
            Long taxRateId = 0L;
            BigDecimal taxRate = BigDecimal.ZERO;
            for(GetReceiptGoodsLotDataVO lotDataVO : goodsDataVO.getGetReceiptGoodsLotDataVO()){
                quantity = quantity.add(lotDataVO.getReceiveQuantity());
            }
            taxRateId = goodsDataVO.getTaxRateId();
            taxRate = getTaxRate(taxRateId);
            orderDetail.setQuantity(quantity);
            orderDetail.setUnitPrice(goodsDataVO.getPurchasePrice());
            GoodsBusiness business = getGoodsRateInfo(goodsDataVO.getGoodsId(),loginUser);
            orderDetail.setMemberPrice(business.getMemberPrice());
            orderDetail.setRetailPrice(business.getRetailPrice());
            orderDetail.setDistrPrice(business.getDistrPrice());
            orderDetail.setLineDiscount(BigDecimal.valueOf(100));
            orderDetail.setAmount(orderDetail.getUnitPrice().multiply(orderDetail.getQuantity()).setScale(2,RoundingMode.HALF_UP));
            orderDetail.setWholeDiscount(BigDecimal.valueOf(100));
            orderDetail.setLineDiscountAmount(BigDecimal.ZERO);
            orderDetail.setRealPrice(orderDetail.getUnitPrice());
            orderDetail.setRealAmount(orderDetail.getAmount());
            orderDetail.setTaxRateId(taxRateId);
            orderDetail.setTaxRate(taxRate);
            orderDetail.setTaxAmount(taxRate.divide(BigDecimal.valueOf(100)).multiply(orderDetail.getUnitPrice()).multiply(orderDetail.getQuantity()).setScale(2,RoundingMode.HALF_UP));
            orderDetail.setNotaxRealPrice(orderDetail.getUnitPrice().subtract(orderDetail.getUnitPrice().multiply(taxRate.divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP))).setScale(2,RoundingMode.HALF_UP));
            orderDetail.setNotaxRealAmount(orderDetail.getAmount().subtract(orderDetail.getTaxAmount()));
            orderDetail.setUnclearQuantity(BigDecimal.ZERO);
            orderDetail.setClearQuantity(orderDetail.getQuantity());
            orderDetail.setLineNum(lineNum++);
            orderDetail.setStatus(order.getStatus());
            UserEnterpriseUtils.setUserCreateOrModify(orderDetail,loginUser,true);
            orderDetailMapper.insert(orderDetail);

            goodsDataVO.setPurchaseOrderDetail(orderDetail);

            quantityTotal = quantityTotal.add(orderDetail.getQuantity());
            goodsSet.add(orderDetail.getGoodsId());
            amountTotal = amountTotal.add(orderDetail.getAmount());
            realAmountTotal = realAmountTotal.add(orderDetail.getRealAmount());
            notaxRealAmountTotal = notaxRealAmountTotal.add(orderDetail.getNotaxRealAmount());
            taxAmountTotal = taxAmountTotal.add(orderDetail.getTaxAmount());
        }
        order.setQuantityTotal(quantityTotal);
        order.setVarietiesQuantity(goodsSet.size());
        order.setAmountTotal(amountTotal);
        order.setRealAmountTotal(realAmountTotal);
        order.setNotaxRealAmountTotal(notaxRealAmountTotal);
        order.setTaxAmountTotal(taxAmountTotal);
        orderMapper.updateByPrimaryKeySelective(order);

        orderOther.setEnterpriseId(loginUser.getParentId() == 0L?loginUser.getEnterpriseId():loginUser.getParentId());
        orderOther.setParentId(0L);
        orderOther.setOrderId(order.getId());
        orderOther.setSettlementType(0);
        orderOther.setSettlementUnitId(loginUser.getEnterpriseId());
        orderOther.setSettlementUnitName(loginUser.getEnterpriseName());

        if(loginUser.getParentId() == null || loginUser.getParentId() == 0){//总部用户

        	orderOther.setReveiverId(getReceiptDataVO.getReceiveManId());
        	User clerker = userMapper.selectByPrimaryKey(getReceiptDataVO.getReceiveManId());
        	if(clerker == null) {
        		throw new BusinessException("收货人员ID不存在！");
        	}
            orderOther.setReceiverCode(clerker.getCode());
            orderOther.setReceiverName(clerker.getName());
        }else{//门店用户

        	orderOther.setReveiverId(config.getReceiverId());
        	orderOther.setReceiverCode(config.getReceiverCode());
        	orderOther.setReceiverName(config.getReceiverName());
        }


        orderOther.setInvoiceType(0);
        orderOther.setReceiveUnit(loginUser.getEnterpriseName());
        orderOther.setReceiveUnitCode(loginUser.getEnterpriseCode());
        orderOther.setReceiveUnitId(loginUser.getEnterpriseId());
        orderOther.setReceiveUnitName(loginUser.getEnterpriseName());
        UserEnterpriseUtils.setUserCreateOrModify(orderOther,loginUser,true);
        orderOther.setStatus(order.getStatus());
        orderOtherMapper.insertSelective(orderOther);
        return order;
    }

    //取配置信息
    private ManageConfig getConfigInfo(Long enterpriseId) throws Exception{
        ManageConfig config = configMapper.getMangeConfigByEPId(enterpriseId);
        if (config == null){
            config = new ManageConfig();
        }
        return config;
    }
    //取商品的默认货位
    private SafetyStock getDefauleShelf(Long goodsId, UserVO loginUser){
        SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(goodsId,loginUser.getEnterpriseId());
        if(safetyStock.getDefaultShelfId() == null)
            throw new BusinessException("未设置默认货位，请先设置默认货位\n!");
        return safetyStock;
    }
    //取商品的默认税率
    private GoodsBusiness getGoodsRateInfo(Long goodsId, UserVO loginUser){
        Map<String,Object> param = new HashMap<>();
        param.put("goodsId",goodsId);
        param.put("enterpriseId",(loginUser.getParentId() == null||loginUser.getParentId()==0)?loginUser.getEnterpriseId():loginUser.getParentId());
        GoodsBusiness business = businessMapper.selectByEnterpriseIdAndGoodsId(param);
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(business == null || business.getPurchaseTaxRateId() == null || business.getPurchaseTaxRate() == null)
            throw new BusinessException(goods.getName() + "没有设置商品的默认税率,请先设置!");
        return business;
    }
    private String getCode(String codePrefix,Long enterpriseId,String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix,enterpriseId,enterpriseCode);
    }



	@Override
	public SelectBoundGoodsVO getGoodsDefaultShelt(UserVO loginUser,
			Long goodsId) {
		SafetyStock stock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(goodsId, loginUser.getEnterpriseId());
		
		SelectBoundGoodsVO goodsVO = new SelectBoundGoodsVO();
		
		if(stock != null){
			goodsVO.setShelfId(stock.getDefaultShelfId());
			goodsVO.setShelfName(stock.getDefaultShelfName());
		}
		
		return goodsVO;
	}

}
