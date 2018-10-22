package com.rograndec.feijiayun.chain.business.storage.inventory.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.app.SpringBeanFactory;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.Warehouse;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseCargoAreaSimpleVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsStockInfoVO;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.InOutRecordDetailMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.InOutRecordDetail;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.constant.InvType;
import com.rograndec.feijiayun.chain.business.storage.inventory.constant.InventoryGoodsInfoConstant;
import com.rograndec.feijiayun.chain.business.storage.inventory.constant.RegisterType;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.Inventory;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryDetail;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryShelf;
import com.rograndec.feijiayun.chain.business.storage.inventory.excel.InventoryRegisterIRowReader;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.storage.inventory.service.InventoryOrderService;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.*;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.InventoryForDiffDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.InventoryShelfSimpleForDiffVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.RequestParamForDiffListVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterExcelVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterOKVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.ResponseGoodsRegisterExcelVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.*;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO2;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryGoodsInfoVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.RequestParamForHadRegisterListVO;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.FileUtils;
import com.rograndec.feijiayun.chain.utils.TwoTuple;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelReaderUtil;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.pinyin.PinYinUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述：
 * Created by ST on 2017/9/29 13:29
 */
@Service
public class InventoryOrderServiceImpl implements InventoryOrderService {

    private static Logger logger = LoggerFactory.getLogger(InventoryOrderServiceImpl.class);

    @Autowired
    private InventoryMapper inventoryMapper;


    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private WarehouseAreaMapper warehouseAreaMapper;

    @Autowired
    private WarehouseCargoAreaMapper cargoAreaMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private InventoryDetailMapper inventoryDetailMapper;

    @Autowired
    private InventoryShelfMapper inventoryShelfMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private CostMapper costMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private PriceOrderMapper priceOrderMapper;

//    @Autowired
//    private InventoryRegisterIRowReader inventoryRegisterIRowReader;

    @Autowired
    private RedisComponent redisComponent;


    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;

    @Autowired
    private InOutRecordDetailMapper inOutRecordDetailMapper;
    @Autowired

    private ExcelComponent excelComponent;

    @Autowired
    FinanceComponent financeComponent;


    /**
     * 盘点单查询
     * @param page
     * @param paramForListVO
     * @param enterpriseId
     */
    @Override
    public void getInventoryList(Page<List<InventoryForOrderListVO>> page, RequestParamForListVO paramForListVO, Long enterpriseId) {
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(paramForListVO.getPageNo(), paramForListVO.getPageSize());
        List<InventoryForOrderListVO> inventoryList = inventoryMapper.getInventoryList(paramForListVO, enterpriseId);
        page.setResult(inventoryList);
        page.setTotalRecord((int)objects.getTotal());
    }

    /**
     * 查询当前企业的仓库
     *
     * @param enterpriseId
     * @return
     */
    @Override
    public List<WarehouseVO> getWarehouseVO(Long enterpriseId) {
        return warehouseMapper.getWarehouseByEnterpriseId(enterpriseId);
    }

    /**
     * 查询当前企业的库区
     *
     * @param enterpriseId
     * @return
     */
    @Override
    public List<WarehouseAreaVO> getWarehouseAreaByEnterpriseIdAndHID(Long enterpriseId,Long warehouseId) {
        return warehouseMapper.getWarehouseAreaByEnterpriseIdAndHID(enterpriseId,warehouseId);
    }

    /**
     * 查询当前企业的货位
     *
     * @param enterpriseId
     * @return
     */
    @Override
    public List<WarehouseCargoAreaSimpleVO> getWarehouseCargoAreaByEnterpriseIdAndHID(Long enterpriseId, List<Long> warehouseAreaIds) {
        return warehouseMapper.getWarehouseCargoAreaByEnterpriseIdAndHID(enterpriseId,warehouseAreaIds);
    }

    /**
     * 搜索商品
     * @param enterpriseId
     * @param goodsStockVO
     * @return
     */
    @Override
    public List<GoodsStockInfoVO> getGoodsInfoForInventory(Long enterpriseId, RequestGoodsStockVO goodsStockVO){
        Integer invType = goodsStockVO.getInvType();
        String param = goodsStockVO.getParam();
        List<Long> shelfIds = new ArrayList<>();
        //仓库id
        Long warehouseId = goodsStockVO.getWarehouseId();
        //库区ids
        List<Long> warehouseAreaIds = goodsStockVO.getWarehouseAreaIds();
        //货区ids
        List<Long> warehouseCargoAreaIds = goodsStockVO.getWarehouseCargoAreaIds();

        if(warehouseId != null){
            shelfIds = getShelfIds(enterpriseId, warehouseId, warehouseAreaIds, warehouseCargoAreaIds);
            if(CollectionUtils.isEmpty(shelfIds)){
               return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
        List<GoodsStockInfoVO> goodsList = new ArrayList<>();
        if(invType == InvType.SHELF.getCode()){
            //按货位
            goodsList = goodsMapper.getGoodsInfoForInventory(shelfIds, enterpriseId, param,null,null,goodsStockVO.getSelectAll());
        } else if(invType == InvType.GOODS.getCode()){
            //按商品
            goodsList = goodsMapper.getGoodsInfoForInventoryGroupByGoodsId(shelfIds, enterpriseId, param,null,null,goodsStockVO.getSelectAll());
        }
        return goodsList;
    }

    private List<Long> getShelfIds(Long enterpriseId, Long warehouseId, List<Long> warehouseAreaIds, List<Long> warehouseCargoAreaIds) {
        List<Long> shelfIds;
        if(CollectionUtils.isEmpty(warehouseAreaIds)){
            warehouseAreaIds = null;
        }
        if(CollectionUtils.isEmpty(warehouseCargoAreaIds)){
            warehouseCargoAreaIds = null;
        }
        List<Long> warehouseIds = Arrays.asList(warehouseId);
        List<WarehouseShelfVO> shelfVOList = warehouseMapper.getShelfIdByParam(enterpriseId, EnableStatus.ENABLE.getStatus(),warehouseIds,warehouseAreaIds,warehouseCargoAreaIds);
        shelfIds = shelfVOList.stream().map(shelf -> shelf.getId()).collect(Collectors.toList());
        return shelfIds;
    }

    @Override
    public void getGoodsInfoForInventoryPage(Page<List<GoodsStockInfoVO>> page, RequestGoodsStockVOPage goodsStockVO, Long enterpriseId) {
        Integer invType = goodsStockVO.getInvType();
        String param = goodsStockVO.getParam();
        List<Long> shelfIds = new ArrayList<>();
        //仓库id
        Long warehouseId = goodsStockVO.getWarehouseId();
        //库区ids
        List<Long> warehouseAreaIds = goodsStockVO.getWarehouseAreaIds();
        //货区ids
        List<Long> warehouseCargoAreaIds = goodsStockVO.getWarehouseCargoAreaIds();
        Integer count = 0;
        List<GoodsStockInfoVO> goodsList = new ArrayList<>();
        if(warehouseId != null){
            shelfIds = getShelfIds(enterpriseId, warehouseId, warehouseAreaIds, warehouseCargoAreaIds);
            if(CollectionUtils.isEmpty(shelfIds)){
                page.setResult(goodsList);
                page.setTotalRecord(count);
                return;
            }
        } else {
            page.setResult(goodsList);
            page.setTotalRecord(count);
            return;
        }


        if(invType == InvType.SHELF.getCode()){
            //按货位
            goodsList = goodsMapper.getGoodsInfoForInventory(shelfIds, enterpriseId, param,page.getStart(),page.getPageSize(),goodsStockVO.getSelectAll());
            count = goodsMapper.getGoodsInfoForInventoryCount(shelfIds,enterpriseId,param,page.getStart(),page.getPageSize());
        } else if(invType == InvType.GOODS.getCode()){
            //按商品
            goodsList = goodsMapper.getGoodsInfoForInventoryGroupByGoodsId(shelfIds, enterpriseId, param,page.getStart(),page.getPageSize(),goodsStockVO.getSelectAll());
            count = goodsMapper.getGoodsInfoForInventoryGroupByGoodsIdCount(shelfIds,enterpriseId,param,page.getStart(),page.getPageSize());
        }
        page.setResult(goodsList);
        page.setTotalRecord(count);
    }

    @Override
    public ResponseGoodsRegisterExcelVO excelImport(HttpServletRequest request) throws Exception{
        InventoryRegisterIRowReader inventoryRegisterIRowReader = SpringBeanFactory.getBean(InventoryRegisterIRowReader.class);
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        String id = request.getParameter("id");//盘点单id
        String registerType = request.getParameter("registerType");
        if(id == null){
            throw new RuntimeException("盘点单id 不能为空");
        }
        Inventory inventory = inventoryMapper.selectByPrimaryKey(Long.parseLong(id));
        if(inventory == null){
            throw new RuntimeException("盘点单不存在");
        }
        Integer invType = inventory.getInvType();//0/按货位； 1/按商品

        inventoryRegisterIRowReader.setInvType(invType);
        inventoryRegisterIRowReader.setUserVO(userVO);
        inventoryRegisterIRowReader.setInvId(Long.valueOf(id));

        Long enterpriseId = userVO.getEnterpriseId();
        Part part = request.getPart("file");
        InputStream input = part.getInputStream();
        //合格产品
        List<GoodsShelfForRegisterExcelVO> qualifiedList = new ArrayList<>();
        //不合格产品
        List<GoodsShelfForRegisterExcelVO> disqualificationList = new ArrayList<>();

        //标准入库实体list
        List<GoodsShelfForRegisterOKVO> qualifiedGVOList = new ArrayList<>();
        inventoryRegisterIRowReader.setQualifiedList(qualifiedList);
        inventoryRegisterIRowReader.setDisqualificationList(disqualificationList);

        inventoryRegisterIRowReader.setQualifiedGVOList(qualifiedGVOList);


        if(invType == InvType.SHELF.getCode()){
            //按货位
            ExcelReaderUtil.excelToArrayList(inventoryRegisterIRowReader, FileUtils.getFileName(part), input, 10, 0);
        } else {
            //按商品
            ExcelReaderUtil.excelToArrayList(inventoryRegisterIRowReader, FileUtils.getFileName(part), input, 7, 0);
        }
        if(registerType == RegisterType.BOOK_REGISTER.getValue()){
            //excel中包含的盘点的商品信息
            List<Long> excelInventoryShelfIdList = inventoryRegisterIRowReader.getInventoryShelfIdList();
            //盘点单的包含盘点商品的数量
            List<InventoryShelf> inventoryShelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId, null, Long.valueOf(id), null,"asc");
            if(excelInventoryShelfIdList.size() < inventoryShelfList.size()){
                throw new InventoryBizException("","Excel中包含的商品信息数量少于对应盘点单的商品的数量");
            }
        }


        ResponseGoodsRegisterExcelVO goodsExcelVO = new ResponseGoodsRegisterExcelVO();

        goodsExcelVO.setQualifiedCount(qualifiedList.size());
        goodsExcelVO.setDisqualificationCount(disqualificationList.size());
        //goodsExcelVO.setQualifiedGoodsList(inventoryRegisterIRowReader.getQualifiedList());
        //goodsExcelVO.setDisqualificationGoodsList(inventoryRegisterIRowReader.getDisqualificationList());

        //向redis中存储合格/不合格的商品
        Long timestamp = System.currentTimeMillis();
        List<GoodsShelfForRegisterExcelVO> qualifiedList2Redis = inventoryRegisterIRowReader.getQualifiedList();
        List<GoodsShelfForRegisterExcelVO> disQualifiedList2Redis = inventoryRegisterIRowReader.getDisqualificationList();
        List<GoodsShelfForRegisterOKVO> qualifiedGVOList2Redis = inventoryRegisterIRowReader.getQualifiedGVOList();
        inventoryRegisterIRowReader.getInventoryShelfIdList().clear();

        redisComponent.set(InventoryGoodsInfoConstant.INVENTORY_QUALIFIED_FIELD + timestamp, JSON.toJSONString(qualifiedList2Redis));
        redisComponent.set(InventoryGoodsInfoConstant.INVENTORY_DISQUALIFIED_FIELD + timestamp,JSON.toJSONString(disQualifiedList2Redis));
        redisComponent.set(InventoryGoodsInfoConstant.INVENTORY_STANDARD_QUALIFIED_FIELD + timestamp,JSON.toJSONString(qualifiedGVOList2Redis));

        goodsExcelVO.setTimestamp(timestamp.toString());
        return goodsExcelVO;
    }


    @Override
    public List<GoodsShelfForRegisterOKVO> getSuccessData(String key) throws Exception {
        String kk = (String) redisComponent.get(InventoryGoodsInfoConstant.INVENTORY_STANDARD_QUALIFIED_FIELD + key);
        List<GoodsShelfForRegisterOKVO> list =  JSON.parseArray(kk,GoodsShelfForRegisterOKVO.class);  //SerializeUtil.deserialize(kk);
        return list;
    }

    @Override
    public Integer getInventoryCountData(UserVO userVO, Long invId) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Inventory inventory = inventoryMapper.selectByPrimaryKey(invId);
        if(inventory.getInvType() == InvType.SHELF.getCode()){
            List<InventoryShelf> inventoryShelfByParam = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId, null, invId, null,"asc");
            if(inventoryShelfByParam != null){
                return inventoryShelfByParam.size();
            }
        } else if(inventory.getInvType() == InvType.GOODS.getCode()){
            List<InventoryDetailForOrderDetailVO> inventoryShelfByParam = inventoryDetailMapper.getDetailByInvId(invId,enterpriseId);
            if(inventoryShelfByParam != null){
                return inventoryShelfByParam.size();
            }
        }
        return null;
    }



    /**
     * 导出成功数据
     *
     * @param output
     * @param key
     * @param type 1--导出成功数据；2--导出失败数据
     * @return
     * @throws Exception
     */
    @Override
    public void exportGoods(OutputStream output, String key, Integer type,Integer invType) throws Exception {
        List<GoodsShelfForRegisterExcelVO> list = new ArrayList<>();
        if(type == 1){
            list = JSON.parseArray((String) redisComponent.get(InventoryGoodsInfoConstant.INVENTORY_QUALIFIED_FIELD + key),GoodsShelfForRegisterExcelVO.class);
        } else {
            list = JSON.parseArray((String) redisComponent.get(InventoryGoodsInfoConstant.INVENTORY_DISQUALIFIED_FIELD + key),GoodsShelfForRegisterExcelVO.class);
        }
        createExcel(output, list,invType);
    }

    private void createExcel(OutputStream output, List<GoodsShelfForRegisterExcelVO> list,Integer invType) {
        try {
            final List<GoodsShelfForRegisterExcelVO> finalList = list;
            ExcelWriter writer = new ExcelWriter() {
                public void generate() throws Exception {
                    // 电子表格开始
                    this.beginWorkSheet();
                    this.beginSheet();
                    createRowHeader(this,invType);
                    for (int rowNum = 0; rowNum < finalList.size(); rowNum++) {
                        GoodsShelfForRegisterExcelVO goodsVO = finalList.get(rowNum);
                        // 插入新行
                        this.insertRow(rowNum + 1);
                        // 建立新单元格,索引值从0开始,表示第一列
                        int k = 0;
                        this.createCell(k++, rowNum + 1);
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getGoodsCode()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getGoodsGenericName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getDosageName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getUnitName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getManufacturer()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getGoodsPlace()));
                        if(invType == InvType.SHELF.getCode()){
                            this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getLotNumber()));
                            this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getProductDate()));
                            this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getValidDate()));
                            this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getShelfName()));
                        }

                        this.createCell(k, StringUtil.transferTrimStr(goodsVO.getInvQuantity()));

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
    private void createRowHeader(ExcelWriter writer,Integer invType) throws IOException {
        // 插入新行
        writer.insertRow(0);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "序号");
        writer.createCell(k++, "商品编码");
        writer.createCell(k++, "通用名称");
        writer.createCell(k++, "规格");
        writer.createCell(k++, "库存计量单位");
        writer.createCell(k++, "生产厂商");
        writer.createCell(k++, "产地");
        if(invType == InvType.SHELF.getCode()){
            writer.createCell(k++, "批号");
            writer.createCell(k++, "生产日期");
            writer.createCell(k++, "有效期至");
            writer.createCell(k++, "货位");
        }
        writer.createCell(k, "实盘数量");
        // 结束行
        writer.endRow();
    }

    @Override
    public void cancelInventoryOrder(UserVO userVO, Long id) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Inventory inventory = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
        inventory.setId(id);
        inventory.setStatus(PubStatus.inventoryOrderStatus.CANCELED);
        inventoryMapper.updateByPrimaryKeySelective(inventory);
        inventoryDetailMapper.updateDetailByInvId(id,PubStatus.inventoryOrderStatus.CANCELED,enterpriseId);
        inventoryShelfMapper.updateShelfByInvId(id,PubStatus.inventoryOrderStatus.CANCELED,enterpriseId);
        //更新库存中商品的盘点状态为正常
        updateStockGoodsInventoryStatus(enterpriseId, inventory.getId(), InventoryStatus.NORMAL.getStatus());

    }

    /**
     * 盘点登记按商品登记查询商品
     *
     * @param userVO
     * @param invId
     * @param param
     * @return
     */
    @Override
    public List<InventoryGoodsInfoVO> getGoodsInfoInInventory(UserVO userVO, Long invId, String param) {
        Inventory inventory = inventoryMapper.selectByPrimaryKey(invId);
        if(inventory.getInvType() == InvType.SHELF.getCode()){
            return inventoryDetailMapper.getGoodsInfoInInventory2Shelf(invId,param,userVO.getEnterpriseId());
        } else if(inventory.getInvType() == InvType.GOODS.getCode()){
            return inventoryDetailMapper.getGoodsInfoInInventory(invId,param,userVO.getEnterpriseId());
        }
        return new ArrayList<>();

    }

    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<InventoryForRegisterVO2> draftCache) {

        if(null == draftCache.getBaseOrderId() || 0L == draftCache.getBaseOrderId()) throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"上游单据id不存在!");

        String redisKeyValue = draftCache.getId();

        DraftCacheVO<InventoryForRegisterVO2> draftCacheVO = new DraftCacheVO();

        draftCacheVO.setOrderCode(OrderRule.INVENTORY.getCodePrefix());

        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());

        draftCacheVO.setOrderData(draftCache.getOrderData());
        draftCacheVO.setId(redisKeyValue);
        draftCacheVO.setBaseOrderId(draftCache.getBaseOrderId());
        DraftCacheVO dcv = redisComponent.saveDraftCacheVO(draftCacheVO);
        return dcv;
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) {
        redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO) {
        Class<InventoryForRegisterVO2> clazz = InventoryForRegisterVO2.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),OrderRule.INVENTORY.getCodePrefix(),clazz);
    }

    @Override
    public DraftCacheVO getDraftCacheVO(UserVO userVO, Long baseOrderId) {
        Class<InventoryForRegisterVO2> clazz = InventoryForRegisterVO2.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),baseOrderId,OrderRule.INVENTORY.getCodePrefix(),clazz);
    }

    /**
     * 保存盘点单
     *
     * @param userVO
     * @param inventoryForAddVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveInventoryOrder(UserVO userVO, InventoryForAddVO inventoryForAddVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = PubStatus.inventoryOrderStatus.WAIT_REGISTER;

        Inventory inventory = (Inventory) EntityUtils.reflectAddSetDefaultValue(Inventory.class,userVO);
        checkManageConfig(userVO, inventoryForAddVO, inventory);
        inventory.setCreateTime(new Date());


        //封装盘点信息
        packInventoryInfo(userVO, inventoryForAddVO, inventory);

        //盘点单据编码
        String code = orderCodeComponent.generate(OrderRule.INVENTORY.getCodePrefix(),userVO.getEnterpriseId(),userVO.getEnterpriseCode());
        inventory.setCode(code);
        inventory.setOrderType(OrderRule.INVENTORY.getType());
        inventory.setStatus(status);
        inventoryMapper.insertSelective(inventory);



        saveAndUpdateInventory(userVO, inventoryForAddVO, enterpriseId, inventory,code,status);
    }

    private BigDecimal packShelfInfoForAdd(UserVO userVO, Long enterpriseId, Inventory inventory, InventoryDetail detail, Long goodsId,Integer status) throws Exception {
        List<GoodsInfoStockShelfVO> goodsStockInfoList = stockMapper.getGoodsStockInfoByGoodsId(enterpriseId, goodsId);
        BigDecimal quantity = BigDecimal.ZERO;
        //商品货位明细
        for(GoodsInfoStockShelfVO goodsShelfVO : goodsStockInfoList){
            InventoryShelf shelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
            shelf.setDtlId(detail.getId());
            shelf.setInvId(inventory.getId());
            shelf.setStatus(status);
            shelf.setLineNum(detail.getLineNum());
            packGoodsShelfInfo(goodsId, goodsShelfVO, shelf,BigDecimal.ZERO);
            inventoryShelfMapper.insertSelective(shelf);
            quantity = quantity.add(goodsShelfVO.getQuantity());
        }
        InventoryDetail updateDetail =(InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class,userVO);
        updateDetail.setId(detail.getId());
        updateDetail.setQuantity(quantity);
        inventoryDetailMapper.updateByPrimaryKeySelective(updateDetail);
        return quantity;
    }

    /**
     * 修改盘点单
     * @param userVO
     * @param inventoryForAddVO
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateInventoryOrder(UserVO userVO,InventoryForAddVO inventoryForAddVO) throws Exception {

        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = PubStatus.inventoryOrderStatus.WAIT_REGISTER;
        //盘点总单id
        Long invId = inventoryForAddVO.getId();
        Inventory inventory = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
        inventory.setId(invId);
        if(inventory == null){
            throw new BusinessException("盘点单不存在！！");
        }

        checkManageConfig(userVO, inventoryForAddVO, inventory);

        //更新盘点单
        inventoryMapper.updateByPrimaryKeySelective(inventory);
        inventory = inventoryMapper.selectByPrimaryKey(invId);
        //更新商品库存的盘点状态为正常
        updateStockGoodsInventoryStatus(enterpriseId, inventory.getId(), InventoryStatus.NORMAL.getStatus());
        //删除盘点明细,删除盘点货位明细
        inventoryDetailMapper.deleteByInvId(invId);
        inventoryShelfMapper.deleteShelfByInvId(enterpriseId,invId);
        saveAndUpdateInventory(userVO, inventoryForAddVO, enterpriseId, inventory,inventory.getCode(),status);

    }

    private void saveAndUpdateInventory(UserVO userVO, InventoryForAddVO inventoryForAddVO, Long enterpriseId, Inventory inventory,String code,Integer status) throws Exception {
        Integer invType = inventoryForAddVO.getInvType();
        Long inventoryId = inventory.getId();
        List<InventoryDetailForAddVO> detailForAddVOList = inventoryForAddVO.getDetailForAddVOList();

        BigDecimal quantityAllTotal = BigDecimal.ZERO;
        Set<Long> varietiesQuantitySet = new HashSet<>();//品种数量
        if(invType == InvType.SHELF.getCode()){
            //按货位
            /**
             * key 商品id;value 账面数量
             */
            Map<Long,InventoryDetailForAddVO> goods2QuantityMap = new HashMap<>();
            for (InventoryDetailForAddVO item : detailForAddVOList) {
                if(item.getLineNum() == null) throw new BusinessException("行号不能为空");
                varietiesQuantitySet.add(item.getGoodsId());
                Long goodsId = item.getGoodsId();//商品id
                Long shelfId = item.getShelfId();
                Long lotId = item.getLotId();
                GoodsInfoStockShelfVO goodsStockInfo = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(enterpriseId, goodsId, shelfId, lotId);
                //账面数量
                BigDecimal quantity = goodsStockInfo.getQuantity();
                quantityAllTotal = quantityAllTotal.add(quantity);
                //统计账面数量
                InventoryDetailForAddVO  inventoryDetailForAddVO = goods2QuantityMap.get(goodsId);
                if (inventoryDetailForAddVO == null) {
                    inventoryDetailForAddVO = new InventoryDetailForAddVO();
                    inventoryDetailForAddVO.setQuantity(quantity);
                    inventoryDetailForAddVO.setInvQuantity(BigDecimal.ZERO);
                    inventoryDetailForAddVO.setLineNum(item.getLineNum());
                    goods2QuantityMap.put(goodsId, inventoryDetailForAddVO);
                } else {
                    //已存在的该商品
                    BigDecimal tmpQuantity = inventoryDetailForAddVO.getQuantity().add(quantity);
                    BigDecimal tmpInvQuantity = inventoryDetailForAddVO.getInvQuantity().add(BigDecimal.ZERO);
                    BigDecimal diffQuantity = tmpInvQuantity.subtract(tmpQuantity);
                    inventoryDetailForAddVO.setQuantity(tmpQuantity);
                    inventoryDetailForAddVO.setInvQuantity(tmpInvQuantity);
                    inventoryDetailForAddVO.setDiffQuantity(diffQuantity);
                    inventoryDetailForAddVO.setLineNum(item.getLineNum());
                }
            }
            Map<Long,Long> goodsIdDetailIdMap = new HashMap<>();
            //创建明细实例
            goods2QuantityMap.forEach((Long goodsId, InventoryDetailForAddVO detailForAddVO) ->{
                InventoryDetail detail = null;
                try {
                    detail = (InventoryDetail) EntityUtils.reflectAddSetDefaultValue(InventoryDetail.class,userVO);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("");
                }
                //设置盘点单的属性
                detail.setInvDate(inventory.getInvDate());
                detail.setStatus(PubStatus.inventoryOrderStatus.WAIT_REGISTER);
                //商品属性
                packGoodsInfo(detail, goodsId);
                detail.setInvId(inventoryId);
                detail.setInvCode(code);
                detail.setInvDate(new Date());
                detail.setOrderType(OrderRule.INVENTORY.getType());
                detail.setStatus(status);
                detail.setQuantity(detailForAddVO.getQuantity());
                detail.setInvQuantity(detailForAddVO.getInvQuantity());
                detail.setDiffQuantity(detailForAddVO.getDiffQuantity());
                detail.setLineNum(detailForAddVO.getLineNum());
                inventoryDetailMapper.insertSelective(detail);
                goodsIdDetailIdMap.put(goodsId,detail.getId());
            });

            //创建盘点货位明细
            for (InventoryDetailForAddVO item : detailForAddVOList) {
                Long goodsId = item.getGoodsId();//商品id
                Long shelfId = item.getShelfId();
                Long lotId = item.getLotId();
                Long detailId = goodsIdDetailIdMap.get(goodsId);
                InventoryShelf shelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
                shelf.setDtlId(detailId);
                shelf.setInvId(inventoryId);
                shelf.setStatus(status);
                Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
                GoodsInfoStockShelfVO goodsStockInfo = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(enterpriseId, goodsId, shelfId, lotId);
                BigDecimal quantity = goodsStockInfo.getQuantity();//账面数量
                shelf.setGoodsId(goodsId);
                shelf.setGoodsCode(goods.getCode());
                shelf.setGoodsName(goods.getName());
                shelf.setLotId(lotId);
                shelf.setLotNumber(goodsStockInfo.getLotNum());
                shelf.setValidDate(goodsStockInfo.getValidUtil());
                shelf.setProductDate(goodsStockInfo.getProductDate());
                shelf.setShelfId(shelfId);
                shelf.setShelfName(goodsStockInfo.getShelfName());
                shelf.setShelfStatusDesc(goodsStockInfo.getShelfStatusDesc());
                shelf.setQuantity(quantity);
                shelf.setInvQuantity(BigDecimal.ZERO);//实盘数量
                shelf.setLineNum(item.getLineNum());
                inventoryShelfMapper.insertSelective(shelf);
            }

        } else {
            //按商品
            //保存盘点明细
            //商品属性

            for (InventoryDetailForAddVO item: detailForAddVOList) {
                if(item.getLineNum() == null) throw new BusinessException("行号不能为空");
                InventoryDetail detail =(InventoryDetail) EntityUtils.reflectAddSetDefaultValue(InventoryDetail.class,userVO);
                varietiesQuantitySet.add(item.getGoodsId());
                Long goodsId = item.getGoodsId();
                //设置盘点单的属性
                detail.setInvDate(inventory.getInvDate());
                detail.setStatus(status);
//                detail.setQuantity(quantityTotal);
                detail.setInvId(inventoryId);
                detail.setInvCode(code);
                detail.setInvDate(new Date());
                detail.setOrderType(OrderRule.INVENTORY.getType());
                //按商品
                //保存盘点明细
                //商品属性
                packGoodsInfo(detail, goodsId);
                detail.setLineNum(item.getLineNum());
                inventoryDetailMapper.insertSelective(detail);
                BigDecimal quantityTotal =  packShelfInfoForAdd(userVO, enterpriseId, inventory, detail, goodsId,status);
                detail.setQuantity(quantityTotal);
                quantityAllTotal = quantityAllTotal.add(quantityTotal);
            }

        }
        inventory.setQuantityTotal(quantityAllTotal);
        inventory.setVarietiesQuantity(varietiesQuantitySet.size());
        inventoryMapper.updateByPrimaryKeySelective(inventory);

        Integer inventoryStatus = InventoryStatus.IN_INVENTORY.getStatus();
        updateStockGoodsInventoryStatus(enterpriseId, inventoryId, inventoryStatus);
    }

    private void updateStockGoodsInventoryStatus(Long enterpriseId, Long inventoryId, Integer inventoryStatus) {
        List<InventoryShelf> shelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId, null, inventoryId, null,"asc");
        shelfList.forEach(item->{
            //更新库存盘点状态
            stockMapper.updateStockInventoryStatus(enterpriseId,item.getGoodsId(),item.getLotId(),item.getShelfId(), inventoryStatus);
        });
    }

    private void checkManageConfig(UserVO userVO, InventoryForAddVO inventoryForAddVO, Inventory inventory) {
        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        if(mangeConfig.getBusinessControl() == 0){
            //关闭
            User user = userMapper.selectByPrimaryKey(inventoryForAddVO.getCreaterId());
            inventory.setCreaterId(user.getId());
            inventory.setCreaterCode(user.getCode());
            inventory.setCreaterName(user.getName());
        } else {
            //开启
            inventory.setCreaterId(userVO.getUserId());
            inventory.setCreaterCode(userVO.getUserCode());
            inventory.setCreaterName(userVO.getUserName());
        }
    }


    /**
     * 查询盘点单详情
     * @param userVO
     * @param id
     * @param type==1 表示点击登记按钮的请求
     */
    @Override
    public InventoryForOrderDetailVO getInventoryOrderDetail(UserVO userVO, Long id,int type) {
        if(type == 1){
            //先查询缓存，如果存在返回缓存，
            Class<InventoryForRegisterVO2> clazz = InventoryForRegisterVO2.class;
            DraftCacheVO draftCacheVO = redisComponent.getDraftCacheVO(userVO.getEnterpriseId(), id, OrderRule.INVENTORY.getCodePrefix(), clazz);
            if(draftCacheVO != null){
                InventoryForRegisterVO2 orderData = (InventoryForRegisterVO2) draftCacheVO.getOrderData();
                InventoryForOrderDetailVO vo = new InventoryForOrderDetailVO();
                BeanUtils.copyProperties(orderData,vo);
                return vo;
            }

        }
        Long enterpriseId = userVO.getEnterpriseId();
        InventoryForOrderDetailVO inventory = inventoryMapper.getInventoryById(enterpriseId,id);
        Integer invType = inventory.getInvType();
        if(invType == InvType.SHELF.getCode()){
            //按货位
            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,null);
            inventory.setDetailForAddVOList(detailList);
        } else if(invType == InvType.GOODS.getCode()){
            //按照商品
            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvId(id, enterpriseId);
            inventory.setDetailForAddVOList(detailList);
        }
        return inventory;
    }

    public Map<String,Object> getInventoryOrderDetail4ExportExcel(UserVO userVO, Long id) {
        Long enterpriseId = userVO.getEnterpriseId();

        Map<String,Object> map = new HashMap<>();
        Inventory inventory = inventoryMapper.getInventoryById4ExportExcel(enterpriseId,id);
        Integer invType = inventory.getInvType();
        if(invType == InvType.SHELF.getCode()){
            //按货位
            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,null);
            map.put("detailList",detailList);
        } else if(invType == InvType.GOODS.getCode()){
            //按照商品
            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvId(id, enterpriseId);
            map.put("detailList",detailList);
        }
        map.put("inventory",inventory);
        return map;
    }

    /**
     *
     * @param output
     * @param id
     * @param userVO
     * @param type
     * @throws Exception
     */
    @Override
    public void export(OutputStream output, Long id, UserVO userVO, int type) throws Exception {

        Map<String,Object> resultMap = getInventoryOrderDetail4ExportExcel(userVO, id);
        Inventory inventoryOrderDetail = (Inventory) resultMap.get("inventory");
        List<InventoryDetailForOrderDetailVO> detailForAddVOList = (List<InventoryDetailForOrderDetailVO>) resultMap.get("detailList");

        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "盘点单";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("goodsCode","商品编码");
        headerHashMap.put("goodsGenericName","通用名称");
        headerHashMap.put("goodsSpecification","规格");
        headerHashMap.put("unitName","单位");
        headerHashMap.put("manufacturer","生产厂商");
        headerHashMap.put("goodsPlace","产地");
        if(inventoryOrderDetail.getInvType() == 0) {
            headerHashMap.put("lotNumber", "批号");
            headerHashMap.put("productDate", "生产日期");
            headerHashMap.put("validDate", "有效期至");
            headerHashMap.put("shelfName", "货位");
        }
        headerHashMap.put("invQuantityExcel","实盘数量");

        List<TwoTuple<String,String>> subHeadingList  = new ArrayList<>();
        if(type == 1){
            subHeadingList.add(new TwoTuple<>(" 盘点单号", StringUtil.transferTrimStr(inventoryOrderDetail.getCode())));
            subHeadingList.add(new TwoTuple<>(" 创建日期",StringUtil.transferTrimStr(inventoryOrderDetail.getCreateTime())));
            subHeadingList.add(new TwoTuple<>(" 创建人员",StringUtil.transferTrimStr(inventoryOrderDetail.getCreaterName())));
            subHeadingList.add(new TwoTuple<>(" 盘点方法",StringUtil.transferTrimStr(InvType.getValue(inventoryOrderDetail.getInvType()))));
            subHeadingList.add(new TwoTuple<>(" 盘点范围",StringUtil.transferTrimStr(inventoryOrderDetail.getInvRange())));
            subHeadingList.add(new TwoTuple<>(" 仓库",StringUtil.transferTrimStr(inventoryOrderDetail.getWarehouseName())));
            subHeadingList.add(new TwoTuple<>(" 库区",StringUtil.transferTrimStr(inventoryOrderDetail.getWarehouseAreaNames())));
            subHeadingList.add(new TwoTuple<>(" 货区/柜组",StringUtil.transferTrimStr(inventoryOrderDetail.getCargoAreaNames())));
        }

        excelComponent.exportExcel(output,firstTitle,secondTitle,subHeadingList,headerHashMap,detailForAddVOList,null);

    }
    /**
     * 查询差异处理盘点单详情
     * @param userVO
     * @param id
     */
    @Override
    public InventoryForOrderDetailVO getInventoryDiffOrderDetail(UserVO userVO, Long id) {
        Long enterpriseId = userVO.getEnterpriseId();
        InventoryForOrderDetailVO inventory = inventoryMapper.getInventoryById(enterpriseId,id);
        //按货位
        List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,0);
        inventory.setDetailForAddVOList(detailList);
        return inventory;
    }

    /**
     * 查询已登记盘点单详情
     * @param userVO
     * @param id
     */
    @Override
    public InventoryForRegisterVO2 getHadRegisterInventoryOrderDetail(UserVO userVO, Long id) {
        Long enterpriseId = userVO.getEnterpriseId();
        InventoryForRegisterVO2 inventory = inventoryMapper.getInventoryByIdForRegister(enterpriseId, id);
        Integer invType = inventory.getInvType();
        if(invType == InvType.SHELF.getCode()){
            //按货位
            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,null);
            setPinyinCode(detailList);
            inventory.setDetailForAddVOList(detailList);
        } else if(invType == InvType.GOODS.getCode()){
            //按照商品
            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvId(id, enterpriseId);
            setPinyinCode(detailList);
            inventory.setDetailForAddVOList(detailList);
        }
        return inventory;
    }

    private void setPinyinCode(List<InventoryDetailForOrderDetailVO> detailList) {
        detailList.stream().forEach(item->{
            String goodsName = item.getGoodsName();
            String goodsGenericName = item.getGoodsGenericName();
            item.setGoodsNamePinyin(PinYinUtils.getFirstSpell(goodsName));
            item.setGoodsGenericNamePinyin(PinYinUtils.getFirstSpell(goodsGenericName));
        });
    }

    @Override
    public void export4HadRegisterInventory(OutputStream output, Long id, UserVO userVO) {

        InventoryForRegisterVO2 hadRegisterInventoryOrderDetail = getHadRegisterInventoryOrderDetail(userVO, id);

        List<InventoryDetailForOrderDetailVO> detailForAddVOList = hadRegisterInventoryOrderDetail.getDetailForAddVOList();

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("manufacturer","生产厂商");
        map.put("unitName","单位");

        if(hadRegisterInventoryOrderDetail.getInvType() == 0) {
            map.put("lotNumber", "批号");
            map.put("productDate", "生产日期");
            map.put("validDate", "有效期至");
            map.put("shelfName", "货位");
        }

        map.put("invQuantity","实盘数量");

        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("盘点单号");
        titleSecondRow.append(!StringUtils.isEmpty(hadRegisterInventoryOrderDetail.getCode()) ? hadRegisterInventoryOrderDetail.getCode() : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("登记日期：");
        titleSecondRow.append(null != hadRegisterInventoryOrderDetail.getRegisterDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hadRegisterInventoryOrderDetail.getRegisterDate())
                : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("登记人员：");
        titleSecondRow.append(!StringUtils.isEmpty(hadRegisterInventoryOrderDetail.getRegisterManName()) ? hadRegisterInventoryOrderDetail.getRegisterManName() : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("登记方法：");
        titleSecondRow.append(null != hadRegisterInventoryOrderDetail.getRegisterType() ? RegisterType.getValue(hadRegisterInventoryOrderDetail.getRegisterType())
                : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("盘点日期：");
        titleSecondRow.append(null != hadRegisterInventoryOrderDetail.getInvDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hadRegisterInventoryOrderDetail.getInvDate()) : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("盘点人员：");
        titleSecondRow.append(!StringUtils.isEmpty(hadRegisterInventoryOrderDetail.getInvManName()) ? hadRegisterInventoryOrderDetail.getInvManName() : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("复盘人员：  ");
        titleSecondRow.append(!StringUtils.isEmpty(hadRegisterInventoryOrderDetail.getSecondInvManName()) ? hadRegisterInventoryOrderDetail.getSecondInvManName() : "" );

        List<String> name = new ArrayList<>();
        name.add(userVO.getEnterpriseName());
        name.add("盘点单");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        purchaseGeneralComponent.commExcelExport(output,map,detailForAddVOList,name,secondTitle,"",true,new ArrayList<>());

    }

    /**
     * 查询已处理订单详情
     * @param userVO
     * @param id
     */
    @Override
    public InventoryForDiffDetailVO getHadHandleInventoryOrderDetail(UserVO userVO, Long id) {
        Long enterpriseId = userVO.getEnterpriseId();
        InventoryForDiffDetailVO inventory = inventoryMapper.getInventoryByIdForDiff(enterpriseId, id);

        List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,null);
        inventory.setDetailForAddVOList(detailList);

        return inventory;
    }

    @Override
    public void export4HadHandleInventory(OutputStream output, Long id, UserVO userVO) {

        InventoryForDiffDetailVO hadHandleInventoryOrderDetail = getHadHandleInventoryOrderDetail(userVO, id);

        List<InventoryDetailForOrderDetailVO> detailForAddVOList = hadHandleInventoryOrderDetail.getDetailForAddVOList();

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("unitName","单位");
        map.put("lotNumber", "批号");
        map.put("validDate", "有效期至");
        map.put("quantity", "库存数量");
        map.put("invQuantity","实盘数量");
        map.put("diffQuantity","损溢数量");

        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("盘点单号: ");
        titleSecondRow.append(!StringUtils.isEmpty(hadHandleInventoryOrderDetail.getCode()) ? hadHandleInventoryOrderDetail.getCode() : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("处理日期：");
        titleSecondRow.append(null != hadHandleInventoryOrderDetail.getHandleDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hadHandleInventoryOrderDetail.getHandleDate())
                : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("处理人员：");
        titleSecondRow.append(!StringUtils.isEmpty(hadHandleInventoryOrderDetail.getHandleManName()) ? hadHandleInventoryOrderDetail.getHandleManName() : "");

        List<String> name = new ArrayList<>();
        name.add(userVO.getEnterpriseName());
        name.add("差异处理");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        purchaseGeneralComponent.commExcelExport(output,map,detailForAddVOList,name,secondTitle,"",true,new ArrayList<>());

    }

    /**
     * 盘点登记
     * @param userVO
     * @param inventoryForRegisterVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRegisterInventory(UserVO userVO, InventoryForRegisterVO inventoryForRegisterVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = PubStatus.inventoryOrderStatus.PENDING;
        Inventory inventoryOld = inventoryMapper.selectByPrimaryKey(inventoryForRegisterVO.getId());
        Inventory inventory = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
        //登记方法(0-按账面登记；1-按实物登记)
        Integer registerType = inventoryForRegisterVO.getRegisterType();
        //盘点方法（0-按货位；1-按商品）
        Integer invType = inventoryOld.getInvType();

        //quantityTotal---账面数量
        BigDecimal quantityTotal = inventoryOld.getQuantityTotal();

        //盘点总单id
        Long invId = inventoryForRegisterVO.getId();

        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        if(mangeConfig.getBusinessControl() == 0){
            //关闭
            User user = userMapper.selectByPrimaryKey(inventoryForRegisterVO.getRegisterManId());
            inventory.setRegisterManId(user.getId());
            inventory.setRegisterManCode(user.getCode());
            inventory.setRegisterManName(user.getName());
            inventory.setRegisterDate(inventoryForRegisterVO.getRegisterDate());
        } else {
            //开启
            inventory.setRegisterManId(userVO.getUserId());
            inventory.setRegisterManCode(userVO.getUserCode());
            inventory.setRegisterManName(userVO.getUserName());
            inventory.setRegisterDate(inventoryForRegisterVO.getRegisterDate());
        }

        //主键
        inventory.setId(inventoryForRegisterVO.getId());
        //登记方法
        inventory.setRegisterType(registerType);

        //盘点日期
        inventory.setInvDate(inventoryForRegisterVO.getInvDate());
        //盘点人员
        User user = userMapper.selectByPrimaryKey(inventoryForRegisterVO.getInvManId());
        inventory.setInvManId(user.getId());
        inventory.setInvManCode(user.getCode());
        inventory.setInvManName(user.getName());

        //复盘人员
        User secondInvMan = userMapper.selectByPrimaryKey(inventoryForRegisterVO.getSecondInvManId());
        inventory.setSecondInvManId(secondInvMan.getId());
        inventory.setSecondInvManCode(secondInvMan.getCode());
        inventory.setSecondInvManName(secondInvMan.getName());
        inventory.setStatus(status);
        inventoryMapper.updateByPrimaryKeySelective(inventory);

        //把盘点单中的所有数据都更改成无损益的
        inventoryDetailMapper.updateByInvIdAndGoodsId(invId,null);
        inventoryShelfMapper.updateByInvIdAndGoodsId(invId,null);

        List<InventoryDetailForRegisterVO> detailForAddVOList = inventoryForRegisterVO.getDetailForAddVOList();
        //总的实盘数量
        BigDecimal invQuantityTotal = BigDecimal.ZERO;


        if(invType == InvType.SHELF.getCode()){
            //按货位
            for(InventoryDetailForRegisterVO detail : detailForAddVOList){
                Long detailId = detail.getId();
                if(detailId == null){
                    throw new BusinessException("明细ID不能为空!");
                }
                BigDecimal invQuantity = detail.getInvQuantity();//货位明细的实盘数量
                if(invQuantity == null){
                    throw new BusinessException("实盘数量不能为空!");
                }
//                if(detail.getLineNum() == null) throw new BusinessException("货位行号不存在");
                //更新盘点货位明细
                Long inventoryShelfId = detail.getInventoryShelfId();
                InventoryShelf inventoryShelf = (InventoryShelf) EntityUtils.reflectUpdateSetDefaultValue(InventoryShelf.class, userVO);
                InventoryShelf shelf = inventoryShelfMapper.selectByPrimaryKey(inventoryShelfId);
                if(shelf == null ) throw new BusinessException("盘点货位明细不存在");
                BigDecimal quantityShelf = shelf.getQuantity();//货位明细的账面数量
                inventoryShelf.setId(inventoryShelfId);
                inventoryShelf.setInvQuantity(invQuantity);
                inventoryShelf.setDiffQuantity(invQuantity.subtract(quantityShelf));
                inventoryShelfMapper.updateByPrimaryKeySelective(inventoryShelf);

                invQuantityTotal = invQuantityTotal.add(invQuantity);
            }
            //计算金额数量
            Map<Long,BigDecimal> map = new HashMap<>();
            for(InventoryDetailForRegisterVO detail : detailForAddVOList){
                Long detailId = detail.getId();
                if(detailId == null){
                    throw new BusinessException("明细ID不能为空!");
                }
                BigDecimal invQuantity = detail.getInvQuantity();//货位明细的实盘数量
                if(invQuantity == null){
                    throw new BusinessException("实盘数量不能为空!");
                }
                BigDecimal invQuantityMap = map.get(detailId);
                if(invQuantityMap == null){
                    map.put(detailId,invQuantity);
                } else {
                    invQuantityMap = invQuantityMap.add(invQuantity);
                    map.put(detailId,invQuantityMap);
                }
            }
            map.forEach((id,invQuantity)->{
                InventoryDetail inventoryDetail = null;
                try {
                    inventoryDetail = (InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class, userVO);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BusinessException("");
                }
                InventoryDetail detail1 = inventoryDetailMapper.selectByPrimaryKey(id);
                if(detail1 == null ) throw new BusinessException("盘点明细不存在");
                BigDecimal quantity = detail1.getQuantity();//明细的账面数量
                inventoryDetail.setInvQuantity(invQuantity);
                inventoryDetail.setQuantity(quantity);
                inventoryDetail.setDiffQuantity(invQuantity.subtract(quantity));
                inventoryDetail.setId(id);
                inventoryDetailMapper.updateByPrimaryKeySelective(inventoryDetail);
            });
        } else if(invType == InvType.GOODS.getCode()){
            //按照商品
            for(InventoryDetailForRegisterVO detailTmp : detailForAddVOList){
                Long detailId = detailTmp.getId();
                if(detailId == null){
                    throw new BusinessException("明细ID不能为空!");
                }
                BigDecimal invQuantity = detailTmp.getInvQuantity();//货位明细的实盘数量
                if(invQuantity == null){
                    throw new BusinessException("实盘数量不能为空!");
                }
                invQuantityTotal = invQuantityTotal.add(invQuantity);
                InventoryDetail detail = inventoryDetailMapper.selectByPrimaryKey(detailId);
                Long goodsId = detail.getGoodsId();
                BigDecimal quantity = detail.getQuantity();//账面数量
                detail.setInvQuantity(invQuantity);
                detail.setDiffQuantity(invQuantity.subtract(quantity));
                detail.setStatus(status);
                inventoryDetailMapper.updateByPrimaryKeySelective(detail);
                inventoryShelfMapper.updateByInvIdAnddtlId(invId,detailId);
                //盘点登记更新盘点货位明细
                if(quantity.compareTo(invQuantity) == -1){
                    //账面小于实盘 盘盈
                    //盘盈 实盘数据增加到近效期远的

                    List<InventoryShelf> inventoryShelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId,goodsId,invId,detailId,"desc");
                    if(CollectionUtils.isEmpty(inventoryShelfList)) throw new BusinessException("盘点单货位明细不存在");
                    InventoryShelf inventoryShelf1 = inventoryShelfList.stream().findFirst().get();
                    Long shelfId = inventoryShelf1.getId();
                    BigDecimal quantity1 = inventoryShelf1.getQuantity();
                    invQuantity = invQuantity.subtract(quantity);//总的实盘数量减去货位明细的账面数量

                    InventoryShelf inventoryShelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
                    inventoryShelf.setId(shelfId);
                    inventoryShelf.setInvQuantity(quantity1.add(invQuantity));
                    inventoryShelf.setDiffQuantity(invQuantity);
                    inventoryShelfMapper.updateByPrimaryKeySelective(inventoryShelf);

                } else if(quantity.compareTo(invQuantity) == 1){
                    //盘亏 实盘数据增加到近效期近的
                    List<InventoryShelf> inventoryShelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId,goodsId,invId,detailId,"asc");
                    invQuantity = quantity.subtract(invQuantity);//总的实盘数量减去货位明细的账面数量
                    updateInventoryShelfQuantity(userVO, invQuantity, inventoryShelfList);
                }
            }
        }

        Inventory inventory1 = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
        if(invQuantityTotal.compareTo(quantityTotal) == 0){
            //改成已处理
            inventory1.setStatus(PubStatus.inventoryOrderStatus.WAIT_POSTING);
            //
            inventory1.setHandleDate(new Date());
            inventory1.setHandleManId(userVO.getUserId());
            inventory1.setHandleManName(userVO.getUserName());
            inventory1.setHandleManCode(userVO.getUserCode());

            //把盘点单中的所有数据都更改成无损益的
            inventoryDetailMapper.updateStatusByInvId(invId,PubStatus.inventoryOrderStatus.WAIT_POSTING);
            inventoryShelfMapper.updateStatusByInvId(invId,PubStatus.inventoryOrderStatus.WAIT_POSTING);
        }
        inventory1.setId(inventory.getId());
        inventory1.setInvQuantityTotal(invQuantityTotal);
        inventoryMapper.updateByPrimaryKeySelective(inventory1);

        //删除redis缓存数据
        String redisKeyValue = inventoryForRegisterVO.getRedisKeyValue();
        redisComponent.removeDraftCacheVO(enterpriseId, OrderRule.INVENTORY.getCodePrefix(),redisKeyValue);

    }

    private void updateInventoryShelfQuantity(UserVO userVO, BigDecimal invQuantity, List<InventoryShelf> inventoryShelfList) throws Exception {
        for(InventoryShelf shelf : inventoryShelfList){
            BigDecimal shelfQuantity = shelf.getQuantity();//货位明细的账面数量
            invQuantity = invQuantity.subtract(shelfQuantity);//总的实盘数量减去货位明细的账面数量
            if(invQuantity.compareTo(BigDecimal.ZERO) == 1){
                //大于0
                InventoryShelf inventoryShelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
                inventoryShelf.setId(shelf.getId());
                inventoryShelf.setInvQuantity(BigDecimal.ZERO);
                inventoryShelf.setDiffQuantity(shelfQuantity);
                inventoryShelfMapper.updateByPrimaryKeySelective(inventoryShelf);
            } else if(invQuantity.compareTo(BigDecimal.ZERO) == 0){
                //等于0
                InventoryShelf inventoryShelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
                inventoryShelf.setId(shelf.getId());
                inventoryShelf.setInvQuantity(BigDecimal.ZERO);
                inventoryShelf.setDiffQuantity(shelfQuantity);
                inventoryShelfMapper.updateByPrimaryKeySelective(inventoryShelf);
                break;
            } else {
                //小于0
                InventoryShelf inventoryShelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
                inventoryShelf.setId(shelf.getId());
                inventoryShelf.setInvQuantity(invQuantity.multiply(new BigDecimal(-1)));
                inventoryShelf.setDiffQuantity(invQuantity.subtract(inventoryShelf.getInvQuantity()));
                inventoryShelfMapper.updateByPrimaryKeySelective(inventoryShelf);
                break;
            }
        }


    }




    /**
     * 保存差异化处理单
     *
     * @param userVO
     * @param inventoryForDiffVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveInventoryDiffHandleOrder(UserVO userVO, InventoryForDiffVO inventoryForDiffVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        String enterpriseName = userVO.getEnterpriseName();
        Long userId = userVO.getUserId();
        String userName = userVO.getUserName();
        int status = PubStatus.inventoryOrderStatus.WAIT_POSTING;//待过账
        Inventory inventory = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
        inventory.setId(inventoryForDiffVO.getId());
        inventory.setHandleDate(inventoryForDiffVO.getHandleDate());
        Long handleManId = inventoryForDiffVO.getHandleManId();
        User user = userMapper.selectByPrimaryKey(handleManId);
        inventory.setHandleManId(handleManId);
        inventory.setHandleManCode(user.getCode());
        inventory.setHandleManName(user.getName());
        inventory.setStatus(status);
        List<InventoryShelfSimpleForDiffVO> detailForAddVOList = inventoryForDiffVO.getDetailForAddVOList();

        BigDecimal invQuantityTotalAll = BigDecimal.ZERO;

        Set<Long> detailIdSet = new HashSet<>();
        Map<Long,BigDecimal> map = new HashMap<>();
        for(InventoryShelfSimpleForDiffVO diffVO : detailForAddVOList){
            Long detailId = diffVO.getId();
            detailIdSet.add(detailId);
            BigDecimal total = map.get(detailId);
            if(total == null){
                BigDecimal invQuantityTotal = BigDecimal.ZERO;
                invQuantityTotal = invQuantityTotal.add(diffVO.getInvQuantity());
                map.put(detailId,invQuantityTotal);
            } else {
                total = total.add(diffVO.getInvQuantity());
                map.put(detailId,total);
            }
            InventoryShelf shelf = inventoryShelfMapper.selectByPrimaryKey(diffVO.getInventoryShelfId());
            shelf.setId(diffVO.getInventoryShelfId());
            shelf.setInvQuantity(diffVO.getInvQuantity());
            shelf.setDiffQuantity(diffVO.getInvQuantity().subtract(shelf.getQuantity()));
            inventoryShelfMapper.updateByPrimaryKeySelective(shelf);
            invQuantityTotalAll = invQuantityTotalAll.add(diffVO.getInvQuantity());
        }
        //更新明细
        for(Long dId : detailIdSet){
            InventoryDetail inventoryDetail = inventoryDetailMapper.selectByPrimaryKey(dId);
            BigDecimal quantity = inventoryDetail.getQuantity();
            BigDecimal invQuantity = map.get(dId);
            inventoryDetail = (InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class,userVO);
            inventoryDetail.setId(dId);
            inventoryDetail.setQuantity(quantity);
            inventoryDetail.setInvQuantity(invQuantity);
            inventoryDetail.setDiffQuantity(invQuantity.subtract(quantity));
            inventoryDetail.setStatus(status);
            inventoryDetailMapper.updateByPrimaryKeySelective(inventoryDetail);
        }
        Inventory inventory1 = inventoryMapper.selectByPrimaryKey(inventoryForDiffVO.getId());
        inventory.setInvQuantityTotal(invQuantityTotalAll);
        inventory.setDiffQuantityTotal(invQuantityTotalAll.subtract(inventory1.getQuantityTotal()));

        inventoryMapper.updateByPrimaryKeySelective(inventory);




    }

    /**
     * 已登记盘点单查询
     *
     * @param page
     * @param paramForListVO
     * @param enterpriseId
     */
    @Override
    public void getHadRegisterInventoryOrderList(Page<List<InventoryForRegisterVO2>> page, RequestParamForHadRegisterListVO paramForListVO, Long enterpriseId) {
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(paramForListVO.getPageNo(), paramForListVO.getPageSize());
        List<InventoryForRegisterVO2> inventoryList = inventoryMapper.getHadRegisterInventoryOrderList(paramForListVO, enterpriseId);
        page.setResult(inventoryList);
        page.setTotalRecord((int)objects.getTotal());
    }

    @Override
    public void getHadHandlerInventoryList(Page<List<InventoryForDiffDetailVO>> page, RequestParamForDiffListVO paramForListVO, Long enterpriseId) {
        paramForListVO.setStart(page.getStart());
        List<InventoryForDiffDetailVO> inventoryList = inventoryMapper.getHadHandlerInventoryList(paramForListVO, enterpriseId);
        Integer count = inventoryMapper.getHadHandlerInventoryListCount(paramForListVO, enterpriseId);
        page.setResult(inventoryList);
        page.setTotalRecord(count);
    }

    /**
     * 已过账单查询
     *
     * @param page
     * @param param
     * @param enterpriseId
     */
    @Override
    public void getInventoryPostOrderList(Page<InventoryPostVO> page, RequestParamForPostListVO param, Long enterpriseId) {
        param.setStart(page.getStart());
        List<InventoryForPostVO> inventoryList = inventoryMapper.getInventoryPostOrderList(param, enterpriseId);
        Integer count = inventoryMapper.getInventoryPostOrderListCount(param, enterpriseId);
        if(!CollectionUtils.isEmpty(inventoryList)){
            InventoryPostVO totalForPost = inventoryMapper.getTotalForPost();
            if(totalForPost != null){
                totalForPost.setInventoryForPostVOList(inventoryList);
                page.setResult(totalForPost);
                page.setTotalRecord(count);
            } else {
                page.setTotalRecord(0);
                InventoryPostVO inventoryPostVO = new InventoryPostVO();
                inventoryPostVO.setInventoryForPostVOList(new ArrayList<>());
                page.setResult(inventoryPostVO);
            }

        } else {
            page.setTotalRecord(0);
            InventoryPostVO inventoryPostVO = new InventoryPostVO();
            inventoryPostVO.setInventoryForPostVOList(new ArrayList<>());
            page.setResult(inventoryPostVO);
        }


    }

    /**
     * 已过账单详情
     *
     * @param userVO
     * @param id
     */
    @Override
    public InventoryForPostDetailVO getPostDetail(UserVO userVO, Long id) {
        Long enterpriseId = userVO.getEnterpriseId();
        InventoryForPostDetailVO inventory = inventoryMapper.getInventoryByIdForPost(enterpriseId, id);

        List<InventoryDetailForPostVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelfForPost(id, enterpriseId);
        inventory.setPostVOList(detailList);

        return inventory;
    }

    @Override
    public void export4PostDetail(OutputStream output, Long id, UserVO userVO) {

        InventoryForPostDetailVO postDetail = getPostDetail(userVO, id);

        List<InventoryDetailForPostVO> postVOList = postDetail.getPostVOList();

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("shelfName", "货位");
        map.put("quantity", "账面数量");
        map.put("invQuantity","实盘数量");
        map.put("diffQuantity","损溢数量");

        map.put("unitPrice","单价");
        map.put("amount","账面金额");
        map.put("realAmount","实盘金额");
        map.put("diffAmount","损溢金额");
        map.put("taxRate","税率");
        map.put("notaxPrice","不含税单价");
        map.put("notaxAmount","不含税账面金额");
        map.put("realNotaxAmount","不含税实盘金额");
        map.put("diffNotaxAmount","不含税损溢出金额");
        map.put("taxAmount","账面税额");
        map.put("realTaxAmount","实盘税额");
        map.put("diffTaxAmount","损溢税额");
        map.put("retailPrice","零售单价");
        map.put("retailAmount","账面零售金额");
        map.put("realRetailAmount","实盘零售金额");
        map.put("diffRetailAmount","损溢零售金额");

        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("盘点单号：");
        titleSecondRow.append(!StringUtils.isEmpty(postDetail.getCode()) ? postDetail.getCode() : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("过账日期：");
        titleSecondRow.append(null != postDetail.getPostDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(postDetail.getPostDate())
                : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("过账人员：");
        titleSecondRow.append(!StringUtils.isEmpty(postDetail.getPostManName()) ? postDetail.getPostManName() : "");

        List<String> name = new ArrayList<>();
        name.add(userVO.getEnterpriseName());
        name.add("盘点过账");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        purchaseGeneralComponent.commExcelExport(output,map,postVOList,name,secondTitle,"",true,new ArrayList<>());

    }

    /**
     * 过账查看详情
     * @param userVO
     * @param id
     * @return
     */
    @Override
    public InventoryForPostDetailVO getPostDetail2Post(UserVO userVO, Long id) {
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        Integer chainType = userVO.getChainType();
        InventoryForPostDetailVO inventory = inventoryMapper.getInventoryByIdForPost(enterpriseId, id);

        List<InventoryDetailForPostVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelfForPost(id, enterpriseId);
        //添加单价 税率
        for(InventoryDetailForPostVO post : detailList){
            //单价计算
            BigDecimal unitPrice = BigDecimal.ZERO;
            BigDecimal noTaxPrice = BigDecimal.ZERO;//不含税单价
            Long goodsId = post.getGoodsId();
            Long lotId = post.getLotId();
            Long shelfId = post.getShelfId();//货位ID
            BigDecimal quantity = post.getQuantity();//账面数量
            BigDecimal invQuantity = post.getInvQuantity();//实盘数量
            PriceOrder priceOrder = null;
            if(chainType == ChainType.Headquarters.getCode()){
                //总部的价格清单0000
                priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);
            } else {
                priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);
            }
            if(priceOrder == null) throw new BusinessException("价格清单不存在");
            PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(enterpriseId,priceOrder.getId(),goodsId);
            if(priceOrderDetail == null) throw new BusinessException("商品" + post.getGoodsCode() + "的价格清单明细不存在");
            BigDecimal retailPrice = priceOrderDetail.getRetailPrice();//零售单价
            //获取成本中可用金额
//            CostInfoPostVO costTotalAmount =  getShelfCostInfo(goodsId,lotId,enterpriseId,shelfId);
            CostInfoPostVO costTotalAmount = inOutRecordDetailMapper.getCostTotalQuantityAndAmount(enterpriseId, lotId, goodsId, shelfId,null);

            BigDecimal notaxRealAmountTotal = costTotalAmount.getNotaxRealAmountTotal();//不含税实际金额
            BigDecimal realAmountTotal = costTotalAmount.getRealAmountTotal();//账面金额
            BigDecimal quantityTotal = costTotalAmount.getQuantityTotal();
            if(quantity.compareTo(invQuantity) == -1){

                //盘盈
                /**
                 * 最近成本单价，优先查询同商品同批号的单价，其次同商品的单价，如果没有则为0
                 * 税率显示商品默认进项税
                 */
                post.setIsProfit(1);
                logger.info("盘盈账面金额={}",realAmountTotal);
                post.setAmount(realAmountTotal);//账面金额--

                GoodsInfoPostVO costInfoForPost = costMapper.getCostInfoForPost(goodsId, lotId, enterpriseId);
//                List<InOutRecordDetail> goodsInShelfInOutDetailList = inOutRecordDetailMapper.getInOutDetailByParam(enterpriseId, lotId, goodsId, shelfId,0,"desc");
//                if(CollectionUtils.isEmpty(goodsInShelfInOutDetailList)) throw new BusinessException("商品的出入库明细不存在");
//                unitPrice = goodsInShelfInOutDetailList.stream().findFirst().get().getRealPrice();//最近的成本单价
                unitPrice = costInfoForPost.getRealPrice();
                logger.info("盘盈单价={}",unitPrice);
                post.setUnitPrice(unitPrice);

                BigDecimal purchaseTaxRate = costInfoForPost.getTaxRate();
                Long purchaseTaxRateId = null;
                List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectByCodeOrRate(enterpriseId, null, purchaseTaxRate, null);
                if(CollectionUtils.isEmpty(goodsTaxRates)){
                    GoodsBusiness goodsBusiness = goodsBusinessMapper.getByGoodsId(goodsId);
                    purchaseTaxRate = goodsBusiness.getPurchaseTaxRate();//进项税
                    purchaseTaxRateId = goodsBusiness.getPurchaseTaxRateId();//进项税id
                } else {
                    GoodsTaxRate goodsTaxRate = goodsTaxRates.get(0);
                    purchaseTaxRate = goodsTaxRate.getTaxRate();//税
                    purchaseTaxRateId = goodsTaxRate.getId();//税id
                }


                BigDecimal diff = invQuantity.subtract(quantity);//损益数量quantity

                BigDecimal diffTotal = diff.multiply(unitPrice);//损益金额
                logger.info("盘盈损溢金额={}",diffTotal);
                post.setDiffAmount(diffTotal);
                BigDecimal realAmount = realAmountTotal.add(diffTotal);
                logger.info("盘盈实盘金额={}",realAmount);
                post.setRealAmount(realAmount);//实盘金额
                logger.info("盘盈不含税账面金额={}",notaxRealAmountTotal);
                post.setNotaxAmount(notaxRealAmountTotal);

                //不含税损益金额
                BigDecimal notaxDiffTotal = diffTotal.divide(BigDecimal.ONE.add(purchaseTaxRate.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
                logger.info("盘盈不含税损益金额={}",notaxDiffTotal);
                post.setDiffNotaxAmount(notaxDiffTotal);
                //不含税单价
                noTaxPrice = notaxDiffTotal.divide(diff, 6, BigDecimal.ROUND_HALF_UP);
                post.setNotaxPrice(noTaxPrice);
                post.setTaxRate(purchaseTaxRate);//税
                post.setTaxRateId(purchaseTaxRateId);
                post.setRetailPrice(retailPrice);

                BigDecimal notaxRealAmount = notaxRealAmountTotal.add(notaxDiffTotal);//不含税实盘金额
                logger.info("盘盈不含税实盘金额={}",notaxRealAmount);
                post.setRealNotaxAmount(notaxRealAmount);

                BigDecimal taxAmount = realAmountTotal.subtract(notaxRealAmountTotal);//账面税额
                BigDecimal realTaxAmount = realAmount.subtract(notaxRealAmount);//实盘税额
                BigDecimal diffTaxAmount = diffTotal.subtract(notaxDiffTotal);//损益税额
                logger.info("盘盈账面税额={},实盘税额={},损益税额={}",taxAmount,realTaxAmount,diffTaxAmount);
                post.setTaxAmount(taxAmount);//账面税额
                post.setRealTaxAmount(realTaxAmount);//实盘税额
                post.setDiffTaxAmount(diffTaxAmount);//损益税额


                BigDecimal retailAmount = retailPrice.multiply(quantity);
                BigDecimal realRetailAmount = retailPrice.multiply(invQuantity);
                BigDecimal diffRetailAmount = realRetailAmount.subtract(retailAmount);
                logger.info("盘盈零售单价={},零售账面金额={},零售实盘金额={},零售损益金额={}",retailPrice,retailAmount,realRetailAmount,diffRetailAmount);
                post.setRetailPrice(retailPrice);
                post.setRetailAmount(retailAmount); //账面零售金额
                post.setRealRetailAmount(realRetailAmount);//实盘零售金额
                post.setDiffRetailAmount(diffRetailAmount);//损益零售金额


            } else if (quantity.compareTo(invQuantity) == 1){
                List<InOutRecordDetail> goodsInShelfInOutDetailList = inOutRecordDetailMapper.getInOutDetailByParam(enterpriseId, lotId, goodsId, shelfId,0,"asc");
                /**
                 *  盘亏： 损溢金额/盘亏数量
                 *  先进先出
                 *  税率不显示
                 */
                //盘亏
                //先进先出的商品信息
                post.setIsProfit(0);
//                List<GoodsInfoPostVO> goodsStockForPost = costMapper.getGoodsStockForPost(enterpriseId, goodsId, lotId);
                BigDecimal diff = quantity.subtract(invQuantity);//损益数量quantity
                BigDecimal diffTotal = diff.multiply(unitPrice);//损益金额
                BigDecimal notaxDiffTotal = diff.multiply(noTaxPrice);//不含税损益金额
                for(InOutRecordDetail postVO : goodsInShelfInOutDetailList){
                    BigDecimal goodsQuantity = postVO.getQuantity();//商品出入库数量
                    BigDecimal realPrice = postVO.getRealPrice();//出入库实际单价
                    BigDecimal notaxRealPrice = postVO.getNotaxRealPrice();//出入库不含税实际单价
                    //使用损益的数量减去入库数量
                    diff = diff.subtract(goodsQuantity);
                    if(diff.compareTo(BigDecimal.ZERO) == 1){
                        //大于0
                        diffTotal = diffTotal.add(goodsQuantity.multiply(realPrice));
                        notaxDiffTotal = notaxDiffTotal.add(goodsQuantity.multiply(notaxRealPrice));
                    } else if(diff.compareTo(BigDecimal.ZERO) == 0){
                        //等于0
                        diffTotal = diffTotal.add(goodsQuantity.multiply(realPrice));
                        notaxDiffTotal = notaxDiffTotal.add(goodsQuantity.multiply(notaxRealPrice));
                        break;
                    } else {
                        //小于0
                        diffTotal = diffTotal.add(goodsQuantity.add(diff).multiply(realPrice));
                        notaxDiffTotal = notaxDiffTotal.add(goodsQuantity.add(diff).multiply(notaxRealPrice));
                        break;
                    }

                }
//                //盘亏单价 不含税实盘金额/实盘数量

//                logger.info("通过计算总损益金额除以损益数量计算损益单价" + unitPrice);
//                unitPrice = commonComponent.getCostPriceWithLotId(enterpriseId,goodsId,lotId,quantity.subtract(invQuantity));
//                System.out.println("通过中义的算法获得损益单价" + unitPrice);
//                noTaxPrice = commonComponent.getNoTaxPriceWithLotId(enterpriseId,goodsId,lotId,diff);

                post.setDiffAmount(diffTotal);//损益金额
                logger.info("损溢金额={}",diffTotal);
                post.setDiffNotaxAmount(notaxDiffTotal);//不含税损益金额
                logger.info("不含税损溢金额={}",notaxDiffTotal);

                post.setAmount(realAmountTotal);//账面金额
                BigDecimal realAmount = realAmountTotal.subtract(diffTotal);
                post.setRealAmount(realAmount);//实盘金额
                logger.info("盘亏账面金额={},实盘金额={}",realAmountTotal,realAmount);

                post.setNotaxAmount(notaxRealAmountTotal);//不含税账面金额
                BigDecimal notaxRealAmount = notaxRealAmountTotal.subtract(notaxDiffTotal);//不含实盘金额
                post.setRealNotaxAmount(notaxRealAmount);//不含实盘金额
                logger.info("盘亏不含税账面金额={},不含实盘金额={}",notaxRealAmountTotal,notaxRealAmount);

                if(BigDecimal.ZERO.compareTo(invQuantity) > 0){
                    unitPrice = realAmount.divide(invQuantity,6, RoundingMode.HALF_UP);
                    noTaxPrice = notaxRealAmount.divide(invQuantity,6, RoundingMode.HALF_UP);
                } else {
                    unitPrice = BigDecimal.ZERO;
                    noTaxPrice = BigDecimal.ZERO;
                }

                post.setUnitPrice(unitPrice);//单价
                post.setNotaxPrice(noTaxPrice);//不含税单价
                logger.info("盘亏单价={},不含税单价={}",unitPrice,noTaxPrice);

                BigDecimal taxAmount = realAmountTotal.subtract(notaxRealAmountTotal);//账面税额
                BigDecimal realTaxAmount = realAmount.subtract(notaxRealAmount);//实盘税额
                BigDecimal diffTaxAmount = diffTotal.subtract(notaxDiffTotal);//损益税额
                logger.info("盘亏账面税额={},实盘税额={},损益税额={}",taxAmount,realTaxAmount,diffTaxAmount);
                post.setTaxAmount(taxAmount);//账面税额
                post.setRealTaxAmount(realTaxAmount);//实盘税额
                post.setDiffTaxAmount(diffTaxAmount);//损益税额

                BigDecimal retailAmount = retailPrice.multiply(quantity);
                BigDecimal realRetailAmount = retailPrice.multiply(invQuantity);
                BigDecimal diffRetailAmount = realRetailAmount.subtract(retailAmount);
                post.setRetailPrice(retailPrice);
                post.setRetailAmount(retailAmount); //账面零售金额
                post.setRealRetailAmount(realRetailAmount);//实盘零售金额
                post.setDiffRetailAmount(diffRetailAmount);//损益零售金额
                logger.info("盘亏零售单价={},零售账面金额={},零售实盘金额={},零售损益金额={}",retailPrice,retailAmount,realRetailAmount,diffRetailAmount);

            } else {
                /**
                 *  无损溢
                 *  税率不显示
                 */
                post.setIsProfit(2);
                if(BigDecimal.ZERO.compareTo(quantityTotal) > 0){
                    unitPrice = realAmountTotal.divide(quantityTotal,6, RoundingMode.HALF_UP);
                    noTaxPrice = notaxRealAmountTotal.divide(quantityTotal,6, RoundingMode.HALF_UP);
                } else {
                    unitPrice = BigDecimal.ZERO;
                    noTaxPrice = BigDecimal.ZERO;
                }

//                unitPrice = realAmountTotal.divide(quantityTotal,6,BigDecimal.ROUND_HALF_UP);//单价
//                noTaxPrice = notaxRealAmountTotal.divide(quantityTotal,6,BigDecimal.ROUND_HALF_UP);//不含税单价
                post.setUnitPrice(unitPrice);
                post.setNotaxPrice(noTaxPrice);

                post.setAmount(realAmountTotal);//账面金额
                post.setRealAmount(realAmountTotal);//实盘金额
                post.setDiffAmount(BigDecimal.ZERO);//损益金额

                post.setNotaxAmount(notaxRealAmountTotal);//不含税账面金额
                post.setRealNotaxAmount(notaxRealAmountTotal);//不含税实盘金额
                post.setDiffNotaxAmount(BigDecimal.ZERO);//不含税损益金额
                BigDecimal taxAmount = realAmountTotal.subtract(notaxRealAmountTotal);
                post.setTaxAmount(taxAmount);//账面税额
                post.setRealTaxAmount(taxAmount);//实盘税额
                post.setDiffTaxAmount(BigDecimal.ZERO);//损益税额

                BigDecimal retailAmount = retailPrice.multiply(quantity);

                post.setRetailPrice(retailPrice);
                post.setRetailAmount(retailAmount); //账面零售金额
                post.setRealRetailAmount(retailAmount);//实盘零售金额
                post.setDiffRetailAmount(BigDecimal.ZERO);//损益零售金额

            }

        }
        inventory.setPostVOList(detailList);

        return inventory;
    }


    /**
     * 保存过账单
     * @param userVO
     * @param detailVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveInventoryPostOrder(UserVO userVO, InventoryForPostDetailVO detailVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        Integer chainType = userVO.getChainType();
        //盘点单
        Inventory inventory = inventoryMapper.selectByPrimaryKey(detailVO.getId());

        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        if(mangeConfig.getBusinessControl() == 0){
            //关闭
            User user = userMapper.selectByPrimaryKey(detailVO.getPostManId());
            inventory.setPostManId(user.getId());
            inventory.setPostManCode(user.getCode());
            inventory.setPostManName(user.getName());
            inventory.setPostDate(detailVO.getPostDate());
        } else {
            //开启
            inventory.setPostManId(userVO.getUserId());
            inventory.setPostManCode(userVO.getUserCode());
            inventory.setPostManName(userVO.getUserName());
            inventory.setPostDate(detailVO.getPostDate());
        }
        // 盘点明细
        List<InventoryDetailForPostVO> detailList = detailVO.getPostVOList();


        for(InventoryDetailForPostVO post : detailList){
            //单价计算
            BigDecimal unitPrice = BigDecimal.ZERO;
            BigDecimal noTaxPrice = BigDecimal.ZERO;//不含税单价
            Long goodsId = post.getGoodsId();
            Long lotId = post.getLotId();
            Long shelfId = post.getShelfId();
            BigDecimal quantity = post.getQuantity();//账面数量
            BigDecimal invQuantity = post.getInvQuantity();//实盘数量

            PriceOrder priceOrder = null;
            if(chainType == ChainType.Headquarters.getCode()){
                //总部的价格清单0000
                priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);
            } else {
                priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);
            }
            if(priceOrder == null) throw new BusinessException("价格清单不存在");
//            GoodsInfoPostVO costInfoForPost = costMapper.getCostInfoForPost(goodsId, lotId, enterpriseId);
            PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(enterpriseId,priceOrder.getId(),goodsId);
            BigDecimal retailPrice = priceOrderDetail.getRetailPrice();//零售单价

            //更新货位明细
            InventoryShelf shelf = (InventoryShelf) EntityUtils.reflectUpdateSetDefaultValue(InventoryShelf.class,userVO);

            CostInfoPostVO costTotalAmount = inOutRecordDetailMapper.getCostTotalQuantityAndAmount(enterpriseId, lotId, goodsId, shelfId,null);
            BigDecimal realAmountTotal = costTotalAmount.getRealAmountTotal();//账面金额
            BigDecimal notaxRealAmountTotal = costTotalAmount.getNotaxRealAmountTotal();//不含税实际金额
            BigDecimal quantityTotal = costTotalAmount.getQuantityTotal();
            if(quantity.compareTo(invQuantity) == -1){
                //盘盈
                /**
                 * 最近成本单价，优先查询同商品同批号的单价，其次同商品的单价，如果没有则为0
                 * 税率显示商品默认进项税
                 */
                post.setIsProfit(1);
                logger.info("盘盈账面金额={}",realAmountTotal);
                post.setAmount(realAmountTotal);//账面金额--
                unitPrice = post.getUnitPrice();
                logger.info("盘盈单价={}",unitPrice);
                post.setUnitPrice(unitPrice);
                BigDecimal diff = invQuantity.subtract(quantity);//损益数量quantity

                BigDecimal diffTotal = diff.multiply(unitPrice);//损益金额
                logger.info("盘盈损溢金额={}",diffTotal);
                post.setDiffAmount(diffTotal);
                BigDecimal realAmount = realAmountTotal.add(diffTotal);
                logger.info("盘盈实盘金额={}",realAmount);
                post.setRealAmount(realAmount);//实盘金额
                logger.info("盘盈不含税账面金额={}",notaxRealAmountTotal);
                post.setNotaxAmount(notaxRealAmountTotal);

                Long purchaseTaxRateId = post.getTaxRateId();
                GoodsTaxRate taxRate = goodsTaxRateMapper.selectByPrimaryKey(purchaseTaxRateId);
                if(taxRate == null) throw new BusinessException("税率不存在");

                BigDecimal purchaseTaxRate = taxRate.getTaxRate();

                //不含税损益金额
                BigDecimal notaxDiffTotal = diffTotal.divide(BigDecimal.ONE.add(purchaseTaxRate.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
                logger.info("盘盈不含税损益金额={}",notaxDiffTotal);
                post.setDiffNotaxAmount(notaxDiffTotal);
                //不含税单价
                noTaxPrice = notaxDiffTotal.divide(diff, 6, BigDecimal.ROUND_HALF_UP);
                post.setNotaxPrice(noTaxPrice);
                post.setTaxRate(purchaseTaxRate);//税
                post.setTaxRateId(purchaseTaxRateId);
                post.setRetailPrice(retailPrice);

                BigDecimal notaxRealAmount = notaxRealAmountTotal.add(notaxDiffTotal);//不含税实盘金额
                logger.info("盘盈不含税实盘金额={}",notaxRealAmount);
                post.setRealNotaxAmount(notaxRealAmount);

                BigDecimal taxAmount = realAmountTotal.subtract(notaxRealAmountTotal);//账面税额
                BigDecimal realTaxAmount = realAmount.subtract(notaxRealAmount);//实盘税额
                BigDecimal diffTaxAmount = diffTotal.subtract(notaxDiffTotal);//损益税额
                logger.info("盘盈账面税额={},实盘税额={},损益税额={}",taxAmount,realTaxAmount,diffTaxAmount);
                post.setTaxAmount(taxAmount);//账面税额
                post.setRealTaxAmount(realTaxAmount);//实盘税额
                post.setDiffTaxAmount(diffTaxAmount);//损益税额


                BigDecimal retailAmount = retailPrice.multiply(quantity);
                BigDecimal realRetailAmount = retailPrice.multiply(invQuantity);
                BigDecimal diffRetailAmount = realRetailAmount.subtract(retailAmount);
                logger.info("盘盈零售单价={},零售账面金额={},零售实盘金额={},零售损益金额={}",retailPrice,retailAmount,realRetailAmount,diffRetailAmount);
                post.setRetailPrice(retailPrice);
                post.setRetailAmount(retailAmount); //账面零售金额
                post.setRealRetailAmount(realRetailAmount);//实盘零售金额
                post.setDiffRetailAmount(diffRetailAmount);//损益零售金额


                shelf.setUnitPrice(unitPrice);
                shelf.setTaxRate(purchaseTaxRate);
                shelf.setTaxRateId(purchaseTaxRateId);
                shelf.setNotaxPrice(noTaxPrice);


            } else if (quantity.compareTo(invQuantity) == 1){

               List<InOutRecordDetail> goodsInShelfInOutDetailList = inOutRecordDetailMapper.getInOutDetailByParam(enterpriseId, lotId, goodsId, shelfId,0,"asc");
                /**
                 *  盘亏： 损溢金额/盘亏数量
                 *  先进先出
                 *  税率不显示
                 */
                //盘亏
                //先进先出的商品信息
                post.setIsProfit(0);
//                List<GoodsInfoPostVO> goodsStockForPost = costMapper.getGoodsStockForPost(enterpriseId, goodsId, lotId);
                BigDecimal diff = quantity.subtract(invQuantity);//损益数量quantity
                BigDecimal diffTotal = diff.multiply(unitPrice);//损益金额
                BigDecimal notaxDiffTotal = diff.multiply(noTaxPrice);//不含税损益金额
                for(InOutRecordDetail postVO : goodsInShelfInOutDetailList){
                    BigDecimal goodsQuantity = postVO.getQuantity();//商品出入库数量
                    BigDecimal realPrice = postVO.getRealPrice();//出入库实际单价
                    BigDecimal notaxRealPrice = postVO.getNotaxRealPrice();//出入库不含税实际单价
                    //使用损益的数量减去入库数量
                    diff = diff.subtract(goodsQuantity);
                    if(diff.compareTo(BigDecimal.ZERO) == 1){
                        //大于0
                        diffTotal = diffTotal.add(goodsQuantity.multiply(realPrice));
                        notaxDiffTotal = notaxDiffTotal.add(goodsQuantity.multiply(notaxRealPrice));
                    } else if(diff.compareTo(BigDecimal.ZERO) == 0){
                        //等于0
                        diffTotal = diffTotal.add(goodsQuantity.multiply(realPrice));
                        notaxDiffTotal = notaxDiffTotal.add(goodsQuantity.multiply(notaxRealPrice));
                        break;
                    } else {
                        //小于0
                        diffTotal = diffTotal.add(goodsQuantity.add(diff).multiply(realPrice));
                        notaxDiffTotal = notaxDiffTotal.add(goodsQuantity.add(diff).multiply(notaxRealPrice));
                        break;
                    }

                }

                post.setDiffAmount(diffTotal);//损益金额
                logger.info("损溢金额={}",diffTotal);
                post.setDiffNotaxAmount(notaxDiffTotal);//不含税损益金额
                logger.info("不含税损溢金额={}",notaxDiffTotal);

                post.setAmount(realAmountTotal);//账面金额
                BigDecimal realAmount = realAmountTotal.subtract(diffTotal);
                post.setRealAmount(realAmount);//实盘金额
                logger.info("盘亏账面金额={},实盘金额={}",realAmountTotal,realAmount);

                post.setNotaxAmount(notaxRealAmountTotal);//不含税账面金额
                BigDecimal notaxRealAmount = notaxRealAmountTotal.subtract(notaxDiffTotal);//不含实盘金额
                post.setRealNotaxAmount(notaxRealAmount);//不含实盘金额
                logger.info("盘亏不含税账面金额={},不含实盘金额={}",notaxRealAmountTotal,notaxRealAmount);

                if(BigDecimal.ZERO.compareTo(invQuantity) > 0){
                    unitPrice = realAmount.divide(invQuantity,6, RoundingMode.HALF_UP);
                    noTaxPrice = notaxRealAmount.divide(invQuantity,6, RoundingMode.HALF_UP);
                } else {
                    unitPrice = BigDecimal.ZERO;
                    noTaxPrice = BigDecimal.ZERO;
                }

                post.setUnitPrice(unitPrice);//单价
                post.setNotaxPrice(noTaxPrice);//不含税单价
                logger.info("盘亏单价={},不含税单价={}",unitPrice,noTaxPrice);

                BigDecimal taxAmount = realAmountTotal.subtract(notaxRealAmountTotal);//账面税额
                BigDecimal realTaxAmount = realAmount.subtract(notaxRealAmount);//实盘税额
                BigDecimal diffTaxAmount = diffTotal.subtract(notaxDiffTotal);//损益税额
                logger.info("盘亏账面税额={},实盘税额={},损益税额={}",taxAmount,realTaxAmount,diffTaxAmount);
                post.setTaxAmount(taxAmount);//账面税额
                post.setRealTaxAmount(realTaxAmount);//实盘税额
                post.setDiffTaxAmount(diffTaxAmount);//损益税额

                BigDecimal retailAmount = retailPrice.multiply(quantity);
                BigDecimal realRetailAmount = retailPrice.multiply(invQuantity);
                BigDecimal diffRetailAmount = realRetailAmount.subtract(retailAmount);
                post.setRetailPrice(retailPrice);
                post.setRetailAmount(retailAmount); //账面零售金额
                post.setRealRetailAmount(realRetailAmount);//实盘零售金额
                post.setDiffRetailAmount(diffRetailAmount);//损益零售金额
                logger.info("盘亏零售单价={},零售账面金额={},零售实盘金额={},零售损益金额={}",retailPrice,retailAmount,realRetailAmount,diffRetailAmount);


                shelf.setUnitPrice(unitPrice);
                shelf.setTaxRate(null);
                shelf.setTaxRateId(null);
                shelf.setNotaxPrice(noTaxPrice);
            } else {
                /**
                 *  无损溢
                 *  税率不显示
                 */
                post.setIsProfit(2);
                if(BigDecimal.ZERO.compareTo(quantityTotal) > 0){
                    unitPrice = realAmountTotal.divide(quantityTotal,6, RoundingMode.HALF_UP);
                    noTaxPrice = notaxRealAmountTotal.divide(quantityTotal,6, RoundingMode.HALF_UP);
                } else {
                    unitPrice = BigDecimal.ZERO;
                    noTaxPrice = BigDecimal.ZERO;
                }
//                unitPrice = realAmountTotal.divide(quantityTotal,6,BigDecimal.ROUND_HALF_UP);//单价
//                noTaxPrice = notaxRealAmountTotal.divide(quantityTotal,6,BigDecimal.ROUND_HALF_UP);//不含税单价
                post.setUnitPrice(unitPrice);
                post.setNotaxPrice(noTaxPrice);

                post.setAmount(realAmountTotal);//账面金额
                post.setRealAmount(realAmountTotal);//实盘金额
                post.setDiffAmount(BigDecimal.ZERO);//损益金额

                post.setNotaxAmount(notaxRealAmountTotal);//不含税账面金额
                post.setRealNotaxAmount(notaxRealAmountTotal);//不含税实盘金额
                post.setDiffNotaxAmount(BigDecimal.ZERO);//不含税损益金额
                BigDecimal taxAmount = realAmountTotal.subtract(notaxRealAmountTotal);
                post.setTaxAmount(taxAmount);//账面税额
                post.setRealTaxAmount(taxAmount);//实盘税额
                post.setDiffTaxAmount(BigDecimal.ZERO);//损益税额

                BigDecimal retailAmount = retailPrice.multiply(quantity);

                post.setRetailPrice(retailPrice);
                post.setRetailAmount(retailAmount); //账面零售金额
                post.setRealRetailAmount(retailAmount);//实盘零售金额
                post.setDiffRetailAmount(BigDecimal.ZERO);//损益零售金额

                shelf.setUnitPrice(unitPrice);
                shelf.setTaxRate(null);
                shelf.setTaxRateId(null);
                shelf.setNotaxPrice(noTaxPrice);

            }


            shelf.setId(post.getInventoryShelfId());

            shelf.setAmount(post.getAmount());
            shelf.setRealAmount(post.getRealAmount());
            shelf.setDiffAmount(post.getDiffAmount());

            shelf.setNotaxAmount(post.getNotaxAmount());
            shelf.setRealNotaxAmount(post.getRealNotaxAmount());
            shelf.setDiffNotaxAmount(post.getDiffNotaxAmount());

            shelf.setTaxAmount(post.getTaxAmount());
            shelf.setRealTaxAmount(post.getRealTaxAmount());
            shelf.setDiffTaxAmount(post.getDiffTaxAmount());

            shelf.setRetailAmount(post.getRetailAmount());
            shelf.setRealRetailAmount(post.getRealRetailAmount());
            shelf.setDiffRetailAmount(post.getDiffRetailAmount());
            shelf.setStatus(PubStatus.inventoryOrderStatus.WAIT_AUDIT);
            inventoryShelfMapper.updateByPrimaryKeySelective(shelf);

//            //更新库存
//            Stock stock = (Stock) EntityUtils.reflectUpdateSetDefaultValue(Stock.class,userVO);
//
//            BigDecimal diffQuantity = invQuantity.subtract(quantity);
//            stock.setQuantity(diffQuantity);
//            stock.setUsableQuantity(diffQuantity);
//            stock.setEnterpriseId(enterpriseId);
//            stock.setGoodsId(goodsId);
//            stock.setLotId(lotId);
//            stock.setShelfId(shelfId);
//            stock.setEnterpriseId(enterpriseId);
//            stockMapper.updateStockByEIdLotIdGIdSId(stock);

        }

        //转数据结构
        Map<Long,List<InventoryShelfForPostVO>> map = new HashMap<>();
        //转换数据结构
        for (InventoryDetailForPostVO item : detailList) {
            Long detailId = item.getId();//明细ID
            if(map.get(detailId) == null){
                List<InventoryShelfForPostVO> simpleVOList = new ArrayList<>();
                packSimpleInventoryShelf(item, simpleVOList);
                map.put(detailId,simpleVOList);
            } else {
                List<InventoryShelfForPostVO> simpleVOList = map.get(detailId);
                packSimpleInventoryShelf(item, simpleVOList);
                map.put(detailId,simpleVOList);
            }
        }

        /**
         * 账面金额
         */
        BigDecimal amountAllTotal = BigDecimal.ZERO;
        /**
         * 实盘金额
         */
        BigDecimal realAmountAllTotal= BigDecimal.ZERO;
        /**
         * 损益金额
         */
        BigDecimal diffAmountAllTotal = BigDecimal.ZERO;
        /**
         * 不含税账面金额
         */
        BigDecimal notaxAmountAllTotal = BigDecimal.ZERO;
        /**
         * 不含税实盘金额
         */
        BigDecimal realNotaxAmountAllTotal = BigDecimal.ZERO;
        /**
         * 不含税损益金额
         */
        BigDecimal diffNotaxAmountAllTotal = BigDecimal.ZERO;
        /**
         * 账面税额
         */
        BigDecimal taxAmountAllTotal = BigDecimal.ZERO;
        /**
         * 实盘税额
         */
        BigDecimal realTaxAmountAllTotal = BigDecimal.ZERO;
        /**
         * 损益税额
         */
        BigDecimal diffTaxAmountAllTotal = BigDecimal.ZERO;
        /**
         * 账面零售金额
         */
        BigDecimal retailAmountAllTotal = BigDecimal.ZERO;
        /**
         * 实盘零售金额
         */
        BigDecimal realRetailAmountAllTotal = BigDecimal.ZERO;
        /**
         * 损益零售金额
         */
        BigDecimal diffRetailAmountAllTotal = BigDecimal.ZERO;

        //操作新数据结构
        Iterator<Map.Entry<Long, List<InventoryShelfForPostVO>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<InventoryShelfForPostVO>> entry = iterator.next();
            Long detailId = entry.getKey();
            List<InventoryShelfForPostVO> shelfForPostVOList = entry.getValue();

            InventoryDetail inventoryDetail = inventoryDetailMapper.selectByPrimaryKey(detailId);
            //金额统计
            /**
             * 账面金额
             */
            BigDecimal amountDetailTotal = BigDecimal.ZERO;
            /**
             * 实盘金额
             */
            BigDecimal realAmountDetailTotal= BigDecimal.ZERO;
            /**
             * 损益金额
             */
            BigDecimal diffAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 不含税账面金额
             */
            BigDecimal notaxAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 不含税实盘金额
             */
            BigDecimal realNotaxAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 不含税损益金额
             */
            BigDecimal diffNotaxAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 账面税额
             */
            BigDecimal taxAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 实盘税额
             */
            BigDecimal realTaxAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 损益税额
             */
            BigDecimal diffTaxAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 账面零售金额
             */
            BigDecimal retailAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 实盘零售金额
             */
            BigDecimal realRetailAmountDetailTotal = BigDecimal.ZERO;
            /**
             * 损益零售金额
             */
            BigDecimal diffRetailAmountDetailTotal = BigDecimal.ZERO;

            for(InventoryShelfForPostVO shelfForPostVO : shelfForPostVOList){
//                InventoryShelf inventoryShelf = (InventoryShelf) EntityUtils.reflectUpdateSetDefaultValue(InventoryShelf.class,userVO);

                inventoryDetail.setUnitPrice(shelfForPostVO.getUnitPrice());
                inventoryDetail.setTaxRateId(shelfForPostVO.getTaxRateId());
                inventoryDetail.setTaxRate(shelfForPostVO.getTaxRate());
                inventoryDetail.setNotaxPrice(shelfForPostVO.getNotaxPrice());
                inventoryDetail.setRetailPrice(shelfForPostVO.getRetailPrice());

                //金额统计
                amountDetailTotal = amountDetailTotal.add(shelfForPostVO.getAmount());
                realAmountDetailTotal = realAmountDetailTotal.add(shelfForPostVO.getRealAmount());
                diffAmountDetailTotal = diffAmountDetailTotal.add(shelfForPostVO.getDiffAmount());

                notaxAmountDetailTotal = notaxAmountDetailTotal.add(shelfForPostVO.getNotaxAmount());
                realNotaxAmountDetailTotal = realNotaxAmountDetailTotal.add(shelfForPostVO.getRealNotaxAmount());
                diffNotaxAmountDetailTotal = diffNotaxAmountDetailTotal.add(shelfForPostVO.getDiffNotaxAmount());

                taxAmountDetailTotal = taxAmountDetailTotal.add(shelfForPostVO.getTaxAmount());
                realTaxAmountDetailTotal = realTaxAmountDetailTotal.add(shelfForPostVO.getRealTaxAmount());
                diffTaxAmountDetailTotal = diffTaxAmountDetailTotal.add(shelfForPostVO.getDiffTaxAmount());

                retailAmountDetailTotal = retailAmountDetailTotal.add(shelfForPostVO.getRetailAmount());
                realRetailAmountDetailTotal =  realRetailAmountDetailTotal.add(shelfForPostVO.getRealRetailAmount());
                diffRetailAmountDetailTotal = diffRetailAmountDetailTotal.add(shelfForPostVO.getDiffRetailAmount());

//                BeanUtils.copyProperties(shelfForPostVO,inventoryShelf);
//                inventoryShelf.setStatus(PubStatus.inventoryOrderStatus.FINISHED);
//                inventoryShelfMapper.updateByPrimaryKeySelective(inventoryShelf);
            }
            inventoryDetail.setAmount(amountDetailTotal);
            inventoryDetail.setRealAmount(realAmountDetailTotal);
            inventoryDetail.setDiffAmount(diffAmountDetailTotal);

            inventoryDetail.setNotaxAmount(notaxAmountDetailTotal);
            inventoryDetail.setRealNotaxAmount(realNotaxAmountDetailTotal);
            inventoryDetail.setDiffNotaxAmount(diffNotaxAmountDetailTotal);

            inventoryDetail.setTaxAmount(taxAmountDetailTotal);
            inventoryDetail.setRealTaxAmount(realTaxAmountDetailTotal);
            inventoryDetail.setDiffTaxAmount(diffTaxAmountDetailTotal);

            inventoryDetail.setRetailAmount(retailAmountDetailTotal);
            inventoryDetail.setRealRetailAmount(realRetailAmountDetailTotal);
            inventoryDetail.setDiffRetailAmount(diffRetailAmountDetailTotal);
            inventoryDetail.setStatus(PubStatus.inventoryOrderStatus.WAIT_AUDIT);
            inventoryDetailMapper.updateByPrimaryKeySelective(inventoryDetail);

            amountAllTotal = amountAllTotal.add(amountDetailTotal);
            realAmountAllTotal = realAmountAllTotal.add(realAmountDetailTotal);
            diffAmountAllTotal = diffAmountAllTotal.add(diffAmountDetailTotal);

            notaxAmountAllTotal = notaxAmountAllTotal.add(notaxAmountDetailTotal);
            realNotaxAmountAllTotal = realNotaxAmountAllTotal.add(realNotaxAmountDetailTotal);
            diffNotaxAmountAllTotal = diffNotaxAmountAllTotal.add(diffNotaxAmountDetailTotal);

            taxAmountAllTotal = taxAmountAllTotal.add(taxAmountDetailTotal);
            realTaxAmountAllTotal = realTaxAmountAllTotal.add(realTaxAmountDetailTotal);
            diffTaxAmountAllTotal = diffTaxAmountAllTotal.add(diffTaxAmountDetailTotal);

            retailAmountAllTotal = retailAmountAllTotal.add(retailAmountDetailTotal);
            realRetailAmountAllTotal = realRetailAmountAllTotal.add(realRetailAmountDetailTotal);
            diffRetailAmountAllTotal = diffRetailAmountAllTotal.add(diffAmountDetailTotal);



        }

        inventory.setAmountTotal(amountAllTotal);
        inventory.setRealAmountTotal(realAmountAllTotal);
        inventory.setDiffAmountTotal(diffAmountAllTotal);

        inventory.setNotaxAmountTotal(notaxAmountAllTotal);
        inventory.setRealNotaxAmountTotal(realNotaxAmountAllTotal);
        inventory.setDiffNotaxAmountTotal(diffNotaxAmountAllTotal);

        inventory.setTaxAmountTotal(taxAmountAllTotal);
        inventory.setRealTaxAmountTotal(realTaxAmountAllTotal);
        inventory.setDiffTaxAmountTotal(diffTaxAmountAllTotal);

        inventory.setRetailAmountTotal(retailAmountAllTotal);
        inventory.setRealRetailAmountTotal(realRetailAmountAllTotal);
        inventory.setDiffRetailAmountTotal(diffRetailAmountAllTotal);
        inventory.setStatus(PubStatus.inventoryOrderStatus.WAIT_AUDIT);
        inventoryMapper.updateByPrimaryKeySelective(inventory);

        //盘点单审核
        //审批流对象
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(enterpriseId,userVO.getEnterpriseName(),
                userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
                chainType.equals(ChainType.Headquarters.getCode()) ? enterpriseId : userVO.getParentId(),
                "0401", inventory.getId(), inventory.getCode(), "");
        approvalFlowComponent.apply(submitApprovalFlowVO,userVO);

        //财务数据更新
        financeComponent.inventoryToBalanceAndVoucher(userVO, inventory);

    }




    private void calculationAmount(BigDecimal unitPrice,BigDecimal notaxPrice,BigDecimal quantity,BigDecimal invQuantity,BigDecimal retailPrice,InventoryDetailForPostVO post){
        post.setUnitPrice(unitPrice);
        post.setNotaxPrice(notaxPrice);

        BigDecimal amount = unitPrice.multiply(quantity);//账面金额

        BigDecimal realAmount = unitPrice.multiply(invQuantity);//实盘金额
        BigDecimal diffAmount = realAmount.subtract(amount);//损益金额

        BigDecimal notaxAmount = notaxPrice.multiply(quantity);//不含税账面金额
        BigDecimal notaxRealAmount = notaxPrice.multiply(invQuantity);//不含税实盘金额
        BigDecimal notaxDiffAmount = notaxRealAmount.subtract(notaxAmount);//不含税损益金额


        BigDecimal taxAmount = amount.subtract(notaxAmount);//账面税额
        BigDecimal realTaxAmount = realAmount.subtract(notaxRealAmount);//实盘税额
        BigDecimal diffTaxAmount = diffAmount.subtract(notaxDiffAmount);//损益税额


        post.setAmount(amount);//账面金额
        post.setRealAmount(realAmount);//实盘金额
        post.setDiffAmount(diffAmount);//损益金额

        post.setNotaxAmount(notaxAmount);//不含税账面金额
        post.setRealNotaxAmount(notaxRealAmount);//不含税实盘金额
        post.setDiffNotaxAmount(notaxDiffAmount);//不含税损益金额

        post.setTaxAmount(taxAmount);//账面税额
        post.setRealTaxAmount(realTaxAmount);//实盘税额
        post.setDiffTaxAmount(diffTaxAmount);//损益税额

        BigDecimal retailAmount = retailPrice.multiply(quantity);
        BigDecimal realRetailAmount = retailPrice.multiply(invQuantity);
        BigDecimal diffRetailAmount = realRetailAmount.subtract(retailAmount);
        post.setRetailPrice(retailPrice);
        post.setRetailAmount(retailAmount); //账面零售金额
        post.setRealRetailAmount(realRetailAmount);//实盘零售金额
        post.setDiffRetailAmount(diffRetailAmount);//损益零售金额
    }

    private void calculationAmount2(BigDecimal unitPrice,BigDecimal notaxPrice,BigDecimal quantity,BigDecimal invQuantity,BigDecimal retailPrice,InventoryDetailForPostVO post){
        post.setUnitPrice(unitPrice);
        post.setNotaxPrice(notaxPrice);

        BigDecimal amount = unitPrice.multiply(quantity);//账面金额
        BigDecimal realAmount = unitPrice.multiply(invQuantity);//实盘金额
        BigDecimal diffAmount = realAmount.subtract(amount);//损益金额

        BigDecimal notaxAmount = notaxPrice.multiply(quantity);//不含税账面金额
        BigDecimal notaxRealAmount = notaxPrice.multiply(invQuantity);//不含税实盘金额
        BigDecimal notaxDiffAmount = notaxRealAmount.subtract(notaxAmount);//不含税损益金额


        BigDecimal taxAmount = amount.subtract(notaxAmount);//账面税额
        BigDecimal realTaxAmount = realAmount.subtract(notaxRealAmount);//实盘税额
        BigDecimal diffTaxAmount = diffAmount.subtract(notaxDiffAmount);//损益税额


        post.setAmount(amount);//账面金额
        post.setRealAmount(realAmount);//实盘金额
        post.setDiffAmount(diffAmount);//损益金额

        post.setNotaxAmount(notaxAmount);//不含税账面金额
        post.setRealNotaxAmount(notaxRealAmount);//不含税实盘金额
        post.setDiffNotaxAmount(notaxDiffAmount);//不含税损益金额

        post.setTaxAmount(taxAmount);//账面税额
        post.setRealTaxAmount(realTaxAmount);//实盘税额
        post.setDiffTaxAmount(diffTaxAmount);//损益税额

        BigDecimal retailAmount = retailPrice.multiply(quantity);
        BigDecimal realRetailAmount = retailPrice.multiply(invQuantity);
        BigDecimal diffRetailAmount = realRetailAmount.subtract(retailAmount);
        post.setRetailPrice(retailPrice);
        post.setRetailAmount(retailAmount); //账面零售金额
        post.setRealRetailAmount(realRetailAmount);//实盘零售金额
        post.setDiffRetailAmount(diffRetailAmount);//损益零售金额
    }


    private void packInventoryInfo(UserVO userVO, InventoryForAddVO inventoryForAddVO, Inventory inventory) {
        inventory.setEnterpriseId(userVO.getEnterpriseId());
        inventory.setParentId(userVO.getParentId());

        inventory.setCreateTime(inventoryForAddVO.getCreateTime());
        inventory.setInvType(inventoryForAddVO.getInvType());
        inventory.setInvRange(inventoryForAddVO.getInvRange());
        Long warehouseId = inventoryForAddVO.getWarehouseId();
        inventory.setWarehouseId(warehouseId);
        Warehouse warehouse = warehouseMapper.selectByPrimaryKey(warehouseId);
        inventory.setWarehouseName(warehouse.getName());


        String warehouseAreaIds = inventoryForAddVO.getWarehouseAreaIds();
        if(warehouseAreaIds != null){
            String[] split = warehouseAreaIds.split(",");
            StringBuilder areaNames = new StringBuilder();
            for(String str : split){
                WarehouseArea warehouseArea = warehouseAreaMapper.selectByPrimaryKey(Long.parseLong(str));
                if(warehouseArea != null){
                    areaNames.append(warehouseArea.getName()).append(",");
                }
            }
            inventory.setWarehouseAreaIds(warehouseAreaIds);
            inventory.setWarehouseAreaNames(areaNames.toString());
        }

        String cargoAreaIds = inventoryForAddVO.getCargoAreaIds();
        if(cargoAreaIds != null){
            String[] split = cargoAreaIds.split(",");
            StringBuilder cargoAreaNames = new StringBuilder();
            for(String str : split){
                WarehouseCargoArea cargoArea = cargoAreaMapper.selectByPrimaryKey(Long.parseLong(str));
                if(cargoArea != null){
                    cargoAreaNames.append(cargoArea.getName()).append(",");
                }
            }
            inventory.setCargoAreaIds(cargoAreaIds);
            inventory.setCargoAreaNames(cargoAreaNames.toString());
        }


        inventory.setRemark(inventoryForAddVO.getRemark());
    }





    private void packGoodsShelfInfo(Long goodsId, GoodsInfoStockShelfVO goodsShelfVO, InventoryShelf shelf,BigDecimal invQuantity) {
        shelf.setGoodsId(goodsId);
        shelf.setGoodsCode(goodsShelfVO.getGoodsCode());
        shelf.setGoodsName(goodsShelfVO.getGoodsName());
        shelf.setLotId(goodsShelfVO.getLotNumberId());
        shelf.setLotNumber(goodsShelfVO.getLotNum());
        shelf.setValidDate(goodsShelfVO.getValidUtil());
        shelf.setProductDate(goodsShelfVO.getProductDate());
        shelf.setShelfId(goodsShelfVO.getShelfId());
        shelf.setShelfName(goodsShelfVO.getShelfName());
        shelf.setShelfStatusDesc(goodsShelfVO.getShelfStatusDesc());
        shelf.setQuantity(goodsShelfVO.getQuantity());
        shelf.setInvQuantity(invQuantity);//实盘数量
        shelf.setDiffQuantity(invQuantity.subtract(goodsShelfVO.getQuantity()));
    }

    private void packGoodsInfo(InventoryDetail detail, Long goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        detail.setDosageId(goods.getDosageId());
        detail.setDosageName(goods.getDosageName());
        detail.setGoodsSpecification(goods.getSpecification());
        detail.setBarcode(goods.getBarcode());
        detail.setGoodsCode(goods.getCode());
        detail.setGoodsGenericName(goods.getGenericName());
        detail.setGoodsPlace(goods.getPlace());
        detail.setManufacturer(goods.getManufacturer());
        detail.setManufacturerId(goods.getManufacturerId());
        detail.setApprovalNumber(goods.getApprovalNumber());
        detail.setUnitId(goods.getUnitId());
        detail.setUnitName(goods.getUnitName());
        detail.setGoodsName(goods.getName());
        detail.setGoodsId(goodsId);
    }

    private void packSimpleInventoryShelf(InventoryDetailForPostVO item, List<InventoryShelfForPostVO> simpleVOList) {
        InventoryShelfForPostVO simpleVO = new InventoryShelfForPostVO();

        simpleVO.setLotId(item.getLotId());
        simpleVO.setLotNumber(item.getLotNumber());
        simpleVO.setQuantity(item.getQuantity());
        simpleVO.setInvQuantity(item.getInvQuantity());
        simpleVO.setShelfId(item.getShelfId());
        simpleVO.setShelfName(item.getShelfName());
        simpleVO.setRetailPrice(item.getRetailPrice());
        simpleVO.setUnitPrice(item.getUnitPrice());
        simpleVO.setTaxRateId(item.getTaxRateId());
        simpleVO.setTaxRate(item.getTaxRate());
        simpleVO.setShelfStatusDesc(item.getShelfStatusDesc());
        simpleVO.setProductDate(item.getProductDate());
        simpleVO.setValidDate(item.getValidDate());
        simpleVO.setId(item.getId());
        simpleVO.setGoodsId(item.getGoodsId());
        simpleVO.setGoodsCode(item.getGoodsCode());
        simpleVO.setGoodsName(item.getGoodsName());



        simpleVO.setAmount(item.getAmount());
        simpleVO.setRealAmount(item.getRealAmount());
        simpleVO.setDiffAmount(item.getDiffAmount());

        simpleVO.setNotaxAmount(item.getNotaxAmount());
        simpleVO.setRealNotaxAmount(item.getRealNotaxAmount());
        simpleVO.setDiffNotaxAmount(item.getDiffNotaxAmount());

        simpleVO.setTaxAmount(item.getTaxAmount());
        simpleVO.setRealTaxAmount(item.getRealTaxAmount());
        simpleVO.setDiffTaxAmount(item.getDiffTaxAmount());

        simpleVO.setRetailAmount(item.getRetailAmount());
        simpleVO.setRealRetailAmount(item.getRealRetailAmount());
        simpleVO.setDiffRetailAmount(item.getDiffRetailAmount());

        simpleVOList.add(simpleVO);
    }






}