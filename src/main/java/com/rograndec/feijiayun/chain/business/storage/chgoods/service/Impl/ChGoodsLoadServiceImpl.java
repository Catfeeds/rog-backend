package com.rograndec.feijiayun.chain.business.storage.chgoods.service.Impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsLoadDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsLoadMapper;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsLoad;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsLoadDetail;
import com.rograndec.feijiayun.chain.business.storage.chgoods.service.ChGoodsLoadService;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChGoodsLoadServiceImpl implements ChGoodsLoadService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsLoadMapper goodsLoadMapper;
    @Autowired
    GoodsLoadDetailMapper goodsLoadDetailMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    LotNumberMapper lotNumberMapper;
    @Autowired
    WarehouseShelfMapper warehouseShelfMapper;
    @Autowired
    WarehouseCargoAreaMapper warehouseCargoAreaMapper;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent<ChGoodsLoadOrderDtlListOneVO> purchaseGeneralComponent;
    @Autowired
    InOutComponent inOutComponent;
    @Autowired
    FinanceComponent financeComponent;

    @Override
    public List<SimpleUserVO> getAuditMan(UserVO loginUser) {
        return userMapper.getSimpleUserVOByEnterpriseId(loginUser.getEnterpriseId(), 1);
    }

    @Override
    public List<ChGoodsLoadOrderListVO> getChGoodsLoadOrderList(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String code, String loadManName, String auditManName, String orderName, String orderType) {


        if (orderName != null && orderName.equals("loadDate")) {
            orderName = "load_date";
        } else {
            orderName = null;
            orderType = null;
        }

        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        }

        Long totalRecord = goodsLoadMapper.queryCountByParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, code, loadManName, auditManName);

        List<ChGoodsLoadOrderListVO> list = goodsLoadMapper.selectByParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, orderName, orderType, code, loadManName, auditManName);

        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list;
    }

    @Override
    public ChGoodsLoadOrderDtlListVO getChGoodsLoadOrderDtlList(Long enterpriseId, Long id) {

        ChGoodsLoadOrderDtlListVO list = goodsLoadMapper.selectByDtlParams(enterpriseId, id);
        List<ChGoodsLoadOrderDtlListOneVO> listVOList = goodsLoadDetailMapper.selectByDtlListParams(enterpriseId, id);
        list.setChGoodsLoadOrderDtlListOneVO(listVOList);

        return list;
    }

    @Override
    public void exportExcel(OutputStream output, ChGoodsLoadOrderDtlListVO chGoodsLoadOrderDtlListVO, UserVO loginUser) {
        //转换一下显示日期
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("manufacturer", "生产厂商");
        map.put("unitName", "单位");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validUntil", "有效期至");
        map.put("srcShelfName", "源货位");
        map.put("srcShelfStatusDesc", "源货位质量状况");
        map.put("targetShelfName", "目标货位");
        map.put("targetShelfStatusDesc", "目标货位质量状况");
        map.put("quantity", "数量");
        map.put("remark", "备注");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("装斗单号:");
        titleSecondRow.append(chGoodsLoadOrderDtlListVO.getCode() == null ? "" : chGoodsLoadOrderDtlListVO.getCode());
        titleSecondRow.append("  装斗日期:");
        titleSecondRow.append(chGoodsLoadOrderDtlListVO.getLoadDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(chGoodsLoadOrderDtlListVO.getLoadDate()));
        titleSecondRow.append("  装斗人员:");
        titleSecondRow.append(chGoodsLoadOrderDtlListVO.getLoadManName() == null ? "" : chGoodsLoadOrderDtlListVO.getLoadManName());
        titleSecondRow.append("  复核人员:");
        titleSecondRow.append(chGoodsLoadOrderDtlListVO.getAuditManName() == null ? "" : chGoodsLoadOrderDtlListVO.getAuditManName());
        titleSecond.add(titleSecondRow.toString());

        StringBuilder end = new StringBuilder();

        List<String> needTotalName = new ArrayList<>();

        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(goodsLoadMapper.selectByPrimaryKey(chGoodsLoadOrderDtlListVO.getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("中药装斗");
        purchaseGeneralComponent.commExcelExport(output, map, chGoodsLoadOrderDtlListVO.getChGoodsLoadOrderDtlListOneVO(), name, titleSecond, end.toString(), true, needTotalName);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int saveChGoodsLoadOrder(UserVO loginUser, SaveChGoodsLoadOrderVO saveChGoodsLoadOrderVO) throws Exception, BusinessException {

        SaveChGoodsLoadOrderHeadVO saveChGoodsLoadOrderHeadVO = saveChGoodsLoadOrderVO.getSaveChGoodsLoadOrderHeadVO();
        GoodsLoad goodsLoad = new GoodsLoad();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveChGoodsLoadOrderHeadVO, goodsLoad);
        UserEnterpriseUtils.setUserCreateOrModify(goodsLoad, loginUser, true);
        goodsLoad.setEnterpriseId(loginUser.getEnterpriseId());
        goodsLoad.setParentId(loginUser.getParentId());
        //set装斗人信息--start
        User user = userMapper.selectByPrimaryKey(goodsLoad.getLoadManId());
        if (user != null) {
            goodsLoad.setLoadManCode(user.getCode());
            goodsLoad.setLoadManName(user.getName());
        }
        //set复核人员信息--start
        User users = userMapper.selectByPrimaryKey(goodsLoad.getAuditManId());
        if (user != null) {
            goodsLoad.setAuditManCode(users.getCode());
            goodsLoad.setAuditManName(users.getName());
        }
        //--end
        goodsLoad.setQuantityTotal(BigDecimal.ZERO);
        goodsLoad.setVarietiesQuantity(0);
        //单号,单据类型
        goodsLoad.setOrderType(OrderRule.LOAD_BUCKET.getType());
        goodsLoad.setStatus(1);
        goodsLoad.setCode(getCode(OrderRule.LOAD_BUCKET.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        int mainId = goodsLoadMapper.insertSelective(goodsLoad);

        int reNum = saveDetial(loginUser, goodsLoad, saveChGoodsLoadOrderVO.getSaveChGoodsLoadOrderDtlVO());



        return reNum;

    }

    @Override
    public List<GoodsStockListVO> getGoodsStockList(Long enterpriseId, String param, Long type) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("enterpriseId", enterpriseId);
        map.put("param", param);
        List<GoodsStockListVO> conclusionVO = goodsLoadMapper.selectGoodsStockList(map);
        return conclusionVO == null ? new ArrayList<>() : conclusionVO;
    }

    @Override
    public GoodsShelfStatusDescVO getGoodsShelfStatusDesc(Long enterpriseId, Long shelfId) {

        String shelfStatusDesc = null;
        GoodsShelfStatusDescVO goodsShelfStatusDescVO = new GoodsShelfStatusDescVO();
        WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(shelfId);
        if (warehouseShelf != null) {
            Long warehouseCargoAreaId = warehouseShelf.getCargoAreaId();
            WarehouseCargoArea warehouseCargoArea = warehouseCargoAreaMapper.selectByPrimaryKey(warehouseCargoAreaId);
            if (warehouseCargoArea != null) {
                int jobType = warehouseCargoArea.getJobType();
                if (jobType == 0) {
                    int jobArea = warehouseCargoArea.getJobArea();
                    if (jobArea == 0) {
                        shelfStatusDesc = "合格";
                    } else if (jobArea == 2) {
                        shelfStatusDesc = "不合格";
                    }
                }
                goodsShelfStatusDescVO.setShelfStatusDesc(shelfStatusDesc);
            }
        }
        goodsShelfStatusDescVO.setId(shelfId);
        return goodsShelfStatusDescVO;
    }

    private int saveDetial(UserVO loginUser, GoodsLoad goodsLoad, List<SaveChGoodsLoadOrderDtlVO> saveChGoodsLoadOrderDtlVOs) throws Exception, BusinessException {

        if (saveChGoodsLoadOrderDtlVOs.size() > 0) {
            List<GoodsLoadDetail> goodsLoadDetailList = new ArrayList<>();
            for (SaveChGoodsLoadOrderDtlVO saveChGoodsLoadOrderDtlVO : saveChGoodsLoadOrderDtlVOs) {

//                if (judge(saveChGoodsLoadOrderDtlVOs)) {
//                    throw new BusinessException("存在相同的商品信息,无法保存");
//                }

                GoodsLoadDetail goodsLoadDetail = new GoodsLoadDetail();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveChGoodsLoadOrderDtlVO, goodsLoadDetail);
                if (goodsLoadDetail.getSrcShelfId().equals(goodsLoadDetail.getTargetShelfId())) {
                    throw new BusinessException("不允许源货位和目标货位一致,无法保存");
                }
                //set状态
                goodsLoadDetail.setStatus(1);
                //set单据id/code/类型/日期
                goodsLoadDetail.setLoadId(goodsLoad.getId());
                goodsLoadDetail.setLoadCode(goodsLoad.getCode());
                goodsLoadDetail.setOrderType(OrderRule.LOAD_BUCKET.getType());
                goodsLoadDetail.setLoadDate(goodsLoad.getLoadDate());
                UserEnterpriseUtils.setUserCreateOrModify(goodsLoadDetail, loginUser, true);
                //货品信息
                Goods goods = goodsMapper.selectByPrimaryKey(goodsLoadDetail.getGoodsId());
                goodsLoadDetail.setGoodsCode(goods.getCode());
                goodsLoadDetail.setBarcode(goods.getBarcode());
                goodsLoadDetail.setGoodsName(goods.getName());
                goodsLoadDetail.setGoodsGenericName(goods.getGenericName());
                goodsLoadDetail.setDosageId(goods.getDosageId());
                goodsLoadDetail.setDosageName(goods.getDosageName());
                goodsLoadDetail.setUnitId(goods.getUnitId());
                goodsLoadDetail.setUnitName(goods.getUnitName());
                goodsLoadDetail.setGoodsSpecification(goods.getSpecification());
                goodsLoadDetail.setManufacturerId(goods.getManufacturerId());
                goodsLoadDetail.setManufacturer(goods.getManufacturer());
                goodsLoadDetail.setGoodsPlace(goods.getPlace());
                goodsLoadDetail.setApprovalNumber(goods.getApprovalNumber());
                //批号信息
                LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(goodsLoadDetail.getLotId());
                goodsLoadDetail.setLotNumber(lotNumber.getLotNum());
                goodsLoadDetail.setProductDate(lotNumber.getProductDate());
                goodsLoadDetail.setValidDate(lotNumber.getValidUntil());
                //货区信息 源货位
                GoodsShelfStatusDescVO goodsShelfStatusDescVO = getGoodsShelfStatusDesc(loginUser.getEnterpriseId(), goodsLoadDetail.getSrcShelfId());
                WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(goodsLoadDetail.getSrcShelfId());
                goodsLoadDetail.setSrcShelfName(warehouseShelf.getName());

                goodsLoadDetail.setSrcShelfStatusDesc(goodsShelfStatusDescVO.getShelfStatusDesc());
                //货区信息 目标货位
                GoodsShelfStatusDescVO goodsShelfStatusDescVO2 = getGoodsShelfStatusDesc(loginUser.getEnterpriseId(), goodsLoadDetail.getTargetShelfId());
                WarehouseShelf warehouseShelf_s = warehouseShelfMapper.selectByPrimaryKey(goodsLoadDetail.getTargetShelfId());
                goodsLoadDetail.setTargetShelfName(warehouseShelf_s.getName());

                goodsLoadDetail.setTargetShelfStatusDesc(goodsShelfStatusDescVO2.getShelfStatusDesc());
                goodsLoadDetail.setEnterpriseId(loginUser.getEnterpriseId());
                goodsLoadDetail.setParentId(loginUser.getParentId());
                goodsLoadDetailMapper.insertSelective(goodsLoadDetail);

                //通过货品ID 和 原货位ID 和 批号ID 和 批次ID 查询库存信息
                Stock stock = stockMapper.getStockOrderByGoodsIdAndShelfIdAndLotId(loginUser.getEnterpriseId(), goodsLoadDetail.getGoodsId(), goodsLoadDetail.getSrcShelfId(), goodsLoadDetail.getLotId());
                //更新原有库存信息  只更新库存数量和可用数量
                BigDecimal quantity = stock.getQuantity();//库存数量
                BigDecimal usableQuantity = stock.getUsableQuantity();//可用数量
                BigDecimal qty = saveChGoodsLoadOrderDtlVO.getQuantity();//装斗数量
                double quantityQty = sub(quantity, qty);
                if (quantityQty < 0) {
                    throw new BusinessException("库存或可用库存不足,无法保存");
                }
                double quantityUsableQuantity = sub(usableQuantity, qty);
                if (quantityUsableQuantity < 0) {
                    throw new BusinessException("库存或可用库存不足,无法保存");
                }
                goodsLoadDetailList.add(goodsLoadDetail);

            }
            //调用公共方法生成关联数据
            OrderModelBuilder builder = new OrderModelBuilder(loginUser);
            OrderModel orderModel = builder.buildOrderModel(OrderRule.LOAD_BUCKET, goodsLoad, goodsLoadDetailList);
            inOutComponent.generateKeyTableDatas(OrderDirection.LOAD, orderModel);
            //更新总单中 数量合计 品种数量
            Double sumTotal = goodsLoadDetailMapper.selectSumTotal(loginUser.getEnterpriseId(), goodsLoad.getId());
            goodsLoad.setQuantityTotal(new BigDecimal(Double.toString(sumTotal)));
            goodsLoad.setVarietiesQuantity(saveChGoodsLoadOrderDtlVOs.size());
            goodsLoadMapper.updateByPrimaryKey(goodsLoad);

            //有关财务接口
            financeComponent.goodsLoadToBalanceAndVoucher(loginUser, goodsLoad);
        } else {
            throw new BusinessException("保存信息数据不全,无法保存");
        }
        return 1;
    }

    /**
     * 判断是否存在重复货品数据
     *
     * @param saveChGoodsLoadOrderDtlVOs
     * @return
     */
    private boolean judge(List<SaveChGoodsLoadOrderDtlVO> saveChGoodsLoadOrderDtlVOs) {

        String goodsids = "";
        for (int i = 0; i < saveChGoodsLoadOrderDtlVOs.size(); i++) {
            Long goodsidList = saveChGoodsLoadOrderDtlVOs.get(i).getGoodsId();
            goodsids += goodsidList + ",";
        }

        String[] array = goodsids.split(",");

        Map<Object, Object> map = new HashMap<Object, Object>();

        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {

                return true;
            } else {
                map.put(array[i], 1);
            }
        }
        return false;
    }

    //获取单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
    }

    /**
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2).doubleValue();
    }


    public static double add(BigDecimal v1, BigDecimal v2) {
        return v1.add(v2).doubleValue();
    }
}
