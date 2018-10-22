//package com.rograndec.feijiayun.chain.business.storage.inventory.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.google.common.collect.Sets;
//import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
//import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseAreaMapper;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseMapper;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.Warehouse;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseAreaVO;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseCargoAreaSimpleVO;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfVO;
//import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
//import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
//import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
//import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
//import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
//import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsStockInfoVO;
//import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
//import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
//import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
//import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
//import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
//import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
//import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
//import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.constant.InvType;
//import com.rograndec.feijiayun.chain.business.storage.inventory.constant.InventoryBizExceptionEnum;
//import com.rograndec.feijiayun.chain.business.storage.inventory.constant.InventoryGoodsInfoConstant;
//import com.rograndec.feijiayun.chain.business.storage.inventory.constant.RegisterType;
//import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryDetailMapper;
//import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryMapper;
//import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryShelfMapper;
//import com.rograndec.feijiayun.chain.business.storage.inventory.entity.Inventory;
//import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryDetail;
//import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryShelf;
//import com.rograndec.feijiayun.chain.business.storage.inventory.excel.InventoryRegisterIRowReader;
//import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
//import com.rograndec.feijiayun.chain.business.storage.inventory.service.InventoryOrderService;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.*;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.InventoryForDiffDetailVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.InventoryShelfSimpleForDiffVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.RequestParamForDiffListVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterExcelVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterOKVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.ResponseGoodsRegisterExcelVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.*;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO2;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryGoodsInfoVO;
//import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.RequestParamForHadRegisterListVO;
//import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
//import com.rograndec.feijiayun.chain.common.Page;
//import com.rograndec.feijiayun.chain.common.component.*;
//import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
//import com.rograndec.feijiayun.chain.common.constant.InventoryStatus;
//import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
//import com.rograndec.feijiayun.chain.common.constant.OrderRule;
//import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
//import com.rograndec.feijiayun.chain.common.model.OrderModel;
//import com.rograndec.feijiayun.chain.common.vo.UserVO;
//import com.rograndec.feijiayun.chain.exception.BusinessException;
//import com.rograndec.feijiayun.chain.utils.FileUtils;
//import com.rograndec.feijiayun.chain.utils.SerializeUtil;
//import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
//import com.rograndec.feijiayun.chain.utils.excel.ExcelReaderUtil;
//import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * 功能描述：
// * Created by ST on 2017/9/29 13:29
// */
//@Service
//public class InventoryOrderServiceImpl22 implements InventoryOrderService {
//
//    @Autowired
//    private InventoryMapper inventoryMapper;
//
//
//    @Autowired
//    private GoodsMapper goodsMapper;
//
//    @Autowired
//    private ManageConfigComponent manageConfigComponent;
//
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private WarehouseMapper warehouseMapper;
//
//    @Autowired
//    private WarehouseAreaMapper warehouseAreaMapper;
//
//    @Autowired
//    private WarehouseCargoAreaMapper cargoAreaMapper;
//
//    @Autowired
//    private StockMapper stockMapper;
//
//    @Autowired
//    private OrderCodeComponent orderCodeComponent;
//
//    @Autowired
//    private InventoryDetailMapper inventoryDetailMapper;
//
//    @Autowired
//    private InventoryShelfMapper inventoryShelfMapper;
//
//    @Autowired
//    private PurchaseGeneralComponent purchaseGeneralComponent;
//    @Autowired
//    private CostMapper costMapper;
//
//    @Autowired
//    private GoodsTaxRateMapper goodsTaxRateMapper;
//
//    @Autowired
//    private GoodsBusinessMapper goodsBusinessMapper;
//
//    @Autowired
//    private PriceOrderDetailMapper priceOrderDetailMapper;
//
//    @Autowired
//    private InventoryRegisterIRowReader inventoryRegisterIRowReader;
//
//    @Autowired
//    private RedisComponent redisComponent;
//
//
//    @Autowired
//    private InOutComponent inOutComponent;
//
//
//    /**
//     * 盘点单查询
//     * @param page
//     * @param paramForListVO
//     * @param enterpriseId
//     */
//    @Override
//    public void getInventoryList(Page<List<InventoryForOrderListVO>> page, RequestParamForListVO paramForListVO, Long enterpriseId) {
//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(paramForListVO.getPageNo(), paramForListVO.getPageSize());
//        List<InventoryForOrderListVO> inventoryList = inventoryMapper.getInventoryList(paramForListVO, enterpriseId);
//        page.setResult(inventoryList);
//        page.setTotalRecord((int)objects.getTotal());
//    }
//
//    /**
//     * 查询当前企业的仓库
//     *
//     * @param enterpriseId
//     * @return
//     */
//    @Override
//    public List<WarehouseVO> getWarehouseVO(Long enterpriseId) {
//        return warehouseMapper.getWarehouseByEnterpriseId(enterpriseId);
//    }
//
//    /**
//     * 查询当前企业的库区
//     *
//     * @param enterpriseId
//     * @return
//     */
//    @Override
//    public List<WarehouseAreaVO> getWarehouseAreaByEnterpriseIdAndHID(Long enterpriseId,Long warehouseId) {
//        return warehouseMapper.getWarehouseAreaByEnterpriseIdAndHID(enterpriseId,warehouseId);
//    }
//
//    /**
//     * 查询当前企业的货位
//     *
//     * @param enterpriseId
//     * @return
//     */
//    @Override
//    public List<WarehouseCargoAreaSimpleVO> getWarehouseCargoAreaByEnterpriseIdAndHID(Long enterpriseId, List<Long> warehouseAreaIds) {
//        return warehouseMapper.getWarehouseCargoAreaByEnterpriseIdAndHID(enterpriseId,warehouseAreaIds);
//    }
//
//    /**
//     * 搜索商品
//     * @param enterpriseId
//     * @param goodsStockVO
//     * @return
//     */
//    @Override
//    public List<GoodsStockInfoVO> getGoodsInfoForInventory(Long enterpriseId, RequestGoodsStockVO goodsStockVO){
//        Integer invType = goodsStockVO.getInvType();
//        String param = goodsStockVO.getParam();
//        List<Long> shelfIds = new ArrayList<>();;
//        //仓库id
//        Long warehouseId = goodsStockVO.getWarehouseId();
//        //库区ids
//        List<Long> warehouseAreaIds = goodsStockVO.getWarehouseAreaIds();
//        //货区ids
//        List<Long> warehouseCargoAreaIds = goodsStockVO.getWarehouseCargoAreaIds();
//
//        if(warehouseId != null){
//            shelfIds = getShelfIds(enterpriseId, warehouseId, warehouseAreaIds, warehouseCargoAreaIds);
//            if(CollectionUtils.isEmpty(shelfIds)){
//               return new ArrayList<>();
//            }
//        } else {
//            return new ArrayList<>();
//        }
//        List<GoodsStockInfoVO> goodsList = new ArrayList<>();
//        if(invType == InvType.SHELF.getCode()){
//            //按货位
//            goodsList = goodsMapper.getGoodsInfoForInventory(shelfIds, enterpriseId, param);
//        } else if(invType == InvType.GOODS.getCode()){
//            //按商品
//            goodsList = goodsMapper.getGoodsInfoForInventoryGroupByGoodsId(shelfIds, enterpriseId, param);
//        }
//        return goodsList;
//    }
//
//    private List<Long> getShelfIds(Long enterpriseId, Long warehouseId, List<Long> warehouseAreaIds, List<Long> warehouseCargoAreaIds) {
//        List<Long> shelfIds;
//        if(CollectionUtils.isEmpty(warehouseAreaIds)){
//            warehouseAreaIds = null;
//        }
//        if(CollectionUtils.isEmpty(warehouseCargoAreaIds)){
//            warehouseCargoAreaIds = null;
//        }
//        List<Long> warehouseIds = Arrays.asList(warehouseId);
//        List<WarehouseShelfVO> shelfVOList = warehouseMapper.getShelfIdByParam(enterpriseId, EnableStatus.ENABLE.getStatus(),warehouseIds,warehouseAreaIds,warehouseCargoAreaIds);
//        shelfIds = shelfVOList.stream().map(shelf -> shelf.getId()).collect(Collectors.toList());
//        return shelfIds;
//    }
//
//    @Override
//    public void getGoodsInfoForInventoryPage(Page<List<GoodsStockInfoVO>> page, RequestGoodsStockVOPage goodsStockVO, Long enterpriseId) {
//        com.github.pagehelper.Page<Object> objects  = PageHelper.startPage(page.getStart(),page.getPageSize());
//        Integer invType = goodsStockVO.getInvType();
//        String param = goodsStockVO.getParam();
//        List<Long> shelfIds = new ArrayList<>();;
//        //仓库id
//        Long warehouseId = goodsStockVO.getWarehouseId();
//        //库区ids
//        List<Long> warehouseAreaIds = goodsStockVO.getWarehouseAreaIds();
//        //货区ids
//        List<Long> warehouseCargoAreaIds = goodsStockVO.getWarehouseCargoAreaIds();
//        List<GoodsStockInfoVO> goodsList = new ArrayList<>();
//        if(warehouseId != null){
//            shelfIds = getShelfIds(enterpriseId, warehouseId, warehouseAreaIds, warehouseCargoAreaIds);
//            if(CollectionUtils.isEmpty(shelfIds)){
//                page.setResult(goodsList);
//                page.setTotalRecord((int) objects.getTotal());
//                return;
//            }
//        } else {
//            page.setResult(goodsList);
//            page.setTotalRecord((int) objects.getTotal());
//            return;
//        }
//
//
//        if(invType == InvType.SHELF.getCode()){
//            //按货位
//            goodsList = goodsMapper.getGoodsInfoForInventory(shelfIds, enterpriseId, param);
//        } else if(invType == InvType.GOODS.getCode()){
//            //按商品
//            goodsList = goodsMapper.getGoodsInfoForInventoryGroupByGoodsId(shelfIds, enterpriseId, param);
//        }
//        page.setResult(goodsList);
//        page.setTotalRecord((int) objects.getTotal());
//    }
//
//    @Override
//    public ResponseGoodsRegisterExcelVO excelImport(HttpServletRequest request) throws Exception{
//        HttpSession session = request.getSession(true);
//        UserVO userVO = (UserVO) session.getAttribute("user");
//        String id = request.getParameter("id");//盘点单id
//        String registerType = request.getParameter("registerType");
//        if(id == null){
//            throw new RuntimeException("盘点单id 不能为空");
//        }
//        Inventory inventory = inventoryMapper.selectByPrimaryKey(Long.parseLong(id));
//        if(inventory == null){
//            throw new RuntimeException("盘点单不存在");
//        }
//        Integer invType = inventory.getInvType();//0/按货位； 1/按商品
//
//        inventoryRegisterIRowReader.setInvType(invType);
//        inventoryRegisterIRowReader.setUserVO(userVO);
//        inventoryRegisterIRowReader.setInvId(Long.valueOf(id));
//
//        Long enterpriseId = userVO.getEnterpriseId();
//        Part part = request.getPart("file");
//        InputStream input = part.getInputStream();
//        //合格产品
//        List<GoodsShelfForRegisterExcelVO> qualifiedList = new ArrayList<>();
//        //不合格产品
//        List<GoodsShelfForRegisterExcelVO> disqualificationList = new ArrayList<>();
//
//        //标准入库实体list
//        List<GoodsShelfForRegisterOKVO> qualifiedGVOList = new ArrayList<>();
//        inventoryRegisterIRowReader.setQualifiedList(qualifiedList);
//        inventoryRegisterIRowReader.setDisqualificationList(disqualificationList);
//
//        inventoryRegisterIRowReader.setQualifiedGVOList(qualifiedGVOList);
//
//
//        if(invType == InvType.SHELF.getCode()){
//            //按货位
//            ExcelReaderUtil.excelToArrayList(inventoryRegisterIRowReader, FileUtils.getFileName(part), input, 11, 0);
//        } else {
//            //按商品
//            ExcelReaderUtil.excelToArrayList(inventoryRegisterIRowReader, FileUtils.getFileName(part), input, 7, 0);
//        }
//        if(registerType == RegisterType.BOOK_REGISTER.getValue()){
//            //excel中包含的盘点的商品信息
//            List<Long> excelInventoryShelfIdList = inventoryRegisterIRowReader.getInventoryShelfIdList();
//            //盘点单的包含盘点商品的数量
//            List<InventoryShelf> inventoryShelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId, null, Long.valueOf(id), null);
//            if(excelInventoryShelfIdList.size() < inventoryShelfList.size()){
//                throw new InventoryBizException("","Excel中包含的商品信息数量少于对应盘点单的商品的数量");
//            }
//        }
//
//
//        ResponseGoodsRegisterExcelVO goodsExcelVO = new ResponseGoodsRegisterExcelVO();
//        goodsExcelVO.setQualifiedGoodsList(inventoryRegisterIRowReader.getQualifiedList());
//        goodsExcelVO.setDisqualificationGoodsList(inventoryRegisterIRowReader.getDisqualificationList());
//        //向redis中存储合格/不合格的商品
//        Long timestamp = System.currentTimeMillis();
//        List<GoodsShelfForRegisterExcelVO> qualifiedList2Redis = inventoryRegisterIRowReader.getQualifiedList();
//        List<GoodsShelfForRegisterExcelVO> disQualifiedList2Redis = inventoryRegisterIRowReader.getDisqualificationList();
//        List<GoodsShelfForRegisterOKVO> qualifiedGVOList2Redis = inventoryRegisterIRowReader.getQualifiedGVOList();
//
//        redisComponent.set(InventoryGoodsInfoConstant.INVENTORY_QUALIFIED_FIELD + timestamp, SerializeUtil.serialize(qualifiedList2Redis));
//        redisComponent.set(InventoryGoodsInfoConstant.INVENTORY_DISQUALIFIED_FIELD + timestamp,SerializeUtil.serialize(disQualifiedList2Redis));
//        redisComponent.set(InventoryGoodsInfoConstant.INVENTORY_STANDARD_QUALIFIED_FIELD + timestamp,SerializeUtil.serialize(qualifiedGVOList2Redis));
//
//        goodsExcelVO.setTimestamp(timestamp.toString());
//        return goodsExcelVO;
//    }
//
//
//    @Override
//    public List<GoodsShelfForRegisterOKVO> getSuccessData(String key) throws Exception {
//        byte[] kk = (byte[]) redisComponent.get(InventoryGoodsInfoConstant.INVENTORY_STANDARD_QUALIFIED_FIELD + key);
//        List<GoodsShelfForRegisterOKVO> list = SerializeUtil.deserialize(kk);
//        return list;
//    }
//
//    @Override
//    public Integer getInventoryCountData(UserVO userVO, Long invId) throws Exception {
//        Long enterpriseId = userVO.getEnterpriseId();
//        Inventory inventory = inventoryMapper.selectByPrimaryKey(invId);
//        if(inventory.getInvType() == InvType.SHELF.getCode()){
//            List<InventoryShelf> inventoryShelfByParam = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId, null, invId, null);
//            if(inventoryShelfByParam != null){
//                return inventoryShelfByParam.size();
//            }
//        } else if(inventory.getInvType() == InvType.GOODS.getCode()){
//            List<InventoryDetailForOrderDetailVO> inventoryShelfByParam = inventoryDetailMapper.getDetailByInvId(invId,enterpriseId);
//            if(inventoryShelfByParam != null){
//                return inventoryShelfByParam.size();
//            }
//        }
//        return null;
//    }
//
//
//
//    /**
//     * 导出成功数据
//     *
//     * @param output
//     * @param key
//     * @param type 1--导出成功数据；2--导出失败数据
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public void exportGoods(OutputStream output, String key, Integer type,Integer invType) throws Exception {
//        List<GoodsShelfForRegisterExcelVO> list = new ArrayList<>();
//        if(type == 1){
//            list = (List<GoodsShelfForRegisterExcelVO>) SerializeUtil.deserialize((byte[]) redisComponent.get(InventoryGoodsInfoConstant.INVENTORY_QUALIFIED_FIELD + key));
//        } else {
//            list = (List<GoodsShelfForRegisterExcelVO>) SerializeUtil.deserialize((byte[]) redisComponent.get(InventoryGoodsInfoConstant.INVENTORY_DISQUALIFIED_FIELD + key));
//        }
//        createExcel(output, list,invType);
//    }
//
//    private void createExcel(OutputStream output, List<GoodsShelfForRegisterExcelVO> list,Integer invType) {
//        try {
//            final List<GoodsShelfForRegisterExcelVO> finalList = list;
//            ExcelWriter writer = new ExcelWriter() {
//                public void generate() throws Exception {
//                    // 电子表格开始
//                    this.beginWorkSheet();
//                    this.beginSheet();
//                    createRowHeader(this,invType);
//                    for (int rowNum = 0; rowNum < finalList.size(); rowNum++) {
//                        GoodsShelfForRegisterExcelVO goodsVO = finalList.get(rowNum);
//                        // 插入新行
//                        this.insertRow(rowNum + 1);
//                        // 建立新单元格,索引值从0开始,表示第一列
//                        int k = 0;
//                        this.createCell(k++, rowNum + 1);
//                        this.createCell(k++, goodsVO.getGoodsCode());
//                        this.createCell(k++, goodsVO.getGoodsGenericName());
//                        this.createCell(k++, goodsVO.getDosageName());
//                        this.createCell(k++, goodsVO.getUnitName());
//                        this.createCell(k++, goodsVO.getManufacturer());
//                        this.createCell(k++, goodsVO.getGoodsPlace());
//                        if(invType == InvType.SHELF.getCode()){
//                            this.createCell(k++, goodsVO.getLotNumber());
//                            this.createCell(k++, goodsVO.getProductDate());
//                            this.createCell(k++, goodsVO.getValidDate());
//                            this.createCell(k++, goodsVO.getShelfName());
//                        }
//
//                        this.createCell(k, goodsVO.getInvQuantity());
//
//                        // 结束行
//                        this.endRow();
//                    }
//                    // 电子表格结束
//                    this.endSheet();
//                    this.endWorkSheet();
//                }
//            };
//            writer.process(output);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//    private void createRowHeader(ExcelWriter writer,Integer invType) throws IOException {
//        // 插入新行
//        writer.insertRow(0);
//        // 建立新单元格,索引值从0开始,表示第一列
//        int k = 0;
//        writer.createCell(k++, "序号");
//        writer.createCell(k++, "商品编码");
//        writer.createCell(k++, "通用名称");
//        writer.createCell(k++, "规格");
//        writer.createCell(k++, "库存计量单位");
//        writer.createCell(k++, "生产厂商");
//        writer.createCell(k++, "产地");
//        if(invType == InvType.SHELF.getCode()){
//            writer.createCell(k++, "批号");
//            writer.createCell(k++, "生产日期");
//            writer.createCell(k++, "有效期至");
//            writer.createCell(k++, "货位");
//        }
//        writer.createCell(k, "实盘数量");
//        // 结束行
//        writer.endRow();
//    }
//
//    @Override
//    public void cancelInventoryOrder(UserVO userVO, Long id) throws Exception {
//        Long enterpriseId = userVO.getEnterpriseId();
//        Inventory inventory = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
//        inventory.setId(id);
//        inventory.setStatus(PubStatus.inventoryOrderStatus.CANCELED);
//        inventoryMapper.updateByPrimaryKeySelective(inventory);
//        inventoryDetailMapper.updateDetailByInvId(id,PubStatus.inventoryOrderStatus.CANCELED,enterpriseId);
//        inventoryShelfMapper.updateShelfByInvId(id,PubStatus.inventoryOrderStatus.CANCELED,enterpriseId);
//    }
//
//    /**
//     * 盘点登记按商品登记查询商品
//     *
//     * @param userVO
//     * @param invId
//     * @param param
//     * @return
//     */
//    @Override
//    public List<InventoryGoodsInfoVO> getGoodsInfoInInventory(UserVO userVO, Long invId, String param) {
//        Inventory inventory = inventoryMapper.selectByPrimaryKey(invId);
//        if(inventory.getInvType() == InvType.SHELF.getCode()){
//            return inventoryDetailMapper.getGoodsInfoInInventory2Shelf(invId,param,userVO.getEnterpriseId());
//        } else if(inventory.getInvType() == InvType.GOODS.getCode()){
//            return inventoryDetailMapper.getGoodsInfoInInventory(invId,param,userVO.getEnterpriseId());
//        }
//        return new ArrayList<>();
//
//    }
//
//    /**
//     * 保存盘点单
//     *
//     * @param userVO
//     * @param inventoryForAddVO
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void saveInventoryOrder(UserVO userVO, InventoryForAddVO inventoryForAddVO) throws Exception {
//        Long enterpriseId = userVO.getEnterpriseId();
//
//        Inventory inventory = new Inventory();
//        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO.getEnterpriseId());
//        if(mangeConfig.getBusinessControl() == 0){
//            //关闭
//            User user = userMapper.selectByPrimaryKey(inventoryForAddVO.getCreaterId());
//            inventory.setCreaterId(user.getId());
//            inventory.setCreaterCode(user.getCode());
//            inventory.setCreaterName(user.getName());
//        } else {
//            //开启
//            inventory.setCreaterId(userVO.getUserId());
//            inventory.setCreaterCode(userVO.getUserCode());
//            inventory.setCreaterName(userVO.getUserName());
//        }
//        inventory.setCreateTime(new Date());
//
//
//        //封装盘点信息
//        packInventoryInfo(userVO, inventoryForAddVO, inventory);
//
//        //盘点单据编码
//        String code = orderCodeComponent.generate(OrderRule.INVENTORY.getCodePrefix(),userVO.getEnterpriseId(),userVO.getEnterpriseCode());
//        inventory.setCode(code);
//        inventory.setOrderType(OrderRule.INVENTORY.getType());
//        inventory.setStatus(PubStatus.inventoryOrderStatus.WAIT_REGISTER);
//        inventoryMapper.insertSelective(inventory);
//
//        Integer invType = inventoryForAddVO.getInvType();
//
//        List<InventoryDetailForAddVO> detailForAddVOList = inventoryForAddVO.getDetailForAddVOList();
//
//        BigDecimal quantityAllTotal = BigDecimal.ZERO;
//        if(invType == InvType.SHELF.getCode()){
//            //按货位
//
//            Map<String,List<InventoryShelfSimpleVO>> map = new HashMap<>();
//            //转换数据结构
//            for (InventoryDetailForAddVO item : detailForAddVOList) {
//                Long goodsId = item.getGoodsId();//商品id
//                Long shelfId = item.getShelfId();
//                Long lotId = item.getLotId();
//                String key = goodsId + "," + shelfId + "," + lotId;
//                if(map.get(key) == null){
//                    List<InventoryShelfSimpleVO> simpleVOList = new ArrayList<>();
//                    packSimpleInventoryShelf(item, simpleVOList);
//                    map.put(key,simpleVOList);
//                } else {
//                    List<InventoryShelfSimpleVO> simpleVOList = map.get(key);
//                    packSimpleInventoryShelf(item, simpleVOList);
//                    map.put(key,simpleVOList);
//                }
//            }
//
//
//            //操作新的数据结构
//            Iterator<Map.Entry<String, List<InventoryShelfSimpleVO>>> iterator = map.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, List<InventoryShelfSimpleVO>> entry = iterator.next();
//                String key = entry.getKey();
//                Long gId = Long.valueOf(key.split(",")[0]);
//                List<InventoryShelfSimpleVO> simpleVOList = entry.getValue();
//
//                //创建明细实例
//                InventoryDetail detail =(InventoryDetail) EntityUtils.reflectAddSetDefaultValue(InventoryDetail.class,userVO);
//                //设置盘点单的属性
//                detail.setInvDate(inventory.getInvDate());
//                detail.setStatus(PubStatus.inventoryOrderStatus.WAIT_REGISTER);
//                //商品属性
//                packGoodsInfo(detail, gId);
//
////                BigDecimal quantityTotal = BigDecimal.ZERO;
////                for (InventoryShelfSimpleVO item : simpleVOList) {
////                    BigDecimal quantity = item.getQuantity();
////                    quantityTotal = quantityTotal.add(quantity);
////                }
//                BigDecimal quantityTotal = simpleVOList.stream().filter(Objects::nonNull)
//                        .filter(c->c.getQuantity() != null)
//                        .map(InventoryShelfSimpleVO::getQuantity)
//                        .reduce(BigDecimal.ZERO,BigDecimal::add);
//                quantityAllTotal = quantityAllTotal.add(quantityTotal);
//                detail.setQuantity(quantityTotal);
//                detail.setInvId(inventory.getId());
//                detail.setInvCode(code);
//                detail.setInvDate(new Date());
//                detail.setOrderType(OrderRule.INVENTORY.getType());
//                inventoryDetailMapper.insertSelective(detail);
//
//                Set<String> goodsIdLotIdShelfIdSet = new HashSet<>();
//                Map<String,BigDecimal> invQuantityMap = new HashMap<>();
//                for (InventoryShelfSimpleVO item : simpleVOList) {
//                    String goodsIdLotIdShelfId = item.getGoodsId() + "" + item.getLotId() + "" +  item.getShelfId() + "";
//                    goodsIdLotIdShelfIdSet.add(goodsIdLotIdShelfId);
//                    invQuantityMap.put(goodsIdLotIdShelfId,Optional.ofNullable(item.getInvQuantity()).orElse(BigDecimal.ZERO));
//                }
//
//                //货位明细
//                packGoodsShelf(userVO, enterpriseId, inventory, detail, gId,PubStatus.inventoryOrderStatus.WAIT_REGISTER,goodsIdLotIdShelfIdSet,invQuantityMap);
//
//            }
//
//        } else {
//            //按商品
//            //保存盘点明细
//            //商品属性
//
//            for (InventoryDetailForAddVO item: detailForAddVOList) {
//                InventoryDetail detail =(InventoryDetail) EntityUtils.reflectAddSetDefaultValue(InventoryDetail.class,userVO);
//                Long goodsId = item.getGoodsId();
//                //设置盘点单的属性
//                detail.setInvDate(inventory.getInvDate());
//                detail.setStatus(PubStatus.inventoryOrderStatus.WAIT_REGISTER);
////                detail.setQuantity(quantityTotal);
//                detail.setInvId(inventory.getId());
//                detail.setInvCode(code);
//                detail.setInvDate(new Date());
//                detail.setOrderType(OrderRule.INVENTORY.getType());
//                //按商品
//                //保存盘点明细
//                //商品属性
//                packGoodsInfo(detail, goodsId);
//                inventoryDetailMapper.insertSelective(detail);
//                packShelfInfoForAdd(userVO, enterpriseId, inventory, detail, goodsId);
//
//                quantityAllTotal = quantityAllTotal.add(item.getQuantity());
//            }
//
//        }
//        inventory.setQuantityTotal(quantityAllTotal);
//        inventoryMapper.updateByPrimaryKeySelective(inventory);
//
//        List<InventoryShelf> shelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId, null, inventory.getId(), null);
//        shelfList.forEach(item->{
//            //更新库存盘点状态
//          stockMapper.updateStockInventoryStatus(enterpriseId,item.getGoodsId(),item.getLotId(),item.getShelfId(), InventoryStatus.IN_INVENTORY.getStatus());
//
//        });
//
//
//    }
//
//    private void packShelfInfoForAdd(UserVO userVO, Long enterpriseId, Inventory inventory, InventoryDetail detail, Long goodsId) throws Exception {
//        List<GoodsInfoStockShelfVO> goodsStockInfoList = stockMapper.getGoodsStockInfoByGoodsId(enterpriseId, goodsId);
//        BigDecimal quantity = BigDecimal.ZERO;
//        //商品货位明细
//        for(GoodsInfoStockShelfVO goodsShelfVO : goodsStockInfoList){
//            InventoryShelf shelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
//            shelf.setDtlId(detail.getId());
//            shelf.setInvId(inventory.getId());
//            shelf.setStatus(PubStatus.inventoryOrderStatus.WAIT_REGISTER);
//            packGoodsShelfInfo(goodsId, goodsShelfVO, shelf,BigDecimal.ZERO);
//            inventoryShelfMapper.insertSelective(shelf);
//            quantity = quantity.add(goodsShelfVO.getUsableQuantity());
//        }
//        InventoryDetail updateDetail =(InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class,userVO);
//        updateDetail.setId(detail.getId());
//        updateDetail.setQuantity(quantity);
//        inventoryDetailMapper.updateByPrimaryKeySelective(updateDetail);
//    }
//
//    /**
//     * 修改盘点单
//     * @param userVO
//     * @param inventoryForAddVO
//     * @throws Exception
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void updateInventoryOrder(UserVO userVO,InventoryForAddVO inventoryForAddVO) throws Exception {
//
//        Long enterpriseId = userVO.getEnterpriseId();
//        //盘点总单id
//        Long invId = inventoryForAddVO.getId();
//        Inventory inventory = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
//        inventory.setId(invId);
//        if(inventory == null){
//            throw new BusinessException("盘点单不存在！！");
//        }
//
//        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO.getEnterpriseId());
//        if(mangeConfig.getBusinessControl() == 0){
//            //关闭
//            User user = userMapper.selectByPrimaryKey(inventoryForAddVO.getCreaterId());
//            inventory.setCreaterId(user.getId());
//            inventory.setCreaterCode(user.getCode());
//            inventory.setCreaterName(user.getName());
//        } else {
//            //开启
//            inventory.setCreaterId(userVO.getUserId());
//            inventory.setCreaterCode(userVO.getUserCode());
//            inventory.setCreaterName(userVO.getUserName());
//        }
//
//        //更新盘点单
//        inventoryMapper.updateByPrimaryKeySelective(inventory);
//
//        inventory = inventoryMapper.selectByPrimaryKey(invId);
//        List<InventoryDetailForAddVO> detailForAddVOList = inventoryForAddVO.getDetailForAddVOList();
//
//        List<Long> existGoodsIdListTmp = new ArrayList<>();
//        //盘点方法
//        Integer invType = inventory.getInvType();
//
//
//        //查询该盘点单下的所有的商品id
//        List<Long> goodsIdList = inventoryDetailMapper.getGoodsIdByInventoryId(invId);
//
//        if(invType == InvType.SHELF.getCode()){
//            //按货位
//            Map<String,List<InventoryShelfSimpleVO>> map = new HashMap<>();
//            //转换数据结构
//            for (InventoryDetailForAddVO item : detailForAddVOList) {
//                Long goodsId = item.getGoodsId();//商品id
//                Long shelfId = item.getShelfId();
//                Long lotId = item.getLotId();
//                String key = goodsId + "," + shelfId + "," + lotId;
//                if(map.get(key) == null){
//                    List<InventoryShelfSimpleVO> simpleVOList = new ArrayList<>();
//                    packSimpleInventoryShelf(item, simpleVOList);
//                    map.put(key,simpleVOList);
//                } else {
//                    List<InventoryShelfSimpleVO> simpleVOList = map.get(key);
//                    packSimpleInventoryShelf(item, simpleVOList);
//                    map.put(key,simpleVOList);
//                }
//            }
//            excuteUpdateInventory(userVO, enterpriseId, inventory, invId, goodsIdList, map,PubStatus.inventoryOrderStatus.WAIT_REGISTER,1);
//
//        } else if(invType == InvType.GOODS.getCode()){
//            //盘点方法按照商品
//            //盘点单的账面数量、品种数量合计
//            BigDecimal quantityTotalAll = BigDecimal.ZERO;
//            Integer varietiesQuantity = detailForAddVOList.size();
//
//            for (InventoryDetailForAddVO item: detailForAddVOList) {
//                InventoryDetail detail =(InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class,userVO);
//                Long goodsId = item.getGoodsId();
//                //设置盘点单的属性
//                detail.setInvDate(inventory.getInvDate());
//                //按商品
//                //保存盘点明细
//                //商品属性
//                BigDecimal quantity = item.getQuantity();//账面数量
//                quantityTotalAll = quantityTotalAll.add(quantity);
//                if(!goodsIdList.contains(goodsId)){
//                    detail =(InventoryDetail) EntityUtils.reflectAddSetDefaultValue(InventoryDetail.class,userVO);
//                    detail.setOrderType(OrderRule.INVENTORY.getType());
//                    detail.setInvId(inventory.getId());
//                    detail.setInvCode(inventory.getCode());
//                    detail.setInvDate(inventory.getInvDate());
//                    detail.setStatus(PubStatus.inventoryOrderStatus.WAIT_REGISTER);
//                    packGoodsInfo(detail, goodsId);
//                    inventoryDetailMapper.insertSelective(detail);
//                    packShelfInfoForAdd(userVO, enterpriseId, inventory, detail, goodsId);
//                } else {
//                    existGoodsIdListTmp.add(goodsId);
//                }
//                //移除商品信息
//                //取出差集，删除明细，并且删除明细对应的货位详情
//                excuteDiffSetGoods(enterpriseId, invId, goodsIdList, existGoodsIdListTmp);
//
//            }
//
//            inventory.setQuantityTotal(quantityTotalAll);
//            inventory.setVarietiesQuantity(varietiesQuantity);
//            inventoryMapper.updateByPrimaryKeySelective(inventory);
//        }
//
//
//
//    }
//
//    /**
//     *
//     * @param userVO
//     * @param enterpriseId
//     * @param inventory
//     * @param invId
//     * @param goodsIdList
//     * @param map
//     * @param status
//     * @param type 1修改盘点单 2--盘点单登记
//     * @throws Exception
//     */
//    private void excuteUpdateInventory(UserVO userVO, Long enterpriseId, Inventory inventory, Long invId, List<Long> goodsIdList,
//
//                                       Map<String, List<InventoryShelfSimpleVO>> map,Integer status,Integer type) throws Exception {
//        //按照货位盘点调用此方法
//
//
//        /**
//         * 操作新的数据结构
//         * 新的数据结构:key是goodsId+","+shelfId+","+"lotId" ; value:商品货位明细信息
//         */
//        Iterator<Map.Entry<String, List<InventoryShelfSimpleVO>>> iterator = map.entrySet().iterator();
//        Integer varietiesQuantity = map.keySet().size();
//        List<Long> existGoodsIdListTmp = new ArrayList<>();
//
//        //账面数量
//        BigDecimal quantityAll = BigDecimal.ZERO;
//        //实盘数量
//        BigDecimal invQuantityAll = BigDecimal.ZERO;
//
//        while (iterator.hasNext()){
//            Map.Entry<String, List<InventoryShelfSimpleVO>> entry = iterator.next();
//            String key = entry.getKey();
//            Long goodsId = Long.valueOf(key.split(",")[0]);
//            //商品货位明细
//            List<InventoryShelfSimpleVO> simpleVOList = entry.getValue();
//
//            //统计明细的账面和实盘数量
//            BigDecimal quantityTotal = BigDecimal.ZERO;
//            BigDecimal invQuantityTotal = BigDecimal.ZERO;
//            for (InventoryShelfSimpleVO item : simpleVOList) {
//                BigDecimal quantity = item.getQuantity();
//                quantityTotal = quantityTotal.add(quantity);
//                if(item.getInvQuantity() != null){
//                    invQuantityTotal = invQuantityTotal.add(item.getInvQuantity());
//                }
//            }
//            quantityAll = quantityAll.add(quantityTotal);
//            invQuantityAll = invQuantityAll.add(invQuantityTotal);
//
//            Set<String> goodsIdLotIdShelfIdSet = new HashSet<>();
//            Map<String,BigDecimal> invQuantityMap = new HashMap<>();
//            for (InventoryShelfSimpleVO item : simpleVOList) {
//                String goodsIdLotIdShelfId = item.getGoodsId() + "," + item.getLotId() +"," + item.getShelfId();
//                goodsIdLotIdShelfIdSet.add(goodsIdLotIdShelfId);
//                invQuantityMap.put(goodsIdLotIdShelfId,Optional.ofNullable(item.getInvQuantity()).orElse(BigDecimal.ZERO));
//            }
//            //创建明细实例
//            InventoryDetail detail =(InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class,userVO);
//            //设置盘点单的属性
//            detail.setInvDate(inventory.getInvDate());
//            detail.setStatus(status);
//
//            if (goodsIdList.contains(goodsId)) {
//                existGoodsIdListTmp.add(goodsId);
//                detail = inventoryDetailMapper.getInventoryDetailByParam(enterpriseId, invId, goodsId,null);
//                //该商品已经存在在明细，更新商品信息
//                detail.setQuantity(quantityTotal);
//                detail.setInvQuantity(invQuantityTotal);
//                detail.setDiffQuantity(invQuantityTotal.subtract(quantityTotal));
//                //更新盘点明细
//                inventoryDetailMapper.updateByPrimaryKeySelective(detail);
//                //删除该明细下所有的货位明细,下面会从新插入
//                inventoryShelfMapper.deleteShelfByDetailId(enterpriseId,invId,detail.getId());
//
//
//            } else {
//                //创建明细实例
//                detail =(InventoryDetail) EntityUtils.reflectAddSetDefaultValue(InventoryDetail.class,userVO);
//                //商品属性
//                packGoodsInfo(detail, goodsId);
//                detail.setOrderType(OrderRule.INVENTORY.getType());
//                detail.setInvId(inventory.getId());
//                detail.setInvCode(inventory.getCode());
//                detail.setInvDate(inventory.getInvDate());
//                detail.setStatus(status);
//                detail.setQuantity(quantityTotal);
//                detail.setInvQuantity(invQuantityTotal);
//                detail.setDiffQuantity(invQuantityTotal.subtract(quantityTotal));
//                inventoryDetailMapper.insertSelective(detail);
//                //
//
//            }
//
//            //新增货位明细
//            //货位明细
//            packGoodsShelf(userVO, enterpriseId, inventory,  detail, goodsId,status,goodsIdLotIdShelfIdSet,invQuantityMap);
//        }
//        inventory.setQuantityTotal(quantityAll);
//        inventory.setInvQuantityTotal(invQuantityAll);
//        inventory.setDiffQuantityTotal(invQuantityAll.subtract(quantityAll));
//        inventory.setVarietiesQuantity(varietiesQuantity);//品种数量
//        inventoryMapper.updateByPrimaryKeySelective(inventory);
//        //移除商品信息
//        //取出差集，删除明细，并且删除明细对应的货位详情
//        excuteDiffSetGoods(enterpriseId, invId, goodsIdList, existGoodsIdListTmp);
//
//
//    }
//
//    private void excuteDiffSetGoods(Long enterpriseId, Long invId, List<Long> goodsIdList, List<Long> existGoodsIdListTmp) {
//        //移除商品信息
//        //取出差集，删除明细，并且删除明细对应的货位详情
//        Set<Long> differenceSet = Sets.difference(Sets.newHashSet(goodsIdList), Sets.newHashSet(existGoodsIdListTmp));
//        for (Long gId : differenceSet) {
//            //更新商品库存盘点状态
//            List<InventoryShelf> inventoryShelfByParam = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId, gId, invId, null);
//            inventoryShelfByParam.forEach(shelfItem->{
//                //更新库存盘点状态
//                stockMapper.updateStockInventoryStatus(enterpriseId,shelfItem.getGoodsId(),shelfItem.getLotId(),shelfItem.getShelfId(), InventoryStatus.NORMAL.getStatus());
//            });
//
//            inventoryDetailMapper.deleteByInvIdAndGoodsId(invId,gId);
//            //删除货位详情
//            inventoryShelfMapper.deleteByParam(invId,gId);
//
//        }
//    }
//
//
//    /**
//     * 查询盘点单详情
//     * @param userVO
//     * @param id
//     */
//    @Override
//    public InventoryForOrderDetailVO getInventoryOrderDetail(UserVO userVO, Long id) {
//        Long enterpriseId = userVO.getEnterpriseId();
//        //
//        InventoryForOrderDetailVO inventory = inventoryMapper.getInventoryById(enterpriseId,id);
//        Integer invType = inventory.getInvType();
//        if(invType == InvType.SHELF.getCode()){
//            //按货位
//            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,null);
//            inventory.setDetailForAddVOList(detailList);
//        } else if(invType == InvType.GOODS.getCode()){
//            //按照商品
//            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvId(id, enterpriseId);
//            inventory.setDetailForAddVOList(detailList);
//        }
//        return inventory;
//    }
//
//    public Map<String,Object> getInventoryOrderDetail4ExportExcel(UserVO userVO, Long id) {
//        Long enterpriseId = userVO.getEnterpriseId();
//
//        Map<String,Object> map = new HashMap<>();
//        Inventory inventory = inventoryMapper.getInventoryById4ExportExcel(enterpriseId,id);
//        Integer invType = inventory.getInvType();
//        if(invType == InvType.SHELF.getCode()){
//            //按货位
//            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,null);
//            map.put("detailList",detailList);
//        } else if(invType == InvType.GOODS.getCode()){
//            //按照商品
//            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvId(id, enterpriseId);
//            map.put("detailList",detailList);
//        }
//        map.put("inventory",inventory);
//        return map;
//    }
//
//    @Override
//    public void export(OutputStream output, Long id, UserVO userVO) {
//
//        Map<String,Object> resultMap = getInventoryOrderDetail4ExportExcel(userVO, id);
//        Inventory inventoryOrderDetail = (Inventory) resultMap.get("inventory");
//        List<InventoryDetailForOrderDetailVO> detailForAddVOList = (List<InventoryDetailForOrderDetailVO>) resultMap.get("detailList");
//
//        LinkedHashMap<String,String> map = new LinkedHashMap<>();
//        map.put("goodsCode","商品编码");
//        map.put("goodsGenericName","通用名称");
//        map.put("dosageName","剂型");
//        map.put("goodsSpecification","规格");
//        map.put("manufacturer","生产厂商");
//        map.put("unitName","单位");
//
//        if(inventoryOrderDetail.getInvType() == 0) {
//            map.put("lotNumber", "批号");
//            map.put("productDate", "生产日期");
//            map.put("validDate", "有效期至");
//            map.put("shelfName", "货位");
//        }
//
//        map.put("invQuantity","实盘数量");
//
//        StringBuilder titleSecondRow = new StringBuilder();
//        titleSecondRow.append("盘点单号：");
//        titleSecondRow.append(!StringUtils.isEmpty(inventoryOrderDetail.getCode()) ? inventoryOrderDetail.getCode() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("创建日期：");
//        titleSecondRow.append(null != inventoryOrderDetail.getCreateTime() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(inventoryOrderDetail.getCreateTime())
//            : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("创建人员：");
//        titleSecondRow.append(!StringUtils.isEmpty(inventoryOrderDetail.getCreaterName()) ? inventoryOrderDetail.getCreaterName() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("盘点方法：");
//        titleSecondRow.append(null != inventoryOrderDetail.getInvType() ? InvType.getValue(inventoryOrderDetail.getInvType())
//            : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("盘点范围：");
//        titleSecondRow.append(null != inventoryOrderDetail.getInvRange() ? inventoryOrderDetail.getInvRange() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("仓库：");
//        titleSecondRow.append(!StringUtils.isEmpty(inventoryOrderDetail.getWarehouseName()) ? inventoryOrderDetail.getWarehouseName() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("库区：");
//        titleSecondRow.append(!StringUtils.isEmpty(inventoryOrderDetail.getWarehouseAreaNames()) ? inventoryOrderDetail.getWarehouseAreaNames() : "" );
//        titleSecondRow.append("     ");
//        titleSecondRow.append("货区/柜组：  ");
//        titleSecondRow.append(!StringUtils.isEmpty(inventoryOrderDetail.getCargoAreaNames()) ? inventoryOrderDetail.getCargoAreaNames() : "");
//
//        List<String> name = new ArrayList<>();
//        name.add(userVO.getEnterpriseName());
//        name.add("盘点单");
//        List<String> secondTitle = new ArrayList<>();
//        secondTitle.add(titleSecondRow.toString());
//        purchaseGeneralComponent.commExcelExport(output,map,detailForAddVOList,name,secondTitle,"",true,new ArrayList<>());
//
//    }
//    /**
//     * 查询差异处理盘点单详情
//     * @param userVO
//     * @param id
//     */
//    @Override
//    public InventoryForOrderDetailVO getInventoryDiffOrderDetail(UserVO userVO, Long id) {
//        Long enterpriseId = userVO.getEnterpriseId();
//        InventoryForOrderDetailVO inventory = inventoryMapper.getInventoryById(enterpriseId,id);
//        //按货位
//        List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,0);
//        inventory.setDetailForAddVOList(detailList);
//        return inventory;
//    }
//
//    /**
//     * 查询已登记盘点单详情
//     * @param userVO
//     * @param id
//     */
//    @Override
//    public InventoryForRegisterVO2 getHadRegisterInventoryOrderDetail(UserVO userVO, Long id) {
//        Long enterpriseId = userVO.getEnterpriseId();
//        InventoryForRegisterVO2 inventory = inventoryMapper.getInventoryByIdForRegister(enterpriseId, id);
//        Integer invType = inventory.getInvType();
//        if(invType == InvType.SHELF.getCode()){
//            //按货位
//            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,null);
//            inventory.setDetailForAddVOList(detailList);
//        } else if(invType == InvType.GOODS.getCode()){
//            //按照商品
//            List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvId(id, enterpriseId);
//            inventory.setDetailForAddVOList(detailList);
//        }
//        return inventory;
//    }
//
//    @Override
//    public void export4HadRegisterInventory(OutputStream output, Long id, UserVO userVO) {
//
//        InventoryForRegisterVO2 hadRegisterInventoryOrderDetail = getHadRegisterInventoryOrderDetail(userVO, id);
//
//        List<InventoryDetailForOrderDetailVO> detailForAddVOList = hadRegisterInventoryOrderDetail.getDetailForAddVOList();
//
//        LinkedHashMap<String,String> map = new LinkedHashMap<>();
//        map.put("goodsCode","商品编码");
//        map.put("goodsGenericName","通用名称");
//        map.put("dosageName","剂型");
//        map.put("goodsSpecification","规格");
//        map.put("manufacturer","生产厂商");
//        map.put("unitName","单位");
//
//        if(hadRegisterInventoryOrderDetail.getInvType() == 0) {
//            map.put("lotNumber", "批号");
//            map.put("productDate", "生产日期");
//            map.put("validDate", "有效期至");
//            map.put("shelfName", "货位");
//        }
//
//        map.put("invQuantity","实盘数量");
//
//        StringBuilder titleSecondRow = new StringBuilder();
//        titleSecondRow.append("盘点单号");
//        titleSecondRow.append(!StringUtils.isEmpty(hadRegisterInventoryOrderDetail.getCode()) ? hadRegisterInventoryOrderDetail.getCode() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("登记日期：");
//        titleSecondRow.append(null != hadRegisterInventoryOrderDetail.getRegisterDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hadRegisterInventoryOrderDetail.getRegisterDate())
//                : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("登记人员：");
//        titleSecondRow.append(!StringUtils.isEmpty(hadRegisterInventoryOrderDetail.getRegisterManName()) ? hadRegisterInventoryOrderDetail.getRegisterManName() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("登记方法：");
//        titleSecondRow.append(null != hadRegisterInventoryOrderDetail.getRegisterType() ? RegisterType.getValue(hadRegisterInventoryOrderDetail.getRegisterType())
//                : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("盘点日期：");
//        titleSecondRow.append(null != hadRegisterInventoryOrderDetail.getInvDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hadRegisterInventoryOrderDetail.getInvDate()) : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("盘点人员：");
//        titleSecondRow.append(!StringUtils.isEmpty(hadRegisterInventoryOrderDetail.getInvManName()) ? hadRegisterInventoryOrderDetail.getInvManName() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("复盘人员：  ");
//        titleSecondRow.append(!StringUtils.isEmpty(hadRegisterInventoryOrderDetail.getSecondInvManName()) ? hadRegisterInventoryOrderDetail.getSecondInvManName() : "" );
//
//        List<String> name = new ArrayList<>();
//        name.add(userVO.getEnterpriseName());
//        name.add("盘点单");
//        List<String> secondTitle = new ArrayList<>();
//        secondTitle.add(titleSecondRow.toString());
//        purchaseGeneralComponent.commExcelExport(output,map,detailForAddVOList,name,secondTitle,"",true,new ArrayList<>());
//
//    }
//
//    /**
//     * 查询已处理订单详情
//     * @param userVO
//     * @param id
//     */
//    @Override
//    public InventoryForDiffDetailVO getHadHandleInventoryOrderDetail(UserVO userVO, Long id) {
//        Long enterpriseId = userVO.getEnterpriseId();
//        InventoryForDiffDetailVO inventory = inventoryMapper.getInventoryByIdForDiff(enterpriseId, id);
//
//        List<InventoryDetailForOrderDetailVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelf(id, enterpriseId,null);
//        inventory.setDetailForAddVOList(detailList);
//
//        return inventory;
//    }
//
//    @Override
//    public void export4HadHandleInventory(OutputStream output, Long id, UserVO userVO) {
//
//        InventoryForDiffDetailVO hadHandleInventoryOrderDetail = getHadHandleInventoryOrderDetail(userVO, id);
//
//        List<InventoryDetailForOrderDetailVO> detailForAddVOList = hadHandleInventoryOrderDetail.getDetailForAddVOList();
//
//        LinkedHashMap<String,String> map = new LinkedHashMap<>();
//        map.put("goodsCode","商品编码");
//        map.put("goodsGenericName","通用名称");
//        map.put("dosageName","剂型");
//        map.put("goodsSpecification","规格");
//        map.put("manufacturer","生产厂商");
//        map.put("goodsPlace","产地");
//        map.put("unitName","单位");
//        map.put("lotNumber", "批号");
//        map.put("validDate", "有效期至");
//        map.put("quantity", "库存数量");
//        map.put("invQuantity","实盘数量");
//        map.put("diffQuantity","损溢数量");
//
//        StringBuilder titleSecondRow = new StringBuilder();
//        titleSecondRow.append("盘点单号: ");
//        titleSecondRow.append(!StringUtils.isEmpty(hadHandleInventoryOrderDetail.getCode()) ? hadHandleInventoryOrderDetail.getCode() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("处理日期：");
//        titleSecondRow.append(null != hadHandleInventoryOrderDetail.getHandleDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hadHandleInventoryOrderDetail.getHandleDate())
//                : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("处理人员：");
//        titleSecondRow.append(!StringUtils.isEmpty(hadHandleInventoryOrderDetail.getHandleManName()) ? hadHandleInventoryOrderDetail.getHandleManName() : "");
//
//        List<String> name = new ArrayList<>();
//        name.add(userVO.getEnterpriseName());
//        name.add("差异处理");
//        List<String> secondTitle = new ArrayList<>();
//        secondTitle.add(titleSecondRow.toString());
//        purchaseGeneralComponent.commExcelExport(output,map,detailForAddVOList,name,secondTitle,"",true,new ArrayList<>());
//
//    }
//
//    /**
//     * 盘点登记
//     * @param userVO
//     * @param inventoryForRegisterVO
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void updateRegisterInventory(UserVO userVO, InventoryForRegisterVO inventoryForRegisterVO) throws Exception {
//        Long enterpriseId = userVO.getEnterpriseId();
//        Inventory inventoryOld = inventoryMapper.selectByPrimaryKey(inventoryForRegisterVO.getId());
//        Inventory inventory = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
//        //登记方法(0-按账面登记；1-按实物登记)
//        Integer registerType = inventoryForRegisterVO.getRegisterType();
//        //盘点方法（0-按货位；1-按商品）
//        Integer invType = inventoryOld.getInvType();
//
//        //盘点总单id
//        Long invId = inventoryForRegisterVO.getId();
//
//        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO.getEnterpriseId());
//        if(mangeConfig.getBusinessControl() == 0){
//            //关闭
//            User user = userMapper.selectByPrimaryKey(inventoryForRegisterVO.getRegisterManId());
//            inventory.setRegisterManId(user.getId());
//            inventory.setRegisterManCode(user.getCode());
//            inventory.setRegisterManName(user.getName());
//            inventory.setRegisterDate(inventoryForRegisterVO.getRegisterDate());
//        } else {
//            //开启
//            inventory.setRegisterManId(userVO.getUserId());
//            inventory.setRegisterManCode(userVO.getUserCode());
//            inventory.setRegisterManName(userVO.getUserName());
//            inventory.setRegisterDate(inventoryForRegisterVO.getRegisterDate());
//        }
//
//        //主键
//        inventory.setId(inventoryForRegisterVO.getId());
//        //登记方法
//        inventory.setRegisterType(registerType);
//
//        //盘点日期
//        inventory.setInvDate(inventoryForRegisterVO.getInvDate());
//        //盘点人员
//        User user = userMapper.selectByPrimaryKey(inventoryForRegisterVO.getInvManId());
//        inventory.setInvManId(user.getId());
//        inventory.setInvManCode(user.getCode());
//        inventory.setInvManName(user.getName());
//
//        //复盘人员
//        User secondInvMan = userMapper.selectByPrimaryKey(inventoryForRegisterVO.getInvManId());
//        inventory.setSecondInvManId(secondInvMan.getId());
//        inventory.setSecondInvManCode(secondInvMan.getCode());
//        inventory.setSecondInvManName(secondInvMan.getName());
//        inventory.setStatus(PubStatus.inventoryOrderStatus.PENDING);
//        inventoryMapper.updateByPrimaryKeySelective(inventory);
//
//        inventory = inventoryMapper.selectByPrimaryKey(inventory.getId());
//
//
//
//        //查询该盘点单下的所有的商品id
//        List<Long> goodsIdList = inventoryDetailMapper.getGoodsIdByInventoryId(invId);
//
//
//        //盘点明细
//        List<InventoryDetailForRegisterVO> detailForAddVOList = inventoryForRegisterVO.getDetailForAddVOList();
////        if(registerType == 0){
//        //账面登记
//
//        List<Long> existGoodsIdListTmp = new ArrayList<>();
//
//            if(invType == InvType.SHELF.getCode()){
//                //盘点方法按照货位
//                Map<String,List<InventoryShelfSimpleVO>> map = new HashMap<>();
//                //转换数据结构
//                for (InventoryDetailForRegisterVO item : detailForAddVOList) {
//                    Long goodsId = item.getGoodsId();//商品id
//                    Long shelfId = item.getShelfId();
//                    Long lotId = item.getLotId();
//                    String key = goodsId + "," + shelfId + "," + lotId;
//                    if(map.get(key) == null){
//                        List<InventoryShelfSimpleVO> simpleVOList = new ArrayList<>();
//                        packSimpleInventoryShelf(item, simpleVOList);
//                        map.put(key,simpleVOList);
//                    } else {
//                        List<InventoryShelfSimpleVO> simpleVOList = map.get(key);
//                        packSimpleInventoryShelf(item, simpleVOList);
//                        map.put(key,simpleVOList);
//                    }
//                }
//
//                //操作新的数据结构
//                excuteUpdateInventory(userVO, enterpriseId, inventory, invId, goodsIdList, map,PubStatus.inventoryOrderStatus.PENDING,2);
//
//            } else if(invType == InvType.GOODS.getCode()){
//
//                //盘点单的账面数量、实盘数量、品种数量合计
//                BigDecimal quantityTotalAll = BigDecimal.ZERO;
//                BigDecimal invQuantityTotalAll = BigDecimal.ZERO;
//                Integer varietiesQuantity = detailForAddVOList.size();
//
//                //盘点方法按照商品
//                for (InventoryDetailForRegisterVO item: detailForAddVOList) {
//                    InventoryDetail detail =(InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class,userVO);
//                    Long goodsId = item.getGoodsId();
//                    //设置盘点单的属性
//                    detail.setInvDate(inventory.getInvDate());
//                    //按商品
//                    //保存盘点明细
//                    //商品属性
//
//                    BigDecimal quantity = item.getQuantity();//账面数量
//                    BigDecimal invQuantity = item.getInvQuantity();//实盘数量
//
//                    quantityTotalAll = quantityTotalAll.add(quantity);
//                    invQuantityTotalAll = invQuantityTotalAll.add(invQuantity);
//                    if(!goodsIdList.contains(goodsId)){
//                        //盘点单中不包含这个商品
//                        throw new InventoryBizException("", InventoryBizExceptionEnum.EXCEPTION_1.getValue());
//                    } else {
//                        //更新商品的实盘数量
//                        detail = (InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class,userVO);
//                        detail.setInvQuantity(invQuantity);
//                        detail.setDiffQuantity(invQuantity.subtract(quantity));
//                        detail.setId(item.getId());
//                        inventoryDetailMapper.updateByPrimaryKeySelective(detail);
//                        existGoodsIdListTmp.add(goodsId);
//                        //盘点登记更新盘点货位明细
//                        if(quantity.compareTo(invQuantity) == -1){
//                            //账面小于实盘 盘盈
//                            //盘盈 实盘数据增加到近效期远的
//                            BigDecimal diff = BigDecimal.ZERO;
//
//                            List<InventoryShelf> inventoryShelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId,goodsId,invId,item.getId());
//                            //商品货位明细
//                            for(int i = 0;i < inventoryShelfList.size();i++){
//                                InventoryShelf shelf =(InventoryShelf) EntityUtils.reflectUpdateSetDefaultValue(InventoryShelf.class,userVO);
//                                shelf.setId(inventoryShelfList.get(i).getId());
//                                if(i != inventoryShelfList.size() - 1){
//                                    diff = inventoryShelfList.get(i).getQuantity();
//                                } else {
//                                    diff = invQuantity.subtract(quantity);
//                                }
//                                shelf.setInvQuantity(diff);
//                                shelf.setStatus(PubStatus.inventoryOrderStatus.PENDING);
//                                inventoryShelfMapper.updateByPrimaryKeySelective(shelf);
//                            }
//
//                        } else if(quantity.compareTo(invQuantity) == 1){
//                            //盘亏 实盘数据增加到近效期近的
//                            BigDecimal diff = quantity.subtract(invQuantity);
//
//                            List<InventoryShelf> inventoryShelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId,goodsId,invId,item.getId());
//                            //商品货位明细
//                            for(int i = 0;i < inventoryShelfList.size();i++){
//                                InventoryShelf shelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
//                                shelf.setId(inventoryShelfList.get(i).getId());
//                                if(i > 0){
//                                    diff = quantity;
//                                }
//                                shelf.setInvQuantity(diff);
//                                shelf.setStatus(PubStatus.inventoryOrderStatus.PENDING);
//                                inventoryShelfMapper.updateByPrimaryKeySelective(shelf);
//                            }
//                        } else {
//                            List<InventoryShelf> inventoryShelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId,goodsId,invId,item.getId());
//                            //商品货位明细
//                            for(int i = 0;i < inventoryShelfList.size();i++){
//                                InventoryShelf shelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
//                                shelf.setId(inventoryShelfList.get(i).getId());
//                                shelf.setInvQuantity(quantity);
//                                shelf.setStatus(PubStatus.inventoryOrderStatus.PENDING);
//                                inventoryShelfMapper.updateByPrimaryKeySelective(shelf);
//                            }
//                        }
//                    }
//
//                    //移除商品信息
//                    //取出差集，删除明细，并且删除明细对应的货位详情
//                    Set<Long> differenceSet = Sets.difference(Sets.newHashSet(goodsIdList), Sets.newHashSet(existGoodsIdListTmp));
//                    for (Long gId : differenceSet) {
//                        //更新实盘数和账面数量一致
//                        inventoryDetailMapper.updateByInvIdAndGoodsId(invId,gId);
//                        inventoryShelfMapper.updateByInvIdAndGoodsId(invId,gId);
//                    }
//
//                }
//
//                inventory.setVarietiesQuantity(varietiesQuantity);
//                inventory.setQuantityTotal(quantityTotalAll);
//                inventory.setInvQuantityTotal(invQuantityTotalAll);
//                inventory.setDiffQuantityTotal(invQuantityTotalAll.subtract(quantityTotalAll));
//                inventoryMapper.updateByPrimaryKeySelective(inventory);
//
//            }
//            //盘点单id
//        Long id = inventoryOld.getId();
//        List<InventoryShelf> inventoryShelfList = inventoryShelfMapper.getIdInvIdDtlId(id, enterpriseId);
//        if(CollectionUtils.isEmpty(inventoryShelfList)){
//            Inventory inventory1 = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
//            inventory1.setId(id);
//            inventory1.setStatus(PubStatus.inventoryOrderStatus.WAIT_POSTING);
//            inventoryMapper.updateByPrimaryKeySelective(inventory1);
//            inventoryDetailMapper.updateDetailByInvId(id,PubStatus.inventoryOrderStatus.WAIT_POSTING,enterpriseId);
//            inventoryShelfMapper.updateShelfByInvId(id,PubStatus.inventoryOrderStatus.WAIT_POSTING,enterpriseId);
//        }
//
//
////        }
//    }
//
//    /**
//     * 保存差异化处理单
//     *
//     * @param userVO
//     * @param inventoryForDiffVO
//     */
//    @Override
//    public void saveInventoryDiffHandleOrder(UserVO userVO, InventoryForDiffVO inventoryForDiffVO) throws Exception {
//        Inventory inventory = (Inventory) EntityUtils.reflectUpdateSetDefaultValue(Inventory.class,userVO);
//        inventory.setId(inventoryForDiffVO.getId());
//        inventory.setHandleDate(inventoryForDiffVO.getHandleDate());
//        Long handleManId = inventoryForDiffVO.getHandleManId();
//        User user = userMapper.selectByPrimaryKey(handleManId);
//        inventory.setHandleManId(handleManId);
//        inventory.setHandleManCode(user.getCode());
//        inventory.setHandleManName(user.getName());
//        inventory.setStatus(PubStatus.inventoryOrderStatus.WAIT_POSTING);
//        List<InventoryShelfSimpleForDiffVO> detailForAddVOList = inventoryForDiffVO.getDetailForAddVOList();
//
//        BigDecimal invQuantityTotalAll = BigDecimal.ZERO;
//
//        Set<Long> detailIdSet = new HashSet<>();
//        Map<Long,BigDecimal> map = new HashMap<>();
//        for(InventoryShelfSimpleForDiffVO diffVO : detailForAddVOList){
//            Long detailId = diffVO.getId();
//            detailIdSet.add(detailId);
//            BigDecimal total = map.get(detailId);
//            if(total == null){
//                BigDecimal invQuantityTotal = BigDecimal.ZERO;
//                invQuantityTotal = invQuantityTotal.add(diffVO.getInvQuantity());
//                map.put(detailId,invQuantityTotal);
//            } else {
//                total = total.add(diffVO.getInvQuantity());
//                map.put(detailId,total);
//            }
//            InventoryShelf shelf = inventoryShelfMapper.selectByPrimaryKey(diffVO.getInventoryShelfId());
//            shelf.setId(diffVO.getInventoryShelfId());
//            shelf.setInvQuantity(diffVO.getInvQuantity());
//            shelf.setDiffQuantity(diffVO.getInvQuantity().subtract(shelf.getQuantity()));
//            inventoryShelfMapper.updateByPrimaryKeySelective(shelf);
//            invQuantityTotalAll = invQuantityTotalAll.add(diffVO.getInvQuantity());
//        }
//        //更新明细
//        for(Long dId : detailIdSet){
//            InventoryDetail inventoryDetail = inventoryDetailMapper.selectByPrimaryKey(dId);
//            BigDecimal quantity = inventoryDetail.getQuantity();
//            BigDecimal invQuantity = map.get(dId);
//            inventoryDetail = (InventoryDetail) EntityUtils.reflectUpdateSetDefaultValue(InventoryDetail.class,userVO);
//            inventoryDetail.setId(dId);
//            inventoryDetail.setQuantity(quantity);
//            inventoryDetail.setInvQuantity(invQuantity);
//            inventoryDetail.setDiffQuantity(invQuantity.subtract(quantity));
//            inventoryDetail.setStatus(PubStatus.inventoryOrderStatus.WAIT_POSTING);
//            inventoryDetailMapper.updateByPrimaryKeySelective(inventoryDetail);
//        }
//        Inventory inventory1 = inventoryMapper.selectByPrimaryKey(inventoryForDiffVO.getId());
//        inventory.setInvQuantityTotal(invQuantityTotalAll);
//        inventory.setDiffQuantityTotal(invQuantityTotalAll.subtract(inventory1.getQuantityTotal()));
//
//        inventoryMapper.updateByPrimaryKeySelective(inventory);
//
//    }
//
//    /**
//     * 已登记盘点单查询
//     *
//     * @param page
//     * @param paramForListVO
//     * @param enterpriseId
//     */
//    @Override
//    public void getHadRegisterInventoryOrderList(Page<List<InventoryForRegisterVO2>> page, RequestParamForHadRegisterListVO paramForListVO, Long enterpriseId) {
//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(paramForListVO.getPageNo(), paramForListVO.getPageSize());
//        List<InventoryForRegisterVO2> inventoryList = inventoryMapper.getHadRegisterInventoryOrderList(paramForListVO, enterpriseId);
//        page.setResult(inventoryList);
//        page.setTotalRecord((int)objects.getTotal());
//    }
//
//    @Override
//    public void getHadHandlerInventoryList(Page<List<InventoryForDiffDetailVO>> page, RequestParamForDiffListVO paramForListVO, Long enterpriseId) {
//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(paramForListVO.getPageNo(), paramForListVO.getPageSize());
//        List<InventoryForDiffDetailVO> inventoryList = inventoryMapper.getHadHandlerInventoryList(paramForListVO, enterpriseId);
//        page.setResult(inventoryList);
//        page.setTotalRecord((int)objects.getTotal());
//    }
//
//    /**
//     * 已过账单查询
//     *
//     * @param page
//     * @param param
//     * @param enterpriseId
//     */
//    @Override
//    public void getInventoryPostOrderList(Page<InventoryPostVO> page, RequestParamForPostListVO param, Long enterpriseId) {
//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(param.getPageNo(), param.getPageSize());
//        List<InventoryForPostVO> inventoryList = inventoryMapper.getInventoryPostOrderList(param, enterpriseId);
//        InventoryPostVO totalForPost = inventoryMapper.getTotalForPost();
//        totalForPost.setInventoryForPostVOList(inventoryList);
//        page.setResult(totalForPost);
//        page.setTotalRecord((int)objects.getTotal());
//    }
//
//    /**
//     * 已过账单详情
//     *
//     * @param userVO
//     * @param id
//     */
//    @Override
//    public InventoryForPostDetailVO getPostDetail(UserVO userVO, Long id) {
//        Long enterpriseId = userVO.getEnterpriseId();
//        InventoryForPostDetailVO inventory = inventoryMapper.getInventoryByIdForPost(enterpriseId, id);
//
//        List<InventoryDetailForPostVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelfForPost(id, enterpriseId);
//        inventory.setPostVOList(detailList);
//
//        return inventory;
//    }
//
//    @Override
//    public void export4PostDetail(OutputStream output, Long id, UserVO userVO) {
//
//        InventoryForPostDetailVO postDetail = getPostDetail(userVO, id);
//
//        List<InventoryDetailForPostVO> postVOList = postDetail.getPostVOList();
//
//        LinkedHashMap<String,String> map = new LinkedHashMap<>();
//        map.put("goodsCode","商品编码");
//        map.put("goodsGenericName","通用名称");
//        map.put("dosageName","剂型");
//        map.put("goodsSpecification","规格");
//        map.put("unitName","单位");
//        map.put("manufacturer","生产厂商");
//        map.put("goodsPlace","产地");
//        map.put("lotNumber", "批号");
//        map.put("productDate", "生产日期");
//        map.put("validDate", "有效期至");
//        map.put("shelfName", "货位");
//        map.put("quantity", "账面数量");
//        map.put("invQuantity","实盘数量");
//        map.put("diffQuantity","损溢数量");
//
//        map.put("unitPrice","单价");
//        map.put("amount","账面金额");
//        map.put("realAmount","实盘金额");
//        map.put("diffAmount","损溢金额");
//        map.put("taxRate","税率");
//        map.put("notaxPrice","不含税单价");
//        map.put("notaxAmount","不含税账面金额");
//        map.put("realNotaxAmount","不含税实盘金额");
//        map.put("diffNotaxAmount","不含税损溢出金额");
//        map.put("taxAmount","账面税额");
//        map.put("realTaxAmount","实盘税额");
//        map.put("diffTaxAmount","损溢税额");
//        map.put("retailPrice","零售单价");
//        map.put("retailAmount","账面零售金额");
//        map.put("realRetailAmount","实盘零售金额");
//        map.put("diffRetailAmount","损溢零售金额");
//
//        StringBuilder titleSecondRow = new StringBuilder();
//        titleSecondRow.append("盘点单号：");
//        titleSecondRow.append(!StringUtils.isEmpty(postDetail.getCode()) ? postDetail.getCode() : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("过账日期：");
//        titleSecondRow.append(null != postDetail.getPostDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(postDetail.getPostDate())
//                : "");
//        titleSecondRow.append("     ");
//        titleSecondRow.append("过账人员：");
//        titleSecondRow.append(!StringUtils.isEmpty(postDetail.getPostManName()) ? postDetail.getPostManName() : "");
//
//        List<String> name = new ArrayList<>();
//        name.add(userVO.getEnterpriseName());
//        name.add("盘点过账");
//        List<String> secondTitle = new ArrayList<>();
//        secondTitle.add(titleSecondRow.toString());
//        purchaseGeneralComponent.commExcelExport(output,map,postVOList,name,secondTitle,"",true,new ArrayList<>());
//
//    }
//
//    /**
//     * 过账查看详情
//     * @param userVO
//     * @param id
//     * @return
//     */
//    @Override
//    public InventoryForPostDetailVO getPostDetail2Post(UserVO userVO, Long id) {
//        Long enterpriseId = userVO.getEnterpriseId();
//        InventoryForPostDetailVO inventory = inventoryMapper.getInventoryByIdForPost(enterpriseId, id);
//
//        List<InventoryDetailForPostVO> detailList = inventoryDetailMapper.getDetailByInvIdAndInvTypeShelfForPost(id, enterpriseId);
//        //添加单价 税率
//        for(InventoryDetailForPostVO post : detailList){
//            //单价计算
//            BigDecimal unitPrice = BigDecimal.ZERO;
//            BigDecimal noTaxPrice = BigDecimal.ZERO;//不含税单价
//            Long goodsId = post.getGoodsId();
//            Long lotId = post.getLotId();
//            BigDecimal quantity = post.getQuantity();//账面数量
//            BigDecimal invQuantity = post.getInvQuantity();//实盘数量
//
//            PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByGoodsIdAndEnterpriseId(goodsId, enterpriseId);
//            BigDecimal retailPrice = priceOrderDetail.getRetailPrice();//零售单价
//            if(quantity.compareTo(invQuantity) == -1){
//                //盘盈
//                /**
//                 * 近效期远的，优先查询同商品同批号的单价，其次同商品的单价，如果没有则为0
//                 * 税率显示商品默认进项税
//                 */
//                post.setIsProfit(1);
//                GoodsBusiness goodsBusiness = goodsBusinessMapper.getByGoodsId(goodsId);
//                BigDecimal purchaseTaxRate = goodsBusiness.getPurchaseTaxRate();//进项税
//                Long purchaseTaxRateId = goodsBusiness.getPurchaseTaxRateId();//进项税id
//                GoodsInfoPostVO goodsInfoPostVO = costMapper.getCostInfoForPost(goodsId,lotId,enterpriseId);
//                 if(goodsInfoPostVO == null){
//                     goodsInfoPostVO = costMapper.getCostInfoForPost(goodsId,null,enterpriseId);
//                     if(goodsInfoPostVO == null){
//                         unitPrice = BigDecimal.ZERO;
//                     } else {
//                         unitPrice = goodsInfoPostVO.getRealPrice();
//                     }
//                 } else {
//                     unitPrice = goodsInfoPostVO.getRealPrice();
//                 }
//                post.setUnitPrice(unitPrice);
//                post.setTaxRate(purchaseTaxRate);
//                post.setTaxRateId(purchaseTaxRateId);
//                post.setRetailPrice(retailPrice);
//
//            } else if (quantity.compareTo(invQuantity) == 1){
//                /**
//                 *  盘亏： 损溢金额/盘亏数量
//                 *  先进先出
//                 *  税率不显示
//                 */
//                //盘亏
//                //先进先出的商品信息
//                post.setIsProfit(0);
//                List<GoodsInfoPostVO> goodsStockForPost = stockMapper.getGoodsStockForPost(enterpriseId, goodsId, lotId);
//                BigDecimal diff = invQuantity.subtract(quantity);//损益数量
//                BigDecimal diffTotal = BigDecimal.ZERO;//损益金额
//                BigDecimal notaxDiffTotal = BigDecimal.ZERO;//不含税损益金额
//                for(GoodsInfoPostVO postVO : goodsStockForPost){
//                    BigDecimal goodsQuantity = postVO.getQuantity();
//                    BigDecimal realPrice = postVO.getRealPrice();
//                    BigDecimal notaxRealPrice = postVO.getNotaxRealPrice();//不含税实际单价
//                    if(diff.compareTo(BigDecimal.ZERO) == 1){
//                        //大于0继续与下一个商品数量相减
//                        diff = diff.subtract(goodsQuantity);
//                        //
//                        if(diff.compareTo(BigDecimal.ZERO) == -1 || diff.compareTo(BigDecimal.ZERO) == 0){
//                            diffTotal = diffTotal.add(diff.multiply(realPrice));
//                            notaxDiffTotal = notaxDiffTotal.add(diff.multiply(notaxRealPrice));
//                        } else {
//                            diffTotal = diffTotal.add(goodsQuantity.multiply(realPrice));
//                            notaxDiffTotal = notaxDiffTotal.add(goodsQuantity.multiply(notaxRealPrice));
//                        }
//                    }
//                }
//                //盘亏单价
//                unitPrice = diffTotal.divide(diff,6,BigDecimal.ROUND_HALF_UP);
//                noTaxPrice = notaxDiffTotal.divide(diff,6,BigDecimal.ROUND_HALF_UP);
//                calculationAmount(unitPrice,noTaxPrice,quantity,invQuantity,retailPrice,post);
//
//            } else {
//                /**
//                 *  无损溢
//                 *  税率不显示
//                 */
//                post.setIsProfit(2);
//                CostInfoPostVO costTotalQuantityAndAmount = costMapper.getCostTotalQuantityAndAmount(goodsId, lotId, enterpriseId);
//                BigDecimal quantityTotal = costTotalQuantityAndAmount.getQuantityTotal();
//                BigDecimal notaxRealAmountTotal = costTotalQuantityAndAmount.getNotaxRealAmountTotal();//不含税金额
//                BigDecimal realAmountTotal = costTotalQuantityAndAmount.getRealAmountTotal();//账面金额
//                unitPrice = realAmountTotal.divide(quantityTotal,6,BigDecimal.ROUND_HALF_UP);//单价
//                noTaxPrice = notaxRealAmountTotal.divide(quantityTotal,6,BigDecimal.ROUND_HALF_UP);//不含税单价
//                post.setUnitPrice(unitPrice);
//                post.setNotaxPrice(noTaxPrice);
//
//                post.setAmount(realAmountTotal);//账面金额
//                post.setRealAmount(realAmountTotal);//实盘金额
//                post.setDiffAmount(BigDecimal.ZERO);//损益金额
//
//                post.setNotaxAmount(notaxRealAmountTotal);//不含税账面金额
//                post.setRealNotaxAmount(notaxRealAmountTotal);//不含税实盘金额
//                post.setDiffNotaxAmount(BigDecimal.ZERO);//不含税损益金额
//                BigDecimal taxAmount = realAmountTotal.subtract(notaxRealAmountTotal);
//                post.setTaxAmount(taxAmount);//账面税额
//                post.setRealTaxAmount(taxAmount);//实盘税额
//                post.setDiffTaxAmount(BigDecimal.ZERO);//损益税额
//
//                BigDecimal retailAmount = retailPrice.multiply(quantity);
//
//                post.setRetailPrice(retailPrice);
//                post.setRetailAmount(retailAmount); //账面零售金额
//                post.setRealRetailAmount(retailAmount);//实盘零售金额
//                post.setDiffRetailAmount(BigDecimal.ZERO);//损益零售金额
//
//            }
//
//        }
//        inventory.setPostVOList(detailList);
//
//        return inventory;
//    }
//
//    /**
//     * 保存过账单
//     * @param userVO
//     * @param detailVO
//     */
//    @Override
//    public void saveInventoryPostOrder(UserVO userVO, InventoryForPostDetailVO detailVO) throws Exception {
//        Long enterpriseId = userVO.getEnterpriseId();
//        //盘点单
//        Inventory inventory = inventoryMapper.selectByPrimaryKey(detailVO.getId());
//
//        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO.getEnterpriseId());
//        if(mangeConfig.getBusinessControl() == 0){
//            //关闭
//            User user = userMapper.selectByPrimaryKey(detailVO.getPostManId());
//            inventory.setPostManId(user.getId());
//            inventory.setPostManCode(user.getCode());
//            inventory.setPostManName(user.getName());
//            inventory.setPostDate(detailVO.getPostDate());
//        } else {
//            //开启
//            inventory.setPostManId(userVO.getUserId());
//            inventory.setPostManCode(userVO.getUserCode());
//            inventory.setPostManName(userVO.getUserName());
//            inventory.setPostDate(detailVO.getPostDate());
//        }
//        // 盘点明细
//        List<InventoryDetailForPostVO> detailList = detailVO.getPostVOList();
//
//
//        for(InventoryDetailForPostVO post : detailList){
//            //单价计算
//            BigDecimal unitPrice = BigDecimal.ZERO;
//            BigDecimal noTaxPrice = BigDecimal.ZERO;//不含税单价
//            Long goodsId = post.getGoodsId();
//            Long lotId = post.getLotId();
//            Long shelfId = post.getShelfId();
//            BigDecimal quantity = post.getQuantity();//账面数量
//            BigDecimal invQuantity = post.getInvQuantity();//实盘数量
//
//            PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByGoodsIdAndEnterpriseId(goodsId, enterpriseId);
//            BigDecimal retailPrice = priceOrderDetail.getRetailPrice();//零售单价
//
//            //更新货位明细
//            InventoryShelf shelf = (InventoryShelf) EntityUtils.reflectUpdateSetDefaultValue(InventoryShelf.class,userVO);
//
//            if(quantity.compareTo(invQuantity) == -1){
//                //盘盈
//                /**
//                 * 不含税单价： 单价/(1+税率)
//                 */
//                unitPrice = post.getUnitPrice();
//                Long taxRateId = post.getTaxRateId();
//                GoodsTaxRate taxRate = goodsTaxRateMapper.selectByPrimaryKey(taxRateId);
//                //不含税单价
//                noTaxPrice = unitPrice.divide(new BigDecimal(100).add(taxRate.getTaxRate()),6,BigDecimal.ROUND_HALF_UP);
//                //计算金额
//                calculationAmount(unitPrice,noTaxPrice,quantity,invQuantity,retailPrice,post);
//                shelf.setUnitPrice(unitPrice);
//                shelf.setTaxRate(taxRate.getTaxRate());
//                shelf.setTaxRateId(taxRateId);
//                shelf.setNotaxPrice(noTaxPrice);
//
//            } else if (quantity.compareTo(invQuantity) == 1){
//                /**
//                 *  盘亏： 损溢金额/盘亏数量
//                 *  先进先出
//                 *  税率不显示
//                 */
//                //盘亏
//                //先进先出的商品信息
//                List<GoodsInfoPostVO> goodsStockForPost = stockMapper.getGoodsStockForPost(enterpriseId, goodsId, lotId);
//                BigDecimal diff = invQuantity.subtract(quantity);//损益数量
//                BigDecimal diffTotal = BigDecimal.ZERO;//损益金额
//                BigDecimal notaxDiffTotal = BigDecimal.ZERO;//不含税损益金额
//                for(GoodsInfoPostVO postVO : goodsStockForPost){
//                    BigDecimal goodsQuantity = postVO.getQuantity();
//                    BigDecimal realPrice = postVO.getRealPrice();
//                    BigDecimal notaxRealPrice = postVO.getNotaxRealPrice();//不含税实际单价
//                    if(diff.compareTo(BigDecimal.ZERO) == 1){
//                        //大于0继续与下一个商品数量相减
//                        diff = diff.subtract(goodsQuantity);
//                        //
//                        if(diff.compareTo(BigDecimal.ZERO) == -1 || diff.compareTo(BigDecimal.ZERO) == 0){
//                            diffTotal = diffTotal.add(diff.multiply(realPrice));
//                            notaxDiffTotal = notaxDiffTotal.add(diff.multiply(notaxRealPrice));
//                        } else {
//                            diffTotal = diffTotal.add(goodsQuantity.multiply(realPrice));
//                            notaxDiffTotal = notaxDiffTotal.add(goodsQuantity.multiply(notaxRealPrice));
//                        }
//                    }
//                }
//                //盘亏单价
//                unitPrice = diffTotal.divide(diff,6,BigDecimal.ROUND_HALF_UP);
//                noTaxPrice = notaxDiffTotal.divide(diff,6,BigDecimal.ROUND_HALF_UP);
//                calculationAmount(unitPrice,noTaxPrice,quantity,invQuantity,retailPrice,post);
//
//                shelf.setUnitPrice(unitPrice);
//                shelf.setTaxRate(null);
//                shelf.setTaxRateId(null);
//                shelf.setNotaxPrice(noTaxPrice);
//            } else {
//                /**
//                 *  无损溢
//                 *  税率不显示
//                 */
//                CostInfoPostVO costTotalQuantityAndAmount = costMapper.getCostTotalQuantityAndAmount(goodsId, lotId, enterpriseId);
//                BigDecimal quantityTotal = costTotalQuantityAndAmount.getQuantityTotal();
//                BigDecimal notaxRealAmountTotal = costTotalQuantityAndAmount.getNotaxRealAmountTotal();//不含税金额
//                BigDecimal realAmountTotal = costTotalQuantityAndAmount.getRealAmountTotal();//账面金额
//                unitPrice = realAmountTotal.divide(quantityTotal,6,BigDecimal.ROUND_HALF_UP);//单价
//                noTaxPrice = notaxRealAmountTotal.divide(quantityTotal,6,BigDecimal.ROUND_HALF_UP);//不含税单价
//                post.setUnitPrice(unitPrice);
//                post.setNotaxPrice(noTaxPrice);
//
//                post.setAmount(realAmountTotal);//账面金额
//                post.setRealAmount(realAmountTotal);//实盘金额
//                post.setDiffAmount(BigDecimal.ZERO);//损益金额
//
//                post.setNotaxAmount(notaxRealAmountTotal);//不含税账面金额
//                post.setRealNotaxAmount(notaxRealAmountTotal);//不含税实盘金额
//                post.setDiffNotaxAmount(BigDecimal.ZERO);//不含税损益金额
//                BigDecimal taxAmount = realAmountTotal.subtract(notaxRealAmountTotal);
//                post.setTaxAmount(taxAmount);//账面税额
//                post.setRealTaxAmount(taxAmount);//实盘税额
//                post.setDiffTaxAmount(BigDecimal.ZERO);//损益税额
//
//                BigDecimal retailAmount = retailPrice.multiply(quantity);
//
//                post.setRetailPrice(retailPrice);
//                post.setRetailAmount(retailAmount); //账面零售金额
//                post.setRealRetailAmount(retailAmount);//实盘零售金额
//                post.setDiffRetailAmount(BigDecimal.ZERO);//损益零售金额
//
//                shelf.setUnitPrice(unitPrice);
//                shelf.setTaxRate(null);
//                shelf.setTaxRateId(null);
//                shelf.setNotaxPrice(noTaxPrice);
//
//            }
//
//
//            shelf.setId(post.getInventoryShelfId());
//
//            shelf.setAmount(post.getAmount());
//            shelf.setRealAmount(post.getRealAmount());
//            shelf.setDiffAmount(post.getDiffAmount());
//
//            shelf.setNotaxAmount(post.getNotaxAmount());
//            shelf.setRealNotaxAmount(post.getRealNotaxAmount());
//            shelf.setDiffNotaxAmount(post.getDiffNotaxAmount());
//
//            shelf.setTaxAmount(post.getTaxAmount());
//            shelf.setRealTaxAmount(post.getRealTaxAmount());
//            shelf.setDiffTaxAmount(post.getDiffTaxAmount());
//
//            shelf.setRetailAmount(post.getRetailAmount());
//            shelf.setRealRetailAmount(post.getRealRetailAmount());
//            shelf.setDiffRetailAmount(post.getDiffRetailAmount());
//            shelf.setStatus(PubStatus.inventoryOrderStatus.FINISHED);
//            inventoryShelfMapper.updateByPrimaryKeySelective(shelf);
//
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
//
//        }
//
//        //转数据结构
//
//        Map<Long,List<InventoryShelfForPostVO>> map = new HashMap<>();
//        //转换数据结构
//        for (InventoryDetailForPostVO item : detailList) {
//            Long detailId = item.getId();//明细ID
//            if(map.get(detailId) == null){
//                List<InventoryShelfForPostVO> simpleVOList = new ArrayList<>();
//                packSimpleInventoryShelf(item, simpleVOList);
//                map.put(detailId,simpleVOList);
//            } else {
//                List<InventoryShelfForPostVO> simpleVOList = map.get(detailId);
//                packSimpleInventoryShelf(item, simpleVOList);
//                map.put(detailId,simpleVOList);
//            }
//        }
//
//        /**
//         * 账面金额
//         */
//        BigDecimal amountAllTotal = BigDecimal.ZERO;
//        /**
//         * 实盘金额
//         */
//        BigDecimal realAmountAllTotal= BigDecimal.ZERO;
//        /**
//         * 损益金额
//         */
//        BigDecimal diffAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 不含税账面金额
//         */
//        BigDecimal notaxAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 不含税实盘金额
//         */
//        BigDecimal realNotaxAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 不含税损益金额
//         */
//        BigDecimal diffNotaxAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 账面税额
//         */
//        BigDecimal taxAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 实盘税额
//         */
//        BigDecimal realTaxAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 损益税额
//         */
//        BigDecimal diffTaxAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 账面零售金额
//         */
//        BigDecimal retailAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 实盘零售金额
//         */
//        BigDecimal realRetailAmountAllTotal = BigDecimal.ZERO;
//        /**
//         * 损益零售金额
//         */
//        BigDecimal diffRetailAmountAllTotal = BigDecimal.ZERO;
//
//        //操作新数据结构
//        Iterator<Map.Entry<Long, List<InventoryShelfForPostVO>>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Long, List<InventoryShelfForPostVO>> entry = iterator.next();
//            Long detailId = entry.getKey();
//            List<InventoryShelfForPostVO> shelfForPostVOList = entry.getValue();
//
//            InventoryDetail inventoryDetail = inventoryDetailMapper.selectByPrimaryKey(detailId);
//            //金额统计
//            /**
//             * 账面金额
//             */
//            BigDecimal amountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 实盘金额
//             */
//            BigDecimal realAmountDetailTotal= BigDecimal.ZERO;
//            /**
//             * 损益金额
//             */
//            BigDecimal diffAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 不含税账面金额
//             */
//            BigDecimal notaxAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 不含税实盘金额
//             */
//            BigDecimal realNotaxAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 不含税损益金额
//             */
//            BigDecimal diffNotaxAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 账面税额
//             */
//            BigDecimal taxAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 实盘税额
//             */
//            BigDecimal realTaxAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 损益税额
//             */
//            BigDecimal diffTaxAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 账面零售金额
//             */
//            BigDecimal retailAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 实盘零售金额
//             */
//            BigDecimal realRetailAmountDetailTotal = BigDecimal.ZERO;
//            /**
//             * 损益零售金额
//             */
//            BigDecimal diffRetailAmountDetailTotal = BigDecimal.ZERO;
//
//            for(InventoryShelfForPostVO shelfForPostVO : shelfForPostVOList){
////                InventoryShelf inventoryShelf = (InventoryShelf) EntityUtils.reflectUpdateSetDefaultValue(InventoryShelf.class,userVO);
//
//                inventoryDetail.setUnitPrice(shelfForPostVO.getUnitPrice());
//                inventoryDetail.setTaxRateId(shelfForPostVO.getTaxRateId());
//                inventoryDetail.setTaxRate(shelfForPostVO.getTaxRate());
//                inventoryDetail.setNotaxPrice(shelfForPostVO.getNotaxPrice());
//                inventoryDetail.setRetailPrice(shelfForPostVO.getRetailPrice());
//
//                //金额统计
//                amountDetailTotal = amountDetailTotal.add(shelfForPostVO.getAmount());
//                realAmountDetailTotal = realAmountDetailTotal.add(shelfForPostVO.getRealAmount());
//                diffAmountDetailTotal = diffAmountDetailTotal.add(shelfForPostVO.getDiffAmount());
//
//                notaxAmountDetailTotal = notaxAmountDetailTotal.add(shelfForPostVO.getNotaxAmount());
//                realNotaxAmountDetailTotal = realNotaxAmountDetailTotal.add(shelfForPostVO.getRealNotaxAmount());
//                diffNotaxAmountDetailTotal = diffNotaxAmountDetailTotal.add(shelfForPostVO.getDiffNotaxAmount());
//
//                taxAmountDetailTotal = taxAmountDetailTotal.add(shelfForPostVO.getTaxAmount());
//                realTaxAmountDetailTotal = realTaxAmountDetailTotal.add(shelfForPostVO.getRealTaxAmount());
//                diffTaxAmountDetailTotal = diffTaxAmountDetailTotal.add(shelfForPostVO.getDiffTaxAmount());
//
//                retailAmountDetailTotal = retailAmountDetailTotal.add(shelfForPostVO.getRetailAmount());
//                realRetailAmountDetailTotal =  realRetailAmountDetailTotal.add(shelfForPostVO.getRealRetailAmount());
//                diffRetailAmountDetailTotal = diffRetailAmountDetailTotal.add(shelfForPostVO.getDiffRetailAmount());
//
////                BeanUtils.copyProperties(shelfForPostVO,inventoryShelf);
////                inventoryShelf.setStatus(PubStatus.inventoryOrderStatus.FINISHED);
////                inventoryShelfMapper.updateByPrimaryKeySelective(inventoryShelf);
//            }
//            inventoryDetail.setAmount(amountDetailTotal);
//            inventoryDetail.setRealAmount(realAmountDetailTotal);
//            inventoryDetail.setDiffAmount(diffAmountDetailTotal);
//
//            inventoryDetail.setNotaxAmount(notaxAmountDetailTotal);
//            inventoryDetail.setRealNotaxAmount(realNotaxAmountDetailTotal);
//            inventoryDetail.setDiffNotaxAmount(diffNotaxAmountDetailTotal);
//
//            inventoryDetail.setTaxAmount(taxAmountDetailTotal);
//            inventoryDetail.setRealTaxAmount(realTaxAmountDetailTotal);
//            inventoryDetail.setDiffTaxAmount(diffTaxAmountDetailTotal);
//
//            inventoryDetail.setRetailAmount(retailAmountDetailTotal);
//            inventoryDetail.setRealRetailAmount(realRetailAmountDetailTotal);
//            inventoryDetail.setDiffRetailAmount(diffRetailAmountDetailTotal);
//            inventoryDetail.setStatus(PubStatus.inventoryOrderStatus.FINISHED);
//            inventoryDetailMapper.updateByPrimaryKeySelective(inventoryDetail);
//
//            amountAllTotal = amountAllTotal.add(amountDetailTotal);
//            realAmountAllTotal = realAmountAllTotal.add(realAmountDetailTotal);
//            diffAmountAllTotal = diffAmountAllTotal.add(diffAmountDetailTotal);
//
//            notaxAmountAllTotal = notaxAmountAllTotal.add(notaxAmountDetailTotal);
//            realNotaxAmountAllTotal = realNotaxAmountAllTotal.add(realNotaxAmountDetailTotal);
//            diffNotaxAmountAllTotal = diffNotaxAmountAllTotal.add(diffNotaxAmountDetailTotal);
//
//            taxAmountAllTotal = taxAmountAllTotal.add(taxAmountDetailTotal);
//            realTaxAmountAllTotal = realTaxAmountAllTotal.add(realTaxAmountDetailTotal);
//            diffTaxAmountAllTotal = diffTaxAmountAllTotal.add(diffTaxAmountDetailTotal);
//
//            retailAmountAllTotal = retailAmountAllTotal.add(retailAmountDetailTotal);
//            realRetailAmountAllTotal = realRetailAmountAllTotal.add(realRetailAmountDetailTotal);
//            diffRetailAmountAllTotal = diffRetailAmountAllTotal.add(diffAmountDetailTotal);
//
//
//
//        }
//
//        inventory.setAmountTotal(amountAllTotal);
//        inventory.setRealAmountTotal(realAmountAllTotal);
//        inventory.setDiffAmountTotal(diffAmountAllTotal);
//
//        inventory.setNotaxAmountTotal(notaxAmountAllTotal);
//        inventory.setRealNotaxAmountTotal(realNotaxAmountAllTotal);
//        inventory.setDiffNotaxAmountTotal(diffNotaxAmountAllTotal);
//
//        inventory.setTaxAmountTotal(taxAmountAllTotal);
//        inventory.setRealTaxAmountTotal(realTaxAmountAllTotal);
//        inventory.setDiffTaxAmountTotal(diffTaxAmountAllTotal);
//
//        inventory.setRetailAmountTotal(retailAmountAllTotal);
//        inventory.setRealRetailAmountTotal(realRetailAmountAllTotal);
//        inventory.setDiffRetailAmountTotal(diffRetailAmountAllTotal);
//        inventory.setStatus(PubStatus.inventoryOrderStatus.FINISHED);
//        inventoryMapper.updateByPrimaryKeySelective(inventory);
//
//        List<InventoryShelf> inventoryShelfList =  inventoryShelfMapper.getIdInvIdDtlId(inventory.getId(),enterpriseId);
//        if(!CollectionUtils.isEmpty(inventoryShelfList)){
//            OrderModelBuilder builder = new OrderModelBuilder(userVO);
//            OrderModel orderModel = builder.buildOrderModel(OrderRule.INVENTORY, inventory,inventoryShelfList);
//            inOutComponent.generateKeyTableDatas(OrderDirection.INVENTORY, orderModel);
//        }
//
//    }
//
//
//
//
//    private void calculationAmount(BigDecimal unitPrice,BigDecimal notaxPrice,BigDecimal quantity,BigDecimal invQuantity,BigDecimal retailPrice,InventoryDetailForPostVO post){
//        post.setUnitPrice(unitPrice);
//        post.setNotaxPrice(notaxPrice);
//
//        BigDecimal amount = unitPrice.multiply(quantity);//账面金额
//        BigDecimal realAmount = unitPrice.multiply(invQuantity);//实盘金额
//        BigDecimal diffAmount = realAmount.subtract(amount);//损益金额
//
//        BigDecimal notaxAmount = notaxPrice.multiply(quantity);//不含税账面金额
//        BigDecimal notaxRealAmount = notaxPrice.multiply(invQuantity);//不含税实盘金额
//        BigDecimal notaxDiffAmount = notaxRealAmount.subtract(notaxAmount);//不含税损益金额
//
//
//        BigDecimal taxAmount = amount.subtract(notaxAmount);//账面税额
//        BigDecimal realTaxAmount = realAmount.subtract(notaxRealAmount);//实盘税额
//        BigDecimal diffTaxAmount = diffAmount.subtract(notaxDiffAmount);//损益税额
//
//
//        post.setAmount(amount);//账面金额
//        post.setRealAmount(realAmount);//实盘金额
//        post.setDiffAmount(diffAmount);//损益金额
//
//        post.setNotaxAmount(notaxAmount);//不含税账面金额
//        post.setRealNotaxAmount(notaxRealAmount);//不含税实盘金额
//        post.setDiffNotaxAmount(notaxDiffAmount);//不含税损益金额
//
//        post.setTaxAmount(taxAmount);//账面税额
//        post.setRealTaxAmount(realTaxAmount);//实盘税额
//        post.setDiffTaxAmount(diffTaxAmount);//损益税额
//
//        BigDecimal retailAmount = retailPrice.multiply(quantity);
//        BigDecimal realRetailAmount = retailPrice.multiply(invQuantity);
//        BigDecimal diffRetailAmount = realRetailAmount.subtract(retailAmount);
//        post.setRetailPrice(retailPrice);
//        post.setRetailAmount(retailAmount); //账面零售金额
//        post.setRealRetailAmount(realRetailAmount);//实盘零售金额
//        post.setDiffRetailAmount(diffRetailAmount);//损益零售金额
//    }
//
//
//    private void packInventoryInfo(UserVO userVO, InventoryForAddVO inventoryForAddVO, Inventory inventory) {
//        inventory.setEnterpriseId(userVO.getEnterpriseId());
//        inventory.setParentId(userVO.getParentId());
//
//        inventory.setCreateTime(inventoryForAddVO.getCreateTime());
//        inventory.setInvType(inventoryForAddVO.getInvType());
//        inventory.setInvRange(inventoryForAddVO.getInvRange());
//        Long warehouseId = inventoryForAddVO.getWarehouseId();
//        inventory.setWarehouseId(warehouseId);
//        Warehouse warehouse = warehouseMapper.selectByPrimaryKey(warehouseId);
//        inventory.setWarehouseName(warehouse.getName());
//
//
//        String warehouseAreaIds = inventoryForAddVO.getWarehouseAreaIds();
//        if(warehouseAreaIds != null){
//            String[] split = warehouseAreaIds.split(",");
//            StringBuilder areaNames = new StringBuilder();
//            for(String str : split){
//                WarehouseArea warehouseArea = warehouseAreaMapper.selectByPrimaryKey(Long.parseLong(str));
//                if(warehouseArea != null){
//                    areaNames.append(warehouseArea.getName()).append(",");
//                }
//            }
//            inventory.setWarehouseAreaIds(warehouseAreaIds);
//            inventory.setWarehouseAreaNames(areaNames.toString());
//        }
//
//        String cargoAreaIds = inventoryForAddVO.getCargoAreaIds();
//        if(cargoAreaIds != null){
//            String[] split = cargoAreaIds.split(",");
//            StringBuilder cargoAreaNames = new StringBuilder();
//            for(String str : split){
//                WarehouseCargoArea cargoArea = cargoAreaMapper.selectByPrimaryKey(Long.parseLong(str));
//                if(cargoArea != null){
//                    cargoAreaNames.append(cargoArea.getName()).append(",");
//                }
//            }
//            inventory.setCargoAreaIds(cargoAreaIds);
//            inventory.setCargoAreaNames(cargoAreaNames.toString());
//        }
//
//
//        inventory.setRemark(inventoryForAddVO.getRemark());
//    }
//
//
//
//    private void packGoodsShelf(UserVO userVO, Long enterpriseId, Inventory inventory, InventoryDetail detail, Long goodsId,Integer status,Set<String> goodsIdLotIdShelfIdSet,Map<String,BigDecimal> invQuantityMap) throws Exception {
//        List<GoodsInfoStockShelfVO> goodsStockInfoList = stockMapper.getGoodsStockInfoByGoodsId(enterpriseId, goodsId);
//        //商品货位明细
//        for(GoodsInfoStockShelfVO goodsShelfVO : goodsStockInfoList){
//            String goodsIdLotIdShelfId = goodsShelfVO.getGoodsId() +","+ goodsShelfVO.getLotNumberId() + "," +  goodsShelfVO.getShelfId();
//            if(goodsIdLotIdShelfIdSet.contains(goodsIdLotIdShelfId)){
//                InventoryShelf shelf =(InventoryShelf) EntityUtils.reflectAddSetDefaultValue(InventoryShelf.class,userVO);
//                shelf.setDtlId(detail.getId());
//                shelf.setInvId(inventory.getId());
//                shelf.setStatus(status);
//                packGoodsShelfInfo(goodsId, goodsShelfVO, shelf,invQuantityMap.get(goodsIdLotIdShelfId));
//                inventoryShelfMapper.insertSelective(shelf);
//            }
//        }
//    }
//
//    private void packGoodsShelfInfo(Long goodsId, GoodsInfoStockShelfVO goodsShelfVO, InventoryShelf shelf,BigDecimal invQuantity) {
//        shelf.setGoodsId(goodsId);
//        shelf.setGoodsCode(goodsShelfVO.getGoodsCode());
//        shelf.setGoodsName(goodsShelfVO.getGoodsName());
//        shelf.setLotId(goodsShelfVO.getLotNumberId());
//        shelf.setLotNumber(goodsShelfVO.getLotNum());
//        shelf.setValidDate(goodsShelfVO.getValidUtil());
//        shelf.setProductDate(goodsShelfVO.getProductDate());
//        shelf.setShelfId(goodsShelfVO.getShelfId());
//        shelf.setShelfName(goodsShelfVO.getShelfName());
//        shelf.setShelfStatusDesc(goodsShelfVO.getShelfStatusDesc());
//        shelf.setQuantity(goodsShelfVO.getQuantity());
//        shelf.setInvQuantity(invQuantity);//实盘数量
//        shelf.setDiffQuantity(invQuantity.subtract(goodsShelfVO.getQuantity()));
//    }
//
//    private void packGoodsInfo(InventoryDetail detail, Long goodsId) {
//        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
//        detail.setDosageId(goods.getDosageId());
//        detail.setDosageName(goods.getDosageName());
//        detail.setGoodsSpecification(goods.getSpecification());
//        detail.setBarcode(goods.getBarcode());
//        detail.setGoodsCode(goods.getCode());
//        detail.setGoodsGenericName(goods.getGenericName());
//        detail.setGoodsPlace(goods.getPlace());
//        detail.setManufacturer(goods.getManufacturer());
//        detail.setManufacturerId(goods.getManufacturerId());
//        detail.setApprovalNumber(goods.getApprovalNumber());
//        detail.setUnitId(goods.getUnitId());
//        detail.setUnitName(goods.getUnitName());
//        detail.setGoodsName(goods.getName());
//        detail.setGoodsId(goodsId);
//    }
//
//    private void packSimpleInventoryShelf(InventoryDetailForPostVO item, List<InventoryShelfForPostVO> simpleVOList) {
//        InventoryShelfForPostVO simpleVO = new InventoryShelfForPostVO();
//
//        simpleVO.setLotId(item.getLotId());
//        simpleVO.setLotNumber(item.getLotNumber());
//        simpleVO.setQuantity(item.getQuantity());
//        simpleVO.setInvQuantity(item.getInvQuantity());
//        simpleVO.setShelfId(item.getShelfId());
//        simpleVO.setShelfName(item.getShelfName());
//        simpleVO.setRetailPrice(item.getRetailPrice());
//        simpleVO.setUnitPrice(item.getUnitPrice());
//        simpleVO.setTaxRateId(item.getTaxRateId());
//        simpleVO.setTaxRate(item.getTaxRate());
//        simpleVO.setShelfStatusDesc(item.getShelfStatusDesc());
//        simpleVO.setProductDate(item.getProductDate());
//        simpleVO.setValidDate(item.getValidDate());
//        simpleVO.setId(item.getId());
//        simpleVO.setGoodsId(item.getGoodsId());
//        simpleVO.setGoodsCode(item.getGoodsCode());
//        simpleVO.setGoodsName(item.getGoodsName());
//
//
//
//        simpleVO.setAmount(item.getAmount());
//        simpleVO.setRealAmount(item.getRealAmount());
//        simpleVO.setDiffAmount(item.getDiffAmount());
//
//        simpleVO.setNotaxAmount(item.getNotaxAmount());
//        simpleVO.setRealNotaxAmount(item.getRealNotaxAmount());
//        simpleVO.setDiffNotaxAmount(item.getDiffNotaxAmount());
//
//        simpleVO.setTaxAmount(item.getTaxAmount());
//        simpleVO.setRealTaxAmount(item.getRealTaxAmount());
//        simpleVO.setDiffTaxAmount(item.getDiffTaxAmount());
//
//        simpleVO.setRetailAmount(item.getRetailAmount());
//        simpleVO.setRealRetailAmount(item.getRealRetailAmount());
//        simpleVO.setDiffRetailAmount(item.getDiffRetailAmount());
//
//        simpleVOList.add(simpleVO);
//    }
//
//    private void packSimpleInventoryShelf(InventoryShelfSimpleVO item, List<InventoryShelfSimpleVO> simpleVOList) {
//        InventoryShelfSimpleVO simpleVO = new InventoryShelfSimpleVO();
//        simpleVO.setInvQuantity(item.getInvQuantity());
//        simpleVO.setLotId(item.getLotId());
//        simpleVO.setLotNumber(item.getLotNumber());
//        simpleVO.setQuantity(item.getQuantity());
//        simpleVO.setShelfId(item.getShelfId());
//        simpleVO.setInventoryShelfId(item.getInventoryShelfId());
//        simpleVOList.add(simpleVO);
//    }
//
//    private void packSimpleInventoryShelf(InventoryDetailForAddVO item, List<InventoryShelfSimpleVO> simpleVOList) {
//        InventoryShelfSimpleVO simpleVO = new InventoryShelfSimpleVO();
//        simpleVO.setInvQuantity(item.getInvQuantity());
//        simpleVO.setLotId(item.getLotId());
//        simpleVO.setLotNumber(item.getLotNumber());
//        simpleVO.setQuantity(item.getQuantity());
//        simpleVO.setShelfId(item.getShelfId());
//        simpleVO.setGoodsId(item.getGoodsId());
//        simpleVO.setId(item.getId());
//        simpleVOList.add(simpleVO);
//
//    }
//
//    private void packSimpleInventoryShelf(InventoryDetailForRegisterVO item, List<InventoryShelfSimpleVO> simpleVOList) {
//        InventoryShelfSimpleVO simpleVO = new InventoryShelfSimpleVO();
//        simpleVO.setInvQuantity(item.getInvQuantity());
//        simpleVO.setLotId(item.getLotId());
//        simpleVO.setLotNumber(item.getLotNumber());
//        simpleVO.setQuantity(item.getQuantity());
//        simpleVO.setShelfId(item.getShelfId());
//        simpleVO.setGoodsId(item.getGoodsId());
//        simpleVOList.add(simpleVO);
//    }
//}